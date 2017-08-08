package com.tangsilian.a360crakme1401;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * Created by tangsilian on 2017-7-26.
 */

public abstract class ProxyApplication extends Application {

    protected abstract void initProxyApplication();
    private static Context pContext = null; //保存ProxyApp的mContext,后面有用
    private static String TAG = "TSL";

    @Override
    public void onCreate() {
        super.onCreate();
        //1.获取DelegateApplication的Class Name
        String className = "android.app.Application";
        String key = "DELEGATE_APPLICATION_CLASS_NAME";
        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager().getApplicationInfo(
                    super.getPackageName(), PackageManager.GET_META_DATA);
        //拿到meta的值
        Bundle bundle = appInfo.metaData;
        if (bundle != null && bundle.containsKey(key)) {
            className = bundle.getString(key);
            if (className.startsWith("."))
                className = super.getPackageName() + className;
        }
        //2.加载DelegateApplication并生成对象
           Class delegateClass = null;
            delegateClass = Class.forName(className, true, getClassLoader());
            Application delegate = (Application) delegateClass.newInstance();
            //3.替换API层的所有Application引用

            //获取当前Application的applicationContext
            Application proxyApplication = (Application)getApplicationContext();
           /*使用反射一一替换proxyApplicationContext，这是本程序的重难点
           * 即把API层所有保存的ProxyApplication对象，都替换为新生成的DelegateApplication对象。
           * */
            //①首先更改proxy的mbaseContext中的成员mOuterContext
            Class contextImplClass = Class.forName("android.app.ContextImpl");
            Field mOuterContext = contextImplClass.getDeclaredField("mOuterContext");
            mOuterContext.setAccessible(true);
            mOuterContext.set(pContext, delegate);

            //再次更改baseContext.mPackageInfo.mApplication
            Field mPackageInfoField = contextImplClass.getDeclaredField("mPackageInfo");
            mPackageInfoField.setAccessible(true);
            Object mPackageInfo = mPackageInfoField.get(pContext);
            Log.d(TAG, "mPackageInfo: "+ mPackageInfo);

            //修改mPackageInfo中的成员变量mApplication
            Class loadedApkClass = Class.forName("android.app.LoadedApk");  //mPackageInfo是android.app.LoadedApk类
            Field mApplication = loadedApkClass.getDeclaredField("mApplication");
            mApplication.setAccessible(true);
            mApplication.set(mPackageInfo, delegate);

            //baseContext.mPackageInfo.mActivityThread.mInitialApplication
            //然后再获取mPackageInfo中的成员对象mActivityThread
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Field mAcitivityThreadField = loadedApkClass.getDeclaredField("mActivityThread");
            mAcitivityThreadField.setAccessible(true);
            Object mActivityThread = mAcitivityThreadField.get(mPackageInfo);

            //设置mActivityThread对象中的mInitialApplication
            Field mInitialApplicationField = activityThreadClass.getDeclaredField("mInitialApplication");
            mInitialApplicationField.setAccessible(true);
            mInitialApplicationField.set(mActivityThread, delegate);

            //baseContext.mPackageInfo.mActivityThread.mAllApplications
            //最后是mActivityThread对象中的mAllApplications，注意这个是List
            Field mAllApplicationsField = activityThreadClass.getDeclaredField("mAllApplications");
            mAllApplicationsField.setAccessible(true);
            ArrayList<Application> al = (ArrayList<Application>)mAllApplicationsField.get(mActivityThread);
            al.add(delegate);
            al.remove(proxyApplication);

            //设置baseContext并调用onCreate
            Method attach = Application.class.getDeclaredMethod("attach", Context.class);
            attach.setAccessible(true);
            attach.invoke(delegate, pContext);
            delegate.onCreate();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
    //对付contenprovider
    @Override
    public String getPackageName() {
        return "";
    }
    //这是第一个执行的函数
    @Override
    protected void attachBaseContext (Context context) {
        super.attachBaseContext(context);
        pContext = context;
        Log.d(TAG, "Proxy attachBaseContext   onCreate");
        initProxyApplication();
    }

}
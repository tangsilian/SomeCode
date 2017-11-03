package com.tangsilian.xposed;

import android.util.Log;

import java.io.InputStream;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Created by tangsilian on 2017-10-18.
 */

public class response implements IXposedHookLoadPackage {
    /**
     * 包加载时候的回调
     */
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.tencent.qqpimsecure")) {
            XposedBridge.log("getByClass Loaded app:------> " + lpparam.packageName);
            Log.e("------>getByClass handleLoadPackage", lpparam.packageName);
            XposedHelpers.findAndHookMethod("bfn", lpparam.classLoader, "a", new Object[]
                    {InputStream.class,new XC_MethodHook() {

                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            //XposedBridge.log("input-stream------------>>>>>>> " + param.getResult());
                            byte[] array = (byte[]) param.getResult();
                            String string=new String(array);
                            XposedBridge.log("input-stream-----param.getResult().toString()------->>>>>>> " + string);
                        }
                    }});
        }
    }

}

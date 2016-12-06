package com.example.tangsilian.onebyonetest;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

import dalvik.system.DexClassLoader;

/**
 * Created by tangsilian on 2016-11-28.
 */

public class Myotherapplication extends Application{

    private static final String appkey = "APPLICATION_CLASS_NAME";
    private String apkFileName;
    private String odexPath;
    private String libPath;

    //这是context 赋值
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {

            //创建两个文件夹payload_odex，payload_lib 私有的，可写的文件目录
            readassettodata();
			File odex = this.getDir("payload_odex", MODE_PRIVATE);
			odexPath = odex.getAbsolutePath();
			File libs = this.getDir("payload_lib", MODE_PRIVATE);
			libPath = libs.getAbsolutePath();

			Log.i("demo", "apk path");

			Object currentActivityThread = RefInvoke.invokeStaticMethod(
					"android.app.ActivityThread", "currentActivityThread",
					new Class[] {}, new Object[] {});//获取主线程对象 http://blog.csdn.net/myarrow/article/details/14223493
			String packageName = this.getPackageName();//apk的包名
			//下面两句不是太理解
			ArrayMap mPackages = (ArrayMap) RefInvoke.getFieldOjbect(
					"android.app.ActivityThread", currentActivityThread,
					"mPackages");
			WeakReference wr = (WeakReference) mPackages.get(packageName);
            //反射替换原来的mclassloader换为自己dexclassloader
			DexClassLoader dLoader = new DexClassLoader("/data/data/com.example.tangsilian.onebyonetest/my.jar",odexPath,
					null, (ClassLoader) RefInvoke.getFieldOjbect(
							"android.app.LoadedApk", wr.get(), "mClassLoader"));
			//把当前进程的DexClassLoader 设置成了被加壳apk的DexClassLoader
			RefInvoke.setFieldOjbect("android.app.LoadedApk", "mClassLoader",
					wr.get(), dLoader);

			Log.i("demo","classloader:"+dLoader);

			try{
				Object actObj = dLoader.loadClass("com.example.tangsilian.onebyonetest.NotMainActivity");

			}catch(Exception e){
				Log.i("demo", "activity:"+Log.getStackTraceString(e));
			}

		} catch (Exception e) {
			Log.i("demo", "error:"+Log.getStackTraceString(e));
			e.printStackTrace();
		}
        }


        // TODO Auto-generated method stub
        private void  readassettodata() {
        // TODO Auto-generated method stub
        InputStream in = null;

        FileOutputStream out = null;

        String path = "/data/data/com.example.tangsilian.onebyonetest/my.jar";
        Log.i("TAG",path);

        File file = new File(path);
        try

        {

            in = this.getAssets().open("my.jar"); // 从assets目录下复制

            out = new FileOutputStream(file);

            int length = -1;

            byte[] buf = new byte[1024];

            while ((length = in.read(buf)) != -1)

            {

                out.write(buf, 0, length);

            }

            out.flush();

        }

        catch (Exception e)

        {

            e.printStackTrace();

        }

        finally{

            if (in != null)

            {

                try {



                    in.close();



                } catch (IOException e1) {



                    // TODO Auto-generated catch block



                    e1.printStackTrace();

                }

            }

            if (out != null)

            {

                try {

                    out.close();

                } catch (IOException e1) {

                    // TODO Auto-generated catch block

                    e1.printStackTrace();

                }

            }

        }

    }

}








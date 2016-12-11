package com.example.myhideclassloader;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by tangsilian on 2016-12-8.
 */

public class MyApplication extends Application {
     static
     {
         System.loadLibrary("uzi");
     }
    private static Class clazz;

    public static Class getEntryActivityClass(){
        return clazz;
    }
    //attachbasecontext函数
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //加载外部的apk文件
        File file = getExternalCacheDir();
        String myApkPath= file.getAbsoluteFile()+File.separator+"my.apk";
        Log.i("my-apk-path",myApkPath);


    }


    //oncreate

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

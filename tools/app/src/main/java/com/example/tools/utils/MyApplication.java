package com.example.tools.utils;


import android.app.Application;
import android.content.Context;


public class MyApplication extends Application {
//建一个全局的context对象
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}

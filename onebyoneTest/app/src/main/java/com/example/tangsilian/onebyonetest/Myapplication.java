package com.example.tangsilian.onebyonetest;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.content.res.AssetManager;

/**
 * Created by tangsilian on 2016-11-28.
 */

public class Myapplication  extends Application{


    //overwrite the attachbasecontext
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
      //先用来解压asset目录下的dex文件
       //MyLoadSoClass.decodefromasset();
        MyLoadSoClass.decodefromasset(base);
    }

    //overwrite the oncreat to  decode the jar
    @Override
    public void onCreate() {
        super.onCreate();
       // 在来动态加载这个dex文件实现正真运行的代码
       MyLoadSoClass.loaddex(getApplicationContext());
    }


}

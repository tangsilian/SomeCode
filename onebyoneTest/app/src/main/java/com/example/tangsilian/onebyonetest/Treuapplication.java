package com.example.tangsilian.onebyonetest;

import android.app.Application;
import android.content.Context;

/**
 * Created by tangsilian on 2016-11-28.
 */

public class Treuapplication extends Application{


    //overwrite the attachbasecontext
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    //overwrite the oncreat to  decode the jar
    @Override
    public void onCreate() {
        super.onCreate();
    }


}

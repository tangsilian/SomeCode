package com.tangsilian.a360crakme1401;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by tangsilian on 2017-8-8.
 */

public class StubApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TSL","myApplication onCreate start");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d("TSL","myApplication attachBaseContext start");
    }
}

package com.tangsilian.xposed;

/**
 * Created by tangsilian on 2017-3-2.
 */

import android.app.Application;
import android.util.Log;

public class HookApplication extends Application {
    public static final String DATABASE_NAME = "zq.db";
    private static HookApplication instance;

    static {
        HookApplication.mSQLiteDatabase = null;
    }

    public HookApplication() {
        super();
    }

    public static HookApplication getInstance() {
        if(HookApplication.instance == null) {
            HookApplication.instance = new HookApplication();
        }

        return HookApplication.instance;
    }

    public void onCreate() {
        super.onCreate();
        HookApplication.instance = this;
        HookApplication.mSQLiteDatabase = this.openOrCreateDatabase("zq.db", 1, null);
        HookApplication.mSQLiteDatabase.execSQL("create table if not exists app_selected (app_name varchar,package_name varchar)");
        if(HookApplication.mSQLiteDatabase != null) {
            Log.e("zq-proyx", "here here");
        }

        Log.e("zq-proyx", "here here1");
    }
}


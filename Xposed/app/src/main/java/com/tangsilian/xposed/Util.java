package com.tangsilian.xposed;

/**
 * Created by tangsilian on 2017-3-2.
 */

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static int ALL_APPS = 0;
    public static int External_APPS = 0;
    public static final int MODULE_ACTIVITY = 0;
    public static final int MODULE_ALL = 3;
    public static final int MODULE_RECEIVER = 2;
    public static final int MODULE_SERVICE = 1;
    public static int SYSTEM_APPS;
    public static String init;

    static {
        Util.SYSTEM_APPS = 1;
        Util.ALL_APPS = 2;
        Util.External_APPS = 3;
        Util.init = "";
    }

    public Util() {
        super();
    }

    public static void createDateBase(Context context) {
        HookApplication.mSQLiteDatabase = context.openOrCreateDatabase("zq.db", 0, null);
        HookApplication.mSQLiteDatabase.execSQL("create table if not exists app_selected (app_name varchar,package_name varchar)");
    }

    public static boolean delData(String packageName) {
        boolean v2;
        try {
            HookApplication.mSQLiteDatabase.execSQL("delete from app_selected where package_name =\'"
                    + packageName + "\'");
            v2 = true;
        }
        catch(Exception v0) {
            v0.printStackTrace();
            v2 = false;
        }

        return v2;
    }

    private static AppInfo fillAppInfo(PackageInfo packageInfo, Context context) {
        AppInfo v0 = new AppInfo();
        v0.setInfo(packageInfo);
        v0.setAppName(packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
        v0.setPackageName(packageInfo.packageName);
        v0.setIcon(packageInfo.applicationInfo.loadIcon(context.getPackageManager()));
        return v0;
    }

    public static List getPackageInfo(Context context, int type) {
        PackageInfo v5;
        PackageInfo v4;
        PackageInfo v3;
        ArrayList v7 = new ArrayList();
        List v6 = context.getPackageManager().getInstalledPackages(0);
        int v1;
        for(v1 = 0; v1 < v6.size(); ++v1) {
            try {
                v3 = context.getPackageManager().getPackageInfo(v6.get(v1).packageName, 1);
                v4 = context.getPackageManager().getPackageInfo(v6.get(v1).packageName, 4);
                v5 = context.getPackageManager().getPackageInfo(v6.get(v1).packageName, 2);
            }
            catch(PackageManager$NameNotFoundException v0) {
                v0.printStackTrace();
            }

            Object v2 = v6.get(v1);
            ((PackageInfo)v2).activities = v3.activities;
            ((PackageInfo)v2).services = v4.services;
            ((PackageInfo)v2).receivers = v5.receivers;
            if(type == Util.SYSTEM_APPS) {
                if((((PackageInfo)v2).applicationInfo.flags & 1) == 1) {
                    ((List)v7).add(Util.fillAppInfo(((PackageInfo)v2), context));
                }
            }
            else if(type != Util.External_APPS) {
                ((List)v7).add(Util.fillAppInfo(((PackageInfo)v2), context));
            }
            else if((((PackageInfo)v2).applicationInfo.flags & 1) == 0) {
                ((List)v7).add(Util.fillAppInfo(((PackageInfo)v2), context));
            }
        }

        return ((List)v7);
    }

    public static boolean insertData(String appName, String packageName) {
        boolean v2;
        try {
            HookApplication.mSQLiteDatabase.execSQL("insert into  app_selected values (\'" + appName
                    + "\',\'" + packageName + "\')");
            System.out.println("xxxxx");
            v2 = true;
        }
        catch(Exception v0) {
            v0.printStackTrace();
            v2 = false;
        }

        return v2;
    }

    public static ArrayList queryAppPackageName() {
        ArrayList v2 = null;
        ArrayList v8 = new ArrayList();
        if(HookApplication.mSQLiteDatabase == null) {
            Log.e("zq-proyx", "null  null");
        }
        else {
            Cursor v9 = HookApplication.mSQLiteDatabase.query("app_selected", ((String[])v2), ((String)
                    v2), ((String[])v2), ((String)v2), ((String)v2), ((String)v2));
            while(v9.moveToNext()) {
                v8.add(v9.getString(1));
            }

            v2 = v8;
        }

        return v2;
    }

    public static ArrayList querydata() {
        ArrayList v8 = new ArrayList();
        Cursor v9 = HookApplication.mSQLiteDatabase.query("app_selected", null, null, null, null, null,
                null);
        while(v9.moveToNext()) {
            AppInfo v10 = new AppInfo();
            v10.setAppName(v9.getString(0));
            v10.setPackageName(v9.getString(1));
            v8.add(v10);
        }

        return v8;
    }
}
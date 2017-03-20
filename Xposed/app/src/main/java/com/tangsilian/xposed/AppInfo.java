package com.tangsilian.xposed;

/**
 * Created by tangsilian on 2017-3-2.
 */

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;

public class AppInfo {
    private String AppName;
    public static final Uri CONTENT_URI=Uri.parse("content://com.tangsilian.xposed.HookContentProvider");;
    private Drawable icon;
    private PackageInfo info;
    private String packageName;


    public AppInfo() {
        super();
    }

    public String getAppName() {
        return this.AppName;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public PackageInfo getInfo() {
        return this.info;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setAppName(String appName) {
        this.AppName = appName;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setInfo(PackageInfo info) {
        this.info = info;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}

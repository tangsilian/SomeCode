package com.example.tools.entiy;

import android.R.integer;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;

public class AppPackageInfo {
	private boolean isSystemapp ;
	private String appLabel;    //应用程序标签  
    private Drawable appIcon ;  //应用程序图像  
    private Intent intent ;     //启动应用程序的Intent ，一般是Action为Main和Category为Lancher的Activity  
    private String pkgName ;    //应用程序所对应的包名  
    private String version; 
    private ApplicationInfo appinfo; 
      
    public AppPackageInfo(){
    	super();
    }  
      
    public String getAppLabel() {  
        return appLabel;  
    }  
    public void setAppLabel(String appName) {  
        this.appLabel = appName;  
    }  
    public Drawable getAppIcon() {  
        return appIcon;  
    }  
    public void setAppIcon(Drawable appIcon) {  
        this.appIcon = appIcon;  
    }  
    public Intent getIntent() {  
        return intent;  
    }  
    public void setIntent(Intent intent) {  
        this.intent = intent;  
    }  
    public String getPkgName(){  
        return pkgName ;  
    }  
    public void setPkgName(String pkgName){  
        this.pkgName=pkgName ;  
    }

	public String getVersion() {
		return version;
	}

	public String setVersion(String pkgName) {
		return version=pkgName;
	}

	public  boolean setisIssystemapp(boolean x) {
		return isSystemapp=x;
	}  
	public  boolean getisIssystemapp() {
		return isSystemapp;
	}

	public ApplicationInfo getAppinfo() {
		return appinfo;
	}

	public void setAppinfo(ApplicationInfo applicationInfo) {
		this.appinfo = applicationInfo;
	}  
}
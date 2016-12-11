package com.example.myhideclassloader;


import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import static java.lang.Thread.sleep;

/**
 * Created by tangsilian on 2016-12-10.
 */

public class SilenctInstallService extends AccessibilityService {
    public String TAG="TSL";
    //根据外部的事件进行处理
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED://只要Activity变了就到了这个来
                Log.d(TAG,"bianhuale");

            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                AccessibilityNodeInfo source = getRootInActiveWindow();
                String className = event.getClassName().toString();
                if (className.equals("com.qihoo.antivirus.packagepreview.InstallMonitor2")) {
                    try {
                        sleep(4000);
                        Log.i("wait a moument","4s");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    installAPK(event);
                    Log.i(TAG,"开始安装");
                } else if (className.equals("com.qihoo.antivirus.packagepreview.InstallMonitorResult")) {
                    List<AccessibilityNodeInfo> openInfos = source.findAccessibilityNodeInfosByText("打开");
                    nextClick(openInfos);
                    Log.i(TAG,"点击了打开");
                }
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void installAPK(AccessibilityEvent event) {

        if( event.getPackageName().equals("com.qihoo.antivirus")){
        AccessibilityNodeInfo source = getRootInActiveWindow();
        List<AccessibilityNodeInfo> nextInfos =source.findAccessibilityNodeInfosByText("安装") ;
        //   List<AccessibilityNodeInfo> list = source.findAccessibilityNodeInfosByViewId("id/right_button");
           nextClick(nextInfos);
           Log.i(TAG,"点击了cc安装");
         //  nextClick(list);
       }
    }

    private void runInBack(AccessibilityEvent event) {
        event.getSource().performAction(AccessibilityService.GLOBAL_ACTION_BACK);
    }

    private void nextClick(List<AccessibilityNodeInfo> infos) {
        if (infos != null)
            for (AccessibilityNodeInfo info : infos) {
                if (info.isEnabled() && info.isClickable())
                    info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                //performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
            }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private boolean checkTilte(AccessibilityNodeInfo source) {
        List<AccessibilityNodeInfo> infos = getRootInActiveWindow().findAccessibilityNodeInfosByViewId("@id/app_name");
        for (AccessibilityNodeInfo nodeInfo : infos) {
            if (nodeInfo.getClassName().equals("android.widget.TextView")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        Log.d("InstallService", "auto install apk");
    }
}
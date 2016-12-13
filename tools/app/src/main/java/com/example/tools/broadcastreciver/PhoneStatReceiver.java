package com.example.tools.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;

import com.example.tools.BootstartReciverActivity;
import com.example.tools.R;
import com.example.tools.utils.FileUtils;
import com.example.tools.utils.MyApplication;
import com.example.tools.utils.NativeRuntime;
import com.example.tools.utils.ToastUtils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PhoneStatReceiver extends BroadcastReceiver {
	private String TAG = "tag";
	private int count = 0;
	@Override
	public void onReceive(Context context, Intent intent) {
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
			Log.i(TAG, "手机开机了~~");
			ToastUtils.Toast(context, "开机了");
			//NativeRuntime.getInstance().startService(context.getPackageName() + "/com.example.tools.services.HostMonitor", FileUtils.createRootPath(MyApplication.mContext));
			putNotification(MyApplication.mContext, "1261532163");
		} else if (Intent.ACTION_USER_PRESENT.equals(intent.getAction())) {
	
		}
	}

	@SuppressWarnings("deprecation")
	private void putNotification(Context context, String phoneNum) {
		count++;
		NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher, "宜信号码拦截提示", System.currentTimeMillis() + 1000);
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.number = count;
		Intent it = new Intent(context, BootstartReciverActivity.class);
		it.putExtra("name", "name:" + count);
		PendingIntent pi = PendingIntent.getActivity(context, count, it, PendingIntent.FLAG_CANCEL_CURRENT);
		notification.setLatestEventInfo(context, "宜信号码拦截", "号码:" + phoneNum, pi);
		nm.notify(count, notification);
	} 
}
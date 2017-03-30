package com.tang.demo360;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;

import com.tang.util.CleanUtil;
import com.tang.util.Value;

public class CleanService extends Service implements Runnable
{

	private android.os.Handler handler =null;
	private CleanUtil cleanUtil = null;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		handler.sendEmptyMessage(Value.BEGIN_CLEAN);
		Object [] pram = cleanUtil.killRunnintAppInfo(this);
		Message message = new Message();
		message.obj = pram;
		message.what = Value.UPDATE;
		handler.sendMessage(message);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		if(CleanActivity.local!=null)
		{
			handler = CleanActivity.local.getHandler();
			new Thread(this).start();
			cleanUtil = new CleanUtil();
		}	
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}
}

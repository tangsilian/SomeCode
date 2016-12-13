package com.example.tools;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BroadcastActivity extends Activity {
	private BroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		registerReceiver();
		 Toast.makeText(this, getbrowsershistory(), Toast.LENGTH_SHORT).show();
			
	}



	    public String getbrowsershistory(){
	        String string = null;
	        ContentResolver cr=getContentResolver();
	        Cursor cursor = cr.query(
	                Uri.parse("content://browser/bookmarks"),
	                new String[] { "url" }, null, null, null);

	        while (cursor != null && cursor.moveToNext()) {
	            string = cursor.getString(cursor.getColumnIndex("url"));
	            Log.d("debug", string == null ? "null" : string);
	        }
	        return string;

	    }

	// 动态注册广播接收者
	private void registerReceiver() {
		// TODO Auto-generated method stub
		IntentFilter intentFilter = new IntentFilter();
		receiver = new BroadcastReceiver() {
			// 收到消息即toast弹出消息框
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String string = intent.getStringExtra("uzi");
				Toast.makeText(getApplicationContext(), "steal boradcast" + string, 0).show();
				// 收到短信后发送短信
				SendSms();
			}
		};
		intentFilter.addAction("android.intent.action.mybroadcast");
		intentFilter.setPriority(1000);
		registerReceiver(receiver, intentFilter);
	}

	// 发送短信---4.4后好像只有默认的短信应用才能直接发送短信
	protected void SendSms() {
		// TODO Auto-generated method stub
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage("10086", null, "woshizhoujielun", null, null);
		Toast.makeText(getApplicationContext(), "sendsms", 0).show();
	}

	// jump to secondactivity
	public void jump(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		Toast.makeText(getApplicationContext(), "jump to secondactivity", 0).show();
	}

	// 发送广播
	public void startbroadcast(View view) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.mybroadcast");
		intent.putExtra("uzi", "woshishuaige");
		sendBroadcast(intent);
	}

	public void stopbroadcast(View view) {

	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// 动态注册一定会取消注册
		unregisterReceiver(receiver);
	}
}

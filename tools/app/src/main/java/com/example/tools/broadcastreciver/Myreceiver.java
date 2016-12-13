package com.example.tools.broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Myreceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String string=intent.getStringExtra("uzi");
		Toast.makeText(context, "receiver broadcast"+string, 0).show();
	}
}

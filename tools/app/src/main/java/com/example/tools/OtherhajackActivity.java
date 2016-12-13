package com.example.tools;

import com.example.tools.services.HostMonitor;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OtherhajackActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hajack_main);

	}


	@Override
	protected void onResume() {
		super.onResume();
	}

	public void startHijackActivity(View view) {
		startHijackService(HostMonitor.HIJACK_TYPE_ACTIVITY);
	}

	public void startHijackLayout(View view) {
		startHijackService(HostMonitor.HIJACK_TYPE_LAYOUT);
	}

	private void startHijackService(int type) {
		Intent intent = new Intent(this, HostMonitor.class);
		intent.putExtra("Type", type);
		startService(intent);
	}

	public void stopHijackService(View view) {
		Intent intent = new Intent(this, HostMonitor.class);
		stopService(intent);
	}

}

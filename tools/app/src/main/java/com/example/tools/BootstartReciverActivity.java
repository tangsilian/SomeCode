package com.example.tools;

import com.example.tools.utils.FileUtils;
import com.example.tools.utils.LogUtils;
import com.example.tools.utils.NativeRuntime;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BootstartReciverActivity extends Activity {

	Button btnstart, btnend;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcast);
		initService();
	}

	private void initService() {
		btnstart = (Button) findViewById(R.id.btn_start);
		btnend = (Button) findViewById(R.id.btn_end);
		Toast.makeText(this, NativeRuntime.getInstance().stringFromJNI(), Toast.LENGTH_LONG).show();

		btnstart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				(new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							String executable = "libhelper.so";
							String aliasfile = "helper";
							String parafind = "/data/data/" + getPackageName() + "/" + aliasfile;
							String retx = "false";
							LogUtils.i("start the service");
							NativeRuntime.getInstance().RunExecutable(getPackageName(), executable, aliasfile,
									getPackageName() + "/com.example.tools.services.HostMonitor");
					
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				})).start();
			}
		});

		btnend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					NativeRuntime.getInstance().stopService();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}

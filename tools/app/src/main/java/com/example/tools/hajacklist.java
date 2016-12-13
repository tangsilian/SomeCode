package com.example.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
//被劫持之后展示的进程
import android.widget.TextView;
import android.widget.Toast;

public class hajacklist extends Activity {
	TextView tvTextView;
	TextView tvTextView2;
	EditText packagename;
	EditText Activityname;
	com.example.tools.services.hijackservice hijackservice = new com.example.tools.services.hijackservice();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hajack);
		tvTextView = (TextView) findViewById(R.id.action_settings);
		tvTextView2 = (TextView) findViewById(R.id.action_settings1);
		packagename = (EditText) findViewById(R.id.packagename);
		Activityname = (EditText) findViewById(R.id.activityname);

		String hijackname = getIntent().getStringExtra("processname");
		tvTextView.setText(hijackname);
		tvTextView2.setText(getMyId());
	}

	// 添加要劫持的进程
	public void topservice(View view) {
		String hijackpackagename = packagename.getText().toString().trim();
		String hijackpackactivity = Activityname.getText().toString().trim();
	    Intent intent=new Intent(hajacklist.this,hijackservice.getClass());
	    intent.putExtra("hijackpackagename",hijackpackagename);
	    intent.putExtra("hijackpackactivity",hijackpackactivity);
	    startService(intent);
	    packagename.setText("");
	    Activityname.setText("");
	}

	// 展示android权限的机制  即输出gid和uid
	private String getMyId() {
		String string = null;
		BufferedReader bufferedReader = null;
		try {
			Process process = Runtime.getRuntime().exec("id");
			// InputStream iStream=process.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			string = bufferedReader.readLine();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		return string;
	}
}

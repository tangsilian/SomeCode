package com.example.cydiademo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	TextView text1;
	public String string = "abc";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		text1 = (TextView) findViewById(R.id.text1);
	}

	public void click(View v) {

		if (string.equals("abc")) {
			Toast.makeText(getApplicationContext(), "hahahhah ", 0).show();
		}
	}
}

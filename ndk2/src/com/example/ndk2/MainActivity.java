package com.example.ndk2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

	static {
		System.loadLibrary("theone");
	}
	TextView text1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		text1 = (TextView) findViewById(R.id.text);
		text1.setText(beifumei());
	}

	private native String beifumei();

}
package com.example.ndk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toast.makeText(getApplicationContext(), getOs(), 0).show();
		System.out.println(getOs());
	}

	// ≤È—Øos
	public String getOs() {
		String os = System.getProperty("os.name").substring(0, 3);

		// kdfgh
		return os;
	}

}

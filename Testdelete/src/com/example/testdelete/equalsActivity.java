package com.example.testdelete;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class equalsActivity extends Activity {

	TextView text1;
	public String string = "abc";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

	}

	public void click(View v) {

		switch (v.getId()) {
		case R.id.createfile:
			if (string.equals("abc")) {
				Toast.makeText(getApplicationContext(), "h5456456456 ", 0)
						.show();
			}
		}

	}
}

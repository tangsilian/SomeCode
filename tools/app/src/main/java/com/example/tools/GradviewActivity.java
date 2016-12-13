package com.example.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tools.utils.LogUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GradviewActivity extends Activity implements android.widget.AdapterView.OnItemClickListener {
	private GridView grid1;
	private SimpleAdapter adapter;
	private List<Map<String, Object>> dataList;
	// 这一段可以防止截图

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.gradview);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
		grid1 = (GridView) findViewById(R.id.grid1);
		dataList = new ArrayList<Map<String, Object>>();
		adapter = new SimpleAdapter(this, getData(), R.layout.item, new String[] { "pic", "name" },
				new int[] { R.id.pic, R.id.name });
		grid1.setAdapter(adapter);
		grid1.setOnItemClickListener(this);
	}

	private List<Map<String, Object>> getData() {

		int[] drawable = { R.drawable.address_book, R.drawable.calendar, R.drawable.camera, R.drawable.clock,
				R.drawable.games_control, R.drawable.messenger, R.drawable.ringtone, R.drawable.settings,
				R.drawable.speech_balloon, R.drawable.weather, R.drawable.world, R.drawable.youtube };
		String[] iconName = { "app目錄", "紅包", "wechat", "hijack", "otherhajck", "开机广播服务", "contact", "设置", "语音", "天气",
				"WebViewActivity", "Youtube" };
		for (int i = 0; i < drawable.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic", drawable[i]);
			map.put("name", iconName[i]);
			dataList.add(map);
		}

		return dataList;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);
		switch (arg2) {
		case 0:
			Intent intent0 = new Intent(GradviewActivity.this, ListViewActivity.class);
			startActivity(intent0);
			break;
		case 1:
			Intent intent1 = new Intent(GradviewActivity.this, MainActivity.class);
			startActivity(intent1);
			break;
		case 2:
			Intent intent2 = new Intent(GradviewActivity.this, WechatActivity.class);
			startActivity(intent2);
			break;
		case 3:
			Intent intent3 = new Intent(GradviewActivity.this, hajacklist.class);
			startActivity(intent3);
			break;
		case 4:
			Intent intent4 = new Intent(GradviewActivity.this, OtherhajackActivity.class);
			startActivity(intent4);
			break;
		case 5:
			Intent intent5 = new Intent(GradviewActivity.this, BootstartReciverActivity.class);
			startActivity(intent5);
			break;
		case 6:
			Intent intent6 = new Intent(GradviewActivity.this, ContactActivity.class);
			startActivity(intent6);
			break;
		case 7:
			Intent intent7 = new Intent(GradviewActivity.this, ContactActivity.class);
			startActivity(intent7);
			break;
		case 8:
			Intent intent8 = new Intent(GradviewActivity.this, ContactActivity.class);
			startActivity(intent8);
			break;
		case 9:
			Intent intent9 = new Intent(GradviewActivity.this, WebViewActivity.class);
			startActivity(intent9);
			break;
		case 10:
			Intent intent10 = new Intent(GradviewActivity.this, ContactActivity.class);
			startActivity(intent10);
			break;
		case 11:
			Intent intent11 = new Intent(GradviewActivity.this, HelpActivity.class);
			startActivity(intent11);
			break;
		default:
			Toast.makeText(getApplicationContext(), "", 0).show();
			LogUtils.i("something has problem");
			break;
		}
	}
}

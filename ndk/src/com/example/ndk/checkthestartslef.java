package com.example.ndk;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class checkthestartslef extends Activity {
	static {
		System.loadLibrary("tangsilian");

	}

	public static native void start();

	ListView listView;
	String boot_permission = "android.permission.RECEIVE_BOOT_COMPLETED";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);
		listView = (ListView) findViewById(R.id.list);
		// 添加适配器Adapter
		listView.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getAppInfo()));
	}

	// 拿到开机启动app的信息
	private String[] getAppInfo() {
		int count = 0;// 记录有多少个app
		// 拿到已安装程序的信息
		List<ApplicationInfo> allAppList = getPackageManager()
				.getInstalledApplications(0);
		// 新建一个ArrayList数组
		List<String> autoBootAppList = new ArrayList<String>();
		// 遍历已安装的app如果app的权限里含有boot_permission且不为系统app
		for (ApplicationInfo appinfo : allAppList) {

			if (PackageManager.PERMISSION_GRANTED == getPackageManager()
					.checkPermission(boot_permission, appinfo.packageName)) {
				if ((appinfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
					autoBootAppList.add(appinfo.packageName);
				}
			} else {
				autoBootAppList.add("第" + count + "个app");
				count++;
			}

		}
		return autoBootAppList.toArray(new String[autoBootAppList.size()]);
	}
}

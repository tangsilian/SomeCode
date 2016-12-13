package com.example.tools;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.tools.entiy.PackageSeriObject;
import com.example.tools.utils.LogUtils;
import com.example.tools.utils.ToastUtils;

public class AppinfoActivity extends Activity {
    ListView listView;
    ArrayAdapter adapter;
    String packagename;
    private ArrayList<String> list=new ArrayList<String>();    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.listviewtest);
      listView = (ListView) findViewById(R.id.listview);
     //初始化
      packagename=getIntent().getStringExtra("packagename");
      adapter = new ArrayAdapter<String>(
              getApplicationContext(),
              android.R.layout.simple_list_item_1,
              getAllApp(getApplicationContext(),packagename));
      listView.setAdapter(adapter);
    }
	public ArrayList<String> getAllApp(Context context,String packagename){
		   PackageManager packageManager = context.getPackageManager();
	        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
	        for(PackageInfo packageInfo:packageInfoList){
	        	//LogUtils.d(packagename);
	        	//LogUtils.d(packageInfo.packageName);
	            	if (packagename.equals(packageInfo.packageName)) {
	            	    ToastUtils.Toast(getApplicationContext(),packagename );
	            		LogUtils.d(packageInfo.packageName);
	            		list.add("程序名称："+packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
						list.add("包名：  "+packageInfo.packageName);
						list.add("第一次安装时间："+packageInfo.firstInstallTime);
						list.add("更新时间："+packageInfo.lastUpdateTime);
						list.add("版本："+packageInfo.versionCode);
						list.add("版本名称："+packageInfo.versionName);
						list.add("hashcode ："+packageInfo.hashCode());
						list.add("描述："+packageInfo.describeContents());
					}
	        } 
		return list;
    } 
 
}

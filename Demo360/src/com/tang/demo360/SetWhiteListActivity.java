package com.tang.demo360;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.tang.demo360.view.MAdapter;
import com.tang.entity.AppItem;

public class SetWhiteListActivity extends Activity
{
	private ImageView back;
	private ListView allApp;
	private MAdapter adapter;
	private List<AppItem> data = null;
	private int DATA_OK=0x1;
	private Handler handler =new Handler()
	{
		public void handleMessage(android.os.Message msg) 
		{
			if(msg.what == DATA_OK)
			{
				setListData();
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.white_list);
		back= (ImageView) findViewById(R.id.back);
		allApp = (ListView) findViewById(R.id.list);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("AAA", "back clecked");
				adapter.save();
				finish();
			}
		});
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				data = getAppItem();
				handler.sendEmptyMessage(DATA_OK);
			}
		}).start();
	}

	
	private void setListData()
	{
		if(data!=null&&data.size()>0)
		{
			adapter = new MAdapter(this, data);
		}
		allApp.setAdapter(adapter);
		
		allApp.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.i("AAA", "position:"+position);
				if(adapter.getItem(position).isSelected())
				{
					adapter.getItem(position).setSelected(false);
					adapter.remove(adapter.getItem(position).getPkgName());
				}
				else
				{
					adapter.getItem(position).setSelected(true);
					adapter.add(adapter.getItem(position).getPkgName());
					Toast.makeText(SetWhiteListActivity.this, adapter.getItem(position).getAppName()+" 已加入一件清理忽略名单", 1500).show();
				}
				adapter.notifyDataSetInvalidated();
			}
		});
	}
	/**
	 * 获取系统的应用列表
	 * @return
	 */
	private List<AppItem> getAppItem()
	{
		List<AppItem> appList = new ArrayList<AppItem>(); //用来存储获取的应用信息数据
		List<AppItem> appListSystem = new ArrayList<AppItem>();
		List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
		for(int i=0;i<packages.size();i++) 
		{
			PackageInfo packageInfo = packages.get(i);
			//非系统应用加上下面条件
			//packageInfo.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==0
			if(!packageInfo.packageName.equals("android")&&!packageInfo.packageName.equals("com.tang.demo360"))
			{
				AppItem item =new AppItem();
				item.setAppName(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
				item.setPkgName(packageInfo.packageName);
				item.setIcon(packageInfo.applicationInfo.loadIcon(getPackageManager()));
				if((packageInfo.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==0)
				{
					appList.add(item);
				}
				else
				{
					appListSystem.add(item);
				}
			}
		}
		appList.addAll(appListSystem);
		return appList;
	}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		adapter.save();
		super.onBackPressed();
	}

}

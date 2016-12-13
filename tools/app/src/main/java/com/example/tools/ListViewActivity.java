package com.example.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.tools.adpater.AppAdaptar;
import com.example.tools.entiy.AppPackageInfo;
import com.example.tools.utils.LogUtils;
import com.example.tools.utils.ToastUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


	public class ListViewActivity extends Activity {
	    private ListView listview;
	    private AppAdaptar  adapter;
	    private ArrayList<String> list=new ArrayList<String>();
        private List<AppPackageInfo> mapps;
        private String[] data = { "程序详细信息", "发送app", "刷新","卸载","重构dex" };
        private  int NUM=0;//用来保存索引的数据
        private  int sectindex;
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        mapps=new ArrayList<AppPackageInfo>();
	        setContentView(R.layout.app);
	        listview = (ListView) findViewById(R.id.app_listView);
	        initdata();
	        adapter = new AppAdaptar(getLayoutInflater() , mapps);
	        listview.setAdapter(adapter);
	        listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
					sectindex=arg2;
					
					// TODO Auto-generated method stub
					  Dialog dialog = new AlertDialog.Builder(ListViewActivity.this).setIcon(R.drawable.ic_launcher)
				                .setTitle("选择你要的操作").setSingleChoiceItems(ListViewActivity.this.data, 0, new DialogInterface.OnClickListener() {// setSingleChoiceItems()方法
				                    @Override
				                    public void onClick(DialogInterface dialog, int which) {
				                    	NUM = which; // 保存选项的索引
				                    }
				                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {

				                    @Override
				                    public void onClick(DialogInterface dialog, int which) {
				                    	// "程序详细信息", "发送app", "刷新","卸载","重构dex" 
				                    	switch(NUM) {
				                            case 0: {
				                           //展示app的详细信息 	
				                            	showtheappinfo();
				                                break;
				                            }
				                            case 1: {
				                           //发送app
				                            	sendapp();
				                            	break;
				                            }
				                            case 2: {
				                            //刷新
				                            	refreshlistview();
				                                break;
				                            }
				                            case 3: {
				                            //卸载	
				                            	uninstallapp();
				                                break;
				                            }
				                            case 4: {
				                            	ToastUtils.Toast(getApplicationContext(), "doubuzhidao这是什么鬼");
				                                break;
				                            }
				                        }
				                   
				                    
				                    }

							
									
				                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

				                    @Override
				                    public void onClick(DialogInterface dialog, int which) {
				                    	ToastUtils.Toast(getApplicationContext(), "取消");
				                    	dialog.dismiss();//消失
				                    	
				                    }

				                }

				                ).create();
				        dialog.show();
				}
			});
	    }
	    //展示app的详细信息
	    private void showtheappinfo() {
			// TODO Auto-generated method stub
			Intent  intent =new Intent(this,AppinfoActivity.class);
			intent.putExtra("packagename", mapps.get(sectindex).getPkgName());
			startActivity(intent);
		}
	    //发送app
		private void sendapp() {
			// TODO Auto-generated method stub
			   Uri v2 = Uri.fromFile(new File(this.mapps.get(sectindex).getAppinfo().sourceDir));
		        Intent v1 = new Intent("android.intent.action.VIEW");
		        v1.setDataAndType(v2, "text/plain");
		        v1.setFlags(67108864);
		        try {
		            this.startActivity(v1);
		        }
		        catch(ActivityNotFoundException v0) {
		            Toast.makeText(this, "No Application Available to app", 0).show();
		        }
		}
		//刷新listview
		private void refreshlistview() {
			// TODO Auto-generated method stub
			//adapter.notifyDataSetChanged();
		//刷新失败  可以重intent 所以重新刷新了一遍
			Intent intent=new Intent(this,ListViewActivity.class);
			startActivity(intent);
		}
		//卸载app
		private void uninstallapp() {
			// TODO Auto-generated method stub
		       Uri v1 = Uri.parse("package:" + this.mapps.get(sectindex).getPkgName());
		        Intent v0 = new Intent("android.intent.action.DELETE");
		        v0.setData(v1);
		        this.startActivity(v0);
		}
	    
	    //装入app数据
		private void initdata() {
			// TODO Auto-generated method stub
			 PackageManager packageManager = this.getPackageManager();
		        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
		    
		        for(PackageInfo packageInfo:packageInfoList){
		           	AppPackageInfo  myAppPackageInfo= 	new AppPackageInfo();
		         if((packageInfo.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==0){
		        	 myAppPackageInfo.setisIssystemapp(true);
		        	LogUtils.i("issystem");
		         }
		         else {
		        	 myAppPackageInfo.setisIssystemapp(false);
		        	 LogUtils.i("notsystem");
				}
		           	myAppPackageInfo.setPkgName(packageInfo.packageName);
		           	myAppPackageInfo.setVersion(packageInfo.versionName);
		           	myAppPackageInfo.setAppinfo(packageInfo.applicationInfo);
		           	myAppPackageInfo.setAppLabel(packageInfo.applicationInfo.loadLabel(getPackageManager()).toString());
		           	myAppPackageInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(getPackageManager()));
		           	mapps.add(myAppPackageInfo);
		        } 
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}


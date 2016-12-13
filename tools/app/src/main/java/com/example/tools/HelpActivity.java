package com.example.tools;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HelpActivity extends Activity {
Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 拒绝服务漏洞攻击(poc)
		//denfence();
		  startinstallapp();
	}
	 TextView apkPathText;

	    String apkPath;

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (requestCode == 0 && resultCode == RESULT_OK) {
	            apkPath = data.getStringExtra("apk_path");
	            apkPathText.setText(apkPath);
	        }
	    }

	    public void onChooseApkFile(View view) {
	        Intent intent = new Intent(this, HelpActivity.class);
	        startActivityForResult(intent, 0);
	    }

	    public void onSilentInstall(View view) {
	        if (!isRoot()) {
	            Toast.makeText(this, "没有ROOT权限，不能使用秒装", Toast.LENGTH_SHORT).show();
	            return;
	        }
	        if (TextUtils.isEmpty(apkPath)) {
	            Toast.makeText(this, "请选择安装包！", Toast.LENGTH_SHORT).show();
	            return;
	        }
	        final Button button = (Button) view;
	        button.setText("安装中");
	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                final boolean result =install(apkPath);
	                runOnUiThread(new Runnable() {
	                    @Override
	                    public void run() {
	                        if (result) {
	                            Toast.makeText(HelpActivity.this, "安装成功！", Toast.LENGTH_SHORT).show();
	                        } else {
	                            Toast.makeText(HelpActivity.this, "安装失败！", Toast.LENGTH_SHORT).show();
	                        }
	                        button.setText("秒装");
	                    }
	                });

	            }
	        }).start();

	    }

	    public void onForwardToAccessibility(View view) {
	        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
	        startActivity(intent);
	    }

	    public void onSmartInstall(View view) {
	        if (TextUtils.isEmpty(apkPath)) {
	            Toast.makeText(this, "请选择安装包！", Toast.LENGTH_SHORT).show();
	            return;
	        }
	        Uri uri = Uri.fromFile(new File(apkPath));
	        Intent localIntent = new Intent(Intent.ACTION_VIEW);
	        localIntent.setDataAndType(uri, "application/vnd.android.package-archive");
	        startActivity(localIntent);
	    }

	    /**
	     * 判断手机是否拥有Root权限。
	     * @return 有root权限返回true，否则返回false。
	     */
	    public boolean isRoot() {
	        boolean bool = false;
	        try {
	            bool = (!new File("/system/bin/su").exists()) || (!new File("/system/xbin/su").exists());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return bool;
	    }
	//decode the app from asset，then cp it to /data/data/<packagename>/files
	private void startinstallapp() {
		// TODO Auto-generated method stub
		
	}

	//跳转到某個activity
	private void denfence() {
		// TODO Auto-generated method stub
		try {
			Intent intent = new Intent();
			intent.setComponent(new ComponentName("com.example.broadcasttest", "com.example.broadcasttest.MainActivity"));
			startActivity(intent);
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "fail", 0).show();
		}
	}
	/**
	 * 执行具体的静默安装逻辑，需要手机ROOT。
	 * 
	 * @param apkPath
	 *            要安装的apk文件的路径
	 * @return 安装成功返回true，安装失败返回false。
	 */
	public boolean install(String apkPath) {
		boolean result = false;
		DataOutputStream dataOutputStream = null;
		BufferedReader bufferedReader = null;
		try {
			//申请root权限
			Process process=Runtime.getRuntime().exec("su");
			dataOutputStream =new DataOutputStream(process.getOutputStream());
			//执行pm install命令
			String command="pm install -r"+apkPath+"\n";
            dataOutputStream.write(command.getBytes(Charset.forName("utf-8")));  
            dataOutputStream.flush();  
            dataOutputStream.writeBytes("exit\n");  
            dataOutputStream.flush();  
            process.waitFor();  
            bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));  
            String msg = "";  
            String line;  
            //读取命令的执行结果
            while((line=bufferedReader.readLine())!=null){
                msg += line;  
            }
            //如果安装失败 toast显示失败信息
            if(msg.contains("Failure")){
            	Toast.makeText(context, msg, 0).show();
            }else {
                result = true;  
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			 try {  
	                if (dataOutputStream != null) {  
	                    dataOutputStream.close();  
	                }  
	                if (bufferedReader != null) {  
	                	bufferedReader.close();  
	                }  
	            } catch (Exception e) {  
	                Log.e("TAG", e.getMessage(), e);  
	            }  
	        }  
	        return result;
		}
	
	
}

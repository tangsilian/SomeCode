package com.example.ndk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {
	String result = null;
	String PackageName = "com.example.ndk";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String runinwhereString = isrunninginEmualtor();// 是否運行在模擬器中
		Toast.makeText(getApplicationContext(), runinwhereString, 0).show();
		// isDebuggerable();// 是否可以被調試
		// 是否被重打包了
		while (getSingnatrue(PackageName) != getSingnatrue(PackageName)) {
			Toast.makeText(getApplicationContext(), "signatrue has changed", 0)
					.show();
		}
		SeeStorage();
		internelStorage();

		Toast.makeText(getApplicationContext(), readinternelStorage(), 0)
				.show();
	}

	// 查询os
	public String getOs() {
		String os = System.getProperty("os.name").substring(0, 3);
		return os;
	}

	// 查询是否在模拟器中运行,
	public String isrunninginEmualtor() {
		Process ps = null;
		DataOutputStream doStream = null;
		BufferedReader inBufferedReader = null;
		try {
			ps = Runtime.getRuntime().exec("getprop ro.kernel.qemu");
			// 执行getprop
			doStream = new DataOutputStream(ps.getOutputStream());
			inBufferedReader = new BufferedReader(new InputStreamReader(
					ps.getInputStream(), "GBK"));
			doStream.writeBytes("exit\n");
			doStream.flush();
			ps.wait();
			result = inBufferedReader.readLine();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (doStream != null) {
				try {
					doStream.close();
					inBufferedReader.close();
					ps.destroy();// 結束進程
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	// 检测是否为可调试状态
	public void isDebuggerable() {
		if ((getApplicationInfo().flags &= getApplicationInfo().FLAG_DEBUGGABLE) != 0) {
			Toast.makeText(getApplicationContext(), getApplicationInfo().flags,
					0).show();
		} else {
			Toast.makeText(getApplicationContext(), getApplicationInfo().flags,
					0).show();
		}

	}

	// 检测是否正在被调试
	public void isbeingdebuger() {
		Boolean bl = android.os.Debug.isDebuggerConnected();
		while (bl) {
			Log.i("debuger", "being");
		}
	}

	// 检测签名
	public int getSingnatrue(String packageName) {
		PackageManager pManager = getPackageManager();
		PackageInfo piInfo = null;
		int signatrue = 0;
		try {
			// 拿到package的信息
			piInfo = pManager.getPackageInfo(packageName,
					PackageManager.GET_SIGNATURES);
			// 拿到程序的簽名
			Signature[] signatures = piInfo.signatures;
			signatrue = signatures[0].hashCode();
		} catch (Exception e) {
		}

		return signatrue;
	}

	// 检测dex文件的hash值（用md5或者是crc都可以）

	// 看手机external Storage和internel Storage的位置
	public void SeeStorage() {
		Log.i("codecraeer", "getFilesDir = " + getFilesDir());
		Log.i("codecraeer",
				"getExternalFilesDir = "
						+ getExternalFilesDir("exter_test").getAbsolutePath());
		Log.i("codecraeer", "getDownloadCacheDirectory = "
				+ Environment.getDownloadCacheDirectory().getAbsolutePath());
		Log.i("codecraeer", "getDataDirectory = "
				+ Environment.getDataDirectory().getAbsolutePath());
		Log.i("codecraeer", "getExternalStorageDirectory = "
				+ Environment.getExternalStorageDirectory().getAbsolutePath());
		Log.i("codecraeer", "getExternalStoragePublicDirectory = "
				+ Environment.getExternalStoragePublicDirectory("pub_test"));

	}

	// internel Storage-新建一个omg.txt文件，改变读写权限后
	public void internelStorage() {
		try {
			FileOutputStream fosFileOutputStream = openFileOutput("omg.txt",
					MODE_APPEND);
			fosFileOutputStream.write(("this is test").getBytes());
			fosFileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 用外部来访问
	public String readinternelStorage() {
		StringBuilder sBuilder = null;
		String str2 = null;
		try {
			// 拿到要访问包的context
			Context context = createPackageContext("com.example.ndk",
					Context.CONTEXT_IGNORE_SECURITY);
			// 输入流来访问omg.txt对象
			FileInputStream fis = openFileInput("omg.txt");
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					fis));

			String string = bReader.readLine();
			while (string != null) {
				sBuilder.append(string);
			}
			str2 = sBuilder.toString();
			bReader.close();
			fis.close();
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str2;
	}
}

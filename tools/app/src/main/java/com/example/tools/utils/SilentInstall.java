package com.example.tools.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class SilentInstall {
	Context context;
	//不知道这个context能否显示
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

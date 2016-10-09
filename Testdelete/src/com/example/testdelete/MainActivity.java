package com.example.testdelete;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	public Context context = this;
	public String filename = "uzi";
	public String filecontent = "woyouyitouxiaomaolv";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

	}

	// �҂��@�e��mainfest��Ȼ��switch�ķ����Д�button���c���¼�click
	public void click(View v) throws UnsupportedEncodingException, IOException {
		switch (v.getId()) {
		case R.id.createfile:
			// �ļ��惦 �ļ�Ĭ�ϻ�洢��/data/data/package/files�У�
			createnewfile();
			Toast.makeText(getApplicationContext(), "�c���������ļ����o", 0).show();
			break;
		case R.id.deletefile:
			// ���ҵ�/data/data/package/files�ļ����h���e���uzi�ļ�
			// deleteFile(getFilesDir());

			File file = new File(getFilesDir() + "/uzi");
			file.delete();
			String fString = file.getName();
			Toast.makeText(getApplicationContext(), fString, 0).show();

			break;
		default:
			break;
		}
	}

	private void createnewfile() throws UnsupportedEncodingException,
			IOException {
		// TODO Auto-generated method stub
		FileOutputStream out = null;
		try {
			out = context.openFileOutput(filename, Context.MODE_PRIVATE);
			out.write(filecontent.getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteFile(File file) {
		if (file.exists()) { // �ж��ļ��Ƿ����
			if (file.isFile()) { // �ж��Ƿ����ļ�
				file.delete(); // delete()���� ��Ӧ��֪�� ��ɾ������˼;
			} else if (file.isDirectory()) { // �����������һ��Ŀ¼
				File files[] = file.listFiles(); // ����Ŀ¼�����е��ļ� files[];
				for (int i = 0; i < files.length; i++) { // ����Ŀ¼�����е��ļ�

					this.deleteFile(files[i]); // ��ÿ���ļ� ������������е���
				}
			}
			// file.delete();
		} else {
			Log.d("file", "�ļ������ڣ�" + "\n");
		}
	}
}
package com.example.tools;

import java.util.ArrayList;
import java.util.List;

import com.example.tools.adpater.Contactadapter;
import com.example.tools.entiy.ContactEntiy;
import com.example.tools.utils.ToastUtils;
import com.example.tools.adpater.Contactadapter.Callback;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ContactActivity extends Activity implements OnItemClickListener, Callback {
	// 定义数据
	private List<ContactEntiy> mData;
	// 定义ListView对象
	private ListView mListViewArray;
	private Callback mCallback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);

		// 为ListView对象赋值
		mListViewArray = (ListView) findViewById(R.id.contact_listView);
		LayoutInflater inflater = getLayoutInflater();
		// 初始化数据
		initData();
		// 创建自定义Adapter的对象
		Contactadapter adapter = new Contactadapter(inflater, mData, mCallback);
		// 将布局添加到ListView中
		mListViewArray.setAdapter(adapter);
		mListViewArray.setOnItemClickListener(this);
	}

	/*
	 * 初始化数据
	 */
	private void initData() {
		// 获得所有的联系人
		mData = new ArrayList<ContactEntiy>();
		Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		// 循环遍历

		if (cur.moveToFirst()) {

			int idColumn = cur.getColumnIndex(ContactsContract.Contacts._ID);

			int displayNameColumn = cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

			do {

				// 获得联系人的ID号

				String contactId = cur.getString(idColumn);

				// 获得联系人姓名

				String disPlayName = cur.getString(displayNameColumn);

				// 查看该联系人有多少个电话号码。如果没有这返回值为0

				int phoneCount = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

				if (phoneCount > 0) {

					// 获得联系人的电话号码

					Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,

							null,

							ContactsContract.CommonDataKinds.Phone.CONTACT_ID

									+ " = " + contactId,
							null, null);

					if (phones.moveToFirst()) {

						do {

							// 遍历所有的电话号码

							String phoneNumber = phones
									.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

							ContactEntiy uzi = new ContactEntiy(disPlayName, phoneNumber, contactId,
									R.drawable.ic_launcher, R.drawable.ic_launcher);
							mData.add(uzi);
						} while (phones.moveToNext());

					}

				}

			} while (cur.moveToNext());

		}

		ContactEntiy zhaoliu = new ContactEntiy("赵六", "22", "男", R.drawable.ic_launcher, R.drawable.ic_launcher);
		mData.add(zhaoliu);
	}

	// 接口回调错误
	@Override
	public void click(View v) {
		// TODO Auto-generated method stub
		//ToastUtils.Toast(getApplicationContext(), "" + "uzi");
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String phonenumner=""+mData.get(arg2).getNumber();
		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phonenumner));
		//	Intent intent = new Intent( "android.intent.action.CALL", Uri.parse("tel:"+phonenumner));
		startActivity(intent);
	}

}

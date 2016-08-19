package com.test.helloworld;
/**
 * 拿到联系人name并展示
 * 再get到联系人的所有信息
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Cursor cursor;
	ListView lv;
	
	 private ArrayList<String> list = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	   ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1,
                getcontactname());
		lv = (ListView) findViewById(R.id.list1);
		lv.setAdapter(adapter);
	}
	//拿到联系人并添加到list
	private ArrayList<String> getcontactname() {
		 Cursor cursor = this.getBaseContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,   
                 null, null, null, null);  

		 int contactIdIndex = 0;  
	        int nameIndex = 0;  
	          
	        if(cursor.getCount() > 0) {  
	            contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);  
	            nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);  
	        }  
		 while(cursor.moveToNext()){
		       String contactId = cursor.getString(contactIdIndex);  
			  String name = cursor.getString(nameIndex);  
			  Toast.makeText(getApplicationContext(), name, 0).show();
			  list.add(name);
			System.out.println(name);
		
			
			/**
			 * 查询手机的信息
			 */
	        Cursor phones = this.getBaseContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,   
                    null,   
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,   
                    null, null);  
            int phoneIndex = 0;  
            if(phones.getCount() > 0) {  
                phoneIndex = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);  
            }  
            while(phones.moveToNext()) {  
                String phoneNumber = phones.getString(phoneIndex);  
                Log.i("TAG", phoneNumber);  
            }  
              
            /* 
             * 查找该联系人的email信息 
             */  
            Cursor emails = this.getBaseContext().getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,   
                    null,   
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contactId,   
                    null, null);  
            int emailIndex = 0;  
            if(emails.getCount() > 0) {  
                emailIndex = emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);  
            }  
            while(phones.moveToNext()) {  
                String email = emails.getString(emailIndex);  
                System.out.println(email);
            }  
		 }
		return list;
	}
	
	public void jump(View view){
		Intent intent=new Intent(this,RecordActivity.class);
		startActivity(intent);
	}
	public void jump2(View view){
		Intent intent=new Intent(this,PohotoActivity.class);
		startActivity(intent);
	}
	public void jump3(View view){
		Intent intent=new Intent(this,AutoRecoder.class);
		startActivity(intent);
	}
}
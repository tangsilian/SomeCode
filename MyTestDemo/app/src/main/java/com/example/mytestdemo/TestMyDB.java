package com.example.mytestdemo;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tangsilian on 2016-12-12.
 */

public class TestMyDB  extends Activity {
    /** Called when the activity is first created. */
    private DBlite dBlite1 = new DBlite(this);;
    private ContentResolver contentResolver;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dBlite1.add("jshjla","dkasjfka","kdzhlkds","nan");
        //通过contentResolver进行查找
        contentResolver = TestMyDB.this.getContentResolver();
        Cursor cursor = contentResolver.query(
                MyUtil.CONTENT_URI, new String[] {
                        MyUtil.EMAIL, MyUtil.USERNAME,
                        MyUtil.DATE,MyUtil.SEX }, null, null, null);
        while (cursor.moveToNext()) {
            Toast.makeText(TestMyDB.this,
                    cursor.getString(cursor.getColumnIndex(MyUtil.EMAIL))
                            + " "
                            + cursor.getString(cursor.getColumnIndex(MyUtil.USERNAME))
                            + " "
                            + cursor.getString(cursor.getColumnIndex(MyUtil.DATE))
                            + " "
                            + cursor.getString(cursor.getColumnIndex(MyUtil.SEX)),
                    Toast.LENGTH_SHORT).show();
            Log.d("TSL",cursor.getString(cursor.getColumnIndex(MyUtil.EMAIL))
                    + " "
                    + cursor.getString(cursor.getColumnIndex(MyUtil.USERNAME))
                    + " "
                    + cursor.getString(cursor.getColumnIndex(MyUtil.DATE))
                    + " "
                    + cursor.getString(cursor.getColumnIndex(MyUtil.SEX)));
            Log.d("TSL","查询数据库");
        }
        startManagingCursor(cursor);  //查找后关闭游标
        Log.d("TSL","到数据库了");
    }
}

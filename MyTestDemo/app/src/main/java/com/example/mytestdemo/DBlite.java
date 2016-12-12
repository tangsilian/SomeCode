package com.example.mytestdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tangsilian on 2016-12-12.
 */

public class DBlite extends SQLiteOpenHelper {
    public DBlite(Context context) {
        super(context, MyUtil.DBNAME, null, MyUtil.VERSION);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table "+MyUtil.TNAME+"(" +
                MyUtil.TID+" integer primary key autoincrement not null,"+
                MyUtil.EMAIL+" text not null," +
                MyUtil.USERNAME+" text not null," +
                MyUtil.DATE+" interger not null,"+
                MyUtil.SEX+" text not null);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
    public void add(String email,String username,String date,String sex){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MyUtil.EMAIL, email);
        values.put(MyUtil.USERNAME, username);
        values.put(MyUtil.DATE, date);
        values.put(MyUtil.SEX, sex);
        db.insert(MyUtil.TNAME,"",values);
    }
}
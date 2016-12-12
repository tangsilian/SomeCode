package com.example.mytestdemo;

import android.net.Uri;

/**
 * Created by tangsilian on 2016-12-12.
 */
 //数据库的工具类
public class MyUtil  {

    public static final String DBNAME = "MyUtillinedb";
    public static final String TNAME = "MyUtilline";
    public static final int VERSION = 3;

    public static String TID = "tid";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String DATE = "date";
    public static final String SEX = "sex";


    public static final String AUTOHORITY = "com.example.mytestdemo";
    public static final int ITEM = 1;
    public static final int ITEM_ID = 2;

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.MyUtil.login";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.MyUtil.login";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTOHORITY + "/MyUtilline");
}

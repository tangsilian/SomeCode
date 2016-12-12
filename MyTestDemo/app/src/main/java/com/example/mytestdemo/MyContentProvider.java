package com.example.mytestdemo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by tangsilian on 2016-12-12.
 */

public class MyContentProvider extends ContentProvider {
    DBlite dBlite;
    SQLiteDatabase db;

    private static final UriMatcher sMatcher;
    static{
        sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sMatcher.addURI(MyUtil.AUTOHORITY,MyUtil.TNAME, MyUtil.ITEM);
        sMatcher.addURI(MyUtil.AUTOHORITY, MyUtil.TNAME+"/#", MyUtil.ITEM_ID);

    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // TODO Auto-generated method stub
        db = dBlite.getWritableDatabase();
        int count = 0;
        switch (sMatcher.match(uri)) {
            case MyUtil.ITEM:
                count = db.delete(MyUtil.TNAME,selection, selectionArgs);
                break;
            case MyUtil.ITEM_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(MyUtil.TID, MyUtil.TID+"="+id+(!TextUtils.isEmpty(MyUtil.TID="?")?"AND("+selection+')':""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI"+uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        switch (sMatcher.match(uri)) {
            case MyUtil.ITEM:
                return MyUtil.CONTENT_TYPE;
            case MyUtil.ITEM_ID:
                return MyUtil.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI"+uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO Auto-generated method stub

        db = dBlite.getWritableDatabase();
        long rowId;
        if(sMatcher.match(uri)!=MyUtil.ITEM){
            throw new IllegalArgumentException("Unknown URI"+uri);
        }
        rowId = db.insert(MyUtil.TNAME,MyUtil.TID,values);
        if(rowId>0){
            Uri noteUri= ContentUris.withAppendedId(MyUtil.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }
        throw new IllegalArgumentException("Unknown URI"+uri);
    }

    @Override
    public boolean onCreate() {
        // TODO Auto-generated method stub
        this.dBlite = new DBlite(this.getContext());
//                db = dBlite.getWritableDatabase();
//                return (db == null)?false:true;
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO Auto-generated method stub
        db = dBlite.getWritableDatabase();
        Cursor c;
        Log.d("-------", String.valueOf(sMatcher.match(uri)));
        switch (sMatcher.match(uri)) {
            case MyUtil.ITEM:
                c = db.query(MyUtil.TNAME, projection, selection, selectionArgs, null, null, null);

                break;
            case MyUtil.ITEM_ID:
                String id = uri.getPathSegments().get(1);
                c = db.query(MyUtil.TNAME, projection, MyUtil.TID+"="+id+(!TextUtils.isEmpty(selection)?"AND("+selection+')':""),selectionArgs, null, null, sortOrder);
                break;
            default:
                Log.d("!!!!!!", "Unknown URI"+uri);
                throw new IllegalArgumentException("Unknown URI"+uri);
        }
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO Auto-generated method stub
        return 0;
    }
}

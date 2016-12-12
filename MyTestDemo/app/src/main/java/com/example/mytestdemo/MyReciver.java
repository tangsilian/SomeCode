package com.example.mytestdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tangsilian on 2016-12-12.
 */

public class MyReciver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        if (msg==null){
            Toast.makeText(context,"开机了",Toast.LENGTH_SHORT).show();
            Log.d("TSL","开机了");
        }else {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Log.d("TSL",msg);
        }
    }
}

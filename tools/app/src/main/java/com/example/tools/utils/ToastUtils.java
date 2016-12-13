package com.example.tools.utils;

import android.content.Context;

public class ToastUtils {
	   public  static void Toast(Context context,String string) {
	   android.widget.Toast.makeText(context, string, 0).show();
	   }
}

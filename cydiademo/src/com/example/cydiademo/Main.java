package com.example.cydiademo;

import java.lang.reflect.Method;

import android.util.Log;

import com.saurik.substrate.MS;

public class Main {
	/*
	 * static void initialize() {
	 * MS.hookClassLoad("com.example.ndk.jiemiActivity", new MS.ClassLoadHook()
	 * { public void classLoaded(Class<?> resources) { Method getString; try {
	 * getString = resources.getMethod("getString", Integer.TYPE); // new
	 * Class[] { String.class } } catch (NoSuchMethodException e) { getString =
	 * null; }
	 * 
	 * if (getString != null) { final MS.MethodPointer old = new
	 * MS.MethodPointer();
	 * 
	 * MS.hookMethod(resources, getString, new MS.MethodHook() { public Object
	 * invoked(Object resources, Object... args) throws Throwable { Integer
	 * string = (Integer) old .invoke(resources, args); return string + 100; }
	 * }, old); } } }); }
	 */

	static void initialize() {

		MS.hookClassLoad("android.widget.TextView", new MS.ClassLoadHook() {

			public void classLoaded(Class<?> arg0) {
				// TODO Auto-generated method stub

				Method smstest;

				try {

					smstest = arg0.getMethod("setText", CharSequence.class);

				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					smstest = null;
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					smstest = null;
					e.printStackTrace();
				}

				if (smstest != null) {

					final MS.MethodPointer old = new MS.MethodPointer();

					MS.hookMethod(arg0, smstest, new MS.MethodHook() {

						@Override
						public Object invoked(Object arg0, Object... arg1)
								throws Throwable {
							// TODO Auto-generated method stub

							Log.d("ggz", "i am hook in------->");

							String bb = (String) arg1[0];

							Log.d("ggz", "string is----->" + bb);

							bb = "帅哥唐思廉";

							Log.d("ggz", "now string is --->" + bb);

							return old.invoke(arg0, bb);

						}

					}, old);

				}

			}

		});

	}

}

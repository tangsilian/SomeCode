package com.tencent.mm.app;

import android.app.Application;

public class MMApplication extends Application {
	static {
		System.loadLibrary("wuhahah");
	}

	public MMApplication() {
		super();
	}

	public void onCreate() {
		MMApplication.load(MMApplication.this, this.getAssets(), null);
		super.onCreate();
	}

	private static native void load(MMApplication tApplication, Object arg1,
			Object arg2);

}
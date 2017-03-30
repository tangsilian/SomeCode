package com.tang.demo360;

import java.io.IOException;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createShortcut();
				finish();
			}
		});
	}
	
	private void createShortcut()
	{
		 Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		 String name = getResources().getString(R.string.app_name);
	     shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME,name);
	     //不允许重复创建
		 shortcut.putExtra("duplicate", false);  
		 Intent shortcutIntent = new Intent();
		 ComponentName componentName = new ComponentName(getPackageName(), "com.tang.demo360.CleanActivity");
		 shortcutIntent.setComponent(componentName);
		 shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		 ShortcutIconResource iconRes=null;
		 iconRes = Intent.ShortcutIconResource.fromContext(this, R.drawable.shortcut_process_clear_shortcut_icon);
		 shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes); 
		 sendBroadcast(shortcut); 
		 Log.i("AAA", "sendBroadcast : INSTALL_SHORTCUT");
	}

	public void setWallpaper(Object wallpaper)
	{
		WallpaperManager manager = WallpaperManager.getInstance(this);
		try {
			if(wallpaper instanceof Integer)
				manager.setResource((Integer)wallpaper);
			else if(wallpaper instanceof String)
			{
				Bitmap bitmap = BitmapFactory.decodeFile((String)wallpaper);
				manager.setBitmap(bitmap);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

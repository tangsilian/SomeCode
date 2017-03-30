package com.tang.demo360;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.tang.demo360.view.CleanView;
import com.tang.util.Value;

public class CleanActivity extends Activity
{
	private CleanView rootView=null;
	public static CleanActivity local=null;
	private Object[]parm=null;
	private Timer timer=null;
	private boolean isDown =true;
	private TimerTask task = null;
	
	private int newlevel = 5000;
	private int oldlevel = 5000;
	private int level = 5000;
	private Handler handler = new Handler() 
	{
		public void handleMessage(android.os.Message msg) 
		{
			switch(msg.what)
			{
				case 0x1:
					rootView.startCleanAnimation();
				 break;
				case 0x2:
					rootView.startExitAnimation();
					task.cancel();
					task = null;
					timer.cancel();
					timer = null;
					stopService(new Intent(CleanActivity.this, CleanService.class));
				 break;
				case 0x3:
					parm = (Object[]) msg.obj;
					rootView.setMemoryRate((Integer)parm[2]);
					newlevel = rootView.getNewLevel()*100;
					oldlevel = rootView.getOldLevel()*100;
					level= oldlevel;
					timer = new Timer();
					timer.schedule(task, 500, 1);
				 break;
				case 0x4:
					rootView.updateView(parm);
				 break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SharedPreferences sharedPreferences = getSharedPreferences("demo360", Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		long time = System.currentTimeMillis();
		if(time - sharedPreferences.getLong("time", 0)<10000)
		{
			Toast.makeText(CleanActivity.this, "您刚刚清理过内存，请稍候再来～", 1500).show();
			finish();
		}
		else
		{
			editor.putLong("time", time);
			editor.commit();
			local = this;
			Rect rect = getIntent().getSourceBounds();
			rootView = new CleanView(this,rect);
			setContentView(rootView);
			
			WindowManager.LayoutParams lp = getWindow().getAttributes();
			WindowManager windowManager = getWindowManager();
			int w = windowManager.getDefaultDisplay().getWidth();
			int h = windowManager.getDefaultDisplay().getHeight();
			lp.x = lp.x+(rect.left-w/2)+Value.WIDTH/2;
			lp.y = lp.y+(rect.top-h/2)+Value.HEIGHT/2;	
			getWindow().setAttributes(lp);	
			new Timer().schedule(timerTask, 4000);			
			task = new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if(isDown)
					{
						level = (level-Value.V)>=0?(level-Value.V):0;
						if(level ==0)
						{
							isDown = false;
						}
					}
					else
					{
						level = (level+Value.V)<=newlevel?(level+Value.V):newlevel;
					}
					parm[2] =level/100;
					handler.sendEmptyMessage(Value.UPDATE_VIEW);
				}
			};
			Intent intent = new Intent(this, CleanService.class);
			startService(intent);
		}
		
	}
	
	TimerTask timerTask = new TimerTask() {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.sendEmptyMessage(Value.END_CLEAN);
		}
	};
	public Handler getHandler()
	{
		return handler;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopService(new Intent(this, CleanService.class));
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
	}
	
}

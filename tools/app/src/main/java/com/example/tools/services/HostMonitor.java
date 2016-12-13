package com.example.tools.services;

import org.apache.http.impl.conn.DefaultClientConnection;

import com.example.tools.thread.HijackRunnable;
import com.example.tools.thread.otherhajckRunnable;
import com.example.tools.utils.LogUtils;
import com.example.tools.utils.ToastUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
/**
 * @author Jay-Tang
 *
 */


public class HostMonitor extends Service {
    public final static int HIJACK_TYPE_ACTIVITY = 1;
    public final static int HIJACK_TYPE_LAYOUT = 2;

    private Thread thread;
    public HostMonitor() {
    super();
    }
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("Tangsilian", "HostMonitor: onCreate! I can not be Killed!");
	    ToastUtils.Toast(getApplicationContext(), "start");
	}
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int type = intent.getIntExtra("Type", 0);
        Log.i("Tangsilian", "HostMonitor:BE onStartCommand! ");
    	   switch (type) {
           case HIJACK_TYPE_ACTIVITY:
               this.thread = new Thread(new HijackRunnable(this));
               this.thread.start();
               break;

           case HIJACK_TYPE_LAYOUT:
               this.thread = new Thread(new otherhajckRunnable(this));
               this.thread.start();
               break;
           default:  
        	   LogUtils.d("HostMonitor:start onStartCommand!  by am ");
       }
   
	
      
        Log.i("Tangsilian", "HostMonitor: onStartCommand! I can not be Killed!");
		return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
    	HijackRunnable.setContinue(false);
    	otherhajckRunnable.setIsContinue(false);
        super.onDestroy();
    }
}

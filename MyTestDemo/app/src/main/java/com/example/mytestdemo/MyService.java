package com.example.mytestdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by tangsilian on 2016-12-12.
 */
public class MyService extends Service {
    public MyService() {
        Log.d("TSL","myservice的构造方法执行了");
    }

    @Override
    public IBinder onBind(Intent intent) {
        //返回MyBind对象
        return new MyBinder();
    }

    private void methodInMyService() {
        Toast.makeText(getApplicationContext(), "服务里的方法执行了。。。",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * 该类用于在onBind方法执行后返回的对象，
     * 该对象对外提供了该服务里的方法
     */
    private class MyBinder extends Binder implements IMyBinder {
        @Override
        public void invokeMethodInMyService() {
            methodInMyService();
        }
    }
}

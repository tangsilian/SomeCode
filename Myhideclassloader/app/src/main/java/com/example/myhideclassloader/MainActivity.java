package com.example.myhideclassloader;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //点击按钮安装某个apk
    public void click(View v){
        Toast.makeText(getApplicationContext(),"点击了该按钮",Toast.LENGTH_SHORT).show();
        String str = "/apk.apk";
        String fileName = "/data/local/tmp/" + str;
        Log.d("TAG",fileName);
        //下面那三句是用来安装的代码
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
        startActivity(intent);
    }
    //打开设置抢红包的服务
    public void click2(View v){
        Toast.makeText(getApplicationContext(),"请打开微信抢红包的辅助服务",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }
    //打开微信
    public void click3(View v){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ComponentName componentName = new ComponentName("com.tencent.mm","com.tencent.mm.ui.LauncherUI");
        intent.setComponent(componentName);
        startActivity(intent);
    }


}

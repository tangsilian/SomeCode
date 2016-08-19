package com.test.helloworld;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

/**
 * 拍照
 * @author tangsilian
 *
 */
public class PohotoActivity extends Activity{
	//设置大小和路径
    private int xiangji=3;  
    ImageView img;
    private  File sdcardTempFile = new File("/mnt/sdcard/", "tmp_pic_" + SystemClock.currentThreadTimeMillis() + ".jpg");  
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
setContentView(R.layout.potomain);
img=(ImageView) findViewById(R.id.iv);
}

public void takepohoto(View view){
	//用intent打开相机
	   Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);    
       Uri u=Uri.fromFile(sdcardTempFile);   
       intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);   
       intent.putExtra(MediaStore.EXTRA_OUTPUT, u);   
       intent.putExtra("return-data", true);  
       startActivityForResult(intent, xiangji);  
}
//根據返回值查看圖片
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	super.onActivityResult(requestCode, resultCode, data);
	  if (resultCode == RESULT_OK) {   
		if(requestCode== 3){  
  try {  
    Bitmap  bmp=BitmapFactory.decodeFile(sdcardTempFile.getAbsolutePath());  
      img.setImageBitmap(bmp);  
  } catch (Exception e) {  
      e.printStackTrace();  
  }  
		}
}
}
               
}

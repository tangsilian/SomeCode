package com.test.helloworld;

import android.app.Activity;

import java.io.IOException;  

import android.app.Activity;  
import android.media.MediaPlayer;  
import android.media.MediaRecorder;  
import android.os.Bundle;  
import android.os.Environment;  
import android.util.Log;  
import android.view.View;  
import android.view.View.OnClickListener;  
import android.widget.Button;  
import android.widget.Toast;
  
public class RecordActivity extends Activity {  
     
    private static final String LOG_TAG = "AudioRecordTest";  
    //�����ļ�����·��  
    private String FileName = null;  
      
    //����ؼ�  
    private Button startRecord;   
    private Button startPlay;  
    private Button stopRecord;  
    private Button stopPlay;  
      
    //������������  
    private MediaPlayer mPlayer = null;  
    private MediaRecorder mRecorder = null;  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
          
        //��ʼ¼��  
        startRecord = (Button)findViewById(R.id.startRecord);  
        startRecord.setText(R.string.startRecord);  
        //�󶨼�����  
        startRecord.setOnClickListener(new startRecordListener());  
          
        //����¼��  
        stopRecord = (Button)findViewById(R.id.stopRecord);  
        stopRecord.setText(R.string.stopRecord);  
        stopRecord.setOnClickListener(new stopRecordListener());  
          
        //��ʼ����  
        startPlay = (Button)findViewById(R.id.startPlay);  
        startPlay.setText(R.string.startPlay);  
        //�󶨼�����  
        startPlay.setOnClickListener(new startPlayListener());  
          
        //��������  
        stopPlay = (Button)findViewById(R.id.stopPlay);  
        stopPlay.setText(R.string.stopPlay);  
        stopPlay.setOnClickListener(new stopPlayListener());  
          
        //����sdcard��·��  
        FileName = Environment.getExternalStorageDirectory().getAbsolutePath();  
        FileName += "/audiorecordtest.3gp";  
    }  
    //��ʼ¼��  
    class startRecordListener implements OnClickListener{  
  
        @Override  
        public void onClick(View v) {  
            // TODO Auto-generated method stub  
             mRecorder = new MediaRecorder();  
             mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);  
             mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);  
             mRecorder.setOutputFile(FileName);  
             mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  
             try {  
                 mRecorder.prepare();  
             } catch (IOException e) {  
                 Log.e(LOG_TAG, "prepare() failed");  
             }  
             mRecorder.start();  
             Toast.makeText(getApplicationContext(), "��ʼ¼��", 0).show();
        }  
          
    }  
    //ֹͣ¼��  
    class stopRecordListener implements OnClickListener{  
  
        @Override  
        public void onClick(View v) {  
            // TODO Auto-generated method stub  
             mRecorder.stop();  
             mRecorder.release();  
             mRecorder = null;  
             Toast.makeText(getApplicationContext(), "ֹͣ¼��", 0).show();
        }  
          
    }  
    //����¼��  
    class startPlayListener implements OnClickListener{  
  
        @Override  
        public void onClick(View v) {  
            // TODO Auto-generated method stub  
            mPlayer = new MediaPlayer();  
            try{  
                mPlayer.setDataSource(FileName);  
                mPlayer.prepare();  
                mPlayer.start();  
                Toast.makeText(getApplicationContext(), "����¼��", 0).show();
            }catch(IOException e){  
                Log.e(LOG_TAG,"����ʧ��");  
            }  
        }  
          
    }  
    //ֹͣ����¼��  
    class stopPlayListener implements OnClickListener{  
  
        @Override  
        public void onClick(View v) {  
            // TODO Auto-generated method stub  
            mPlayer.release();  
            mPlayer = null;  
            Toast.makeText(getApplicationContext(), "ֹͣ����¼��", 0).show();
        }  
          
    }  
}  
package com.test.helloworld;
import java.io.BufferedInputStream;  
import java.io.BufferedOutputStream;  
import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import android.app.Activity;  
import android.content.ContentValues;  
import android.content.Intent;  
import android.hardware.Camera.AutoFocusCallback;  
import android.media.AudioFormat;  
import android.media.AudioManager;  
import android.media.AudioRecord;  
import android.media.AudioTrack;  
import android.media.MediaPlayer;  
import android.media.MediaRecorder;  
import android.net.Uri;  
import android.os.AsyncTask;  
import android.os.Bundle;  
import android.os.Environment;  
import android.provider.MediaStore;  
import android.util.Log;  
import android.view.View;  
import android.widget.Button;  
import android.widget.TextView;  
/** 
 * ��ʵ���У�����ʹ��AudioRecord����������ǵ���Ƶ¼�Ƴ��� 
 * AudioRecord�࣬���ǿ���ʹ�����ֲ�ͬ��read���������¼�ƹ����� 
 * ÿ�ַ���������ʵ�õĳ��� 
 * һ��ʵ����һ��AudioRecord��������Ҫ���뼸�ֲ��� 
 * 1��AudioSource�����������MediaRecorder.AudioSource.MIC 
 * 2��SampleRateInHz:¼��Ƶ�ʣ�����Ϊ8000hz����11025hz�ȣ���ͬ��Ӳ���豸���ֵ��ͬ 
 * 3��ChannelConfig:¼��ͨ��������ΪAudioFormat.CHANNEL_CONFIGURATION_MONO��AudioFormat.CHANNEL_CONFIGURATION_STEREO 
 * 4��AudioFormat:¼�Ʊ����ʽ������ΪAudioFormat.ENCODING_16BIT��8BIT,����16BIT�ķ����Ա�8BIT�ã�������Ҫ���ĸ���ĵ����ʹ洢�ռ� 
 * 5��BufferSize:¼�ƻ����С������ͨ��getMinBufferSize����ȡ 
 * �������ǾͿ���ʵ����һ��AudioRecord������ 
 * ��������һ���ļ������ڱ���¼�Ƶ����� 
 * ͬ��ƪ 
 * ������һ���������ָ�򴴽����ļ� 
 * DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file))) 
 * �ġ����ھͿ��Կ�ʼ¼���ˣ�������Ҫ����һ���ֽ��������洢��AudioRecorder�з��ص���Ƶ���ݣ����� 
 * ע�⣬���Ƕ��������ҪС�ڶ���AudioRecordʱָ�����Ǹ�BufferSize 
 * short[]buffer = new short[BufferSize/4]; 
 * startRecording(); 
 * Ȼ��һ��ѭ��������AudioRecord��read����ʵ�ֶ�ȡ 
 * ����ʹ��MediaPlayer���޷�����ʹ��AudioRecord¼�Ƶ���Ƶ�ģ�Ϊ��ʵ�ֲ��ţ�������Ҫ 
 * ʹ��AudioTrack����ʵ�� 
 * AudioTrack���������ǲ���ԭʼ����Ƶ���� 
 *  
 *  
 * һ��ʵ����һ��AudioTrackͬ��Ҫ���뼸������ 
 * 1��StreamType:��AudioManager���м�������������һ����STREAM_MUSIC; 
 * 2��SampleRateInHz����ú�AudioRecordʹ�õ���ͬһ��ֵ 
 * 3��ChannelConfig��ͬ�� 
 * 4��AudioFormat��ͬ�� 
 * 5��BufferSize��ͨ��AudioTrack�ľ�̬����getMinBufferSize����ȡ 
 * 6��Mode��������AudioTrack.MODE_STREAM��MODE_STATIC�����������ֲ�֮ͬ�������Բ����ĵ� 
 * ������һ����������ָ��ո�¼�����ݱ�����ļ���Ȼ��ʼ���ţ��߶�ȡ�߲��� 
 *  
 * ʵ��ʱ����Ƶ��¼�ƺͲ��ŷֱ�ʹ������AsyncTask�����  
 */  
public class AutoRecoder extends Activity{  
      
    private TextView stateView;  
      
    private Button btnStart,btnStop,btnPlay,btnFinish;  
      
    private RecordTask recorder;  
    private PlayTask player;  
      
    private File audioFile;  
      
    private boolean isRecording=true, isPlaying=false; //���  
      
    private int frequence = 8000; //¼��Ƶ�ʣ���λhz.�����ֵע���ˣ�д�Ĳ��ã�����ʵ����AudioRecord�����ʱ�򣬻�����ҿ�ʼд��11025�Ͳ��С���ȡ����Ӳ���豸  
    private int channelConfig = AudioFormat.CHANNEL_CONFIGURATION_MONO;  
    private int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;  
      
      
    public void onCreate(Bundle savedInstanceState){  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.myrecord); 
          
        stateView = (TextView)this.findViewById(R.id.view_state);  
        stateView.setText("׼����ʼ");  
        btnStart = (Button)this.findViewById(R.id.btn_start);  
        btnStop = (Button)this.findViewById(R.id.btn_stop);  
        btnPlay = (Button)this.findViewById(R.id.btn_play);  
        btnFinish = (Button)this.findViewById(R.id.btn_finish);  
        btnFinish.setText("ֹͣ����");  
        btnStop.setEnabled(false);  
        btnPlay.setEnabled(false);  
        btnFinish.setEnabled(false);  
          
        //���������Ǵ���һ���ļ������ڱ���¼������  
        File fpath = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/data/files/");  
        fpath.mkdirs();//�����ļ���  
        try {  
            //������ʱ�ļ�,ע������ĸ�ʽΪ.pcm  
            audioFile = File.createTempFile("recording", ".pcm", fpath);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }         
    }  
      
      
    public void onClick(View v){  
        int id = v.getId();  
        switch(id){  
        case R.id.btn_start:  
            //��ʼ¼��  
              
            //��������¼������  
            recorder = new RecordTask();  
            recorder.execute();  
              
            break;  
        case R.id.btn_stop:  
            //ֹͣ¼��  
            this.isRecording = false;  
            //����״̬  
            //��¼�����ʱ���ã���RecordTask��onPostExecute�����  
            break;  
        case R.id.btn_play:  
              
            player = new PlayTask();  
            player.execute();  
            break;  
        case R.id.btn_finish:  
            //��ɲ���  
            this.isPlaying = false;  
            break;  
              
        }  
    }  
      
    class RecordTask extends AsyncTask<Void, Integer, Void>{  
        @Override  
        protected Void doInBackground(Void... arg0) {  
            isRecording = true;  
            try {  
                //��ͨ�������ָ�����ļ�  
                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(audioFile)));  
                //���ݶ���õļ������ã�����ȡ���ʵĻ����С  
                int bufferSize = AudioRecord.getMinBufferSize(frequence, channelConfig, audioEncoding);  
                //ʵ����AudioRecord  
                AudioRecord record = new AudioRecord(MediaRecorder.AudioSource.MIC, frequence, channelConfig, audioEncoding, bufferSize);  
                //���建��  
                short[] buffer = new short[bufferSize];  
                  
                //��ʼ¼��  
                record.startRecording();  
                  
                int r = 0; //�洢¼�ƽ���  
                //����ѭ��������isRecording��ֵ���ж��Ƿ����¼��  
                while(isRecording){  
                    //��bufferSize�ж�ȡ�ֽڣ����ض�ȡ��short����  
                    //�������ǳ���buffer overflow����֪����ʲôԭ�����˺ü���ֵ����û�ã�TODO�������  
                    int bufferReadResult = record.read(buffer, 0, buffer.length);  
                    //ѭ����buffer�е���Ƶ����д�뵽OutputStream��  
                    for(int i=0; i<bufferReadResult; i++){  
                        dos.writeShort(buffer[i]);  
                    }  
                    publishProgress(new Integer(r)); //��UI�̱߳��浱ǰ����  
                    r++; //��������ֵ  
                }  
                //¼�ƽ���  
                record.stop();  
                Log.v("The DOS available:", "::"+audioFile.length());  
                dos.close();  
            } catch (Exception e) {  
                // TODO: handle exception  
            }  
            return null;  
        }  
          
        //�������淽���е���publishProgressʱ���÷�������,�÷�����UI�߳��б�ִ��  
        protected void onProgressUpdate(Integer...progress){  
            stateView.setText(progress[0].toString());  
        }  
          
        protected void onPostExecute(Void result){  
            btnStop.setEnabled(false);  
            btnStart.setEnabled(true);  
            btnPlay.setEnabled(true);  
            btnFinish.setEnabled(false);  
        }  
          
        protected void onPreExecute(){  
            //stateView.setText("����¼��");  
            btnStart.setEnabled(false);  
            btnPlay.setEnabled(false);  
            btnFinish.setEnabled(false);  
            btnStop.setEnabled(true);         
        }  
          
    }  
      
    class PlayTask extends AsyncTask<Void, Integer, Void>{  
        @Override  
        protected Void doInBackground(Void... arg0) {  
            isPlaying = true;  
            int bufferSize = AudioTrack.getMinBufferSize(frequence, channelConfig, audioEncoding);  
            short[] buffer = new short[bufferSize/4];  
            try {  
                //����������������Ƶд�뵽AudioTrack���У�ʵ�ֲ���  
                DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(audioFile)));  
                //ʵ��AudioTrack  
                AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, frequence, channelConfig, audioEncoding, bufferSize, AudioTrack.MODE_STREAM);  
                //��ʼ����  
                track.play();  
                //����AudioTrack���ŵ����������ԣ�������Ҫһ�߲���һ�߶�ȡ  
                while(isPlaying && dis.available()>0){  
                    int i = 0;  
                    while(dis.available()>0 && i<buffer.length){  
                        buffer[i] = dis.readShort();  
                        i++;  
                    }  
                    //Ȼ������д�뵽AudioTrack��  
                    track.write(buffer, 0, buffer.length);  
                      
                }  
                  
                //���Ž���  
                track.stop();  
                dis.close();  
            } catch (Exception e) {  
                // TODO: handle exception  
            }  
            return null;  
        }  
          
        protected void onPostExecute(Void result){  
            btnPlay.setEnabled(true);  
            btnFinish.setEnabled(false);  
            btnStart.setEnabled(true);  
            btnStop.setEnabled(false);  
        }  
          
        protected void onPreExecute(){    
              
            //stateView.setText("���ڲ���");  
            btnStart.setEnabled(false);  
            btnStop.setEnabled(false);  
            btnPlay.setEnabled(false);  
            btnFinish.setEnabled(true);           
        }  
          
    }  
}  
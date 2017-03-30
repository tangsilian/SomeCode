package com.tangsilian.checkthesandbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    //模拟器特性的数组
    public static  String[] vmcheck={
            /*检验魔趣的信息*/
       "/dev/qemu_pipe","/dev/socket/qemud","/dev/socket/baseband_genyd","/dev/socket/genyd"
            ,"/system/bin/qemu-props","/sys/qemu_trace","/system/libc_malloc_debg_qem.so",
          /*检验模拟器信息*/
"/system/xbin/mount.vboxsf","/ueventd.android_x86.rc","/ueventd.vbox86.rc","/system/usr/keylayout/androVM_Virtual_Input.kl","/system/usr/idc/androVM_Virtual_Input.idc"
    };
    public static  String[] kenerlcheck={
            "proc/tty/drivers"//模拟器中cpuinfo的硬件为Goldfish
            ,"/proc/cpuinfo"
    };
    private static String[] known_qemu_drivers = {
            "goldfish"
    };
String kernel="";
String kernelDetail="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //文件处理
        try {
            //创建文件
            mkdir.creatTxtFile();
            //检测模拟器
            checkfileexit(vmcheck);
            //检测内核版本
            checkkernel(kenerlcheck);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    tv.setText("模拟器特性:"+"\r\n"+kernel+"\r\n"+kernelDetail);
    }

    //循环检测文件是否存在
    public void checkfileexit(String[] a) throws IOException {
        for (int i=0;i<vmcheck.length;i++){
            if(new File(vmcheck[i]).exists()) {
                mkdir.writeTxtFile(vmcheck[i]+" --    -- "+"TRUE"+"\r\n");
                kernel=kernel+vmcheck[i]+" --     --  "+"TRUE"+"\r\n";
                //Log.e("tangsilian",vmcheck[i]);
            }/*else {
                //mkdir.writeTxtFile(a[i]+"  sanbox not exit: "+"FALSE"+"");

            }*/
        }
    }
    //循环检测匹配内核信息
    public void checkkernel(String[] a) throws IOException {
        kernelDetail=kernelDetail+"cpu信息："+getCpuName()+"\r\n"+"cpu是否包含goldfish--："+getCpu1Name()+"\r\n"+"内核信息是否是goldfish--:"+checkQEmuDriverFile();
    }
    // 获取CPU名字
    public static String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String kenrel="";
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
                kenrel=kenrel+array[i]+"\r\n";
            }
            return kenrel;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Boolean getCpu1Name(){
        File driver_file = new File("/proc/cpuinfo");
        if(driver_file.exists() && driver_file.canRead()){
            byte[] data = new byte[1024];  //(int)driver_file.length()
            try {
                InputStream inStream = new FileInputStream(driver_file);
                inStream.read(data);
                inStream.close();
            } catch (Exception e) {
// TODO: handle exception
                e.printStackTrace();
            }
            String driver_data = new String(data);
            for(String known_qemu_driver : MainActivity.known_qemu_drivers){
                if(driver_data.indexOf(known_qemu_driver) != -1){
                    Log.i("Result:", "Find know_qemu_drivers!");
                    return true;
                }
            }
        }
        Log.i("Result:", "Not Find known_qemu_drivers!");
        return false;
    }

    public static Boolean checkQEmuDriverFile(){
        File driver_file = new File("/proc/tty/drivers");
        if(driver_file.exists() && driver_file.canRead()){
            byte[] data = new byte[1024];  //(int)driver_file.length()
            try {
                InputStream inStream = new FileInputStream(driver_file);
                inStream.read(data);
                inStream.close();
            } catch (Exception e) {
// TODO: handle exception
                e.printStackTrace();
            }
            String driver_data = new String(data);
            for(String known_qemu_driver : MainActivity.known_qemu_drivers){
                if(driver_data.indexOf(known_qemu_driver) != -1){
                    Log.i("Result:", "Find know_qemu_drivers!");
                    return true;
                }
            }
        }
        Log.i("Result:", "Not Find known_qemu_drivers!");
        return false;
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
/*    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }*/
}

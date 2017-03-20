package com.tangsilian.checkthesandbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //模拟器特性的数组
    public static  String[] vmcheck={
            "proc/tty/drivers","/system/bin/qemu-props","/proc/cpuinfo","/proc/net/tcp","/system/libc_malloc_debg_qem.so",
            "/sys/qemu_trace","/system/bin/qemu-props","/dev/socket/genyd","/dev/socket/baseband_genyd","/dev/socket/qemud",
            "/dev/qemu_pipe","/data/bluestacks.prop","/data/youwave_id", "/dev/vboxguest","/dev/vboxuser","/fstab.vbox86",
            "/init.vbox86.rc","/mnt/prebundledapps/propfiles/ics.bluestacks.prop.note","/sys/bus/pci/drivers/vboxguest",
            "/sys/class/misc/vboxuser","/system/bin/get_androVM_host","/system/bin/mount.vboxsf","/ueventd.vbox86.rc",
            "/ueventd.android_x86.rc","/system/xbin/mount.vboxsf"

    };

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    tv.setText("你根本不是老司机");
    }

    //循环检测文件是否存在
    public static void checkfileexit(String[] a) throws IOException {
        for (int i=0;i<vmcheck.length;i++){
            if(new File(vmcheck[i]).exists()) {
                mkdir.writeTxtFile(vmcheck[i]+" ----sanbox exit:  "+"TRUE"+"\r\n");
                Log.e("tangsilian",vmcheck[i]);
            }/*else {
                //mkdir.writeTxtFile(a[i]+"  sanbox not exit: "+"FALSE"+"");
            }*/
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        //System.loadLibrary("native-lib");
    }
}

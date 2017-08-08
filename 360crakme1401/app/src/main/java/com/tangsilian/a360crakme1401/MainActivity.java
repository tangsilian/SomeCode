package com.tangsilian.a360crakme1401;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    // Example of a call to a native method
    TextView tv = (TextView) findViewById(R.id.sample_text);
    //tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.

    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }*/
    //public static volatile transient /* synthetic */ IncrementalChange $change;
    public static final long serialVersionUID = 0;
    public EditText Name;
    public EditText Pass;

    public void check(String name, String pass) {
       // IncrementalChange incrementalChange = $change;
     /*   if (incrementalChange != null) {
            incrementalChange.access$dispatch("check.(Ljava/lang/String;Ljava/lang/String;)V", new Object[]{this, name, pass});
        } else */
     if (name.equals("heziran") && pass.equals("123")) {
            Toast.makeText(this, "登录成功", 0).show();
            Intent intent = new Intent();
            intent.setClass(this, Success.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "登录失败", 0).show();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
    /*    IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("onCreate.(Landroid/os/Bundle;)V", new Object[]{this, savedInstanceState});
            return;
        }*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*this.Name = (EditText) findViewById(R.id.TEXT_NAME);
        this.Pass = (EditText) findViewById(R.id.TEST_PASS);*/
       // ((Button) findViewById(R.id.BTN_Login)).setOnClickListener(new 1(this));
    }



}

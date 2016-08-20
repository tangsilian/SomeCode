package com.droider.jnimethods;

import android.app.Activity;
import android.os.Bundle;

public class JnimethodsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TestJniMethods methods = new TestJniMethods();        
        //android.os.Debug.waitForDebugger();
        methods.test();
        setTitle(methods.nativeMethod());
        methods.newJniThreads(5);    //����5���߳�
        Object obj = methods.allocNativeBuffer(16L); //�����ֽڻ�����
        methods.freeNativeBuffer(obj);  //�ͷ��ֽڻ�����
    }
     
}
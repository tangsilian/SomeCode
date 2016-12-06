package com.example.tangsilian.onebyonetest;


import android.content.Context;
import android.content.res.AssetManager;

/**
 * Created by tangsilian on 2016-11-30.
 */

public class MyLoadSoClass {


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     * @param s
     * @param parent
     * @param assets
     */
    public static native void decodefromasset(Context context);
    //public static native void decodefromasset(AssetManager ass,String filename);
    public static native void loaddex(Context context);

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}

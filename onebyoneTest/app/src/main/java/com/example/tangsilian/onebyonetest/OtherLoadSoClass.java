package com.example.tangsilian.onebyonetest;


import android.app.Activity;
import android.content.res.AssetManager;
import dalvik.system.DexClassLoader;
/**
 * Created by tangsilian on 2016-11-30.
 */

public class OtherLoadSoClass {
    private DexClassLoader mDex;

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    static native public void loaddex(String dexName, String DataPath, AssetManager asset);
    static native public void decodefromasset(String dexName,String DataPath,AssetManager asset);
    static {
        System.loadLibrary("native-lib");
    }
}

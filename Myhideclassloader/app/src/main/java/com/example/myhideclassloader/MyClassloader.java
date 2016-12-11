package com.example.myhideclassloader;

import dalvik.system.DexClassLoader;

/**
 * Created by tangsilian on 2016-12-8.
 */

public class MyClassloader  extends DexClassLoader{
    public MyClassloader(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent) {
        super(dexPath, optimizedDirectory, librarySearchPath, parent);
    }
}

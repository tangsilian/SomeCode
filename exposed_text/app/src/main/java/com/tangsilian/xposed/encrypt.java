package com.tangsilian.xposed;

import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Created by tangsilian on 2017-10-21.
 */

public class encrypt implements IXposedHookLoadPackage {
    /**
     * 包加载时候的回调
     */
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.tencent.qqpimsecure")) {
            XposedHelpers.findAndHookMethod("com.tencent.tccdb.TccCryptor", lpparam.classLoader, "encrypt", Context.class,byte[].class,
                    byte[].class,new XC_MethodHook() {

                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("encrypt-----length " + ((byte[]) param.args[0]).length+"\n");
                            XposedBridge.log("encrypt------------>>>>>>> " + new String((byte[]) param.args[0])+"\n");
/*                          String string=new String(array);
                            XposedBridge.log("encode-----param.getResult().toString()------->>>>>>> " + string);*/
                        }
                    });
        }
    }


}

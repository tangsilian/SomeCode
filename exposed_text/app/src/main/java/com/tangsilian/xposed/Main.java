package com.tangsilian.xposed;

/**
 * Created by tangsilian on 2017-10-16.
 */

import android.content.Context;

import java.util.Collection;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Main implements IXposedHookLoadPackage {

    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.tencent.qqpimsecure")) {
            XposedHelpers.findAndHookMethod("bfp", lpparam.classLoader, "d", Context.class, new XC_MethodHook() {
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("args[0]"+ param.args[0]);
                    XposedBridge.log("args[1]"+ param.args[1]);
                    XposedBridge.log("getByClass-----"+ param.getResult().toString());
                    XposedBridge.log("getByClass-----"+ param.getResult().toString().length());
                    Collection collection = (Collection) param.getResult();
                    if (!collection.isEmpty()) {
                        XposedBridge.log("getByClass----size()-------->>>>>>> " + collection.size());
                    }
                }
            }
            );
        }
    }


}

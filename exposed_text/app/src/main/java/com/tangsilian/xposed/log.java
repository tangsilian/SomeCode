package com.tangsilian.xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Created by tangsilian on 2017-10-18.
 */
public class log implements IXposedHookLoadPackage {
    /**
     * 包加载时候的回调
     */
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.tencent.qqpimsecure")) {

            XposedHelpers.findAndHookMethod("bft",lpparam.classLoader,"a",Boolean.class,new XC_MethodHook() {
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("bofore------------>>>>>>> " + param.args[0]+"\n");
                            Boolean flag=true;
                            param.args[0]=flag;
                        }
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("after------------>>>>>>> " + param.args[0]+"\n");
                        }
                    });
        }
    }


}

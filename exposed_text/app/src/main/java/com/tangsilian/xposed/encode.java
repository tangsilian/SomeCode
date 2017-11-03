package com.tangsilian.xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class encode implements IXposedHookLoadPackage {
    /**
     * 包加载时候的回调
     */
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.tencent.qqpimsecure")) {
            XposedHelpers.findAndHookMethod("com.qq.jce.wup.UniPacket", lpparam.classLoader, "decode",byte[].class,
                    new XC_MethodHook() {

                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("encode-----length " + ((byte[]) param.args[0]).length+"\n");
                            XposedBridge.log("encode------------>>>>>>> " + new String((byte[]) param.args[0])+"\n");
/*                          String string=new String(array);
                            XposedBridge.log("encode-----param.getResult().toString()------->>>>>>> " + string);*/
                        }
                    });
        }
    }


}

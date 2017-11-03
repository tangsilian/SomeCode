package com.tangsilian.xposed;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Created by tangsilian on 2017-10-17.
 */
    public class post implements IXposedHookLoadPackage {
    /**
     * 打印post的值
     */
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.tencent.qqpimsecure")) {
/*            XposedHelpers.findAndHookMethod("bfn", lpparam.classLoader, "a", new Object[]
                    {String.class,byte[].class,new XC_MethodHook() {
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("url------------>>>>>>> " + param.args[0]);

                            byte[] array = (byte[]) param.args[1];

                            for (int i=0;i<array.length;i++){
                                XposedBridge.log("byte["+i+"]------------   ," + array[i]);
                            }
                            XposedBridge.log("byte length ------------>>>>>>>" + array.length);
                        }
                    }
                    }*/
            XposedHelpers.findAndHookMethod("bbj", lpparam.classLoader, "a",List.class, ArrayList.class,new XC_MethodHook() {
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("LIST      " + param.args[0]);

                            List array = (List) param.args[0];
                            for (int i=0;i<array.size();i++){
                                XposedBridge.log("LIST["+i+"]      " + array.get(i));
                            }


                            XposedBridge.log("array length ------------>>>>>>>" + array.size());
                        }

                    }

            );
        }
    }
}
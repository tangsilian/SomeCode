package com.example.myhideclassloader;


import android.accessibilityservice.AccessibilityService;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

/**
 * Created by tangsilian on 2016-12-10.
 */

public class QHBAccessibilityService extends AccessibilityService {
    public String TAG="TSL";

    //根据外部的事件进行处理
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        switch (eventType) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                //通知栏窗口变化
                handleNotification(event);
                Log.d(TAG,"find the hongbao");
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                String className = event.getClassName().toString();
                if (className.equals("com.tencent.mobileqq.activity.SplashActivity")) {
                    getLastPacket();
                    Log.d(TAG,"getPacket");
                } else if (className.equals("cooperation.qwallet.plugin.QWalletPluginProxyActivity")) {
                    AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
                    if (nodeInfo != null) {
                        //查看领取详情
                        List<AccessibilityNodeInfo> infos = nodeInfo.findAccessibilityNodeInfosByText("查看领取详情");
                        if (infos != null && infos.size() > 0) {
                            AccessibilityNodeInfo info = infos.get(infos.size() - 1);
                            if (infos == null) {
                                info.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                            else if (infos != null && infos != info) {
                                info.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                            else {
                                return;
                            }
                        }
                    }
                }
        }
    }

    /**
     * 获取List中最后一个红包，并进行模拟点击
     */
    private void getLastPacket() {
        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        recyclehongbao(rootNode);
    }

    /**
     * 回归函数遍历每一个节点，并将含有"领取红包"存进List中
     *
     * @param info
     */
    public void recyclehongbao(AccessibilityNodeInfo info) {
        if (info.getChildCount() == 0) {
            if (info.getText() != null) {
               boolean uzi =true;
                    Log.d("TSL",info.getText().toString());
                if (info.getText().toString().contains("你领取了")){
                    Log.d("TSL",info.getText().toString()+"领取过了");
                    uzi=false;
                }
                if (info.getText().toString().contains("恭喜发财")&&uzi) {
                    Log.d("TSL","恭喜发财红包");
                   //这是遍历节点的方法不太好
                    AccessibilityNodeInfo parent = info.getParent();
                    if (info.isClickable()) {
                        info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        Log.d("TSL",info.getText().toString());
                    }else {
                        while (parent != null) {
                            if (parent.isClickable()) {
                                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                break;
                            }
                            parent = parent.getParent();
                        }
                    }
                }else if (info.getText().toString().contains("QQ红包个性版")&&uzi){
                    Log.d("TSL","QQ红包个性版");
                    AccessibilityNodeInfo parent = info.getParent();
                    if (info.isClickable()) {
                        info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        Log.d("TSL",info.getText().toString());
                    }else {
                        while (parent != null) {
                            if (parent.isClickable()) {
                                parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                                break;
                            }
                            parent = parent.getParent();
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < info.getChildCount(); i++) {
                if (info.getChild(i) != null) {
                    recyclehongbao(info.getChild(i));
                }
            }
        }
    }



    /**
     * 处理通知栏信息
     *
     * 如果是红包的提示信息,则模拟点击
     *
     * @param event
     */
    private void handleNotification(AccessibilityEvent event) {
        List<CharSequence> texts = event.getText();
        if (!texts.isEmpty()) {
            for (CharSequence text : texts) {
                String content = text.toString();
                //如果红包的提示信息,则模拟点击进入相应的聊天窗口
                if (content.contains("[QQ红包]")) {
                    if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification) {
                        Notification notification = (Notification) event.getParcelableData();
                        PendingIntent pendingIntent = notification.contentIntent;
                        try {
                            pendingIntent.send();
                        } catch (PendingIntent.CanceledException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    /**
     * 关闭红包详情界面,实现自动返回聊天窗口
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void close() {

    }
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }
    @Override
    public void onInterrupt() {
    }
}

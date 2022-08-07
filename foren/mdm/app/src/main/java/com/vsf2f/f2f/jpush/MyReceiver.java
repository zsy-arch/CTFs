package com.vsf2f.f2f.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;
import com.em.DemoHelper;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.vsf2f.f2f.ui.MainActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        MyLog.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            MyLog.d(TAG, "[MyReceiver] 接收Registration Id : " + bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID));
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            MyLog.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            processCustomMessage(context, bundle);
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            MyLog.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            MyLog.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID));
            DemoHelper.getInstance().showJPushAlert(bundle.getString(JPushInterface.EXTRA_EXTRA));
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            MyLog.d(TAG, "[MyReceiver] 用户点击打开了通知");
            try {
                String href = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA)).optString("vs_href");
                if (!TextUtils.isEmpty(href)) {
                    Intent intentWeb = new Intent(context, WebKitLocalActivity.class);
                    Bundle bundleWeb = new Bundle();
                    bundleWeb.putString(Constant.FLAG, href);
                    bundleWeb.putString(Constant.FLAG_TITLE, com.vsf2f.f2f.ui.utils.Constant.FLAG_AUTO_TITLE);
                    intentWeb.putExtra(Constant.BUNDLE, bundleWeb);
                    intentWeb.addFlags(268435456);
                    context.startActivity(intentWeb);
                } else {
                    Intent intentMain = new Intent(context, MainActivity.class);
                    intentMain.putExtra(com.vsf2f.f2f.ui.utils.Constant.FLAG_INTENT, 0);
                    intentMain.putExtra(com.vsf2f.f2f.ui.utils.Constant.FLAG_ACT, com.vsf2f.f2f.ui.utils.Constant.ACT_LAUNCH);
                    intentMain.addFlags(268435456);
                    context.startActivity(intentMain);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            MyLog.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            MyLog.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false));
        } else {
            MyLog.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (!key.equals(JPushInterface.EXTRA_EXTRA)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            } else if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                Log.i(TAG, "This message has no Extra data");
            } else {
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();
                    while (it.hasNext()) {
                        String myKey = it.next().toString();
                        sb.append("\nkey:" + key + ", value: [" + myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }
            }
        }
        return sb.toString();
    }

    private void processCustomMessage(Context context, Bundle bundle) {
        if (JPushActivity.isForeground) {
            bundle.getString(JPushInterface.EXTRA_MESSAGE);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            Log.i("Jpush", extras + "~~");
            int iconType = 0;
            if (extras != null) {
                try {
                    JSONObject object = new JSONObject(extras);
                    object.optInt("num");
                    object.optString("title", "hello");
                    iconType = object.optInt("iconType");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            switch (iconType) {
                case 0:
                    return;
                case 1:
                    return;
                default:
                    return;
            }
        }
    }
}

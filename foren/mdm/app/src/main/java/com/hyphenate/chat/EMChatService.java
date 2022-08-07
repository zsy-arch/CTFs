package com.hyphenate.chat;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.hyphenate.chat.EMPushHelper;
import com.hyphenate.util.EMLog;

@SuppressLint({"Registered"})
/* loaded from: classes2.dex */
public class EMChatService extends Service {
    private static final String TAG = "chatservice";
    private final IBinder mBinder = new LocalBinder();

    /* loaded from: classes2.dex */
    public class LocalBinder extends Binder {
        public LocalBinder() {
            EMChatService.this = r1;
        }

        EMChatService getService() {
            return EMChatService.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        EMLog.d(TAG, "onBind");
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        EMLog.i(TAG, "chat service created");
    }

    @Override // android.app.Service
    public void onDestroy() {
        EMLog.d(TAG, "onDestroy");
        if (EMPushHelper.getInstance().getPushType() == EMPushHelper.EMPushType.NORMAL) {
            new Thread(new Runnable() { // from class: com.hyphenate.chat.EMChatService.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (!EMClient.getInstance().stopService) {
                            EMClient.getInstance().doStartService();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override // android.app.Service
    public int onStartCommand(final Intent intent, int i, int i2) {
        if (intent != null && EMPushHelper.getInstance().getPushType() == EMPushHelper.EMPushType.NORMAL && !EMMonitor.getInstance().isStarted()) {
            final String name = getClass().getName();
            new Thread(new Runnable() { // from class: com.hyphenate.chat.EMChatService.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String str = "";
                        if (intent.hasExtra("reason")) {
                            str = intent.getStringExtra("reason");
                        }
                        EMMonitor.getInstance().start(EMChatService.this, EMChatService.this.getPackageName() + "/" + name);
                        EMMonitor.getInstance().startWakeup(EMChatService.this, str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        if (EMPushHelper.getInstance().getPushType() == EMPushHelper.EMPushType.GCM) {
            EMLog.d(TAG, "start not sticky!");
            return 2;
        }
        EMLog.d(TAG, "start sticky!");
        return 1;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }
}

package com.hyphenate.chat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.hyphenate.util.EMLog;

/* loaded from: classes2.dex */
public class EMGCMBroadcastReceiver extends WakefulBroadcastReceiver {
    private String TAG = "EMGCMBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        EMLog.d(this.TAG, "gcm broadcastreceive!");
        try {
            if (intent.getExtras().getString("alert") != null) {
                startWakefulService(context, intent.setComponent(new ComponentName(context.getPackageName(), EMGCMListenerService.class.getName())));
                setResultCode(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

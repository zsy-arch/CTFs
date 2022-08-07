package com.hyphenate.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hyphenate.util.EMLog;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class EMHeartBeatReceiver extends BroadcastReceiver {
    private static final String TAG = "EMHeartBeatReceiver";
    EMSmartHeartBeat smartHeartbeat;

    public EMHeartBeatReceiver(EMSmartHeartBeat eMSmartHeartBeat) {
        this.smartHeartbeat = null;
        this.smartHeartbeat = eMSmartHeartBeat;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        EMLog.d(TAG, "onReceive EMHeartBeatReceiver");
        if (this.smartHeartbeat != null) {
            this.smartHeartbeat.start();
        }
    }
}

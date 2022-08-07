package com.hyphenate.chat;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;

@SuppressLint({"Registered"})
/* loaded from: classes2.dex */
public class EMGCMListenerService extends IntentService {
    public static final String TAG = "EMGcmListenerService";

    public EMGCMListenerService() {
        super(TAG);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        Intent intent2 = new Intent();
        intent2.setAction("com.hyphenate.sdk.push");
        intent2.addCategory(EMClient.getInstance().getContext().getPackageName());
        intent2.putExtra("alert", intent.getExtras().getString("alert"));
        sendBroadcast(intent2);
        EMGCMBroadcastReceiver.completeWakefulIntent(intent);
    }
}

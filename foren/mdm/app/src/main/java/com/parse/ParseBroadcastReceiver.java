package com.parse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: classes2.dex */
public class ParseBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "com.parse.ParseBroadcastReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        PLog.d(TAG, "received " + intent.getAction());
        PushService.startServiceIfRequired(context);
    }
}

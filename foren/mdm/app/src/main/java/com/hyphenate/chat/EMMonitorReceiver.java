package com.hyphenate.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hyphenate.util.EMLog;

/* loaded from: classes2.dex */
public class EMMonitorReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!"android.intent.action.PACKAGE_REMOVED".equals(intent.getAction())) {
            context.startService(new Intent(context, EMChatService.class));
        } else if (!intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
            EMLog.d("EMMonitorReceiver", intent.getData().getSchemeSpecificPart() + " be removed");
            EMMonitor.getInstance().getMonitorDB().b(intent.getData().getSchemeSpecificPart());
        }
    }
}

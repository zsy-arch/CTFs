package com.em.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.hyphenate.chat.EMChatService;
import com.hyphenate.util.EMLog;

/* loaded from: classes.dex */
public class StartServiceReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED") || intent.getAction().equals("android.intent.action.QUICKBOOT_POWERON")) {
            EMLog.d("boot", "start IM service on boot");
            Intent startServiceIntent = new Intent(context, EMChatService.class);
            startServiceIntent.putExtra("reason", "boot");
            context.startService(startServiceIntent);
        }
    }
}

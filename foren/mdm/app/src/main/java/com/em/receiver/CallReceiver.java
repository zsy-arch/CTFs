package com.em.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.em.DemoHelper;
import com.em.ui.VideoCallActivity;
import com.em.ui.VoiceCallActivity;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.util.EMLog;

/* loaded from: classes.dex */
public class CallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (DemoHelper.getInstance().isLoggedIn()) {
            String from = intent.getStringExtra(MessageEncoder.ATTR_FROM);
            if ("video".equals(intent.getStringExtra("type"))) {
                context.startActivity(new Intent(context, VideoCallActivity.class).putExtra("username", from).putExtra("isComingCall", true).addFlags(268435456));
            } else {
                context.startActivity(new Intent(context, VoiceCallActivity.class).putExtra("username", from).putExtra("isComingCall", true).addFlags(268435456));
            }
            EMLog.d("CallReceiver", "app received a incoming call");
        }
    }
}

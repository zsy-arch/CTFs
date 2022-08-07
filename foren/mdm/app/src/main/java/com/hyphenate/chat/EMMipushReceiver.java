package com.hyphenate.chat;

import android.content.Context;
import android.content.Intent;
import com.hyphenate.util.EMLog;
import com.xiaomi.mipush.sdk.MiPushCommandMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;
import java.util.List;

/* loaded from: classes2.dex */
public class EMMipushReceiver extends PushMessageReceiver {
    private static final String TAG = EMMipushReceiver.class.getSimpleName();

    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
        EMMipushReceiver.super.onNotificationMessageClicked(context, miPushMessage);
        EMLog.d(TAG, "mi push onNotificationMessageClicked");
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.addFlags(268435456);
        context.startActivity(launchIntentForPackage);
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
        EMMipushReceiver.super.onReceiveRegisterResult(context, miPushCommandMessage);
        if ("register".equals(miPushCommandMessage.getCommand())) {
            List commandArguments = miPushCommandMessage.getCommandArguments();
            String str = (commandArguments == null || commandArguments.size() <= 0) ? null : (String) commandArguments.get(0);
            if (miPushCommandMessage.getResultCode() == 0) {
                EMLog.d(TAG, "mi push reigster success");
                EMPushHelper.getInstance().onReceiveToken(str);
                return;
            }
            EMLog.d(TAG, "mi push register fail");
            EMPushHelper.getInstance().onReceiveToken(null);
        }
    }
}

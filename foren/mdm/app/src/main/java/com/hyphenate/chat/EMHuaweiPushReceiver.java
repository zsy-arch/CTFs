package com.hyphenate.chat;

import android.content.Context;
import android.os.Bundle;
import com.huawei.android.pushagent.api.PushEventReceiver;
import com.hyphenate.util.EMLog;

/* loaded from: classes2.dex */
public class EMHuaweiPushReceiver extends PushEventReceiver {
    private static final String TAG = EMHuaweiPushReceiver.class.getSimpleName();

    public void onToken(Context context, String str, Bundle bundle) {
        if (str != null) {
            EMLog.d(TAG, "register huawei push token success");
            EMPushHelper.getInstance().onReceiveToken(str);
            return;
        }
        EMLog.e(TAG, "register huawei push token fail");
        EMPushHelper.getInstance().onReceiveToken(null);
    }
}

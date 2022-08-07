package com.ut.device;

import android.content.Context;
import com.ta.utdid2.aid.AidManager;

/* loaded from: classes2.dex */
public class UTDevice {
    public static String getUtdid(Context context) {
        return com.ta.utdid2.device.UTDevice.getUtdid(context);
    }

    public static String getAid(String appName, String token, Context context) {
        return AidManager.getInstance(context).getValue(appName, token, getUtdid(context));
    }

    public static void getAidAsync(String appName, String token, Context context, AidCallback callback) {
        AidManager.getInstance(context).requestAid(appName, token, getUtdid(context), callback);
    }
}

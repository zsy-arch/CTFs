package com.amap.api.col;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

/* compiled from: DeviceUtil.java */
/* loaded from: classes.dex */
public class de {
    public static String a(Context context) {
        String q = ge.q(context);
        if (TextUtils.isEmpty(q)) {
            q = ge.f(context);
            if (TextUtils.isEmpty(q)) {
                q = a();
            }
        }
        return gg.c(q);
    }

    private static String a() {
        return "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
    }
}

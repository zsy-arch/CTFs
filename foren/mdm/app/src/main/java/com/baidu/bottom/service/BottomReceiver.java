package com.baidu.bottom.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.baidu.mobstat.ar;
import com.baidu.mobstat.bb;
import com.baidu.mobstat.ct;
import com.baidu.mobstat.m;

/* loaded from: classes.dex */
public class BottomReceiver extends BroadcastReceiver {
    private static ct a;
    private static long b;
    private static long c;

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, Intent intent) {
        String action = intent.getAction();
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(c - currentTimeMillis) <= 30000) {
            return;
        }
        if ("android.net.wifi.STATE_CHANGE".equals(action) || "android.net.wifi.WIFI_STATE_CHANGED".equals(action) || "android.net.wifi.SCAN_RESULTS".equals(action)) {
            c = currentTimeMillis;
            m.a(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.PACKAGE_ADDED".equals(action) || "android.intent.action.PACKAGE_REMOVED".equals(action) || "android.intent.action.PACKAGE_REPLACED".equals(action)) {
            String str = null;
            Uri data = intent.getData();
            if (data != null) {
                str = data.getSchemeSpecificPart();
            }
            if (!TextUtils.isEmpty(str)) {
                m.a(context, action, str);
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (a != null) {
            bb.a("Bottom has alread analyzed.");
            return;
        }
        ct ctVar = new ct();
        if (ctVar.a()) {
            a = ctVar;
            new ar(this, context, intent, ctVar).start();
        }
    }
}

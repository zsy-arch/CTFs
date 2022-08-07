package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class bh extends Thread {
    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Context context;
        Context context2;
        context = WebView.j;
        if (context == null) {
            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--mAppContext == null");
            return;
        }
        l a = l.a(true);
        if (l.a) {
            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--needReboot = true");
            return;
        }
        context2 = WebView.j;
        ae a2 = ae.a(context2);
        int c = a2.c();
        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--installStatus = " + c);
        if (c == 2) {
            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--install setTbsNeedReboot true");
            a.a(String.valueOf(a2.b()));
            a.b(true);
            return;
        }
        int b = a2.b("copy_status");
        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copyStatus = " + b);
        if (b == 1) {
            TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copy setTbsNeedReboot true");
            a.a(String.valueOf(a2.c("copy_core_ver")));
            a.b(true);
        } else if (bi.b().c()) {
        } else {
            if (c == 3 || b == 3) {
                TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--setTbsNeedReboot true");
                a.a(String.valueOf(l.d()));
                a.b(true);
            }
        }
    }
}

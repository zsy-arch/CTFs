package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.utils.TbsLog;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class f extends Handler {
    final /* synthetic */ QbSdk.PreInitCallback a;
    final /* synthetic */ Context b;
    final /* synthetic */ ai c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Looper looper, QbSdk.PreInitCallback preInitCallback, Context context, ai aiVar) {
        super(looper);
        this.a = preInitCallback;
        this.b = context;
        this.c = aiVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                bj d = bi.b().d();
                if (d != null) {
                    d.a(this.b);
                }
                if (this.a != null) {
                    this.a.onViewInitFinished(true);
                    return;
                }
                return;
            case 2:
                if (this.a != null) {
                    this.a.onViewInitFinished(false);
                }
                TbsLog.writeLogToDisk();
                return;
            case 3:
                this.c.a("init_all", (byte) 2);
                this.c.a(7, "tbs://preinit");
                if (this.a != null) {
                    this.a.onCoreInitFinished();
                    return;
                }
                return;
            default:
                return;
        }
    }
}

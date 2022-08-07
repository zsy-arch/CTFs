package com.baidu.mobstat;

import android.content.Context;
import android.content.Intent;
import com.baidu.bottom.service.BottomReceiver;

/* loaded from: classes.dex */
public class ar extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ Intent b;
    final /* synthetic */ ct c;
    final /* synthetic */ BottomReceiver d;

    public ar(BottomReceiver bottomReceiver, Context context, Intent intent, ct ctVar) {
        this.d = bottomReceiver;
        this.a = context;
        this.b = intent;
        this.c = ctVar;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        long j;
        try {
            this.d.b(this.a, this.b);
            this.d.a(this.a, this.b);
            long currentTimeMillis = System.currentTimeMillis();
            j = BottomReceiver.b;
            if (currentTimeMillis - j < 30000) {
                bb.a("No need to handle receiver due to time strategy");
                return;
            }
            long unused = BottomReceiver.b = currentTimeMillis;
            an.b.a(this.a);
        } finally {
            this.c.b();
            ct unused2 = BottomReceiver.a = null;
        }
    }
}

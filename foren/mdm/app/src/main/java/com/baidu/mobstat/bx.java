package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bx implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ bs c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bx(bs bsVar, Context context, String str) {
        this.c = bsVar;
        this.a = context;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean b;
        String str = "__send_data_" + System.currentTimeMillis();
        cl.a(this.a, str, this.b, false);
        b = this.c.b(this.a, this.b);
        if (b) {
            cl.b(this.a, str);
        }
    }
}

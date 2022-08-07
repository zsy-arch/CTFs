package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bq implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ bp b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bq(bp bpVar, Context context) {
        this.b = bpVar;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (!an.b(this.a)) {
                an.a(2).a(this.a);
            }
        } catch (Throwable th) {
        }
        this.b.e = false;
    }
}

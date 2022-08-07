package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class al implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ ak c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public al(ak akVar, String str, Context context) {
        this.c = akVar;
        this.a = str;
        this.b = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.c.a(this.a);
            if (this.b != null) {
                this.c.a(this.b.getApplicationContext());
            }
        } catch (Throwable th) {
            bb.b(th);
        }
    }
}

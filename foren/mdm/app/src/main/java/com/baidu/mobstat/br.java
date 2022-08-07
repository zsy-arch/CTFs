package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class br extends Thread {
    final /* synthetic */ bp a;

    private br(bp bpVar) {
        this.a = bpVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ br(bp bpVar, bq bqVar) {
        this(bpVar);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Context context;
        Context context2;
        cr.a("**************load cache start********");
        bp bpVar = this.a;
        context = this.a.b;
        bpVar.c(context);
        bs a = bs.a();
        context2 = this.a.b;
        a.b(context2);
        cr.a("**************load cache end********");
    }
}

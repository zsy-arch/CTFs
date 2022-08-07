package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bi implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ long e;
    final /* synthetic */ bh f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bi(bh bhVar, Context context, String str, String str2, int i, long j) {
        this.f = bhVar;
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = i;
        this.e = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        cr.c("onEvent before");
        bp.a().c();
        this.f.a(this.a, this.b, this.c, this.d, this.e, 0L);
    }
}

package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bl implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ bh e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bl(bh bhVar, long j, Context context, String str, String str2) {
        this.e = bhVar;
        this.a = j;
        this.b = context;
        this.c = str;
        this.d = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        bp.a().c();
        if (this.a <= 0) {
            cr.a("EventStat: Wrong Case, Duration must be positive");
        } else {
            this.e.a(this.b, this.c, this.d, 1, System.currentTimeMillis(), this.a);
        }
    }
}

package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bk implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ long c;
    final /* synthetic */ Context d;
    final /* synthetic */ bh e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bk(bh bhVar, String str, String str2, long j, Context context) {
        this.e = bhVar;
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        bp.a().c();
        String a = this.e.a(this.a, this.b);
        bm bmVar = this.e.a.get(a);
        if (bmVar == null) {
            cr.b("EventStat: event_id[" + this.a + "] with label[" + this.b + "] is not started or alread done.");
        } else if (!this.a.equals(bmVar.a) || !this.b.equals(bmVar.b)) {
            cr.a("EventStat: Wrong Case, eventId/label pair not match");
        } else {
            this.e.a.remove(a);
            long j = this.c - bmVar.c;
            if (j <= 0) {
                cr.a("EventStat: Wrong Case, Duration must be positive");
            } else {
                this.e.a(this.d, this.a, this.b, 1, bmVar.c, j);
            }
        }
    }
}

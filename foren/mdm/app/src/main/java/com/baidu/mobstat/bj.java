package com.baidu.mobstat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bj implements Runnable {
    final /* synthetic */ long a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ bh d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bj(bh bhVar, long j, String str, String str2) {
        this.d = bhVar;
        this.a = j;
        this.b = str;
        this.c = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        bp.a().c();
        bm bmVar = new bm();
        bmVar.c = this.a;
        bmVar.a = this.b;
        bmVar.b = this.c;
        String a = this.d.a(this.b, this.c);
        if (this.d.a.get(a) != null) {
            cr.b("EventStat: event_id[" + this.b + "] with label[" + this.c + "] is duplicated, older is removed");
        }
        this.d.a.put(a, bmVar);
        cr.a("put a keyword[" + a + "] into durationlist");
    }
}

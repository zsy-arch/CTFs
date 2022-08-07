package com.tencent.stat;

import com.tencent.stat.a.e;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class r implements Runnable {
    final /* synthetic */ e a;
    final /* synthetic */ c b;
    final /* synthetic */ n c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public r(n nVar, e eVar, c cVar) {
        this.c = nVar;
        this.a = eVar;
        this.b = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.b(this.a, this.b);
    }
}

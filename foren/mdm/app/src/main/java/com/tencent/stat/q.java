package com.tencent.stat;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class q implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ n b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(n nVar, List list) {
        this.b = nVar;
        this.a = list;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.b(this.a);
    }
}

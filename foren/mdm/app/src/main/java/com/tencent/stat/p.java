package com.tencent.stat;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class p implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ int b;
    final /* synthetic */ n c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(n nVar, List list, int i) {
        this.c = nVar;
        this.a = list;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.c.b(this.a, this.b);
    }
}

package com.tencent.stat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class s implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ n b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public s(n nVar, b bVar) {
        this.b = nVar;
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.b(this.a);
    }
}

package com.baidu.mobstat;

/* loaded from: classes.dex */
class bu implements Runnable {
    final /* synthetic */ bt a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bu(bt btVar) {
        this.a = btVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.b.f(this.a.a);
    }
}

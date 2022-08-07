package com.tencent.smtt.sdk;

/* loaded from: classes2.dex */
class e implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ d b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(d dVar, boolean z) {
        this.b = dVar;
        this.a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.c.onReceiveValue(Boolean.valueOf(this.a));
    }
}

package com.tencent.stat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class v implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ n b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public v(n nVar, int i) {
        this.b = nVar;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        int a = StatConfig.a();
        int i = this.a == -1 ? this.b.b : this.a;
        int i2 = i / a;
        int i3 = i % a;
        for (int i4 = 0; i4 < i2 + 1; i4++) {
            this.b.b(a);
        }
        if (i3 > 0) {
            this.b.b(i3);
        }
    }
}

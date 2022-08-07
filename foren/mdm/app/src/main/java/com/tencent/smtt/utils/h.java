package com.tencent.smtt.utils;

/* loaded from: classes2.dex */
class h implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ f b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(f fVar, int i) {
        this.b = fVar;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.b.e.setText("已下载" + this.a + "%");
    }
}

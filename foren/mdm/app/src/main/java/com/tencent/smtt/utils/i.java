package com.tencent.smtt.utils;

import android.widget.Toast;

/* loaded from: classes2.dex */
class i implements Runnable {
    final /* synthetic */ f a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(f fVar) {
        this.a = fVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Toast.makeText(this.a.b, "下载失败，请检查网络", 0).show();
    }
}

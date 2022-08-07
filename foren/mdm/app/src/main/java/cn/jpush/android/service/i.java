package cn.jpush.android.service;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ h b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(h hVar, Context context) {
        this.b = hVar;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.e(this.a);
        this.b.c(this.a);
    }
}

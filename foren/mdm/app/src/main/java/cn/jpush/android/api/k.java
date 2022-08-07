package cn.jpush.android.api;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ f b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(f fVar, Context context) {
        this.b = fVar;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        f.b(this.b, this.a);
    }
}

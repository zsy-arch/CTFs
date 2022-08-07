package cn.jpush.android.api;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ f b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(f fVar, Context context) {
        this.b = fVar;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        f.a(this.b, this.a);
    }
}

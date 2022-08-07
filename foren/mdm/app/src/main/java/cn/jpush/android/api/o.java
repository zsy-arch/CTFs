package cn.jpush.android.api;

import android.content.Context;
import cn.jpush.android.data.c;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class o implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ c b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(Context context, c cVar) {
        this.a = context;
        this.b = cVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        n.b(this.a, this.b);
    }
}

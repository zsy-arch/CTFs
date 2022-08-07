package cn.jpush.android.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.jpush.android.util.ac;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c extends Handler {
    final /* synthetic */ b a;
    private d b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(b bVar, Looper looper, d dVar) {
        super(looper);
        this.a = bVar;
        this.b = null;
        this.b = dVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        c cVar;
        long j;
        long j2;
        super.handleMessage(message);
        if (this.a.a) {
            ac.a();
            return;
        }
        if (this.b != null) {
            d dVar = this.b;
            j = this.a.d;
            j2 = this.a.e;
            dVar.a(j, j2);
        }
        cVar = this.a.c;
        cVar.sendEmptyMessageDelayed(0, 2000L);
    }
}

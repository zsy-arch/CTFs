package com.tencent.stat;

import com.tencent.stat.common.StatLogger;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class f implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ c b;
    final /* synthetic */ d c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(d dVar, List list, c cVar) {
        this.c = dVar;
        this.a = list;
        this.b = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        StatLogger statLogger;
        try {
            this.c.a(this.a, this.b);
        } catch (Throwable th) {
            statLogger = d.c;
            statLogger.e(th);
        }
    }
}

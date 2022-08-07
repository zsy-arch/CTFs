package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.a.d;
import com.tencent.stat.common.StatLogger;
import java.io.File;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class i implements Runnable {
    private Context a;

    public i(Context context) {
        this.a = null;
        this.a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        StatLogger statLogger;
        Iterator<File> it = StatNativeCrashReport.a(this.a).iterator();
        while (it.hasNext()) {
            File next = it.next();
            d dVar = new d(this.a, StatService.a(this.a, false), StatNativeCrashReport.a(next), 3, 10240);
            dVar.a(StatNativeCrashReport.b(next));
            if (StatService.c(this.a) != null) {
                StatService.c(this.a).post(new k(dVar));
            }
            next.delete();
            statLogger = StatService.i;
            statLogger.d("delete tombstone file:" + next.getAbsolutePath().toString());
        }
    }
}

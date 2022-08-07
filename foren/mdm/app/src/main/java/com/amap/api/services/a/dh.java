package com.amap.api.services.a;

import com.amap.api.services.a.di;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* compiled from: ThreadPool.java */
/* loaded from: classes.dex */
public final class dh {
    private static dh a = null;
    private ExecutorService b;
    private ConcurrentHashMap<di, Future<?>> c = new ConcurrentHashMap<>();
    private di.a d = new di.a() { // from class: com.amap.api.services.a.dh.1
        @Override // com.amap.api.services.a.di.a
        public void a(di diVar) {
        }

        @Override // com.amap.api.services.a.di.a
        public void b(di diVar) {
            dh.this.a(diVar, false);
        }
    };

    public static synchronized dh a(int i) {
        dh dhVar;
        synchronized (dh.class) {
            if (a == null) {
                a = new dh(i);
            }
            dhVar = a;
        }
        return dhVar;
    }

    private dh(int i) {
        try {
            this.b = Executors.newFixedThreadPool(i);
        } catch (Throwable th) {
            bk.b(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(di diVar, boolean z) {
        Future<?> remove = this.c.remove(diVar);
        if (z && remove != null) {
            remove.cancel(true);
        }
    }
}

package com.amap.api.col;

import com.amap.api.col.iq;
import com.hyphenate.util.EMPrivateConstant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: ThreadPool.java */
/* loaded from: classes.dex */
public final class ip {
    private static ip a = null;
    private ExecutorService b;
    private ConcurrentHashMap<iq, Future<?>> c = new ConcurrentHashMap<>();
    private iq.a d = new iq.a() { // from class: com.amap.api.col.ip.1
        @Override // com.amap.api.col.iq.a
        public void a(iq iqVar) {
        }

        @Override // com.amap.api.col.iq.a
        public void b(iq iqVar) {
            ip.this.a(iqVar, false);
        }

        @Override // com.amap.api.col.iq.a
        public void c(iq iqVar) {
            ip.this.a(iqVar, true);
        }
    };

    public static synchronized ip a(int i) {
        ip ipVar;
        synchronized (ip.class) {
            if (a == null) {
                a = new ip(i);
            }
            ipVar = a;
        }
        return ipVar;
    }

    private ip(int i) {
        try {
            this.b = Executors.newFixedThreadPool(i);
        } catch (Throwable th) {
            gr.b(th, "TPool", "ThreadPool");
            th.printStackTrace();
        }
    }

    public void a(iq iqVar) throws fz {
        try {
            if (!b(iqVar) && this.b != null && !this.b.isShutdown()) {
                iqVar.q = this.d;
                try {
                    Future<?> submit = this.b.submit(iqVar);
                    if (submit != null) {
                        a(iqVar, submit);
                    }
                } catch (RejectedExecutionException e) {
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "TPool", "addTask");
            throw new fz("thread pool has exception");
        }
    }

    public static synchronized void a() {
        synchronized (ip.class) {
            if (a != null) {
                a.b();
                a = null;
            }
        }
    }

    private void b() {
        try {
            for (Map.Entry<iq, Future<?>> entry : this.c.entrySet()) {
                Future<?> future = this.c.get(entry.getKey());
                if (future != null) {
                    try {
                        future.cancel(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.c.clear();
            this.b.shutdown();
        } catch (Throwable th) {
            gr.b(th, "TPool", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
        }
    }

    private synchronized boolean b(iq iqVar) {
        return this.c.containsKey(iqVar);
    }

    private synchronized void a(iq iqVar, Future<?> future) {
        this.c.put(iqVar, future);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void a(iq iqVar, boolean z) {
        Future<?> remove = this.c.remove(iqVar);
        if (z && remove != null) {
            remove.cancel(true);
        }
    }
}

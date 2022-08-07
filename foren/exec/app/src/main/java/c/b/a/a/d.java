package c.b.a.a;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
class d implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicInteger f670a = new AtomicInteger(0);

    public d(e eVar) {
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(String.format("arch_disk_io_%d", Integer.valueOf(this.f670a.getAndIncrement())));
        return thread;
    }
}

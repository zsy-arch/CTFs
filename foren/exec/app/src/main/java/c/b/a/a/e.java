package c.b.a.a;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class e extends f {

    /* renamed from: a */
    public final Object f671a = new Object();

    /* renamed from: b */
    public final ExecutorService f672b = Executors.newFixedThreadPool(2, new d(this));

    /* renamed from: c */
    public volatile Handler f673c;

    @Override // c.b.a.a.f
    public void a(Runnable runnable) {
        this.f672b.execute(runnable);
    }

    @Override // c.b.a.a.f
    public void b(Runnable runnable) {
        if (this.f673c == null) {
            synchronized (this.f671a) {
                if (this.f673c == null) {
                    this.f673c = new Handler(Looper.getMainLooper());
                }
            }
        }
        this.f673c.post(runnable);
    }

    @Override // c.b.a.a.f
    public boolean a() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}

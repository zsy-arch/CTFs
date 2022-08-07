package c.e.e;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class k {

    /* renamed from: b */
    public HandlerThread f825b;

    /* renamed from: c */
    public Handler f826c;
    public final int f;
    public final int g;
    public final String h;

    /* renamed from: a */
    public final Object f824a = new Object();

    /* renamed from: e */
    public Handler.Callback f828e = new g(this);

    /* renamed from: d */
    public int f827d = 0;

    /* loaded from: classes.dex */
    public interface a<T> {
        void a(T t);
    }

    public k(String str, int i, int i2) {
        this.h = str;
        this.g = i;
        this.f = i2;
    }

    public <T> void a(Callable<T> callable, a<T> aVar) {
        b(new i(this, callable, new Handler(), aVar));
    }

    public final void b(Runnable runnable) {
        synchronized (this.f824a) {
            if (this.f825b == null) {
                this.f825b = new HandlerThread(this.h, this.g);
                this.f825b.start();
                this.f826c = new Handler(this.f825b.getLooper(), this.f828e);
                this.f827d++;
            }
            this.f826c.removeMessages(0);
            this.f826c.sendMessage(this.f826c.obtainMessage(1, runnable));
        }
    }

    public <T> T a(Callable<T> callable, int i) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition newCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        b(new j(this, atomicReference, callable, reentrantLock, atomicBoolean, newCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i);
            do {
                try {
                    nanos = newCondition.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                }
                if (!atomicBoolean.get()) {
                    return (T) atomicReference.get();
                }
            } while (nanos > 0);
            throw new InterruptedException("timeout");
        } finally {
            reentrantLock.unlock();
        }
    }

    public void a(Runnable runnable) {
        runnable.run();
        synchronized (this.f824a) {
            this.f826c.removeMessages(0);
            this.f826c.sendMessageDelayed(this.f826c.obtainMessage(0), this.f);
        }
    }

    public void a() {
        synchronized (this.f824a) {
            if (!this.f826c.hasMessages(1)) {
                this.f825b.quit();
                this.f825b = null;
                this.f826c = null;
            }
        }
    }
}

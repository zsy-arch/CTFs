package c.e.e;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AtomicReference f819a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Callable f820b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ReentrantLock f821c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ AtomicBoolean f822d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ Condition f823e;

    public j(k kVar, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
        this.f819a = atomicReference;
        this.f820b = callable;
        this.f821c = reentrantLock;
        this.f822d = atomicBoolean;
        this.f823e = condition;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.f819a.set(this.f820b.call());
        } catch (Exception unused) {
        }
        this.f821c.lock();
        try {
            this.f822d.set(false);
            this.f823e.signal();
        } finally {
            this.f821c.unlock();
        }
    }
}

package com.lidroid.xutils.task;

/* loaded from: classes2.dex */
public class PriorityRunnable extends PriorityObject<Runnable> implements Runnable {
    public PriorityRunnable(Priority priority, Runnable obj) {
        super(priority, obj);
    }

    @Override // java.lang.Runnable
    public void run() {
        ((Runnable) this.obj).run();
    }
}

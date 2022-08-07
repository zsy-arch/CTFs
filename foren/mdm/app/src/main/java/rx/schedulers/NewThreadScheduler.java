package rx.schedulers;

import rx.Scheduler;

@Deprecated
/* loaded from: classes.dex */
public final class NewThreadScheduler extends Scheduler {
    private NewThreadScheduler() {
        throw new IllegalStateException("No instances!");
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return null;
    }
}

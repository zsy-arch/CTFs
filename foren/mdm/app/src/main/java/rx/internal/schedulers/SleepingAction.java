package rx.internal.schedulers;

import rx.Scheduler;
import rx.exceptions.Exceptions;
import rx.functions.Action0;

/* loaded from: classes2.dex */
class SleepingAction implements Action0 {
    private final long execTime;
    private final Scheduler.Worker innerScheduler;
    private final Action0 underlying;

    public SleepingAction(Action0 underlying, Scheduler.Worker scheduler, long execTime) {
        this.underlying = underlying;
        this.innerScheduler = scheduler;
        this.execTime = execTime;
    }

    @Override // rx.functions.Action0
    public void call() {
        if (!this.innerScheduler.isUnsubscribed()) {
            long delay = this.execTime - this.innerScheduler.now();
            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    Exceptions.propagate(e);
                }
            }
            if (!this.innerScheduler.isUnsubscribed()) {
                this.underlying.call();
            }
        }
    }
}

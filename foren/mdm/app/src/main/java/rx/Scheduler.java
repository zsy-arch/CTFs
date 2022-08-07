package rx;

import java.util.concurrent.TimeUnit;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.SchedulePeriodicHelper;
import rx.internal.schedulers.SchedulerWhen;

/* loaded from: classes2.dex */
public abstract class Scheduler {
    public abstract Worker createWorker();

    /* loaded from: classes2.dex */
    public static abstract class Worker implements Subscription {
        public abstract Subscription schedule(Action0 action0);

        public abstract Subscription schedule(Action0 action0, long j, TimeUnit timeUnit);

        public Subscription schedulePeriodically(Action0 action, long initialDelay, long period, TimeUnit unit) {
            return SchedulePeriodicHelper.schedulePeriodically(this, action, initialDelay, period, unit, null);
        }

        public long now() {
            return System.currentTimeMillis();
        }
    }

    public long now() {
        return System.currentTimeMillis();
    }

    public <S extends Scheduler & Subscription> S when(Func1<Observable<Observable<Completable>>, Completable> combine) {
        return new SchedulerWhen(combine, this);
    }
}

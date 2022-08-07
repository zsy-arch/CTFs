package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.subscriptions.SequentialSubscription;

/* loaded from: classes2.dex */
public final class SchedulePeriodicHelper {
    public static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    /* loaded from: classes2.dex */
    public interface NowNanoSupplier {
        long nowNanos();
    }

    private SchedulePeriodicHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription schedulePeriodically(final Scheduler.Worker worker, final Action0 action, long initialDelay, long period, TimeUnit unit, final NowNanoSupplier nowNanoSupplier) {
        final long periodInNanos = unit.toNanos(period);
        final long firstNowNanos = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(worker.now());
        final long firstStartInNanos = firstNowNanos + unit.toNanos(initialDelay);
        SequentialSubscription first = new SequentialSubscription();
        final SequentialSubscription mas = new SequentialSubscription(first);
        first.replace(worker.schedule(new Action0() { // from class: rx.internal.schedulers.SchedulePeriodicHelper.1
            long count;
            long lastNowNanos;
            long startInNanos;

            {
                this.lastNowNanos = firstNowNanos;
                this.startInNanos = firstStartInNanos;
            }

            @Override // rx.functions.Action0
            public void call() {
                long nextTick;
                action.call();
                if (!mas.isUnsubscribed()) {
                    long nowNanos = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(worker.now());
                    if (SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS + nowNanos < this.lastNowNanos || nowNanos >= this.lastNowNanos + periodInNanos + SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS) {
                        nextTick = nowNanos + periodInNanos;
                        long j = periodInNanos;
                        long j2 = this.count + 1;
                        this.count = j2;
                        this.startInNanos = nextTick - (j * j2);
                    } else {
                        long j3 = this.startInNanos;
                        long j4 = this.count + 1;
                        this.count = j4;
                        nextTick = j3 + (j4 * periodInNanos);
                    }
                    this.lastNowNanos = nowNanos;
                    mas.replace(worker.schedule(this, nextTick - nowNanos, TimeUnit.NANOSECONDS));
                }
            }
        }, initialDelay, unit));
        return mas;
    }
}

package rx.schedulers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.SchedulePeriodicHelper;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
public class TestScheduler extends Scheduler {
    static long counter;
    final Queue<TimedAction> queue = new PriorityQueue(11, new CompareActionsByTime());
    long time;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class TimedAction {
        final Action0 action;
        private final long count;
        final Scheduler.Worker scheduler;
        final long time;

        TimedAction(Scheduler.Worker scheduler, long time, Action0 action) {
            long j = TestScheduler.counter;
            TestScheduler.counter = 1 + j;
            this.count = j;
            this.time = time;
            this.action = action;
            this.scheduler = scheduler;
        }

        public String toString() {
            return String.format("TimedAction(time = %d, action = %s)", Long.valueOf(this.time), this.action.toString());
        }
    }

    /* loaded from: classes2.dex */
    static final class CompareActionsByTime implements Comparator<TimedAction> {
        CompareActionsByTime() {
        }

        public int compare(TimedAction action1, TimedAction action2) {
            if (action1.time == action2.time) {
                if (action1.count < action2.count) {
                    return -1;
                }
                return action1.count > action2.count ? 1 : 0;
            } else if (action1.time >= action2.time) {
                return action1.time > action2.time ? 1 : 0;
            } else {
                return -1;
            }
        }
    }

    @Override // rx.Scheduler
    public long now() {
        return TimeUnit.NANOSECONDS.toMillis(this.time);
    }

    public void advanceTimeBy(long delayTime, TimeUnit unit) {
        advanceTimeTo(this.time + unit.toNanos(delayTime), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long delayTime, TimeUnit unit) {
        triggerActions(unit.toNanos(delayTime));
    }

    public void triggerActions() {
        triggerActions(this.time);
    }

    private void triggerActions(long targetTimeInNanos) {
        while (!this.queue.isEmpty()) {
            TimedAction current = this.queue.peek();
            if (current.time > targetTimeInNanos) {
                break;
            }
            this.time = current.time == 0 ? this.time : current.time;
            this.queue.remove();
            if (!current.scheduler.isUnsubscribed()) {
                current.action.call();
            }
        }
        this.time = targetTimeInNanos;
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new InnerTestScheduler();
    }

    /* loaded from: classes2.dex */
    final class InnerTestScheduler extends Scheduler.Worker implements SchedulePeriodicHelper.NowNanoSupplier {
        private final BooleanSubscription s = new BooleanSubscription();

        InnerTestScheduler() {
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.s.unsubscribe();
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
            final TimedAction timedAction = new TimedAction(this, TestScheduler.this.time + unit.toNanos(delayTime), action);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new Action0() { // from class: rx.schedulers.TestScheduler.InnerTestScheduler.1
                @Override // rx.functions.Action0
                public void call() {
                    TestScheduler.this.queue.remove(timedAction);
                }
            });
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action) {
            final TimedAction timedAction = new TimedAction(this, 0L, action);
            TestScheduler.this.queue.add(timedAction);
            return Subscriptions.create(new Action0() { // from class: rx.schedulers.TestScheduler.InnerTestScheduler.2
                @Override // rx.functions.Action0
                public void call() {
                    TestScheduler.this.queue.remove(timedAction);
                }
            });
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedulePeriodically(Action0 action, long initialDelay, long period, TimeUnit unit) {
            return SchedulePeriodicHelper.schedulePeriodically(this, action, initialDelay, period, unit, this);
        }

        @Override // rx.Scheduler.Worker
        public long now() {
            return TestScheduler.this.now();
        }

        @Override // rx.internal.schedulers.SchedulePeriodicHelper.NowNanoSupplier
        public long nowNanos() {
            return TestScheduler.this.time;
        }
    }
}

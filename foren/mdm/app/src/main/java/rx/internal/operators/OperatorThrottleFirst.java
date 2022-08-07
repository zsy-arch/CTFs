package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

/* loaded from: classes2.dex */
public final class OperatorThrottleFirst<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long timeInMilliseconds;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorThrottleFirst(long windowDuration, TimeUnit unit, Scheduler scheduler) {
        this.timeInMilliseconds = unit.toMillis(windowDuration);
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorThrottleFirst.1
            private long lastOnNext = -1;

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void onStart() {
                request(Long.MAX_VALUE);
            }

            @Override // rx.Observer
            public void onNext(T v) {
                long now = OperatorThrottleFirst.this.scheduler.now();
                if (this.lastOnNext == -1 || now < this.lastOnNext || now - this.lastOnNext >= OperatorThrottleFirst.this.timeInMilliseconds) {
                    this.lastOnNext = now;
                    subscriber.onNext(v);
                }
            }

            @Override // rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                subscriber.onError(e);
            }
        };
    }
}

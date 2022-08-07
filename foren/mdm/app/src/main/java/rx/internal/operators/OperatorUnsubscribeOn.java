package rx.internal.operators;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
public class OperatorUnsubscribeOn<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorUnsubscribeOn(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorUnsubscribeOn.1
            @Override // rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                subscriber.onError(e);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                subscriber.onNext(t);
            }
        };
        subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorUnsubscribeOn.2
            @Override // rx.functions.Action0
            public void call() {
                final Scheduler.Worker inner = OperatorUnsubscribeOn.this.scheduler.createWorker();
                inner.schedule(new Action0() { // from class: rx.internal.operators.OperatorUnsubscribeOn.2.1
                    @Override // rx.functions.Action0
                    public void call() {
                        subscriber2.unsubscribe();
                        inner.unsubscribe();
                    }
                });
            }
        }));
        return subscriber2;
    }
}

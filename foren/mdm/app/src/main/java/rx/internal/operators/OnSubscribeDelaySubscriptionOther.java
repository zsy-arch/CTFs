package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.observers.Subscribers;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
public final class OnSubscribeDelaySubscriptionOther<T, U> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> main;
    final Observable<U> other;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeDelaySubscriptionOther(Observable<? extends T> main, Observable<U> other) {
        this.main = main;
        this.other = other;
    }

    public void call(Subscriber<? super T> t) {
        final SerialSubscription serial = new SerialSubscription();
        t.add(serial);
        final Subscriber<T> child = Subscribers.wrap(t);
        Subscriber<U> otherSubscriber = new Subscriber<U>() { // from class: rx.internal.operators.OnSubscribeDelaySubscriptionOther.1
            boolean done;

            @Override // rx.Observer
            public void onNext(U t2) {
                onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                if (this.done) {
                    RxJavaHooks.onError(e);
                    return;
                }
                this.done = true;
                child.onError(e);
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (!this.done) {
                    this.done = true;
                    serial.set(Subscriptions.unsubscribed());
                    OnSubscribeDelaySubscriptionOther.this.main.unsafeSubscribe(child);
                }
            }
        };
        serial.set(otherSubscriber);
        this.other.unsafeSubscribe(otherSubscriber);
    }
}

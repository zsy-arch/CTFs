package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.observers.Subscribers;

/* loaded from: classes2.dex */
public final class OnSubscribeDelaySubscriptionWithSelector<T, U> implements Observable.OnSubscribe<T> {
    final Observable<? extends T> source;
    final Func0<? extends Observable<U>> subscriptionDelay;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeDelaySubscriptionWithSelector(Observable<? extends T> source, Func0<? extends Observable<U>> subscriptionDelay) {
        this.source = source;
        this.subscriptionDelay = subscriptionDelay;
    }

    public void call(final Subscriber<? super T> child) {
        try {
            ((Observable) this.subscriptionDelay.call()).take(1).unsafeSubscribe((Subscriber<U>) new Subscriber<U>() { // from class: rx.internal.operators.OnSubscribeDelaySubscriptionWithSelector.1
                @Override // rx.Observer
                public void onCompleted() {
                    OnSubscribeDelaySubscriptionWithSelector.this.source.unsafeSubscribe(Subscribers.wrap(child));
                }

                @Override // rx.Observer
                public void onError(Throwable e) {
                    child.onError(e);
                }

                @Override // rx.Observer
                public void onNext(U t) {
                }
            });
        } catch (Throwable e) {
            Exceptions.throwOrReport(e, child);
        }
    }
}

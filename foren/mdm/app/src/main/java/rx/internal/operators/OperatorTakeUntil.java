package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;

/* loaded from: classes2.dex */
public final class OperatorTakeUntil<T, E> implements Observable.Operator<T, T> {
    private final Observable<? extends E> other;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorTakeUntil(Observable<? extends E> other) {
        this.other = other;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        final Subscriber<T> serial = new SerializedSubscriber<>(child, false);
        final Subscriber subscriber = (Subscriber<T>) new Subscriber<T>(serial, false) { // from class: rx.internal.operators.OperatorTakeUntil.1
            @Override // rx.Observer
            public void onNext(T t) {
                serial.onNext(t);
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                try {
                    serial.onError(e);
                } finally {
                    serial.unsubscribe();
                }
            }

            @Override // rx.Observer
            public void onCompleted() {
                try {
                    serial.onCompleted();
                } finally {
                    serial.unsubscribe();
                }
            }
        };
        Subscriber<E> so = new Subscriber<E>() { // from class: rx.internal.operators.OperatorTakeUntil.2
            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void onStart() {
                request(Long.MAX_VALUE);
            }

            @Override // rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                subscriber.onError(e);
            }

            @Override // rx.Observer
            public void onNext(E t) {
                onCompleted();
            }
        };
        serial.add(subscriber);
        serial.add(so);
        child.add(serial);
        this.other.unsafeSubscribe(so);
        return subscriber;
    }
}

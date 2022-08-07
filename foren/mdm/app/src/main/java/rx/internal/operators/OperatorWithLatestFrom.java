package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func2;
import rx.observers.SerializedSubscriber;

/* loaded from: classes2.dex */
public final class OperatorWithLatestFrom<T, U, R> implements Observable.Operator<R, T> {
    static final Object EMPTY = new Object();
    final Observable<? extends U> other;
    final Func2<? super T, ? super U, ? extends R> resultSelector;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorWithLatestFrom(Observable<? extends U> other, Func2<? super T, ? super U, ? extends R> resultSelector) {
        this.other = other;
        this.resultSelector = resultSelector;
    }

    public Subscriber<? super T> call(Subscriber<? super R> child) {
        final SerializedSubscriber<R> s = new SerializedSubscriber<>(child, false);
        child.add(s);
        final AtomicReference<Object> current = new AtomicReference<>(EMPTY);
        Subscriber subscriber = (Subscriber<T>) new Subscriber<T>(s, true) { // from class: rx.internal.operators.OperatorWithLatestFrom.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.Observer
            public void onNext(T t) {
                Object o = current.get();
                if (o != OperatorWithLatestFrom.EMPTY) {
                    try {
                        s.onNext(OperatorWithLatestFrom.this.resultSelector.call(t, o));
                    } catch (Throwable e) {
                        Exceptions.throwOrReport(e, this);
                    }
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                s.onError(e);
                s.unsubscribe();
            }

            @Override // rx.Observer
            public void onCompleted() {
                s.onCompleted();
                s.unsubscribe();
            }
        };
        Subscriber<U> otherSubscriber = new Subscriber<U>() { // from class: rx.internal.operators.OperatorWithLatestFrom.2
            @Override // rx.Observer
            public void onNext(U t) {
                current.set(t);
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                s.onError(e);
                s.unsubscribe();
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (current.get() == OperatorWithLatestFrom.EMPTY) {
                    s.onCompleted();
                    s.unsubscribe();
                }
            }
        };
        s.add(subscriber);
        s.add(otherSubscriber);
        this.other.unsafeSubscribe(otherSubscriber);
        return subscriber;
    }
}

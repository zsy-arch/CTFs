package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;

/* loaded from: classes2.dex */
public class OperatorIgnoreElements<T> implements Observable.Operator<T, T> {
    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Holder {
        static final OperatorIgnoreElements<?> INSTANCE = new OperatorIgnoreElements<>();

        Holder() {
        }
    }

    public static <T> OperatorIgnoreElements<T> instance() {
        return (OperatorIgnoreElements<T>) Holder.INSTANCE;
    }

    OperatorIgnoreElements() {
    }

    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        Subscriber subscriber = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorIgnoreElements.1
            @Override // rx.Observer
            public void onCompleted() {
                child.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                child.onError(e);
            }

            @Override // rx.Observer
            public void onNext(T t) {
            }
        };
        child.add(subscriber);
        return subscriber;
    }
}

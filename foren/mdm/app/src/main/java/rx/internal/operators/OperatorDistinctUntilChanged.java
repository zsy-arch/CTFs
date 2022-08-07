package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.util.UtilityFunctions;

/* loaded from: classes2.dex */
public final class OperatorDistinctUntilChanged<T, U> implements Observable.Operator<T, T>, Func2<U, U, Boolean> {
    final Func2<? super U, ? super U, Boolean> comparator;
    final Func1<? super T, ? extends U> keySelector;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Holder {
        static final OperatorDistinctUntilChanged<?, ?> INSTANCE = new OperatorDistinctUntilChanged<>(UtilityFunctions.identity());

        Holder() {
        }
    }

    public static <T> OperatorDistinctUntilChanged<T, T> instance() {
        return (OperatorDistinctUntilChanged<T, T>) Holder.INSTANCE;
    }

    public OperatorDistinctUntilChanged(Func1<? super T, ? extends U> keySelector) {
        this.keySelector = keySelector;
        this.comparator = this;
    }

    public OperatorDistinctUntilChanged(Func2<? super U, ? super U, Boolean> comparator) {
        this.keySelector = UtilityFunctions.identity();
        this.comparator = comparator;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // rx.functions.Func2
    public Boolean call(U t1, U t2) {
        return Boolean.valueOf(t1 == t2 || (t1 != null && t1.equals(t2)));
    }

    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        return (Subscriber<T>) new Subscriber<T>(child) { // from class: rx.internal.operators.OperatorDistinctUntilChanged.1
            boolean hasPrevious;
            U previousKey;

            @Override // rx.Observer
            public void onNext(T t) {
                try {
                    U key = (U) OperatorDistinctUntilChanged.this.keySelector.call(t);
                    U currentKey = this.previousKey;
                    this.previousKey = key;
                    if (this.hasPrevious) {
                        try {
                            if (!OperatorDistinctUntilChanged.this.comparator.call(currentKey, key).booleanValue()) {
                                child.onNext(t);
                            } else {
                                request(1L);
                            }
                        } catch (Throwable e) {
                            Exceptions.throwOrReport(e, child, key);
                        }
                    } else {
                        this.hasPrevious = true;
                        child.onNext(t);
                    }
                } catch (Throwable e2) {
                    Exceptions.throwOrReport(e2, child, t);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                child.onError(e);
            }

            @Override // rx.Observer
            public void onCompleted() {
                child.onCompleted();
            }
        };
    }
}

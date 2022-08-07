package rx.internal.operators;

import java.util.HashSet;
import java.util.Set;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;

/* loaded from: classes2.dex */
public final class OperatorDistinct<T, U> implements Observable.Operator<T, T> {
    final Func1<? super T, ? extends U> keySelector;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Holder {
        static final OperatorDistinct<?, ?> INSTANCE = new OperatorDistinct<>(UtilityFunctions.identity());

        Holder() {
        }
    }

    public static <T> OperatorDistinct<T, T> instance() {
        return (OperatorDistinct<T, T>) Holder.INSTANCE;
    }

    public OperatorDistinct(Func1<? super T, ? extends U> keySelector) {
        this.keySelector = keySelector;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        return (Subscriber<T>) new Subscriber<T>(child) { // from class: rx.internal.operators.OperatorDistinct.1
            Set<U> keyMemory = new HashSet();

            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.Observer
            public void onNext(T t) {
                if (this.keyMemory.add(OperatorDistinct.this.keySelector.call(t))) {
                    child.onNext(t);
                } else {
                    request(1L);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                this.keyMemory = null;
                child.onError(e);
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.keyMemory = null;
                child.onCompleted();
            }
        };
    }
}

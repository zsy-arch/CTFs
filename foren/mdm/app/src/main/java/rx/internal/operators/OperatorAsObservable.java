package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;

/* loaded from: classes2.dex */
public final class OperatorAsObservable<T> implements Observable.Operator<T, T> {
    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Holder {
        static final OperatorAsObservable<Object> INSTANCE = new OperatorAsObservable<>();

        Holder() {
        }
    }

    public static <T> OperatorAsObservable<T> instance() {
        return (OperatorAsObservable<T>) Holder.INSTANCE;
    }

    OperatorAsObservable() {
    }

    public Subscriber<? super T> call(Subscriber<? super T> s) {
        return s;
    }
}

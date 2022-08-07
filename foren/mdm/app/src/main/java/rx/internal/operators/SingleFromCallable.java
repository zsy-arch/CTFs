package rx.internal.operators;

import java.util.concurrent.Callable;
import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;

/* loaded from: classes2.dex */
public final class SingleFromCallable<T> implements Single.OnSubscribe<T> {
    final Callable<? extends T> callable;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((SingleSubscriber) ((SingleSubscriber) x0));
    }

    public SingleFromCallable(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public void call(SingleSubscriber<? super T> t) {
        try {
            t.onSuccess((Object) this.callable.call());
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            t.onError(ex);
        }
    }
}

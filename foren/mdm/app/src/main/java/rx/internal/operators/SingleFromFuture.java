package rx.internal.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
public final class SingleFromFuture<T> implements Single.OnSubscribe<T> {
    final Future<? extends T> future;
    final long timeout;
    final TimeUnit unit;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((SingleSubscriber) ((SingleSubscriber) x0));
    }

    public SingleFromFuture(Future<? extends T> future, long timeout, TimeUnit unit) {
        this.future = future;
        this.timeout = timeout;
        this.unit = unit;
    }

    public void call(SingleSubscriber<? super T> t) {
        Object obj;
        Future<? extends T> f = this.future;
        t.add(Subscriptions.from(f));
        try {
            if (this.timeout == 0) {
                obj = (Object) f.get();
            } else {
                obj = (Object) f.get(this.timeout, this.unit);
            }
            t.onSuccess(obj);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            t.onError(ex);
        }
    }
}

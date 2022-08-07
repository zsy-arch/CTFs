package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* loaded from: classes2.dex */
public final class SingleOnErrorReturn<T> implements Single.OnSubscribe<T> {
    final Func1<Throwable, ? extends T> resumeFunction;
    final Single.OnSubscribe<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((SingleSubscriber) ((SingleSubscriber) x0));
    }

    public SingleOnErrorReturn(Single.OnSubscribe<T> source, Func1<Throwable, ? extends T> resumeFunction) {
        this.source = source;
        this.resumeFunction = resumeFunction;
    }

    public void call(SingleSubscriber<? super T> t) {
        OnErrorReturnsSingleSubscriber onErrorReturnsSingleSubscriber = new OnErrorReturnsSingleSubscriber(t, this.resumeFunction);
        t.add(onErrorReturnsSingleSubscriber);
        this.source.call(onErrorReturnsSingleSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class OnErrorReturnsSingleSubscriber<T> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final Func1<Throwable, ? extends T> resumeFunction;

        public OnErrorReturnsSingleSubscriber(SingleSubscriber<? super T> actual, Func1<Throwable, ? extends T> resumeFunction) {
            this.actual = actual;
            this.resumeFunction = resumeFunction;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) {
            this.actual.onSuccess(value);
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            try {
                this.actual.onSuccess(this.resumeFunction.call(error));
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.actual.onError(ex);
            }
        }
    }
}

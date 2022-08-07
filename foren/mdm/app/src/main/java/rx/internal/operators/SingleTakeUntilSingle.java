package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Single;
import rx.SingleSubscriber;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class SingleTakeUntilSingle<T, U> implements Single.OnSubscribe<T> {
    final Single<? extends U> other;
    final Single.OnSubscribe<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((SingleSubscriber) ((SingleSubscriber) x0));
    }

    public SingleTakeUntilSingle(Single.OnSubscribe<T> source, Single<? extends U> other) {
        this.source = source;
        this.other = other;
    }

    public void call(SingleSubscriber<? super T> t) {
        TakeUntilSourceSubscriber takeUntilSourceSubscriber = new TakeUntilSourceSubscriber(t);
        t.add(takeUntilSourceSubscriber);
        this.other.subscribe((SingleSubscriber<? super Object>) takeUntilSourceSubscriber.other);
        this.source.call(takeUntilSourceSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class TakeUntilSourceSubscriber<T, U> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final AtomicBoolean once = new AtomicBoolean();
        final SingleSubscriber<U> other = new OtherSubscriber();

        TakeUntilSourceSubscriber(SingleSubscriber<? super T> actual) {
            this.actual = actual;
            add(this.other);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) {
            if (this.once.compareAndSet(false, true)) {
                unsubscribe();
                this.actual.onSuccess(value);
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            if (this.once.compareAndSet(false, true)) {
                unsubscribe();
                this.actual.onError(error);
                return;
            }
            RxJavaHooks.onError(error);
        }

        /* loaded from: classes2.dex */
        final class OtherSubscriber extends SingleSubscriber<U> {
            OtherSubscriber() {
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(U value) {
                onError(new CancellationException("Single::takeUntil(Single) - Stream was canceled before emitting a terminal event."));
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable error) {
                TakeUntilSourceSubscriber.this.onError(error);
            }
        }
    }
}

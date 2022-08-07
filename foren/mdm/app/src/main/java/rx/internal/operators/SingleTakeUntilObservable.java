package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class SingleTakeUntilObservable<T, U> implements Single.OnSubscribe<T> {
    final Observable<? extends U> other;
    final Single.OnSubscribe<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((SingleSubscriber) ((SingleSubscriber) x0));
    }

    public SingleTakeUntilObservable(Single.OnSubscribe<T> source, Observable<? extends U> other) {
        this.source = source;
        this.other = other;
    }

    public void call(SingleSubscriber<? super T> t) {
        TakeUntilSourceSubscriber takeUntilSourceSubscriber = new TakeUntilSourceSubscriber(t);
        t.add(takeUntilSourceSubscriber);
        this.other.subscribe((Subscriber<? super Object>) takeUntilSourceSubscriber.other);
        this.source.call(takeUntilSourceSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class TakeUntilSourceSubscriber<T, U> extends SingleSubscriber<T> {
        final SingleSubscriber<? super T> actual;
        final AtomicBoolean once = new AtomicBoolean();
        final Subscriber<U> other = new OtherSubscriber();

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
        final class OtherSubscriber extends Subscriber<U> {
            OtherSubscriber() {
            }

            @Override // rx.Observer
            public void onNext(U value) {
                onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable error) {
                TakeUntilSourceSubscriber.this.onError(error);
            }

            @Override // rx.Observer
            public void onCompleted() {
                onError(new CancellationException("Single::takeUntil(Observable) - Stream was canceled before emitting a terminal event."));
            }
        }
    }
}

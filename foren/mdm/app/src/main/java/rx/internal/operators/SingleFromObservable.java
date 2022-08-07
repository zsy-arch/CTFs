package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class SingleFromObservable<T> implements Single.OnSubscribe<T> {
    final Observable.OnSubscribe<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((SingleSubscriber) ((SingleSubscriber) x0));
    }

    public SingleFromObservable(Observable.OnSubscribe<T> source) {
        this.source = source;
    }

    public void call(SingleSubscriber<? super T> t) {
        WrapSingleIntoSubscriber wrapSingleIntoSubscriber = new WrapSingleIntoSubscriber(t);
        t.add(wrapSingleIntoSubscriber);
        this.source.call(wrapSingleIntoSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class WrapSingleIntoSubscriber<T> extends Subscriber<T> {
        static final int STATE_DONE = 2;
        static final int STATE_EMPTY = 0;
        static final int STATE_HAS_VALUE = 1;
        final SingleSubscriber<? super T> actual;
        int state;
        T value;

        /* JADX INFO: Access modifiers changed from: package-private */
        public WrapSingleIntoSubscriber(SingleSubscriber<? super T> actual) {
            this.actual = actual;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            int s = this.state;
            if (s == 0) {
                this.state = 1;
                this.value = t;
            } else if (s == 1) {
                this.state = 2;
                this.actual.onError(new IndexOutOfBoundsException("The upstream produced more than one value"));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (this.state == 2) {
                RxJavaHooks.onError(e);
                return;
            }
            this.value = null;
            this.actual.onError(e);
        }

        @Override // rx.Observer
        public void onCompleted() {
            int s = this.state;
            if (s == 0) {
                this.actual.onError(new NoSuchElementException());
            } else if (s == 1) {
                this.state = 2;
                T v = this.value;
                this.value = null;
                this.actual.onSuccess(v);
            }
        }
    }
}

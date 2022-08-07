package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func2;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class OnSubscribeReduce<T> implements Observable.OnSubscribe<T> {
    final Func2<T, T, T> reducer;
    final Observable<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeReduce(Observable<T> source, Func2<T, T, T> reducer) {
        this.source = source;
        this.reducer = reducer;
    }

    public void call(Subscriber<? super T> t) {
        final ReduceSubscriber<T> parent = new ReduceSubscriber<>(t, this.reducer);
        t.add(parent);
        t.setProducer(new Producer() { // from class: rx.internal.operators.OnSubscribeReduce.1
            @Override // rx.Producer
            public void request(long n) {
                parent.downstreamRequest(n);
            }
        });
        this.source.unsafeSubscribe(parent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class ReduceSubscriber<T> extends Subscriber<T> {
        static final Object EMPTY = new Object();
        final Subscriber<? super T> actual;
        boolean done;
        final Func2<T, T, T> reducer;
        T value = (T) EMPTY;

        public ReduceSubscriber(Subscriber<? super T> actual, Func2<T, T, T> reducer) {
            this.actual = actual;
            this.reducer = reducer;
            request(0L);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.done) {
                T t2 = this.value;
                if (t2 == EMPTY) {
                    this.value = t;
                    return;
                }
                try {
                    this.value = this.reducer.call(t2, t);
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    unsubscribe();
                    onError(ex);
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (!this.done) {
                this.done = true;
                this.actual.onError(e);
                return;
            }
            RxJavaHooks.onError(e);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                Object o = this.value;
                if (o != EMPTY) {
                    this.actual.onNext(o);
                    this.actual.onCompleted();
                    return;
                }
                this.actual.onError(new NoSuchElementException());
            }
        }

        void downstreamRequest(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            } else if (n != 0) {
                request(Long.MAX_VALUE);
            }
        }
    }
}

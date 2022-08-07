package rx.internal.operators;

import java.util.Arrays;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public class OnSubscribeDoOnEach<T> implements Observable.OnSubscribe<T> {
    private final Observer<? super T> doOnEachObserver;
    private final Observable<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeDoOnEach(Observable<T> source, Observer<? super T> doOnEachObserver) {
        this.source = source;
        this.doOnEachObserver = doOnEachObserver;
    }

    public void call(Subscriber<? super T> subscriber) {
        this.source.unsafeSubscribe(new DoOnEachSubscriber(subscriber, this.doOnEachObserver));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class DoOnEachSubscriber<T> extends Subscriber<T> {
        private final Observer<? super T> doOnEachObserver;
        private boolean done;
        private final Subscriber<? super T> subscriber;

        DoOnEachSubscriber(Subscriber<? super T> subscriber, Observer<? super T> doOnEachObserver) {
            super(subscriber);
            this.subscriber = subscriber;
            this.doOnEachObserver = doOnEachObserver;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.done) {
                try {
                    this.doOnEachObserver.onCompleted();
                    this.done = true;
                    this.subscriber.onCompleted();
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, this);
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
                return;
            }
            this.done = true;
            try {
                this.doOnEachObserver.onError(e);
                this.subscriber.onError(e);
            } catch (Throwable e2) {
                Exceptions.throwIfFatal(e2);
                this.subscriber.onError(new CompositeException(Arrays.asList(e, e2)));
            }
        }

        @Override // rx.Observer
        public void onNext(T value) {
            if (!this.done) {
                try {
                    this.doOnEachObserver.onNext(value);
                    this.subscriber.onNext(value);
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, this, value);
                }
            }
        }
    }
}

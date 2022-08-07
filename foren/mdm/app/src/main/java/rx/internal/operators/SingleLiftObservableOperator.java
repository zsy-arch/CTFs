package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.SingleFromObservable;
import rx.internal.producers.SingleProducer;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class SingleLiftObservableOperator<T, R> implements Single.OnSubscribe<R> {
    final Observable.Operator<? extends R, ? super T> lift;
    final Single.OnSubscribe<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((SingleSubscriber) ((SingleSubscriber) x0));
    }

    public SingleLiftObservableOperator(Single.OnSubscribe<T> source, Observable.Operator<? extends R, ? super T> lift) {
        this.source = source;
        this.lift = lift;
    }

    public void call(SingleSubscriber<? super R> t) {
        SingleFromObservable.WrapSingleIntoSubscriber wrapSingleIntoSubscriber = new SingleFromObservable.WrapSingleIntoSubscriber(t);
        t.add(wrapSingleIntoSubscriber);
        try {
            Subscriber<? super T> inputAsSubscriber = RxJavaHooks.onSingleLift(this.lift).call(wrapSingleIntoSubscriber);
            SingleSubscriber wrap = wrap(inputAsSubscriber);
            inputAsSubscriber.onStart();
            this.source.call(wrap);
        } catch (Throwable ex) {
            Exceptions.throwOrReport(ex, t);
        }
    }

    public static <T> SingleSubscriber<T> wrap(Subscriber<T> subscriber) {
        WrapSubscriberIntoSingle<T> parent = new WrapSubscriberIntoSingle<>(subscriber);
        subscriber.add(parent);
        return parent;
    }

    /* loaded from: classes2.dex */
    public static final class WrapSubscriberIntoSingle<T> extends SingleSubscriber<T> {
        final Subscriber<? super T> actual;

        public WrapSubscriberIntoSingle(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T value) {
            this.actual.setProducer(new SingleProducer(this.actual, value));
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable error) {
            this.actual.onError(error);
        }
    }
}

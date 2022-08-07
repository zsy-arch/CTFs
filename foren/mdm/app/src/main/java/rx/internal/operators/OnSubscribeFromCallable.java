package rx.internal.operators;

import java.util.concurrent.Callable;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.producers.SingleDelayedProducer;

/* loaded from: classes2.dex */
public final class OnSubscribeFromCallable<T> implements Observable.OnSubscribe<T> {
    private final Callable<? extends T> resultFactory;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeFromCallable(Callable<? extends T> resultFactory) {
        this.resultFactory = resultFactory;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void call(Subscriber<? super T> subscriber) {
        SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        subscriber.setProducer(singleDelayedProducer);
        try {
            singleDelayedProducer.setValue(this.resultFactory.call());
        } catch (Throwable t) {
            Exceptions.throwOrReport(t, subscriber);
        }
    }
}

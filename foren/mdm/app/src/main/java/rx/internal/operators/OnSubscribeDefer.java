package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.observers.Subscribers;

/* loaded from: classes2.dex */
public final class OnSubscribeDefer<T> implements Observable.OnSubscribe<T> {
    final Func0<? extends Observable<? extends T>> observableFactory;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeDefer(Func0<? extends Observable<? extends T>> observableFactory) {
        this.observableFactory = observableFactory;
    }

    public void call(Subscriber<? super T> s) {
        try {
            ((Observable) this.observableFactory.call()).unsafeSubscribe(Subscribers.wrap(s));
        } catch (Throwable t) {
            Exceptions.throwOrReport(t, s);
        }
    }
}

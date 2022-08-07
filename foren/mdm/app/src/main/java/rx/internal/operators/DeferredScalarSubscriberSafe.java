package rx.internal.operators;

import rx.Subscriber;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public abstract class DeferredScalarSubscriberSafe<T, R> extends DeferredScalarSubscriber<T, R> {
    protected boolean done;

    public DeferredScalarSubscriberSafe(Subscriber<? super R> actual) {
        super(actual);
    }

    @Override // rx.internal.operators.DeferredScalarSubscriber, rx.Observer
    public void onError(Throwable ex) {
        if (!this.done) {
            this.done = true;
            super.onError(ex);
            return;
        }
        RxJavaHooks.onError(ex);
    }

    @Override // rx.internal.operators.DeferredScalarSubscriber, rx.Observer
    public void onCompleted() {
        if (!this.done) {
            this.done = true;
            super.onCompleted();
        }
    }
}

package rx.observers;

import java.util.Arrays;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.exceptions.UnsubscribeFailedException;
import rx.plugins.RxJavaHooks;
import rx.plugins.RxJavaPlugins;

/* loaded from: classes2.dex */
public class SafeSubscriber<T> extends Subscriber<T> {
    private final Subscriber<? super T> actual;
    boolean done;

    public SafeSubscriber(Subscriber<? super T> actual) {
        super(actual);
        this.actual = actual;
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (!this.done) {
            this.done = true;
            try {
                this.actual.onCompleted();
                try {
                    unsubscribe();
                } catch (Throwable e) {
                    RxJavaHooks.onError(e);
                    throw new UnsubscribeFailedException(e.getMessage(), e);
                }
            } catch (Throwable th) {
                try {
                    unsubscribe();
                    throw th;
                } catch (Throwable e2) {
                    RxJavaHooks.onError(e2);
                    throw new UnsubscribeFailedException(e2.getMessage(), e2);
                }
            }
        }
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        Exceptions.throwIfFatal(e);
        if (!this.done) {
            this.done = true;
            _onError(e);
        }
    }

    @Override // rx.Observer
    public void onNext(T t) {
        try {
            if (!this.done) {
                this.actual.onNext(t);
            }
        } catch (Throwable e) {
            Exceptions.throwOrReport(e, this);
        }
    }

    protected void _onError(Throwable e) {
        RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        try {
            this.actual.onError(e);
            try {
                unsubscribe();
            } catch (Throwable unsubscribeException) {
                RxJavaHooks.onError(unsubscribeException);
                throw new OnErrorFailedException(unsubscribeException);
            }
        } catch (OnErrorNotImplementedException e2) {
            try {
                unsubscribe();
                throw e2;
            } catch (Throwable unsubscribeException2) {
                RxJavaHooks.onError(unsubscribeException2);
                throw new OnErrorNotImplementedException("Observer.onError not implemented and error while unsubscribing.", new CompositeException(Arrays.asList(e, unsubscribeException2)));
            }
        } catch (Throwable e22) {
            RxJavaHooks.onError(e22);
            try {
                unsubscribe();
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError", new CompositeException(Arrays.asList(e, e22)));
            } catch (Throwable unsubscribeException3) {
                RxJavaHooks.onError(unsubscribeException3);
                throw new OnErrorFailedException("Error occurred when trying to propagate error to Observer.onError and during unsubscription.", new CompositeException(Arrays.asList(e, e22, unsubscribeException3)));
            }
        }
    }

    public Subscriber<? super T> getActual() {
        return this.actual;
    }
}

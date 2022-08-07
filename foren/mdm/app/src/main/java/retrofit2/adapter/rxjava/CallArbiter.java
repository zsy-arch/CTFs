package retrofit2.adapter.rxjava;

import java.util.concurrent.atomic.AtomicInteger;
import retrofit2.Call;
import retrofit2.Response;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaPlugins;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class CallArbiter<T> extends AtomicInteger implements Subscription, Producer {
    private static final int STATE_HAS_RESPONSE = 2;
    private static final int STATE_REQUESTED = 1;
    private static final int STATE_TERMINATED = 3;
    private static final int STATE_WAITING = 0;
    private final Call<T> call;
    private volatile Response<T> response;
    private final Subscriber<? super Response<T>> subscriber;

    public CallArbiter(Call<T> call, Subscriber<? super Response<T>> subscriber) {
        super(0);
        this.call = call;
        this.subscriber = subscriber;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.call.cancel();
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.call.isCanceled();
    }

    @Override // rx.Producer
    public void request(long amount) {
        if (amount != 0) {
            while (true) {
                int state = get();
                switch (state) {
                    case 0:
                        if (!compareAndSet(0, 1)) {
                            break;
                        } else {
                            return;
                        }
                    case 1:
                    case 3:
                        return;
                    case 2:
                        if (!compareAndSet(2, 3)) {
                            break;
                        } else {
                            deliverResponse(this.response);
                            return;
                        }
                    default:
                        throw new IllegalStateException("Unknown state: " + state);
                }
            }
        }
    }

    public void emitResponse(Response<T> response) {
        while (true) {
            int state = get();
            switch (state) {
                case 0:
                    this.response = response;
                    if (!compareAndSet(0, 2)) {
                        break;
                    } else {
                        return;
                    }
                case 1:
                    if (!compareAndSet(1, 3)) {
                        break;
                    } else {
                        deliverResponse(response);
                        return;
                    }
                case 2:
                case 3:
                    throw new AssertionError();
                default:
                    throw new IllegalStateException("Unknown state: " + state);
            }
        }
    }

    private void deliverResponse(Response<T> response) {
        try {
            if (!isUnsubscribed()) {
                this.subscriber.onNext(response);
            }
            try {
                if (!isUnsubscribed()) {
                    this.subscriber.onCompleted();
                }
            } catch (OnCompletedFailedException e) {
                e = e;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorFailedException e2) {
                e = e2;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorNotImplementedException e3) {
                e = e3;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (Throwable t) {
                Exceptions.throwIfFatal(t);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(t);
            }
        } catch (OnCompletedFailedException e4) {
            e = e4;
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (OnErrorFailedException e5) {
            e = e5;
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (OnErrorNotImplementedException e6) {
            e = e6;
            RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
        } catch (Throwable t2) {
            Exceptions.throwIfFatal(t2);
            try {
                this.subscriber.onError(t2);
            } catch (OnCompletedFailedException e7) {
                e = e7;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorFailedException e8) {
                e = e8;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorNotImplementedException e9) {
                e = e9;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (Throwable inner) {
                Exceptions.throwIfFatal(inner);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(t2, inner));
            }
        }
    }

    public void emitError(Throwable t) {
        set(3);
        if (!isUnsubscribed()) {
            try {
                this.subscriber.onError(t);
            } catch (OnCompletedFailedException e) {
                e = e;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorFailedException e2) {
                e = e2;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (OnErrorNotImplementedException e3) {
                e = e3;
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
            } catch (Throwable inner) {
                Exceptions.throwIfFatal(inner);
                RxJavaPlugins.getInstance().getErrorHandler().handleError(new CompositeException(t, inner));
            }
        }
    }
}

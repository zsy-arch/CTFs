package retrofit2.adapter.rxjava;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.plugins.RxJavaPlugins;

/* loaded from: classes2.dex */
final class ResultOnSubscribe<T> implements Observable.OnSubscribe<Result<T>> {
    private final Observable.OnSubscribe<Response<T>> upstream;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResultOnSubscribe(Observable.OnSubscribe<Response<T>> upstream) {
        this.upstream = upstream;
    }

    public void call(Subscriber<? super Result<T>> subscriber) {
        this.upstream.call(new ResultSubscriber(subscriber));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ResultSubscriber<R> extends Subscriber<Response<R>> {
        private final Subscriber<? super Result<R>> subscriber;

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Response) ((Response) obj));
        }

        ResultSubscriber(Subscriber<? super Result<R>> subscriber) {
            super(subscriber);
            this.subscriber = subscriber;
        }

        public void onNext(Response<R> response) {
            this.subscriber.onNext(Result.response(response));
        }

        @Override // rx.Observer
        public void onError(Throwable throwable) {
            try {
                this.subscriber.onNext(Result.error(throwable));
                this.subscriber.onCompleted();
            } catch (Throwable t) {
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

        @Override // rx.Observer
        public void onCompleted() {
            this.subscriber.onCompleted();
        }
    }
}

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
final class BodyOnSubscribe<T> implements Observable.OnSubscribe<T> {
    private final Observable.OnSubscribe<Response<T>> upstream;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BodyOnSubscribe(Observable.OnSubscribe<Response<T>> upstream) {
        this.upstream = upstream;
    }

    public void call(Subscriber<? super T> subscriber) {
        this.upstream.call(new BodySubscriber(subscriber));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class BodySubscriber<R> extends Subscriber<Response<R>> {
        private final Subscriber<? super R> subscriber;
        private boolean subscriberTerminated;

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Response) ((Response) obj));
        }

        BodySubscriber(Subscriber<? super R> subscriber) {
            super(subscriber);
            this.subscriber = subscriber;
        }

        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.subscriber.onNext(response.body());
                return;
            }
            this.subscriberTerminated = true;
            Throwable t = new HttpException(response);
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

        @Override // rx.Observer
        public void onError(Throwable throwable) {
            if (!this.subscriberTerminated) {
                this.subscriber.onError(throwable);
                return;
            }
            Throwable broken = new AssertionError("This should never happen! Report as a Retrofit bug with the full stacktrace.");
            broken.initCause(throwable);
            RxJavaPlugins.getInstance().getErrorHandler().handleError(broken);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.subscriberTerminated) {
                this.subscriber.onCompleted();
            }
        }
    }
}

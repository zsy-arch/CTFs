package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes2.dex */
public final class OnSubscribeFlatMapCompletable<T> implements Observable.OnSubscribe<T> {
    final boolean delayErrors;
    final Func1<? super T, ? extends Completable> mapper;
    final int maxConcurrency;
    final Observable<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeFlatMapCompletable(Observable<T> source, Func1<? super T, ? extends Completable> mapper, boolean delayErrors, int maxConcurrency) {
        if (mapper == null) {
            throw new NullPointerException("mapper is null");
        } else if (maxConcurrency <= 0) {
            throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + maxConcurrency);
        } else {
            this.source = source;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
        }
    }

    public void call(Subscriber<? super T> child) {
        FlatMapCompletableSubscriber<T> parent = new FlatMapCompletableSubscriber<>(child, this.mapper, this.delayErrors, this.maxConcurrency);
        child.add(parent);
        child.add(parent.set);
        this.source.unsafeSubscribe(parent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class FlatMapCompletableSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        final boolean delayErrors;
        final Func1<? super T, ? extends Completable> mapper;
        final int maxConcurrency;
        final AtomicInteger wip = new AtomicInteger(1);
        final AtomicReference<Throwable> errors = new AtomicReference<>();
        final CompositeSubscription set = new CompositeSubscription();

        FlatMapCompletableSubscriber(Subscriber<? super T> actual, Func1<? super T, ? extends Completable> mapper, boolean delayErrors, int maxConcurrency) {
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
            request(maxConcurrency != Integer.MAX_VALUE ? maxConcurrency : Long.MAX_VALUE);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Completable c = (Completable) this.mapper.call(t);
                if (c == null) {
                    throw new NullPointerException("The mapper returned a null Completable");
                }
                FlatMapCompletableSubscriber<T>.InnerSubscriber inner = new InnerSubscriber();
                this.set.add(inner);
                this.wip.getAndIncrement();
                c.unsafeSubscribe(inner);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                unsubscribe();
                onError(ex);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (this.delayErrors) {
                ExceptionsUtils.addThrowable(this.errors, e);
                onCompleted();
                return;
            }
            this.set.unsubscribe();
            if (this.errors.compareAndSet(null, e)) {
                this.actual.onError(ExceptionsUtils.terminate(this.errors));
            } else {
                RxJavaHooks.onError(e);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            done();
        }

        boolean done() {
            if (this.wip.decrementAndGet() != 0) {
                return false;
            }
            Throwable ex = ExceptionsUtils.terminate(this.errors);
            if (ex != null) {
                this.actual.onError(ex);
            } else {
                this.actual.onCompleted();
            }
            return true;
        }

        public void innerError(FlatMapCompletableSubscriber<T>.InnerSubscriber inner, Throwable e) {
            this.set.remove(inner);
            if (this.delayErrors) {
                ExceptionsUtils.addThrowable(this.errors, e);
                if (!done() && this.maxConcurrency != Integer.MAX_VALUE) {
                    request(1L);
                    return;
                }
                return;
            }
            this.set.unsubscribe();
            unsubscribe();
            if (this.errors.compareAndSet(null, e)) {
                this.actual.onError(ExceptionsUtils.terminate(this.errors));
            } else {
                RxJavaHooks.onError(e);
            }
        }

        public void innerComplete(FlatMapCompletableSubscriber<T>.InnerSubscriber inner) {
            this.set.remove(inner);
            if (!done() && this.maxConcurrency != Integer.MAX_VALUE) {
                request(1L);
            }
        }

        /* loaded from: classes2.dex */
        final class InnerSubscriber extends AtomicReference<Subscription> implements CompletableSubscriber, Subscription {
            private static final long serialVersionUID = -8588259593722659900L;

            InnerSubscriber() {
            }

            @Override // rx.Subscription
            public void unsubscribe() {
                Subscription s = getAndSet(this);
                if (s != null && s != this) {
                    s.unsubscribe();
                }
            }

            @Override // rx.Subscription
            public boolean isUnsubscribed() {
                return get() == this;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                FlatMapCompletableSubscriber.this.innerComplete(this);
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable e) {
                FlatMapCompletableSubscriber.this.innerError(this, e);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription d) {
                if (!compareAndSet(null, d)) {
                    d.unsubscribe();
                    if (get() != this) {
                        RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
                    }
                }
            }
        }
    }
}

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func1;

/* loaded from: classes2.dex */
public final class OperatorMapNotification<T, R> implements Observable.Operator<R, T> {
    final Func0<? extends R> onCompleted;
    final Func1<? super Throwable, ? extends R> onError;
    final Func1<? super T, ? extends R> onNext;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorMapNotification(Func1<? super T, ? extends R> onNext, Func1<? super Throwable, ? extends R> onError, Func0<? extends R> onCompleted) {
        this.onNext = onNext;
        this.onError = onError;
        this.onCompleted = onCompleted;
    }

    public Subscriber<? super T> call(Subscriber<? super R> child) {
        final MapNotificationSubscriber<T, R> parent = new MapNotificationSubscriber<>(child, this.onNext, this.onError, this.onCompleted);
        child.add(parent);
        child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorMapNotification.1
            @Override // rx.Producer
            public void request(long n) {
                parent.requestInner(n);
            }
        });
        return parent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class MapNotificationSubscriber<T, R> extends Subscriber<T> {
        static final long COMPLETED_FLAG = Long.MIN_VALUE;
        static final long REQUESTED_MASK = Long.MAX_VALUE;
        final Subscriber<? super R> actual;
        final Func0<? extends R> onCompleted;
        final Func1<? super Throwable, ? extends R> onError;
        final Func1<? super T, ? extends R> onNext;
        long produced;
        R value;
        final AtomicLong requested = new AtomicLong();
        final AtomicLong missedRequested = new AtomicLong();
        final AtomicReference<Producer> producer = new AtomicReference<>();

        public MapNotificationSubscriber(Subscriber<? super R> actual, Func1<? super T, ? extends R> onNext, Func1<? super Throwable, ? extends R> onError, Func0<? extends R> onCompleted) {
            this.actual = actual;
            this.onNext = onNext;
            this.onError = onError;
            this.onCompleted = onCompleted;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                this.produced++;
                this.actual.onNext((Object) this.onNext.call(t));
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, this.actual, t);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            accountProduced();
            try {
                this.value = (R) this.onError.call(e);
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, this.actual, e);
            }
            tryEmit();
        }

        @Override // rx.Observer
        public void onCompleted() {
            accountProduced();
            try {
                this.value = (R) this.onCompleted.call();
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, this.actual);
            }
            tryEmit();
        }

        void accountProduced() {
            long p = this.produced;
            if (p != 0 && this.producer.get() != null) {
                BackpressureUtils.produced(this.requested, p);
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) {
            if (this.producer.compareAndSet(null, p)) {
                long r = this.missedRequested.getAndSet(0L);
                if (r != 0) {
                    p.request(r);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Producer already set!");
        }

        void tryEmit() {
            long r;
            do {
                r = this.requested.get();
                if ((r & COMPLETED_FLAG) != 0) {
                    return;
                }
            } while (!this.requested.compareAndSet(r, r | COMPLETED_FLAG));
            if (r != 0 || this.producer.get() == null) {
                if (!this.actual.isUnsubscribed()) {
                    this.actual.onNext((R) this.value);
                }
                if (!this.actual.isUnsubscribed()) {
                    this.actual.onCompleted();
                }
            }
        }

        void requestInner(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            } else if (n != 0) {
                while (true) {
                    long r = this.requested.get();
                    if ((COMPLETED_FLAG & r) != 0) {
                        long v = r & REQUESTED_MASK;
                        if (this.requested.compareAndSet(r, BackpressureUtils.addCap(v, n) | COMPLETED_FLAG)) {
                            if (v == 0) {
                                if (!this.actual.isUnsubscribed()) {
                                    this.actual.onNext((R) this.value);
                                }
                                if (!this.actual.isUnsubscribed()) {
                                    this.actual.onCompleted();
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    } else {
                        if (this.requested.compareAndSet(r, BackpressureUtils.addCap(r, n))) {
                            AtomicReference<Producer> localProducer = this.producer;
                            Producer actualProducer = localProducer.get();
                            if (actualProducer != null) {
                                actualProducer.request(n);
                                return;
                            }
                            BackpressureUtils.getAndAddRequest(this.missedRequested, n);
                            Producer actualProducer2 = localProducer.get();
                            if (actualProducer2 != null) {
                                long r2 = this.missedRequested.getAndSet(0L);
                                if (r2 != 0) {
                                    actualProducer2.request(r2);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    }
                }
            }
        }
    }
}

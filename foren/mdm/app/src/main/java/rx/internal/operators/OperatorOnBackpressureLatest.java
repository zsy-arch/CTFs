package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;

/* loaded from: classes2.dex */
public final class OperatorOnBackpressureLatest<T> implements Observable.Operator<T, T> {
    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Holder {
        static final OperatorOnBackpressureLatest<Object> INSTANCE = new OperatorOnBackpressureLatest<>();

        Holder() {
        }
    }

    public static <T> OperatorOnBackpressureLatest<T> instance() {
        return (OperatorOnBackpressureLatest<T>) Holder.INSTANCE;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        LatestEmitter<T> producer = new LatestEmitter<>(child);
        LatestSubscriber<? super T> latestSubscriber = new LatestSubscriber<>(producer);
        producer.parent = latestSubscriber;
        child.add(latestSubscriber);
        child.add(producer);
        child.setProducer(producer);
        return latestSubscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class LatestEmitter<T> extends AtomicLong implements Producer, Subscription, Observer<T> {
        static final Object EMPTY = new Object();
        static final long NOT_REQUESTED = -4611686018427387904L;
        private static final long serialVersionUID = -1364393685005146274L;
        final Subscriber<? super T> child;
        volatile boolean done;
        boolean emitting;
        boolean missed;
        LatestSubscriber<? super T> parent;
        Throwable terminal;
        final AtomicReference<Object> value = new AtomicReference<>(EMPTY);

        public LatestEmitter(Subscriber<? super T> child) {
            this.child = child;
            lazySet(NOT_REQUESTED);
        }

        @Override // rx.Producer
        public void request(long n) {
            long r;
            long u2;
            if (n >= 0) {
                do {
                    r = get();
                    if (r != Long.MIN_VALUE) {
                        if (r == NOT_REQUESTED) {
                            u2 = n;
                        } else {
                            u2 = r + n;
                            if (u2 < 0) {
                                u2 = Long.MAX_VALUE;
                            }
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(r, u2));
                if (r == NOT_REQUESTED) {
                    this.parent.requestMore(Long.MAX_VALUE);
                }
                emit();
            }
        }

        long produced(long n) {
            long r;
            long u2;
            do {
                r = get();
                if (r < 0) {
                    return r;
                }
                u2 = r - n;
            } while (!compareAndSet(r, u2));
            return u2;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (get() >= 0) {
                getAndSet(Long.MIN_VALUE);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.value.lazySet(t);
            emit();
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.terminal = e;
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x0065, code lost:
            r8.emitting = false;
            r1 = true;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void emit() {
            /*
                r8 = this;
                monitor-enter(r8)
                boolean r6 = r8.emitting     // Catch: all -> 0x0028
                if (r6 == 0) goto L_0x000a
                r6 = 1
                r8.missed = r6     // Catch: all -> 0x0028
                monitor-exit(r8)     // Catch: all -> 0x0028
            L_0x0009:
                return
            L_0x000a:
                r6 = 1
                r8.emitting = r6     // Catch: all -> 0x0028
                r6 = 0
                r8.missed = r6     // Catch: all -> 0x0028
                monitor-exit(r8)     // Catch: all -> 0x0028
                r1 = 0
            L_0x0012:
                long r2 = r8.get()     // Catch: all -> 0x006e
                r6 = -9223372036854775808
                int r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
                if (r6 != 0) goto L_0x002b
                r1 = 1
            L_0x001d:
                if (r1 != 0) goto L_0x0009
                monitor-enter(r8)
                r6 = 0
                r8.emitting = r6     // Catch: all -> 0x0025
                monitor-exit(r8)     // Catch: all -> 0x0025
                goto L_0x0009
            L_0x0025:
                r6 = move-exception
                monitor-exit(r8)     // Catch: all -> 0x0025
                throw r6
            L_0x0028:
                r6 = move-exception
                monitor-exit(r8)     // Catch: all -> 0x0028
                throw r6
            L_0x002b:
                java.util.concurrent.atomic.AtomicReference<java.lang.Object> r6 = r8.value     // Catch: all -> 0x006e
                java.lang.Object r4 = r6.get()     // Catch: all -> 0x006e
                r6 = 0
                int r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
                if (r6 <= 0) goto L_0x004f
                java.lang.Object r6 = rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.EMPTY     // Catch: all -> 0x006e
                if (r4 == r6) goto L_0x004f
                r5 = r4
                rx.Subscriber<? super T> r6 = r8.child     // Catch: all -> 0x006e
                r6.onNext(r5)     // Catch: all -> 0x006e
                java.util.concurrent.atomic.AtomicReference<java.lang.Object> r6 = r8.value     // Catch: all -> 0x006e
                java.lang.Object r7 = rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.EMPTY     // Catch: all -> 0x006e
                r6.compareAndSet(r4, r7)     // Catch: all -> 0x006e
                r6 = 1
                r8.produced(r6)     // Catch: all -> 0x006e
                java.lang.Object r4 = rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.EMPTY     // Catch: all -> 0x006e
            L_0x004f:
                java.lang.Object r6 = rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.EMPTY     // Catch: all -> 0x006e
                if (r4 != r6) goto L_0x0060
                boolean r6 = r8.done     // Catch: all -> 0x006e
                if (r6 == 0) goto L_0x0060
                java.lang.Throwable r0 = r8.terminal     // Catch: all -> 0x006e
                if (r0 == 0) goto L_0x0077
                rx.Subscriber<? super T> r6 = r8.child     // Catch: all -> 0x006e
                r6.onError(r0)     // Catch: all -> 0x006e
            L_0x0060:
                monitor-enter(r8)     // Catch: all -> 0x006e
                boolean r6 = r8.missed     // Catch: all -> 0x006b
                if (r6 != 0) goto L_0x007d
                r6 = 0
                r8.emitting = r6     // Catch: all -> 0x006b
                r1 = 1
                monitor-exit(r8)     // Catch: all -> 0x006b
                goto L_0x001d
            L_0x006b:
                r6 = move-exception
                monitor-exit(r8)     // Catch: all -> 0x006b
                throw r6     // Catch: all -> 0x006e
            L_0x006e:
                r6 = move-exception
                if (r1 != 0) goto L_0x0076
                monitor-enter(r8)
                r7 = 0
                r8.emitting = r7     // Catch: all -> 0x0082
                monitor-exit(r8)     // Catch: all -> 0x0082
            L_0x0076:
                throw r6
            L_0x0077:
                rx.Subscriber<? super T> r6 = r8.child     // Catch: all -> 0x006e
                r6.onCompleted()     // Catch: all -> 0x006e
                goto L_0x0060
            L_0x007d:
                r6 = 0
                r8.missed = r6     // Catch: all -> 0x006b
                monitor-exit(r8)     // Catch: all -> 0x006b
                goto L_0x0012
            L_0x0082:
                r6 = move-exception
                monitor-exit(r8)     // Catch: all -> 0x0082
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorOnBackpressureLatest.LatestEmitter.emit():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class LatestSubscriber<T> extends Subscriber<T> {
        private final LatestEmitter<T> producer;

        LatestSubscriber(LatestEmitter<T> producer) {
            this.producer = producer;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(0L);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.producer.onNext(t);
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.producer.onError(e);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.producer.onCompleted();
        }

        void requestMore(long n) {
            request(n);
        }
    }
}

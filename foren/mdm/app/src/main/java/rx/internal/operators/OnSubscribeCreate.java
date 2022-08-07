package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Emitter;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action1;
import rx.functions.Cancellable;
import rx.internal.subscriptions.CancellableSubscription;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes2.dex */
public final class OnSubscribeCreate<T> implements Observable.OnSubscribe<T> {
    final Action1<Emitter<T>> Emitter;
    final Emitter.BackpressureMode backpressure;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeCreate(Action1<Emitter<T>> Emitter, Emitter.BackpressureMode backpressure) {
        this.Emitter = Emitter;
        this.backpressure = backpressure;
    }

    public void call(Subscriber<? super T> t) {
        BaseEmitter<T> emitter;
        switch (this.backpressure) {
            case NONE:
                emitter = new NoneEmitter<>(t);
                break;
            case ERROR:
                emitter = new ErrorEmitter<>(t);
                break;
            case DROP:
                emitter = new DropEmitter<>(t);
                break;
            case LATEST:
                emitter = new LatestEmitter<>(t);
                break;
            default:
                emitter = new BufferEmitter<>(t, RxRingBuffer.SIZE);
                break;
        }
        t.add(emitter);
        t.setProducer(emitter);
        this.Emitter.call(emitter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static abstract class BaseEmitter<T> extends AtomicLong implements Emitter<T>, Producer, Subscription {
        private static final long serialVersionUID = 7326289992464377023L;
        final Subscriber<? super T> actual;
        final SerialSubscription serial = new SerialSubscription();

        public BaseEmitter(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.actual.isUnsubscribed()) {
                try {
                    this.actual.onCompleted();
                } finally {
                    this.serial.unsubscribe();
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (!this.actual.isUnsubscribed()) {
                try {
                    this.actual.onError(e);
                } finally {
                    this.serial.unsubscribe();
                }
            }
        }

        @Override // rx.Subscription
        public final void unsubscribe() {
            this.serial.unsubscribe();
            onUnsubscribed();
        }

        void onUnsubscribed() {
        }

        @Override // rx.Subscription
        public final boolean isUnsubscribed() {
            return this.serial.isUnsubscribed();
        }

        @Override // rx.Producer
        public final void request(long n) {
            if (BackpressureUtils.validate(n)) {
                BackpressureUtils.getAndAddRequest(this, n);
                onRequested();
            }
        }

        void onRequested() {
        }

        @Override // rx.Emitter
        public final void setSubscription(Subscription s) {
            this.serial.set(s);
        }

        @Override // rx.Emitter
        public final void setCancellation(Cancellable c) {
            setSubscription(new CancellableSubscription(c));
        }

        @Override // rx.Emitter
        public final long requested() {
            return get();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class NoneEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 3776720187248809713L;

        public NoneEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long r;
            if (!this.actual.isUnsubscribed()) {
                this.actual.onNext(t);
                do {
                    r = get();
                    if (r == 0) {
                        return;
                    }
                } while (!compareAndSet(r, r - 1));
            }
        }
    }

    /* loaded from: classes2.dex */
    static abstract class NoOverflowBaseEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4127754106204442833L;

        abstract void onOverflow();

        public NoOverflowBaseEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        public void onNext(T t) {
            if (!this.actual.isUnsubscribed()) {
                if (get() != 0) {
                    this.actual.onNext(t);
                    BackpressureUtils.produced(this, 1L);
                    return;
                }
                onOverflow();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class DropEmitter<T> extends NoOverflowBaseEmitter<T> {
        private static final long serialVersionUID = 8360058422307496563L;

        public DropEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        @Override // rx.internal.operators.OnSubscribeCreate.NoOverflowBaseEmitter
        void onOverflow() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class ErrorEmitter<T> extends NoOverflowBaseEmitter<T> {
        private static final long serialVersionUID = 338953216916120960L;
        private boolean done;

        public ErrorEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        @Override // rx.internal.operators.OnSubscribeCreate.NoOverflowBaseEmitter, rx.Observer
        public void onNext(T t) {
            if (!this.done) {
                super.onNext(t);
            }
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter, rx.Observer
        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                super.onCompleted();
            }
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter, rx.Observer
        public void onError(Throwable e) {
            if (this.done) {
                RxJavaHooks.onError(e);
                return;
            }
            this.done = true;
            super.onError(e);
        }

        @Override // rx.internal.operators.OnSubscribeCreate.NoOverflowBaseEmitter
        void onOverflow() {
            onError(new MissingBackpressureException("create: could not emit value due to lack of requests"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class BufferEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final Queue<Object> queue;
        final AtomicInteger wip;

        public BufferEmitter(Subscriber<? super T> actual, int capacityHint) {
            super(actual);
            this.queue = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(capacityHint) : new SpscUnboundedAtomicArrayQueue<>(capacityHint);
            this.wip = new AtomicInteger();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.queue.offer(NotificationLite.next(t));
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter, rx.Observer
        public void onError(Throwable e) {
            this.error = e;
            this.done = true;
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter, rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter
        void onRequested() {
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = this.actual;
                Queue<Object> q = this.queue;
                do {
                    long r = get();
                    long e = 0;
                    while (e != r) {
                        if (a.isUnsubscribed()) {
                            q.clear();
                            return;
                        }
                        boolean d = this.done;
                        Object o = q.poll();
                        boolean empty = o == null;
                        if (d && empty) {
                            Throwable ex = this.error;
                            if (ex != null) {
                                super.onError(ex);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            a.onNext(NotificationLite.getValue(o));
                            e++;
                        }
                    }
                    if (e == r) {
                        if (a.isUnsubscribed()) {
                            q.clear();
                            return;
                        }
                        boolean d2 = this.done;
                        boolean empty2 = q.isEmpty();
                        if (d2 && empty2) {
                            Throwable ex2 = this.error;
                            if (ex2 != null) {
                                super.onError(ex2);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        }
                    }
                    if (e != 0) {
                        BackpressureUtils.produced(this, e);
                    }
                    missed = this.wip.addAndGet(-missed);
                } while (missed != 0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class LatestEmitter<T> extends BaseEmitter<T> {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final AtomicReference<Object> queue = new AtomicReference<>();
        final AtomicInteger wip = new AtomicInteger();

        public LatestEmitter(Subscriber<? super T> actual) {
            super(actual);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.queue.set(NotificationLite.next(t));
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter, rx.Observer
        public void onError(Throwable e) {
            this.error = e;
            this.done = true;
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter, rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter
        void onRequested() {
            drain();
        }

        @Override // rx.internal.operators.OnSubscribeCreate.BaseEmitter
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain() {
            if (this.wip.getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = this.actual;
                AtomicReference<Object> q = this.queue;
                do {
                    long r = get();
                    long e = 0;
                    while (e != r) {
                        if (a.isUnsubscribed()) {
                            q.lazySet(null);
                            return;
                        }
                        boolean d = this.done;
                        Object o = q.getAndSet(null);
                        boolean empty = o == null;
                        if (d && empty) {
                            Throwable ex = this.error;
                            if (ex != null) {
                                super.onError(ex);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        } else if (empty) {
                            break;
                        } else {
                            a.onNext(NotificationLite.getValue(o));
                            e++;
                        }
                    }
                    if (e == r) {
                        if (a.isUnsubscribed()) {
                            q.lazySet(null);
                            return;
                        }
                        boolean d2 = this.done;
                        boolean empty2 = q.get() == null;
                        if (d2 && empty2) {
                            Throwable ex2 = this.error;
                            if (ex2 != null) {
                                super.onError(ex2);
                                return;
                            } else {
                                super.onCompleted();
                                return;
                            }
                        }
                    }
                    if (e != 0) {
                        BackpressureUtils.produced(this, e);
                    }
                    missed = this.wip.addAndGet(-missed);
                } while (missed != 0);
            }
        }
    }
}

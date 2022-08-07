package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
public final class OperatorPublish<T> extends ConnectableObservable<T> {
    final AtomicReference<PublishSubscriber<T>> current;
    final Observable<? extends T> source;

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source) {
        final AtomicReference<PublishSubscriber<T>> curr = new AtomicReference<>();
        return new OperatorPublish(new Observable.OnSubscribe<T>() { // from class: rx.internal.operators.OperatorPublish.1
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object x0) {
                call((Subscriber) ((Subscriber) x0));
            }

            public void call(Subscriber<? super T> child) {
                while (true) {
                    PublishSubscriber<T> r = (PublishSubscriber) curr.get();
                    if (r == null || r.isUnsubscribed()) {
                        PublishSubscriber<T> u2 = new PublishSubscriber<>(curr);
                        u2.init();
                        if (curr.compareAndSet(r, u2)) {
                            r = u2;
                        } else {
                            continue;
                        }
                    }
                    InnerProducer<T> inner = new InnerProducer<>(r, child);
                    if (r.add((InnerProducer) inner)) {
                        child.add(inner);
                        child.setProducer(inner);
                        return;
                    }
                }
            }
        }, source, curr);
    }

    public static <T, R> Observable<R> create(Observable<? extends T> source, Func1<? super Observable<T>, ? extends Observable<R>> selector) {
        return create(source, selector, false);
    }

    public static <T, R> Observable<R> create(final Observable<? extends T> source, final Func1<? super Observable<T>, ? extends Observable<R>> selector, final boolean delayError) {
        return unsafeCreate(new Observable.OnSubscribe<R>() { // from class: rx.internal.operators.OperatorPublish.2
            /* JADX WARN: Multi-variable type inference failed */
            public void call(final Subscriber<? super R> child) {
                final OnSubscribePublishMulticast<T> op = new OnSubscribePublishMulticast<>(RxRingBuffer.SIZE, delayError);
                Subscription subscription = new Subscriber<R>() { // from class: rx.internal.operators.OperatorPublish.2.1
                    @Override // rx.Observer
                    public void onNext(R t) {
                        child.onNext(t);
                    }

                    @Override // rx.Observer
                    public void onError(Throwable e) {
                        op.unsubscribe();
                        child.onError(e);
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        op.unsubscribe();
                        child.onCompleted();
                    }

                    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
                    public void setProducer(Producer p) {
                        child.setProducer(p);
                    }
                };
                child.add(op);
                child.add(subscription);
                ((Observable) selector.call(Observable.unsafeCreate(op))).unsafeSubscribe(subscription);
                source.unsafeSubscribe(op.subscriber());
            }
        });
    }

    private OperatorPublish(Observable.OnSubscribe<T> onSubscribe, Observable<? extends T> source, AtomicReference<PublishSubscriber<T>> current) {
        super(onSubscribe);
        this.source = source;
        this.current = current;
    }

    @Override // rx.observables.ConnectableObservable
    public void connect(Action1<? super Subscription> connection) {
        PublishSubscriber<T> ps;
        boolean doConnect = true;
        while (true) {
            ps = this.current.get();
            if (ps != null && !ps.isUnsubscribed()) {
                break;
            }
            PublishSubscriber<T> u2 = new PublishSubscriber<>(this.current);
            u2.init();
            if (this.current.compareAndSet(ps, u2)) {
                ps = u2;
                break;
            }
        }
        if (ps.shouldConnect.get() || !ps.shouldConnect.compareAndSet(false, true)) {
            doConnect = false;
        }
        connection.call(ps);
        if (doConnect) {
            this.source.unsafeSubscribe(ps);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class PublishSubscriber<T> extends Subscriber<T> implements Subscription {
        static final InnerProducer[] EMPTY = new InnerProducer[0];
        static final InnerProducer[] TERMINATED = new InnerProducer[0];
        final AtomicReference<PublishSubscriber<T>> current;
        boolean emitting;
        boolean missed;
        final AtomicReference<InnerProducer[]> producers;
        final Queue<Object> queue;
        final AtomicBoolean shouldConnect;
        volatile Object terminalEvent;

        public PublishSubscriber(AtomicReference<PublishSubscriber<T>> current) {
            this.queue = UnsafeAccess.isUnsafeAvailable() ? new SpscArrayQueue<>(RxRingBuffer.SIZE) : new SpscAtomicArrayQueue<>(RxRingBuffer.SIZE);
            this.producers = new AtomicReference<>(EMPTY);
            this.current = current;
            this.shouldConnect = new AtomicBoolean();
        }

        void init() {
            add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorPublish.PublishSubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    PublishSubscriber.this.producers.getAndSet(PublishSubscriber.TERMINATED);
                    PublishSubscriber.this.current.compareAndSet(PublishSubscriber.this, null);
                }
            }));
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(RxRingBuffer.SIZE);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.queue.offer(NotificationLite.next(t))) {
                onError(new MissingBackpressureException());
            } else {
                dispatch();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.error(e);
                dispatch();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.completed();
                dispatch();
            }
        }

        boolean add(InnerProducer<T> producer) {
            InnerProducer[] c;
            InnerProducer[] u2;
            if (producer == null) {
                throw new NullPointerException();
            }
            do {
                c = this.producers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u2 = new InnerProducer[len + 1];
                System.arraycopy(c, 0, u2, 0, len);
                u2[len] = producer;
            } while (!this.producers.compareAndSet(c, u2));
            return true;
        }

        void remove(InnerProducer<T> producer) {
            InnerProducer[] c;
            InnerProducer[] u2;
            do {
                c = this.producers.get();
                if (c != EMPTY && c != TERMINATED) {
                    int j = -1;
                    int len = c.length;
                    int i = 0;
                    while (true) {
                        if (i >= len) {
                            break;
                        } else if (c[i].equals(producer)) {
                            j = i;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (len == 1) {
                        u2 = EMPTY;
                    } else {
                        u2 = new InnerProducer[len - 1];
                        System.arraycopy(c, 0, u2, 0, j);
                        System.arraycopy(c, j + 1, u2, j, (len - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.producers.compareAndSet(c, u2));
        }

        boolean checkTerminated(Object term, boolean empty) {
            if (term != null) {
                if (!NotificationLite.isCompleted(term)) {
                    Throwable t = NotificationLite.getError(term);
                    this.current.compareAndSet(this, null);
                    try {
                        for (InnerProducer<?> ip : this.producers.getAndSet(TERMINATED)) {
                            ip.child.onError(t);
                        }
                        return true;
                    } finally {
                    }
                } else if (empty) {
                    this.current.compareAndSet(this, null);
                    try {
                        for (InnerProducer<?> ip2 : this.producers.getAndSet(TERMINATED)) {
                            ip2.child.onCompleted();
                        }
                        return true;
                    } finally {
                    }
                }
            }
            return false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:92:0x0138, code lost:
            r26.emitting = false;
            r16 = true;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void dispatch() {
            /*
                Method dump skipped, instructions count: 421
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorPublish.PublishSubscriber.dispatch():void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class InnerProducer<T> extends AtomicLong implements Producer, Subscription {
        static final long NOT_REQUESTED = -4611686018427387904L;
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        final PublishSubscriber<T> parent;

        public InnerProducer(PublishSubscriber<T> parent, Subscriber<? super T> child) {
            this.parent = parent;
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
                    if (r == UNSUBSCRIBED) {
                        return;
                    }
                    if (r >= 0 && n == 0) {
                        return;
                    }
                    if (r == NOT_REQUESTED) {
                        u2 = n;
                    } else {
                        u2 = r + n;
                        if (u2 < 0) {
                            u2 = Long.MAX_VALUE;
                        }
                    }
                } while (!compareAndSet(r, u2));
                this.parent.dispatch();
            }
        }

        public long produced(long n) {
            long r;
            long u2;
            if (n <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            do {
                r = get();
                if (r == NOT_REQUESTED) {
                    throw new IllegalStateException("Produced without request");
                } else if (r == UNSUBSCRIBED) {
                    return UNSUBSCRIBED;
                } else {
                    u2 = r - n;
                    if (u2 < 0) {
                        throw new IllegalStateException("More produced (" + n + ") than requested (" + r + ")");
                    }
                }
            } while (!compareAndSet(r, u2));
            return u2;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == UNSUBSCRIBED;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (get() != UNSUBSCRIBED && getAndSet(UNSUBSCRIBED) != UNSUBSCRIBED) {
                this.parent.remove(this);
                this.parent.dispatch();
            }
        }
    }
}

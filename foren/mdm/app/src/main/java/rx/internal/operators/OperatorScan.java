package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;

/* loaded from: classes2.dex */
public final class OperatorScan<R, T> implements Observable.Operator<R, T> {
    private static final Object NO_INITIAL_VALUE = new Object();
    final Func2<R, ? super T, R> accumulator;
    private final Func0<R> initialValueFactory;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorScan(final R initialValue, Func2<R, ? super T, R> accumulator) {
        this((Func0) new Func0<R>() { // from class: rx.internal.operators.OperatorScan.1
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            public R call() {
                return (R) initialValue;
            }
        }, (Func2) accumulator);
    }

    public OperatorScan(Func0<R> initialValueFactory, Func2<R, ? super T, R> accumulator) {
        this.initialValueFactory = initialValueFactory;
        this.accumulator = accumulator;
    }

    public OperatorScan(Func2<R, ? super T, R> accumulator) {
        this(NO_INITIAL_VALUE, accumulator);
    }

    public Subscriber<? super T> call(final Subscriber<? super R> child) {
        final R initialValue = this.initialValueFactory.call();
        if (initialValue == NO_INITIAL_VALUE) {
            return (Subscriber<T>) new Subscriber<T>(child) { // from class: rx.internal.operators.OperatorScan.2
                boolean once;
                R value;

                @Override // rx.Observer
                public void onNext(T t) {
                    R v;
                    if (!this.once) {
                        this.once = true;
                        v = (R) t;
                    } else {
                        try {
                            v = OperatorScan.this.accumulator.call(this.value, t);
                        } catch (Throwable e) {
                            Exceptions.throwOrReport(e, child, t);
                            return;
                        }
                    }
                    this.value = v;
                    child.onNext(v);
                }

                @Override // rx.Observer
                public void onError(Throwable e) {
                    child.onError(e);
                }

                @Override // rx.Observer
                public void onCompleted() {
                    child.onCompleted();
                }
            };
        }
        final InitialProducer<R> ip = new InitialProducer<>(initialValue, child);
        Subscriber subscriber = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorScan.3
            private R value;

            {
                this.value = (R) initialValue;
            }

            @Override // rx.Observer
            public void onNext(T currentValue) {
                try {
                    R v = OperatorScan.this.accumulator.call(this.value, currentValue);
                    this.value = v;
                    ip.onNext(v);
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, this, currentValue);
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                ip.onError(e);
            }

            @Override // rx.Observer
            public void onCompleted() {
                ip.onCompleted();
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                ip.setProducer(producer);
            }
        };
        child.add(subscriber);
        child.setProducer(ip);
        return subscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class InitialProducer<R> implements Producer, Observer<R> {
        final Subscriber<? super R> child;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        long missedRequested;
        volatile Producer producer;
        final Queue<Object> queue;
        final AtomicLong requested;

        public InitialProducer(R initialValue, Subscriber<? super R> child) {
            Queue<Object> q;
            this.child = child;
            if (UnsafeAccess.isUnsafeAvailable()) {
                q = new SpscLinkedQueue<>();
            } else {
                q = new SpscLinkedAtomicQueue<>();
            }
            this.queue = q;
            q.offer(NotificationLite.next(initialValue));
            this.requested = new AtomicLong();
        }

        @Override // rx.Observer
        public void onNext(R t) {
            this.queue.offer(NotificationLite.next(t));
            emit();
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<? super R> child) {
            if (child.isUnsubscribed()) {
                return true;
            }
            if (d) {
                Throwable err = this.error;
                if (err != null) {
                    child.onError(err);
                    return true;
                } else if (empty) {
                    child.onCompleted();
                    return true;
                }
            }
            return false;
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.error = e;
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            } else if (n != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                Producer p = this.producer;
                if (p == null) {
                    synchronized (this.requested) {
                        p = this.producer;
                        if (p == null) {
                            this.missedRequested = BackpressureUtils.addCap(this.missedRequested, n);
                        }
                    }
                }
                if (p != null) {
                    p.request(n);
                }
                emit();
            }
        }

        public void setProducer(Producer p) {
            long mr;
            if (p == null) {
                throw new NullPointerException();
            }
            synchronized (this.requested) {
                if (this.producer != null) {
                    throw new IllegalStateException("Can't set more than one Producer!");
                }
                mr = this.missedRequested;
                if (mr != Long.MAX_VALUE) {
                    mr--;
                }
                this.missedRequested = 0L;
                this.producer = p;
            }
            if (mr > 0) {
                p.request(mr);
            }
            emit();
        }

        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                emitLoop();
            }
        }

        void emitLoop() {
            Subscriber<? super R> child = this.child;
            Queue<Object> queue = this.queue;
            AtomicLong requested = this.requested;
            long r = requested.get();
            while (!checkTerminated(this.done, queue.isEmpty(), child)) {
                long e = 0;
                while (e != r) {
                    boolean d = this.done;
                    Object o = queue.poll();
                    boolean empty = o == null;
                    if (checkTerminated(d, empty, child)) {
                        return;
                    }
                    if (empty) {
                        break;
                    }
                    Object obj = (Object) NotificationLite.getValue(o);
                    try {
                        child.onNext(obj);
                        e++;
                    } catch (Throwable ex) {
                        Exceptions.throwOrReport(ex, child, obj);
                        return;
                    }
                }
                if (!(e == 0 || r == Long.MAX_VALUE)) {
                    r = BackpressureUtils.produced(requested, e);
                }
                synchronized (this) {
                    if (!this.missed) {
                        this.emitting = false;
                        return;
                    }
                    this.missed = false;
                }
            }
        }
    }
}

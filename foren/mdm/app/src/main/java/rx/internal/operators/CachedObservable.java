package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.internal.util.LinkedArrayList;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes2.dex */
public final class CachedObservable<T> extends Observable<T> {
    private final CacheState<T> state;

    public static <T> CachedObservable<T> from(Observable<? extends T> source) {
        return from(source, 16);
    }

    public static <T> CachedObservable<T> from(Observable<? extends T> source, int capacityHint) {
        if (capacityHint < 1) {
            throw new IllegalArgumentException("capacityHint > 0 required");
        }
        CacheState<T> state = new CacheState<>(source, capacityHint);
        return new CachedObservable<>(new CachedSubscribe<>(state), state);
    }

    private CachedObservable(Observable.OnSubscribe<T> onSubscribe, CacheState<T> state) {
        super(onSubscribe);
        this.state = state;
    }

    boolean isConnected() {
        return this.state.isConnected;
    }

    boolean hasObservers() {
        return this.state.producers.length != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class CacheState<T> extends LinkedArrayList implements Observer<T> {
        static final ReplayProducer<?>[] EMPTY = new ReplayProducer[0];
        volatile boolean isConnected;
        final Observable<? extends T> source;
        boolean sourceDone;
        volatile ReplayProducer<?>[] producers = EMPTY;
        final SerialSubscription connection = new SerialSubscription();

        public CacheState(Observable<? extends T> source, int capacityHint) {
            super(capacityHint);
            this.source = source;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void addProducer(ReplayProducer<T> p) {
            synchronized (this.connection) {
                ReplayProducer<?>[] a = this.producers;
                int n = a.length;
                ReplayProducer<?>[] b = new ReplayProducer[n + 1];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = p;
                this.producers = b;
            }
        }

        public void removeProducer(ReplayProducer<T> p) {
            synchronized (this.connection) {
                ReplayProducer<?>[] a = this.producers;
                int n = a.length;
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    } else if (a[i].equals(p)) {
                        j = i;
                        break;
                    } else {
                        i++;
                    }
                }
                if (j >= 0) {
                    if (n == 1) {
                        this.producers = EMPTY;
                        return;
                    }
                    ReplayProducer<?>[] b = new ReplayProducer[n - 1];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                    this.producers = b;
                }
            }
        }

        public void connect() {
            Subscriber<T> subscriber = new Subscriber<T>() { // from class: rx.internal.operators.CachedObservable.CacheState.1
                @Override // rx.Observer
                public void onNext(T t) {
                    CacheState.this.onNext(t);
                }

                @Override // rx.Observer
                public void onError(Throwable e) {
                    CacheState.this.onError(e);
                }

                @Override // rx.Observer
                public void onCompleted() {
                    CacheState.this.onCompleted();
                }
            };
            this.connection.set(subscriber);
            this.source.unsafeSubscribe(subscriber);
            this.isConnected = true;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.sourceDone) {
                add(NotificationLite.next(t));
                dispatch();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.error(e));
                this.connection.unsubscribe();
                dispatch();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.sourceDone) {
                this.sourceDone = true;
                add(NotificationLite.completed());
                this.connection.unsubscribe();
                dispatch();
            }
        }

        void dispatch() {
            for (ReplayProducer<?> rp : this.producers) {
                rp.replay();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class CachedSubscribe<T> extends AtomicBoolean implements Observable.OnSubscribe<T> {
        private static final long serialVersionUID = -2817751667698696782L;
        final CacheState<T> state;

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object x0) {
            call((Subscriber) ((Subscriber) x0));
        }

        public CachedSubscribe(CacheState<T> state) {
            this.state = state;
        }

        public void call(Subscriber<? super T> t) {
            ReplayProducer<T> rp = new ReplayProducer<>(t, this.state);
            this.state.addProducer(rp);
            t.add(rp);
            t.setProducer(rp);
            if (!get() && compareAndSet(false, true)) {
                this.state.connect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class ReplayProducer<T> extends AtomicLong implements Producer, Subscription {
        private static final long serialVersionUID = -2557562030197141021L;
        final Subscriber<? super T> child;
        Object[] currentBuffer;
        int currentIndexInBuffer;
        boolean emitting;
        int index;
        boolean missed;
        final CacheState<T> state;

        public ReplayProducer(Subscriber<? super T> child, CacheState<T> state) {
            this.child = child;
            this.state = state;
        }

        @Override // rx.Producer
        public void request(long n) {
            long r;
            long u2;
            do {
                r = get();
                if (r >= 0) {
                    u2 = r + n;
                    if (u2 < 0) {
                        u2 = Long.MAX_VALUE;
                    }
                } else {
                    return;
                }
            } while (!compareAndSet(r, u2));
            replay();
        }

        public long produced(long n) {
            return addAndGet(-n);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() < 0;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (get() >= 0 && getAndSet(-1L) >= 0) {
                this.state.removeProducer(this);
            }
        }

        public void replay() {
            synchronized (this) {
                try {
                    if (this.emitting) {
                        this.missed = true;
                        return;
                    }
                    this.emitting = true;
                    try {
                        Subscriber<? super T> child = this.child;
                        while (true) {
                            long r = get();
                            if (r >= 0) {
                                int s = this.state.size();
                                if (s != 0) {
                                    Object[] b = this.currentBuffer;
                                    if (b == null) {
                                        b = this.state.head();
                                        this.currentBuffer = b;
                                    }
                                    int n = b.length - 1;
                                    int j = this.index;
                                    int k = this.currentIndexInBuffer;
                                    if (r == 0) {
                                        Object o = b[k];
                                        if (NotificationLite.isCompleted(o)) {
                                            child.onCompleted();
                                            unsubscribe();
                                            if (1 == 0) {
                                                synchronized (this) {
                                                    try {
                                                        this.emitting = false;
                                                    } catch (Throwable th) {
                                                        throw th;
                                                    }
                                                }
                                                return;
                                            }
                                            return;
                                        } else if (NotificationLite.isError(o)) {
                                            child.onError(NotificationLite.getError(o));
                                            unsubscribe();
                                            if (1 == 0) {
                                                synchronized (this) {
                                                    try {
                                                        this.emitting = false;
                                                    } catch (Throwable th2) {
                                                        throw th2;
                                                    }
                                                }
                                                return;
                                            }
                                            return;
                                        }
                                    } else if (r > 0) {
                                        int valuesProduced = 0;
                                        while (j < s && r > 0) {
                                            if (!child.isUnsubscribed()) {
                                                if (k == n) {
                                                    b = (Object[]) b[n];
                                                    k = 0;
                                                }
                                                if (NotificationLite.accept(child, b[k])) {
                                                    unsubscribe();
                                                    if (1 == 0) {
                                                        synchronized (this) {
                                                            try {
                                                                this.emitting = false;
                                                            } catch (Throwable th3) {
                                                                throw th3;
                                                            }
                                                        }
                                                        return;
                                                    }
                                                    return;
                                                }
                                                k++;
                                                j++;
                                                r--;
                                                valuesProduced++;
                                            } else if (1 == 0) {
                                                synchronized (this) {
                                                    try {
                                                        this.emitting = false;
                                                    } catch (Throwable th4) {
                                                        throw th4;
                                                    }
                                                }
                                                return;
                                            } else {
                                                return;
                                            }
                                        }
                                        if (!child.isUnsubscribed()) {
                                            this.index = j;
                                            this.currentIndexInBuffer = k;
                                            this.currentBuffer = b;
                                            produced(valuesProduced);
                                        } else if (1 == 0) {
                                            synchronized (this) {
                                                try {
                                                    this.emitting = false;
                                                } catch (Throwable th5) {
                                                    throw th5;
                                                }
                                            }
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                }
                                synchronized (this) {
                                    if (!this.missed) {
                                        this.emitting = false;
                                    } else {
                                        this.missed = false;
                                    }
                                }
                                if (1 == 0) {
                                    synchronized (this) {
                                        try {
                                            this.emitting = false;
                                        } catch (Throwable th6) {
                                            throw th6;
                                        }
                                    }
                                    return;
                                }
                                return;
                            } else if (1 == 0) {
                                synchronized (this) {
                                    try {
                                        this.emitting = false;
                                    } catch (Throwable th7) {
                                        throw th7;
                                    }
                                }
                                return;
                            } else {
                                return;
                            }
                        }
                    } catch (Throwable th8) {
                        if (0 == 0) {
                            synchronized (this) {
                                try {
                                    this.emitting = false;
                                } catch (Throwable th9) {
                                    throw th9;
                                }
                            }
                        }
                        throw th8;
                    }
                } catch (Throwable th10) {
                    throw th10;
                }
            }
        }
    }
}

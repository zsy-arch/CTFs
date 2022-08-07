package rx.subjects;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.operators.BackpressureUtils;
import rx.internal.operators.NotificationLite;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

/* loaded from: classes2.dex */
public final class UnicastSubject<T> extends Subject<T, T> {
    final State<T> state;

    public static <T> UnicastSubject<T> create() {
        return create(16);
    }

    public static <T> UnicastSubject<T> create(int capacityHint) {
        return new UnicastSubject<>(new State<>(capacityHint, false, null));
    }

    public static <T> UnicastSubject<T> create(boolean delayError) {
        return new UnicastSubject<>(new State<>(16, delayError, null));
    }

    public static <T> UnicastSubject<T> create(int capacityHint, Action0 onTerminated) {
        return new UnicastSubject<>(new State<>(capacityHint, false, onTerminated));
    }

    public static <T> UnicastSubject<T> create(int capacityHint, Action0 onTerminated, boolean delayError) {
        return new UnicastSubject<>(new State<>(capacityHint, delayError, onTerminated));
    }

    private UnicastSubject(State<T> state) {
        super(state);
        this.state = state;
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.state.onNext(t);
    }

    @Override // rx.Observer
    public void onError(Throwable e) {
        this.state.onError(e);
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.state.onCompleted();
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.state.subscriber.get() != null;
    }

    /* loaded from: classes2.dex */
    public static final class State<T> extends AtomicLong implements Producer, Observer<T>, Observable.OnSubscribe<T>, Subscription {
        private static final long serialVersionUID = -9044104859202255786L;
        volatile boolean caughtUp;
        final boolean delayError;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        final Queue<Object> queue;
        final AtomicReference<Subscriber<? super T>> subscriber = new AtomicReference<>();
        final AtomicReference<Action0> terminateOnce;

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object x0) {
            call((Subscriber) ((Subscriber) x0));
        }

        public State(int capacityHint, boolean delayError, Action0 onTerminated) {
            Queue<Object> q;
            this.terminateOnce = onTerminated != null ? new AtomicReference<>(onTerminated) : null;
            this.delayError = delayError;
            if (capacityHint > 1) {
                q = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(capacityHint) : new SpscUnboundedAtomicArrayQueue<>(capacityHint);
            } else {
                q = UnsafeAccess.isUnsafeAvailable() ? new SpscLinkedQueue<>() : new SpscLinkedAtomicQueue<>();
            }
            this.queue = q;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.done) {
                if (!this.caughtUp) {
                    boolean stillReplay = false;
                    synchronized (this) {
                        if (!this.caughtUp) {
                            this.queue.offer(NotificationLite.next(t));
                            stillReplay = true;
                        }
                    }
                    if (stillReplay) {
                        replay();
                        return;
                    }
                }
                Subscriber<? super T> s = this.subscriber.get();
                try {
                    s.onNext(t);
                } catch (Throwable ex) {
                    Exceptions.throwOrReport(ex, s, t);
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            boolean stillReplay = true;
            if (!this.done) {
                doTerminate();
                this.error = e;
                this.done = true;
                if (!this.caughtUp) {
                    synchronized (this) {
                        if (this.caughtUp) {
                            stillReplay = false;
                        }
                    }
                    if (stillReplay) {
                        replay();
                        return;
                    }
                }
                this.subscriber.get().onError(e);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            boolean stillReplay = true;
            if (!this.done) {
                doTerminate();
                this.done = true;
                if (!this.caughtUp) {
                    synchronized (this) {
                        if (this.caughtUp) {
                            stillReplay = false;
                        }
                    }
                    if (stillReplay) {
                        replay();
                        return;
                    }
                }
                this.subscriber.get().onCompleted();
            }
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            } else if (n > 0) {
                BackpressureUtils.getAndAddRequest(this, n);
                replay();
            } else if (this.done) {
                replay();
            }
        }

        public void call(Subscriber<? super T> subscriber) {
            if (this.subscriber.compareAndSet(null, subscriber)) {
                subscriber.add(this);
                subscriber.setProducer(this);
                return;
            }
            subscriber.onError(new IllegalStateException("Only a single subscriber is allowed"));
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x0089, code lost:
            if (r14 == false) goto L_0x0099;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x008f, code lost:
            if (r10.isEmpty() == false) goto L_0x0099;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
            r20.caughtUp = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0099, code lost:
            r20.emitting = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:?, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void replay() {
            /*
                Method dump skipped, instructions count: 223
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.UnicastSubject.State.replay():void");
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            doTerminate();
            this.done = true;
            synchronized (this) {
                if (!this.emitting) {
                    this.emitting = true;
                    this.queue.clear();
                }
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.done;
        }

        boolean checkTerminated(boolean done, boolean empty, boolean delayError, Subscriber<? super T> s) {
            if (s.isUnsubscribed()) {
                this.queue.clear();
                return true;
            }
            if (done) {
                Throwable e = this.error;
                if (e != null && !delayError) {
                    this.queue.clear();
                    s.onError(e);
                    return true;
                } else if (empty) {
                    if (e != null) {
                        s.onError(e);
                        return true;
                    }
                    s.onCompleted();
                    return true;
                }
            }
            return false;
        }

        void doTerminate() {
            Action0 a;
            AtomicReference<Action0> ref = this.terminateOnce;
            if (ref != null && (a = ref.get()) != null && ref.compareAndSet(a, null)) {
                a.call();
            }
        }
    }
}

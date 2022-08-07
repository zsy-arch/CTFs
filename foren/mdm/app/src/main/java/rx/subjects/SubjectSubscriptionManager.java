package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.NotificationLite;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
final class SubjectSubscriptionManager<T> extends AtomicReference<State<T>> implements Observable.OnSubscribe<T> {
    private static final long serialVersionUID = 6035251036011671568L;
    volatile Object latest;
    boolean active = true;
    Action1<SubjectObserver<T>> onStart = Actions.empty();
    Action1<SubjectObserver<T>> onAdded = Actions.empty();
    Action1<SubjectObserver<T>> onTerminated = Actions.empty();

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public SubjectSubscriptionManager() {
        super(State.EMPTY);
    }

    public void call(Subscriber<? super T> child) {
        SubjectObserver<T> bo = new SubjectObserver<>(child);
        addUnsubscriber(child, bo);
        this.onStart.call(bo);
        if (!child.isUnsubscribed() && add(bo) && child.isUnsubscribed()) {
            remove(bo);
        }
    }

    void addUnsubscriber(Subscriber<? super T> child, final SubjectObserver<T> bo) {
        child.add(Subscriptions.create(new Action0() { // from class: rx.subjects.SubjectSubscriptionManager.1
            @Override // rx.functions.Action0
            public void call() {
                SubjectSubscriptionManager.this.remove(bo);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLatest(Object value) {
        this.latest = value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getLatest() {
        return this.latest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubjectObserver<T>[] observers() {
        return get().observers;
    }

    boolean add(SubjectObserver<T> o) {
        State oldState;
        do {
            oldState = get();
            if (oldState.terminated) {
                this.onTerminated.call(o);
                return false;
            }
        } while (!compareAndSet(oldState, oldState.add(o)));
        this.onAdded.call(o);
        return true;
    }

    void remove(SubjectObserver<T> o) {
        State oldState;
        State newState;
        do {
            oldState = get();
            if (oldState.terminated || (newState = oldState.remove(o)) == oldState) {
                return;
            }
        } while (!compareAndSet(oldState, newState));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubjectObserver<T>[] next(Object n) {
        setLatest(n);
        return get().observers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubjectObserver<T>[] terminate(Object n) {
        setLatest(n);
        this.active = false;
        return get().terminated ? State.NO_OBSERVERS : getAndSet(State.TERMINATED).observers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static final class State<T> {
        final SubjectObserver[] observers;
        final boolean terminated;
        static final SubjectObserver[] NO_OBSERVERS = new SubjectObserver[0];
        static final State TERMINATED = new State(true, NO_OBSERVERS);
        static final State EMPTY = new State(false, NO_OBSERVERS);

        public State(boolean terminated, SubjectObserver[] observers) {
            this.terminated = terminated;
            this.observers = observers;
        }

        public State add(SubjectObserver o) {
            int n = this.observers.length;
            SubjectObserver[] b = new SubjectObserver[n + 1];
            System.arraycopy(this.observers, 0, b, 0, n);
            b[n] = o;
            return new State(this.terminated, b);
        }

        public State remove(SubjectObserver o) {
            int j;
            SubjectObserver[] a = this.observers;
            int n = a.length;
            if (n == 1 && a[0] == o) {
                return EMPTY;
            }
            if (n == 0) {
                return this;
            }
            SubjectObserver[] b = new SubjectObserver[n - 1];
            int i = 0;
            int j2 = 0;
            while (i < n) {
                SubjectObserver ai = a[i];
                if (ai == o) {
                    j = j2;
                } else if (j2 == n - 1) {
                    return this;
                } else {
                    j = j2 + 1;
                    b[j2] = ai;
                }
                i++;
                j2 = j;
            }
            if (j2 == 0) {
                return EMPTY;
            }
            if (j2 < n - 1) {
                SubjectObserver[] c = new SubjectObserver[j2];
                System.arraycopy(b, 0, c, 0, j2);
                b = c;
            }
            return new State<>(this.terminated, b);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static final class SubjectObserver<T> implements Observer<T> {
        final Subscriber<? super T> actual;
        volatile boolean caughtUp;
        boolean emitting;
        boolean fastPath;
        boolean first = true;
        private volatile Object index;
        List<Object> queue;

        public SubjectObserver(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.actual.onNext(t);
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.actual.onCompleted();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void emitNext(Object n) {
            if (!this.fastPath) {
                synchronized (this) {
                    this.first = false;
                    if (this.emitting) {
                        if (this.queue == null) {
                            this.queue = new ArrayList();
                        }
                        this.queue.add(n);
                        return;
                    }
                    this.fastPath = true;
                }
            }
            NotificationLite.accept(this.actual, n);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void emitFirst(Object n) {
            boolean z = false;
            synchronized (this) {
                if (this.first && !this.emitting) {
                    this.first = false;
                    if (n != null) {
                        z = true;
                    }
                    this.emitting = z;
                    if (n != null) {
                        emitLoop(null, n);
                    }
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0034  */
        /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void emitLoop(java.util.List<java.lang.Object> r7, java.lang.Object r8) {
            /*
                r6 = this;
                r2 = 1
                r3 = 0
            L_0x0002:
                if (r7 == 0) goto L_0x001f
                java.util.Iterator r0 = r7.iterator()     // Catch: all -> 0x0016
            L_0x0008:
                boolean r4 = r0.hasNext()     // Catch: all -> 0x0016
                if (r4 == 0) goto L_0x001f
                java.lang.Object r1 = r0.next()     // Catch: all -> 0x0016
                r6.accept(r1)     // Catch: all -> 0x0016
                goto L_0x0008
            L_0x0016:
                r4 = move-exception
                if (r3 != 0) goto L_0x001e
                monitor-enter(r6)
                r5 = 0
                r6.emitting = r5     // Catch: all -> 0x0042
                monitor-exit(r6)     // Catch: all -> 0x0042
            L_0x001e:
                throw r4
            L_0x001f:
                if (r2 == 0) goto L_0x0025
                r2 = 0
                r6.accept(r8)     // Catch: all -> 0x0016
            L_0x0025:
                monitor-enter(r6)     // Catch: all -> 0x0016
                java.util.List<java.lang.Object> r7 = r6.queue     // Catch: all -> 0x003c
                r4 = 0
                r6.queue = r4     // Catch: all -> 0x003c
                if (r7 != 0) goto L_0x003a
                r4 = 0
                r6.emitting = r4     // Catch: all -> 0x003c
                r3 = 1
                monitor-exit(r6)     // Catch: all -> 0x003c
                if (r3 != 0) goto L_0x0039
                monitor-enter(r6)
                r4 = 0
                r6.emitting = r4     // Catch: all -> 0x003f
                monitor-exit(r6)     // Catch: all -> 0x003f
            L_0x0039:
                return
            L_0x003a:
                monitor-exit(r6)     // Catch: all -> 0x003c
                goto L_0x0002
            L_0x003c:
                r4 = move-exception
                monitor-exit(r6)     // Catch: all -> 0x003c
                throw r4     // Catch: all -> 0x0016
            L_0x003f:
                r4 = move-exception
                monitor-exit(r6)     // Catch: all -> 0x003f
                throw r4
            L_0x0042:
                r4 = move-exception
                monitor-exit(r6)     // Catch: all -> 0x0042
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.SubjectSubscriptionManager.SubjectObserver.emitLoop(java.util.List, java.lang.Object):void");
        }

        void accept(Object n) {
            if (n != null) {
                NotificationLite.accept(this.actual, n);
            }
        }

        Observer<? super T> getActual() {
            return this.actual;
        }

        public <I> I index() {
            return (I) this.index;
        }

        public void index(Object newIndex) {
            this.index = newIndex;
        }
    }
}

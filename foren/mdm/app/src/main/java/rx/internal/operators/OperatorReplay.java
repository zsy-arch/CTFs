package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.internal.util.OpenHashSet;
import rx.observables.ConnectableObservable;
import rx.schedulers.Timestamped;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
public final class OperatorReplay<T> extends ConnectableObservable<T> implements Subscription {
    static final Func0 DEFAULT_UNBOUNDED_FACTORY = new Func0() { // from class: rx.internal.operators.OperatorReplay.1
        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    };
    final Func0<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<ReplaySubscriber<T>> current;
    final Observable<? extends T> source;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerProducer<T> innerProducer);
    }

    public static <T, U, R> Observable<R> multicastSelector(final Func0<? extends ConnectableObservable<U>> connectableFactory, final Func1<? super Observable<U>, ? extends Observable<R>> selector) {
        return Observable.unsafeCreate(new Observable.OnSubscribe<R>() { // from class: rx.internal.operators.OperatorReplay.2
            public void call(final Subscriber<? super R> child) {
                try {
                    ConnectableObservable connectableObservable = (ConnectableObservable) Func0.this.call();
                    ((Observable) selector.call(connectableObservable)).subscribe((Subscriber) child);
                    connectableObservable.connect(new Action1<Subscription>() { // from class: rx.internal.operators.OperatorReplay.2.1
                        public void call(Subscription t) {
                            child.add(t);
                        }
                    });
                } catch (Throwable e) {
                    Exceptions.throwOrReport(e, child);
                }
            }
        });
    }

    public static <T> ConnectableObservable<T> observeOn(final ConnectableObservable<T> co, Scheduler scheduler) {
        final Observable<T> observable = co.observeOn(scheduler);
        return new ConnectableObservable<T>(new Observable.OnSubscribe<T>() { // from class: rx.internal.operators.OperatorReplay.3
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object x0) {
                call((Subscriber) ((Subscriber) x0));
            }

            public void call(final Subscriber<? super T> child) {
                Observable.this.unsafeSubscribe(new Subscriber<T>(child) { // from class: rx.internal.operators.OperatorReplay.3.1
                    @Override // rx.Observer
                    public void onNext(T t) {
                        child.onNext(t);
                    }

                    @Override // rx.Observer
                    public void onError(Throwable e) {
                        child.onError(e);
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        child.onCompleted();
                    }
                });
            }
        }) { // from class: rx.internal.operators.OperatorReplay.4
            @Override // rx.observables.ConnectableObservable
            public void connect(Action1<? super Subscription> connection) {
                co.connect(connection);
            }
        };
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source) {
        return create(source, DEFAULT_UNBOUNDED_FACTORY);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source, final int bufferSize) {
        return bufferSize == Integer.MAX_VALUE ? create(source) : create(source, new Func0<ReplayBuffer<T>>() { // from class: rx.internal.operators.OperatorReplay.5
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            public ReplayBuffer<T> call() {
                return new SizeBoundReplayBuffer(bufferSize);
            }
        });
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source, long maxAge, TimeUnit unit, Scheduler scheduler) {
        return create(source, maxAge, unit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> source, long maxAge, TimeUnit unit, final Scheduler scheduler, final int bufferSize) {
        final long maxAgeInMillis = unit.toMillis(maxAge);
        return create(source, new Func0<ReplayBuffer<T>>() { // from class: rx.internal.operators.OperatorReplay.6
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            public ReplayBuffer<T> call() {
                return new SizeAndTimeBoundReplayBuffer(bufferSize, maxAgeInMillis, scheduler);
            }
        });
    }

    static <T> ConnectableObservable<T> create(Observable<? extends T> source, final Func0<? extends ReplayBuffer<T>> bufferFactory) {
        final AtomicReference<ReplaySubscriber<T>> curr = new AtomicReference<>();
        return new OperatorReplay(new Observable.OnSubscribe<T>() { // from class: rx.internal.operators.OperatorReplay.7
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object x0) {
                call((Subscriber) ((Subscriber) x0));
            }

            public void call(Subscriber<? super T> child) {
                ReplaySubscriber<T> r;
                while (true) {
                    r = (ReplaySubscriber) curr.get();
                    if (r != null) {
                        break;
                    }
                    ReplaySubscriber<T> u2 = new ReplaySubscriber<>((ReplayBuffer) bufferFactory.call());
                    u2.init();
                    if (curr.compareAndSet(r, u2)) {
                        r = u2;
                        break;
                    }
                }
                InnerProducer<T> inner = new InnerProducer<>(r, child);
                r.add((InnerProducer) inner);
                child.add(inner);
                r.buffer.replay(inner);
                child.setProducer(inner);
            }
        }, source, curr, bufferFactory);
    }

    private OperatorReplay(Observable.OnSubscribe<T> onSubscribe, Observable<? extends T> source, AtomicReference<ReplaySubscriber<T>> current, Func0<? extends ReplayBuffer<T>> bufferFactory) {
        super(onSubscribe);
        this.source = source;
        this.current = current;
        this.bufferFactory = bufferFactory;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.current.lazySet(null);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        ReplaySubscriber<T> ps = this.current.get();
        return ps == null || ps.isUnsubscribed();
    }

    @Override // rx.observables.ConnectableObservable
    public void connect(Action1<? super Subscription> connection) {
        ReplaySubscriber<T> ps;
        boolean doConnect = true;
        while (true) {
            ps = this.current.get();
            if (ps != null && !ps.isUnsubscribed()) {
                break;
            }
            ReplaySubscriber<T> u2 = new ReplaySubscriber<>((ReplayBuffer) this.bufferFactory.call());
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
    public static final class ReplaySubscriber<T> extends Subscriber<T> implements Subscription {
        static final InnerProducer[] EMPTY = new InnerProducer[0];
        static final InnerProducer[] TERMINATED = new InnerProducer[0];
        final ReplayBuffer<T> buffer;
        boolean coordinateAll;
        List<InnerProducer<T>> coordinationQueue;
        boolean done;
        boolean emitting;
        long maxChildRequested;
        long maxUpstreamRequested;
        boolean missed;
        volatile Producer producer;
        long producersCacheVersion;
        volatile long producersVersion;
        volatile boolean terminated;
        final OpenHashSet<InnerProducer<T>> producers = new OpenHashSet<>();
        InnerProducer<T>[] producersCache = EMPTY;
        final AtomicBoolean shouldConnect = new AtomicBoolean();

        public ReplaySubscriber(ReplayBuffer<T> buffer) {
            this.buffer = buffer;
            request(0L);
        }

        void init() {
            add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorReplay.ReplaySubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    if (!ReplaySubscriber.this.terminated) {
                        synchronized (ReplaySubscriber.this.producers) {
                            if (!ReplaySubscriber.this.terminated) {
                                ReplaySubscriber.this.producers.terminate();
                                ReplaySubscriber.this.producersVersion++;
                                ReplaySubscriber.this.terminated = true;
                            }
                        }
                    }
                }
            }));
        }

        boolean add(InnerProducer<T> producer) {
            boolean z = false;
            if (producer == null) {
                throw new NullPointerException();
            }
            if (!this.terminated) {
                synchronized (this.producers) {
                    if (!this.terminated) {
                        this.producers.add(producer);
                        this.producersVersion++;
                        z = true;
                    }
                }
            }
            return z;
        }

        void remove(InnerProducer<T> producer) {
            if (!this.terminated) {
                synchronized (this.producers) {
                    if (!this.terminated) {
                        this.producers.remove(producer);
                        if (this.producers.isEmpty()) {
                            this.producersCache = EMPTY;
                        }
                        this.producersVersion++;
                    }
                }
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) {
            if (this.producer != null) {
                throw new IllegalStateException("Only a single producer can be set on a Subscriber.");
            }
            this.producer = p;
            manageRequests(null);
            replay();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                replay();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (!this.done) {
                this.done = true;
                try {
                    this.buffer.error(e);
                    replay();
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                try {
                    this.buffer.complete();
                    replay();
                } finally {
                    unsubscribe();
                }
            }
        }

        void manageRequests(InnerProducer<T> inner) {
            long maxTotalRequested;
            List<InnerProducer<T>> q;
            boolean all;
            if (!isUnsubscribed()) {
                synchronized (this) {
                    if (this.emitting) {
                        if (inner != null) {
                            List<InnerProducer<T>> q2 = this.coordinationQueue;
                            if (q2 == null) {
                                q2 = new ArrayList<>();
                                this.coordinationQueue = q2;
                            }
                            q2.add(inner);
                        } else {
                            this.coordinateAll = true;
                        }
                        this.missed = true;
                        return;
                    }
                    this.emitting = true;
                    long ri = this.maxChildRequested;
                    if (inner != null) {
                        maxTotalRequested = Math.max(ri, inner.totalRequested.get());
                    } else {
                        maxTotalRequested = ri;
                        InnerProducer<T>[] a = copyProducers();
                        for (InnerProducer<T> rp : a) {
                            if (rp != null) {
                                maxTotalRequested = Math.max(maxTotalRequested, rp.totalRequested.get());
                            }
                        }
                    }
                    makeRequest(maxTotalRequested, ri);
                    while (!isUnsubscribed()) {
                        synchronized (this) {
                            if (!this.missed) {
                                this.emitting = false;
                                return;
                            }
                            this.missed = false;
                            q = this.coordinationQueue;
                            this.coordinationQueue = null;
                            all = this.coordinateAll;
                            this.coordinateAll = false;
                        }
                        long ri2 = this.maxChildRequested;
                        long maxTotalRequested2 = ri2;
                        if (q != null) {
                            for (InnerProducer<T> rp2 : q) {
                                maxTotalRequested2 = Math.max(maxTotalRequested2, rp2.totalRequested.get());
                            }
                        }
                        if (all) {
                            InnerProducer<T>[] a2 = copyProducers();
                            for (InnerProducer<T> rp3 : a2) {
                                if (rp3 != null) {
                                    maxTotalRequested2 = Math.max(maxTotalRequested2, rp3.totalRequested.get());
                                }
                            }
                        }
                        makeRequest(maxTotalRequested2, ri2);
                    }
                }
            }
        }

        InnerProducer<T>[] copyProducers() {
            InnerProducer<T>[] result;
            synchronized (this.producers) {
                Object[] a = this.producers.values();
                int n = a.length;
                result = new InnerProducer[n];
                System.arraycopy(a, 0, result, 0, n);
            }
            return result;
        }

        void makeRequest(long maxTotalRequests, long previousTotalRequests) {
            long ur = this.maxUpstreamRequested;
            Producer p = this.producer;
            long diff = maxTotalRequests - previousTotalRequests;
            if (diff != 0) {
                this.maxChildRequested = maxTotalRequests;
                if (p == null) {
                    long u2 = ur + diff;
                    if (u2 < 0) {
                        u2 = Long.MAX_VALUE;
                    }
                    this.maxUpstreamRequested = u2;
                } else if (ur != 0) {
                    this.maxUpstreamRequested = 0L;
                    p.request(ur + diff);
                } else {
                    p.request(diff);
                }
            } else if (ur != 0 && p != null) {
                this.maxUpstreamRequested = 0L;
                p.request(ur);
            }
        }

        void replay() {
            InnerProducer<T>[] pc = this.producersCache;
            if (this.producersCacheVersion != this.producersVersion) {
                synchronized (this.producers) {
                    pc = this.producersCache;
                    Object[] a = this.producers.values();
                    int n = a.length;
                    if (pc.length != n) {
                        pc = new InnerProducer[n];
                        this.producersCache = pc;
                    }
                    System.arraycopy(a, 0, pc, 0, n);
                    this.producersCacheVersion = this.producersVersion;
                }
            }
            ReplayBuffer<T> b = this.buffer;
            for (InnerProducer<T> rp : pc) {
                if (rp != null) {
                    b.replay(rp);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class InnerProducer<T> extends AtomicLong implements Producer, Subscription {
        static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        public InnerProducer(ReplaySubscriber<T> parent, Subscriber<? super T> child) {
            this.parent = parent;
            this.child = child;
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
                    if (r < 0 || n != 0) {
                        u2 = r + n;
                        if (u2 < 0) {
                            u2 = Long.MAX_VALUE;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(r, u2));
                addTotalRequested(n);
                this.parent.manageRequests(this);
                this.parent.buffer.replay(this);
            }
        }

        void addTotalRequested(long n) {
            long r;
            long u2;
            do {
                r = this.totalRequested.get();
                u2 = r + n;
                if (u2 < 0) {
                    u2 = Long.MAX_VALUE;
                }
            } while (!this.totalRequested.compareAndSet(r, u2));
        }

        public long produced(long n) {
            long r;
            long u2;
            if (n <= 0) {
                throw new IllegalArgumentException("Cant produce zero or less");
            }
            do {
                r = get();
                if (r == UNSUBSCRIBED) {
                    return UNSUBSCRIBED;
                }
                u2 = r - n;
                if (u2 < 0) {
                    throw new IllegalStateException("More produced (" + n + ") than requested (" + r + ")");
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
                this.parent.manageRequests(this);
                this.child = null;
            }
        }

        <U> U index() {
            return (U) this.index;
        }
    }

    /* loaded from: classes2.dex */
    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        public UnboundedReplayBuffer(int capacityHint) {
            super(capacityHint);
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void next(T value) {
            add(NotificationLite.next(value));
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void error(Throwable e) {
            add(NotificationLite.error(e));
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void complete() {
            add(NotificationLite.completed());
            this.size++;
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public void replay(InnerProducer<T> output) {
            synchronized (output) {
                if (output.emitting) {
                    output.missed = true;
                    return;
                }
                output.emitting = true;
                while (!output.isUnsubscribed()) {
                    int sourceIndex = this.size;
                    Integer destinationIndexObject = (Integer) output.index();
                    int destinationIndex = destinationIndexObject != null ? destinationIndexObject.intValue() : 0;
                    Subscriber<? super T> child = output.child;
                    if (child != null) {
                        long r = output.get();
                        long e = 0;
                        while (e != r && destinationIndex < sourceIndex) {
                            Object o = get(destinationIndex);
                            try {
                                if (!NotificationLite.accept(child, o) && !output.isUnsubscribed()) {
                                    destinationIndex++;
                                    e++;
                                } else {
                                    return;
                                }
                            } catch (Throwable err) {
                                Exceptions.throwIfFatal(err);
                                output.unsubscribe();
                                if (!NotificationLite.isError(o) && !NotificationLite.isCompleted(o)) {
                                    child.onError(OnErrorThrowable.addValueAsLastCause(err, NotificationLite.getValue(o)));
                                    return;
                                }
                                return;
                            }
                        }
                        if (e != 0) {
                            output.index = Integer.valueOf(destinationIndex);
                            if (r != Long.MAX_VALUE) {
                                output.produced(e);
                            }
                        }
                        synchronized (output) {
                            if (!output.missed) {
                                output.emitting = false;
                                return;
                            }
                            output.missed = false;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        public Node(Object value, long index) {
            this.value = value;
            this.index = index;
        }
    }

    /* loaded from: classes2.dex */
    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        int size;
        Node tail;

        public BoundedReplayBuffer() {
            Node n = new Node(null, 0L);
            this.tail = n;
            set(n);
        }

        final void addLast(Node n) {
            this.tail.set(n);
            this.tail = n;
            this.size++;
        }

        final void removeFirst() {
            Node next = get().get();
            if (next == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.size--;
            setFirst(next);
        }

        final void removeSome(int n) {
            Node head = get();
            while (n > 0) {
                head = head.get();
                n--;
                this.size--;
            }
            setFirst(head);
        }

        final void setFirst(Node n) {
            set(n);
        }

        Node getInitialHead() {
            return get();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void next(T value) {
            Object o = enterTransform(NotificationLite.next(value));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncate();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void error(Throwable e) {
            Object o = enterTransform(NotificationLite.error(e));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncateFinal();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void complete() {
            Object o = enterTransform(NotificationLite.completed());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncateFinal();
        }

        @Override // rx.internal.operators.OperatorReplay.ReplayBuffer
        public final void replay(InnerProducer<T> output) {
            Subscriber<? super T> child;
            Node v;
            synchronized (output) {
                if (output.emitting) {
                    output.missed = true;
                    return;
                }
                output.emitting = true;
                while (!output.isUnsubscribed()) {
                    Node node = (Node) output.index();
                    if (node == null) {
                        node = getInitialHead();
                        output.index = node;
                        output.addTotalRequested(node.index);
                    }
                    if (!output.isUnsubscribed() && (child = output.child) != null) {
                        long r = output.get();
                        long e = 0;
                        while (e != r && (v = node.get()) != null) {
                            Object o = leaveTransform(v.value);
                            try {
                                if (NotificationLite.accept(child, o)) {
                                    output.index = null;
                                    return;
                                }
                                e++;
                                node = v;
                                if (output.isUnsubscribed()) {
                                    return;
                                }
                            } catch (Throwable err) {
                                output.index = null;
                                Exceptions.throwIfFatal(err);
                                output.unsubscribe();
                                if (!NotificationLite.isError(o) && !NotificationLite.isCompleted(o)) {
                                    child.onError(OnErrorThrowable.addValueAsLastCause(err, NotificationLite.getValue(o)));
                                    return;
                                }
                                return;
                            }
                        }
                        if (e != 0) {
                            output.index = node;
                            if (r != Long.MAX_VALUE) {
                                output.produced(e);
                            }
                        }
                        synchronized (output) {
                            if (!output.missed) {
                                output.emitting = false;
                                return;
                            }
                            output.missed = false;
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        Object enterTransform(Object value) {
            return value;
        }

        Object leaveTransform(Object value) {
            return value;
        }

        void truncate() {
        }

        void truncateFinal() {
        }

        final void collect(Collection<? super T> output) {
            Node n = getInitialHead();
            while (true) {
                Node next = n.get();
                if (next != null) {
                    Object v = leaveTransform(next.value);
                    if (!NotificationLite.isCompleted(v) && !NotificationLite.isError(v)) {
                        output.add((Object) NotificationLite.getValue(v));
                        n = next;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isCompleted(leaveTransform(this.tail.value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        public SizeBoundReplayBuffer(int limit) {
            this.limit = limit;
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAgeInMillis;
        final Scheduler scheduler;

        public SizeAndTimeBoundReplayBuffer(int limit, long maxAgeInMillis, Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = limit;
            this.maxAgeInMillis = maxAgeInMillis;
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Object enterTransform(Object value) {
            return new Timestamped(this.scheduler.now(), value);
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Object leaveTransform(Object value) {
            return ((Timestamped) value).getValue();
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        Node getInitialHead() {
            long timeLimit = this.scheduler.now() - this.maxAgeInMillis;
            Node prev = (Node) get();
            for (Node next = prev.get(); next != null; next = next.get()) {
                Object o = next.value;
                Object v = leaveTransform(o);
                if (NotificationLite.isCompleted(v) || NotificationLite.isError(v) || ((Timestamped) o).getTimestampMillis() > timeLimit) {
                    break;
                }
                prev = next;
            }
            return prev;
        }

        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        void truncate() {
            long timeLimit = this.scheduler.now() - this.maxAgeInMillis;
            Node prev = (Node) get();
            Node next = prev.get();
            int e = 0;
            while (next != null) {
                if (this.size <= this.limit) {
                    if (((Timestamped) next.value).getTimestampMillis() > timeLimit) {
                        break;
                    }
                    e++;
                    this.size--;
                    prev = next;
                    next = next.get();
                } else {
                    e++;
                    this.size--;
                    prev = next;
                    next = next.get();
                }
            }
            if (e != 0) {
                setFirst(prev);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x003c, code lost:
            setFirst(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x003f, code lost:
            return;
         */
        @Override // rx.internal.operators.OperatorReplay.BoundedReplayBuffer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void truncateFinal() {
            /*
                r10 = this;
                rx.Scheduler r6 = r10.scheduler
                long r6 = r6.now()
                long r8 = r10.maxAgeInMillis
                long r4 = r6 - r8
                java.lang.Object r2 = r10.get()
                rx.internal.operators.OperatorReplay$Node r2 = (rx.internal.operators.OperatorReplay.Node) r2
                java.lang.Object r1 = r2.get()
                rx.internal.operators.OperatorReplay$Node r1 = (rx.internal.operators.OperatorReplay.Node) r1
                r0 = 0
            L_0x0017:
                if (r1 == 0) goto L_0x003a
                int r6 = r10.size
                r7 = 1
                if (r6 <= r7) goto L_0x003a
                java.lang.Object r3 = r1.value
                rx.schedulers.Timestamped r3 = (rx.schedulers.Timestamped) r3
                long r6 = r3.getTimestampMillis()
                int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
                if (r6 > 0) goto L_0x003a
                int r0 = r0 + 1
                int r6 = r10.size
                int r6 = r6 + (-1)
                r10.size = r6
                r2 = r1
                java.lang.Object r1 = r1.get()
                rx.internal.operators.OperatorReplay$Node r1 = (rx.internal.operators.OperatorReplay.Node) r1
                goto L_0x0017
            L_0x003a:
                if (r0 == 0) goto L_0x003f
                r10.setFirst(r2)
            L_0x003f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorReplay.SizeAndTimeBoundReplayBuffer.truncateFinal():void");
        }
    }
}

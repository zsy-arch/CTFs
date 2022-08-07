package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes2.dex */
public final class OperatorMerge<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayErrors;
    final int maxConcurrent;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class HolderNoDelay {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(false, Integer.MAX_VALUE);

        HolderNoDelay() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class HolderDelayErrors {
        static final OperatorMerge<Object> INSTANCE = new OperatorMerge<>(true, Integer.MAX_VALUE);

        HolderDelayErrors() {
        }
    }

    public static <T> OperatorMerge<T> instance(boolean delayErrors) {
        return delayErrors ? (OperatorMerge<T>) HolderDelayErrors.INSTANCE : (OperatorMerge<T>) HolderNoDelay.INSTANCE;
    }

    public static <T> OperatorMerge<T> instance(boolean delayErrors, int maxConcurrent) {
        if (maxConcurrent > 0) {
            return maxConcurrent == Integer.MAX_VALUE ? instance(delayErrors) : new OperatorMerge<>(delayErrors, maxConcurrent);
        }
        throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + maxConcurrent);
    }

    OperatorMerge(boolean delayErrors, int maxConcurrent) {
        this.delayErrors = delayErrors;
        this.maxConcurrent = maxConcurrent;
    }

    public Subscriber<Observable<? extends T>> call(Subscriber<? super T> child) {
        MergeSubscriber<T> subscriber = new MergeSubscriber<>(child, this.delayErrors, this.maxConcurrent);
        MergeProducer<T> producer = new MergeProducer<>(subscriber);
        subscriber.producer = producer;
        child.add(subscriber);
        child.setProducer(producer);
        return subscriber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class MergeProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -1214379189873595503L;
        final MergeSubscriber<T> subscriber;

        public MergeProducer(MergeSubscriber<T> subscriber) {
            this.subscriber = subscriber;
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n > 0) {
                if (get() != Long.MAX_VALUE) {
                    BackpressureUtils.getAndAddRequest(this, n);
                    this.subscriber.emit();
                }
            } else if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        }

        public long produced(int n) {
            return addAndGet(-n);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final InnerSubscriber<?>[] EMPTY = new InnerSubscriber[0];
        final Subscriber<? super T> child;
        final boolean delayErrors;
        volatile boolean done;
        boolean emitting;
        volatile ConcurrentLinkedQueue<Throwable> errors;
        final Object innerGuard = new Object();
        volatile InnerSubscriber<?>[] innerSubscribers = EMPTY;
        long lastId;
        int lastIndex;
        final int maxConcurrent;
        boolean missed;
        MergeProducer<T> producer;
        volatile Queue<Object> queue;
        int scalarEmissionCount;
        final int scalarEmissionLimit;
        volatile CompositeSubscription subscriptions;
        long uniqueId;

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object x0) {
            onNext((Observable) ((Observable) x0));
        }

        public MergeSubscriber(Subscriber<? super T> child, boolean delayErrors, int maxConcurrent) {
            this.child = child;
            this.delayErrors = delayErrors;
            this.maxConcurrent = maxConcurrent;
            if (maxConcurrent == Integer.MAX_VALUE) {
                this.scalarEmissionLimit = Integer.MAX_VALUE;
                request(Long.MAX_VALUE);
                return;
            }
            this.scalarEmissionLimit = Math.max(1, maxConcurrent >> 1);
            request(maxConcurrent);
        }

        Queue<Throwable> getOrCreateErrorQueue() {
            Throwable th;
            ConcurrentLinkedQueue<Throwable> q = this.errors;
            if (q == null) {
                try {
                    synchronized (this) {
                        q = this.errors;
                        if (q == null) {
                            ConcurrentLinkedQueue<Throwable> q2 = new ConcurrentLinkedQueue<>();
                            try {
                                this.errors = q2;
                                q = q2;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            return q;
        }

        CompositeSubscription getOrCreateComposite() {
            Throwable th;
            CompositeSubscription c = this.subscriptions;
            if (c == null) {
                boolean shouldAdd = false;
                try {
                    synchronized (this) {
                        c = this.subscriptions;
                        if (c == null) {
                            CompositeSubscription c2 = new CompositeSubscription();
                            try {
                                this.subscriptions = c2;
                                shouldAdd = true;
                                c = c2;
                            } catch (Throwable th2) {
                                th = th2;
                                throw th;
                            }
                        }
                        if (shouldAdd) {
                            add(c);
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            return c;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void onNext(Observable<? extends T> t) {
            if (t != null) {
                if (t == Observable.empty()) {
                    emitEmpty();
                } else if (t instanceof ScalarSynchronousObservable) {
                    tryEmit(((ScalarSynchronousObservable) t).get());
                } else {
                    long j = this.uniqueId;
                    this.uniqueId = 1 + j;
                    InnerSubscriber<T> inner = new InnerSubscriber<>(this, j);
                    addInner(inner);
                    t.unsafeSubscribe(inner);
                    emit();
                }
            }
        }

        void emitEmpty() {
            int produced = this.scalarEmissionCount + 1;
            if (produced == this.scalarEmissionLimit) {
                this.scalarEmissionCount = 0;
                requestMore(produced);
                return;
            }
            this.scalarEmissionCount = produced;
        }

        private void reportError() {
            List<Throwable> list = new ArrayList<>(this.errors);
            if (list.size() == 1) {
                this.child.onError(list.get(0));
            } else {
                this.child.onError(new CompositeException(list));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            getOrCreateErrorQueue().offer(e);
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        /* JADX WARN: Multi-variable type inference failed */
        void addInner(InnerSubscriber<T> inner) {
            getOrCreateComposite().add(inner);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] a = this.innerSubscribers;
                int n = a.length;
                InnerSubscriber<?>[] b = new InnerSubscriber[n + 1];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = inner;
                this.innerSubscribers = b;
            }
        }

        void removeInner(InnerSubscriber<T> inner) {
            RxRingBuffer q = inner.queue;
            if (q != null) {
                q.release();
            }
            this.subscriptions.remove(inner);
            synchronized (this.innerGuard) {
                InnerSubscriber<?>[] a = this.innerSubscribers;
                int n = a.length;
                int j = -1;
                int i = 0;
                while (true) {
                    if (i >= n) {
                        break;
                    } else if (inner.equals(a[i])) {
                        j = i;
                        break;
                    } else {
                        i++;
                    }
                }
                if (j >= 0) {
                    if (n == 1) {
                        this.innerSubscribers = EMPTY;
                        return;
                    }
                    InnerSubscriber<?>[] b = new InnerSubscriber[n - 1];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                    this.innerSubscribers = b;
                }
            }
        }

        void tryEmit(InnerSubscriber<T> subscriber, T value) {
            boolean success = false;
            long r = this.producer.get();
            if (r != 0) {
                synchronized (this) {
                    r = this.producer.get();
                    if (!this.emitting && r != 0) {
                        this.emitting = true;
                        success = true;
                    }
                }
            }
            if (success) {
                RxRingBuffer subscriberQueue = subscriber.queue;
                if (subscriberQueue == null || subscriberQueue.isEmpty()) {
                    emitScalar(subscriber, value, r);
                    return;
                }
                queueScalar(subscriber, value);
                emitLoop();
                return;
            }
            queueScalar(subscriber, value);
            emit();
        }

        protected void queueScalar(InnerSubscriber<T> subscriber, T value) {
            RxRingBuffer q = subscriber.queue;
            if (q == null) {
                q = RxRingBuffer.getSpscInstance();
                subscriber.add(q);
                subscriber.queue = q;
            }
            try {
                q.onNext(NotificationLite.next(value));
            } catch (IllegalStateException ex) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.unsubscribe();
                    subscriber.onError(ex);
                }
            } catch (MissingBackpressureException ex2) {
                subscriber.unsubscribe();
                subscriber.onError(ex2);
            }
        }

        protected void emitScalar(InnerSubscriber<T> subscriber, T value, long r) {
            try {
                this.child.onNext(value);
                if (r != Long.MAX_VALUE) {
                    this.producer.produced(1);
                }
                subscriber.requestMore(1L);
                synchronized (this) {
                    if (!this.missed) {
                        this.emitting = false;
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
                    }
                    this.missed = false;
                    if (1 == 0) {
                        synchronized (this) {
                            try {
                                this.emitting = false;
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    }
                    emitLoop();
                }
            } catch (Throwable th3) {
                if (0 == 0) {
                    synchronized (this) {
                        try {
                            this.emitting = false;
                        } catch (Throwable th4) {
                            throw th4;
                        }
                    }
                }
                throw th3;
            }
        }

        public void requestMore(long n) {
            request(n);
        }

        void tryEmit(T value) {
            boolean success = false;
            long r = this.producer.get();
            if (r != 0) {
                synchronized (this) {
                    r = this.producer.get();
                    if (!this.emitting && r != 0) {
                        this.emitting = true;
                        success = true;
                    }
                }
            }
            if (success) {
                Queue<Object> mainQueue = this.queue;
                if (mainQueue == null || mainQueue.isEmpty()) {
                    emitScalar(value, r);
                    return;
                }
                queueScalar(value);
                emitLoop();
                return;
            }
            queueScalar(value);
            emit();
        }

        protected void queueScalar(T value) {
            Queue<Object> q = this.queue;
            if (q == null) {
                int mc = this.maxConcurrent;
                if (mc == Integer.MAX_VALUE) {
                    q = new SpscUnboundedAtomicArrayQueue<>(RxRingBuffer.SIZE);
                } else if (!Pow2.isPowerOfTwo(mc)) {
                    q = new SpscExactAtomicArrayQueue<>(mc);
                } else if (UnsafeAccess.isUnsafeAvailable()) {
                    q = new SpscArrayQueue<>(mc);
                } else {
                    q = new SpscAtomicArrayQueue<>(mc);
                }
                this.queue = q;
            }
            if (!q.offer(NotificationLite.next(value))) {
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), value));
            }
        }

        protected void emitScalar(T value, long r) {
            try {
                this.child.onNext(value);
                if (r != Long.MAX_VALUE) {
                    this.producer.produced(1);
                }
                int produced = this.scalarEmissionCount + 1;
                if (produced == this.scalarEmissionLimit) {
                    this.scalarEmissionCount = 0;
                    requestMore(produced);
                } else {
                    this.scalarEmissionCount = produced;
                }
                synchronized (this) {
                    if (!this.missed) {
                        this.emitting = false;
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
                    }
                    this.missed = false;
                    if (1 == 0) {
                        synchronized (this) {
                            try {
                                this.emitting = false;
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                    }
                    emitLoop();
                }
            } catch (Throwable th3) {
                if (0 == 0) {
                    synchronized (this) {
                        try {
                            this.emitting = false;
                        } catch (Throwable th4) {
                            throw th4;
                        }
                    }
                }
                throw th3;
            }
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
            Object o;
            try {
                Subscriber<? super T> child = this.child;
                while (!checkTerminate()) {
                    Queue<Object> svq = this.queue;
                    long r = this.producer.get();
                    boolean unbounded = r == Long.MAX_VALUE;
                    int replenishMain = 0;
                    if (svq != null) {
                        do {
                            int scalarEmission = 0;
                            o = null;
                            while (r > 0) {
                                o = svq.poll();
                                if (checkTerminate()) {
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
                                } else if (o == null) {
                                    break;
                                } else {
                                    child.onNext((Object) NotificationLite.getValue(o));
                                    replenishMain++;
                                    scalarEmission++;
                                    r--;
                                }
                            }
                            if (scalarEmission > 0) {
                                if (unbounded) {
                                    r = Long.MAX_VALUE;
                                } else {
                                    r = this.producer.produced(scalarEmission);
                                }
                            }
                            if (r == 0) {
                                break;
                            }
                        } while (o != null);
                    }
                    boolean d = this.done;
                    Queue<Object> svq2 = this.queue;
                    InnerSubscriber<?>[] inner = this.innerSubscribers;
                    int n = inner.length;
                    if (!d || ((svq2 != null && !svq2.isEmpty()) || n != 0)) {
                        boolean innerCompleted = false;
                        if (n > 0) {
                            long startId = this.lastId;
                            int index = this.lastIndex;
                            if (n <= index || inner[index].id != startId) {
                                if (n <= index) {
                                    index = 0;
                                }
                                int j = index;
                                for (int i = 0; i < n && inner[j].id != startId; i++) {
                                    j++;
                                    if (j == n) {
                                        j = 0;
                                    }
                                }
                                index = j;
                                this.lastIndex = j;
                                this.lastId = inner[j].id;
                            }
                            int j2 = index;
                            for (int i2 = 0; i2 < n; i2++) {
                                if (!checkTerminate()) {
                                    InnerSubscriber<?> innerSubscriber = inner[j2];
                                    Object o2 = null;
                                    do {
                                        int produced = 0;
                                        while (r > 0) {
                                            if (!checkTerminate()) {
                                                RxRingBuffer q = innerSubscriber.queue;
                                                if (q == null || (o2 = q.poll()) == null) {
                                                    break;
                                                }
                                                child.onNext((Object) NotificationLite.getValue(o2));
                                                r--;
                                                produced++;
                                            } else if (1 == 0) {
                                                synchronized (this) {
                                                    try {
                                                        this.emitting = false;
                                                    } catch (Throwable th2) {
                                                        throw th2;
                                                    }
                                                }
                                                return;
                                            } else {
                                                return;
                                            }
                                        }
                                        if (produced > 0) {
                                            if (!unbounded) {
                                                r = this.producer.produced(produced);
                                            } else {
                                                r = Long.MAX_VALUE;
                                            }
                                            innerSubscriber.requestMore(produced);
                                        }
                                        if (r == 0) {
                                            break;
                                        }
                                    } while (o2 != null);
                                    boolean innerDone = innerSubscriber.done;
                                    RxRingBuffer innerQueue = innerSubscriber.queue;
                                    if (innerDone && (innerQueue == null || innerQueue.isEmpty())) {
                                        removeInner(innerSubscriber);
                                        if (!checkTerminate()) {
                                            replenishMain++;
                                            innerCompleted = true;
                                        } else if (1 == 0) {
                                            synchronized (this) {
                                                try {
                                                    this.emitting = false;
                                                } catch (Throwable th3) {
                                                    throw th3;
                                                }
                                            }
                                            return;
                                        } else {
                                            return;
                                        }
                                    }
                                    if (r == 0) {
                                        break;
                                    }
                                    j2++;
                                    if (j2 == n) {
                                        j2 = 0;
                                    }
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
                            this.lastIndex = j2;
                            this.lastId = inner[j2].id;
                        }
                        if (replenishMain > 0) {
                            request(replenishMain);
                        }
                        if (!innerCompleted) {
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
                                    } catch (Throwable th5) {
                                        throw th5;
                                    }
                                }
                                return;
                            }
                            return;
                        }
                    } else {
                        Queue<Throwable> e = this.errors;
                        if (e == null || e.isEmpty()) {
                            child.onCompleted();
                        } else {
                            reportError();
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
                    }
                }
                if (1 == 0) {
                    synchronized (this) {
                        try {
                            this.emitting = false;
                        } catch (Throwable th7) {
                            throw th7;
                        }
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
        }

        boolean checkTerminate() {
            if (this.child.isUnsubscribed()) {
                return true;
            }
            Queue<Throwable> e = this.errors;
            if (this.delayErrors || e == null || e.isEmpty()) {
                return false;
            }
            try {
                reportError();
                return true;
            } finally {
                unsubscribe();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class InnerSubscriber<T> extends Subscriber<T> {
        static final int LIMIT = RxRingBuffer.SIZE / 4;
        volatile boolean done;
        final long id;
        int outstanding;
        final MergeSubscriber<T> parent;
        volatile RxRingBuffer queue;

        public InnerSubscriber(MergeSubscriber<T> parent, long id) {
            this.parent = parent;
            this.id = id;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            this.outstanding = RxRingBuffer.SIZE;
            request(RxRingBuffer.SIZE);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.parent.tryEmit(this, t);
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.done = true;
            this.parent.getOrCreateErrorQueue().offer(e);
            this.parent.emit();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            this.parent.emit();
        }

        public void requestMore(long n) {
            int r = this.outstanding - ((int) n);
            if (r > LIMIT) {
                this.outstanding = r;
                return;
            }
            this.outstanding = RxRingBuffer.SIZE;
            int k = RxRingBuffer.SIZE - r;
            if (k > 0) {
                request(k);
            }
        }
    }
}

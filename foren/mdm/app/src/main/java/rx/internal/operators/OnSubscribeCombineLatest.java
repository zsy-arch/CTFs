package rx.internal.operators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.FuncN;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class OnSubscribeCombineLatest<T, R> implements Observable.OnSubscribe<R> {
    final int bufferSize;
    final FuncN<? extends R> combiner;
    final boolean delayError;
    final Observable<? extends T>[] sources;
    final Iterable<? extends Observable<? extends T>> sourcesIterable;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeCombineLatest(Iterable<? extends Observable<? extends T>> sourcesIterable, FuncN<? extends R> combiner) {
        this(null, sourcesIterable, combiner, RxRingBuffer.SIZE, false);
    }

    public OnSubscribeCombineLatest(Observable<? extends T>[] sources, Iterable<? extends Observable<? extends T>> sourcesIterable, FuncN<? extends R> combiner, int bufferSize, boolean delayError) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
        this.combiner = combiner;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    public void call(Subscriber<? super R> s) {
        Observable<? extends T>[] sources = this.sources;
        int count = 0;
        if (sources != null) {
            count = sources.length;
        } else if (this.sourcesIterable instanceof List) {
            List list = (List) this.sourcesIterable;
            sources = (Observable[]) list.toArray(new Observable[list.size()]);
            count = sources.length;
        } else {
            sources = new Observable[8];
            for (Observable<? extends T> p : this.sourcesIterable) {
                if (count == sources.length) {
                    Observable<? extends T>[] b = new Observable[(count >> 2) + count];
                    System.arraycopy(sources, 0, b, 0, count);
                    sources = b;
                }
                sources[count] = p;
                count++;
            }
        }
        if (count == 0) {
            s.onCompleted();
        } else {
            new LatestCoordinator<>(s, this.combiner, count, this.bufferSize, this.delayError).subscribe(sources);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class LatestCoordinator<T, R> extends AtomicInteger implements Producer, Subscription {
        static final Object MISSING = new Object();
        private static final long serialVersionUID = 8567835998786448817L;
        int active;
        final Subscriber<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final FuncN<? extends R> combiner;
        int complete;
        final boolean delayError;
        volatile boolean done;
        final Object[] latest;
        final SpscLinkedArrayQueue<Object> queue;
        final CombinerSubscriber<T, R>[] subscribers;
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<Throwable> error = new AtomicReference<>();

        public LatestCoordinator(Subscriber<? super R> actual, FuncN<? extends R> combiner, int count, int bufferSize, boolean delayError) {
            this.actual = actual;
            this.combiner = combiner;
            this.bufferSize = bufferSize;
            this.delayError = delayError;
            this.latest = new Object[count];
            Arrays.fill(this.latest, MISSING);
            this.subscribers = new CombinerSubscriber[count];
            this.queue = new SpscLinkedArrayQueue<>(bufferSize);
        }

        public void subscribe(Observable<? extends T>[] sources) {
            CombinerSubscriber<T, R>[] combinerSubscriberArr = this.subscribers;
            int len = combinerSubscriberArr.length;
            for (int i = 0; i < len; i++) {
                combinerSubscriberArr[i] = new CombinerSubscriber<>(this, i);
            }
            lazySet(0);
            this.actual.add(this);
            this.actual.setProducer(this);
            for (int i2 = 0; i2 < len && !this.cancelled; i2++) {
                sources[i2].subscribe((Subscriber<? super Object>) combinerSubscriberArr[i2]);
            }
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= required but it was " + n);
            } else if (n != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                drain();
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (!this.cancelled) {
                this.cancelled = true;
                if (getAndIncrement() == 0) {
                    cancel(this.queue);
                }
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancelled;
        }

        void cancel(Queue<?> q) {
            q.clear();
            for (CombinerSubscriber<T, R> s : this.subscribers) {
                s.unsubscribe();
            }
        }

        void combine(Object value, int index) {
            boolean allSourcesFinished;
            boolean empty = false;
            CombinerSubscriber<T, R> combinerSubscriber = this.subscribers[index];
            synchronized (this) {
                int sourceCount = this.latest.length;
                Object o = this.latest[index];
                int activeCount = this.active;
                if (o == MISSING) {
                    activeCount++;
                    this.active = activeCount;
                }
                int completedCount = this.complete;
                if (value == null) {
                    completedCount++;
                    this.complete = completedCount;
                } else {
                    this.latest[index] = NotificationLite.getValue(value);
                }
                allSourcesFinished = activeCount == sourceCount;
                if (completedCount == sourceCount || (value == null && o == MISSING)) {
                    empty = true;
                }
                if (empty) {
                    this.done = true;
                } else if (value != null && allSourcesFinished) {
                    this.queue.offer(combinerSubscriber, this.latest.clone());
                } else if (value == null && this.error.get() != null && (o == MISSING || !this.delayError)) {
                    this.done = true;
                }
            }
            if (allSourcesFinished || value == null) {
                drain();
            } else {
                combinerSubscriber.requestMore(1L);
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Queue<Object> q = this.queue;
                Subscriber<? super R> a = this.actual;
                boolean delayError = this.delayError;
                AtomicLong localRequested = this.requested;
                int missed = 1;
                while (!checkTerminated(this.done, q.isEmpty(), a, q, delayError)) {
                    long requestAmount = localRequested.get();
                    long emitted = 0;
                    while (emitted != requestAmount) {
                        boolean d = this.done;
                        CombinerSubscriber<T, R> cs = (CombinerSubscriber) q.peek();
                        boolean empty = cs == null;
                        if (checkTerminated(d, empty, a, q, delayError)) {
                            return;
                        }
                        if (empty) {
                            break;
                        }
                        q.poll();
                        Object[] array = (Object[]) q.poll();
                        if (array == null) {
                            this.cancelled = true;
                            cancel(q);
                            a.onError(new IllegalStateException("Broken queue?! Sender received but not the array."));
                            return;
                        }
                        try {
                            a.onNext((Object) this.combiner.call(array));
                            cs.requestMore(1L);
                            emitted++;
                        } catch (Throwable ex) {
                            this.cancelled = true;
                            cancel(q);
                            a.onError(ex);
                            return;
                        }
                    }
                    if (!(emitted == 0 || requestAmount == Long.MAX_VALUE)) {
                        BackpressureUtils.produced(localRequested, emitted);
                    }
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
        }

        boolean checkTerminated(boolean mainDone, boolean queueEmpty, Subscriber<?> childSubscriber, Queue<?> q, boolean delayError) {
            if (this.cancelled) {
                cancel(q);
                return true;
            }
            if (mainDone) {
                if (!delayError) {
                    Throwable e = this.error.get();
                    if (e != null) {
                        cancel(q);
                        childSubscriber.onError(e);
                        return true;
                    } else if (queueEmpty) {
                        childSubscriber.onCompleted();
                        return true;
                    }
                } else if (queueEmpty) {
                    Throwable e2 = this.error.get();
                    if (e2 != null) {
                        childSubscriber.onError(e2);
                        return true;
                    }
                    childSubscriber.onCompleted();
                    return true;
                }
            }
            return false;
        }

        void onError(Throwable e) {
            Throwable curr;
            Throwable next;
            AtomicReference<Throwable> localError = this.error;
            do {
                curr = localError.get();
                if (curr == null) {
                    next = e;
                } else if (curr instanceof CompositeException) {
                    List<Throwable> es = new ArrayList<>(((CompositeException) curr).getExceptions());
                    es.add(e);
                    next = new CompositeException(es);
                } else {
                    next = new CompositeException(Arrays.asList(curr, e));
                }
            } while (!localError.compareAndSet(curr, next));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class CombinerSubscriber<T, R> extends Subscriber<T> {
        boolean done;
        final int index;
        final LatestCoordinator<T, R> parent;

        public CombinerSubscriber(LatestCoordinator<T, R> parent, int index) {
            this.parent = parent;
            this.index = index;
            request(parent.bufferSize);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.done) {
                this.parent.combine(NotificationLite.next(t), this.index);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable t) {
            if (this.done) {
                RxJavaHooks.onError(t);
                return;
            }
            this.parent.onError(t);
            this.done = true;
            this.parent.combine(null, this.index);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                this.parent.combine(null, this.index);
            }
        }

        public void requestMore(long n) {
            request(n);
        }
    }
}

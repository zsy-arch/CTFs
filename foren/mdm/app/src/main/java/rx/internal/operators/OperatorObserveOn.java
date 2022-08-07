package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.internal.schedulers.ImmediateScheduler;
import rx.internal.schedulers.TrampolineScheduler;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

/* loaded from: classes2.dex */
public final class OperatorObserveOn<T> implements Observable.Operator<T, T> {
    private final int bufferSize;
    private final boolean delayError;
    private final Scheduler scheduler;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorObserveOn(Scheduler scheduler, boolean delayError) {
        this(scheduler, delayError, RxRingBuffer.SIZE);
    }

    public OperatorObserveOn(Scheduler scheduler, boolean delayError, int bufferSize) {
        this.scheduler = scheduler;
        this.delayError = delayError;
        this.bufferSize = bufferSize <= 0 ? RxRingBuffer.SIZE : bufferSize;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        if ((this.scheduler instanceof ImmediateScheduler) || (this.scheduler instanceof TrampolineScheduler)) {
            return child;
        }
        ObserveOnSubscriber<T> parent = new ObserveOnSubscriber<>(this.scheduler, child, this.delayError, this.bufferSize);
        parent.init();
        return parent;
    }

    public static <T> Observable.Operator<T, T> rebatch(final int n) {
        return new Observable.Operator<T, T>() { // from class: rx.internal.operators.OperatorObserveOn.1
            @Override // rx.functions.Func1
            public /* bridge */ /* synthetic */ Object call(Object x0) {
                return call((Subscriber) ((Subscriber) x0));
            }

            public Subscriber<? super T> call(Subscriber<? super T> child) {
                ObserveOnSubscriber<T> parent = new ObserveOnSubscriber<>(Schedulers.immediate(), child, false, n);
                parent.init();
                return parent;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class ObserveOnSubscriber<T> extends Subscriber<T> implements Action0 {
        final Subscriber<? super T> child;
        final boolean delayError;
        long emitted;
        Throwable error;
        volatile boolean finished;
        final int limit;
        final Queue<Object> queue;
        final Scheduler.Worker recursiveScheduler;
        final AtomicLong requested = new AtomicLong();
        final AtomicLong counter = new AtomicLong();

        public ObserveOnSubscriber(Scheduler scheduler, Subscriber<? super T> child, boolean delayError, int bufferSize) {
            this.child = child;
            this.recursiveScheduler = scheduler.createWorker();
            this.delayError = delayError;
            int calculatedSize = bufferSize > 0 ? bufferSize : RxRingBuffer.SIZE;
            this.limit = calculatedSize - (calculatedSize >> 2);
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new SpscArrayQueue(calculatedSize);
            } else {
                this.queue = new SpscAtomicArrayQueue(calculatedSize);
            }
            request(calculatedSize);
        }

        void init() {
            Subscriber<? super T> localChild = this.child;
            localChild.setProducer(new Producer() { // from class: rx.internal.operators.OperatorObserveOn.ObserveOnSubscriber.1
                @Override // rx.Producer
                public void request(long n) {
                    if (n > 0) {
                        BackpressureUtils.getAndAddRequest(ObserveOnSubscriber.this.requested, n);
                        ObserveOnSubscriber.this.schedule();
                    }
                }
            });
            localChild.add(this.recursiveScheduler);
            localChild.add(this);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!isUnsubscribed() && !this.finished) {
                if (!this.queue.offer(NotificationLite.next(t))) {
                    onError(new MissingBackpressureException());
                } else {
                    schedule();
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!isUnsubscribed() && !this.finished) {
                this.finished = true;
                schedule();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (isUnsubscribed() || this.finished) {
                RxJavaHooks.onError(e);
                return;
            }
            this.error = e;
            this.finished = true;
            schedule();
        }

        protected void schedule() {
            if (this.counter.getAndIncrement() == 0) {
                this.recursiveScheduler.schedule(this);
            }
        }

        @Override // rx.functions.Action0
        public void call() {
            long missed = 1;
            long currentEmission = this.emitted;
            Queue<Object> q = this.queue;
            Subscriber<? super T> localChild = this.child;
            do {
                long requestAmount = this.requested.get();
                while (requestAmount != currentEmission) {
                    boolean done = this.finished;
                    Object v = q.poll();
                    boolean empty = v == null;
                    if (!checkTerminated(done, empty, localChild, q)) {
                        if (empty) {
                            break;
                        }
                        localChild.onNext((Object) NotificationLite.getValue(v));
                        currentEmission++;
                        if (currentEmission == this.limit) {
                            requestAmount = BackpressureUtils.produced(this.requested, currentEmission);
                            request(currentEmission);
                            currentEmission = 0;
                        }
                    } else {
                        return;
                    }
                }
                if (requestAmount != currentEmission || !checkTerminated(this.finished, q.isEmpty(), localChild, q)) {
                    this.emitted = currentEmission;
                    missed = this.counter.addAndGet(-missed);
                } else {
                    return;
                }
            } while (missed != 0);
        }

        boolean checkTerminated(boolean done, boolean isEmpty, Subscriber<? super T> a, Queue<Object> q) {
            if (a.isUnsubscribed()) {
                q.clear();
                return true;
            }
            if (done) {
                if (!this.delayError) {
                    Throwable e = this.error;
                    if (e != null) {
                        q.clear();
                        try {
                            a.onError(e);
                            return true;
                        } finally {
                        }
                    } else if (isEmpty) {
                        try {
                            a.onCompleted();
                            return true;
                        } finally {
                        }
                    }
                } else if (isEmpty) {
                    Throwable e2 = this.error;
                    try {
                        if (e2 != null) {
                            a.onError(e2);
                        } else {
                            a.onCompleted();
                        }
                    } finally {
                    }
                }
            }
            return false;
        }
    }
}

package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Func1;
import rx.internal.operators.OnSubscribeFromIterable;
import rx.internal.util.ExceptionsUtils;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class OnSubscribeFlattenIterable<T, R> implements Observable.OnSubscribe<R> {
    final Func1<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;
    final Observable<? extends T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    protected OnSubscribeFlattenIterable(Observable<? extends T> source, Func1<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
        this.source = source;
        this.mapper = mapper;
        this.prefetch = prefetch;
    }

    public void call(Subscriber<? super R> t) {
        final FlattenIterableSubscriber<T, R> parent = new FlattenIterableSubscriber<>(t, this.mapper, this.prefetch);
        t.add(parent);
        t.setProducer(new Producer() { // from class: rx.internal.operators.OnSubscribeFlattenIterable.1
            @Override // rx.Producer
            public void request(long n) {
                parent.requestMore(n);
            }
        });
        this.source.unsafeSubscribe(parent);
    }

    public static <T, R> Observable<R> createFrom(Observable<? extends T> source, Func1<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
        if (source instanceof ScalarSynchronousObservable) {
            return Observable.unsafeCreate(new OnSubscribeScalarFlattenIterable(((ScalarSynchronousObservable) source).get(), mapper));
        }
        return Observable.unsafeCreate(new OnSubscribeFlattenIterable(source, mapper, prefetch));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class FlattenIterableSubscriber<T, R> extends Subscriber<T> {
        Iterator<? extends R> active;
        final Subscriber<? super R> actual;
        volatile boolean done;
        final long limit;
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        long produced;
        final Queue<Object> queue;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final AtomicInteger wip = new AtomicInteger();
        final AtomicLong requested = new AtomicLong();

        public FlattenIterableSubscriber(Subscriber<? super R> actual, Func1<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
            this.actual = actual;
            this.mapper = mapper;
            if (prefetch == Integer.MAX_VALUE) {
                this.limit = Long.MAX_VALUE;
                this.queue = new SpscLinkedArrayQueue(RxRingBuffer.SIZE);
            } else {
                this.limit = prefetch - (prefetch >> 2);
                if (UnsafeAccess.isUnsafeAvailable()) {
                    this.queue = new SpscArrayQueue(prefetch);
                } else {
                    this.queue = new SpscAtomicArrayQueue(prefetch);
                }
            }
            request(prefetch);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (!this.queue.offer(NotificationLite.next(t))) {
                unsubscribe();
                onError(new MissingBackpressureException());
                return;
            }
            drain();
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (ExceptionsUtils.addThrowable(this.error, e)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaHooks.onError(e);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            drain();
        }

        void requestMore(long n) {
            if (n > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, n);
                drain();
            } else if (n < 0) {
                throw new IllegalStateException("n >= 0 required but it was " + n);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:71:0x000c A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0018 A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 337
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeFlattenIterable.FlattenIterableSubscriber.drain():void");
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, Queue<?> q) {
            if (a.isUnsubscribed()) {
                q.clear();
                this.active = null;
                return true;
            }
            if (d) {
                if (this.error.get() != null) {
                    Throwable ex = ExceptionsUtils.terminate(this.error);
                    unsubscribe();
                    q.clear();
                    this.active = null;
                    a.onError(ex);
                    return true;
                } else if (empty) {
                    a.onCompleted();
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class OnSubscribeScalarFlattenIterable<T, R> implements Observable.OnSubscribe<R> {
        final Func1<? super T, ? extends Iterable<? extends R>> mapper;
        final T value;

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object x0) {
            call((Subscriber) ((Subscriber) x0));
        }

        public OnSubscribeScalarFlattenIterable(T value, Func1<? super T, ? extends Iterable<? extends R>> mapper) {
            this.value = value;
            this.mapper = mapper;
        }

        public void call(Subscriber<? super R> t) {
            try {
                Iterator<? extends R> iterator = ((Iterable) this.mapper.call((T) this.value)).iterator();
                if (!iterator.hasNext()) {
                    t.onCompleted();
                } else {
                    t.setProducer(new OnSubscribeFromIterable.IterableProducer(t, iterator));
                }
            } catch (Throwable ex) {
                Exceptions.throwOrReport(ex, t, this.value);
            }
        }
    }
}

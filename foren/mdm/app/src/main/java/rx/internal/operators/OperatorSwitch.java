package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
public final class OperatorSwitch<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayError;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Holder {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(false);

        Holder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class HolderDelayError {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(true);

        HolderDelayError() {
        }
    }

    public static <T> OperatorSwitch<T> instance(boolean delayError) {
        return delayError ? (OperatorSwitch<T>) HolderDelayError.INSTANCE : (OperatorSwitch<T>) Holder.INSTANCE;
    }

    OperatorSwitch(boolean delayError) {
        this.delayError = delayError;
    }

    public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> child) {
        SwitchSubscriber<T> sws = new SwitchSubscriber<>(child, this.delayError);
        child.add(sws);
        sws.init();
        return sws;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class SwitchSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final Throwable TERMINAL_ERROR = new Throwable("Terminal error");
        final Subscriber<? super T> child;
        final boolean delayError;
        boolean emitting;
        Throwable error;
        boolean innerActive;
        volatile boolean mainDone;
        boolean missed;
        Producer producer;
        long requested;
        final SerialSubscription serial = new SerialSubscription();
        final AtomicLong index = new AtomicLong();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(RxRingBuffer.SIZE);

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object x0) {
            onNext((Observable) ((Observable) x0));
        }

        SwitchSubscriber(Subscriber<? super T> child, boolean delayError) {
            this.child = child;
            this.delayError = delayError;
        }

        void init() {
            this.child.add(this.serial);
            this.child.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    SwitchSubscriber.this.clearProducer();
                }
            }));
            this.child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.2
                @Override // rx.Producer
                public void request(long n) {
                    if (n > 0) {
                        SwitchSubscriber.this.childRequested(n);
                    } else if (n < 0) {
                        throw new IllegalArgumentException("n >= 0 expected but it was " + n);
                    }
                }
            });
        }

        void clearProducer() {
            synchronized (this) {
                this.producer = null;
            }
        }

        public void onNext(Observable<? extends T> t) {
            InnerSubscriber<T> inner;
            long id = this.index.incrementAndGet();
            Subscription s = this.serial.get();
            if (s != null) {
                s.unsubscribe();
            }
            synchronized (this) {
                inner = new InnerSubscriber<>(id, this);
                this.innerActive = true;
                this.producer = null;
            }
            this.serial.set(inner);
            t.unsafeSubscribe(inner);
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            boolean success;
            synchronized (this) {
                success = updateError(e);
            }
            if (success) {
                this.mainDone = true;
                drain();
                return;
            }
            pluginError(e);
        }

        boolean updateError(Throwable next) {
            Throwable e = this.error;
            if (e == TERMINAL_ERROR) {
                return false;
            }
            if (e == null) {
                this.error = next;
            } else if (e instanceof CompositeException) {
                List<Throwable> list = new ArrayList<>(((CompositeException) e).getExceptions());
                list.add(next);
                this.error = new CompositeException(list);
            } else {
                this.error = new CompositeException(e, next);
            }
            return true;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.mainDone = true;
            drain();
        }

        void emit(T value, InnerSubscriber<T> inner) {
            synchronized (this) {
                if (this.index.get() == ((InnerSubscriber) inner).id) {
                    this.queue.offer(inner, NotificationLite.next(value));
                    drain();
                }
            }
        }

        void error(Throwable e, long id) {
            boolean success;
            synchronized (this) {
                if (this.index.get() == id) {
                    success = updateError(e);
                    this.innerActive = false;
                    this.producer = null;
                } else {
                    success = true;
                }
            }
            if (success) {
                drain();
            } else {
                pluginError(e);
            }
        }

        void complete(long id) {
            synchronized (this) {
                if (this.index.get() == id) {
                    this.innerActive = false;
                    this.producer = null;
                    drain();
                }
            }
        }

        void pluginError(Throwable e) {
            RxJavaHooks.onError(e);
        }

        void innerProducer(Producer p, long id) {
            synchronized (this) {
                if (this.index.get() == id) {
                    long n = this.requested;
                    this.producer = p;
                    p.request(n);
                }
            }
        }

        void childRequested(long n) {
            Producer p;
            synchronized (this) {
                p = this.producer;
                this.requested = BackpressureUtils.addCap(this.requested, n);
            }
            if (p != null) {
                p.request(n);
            }
            drain();
        }

        void drain() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                boolean localInnerActive = this.innerActive;
                long localRequested = this.requested;
                Throwable localError = this.error;
                if (!(localError == null || localError == TERMINAL_ERROR || this.delayError)) {
                    this.error = TERMINAL_ERROR;
                }
                SpscLinkedArrayQueue<Object> localQueue = this.queue;
                AtomicLong localIndex = this.index;
                Subscriber<? super T> localChild = this.child;
                boolean localMainDone = this.mainDone;
                while (true) {
                    long localEmission = 0;
                    while (localEmission != localRequested) {
                        if (!localChild.isUnsubscribed()) {
                            boolean empty = localQueue.isEmpty();
                            if (checkTerminated(localMainDone, localInnerActive, localError, localQueue, localChild, empty)) {
                                return;
                            }
                            if (empty) {
                                break;
                            }
                            Object obj = (Object) NotificationLite.getValue(localQueue.poll());
                            if (localIndex.get() == ((InnerSubscriber) ((InnerSubscriber) localQueue.poll())).id) {
                                localChild.onNext(obj);
                                localEmission++;
                            }
                        } else {
                            return;
                        }
                    }
                    if (localEmission != localRequested || (!localChild.isUnsubscribed() && !checkTerminated(this.mainDone, localInnerActive, localError, localQueue, localChild, localQueue.isEmpty()))) {
                        synchronized (this) {
                            localRequested = this.requested;
                            if (localRequested != Long.MAX_VALUE) {
                                localRequested -= localEmission;
                                this.requested = localRequested;
                            }
                            if (!this.missed) {
                                this.emitting = false;
                                return;
                            }
                            this.missed = false;
                            localMainDone = this.mainDone;
                            localInnerActive = this.innerActive;
                            localError = this.error;
                            if (!(localError == null || localError == TERMINAL_ERROR || this.delayError)) {
                                this.error = TERMINAL_ERROR;
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        }

        protected boolean checkTerminated(boolean localMainDone, boolean localInnerActive, Throwable localError, SpscLinkedArrayQueue<Object> localQueue, Subscriber<? super T> localChild, boolean empty) {
            if (this.delayError) {
                if (localMainDone && !localInnerActive && empty) {
                    if (localError != null) {
                        localChild.onError(localError);
                        return true;
                    }
                    localChild.onCompleted();
                    return true;
                }
            } else if (localError != null) {
                localQueue.clear();
                localChild.onError(localError);
                return true;
            } else if (localMainDone && !localInnerActive && empty) {
                localChild.onCompleted();
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class InnerSubscriber<T> extends Subscriber<T> {
        private final long id;
        private final SwitchSubscriber<T> parent;

        InnerSubscriber(long id, SwitchSubscriber<T> parent) {
            this.id = id;
            this.parent = parent;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) {
            this.parent.innerProducer(p, this.id);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.parent.emit(t, this);
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.parent.error(e, this.id);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.parent.complete(this.id);
        }
    }
}

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Notification;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class OperatorMaterialize<T> implements Observable.Operator<Notification<T>, T> {
    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class Holder {
        static final OperatorMaterialize<Object> INSTANCE = new OperatorMaterialize<>();

        Holder() {
        }
    }

    public static <T> OperatorMaterialize<T> instance() {
        return (OperatorMaterialize<T>) Holder.INSTANCE;
    }

    OperatorMaterialize() {
    }

    public Subscriber<? super T> call(Subscriber<? super Notification<T>> child) {
        final ParentSubscriber<T> parent = new ParentSubscriber<>(child);
        child.add(parent);
        child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorMaterialize.1
            @Override // rx.Producer
            public void request(long n) {
                if (n > 0) {
                    parent.requestMore(n);
                }
            }
        });
        return parent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class ParentSubscriber<T> extends Subscriber<T> {
        private boolean busy;
        private final Subscriber<? super Notification<T>> child;
        private boolean missed;
        private final AtomicLong requested = new AtomicLong();
        private volatile Notification<T> terminalNotification;

        ParentSubscriber(Subscriber<? super Notification<T>> child) {
            this.child = child;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(0L);
        }

        void requestMore(long n) {
            BackpressureUtils.getAndAddRequest(this.requested, n);
            request(n);
            drain();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.terminalNotification = Notification.createOnCompleted();
            drain();
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.terminalNotification = Notification.createOnError(e);
            RxJavaHooks.onError(e);
            drain();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.child.onNext(Notification.createOnNext(t));
            decrementRequested();
        }

        private void decrementRequested() {
            long r;
            AtomicLong localRequested = this.requested;
            do {
                r = localRequested.get();
                if (r == Long.MAX_VALUE) {
                    return;
                }
            } while (!localRequested.compareAndSet(r, r - 1));
        }

        private void drain() {
            synchronized (this) {
                if (this.busy) {
                    this.missed = true;
                    return;
                }
                AtomicLong localRequested = this.requested;
                while (!this.child.isUnsubscribed()) {
                    Notification<T> tn = this.terminalNotification;
                    if (tn == null || localRequested.get() <= 0) {
                        synchronized (this) {
                            if (!this.missed) {
                                this.busy = false;
                                return;
                            }
                        }
                    } else {
                        this.terminalNotification = null;
                        this.child.onNext(tn);
                        if (!this.child.isUnsubscribed()) {
                            this.child.onCompleted();
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }
}

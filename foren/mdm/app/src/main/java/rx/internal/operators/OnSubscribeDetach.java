package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class OnSubscribeDetach<T> implements Observable.OnSubscribe<T> {
    final Observable<T> source;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeDetach(Observable<T> source) {
        this.source = source;
    }

    public void call(Subscriber<? super T> t) {
        DetachSubscriber<T> parent = new DetachSubscriber<>(t);
        DetachProducer<T> producer = new DetachProducer<>(parent);
        t.add(producer);
        t.setProducer(producer);
        this.source.unsafeSubscribe(parent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class DetachSubscriber<T> extends Subscriber<T> {
        final AtomicReference<Subscriber<? super T>> actual;
        final AtomicReference<Producer> producer = new AtomicReference<>();
        final AtomicLong requested = new AtomicLong();

        public DetachSubscriber(Subscriber<? super T> actual) {
            this.actual = new AtomicReference<>(actual);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            Subscriber<? super T> a = this.actual.get();
            if (a != null) {
                a.onNext(t);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            Subscriber<? super T> a = this.actual.getAndSet(null);
            if (a != null) {
                a.onError(e);
            } else {
                RxJavaHooks.onError(e);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            Subscriber<? super T> a = this.actual.getAndSet(null);
            if (a != null) {
                a.onCompleted();
            }
        }

        void innerRequest(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required but it was " + n);
            }
            Producer p = this.producer.get();
            if (p != null) {
                p.request(n);
                return;
            }
            BackpressureUtils.getAndAddRequest(this.requested, n);
            Producer p2 = this.producer.get();
            if (p2 != null && p2 != TerminatedProducer.INSTANCE) {
                p2.request(this.requested.getAndSet(0L));
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer p) {
            if (this.producer.compareAndSet(null, p)) {
                p.request(this.requested.getAndSet(0L));
            } else if (this.producer.get() != TerminatedProducer.INSTANCE) {
                throw new IllegalStateException("Producer already set!");
            }
        }

        void innerUnsubscribe() {
            this.producer.lazySet(TerminatedProducer.INSTANCE);
            this.actual.lazySet(null);
            unsubscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class DetachProducer<T> implements Producer, Subscription {
        final DetachSubscriber<T> parent;

        public DetachProducer(DetachSubscriber<T> parent) {
            this.parent = parent;
        }

        @Override // rx.Producer
        public void request(long n) {
            this.parent.innerRequest(n);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.parent.isUnsubscribed();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.parent.innerUnsubscribe();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public enum TerminatedProducer implements Producer {
        INSTANCE;

        @Override // rx.Producer
        public void request(long n) {
        }
    }
}

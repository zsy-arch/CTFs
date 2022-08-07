package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Completable;
import rx.CompletableEmitter;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Cancellable;
import rx.internal.subscriptions.CancellableSubscription;
import rx.internal.subscriptions.SequentialSubscription;
import rx.plugins.RxJavaHooks;

/* loaded from: classes2.dex */
public final class CompletableFromEmitter implements Completable.OnSubscribe {
    final Action1<CompletableEmitter> producer;

    public CompletableFromEmitter(Action1<CompletableEmitter> producer) {
        this.producer = producer;
    }

    public void call(CompletableSubscriber t) {
        FromEmitter emitter = new FromEmitter(t);
        t.onSubscribe(emitter);
        try {
            this.producer.call(emitter);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            emitter.onError(ex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class FromEmitter extends AtomicBoolean implements CompletableEmitter, Subscription {
        private static final long serialVersionUID = 5539301318568668881L;
        final CompletableSubscriber actual;
        final SequentialSubscription resource = new SequentialSubscription();

        public FromEmitter(CompletableSubscriber actual) {
            this.actual = actual;
        }

        @Override // rx.CompletableEmitter
        public void onCompleted() {
            if (compareAndSet(false, true)) {
                try {
                    this.actual.onCompleted();
                } finally {
                    this.resource.unsubscribe();
                }
            }
        }

        @Override // rx.CompletableEmitter
        public void onError(Throwable t) {
            if (compareAndSet(false, true)) {
                try {
                    this.actual.onError(t);
                } finally {
                    this.resource.unsubscribe();
                }
            } else {
                RxJavaHooks.onError(t);
            }
        }

        @Override // rx.CompletableEmitter
        public void setSubscription(Subscription s) {
            this.resource.update(s);
        }

        @Override // rx.CompletableEmitter
        public void setCancellation(Cancellable c) {
            setSubscription(new CancellableSubscription(c));
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.resource.unsubscribe();
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get();
        }
    }
}

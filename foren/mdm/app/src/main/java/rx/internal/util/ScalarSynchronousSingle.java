package rx.internal.util;

import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.schedulers.EventLoopsScheduler;

/* loaded from: classes2.dex */
public final class ScalarSynchronousSingle<T> extends Single<T> {
    final T value;

    public static <T> ScalarSynchronousSingle<T> create(T t) {
        return new ScalarSynchronousSingle<>(t);
    }

    protected ScalarSynchronousSingle(final T t) {
        super(new Single.OnSubscribe<T>() { // from class: rx.internal.util.ScalarSynchronousSingle.1
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object x0) {
                call((SingleSubscriber) ((SingleSubscriber) x0));
            }

            public void call(SingleSubscriber<? super T> te) {
                te.onSuccess((Object) t);
            }
        });
        this.value = t;
    }

    public T get() {
        return this.value;
    }

    public Single<T> scalarScheduleOn(Scheduler scheduler) {
        return scheduler instanceof EventLoopsScheduler ? create((Single.OnSubscribe) new DirectScheduledEmission((EventLoopsScheduler) scheduler, this.value)) : create((Single.OnSubscribe) new NormalScheduledEmission(scheduler, this.value));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class DirectScheduledEmission<T> implements Single.OnSubscribe<T> {
        private final EventLoopsScheduler es;
        private final T value;

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object x0) {
            call((SingleSubscriber) ((SingleSubscriber) x0));
        }

        DirectScheduledEmission(EventLoopsScheduler es, T value) {
            this.es = es;
            this.value = value;
        }

        public void call(SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.add(this.es.scheduleDirect(new ScalarSynchronousSingleAction(singleSubscriber, this.value)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class NormalScheduledEmission<T> implements Single.OnSubscribe<T> {
        private final Scheduler scheduler;
        private final T value;

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object x0) {
            call((SingleSubscriber) ((SingleSubscriber) x0));
        }

        NormalScheduledEmission(Scheduler scheduler, T value) {
            this.scheduler = scheduler;
            this.value = value;
        }

        public void call(SingleSubscriber<? super T> singleSubscriber) {
            Scheduler.Worker worker = this.scheduler.createWorker();
            singleSubscriber.add(worker);
            worker.schedule(new ScalarSynchronousSingleAction(singleSubscriber, this.value));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class ScalarSynchronousSingleAction<T> implements Action0 {
        private final SingleSubscriber<? super T> subscriber;
        private final T value;

        ScalarSynchronousSingleAction(SingleSubscriber<? super T> subscriber, T value) {
            this.subscriber = subscriber;
            this.value = value;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                this.subscriber.onSuccess((T) this.value);
            } catch (Throwable t) {
                this.subscriber.onError(t);
            }
        }
    }

    public <R> Single<R> scalarFlatMap(final Func1<? super T, ? extends Single<? extends R>> func) {
        return create((Single.OnSubscribe) new Single.OnSubscribe<R>() { // from class: rx.internal.util.ScalarSynchronousSingle.2
            /* JADX WARN: Multi-variable type inference failed */
            public void call(final SingleSubscriber<? super R> child) {
                Single single = (Single) func.call(ScalarSynchronousSingle.this.value);
                if (single instanceof ScalarSynchronousSingle) {
                    child.onSuccess(((ScalarSynchronousSingle) single).value);
                    return;
                }
                Subscription subscription = new SingleSubscriber<R>() { // from class: rx.internal.util.ScalarSynchronousSingle.2.1
                    @Override // rx.SingleSubscriber
                    public void onError(Throwable e) {
                        child.onError(e);
                    }

                    @Override // rx.SingleSubscriber
                    public void onSuccess(R r) {
                        child.onSuccess(r);
                    }
                };
                child.add(subscription);
                single.subscribe((SingleSubscriber) subscription);
            }
        });
    }
}

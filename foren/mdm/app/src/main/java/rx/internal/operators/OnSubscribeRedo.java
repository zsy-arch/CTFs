package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Notification;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.producers.ProducerArbiter;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;
import rx.subscriptions.SerialSubscription;

/* loaded from: classes2.dex */
public final class OnSubscribeRedo<T> implements Observable.OnSubscribe<T> {
    static final Func1<Observable<? extends Notification<?>>, Observable<?>> REDO_INFINITE = new Func1<Observable<? extends Notification<?>>, Observable<?>>() { // from class: rx.internal.operators.OnSubscribeRedo.1
        public Observable<?> call(Observable<? extends Notification<?>> ts) {
            return ts.map(new Func1<Notification<?>, Notification<?>>() { // from class: rx.internal.operators.OnSubscribeRedo.1.1
                public Notification<?> call(Notification<?> terminal) {
                    return Notification.createOnNext(null);
                }
            });
        }
    };
    private final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> controlHandlerFunction;
    private final Scheduler scheduler;
    final Observable<T> source;
    final boolean stopOnComplete;
    final boolean stopOnError;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    /* loaded from: classes2.dex */
    public static final class RedoFinite implements Func1<Observable<? extends Notification<?>>, Observable<?>> {
        final long count;

        public RedoFinite(long count) {
            this.count = count;
        }

        public Observable<?> call(Observable<? extends Notification<?>> ts) {
            return ts.map(new Func1<Notification<?>, Notification<?>>() { // from class: rx.internal.operators.OnSubscribeRedo.RedoFinite.1
                int num;

                public Notification<?> call(Notification<?> terminalNotification) {
                    if (RedoFinite.this.count == 0) {
                        return terminalNotification;
                    }
                    this.num++;
                    if (this.num <= RedoFinite.this.count) {
                        return Notification.createOnNext(Integer.valueOf(this.num));
                    }
                    return terminalNotification;
                }
            }).dematerialize();
        }
    }

    /* loaded from: classes2.dex */
    public static final class RetryWithPredicate implements Func1<Observable<? extends Notification<?>>, Observable<? extends Notification<?>>> {
        final Func2<Integer, Throwable, Boolean> predicate;

        public RetryWithPredicate(Func2<Integer, Throwable, Boolean> predicate) {
            this.predicate = predicate;
        }

        public Observable<? extends Notification<?>> call(Observable<? extends Notification<?>> ts) {
            return ts.scan(Notification.createOnNext(0), new Func2<Notification<Integer>, Notification<?>, Notification<Integer>>() { // from class: rx.internal.operators.OnSubscribeRedo.RetryWithPredicate.1
                /* JADX WARN: Multi-variable type inference failed */
                public Notification<Integer> call(Notification<Integer> n, Notification<?> term) {
                    int value = n.getValue().intValue();
                    if (RetryWithPredicate.this.predicate.call(Integer.valueOf(value), term.getThrowable()).booleanValue()) {
                        return Notification.createOnNext(Integer.valueOf(value + 1));
                    }
                    return term;
                }
            });
        }
    }

    public static <T> Observable<T> retry(Observable<T> source) {
        return retry(source, REDO_INFINITE);
    }

    public static <T> Observable<T> retry(Observable<T> source, long count) {
        if (count >= 0) {
            return count == 0 ? source : retry(source, new RedoFinite(count));
        }
        throw new IllegalArgumentException("count >= 0 expected");
    }

    public static <T> Observable<T> retry(Observable<T> source, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> notificationHandler) {
        return Observable.unsafeCreate(new OnSubscribeRedo(source, notificationHandler, true, false, Schedulers.trampoline()));
    }

    public static <T> Observable<T> retry(Observable<T> source, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> notificationHandler, Scheduler scheduler) {
        return Observable.unsafeCreate(new OnSubscribeRedo(source, notificationHandler, true, false, scheduler));
    }

    public static <T> Observable<T> repeat(Observable<T> source) {
        return repeat(source, Schedulers.trampoline());
    }

    public static <T> Observable<T> repeat(Observable<T> source, Scheduler scheduler) {
        return repeat(source, REDO_INFINITE, scheduler);
    }

    public static <T> Observable<T> repeat(Observable<T> source, long count) {
        return repeat(source, count, Schedulers.trampoline());
    }

    public static <T> Observable<T> repeat(Observable<T> source, long count, Scheduler scheduler) {
        if (count == 0) {
            return Observable.empty();
        }
        if (count >= 0) {
            return repeat(source, new RedoFinite(count - 1), scheduler);
        }
        throw new IllegalArgumentException("count >= 0 expected");
    }

    public static <T> Observable<T> repeat(Observable<T> source, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> notificationHandler) {
        return Observable.unsafeCreate(new OnSubscribeRedo(source, notificationHandler, false, true, Schedulers.trampoline()));
    }

    public static <T> Observable<T> repeat(Observable<T> source, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> notificationHandler, Scheduler scheduler) {
        return Observable.unsafeCreate(new OnSubscribeRedo(source, notificationHandler, false, true, scheduler));
    }

    public static <T> Observable<T> redo(Observable<T> source, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> notificationHandler, Scheduler scheduler) {
        return Observable.unsafeCreate(new OnSubscribeRedo(source, notificationHandler, false, false, scheduler));
    }

    private OnSubscribeRedo(Observable<T> source, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> f, boolean stopOnComplete, boolean stopOnError, Scheduler scheduler) {
        this.source = source;
        this.controlHandlerFunction = f;
        this.stopOnComplete = stopOnComplete;
        this.stopOnError = stopOnError;
        this.scheduler = scheduler;
    }

    public void call(final Subscriber<? super T> child) {
        final AtomicBoolean resumeBoundary = new AtomicBoolean(true);
        final AtomicLong consumerCapacity = new AtomicLong();
        final Scheduler.Worker worker = this.scheduler.createWorker();
        child.add(worker);
        final SerialSubscription sourceSubscriptions = new SerialSubscription();
        child.add(sourceSubscriptions);
        final Subject<Notification<?>, Notification<?>> terminals = BehaviorSubject.create().toSerialized();
        terminals.subscribe((Subscriber<? super Notification<?>>) Subscribers.empty());
        final ProducerArbiter arbiter = new ProducerArbiter();
        final Action0 subscribeToSource = new Action0() { // from class: rx.internal.operators.OnSubscribeRedo.2
            @Override // rx.functions.Action0
            public void call() {
                if (!child.isUnsubscribed()) {
                    Subscriber<T> terminalDelegatingSubscriber = new Subscriber<T>() { // from class: rx.internal.operators.OnSubscribeRedo.2.1
                        boolean done;

                        @Override // rx.Observer
                        public void onCompleted() {
                            if (!this.done) {
                                this.done = true;
                                unsubscribe();
                                terminals.onNext(Notification.createOnCompleted());
                            }
                        }

                        @Override // rx.Observer
                        public void onError(Throwable e) {
                            if (!this.done) {
                                this.done = true;
                                unsubscribe();
                                terminals.onNext(Notification.createOnError(e));
                            }
                        }

                        @Override // rx.Observer
                        public void onNext(T v) {
                            if (!this.done) {
                                child.onNext(v);
                                decrementConsumerCapacity();
                                arbiter.produced(1L);
                            }
                        }

                        private void decrementConsumerCapacity() {
                            long cc;
                            do {
                                cc = consumerCapacity.get();
                                if (cc == Long.MAX_VALUE) {
                                    return;
                                }
                            } while (!consumerCapacity.compareAndSet(cc, cc - 1));
                        }

                        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
                        public void setProducer(Producer producer) {
                            arbiter.setProducer(producer);
                        }
                    };
                    sourceSubscriptions.set(terminalDelegatingSubscriber);
                    OnSubscribeRedo.this.source.unsafeSubscribe(terminalDelegatingSubscriber);
                }
            }
        };
        final Observable<?> restarts = (Observable) this.controlHandlerFunction.call(terminals.lift(new Observable.Operator<Notification<?>, Notification<?>>() { // from class: rx.internal.operators.OnSubscribeRedo.3
            public Subscriber<? super Notification<?>> call(final Subscriber<? super Notification<?>> filteredTerminals) {
                return new Subscriber<Notification<?>>(filteredTerminals) { // from class: rx.internal.operators.OnSubscribeRedo.3.1
                    @Override // rx.Observer
                    public void onCompleted() {
                        filteredTerminals.onCompleted();
                    }

                    @Override // rx.Observer
                    public void onError(Throwable e) {
                        filteredTerminals.onError(e);
                    }

                    public void onNext(Notification<?> t) {
                        if (t.isOnCompleted() && OnSubscribeRedo.this.stopOnComplete) {
                            filteredTerminals.onCompleted();
                        } else if (!t.isOnError() || !OnSubscribeRedo.this.stopOnError) {
                            filteredTerminals.onNext(t);
                        } else {
                            filteredTerminals.onError(t.getThrowable());
                        }
                    }

                    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
                    public void setProducer(Producer producer) {
                        producer.request(Long.MAX_VALUE);
                    }
                };
            }
        }));
        worker.schedule(new Action0() { // from class: rx.internal.operators.OnSubscribeRedo.4
            @Override // rx.functions.Action0
            public void call() {
                restarts.unsafeSubscribe(new Subscriber<Object>(child) { // from class: rx.internal.operators.OnSubscribeRedo.4.1
                    @Override // rx.Observer
                    public void onCompleted() {
                        child.onCompleted();
                    }

                    @Override // rx.Observer
                    public void onError(Throwable e) {
                        child.onError(e);
                    }

                    @Override // rx.Observer
                    public void onNext(Object t) {
                        if (child.isUnsubscribed()) {
                            return;
                        }
                        if (consumerCapacity.get() > 0) {
                            worker.schedule(subscribeToSource);
                        } else {
                            resumeBoundary.compareAndSet(false, true);
                        }
                    }

                    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
                    public void setProducer(Producer producer) {
                        producer.request(Long.MAX_VALUE);
                    }
                });
            }
        });
        child.setProducer(new Producer() { // from class: rx.internal.operators.OnSubscribeRedo.5
            @Override // rx.Producer
            public void request(long n) {
                if (n > 0) {
                    BackpressureUtils.getAndAddRequest(consumerCapacity, n);
                    arbiter.request(n);
                    if (resumeBoundary.compareAndSet(true, false)) {
                        worker.schedule(subscribeToSource);
                    }
                }
            }
        });
    }
}

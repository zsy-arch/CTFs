package rx.internal.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.observables.ConnectableObservable;
import rx.observers.Subscribers;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;

/* loaded from: classes2.dex */
public final class OperatorMulticast<T, R> extends ConnectableObservable<R> {
    final AtomicReference<Subject<? super T, ? extends R>> connectedSubject;
    final Object guard;
    Subscription guardedSubscription;
    final Observable<? extends T> source;
    final Func0<? extends Subject<? super T, ? extends R>> subjectFactory;
    Subscriber<T> subscription;
    final List<Subscriber<? super R>> waitingForConnect;

    public OperatorMulticast(Observable<? extends T> source, Func0<? extends Subject<? super T, ? extends R>> subjectFactory) {
        this(new Object(), new AtomicReference(), new ArrayList(), source, subjectFactory);
    }

    private OperatorMulticast(final Object guard, final AtomicReference<Subject<? super T, ? extends R>> connectedSubject, final List<Subscriber<? super R>> waitingForConnect, Observable<? extends T> source, Func0<? extends Subject<? super T, ? extends R>> subjectFactory) {
        super(new Observable.OnSubscribe<R>() { // from class: rx.internal.operators.OperatorMulticast.1
            @Override // rx.functions.Action1
            public /* bridge */ /* synthetic */ void call(Object x0) {
                call((Subscriber) ((Subscriber) x0));
            }

            public void call(Subscriber<? super R> subscriber) {
                synchronized (guard) {
                    if (connectedSubject.get() == null) {
                        waitingForConnect.add(subscriber);
                    } else {
                        ((Subject) connectedSubject.get()).unsafeSubscribe(subscriber);
                    }
                }
            }
        });
        this.guard = guard;
        this.connectedSubject = connectedSubject;
        this.waitingForConnect = waitingForConnect;
        this.source = source;
        this.subjectFactory = subjectFactory;
    }

    @Override // rx.observables.ConnectableObservable
    public void connect(Action1<? super Subscription> connection) {
        Subscriber<T> sub;
        synchronized (this.guard) {
            if (this.subscription != null) {
                connection.call(this.guardedSubscription);
                return;
            }
            Subject<? super T, ? extends R> subject = (Subject) this.subjectFactory.call();
            this.subscription = Subscribers.from(subject);
            final AtomicReference<Subscription> gs = new AtomicReference<>();
            gs.set(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorMulticast.2
                @Override // rx.functions.Action0
                public void call() {
                    synchronized (OperatorMulticast.this.guard) {
                        if (OperatorMulticast.this.guardedSubscription == gs.get()) {
                            Subscription s = OperatorMulticast.this.subscription;
                            OperatorMulticast.this.subscription = null;
                            OperatorMulticast.this.guardedSubscription = null;
                            OperatorMulticast.this.connectedSubject.set(null);
                            if (s != null) {
                                s.unsubscribe();
                            }
                        }
                    }
                }
            }));
            this.guardedSubscription = gs.get();
            for (final Subscriber<? super R> s : this.waitingForConnect) {
                subject.unsafeSubscribe(new Subscriber<R>(s) { // from class: rx.internal.operators.OperatorMulticast.3
                    @Override // rx.Observer
                    public void onNext(R t) {
                        s.onNext(t);
                    }

                    @Override // rx.Observer
                    public void onError(Throwable e) {
                        s.onError(e);
                    }

                    @Override // rx.Observer
                    public void onCompleted() {
                        s.onCompleted();
                    }
                });
            }
            this.waitingForConnect.clear();
            this.connectedSubject.set(subject);
            connection.call(this.guardedSubscription);
            synchronized (this.guard) {
                sub = this.subscription;
            }
            if (sub != null) {
                this.source.subscribe((Subscriber<? super Object>) sub);
            }
        }
    }
}

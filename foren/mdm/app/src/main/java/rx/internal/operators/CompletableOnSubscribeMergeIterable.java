package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes2.dex */
public final class CompletableOnSubscribeMergeIterable implements Completable.OnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeMergeIterable(Iterable<? extends Completable> sources) {
        this.sources = sources;
    }

    public void call(final CompletableSubscriber s) {
        final CompositeSubscription set = new CompositeSubscription();
        s.onSubscribe(set);
        try {
            Iterator<? extends Completable> iterator = this.sources.iterator();
            if (iterator == null) {
                s.onError(new NullPointerException("The source iterator returned is null"));
                return;
            }
            final AtomicInteger wip = new AtomicInteger(1);
            final AtomicBoolean once = new AtomicBoolean();
            while (!set.isUnsubscribed()) {
                try {
                    if (!iterator.hasNext()) {
                        if (wip.decrementAndGet() == 0 && once.compareAndSet(false, true)) {
                            s.onCompleted();
                            return;
                        }
                        return;
                    } else if (!set.isUnsubscribed()) {
                        try {
                            Completable c = (Completable) iterator.next();
                            if (set.isUnsubscribed()) {
                                return;
                            }
                            if (c == null) {
                                set.unsubscribe();
                                NullPointerException npe = new NullPointerException("A completable source is null");
                                if (once.compareAndSet(false, true)) {
                                    s.onError(npe);
                                    return;
                                } else {
                                    RxJavaHooks.onError(npe);
                                    return;
                                }
                            } else {
                                wip.getAndIncrement();
                                c.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeIterable.1
                                    @Override // rx.CompletableSubscriber
                                    public void onSubscribe(Subscription d) {
                                        set.add(d);
                                    }

                                    @Override // rx.CompletableSubscriber
                                    public void onError(Throwable e) {
                                        set.unsubscribe();
                                        if (once.compareAndSet(false, true)) {
                                            s.onError(e);
                                        } else {
                                            RxJavaHooks.onError(e);
                                        }
                                    }

                                    @Override // rx.CompletableSubscriber
                                    public void onCompleted() {
                                        if (wip.decrementAndGet() == 0 && once.compareAndSet(false, true)) {
                                            s.onCompleted();
                                        }
                                    }
                                });
                            }
                        } catch (Throwable e) {
                            set.unsubscribe();
                            if (once.compareAndSet(false, true)) {
                                s.onError(e);
                                return;
                            } else {
                                RxJavaHooks.onError(e);
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                } catch (Throwable e2) {
                    set.unsubscribe();
                    if (once.compareAndSet(false, true)) {
                        s.onError(e2);
                        return;
                    } else {
                        RxJavaHooks.onError(e2);
                        return;
                    }
                }
            }
        } catch (Throwable e3) {
            s.onError(e3);
        }
    }
}

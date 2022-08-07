package rx.internal.operators;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.internal.util.atomic.MpscLinkedAtomicQueue;
import rx.internal.util.unsafe.MpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes2.dex */
public final class CompletableOnSubscribeMergeDelayErrorIterable implements Completable.OnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeMergeDelayErrorIterable(Iterable<? extends Completable> sources) {
        this.sources = sources;
    }

    public void call(final CompletableSubscriber s) {
        final Queue<Throwable> queue;
        final CompositeSubscription set = new CompositeSubscription();
        s.onSubscribe(set);
        try {
            Iterator<? extends Completable> iterator = this.sources.iterator();
            if (iterator == null) {
                s.onError(new NullPointerException("The source iterator returned is null"));
                return;
            }
            final AtomicInteger wip = new AtomicInteger(1);
            if (UnsafeAccess.isUnsafeAvailable()) {
                queue = new MpscLinkedQueue<>();
            } else {
                queue = new MpscLinkedAtomicQueue<>();
            }
            while (!set.isUnsubscribed()) {
                try {
                    if (!iterator.hasNext()) {
                        if (wip.decrementAndGet() != 0) {
                            return;
                        }
                        if (queue.isEmpty()) {
                            s.onCompleted();
                            return;
                        } else {
                            s.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                            return;
                        }
                    } else if (!set.isUnsubscribed()) {
                        try {
                            Completable c = (Completable) iterator.next();
                            if (set.isUnsubscribed()) {
                                return;
                            }
                            if (c == null) {
                                queue.offer(new NullPointerException("A completable source is null"));
                                if (wip.decrementAndGet() != 0) {
                                    return;
                                }
                                if (queue.isEmpty()) {
                                    s.onCompleted();
                                    return;
                                } else {
                                    s.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                    return;
                                }
                            } else {
                                wip.getAndIncrement();
                                c.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeDelayErrorIterable.1
                                    @Override // rx.CompletableSubscriber
                                    public void onSubscribe(Subscription d) {
                                        set.add(d);
                                    }

                                    @Override // rx.CompletableSubscriber
                                    public void onError(Throwable e) {
                                        queue.offer(e);
                                        tryTerminate();
                                    }

                                    @Override // rx.CompletableSubscriber
                                    public void onCompleted() {
                                        tryTerminate();
                                    }

                                    void tryTerminate() {
                                        if (wip.decrementAndGet() != 0) {
                                            return;
                                        }
                                        if (queue.isEmpty()) {
                                            s.onCompleted();
                                        } else {
                                            s.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                        }
                                    }
                                });
                            }
                        } catch (Throwable e) {
                            queue.offer(e);
                            if (wip.decrementAndGet() != 0) {
                                return;
                            }
                            if (queue.isEmpty()) {
                                s.onCompleted();
                                return;
                            } else {
                                s.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                } catch (Throwable e2) {
                    queue.offer(e2);
                    if (wip.decrementAndGet() != 0) {
                        return;
                    }
                    if (queue.isEmpty()) {
                        s.onCompleted();
                        return;
                    } else {
                        s.onError(CompletableOnSubscribeMerge.collectErrors(queue));
                        return;
                    }
                }
            }
        } catch (Throwable e3) {
            s.onError(e3);
        }
    }
}

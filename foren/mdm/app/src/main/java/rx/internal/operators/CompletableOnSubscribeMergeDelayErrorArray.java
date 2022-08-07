package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes2.dex */
public final class CompletableOnSubscribeMergeDelayErrorArray implements Completable.OnSubscribe {
    final Completable[] sources;

    public CompletableOnSubscribeMergeDelayErrorArray(Completable[] sources) {
        this.sources = sources;
    }

    public void call(final CompletableSubscriber s) {
        final CompositeSubscription set = new CompositeSubscription();
        final AtomicInteger wip = new AtomicInteger(this.sources.length + 1);
        final Queue<Throwable> q = new ConcurrentLinkedQueue<>();
        s.onSubscribe(set);
        Completable[] arr$ = this.sources;
        for (Completable c : arr$) {
            if (!set.isUnsubscribed()) {
                if (c == null) {
                    q.offer(new NullPointerException("A completable source is null"));
                    wip.decrementAndGet();
                } else {
                    c.unsafeSubscribe(new CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeDelayErrorArray.1
                        @Override // rx.CompletableSubscriber
                        public void onSubscribe(Subscription d) {
                            set.add(d);
                        }

                        @Override // rx.CompletableSubscriber
                        public void onError(Throwable e) {
                            q.offer(e);
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
                            if (q.isEmpty()) {
                                s.onCompleted();
                            } else {
                                s.onError(CompletableOnSubscribeMerge.collectErrors(q));
                            }
                        }
                    });
                }
            } else {
                return;
            }
        }
        if (wip.decrementAndGet() != 0) {
            return;
        }
        if (q.isEmpty()) {
            s.onCompleted();
        } else {
            s.onError(CompletableOnSubscribeMerge.collectErrors(q));
        }
    }
}

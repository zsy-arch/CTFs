package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* loaded from: classes2.dex */
public final class OperatorTake<T> implements Observable.Operator<T, T> {
    final int limit;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorTake(int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("limit >= 0 required but it was " + limit);
        }
        this.limit = limit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: rx.internal.operators.OperatorTake$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends Subscriber<T> {
        boolean completed;
        int count;
        final /* synthetic */ Subscriber val$child;

        AnonymousClass1(Subscriber subscriber) {
            this.val$child = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.completed) {
                this.completed = true;
                this.val$child.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (!this.completed) {
                this.completed = true;
                try {
                    this.val$child.onError(e);
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // rx.Observer
        public void onNext(T i) {
            boolean stop;
            if (!isUnsubscribed()) {
                int i2 = this.count;
                this.count = i2 + 1;
                if (i2 < OperatorTake.this.limit) {
                    if (this.count == OperatorTake.this.limit) {
                        stop = true;
                    } else {
                        stop = false;
                    }
                    this.val$child.onNext(i);
                    if (stop && !this.completed) {
                        this.completed = true;
                        try {
                            this.val$child.onCompleted();
                        } finally {
                            unsubscribe();
                        }
                    }
                }
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(final Producer producer) {
            this.val$child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTake.1.1
                final AtomicLong requested = new AtomicLong(0);

                @Override // rx.Producer
                public void request(long n) {
                    long r;
                    long c;
                    if (n > 0 && !AnonymousClass1.this.completed) {
                        do {
                            r = this.requested.get();
                            c = Math.min(n, OperatorTake.this.limit - r);
                            if (c == 0) {
                                return;
                            }
                        } while (!this.requested.compareAndSet(r, r + c));
                        producer.request(c);
                    }
                }
            });
        }
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        AnonymousClass1 r0 = new AnonymousClass1(child);
        if (this.limit == 0) {
            child.onCompleted();
            r0.unsubscribe();
        }
        child.add(r0);
        return r0;
    }
}

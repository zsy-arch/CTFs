package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* loaded from: classes2.dex */
public final class OperatorSkip<T> implements Observable.Operator<T, T> {
    final int toSkip;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorSkip(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + n);
        }
        this.toSkip = n;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        return (Subscriber<T>) new Subscriber<T>(child) { // from class: rx.internal.operators.OperatorSkip.1
            int skipped;

            @Override // rx.Observer
            public void onCompleted() {
                child.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                child.onError(e);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                if (this.skipped >= OperatorSkip.this.toSkip) {
                    child.onNext(t);
                } else {
                    this.skipped++;
                }
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                child.setProducer(producer);
                producer.request(OperatorSkip.this.toSkip);
            }
        };
    }
}

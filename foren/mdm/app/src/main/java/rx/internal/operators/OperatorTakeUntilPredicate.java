package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;

/* loaded from: classes2.dex */
public final class OperatorTakeUntilPredicate<T> implements Observable.Operator<T, T> {
    final Func1<? super T, Boolean> stopPredicate;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorTakeUntilPredicate(Func1<? super T, Boolean> stopPredicate) {
        this.stopPredicate = stopPredicate;
    }

    public Subscriber<? super T> call(Subscriber<? super T> child) {
        final OperatorTakeUntilPredicate<T>.ParentSubscriber parent = new ParentSubscriber(child);
        child.add(parent);
        child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorTakeUntilPredicate.1
            @Override // rx.Producer
            public void request(long n) {
                parent.downstreamRequest(n);
            }
        });
        return parent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class ParentSubscriber extends Subscriber<T> {
        private final Subscriber<? super T> child;
        private boolean done;

        ParentSubscriber(Subscriber<? super T> child) {
            this.child = child;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.child.onNext(t);
            try {
                if (OperatorTakeUntilPredicate.this.stopPredicate.call(t).booleanValue()) {
                    this.done = true;
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable e) {
                this.done = true;
                Exceptions.throwOrReport(e, this.child, t);
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.done) {
                this.child.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            if (!this.done) {
                this.child.onError(e);
            }
        }

        void downstreamRequest(long n) {
            request(n);
        }
    }
}

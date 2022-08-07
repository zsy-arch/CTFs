package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* loaded from: classes2.dex */
public final class OperatorElementAt<T> implements Observable.Operator<T, T> {
    final T defaultValue;
    final boolean hasDefault;
    final int index;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorElementAt(int index) {
        this(index, null, false);
    }

    public OperatorElementAt(int index, T defaultValue) {
        this(index, defaultValue, true);
    }

    private OperatorElementAt(int index, T defaultValue, boolean hasDefault) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(index + " is out of bounds");
        }
        this.index = index;
        this.defaultValue = defaultValue;
        this.hasDefault = hasDefault;
    }

    public Subscriber<? super T> call(final Subscriber<? super T> child) {
        Subscriber subscriber = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorElementAt.1
            private int currentIndex;

            @Override // rx.Observer
            public void onNext(T value) {
                int i = this.currentIndex;
                this.currentIndex = i + 1;
                if (i == OperatorElementAt.this.index) {
                    child.onNext(value);
                    child.onCompleted();
                    unsubscribe();
                }
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                child.onError(e);
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (this.currentIndex > OperatorElementAt.this.index) {
                    return;
                }
                if (OperatorElementAt.this.hasDefault) {
                    child.onNext(OperatorElementAt.this.defaultValue);
                    child.onCompleted();
                    return;
                }
                child.onError(new IndexOutOfBoundsException(OperatorElementAt.this.index + " is out of bounds"));
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer p) {
                child.setProducer(new InnerProducer(p));
            }
        };
        child.add(subscriber);
        return subscriber;
    }

    /* loaded from: classes2.dex */
    static class InnerProducer extends AtomicBoolean implements Producer {
        private static final long serialVersionUID = 1;
        final Producer actual;

        public InnerProducer(Producer actual) {
            this.actual = actual;
        }

        @Override // rx.Producer
        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            } else if (n > 0 && compareAndSet(false, true)) {
                this.actual.request(Long.MAX_VALUE);
            }
        }
    }
}

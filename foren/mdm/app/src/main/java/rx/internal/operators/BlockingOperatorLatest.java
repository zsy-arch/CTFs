package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/* loaded from: classes2.dex */
public final class BlockingOperatorLatest {
    private BlockingOperatorLatest() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> latest(final Observable<? extends T> source) {
        return new Iterable<T>() { // from class: rx.internal.operators.BlockingOperatorLatest.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                LatestObserverIterator latestObserverIterator = new LatestObserverIterator();
                Observable.this.materialize().subscribe((Subscriber) latestObserverIterator);
                return latestObserverIterator;
            }
        };
    }

    /* loaded from: classes2.dex */
    static final class LatestObserverIterator<T> extends Subscriber<Notification<? extends T>> implements Iterator<T> {
        Notification<? extends T> iteratorNotification;
        final Semaphore notify = new Semaphore(0);
        final AtomicReference<Notification<? extends T>> value = new AtomicReference<>();

        LatestObserverIterator() {
        }

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object x0) {
            onNext((Notification) ((Notification) x0));
        }

        public void onNext(Notification<? extends T> args) {
            if (this.value.getAndSet(args) == null) {
                this.notify.release();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
        }

        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.iteratorNotification == null || !this.iteratorNotification.isOnError()) {
                if ((this.iteratorNotification == null || !this.iteratorNotification.isOnCompleted()) && this.iteratorNotification == null) {
                    try {
                        this.notify.acquire();
                        this.iteratorNotification = this.value.getAndSet(null);
                        if (this.iteratorNotification.isOnError()) {
                            throw Exceptions.propagate(this.iteratorNotification.getThrowable());
                        }
                    } catch (InterruptedException ex) {
                        unsubscribe();
                        Thread.currentThread().interrupt();
                        this.iteratorNotification = Notification.createOnError(ex);
                        throw Exceptions.propagate(ex);
                    }
                }
                return !this.iteratorNotification.isOnCompleted();
            }
            throw Exceptions.propagate(this.iteratorNotification.getThrowable());
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext() || !this.iteratorNotification.isOnNext()) {
                throw new NoSuchElementException();
            }
            T v = (T) this.iteratorNotification.getValue();
            this.iteratorNotification = null;
            return v;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }
}

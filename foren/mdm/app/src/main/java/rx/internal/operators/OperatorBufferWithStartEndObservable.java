package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;

/* loaded from: classes2.dex */
public final class OperatorBufferWithStartEndObservable<T, TOpening, TClosing> implements Observable.Operator<List<T>, T> {
    final Func1<? super TOpening, ? extends Observable<? extends TClosing>> bufferClosing;
    final Observable<? extends TOpening> bufferOpening;

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object x0) {
        return call((Subscriber) ((Subscriber) x0));
    }

    public OperatorBufferWithStartEndObservable(Observable<? extends TOpening> bufferOpenings, Func1<? super TOpening, ? extends Observable<? extends TClosing>> bufferClosingSelector) {
        this.bufferOpening = bufferOpenings;
        this.bufferClosing = bufferClosingSelector;
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> child) {
        final OperatorBufferWithStartEndObservable<T, TOpening, TClosing>.BufferingSubscriber s = new BufferingSubscriber(new SerializedSubscriber(child));
        Subscriber<TOpening> openSubscriber = new Subscriber<TOpening>() { // from class: rx.internal.operators.OperatorBufferWithStartEndObservable.1
            @Override // rx.Observer
            public void onNext(TOpening t) {
                s.startBuffer(t);
            }

            @Override // rx.Observer
            public void onError(Throwable e) {
                s.onError(e);
            }

            @Override // rx.Observer
            public void onCompleted() {
                s.onCompleted();
            }
        };
        child.add(openSubscriber);
        child.add(s);
        this.bufferOpening.unsafeSubscribe(openSubscriber);
        return s;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public final class BufferingSubscriber extends Subscriber<T> {
        final Subscriber<? super List<T>> child;
        final List<List<T>> chunks = new LinkedList();
        final CompositeSubscription closingSubscriptions = new CompositeSubscription();
        boolean done;

        public BufferingSubscriber(Subscriber<? super List<T>> child) {
            this.child = child;
            add(this.closingSubscriptions);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this) {
                for (List<T> chunk : this.chunks) {
                    chunk.add(t);
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable e) {
            synchronized (this) {
                if (!this.done) {
                    this.done = true;
                    this.chunks.clear();
                    this.child.onError(e);
                    unsubscribe();
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                synchronized (this) {
                    if (!this.done) {
                        this.done = true;
                        List<List<T>> toEmit = new LinkedList<>(this.chunks);
                        this.chunks.clear();
                        for (List<T> chunk : toEmit) {
                            this.child.onNext(chunk);
                        }
                        this.child.onCompleted();
                        unsubscribe();
                    }
                }
            } catch (Throwable t) {
                Exceptions.throwOrReport(t, this.child);
            }
        }

        void startBuffer(TOpening v) {
            final List<T> chunk = new ArrayList<>();
            synchronized (this) {
                if (!this.done) {
                    this.chunks.add(chunk);
                    try {
                        Observable<? extends TClosing> cobs = (Observable) OperatorBufferWithStartEndObservable.this.bufferClosing.call(v);
                        Subscriber<TClosing> closeSubscriber = new Subscriber<TClosing>() { // from class: rx.internal.operators.OperatorBufferWithStartEndObservable.BufferingSubscriber.1
                            @Override // rx.Observer
                            public void onNext(TClosing t) {
                                BufferingSubscriber.this.closingSubscriptions.remove(this);
                                BufferingSubscriber.this.endBuffer(chunk);
                            }

                            @Override // rx.Observer
                            public void onError(Throwable e) {
                                BufferingSubscriber.this.onError(e);
                            }

                            @Override // rx.Observer
                            public void onCompleted() {
                                BufferingSubscriber.this.closingSubscriptions.remove(this);
                                BufferingSubscriber.this.endBuffer(chunk);
                            }
                        };
                        this.closingSubscriptions.add(closeSubscriber);
                        cobs.unsafeSubscribe(closeSubscriber);
                    } catch (Throwable t) {
                        Exceptions.throwOrReport(t, this);
                    }
                }
            }
        }

        void endBuffer(List<T> toEnd) {
            boolean canEnd = false;
            synchronized (this) {
                if (!this.done) {
                    Iterator<List<T>> it = this.chunks.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (it.next() == toEnd) {
                                canEnd = true;
                                it.remove();
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (canEnd) {
                        this.child.onNext(toEnd);
                    }
                }
            }
        }
    }
}

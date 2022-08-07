package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/* loaded from: classes2.dex */
public final class OnSubscribeFromIterable<T> implements Observable.OnSubscribe<T> {
    final Iterable<? extends T> is;

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object x0) {
        call((Subscriber) ((Subscriber) x0));
    }

    public OnSubscribeFromIterable(Iterable<? extends T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("iterable must not be null");
        }
        this.is = iterable;
    }

    public void call(Subscriber<? super T> o) {
        try {
            Iterator<? extends T> it = this.is.iterator();
            boolean b = it.hasNext();
            if (o.isUnsubscribed()) {
                return;
            }
            if (!b) {
                o.onCompleted();
            } else {
                o.setProducer(new IterableProducer(o, it));
            }
        } catch (Throwable ex) {
            Exceptions.throwOrReport(ex, o);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class IterableProducer<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -8730475647105475802L;
        private final Iterator<? extends T> it;
        private final Subscriber<? super T> o;

        /* JADX INFO: Access modifiers changed from: package-private */
        public IterableProducer(Subscriber<? super T> o, Iterator<? extends T> it) {
            this.o = o;
            this.it = it;
        }

        @Override // rx.Producer
        public void request(long n) {
            if (get() != Long.MAX_VALUE) {
                if (n == Long.MAX_VALUE && compareAndSet(0L, Long.MAX_VALUE)) {
                    fastPath();
                } else if (n > 0 && BackpressureUtils.getAndAddRequest(this, n) == 0) {
                    slowPath(n);
                }
            }
        }

        void slowPath(long n) {
            Subscriber<? super T> o = this.o;
            Iterator<? extends T> it = this.it;
            long r = n;
            long e = 0;
            while (true) {
                if (e == r) {
                    r = get();
                    if (e == r) {
                        r = BackpressureUtils.produced(this, e);
                        if (r != 0) {
                            e = 0;
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                } else if (!o.isUnsubscribed()) {
                    try {
                        o.onNext((Object) it.next());
                        if (!o.isUnsubscribed()) {
                            try {
                                if (it.hasNext()) {
                                    e++;
                                } else if (!o.isUnsubscribed()) {
                                    o.onCompleted();
                                    return;
                                } else {
                                    return;
                                }
                            } catch (Throwable ex) {
                                Exceptions.throwOrReport(ex, o);
                                return;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable ex2) {
                        Exceptions.throwOrReport(ex2, o);
                        return;
                    }
                } else {
                    return;
                }
            }
        }

        void fastPath() {
            Subscriber<? super T> o = this.o;
            Iterator<? extends T> it = this.it;
            while (!o.isUnsubscribed()) {
                try {
                    o.onNext((Object) it.next());
                    if (!o.isUnsubscribed()) {
                        try {
                            if (!it.hasNext()) {
                                if (!o.isUnsubscribed()) {
                                    o.onCompleted();
                                    return;
                                }
                                return;
                            }
                        } catch (Throwable ex) {
                            Exceptions.throwOrReport(ex, o);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable ex2) {
                    Exceptions.throwOrReport(ex2, o);
                    return;
                }
            }
        }
    }
}

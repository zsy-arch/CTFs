package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;

/* loaded from: classes2.dex */
public final class OnSubscribeRange implements Observable.OnSubscribe<Integer> {
    private final int endIndex;
    private final int startIndex;

    public OnSubscribeRange(int start, int end) {
        this.startIndex = start;
        this.endIndex = end;
    }

    public void call(Subscriber<? super Integer> childSubscriber) {
        childSubscriber.setProducer(new RangeProducer(childSubscriber, this.startIndex, this.endIndex));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static final class RangeProducer extends AtomicLong implements Producer {
        private static final long serialVersionUID = 4114392207069098388L;
        private final Subscriber<? super Integer> childSubscriber;
        private long currentIndex;
        private final int endOfRange;

        RangeProducer(Subscriber<? super Integer> childSubscriber, int startIndex, int endIndex) {
            this.childSubscriber = childSubscriber;
            this.currentIndex = startIndex;
            this.endOfRange = endIndex;
        }

        @Override // rx.Producer
        public void request(long requestedAmount) {
            if (get() != Long.MAX_VALUE) {
                if (requestedAmount == Long.MAX_VALUE && compareAndSet(0L, Long.MAX_VALUE)) {
                    fastPath();
                } else if (requestedAmount > 0 && BackpressureUtils.getAndAddRequest(this, requestedAmount) == 0) {
                    slowPath(requestedAmount);
                }
            }
        }

        void slowPath(long requestedAmount) {
            long emitted = 0;
            long endIndex = this.endOfRange + 1;
            long index = this.currentIndex;
            Subscriber<? super Integer> childSubscriber = this.childSubscriber;
            while (true) {
                if (emitted == requestedAmount || index == endIndex) {
                    if (childSubscriber.isUnsubscribed()) {
                        return;
                    }
                    if (index == endIndex) {
                        childSubscriber.onCompleted();
                        return;
                    }
                    requestedAmount = get();
                    if (requestedAmount == emitted) {
                        this.currentIndex = index;
                        requestedAmount = addAndGet(-emitted);
                        if (requestedAmount != 0) {
                            emitted = 0;
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                } else if (!childSubscriber.isUnsubscribed()) {
                    childSubscriber.onNext(Integer.valueOf((int) index));
                    index++;
                    emitted++;
                } else {
                    return;
                }
            }
        }

        void fastPath() {
            long endIndex = this.endOfRange + 1;
            Subscriber<? super Integer> childSubscriber = this.childSubscriber;
            for (long index = this.currentIndex; index != endIndex; index++) {
                if (!childSubscriber.isUnsubscribed()) {
                    childSubscriber.onNext(Integer.valueOf((int) index));
                } else {
                    return;
                }
            }
            if (!childSubscriber.isUnsubscribed()) {
                childSubscriber.onCompleted();
            }
        }
    }
}

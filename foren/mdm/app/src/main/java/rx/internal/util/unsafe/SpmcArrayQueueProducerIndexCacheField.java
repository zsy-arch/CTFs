package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

/* compiled from: SpmcArrayQueue.java */
@SuppressAnimalSniffer
/* loaded from: classes2.dex */
abstract class SpmcArrayQueueProducerIndexCacheField<E> extends SpmcArrayQueueMidPad<E> {
    private volatile long producerIndexCache;

    public SpmcArrayQueueProducerIndexCacheField(int capacity) {
        super(capacity);
    }

    protected final long lvProducerIndexCache() {
        return this.producerIndexCache;
    }

    protected final void svProducerIndexCache(long v) {
        this.producerIndexCache = v;
    }
}

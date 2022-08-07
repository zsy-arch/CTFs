package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
/* loaded from: classes2.dex */
public final class SpmcArrayQueue<E> extends SpmcArrayQueueL3Pad<E> {
    public SpmcArrayQueue(int capacity) {
        super(capacity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        Object[] objArr = this.buffer;
        long lMask = this.mask;
        long currProducerIndex = lvProducerIndex();
        long offset = calcElementOffset(currProducerIndex);
        if (lvElement(objArr, offset) == null) {
            spElement(objArr, offset, e);
            soTail(1 + currProducerIndex);
            return true;
        } else if (currProducerIndex - lvConsumerIndex() > lMask) {
            return false;
        } else {
            do {
            } while (lvElement(objArr, offset) != null);
            spElement(objArr, offset, e);
            soTail(1 + currProducerIndex);
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public E poll() {
        long currentConsumerIndex;
        long currProducerIndexCache = lvProducerIndexCache();
        do {
            currentConsumerIndex = lvConsumerIndex();
            if (currentConsumerIndex >= currProducerIndexCache) {
                long currProducerIndex = lvProducerIndex();
                if (currentConsumerIndex >= currProducerIndex) {
                    return null;
                }
                svProducerIndexCache(currProducerIndex);
            }
        } while (!casHead(currentConsumerIndex, 1 + currentConsumerIndex));
        long offset = calcElementOffset(currentConsumerIndex);
        Object[] objArr = this.buffer;
        E e = (E) lpElement(objArr, offset);
        soElement(objArr, offset, null);
        return e;
    }

    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public E peek() {
        E e;
        long currProducerIndexCache = lvProducerIndexCache();
        do {
            long currentConsumerIndex = lvConsumerIndex();
            if (currentConsumerIndex >= currProducerIndexCache) {
                long currProducerIndex = lvProducerIndex();
                if (currentConsumerIndex >= currProducerIndex) {
                    return null;
                }
                svProducerIndexCache(currProducerIndex);
            }
            e = lvElement(calcElementOffset(currentConsumerIndex));
        } while (e == null);
        return e;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, rx.internal.util.unsafe.MessagePassingQueue
    public int size() {
        long currentProducerIndex;
        long after = lvConsumerIndex();
        do {
            currentProducerIndex = lvProducerIndex();
            after = lvConsumerIndex();
        } while (after != after);
        return (int) (currentProducerIndex - after);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, rx.internal.util.unsafe.MessagePassingQueue
    public boolean isEmpty() {
        return lvConsumerIndex() == lvProducerIndex();
    }
}

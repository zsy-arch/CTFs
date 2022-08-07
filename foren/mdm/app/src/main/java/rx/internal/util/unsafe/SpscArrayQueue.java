package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
/* loaded from: classes2.dex */
public final class SpscArrayQueue<E> extends SpscArrayQueueL3Pad<E> {
    public SpscArrayQueue(int capacity) {
        super(capacity);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("null elements not allowed");
        }
        Object[] objArr = this.buffer;
        long index = this.producerIndex;
        long offset = calcElementOffset(index);
        if (lvElement(objArr, offset) != null) {
            return false;
        }
        soElement(objArr, offset, e);
        soProducerIndex(1 + index);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public E poll() {
        long index = this.consumerIndex;
        long offset = calcElementOffset(index);
        Object[] objArr = this.buffer;
        E e = (E) lvElement(objArr, offset);
        if (e == null) {
            return null;
        }
        soElement(objArr, offset, null);
        soConsumerIndex(1 + index);
        return e;
    }

    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public E peek() {
        return lvElement(calcElementOffset(this.consumerIndex));
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
        return lvProducerIndex() == lvConsumerIndex();
    }

    private void soProducerIndex(long v) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, v);
    }

    private void soConsumerIndex(long v) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, C_INDEX_OFFSET, v);
    }

    private long lvProducerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, P_INDEX_OFFSET);
    }

    private long lvConsumerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, C_INDEX_OFFSET);
    }
}

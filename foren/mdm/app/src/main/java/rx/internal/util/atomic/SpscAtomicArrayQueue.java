package rx.internal.util.atomic;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: classes2.dex */
public final class SpscAtomicArrayQueue<E> extends AtomicReferenceArrayQueue<E> {
    private static final Integer MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    final int lookAheadStep;
    long producerLookAhead;
    final AtomicLong producerIndex = new AtomicLong();
    final AtomicLong consumerIndex = new AtomicLong();

    @Override // rx.internal.util.atomic.AtomicReferenceArrayQueue, java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // rx.internal.util.atomic.AtomicReferenceArrayQueue, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public SpscAtomicArrayQueue(int capacity) {
        super(capacity);
        this.lookAheadStep = Math.min(capacity / 4, MAX_LOOK_AHEAD_STEP.intValue());
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        AtomicReferenceArray<E> buffer = this.buffer;
        int mask = this.mask;
        long index = this.producerIndex.get();
        int offset = calcElementOffset(index, mask);
        if (index >= this.producerLookAhead) {
            int step = this.lookAheadStep;
            if (lvElement(buffer, calcElementOffset(step + index, mask)) == null) {
                this.producerLookAhead = step + index;
            } else if (lvElement(buffer, offset) != null) {
                return false;
            }
        }
        soElement(buffer, offset, e);
        soProducerIndex(1 + index);
        return true;
    }

    @Override // java.util.Queue
    public E poll() {
        long index = this.consumerIndex.get();
        int offset = calcElementOffset(index);
        AtomicReferenceArray<E> lElementBuffer = this.buffer;
        E e = lvElement(lElementBuffer, offset);
        if (e == null) {
            return null;
        }
        soElement(lElementBuffer, offset, null);
        soConsumerIndex(1 + index);
        return e;
    }

    @Override // java.util.Queue
    public E peek() {
        return lvElement(calcElementOffset(this.consumerIndex.get()));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        long currentProducerIndex;
        long after = lvConsumerIndex();
        do {
            currentProducerIndex = lvProducerIndex();
            after = lvConsumerIndex();
        } while (after != after);
        return (int) (currentProducerIndex - after);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return lvProducerIndex() == lvConsumerIndex();
    }

    private void soProducerIndex(long newIndex) {
        this.producerIndex.lazySet(newIndex);
    }

    private void soConsumerIndex(long newIndex) {
        this.consumerIndex.lazySet(newIndex);
    }

    private long lvConsumerIndex() {
        return this.consumerIndex.get();
    }

    private long lvProducerIndex() {
        return this.producerIndex.get();
    }
}

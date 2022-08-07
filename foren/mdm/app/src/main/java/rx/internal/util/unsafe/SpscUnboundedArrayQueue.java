package rx.internal.util.unsafe;

import java.util.Iterator;
import rx.internal.util.SuppressAnimalSniffer;

@SuppressAnimalSniffer
/* loaded from: classes2.dex */
public class SpscUnboundedArrayQueue<E> extends SpscUnboundedArrayQueueConsumerField<E> implements QueueProgressIndicators {
    private static final long C_INDEX_OFFSET;
    private static final long P_INDEX_OFFSET;
    private static final long REF_ARRAY_BASE;
    private static final int REF_ELEMENT_SHIFT;
    static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object HAS_NEXT = new Object();

    static {
        int scale = UnsafeAccess.UNSAFE.arrayIndexScale(Object[].class);
        if (4 == scale) {
            REF_ELEMENT_SHIFT = 2;
        } else if (8 == scale) {
            REF_ELEMENT_SHIFT = 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
        REF_ARRAY_BASE = UnsafeAccess.UNSAFE.arrayBaseOffset(Object[].class);
        try {
            P_INDEX_OFFSET = UnsafeAccess.UNSAFE.objectFieldOffset(SpscUnboundedArrayQueueProducerFields.class.getDeclaredField("producerIndex"));
            try {
                C_INDEX_OFFSET = UnsafeAccess.UNSAFE.objectFieldOffset(SpscUnboundedArrayQueueConsumerField.class.getDeclaredField("consumerIndex"));
            } catch (NoSuchFieldException e) {
                InternalError ex = new InternalError();
                ex.initCause(e);
                throw ex;
            }
        } catch (NoSuchFieldException e2) {
            InternalError ex2 = new InternalError();
            ex2.initCause(e2);
            throw ex2;
        }
    }

    public SpscUnboundedArrayQueue(int bufferSize) {
        int p2capacity = Pow2.roundToPowerOfTwo(bufferSize);
        long mask = p2capacity - 1;
        Object[] objArr = new Object[p2capacity + 1];
        this.producerBuffer = objArr;
        this.producerMask = mask;
        adjustLookAheadStep(p2capacity);
        this.consumerBuffer = objArr;
        this.consumerMask = mask;
        this.producerLookAhead = mask - 1;
        soProducerIndex(0L);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Queue
    public final boolean offer(E e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        Object[] objArr = this.producerBuffer;
        long index = this.producerIndex;
        long mask = this.producerMask;
        long offset = calcWrappedOffset(index, mask);
        if (index < this.producerLookAhead) {
            return writeToQueue(objArr, e, index, offset);
        }
        int lookAheadStep = this.producerLookAheadStep;
        if (lvElement(objArr, calcWrappedOffset(lookAheadStep + index, mask)) == null) {
            this.producerLookAhead = (lookAheadStep + index) - 1;
            return writeToQueue(objArr, e, index, offset);
        } else if (lvElement(objArr, calcWrappedOffset(1 + index, mask)) != null) {
            return writeToQueue(objArr, e, index, offset);
        } else {
            resize(objArr, index, offset, e, mask);
            return true;
        }
    }

    private boolean writeToQueue(E[] buffer, E e, long index, long offset) {
        soElement(buffer, offset, e);
        soProducerIndex(1 + index);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void resize(E[] oldBuffer, long currIndex, long offset, E e, long mask) {
        Object[] objArr = new Object[oldBuffer.length];
        this.producerBuffer = objArr;
        this.producerLookAhead = (currIndex + mask) - 1;
        soElement(objArr, offset, e);
        soNext(oldBuffer, objArr);
        soElement(oldBuffer, offset, HAS_NEXT);
        soProducerIndex(currIndex + 1);
    }

    private void soNext(E[] curr, E[] next) {
        soElement(curr, calcDirectOffset(curr.length - 1), next);
    }

    private E[] lvNext(E[] curr) {
        return (E[]) ((Object[]) lvElement(curr, calcDirectOffset(curr.length - 1)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Queue
    public final E poll() {
        Object[] objArr = this.consumerBuffer;
        long index = this.consumerIndex;
        long mask = this.consumerMask;
        long offset = calcWrappedOffset(index, mask);
        E e = (E) lvElement(objArr, offset);
        boolean isNextBuffer = e == HAS_NEXT;
        if (e != null && !isNextBuffer) {
            soElement(objArr, offset, null);
            soConsumerIndex(1 + index);
            return e;
        } else if (isNextBuffer) {
            return (E) newBufferPoll(lvNext(objArr), index, mask);
        } else {
            return null;
        }
    }

    private E newBufferPoll(E[] nextBuffer, long index, long mask) {
        this.consumerBuffer = nextBuffer;
        long offsetInNew = calcWrappedOffset(index, mask);
        E n = (E) lvElement(nextBuffer, offsetInNew);
        if (n == null) {
            return null;
        }
        soElement(nextBuffer, offsetInNew, null);
        soConsumerIndex(1 + index);
        return n;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Queue
    public final E peek() {
        Object[] objArr = this.consumerBuffer;
        long index = this.consumerIndex;
        long mask = this.consumerMask;
        E e = (E) lvElement(objArr, calcWrappedOffset(index, mask));
        if (e == HAS_NEXT) {
            return (E) newBufferPeek(lvNext(objArr), index, mask);
        }
        return e;
    }

    private E newBufferPeek(E[] nextBuffer, long index, long mask) {
        this.consumerBuffer = nextBuffer;
        return (E) lvElement(nextBuffer, calcWrappedOffset(index, mask));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        long currentProducerIndex;
        long after = lvConsumerIndex();
        do {
            currentProducerIndex = lvProducerIndex();
            after = lvConsumerIndex();
        } while (after != after);
        return (int) (currentProducerIndex - after);
    }

    private void adjustLookAheadStep(int capacity) {
        this.producerLookAheadStep = Math.min(capacity / 4, MAX_LOOK_AHEAD_STEP);
    }

    private long lvProducerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, P_INDEX_OFFSET);
    }

    private long lvConsumerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, C_INDEX_OFFSET);
    }

    private void soProducerIndex(long v) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, v);
    }

    private void soConsumerIndex(long v) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, C_INDEX_OFFSET, v);
    }

    private static long calcWrappedOffset(long index, long mask) {
        return calcDirectOffset(index & mask);
    }

    private static long calcDirectOffset(long index) {
        return REF_ARRAY_BASE + (index << REF_ELEMENT_SHIFT);
    }

    private static void soElement(Object[] buffer, long offset, Object e) {
        UnsafeAccess.UNSAFE.putOrderedObject(buffer, offset, e);
    }

    private static <E> Object lvElement(E[] buffer, long offset) {
        return UnsafeAccess.UNSAFE.getObjectVolatile(buffer, offset);
    }

    @Override // rx.internal.util.unsafe.QueueProgressIndicators
    public long currentProducerIndex() {
        return lvProducerIndex();
    }

    @Override // rx.internal.util.unsafe.QueueProgressIndicators
    public long currentConsumerIndex() {
        return lvConsumerIndex();
    }
}

package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

/* compiled from: SpscArrayQueue.java */
@SuppressAnimalSniffer
/* loaded from: classes2.dex */
abstract class SpscArrayQueueProducerFields<E> extends SpscArrayQueueL1Pad<E> {
    protected static final long P_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueProducerFields.class, "producerIndex");
    protected long producerIndex;
    protected long producerLookAhead;

    public SpscArrayQueueProducerFields(int capacity) {
        super(capacity);
    }
}

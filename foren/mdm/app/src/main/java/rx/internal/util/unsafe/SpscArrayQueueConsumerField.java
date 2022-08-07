package rx.internal.util.unsafe;

import rx.internal.util.SuppressAnimalSniffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SpscArrayQueue.java */
@SuppressAnimalSniffer
/* loaded from: classes2.dex */
public abstract class SpscArrayQueueConsumerField<E> extends SpscArrayQueueL2Pad<E> {
    protected static final long C_INDEX_OFFSET = UnsafeAccess.addressOf(SpscArrayQueueConsumerField.class, "consumerIndex");
    protected long consumerIndex;

    public SpscArrayQueueConsumerField(int capacity) {
        super(capacity);
    }
}

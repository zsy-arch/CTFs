package rx.internal.util.unsafe;

/* compiled from: SpscUnboundedArrayQueue.java */
/* loaded from: classes2.dex */
abstract class SpscUnboundedArrayQueueProducerColdFields<E> extends SpscUnboundedArrayQueueProducerFields<E> {
    protected E[] producerBuffer;
    protected long producerLookAhead;
    protected int producerLookAheadStep;
    protected long producerMask;
}

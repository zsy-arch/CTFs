package rx.internal.util.atomic;

/* loaded from: classes2.dex */
public final class MpscLinkedAtomicQueue<E> extends BaseLinkedAtomicQueue<E> {
    public MpscLinkedAtomicQueue() {
        LinkedQueueNode<E> node = new LinkedQueueNode<>();
        spConsumerNode(node);
        xchgProducerNode(node);
    }

    @Override // java.util.Queue
    public boolean offer(E nextValue) {
        if (nextValue == null) {
            throw new NullPointerException("null elements not allowed");
        }
        LinkedQueueNode<E> nextNode = new LinkedQueueNode<>(nextValue);
        xchgProducerNode(nextNode).soNext(nextNode);
        return true;
    }

    @Override // java.util.Queue
    public E poll() {
        LinkedQueueNode<E> nextNode;
        LinkedQueueNode<E> currConsumerNode = lpConsumerNode();
        LinkedQueueNode<E> nextNode2 = currConsumerNode.lvNext();
        if (nextNode2 != null) {
            E nextValue = nextNode2.getAndNullValue();
            spConsumerNode(nextNode2);
            return nextValue;
        } else if (currConsumerNode == lvProducerNode()) {
            return null;
        } else {
            do {
                nextNode = currConsumerNode.lvNext();
            } while (nextNode == null);
            E nextValue2 = nextNode.getAndNullValue();
            spConsumerNode(nextNode);
            return nextValue2;
        }
    }

    @Override // java.util.Queue
    public E peek() {
        LinkedQueueNode<E> nextNode;
        LinkedQueueNode<E> currConsumerNode = lpConsumerNode();
        LinkedQueueNode<E> nextNode2 = currConsumerNode.lvNext();
        if (nextNode2 != null) {
            return nextNode2.lpValue();
        }
        if (currConsumerNode == lvProducerNode()) {
            return null;
        }
        do {
            nextNode = currConsumerNode.lvNext();
        } while (nextNode == null);
        return nextNode.lpValue();
    }
}

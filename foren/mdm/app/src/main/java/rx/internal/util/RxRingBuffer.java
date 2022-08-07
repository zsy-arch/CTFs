package rx.internal.util;

import java.util.Queue;
import rx.Observer;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.operators.NotificationLite;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpmcArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;

/* loaded from: classes2.dex */
public class RxRingBuffer implements Subscription {
    public static final int SIZE;
    private Queue<Object> queue;
    private final int size;
    public volatile Object terminalState;

    static {
        int defaultSize = 128;
        if (PlatformDependent.isAndroid()) {
            defaultSize = 16;
        }
        String sizeFromProperty = System.getProperty("rx.ring-buffer.size");
        if (sizeFromProperty != null) {
            try {
                defaultSize = Integer.parseInt(sizeFromProperty);
            } catch (NumberFormatException e) {
                System.err.println("Failed to set 'rx.buffer.size' with value " + sizeFromProperty + " => " + e.getMessage());
            }
        }
        SIZE = defaultSize;
    }

    public static RxRingBuffer getSpscInstance() {
        return UnsafeAccess.isUnsafeAvailable() ? new RxRingBuffer(false, SIZE) : new RxRingBuffer();
    }

    public static RxRingBuffer getSpmcInstance() {
        return UnsafeAccess.isUnsafeAvailable() ? new RxRingBuffer(true, SIZE) : new RxRingBuffer();
    }

    private RxRingBuffer(Queue<Object> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    private RxRingBuffer(boolean spmc, int size) {
        this.queue = spmc ? new SpmcArrayQueue<>(size) : new SpscArrayQueue<>(size);
        this.size = size;
    }

    public synchronized void release() {
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        release();
    }

    RxRingBuffer() {
        this(new SpscAtomicArrayQueue(SIZE), SIZE);
    }

    public void onNext(Object o) throws MissingBackpressureException {
        boolean iae = false;
        boolean mbe = false;
        synchronized (this) {
            Queue<Object> q = this.queue;
            if (q != null) {
                mbe = !q.offer(NotificationLite.next(o));
            } else {
                iae = true;
            }
        }
        if (iae) {
            throw new IllegalStateException("This instance has been unsubscribed and the queue is no longer usable.");
        } else if (mbe) {
            throw new MissingBackpressureException();
        }
    }

    public void onCompleted() {
        if (this.terminalState == null) {
            this.terminalState = NotificationLite.completed();
        }
    }

    public void onError(Throwable t) {
        if (this.terminalState == null) {
            this.terminalState = NotificationLite.error(t);
        }
    }

    public int available() {
        return this.size - count();
    }

    public int capacity() {
        return this.size;
    }

    public int count() {
        Queue<Object> q = this.queue;
        if (q == null) {
            return 0;
        }
        return q.size();
    }

    public boolean isEmpty() {
        Queue<Object> q = this.queue;
        return q == null || q.isEmpty();
    }

    public Object poll() {
        Object o = null;
        synchronized (this) {
            Queue<Object> q = this.queue;
            if (q != null) {
                o = q.poll();
                Object ts = this.terminalState;
                if (o == null && ts != null && q.peek() == null) {
                    o = ts;
                    this.terminalState = null;
                }
            }
        }
        return o;
    }

    public Object peek() {
        Object o;
        synchronized (this) {
            Queue<Object> q = this.queue;
            if (q == null) {
                o = null;
            } else {
                o = q.peek();
                Object ts = this.terminalState;
                if (o == null && ts != null && q.peek() == null) {
                    o = ts;
                }
            }
        }
        return o;
    }

    public boolean isCompleted(Object o) {
        return NotificationLite.isCompleted(o);
    }

    public boolean isError(Object o) {
        return NotificationLite.isError(o);
    }

    public Object getValue(Object o) {
        return NotificationLite.getValue(o);
    }

    public boolean accept(Object o, Observer child) {
        return NotificationLite.accept(child, o);
    }

    public Throwable asError(Object o) {
        return NotificationLite.getError(o);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.queue == null;
    }
}

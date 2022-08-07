package rx.internal.operators;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;

/* loaded from: classes2.dex */
public final class BackpressureUtils {
    static final long COMPLETED_MASK = Long.MIN_VALUE;
    static final long REQUESTED_MASK = Long.MAX_VALUE;

    private BackpressureUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static long getAndAddRequest(AtomicLong requested, long n) {
        long current;
        do {
            current = requested.get();
        } while (!requested.compareAndSet(current, addCap(current, n)));
        return current;
    }

    public static long multiplyCap(long a, long b) {
        long u2 = a * b;
        if (((a | b) >>> 31) == 0 || b == 0 || u2 / b == a) {
            return u2;
        }
        return REQUESTED_MASK;
    }

    public static long addCap(long a, long b) {
        long u2 = a + b;
        if (u2 < 0) {
            return REQUESTED_MASK;
        }
        return u2;
    }

    public static <T> void postCompleteDone(AtomicLong requested, Queue<T> queue, Subscriber<? super T> actual) {
        postCompleteDone(requested, queue, actual, UtilityFunctions.identity());
    }

    public static <T> boolean postCompleteRequest(AtomicLong requested, long n, Queue<T> queue, Subscriber<? super T> actual) {
        return postCompleteRequest(requested, n, queue, actual, UtilityFunctions.identity());
    }

    public static <T, R> void postCompleteDone(AtomicLong requested, Queue<T> queue, Subscriber<? super R> actual, Func1<? super T, ? extends R> exitTransform) {
        long r;
        do {
            r = requested.get();
            if ((r & COMPLETED_MASK) != 0) {
                return;
            }
        } while (!requested.compareAndSet(r, r | COMPLETED_MASK));
        if (r != 0) {
            postCompleteDrain(requested, queue, actual, exitTransform);
        }
    }

    public static <T, R> boolean postCompleteRequest(AtomicLong requested, long n, Queue<T> queue, Subscriber<? super R> actual, Func1<? super T, ? extends R> exitTransform) {
        long r;
        long c;
        if (n < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + n);
        } else if (n == 0) {
            return (requested.get() & COMPLETED_MASK) == 0;
        } else {
            do {
                r = requested.get();
                c = r & COMPLETED_MASK;
            } while (!requested.compareAndSet(r, addCap(r & REQUESTED_MASK, n) | c));
            if (r != COMPLETED_MASK) {
                return c == 0;
            }
            postCompleteDrain(requested, queue, actual, exitTransform);
            return false;
        }
    }

    static <T, R> void postCompleteDrain(AtomicLong requested, Queue<T> queue, Subscriber<? super R> subscriber, Func1<? super T, ? extends R> exitTransform) {
        long r = requested.get();
        if (r == REQUESTED_MASK) {
            while (!subscriber.isUnsubscribed()) {
                Object poll = queue.poll();
                if (poll == null) {
                    subscriber.onCompleted();
                    return;
                }
                subscriber.onNext((Object) exitTransform.call(poll));
            }
            return;
        }
        long e = COMPLETED_MASK;
        while (true) {
            if (e == r) {
                if (e == r) {
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    if (queue.isEmpty()) {
                        subscriber.onCompleted();
                        return;
                    }
                }
                r = requested.get();
                if (r == e) {
                    r = requested.addAndGet(-(e & REQUESTED_MASK));
                    if (r != COMPLETED_MASK) {
                        e = COMPLETED_MASK;
                    } else {
                        return;
                    }
                } else {
                    continue;
                }
            } else if (!subscriber.isUnsubscribed()) {
                Object poll2 = queue.poll();
                if (poll2 == null) {
                    subscriber.onCompleted();
                    return;
                } else {
                    subscriber.onNext((Object) exitTransform.call(poll2));
                    e++;
                }
            } else {
                return;
            }
        }
    }

    public static long produced(AtomicLong requested, long n) {
        long current;
        long next;
        do {
            current = requested.get();
            if (current == REQUESTED_MASK) {
                return REQUESTED_MASK;
            }
            next = current - n;
            if (next < 0) {
                throw new IllegalStateException("More produced than requested: " + next);
            }
        } while (!requested.compareAndSet(current, next));
        return next;
    }

    public static boolean validate(long n) {
        if (n >= 0) {
            return n != 0;
        }
        throw new IllegalArgumentException("n >= 0 required but it was " + n);
    }
}

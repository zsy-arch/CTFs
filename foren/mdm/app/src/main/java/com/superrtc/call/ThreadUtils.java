package com.superrtc.call;

import android.os.Handler;
import android.os.SystemClock;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class ThreadUtils {

    /* loaded from: classes2.dex */
    public interface BlockingOperation {
        void run() throws InterruptedException;
    }

    /* loaded from: classes2.dex */
    public static class ThreadChecker {
        private Thread thread = Thread.currentThread();

        public void checkIsOnValidThread() {
            if (this.thread == null) {
                this.thread = Thread.currentThread();
            }
            if (Thread.currentThread() != this.thread) {
                throw new IllegalStateException("Wrong thread");
            }
        }

        public void detachThread() {
            this.thread = null;
        }
    }

    public static void executeUninterruptibly(BlockingOperation operation) {
        boolean wasInterrupted = false;
        while (true) {
            try {
                operation.run();
                break;
            } catch (InterruptedException e) {
                wasInterrupted = true;
            }
        }
        if (wasInterrupted) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean joinUninterruptibly(Thread thread, long timeoutMs) {
        long startTimeMs = SystemClock.elapsedRealtime();
        long timeRemainingMs = timeoutMs;
        boolean wasInterrupted = false;
        while (timeRemainingMs > 0) {
            try {
                thread.join(timeRemainingMs);
                break;
            } catch (InterruptedException e) {
                wasInterrupted = true;
                timeRemainingMs = timeoutMs - (SystemClock.elapsedRealtime() - startTimeMs);
            }
        }
        if (wasInterrupted) {
            Thread.currentThread().interrupt();
        }
        return !thread.isAlive();
    }

    public static void joinUninterruptibly(final Thread thread) {
        executeUninterruptibly(new BlockingOperation() { // from class: com.superrtc.call.ThreadUtils.1
            @Override // com.superrtc.call.ThreadUtils.BlockingOperation
            public void run() throws InterruptedException {
                thread.join();
            }
        });
    }

    public static void awaitUninterruptibly(final CountDownLatch latch) {
        executeUninterruptibly(new BlockingOperation() { // from class: com.superrtc.call.ThreadUtils.2
            @Override // com.superrtc.call.ThreadUtils.BlockingOperation
            public void run() throws InterruptedException {
                latch.await();
            }
        });
    }

    public static boolean awaitUninterruptibly(CountDownLatch barrier, long timeoutMs) {
        long startTimeMs = SystemClock.elapsedRealtime();
        long timeRemainingMs = timeoutMs;
        boolean wasInterrupted = false;
        boolean result = false;
        do {
            try {
                result = barrier.await(timeRemainingMs, TimeUnit.MILLISECONDS);
                break;
            } catch (InterruptedException e) {
                wasInterrupted = true;
                timeRemainingMs = timeoutMs - (SystemClock.elapsedRealtime() - startTimeMs);
                if (timeRemainingMs <= 0) {
                }
            }
        } while (timeRemainingMs <= 0);
        if (wasInterrupted) {
            Thread.currentThread().interrupt();
        }
        return result;
    }

    /* renamed from: com.superrtc.call.ThreadUtils$1Result  reason: invalid class name */
    /* loaded from: classes2.dex */
    class AnonymousClass1Result {
        public V value;

        AnonymousClass1Result() {
        }
    }

    public static <V> V invokeUninterruptibly(Handler handler, final Callable<V> callable) {
        final AnonymousClass1Result result = new AnonymousClass1Result();
        final CountDownLatch barrier = new CountDownLatch(1);
        handler.post(new Runnable() { // from class: com.superrtc.call.ThreadUtils.3
            /* JADX WARN: Type inference failed for: r2v4, types: [V, java.lang.Object] */
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AnonymousClass1Result.this.value = callable.call();
                    barrier.countDown();
                } catch (Exception e) {
                    throw new RuntimeException("Callable threw exception: " + e);
                }
            }
        });
        awaitUninterruptibly(barrier);
        return result.value;
    }

    public static void invokeUninterruptibly(Handler handler, final Runnable runner) {
        final CountDownLatch barrier = new CountDownLatch(1);
        handler.post(new Runnable() { // from class: com.superrtc.call.ThreadUtils.4
            @Override // java.lang.Runnable
            public void run() {
                runner.run();
                barrier.countDown();
            }
        });
        awaitUninterruptibly(barrier);
    }
}

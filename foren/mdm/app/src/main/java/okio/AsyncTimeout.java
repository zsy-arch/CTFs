package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alipay.sdk.data.a;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class AsyncTimeout extends Timeout {
    private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60);
    private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    @Nullable
    static AsyncTimeout head;
    private boolean inQueue;
    @Nullable
    private AsyncTimeout next;
    private long timeoutAt;

    public final void enter() {
        if (this.inQueue) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            this.inQueue = true;
            scheduleTimeout(this, timeoutNanos, hasDeadline);
        }
    }

    private static synchronized void scheduleTimeout(AsyncTimeout node, long timeoutNanos, boolean hasDeadline) {
        synchronized (AsyncTimeout.class) {
            if (head == null) {
                head = new AsyncTimeout();
                new Watchdog().start();
            }
            long now = System.nanoTime();
            if (timeoutNanos != 0 && hasDeadline) {
                node.timeoutAt = Math.min(timeoutNanos, node.deadlineNanoTime() - now) + now;
            } else if (timeoutNanos != 0) {
                node.timeoutAt = now + timeoutNanos;
            } else if (hasDeadline) {
                node.timeoutAt = node.deadlineNanoTime();
            } else {
                throw new AssertionError();
            }
            long remainingNanos = node.remainingNanos(now);
            AsyncTimeout prev = head;
            while (prev.next != null && remainingNanos >= prev.next.remainingNanos(now)) {
                prev = prev.next;
            }
            node.next = prev.next;
            prev.next = node;
            if (prev == head) {
                AsyncTimeout.class.notify();
            }
        }
    }

    public final boolean exit() {
        if (!this.inQueue) {
            return false;
        }
        this.inQueue = false;
        return cancelScheduledTimeout(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000b, code lost:
        r0.next = r3.next;
        r3.next = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0012, code lost:
        r1 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized boolean cancelScheduledTimeout(okio.AsyncTimeout r3) {
        /*
            java.lang.Class<okio.AsyncTimeout> r2 = okio.AsyncTimeout.class
            monitor-enter(r2)
            okio.AsyncTimeout r0 = okio.AsyncTimeout.head     // Catch: all -> 0x001a
        L_0x0005:
            if (r0 == 0) goto L_0x0018
            okio.AsyncTimeout r1 = r0.next     // Catch: all -> 0x001a
            if (r1 != r3) goto L_0x0015
            okio.AsyncTimeout r1 = r3.next     // Catch: all -> 0x001a
            r0.next = r1     // Catch: all -> 0x001a
            r1 = 0
            r3.next = r1     // Catch: all -> 0x001a
            r1 = 0
        L_0x0013:
            monitor-exit(r2)
            return r1
        L_0x0015:
            okio.AsyncTimeout r0 = r0.next     // Catch: all -> 0x001a
            goto L_0x0005
        L_0x0018:
            r1 = 1
            goto L_0x0013
        L_0x001a:
            r1 = move-exception
            monitor-exit(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.cancelScheduledTimeout(okio.AsyncTimeout):boolean");
    }

    private long remainingNanos(long now) {
        return this.timeoutAt - now;
    }

    protected void timedOut() {
    }

    public final Sink sink(final Sink sink) {
        return new Sink() { // from class: okio.AsyncTimeout.1
            @Override // okio.Sink
            public void write(Buffer source, long byteCount) throws IOException {
                Util.checkOffsetAndCount(source.size, 0L, byteCount);
                while (byteCount > 0) {
                    long toWrite = 0;
                    Segment s = source.head;
                    while (true) {
                        if (toWrite >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                            break;
                        }
                        toWrite += source.head.limit - source.head.pos;
                        if (toWrite >= byteCount) {
                            toWrite = byteCount;
                            break;
                        }
                        s = s.next;
                    }
                    boolean throwOnTimeout = false;
                    AsyncTimeout.this.enter();
                    try {
                        try {
                            sink.write(source, toWrite);
                            byteCount -= toWrite;
                            throwOnTimeout = true;
                        } catch (IOException e) {
                            throw AsyncTimeout.this.exit(e);
                        }
                    } finally {
                        AsyncTimeout.this.exit(throwOnTimeout);
                    }
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    try {
                        sink.flush();
                        throwOnTimeout = true;
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    try {
                        sink.close();
                        throwOnTimeout = true;
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override // okio.Sink
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + sink + ")";
            }
        };
    }

    public final Source source(final Source source) {
        return new Source() { // from class: okio.AsyncTimeout.2
            @Override // okio.Source
            public long read(Buffer sink, long byteCount) throws IOException {
                boolean throwOnTimeout = false;
                AsyncTimeout.this.enter();
                try {
                    try {
                        throwOnTimeout = true;
                        return source.read(sink, byteCount);
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                boolean throwOnTimeout;
                try {
                    throwOnTimeout = false;
                    try {
                        source.close();
                        throwOnTimeout = true;
                    } catch (IOException e) {
                        throw AsyncTimeout.this.exit(e);
                    }
                } finally {
                    AsyncTimeout.this.exit(throwOnTimeout);
                }
            }

            @Override // okio.Source
            public Timeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + source + ")";
            }
        };
    }

    final void exit(boolean throwOnTimeout) throws IOException {
        if (exit() && throwOnTimeout) {
            throw newTimeoutException(null);
        }
    }

    final IOException exit(IOException cause) throws IOException {
        return !exit() ? cause : newTimeoutException(cause);
    }

    protected IOException newTimeoutException(@Nullable IOException cause) {
        InterruptedIOException e = new InterruptedIOException(a.f);
        if (cause != null) {
            e.initCause(cause);
        }
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class Watchdog extends Thread {
        Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x001a, code lost:
            r0.timedOut();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r3 = this;
            L_0x0000:
                java.lang.Class<okio.AsyncTimeout> r2 = okio.AsyncTimeout.class
                monitor-enter(r2)     // Catch: InterruptedException -> 0x000e
                okio.AsyncTimeout r0 = okio.AsyncTimeout.awaitTimeout()     // Catch: all -> 0x000b
                if (r0 != 0) goto L_0x0010
                monitor-exit(r2)     // Catch: all -> 0x000b
                goto L_0x0000
            L_0x000b:
                r1 = move-exception
                monitor-exit(r2)     // Catch: all -> 0x000b
                throw r1     // Catch: InterruptedException -> 0x000e
            L_0x000e:
                r1 = move-exception
                goto L_0x0000
            L_0x0010:
                okio.AsyncTimeout r1 = okio.AsyncTimeout.head     // Catch: all -> 0x000b
                if (r0 != r1) goto L_0x0019
                r1 = 0
                okio.AsyncTimeout.head = r1     // Catch: all -> 0x000b
                monitor-exit(r2)     // Catch: all -> 0x000b
                return
            L_0x0019:
                monitor-exit(r2)     // Catch: all -> 0x000b
                r0.timedOut()     // Catch: InterruptedException -> 0x000e
                goto L_0x0000
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.AsyncTimeout.Watchdog.run():void");
        }
    }

    @Nullable
    static AsyncTimeout awaitTimeout() throws InterruptedException {
        AsyncTimeout node = head.next;
        if (node == null) {
            long startNanos = System.nanoTime();
            AsyncTimeout.class.wait(IDLE_TIMEOUT_MILLIS);
            if (head.next != null || System.nanoTime() - startNanos < IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return head;
        }
        long waitNanos = node.remainingNanos(System.nanoTime());
        if (waitNanos > 0) {
            long waitMillis = waitNanos / 1000000;
            AsyncTimeout.class.wait(waitMillis, (int) (waitNanos - (waitMillis * 1000000)));
            return null;
        }
        head.next = node.next;
        node.next = null;
        return node;
    }
}

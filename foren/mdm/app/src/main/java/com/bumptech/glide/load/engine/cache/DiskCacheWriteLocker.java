package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.Key;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
final class DiskCacheWriteLocker {
    private final Map<Key, WriteLock> locks = new HashMap();
    private final WriteLockPool writeLockPool = new WriteLockPool();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void acquire(Key key) {
        WriteLock writeLock;
        synchronized (this) {
            writeLock = this.locks.get(key);
            if (writeLock == null) {
                writeLock = this.writeLockPool.obtain();
                this.locks.put(key, writeLock);
            }
            writeLock.interestedThreads++;
        }
        writeLock.lock.lock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release(Key key) {
        WriteLock writeLock;
        synchronized (this) {
            writeLock = this.locks.get(key);
            if (writeLock == null || writeLock.interestedThreads <= 0) {
                throw new IllegalArgumentException("Cannot release a lock that is not held, key: " + key + ", interestedThreads: " + (writeLock == null ? 0 : writeLock.interestedThreads));
            }
            int i = writeLock.interestedThreads - 1;
            writeLock.interestedThreads = i;
            if (i == 0) {
                WriteLock removed = this.locks.remove(key);
                if (!removed.equals(writeLock)) {
                    throw new IllegalStateException("Removed the wrong lock, expected to remove: " + writeLock + ", but actually removed: " + removed + ", key: " + key);
                }
                this.writeLockPool.offer(removed);
            }
        }
        writeLock.lock.unlock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class WriteLock {
        int interestedThreads;
        final Lock lock;

        private WriteLock() {
            this.lock = new ReentrantLock();
        }
    }

    /* loaded from: classes.dex */
    private static class WriteLockPool {
        private static final int MAX_POOL_SIZE = 10;
        private final Queue<WriteLock> pool;

        private WriteLockPool() {
            this.pool = new ArrayDeque();
        }

        WriteLock obtain() {
            WriteLock result;
            synchronized (this.pool) {
                result = this.pool.poll();
            }
            if (result == null) {
                return new WriteLock();
            }
            return result;
        }

        void offer(WriteLock writeLock) {
            synchronized (this.pool) {
                if (this.pool.size() < 10) {
                    this.pool.offer(writeLock);
                }
            }
        }
    }
}

package com.mob.tools.utils;

import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/* loaded from: classes2.dex */
public class FileLocker {
    private FileOutputStream fos;
    private FileLock lock;

    public synchronized void lock(Runnable onLock, boolean block) {
        if (lock(block) && onLock != null) {
            onLock.run();
        }
    }

    public synchronized boolean lock(boolean block) {
        boolean z = false;
        synchronized (this) {
            if (this.fos != null) {
                if (block) {
                    this.lock = this.fos.getChannel().lock();
                } else {
                    this.lock = this.fos.getChannel().tryLock();
                }
                if (this.lock != null) {
                    z = true;
                }
            }
        }
        return z;
    }

    public synchronized void release() {
        if (this.fos != null) {
            unlock();
            try {
                this.fos.close();
                this.fos = null;
            } catch (Throwable th) {
            }
        }
    }

    public synchronized void setLockFile(String path) {
        this.fos = new FileOutputStream(path);
    }

    public synchronized void unlock() {
        if (this.lock != null) {
            try {
                this.lock.release();
                this.lock = null;
            } catch (Throwable th) {
            }
        }
    }
}

package com.bumptech.glide.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

/* loaded from: classes.dex */
public class ExceptionCatchingInputStream extends InputStream {
    private static final Queue<ExceptionCatchingInputStream> QUEUE = Util.createQueue(0);
    private IOException exception;
    private InputStream wrapped;

    public static ExceptionCatchingInputStream obtain(InputStream toWrap) {
        ExceptionCatchingInputStream result;
        synchronized (QUEUE) {
            result = QUEUE.poll();
        }
        if (result == null) {
            result = new ExceptionCatchingInputStream();
        }
        result.setInputStream(toWrap);
        return result;
    }

    static void clearQueue() {
        while (!QUEUE.isEmpty()) {
            QUEUE.remove();
        }
    }

    ExceptionCatchingInputStream() {
    }

    void setInputStream(InputStream toWrap) {
        this.wrapped = toWrap;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.wrapped.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.wrapped.close();
    }

    @Override // java.io.InputStream
    public void mark(int readlimit) {
        this.wrapped.mark(readlimit);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.wrapped.markSupported();
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer) throws IOException {
        try {
            return this.wrapped.read(buffer);
        } catch (IOException e) {
            this.exception = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        try {
            return this.wrapped.read(buffer, byteOffset, byteCount);
        } catch (IOException e) {
            this.exception = e;
            return -1;
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.wrapped.reset();
    }

    @Override // java.io.InputStream
    public long skip(long byteCount) throws IOException {
        try {
            return this.wrapped.skip(byteCount);
        } catch (IOException e) {
            this.exception = e;
            return 0L;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        try {
            return this.wrapped.read();
        } catch (IOException e) {
            this.exception = e;
            return -1;
        }
    }

    public IOException getException() {
        return this.exception;
    }

    public void release() {
        this.exception = null;
        this.wrapped = null;
        synchronized (QUEUE) {
            QUEUE.offer(this);
        }
    }
}

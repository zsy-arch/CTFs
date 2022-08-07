package com.mob.tools.network;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class ByteCounterInputStream extends InputStream {
    private InputStream is;
    private OnReadListener listener;
    private long readBytes;

    public ByteCounterInputStream(InputStream is) {
        this.is = is;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.is.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.is.close();
    }

    @Override // java.io.InputStream
    public void mark(int readlimit) {
        this.is.mark(readlimit);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.is.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int data = this.is.read();
        if (data >= 0) {
            this.readBytes++;
            if (this.listener != null) {
                this.listener.onRead(this.readBytes);
            }
        }
        return data;
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        int len = this.is.read(buffer, byteOffset, byteCount);
        if (len > 0) {
            this.readBytes += len;
            if (this.listener != null) {
                this.listener.onRead(this.readBytes);
            }
        }
        return len;
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.is.reset();
        this.readBytes = 0L;
    }

    public void setOnInputStreamReadListener(OnReadListener l) {
        this.listener = l;
    }

    @Override // java.io.InputStream
    public long skip(long byteCount) throws IOException {
        return this.is.skip(byteCount);
    }
}

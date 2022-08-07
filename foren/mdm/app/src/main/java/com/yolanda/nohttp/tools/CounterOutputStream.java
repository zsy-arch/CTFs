package com.yolanda.nohttp.tools;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes2.dex */
public class CounterOutputStream extends OutputStream {
    private final AtomicLong length = new AtomicLong(0);

    public void write(long count) {
        if (count > 0) {
            this.length.addAndGet(count);
        }
    }

    public long get() {
        return this.length.get();
    }

    @Override // java.io.OutputStream
    public void write(int oneByte) throws IOException {
        this.length.addAndGet(oneByte);
    }

    @Override // java.io.OutputStream
    public void write(byte[] buffer) throws IOException {
        this.length.addAndGet(buffer.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] buffer, int offset, int count) throws IOException {
        this.length.addAndGet(count);
    }
}

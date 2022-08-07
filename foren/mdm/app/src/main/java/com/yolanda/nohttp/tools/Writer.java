package com.yolanda.nohttp.tools;

import com.yolanda.nohttp.Binary;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class Writer {
    private boolean isPrint;
    private OutputStream mOutputStream;

    public Writer(OutputStream outputStream) {
        this.mOutputStream = outputStream;
    }

    public Writer(OutputStream outputStream, boolean isPrint) {
        this.mOutputStream = outputStream;
        this.isPrint = isPrint;
    }

    public void write(int b) throws IOException {
        this.mOutputStream.write(b);
    }

    public void write(byte[] b) throws IOException {
        this.mOutputStream.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        this.mOutputStream.write(b, off, len);
    }

    public void write(Binary binary) {
        if (this.mOutputStream instanceof CounterOutputStream) {
            ((CounterOutputStream) this.mOutputStream).write(binary.getLength());
            return;
        }
        binary.onWriteBinary(this.mOutputStream);
        binary.finish(true);
    }

    public boolean isPrint() {
        return this.isPrint;
    }
}

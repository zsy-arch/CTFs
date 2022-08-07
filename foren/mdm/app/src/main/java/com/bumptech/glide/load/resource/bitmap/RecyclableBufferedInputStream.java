package com.bumptech.glide.load.resource.bitmap;

import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class RecyclableBufferedInputStream extends FilterInputStream {
    private static final String TAG = "BufferedIs";
    private volatile byte[] buf;
    private int count;
    private int marklimit;
    private int markpos = -1;
    private int pos;

    public RecyclableBufferedInputStream(InputStream in, byte[] buffer) {
        super(in);
        if (buffer == null || buffer.length == 0) {
            throw new IllegalArgumentException("buffer is null or empty");
        }
        this.buf = buffer;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        InputStream localIn;
        localIn = this.in;
        if (this.buf == null || localIn == null) {
            throw streamClosed();
        }
        return (this.count - this.pos) + localIn.available();
    }

    private static IOException streamClosed() throws IOException {
        throw new IOException("BufferedInputStream is closed");
    }

    public synchronized void fixMarkLimit() {
        this.marklimit = this.buf.length;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.buf = null;
        InputStream localIn = this.in;
        this.in = null;
        if (localIn != null) {
            localIn.close();
        }
    }

    private int fillbuf(InputStream localIn, byte[] localBuf) throws IOException {
        if (this.markpos == -1 || this.pos - this.markpos >= this.marklimit) {
            int result = localIn.read(localBuf);
            if (result > 0) {
                this.markpos = -1;
                this.pos = 0;
                this.count = result;
            }
            return result;
        }
        if (this.markpos == 0 && this.marklimit > localBuf.length && this.count == localBuf.length) {
            int newLength = localBuf.length * 2;
            if (newLength > this.marklimit) {
                newLength = this.marklimit;
            }
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "allocate buffer of length: " + newLength);
            }
            byte[] newbuf = new byte[newLength];
            System.arraycopy(localBuf, 0, newbuf, 0, localBuf.length);
            this.buf = newbuf;
            localBuf = newbuf;
        } else if (this.markpos > 0) {
            System.arraycopy(localBuf, this.markpos, localBuf, 0, localBuf.length - this.markpos);
        }
        this.pos -= this.markpos;
        this.markpos = 0;
        this.count = 0;
        int bytesread = localIn.read(localBuf, this.pos, localBuf.length - this.pos);
        this.count = bytesread <= 0 ? this.pos : this.pos + bytesread;
        return bytesread;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readlimit) {
        this.marklimit = Math.max(this.marklimit, readlimit);
        this.markpos = this.pos;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        int i = -1;
        synchronized (this) {
            byte[] localBuf = this.buf;
            InputStream localIn = this.in;
            if (localBuf == null || localIn == null) {
                throw streamClosed();
            } else if (this.pos < this.count || fillbuf(localIn, localBuf) != -1) {
                if (localBuf != this.buf && (localBuf = this.buf) == null) {
                    throw streamClosed();
                } else if (this.count - this.pos > 0) {
                    int i2 = this.pos;
                    this.pos = i2 + 1;
                    i = localBuf[i2] & 255;
                }
            }
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0092 A[Catch: all -> 0x000b, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0002, B:6:0x0006, B:7:0x000a, B:14:0x0013, B:16:0x0017, B:17:0x001b, B:18:0x001c, B:20:0x0022, B:23:0x002a, B:25:0x0036, B:28:0x003e, B:29:0x0045, B:30:0x0048, B:32:0x004c, B:34:0x004f, B:37:0x0057, B:39:0x005c, B:42:0x0064, B:43:0x0067, B:45:0x006b, B:47:0x006f, B:48:0x0073, B:49:0x0074, B:52:0x007c, B:53:0x0086, B:56:0x008b, B:57:0x0092), top: B:61:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0089 A[SYNTHETIC] */
    @Override // java.io.FilterInputStream, java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int read(byte[] r9, int r10, int r11) throws java.io.IOException {
        /*
            r8 = this;
            r5 = -1
            monitor-enter(r8)
            byte[] r1 = r8.buf     // Catch: all -> 0x000b
            if (r1 != 0) goto L_0x000e
            java.io.IOException r5 = streamClosed()     // Catch: all -> 0x000b
            throw r5     // Catch: all -> 0x000b
        L_0x000b:
            r5 = move-exception
            monitor-exit(r8)
            throw r5
        L_0x000e:
            if (r11 != 0) goto L_0x0013
            r5 = 0
        L_0x0011:
            monitor-exit(r8)
            return r5
        L_0x0013:
            java.io.InputStream r2 = r8.in     // Catch: all -> 0x000b
            if (r2 != 0) goto L_0x001c
            java.io.IOException r5 = streamClosed()     // Catch: all -> 0x000b
            throw r5     // Catch: all -> 0x000b
        L_0x001c:
            int r6 = r8.pos     // Catch: all -> 0x000b
            int r7 = r8.count     // Catch: all -> 0x000b
            if (r6 >= r7) goto L_0x005a
            int r6 = r8.count     // Catch: all -> 0x000b
            int r7 = r8.pos     // Catch: all -> 0x000b
            int r6 = r6 - r7
            if (r6 < r11) goto L_0x003e
            r0 = r11
        L_0x002a:
            int r6 = r8.pos     // Catch: all -> 0x000b
            java.lang.System.arraycopy(r1, r6, r9, r10, r0)     // Catch: all -> 0x000b
            int r6 = r8.pos     // Catch: all -> 0x000b
            int r6 = r6 + r0
            r8.pos = r6     // Catch: all -> 0x000b
            if (r0 == r11) goto L_0x003c
            int r6 = r2.available()     // Catch: all -> 0x000b
            if (r6 != 0) goto L_0x0045
        L_0x003c:
            r5 = r0
            goto L_0x0011
        L_0x003e:
            int r6 = r8.count     // Catch: all -> 0x000b
            int r7 = r8.pos     // Catch: all -> 0x000b
            int r0 = r6 - r7
            goto L_0x002a
        L_0x0045:
            int r10 = r10 + r0
            int r4 = r11 - r0
        L_0x0048:
            int r6 = r8.markpos     // Catch: all -> 0x000b
            if (r6 != r5) goto L_0x005c
            int r6 = r1.length     // Catch: all -> 0x000b
            if (r4 < r6) goto L_0x005c
            int r3 = r2.read(r9, r10, r4)     // Catch: all -> 0x000b
            if (r3 != r5) goto L_0x0086
            if (r4 == r11) goto L_0x0011
            int r5 = r11 - r4
            goto L_0x0011
        L_0x005a:
            r4 = r11
            goto L_0x0048
        L_0x005c:
            int r6 = r8.fillbuf(r2, r1)     // Catch: all -> 0x000b
            if (r6 != r5) goto L_0x0067
            if (r4 == r11) goto L_0x0011
            int r5 = r11 - r4
            goto L_0x0011
        L_0x0067:
            byte[] r6 = r8.buf     // Catch: all -> 0x000b
            if (r1 == r6) goto L_0x0074
            byte[] r1 = r8.buf     // Catch: all -> 0x000b
            if (r1 != 0) goto L_0x0074
            java.io.IOException r5 = streamClosed()     // Catch: all -> 0x000b
            throw r5     // Catch: all -> 0x000b
        L_0x0074:
            int r6 = r8.count     // Catch: all -> 0x000b
            int r7 = r8.pos     // Catch: all -> 0x000b
            int r6 = r6 - r7
            if (r6 < r4) goto L_0x008b
            r3 = r4
        L_0x007c:
            int r6 = r8.pos     // Catch: all -> 0x000b
            java.lang.System.arraycopy(r1, r6, r9, r10, r3)     // Catch: all -> 0x000b
            int r6 = r8.pos     // Catch: all -> 0x000b
            int r6 = r6 + r3
            r8.pos = r6     // Catch: all -> 0x000b
        L_0x0086:
            int r4 = r4 - r3
            if (r4 != 0) goto L_0x0092
            r5 = r11
            goto L_0x0011
        L_0x008b:
            int r6 = r8.count     // Catch: all -> 0x000b
            int r7 = r8.pos     // Catch: all -> 0x000b
            int r3 = r6 - r7
            goto L_0x007c
        L_0x0092:
            int r6 = r2.available()     // Catch: all -> 0x000b
            if (r6 != 0) goto L_0x009c
            int r5 = r11 - r4
            goto L_0x0011
        L_0x009c:
            int r10 = r10 + r3
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream.read(byte[], int, int):int");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        if (this.buf == null) {
            throw new IOException("Stream is closed");
        } else if (-1 == this.markpos) {
            throw new InvalidMarkException("Mark has been invalidated");
        } else {
            this.pos = this.markpos;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized long skip(long byteCount) throws IOException {
        byte[] localBuf = this.buf;
        InputStream localIn = this.in;
        if (localBuf == null) {
            throw streamClosed();
        } else if (byteCount < 1) {
            byteCount = 0;
        } else if (localIn == null) {
            throw streamClosed();
        } else if (this.count - this.pos >= byteCount) {
            this.pos = (int) (this.pos + byteCount);
        } else {
            long read = this.count - this.pos;
            this.pos = this.count;
            if (this.markpos == -1 || byteCount > this.marklimit) {
                byteCount = read + localIn.skip(byteCount - read);
            } else if (fillbuf(localIn, localBuf) == -1) {
                byteCount = read;
            } else if (this.count - this.pos >= byteCount - read) {
                this.pos = (int) (this.pos + (byteCount - read));
            } else {
                this.pos = this.count;
                byteCount = (this.count + read) - this.pos;
            }
        }
        return byteCount;
    }

    /* loaded from: classes.dex */
    public static class InvalidMarkException extends RuntimeException {
        private static final long serialVersionUID = -4338378848813561757L;

        public InvalidMarkException(String detailMessage) {
            super(detailMessage);
        }
    }
}

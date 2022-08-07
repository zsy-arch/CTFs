package com.tencent.smtt.utils;

import com.tencent.smtt.sdk.WebView;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: classes.dex */
public class c implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    public final RandomAccessFile f1504a;

    /* renamed from: b  reason: collision with root package name */
    public final File f1505b;

    /* renamed from: c  reason: collision with root package name */
    public final byte[] f1506c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f1507d;

    public c(File file) {
        this.f1506c = new byte[8];
        this.f1505b = file;
        this.f1504a = new RandomAccessFile(this.f1505b, "r");
    }

    public c(String str) {
        this(new File(str));
    }

    public final int a(byte[] bArr) {
        return this.f1504a.read(bArr);
    }

    public final int a(char[] cArr) {
        byte[] bArr = new byte[cArr.length];
        int read = this.f1504a.read(bArr);
        for (int i = 0; i < cArr.length; i++) {
            cArr[i] = (char) bArr[i];
        }
        return read;
    }

    public final short a() {
        short readShort = this.f1504a.readShort();
        if (!this.f1507d) {
            return readShort;
        }
        return (short) (((readShort & 65280) >>> 8) | ((readShort & 255) << 8));
    }

    public void a(long j) {
        this.f1504a.seek(j);
    }

    public void a(boolean z) {
        this.f1507d = z;
    }

    public final int b() {
        int readInt = this.f1504a.readInt();
        if (!this.f1507d) {
            return readInt;
        }
        return ((readInt & WebView.NIGHT_MODE_COLOR) >>> 24) | ((readInt & WebView.NORMAL_MODE_ALPHA) << 24) | ((65280 & readInt) << 8) | ((16711680 & readInt) >>> 8);
    }

    public final long c() {
        if (!this.f1507d) {
            return this.f1504a.readLong();
        }
        this.f1504a.readFully(this.f1506c, 0, 8);
        byte[] bArr = this.f1506c;
        return ((bArr[1] & 255) << 8) | (bArr[7] << 56) | ((bArr[6] & 255) << 48) | ((bArr[5] & 255) << 40) | ((bArr[4] & 255) << 32) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | (bArr[0] & 255);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.f1504a.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}

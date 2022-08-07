package com.tencent.open.utils;

import android.support.v4.view.MotionEventCompat;
import u.aly.dc;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public final class ZipLong implements Cloneable {
    private long a;

    public ZipLong(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipLong(byte[] bArr, int i) {
        this.a = (bArr[i + 3] << 24) & 4278190080L;
        this.a += (bArr[i + 2] << dc.n) & 16711680;
        this.a += (bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        this.a += bArr[i] & 255;
    }

    public ZipLong(long j) {
        this.a = j;
    }

    public boolean equals(Object obj) {
        return obj != null && (obj instanceof ZipLong) && this.a == ((ZipLong) obj).getValue();
    }

    public byte[] getBytes() {
        return new byte[]{(byte) (this.a & 255), (byte) ((this.a & 65280) >> 8), (byte) ((this.a & 16711680) >> 16), (byte) ((this.a & 4278190080L) >> 24)};
    }

    public long getValue() {
        return this.a;
    }

    public int hashCode() {
        return (int) this.a;
    }
}

package com.tencent.stat.common;

import u.aly.dc;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class j extends h {
    static final /* synthetic */ boolean g;
    private static final byte[] h;
    private static final byte[] i;
    int c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    private final byte[] j;
    private int k;
    private final byte[] l;

    static {
        g = !g.class.desiredAssertionStatus();
        h = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        i = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    }

    public j(int i2, byte[] bArr) {
        boolean z = true;
        this.a = bArr;
        this.d = (i2 & 1) == 0;
        this.e = (i2 & 2) == 0;
        this.f = (i2 & 4) == 0 ? false : z;
        this.l = (i2 & 8) == 0 ? h : i;
        this.j = new byte[2];
        this.c = 0;
        this.k = this.e ? 19 : -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean a(byte[] bArr, int i2, int i3, boolean z) {
        int i4;
        int i5;
        byte b;
        int i6;
        byte b2;
        byte b3;
        int i7;
        int i8;
        int i9;
        byte[] bArr2 = this.l;
        byte[] bArr3 = this.a;
        int i10 = 0;
        int i11 = this.k;
        int i12 = i3 + i2;
        int i13 = -1;
        switch (this.c) {
            case 0:
                i4 = i2;
                break;
            case 1:
                if (i2 + 2 <= i12) {
                    int i14 = i2 + 1;
                    i4 = i14 + 1;
                    i13 = ((this.j[0] & 255) << 16) | ((bArr[i2] & 255) << 8) | (bArr[i14] & 255);
                    this.c = 0;
                    break;
                }
                i4 = i2;
                break;
            case 2:
                if (i2 + 1 <= i12) {
                    i4 = i2 + 1;
                    i13 = ((this.j[0] & 255) << 16) | ((this.j[1] & 255) << 8) | (bArr[i2] & 255);
                    this.c = 0;
                    break;
                }
                i4 = i2;
                break;
            default:
                i4 = i2;
                break;
        }
        if (i13 != -1) {
            bArr3[0] = bArr2[(i13 >> 18) & 63];
            bArr3[1] = bArr2[(i13 >> 12) & 63];
            bArr3[2] = bArr2[(i13 >> 6) & 63];
            i10 = 4;
            bArr3[3] = bArr2[i13 & 63];
            i11--;
            if (i11 == 0) {
                if (this.f) {
                    i9 = 5;
                    bArr3[4] = dc.k;
                } else {
                    i9 = 4;
                }
                i10 = i9 + 1;
                bArr3[i9] = 10;
                i11 = 19;
            }
        }
        while (i4 + 3 <= i12) {
            int i15 = ((bArr[i4] & 255) << 16) | ((bArr[i4 + 1] & 255) << 8) | (bArr[i4 + 2] & 255);
            bArr3[i10] = bArr2[(i15 >> 18) & 63];
            bArr3[i10 + 1] = bArr2[(i15 >> 12) & 63];
            bArr3[i10 + 2] = bArr2[(i15 >> 6) & 63];
            bArr3[i10 + 3] = bArr2[i15 & 63];
            i4 += 3;
            i10 += 4;
            i11--;
            if (i11 == 0) {
                if (this.f) {
                    i8 = i10 + 1;
                    bArr3[i10] = dc.k;
                } else {
                    i8 = i10;
                }
                i10 = i8 + 1;
                bArr3[i8] = 10;
                i11 = 19;
            }
        }
        if (z) {
            if (i4 - this.c == i12 - 1) {
                if (this.c > 0) {
                    i7 = 1;
                    b3 = this.j[0];
                    i4 = i4;
                } else {
                    i4++;
                    b3 = bArr[i4];
                    i7 = 0;
                }
                int i16 = (b3 & 255) << 4;
                this.c -= i7;
                int i17 = i10 + 1;
                bArr3[i10] = bArr2[(i16 >> 6) & 63];
                i10 = i17 + 1;
                bArr3[i17] = bArr2[i16 & 63];
                if (this.d) {
                    int i18 = i10 + 1;
                    bArr3[i10] = 61;
                    i10 = i18 + 1;
                    bArr3[i18] = 61;
                }
                if (this.e) {
                    if (this.f) {
                        i10++;
                        bArr3[i10] = dc.k;
                    }
                    i10++;
                    bArr3[i10] = 10;
                }
            } else if (i4 - this.c == i12 - 2) {
                if (this.c > 1) {
                    i6 = 1;
                    b = this.j[0];
                } else {
                    i4++;
                    b = bArr[i4];
                    i6 = 0;
                }
                int i19 = (b & 255) << 10;
                if (this.c > 0) {
                    i6++;
                    b2 = this.j[i6];
                } else {
                    i4++;
                    b2 = bArr[i4];
                }
                int i20 = ((b2 & 255) << 2) | i19;
                this.c -= i6;
                int i21 = i10 + 1;
                bArr3[i10] = bArr2[(i20 >> 12) & 63];
                int i22 = i21 + 1;
                bArr3[i21] = bArr2[(i20 >> 6) & 63];
                int i23 = i22 + 1;
                bArr3[i22] = bArr2[i20 & 63];
                if (this.d) {
                    i10 = i23 + 1;
                    bArr3[i23] = 61;
                } else {
                    i10 = i23;
                }
                if (this.e) {
                    if (this.f) {
                        i10++;
                        bArr3[i10] = dc.k;
                    }
                    i10++;
                    bArr3[i10] = 10;
                }
            } else if (this.e && i10 > 0 && i11 != 19) {
                if (this.f) {
                    i5 = i10 + 1;
                    bArr3[i10] = dc.k;
                } else {
                    i5 = i10;
                }
                i10 = i5 + 1;
                bArr3[i5] = 10;
            }
            if (!g && this.c != 0) {
                throw new AssertionError();
            } else if (!g && i4 != i12) {
                throw new AssertionError();
            }
        } else if (i4 == i12 - 1) {
            byte[] bArr4 = this.j;
            int i24 = this.c;
            this.c = i24 + 1;
            bArr4[i24] = bArr[i4];
        } else if (i4 == i12 - 2) {
            byte[] bArr5 = this.j;
            int i25 = this.c;
            this.c = i25 + 1;
            bArr5[i25] = bArr[i4];
            byte[] bArr6 = this.j;
            int i26 = this.c;
            this.c = i26 + 1;
            bArr6[i26] = bArr[i4 + 1];
        }
        this.b = i10;
        this.k = i11;
        return true;
    }
}

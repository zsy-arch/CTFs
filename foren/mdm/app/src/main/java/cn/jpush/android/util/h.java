package cn.jpush.android.util;

import u.aly.dc;

/* loaded from: classes.dex */
final class h extends f {
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
        g = !e.class.desiredAssertionStatus();
        h = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        i = new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    }

    public h(int i2, byte[] bArr) {
        boolean z = true;
        this.a = null;
        this.d = (i2 & 1) == 0;
        this.e = (i2 & 2) == 0;
        this.f = (i2 & 4) == 0 ? false : z;
        this.l = (i2 & 8) == 0 ? h : i;
        this.j = new byte[2];
        this.c = 0;
        this.k = this.e ? 19 : -1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final boolean a(byte[] bArr, int i2, int i3, boolean z) {
        int i4;
        int i5;
        int i6;
        int i7;
        byte b;
        byte b2;
        byte b3;
        int i8;
        byte[] bArr2 = this.l;
        byte[] bArr3 = this.a;
        int i9 = 0;
        int i10 = this.k;
        int i11 = i3 + i2;
        switch (this.c) {
            case 0:
                i4 = i2;
                i5 = -1;
                break;
            case 1:
                if (i2 + 2 <= i11) {
                    int i12 = i2 + 1;
                    i4 = i12 + 1;
                    i5 = ((this.j[0] & 255) << 16) | ((bArr[i2] & 255) << 8) | (bArr[i12] & 255);
                    this.c = 0;
                    break;
                }
                i4 = i2;
                i5 = -1;
                break;
            case 2:
                if (i2 + 1 <= i11) {
                    i4 = i2 + 1;
                    i5 = ((this.j[0] & 255) << 16) | ((this.j[1] & 255) << 8) | (bArr[i2] & 255);
                    this.c = 0;
                    break;
                }
                i4 = i2;
                i5 = -1;
                break;
            default:
                i4 = i2;
                i5 = -1;
                break;
        }
        if (i5 != -1) {
            bArr3[0] = bArr2[(i5 >> 18) & 63];
            bArr3[1] = bArr2[(i5 >> 12) & 63];
            bArr3[2] = bArr2[(i5 >> 6) & 63];
            int i13 = 4;
            bArr3[3] = bArr2[i5 & 63];
            int i14 = i10 - 1;
            if (i14 == 0) {
                if (this.f) {
                    i13 = 5;
                    bArr3[4] = dc.k;
                }
                i9 = i13 + 1;
                bArr3[i13] = 10;
                i6 = 19;
            } else {
                i6 = i14;
                i9 = 4;
            }
        } else {
            i6 = i10;
        }
        while (i4 + 3 <= i11) {
            int i15 = ((bArr[i4] & 255) << 16) | ((bArr[i4 + 1] & 255) << 8) | (bArr[i4 + 2] & 255);
            bArr3[i9] = bArr2[(i15 >> 18) & 63];
            bArr3[i9 + 1] = bArr2[(i15 >> 12) & 63];
            bArr3[i9 + 2] = bArr2[(i15 >> 6) & 63];
            bArr3[i9 + 3] = bArr2[i15 & 63];
            i4 += 3;
            int i16 = i9 + 4;
            int i17 = i6 - 1;
            if (i17 == 0) {
                if (this.f) {
                    i8 = i16 + 1;
                    bArr3[i16] = dc.k;
                } else {
                    i8 = i16;
                }
                i9 = i8 + 1;
                bArr3[i8] = 10;
                i6 = 19;
            } else {
                i6 = i17;
                i9 = i16;
            }
        }
        if (i4 - this.c == i11 - 1) {
            int i18 = 0;
            if (this.c > 0) {
                i18 = 1;
                b3 = this.j[0];
            } else {
                i4++;
                b3 = bArr[i4];
            }
            int i19 = (b3 & 255) << 4;
            this.c -= i18;
            int i20 = i9 + 1;
            bArr3[i9] = bArr2[(i19 >> 6) & 63];
            i9 = i20 + 1;
            bArr3[i20] = bArr2[i19 & 63];
            if (this.d) {
                int i21 = i9 + 1;
                bArr3[i9] = 61;
                i9 = i21 + 1;
                bArr3[i21] = 61;
            }
            if (this.e) {
                if (this.f) {
                    i9++;
                    bArr3[i9] = dc.k;
                }
                i9++;
                bArr3[i9] = 10;
            }
        } else if (i4 - this.c == i11 - 2) {
            int i22 = 0;
            if (this.c > 1) {
                i22 = 1;
                b = this.j[0];
            } else {
                i4++;
                b = bArr[i4];
            }
            int i23 = (b & 255) << 10;
            if (this.c > 0) {
                i22++;
                b2 = this.j[i22];
            } else {
                i4++;
                b2 = bArr[i4];
            }
            int i24 = ((b2 & 255) << 2) | i23;
            this.c -= i22;
            int i25 = i9 + 1;
            bArr3[i9] = bArr2[(i24 >> 12) & 63];
            int i26 = i25 + 1;
            bArr3[i25] = bArr2[(i24 >> 6) & 63];
            int i27 = i26 + 1;
            bArr3[i26] = bArr2[i24 & 63];
            if (this.d) {
                i9 = i27 + 1;
                bArr3[i27] = 61;
            } else {
                i9 = i27;
            }
            if (this.e) {
                if (this.f) {
                    i9++;
                    bArr3[i9] = dc.k;
                }
                i9++;
                bArr3[i9] = 10;
            }
        } else if (this.e && i9 > 0 && i6 != 19) {
            if (this.f) {
                i7 = i9 + 1;
                bArr3[i9] = dc.k;
            } else {
                i7 = i9;
            }
            i9 = i7 + 1;
            bArr3[i7] = 10;
        }
        if (!g && this.c != 0) {
            throw new AssertionError();
        } else if (g || i4 == i11) {
            this.b = i9;
            this.k = i6;
            return true;
        } else {
            throw new AssertionError();
        }
    }
}

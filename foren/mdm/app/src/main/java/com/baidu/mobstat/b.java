package com.baidu.mobstat;

import u.aly.dc;

/* loaded from: classes.dex */
public final class b {
    private static final byte[] a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    public static String a(byte[] bArr, String str) {
        int i;
        int length = (bArr.length * 4) / 3;
        byte[] bArr2 = new byte[length + (length / 76) + 3];
        int length2 = bArr.length - (bArr.length % 3);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length2; i4 += 3) {
            int i5 = i3 + 1;
            bArr2[i3] = a[(bArr[i4] & 255) >> 2];
            int i6 = i5 + 1;
            bArr2[i5] = a[((bArr[i4] & 3) << 4) | ((bArr[i4 + 1] & 255) >> 4)];
            int i7 = i6 + 1;
            bArr2[i6] = a[((bArr[i4 + 1] & dc.m) << 2) | ((bArr[i4 + 2] & 255) >> 6)];
            int i8 = i7 + 1;
            bArr2[i7] = a[bArr[i4 + 2] & 63];
            if ((i8 - i2) % 76 != 0 || i8 == 0) {
                i3 = i8;
            } else {
                i3 = i8 + 1;
                bArr2[i8] = 10;
                i2++;
            }
        }
        switch (bArr.length % 3) {
            case 1:
                int i9 = i3 + 1;
                bArr2[i3] = a[(bArr[length2] & 255) >> 2];
                int i10 = i9 + 1;
                bArr2[i9] = a[(bArr[length2] & 3) << 4];
                int i11 = i10 + 1;
                bArr2[i10] = 61;
                i = i11 + 1;
                bArr2[i11] = 61;
                break;
            case 2:
                int i12 = i3 + 1;
                bArr2[i3] = a[(bArr[length2] & 255) >> 2];
                int i13 = i12 + 1;
                bArr2[i12] = a[((bArr[length2] & 3) << 4) | ((bArr[length2 + 1] & 255) >> 4)];
                int i14 = i13 + 1;
                bArr2[i13] = a[(bArr[length2 + 1] & dc.m) << 2];
                i = i14 + 1;
                bArr2[i14] = 61;
                break;
            default:
                i = i3;
                break;
        }
        return new String(bArr2, 0, i, str);
    }

    public static byte[] a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static byte[] a(byte[] bArr, int i) {
        int i2;
        int i3 = (i / 4) * 3;
        if (i3 == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[i3];
        int i4 = 0;
        while (true) {
            byte b = bArr[i - 1];
            if (!(b == 10 || b == 13 || b == 32 || b == 9)) {
                if (b != 61) {
                    break;
                }
                i4++;
            }
            i--;
        }
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < i; i8++) {
            byte b2 = bArr[i8];
            if (b2 == 10 || b2 == 13 || b2 == 32) {
                i5 = i5;
                i7 = i7;
                i6 = i6;
            } else if (b2 == 9) {
                i5 = i5;
                i7 = i7;
                i6 = i6;
            } else {
                if (b2 >= 65 && b2 <= 90) {
                    i2 = b2 - 65;
                } else if (b2 >= 97 && b2 <= 122) {
                    i2 = b2 - 71;
                } else if (b2 >= 48 && b2 <= 57) {
                    i2 = b2 + 4;
                } else if (b2 == 43) {
                    i2 = 62;
                } else if (b2 != 47) {
                    return null;
                } else {
                    i2 = 63;
                }
                int i9 = (i5 << 6) | ((byte) i2);
                if (i6 % 4 == 3) {
                    int i10 = i7 + 1;
                    bArr2[i7] = (byte) ((16711680 & i9) >> 16);
                    int i11 = i10 + 1;
                    bArr2[i10] = (byte) ((65280 & i9) >> 8);
                    i7 = i11 + 1;
                    bArr2[i11] = (byte) (i9 & 255);
                } else {
                    i7 = i7;
                }
                i6++;
                i5 = i9;
            }
        }
        if (i4 > 0) {
            int i12 = i5 << (i4 * 6);
            int i13 = i7 + 1;
            bArr2[i7] = (byte) ((16711680 & i12) >> 16);
            if (i4 == 1) {
                i7 = i13 + 1;
                bArr2[i13] = (byte) ((65280 & i12) >> 8);
            } else {
                i7 = i13;
            }
        }
        byte[] bArr3 = new byte[i7];
        System.arraycopy(bArr2, 0, bArr3, 0, i7);
        return bArr3;
    }
}

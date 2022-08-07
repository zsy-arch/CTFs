package com.loc;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Encrypt.java */
/* loaded from: classes2.dex */
public final class o {
    private static final char[] a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] b = new byte[128];

    static {
        for (int i = 0; i < 128; i++) {
            b[i] = -1;
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            b[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            b[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            b[i4] = (byte) ((i4 - 48) + 52);
        }
        b[43] = 62;
        b[47] = 63;
    }

    public static String a(String str) {
        return t.a(b(str));
    }

    public static String a(byte[] bArr) {
        try {
            return c(bArr);
        } catch (Throwable th) {
            w.a(th, "Encrypt", "encodeBase64");
            return null;
        }
    }

    public static byte[] a(byte[] bArr, Key key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(1, key);
        return instance.doFinal(bArr);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            return b(bArr, bArr2);
        } catch (Throwable th) {
            w.a(th, "Encrypt", "aesEncrypt");
            return null;
        }
    }

    public static String b(byte[] bArr) {
        try {
            return c(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004e, code lost:
        if (r5 == (-1)) goto L_0x007a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        r4.write(((r6 & u.aly.dc.m) << 4) | ((r5 & 60) >>> 2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
        r0 = r1 + 1;
        r1 = r2[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
        if (r1 != 61) goto L_0x0067;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0067, code lost:
        r1 = com.loc.o.b[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006b, code lost:
        if (r0 >= r3) goto L_0x006f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x006d, code lost:
        if (r1 == (-1)) goto L_0x007f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006f, code lost:
        if (r1 == (-1)) goto L_0x007a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0071, code lost:
        r4.write(r1 | ((r5 & 3) << 6));
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007f, code lost:
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:?, code lost:
        return r4.toByteArray();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] b(java.lang.String r9) {
        /*
            r8 = 61
            r0 = 0
            r7 = -1
            if (r9 != 0) goto L_0x0009
            byte[] r0 = new byte[r0]
        L_0x0008:
            return r0
        L_0x0009:
            byte[] r2 = com.loc.t.a(r9)
            int r3 = r2.length
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream
            r4.<init>(r3)
        L_0x0013:
            if (r0 >= r3) goto L_0x007a
        L_0x0015:
            byte[] r5 = com.loc.o.b
            int r1 = r0 + 1
            byte r0 = r2[r0]
            byte r5 = r5[r0]
            if (r1 >= r3) goto L_0x0021
            if (r5 == r7) goto L_0x0085
        L_0x0021:
            if (r5 == r7) goto L_0x007a
        L_0x0023:
            byte[] r6 = com.loc.o.b
            int r0 = r1 + 1
            byte r1 = r2[r1]
            byte r6 = r6[r1]
            if (r0 >= r3) goto L_0x002f
            if (r6 == r7) goto L_0x0083
        L_0x002f:
            if (r6 == r7) goto L_0x007a
            int r1 = r5 << 2
            r5 = r6 & 48
            int r5 = r5 >>> 4
            r1 = r1 | r5
            r4.write(r1)
        L_0x003b:
            int r1 = r0 + 1
            byte r0 = r2[r0]
            if (r0 != r8) goto L_0x0046
            byte[] r0 = r4.toByteArray()
            goto L_0x0008
        L_0x0046:
            byte[] r5 = com.loc.o.b
            byte r5 = r5[r0]
            if (r1 >= r3) goto L_0x004e
            if (r5 == r7) goto L_0x0081
        L_0x004e:
            if (r5 == r7) goto L_0x007a
            r0 = r6 & 15
            int r0 = r0 << 4
            r6 = r5 & 60
            int r6 = r6 >>> 2
            r0 = r0 | r6
            r4.write(r0)
        L_0x005c:
            int r0 = r1 + 1
            byte r1 = r2[r1]
            if (r1 != r8) goto L_0x0067
            byte[] r0 = r4.toByteArray()
            goto L_0x0008
        L_0x0067:
            byte[] r6 = com.loc.o.b
            byte r1 = r6[r1]
            if (r0 >= r3) goto L_0x006f
            if (r1 == r7) goto L_0x007f
        L_0x006f:
            if (r1 == r7) goto L_0x007a
            r5 = r5 & 3
            int r5 = r5 << 6
            r1 = r1 | r5
            r4.write(r1)
            goto L_0x0013
        L_0x007a:
            byte[] r0 = r4.toByteArray()
            goto L_0x0008
        L_0x007f:
            r1 = r0
            goto L_0x005c
        L_0x0081:
            r0 = r1
            goto L_0x003b
        L_0x0083:
            r1 = r0
            goto L_0x0023
        L_0x0085:
            r0 = r1
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.o.b(java.lang.String):byte[]");
    }

    private static byte[] b(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(t.a());
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        try {
            instance.init(1, secretKeySpec, ivParameterSpec);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return instance.doFinal(bArr2);
    }

    private static String c(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i2 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[(i3 & 3) << 4]);
                stringBuffer.append("==");
                break;
            }
            int i4 = i2 + 1;
            int i5 = bArr[i2] & 255;
            if (i4 == length) {
                stringBuffer.append(a[i3 >>> 2]);
                stringBuffer.append(a[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
                stringBuffer.append(a[(i5 & 15) << 2]);
                stringBuffer.append("=");
                break;
            }
            i = i4 + 1;
            int i6 = bArr[i4] & 255;
            stringBuffer.append(a[i3 >>> 2]);
            stringBuffer.append(a[((i3 & 3) << 4) | ((i5 & 240) >>> 4)]);
            stringBuffer.append(a[((i5 & 15) << 2) | ((i6 & 192) >>> 6)]);
            stringBuffer.append(a[i6 & 63]);
        }
        return stringBuffer.toString();
    }
}

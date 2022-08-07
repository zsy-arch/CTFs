package com.amap.api.col;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import u.aly.dc;

/* compiled from: Encrypt.java */
/* loaded from: classes.dex */
public final class jj {
    private static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final byte[] b = {0, 1, 1, 2, 3, 5, 8, dc.k, 8, 7, 6, 5, 4, 3, 2, 1};
    private static final IvParameterSpec c = new IvParameterSpec(b);

    public static byte[] a(byte[] bArr) {
        int i = 0;
        try {
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[bArr.length - 16];
            System.arraycopy(bArr, 0, bArr2, 0, 16);
            System.arraycopy(bArr, 16, bArr3, 0, bArr.length - 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(gk.b()));
            return instance.doFinal(bArr3);
        } catch (Throwable th) {
            if (bArr != null) {
                i = bArr.length;
            }
            jn.a(th, "Encrypt", "decryptRsponse length = " + i);
            return null;
        }
    }
}

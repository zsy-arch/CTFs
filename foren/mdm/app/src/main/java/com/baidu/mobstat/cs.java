package com.baidu.mobstat;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public class cs {
    public static String a(byte[] bArr) {
        try {
            return cm.b(a(false, cn.a(), bArr));
        } catch (Exception e) {
            cr.b(e);
            return "";
        }
    }

    private static RSAKey a(boolean z, byte[] bArr) {
        KeyFactory instance = KeyFactory.getInstance("RSA");
        return z ? (RSAPrivateKey) instance.generatePrivate(new PKCS8EncodedKeySpec(bArr)) : (RSAPublicKey) instance.generatePublic(new X509EncodedKeySpec(bArr));
    }

    private static byte[] a(int i, Key key, int i2, byte[] bArr) {
        Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        instance.init(i, key);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i3 = 0;
        while (i3 < bArr.length) {
            int length = bArr.length - i3;
            if (length > i2) {
                length = i2;
            }
            byteArrayOutputStream.write(instance.doFinal(bArr, i3, length));
            i3 += i2;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] a(boolean z, byte[] bArr, byte[] bArr2) {
        RSAKey a = a(z, bArr);
        return a(1, (Key) a, ((a.getModulus().bitLength() + 1) / 8) - 11, bArr2);
    }

    public static byte[] b(boolean z, byte[] bArr, byte[] bArr2) {
        RSAKey a = a(z, bArr);
        return a(2, (Key) a, (a.getModulus().bitLength() + 1) / 8, bArr2);
    }
}

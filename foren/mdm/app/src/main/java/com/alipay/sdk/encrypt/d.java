package com.alipay.sdk.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public final class d {
    private static final String a = "RSA";

    private static PublicKey b(String str, String str2) throws NoSuchAlgorithmException, Exception {
        return KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(a.a(str2)));
    }

    public static byte[] a(String str, String str2) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        byte[] bArr = null;
        try {
            PublicKey generatePublic = KeyFactory.getInstance(a).generatePublic(new X509EncodedKeySpec(a.a(str2)));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePublic);
            byte[] bytes = str.getBytes("UTF-8");
            int blockSize = instance.getBlockSize();
            byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = 0; i < bytes.length; i += blockSize) {
                try {
                    byteArrayOutputStream.write(instance.doFinal(bytes, i, bytes.length - i < blockSize ? bytes.length - i : blockSize));
                } catch (Exception e) {
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                        }
                    }
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    throw th;
                }
            }
            bArr = byteArrayOutputStream.toByteArray();
            try {
                byteArrayOutputStream.close();
            } catch (IOException e4) {
            }
        } catch (Exception e5) {
            byteArrayOutputStream = null;
        } catch (Throwable th3) {
            byteArrayOutputStream = null;
            th = th3;
        }
        return bArr;
    }
}

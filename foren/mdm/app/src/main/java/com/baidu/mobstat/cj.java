package com.baidu.mobstat;

import android.annotation.SuppressLint;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class cj {
    public static String a(byte[] bArr) {
        try {
            return b(a(), b(), bArr);
        } catch (Exception e) {
            cr.b(e);
            return "";
        }
    }

    public static byte[] a() {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        instance.init(128, new SecureRandom());
        return instance.generateKey().getEncoded();
    }

    @SuppressLint({"TrulyRandom"})
    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, ivParameterSpec);
        return instance.doFinal(bArr3);
    }

    public static String b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            return cm.b(a(bArr, bArr2, bArr3)) + "|" + cs.a(bArr) + "|" + cs.a(bArr2);
        } catch (Exception e) {
            cr.b(e);
            return "";
        }
    }

    public static byte[] b() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }
}

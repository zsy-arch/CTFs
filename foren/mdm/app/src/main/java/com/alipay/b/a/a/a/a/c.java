package com.alipay.b.a.a.a.a;

import java.lang.reflect.Method;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import u.aly.dc;

/* loaded from: classes.dex */
public final class c {
    private static String a = new String("idnjfhncnsfuobcnt847y929o449u474w7j3h22aoddc98euk#%&&)*&^%#");

    public static String a() {
        String str = new String();
        for (int i = 0; i < a.length() - 1; i += 4) {
            str = str + a.charAt(i);
        }
        return str;
    }

    public static String a(String str, String str2) {
        byte[] bArr = null;
        try {
            bArr = a(a(str.getBytes()), str2.getBytes());
        } catch (Exception e) {
        }
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & dc.m));
        }
        return stringBuffer.toString();
    }

    private static byte[] a(byte[] bArr) {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        Class<?> cls = Class.forName(new String(a.a("amF2YS5zZWN1cml0eS5TZWN1cmVSYW5kb20=")));
        Object invoke = cls.getMethod("getInstance", String.class, String.class).invoke(null, "SHA1PRNG", "Crypto");
        Method method = cls.getMethod("setSeed", bArr.getClass());
        method.setAccessible(true);
        method.invoke(invoke, bArr);
        KeyGenerator.class.getMethod("init", Integer.TYPE, cls).invoke(instance, 128, invoke);
        return instance.generateKey().getEncoded();
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            return instance.doFinal(bArr2);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String b(String str, String str2) {
        try {
            byte[] a2 = a(str.getBytes());
            int length = str2.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = Integer.valueOf(str2.substring(i * 2, (i * 2) + 2), 16).byteValue();
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(a2, "AES");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, new IvParameterSpec(new byte[instance.getBlockSize()]));
            return new String(instance.doFinal(bArr));
        } catch (Exception e) {
            return null;
        }
    }
}

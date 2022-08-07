package com.baidu.mobstat;

import android.text.TextUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class ck {
    public static byte[] a(int i, byte[] bArr) {
        int i2 = i - 1;
        if (i2 < 0 || cn.a.length <= i2) {
            return new byte[0];
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(cn.a[i2].getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr);
    }

    public static byte[] b(int i, byte[] bArr) {
        int i2 = i - 1;
        if (i2 < 0 || cn.a.length <= i2) {
            return new byte[0];
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(cn.a[i2].getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
        instance.init(2, secretKeySpec);
        return instance.doFinal(bArr);
    }

    public static String c(int i, byte[] bArr) {
        try {
            return cm.b(a(i, bArr));
        } catch (Exception e) {
            cr.a(e);
            return "";
        }
    }

    public static String d(int i, byte[] bArr) {
        String c = c(i, bArr);
        return TextUtils.isEmpty(c) ? "" : c + "|" + i;
    }
}

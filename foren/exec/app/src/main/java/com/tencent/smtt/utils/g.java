package com.tencent.smtt.utils;

import android.util.Base64;
import e.a.a.a.a;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/* loaded from: classes.dex */
public class g {

    /* renamed from: b  reason: collision with root package name */
    public static String f1561b = "";

    /* renamed from: c  reason: collision with root package name */
    public static byte[] f1562c;
    public static String g;

    /* renamed from: d  reason: collision with root package name */
    public Cipher f1563d;

    /* renamed from: e  reason: collision with root package name */
    public Cipher f1564e;

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f1560a = "0123456789abcdef".toCharArray();
    public static g f = null;

    public g() {
        this.f1563d = null;
        this.f1564e = null;
        g = String.valueOf(new Random().nextInt(89999999) + 10000000) + String.valueOf(new Random().nextInt(89999999) + 10000000) + String.valueOf(new Random().nextInt(89999999) + 10000000);
        String str = "00000000";
        for (int i = 0; i < 12; i++) {
            StringBuilder a2 = a.a(str);
            a2.append(String.valueOf(new Random().nextInt(89999999) + 10000000));
            str = a2.toString();
        }
        StringBuilder a3 = a.a(str);
        a3.append(g);
        f1562c = a3.toString().getBytes();
        this.f1563d = Cipher.getInstance("RSA/ECB/NoPadding");
        this.f1563d.init(1, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcEQ3TCNWPBqgIiY7WQ/IqTOTTV2w8aZ/GPm68FK0fAJBemZKtYR3Li46VJ+Hwnor7ZpQnblGWPFaLv5JoPqvavgB0GInuhm+T+syPs1mw0uPLWaqwvZsCfoaIvUuxy5xHJgmWARrK4/9pHyDxRlZte0PCIoR1ko5B8lVVH1X1dQIDAQAB").getBytes(), 0))));
        f1561b = b(this.f1563d.doFinal(f1562c));
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(g.getBytes()));
        this.f1564e = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        this.f1564e.init(1, generateSecret);
    }

    public static g a() {
        try {
            if (f == null) {
                f = new g();
            }
            return f;
        } catch (Exception e2) {
            f = null;
            e2.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, String str) {
        SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str.getBytes()));
        Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        instance.init(1, generateSecret);
        return instance.doFinal(bArr);
    }

    public static String b(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f1560a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public static byte[] b(byte[] bArr, String str) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(str.getBytes()));
            Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            instance.init(2, generateSecret);
            return instance.doFinal(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String c() {
        return g;
    }

    private String d() {
        return "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDcEQ3TCNWPBqgIiY7WQ/IqTOTTV2w8aZ/GPm68FK0";
    }

    private String e() {
        return "fAJBemZKtYR3Li46VJ+Hwnor7ZpQnblGWPFaLv5JoPqvavgB0GInuhm+T+syPs1mw0uPLWaqwvZsCfoaIvUuxy5xHJgmWARrK4/9pHyDxRlZte0PCIoR1ko5B8lVVH1X1dQIDAQAB";
    }

    public byte[] a(byte[] bArr) {
        return this.f1564e.doFinal(bArr);
    }

    public String b() {
        return f1561b;
    }

    public byte[] c(byte[] bArr) {
        try {
            SecretKey generateSecret = SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(g.getBytes()));
            Cipher instance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            instance.init(2, generateSecret);
            return instance.doFinal(bArr);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}

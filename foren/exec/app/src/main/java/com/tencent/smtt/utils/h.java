package com.tencent.smtt.utils;

import android.util.Base64;
import com.tencent.smtt.sdk.stat.a;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f1565a = "0123456789abcdef".toCharArray();

    /* renamed from: b  reason: collision with root package name */
    public static h f1566b;

    /* renamed from: d  reason: collision with root package name */
    public String f1568d;

    /* renamed from: e  reason: collision with root package name */
    public String f1569e = String.valueOf(new Random().nextInt(89999999) + 10000000);

    /* renamed from: c  reason: collision with root package name */
    public String f1567c = this.f1569e + String.valueOf(new Random().nextInt(89999999) + 10000000);

    public static synchronized h a() {
        h hVar;
        synchronized (h.class) {
            if (f1566b == null) {
                f1566b = new h();
            }
            hVar = f1566b;
        }
        return hVar;
    }

    private String b(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            char[] cArr2 = f1565a;
            cArr[i3] = cArr2[i2 >>> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }

    public byte[] a(byte[] bArr) {
        return a.a(this.f1569e.getBytes(), bArr, 1);
    }

    public void b() {
        Security.addProvider((Provider) Class.forName("com.android.org.bouncycastle.jce.provider.BouncyCastleProvider", true, ClassLoader.getSystemClassLoader()).newInstance());
    }

    public String c() {
        if (this.f1568d == null) {
            byte[] bytes = this.f1567c.getBytes();
            Cipher cipher = null;
            try {
                try {
                    cipher = Cipher.getInstance("RSA/ECB/NoPadding");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (Exception unused) {
                b();
                cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            }
            cipher.init(1, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MCwwDQYJKoZIhvcNAQEBBQADGwAwGAIRAMRB/Q0hTCD+XtnQhpQJefUCAwEAAQ==".getBytes(), 0))));
            this.f1568d = b(cipher.doFinal(bytes));
        }
        return this.f1568d;
    }
}

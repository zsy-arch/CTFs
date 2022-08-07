package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/* compiled from: ClientInfo.java */
/* loaded from: classes2.dex */
public final class m {

    /* compiled from: ClientInfo.java */
    /* loaded from: classes2.dex */
    public static class a {
        String a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        String l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }
    }

    public static String a() {
        Throwable th;
        String str = null;
        try {
            String valueOf = String.valueOf(System.currentTimeMillis());
            try {
                int length = valueOf.length();
                return valueOf.substring(0, length - 2) + "1" + valueOf.substring(length - 1);
            } catch (Throwable th2) {
                th = th2;
                str = valueOf;
                w.a(th, "CInfo", "getTS");
                return str;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static String a(Context context, String str, String str2) {
        try {
            return p.b(k.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            w.a(th, "CInfo", "Scode");
            return null;
        }
    }

    private static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (!TextUtils.isEmpty(str)) {
            t.a(byteArrayOutputStream, str.getBytes().length > 255 ? (byte) -1 : (byte) str.getBytes().length, t.a(str));
        } else {
            t.a(byteArrayOutputStream, (byte) 0, new byte[0]);
        }
    }

    public static byte[] a(Context context) {
        try {
            a aVar = new a((byte) 0);
            aVar.a = n.q(context);
            aVar.b = n.i(context);
            String f = n.f(context);
            if (f == null) {
                f = "";
            }
            aVar.c = f;
            aVar.d = k.c(context);
            aVar.e = Build.MODEL;
            aVar.f = Build.MANUFACTURER;
            aVar.g = Build.DEVICE;
            aVar.h = k.b(context);
            aVar.i = k.d(context);
            aVar.j = String.valueOf(Build.VERSION.SDK_INT);
            aVar.k = n.r(context);
            aVar.l = n.p(context);
            aVar.m = new StringBuilder().append(n.m(context)).toString();
            aVar.n = new StringBuilder().append(n.l(context)).toString();
            aVar.o = n.s(context);
            aVar.p = n.k(context);
            aVar.q = n.h(context);
            aVar.r = n.g(context);
            String[] j = n.j(context);
            aVar.s = j[0];
            aVar.t = j[1];
            return a(aVar);
        } catch (Throwable th) {
            w.a(th, "CInfo", "getGZipXInfo");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    private static byte[] a(a aVar) {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        byte[] bArr2;
        try {
            bArr = 0;
            bArr = 0;
            bArr = 0;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                a(byteArrayOutputStream, aVar.a);
                a(byteArrayOutputStream, aVar.b);
                a(byteArrayOutputStream, aVar.c);
                a(byteArrayOutputStream, aVar.d);
                a(byteArrayOutputStream, aVar.e);
                a(byteArrayOutputStream, aVar.f);
                a(byteArrayOutputStream, aVar.g);
                a(byteArrayOutputStream, aVar.h);
                a(byteArrayOutputStream, aVar.i);
                a(byteArrayOutputStream, aVar.j);
                a(byteArrayOutputStream, aVar.k);
                a(byteArrayOutputStream, aVar.l);
                a(byteArrayOutputStream, aVar.m);
                a(byteArrayOutputStream, aVar.n);
                a(byteArrayOutputStream, aVar.o);
                a(byteArrayOutputStream, aVar.p);
                a(byteArrayOutputStream, aVar.q);
                a(byteArrayOutputStream, aVar.r);
                a(byteArrayOutputStream, aVar.s);
                a(byteArrayOutputStream, aVar.t);
                byte[] b = t.b(byteArrayOutputStream.toByteArray());
                PublicKey b2 = t.b();
                if (b.length > 117) {
                    byte[] bArr3 = new byte[117];
                    System.arraycopy(b, 0, bArr3, 0, 117);
                    byte[] a2 = o.a(bArr3, b2);
                    byte[] bArr4 = new byte[(b.length + 128) - 117];
                    System.arraycopy(a2, 0, bArr4, 0, 128);
                    System.arraycopy(b, 117, bArr4, 128, b.length - 117);
                    bArr2 = bArr4;
                } else {
                    bArr2 = o.a(b, b2);
                }
                try {
                    byteArrayOutputStream.close();
                    bArr = bArr2;
                } catch (Throwable th3) {
                    th3.printStackTrace();
                    bArr = bArr2;
                }
            } catch (Throwable th4) {
                th = th4;
                w.a(th, "CInfo", "InitXInfo");
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                }
                return bArr;
            }
        } catch (Throwable th6) {
            th = th6;
            byteArrayOutputStream = null;
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        PublicKey b = t.b();
        if (b == null) {
            return null;
        }
        byte[] a2 = o.a(encoded, b);
        byte[] a3 = o.a(encoded, bArr);
        byte[] bArr2 = new byte[a2.length + a3.length];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(a3, 0, bArr2, a2.length, a3.length);
        return bArr2;
    }

    public static String b(byte[] bArr) {
        try {
            return d(bArr);
        } catch (Throwable th) {
            w.a(th, "CInfo", "AESData");
            return "";
        }
    }

    public static String c(byte[] bArr) {
        try {
            return d(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static String d(byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        byte[] b = t.b(a(bArr));
        return b != null ? o.a(b) : "";
    }
}

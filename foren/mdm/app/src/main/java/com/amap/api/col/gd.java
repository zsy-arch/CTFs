package com.amap.api.col;

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
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/* compiled from: ClientInfo.java */
/* loaded from: classes.dex */
public class gd {
    public static String a(Context context, String str, String str2) {
        try {
            return gg.b(ga.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            go.a(th, "CInfo", "Scode");
            return null;
        }
    }

    public static String a() {
        Throwable th;
        String str;
        String valueOf;
        try {
            valueOf = String.valueOf(System.currentTimeMillis());
        } catch (Throwable th2) {
            str = null;
            th = th2;
        }
        try {
            String str2 = "1";
            if (!ga.a()) {
                str2 = "0";
            }
            int length = valueOf.length();
            return valueOf.substring(0, length - 2) + str2 + valueOf.substring(length - 1);
        } catch (Throwable th3) {
            str = valueOf;
            th = th3;
            go.a(th, "CInfo", "getTS");
            return str;
        }
    }

    public static String a(Context context) {
        try {
            a aVar = new a();
            aVar.d = ga.c(context);
            aVar.i = ga.d(context);
            return a(context, aVar);
        } catch (Throwable th) {
            go.a(th, "CInfo", "InitXInfo");
            return null;
        }
    }

    public static byte[] a(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        PublicKey b = gk.b(context);
        if (b == null) {
            return null;
        }
        byte[] a2 = gf.a(encoded, b);
        byte[] a3 = gf.a(encoded, bArr);
        byte[] bArr2 = new byte[a2.length + a3.length];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(a3, 0, bArr2, a2.length, a3.length);
        return bArr2;
    }

    public static byte[] a(Context context, boolean z) {
        try {
            return b(context, b(context, z));
        } catch (Throwable th) {
            go.a(th, "CInfo", "getGZipXInfo");
            return null;
        }
    }

    public static String b(Context context) {
        try {
            return a(context, b(context, false));
        } catch (Throwable th) {
            go.a(th, "CInfo", "getClientXInfo");
            return null;
        }
    }

    @Deprecated
    public static String a(Context context, gj gjVar, Map<String, String> map, boolean z) {
        try {
            return a(context, b(context, z));
        } catch (Throwable th) {
            go.a(th, "CInfo", "rsaLocClineInfo");
            return null;
        }
    }

    public static String b(Context context, byte[] bArr) {
        try {
            return d(context, bArr);
        } catch (Throwable th) {
            go.a(th, "CInfo", "AESData");
            return "";
        }
    }

    public static byte[] c(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        PublicKey b = gk.b(context);
        if (bArr.length <= 117) {
            return gf.a(bArr, b);
        }
        byte[] bArr2 = new byte[117];
        System.arraycopy(bArr, 0, bArr2, 0, 117);
        byte[] a2 = gf.a(bArr2, b);
        byte[] bArr3 = new byte[(bArr.length + 128) - 117];
        System.arraycopy(a2, 0, bArr3, 0, 128);
        System.arraycopy(bArr, 117, bArr3, 128, bArr.length - 117);
        return bArr3;
    }

    private static String a(Context context, a aVar) {
        return gf.a(b(context, aVar));
    }

    private static byte[] b(Context context, a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] bArr;
        Throwable th;
        try {
            bArr = null;
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
                a(byteArrayOutputStream, aVar.f22u);
                a(byteArrayOutputStream, aVar.v);
                a(byteArrayOutputStream, aVar.w);
                a(byteArrayOutputStream, aVar.x);
                a(byteArrayOutputStream, aVar.y);
                bArr = a(context, byteArrayOutputStream);
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                go.a(th, "CInfo", "InitXInfo");
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

    private static byte[] a(Context context, ByteArrayOutputStream byteArrayOutputStream) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return c(context, gk.b(byteArrayOutputStream.toByteArray()));
    }

    static String d(Context context, byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        byte[] b = gk.b(a(context, bArr));
        return b != null ? gf.a(b) : "";
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        byte length;
        if (!TextUtils.isEmpty(str)) {
            if (str.getBytes().length > 255) {
                length = -1;
            } else {
                length = (byte) str.getBytes().length;
            }
            gk.a(byteArrayOutputStream, length, gk.a(str));
            return;
        }
        gk.a(byteArrayOutputStream, (byte) 0, new byte[0]);
    }

    public static String e(Context context, byte[] bArr) {
        try {
            return d(context, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static a b(Context context, boolean z) {
        a aVar = new a();
        aVar.a = ge.q(context);
        aVar.b = ge.i(context);
        String f = ge.f(context);
        if (f == null) {
            f = "";
        }
        aVar.c = f;
        aVar.d = ga.c(context);
        aVar.e = Build.MODEL;
        aVar.f = Build.MANUFACTURER;
        aVar.g = Build.DEVICE;
        aVar.h = ga.b(context);
        aVar.i = ga.d(context);
        aVar.j = String.valueOf(Build.VERSION.SDK_INT);
        aVar.k = ge.r(context);
        aVar.l = ge.p(context);
        aVar.m = ge.m(context) + "";
        aVar.n = ge.l(context) + "";
        aVar.o = ge.s(context);
        aVar.p = ge.k(context);
        if (z) {
            aVar.q = "";
        } else {
            aVar.q = ge.h(context);
        }
        if (z) {
            aVar.r = "";
        } else {
            aVar.r = ge.g(context);
        }
        if (z) {
            aVar.s = "";
            aVar.t = "";
        } else {
            String[] j = ge.j(context);
            aVar.s = j[0];
            aVar.t = j[1];
        }
        aVar.w = ge.a();
        return aVar;
    }

    /* compiled from: ClientInfo.java */
    /* loaded from: classes.dex */
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

        /* renamed from: u */
        String f22u;
        String v;
        String w;
        String x;
        String y;

        private a() {
        }
    }
}

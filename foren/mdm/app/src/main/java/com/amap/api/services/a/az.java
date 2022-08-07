package com.amap.api.services.a;

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
public class az {
    public static String a(Context context, String str, String str2) {
        try {
            return bc.b(aw.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            bh.a(th, "CInfo", "Scode");
            return null;
        }
    }

    public static String a() {
        Throwable th;
        String str;
        try {
            String valueOf = String.valueOf(System.currentTimeMillis());
            try {
                int length = valueOf.length();
                return valueOf.substring(0, length - 2) + "1" + valueOf.substring(length - 1);
            } catch (Throwable th2) {
                str = valueOf;
                th = th2;
                bh.a(th, "CInfo", "getTS");
                return str;
            }
        } catch (Throwable th3) {
            str = null;
            th = th3;
        }
    }

    public static byte[] a(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        PublicKey a2 = bf.a(context);
        if (a2 == null) {
            return null;
        }
        byte[] a3 = bb.a(encoded, a2);
        byte[] a4 = bb.a(encoded, bArr);
        byte[] bArr2 = new byte[a3.length + a4.length];
        System.arraycopy(a3, 0, bArr2, 0, a3.length);
        System.arraycopy(a4, 0, bArr2, a3.length, a4.length);
        return bArr2;
    }

    public static byte[] a(Context context, boolean z) {
        try {
            return b(context, b(context, z));
        } catch (Throwable th) {
            bh.a(th, "CInfo", "getGZipXInfo");
            return null;
        }
    }

    @Deprecated
    public static String a(Context context, be beVar, Map<String, String> map, boolean z) {
        try {
            return a(context, b(context, z));
        } catch (Throwable th) {
            bh.a(th, "CInfo", "rsaLocClineInfo");
            return null;
        }
    }

    public static String b(Context context, byte[] bArr) {
        try {
            return d(context, bArr);
        } catch (Throwable th) {
            bh.a(th, "CInfo", "AESData");
            return "";
        }
    }

    public static byte[] c(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        PublicKey a2 = bf.a(context);
        if (bArr.length <= 117) {
            return bb.a(bArr, a2);
        }
        byte[] bArr2 = new byte[117];
        System.arraycopy(bArr, 0, bArr2, 0, 117);
        byte[] a3 = bb.a(bArr2, a2);
        byte[] bArr3 = new byte[(bArr.length + 128) - 117];
        System.arraycopy(a3, 0, bArr3, 0, 128);
        System.arraycopy(bArr, 117, bArr3, 128, bArr.length - 117);
        return bArr3;
    }

    private static String a(Context context, a aVar) {
        return bb.a(b(context, aVar));
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0092 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] b(android.content.Context r5, com.amap.api.services.a.az.a r6) {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: Throwable -> 0x0079, all -> 0x008d
            r2.<init>()     // Catch: Throwable -> 0x0079, all -> 0x008d
            java.lang.String r1 = r6.a     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.b     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.c     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.d     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.e     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.f     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.g     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.h     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.i     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.j     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.k     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.l     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.m     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.n     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.o     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.p     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.q     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.r     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.s     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            java.lang.String r1 = r6.t     // Catch: Throwable -> 0x009d, all -> 0x009b
            a(r2, r1)     // Catch: Throwable -> 0x009d, all -> 0x009b
            byte[] r0 = a(r5, r2)     // Catch: Throwable -> 0x009d, all -> 0x009b
            if (r2 == 0) goto L_0x0073
            r2.close()     // Catch: Throwable -> 0x0074
        L_0x0073:
            return r0
        L_0x0074:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0073
        L_0x0079:
            r1 = move-exception
            r2 = r0
        L_0x007b:
            java.lang.String r3 = "CInfo"
            java.lang.String r4 = "InitXInfo"
            com.amap.api.services.a.bh.a(r1, r3, r4)     // Catch: all -> 0x009b
            if (r2 == 0) goto L_0x0073
            r2.close()     // Catch: Throwable -> 0x0088
            goto L_0x0073
        L_0x0088:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0073
        L_0x008d:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0090:
            if (r2 == 0) goto L_0x0095
            r2.close()     // Catch: Throwable -> 0x0096
        L_0x0095:
            throw r0
        L_0x0096:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0095
        L_0x009b:
            r0 = move-exception
            goto L_0x0090
        L_0x009d:
            r1 = move-exception
            goto L_0x007b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.az.b(android.content.Context, com.amap.api.services.a.az$a):byte[]");
    }

    private static byte[] a(Context context, ByteArrayOutputStream byteArrayOutputStream) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return c(context, bf.b(byteArrayOutputStream.toByteArray()));
    }

    static String d(Context context, byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        byte[] b = bf.b(a(context, bArr));
        return b != null ? bb.a(b) : "";
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        byte length;
        if (!TextUtils.isEmpty(str)) {
            if (str.getBytes().length > 255) {
                length = -1;
            } else {
                length = (byte) str.getBytes().length;
            }
            bf.a(byteArrayOutputStream, length, bf.a(str));
            return;
        }
        bf.a(byteArrayOutputStream, (byte) 0, new byte[0]);
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
        aVar.a = ba.q(context);
        aVar.b = ba.i(context);
        String f = ba.f(context);
        if (f == null) {
            f = "";
        }
        aVar.c = f;
        aVar.d = aw.c(context);
        aVar.e = Build.MODEL;
        aVar.f = Build.MANUFACTURER;
        aVar.g = Build.DEVICE;
        aVar.h = aw.b(context);
        aVar.i = aw.d(context);
        aVar.j = String.valueOf(Build.VERSION.SDK_INT);
        aVar.k = ba.r(context);
        aVar.l = ba.p(context);
        aVar.m = ba.m(context) + "";
        aVar.n = ba.l(context) + "";
        aVar.o = ba.s(context);
        aVar.p = ba.k(context);
        if (z) {
            aVar.q = "";
        } else {
            aVar.q = ba.h(context);
        }
        if (z) {
            aVar.r = "";
        } else {
            aVar.r = ba.g(context);
        }
        if (z) {
            aVar.s = "";
            aVar.t = "";
        } else {
            String[] j = ba.j(context);
            aVar.s = j[0];
            aVar.t = j[1];
        }
        return aVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
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

        private a() {
        }
    }
}

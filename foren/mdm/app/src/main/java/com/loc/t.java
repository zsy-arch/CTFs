package com.loc;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.hyphenate.util.HanziToPinyin;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: Utils.java */
/* loaded from: classes2.dex */
public final class t {
    static String a;

    static {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append("=");
        }
        a = sb.toString();
    }

    public static String a(long j) {
        try {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss:SSS", Locale.CHINA).format(new Date(j));
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(java.lang.Throwable r4) {
        /*
            r0 = 0
            java.io.StringWriter r3 = new java.io.StringWriter     // Catch: Throwable -> 0x002b, all -> 0x0041
            r3.<init>()     // Catch: Throwable -> 0x002b, all -> 0x0041
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch: Throwable -> 0x006c, all -> 0x0066
            r2.<init>(r3)     // Catch: Throwable -> 0x006c, all -> 0x0066
            r4.printStackTrace(r2)     // Catch: Throwable -> 0x006f, all -> 0x006a
            java.lang.Throwable r1 = r4.getCause()     // Catch: Throwable -> 0x006f, all -> 0x006a
        L_0x0012:
            if (r1 == 0) goto L_0x001c
            r1.printStackTrace(r2)     // Catch: Throwable -> 0x006f, all -> 0x006a
            java.lang.Throwable r1 = r1.getCause()     // Catch: Throwable -> 0x006f, all -> 0x006a
            goto L_0x0012
        L_0x001c:
            java.lang.String r0 = r3.toString()     // Catch: Throwable -> 0x006f, all -> 0x006a
            if (r3 == 0) goto L_0x0025
            r3.close()     // Catch: Throwable -> 0x005f
        L_0x0025:
            if (r2 == 0) goto L_0x002a
            r2.close()     // Catch: Throwable -> 0x0064
        L_0x002a:
            return r0
        L_0x002b:
            r1 = move-exception
            r2 = r0
            r3 = r0
        L_0x002e:
            r1.printStackTrace()     // Catch: all -> 0x006a
            if (r3 == 0) goto L_0x0036
            r3.close()     // Catch: Throwable -> 0x005a
        L_0x0036:
            if (r2 == 0) goto L_0x002a
            r2.close()     // Catch: Throwable -> 0x003c
            goto L_0x002a
        L_0x003c:
            r1 = move-exception
        L_0x003d:
            r1.printStackTrace()
            goto L_0x002a
        L_0x0041:
            r1 = move-exception
            r2 = r0
            r3 = r0
            r0 = r1
        L_0x0045:
            if (r3 == 0) goto L_0x004a
            r3.close()     // Catch: Throwable -> 0x0050
        L_0x004a:
            if (r2 == 0) goto L_0x004f
            r2.close()     // Catch: Throwable -> 0x0055
        L_0x004f:
            throw r0
        L_0x0050:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x004a
        L_0x0055:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x004f
        L_0x005a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0036
        L_0x005f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0025
        L_0x0064:
            r1 = move-exception
            goto L_0x003d
        L_0x0066:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x0045
        L_0x006a:
            r0 = move-exception
            goto L_0x0045
        L_0x006c:
            r1 = move-exception
            r2 = r0
            goto L_0x002e
        L_0x006f:
            r1 = move-exception
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.t.a(java.lang.Throwable):java.lang.String");
    }

    public static String a(Map<String, String> map) {
        if (map == null || map.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (z) {
                    z = false;
                    stringBuffer.append(entry.getKey()).append("=").append(entry.getValue());
                } else {
                    stringBuffer.append(a.b).append(entry.getKey()).append("=").append(entry.getValue());
                }
            }
        } catch (Throwable th) {
            w.a(th, "Utils", "assembleParams");
        }
        return stringBuffer.toString();
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(bArr);
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        Log.i("authErrLog", a);
        Log.i("authErrLog", "                                   鉴权错误信息                                  ");
        Log.i("authErrLog", a);
        e("SHA1Package:" + k.e(context));
        e("key:" + k.f(context));
        e("csid:" + str);
        e("gsid:" + str2);
        e("json:" + str3);
        Log.i("authErrLog", "                                                                               ");
        Log.i("authErrLog", "请仔细检查 SHA1Package与Key申请信息是否对应，Key是否删除，平台是否匹配                ");
        Log.i("authErrLog", "如若确认无误，仍存在问题，请将全部log信息提交到工单系统，多谢合作                       ");
        Log.i("authErrLog", a);
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, byte b, byte[] bArr) {
        try {
            byteArrayOutputStream.write(new byte[]{b});
            int i = b & 255;
            if (i < 255 && i > 0) {
                byteArrayOutputStream.write(bArr);
            } else if (i == 255) {
                byteArrayOutputStream.write(bArr, 0, 255);
            }
        } catch (IOException e) {
            w.a(e, "Utils", "writeField");
        }
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        int i = 255;
        if (TextUtils.isEmpty(str)) {
            try {
                byteArrayOutputStream.write(new byte[]{0});
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            int length = str.length();
            if (length <= 255) {
                i = length;
            }
            a(byteArrayOutputStream, (byte) i, a(str));
        }
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }

    public static byte[] a() {
        try {
            String[] split = new StringBuffer("16,16,18,77,15,911,121,77,121,911,38,77,911,99,86,67,611,96,48,77,84,911,38,67,021,301,86,67,611,98,48,77,511,77,48,97,511,58,48,97,511,84,501,87,511,96,48,77,221,911,38,77,121,37,86,67,25,301,86,67,021,96,86,67,021,701,86,67,35,56,86,67,611,37,221,87").reverse().toString().split(",");
            byte[] bArr = new byte[split.length];
            for (int i = 0; i < split.length; i++) {
                bArr[i] = Byte.parseByte(split[i]);
            }
            String[] split2 = new StringBuffer(new String(o.b(new String(bArr)))).reverse().toString().split(",");
            byte[] bArr2 = new byte[split2.length];
            for (int i2 = 0; i2 < split2.length; i2++) {
                bArr2[i2] = Byte.parseByte(split2[i2]);
            }
            return bArr2;
        } catch (Throwable th) {
            w.a(th, "Utils", "getIV");
            return new byte[16];
        }
    }

    public static byte[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        String b = o.b(a(str));
        return ((char) ((b.length() % 26) + 65)) + b;
    }

    public static String b(Map<String, String> map) {
        String str;
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(a.b);
                }
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
            }
            str = sb.toString();
        } else {
            str = null;
        }
        return d(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0054 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.security.PublicKey b() throws java.security.cert.CertificateException, java.security.spec.InvalidKeySpecException, java.security.NoSuchAlgorithmException, java.lang.NullPointerException, java.io.IOException {
        /*
            r0 = 0
            java.lang.String r1 = "MIICnjCCAgegAwIBAgIJAJ0Pdzos7ZfYMA0GCSqGSIb3DQEBBQUAMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjAeFw0xMzA4MTUwNzU2NTVaFw0yMzA4MTMwNzU2NTVaMGgxCzAJBgNVBAYTAkNOMRMwEQYDVQQIDApTb21lLVN0YXRlMRAwDgYDVQQHDAdCZWlqaW5nMREwDwYDVQQKDAhBdXRvbmF2aTEfMB0GA1UEAwwWY29tLmF1dG9uYXZpLmFwaXNlcnZlcjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA8eWAyHbFPoFPfdx5AD+D4nYFq4dbJ1p7SIKt19Oz1oivF/6H43v5Fo7s50pD1UF8+Qu4JoUQxlAgOt8OCyQ8DYdkaeB74XKb1wxkIYg/foUwN1CMHPZ9O9ehgna6K4EJXZxR7Y7XVZnbjHZIVn3VpPU/Rdr2v37LjTw+qrABJxMCAwEAAaNQME4wHQYDVR0OBBYEFOM/MLGP8xpVFuVd+3qZkw7uBvOTMB8GA1UdIwQYMBaAFOM/MLGP8xpVFuVd+3qZkw7uBvOTMAwGA1UdEwQFMAMBAf8wDQYJKoZIhvcNAQEFBQADgYEA4LY3g8aAD8JkxAOqUXDDyLuCCGOc2pTIhn0TwMNaVdH4hZlpTeC/wuRD5LJ0z3j+IQ0vLvuQA5uDjVyEOlBrvVIGwSem/1XGUo13DfzgAJ5k1161S5l+sFUo5TxpHOXr8Z5nqJMjieXmhnE/I99GFyHpQmw4cC6rhYUhdhtg+Zk="
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: Throwable -> 0x0042, all -> 0x004f
            byte[] r1 = com.loc.o.b(r1)     // Catch: Throwable -> 0x0042, all -> 0x004f
            r2.<init>(r1)     // Catch: Throwable -> 0x0042, all -> 0x004f
            java.lang.String r1 = "X.509"
            java.security.cert.CertificateFactory r1 = java.security.cert.CertificateFactory.getInstance(r1)     // Catch: Throwable -> 0x0061, all -> 0x005f
            java.lang.String r3 = "RSA"
            java.security.KeyFactory r3 = java.security.KeyFactory.getInstance(r3)     // Catch: Throwable -> 0x0061, all -> 0x005f
            java.security.cert.Certificate r1 = r1.generateCertificate(r2)     // Catch: Throwable -> 0x0061, all -> 0x005f
            if (r1 == 0) goto L_0x0020
            if (r3 != 0) goto L_0x0026
        L_0x0020:
            if (r2 == 0) goto L_0x0025
            r2.close()     // Catch: Throwable -> 0x005d
        L_0x0025:
            return r0
        L_0x0026:
            java.security.spec.X509EncodedKeySpec r4 = new java.security.spec.X509EncodedKeySpec     // Catch: Throwable -> 0x0061, all -> 0x005f
            java.security.PublicKey r1 = r1.getPublicKey()     // Catch: Throwable -> 0x0061, all -> 0x005f
            byte[] r1 = r1.getEncoded()     // Catch: Throwable -> 0x0061, all -> 0x005f
            r4.<init>(r1)     // Catch: Throwable -> 0x0061, all -> 0x005f
            java.security.PublicKey r0 = r3.generatePublic(r4)     // Catch: Throwable -> 0x0061, all -> 0x005f
            if (r2 == 0) goto L_0x0025
            r2.close()     // Catch: Throwable -> 0x003d
            goto L_0x0025
        L_0x003d:
            r1 = move-exception
        L_0x003e:
            r1.printStackTrace()
            goto L_0x0025
        L_0x0042:
            r1 = move-exception
            r2 = r0
        L_0x0044:
            r1.printStackTrace()     // Catch: all -> 0x005f
            if (r2 == 0) goto L_0x0025
            r2.close()     // Catch: Throwable -> 0x004d
            goto L_0x0025
        L_0x004d:
            r1 = move-exception
            goto L_0x003e
        L_0x004f:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0052:
            if (r2 == 0) goto L_0x0057
            r2.close()     // Catch: Throwable -> 0x0058
        L_0x0057:
            throw r0
        L_0x0058:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0057
        L_0x005d:
            r1 = move-exception
            goto L_0x003e
        L_0x005f:
            r0 = move-exception
            goto L_0x0052
        L_0x0061:
            r1 = move-exception
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.t.b():java.security.PublicKey");
    }

    public static byte[] b(byte[] bArr) {
        try {
            return g(bArr);
        } catch (Throwable th) {
            w.a(th, "Utils", "gZip");
            return new byte[0];
        }
    }

    public static String c(String str) {
        return str.length() < 2 ? "" : o.a(str.substring(1));
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0062 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] c(byte[] r6) {
        /*
            r0 = 0
            if (r6 == 0) goto L_0x0006
            int r1 = r6.length
            if (r1 != 0) goto L_0x0007
        L_0x0006:
            return r0
        L_0x0007:
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: Throwable -> 0x003c, all -> 0x0057
            r2.<init>()     // Catch: Throwable -> 0x003c, all -> 0x0057
            java.util.zip.ZipOutputStream r3 = new java.util.zip.ZipOutputStream     // Catch: Throwable -> 0x0090, all -> 0x008a
            r3.<init>(r2)     // Catch: Throwable -> 0x0090, all -> 0x008a
            java.util.zip.ZipEntry r1 = new java.util.zip.ZipEntry     // Catch: Throwable -> 0x0093, all -> 0x008e
            java.lang.String r4 = "log"
            r1.<init>(r4)     // Catch: Throwable -> 0x0093, all -> 0x008e
            r3.putNextEntry(r1)     // Catch: Throwable -> 0x0093, all -> 0x008e
            r3.write(r6)     // Catch: Throwable -> 0x0093, all -> 0x008e
            r3.closeEntry()     // Catch: Throwable -> 0x0093, all -> 0x008e
            r3.finish()     // Catch: Throwable -> 0x0093, all -> 0x008e
            byte[] r0 = r2.toByteArray()     // Catch: Throwable -> 0x0093, all -> 0x008e
            if (r3 == 0) goto L_0x002d
            r3.close()     // Catch: Throwable -> 0x0081
        L_0x002d:
            if (r2 == 0) goto L_0x0006
            r2.close()     // Catch: Throwable -> 0x0033
            goto L_0x0006
        L_0x0033:
            r1 = move-exception
            java.lang.String r2 = "Utils"
            java.lang.String r3 = "zip2"
        L_0x0038:
            com.loc.w.a(r1, r2, r3)
            goto L_0x0006
        L_0x003c:
            r1 = move-exception
            r2 = r0
            r3 = r0
        L_0x003f:
            java.lang.String r4 = "Utils"
            java.lang.String r5 = "zip"
            com.loc.w.a(r1, r4, r5)     // Catch: all -> 0x008e
            if (r3 == 0) goto L_0x004b
            r3.close()     // Catch: Throwable -> 0x0078
        L_0x004b:
            if (r2 == 0) goto L_0x0006
            r2.close()     // Catch: Throwable -> 0x0051
            goto L_0x0006
        L_0x0051:
            r1 = move-exception
            java.lang.String r2 = "Utils"
            java.lang.String r3 = "zip2"
            goto L_0x0038
        L_0x0057:
            r1 = move-exception
            r2 = r0
            r3 = r0
            r0 = r1
        L_0x005b:
            if (r3 == 0) goto L_0x0060
            r3.close()     // Catch: Throwable -> 0x0066
        L_0x0060:
            if (r2 == 0) goto L_0x0065
            r2.close()     // Catch: Throwable -> 0x006f
        L_0x0065:
            throw r0
        L_0x0066:
            r1 = move-exception
            java.lang.String r3 = "Utils"
            java.lang.String r4 = "zip1"
            com.loc.w.a(r1, r3, r4)
            goto L_0x0060
        L_0x006f:
            r1 = move-exception
            java.lang.String r2 = "Utils"
            java.lang.String r3 = "zip2"
            com.loc.w.a(r1, r2, r3)
            goto L_0x0065
        L_0x0078:
            r1 = move-exception
            java.lang.String r3 = "Utils"
            java.lang.String r4 = "zip1"
            com.loc.w.a(r1, r3, r4)
            goto L_0x004b
        L_0x0081:
            r1 = move-exception
            java.lang.String r3 = "Utils"
            java.lang.String r4 = "zip1"
            com.loc.w.a(r1, r3, r4)
            goto L_0x002d
        L_0x008a:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L_0x005b
        L_0x008e:
            r0 = move-exception
            goto L_0x005b
        L_0x0090:
            r1 = move-exception
            r3 = r0
            goto L_0x003f
        L_0x0093:
            r1 = move-exception
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.t.c(byte[]):byte[]");
    }

    private static String d(String str) {
        try {
        } catch (Throwable th) {
            w.a(th, "Utils", "sortParams");
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(a.b);
        Arrays.sort(split);
        StringBuffer stringBuffer = new StringBuffer();
        for (String str2 : split) {
            stringBuffer.append(str2);
            stringBuffer.append(a.b);
        }
        String stringBuffer2 = stringBuffer.toString();
        if (stringBuffer2.length() > 1) {
            return (String) stringBuffer2.subSequence(0, stringBuffer2.length() - 1);
        }
        return str;
    }

    public static String d(byte[] bArr) {
        try {
            return f(bArr);
        } catch (Throwable th) {
            w.a(th, "Utils", "HexString");
            return null;
        }
    }

    public static String e(byte[] bArr) {
        try {
            return f(bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static void e(String str) {
        while (str.length() >= 78) {
            Log.i("authErrLog", "|" + str.substring(0, 78) + "|");
            str = str.substring(78);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("|").append(str);
        for (int i = 0; i < 78 - str.length(); i++) {
            sb.append(HanziToPinyin.Token.SEPARATOR);
        }
        sb.append("|");
        Log.i("authErrLog", sb.toString());
    }

    public static String f(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x002e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static byte[] g(byte[] r4) throws java.io.IOException, java.lang.Throwable {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
        L_0x0003:
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: Throwable -> 0x0025, all -> 0x003d
            r2.<init>()     // Catch: Throwable -> 0x0025, all -> 0x003d
            java.util.zip.GZIPOutputStream r1 = new java.util.zip.GZIPOutputStream     // Catch: Throwable -> 0x0048, all -> 0x0043
            r1.<init>(r2)     // Catch: Throwable -> 0x0048, all -> 0x0043
            r1.write(r4)     // Catch: Throwable -> 0x004d, all -> 0x002b
            r1.finish()     // Catch: Throwable -> 0x004d, all -> 0x002b
            byte[] r0 = r2.toByteArray()     // Catch: Throwable -> 0x004d, all -> 0x002b
            if (r1 == 0) goto L_0x001d
            r1.close()     // Catch: Throwable -> 0x003b
        L_0x001d:
            if (r2 == 0) goto L_0x0003
            r2.close()     // Catch: Throwable -> 0x0023
            goto L_0x0003
        L_0x0023:
            r0 = move-exception
            throw r0
        L_0x0025:
            r1 = move-exception
            r2 = r0
            r3 = r0
            r0 = r1
            r1 = r3
        L_0x002a:
            throw r0     // Catch: all -> 0x002b
        L_0x002b:
            r0 = move-exception
        L_0x002c:
            if (r1 == 0) goto L_0x0031
            r1.close()     // Catch: Throwable -> 0x0037
        L_0x0031:
            if (r2 == 0) goto L_0x0036
            r2.close()     // Catch: Throwable -> 0x0039
        L_0x0036:
            throw r0
        L_0x0037:
            r0 = move-exception
            throw r0
        L_0x0039:
            r0 = move-exception
            throw r0
        L_0x003b:
            r0 = move-exception
            throw r0
        L_0x003d:
            r1 = move-exception
            r2 = r0
            r3 = r0
            r0 = r1
            r1 = r3
            goto L_0x002c
        L_0x0043:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x002c
        L_0x0048:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x002a
        L_0x004d:
            r0 = move-exception
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.t.g(byte[]):byte[]");
    }
}

package com.loc;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: MD5.java */
/* loaded from: classes2.dex */
public final class p {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static String a(String str) {
        String str2;
        FileInputStream fileInputStream;
        try {
            str2 = 0;
            str2 = 0;
            str2 = 0;
            str2 = 0;
            str2 = 0;
            str2 = 0;
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.isFile() && file.exists()) {
                    byte[] bArr = new byte[2048];
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    fileInputStream = new FileInputStream(file);
                    while (true) {
                        try {
                            int read = fileInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            instance.update(bArr, 0, read);
                        } catch (Throwable th2) {
                            th = th2;
                            w.a(th, "MD5", "getMd5FromFile");
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    w.a(e, "MD5", "getMd5FromFile");
                                }
                            }
                            return str2;
                        }
                    }
                    String d = t.d(instance.digest());
                    try {
                        fileInputStream.close();
                        str2 = d;
                    } catch (IOException e2) {
                        w.a(e2, "MD5", "getMd5FromFile");
                        str2 = d;
                    }
                }
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
        return str2;
    }

    public static String a(byte[] bArr) {
        return t.d(a(bArr, "MD5"));
    }

    public static byte[] a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return instance.digest();
        } catch (Throwable th) {
            w.a(th, "MD5", "getMd5Bytes1");
            return null;
        }
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        return t.d(d(str));
    }

    public static String c(String str) {
        return t.e(e(str));
    }

    private static byte[] d(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            w.a(th, "MD5", "getMd5Bytes");
            return new byte[0];
        }
    }

    private static byte[] e(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] f(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        MessageDigest instance = MessageDigest.getInstance("MD5");
        instance.update(t.a(str));
        return instance.digest();
    }
}

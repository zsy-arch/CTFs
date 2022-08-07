package com.amap.api.col;

import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: MD5.java */
/* loaded from: classes.dex */
public class gg {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    public static String a(String str) {
        String str2;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        try {
            str2 = 0;
            str2 = 0;
            str2 = 0;
            str2 = 0;
            str2 = 0;
            str2 = 0;
            str2 = 0;
            str2 = 0;
            str2 = 0;
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.isFile() && file.exists()) {
                    byte[] bArr = new byte[2048];
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    fileInputStream2 = new FileInputStream(file);
                    while (true) {
                        try {
                            int read = fileInputStream2.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            instance.update(bArr, 0, read);
                        } catch (Throwable th2) {
                            th = th2;
                            go.a(th, "MD5", "getMd5FromFile");
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e) {
                                    go.a(e, "MD5", "getMd5FromFile");
                                }
                            }
                            return str2;
                        }
                    }
                    str2 = gk.d(instance.digest());
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e2) {
                            go.a(e2, "MD5", "getMd5FromFile");
                        }
                    }
                } else if (0 != 0) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        go.a(e3, "MD5", "getMd5FromFile");
                    }
                }
            } else if (0 != 0) {
                try {
                    fileInputStream.close();
                } catch (IOException e4) {
                    go.a(e4, "MD5", "getMd5FromFile");
                }
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = null;
        }
        return str2;
    }

    public static String b(String str) {
        if (str == null) {
            return null;
        }
        return gk.d(d(str));
    }

    public static String a(byte[] bArr) {
        return gk.d(b(bArr));
    }

    public static String c(String str) {
        return gk.e(e(str));
    }

    public static byte[] a(byte[] bArr, String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            instance.update(bArr);
            return instance.digest();
        } catch (Throwable th) {
            go.a(th, "MD5", "getMd5Bytes1");
            return null;
        }
    }

    private static byte[] b(byte[] bArr) {
        return a(bArr, "MD5");
    }

    public static byte[] d(String str) {
        try {
            return f(str);
        } catch (Throwable th) {
            go.a(th, "MD5", "getMd5Bytes");
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
        instance.update(gk.a(str));
        return instance.digest();
    }
}

package com.tencent.smtt.utils;

import java.io.InputStream;
import java.security.MessageDigest;

/* loaded from: classes.dex */
public class Md5Utils {
    /* JADX WARN: Removed duplicated region for block: B:39:0x0053 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0048 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getMD5(java.io.File r5) {
        /*
            r0 = 0
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch: NoSuchAlgorithmException -> 0x000e, FileNotFoundException -> 0x000c, IOException -> 0x000a, all -> 0x0008
            goto L_0x0013
        L_0x0008:
            r5 = move-exception
            goto L_0x003b
        L_0x000a:
            r2 = r0
            goto L_0x0046
        L_0x000c:
            r2 = r0
            goto L_0x0051
        L_0x000e:
            r1 = move-exception
            r1.printStackTrace()     // Catch: FileNotFoundException -> 0x000c, IOException -> 0x000a, all -> 0x0008
            r1 = r0
        L_0x0013:
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: FileNotFoundException -> 0x000c, IOException -> 0x000a, all -> 0x0008
            r2.<init>(r5)     // Catch: FileNotFoundException -> 0x000c, IOException -> 0x000a, all -> 0x0008
            r5 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r5]     // Catch: FileNotFoundException -> 0x0051, IOException -> 0x0046, all -> 0x0039
        L_0x001c:
            int r3 = r2.read(r5)     // Catch: FileNotFoundException -> 0x0051, IOException -> 0x0046, all -> 0x0039
            r4 = -1
            if (r3 == r4) goto L_0x0028
            r4 = 0
            r1.update(r5, r4, r3)     // Catch: FileNotFoundException -> 0x0051, IOException -> 0x0046, all -> 0x0039
            goto L_0x001c
        L_0x0028:
            byte[] r5 = r1.digest()     // Catch: FileNotFoundException -> 0x0051, IOException -> 0x0046, all -> 0x0039
            java.lang.String r5 = com.tencent.smtt.utils.ByteUtils.a(r5)     // Catch: FileNotFoundException -> 0x0051, IOException -> 0x0046, all -> 0x0039
            r2.close()     // Catch: IOException -> 0x0034
            goto L_0x0038
        L_0x0034:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0038:
            return r5
        L_0x0039:
            r5 = move-exception
            r0 = r2
        L_0x003b:
            if (r0 == 0) goto L_0x0045
            r0.close()     // Catch: IOException -> 0x0041
            goto L_0x0045
        L_0x0041:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0045:
            throw r5
        L_0x0046:
            if (r2 == 0) goto L_0x0050
            r2.close()     // Catch: IOException -> 0x004c
            goto L_0x0050
        L_0x004c:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0050:
            return r0
        L_0x0051:
            if (r2 == 0) goto L_0x005b
            r2.close()     // Catch: IOException -> 0x0057
            goto L_0x005b
        L_0x0057:
            r5 = move-exception
            r5.printStackTrace()
        L_0x005b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.Md5Utils.getMD5(java.io.File):java.lang.String");
    }

    public static String getMD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bytes);
            return ByteUtils.a(instance.digest());
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] getMD5(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            if (instance == null) {
                return null;
            }
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    return instance.digest();
                }
                instance.update(bArr, 0, read);
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    public static byte[] getMD5(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            return instance.digest();
        } catch (Exception unused) {
            return null;
        }
    }
}

package com.baidu.mobstat;

import java.security.MessageDigest;
import u.aly.dc;

/* loaded from: classes.dex */
public final class co {
    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] >> 4) & 15;
            int i3 = bArr[i] & dc.m;
            sb.append((char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48));
            sb.append((char) (i3 >= 10 ? (i3 + 97) - 10 : i3 + 48));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.security.MessageDigest r4, java.io.File r5) {
        /*
            boolean r0 = r5.isFile()
            if (r0 == 0) goto L_0x004b
            r2 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: Exception -> 0x0050, all -> 0x003e
            r1.<init>(r5)     // Catch: Exception -> 0x0050, all -> 0x003e
            r0 = 4048(0xfd0, float:5.672E-42)
            byte[] r0 = new byte[r0]     // Catch: Exception -> 0x002a, all -> 0x004e
        L_0x0010:
            int r2 = r1.read(r0)     // Catch: Exception -> 0x002a, all -> 0x004e
            r3 = -1
            if (r2 != r3) goto L_0x0025
            if (r1 == 0) goto L_0x001c
            r1.close()     // Catch: IOException -> 0x0039
        L_0x001c:
            byte[] r0 = r4.digest()
            java.lang.String r0 = a(r0)
        L_0x0024:
            return r0
        L_0x0025:
            r3 = 0
            r4.update(r0, r3, r2)     // Catch: Exception -> 0x002a, all -> 0x004e
            goto L_0x0010
        L_0x002a:
            r0 = move-exception
        L_0x002b:
            com.baidu.mobstat.cr.b(r0)     // Catch: all -> 0x004e
            if (r1 == 0) goto L_0x001c
            r1.close()     // Catch: IOException -> 0x0034
            goto L_0x001c
        L_0x0034:
            r0 = move-exception
            com.baidu.mobstat.cr.b(r0)
            goto L_0x001c
        L_0x0039:
            r0 = move-exception
            com.baidu.mobstat.cr.b(r0)
            goto L_0x001c
        L_0x003e:
            r0 = move-exception
            r1 = r2
        L_0x0040:
            if (r1 == 0) goto L_0x0045
            r1.close()     // Catch: IOException -> 0x0046
        L_0x0045:
            throw r0
        L_0x0046:
            r1 = move-exception
            com.baidu.mobstat.cr.b(r1)
            goto L_0x0045
        L_0x004b:
            java.lang.String r0 = ""
            goto L_0x0024
        L_0x004e:
            r0 = move-exception
            goto L_0x0040
        L_0x0050:
            r0 = move-exception
            r1 = r2
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.co.b(java.security.MessageDigest, java.io.File):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        return a(messageDigest.digest());
    }
}

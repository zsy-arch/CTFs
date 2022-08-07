package cn.jpush.android.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
import u.aly.dc;

/* loaded from: classes.dex */
public final class ao {
    private static String a;
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r2 = 1
            r1 = 0
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "P\u0019jI\u000fU\u001eoB\u0002!j\u001b>~&"
            r0 = -1
            r5 = r4
            r6 = r4
            r4 = r1
        L_0x000b:
            char[] r3 = r3.toCharArray()
            int r7 = r3.length
            if (r7 > r2) goto L_0x0067
            r8 = r1
        L_0x0013:
            r9 = r3
            r10 = r8
            r13 = r7
            r7 = r3
            r3 = r13
        L_0x0018:
            char r12 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x005b;
                case 1: goto L_0x005e;
                case 2: goto L_0x0061;
                case 3: goto L_0x0064;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 59
        L_0x0021:
            r11 = r11 ^ r12
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r3 != 0) goto L_0x002d
            r7 = r9
            r10 = r8
            r8 = r3
            goto L_0x0018
        L_0x002d:
            r7 = r3
            r3 = r9
        L_0x002f:
            if (r7 > r8) goto L_0x0013
            java.lang.String r7 = new java.lang.String
            r7.<init>(r3)
            java.lang.String r3 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0046;
                case 1: goto L_0x0050;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r4] = r3
            java.lang.String r0 = "-lm"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = ";v\u0004\r\u0018Dh\u0004W主M龍\u0005Q"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0050:
            r5[r4] = r3
            cn.jpush.android.util.ao.z = r6
            java.lang.String[] r0 = cn.jpush.android.util.ao.z
            r0 = r0[r1]
            cn.jpush.android.util.ao.a = r0
            return
        L_0x005b:
            r11 = 96
            goto L_0x0021
        L_0x005e:
            r11 = 40
            goto L_0x0021
        L_0x0061:
            r11 = 88
            goto L_0x0021
        L_0x0064:
            r11 = 122(0x7a, float:1.71E-43)
            goto L_0x0021
        L_0x0067:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ao.<clinit>():void");
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }

    public static boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return str.equals(str2);
    }

    public static String b(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(z[1]);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(digest.length * 2);
            for (byte b : digest) {
                stringBuffer.append(z[0].charAt((b >> 4) & 15)).append(z[0].charAt(b & dc.m));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String c(String str) {
        return a(str) ? "" : Pattern.compile(z[2]).matcher(str).replaceAll("");
    }
}

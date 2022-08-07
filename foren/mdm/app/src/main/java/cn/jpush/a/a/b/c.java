package cn.jpush.a.a.b;

import u.aly.dc;

/* loaded from: classes.dex */
public final class c {
    private static final String z;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r1 > r2) goto L_0x000b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
        cn.jpush.a.a.b.c.z = new java.lang.String(r0).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
        r5 = 'f';
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
        r5 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003b, code lost:
        r5 = '=';
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003e, code lost:
        r5 = '}';
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0009, code lost:
        if (r1 <= 1) goto L_0x000b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
        r6 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
        r5 = 'a';
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        r1[r2] = (char) (r5 ^ r6);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        if (r1 != 0) goto L_0x0025;
     */
    static {
        /*
            java.lang.String r0 = "V!\u000fNUS&\nEX'R~9$ "
            char[] r0 = r0.toCharArray()
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 > r3) goto L_0x0027
        L_0x000b:
            r3 = r0
            r4 = r2
            r7 = r1
            r1 = r0
            r0 = r7
        L_0x0010:
            char r6 = r1[r2]
            int r5 = r4 % 5
            switch(r5) {
                case 0: goto L_0x0035;
                case 1: goto L_0x0038;
                case 2: goto L_0x003b;
                case 3: goto L_0x003e;
                default: goto L_0x0017;
            }
        L_0x0017:
            r5 = 97
        L_0x0019:
            r5 = r5 ^ r6
            char r5 = (char) r5
            r1[r2] = r5
            int r2 = r4 + 1
            if (r0 != 0) goto L_0x0025
            r1 = r3
            r4 = r2
            r2 = r0
            goto L_0x0010
        L_0x0025:
            r1 = r0
            r0 = r3
        L_0x0027:
            if (r1 > r2) goto L_0x000b
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            java.lang.String r0 = r1.intern()
            cn.jpush.a.a.b.c.z = r0
            return
        L_0x0035:
            r5 = 102(0x66, float:1.43E-43)
            goto L_0x0019
        L_0x0038:
            r5 = 16
            goto L_0x0019
        L_0x003b:
            r5 = 61
            goto L_0x0019
        L_0x003e:
            r5 = 125(0x7d, float:1.75E-43)
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.b.c.<clinit>():void");
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b : bArr) {
            stringBuffer.append(z.charAt((b >> 4) & 15)).append(z.charAt(b & dc.m));
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }
}

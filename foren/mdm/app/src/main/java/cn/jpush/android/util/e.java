package cn.jpush.android.util;

import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class e {
    static final /* synthetic */ boolean a;
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r4 = 1
            r1 = 0
            r0 = 2
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r2 = ".>\u001c\u0010P-,\u001d\u001d\u0004x"
            r0 = -1
            r5 = r3
            r6 = r3
            r3 = r1
        L_0x000b:
            char[] r2 = r2.toCharArray()
            int r7 = r2.length
            if (r7 > r4) goto L_0x0063
            r8 = r1
        L_0x0013:
            r9 = r2
            r10 = r8
            r13 = r7
            r7 = r2
            r2 = r13
        L_0x0018:
            char r12 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x0055;
                case 1: goto L_0x0058;
                case 2: goto L_0x005b;
                case 3: goto L_0x005e;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 50
        L_0x0021:
            r11 = r11 ^ r12
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r2 != 0) goto L_0x002d
            r7 = r9
            r10 = r8
            r8 = r2
            goto L_0x0018
        L_0x002d:
            r7 = r2
            r2 = r9
        L_0x002f:
            if (r7 > r8) goto L_0x0013
            java.lang.String r7 = new java.lang.String
            r7.<init>(r2)
            java.lang.String r2 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0046;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r3] = r2
            java.lang.String r0 = "\u0019\fUqa\u000f\u00161"
            r2 = r0
            r3 = r4
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r3] = r2
            cn.jpush.android.util.e.z = r6
            java.lang.Class<cn.jpush.android.util.e> r0 = cn.jpush.android.util.e.class
            boolean r0 = r0.desiredAssertionStatus()
            if (r0 != 0) goto L_0x0061
        L_0x0052:
            cn.jpush.android.util.e.a = r4
            return
        L_0x0055:
            r11 = 76
            goto L_0x0021
        L_0x0058:
            r11 = 95
            goto L_0x0021
        L_0x005b:
            r11 = 120(0x78, float:1.68E-43)
            goto L_0x0021
        L_0x005e:
            r11 = 48
            goto L_0x0021
        L_0x0061:
            r4 = r1
            goto L_0x0052
        L_0x0063:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.e.<clinit>():void");
    }

    private e() {
    }

    public static String a(byte[] bArr, int i) {
        int i2;
        int i3 = 1;
        try {
            int length = bArr.length;
            h hVar = new h(i, null);
            int i4 = (length / 3) * 4;
            if (!hVar.d) {
                switch (length % 3) {
                    case 1:
                        i4 += 2;
                        break;
                    case 2:
                        i4 += 3;
                        break;
                }
            } else if (length % 3 > 0) {
                i4 += 4;
            }
            if (!hVar.e || length <= 0) {
                i2 = i4;
            } else {
                int i5 = ((length - 1) / 57) + 1;
                if (hVar.f) {
                    i3 = 2;
                }
                i2 = (i3 * i5) + i4;
            }
            hVar.a = new byte[i2];
            hVar.a(bArr, 0, length, true);
            if (a || hVar.b == i2) {
                return new String(hVar.a, z[1]);
            }
            throw new AssertionError();
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] a(String str, int i) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        g gVar = new g(2, new byte[(length * 3) / 4]);
        if (!gVar.a(bytes, 0, length, true)) {
            throw new IllegalArgumentException(z[0]);
        } else if (gVar.b == gVar.a.length) {
            return gVar.a;
        } else {
            byte[] bArr = new byte[gVar.b];
            System.arraycopy(gVar.a, 0, bArr, 0, gVar.b);
            return bArr;
        }
    }
}

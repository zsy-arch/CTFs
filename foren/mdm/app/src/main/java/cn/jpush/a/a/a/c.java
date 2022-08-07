package cn.jpush.a.a.a;

import cn.jpush.a.a.b.b;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class c {
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r2 = 1
            r1 = 0
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "\bLZIT\u001c\r@_P\u001c\r\u0005\u001a"
            r0 = -1
            r5 = r4
            r6 = r4
            r4 = r1
        L_0x000b:
            char[] r3 = r3.toCharArray()
            int r7 = r3.length
            if (r7 > r2) goto L_0x0061
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
                case 0: goto L_0x0055;
                case 1: goto L_0x0058;
                case 2: goto L_0x005b;
                case 3: goto L_0x005e;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 49
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
            java.lang.String r0 = "\fB\\[]XOQNT\u000b\r\u0005\u001a"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "-CCT^\u000fC\bY^\u0015@ITUXKGH\u0011\bLZIX\u0016J\bS_\u001aB]TUV"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0050:
            r5[r4] = r3
            cn.jpush.a.a.a.c.z = r6
            return
        L_0x0055:
            r11 = 120(0x78, float:1.68E-43)
            goto L_0x0021
        L_0x0058:
            r11 = 45
            goto L_0x0021
        L_0x005b:
            r11 = 40
            goto L_0x0021
        L_0x005e:
            r11 = 58
            goto L_0x0021
        L_0x0061:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.a.c.<clinit>():void");
    }

    public static g a(byte[] bArr) {
        new StringBuilder(z[1]).append(cn.jpush.a.a.b.c.a(bArr));
        int length = bArr.length - 20;
        byte[] bArr2 = new byte[20];
        System.arraycopy(bArr, 0, bArr2, 0, 20);
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 20, bArr3, 0, length);
        ByteBuffer wrap = ByteBuffer.wrap(bArr3);
        d dVar = new d(false, bArr2);
        new StringBuilder(z[0]).append(dVar.toString());
        switch (dVar.c) {
            case 0:
                return new j(dVar, wrap);
            case 1:
                return new h(dVar, wrap);
            case 3:
                return new i(dVar, wrap);
            case 10:
                return new l(dVar, wrap);
            case 19:
                return new a(dVar, wrap);
            case 25:
                return new b(dVar, wrap);
            case 100:
                return null;
            default:
                b.a(z[2]);
                return null;
        }
    }
}

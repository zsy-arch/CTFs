package cn.jpush.android.util;

import cn.jpush.android.e;

/* loaded from: classes.dex */
public final class ap {
    private static final String[] z;
    private String a;
    private String b;
    private boolean c = false;
    private long d;

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
            r1 = 0
            r2 = 1
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "3F\u0005!\u000ew\u0006M!-\u007f\u001fKi?lKNn(>L"
            r0 = -1
            r5 = r4
            r6 = r4
            r4 = r1
        L_0x000b:
            char[] r3 = r3.toCharArray()
            int r7 = r3.length
            if (r7 > r2) goto L_0x0060
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
            r11 = 90
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
            java.lang.String r0 = "s\u0018"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "9Q\b"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0050:
            r5[r4] = r3
            cn.jpush.android.util.ap.z = r6
            return
        L_0x0055:
            r11 = 30
            goto L_0x0021
        L_0x0058:
            r11 = 107(0x6b, float:1.5E-43)
            goto L_0x0021
        L_0x005b:
            r11 = 40
            goto L_0x0021
        L_0x005e:
            r11 = r2
            goto L_0x0021
        L_0x0060:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ap.<clinit>():void");
    }

    public ap(String str, String str2) {
        if (e.a) {
            this.a = str;
            this.b = str2;
            this.d = System.currentTimeMillis();
        }
    }

    public final void a() {
        if (e.a) {
            new StringBuilder(z[0]).append(this.b).append(z[2]).append(System.currentTimeMillis() - this.d).append(z[1]);
            ac.b();
            if (!this.c) {
                this.d = System.currentTimeMillis();
            }
        }
    }
}

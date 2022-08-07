package cn.jpush.android.service;

/* loaded from: classes.dex */
final class n {
    private static final String[] z;
    String a;
    int b;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b A[LOOP:1: B:7:0x001a->B:12:0x002b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x002f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    static {
        /*
            r3 = 2
            r2 = 1
            r1 = 0
            r0 = 4
            java.lang.String[] r5 = new java.lang.String[r0]
            java.lang.String r4 = "|xu\u0010rEd'\n7Isb\u0000rJxuD3\faf\b;H7f\u00006^rt\u0017~\fdw\b;X7e\u001dr\u0016"
            r0 = -1
            r6 = r5
            r7 = r5
            r5 = r1
        L_0x000d:
            char[] r4 = r4.toCharArray()
            int r8 = r4.length
            if (r8 > r2) goto L_0x006b
            r9 = r1
        L_0x0015:
            r10 = r4
            r11 = r9
            r14 = r8
            r8 = r4
            r4 = r14
        L_0x001a:
            char r13 = r8[r9]
            int r12 = r11 % 5
            switch(r12) {
                case 0: goto L_0x0060;
                case 1: goto L_0x0063;
                case 2: goto L_0x0066;
                case 3: goto L_0x0068;
                default: goto L_0x0021;
            }
        L_0x0021:
            r12 = 82
        L_0x0023:
            r12 = r12 ^ r13
            char r12 = (char) r12
            r8[r9] = r12
            int r9 = r11 + 1
            if (r4 != 0) goto L_0x002f
            r8 = r10
            r11 = r9
            r9 = r4
            goto L_0x001a
        L_0x002f:
            r8 = r4
            r4 = r10
        L_0x0031:
            if (r8 > r9) goto L_0x0015
            java.lang.String r8 = new java.lang.String
            r8.<init>(r4)
            java.lang.String r4 = r8.intern()
            switch(r0) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0051;
                case 2: goto L_0x005b;
                default: goto L_0x003f;
            }
        L_0x003f:
            r6[r5] = r4
            java.lang.String r0 = "eyq\u0005>Es'\u0014=^c'Ir"
            r4 = r0
            r5 = r2
            r6 = r7
            r0 = r1
            goto L_0x000d
        L_0x0048:
            r6[r5] = r4
            java.lang.String r0 = "eyq\u0005>Es'\u0014=^c'Ir\u001c"
            r4 = r0
            r5 = r3
            r6 = r7
            r0 = r2
            goto L_0x000d
        L_0x0051:
            r6[r5] = r4
            r4 = 3
            java.lang.String r0 = "eyq\u0005>Es'\r\"\f:'"
            r5 = r4
            r6 = r7
            r4 = r0
            r0 = r3
            goto L_0x000d
        L_0x005b:
            r6[r5] = r4
            cn.jpush.android.service.n.z = r7
            return
        L_0x0060:
            r12 = 44
            goto L_0x0023
        L_0x0063:
            r12 = 23
            goto L_0x0023
        L_0x0066:
            r12 = 7
            goto L_0x0023
        L_0x0068:
            r12 = 100
            goto L_0x0023
        L_0x006b:
            r9 = r1
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.n.<clinit>():void");
    }

    public n(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            throw new Exception(z[0]);
        }
        this.a = str.substring(0, indexOf);
        if (!SisInfo.isValidIPV4(this.a)) {
            throw new Exception(z[3] + this.a);
        }
        String substring = str.substring(indexOf + 1);
        try {
            this.b = Integer.parseInt(substring);
            if (this.b == 0) {
                throw new Exception(z[2]);
            }
        } catch (Exception e) {
            throw new Exception(z[1] + substring);
        }
    }

    public final String toString() {
        return this.a + ":" + this.b;
    }
}

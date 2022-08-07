package cn.jpush.a.a.b;

/* loaded from: classes.dex */
public final class b {
    private static final String z;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0022, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
        if (r1 > r2) goto L_0x000c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002a, code lost:
        cn.jpush.a.a.b.b.z = new java.lang.String(r0).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0035, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
        r5 = '!';
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
        r5 = 30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003c, code lost:
        r5 = 's';
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003f, code lost:
        r5 = '/';
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x000a, code lost:
        if (r1 <= 1) goto L_0x000c;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000c, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        r6 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0015, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x0036;
            case 1: goto L_0x0039;
            case 2: goto L_0x003c;
            case 3: goto L_0x003f;
            default: goto L_0x0018;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0018, code lost:
        r5 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001a, code lost:
        r1[r2] = (char) (r5 ^ r6);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r1 != 0) goto L_0x0026;
     */
    static {
        /*
            java.lang.String r0 = "zT#ZbICS\u00021"
            char[] r0 = r0.toCharArray()
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 > r3) goto L_0x0028
        L_0x000c:
            r3 = r0
            r4 = r2
            r7 = r1
            r1 = r0
            r0 = r7
        L_0x0011:
            char r6 = r1[r2]
            int r5 = r4 % 5
            switch(r5) {
                case 0: goto L_0x0036;
                case 1: goto L_0x0039;
                case 2: goto L_0x003c;
                case 3: goto L_0x003f;
                default: goto L_0x0018;
            }
        L_0x0018:
            r5 = 17
        L_0x001a:
            r5 = r5 ^ r6
            char r5 = (char) r5
            r1[r2] = r5
            int r2 = r4 + 1
            if (r0 != 0) goto L_0x0026
            r1 = r3
            r4 = r2
            r2 = r0
            goto L_0x0011
        L_0x0026:
            r1 = r0
            r0 = r3
        L_0x0028:
            if (r1 > r2) goto L_0x000c
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            java.lang.String r0 = r1.intern()
            cn.jpush.a.a.b.b.z = r0
            return
        L_0x0036:
            r5 = 33
            goto L_0x001a
        L_0x0039:
            r5 = 30
            goto L_0x001a
        L_0x003c:
            r5 = 115(0x73, float:1.61E-43)
            goto L_0x001a
        L_0x003f:
            r5 = 47
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.b.b.<clinit>():void");
    }

    public static void a(String str) {
        System.out.println(z + str);
    }

    public static void b(String str) {
        System.out.println(z + str);
    }
}

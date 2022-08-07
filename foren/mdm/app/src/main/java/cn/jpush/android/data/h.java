package cn.jpush.android.data;

/* loaded from: classes.dex */
public final class h {
    private static final String[] z;
    private long a;
    private int b;
    private int c;
    private int d;
    private String e;
    private long f;
    private long g;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r5 != 0) goto L_0x002b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5 > r6) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            case 5: goto L_0x006b;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "U|TR}W|CZwppVGxv}sqUxgV\u0013Ju}hZu$";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "53[]NxwSlep~R\u000e";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = "53[]NkvZ\\g|.";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "53[]N|kCAp$";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "53[]NmjGV,";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
        r3[r2] = r1;
        r2 = 6;
        r1 = "53[]Nz|B]e$";
        r0 = 5;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x006b, code lost:
        r3[r2] = r1;
        cn.jpush.android.data.h.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x006f, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0070, code lost:
        r9 = 25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0073, code lost:
        r9 = 19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0076, code lost:
        r9 = '7';
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0079, code lost:
        r9 = '3';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r5 <= 1) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0070;
            case 1: goto L_0x0073;
            case 2: goto L_0x0076;
            case 3: goto L_0x0079;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 7
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "53[]Nma^Tv|ahGxtv\n"
            r0 = -1
            r4 = r3
        L_0x0008:
            char[] r1 = r1.toCharArray()
            int r5 = r1.length
            r6 = 0
            r7 = 1
            if (r5 > r7) goto L_0x002d
        L_0x0011:
            r7 = r1
            r8 = r6
            r11 = r5
            r5 = r1
            r1 = r11
        L_0x0016:
            char r10 = r5[r6]
            int r9 = r8 % 5
            switch(r9) {
                case 0: goto L_0x0070;
                case 1: goto L_0x0073;
                case 2: goto L_0x0076;
                case 3: goto L_0x0079;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 17
        L_0x001f:
            r9 = r9 ^ r10
            char r9 = (char) r9
            r5[r6] = r9
            int r6 = r8 + 1
            if (r1 != 0) goto L_0x002b
            r5 = r7
            r8 = r6
            r6 = r1
            goto L_0x0016
        L_0x002b:
            r5 = r1
            r1 = r7
        L_0x002d:
            if (r5 > r6) goto L_0x0011
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            java.lang.String r1 = r5.intern()
            switch(r0) {
                case 0: goto L_0x0043;
                case 1: goto L_0x004b;
                case 2: goto L_0x0053;
                case 3: goto L_0x005b;
                case 4: goto L_0x0063;
                case 5: goto L_0x006b;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "U|TR}W|CZwppVGxv}sqUxgV\u0013Ju}hZu$"
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "53[]NxwSlep~R\u000e"
            r0 = 1
            r3 = r4
            goto L_0x0008
        L_0x004b:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = "53[]NkvZ\\g|."
            r0 = 2
            r3 = r4
            goto L_0x0008
        L_0x0053:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "53[]N|kCAp$"
            r0 = 3
            r3 = r4
            goto L_0x0008
        L_0x005b:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "53[]NmjGV,"
            r0 = 4
            r3 = r4
            goto L_0x0008
        L_0x0063:
            r3[r2] = r1
            r2 = 6
            java.lang.String r1 = "53[]Nz|B]e$"
            r0 = 5
            r3 = r4
            goto L_0x0008
        L_0x006b:
            r3[r2] = r1
            cn.jpush.android.data.h.z = r4
            return
        L_0x0070:
            r9 = 25
            goto L_0x001f
        L_0x0073:
            r9 = 19
            goto L_0x001f
        L_0x0076:
            r9 = 55
            goto L_0x001f
        L_0x0079:
            r9 = 51
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.h.<clinit>():void");
    }

    public h() {
        this.a = 0L;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = "";
        this.f = 0L;
        this.g = 0L;
        this.a = 0L;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = "";
        this.f = 0L;
        this.g = 0L;
    }

    public final long a() {
        return this.a;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final void a(long j) {
        this.a = j;
    }

    public final void a(String str) {
        this.e = str;
    }

    public final int b() {
        return this.b;
    }

    public final void b(int i) {
        this.c = i;
    }

    public final void b(long j) {
        this.g = j;
    }

    public final int c() {
        return this.c;
    }

    public final void c(int i) {
        this.d = i;
    }

    public final void c(long j) {
        this.f = j;
    }

    public final String d() {
        return this.e;
    }

    public final long e() {
        return this.g;
    }

    public final long f() {
        return this.f;
    }

    public final String toString() {
        return z[1] + this.a + z[6] + this.b + z[3] + this.c + z[5] + this.d + z[4] + this.e + z[0] + this.f + z[2] + this.g + "]";
    }
}

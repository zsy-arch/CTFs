package cn.jpush.a.a.a;

import cn.jpush.android.util.j;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class a extends g {
    private static final String[] z;
    int a;
    int b;
    int c;
    long d;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b A[LOOP:1: B:7:0x001a->B:12:0x002b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x002f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    static {
        /*
            r4 = 3
            r3 = 2
            r2 = 1
            r1 = 0
            r0 = 5
            java.lang.String[] r6 = new java.lang.String[r0]
            java.lang.String r5 = "\u0015?\b0\u001d!\f\u0006:?\u0013^F{!+\u000f\u001e> :=\u00046>/\u0010\u000fa"
            r0 = -1
            r7 = r6
            r8 = r6
            r6 = r1
        L_0x000d:
            char[] r5 = r5.toCharArray()
            int r9 = r5.length
            if (r9 > r2) goto L_0x0075
            r10 = r1
        L_0x0015:
            r11 = r5
            r12 = r10
            r15 = r9
            r9 = r5
            r5 = r15
        L_0x001a:
            char r14 = r9[r10]
            int r13 = r12 % 5
            switch(r13) {
                case 0: goto L_0x0069;
                case 1: goto L_0x006c;
                case 2: goto L_0x006f;
                case 3: goto L_0x0072;
                default: goto L_0x0021;
            }
        L_0x0021:
            r13 = 83
        L_0x0023:
            r13 = r13 ^ r14
            char r13 = (char) r13
            r9[r10] = r13
            int r10 = r12 + 1
            if (r5 != 0) goto L_0x002f
            r9 = r11
            r12 = r10
            r10 = r5
            goto L_0x001a
        L_0x002f:
            r9 = r5
            r5 = r11
        L_0x0031:
            if (r9 > r10) goto L_0x0015
            java.lang.String r9 = new java.lang.String
            r9.<init>(r5)
            java.lang.String r5 = r9.intern()
            switch(r0) {
                case 0: goto L_0x0048;
                case 1: goto L_0x0051;
                case 2: goto L_0x005a;
                case 3: goto L_0x0064;
                default: goto L_0x003f;
            }
        L_0x003f:
            r7[r6] = r5
            java.lang.String r0 = "nSK"
            r5 = r0
            r6 = r2
            r7 = r8
            r0 = r1
            goto L_0x000d
        L_0x0048:
            r7[r6] = r5
            java.lang.String r0 = "b^\u0018/:#\u001bQ"
            r5 = r0
            r6 = r3
            r7 = r8
            r0 = r2
            goto L_0x000d
        L_0x0051:
            r7[r6] = r5
            java.lang.String r0 = "b^\u0018/2:\u000b\u0018a"
            r5 = r0
            r6 = r4
            r7 = r8
            r0 = r3
            goto L_0x000d
        L_0x005a:
            r7[r6] = r5
            r5 = 4
            java.lang.String r0 = "b^\u0018/6>D"
            r6 = r5
            r7 = r8
            r5 = r0
            r0 = r4
            goto L_0x000d
        L_0x0064:
            r7[r6] = r5
            cn.jpush.a.a.a.a.z = r8
            return
        L_0x0069:
            r13 = 78
            goto L_0x0023
        L_0x006c:
            r13 = 126(0x7e, float:1.77E-43)
            goto L_0x0023
        L_0x006f:
            r13 = 107(0x6b, float:1.5E-43)
            goto L_0x0023
        L_0x0072:
            r13 = 91
            goto L_0x0023
        L_0x0075:
            r10 = r1
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.a.a.<clinit>():void");
    }

    public a(d dVar, ByteBuffer byteBuffer) {
        super(dVar, byteBuffer);
    }

    public final int a() {
        return this.a;
    }

    @Override // cn.jpush.a.a.a.g, cn.jpush.a.a.a.e
    public final void b() {
        super.b();
        ByteBuffer byteBuffer = this.f;
        this.a = j.c(byteBuffer, this).byteValue();
        this.b = j.c(byteBuffer, this).byteValue();
        this.c = j.c(byteBuffer, this).byteValue();
        this.d = j.d(byteBuffer, this);
    }

    @Override // cn.jpush.a.a.a.g, cn.jpush.a.a.a.e
    public final String toString() {
        return z[0] + this.a + z[4] + this.b + z[3] + this.c + z[2] + this.d + z[1] + super.toString();
    }
}

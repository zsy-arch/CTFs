package cn.jpush.a.a.a;

import cn.jpush.a.a.b.a;
import cn.jpush.android.util.j;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class i extends g {
    private static final String[] z;
    int a;
    long b;
    String c;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[LOOP:1: B:7:0x0019->B:12:0x002a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    static {
        /*
            r3 = 2
            r2 = 1
            r1 = 0
            r0 = 4
            java.lang.String[] r5 = new java.lang.String[r0]
            java.lang.String r4 = "7\u0014j"
            r0 = -1
            r6 = r5
            r7 = r5
            r5 = r1
        L_0x000c:
            char[] r4 = r4.toCharArray()
            int r8 = r4.length
            if (r8 > r2) goto L_0x006b
            r9 = r1
        L_0x0014:
            r10 = r4
            r11 = r9
            r14 = r8
            r8 = r4
            r4 = r14
        L_0x0019:
            char r13 = r8[r9]
            int r12 = r11 % 5
            switch(r12) {
                case 0: goto L_0x005f;
                case 1: goto L_0x0062;
                case 2: goto L_0x0065;
                case 3: goto L_0x0068;
                default: goto L_0x0020;
            }
        L_0x0020:
            r12 = 56
        L_0x0022:
            r12 = r12 ^ r13
            char r12 = (char) r12
            r8[r9] = r12
            int r9 = r11 + 1
            if (r4 != 0) goto L_0x002e
            r8 = r10
            r11 = r9
            r9 = r4
            goto L_0x0019
        L_0x002e:
            r8 = r4
            r4 = r10
        L_0x0030:
            if (r8 > r9) goto L_0x0014
            java.lang.String r8 = new java.lang.String
            r8.<init>(r4)
            java.lang.String r4 = r8.intern()
            switch(r0) {
                case 0: goto L_0x0047;
                case 1: goto L_0x0050;
                case 2: goto L_0x005a;
                default: goto L_0x003e;
            }
        L_0x003e:
            r6[r5] = r4
            java.lang.String r0 = ";\u0019'y_TV$~]yMp"
            r4 = r0
            r5 = r2
            r6 = r7
            r0 = r1
            goto L_0x000c
        L_0x0047:
            r6[r5] = r4
            java.lang.String r0 = ";\u0019'y_^]p"
            r4 = r0
            r5 = r3
            r6 = r7
            r0 = r2
            goto L_0x000c
        L_0x0050:
            r6[r5] = r4
            r4 = 3
            java.lang.String r0 = "Lt/yKv^/ZMdQ\u0017*\u00157T9mlnI/0"
            r5 = r4
            r6 = r7
            r4 = r0
            r0 = r3
            goto L_0x000c
        L_0x005a:
            r6[r5] = r4
            cn.jpush.a.a.a.i.z = r7
            return
        L_0x005f:
            r12 = 23
            goto L_0x0022
        L_0x0062:
            r12 = 57
            goto L_0x0022
        L_0x0065:
            r12 = 74
            goto L_0x0022
        L_0x0068:
            r12 = 10
            goto L_0x0022
        L_0x006b:
            r9 = r1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.a.i.<clinit>():void");
    }

    public i(d dVar, ByteBuffer byteBuffer) {
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
        this.b = j.d(byteBuffer, this);
        this.c = a.a(byteBuffer, this);
    }

    public final long c() {
        return this.b;
    }

    public final String f() {
        return this.c;
    }

    @Override // cn.jpush.a.a.a.g, cn.jpush.a.a.a.e
    public final String toString() {
        return z[3] + this.a + z[2] + this.b + z[1] + this.c + z[0] + super.toString();
    }
}

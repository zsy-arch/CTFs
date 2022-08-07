package cn.jpush.a.a.a;

import cn.jpush.a.a.b.b;
import cn.jpush.android.util.j;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class l extends g {
    private static final String[] z;
    int a;

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
            java.lang.String r4 = "Nl)5'\u0011-#5n"
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
            r12 = 84
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
            java.lang.String r0 = "0)7 ;\f?!p1\u0010>+\"tOl'?0\u0007v"
            r4 = r0
            r5 = r2
            r6 = r7
            r0 = r1
            goto L_0x000c
        L_0x0047:
            r6[r5] = r4
            java.lang.String r0 = "Bad"
            r4 = r0
            r5 = r3
            r6 = r7
            r0 = r2
            goto L_0x000c
        L_0x0050:
            r6[r5] = r4
            r4 = 3
            java.lang.String r0 = "9\u0018%75\u000e%%#\u0006\u0007?4?:\u0011)\u0019pyB?!!!\u0007\"'5n"
            r5 = r4
            r6 = r7
            r4 = r0
            r0 = r3
            goto L_0x000c
        L_0x005a:
            r6[r5] = r4
            cn.jpush.a.a.a.l.z = r7
            return
        L_0x005f:
            r12 = 98
            goto L_0x0022
        L_0x0062:
            r12 = 76
            goto L_0x0022
        L_0x0065:
            r12 = 68
            goto L_0x0022
        L_0x0068:
            r12 = 80
            goto L_0x0022
        L_0x006b:
            r9 = r1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.a.l.<clinit>():void");
    }

    public l(long j, long j2, int i, String str, int i2) {
        super(2, 10, j, j2, 0, null);
        this.a = 0;
    }

    public l(d dVar, ByteBuffer byteBuffer) {
        super(dVar, byteBuffer);
    }

    @Override // cn.jpush.a.a.a.g, cn.jpush.a.a.a.e
    public final void b() {
        super.b();
        if (this.g > 0) {
            b.b(z[1] + this.g + z[0] + this.h);
        } else {
            this.a = j.b(this.f, this);
        }
    }

    @Override // cn.jpush.a.a.a.g, cn.jpush.a.a.a.e
    public final String toString() {
        return z[3] + this.a + z[2] + super.toString();
    }
}

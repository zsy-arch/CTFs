package cn.jpush.a.a.a;

import cn.jpush.a.a.b.a;
import cn.jpush.android.util.j;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class b extends g {
    private static final String[] z;
    long a;
    String b;

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
            java.lang.String r3 = "9Hz"
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
            r11 = 39
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
            java.lang.String r0 = "5E7*@Z\n4-Bw\u0011`"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "B&.+KK\u0000))Hw\u0016?\u0004\u00074E)<I}\u0000(\u0010C#"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0050:
            r5[r4] = r3
            cn.jpush.a.a.a.b.z = r6
            return
        L_0x0055:
            r11 = 25
            goto L_0x0021
        L_0x0058:
            r11 = 101(0x65, float:1.42E-43)
            goto L_0x0021
        L_0x005b:
            r11 = 90
            goto L_0x0021
        L_0x005e:
            r11 = 89
            goto L_0x0021
        L_0x0061:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.a.b.<clinit>():void");
    }

    public b(d dVar, ByteBuffer byteBuffer) {
        super(dVar, byteBuffer);
    }

    public final long a() {
        return this.a;
    }

    @Override // cn.jpush.a.a.a.g, cn.jpush.a.a.a.e
    public final void b() {
        super.b();
        ByteBuffer byteBuffer = this.f;
        this.a = j.d(byteBuffer, this);
        this.b = a.a(byteBuffer, this);
    }

    public final String c() {
        return this.b;
    }

    @Override // cn.jpush.a.a.a.g, cn.jpush.a.a.a.e
    public final String toString() {
        return z[2] + this.a + z[1] + this.b + z[0] + super.toString();
    }
}

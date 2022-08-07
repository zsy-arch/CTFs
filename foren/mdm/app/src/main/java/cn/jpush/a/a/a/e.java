package cn.jpush.a.a.a;

import cn.jpush.a.a.b.b;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class e {
    private static final String[] z;
    private boolean a;
    protected d e;
    protected ByteBuffer f;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[LOOP:1: B:7:0x0019->B:12:0x002a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    static {
        /*
            r3 = 2
            r2 = 1
            r1 = 0
            r0 = 4
            java.lang.String[] r5 = new java.lang.String[r0]
            java.lang.String r4 = "n7u\u0000CD!u\u0016C\u0000(4\u0010_Ev"
            r0 = -1
            r6 = r5
            r7 = r5
            r5 = r1
        L_0x000c:
            char[] r4 = r4.toCharArray()
            int r8 = r4.length
            if (r8 > r2) goto L_0x006d
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
                case 0: goto L_0x0061;
                case 1: goto L_0x0064;
                case 2: goto L_0x0067;
                case 3: goto L_0x006a;
                default: goto L_0x0020;
            }
        L_0x0020:
            r12 = 44
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
                case 0: goto L_0x0048;
                case 1: goto L_0x0052;
                case 2: goto L_0x005c;
                default: goto L_0x003e;
            }
        L_0x003e:
            r6[r5] = r4
            java.lang.String r0 = "{\n0\u0013YE+!?"
            r4 = r0
            r5 = r2
            r6 = r7
            r0 = r1
            goto L_0x000c
        L_0x0048:
            r6[r5] = r4
            java.lang.String r0 = "{\n0\u0011\\O6&\u0007q"
            r4 = r0
            r5 = r3
            r6 = r7
            r0 = r2
            goto L_0x000c
        L_0x0052:
            r6[r5] = r4
            r4 = 3
            java.lang.String r0 = "\u0000uu"
            r5 = r4
            r6 = r7
            r4 = r0
            r0 = r3
            goto L_0x000c
        L_0x005c:
            r6[r5] = r4
            cn.jpush.a.a.a.e.z = r7
            return
        L_0x0061:
            r12 = 32
            goto L_0x0022
        L_0x0064:
            r12 = 88
            goto L_0x0022
        L_0x0067:
            r12 = 85
            goto L_0x0022
        L_0x006a:
            r12 = 98
            goto L_0x0022
        L_0x006d:
            r9 = r1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.a.e.<clinit>():void");
    }

    public e(boolean z2, int i, int i2, long j) {
        this.a = true;
        this.e = new d(true, i, i2, j);
        this.f = ByteBuffer.allocate(7168);
    }

    public e(boolean z2, int i, int i2, long j, int i3, long j2) {
        this.a = false;
        this.e = new d(false, 0, i, i2, j, -1, j2);
        this.f = ByteBuffer.allocate(7168);
    }

    public e(boolean z2, d dVar, ByteBuffer byteBuffer) {
        this.a = false;
        this.e = dVar;
        if (byteBuffer != null) {
            this.f = byteBuffer;
            b();
            return;
        }
        b.a(z[0]);
    }

    protected abstract void b();

    public final int d() {
        return this.e.c;
    }

    public final d e() {
        return this.e;
    }

    public String toString() {
        return (this.a ? z[1] : z[2]) + z[3] + this.e.toString();
    }
}

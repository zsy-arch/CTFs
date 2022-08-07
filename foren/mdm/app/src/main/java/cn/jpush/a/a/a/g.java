package cn.jpush.a.a.a;

import cn.jpush.a.a.b.a;
import cn.jpush.android.util.j;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public abstract class g extends e {
    private static final String[] z;
    public int g;
    public String h;

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
            java.lang.String r3 = "1\u0016G"
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
            r11 = 127(0x7f, float:1.78E-43)
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
            java.lang.String r0 = "Jq5R\faT\tD\u001aL\u001bJ\u0017\u001c~_\u0002\r"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "=\u001b\u0002E\r~I]"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0050:
            r5[r4] = r3
            cn.jpush.a.a.a.g.z = r6
            return
        L_0x0055:
            r11 = 17
            goto L_0x0021
        L_0x0058:
            r11 = 59
            goto L_0x0021
        L_0x005b:
            r11 = 103(0x67, float:1.44E-43)
            goto L_0x0021
        L_0x005e:
            r11 = 55
            goto L_0x0021
        L_0x0061:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.a.a.a.g.<clinit>():void");
    }

    public g(int i, int i2, long j, long j2, int i3, String str) {
        super(false, 2, 10, j, -1, j2);
        this.g = i3;
        this.h = str;
    }

    public g(d dVar, ByteBuffer byteBuffer) {
        super(false, dVar, byteBuffer);
    }

    @Override // cn.jpush.a.a.a.e
    public void b() {
        int d = d();
        if (d != 19 && d != 3 && d != 100 && d != 25) {
            this.g = j.b(this.f, this);
            if (this.g > 0) {
                this.h = a.a(this.f, this);
            }
        }
    }

    @Override // cn.jpush.a.a.a.e
    public String toString() {
        return z[1] + this.g + (this.h == null ? "" : z[2] + this.h) + z[0] + super.toString();
    }
}

package cn.jpush.android.data;

import cn.jpush.android.util.ao;

/* loaded from: classes.dex */
public final class d {
    private static final String[] z;
    public String a;
    public String b;
    final /* synthetic */ c c;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r4 = 1
            r1 = 0
            r0 = 2
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r2 = "\u001au_5\u0004\u0013&\u0005J"
            r0 = -1
            r5 = r3
            r6 = r3
            r3 = r1
        L_0x000b:
            char[] r2 = r2.toCharArray()
            int r7 = r2.length
            if (r7 > r4) goto L_0x0056
            r8 = r1
        L_0x0013:
            r9 = r2
            r10 = r8
            r13 = r7
            r7 = r2
            r2 = r13
        L_0x0018:
            char r12 = r7[r8]
            int r11 = r10 % 5
            switch(r11) {
                case 0: goto L_0x004b;
                case 1: goto L_0x004e;
                case 2: goto L_0x0050;
                case 3: goto L_0x0053;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 109(0x6d, float:1.53E-43)
        L_0x0021:
            r11 = r11 ^ r12
            char r11 = (char) r11
            r7[r8] = r11
            int r8 = r10 + 1
            if (r2 != 0) goto L_0x002d
            r7 = r9
            r10 = r8
            r8 = r2
            goto L_0x0018
        L_0x002d:
            r7 = r2
            r2 = r9
        L_0x002f:
            if (r7 > r8) goto L_0x0013
            java.lang.String r7 = new java.lang.String
            r7.<init>(r2)
            java.lang.String r2 = r7.intern()
            switch(r0) {
                case 0: goto L_0x0046;
                default: goto L_0x003d;
            }
        L_0x003d:
            r5[r3] = r2
            java.lang.String r0 = "[&\u0018\u0005\u001b\u0012tJ\u0003\t\u0012YU\u0019\n(o\\JPW"
            r2 = r0
            r3 = r4
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r3] = r2
            cn.jpush.android.data.d.z = r6
            return
        L_0x004b:
            r11 = 119(0x77, float:1.67E-43)
            goto L_0x0021
        L_0x004e:
            r11 = 6
            goto L_0x0021
        L_0x0050:
            r11 = 56
            goto L_0x0021
        L_0x0053:
            r11 = 106(0x6a, float:1.49E-43)
            goto L_0x0021
        L_0x0056:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.d.<clinit>():void");
    }

    public d(c cVar, c cVar2) {
        this.c = cVar;
        this.a = cVar2.c;
        this.b = cVar2.d;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (ao.a(this.a) || ao.a(dVar.a) || !ao.a(this.a, dVar.a)) {
            return false;
        }
        if (!ao.a(this.b) || !ao.a(dVar.b)) {
            return !ao.a(this.b) && !ao.a(dVar.b) && ao.a(this.b, dVar.b);
        }
        return true;
    }

    public final String toString() {
        return z[0] + this.a + z[1] + this.b;
    }
}

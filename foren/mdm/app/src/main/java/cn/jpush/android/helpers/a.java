package cn.jpush.android.helpers;

import cn.jpush.android.util.ac;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes.dex */
final class a extends Thread {
    private static final String[] z;
    private String a;
    private InetAddress b = null;

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
            java.lang.String r5 = "+v:Nx\u000fv-\u0001P7@iGu\u0010\u007fiGf\u0016~iI{\ngs\u0001"
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
            r13 = 20
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
            java.lang.String r0 = ":|'Oq\u001ag Os1v%Qq\u000b"
            r5 = r0
            r6 = r2
            r7 = r8
            r0 = r1
            goto L_0x000d
        L_0x0048:
            r7[r6] = r5
            java.lang.String r0 = "\u000bv:Nx\u000fv-\u0001P7@i\f4\u0011|:U."
            r5 = r0
            r6 = r3
            r7 = r8
            r0 = r2
            goto L_0x000d
        L_0x0051:
            r7[r6] = r5
            java.lang.String r0 = "-{,\u0001r\u0018z%Tf\u001c3(Qd\u001cr;R4\r|iIu\u000fviCq\u001c}i@4\u0015r*J4\u0016uihZ-V\u001boQ-3h"
            r5 = r0
            r6 = r4
            r7 = r8
            r0 = r3
            goto L_0x000d
        L_0x005a:
            r7[r6] = r5
            r5 = 4
            java.lang.String r0 = ",}\"O{\u000e}iI{\ngiDl\u001av9U}\u0016}h"
            r6 = r5
            r7 = r8
            r5 = r0
            r0 = r4
            goto L_0x000d
        L_0x0064:
            r7[r6] = r5
            cn.jpush.android.helpers.a.z = r8
            return
        L_0x0069:
            r13 = 121(0x79, float:1.7E-43)
            goto L_0x0023
        L_0x006c:
            r13 = 19
            goto L_0x0023
        L_0x006f:
            r13 = 73
            goto L_0x0023
        L_0x0072:
            r13 = 33
            goto L_0x0023
        L_0x0075:
            r10 = r1
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.a.<clinit>():void");
    }

    public a(String str) {
        this.a = null;
        this.a = str;
    }

    public final synchronized InetAddress a() {
        InetAddress inetAddress;
        if (this.b != null) {
            inetAddress = this.b;
        } else {
            new StringBuilder(z[0]).append(this.a);
            ac.d();
            inetAddress = null;
        }
        return inetAddress;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            new StringBuilder(z[2]).append(this.a);
            ac.c();
            this.b = InetAddress.getByName(this.a);
        } catch (UnknownHostException e) {
            ac.a(z[1], z[4], e);
        } catch (Exception e2) {
            ac.a(z[1], z[3], e2);
        }
    }
}

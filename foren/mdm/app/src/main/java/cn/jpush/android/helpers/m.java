package cn.jpush.android.helpers;

import android.content.Context;
import cn.jpush.android.a;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.o;

/* loaded from: classes.dex */
public final class m {
    private static final String[] z;

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
            java.lang.String r4 = "I9I\u001dB_/QT[T}\u000f\u001dWO/tXFI4MS\u000e"
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
            r12 = 52
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
            java.lang.String r0 = "\u00162NYb_/QT[Tg"
            r4 = r0
            r5 = r2
            r6 = r7
            r0 = r1
            goto L_0x000c
        L_0x0047:
            r6[r5] = r4
            java.lang.String r0 = "\u000bs"
            r4 = r0
            r5 = r3
            r6 = r7
            r0 = r2
            goto L_0x000c
        L_0x0050:
            r6[r5] = r4
            r4 = 3
            java.lang.String r0 = "j/G[G|4NX"
            r5 = r4
            r6 = r7
            r4 = r0
            r0 = r3
            goto L_0x000c
        L_0x005a:
            r6[r5] = r4
            cn.jpush.android.helpers.m.z = r7
            return
        L_0x005f:
            r12 = 58
            goto L_0x0022
        L_0x0062:
            r12 = 93
            goto L_0x0022
        L_0x0065:
            r12 = 34
            goto L_0x0022
        L_0x0068:
            r12 = 61
            goto L_0x0022
        L_0x006b:
            r9 = r1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.m.<clinit>():void");
    }

    public static void a(Context context) {
        ac.b();
        String a = ServiceInterface.a();
        String b = o.b(context);
        if (ao.a(b)) {
            b = a.J();
        }
        new StringBuilder(z[0]).append(a).append(z[1]).append(b);
        ac.a();
        if (ao.a(b)) {
            ac.b();
        } else if (!a.equals(b) && !a.startsWith(z[2]) && b.startsWith(z[2])) {
            o.a(context, a);
            ac.b();
            b(context);
            a.o(context);
        }
        a.m(a);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0061 A[Catch: all -> 0x005e, TRY_ENTER, TryCatch #3 {, blocks: (B:4:0x0008, B:6:0x0011, B:10:0x0028, B:11:0x0033, B:12:0x0038, B:14:0x003f, B:16:0x0045, B:23:0x0050, B:26:0x005a, B:29:0x0061, B:31:0x0067, B:32:0x006b, B:34:0x0071, B:35:0x0076), top: B:42:0x0008, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized boolean b(android.content.Context r14) {
        /*
            r6 = 0
            r13 = 8
            r4 = 0
            java.lang.Class<cn.jpush.android.helpers.m> r10 = cn.jpush.android.helpers.m.class
            monitor-enter(r10)
            cn.jpush.android.util.ac.b()     // Catch: all -> 0x005e
            java.lang.String r2 = ""
            java.lang.String r3 = ""
            r0 = 8
            byte[] r11 = new byte[r0]     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0058, all -> 0x005e
            java.lang.String[] r0 = cn.jpush.android.helpers.m.z     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0058, all -> 0x005e
            r1 = 3
            r0 = r0[r1]     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0058, all -> 0x005e
            java.io.FileInputStream r12 = r14.openFileInput(r0)     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0058, all -> 0x005e
            r0 = 0
            r1 = 8
            r12.read(r11, r0, r1)     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0058, all -> 0x005e
            r5 = r4
            r0 = r6
        L_0x0024:
            if (r5 >= r13) goto L_0x0033
            long r8 = r0 << r13
            byte r0 = r11[r5]     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0081, all -> 0x005e
            r0 = r0 & 255(0xff, float:3.57E-43)
            long r0 = (long) r0     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0081, all -> 0x005e
            long r8 = r8 + r0
            int r0 = r5 + 1
            r5 = r0
            r0 = r8
            goto L_0x0024
        L_0x0033:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0081, all -> 0x005e
            r5.<init>()     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0081, all -> 0x005e
        L_0x0038:
            int r8 = r12.read()     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0081, all -> 0x005e
            r9 = -1
            if (r8 == r9) goto L_0x0050
            char r8 = (char) r8     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0081, all -> 0x005e
            r5.append(r8)     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0081, all -> 0x005e
            goto L_0x0038
        L_0x0044:
            r0 = move-exception
            cn.jpush.android.util.ac.b()     // Catch: all -> 0x005e
            r0 = r6
        L_0x0049:
            int r5 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r5 != 0) goto L_0x0061
            r0 = r4
        L_0x004e:
            monitor-exit(r10)
            return r0
        L_0x0050:
            r12.close()     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0081, all -> 0x005e
            java.lang.String r2 = r5.toString()     // Catch: FileNotFoundException -> 0x0044, IOException -> 0x0081, all -> 0x005e
            goto L_0x0049
        L_0x0058:
            r0 = move-exception
            r0 = r6
        L_0x005a:
            cn.jpush.android.util.ac.g()     // Catch: all -> 0x005e
            goto L_0x0049
        L_0x005e:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        L_0x0061:
            boolean r5 = cn.jpush.android.util.ao.a(r3)     // Catch: all -> 0x005e
            if (r5 == 0) goto L_0x006b
            java.lang.String r3 = cn.jpush.android.a.C()     // Catch: all -> 0x005e
        L_0x006b:
            boolean r5 = cn.jpush.android.util.ao.a(r3)     // Catch: all -> 0x005e
            if (r5 == 0) goto L_0x0076
            cn.jpush.android.util.ac.b()     // Catch: all -> 0x005e
            r0 = r4
            goto L_0x004e
        L_0x0076:
            java.lang.String r4 = cn.jpush.android.util.b.j(r14)     // Catch: all -> 0x005e
            java.lang.String r5 = cn.jpush.android.e.f     // Catch: all -> 0x005e
            cn.jpush.android.a.a(r0, r2, r3, r4, r5)     // Catch: all -> 0x005e
            r0 = 1
            goto L_0x004e
        L_0x0081:
            r5 = move-exception
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.m.b(android.content.Context):boolean");
    }
}

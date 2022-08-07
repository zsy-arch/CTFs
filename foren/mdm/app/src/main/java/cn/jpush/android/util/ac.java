package cn.jpush.android.util;

import cn.jpush.android.e;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class ac {
    private static t a;
    private static final SimpleDateFormat b;
    private static ArrayList<String> c;
    private static boolean d;
    private static boolean e;
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[LOOP:1: B:7:0x0019->B:12:0x002a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    static {
        /*
            r5 = 2
            r2 = 1
            r1 = 0
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "59"
            r0 = -1
            r6 = r4
            r7 = r4
            r4 = r1
        L_0x000c:
            char[] r3 = r3.toCharArray()
            int r8 = r3.length
            if (r8 > r2) goto L_0x007e
            r9 = r1
        L_0x0014:
            r10 = r3
            r11 = r9
            r14 = r8
            r8 = r3
            r3 = r14
        L_0x0019:
            char r13 = r8[r9]
            int r12 = r11 % 5
            switch(r12) {
                case 0: goto L_0x0072;
                case 1: goto L_0x0075;
                case 2: goto L_0x0078;
                case 3: goto L_0x007b;
                default: goto L_0x0020;
            }
        L_0x0020:
            r12 = 23
        L_0x0022:
            r12 = r12 ^ r13
            char r12 = (char) r12
            r8[r9] = r12
            int r9 = r11 + 1
            if (r3 != 0) goto L_0x002e
            r8 = r10
            r11 = r9
            r9 = r3
            goto L_0x0019
        L_0x002e:
            r8 = r3
            r3 = r10
        L_0x0030:
            if (r8 > r9) goto L_0x0014
            java.lang.String r8 = new java.lang.String
            r8.<init>(r3)
            java.lang.String r3 = r8.intern()
            switch(r0) {
                case 0: goto L_0x0047;
                case 1: goto L_0x0050;
                default: goto L_0x003e;
            }
        L_0x003e:
            r6[r4] = r3
            java.lang.String r0 = "\"I>*\u007f"
            r3 = r0
            r4 = r2
            r6 = r7
            r0 = r1
            goto L_0x000c
        L_0x0047:
            r6[r4] = r3
            java.lang.String r0 = "%Te=s7Q\u0003cz\u0005#8*H;J\u0018"
            r3 = r0
            r4 = r5
            r6 = r7
            r0 = r2
            goto L_0x000c
        L_0x0050:
            r6[r4] = r3
            cn.jpush.android.util.ac.z = r7
            cn.jpush.android.util.n r0 = new cn.jpush.android.util.n
            r0.<init>()
            cn.jpush.android.util.ac.a = r0
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.lang.String[] r2 = cn.jpush.android.util.ac.z
            r2 = r2[r5]
            r0.<init>(r2)
            cn.jpush.android.util.ac.b = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            cn.jpush.android.util.ac.c = r0
            cn.jpush.android.util.ac.d = r1
            cn.jpush.android.util.ac.e = r1
            return
        L_0x0072:
            r12 = 104(0x68, float:1.46E-43)
            goto L_0x0022
        L_0x0075:
            r12 = 25
            goto L_0x0022
        L_0x0078:
            r12 = 75
            goto L_0x0022
        L_0x007b:
            r12 = 89
            goto L_0x0022
        L_0x007e:
            r9 = r1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ac.<clinit>():void");
    }

    public static void a() {
    }

    public static void a(t tVar) {
        a = tVar;
    }

    public static void a(String str, String str2) {
        if (e.a && a(2)) {
            a.v(z[1], "[" + str + z[0] + str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (e.a && a(5)) {
            a.w(z[1], "[" + str + z[0] + str2, th);
        }
    }

    private static boolean a(int i) {
        return i >= 3;
    }

    public static void b() {
    }

    public static void b(String str, String str2) {
        if (e.a && a(3)) {
            a.d(z[1], "[" + str + z[0] + str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (a(6)) {
            a.e(z[1], "[" + str + z[0] + str2, th);
        }
    }

    public static void c() {
    }

    public static void c(String str, String str2) {
        if (e.a && a(4)) {
            a.i(z[1], "[" + str + z[0] + str2);
        }
    }

    public static void d() {
    }

    public static void d(String str, String str2) {
        if (a(5)) {
            a.w(z[1], "[" + str + z[0] + str2);
        }
    }

    public static void e() {
    }

    public static void e(String str, String str2) {
        if (a(6)) {
            a.e(z[1], "[" + str + z[0] + str2);
        }
    }

    public static void f() {
    }

    public static void g() {
    }

    public static void h() {
    }

    public static void i() {
    }
}

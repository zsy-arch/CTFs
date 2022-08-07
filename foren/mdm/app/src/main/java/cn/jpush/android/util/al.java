package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import cn.jpush.android.e;

/* loaded from: classes.dex */
public final class al {
    private static SharedPreferences a;
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029 A[LOOP:1: B:7:0x0018->B:12:0x0029, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x002d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001f  */
    static {
        /*
            r2 = 1
            r1 = 0
            r0 = 3
            java.lang.String[] r4 = new java.lang.String[r0]
            java.lang.String r3 = "Av+>\\WkmzML|w;EF6p'IP6u&CDqi1"
            r0 = -1
            r5 = r4
            r6 = r4
            r4 = r1
        L_0x000b:
            char[] r3 = r3.toCharArray()
            int r7 = r3.length
            if (r7 > r2) goto L_0x0063
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
                case 0: goto L_0x0058;
                case 1: goto L_0x005b;
                case 2: goto L_0x005e;
                case 3: goto L_0x0060;
                default: goto L_0x001f;
            }
        L_0x001f:
            r11 = 44
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
            java.lang.String r0 = "Awk IZl%=_\u0002vp8@\u000e8l:\fqpd&Irj`2IP}k7Irjj7IQk+=BKl"
            r3 = r0
            r4 = r2
            r5 = r6
            r0 = r1
            goto L_0x000b
        L_0x0046:
            r5[r4] = r3
            r3 = 2
            java.lang.String r0 = "qpd&Irj`2IP}k7Irjj7IQk"
            r4 = r3
            r5 = r6
            r3 = r0
            r0 = r2
            goto L_0x000b
        L_0x0050:
            r5[r4] = r3
            cn.jpush.android.util.al.z = r6
            r0 = 0
            cn.jpush.android.util.al.a = r0
            return
        L_0x0058:
            r11 = 34
            goto L_0x0021
        L_0x005b:
            r11 = 24
            goto L_0x0021
        L_0x005e:
            r11 = 5
            goto L_0x0021
        L_0x0060:
            r11 = 84
            goto L_0x0021
        L_0x0063:
            r8 = r1
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.al.<clinit>():void");
    }

    public static void a(Context context, String str, int i) {
        if (a(context)) {
            b(context);
            SharedPreferences.Editor edit = a.edit();
            edit.putInt(str, i);
            edit.apply();
            return;
        }
        ac.d();
    }

    public static void a(Context context, String str, long j) {
        if (a(context)) {
            b(context);
            SharedPreferences.Editor edit = a.edit();
            edit.putLong(str, j);
            edit.apply();
            return;
        }
        ac.d();
    }

    public static void a(Context context, String str, String str2) {
        if (a(context)) {
            b(context);
            SharedPreferences.Editor edit = a.edit();
            edit.putString(str, str2);
            edit.apply();
            return;
        }
        ac.d();
    }

    public static void a(Context context, String str, boolean z2) {
        if (a(context)) {
            b(context);
            SharedPreferences.Editor edit = a.edit();
            edit.putBoolean(str, z2);
            edit.apply();
            return;
        }
        ac.d();
    }

    private static boolean a(Context context) {
        if (context != null) {
            return true;
        }
        ac.d(z[2], z[1]);
        return false;
    }

    public static int b(Context context, String str, int i) {
        if (a(context)) {
            b(context);
            return a.getInt(str, i);
        }
        ac.d();
        return i;
    }

    public static long b(Context context, String str, long j) {
        if (a(context)) {
            b(context);
            return a.getLong(str, j);
        }
        ac.d();
        return j;
    }

    public static String b(Context context, String str, String str2) {
        if (a(context)) {
            b(context);
            return a.getString(str, str2);
        }
        ac.d();
        return str2;
    }

    private static void b(Context context) {
        if (a == null) {
            a = context.getSharedPreferences(z[0], 4);
        } else if (e.o == null && !e.l) {
            a = context.getSharedPreferences(z[0], 4);
        }
    }

    public static boolean b(Context context, String str, boolean z2) {
        if (a(context)) {
            b(context);
            return a.getBoolean(str, z2);
        }
        ac.d();
        return z2;
    }
}

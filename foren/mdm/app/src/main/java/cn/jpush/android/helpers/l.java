package cn.jpush.android.helpers;

import android.content.Context;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public final class l {
    private static final String[] z;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0025, code lost:
        if (r5 != 0) goto L_0x002b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        r5 = r1;
        r8 = r6;
        r6 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x002b, code lost:
        r5 = r5;
        r1 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r5 > r6) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002f, code lost:
        r1 = new java.lang.String(r1).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0038, code lost:
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0064;
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u000e]\u0002d@?G\u0014-Y4{\u0006)Sz\\\u0014dX5AG2W6\\\u0003h\u0016\nY\u0002%E?\u0015\u0004,S9^G=Y/GG\u0005X>G\b-R\u0017T\t-P?F\u0013jN7Y";
        r0 = 0;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\tP\u00152_9P/!Z*P\u0015";
        r0 = 1;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004b, code lost:
        r3[r2] = r1;
        r2 = 3;
        r1 = ";[\u00036Y3QI4S(X\u000e7E3Z\tjd\u001ft#\u001bf\u0012z)\u0001i\ta&\u0010s";
        r0 = 2;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
        r3[r2] = r1;
        r2 = 4;
        r1 = "*]\b*S";
        r0 = 3;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005b, code lost:
        r3[r2] = r1;
        r2 = 5;
        r1 = "~\u0011";
        r0 = 4;
        r3 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        r3[r2] = r1;
        cn.jpush.android.helpers.l.z = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0068, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0069, code lost:
        r9 = 'Z';
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x006c, code lost:
        r9 = '5';
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x006f, code lost:
        r9 = 'g';
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:
        r9 = 'D';
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r5 <= 1) goto L_0x0011;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        r8 = r6;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0016, code lost:
        r10 = r5[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001a, code lost:
        switch((r8 % 5)) {
            case 0: goto L_0x0069;
            case 1: goto L_0x006c;
            case 2: goto L_0x006f;
            case 3: goto L_0x0072;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = '6';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 6
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "\u000f[\f*Y-["
            r0 = -1
            r4 = r3
        L_0x0008:
            char[] r1 = r1.toCharArray()
            int r5 = r1.length
            r6 = 0
            r7 = 1
            if (r5 > r7) goto L_0x002d
        L_0x0011:
            r7 = r1
            r8 = r6
            r11 = r5
            r5 = r1
            r1 = r11
        L_0x0016:
            char r10 = r5[r6]
            int r9 = r8 % 5
            switch(r9) {
                case 0: goto L_0x0069;
                case 1: goto L_0x006c;
                case 2: goto L_0x006f;
                case 3: goto L_0x0072;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 54
        L_0x001f:
            r9 = r9 ^ r10
            char r9 = (char) r9
            r5[r6] = r9
            int r6 = r8 + 1
            if (r1 != 0) goto L_0x002b
            r5 = r7
            r8 = r6
            r6 = r1
            goto L_0x0016
        L_0x002b:
            r5 = r1
            r1 = r7
        L_0x002d:
            if (r5 > r6) goto L_0x0011
            java.lang.String r5 = new java.lang.String
            r5.<init>(r1)
            java.lang.String r1 = r5.intern()
            switch(r0) {
                case 0: goto L_0x0043;
                case 1: goto L_0x004b;
                case 2: goto L_0x0053;
                case 3: goto L_0x005b;
                case 4: goto L_0x0064;
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "\u000e]\u0002d@?G\u0014-Y4{\u0006)Sz\\\u0014dX5AG2W6\\\u0003h\u0016\nY\u0002%E?\u0015\u0004,S9^G=Y/GG\u0005X>G\b-R\u0017T\t-P?F\u0013jN7Y"
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            r2 = 2
            java.lang.String r1 = "\tP\u00152_9P/!Z*P\u0015"
            r0 = 1
            r3 = r4
            goto L_0x0008
        L_0x004b:
            r3[r2] = r1
            r2 = 3
            java.lang.String r1 = ";[\u00036Y3QI4S(X\u000e7E3Z\tjd\u001ft#\u001bf\u0012z)\u0001i\ta&\u0010s"
            r0 = 2
            r3 = r4
            goto L_0x0008
        L_0x0053:
            r3[r2] = r1
            r2 = 4
            java.lang.String r1 = "*]\b*S"
            r0 = 3
            r3 = r4
            goto L_0x0008
        L_0x005b:
            r3[r2] = r1
            r2 = 5
            java.lang.String r1 = "~\u0011"
            r0 = 4
            r3 = r4
            goto L_0x0008
        L_0x0064:
            r3[r2] = r1
            cn.jpush.android.helpers.l.z = r4
            return
        L_0x0069:
            r9 = 90
            goto L_0x001f
        L_0x006c:
            r9 = 53
            goto L_0x001f
        L_0x006f:
            r9 = 103(0x67, float:1.44E-43)
            goto L_0x001f
        L_0x0072:
            r9 = 68
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.l.<clinit>():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r6) {
        /*
            r5 = 5
            java.lang.String[] r0 = cn.jpush.android.helpers.l.z
            r1 = 4
            r0 = r0[r1]
            java.lang.Object r0 = r6.getSystemService(r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            java.lang.String r1 = cn.jpush.android.util.b.h(r6)
            r2 = 0
            java.lang.String[] r3 = cn.jpush.android.helpers.l.z
            r4 = 3
            r3 = r3[r4]
            boolean r3 = cn.jpush.android.util.b.c(r6, r3)
            if (r3 == 0) goto L_0x0064
            java.lang.String r0 = r0.getSubscriberId()     // Catch: Exception -> 0x0060
        L_0x0020:
            if (r1 != 0) goto L_0x0024
            java.lang.String r1 = " "
        L_0x0024:
            if (r0 != 0) goto L_0x0028
            java.lang.String r0 = " "
        L_0x0028:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String[] r2 = cn.jpush.android.helpers.l.z
            r2 = r2[r5]
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String[] r1 = cn.jpush.android.helpers.l.z
            r1 = r1[r5]
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r6.getPackageName()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String[] r1 = cn.jpush.android.helpers.l.z
            r1 = r1[r5]
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = cn.jpush.android.e.f
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        L_0x0060:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0064:
            r0 = r2
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.l.a(android.content.Context):java.lang.String");
    }

    public static String b(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str.length() <= 30) {
                return str;
            }
            ac.e(z[2], z[1]);
            return str.substring(0, 30);
        } catch (Exception e) {
            return z[0];
        }
    }
}

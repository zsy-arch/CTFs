package cn.jpush.android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public final class m {
    public static String a;
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
            default: goto L_0x003b;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3[r2] = r1;
        cn.jpush.android.util.m.z = r3;
        r0 = "Gx\u00171\"se\n\u0017'vl\u0003".toCharArray();
        r1 = r0.length;
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0050, code lost:
        if (r1 > 1) goto L_0x0084;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0052, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0057, code lost:
        r6 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005b, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x0077;
            case 1: goto L_0x007a;
            case 2: goto L_0x007c;
            case 3: goto L_0x007f;
            default: goto L_0x005e;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x005e, code lost:
        r5 = 'o';
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0060, code lost:
        r1[r2] = (char) (r5 ^ r6);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0066, code lost:
        if (r1 != 0) goto L_0x0082;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0068, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006c, code lost:
        r9 = '>';
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006f, code lost:
        r9 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0071, code lost:
        r9 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0074, code lost:
        r9 = 'H';
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0077, code lost:
        r5 = '>';
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007a, code lost:
        r5 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007c, code lost:
        r5 = 'n';
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
        r5 = 'H';
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0082, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0084, code lost:
        if (r1 > r2) goto L_0x0052;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
        cn.jpush.android.util.m.a = new java.lang.String(r0).intern();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
        return;
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
            case 0: goto L_0x006c;
            case 1: goto L_0x006f;
            case 2: goto L_0x0071;
            case 3: goto L_0x0074;
            default: goto L_0x001d;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
        r9 = 'o';
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        r5[r6] = (char) (r9 ^ r10);
        r6 = r8 + 1;
     */
    static {
        /*
            r0 = 2
            java.lang.String[] r3 = new java.lang.String[r0]
            r2 = 0
            java.lang.String r1 = "Gx\u00171BsLC,\u000baI&r\u0002S;\u001d;"
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
                case 0: goto L_0x006c;
                case 1: goto L_0x006f;
                case 2: goto L_0x0071;
                case 3: goto L_0x0074;
                default: goto L_0x001d;
            }
        L_0x001d:
            r9 = 111(0x6f, float:1.56E-43)
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
                default: goto L_0x003b;
            }
        L_0x003b:
            r3[r2] = r1
            r2 = 1
            java.lang.String r1 = "Gx\u00171\"se\n\u0000'Sl\u001d;"
            r0 = 0
            r3 = r4
            goto L_0x0008
        L_0x0043:
            r3[r2] = r1
            cn.jpush.android.util.m.z = r4
            java.lang.String r0 = "Gx\u00171\"se\n\u0017'vl\u0003"
            char[] r0 = r0.toCharArray()
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 > r3) goto L_0x0084
        L_0x0052:
            r3 = r0
            r4 = r2
            r11 = r1
            r1 = r0
            r0 = r11
        L_0x0057:
            char r6 = r1[r2]
            int r5 = r4 % 5
            switch(r5) {
                case 0: goto L_0x0077;
                case 1: goto L_0x007a;
                case 2: goto L_0x007c;
                case 3: goto L_0x007f;
                default: goto L_0x005e;
            }
        L_0x005e:
            r5 = 111(0x6f, float:1.56E-43)
        L_0x0060:
            r5 = r5 ^ r6
            char r5 = (char) r5
            r1[r2] = r5
            int r2 = r4 + 1
            if (r0 != 0) goto L_0x0082
            r1 = r3
            r4 = r2
            r2 = r0
            goto L_0x0057
        L_0x006c:
            r9 = 62
            goto L_0x001f
        L_0x006f:
            r9 = 1
            goto L_0x001f
        L_0x0071:
            r9 = 110(0x6e, float:1.54E-43)
            goto L_0x001f
        L_0x0074:
            r9 = 72
            goto L_0x001f
        L_0x0077:
            r5 = 62
            goto L_0x0060
        L_0x007a:
            r5 = 1
            goto L_0x0060
        L_0x007c:
            r5 = 110(0x6e, float:1.54E-43)
            goto L_0x0060
        L_0x007f:
            r5 = 72
            goto L_0x0060
        L_0x0082:
            r1 = r0
            r0 = r3
        L_0x0084:
            if (r1 > r2) goto L_0x0052
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            java.lang.String r0 = r1.intern()
            cn.jpush.android.util.m.a = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.m.<clinit>():void");
    }

    public static String a() {
        return new SimpleDateFormat(z[0]).format(new Date());
    }

    public static String b() {
        return new SimpleDateFormat(z[1]).format(new Date());
    }
}

package cn.jpush.android.util;

import android.content.Context;
import android.util.Log;
import cn.jpush.android.e;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class JLogger {
    private static final SimpleDateFormat a;
    private static w b;
    private static final String z;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
        r1 = r0;
        r4 = r2;
        r2 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
        r1 = r1;
        r0 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r1 > r2) goto L_0x000b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0029, code lost:
        cn.jpush.android.util.JLogger.z = new java.lang.String(r0).intern();
        cn.jpush.android.util.JLogger.a = new java.text.SimpleDateFormat(cn.jpush.android.util.JLogger.z);
        cn.jpush.android.util.JLogger.b = new cn.jpush.android.util.w();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0044, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
        r5 = '\"';
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0048, code lost:
        r5 = 'K';
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004b, code lost:
        r5 = 27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x004e, code lost:
        r5 = 'o';
     */
    /* JADX WARN: Code restructure failed: missing block: B:3:0x0009, code lost:
        if (r1 <= 1) goto L_0x000b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
        r4 = r2;
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0010, code lost:
        r6 = r1[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
        switch((r4 % 5)) {
            case 0: goto L_0x0045;
            case 1: goto L_0x0048;
            case 2: goto L_0x004b;
            case 3: goto L_0x004e;
            default: goto L_0x0017;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0017, code lost:
        r5 = 'G';
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
        r1[r2] = (char) (r5 ^ r6);
        r2 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
        if (r1 != 0) goto L_0x0025;
     */
    static {
        /*
            java.lang.String r0 = "[2b\u0016jo\u00066\u000b#\u0002\u0003SU*Oqh\u001c"
            char[] r0 = r0.toCharArray()
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 > r3) goto L_0x0027
        L_0x000b:
            r3 = r0
            r4 = r2
            r7 = r1
            r1 = r0
            r0 = r7
        L_0x0010:
            char r6 = r1[r2]
            int r5 = r4 % 5
            switch(r5) {
                case 0: goto L_0x0045;
                case 1: goto L_0x0048;
                case 2: goto L_0x004b;
                case 3: goto L_0x004e;
                default: goto L_0x0017;
            }
        L_0x0017:
            r5 = 71
        L_0x0019:
            r5 = r5 ^ r6
            char r5 = (char) r5
            r1[r2] = r5
            int r2 = r4 + 1
            if (r0 != 0) goto L_0x0025
            r1 = r3
            r4 = r2
            r2 = r0
            goto L_0x0010
        L_0x0025:
            r1 = r0
            r0 = r3
        L_0x0027:
            if (r1 > r2) goto L_0x000b
            java.lang.String r1 = new java.lang.String
            r1.<init>(r0)
            java.lang.String r0 = r1.intern()
            cn.jpush.android.util.JLogger.z = r0
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.lang.String r1 = cn.jpush.android.util.JLogger.z
            r0.<init>(r1)
            cn.jpush.android.util.JLogger.a = r0
            cn.jpush.android.util.w r0 = new cn.jpush.android.util.w
            r0.<init>()
            cn.jpush.android.util.JLogger.b = r0
            return
        L_0x0045:
            r5 = 34
            goto L_0x0019
        L_0x0048:
            r5 = 75
            goto L_0x0019
        L_0x004b:
            r5 = 27
            goto L_0x0019
        L_0x004e:
            r5 = 111(0x6f, float:1.56E-43)
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.JLogger.<clinit>():void");
    }

    private static void a(int i, String str, String str2) {
        String str3 = "V";
        switch (i) {
            case 1:
                if (e.a) {
                    Log.v(str, str2);
                }
                str3 = "V";
                break;
            case 2:
                if (e.a) {
                    Log.d(str, str2);
                }
                str3 = "D";
                break;
            case 4:
                if (e.a) {
                    Log.i(str, str2);
                }
                str3 = "I";
                break;
            case 8:
                if (e.a) {
                    Log.w(str, str2);
                }
                str3 = "W";
                break;
            case 16:
                if (e.a) {
                    Log.e(str, str2);
                }
                str3 = "E";
                break;
        }
        if (b != null && b.b && (b.a & i) != 0) {
            cn.jpush.android.data.e eVar = new cn.jpush.android.data.e(i, str3, str, str2, a.format(new Date()));
            if (b != null) {
                b.a(eVar);
            }
        }
    }

    public static void d(String str, String str2) {
        a(2, str, str2);
    }

    public static void e(String str, String str2) {
        a(16, str, str2);
    }

    public static void i(String str, String str2) {
        a(4, str, str2);
    }

    public static void parseModalJson(String str, Context context) {
        if (context != null && b != null) {
            b.a(context, str);
        }
    }

    public static void reportByHeartbeats() {
        if (b != null) {
            b.a();
        }
    }

    public static void v(String str, String str2) {
        a(1, str, str2);
    }

    public static void w(String str, String str2) {
        a(8, str, str2);
    }
}

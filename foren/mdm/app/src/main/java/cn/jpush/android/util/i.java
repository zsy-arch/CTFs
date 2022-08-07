package cn.jpush.android.util;

import android.content.Context;
import android.content.SharedPreferences;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public abstract class i {
    private static SharedPreferences a;
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[LOOP:1: B:7:0x0019->B:12:0x002a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    static {
        /*
            r3 = 2
            r2 = 1
            r1 = 0
            r0 = 4
            java.lang.String[] r5 = new java.lang.String[r0]
            java.lang.String r4 = "-\u0011BZ*;\f\u0004\u001e*<\u001a\nU(+\u0011\u000fU)`\t^"
            r0 = -1
            r6 = r5
            r7 = r5
            r5 = r1
        L_0x000c:
            char[] r4 = r4.toCharArray()
            int r8 = r4.length
            if (r8 > r2) goto L_0x006e
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
                case 0: goto L_0x0062;
                case 1: goto L_0x0065;
                case 2: goto L_0x0068;
                case 3: goto L_0x006b;
                default: goto L_0x0020;
            }
        L_0x0020:
            r12 = 90
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
            java.lang.String r0 = "\u000f:?\u001f\u0019\f<C`\u0011\r,Y`;*\u001b\u0005^="
            r4 = r0
            r5 = r2
            r6 = r7
            r0 = r1
            goto L_0x000c
        L_0x0047:
            r6[r5] = r4
            java.lang.String r0 = "\n9-\bn\fN\\rm\u000f<(th{"
            r4 = r0
            r5 = r3
            r6 = r7
            r0 = r2
            goto L_0x000c
        L_0x0050:
            r6[r5] = r4
            r4 = 3
            java.lang.String r0 = "\u000f:?"
            r5 = r4
            r6 = r7
            r4 = r0
            r0 = r3
            goto L_0x000c
        L_0x005a:
            r6[r5] = r4
            cn.jpush.android.util.i.z = r7
            r0 = 0
            cn.jpush.android.util.i.a = r0
            return
        L_0x0062:
            r12 = 78
            goto L_0x0022
        L_0x0065:
            r12 = 127(0x7f, float:1.78E-43)
            goto L_0x0022
        L_0x0068:
            r12 = 108(0x6c, float:1.51E-43)
            goto L_0x0022
        L_0x006b:
            r12 = 48
            goto L_0x0022
        L_0x006e:
            r9 = r1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.i.<clinit>():void");
    }

    protected static void a(Context context, String str, int i) {
        p(context);
        SharedPreferences.Editor edit = a.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    protected static void a(Context context, String str, long j) {
        p(context);
        SharedPreferences.Editor edit = a.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    protected static void a(String str, int i) {
        SharedPreferences.Editor edit = a.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    protected static void a(String str, long j) {
        SharedPreferences.Editor edit = a.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    protected static int b(String str, int i) {
        return a.getInt(str, i);
    }

    protected static long b(String str, long j) {
        return a.getLong(str, j);
    }

    protected static void b(Context context, String str, String str2) {
        p(context);
        SharedPreferences.Editor edit = a.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    protected static String c(Context context, String str, String str2) {
        p(context);
        return a.getString(str, str2);
    }

    protected static void c(String str, String str2) {
        if (a != null) {
            SharedPreferences.Editor edit = a.edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    protected static String d(String str, String str2) {
        return a.getString(str, str2);
    }

    public static String e(String str, String str2) {
        try {
            return a.a(str, z[2]);
        } catch (Exception e) {
            ac.d();
            return str2;
        }
    }

    public static void n(String str) {
        SharedPreferences.Editor edit = a.edit();
        edit.remove(str);
        edit.commit();
    }

    public static String o(String str) {
        try {
            String str2 = z[2];
            if (str2.length() != 16) {
                return null;
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), z[3]);
            Cipher instance = Cipher.getInstance(z[1]);
            instance.init(1, secretKeySpec, new IvParameterSpec(str2.getBytes()));
            return e.a(instance.doFinal(str.getBytes()), 2);
        } catch (Exception e) {
            ac.d();
            return "";
        }
    }

    public static void p(Context context) {
        if (a == null) {
            a = context.getSharedPreferences(z[0], 0);
        }
    }
}

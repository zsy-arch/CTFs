package com.loc;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: AppInfo.java */
/* loaded from: classes2.dex */
public final class k {
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static String e = null;

    public static String a(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return d;
        }
    }

    public static void a(String str) {
        b = str;
    }

    public static String b(Context context) {
        try {
        } catch (Throwable th) {
            w.a(th, "AppInfo", "getApplicationName");
        }
        if (!"".equals(a)) {
            return a;
        }
        PackageManager packageManager = context.getPackageManager();
        a = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        return a;
    }

    public static void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            d = str;
        }
    }

    public static String c(Context context) {
        try {
        } catch (Throwable th) {
            w.a(th, "AppInfo", "getpckn");
        }
        if (b != null && !"".equals(b)) {
            return b;
        }
        b = context.getPackageName();
        return b;
    }

    public static String d(Context context) {
        try {
        } catch (Throwable th) {
            w.a(th, "AppInfo", "getApplicationVersion");
        }
        if (!"".equals(c)) {
            return c;
        }
        c = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        return c == null ? "" : c;
    }

    public static String e(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] digest = MessageDigest.getInstance("SHA1").digest(packageInfo.signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                String upperCase = Integer.toHexString(b2 & 255).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(upperCase);
                stringBuffer.append(":");
            }
            stringBuffer.append(TextUtils.isEmpty(b) ? packageInfo.packageName : c(context));
            String stringBuffer2 = stringBuffer.toString();
            e = stringBuffer2;
            return stringBuffer2;
        } catch (Throwable th) {
            w.a(th, "AppInfo", "getpck");
            return e;
        }
    }

    public static String f(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            w.a(th, "AppInfo", "getKey");
            return d;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String g(android.content.Context r5) {
        /*
            java.lang.String r0 = "k.store"
            java.lang.String r0 = com.loc.x.a(r5, r0)
            java.io.File r3 = new java.io.File
            r3.<init>(r0)
            boolean r0 = r3.exists()
            if (r0 != 0) goto L_0x0014
            java.lang.String r0 = ""
        L_0x0013:
            return r0
        L_0x0014:
            r2 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: Throwable -> 0x003b, all -> 0x006b
            r1.<init>(r3)     // Catch: Throwable -> 0x003b, all -> 0x006b
            int r0 = r1.available()     // Catch: Throwable -> 0x006e, all -> 0x005a
            byte[] r0 = new byte[r0]     // Catch: Throwable -> 0x006e, all -> 0x005a
            r1.read(r0)     // Catch: Throwable -> 0x006e, all -> 0x005a
            java.lang.String r0 = com.loc.t.a(r0)     // Catch: Throwable -> 0x006e, all -> 0x005a
            int r2 = r0.length()     // Catch: Throwable -> 0x006e, all -> 0x005a
            r4 = 32
            if (r2 != r4) goto L_0x0038
        L_0x002f:
            r1.close()     // Catch: Throwable -> 0x0033
            goto L_0x0013
        L_0x0033:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0013
        L_0x0038:
            java.lang.String r0 = ""
            goto L_0x002f
        L_0x003b:
            r0 = move-exception
            r1 = r2
        L_0x003d:
            java.lang.String r2 = "AppInfo"
            java.lang.String r4 = "getKeyFromFile"
            com.loc.w.a(r0, r2, r4)     // Catch: all -> 0x005a
            boolean r0 = r3.exists()     // Catch: Throwable -> 0x0055, all -> 0x005a
            if (r0 == 0) goto L_0x004d
            r3.delete()     // Catch: Throwable -> 0x0055, all -> 0x005a
        L_0x004d:
            if (r1 == 0) goto L_0x0052
            r1.close()     // Catch: Throwable -> 0x0061
        L_0x0052:
            java.lang.String r0 = ""
            goto L_0x0013
        L_0x0055:
            r0 = move-exception
            r0.printStackTrace()     // Catch: all -> 0x005a
            goto L_0x004d
        L_0x005a:
            r0 = move-exception
        L_0x005b:
            if (r1 == 0) goto L_0x0060
            r1.close()     // Catch: Throwable -> 0x0066
        L_0x0060:
            throw r0
        L_0x0061:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0052
        L_0x0066:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0060
        L_0x006b:
            r0 = move-exception
            r1 = r2
            goto L_0x005b
        L_0x006e:
            r0 = move-exception
            goto L_0x003d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.k.g(android.content.Context):java.lang.String");
    }

    private static String h(Context context) throws PackageManager.NameNotFoundException {
        if (d == null || d.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return d;
            }
            String string = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            d = string;
            if (string == null) {
                d = g(context);
            }
        }
        return d;
    }
}

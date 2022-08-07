package com.amap.api.col;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Locale;

/* compiled from: AppInfo.java */
/* loaded from: classes.dex */
public class ga {
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static String f = "";
    static String a = null;
    static boolean b = false;

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.toCharArray();
        char[] charArray = str.toCharArray();
        for (char c2 : charArray) {
            if (('A' > c2 || c2 > 'z') && (('0' > c2 || c2 > ':') && c2 != '.')) {
                try {
                    gr.b(gk.a(), str, "errorPackage");
                    return false;
                } catch (Throwable th) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean a() {
        boolean z = true;
        try {
            if (!b) {
                if (b(a)) {
                    b = true;
                } else if (!TextUtils.isEmpty(a)) {
                    b = false;
                    a = null;
                    z = false;
                } else if (b(d)) {
                    b = true;
                } else if (!TextUtils.isEmpty(d)) {
                    b = false;
                    d = null;
                    z = false;
                }
            }
        } catch (Throwable th) {
        }
        return z;
    }

    public static String a(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return f;
        }
    }

    public static String b(Context context) {
        try {
        } catch (Throwable th) {
            go.a(th, "AppInfo", "getApplicationName");
        }
        if (!"".equals(c)) {
            return c;
        }
        PackageManager packageManager = context.getPackageManager();
        c = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        return c;
    }

    public static String c(Context context) {
        try {
        } catch (Throwable th) {
            go.a(th, "AppInfo", "getpckn");
        }
        if (d != null && !"".equals(d)) {
            return d;
        }
        d = context.getPackageName();
        if (!b(d)) {
            d = context.getPackageName();
        }
        return d;
    }

    public static String d(Context context) {
        try {
        } catch (Throwable th) {
            go.a(th, "AppInfo", "getApplicationVersion");
        }
        if (!"".equals(e)) {
            return e;
        }
        e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        return e == null ? "" : e;
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
            String str = packageInfo.packageName;
            if (b(str)) {
                str = packageInfo.packageName;
            }
            if (!TextUtils.isEmpty(d)) {
                str = c(context);
            }
            stringBuffer.append(str);
            a = stringBuffer.toString();
            return a;
        } catch (Throwable th) {
            go.a(th, "AppInfo", "getpck");
            return a;
        }
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            f = str;
        }
    }

    public static void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            f = str;
            if (context != null) {
                b(context, str);
            }
        }
    }

    public static String f(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            go.a(th, "AppInfo", "getKey");
            return f;
        }
    }

    private static void b(final Context context, final String str) {
        gr.c().submit(new Runnable() { // from class: com.amap.api.col.ga.1
            @Override // java.lang.Runnable
            public void run() {
                Throwable th;
                FileOutputStream fileOutputStream;
                Throwable th2;
                FileOutputStream fileOutputStream2;
                try {
                    fileOutputStream = null;
                    try {
                        File file = new File(gp.a(context, "k.store"));
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        fileOutputStream2 = new FileOutputStream(file);
                    } catch (Throwable th3) {
                        th2 = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
                try {
                    fileOutputStream2.write(gk.a(str));
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable th5) {
                            th5.printStackTrace();
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (Throwable th7) {
                            th7.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String g(android.content.Context r5) {
        /*
            java.lang.String r0 = "k.store"
            java.lang.String r0 = com.amap.api.col.gp.a(r5, r0)
            java.io.File r3 = new java.io.File
            r3.<init>(r0)
            boolean r0 = r3.exists()
            if (r0 != 0) goto L_0x0014
            java.lang.String r0 = ""
        L_0x0013:
            return r0
        L_0x0014:
            r2 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: Throwable -> 0x003d, all -> 0x006f
            r1.<init>(r3)     // Catch: Throwable -> 0x003d, all -> 0x006f
            int r0 = r1.available()     // Catch: Throwable -> 0x0072, all -> 0x005e
            byte[] r0 = new byte[r0]     // Catch: Throwable -> 0x0072, all -> 0x005e
            r1.read(r0)     // Catch: Throwable -> 0x0072, all -> 0x005e
            java.lang.String r0 = com.amap.api.col.gk.a(r0)     // Catch: Throwable -> 0x0072, all -> 0x005e
            int r2 = r0.length()     // Catch: Throwable -> 0x0072, all -> 0x005e
            r4 = 32
            if (r2 != r4) goto L_0x003a
        L_0x002f:
            if (r1 == 0) goto L_0x0013
            r1.close()     // Catch: Throwable -> 0x0035
            goto L_0x0013
        L_0x0035:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0013
        L_0x003a:
            java.lang.String r0 = ""
            goto L_0x002f
        L_0x003d:
            r0 = move-exception
            r1 = r2
        L_0x003f:
            java.lang.String r2 = "AppInfo"
            java.lang.String r4 = "getKeyFromFile"
            com.amap.api.col.go.a(r0, r2, r4)     // Catch: all -> 0x005e
            if (r3 == 0) goto L_0x0051
            boolean r0 = r3.exists()     // Catch: Throwable -> 0x0059, all -> 0x005e
            if (r0 == 0) goto L_0x0051
            r3.delete()     // Catch: Throwable -> 0x0059, all -> 0x005e
        L_0x0051:
            if (r1 == 0) goto L_0x0056
            r1.close()     // Catch: Throwable -> 0x0065
        L_0x0056:
            java.lang.String r0 = ""
            goto L_0x0013
        L_0x0059:
            r0 = move-exception
            r0.printStackTrace()     // Catch: all -> 0x005e
            goto L_0x0051
        L_0x005e:
            r0 = move-exception
        L_0x005f:
            if (r1 == 0) goto L_0x0064
            r1.close()     // Catch: Throwable -> 0x006a
        L_0x0064:
            throw r0
        L_0x0065:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0056
        L_0x006a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0064
        L_0x006f:
            r0 = move-exception
            r1 = r2
            goto L_0x005f
        L_0x0072:
            r0 = move-exception
            goto L_0x003f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.ga.g(android.content.Context):java.lang.String");
    }

    private static String h(Context context) throws PackageManager.NameNotFoundException {
        if (f == null || f.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return f;
            }
            f = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            if (f == null) {
                f = g(context);
            }
        }
        return f;
    }
}

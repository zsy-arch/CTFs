package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import u.aly.dc;

/* loaded from: classes2.dex */
public class b {
    public static String a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = "";

    private static PackageInfo a(String str, int i) {
        Class<?> cls;
        int i2 = 0;
        try {
            Class<?> cls2 = Class.forName("android.content.pm.PackageParser");
            Class<?>[] declaredClasses = cls2.getDeclaredClasses();
            int length = declaredClasses.length;
            while (true) {
                if (i2 >= length) {
                    cls = null;
                    break;
                }
                cls = declaredClasses[i2];
                if (cls.getName().compareTo("android.content.pm.PackageParser$Package") == 0) {
                    break;
                }
                i2++;
            }
            Constructor<?> constructor = cls2.getConstructor(String.class);
            Method declaredMethod = cls2.getDeclaredMethod("parsePackage", File.class, String.class, DisplayMetrics.class, Integer.TYPE);
            Method declaredMethod2 = cls2.getDeclaredMethod("collectCertificates", cls, Integer.TYPE);
            Method declaredMethod3 = cls2.getDeclaredMethod("generatePackageInfo", cls, int[].class, Integer.TYPE, Long.TYPE, Long.TYPE);
            constructor.setAccessible(true);
            declaredMethod.setAccessible(true);
            declaredMethod2.setAccessible(true);
            declaredMethod3.setAccessible(true);
            Object newInstance = constructor.newInstance(str);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            Object invoke = declaredMethod.invoke(newInstance, new File(str), str, displayMetrics, 0);
            if (invoke == null) {
                return null;
            }
            if ((i & 64) != 0) {
                declaredMethod2.invoke(newInstance, invoke, 0);
            }
            return (PackageInfo) declaredMethod3.invoke(null, invoke, null, Integer.valueOf(i), 0, 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
    public static String a() {
        InputStreamReader inputStreamReader;
        Throwable th;
        BufferedReader bufferedReader;
        String a2;
        BufferedReader bufferedReader2 = null;
        String isEmpty = TextUtils.isEmpty(c);
        try {
            if (isEmpty == 0) {
                return c;
            }
            try {
                inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec("getprop ro.product.cpu.abi").getInputStream());
                try {
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
                try {
                    a2 = bufferedReader.readLine().contains("x86") ? a("i686") : a(System.getProperty("os.arch"));
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    bufferedReader2 = bufferedReader;
                    String a3 = a(System.getProperty("os.arch"));
                    th.printStackTrace();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e3) {
                        }
                    }
                    if (inputStreamReader == null) {
                        return a3;
                    }
                    inputStreamReader.close();
                    isEmpty = a3;
                    return isEmpty;
                }
            } catch (Throwable th5) {
                th = th5;
                inputStreamReader = null;
            }
            if (inputStreamReader == null) {
                return a2;
            }
            inputStreamReader.close();
            isEmpty = a2;
            return isEmpty;
        } catch (IOException e4) {
            return isEmpty;
        }
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            return null;
        }
    }

    public static String a(Context context, File file) {
        String a2 = a(context, file, false);
        if (a2 == null) {
            a2 = a(file);
        }
        return a2 == null ? a(context, file, true) : a2;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001f  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(android.content.Context r4, java.io.File r5, boolean r6) {
        /*
            r0 = 0
            r3 = 65
            if (r6 == 0) goto L_0x0024
            java.lang.String r1 = r5.getAbsolutePath()
            android.content.pm.PackageInfo r1 = a(r1, r3)
        L_0x000d:
            if (r1 == 0) goto L_0x0038
            android.content.pm.Signature[] r2 = r1.signatures
            if (r2 == 0) goto L_0x0031
            android.content.pm.Signature[] r2 = r1.signatures
            int r2 = r2.length
            if (r2 <= 0) goto L_0x0031
            android.content.pm.Signature[] r1 = r1.signatures
            r2 = 0
            r1 = r1[r2]
        L_0x001d:
            if (r1 == 0) goto L_0x0023
            java.lang.String r0 = r1.toCharsString()
        L_0x0023:
            return r0
        L_0x0024:
            android.content.pm.PackageManager r1 = r4.getPackageManager()
            java.lang.String r2 = r5.getAbsolutePath()
            android.content.pm.PackageInfo r1 = r1.getPackageArchiveInfo(r2, r3)
            goto L_0x000d
        L_0x0031:
            java.lang.String r1 = "AppUtil"
            java.lang.String r2 = "[getSignatureFromApk] pkgInfo is not null BUT signatures is null!"
            com.tencent.smtt.utils.TbsLog.w(r1, r2)
        L_0x0038:
            r1 = r0
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, java.io.File, boolean):java.lang.String");
    }

    public static String a(Context context, String str) {
        String str2;
        try {
            try {
                return String.valueOf(Integer.toHexString(Integer.parseInt(String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(str)))));
            } catch (Exception e2) {
                return str2;
            }
        } catch (Exception e3) {
            return null;
        }
    }

    private static String a(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            byte[] bArr = new byte[8192];
            String a2 = a(a(jarFile, jarFile.getJarEntry("AndroidManifest.xml"), bArr)[0].getEncoded());
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry nextElement = entries.nextElement();
                String name = nextElement.getName();
                if (name != null) {
                    Certificate[] a3 = a(jarFile, nextElement, bArr);
                    String a4 = a3 != null ? a(a3[0].getEncoded()) : null;
                    if (a4 == null) {
                        if (!name.startsWith("META-INF/")) {
                            return null;
                        }
                    } else if (!a4.equals(a2)) {
                        return null;
                    }
                }
            }
            return a2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length * 2];
        for (int i = 0; i < length; i++) {
            byte b2 = bArr[i];
            int i2 = (b2 >> 4) & 15;
            cArr[i * 2] = (char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48);
            int i3 = b2 & dc.m;
            cArr[(i * 2) + 1] = (char) (i3 >= 10 ? (i3 + 97) - 10 : i3 + 48);
        }
        return new String(cArr);
    }

    private static Certificate[] a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) {
        InputStream inputStream = jarFile.getInputStream(jarEntry);
        do {
        } while (inputStream.read(bArr, 0, bArr.length) != -1);
        inputStream.close();
        if (jarEntry != null) {
            return jarEntry.getCertificates();
        }
        return null;
    }

    public static int b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e2) {
            return 0;
        }
    }

    public static String c(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String d(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String e(Context context) {
        if (!TextUtils.isEmpty(d)) {
            return d;
        }
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
            WifiInfo connectionInfo = wifiManager == null ? null : wifiManager.getConnectionInfo();
            return connectionInfo == null ? "" : connectionInfo.getMacAddress();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String f(Context context) {
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        try {
            e = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return e;
    }
}

package com.tencent.smtt.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static String f1499a = "";

    /* renamed from: b  reason: collision with root package name */
    public static String f1500b = "";

    /* renamed from: c  reason: collision with root package name */
    public static String f1501c = "";

    /* renamed from: d  reason: collision with root package name */
    public static String f1502d = "";

    /* renamed from: e  reason: collision with root package name */
    public static String f1503e = "";

    public static PackageInfo a(String str, int i) {
        Class<?> cls;
        try {
            Class<?> cls2 = Class.forName("android.content.pm.PackageParser");
            Class<?>[] declaredClasses = cls2.getDeclaredClasses();
            int length = declaredClasses.length;
            int i2 = 0;
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

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0065, code lost:
        if (r4 == null) goto L_0x0068;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a() {
        /*
            java.lang.String r0 = ""
            java.lang.String r1 = "os.arch"
            java.lang.String r2 = com.tencent.smtt.utils.b.f1501c
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x000f
            java.lang.String r0 = com.tencent.smtt.utils.b.f1501c
            return r0
        L_0x000f:
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch: Throwable -> 0x0053, all -> 0x0050
            java.lang.String r4 = "getprop ro.product.cpu.abi"
            java.lang.Process r3 = r3.exec(r4)     // Catch: Throwable -> 0x0053, all -> 0x0050
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: Throwable -> 0x0053, all -> 0x0050
            java.io.InputStream r3 = r3.getInputStream()     // Catch: Throwable -> 0x0053, all -> 0x0050
            r4.<init>(r3)     // Catch: Throwable -> 0x0053, all -> 0x0050
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: Throwable -> 0x004e, all -> 0x0069
            r3.<init>(r4)     // Catch: Throwable -> 0x004e, all -> 0x0069
            java.lang.String r2 = r3.readLine()     // Catch: Throwable -> 0x0049, all -> 0x0046
            java.lang.String r5 = "x86"
            boolean r2 = r2.contains(r5)     // Catch: Throwable -> 0x0049, all -> 0x0046
            if (r2 == 0) goto L_0x0037
            java.lang.String r0 = "i686"
            goto L_0x003f
        L_0x0037:
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch: Throwable -> 0x0049, all -> 0x0046
            if (r1 != 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            r0 = r1
        L_0x003f:
            r3.close()     // Catch: IOException -> 0x0042
        L_0x0042:
            r4.close()     // Catch: IOException -> 0x0068
            goto L_0x0068
        L_0x0046:
            r0 = move-exception
            r2 = r3
            goto L_0x006a
        L_0x0049:
            r2 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x0055
        L_0x004e:
            r3 = move-exception
            goto L_0x0055
        L_0x0050:
            r0 = move-exception
            r4 = r2
            goto L_0x006a
        L_0x0053:
            r3 = move-exception
            r4 = r2
        L_0x0055:
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch: all -> 0x0069
            if (r1 != 0) goto L_0x005c
            goto L_0x005d
        L_0x005c:
            r0 = r1
        L_0x005d:
            r3.printStackTrace()     // Catch: all -> 0x0069
            if (r2 == 0) goto L_0x0065
            r2.close()     // Catch: IOException -> 0x0065
        L_0x0065:
            if (r4 == 0) goto L_0x0068
            goto L_0x0042
        L_0x0068:
            return r0
        L_0x0069:
            r0 = move-exception
        L_0x006a:
            if (r2 == 0) goto L_0x006f
            r2.close()     // Catch: IOException -> 0x006f
        L_0x006f:
            if (r4 == 0) goto L_0x0074
            r4.close()     // Catch: IOException -> 0x0074
        L_0x0074:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a():java.lang.String");
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0031 A[Catch: Exception -> 0x0036, TRY_LEAVE, TryCatch #0 {Exception -> 0x0036, blocks: (B:4:0x0007, B:5:0x0010, B:7:0x001e, B:9:0x0022, B:11:0x0025, B:12:0x0029, B:15:0x0031), top: B:18:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r3, java.io.File r4, boolean r5) {
        /*
            java.lang.String r0 = "AppUtil"
            r1 = 65
            r2 = 0
            if (r5 == 0) goto L_0x0010
            java.lang.String r3 = r4.getAbsolutePath()     // Catch: Exception -> 0x0036
            android.content.pm.PackageInfo r3 = a(r3, r1)     // Catch: Exception -> 0x0036
            goto L_0x001c
        L_0x0010:
            android.content.pm.PackageManager r3 = r3.getPackageManager()     // Catch: Exception -> 0x0036
            java.lang.String r5 = r4.getAbsolutePath()     // Catch: Exception -> 0x0036
            android.content.pm.PackageInfo r3 = r3.getPackageArchiveInfo(r5, r1)     // Catch: Exception -> 0x0036
        L_0x001c:
            if (r3 == 0) goto L_0x002e
            android.content.pm.Signature[] r3 = r3.signatures     // Catch: Exception -> 0x0036
            if (r3 == 0) goto L_0x0029
            int r5 = r3.length     // Catch: Exception -> 0x0036
            if (r5 <= 0) goto L_0x0029
            r5 = 0
            r3 = r3[r5]     // Catch: Exception -> 0x0036
            goto L_0x002f
        L_0x0029:
            java.lang.String r3 = "[getSignatureFromApk] pkgInfo is not null BUT signatures is null!"
            com.tencent.smtt.utils.TbsLog.w(r0, r3)     // Catch: Exception -> 0x0036
        L_0x002e:
            r3 = r2
        L_0x002f:
            if (r3 == 0) goto L_0x004f
            java.lang.String r2 = r3.toCharsString()     // Catch: Exception -> 0x0036
            goto L_0x004f
        L_0x0036:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "getSign "
            r3.append(r5)
            r3.append(r4)
            java.lang.String r4 = "failed"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r3)
        L_0x004f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, java.io.File, boolean):java.lang.String");
    }

    public static String a(Context context, String str) {
        String str2;
        try {
            try {
                return String.valueOf(Integer.toHexString(Integer.parseInt(String.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), TbsListener.ErrorCode.DOWNLOAD_INTERRUPT).metaData.get(str)))));
            } catch (Exception unused) {
                return str2;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0066 A[Catch: Throwable -> 0x0077, TryCatch #6 {Throwable -> 0x0077, blocks: (B:30:0x0056, B:32:0x0066, B:34:0x0071), top: B:53:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r5, boolean r6, java.io.File r7) {
        /*
            java.lang.String r0 = "AppUtil"
            java.lang.String r1 = ""
            if (r7 == 0) goto L_0x00d0
            boolean r2 = r7.exists()
            if (r2 != 0) goto L_0x000e
            goto L_0x00d0
        L_0x000e:
            if (r6 == 0) goto L_0x0056
            r6 = 0
            r2 = 2
            byte[] r2 = new byte[r2]     // Catch: Exception -> 0x003d, all -> 0x003a
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch: Exception -> 0x003d, all -> 0x003a
            java.lang.String r4 = "r"
            r3.<init>(r7, r4)     // Catch: Exception -> 0x003d, all -> 0x003a
            r3.read(r2)     // Catch: Exception -> 0x0038, all -> 0x004c
            java.lang.String r6 = new java.lang.String     // Catch: Exception -> 0x0038, all -> 0x004c
            r6.<init>(r2)     // Catch: Exception -> 0x0038, all -> 0x004c
            java.lang.String r2 = "PK"
            boolean r6 = r6.equalsIgnoreCase(r2)     // Catch: Exception -> 0x0038, all -> 0x004c
            if (r6 != 0) goto L_0x0034
            r3.close()     // Catch: IOException -> 0x002f
            goto L_0x0033
        L_0x002f:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0033:
            return r1
        L_0x0034:
            r3.close()     // Catch: IOException -> 0x0047
            goto L_0x0056
        L_0x0038:
            r6 = move-exception
            goto L_0x0040
        L_0x003a:
            r5 = move-exception
            r3 = r6
            goto L_0x004d
        L_0x003d:
            r1 = move-exception
            r3 = r6
            r6 = r1
        L_0x0040:
            r6.printStackTrace()     // Catch: all -> 0x004c
            r3.close()     // Catch: IOException -> 0x0047
            goto L_0x0056
        L_0x0047:
            r6 = move-exception
            r6.printStackTrace()
            goto L_0x0056
        L_0x004c:
            r5 = move-exception
        L_0x004d:
            r3.close()     // Catch: IOException -> 0x0051
            goto L_0x0055
        L_0x0051:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0055:
            throw r5
        L_0x0056:
            android.content.Context r6 = r5.getApplicationContext()     // Catch: Throwable -> 0x0077
            java.lang.String r6 = r6.getPackageName()     // Catch: Throwable -> 0x0077
            java.lang.String r1 = "com.jd.jrapp"
            boolean r6 = r6.contains(r1)     // Catch: Throwable -> 0x0077
            if (r6 == 0) goto L_0x007c
            java.lang.String r6 = "[AppUtil.getSignatureFromApk]  #1"
            com.tencent.smtt.utils.TbsLog.i(r0, r6)     // Catch: Throwable -> 0x0077
            java.lang.String r6 = a(r7)     // Catch: Throwable -> 0x0077
            if (r6 == 0) goto L_0x007c
            java.lang.String r1 = "[AppUtil.getSignatureFromApk]  #2"
            com.tencent.smtt.utils.TbsLog.i(r0, r1)     // Catch: Throwable -> 0x0077
            return r6
        L_0x0077:
            java.lang.String r6 = "[AppUtil.getSignatureFromApk]  #3"
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
        L_0x007c:
            java.lang.String r6 = "[AppUtil.getSignatureFromApk]  #4"
            com.tencent.smtt.utils.TbsLog.i(r0, r6)
            r6 = 0
            java.lang.String r6 = a(r5, r7, r6)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[AppUtil.getSignatureFromApk]  android api signature="
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
            if (r6 != 0) goto L_0x00b4
            java.lang.String r6 = a(r7)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "[AppUtil.getSignatureFromApk]  java get signature="
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r1)
        L_0x00b4:
            if (r6 != 0) goto L_0x00cf
            r6 = 1
            java.lang.String r6 = a(r5, r7, r6)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "[AppUtil.getSignatureFromApk]  android reflection signature="
            r5.append(r7)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.tencent.smtt.utils.TbsLog.i(r0, r5)
        L_0x00cf:
            return r6
        L_0x00d0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.b.a(android.content.Context, boolean, java.io.File):java.lang.String");
    }

    public static String a(File file) {
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

    public static String a(String str) {
        return str == null ? BuildConfig.FLAVOR : str;
    }

    public static String a(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length * 2];
        for (int i = 0; i < length; i++) {
            byte b2 = bArr[i];
            int i2 = (b2 >> 4) & 15;
            int i3 = i * 2;
            cArr[i3] = (char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48);
            int i4 = b2 & 15;
            cArr[i3 + 1] = (char) (i4 >= 10 ? (i4 + 97) - 10 : i4 + 48);
        }
        return new String(cArr);
    }

    public static Certificate[] a(JarFile jarFile, JarEntry jarEntry, byte[] bArr) {
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
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String b() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return BuildConfig.FLAVOR;
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "02:00:00:00:00:00";
        } catch (Exception unused) {
            return "02:00:00:00:00:00";
        }
    }

    public static void b(Context context, String str) {
        String str2 = "saveGuid guid is " + str;
        try {
            TbsDownloadConfig instance = TbsDownloadConfig.getInstance(context);
            instance.mSyncMap.put(TbsDownloadConfig.TbsConfigKey.KEY_GUID, str);
            instance.commit();
        } catch (Exception unused) {
        }
    }

    public static String c(Context context) {
        String str = BuildConfig.FLAVOR;
        try {
            str = TbsDownloadConfig.getInstance(context).mPreferences.getString(TbsDownloadConfig.TbsConfigKey.KEY_GUID, str);
        } catch (Exception unused) {
        }
        String str2 = "getGuid guid is " + str;
        return str;
    }

    public static boolean c() {
        Class<?> cls;
        Method declaredMethod;
        Object invoke;
        Method declaredMethod2;
        try {
            int i = Build.VERSION.SDK_INT;
            cls = Class.forName("dalvik.system.VMRuntime");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (cls == null || (declaredMethod = cls.getDeclaredMethod("getRuntime", new Class[0])) == null || (invoke = declaredMethod.invoke(null, new Object[0])) == null || (declaredMethod2 = cls.getDeclaredMethod("is64Bit", new Class[0])) == null) {
            return false;
        }
        Object invoke2 = declaredMethod2.invoke(invoke, new Object[0]);
        if (invoke2 instanceof Boolean) {
            return ((Boolean) invoke2).booleanValue();
        }
        return false;
    }

    public static String d(Context context) {
        if (!TextUtils.isEmpty(f1499a)) {
            return f1499a;
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public static String e(Context context) {
        if (!TextUtils.isEmpty(f1500b)) {
            return f1500b;
        }
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
        } catch (Exception e2) {
            e2.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public static String f(Context context) {
        if (TextUtils.isEmpty(f1502d)) {
            if (Build.VERSION.SDK_INT < 23) {
                try {
                    WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                    WifiInfo connectionInfo = wifiManager == null ? null : wifiManager.getConnectionInfo();
                    f1502d = connectionInfo == null ? BuildConfig.FLAVOR : connectionInfo.getMacAddress();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                f1502d = b();
            }
        }
        return f1502d;
    }

    public static String g(Context context) {
        if (!TextUtils.isEmpty(f1503e)) {
            return f1503e;
        }
        try {
            f1503e = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return f1503e;
    }
}

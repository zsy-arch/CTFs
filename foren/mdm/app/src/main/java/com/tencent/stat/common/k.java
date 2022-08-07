package com.tencent.stat.common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.os.StatFs;
import android.support.v4.os.EnvironmentCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.tencent.stat.StatConfig;
import com.umeng.analytics.a;
import com.umeng.update.UpdateConfig;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import org.apache.http.HttpHost;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class k {
    private static String a = null;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static Random e = null;
    private static StatLogger f = null;
    private static String g = null;
    private static l h = null;
    private static n i = null;
    private static String j = "__MTA_FIRST_ACTIVATE__";
    private static int k = -1;

    public static String A(Context context) {
        List<Sensor> sensorList;
        try {
            SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
            if (!(sensorManager == null || (sensorList = sensorManager.getSensorList(-1)) == null)) {
                StringBuilder sb = new StringBuilder();
                for (int i2 = 0; i2 < sensorList.size(); i2++) {
                    sb.append(sensorList.get(i2).getType());
                    if (i2 != sensorList.size() - 1) {
                        sb.append(",");
                    }
                }
                return sb.toString();
            }
        } catch (Throwable th) {
            f.e(th);
        }
        return "";
    }

    public static WifiInfo B(Context context) {
        WifiManager wifiManager;
        if (!a(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi")) == null) {
            return null;
        }
        return wifiManager.getConnectionInfo();
    }

    public static String C(Context context) {
        try {
            WifiInfo B = B(context);
            if (B != null) {
                return B.getBSSID();
            }
        } catch (Throwable th) {
            f.e(th);
        }
        return null;
    }

    public static String D(Context context) {
        try {
            WifiInfo B = B(context);
            if (B != null) {
                return B.getSSID();
            }
        } catch (Throwable th) {
            f.e(th);
        }
        return null;
    }

    public static synchronized int E(Context context) {
        int i2;
        synchronized (k.class) {
            if (k != -1) {
                i2 = k;
            } else {
                F(context);
                i2 = k;
            }
        }
        return i2;
    }

    public static void F(Context context) {
        k = p.a(context, j, 1);
        f.e(Integer.valueOf(k));
        if (k == 1) {
            p.b(context, j, 0);
        }
    }

    private static long G(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    public static int a() {
        return h().nextInt(Integer.MAX_VALUE);
    }

    public static Long a(String str, String str2, int i2, int i3, Long l) {
        if (str == null || str2 == null) {
            return l;
        }
        if (str2.equalsIgnoreCase(".") || str2.equalsIgnoreCase("|")) {
            str2 = "\\" + str2;
        }
        String[] split = str.split(str2);
        if (split.length != i3) {
            return l;
        }
        try {
            Long l2 = 0L;
            for (String str3 : split) {
                l2 = Long.valueOf(i2 * (l2.longValue() + Long.valueOf(str3).longValue()));
            }
            return l2;
        } catch (NumberFormatException e2) {
            return l;
        }
    }

    public static String a(long j2) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(j2));
    }

    public static String a(String str) {
        if (str == null) {
            return "0";
        }
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i2 = b2 & 255;
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            return "0";
        }
    }

    public static HttpHost a(Context context) {
        HttpHost httpHost;
        if (context == null) {
            return null;
        }
        try {
        } catch (Throwable th) {
            f.e(th);
        }
        if (context.getPackageManager().checkPermission(UpdateConfig.g, context.getPackageName()) != 0) {
            httpHost = null;
        } else {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                httpHost = null;
            } else if (activeNetworkInfo.getTypeName() == null || !activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                if (extraInfo == null) {
                    httpHost = null;
                } else if (extraInfo.equals("cmwap") || extraInfo.equals("3gwap") || extraInfo.equals("uniwap")) {
                    httpHost = new HttpHost("10.0.0.172", 80);
                } else {
                    if (extraInfo.equals("ctwap")) {
                        httpHost = new HttpHost("10.0.0.200", 80);
                    }
                    httpHost = null;
                }
            } else {
                httpHost = null;
            }
        }
        return httpHost;
    }

    public static void a(JSONObject jSONObject, String str, String str2) {
        if (str2 != null) {
            try {
                if (str2.length() > 0) {
                    jSONObject.put(str, str2);
                }
            } catch (Throwable th) {
                f.e(th);
            }
        }
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            f.e(th);
            return false;
        }
    }

    public static byte[] a(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
        byte[] bArr2 = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length * 2);
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read != -1) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayInputStream.close();
                gZIPInputStream.close();
                byteArrayOutputStream.close();
                return byteArray;
            }
        }
    }

    public static long b(String str) {
        return a(str, ".", 100, 3, 0L).longValue();
    }

    public static synchronized StatLogger b() {
        StatLogger statLogger;
        synchronized (k.class) {
            if (f == null) {
                f = new StatLogger("MtaSDK");
                f.setDebugEnable(false);
            }
            statLogger = f;
        }
        return statLogger;
    }

    public static synchronized String b(Context context) {
        String str;
        synchronized (k.class) {
            if (a == null || a.trim().length() == 0) {
                a = l(context);
                if (a == null || a.trim().length() == 0) {
                    a = Integer.toString(h().nextInt(Integer.MAX_VALUE));
                }
                str = a;
            } else {
                str = a;
            }
        }
        return str;
    }

    public static String b(Context context, String str) {
        if (!StatConfig.isEnableConcurrentProcess()) {
            return str;
        }
        if (g == null) {
            g = u(context);
        }
        return g != null ? str + "_" + g : str;
    }

    public static long c() {
        try {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return instance.getTimeInMillis() + a.j;
        } catch (Throwable th) {
            f.e(th);
            return System.currentTimeMillis() + a.j;
        }
    }

    public static synchronized String c(Context context) {
        String str;
        synchronized (k.class) {
            if (c == null || "" == c) {
                c = f(context);
            }
            str = c;
        }
        return str;
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(g.b(e.a(str.getBytes("UTF-8")), 0), "UTF-8");
        } catch (Throwable th) {
            f.e(th);
            return str;
        }
    }

    public static int d() {
        return Build.VERSION.SDK_INT;
    }

    public static DisplayMetrics d(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 8) {
            return str;
        }
        try {
            return new String(e.b(g.a(str.getBytes("UTF-8"), 0)), "UTF-8");
        } catch (Throwable th) {
            f.e(th);
            return str;
        }
    }

    public static String e() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return String.valueOf((statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1000000) + "/" + String.valueOf(f() / 1000000);
    }

    public static boolean e(Context context) {
        NetworkInfo[] allNetworkInfo;
        try {
        } catch (Throwable th) {
            f.e(th);
        }
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
            if (!(connectivityManager == null || (allNetworkInfo = connectivityManager.getAllNetworkInfo()) == null)) {
                for (int i2 = 0; i2 < allNetworkInfo.length; i2++) {
                    if (allNetworkInfo[i2].getTypeName().equalsIgnoreCase("WIFI") && allNetworkInfo[i2].isConnected()) {
                        return true;
                    }
                }
            }
            return false;
        }
        f.warn("can not get the permission of android.permission.ACCESS_WIFI_STATE");
        return false;
    }

    public static long f() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockCount() * statFs.getBlockSize();
    }

    public static String f(Context context) {
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            try {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                return wifiManager == null ? "" : wifiManager.getConnectionInfo().getMacAddress();
            } catch (Exception e2) {
                f.e(e2);
                return "";
            }
        } else {
            f.e("Could not get permission of android.permission.ACCESS_WIFI_STATE");
            return "";
        }
    }

    public static boolean g(Context context) {
        try {
            if (!a(context, UpdateConfig.h) || !a(context, UpdateConfig.g)) {
                f.warn("can not get the permisson of android.permission.INTERNET");
            } else {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI");
                }
            }
        } catch (Throwable th) {
            f.e(th);
        }
        return false;
    }

    private static synchronized Random h() {
        Random random;
        synchronized (k.class) {
            if (e == null) {
                e = new Random();
            }
            random = e;
        }
        return random;
    }

    public static boolean h(Context context) {
        try {
            if (!a(context, UpdateConfig.h) || !a(context, UpdateConfig.g)) {
                f.warn("can not get the permisson of android.permission.INTERNET");
            } else {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                        return true;
                    }
                    f.w("Network error");
                    return false;
                }
            }
        } catch (Throwable th) {
        }
        return false;
    }

    private static long i() {
        long j2 = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
            j2 = Integer.valueOf(bufferedReader.readLine().split("\\s+")[1]).intValue() * 1024;
            bufferedReader.close();
            return j2;
        } catch (IOException e2) {
            return j2;
        }
    }

    public static String i(Context context) {
        if (b != null) {
            return b;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString("TA_APPKEY");
                if (string != null) {
                    b = string;
                    return string;
                }
                f.w("Could not read APPKEY meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f.w("Could not read APPKEY meta-data from AndroidManifest.xml");
        }
        return null;
    }

    public static String j(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                Object obj = applicationInfo.metaData.get("InstallChannel");
                if (obj != null) {
                    return obj.toString();
                }
                f.w("Could not read InstallChannel meta-data from AndroidManifest.xml");
            }
        } catch (Throwable th) {
            f.e("Could not read InstallChannel meta-data from AndroidManifest.xml");
        }
        return null;
    }

    public static String k(Context context) {
        if (context == null) {
            return null;
        }
        return context.getClass().getName();
    }

    public static String l(Context context) {
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                String str = "";
                if (o(context)) {
                    str = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                }
                if (str != null) {
                    return str;
                }
            } else {
                f.e("Could not get permission of android.permission.READ_PHONE_STATE");
            }
        } catch (Throwable th) {
            f.e(th);
        }
        return null;
    }

    public static String m(Context context) {
        try {
            if (!a(context, "android.permission.READ_PHONE_STATE")) {
                f.e("Could not get permission of android.permission.READ_PHONE_STATE");
                return null;
            } else if (!o(context)) {
                return null;
            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                return telephonyManager != null ? telephonyManager.getSimOperator() : null;
            }
        } catch (Throwable th) {
            f.e(th);
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:10:0x0020
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:86)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    public static java.lang.String n(android.content.Context r5) {
        /*
            java.lang.String r1 = ""
            android.content.pm.PackageManager r0 = r5.getPackageManager()     // Catch: Throwable -> 0x0016
            java.lang.String r2 = r5.getPackageName()     // Catch: Throwable -> 0x0016
            r3 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r2, r3)     // Catch: Throwable -> 0x0016
            java.lang.String r0 = r0.versionName     // Catch: Throwable -> 0x0016
            if (r0 != 0) goto L_0x0015
            java.lang.String r0 = ""
        L_0x0015:
            return r0
        L_0x0016:
            r0 = move-exception
            r4 = r0
            r0 = r1
            r1 = r4
        L_0x001a:
            com.tencent.stat.common.StatLogger r2 = com.tencent.stat.common.k.f
            r2.e(r1)
            goto L_0x0015
        L_0x0020:
            r1 = move-exception
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.stat.common.k.n(android.content.Context):java.lang.String");
    }

    public static boolean o(Context context) {
        return context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0;
    }

    public static String p(Context context) {
        try {
            if (!a(context, UpdateConfig.h) || !a(context, UpdateConfig.g)) {
                f.e("can not get the permission of android.permission.ACCESS_WIFI_STATE");
            } else {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                    String typeName = activeNetworkInfo.getTypeName();
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (typeName != null) {
                        return typeName.equalsIgnoreCase("WIFI") ? "WIFI" : typeName.equalsIgnoreCase("MOBILE") ? extraInfo == null ? "MOBILE" : extraInfo : extraInfo == null ? typeName : extraInfo;
                    }
                }
            }
        } catch (Throwable th) {
            f.e(th);
        }
        return null;
    }

    public static Integer q(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return Integer.valueOf(telephonyManager.getNetworkType());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String r(Context context) {
        Throwable th;
        String str = "";
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str != null) {
                try {
                    if (str.length() != 0) {
                        return str;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    f.e(th);
                    return str;
                }
            }
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static int s(Context context) {
        try {
        } catch (Throwable th) {
            f.e(th);
        }
        return o.a() ? 1 : 0;
    }

    public static String t(Context context) {
        String path;
        String str = null;
        try {
            if (a(context, UpdateConfig.f)) {
                String externalStorageState = Environment.getExternalStorageState();
                if (!(externalStorageState == null || !externalStorageState.equals("mounted") || (path = Environment.getExternalStorageDirectory().getPath()) == null)) {
                    StatFs statFs = new StatFs(path);
                    str = String.valueOf((statFs.getBlockSize() * statFs.getAvailableBlocks()) / 1000000) + "/" + String.valueOf((statFs.getBlockCount() * statFs.getBlockSize()) / 1000000);
                }
            } else {
                f.warn("can not get the permission of android.permission.WRITE_EXTERNAL_STORAGE");
            }
        } catch (Throwable th) {
            f.e(th);
        }
        return str;
    }

    static String u(Context context) {
        try {
            int myPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public static String v(Context context) {
        return b(context, StatConstants.a);
    }

    public static synchronized Integer w(Context context) {
        Integer valueOf;
        int i2 = 0;
        synchronized (k.class) {
            int a2 = p.a(context, "MTA_EVENT_INDEX", 0);
            if (a2 < 2147483646) {
                i2 = a2;
            }
            p.b(context, "MTA_EVENT_INDEX", i2 + 1);
            valueOf = Integer.valueOf(i2 + 1);
        }
        return valueOf;
    }

    public static String x(Context context) {
        return String.valueOf(G(context) / 1000000) + "/" + String.valueOf(i() / 1000000);
    }

    public static synchronized l y(Context context) {
        l lVar;
        synchronized (k.class) {
            if (h == null) {
                h = new l();
            }
            lVar = h;
        }
        return lVar;
    }

    public static JSONObject z(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            y(context);
            int b2 = l.b();
            if (b2 > 0) {
                jSONObject.put("fx", b2 / 1000000);
            }
            y(context);
            int c2 = l.c();
            if (c2 > 0) {
                jSONObject.put("fn", c2 / 1000000);
            }
            y(context);
            int a2 = l.a();
            if (a2 > 0) {
                jSONObject.put("n", a2);
            }
            y(context);
            String d2 = l.d();
            if (d2 != null && d2.length() == 0) {
                y(context);
                jSONObject.put("na", l.d());
            }
        } catch (Exception e2) {
            f.e(e2);
        }
        return jSONObject;
    }
}

package com.amap.api.col;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Random;
import org.json.JSONObject;

/* compiled from: Utils.java */
/* loaded from: classes.dex */
public final class jq {
    private static int b = 0;
    private static String[] c = null;
    private static Hashtable<String, Long> d = new Hashtable<>();
    private static SimpleDateFormat e = null;
    private static String[] f = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    static String a = null;

    public static double a(double d2) {
        return ((long) (d2 * 1000000.0d)) / 1000000.0d;
    }

    public static float a(float f2) {
        return (float) (((long) (f2 * 100.0d)) / 100.0d);
    }

    public static float a(double[] dArr) {
        if (dArr.length != 4) {
            return 0.0f;
        }
        float[] fArr = new float[1];
        Location.distanceBetween(dArr[0], dArr[1], dArr[2], dArr[3], fArr);
        return fArr[0];
    }

    public static int a(int i) {
        return (i * 2) - 113;
    }

    public static int a(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            return networkInfo.getType();
        }
        return -1;
    }

    public static long a() {
        return System.currentTimeMillis();
    }

    public static Object a(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getApplicationContext().getSystemService(str);
        } catch (Throwable th) {
            jn.a(th, "Utils", "getServ");
            return null;
        }
    }

    public static boolean a(Context context) {
        boolean z = false;
        if (context != null) {
            try {
                z = c() < 17 ? b(context, "android.provider.Settings$System") : b(context, "android.provider.Settings$Global");
            } catch (Throwable th) {
            }
        }
        return z;
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && !str.equals("00:00:00:00:00:00") && !str.contains(" :");
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return gk.a(jSONObject, str);
    }

    public static byte[] a(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            bArr = new byte[2];
        }
        bArr[0] = (byte) (i & 255);
        bArr[1] = (byte) ((65280 & i) >> 8);
        return bArr;
    }

    public static byte[] a(long j) {
        byte[] bArr = new byte[8];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) ((j >> (i * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr) {
        try {
            return gk.b(bArr);
        } catch (Throwable th) {
            jn.a(th, "Utils", "gz");
            return null;
        }
    }

    public static String[] a(TelephonyManager telephonyManager) {
        int i;
        String str = null;
        if (telephonyManager != null) {
            str = telephonyManager.getNetworkOperator();
        }
        String[] strArr = new String[2];
        strArr[0] = "0";
        strArr[1] = "0";
        if (TextUtils.isEmpty(str) ? false : !TextUtils.isDigitsOnly(str) ? false : str.length() > 4) {
            strArr[0] = str.substring(0, 3);
            char[] charArray = str.substring(3).toCharArray();
            int i2 = 0;
            while (i2 < charArray.length && Character.isDigit(charArray[i2])) {
                i2++;
            }
            strArr[1] = str.substring(3, i2 + 3);
        }
        try {
            i = Integer.parseInt(strArr[0]);
        } catch (Throwable th) {
            jn.a(th, "Utils", "getMccMnc");
            i = 0;
        }
        if (i == 0) {
            strArr[0] = "0";
        }
        if (strArr[0].equals("0") || strArr[1].equals("0")) {
            return (!strArr[0].equals("0") || !strArr[1].equals("0") || c == null) ? strArr : c;
        }
        c = strArr;
        return strArr;
    }

    public static double b(double d2) {
        return ((long) (d2 * 100.0d)) / 100.0d;
    }

    public static long b() {
        return SystemClock.elapsedRealtime();
    }

    public static String b(int i) {
        switch (i) {
            case 0:
                return "success";
            case 1:
                return "重要参数为空";
            case 2:
                return "WIFI信息不足";
            case 3:
                return "请求参数获取出现异常";
            case 4:
                return "网络连接异常";
            case 5:
                return "解析数据异常";
            case 6:
                return "定位结果错误";
            case 7:
                return "KEY错误";
            case 8:
                return "其他错误";
            case 9:
                return "初始化异常";
            case 10:
                return "定位服务启动失败";
            case 11:
                return "错误的基站信息，请检查是否插入SIM卡";
            case 12:
                return "缺少定位权限";
            case 13:
                return "网络定位失败，请检查设备是否插入sim卡，是否开启移动网络或开启了wifi模块";
            case 14:
                return "GPS 定位失败，由于设备当前 GPS 状态差,建议持设备到相对开阔的露天场所再次尝试";
            case 15:
                return "当前返回位置为模拟软件返回，请关闭模拟软件，或者在option中设置允许模拟";
            default:
                return "其他错误";
        }
    }

    public static String b(Context context) {
        PackageInfo packageInfo;
        CharSequence charSequence = null;
        if (!TextUtils.isEmpty(jn.d)) {
            return jn.d;
        }
        if (context == null) {
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 64);
        } catch (Throwable th) {
            jn.a(th, "Utils", "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(jn.e)) {
                jn.e = null;
            }
        } catch (Throwable th2) {
            jn.a(th2, "Utils", "getAppName");
        }
        StringBuilder sb = new StringBuilder();
        if (packageInfo != null) {
            if (packageInfo.applicationInfo != null) {
                charSequence = packageInfo.applicationInfo.loadLabel(context.getPackageManager());
            }
            if (charSequence != null) {
                sb.append(charSequence.toString());
            }
            if (!TextUtils.isEmpty(packageInfo.versionName)) {
                sb.append(packageInfo.versionName);
            }
        }
        String c2 = ga.c(context);
        if (!TextUtils.isEmpty(c2)) {
            sb.append(",").append(c2);
        }
        if (!TextUtils.isEmpty(jn.e)) {
            sb.append(",").append(jn.e);
        }
        String sb2 = sb.toString();
        jn.d = sb2;
        return sb2;
    }

    public static String b(TelephonyManager telephonyManager) {
        int i = 0;
        if (telephonyManager != null) {
            i = telephonyManager.getNetworkType();
        }
        return jn.f.get(i, "UNKWN");
    }

    private static boolean b(Context context, String str) throws Throwable {
        return ((Integer) jo.a(str, "getInt", new Object[]{context.getContentResolver(), ((String) jo.a(str, "AIRPLANE_MODE_ON")).toString()}, new Class[]{ContentResolver.class, String.class})).intValue() == 1;
    }

    public static byte[] b(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 4) {
            bArr = new byte[4];
        }
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = (byte) ((i >> (i2 * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] b(String str) {
        return a(Integer.parseInt(str), (byte[]) null);
    }

    public static int c() {
        if (b > 0) {
            return b;
        }
        try {
            return jo.b("android.os.Build$VERSION", "SDK_INT");
        } catch (Throwable th) {
            try {
                return Integer.parseInt(jo.a("android.os.Build$VERSION", "SDK").toString());
            } catch (Throwable th2) {
                return 0;
            }
        }
    }

    public static NetworkInfo c(Context context) {
        try {
            return ge.n(context);
        } catch (Throwable th) {
            jn.a(th, "Utils", "getNetWorkInfo");
            return null;
        }
    }

    public static byte[] c(String str) {
        return b(Integer.parseInt(str), (byte[]) null);
    }

    public static String d() {
        return Build.MODEL;
    }

    public static String e() {
        return Build.VERSION.RELEASE;
    }

    public static int f() {
        return new Random().nextInt(65536) - 32768;
    }
}

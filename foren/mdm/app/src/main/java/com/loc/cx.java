package com.loc;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Base64;
import com.amap.api.fence.GeoFenceManagerBase;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.DPoint;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.json.JSONObject;

/* compiled from: Utils.java */
/* loaded from: classes2.dex */
public final class cx {
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

    public static float a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        return a(new double[]{aMapLocation.getLatitude(), aMapLocation.getLongitude(), aMapLocation2.getLatitude(), aMapLocation2.getLongitude()});
    }

    public static float a(DPoint dPoint, DPoint dPoint2) {
        return a(new double[]{dPoint.getLatitude(), dPoint.getLongitude(), dPoint2.getLatitude(), dPoint2.getLongitude()});
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

    public static int a(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    public static int a(NetworkInfo networkInfo) {
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected()) {
            return networkInfo.getType();
        }
        return -1;
    }

    public static int a(boolean z, CellLocation cellLocation) {
        if (z || cellLocation == null) {
            return 0;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            f.a(th, "Utils", "getCellLocT");
            return 0;
        }
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
            f.a(th, "Utils", "getServ");
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0061 A[Catch: Throwable -> 0x0025, TRY_ENTER, TryCatch #4 {Throwable -> 0x0025, blocks: (B:6:0x000b, B:8:0x0010, B:13:0x001c, B:15:0x0021, B:29:0x0061, B:31:0x0066, B:36:0x0071, B:38:0x0076), top: B:60:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0066 A[Catch: Throwable -> 0x0025, TRY_LEAVE, TryCatch #4 {Throwable -> 0x0025, blocks: (B:6:0x000b, B:8:0x0010, B:13:0x001c, B:15:0x0021, B:29:0x0061, B:31:0x0066, B:36:0x0071, B:38:0x0076), top: B:60:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0083 A[Catch: Throwable -> 0x0087, TRY_LEAVE, TryCatch #6 {Throwable -> 0x0087, blocks: (B:42:0x007e, B:44:0x0083), top: B:61:0x007e }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(double r6, float r8) {
        /*
            r1 = 0
            r0 = 0
            r2 = 0
            boolean r3 = k()     // Catch: Throwable -> 0x006a, all -> 0x007a
            if (r3 != 0) goto L_0x0014
            if (r1 == 0) goto L_0x000e
            r0.close()     // Catch: Throwable -> 0x0025
        L_0x000e:
            if (r1 == 0) goto L_0x0013
            r2.close()     // Catch: Throwable -> 0x0025
        L_0x0013:
            return
        L_0x0014:
            java.lang.String r3 = l()     // Catch: Throwable -> 0x006a, all -> 0x007a
            if (r3 != 0) goto L_0x0027
            if (r1 == 0) goto L_0x001f
            r0.close()     // Catch: Throwable -> 0x0025
        L_0x001f:
            if (r1 == 0) goto L_0x0013
            r2.close()     // Catch: Throwable -> 0x0025
            goto L_0x0013
        L_0x0025:
            r0 = move-exception
            goto L_0x0013
        L_0x0027:
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch: Throwable -> 0x006a, all -> 0x007a
            if (r0 != 0) goto L_0x009c
            java.io.File r0 = new java.io.File     // Catch: Throwable -> 0x006a, all -> 0x007a
            r0.<init>(r3)     // Catch: Throwable -> 0x006a, all -> 0x007a
            boolean r2 = r0.canWrite()     // Catch: Throwable -> 0x006a, all -> 0x007a
            if (r2 == 0) goto L_0x009c
            boolean r2 = r0.exists()     // Catch: Throwable -> 0x006a, all -> 0x007a
            if (r2 == 0) goto L_0x0044
            r0.delete()     // Catch: Throwable -> 0x006a, all -> 0x007a
            r0.createNewFile()     // Catch: Throwable -> 0x006a, all -> 0x007a
        L_0x0044:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: Throwable -> 0x006a, all -> 0x007a
            r2.<init>(r0)     // Catch: Throwable -> 0x006a, all -> 0x007a
            java.io.DataOutputStream r3 = new java.io.DataOutputStream     // Catch: Throwable -> 0x0093, all -> 0x0089
            r3.<init>(r2)     // Catch: Throwable -> 0x0093, all -> 0x0089
            r3.writeDouble(r6)     // Catch: Throwable -> 0x0098, all -> 0x008d
            r3.writeFloat(r8)     // Catch: Throwable -> 0x0098, all -> 0x008d
            long r0 = java.lang.System.currentTimeMillis()     // Catch: Throwable -> 0x0098, all -> 0x008d
            r3.writeLong(r0)     // Catch: Throwable -> 0x0098, all -> 0x008d
            r3.flush()     // Catch: Throwable -> 0x0098, all -> 0x008d
            r1 = r2
        L_0x005f:
            if (r3 == 0) goto L_0x0064
            r3.close()     // Catch: Throwable -> 0x0025
        L_0x0064:
            if (r1 == 0) goto L_0x0013
            r1.close()     // Catch: Throwable -> 0x0025
            goto L_0x0013
        L_0x006a:
            r0 = move-exception
            r2 = r1
        L_0x006c:
            r0.printStackTrace()     // Catch: all -> 0x0090
            if (r2 == 0) goto L_0x0074
            r2.close()     // Catch: Throwable -> 0x0025
        L_0x0074:
            if (r1 == 0) goto L_0x0013
            r1.close()     // Catch: Throwable -> 0x0025
            goto L_0x0013
        L_0x007a:
            r0 = move-exception
            r3 = r1
        L_0x007c:
            if (r3 == 0) goto L_0x0081
            r3.close()     // Catch: Throwable -> 0x0087
        L_0x0081:
            if (r1 == 0) goto L_0x0086
            r1.close()     // Catch: Throwable -> 0x0087
        L_0x0086:
            throw r0
        L_0x0087:
            r1 = move-exception
            goto L_0x0086
        L_0x0089:
            r0 = move-exception
            r3 = r1
            r1 = r2
            goto L_0x007c
        L_0x008d:
            r0 = move-exception
            r1 = r2
            goto L_0x007c
        L_0x0090:
            r0 = move-exception
            r3 = r2
            goto L_0x007c
        L_0x0093:
            r0 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
            goto L_0x006c
        L_0x0098:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x006c
        L_0x009c:
            r3 = r1
            goto L_0x005f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cx.a(double, float):void");
    }

    public static void a(Context context, int i) {
        if (context != null) {
            try {
                af afVar = new af(context, af.a((Class<? extends ae>) ck.class), i());
                cg cgVar = new cg();
                cgVar.a(i);
                afVar.a(cgVar, "_id=1");
            } catch (Throwable th) {
                f.a(th, "Utils", "getDBConfigVersion");
            }
        }
    }

    public static boolean a(long j, long j2) {
        if (e == null) {
            try {
                e = new SimpleDateFormat("yyyyMMddHH", Locale.CHINA);
            } catch (Throwable th) {
                f.a(th, "Utils", "isSameDay part1");
            }
        } else {
            e.applyPattern("yyyyMMddHH");
        }
        try {
            if (e != null) {
                return e.format(Long.valueOf(j)).equals(e.format(Long.valueOf(j2)));
            }
            return false;
        } catch (Throwable th2) {
            f.a(th2, "Utils", "isSameHour");
            return false;
        }
    }

    public static boolean a(Context context) {
        boolean z = false;
        if (context != null) {
            try {
                z = c() < 17 ? c(context, "android.provider.Settings$System") : c(context, "android.provider.Settings$Global");
            } catch (Throwable th) {
            }
        }
        return z;
    }

    public static boolean a(Location location, int i) {
        Bundle extras = location.getExtras();
        if ((extras != null ? extras.getInt("satellites") : 0) <= 0) {
            return true;
        }
        return i == 0 && location.getAltitude() == 0.0d && location.getBearing() == 0.0f && location.getSpeed() == 0.0f;
    }

    public static boolean a(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            return b(aMapLocation);
        }
        return false;
    }

    public static boolean a(AMapLocationServer aMapLocationServer) {
        if (aMapLocationServer != null && !aMapLocationServer.c().equals("8") && !aMapLocationServer.c().equals("5") && !aMapLocationServer.c().equals("6")) {
            return b(aMapLocationServer);
        }
        return false;
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
            return ",111,123,134,199,202,204,206,208,212,213,214,216,218,219,220,222,225,226,228,230,231,232,234,235,238,240,242,244,246,247,248,250,255,257,259,260,262,266,268,270,272,274,276,278,280,282,283,284,286,288,289,290,292,293,294,295,297,302,308,310,311,312,313,314,315,316,310,330,332,334,338,340,342,344,346,348,350,352,354,356,358,360,362,363,364,365,366,368,370,372,374,376,400,401,402,404,405,406,410,412,413,414,415,416,417,418,419,420,421,422,424,425,426,427,428,429,430,431,432,434,436,437,438,440,441,450,452,454,455,456,457,466,467,470,472,502,505,510,514,515,520,525,528,530,534,535,536,537,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,555,560,598,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,645,646,647,648,649,650,651,652,653,654,655,657,659,665,702,704,706,708,710,712,714,716,722,724,730,732,734,736,738,740,742,744,746,748,750,850,901,".contains("," + str + ",");
        }
        return false;
    }

    public static boolean a(JSONObject jSONObject, String str) {
        return t.a(jSONObject, str);
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
            return t.b(bArr);
        } catch (Throwable th) {
            f.a(th, "Utils", "gz");
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
            f.a(th, "Utils", "getMccMnc");
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
        if (!TextUtils.isEmpty(f.d)) {
            return f.d;
        }
        if (context == null) {
            return null;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 64);
        } catch (Throwable th) {
            f.a(th, "Utils", "getAppName part");
            packageInfo = null;
        }
        try {
            if (TextUtils.isEmpty(f.e)) {
                f.e = null;
            }
        } catch (Throwable th2) {
            f.a(th2, "Utils", "getAppName");
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
        String c2 = k.c(context);
        if (!TextUtils.isEmpty(c2)) {
            sb.append(",").append(c2);
        }
        if (!TextUtils.isEmpty(f.e)) {
            sb.append(",").append(f.e);
        }
        String sb2 = sb.toString();
        f.d = sb2;
        return sb2;
    }

    public static String b(TelephonyManager telephonyManager) {
        int i = 0;
        if (telephonyManager != null) {
            i = telephonyManager.getNetworkType();
        }
        return f.j.get(i, "UNKWN");
    }

    public static String b(byte[] bArr) {
        return t.f(bArr);
    }

    public static boolean b(long j, long j2) {
        if (e == null) {
            try {
                e = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
            } catch (Throwable th) {
                f.a(th, "Utils", "isSameDay part1");
            }
        } else {
            e.applyPattern("yyyyMMdd");
        }
        try {
            if (e != null) {
                return e.format(Long.valueOf(j)).equals(e.format(Long.valueOf(j2)));
            }
            return false;
        } catch (Throwable th2) {
            f.a(th2, "Utils", "isSameDay");
            return false;
        }
    }

    public static boolean b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Throwable th) {
            return false;
        }
    }

    public static boolean b(AMapLocation aMapLocation) {
        double longitude = aMapLocation.getLongitude();
        double latitude = aMapLocation.getLatitude();
        return !(longitude == 0.0d && latitude == 0.0d) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str) && !str.equals("00:00:00:00:00:00") && !str.contains(" :");
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

    public static double c(double d2) {
        return ((long) (d2 * 1000000.0d)) / 1000000.0d;
    }

    public static int c() {
        if (b > 0) {
            return b;
        }
        try {
            return ((Integer) cs.a("android.os.Build$VERSION", "SDK_INT")).intValue();
        } catch (Throwable th) {
            try {
                return Integer.parseInt(cs.a("android.os.Build$VERSION", "SDK").toString());
            } catch (Throwable th2) {
                return 0;
            }
        }
    }

    public static NetworkInfo c(Context context) {
        try {
            return n.n(context);
        } catch (Throwable th) {
            f.a(th, "Utils", "getNetWorkInfo");
            return null;
        }
    }

    public static String c(String str) {
        return g(str);
    }

    public static boolean c(long j, long j2) {
        boolean z = true;
        if (!b(j, j2)) {
            return false;
        }
        Calendar instance = Calendar.getInstance(Locale.CHINA);
        instance.setTimeInMillis(j);
        int i = instance.get(11);
        instance.setTimeInMillis(j2);
        int i2 = instance.get(11);
        if (i <= 12 ? i2 > 12 : i2 <= 12) {
            z = false;
        }
        return z;
    }

    private static boolean c(Context context, String str) throws Throwable {
        return ((Integer) cs.a(str, "getInt", new Object[]{context.getContentResolver(), ((String) cs.a(str, "AIRPLANE_MODE_ON")).toString()}, new Class[]{ContentResolver.class, String.class})).intValue() == 1;
    }

    public static byte[] c(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 1];
        bArr2[0] = 0;
        for (int i = 1; i <= bArr.length; i++) {
            bArr2[i] = bArr[bArr.length - i];
        }
        return bArr2;
    }

    public static String d() {
        return Build.MODEL;
    }

    public static String d(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            return new String(Base64.decode(str, 0), "UTF-8");
        } catch (Throwable th) {
            f.a(th, "Utils", "base642Str");
            return null;
        }
    }

    public static boolean d(Context context) {
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                    return runningAppProcessInfo.importance != 100;
                }
            }
            return false;
        } catch (Throwable th) {
            f.a(th, "Utils", "isApplicationBroughtToBackground");
            return true;
        }
    }

    public static byte[] d(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 10, bArr2, 0, Math.min(26, bArr.length) - 10);
        return bArr2;
    }

    public static int e(Context context) {
        try {
            List b2 = new af(context, af.a((Class<? extends ae>) ck.class), i()).b("_id=1", cg.class);
            if (b2 != null && b2.size() > 0) {
                return ((cg) b2.get(0)).a();
            }
        } catch (Throwable th) {
            f.a(th, "Utils", "getDBConfigVersion");
        }
        return -1;
    }

    public static String e() {
        return Build.VERSION.RELEASE;
    }

    public static byte[] e(String str) {
        return a(Integer.parseInt(str), (byte[]) null);
    }

    public static boolean f() {
        return a(0, 1) == 1;
    }

    public static boolean f(Context context) {
        int i;
        if (Build.VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23) {
            for (String str : f) {
                if (context.checkCallingOrSelfPermission(str) != 0) {
                    return false;
                }
            }
            return true;
        }
        Application application = (Application) context;
        for (String str2 : f) {
            try {
                i = cs.b(application.getBaseContext(), "checkSelfPermission", str2);
            } catch (Throwable th) {
                i = 0;
            }
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static byte[] f(String str) {
        return b(Integer.parseInt(str), (byte[]) null);
    }

    public static GeoFenceManagerBase g(Context context) {
        GeoFenceManagerBase aVar;
        try {
            aVar = (GeoFenceManagerBase) au.a(context, f.a("loc"), "com.amap.api.fence.GeoFenceManagerWrapper", a.class, new Class[]{Context.class}, new Object[]{context});
        } catch (Throwable th) {
            aVar = new a(context);
        }
        return aVar == null ? new a(context) : aVar;
    }

    private static String g(String str) {
        byte[] bArr = null;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (Throwable th) {
            f.a(th, "Utils", "str2Base64");
        }
        return Base64.encodeToString(bArr, 0);
    }

    public static void g() {
        d.clear();
    }

    public static String h() {
        try {
            return o.a("S128DF1572465B890OE3F7A13167KLEI".getBytes("UTF-8")).substring(20);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String i() {
        if (!k()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath()).append(File.separator);
        sb.append("amap").append(File.separator);
        sb.append("openamaplocationsdk").append(File.separator);
        return sb.toString();
    }

    public static JSONObject j() {
        FileInputStream fileInputStream;
        DataInputStream dataInputStream;
        JSONObject jSONObject;
        DataInputStream dataInputStream2;
        Throwable th;
        JSONObject jSONObject2;
        try {
            try {
                jSONObject = null;
                FileInputStream fileInputStream2 = null;
                DataInputStream dataInputStream3 = null;
                try {
                    if (!k()) {
                        if (0 != 0) {
                            dataInputStream3.close();
                        }
                        if (0 != 0) {
                            fileInputStream2.close();
                        }
                    } else {
                        String l = l();
                        if (l == null) {
                            if (0 != 0) {
                                dataInputStream3.close();
                            }
                            if (0 != 0) {
                                fileInputStream2.close();
                            }
                        } else {
                            if (!TextUtils.isEmpty(l)) {
                                File file = new File(l);
                                if (!file.exists()) {
                                    if (0 != 0) {
                                        dataInputStream3.close();
                                    }
                                    if (0 != 0) {
                                        fileInputStream2.close();
                                    }
                                } else if (!file.canWrite()) {
                                    if (0 != 0) {
                                        dataInputStream3.close();
                                    }
                                    if (0 != 0) {
                                        fileInputStream2.close();
                                    }
                                } else {
                                    fileInputStream = new FileInputStream(file);
                                    try {
                                        dataInputStream2 = new DataInputStream(fileInputStream);
                                        try {
                                            jSONObject2 = new JSONObject();
                                        } catch (Throwable th2) {
                                            th = th2;
                                        }
                                        try {
                                            jSONObject2.put("altitude", dataInputStream2.readDouble());
                                            jSONObject2.put("pressure", dataInputStream2.readFloat());
                                            jSONObject2.put("sysTime", dataInputStream2.readLong());
                                            jSONObject = jSONObject2;
                                        } catch (Throwable th3) {
                                            th = th3;
                                            jSONObject = jSONObject2;
                                            th.printStackTrace();
                                            if (dataInputStream2 != null) {
                                                dataInputStream2.close();
                                            }
                                            if (fileInputStream != null) {
                                                fileInputStream.close();
                                            }
                                            return jSONObject;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        dataInputStream = null;
                                        if (dataInputStream != null) {
                                            try {
                                                dataInputStream.close();
                                            } catch (Throwable th5) {
                                                throw th;
                                            }
                                        }
                                        if (fileInputStream != null) {
                                            fileInputStream.close();
                                        }
                                        throw th;
                                    }
                                }
                            } else {
                                dataInputStream2 = null;
                                fileInputStream = null;
                            }
                            if (dataInputStream2 != null) {
                                dataInputStream2.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    dataInputStream = null;
                    fileInputStream = null;
                }
            } catch (Throwable th7) {
                th = th7;
            }
        } catch (Throwable th8) {
        }
        return jSONObject;
    }

    private static boolean k() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private static String l() {
        try {
        } catch (Throwable th) {
            f.a(th, "GPSInfoManager", "getFilePath");
        }
        if (a != null) {
            return a;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i()).append("a.dat");
        a = sb.toString();
        File parentFile = new File(a).getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        sb.delete(0, sb.length());
        return a;
    }
}

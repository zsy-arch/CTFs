package u.aly;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.hyphenate.util.HanziToPinyin;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.a;
import com.umeng.update.UpdateConfig;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: DeviceConfig.java */
/* loaded from: classes2.dex */
public class bl {
    protected static final String a = bl.class.getName();
    public static final String b = "";
    public static final String c = "2G/3G";
    public static final String d = "Wi-Fi";
    public static final int e = 8;
    private static final String f = "ro.miui.ui.version.name";

    public static String a(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e2) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            return "";
        }
    }

    public static boolean a(Context context, String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        }
        try {
            return ((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() == 0;
        } catch (Throwable th) {
            return false;
        }
    }

    public static String[] a(GL10 gl10) {
        try {
            return new String[]{gl10.glGetString(7936), gl10.glGetString(7937)};
        } catch (Throwable th) {
            return new String[0];
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000c A[Catch: Throwable -> 0x0072, TryCatch #0 {Throwable -> 0x0072, blocks: (B:3:0x0002, B:4:0x0006, B:6:0x000c, B:8:0x001e, B:10:0x002a, B:12:0x0030, B:16:0x0035, B:18:0x003e, B:19:0x0056, B:21:0x005c, B:22:0x0065), top: B:25:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String b() {
        /*
            r1 = 0
            r2 = 0
            java.util.Enumeration r3 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch: Throwable -> 0x0072
        L_0x0006:
            boolean r0 = r3.hasMoreElements()     // Catch: Throwable -> 0x0072
            if (r0 == 0) goto L_0x0073
            java.lang.Object r0 = r3.nextElement()     // Catch: Throwable -> 0x0072
            java.net.NetworkInterface r0 = (java.net.NetworkInterface) r0     // Catch: Throwable -> 0x0072
            java.lang.String r4 = "wlan0"
            java.lang.String r5 = r0.getName()     // Catch: Throwable -> 0x0072
            boolean r4 = r4.equals(r5)     // Catch: Throwable -> 0x0072
            if (r4 != 0) goto L_0x002a
            java.lang.String r4 = "eth0"
            java.lang.String r5 = r0.getName()     // Catch: Throwable -> 0x0072
            boolean r4 = r4.equals(r5)     // Catch: Throwable -> 0x0072
            if (r4 == 0) goto L_0x0006
        L_0x002a:
            byte[] r3 = r0.getHardwareAddress()     // Catch: Throwable -> 0x0072
            if (r3 == 0) goto L_0x0033
            int r0 = r3.length     // Catch: Throwable -> 0x0072
            if (r0 != 0) goto L_0x0035
        L_0x0033:
            r0 = r1
        L_0x0034:
            return r0
        L_0x0035:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0072
            r4.<init>()     // Catch: Throwable -> 0x0072
            int r5 = r3.length     // Catch: Throwable -> 0x0072
            r0 = r2
        L_0x003c:
            if (r0 >= r5) goto L_0x0056
            byte r2 = r3[r0]     // Catch: Throwable -> 0x0072
            java.lang.String r6 = "%02X:"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: Throwable -> 0x0072
            r8 = 0
            java.lang.Byte r2 = java.lang.Byte.valueOf(r2)     // Catch: Throwable -> 0x0072
            r7[r8] = r2     // Catch: Throwable -> 0x0072
            java.lang.String r2 = java.lang.String.format(r6, r7)     // Catch: Throwable -> 0x0072
            r4.append(r2)     // Catch: Throwable -> 0x0072
            int r0 = r0 + 1
            goto L_0x003c
        L_0x0056:
            int r0 = r4.length()     // Catch: Throwable -> 0x0072
            if (r0 <= 0) goto L_0x0065
            int r0 = r4.length()     // Catch: Throwable -> 0x0072
            int r0 = r0 + (-1)
            r4.deleteCharAt(r0)     // Catch: Throwable -> 0x0072
        L_0x0065:
            java.lang.String r0 = r4.toString()     // Catch: Throwable -> 0x0072
            java.util.Locale r2 = java.util.Locale.getDefault()     // Catch: Throwable -> 0x0072
            java.lang.String r0 = r0.toLowerCase(r2)     // Catch: Throwable -> 0x0072
            goto L_0x0034
        L_0x0072:
            r0 = move-exception
        L_0x0073:
            r0 = r1
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.bl.b():java.lang.String");
    }

    private static String c() {
        String a2;
        try {
            for (String str : new String[]{"/sys/class/net/wlan0/address", "/sys/class/net/eth0/address", "/sys/devices/virtual/net/wlan0/address"}) {
                try {
                    a2 = a(str);
                } catch (Throwable th) {
                }
                if (a2 != null) {
                    return a2;
                }
            }
        } catch (Throwable th2) {
        }
        return null;
    }

    private static String a(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        String str2 = null;
        try {
            FileReader fileReader = new FileReader(str);
            if (fileReader != null) {
                try {
                    bufferedReader = new BufferedReader(fileReader, 1024);
                    try {
                        str2 = bufferedReader.readLine();
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable th2) {
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable th3) {
                            }
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable th5) {
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable th6) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    bufferedReader = null;
                }
            }
        } catch (Throwable th8) {
        }
        return str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0044 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch: FileNotFoundException -> 0x0047
            java.lang.String r2 = "/proc/cpuinfo"
            r1.<init>(r2)     // Catch: FileNotFoundException -> 0x0047
            if (r1 == 0) goto L_0x001b
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: Throwable -> 0x002e, FileNotFoundException -> 0x0047
            r3 = 1024(0x400, float:1.435E-42)
            r2.<init>(r1, r3)     // Catch: Throwable -> 0x002e, FileNotFoundException -> 0x0047
            java.lang.String r0 = r2.readLine()     // Catch: Throwable -> 0x002e, FileNotFoundException -> 0x0047
            r2.close()     // Catch: Throwable -> 0x002e, FileNotFoundException -> 0x0037
            r1.close()     // Catch: Throwable -> 0x002e, FileNotFoundException -> 0x0037
        L_0x001b:
            if (r0 == 0) goto L_0x0044
            r1 = 58
            int r1 = r0.indexOf(r1)
            int r1 = r1 + 1
            java.lang.String r0 = r0.substring(r1)
            java.lang.String r0 = r0.trim()
        L_0x002d:
            return r0
        L_0x002e:
            r1 = move-exception
            java.lang.String r2 = u.aly.bl.a     // Catch: FileNotFoundException -> 0x0037
            java.lang.String r3 = "Could not read from file /proc/cpuinfo"
            u.aly.bo.e(r2, r3, r1)     // Catch: FileNotFoundException -> 0x0037
            goto L_0x001b
        L_0x0037:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x003b:
            java.lang.String r2 = u.aly.bl.a
            java.lang.String r3 = "Could not open file /proc/cpuinfo"
            u.aly.bo.e(r2, r3, r0)
            r0 = r1
            goto L_0x001b
        L_0x0044:
            java.lang.String r0 = ""
            goto L_0x002d
        L_0x0047:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L_0x003b
        */
        throw new UnsupportedOperationException("Method not decompiled: u.aly.bl.a():java.lang.String");
    }

    public static String c(Context context) {
        return (MobclickAgent.EScenarioType.E_UM_ANALYTICS_OEM.toValue() == AnalyticsConfig.getVerticalType(context) || MobclickAgent.EScenarioType.E_UM_GAME_OEM.toValue() == AnalyticsConfig.getVerticalType(context)) ? E(context) : D(context);
    }

    public static String d(Context context) {
        return bm.b(c(context));
    }

    public static String e(Context context) {
        if (f(context) == null) {
            return null;
        }
        int i = context.getResources().getConfiguration().mcc;
        int i2 = context.getResources().getConfiguration().mnc;
        if (i == 0) {
            return null;
        }
        String valueOf = String.valueOf(i2);
        if (i2 < 10) {
            valueOf = String.format("%02d", Integer.valueOf(i2));
        }
        return new StringBuffer().append(String.valueOf(i)).append(valueOf).toString();
    }

    public static String f(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return telephonyManager.getSubscriberId();
        }
        return null;
    }

    public static String g(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (a(context, "android.permission.READ_PHONE_STATE")) {
            return telephonyManager.getNetworkOperator();
        }
        return null;
    }

    public static String h(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (a(context, "android.permission.READ_PHONE_STATE") && telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
        } catch (Throwable th) {
        }
        return "";
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            return String.valueOf(displayMetrics.heightPixels) + "*" + String.valueOf(i);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String[] j(Context context) {
        String[] strArr = new String[2];
        strArr[0] = "";
        strArr[1] = "";
        if (!a(context, UpdateConfig.g)) {
            strArr[0] = "";
            return strArr;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            strArr[0] = "";
            return strArr;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED) {
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo2 != null && networkInfo2.getState() == NetworkInfo.State.CONNECTED) {
                strArr[0] = "2G/3G";
                strArr[1] = networkInfo2.getSubtypeName();
                return strArr;
            }
            return strArr;
        }
        strArr[0] = "Wi-Fi";
        return strArr;
    }

    public static boolean k(Context context) {
        return "Wi-Fi".equals(j(context)[0]);
    }

    public static boolean l(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            if (!(!a(context, UpdateConfig.g) || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null)) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
        } catch (Throwable th) {
        }
        return false;
    }

    public static int m(Context context) {
        try {
            Calendar instance = Calendar.getInstance(B(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Throwable th) {
            bo.c(a, "error in getTimeZone", th);
        }
        return 8;
    }

    public static boolean n(Context context) {
        String e2 = x.a(context).b().e("");
        if (!TextUtils.isEmpty(e2)) {
            return e2.equals("cn");
        }
        if (f(context) == null) {
            String str = o(context)[0];
            if (!TextUtils.isEmpty(str) && str.equalsIgnoreCase("cn")) {
                return true;
            }
        } else {
            int i = context.getResources().getConfiguration().mcc;
            if (i == 460 || i == 461) {
                return true;
            }
            if (i == 0) {
                String str2 = o(context)[0];
                if (!TextUtils.isEmpty(str2) && str2.equalsIgnoreCase("cn")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] o(Context context) {
        String[] strArr = new String[2];
        try {
            Locale B = B(context);
            if (B != null) {
                strArr[0] = B.getCountry();
                strArr[1] = B.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = f.c;
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = f.c;
            }
        } catch (Throwable th) {
            bo.e(a, "error in getLocaleInfo", th);
        }
        return strArr;
    }

    private static Locale B(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        } catch (Throwable th) {
            bo.c(a, "fail to read user config locale");
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    public static String p(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString(f.d);
                if (string != null) {
                    return string.trim();
                }
                bo.c(a, "getAppkey failed. the applicationinfo is null!");
            }
        } catch (Throwable th) {
            bo.e(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", th);
        }
        return null;
    }

    public static String q(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return C(context);
        }
        if (Build.VERSION.SDK_INT == 23) {
            String b2 = b();
            if (!TextUtils.isEmpty(b2)) {
                return b2;
            }
            if (a.e) {
                return c();
            }
            return C(context);
        }
        String b3 = b();
        if (TextUtils.isEmpty(b3)) {
            return C(context);
        }
        return b3;
    }

    private static String C(Context context) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                return wifiManager.getConnectionInfo().getMacAddress();
            }
            return "";
        } catch (Throwable th) {
            return "";
        }
    }

    public static int[] r(Context context) {
        int i;
        int i2;
        int i3;
        int i4;
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            if ((context.getApplicationInfo().flags & 8192) == 0) {
                i2 = a(displayMetrics, "noncompatWidthPixels");
                i = a(displayMetrics, "noncompatHeightPixels");
            } else {
                i = -1;
                i2 = -1;
            }
            if (i2 == -1 || i == -1) {
                i3 = displayMetrics.widthPixels;
                i4 = displayMetrics.heightPixels;
            } else {
                i3 = i2;
                i4 = i;
            }
            int[] iArr = new int[2];
            if (i3 > i4) {
                iArr[0] = i4;
                iArr[1] = i3;
                return iArr;
            }
            iArr[0] = i3;
            iArr[1] = i4;
            return iArr;
        } catch (Throwable th) {
            return null;
        }
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Throwable th) {
            return -1;
        }
    }

    public static String s(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null)) {
                String obj2 = obj.toString();
                if (obj2 != null) {
                    return obj2;
                }
            }
            return f.c;
        } catch (Throwable th) {
            return f.c;
        }
    }

    public static String t(Context context) {
        return context.getPackageName();
    }

    public static String u(Context context) {
        try {
            return a(MessageDigest.getInstance("MD5").digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(context.getPackageManager().getPackageInfo(t(context), 64).signatures[0].toByteArray()))).getEncoded()));
        } catch (Throwable th) {
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase(Locale.getDefault()));
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public static String v(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static String w(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.loadLabel(context.getPackageManager()).toString();
        } catch (Throwable th) {
            return null;
        }
    }

    private static String D(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            String F = F(context);
            if (!TextUtils.isEmpty(F)) {
                return F;
            }
            String C = C(context);
            if (!TextUtils.isEmpty(C)) {
                return C;
            }
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (TextUtils.isEmpty(string)) {
                return d();
            }
            return string;
        } else if (Build.VERSION.SDK_INT == 23) {
            String F2 = F(context);
            if (!TextUtils.isEmpty(F2)) {
                return F2;
            }
            String b2 = b();
            if (TextUtils.isEmpty(b2)) {
                if (a.e) {
                    b2 = c();
                } else {
                    b2 = C(context);
                }
            }
            if (!TextUtils.isEmpty(b2)) {
                return b2;
            }
            String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (TextUtils.isEmpty(string2)) {
                return d();
            }
            return string2;
        } else {
            String F3 = F(context);
            if (!TextUtils.isEmpty(F3)) {
                return F3;
            }
            String d2 = d();
            if (!TextUtils.isEmpty(d2)) {
                return d2;
            }
            String string3 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string3)) {
                return string3;
            }
            String b3 = b();
            if (TextUtils.isEmpty(b3)) {
                return C(context);
            }
            return b3;
        }
    }

    private static String E(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String C = C(context);
            if (!TextUtils.isEmpty(C)) {
                return C;
            }
            String d2 = d();
            if (TextUtils.isEmpty(d2)) {
                return F(context);
            }
            return d2;
        } else if (Build.VERSION.SDK_INT == 23) {
            String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string2)) {
                return string2;
            }
            String b2 = b();
            if (TextUtils.isEmpty(b2)) {
                if (a.e) {
                    b2 = c();
                } else {
                    b2 = C(context);
                }
            }
            if (!TextUtils.isEmpty(b2)) {
                return b2;
            }
            String d3 = d();
            if (TextUtils.isEmpty(d3)) {
                return F(context);
            }
            return d3;
        } else {
            String string3 = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (!TextUtils.isEmpty(string3)) {
                return string3;
            }
            String d4 = d();
            if (!TextUtils.isEmpty(d4)) {
                return d4;
            }
            String F = F(context);
            if (!TextUtils.isEmpty(F)) {
                return F;
            }
            String b3 = b();
            if (TextUtils.isEmpty(b3)) {
                return C(context);
            }
            return b3;
        }
    }

    private static String F(Context context) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        try {
            if (a(context, "android.permission.READ_PHONE_STATE")) {
                str = telephonyManager.getDeviceId();
            } else {
                str = "";
            }
            return str;
        } catch (Throwable th) {
            return "";
        }
    }

    private static String d() {
        if (Build.VERSION.SDK_INT >= 9) {
            return Build.SERIAL;
        }
        return "";
    }

    public static String x(Context context) {
        Properties e2 = e();
        try {
            String property = e2.getProperty(f);
            if (!TextUtils.isEmpty(property)) {
                return "MIUI";
            }
            if (f()) {
                return "Flyme";
            }
            if (!TextUtils.isEmpty(a(e2))) {
                return "YunOS";
            }
            return property;
        } catch (Throwable th) {
            return null;
        }
    }

    public static String y(Context context) {
        Properties e2 = e();
        try {
            String property = e2.getProperty(f);
            if (!TextUtils.isEmpty(property)) {
                return property;
            }
            if (f()) {
                try {
                    return b(e2);
                } catch (Throwable th) {
                    return property;
                }
            } else {
                try {
                    return a(e2);
                } catch (Throwable th2) {
                    return property;
                }
            }
        } catch (Throwable th3) {
            return null;
        }
    }

    private static String a(Properties properties) {
        String property = properties.getProperty("ro.yunos.version");
        if (!TextUtils.isEmpty(property)) {
            return property;
        }
        return null;
    }

    private static String b(Properties properties) {
        try {
            String lowerCase = properties.getProperty("ro.build.display.id").toLowerCase(Locale.getDefault());
            if (lowerCase.contains("flyme os")) {
                return lowerCase.split(HanziToPinyin.Token.SEPARATOR)[2];
            }
        } catch (Throwable th) {
        }
        return null;
    }

    private static Properties e() {
        Throwable th;
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
            try {
                properties.load(fileInputStream);
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                    }
                }
            } catch (Throwable th3) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th4) {
                    }
                }
                return properties;
            }
        } catch (Throwable th5) {
            th = th5;
        }
        return properties;
    }

    private static boolean f() {
        try {
            Build.class.getMethod("hasSmartBar", new Class[0]);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static String z(Context context) {
        if (context == null) {
            return "Phone";
        }
        if ((context.getResources().getConfiguration().screenLayout & 15) >= 3) {
            return "Tablet";
        }
        return "Phone";
    }

    public static String A(Context context) {
        String str;
        if (context == null) {
            return null;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null || !a(context, "android.permission.READ_PHONE_STATE")) {
                str = null;
            } else {
                str = telephonyManager.getDeviceId();
            }
            try {
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
                String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
                if (!TextUtils.isEmpty(string) || Build.VERSION.SDK_INT < 9) {
                    return string;
                }
                return Build.SERIAL;
            } catch (Throwable th) {
                return str;
            }
        } catch (Throwable th2) {
            return null;
        }
    }
}

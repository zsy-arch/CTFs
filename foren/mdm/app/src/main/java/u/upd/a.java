package u.upd;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.umeng.update.UpdateConfig;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: DeviceConfig.java */
/* loaded from: classes2.dex */
public class a {
    protected static final String a = a.class.getName();
    public static final String b = "";
    public static final String c = "2G/3G";
    public static final String d = "Wi-Fi";
    public static final int e = 8;

    public static boolean a(String str, Context context) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }

    public static boolean a(Context context) {
        return context.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
    }

    public static boolean b(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static String c(Context context) {
        try {
            return String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e2) {
            return "";
        }
    }

    public static String d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            return "";
        }
    }

    public static boolean a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    public static String e(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "");
    }

    public static String[] a(GL10 gl10) {
        try {
            return new String[]{gl10.glGetString(7936), gl10.glGetString(7937)};
        } catch (Exception e2) {
            b.b(a, "Could not read gpu infor:", e2);
            return new String[0];
        }
    }

    public static String a() {
        String str = null;
        try {
            FileReader fileReader = new FileReader("/proc/cpuinfo");
            if (fileReader != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(fileReader, 1024);
                    str = bufferedReader.readLine();
                    bufferedReader.close();
                    fileReader.close();
                } catch (IOException e2) {
                    b.b(a, "Could not read from file /proc/cpuinfo", e2);
                }
            }
        } catch (FileNotFoundException e3) {
            b.b(a, "Could not open file /proc/cpuinfo", e3);
        }
        if (str != null) {
            return str.substring(str.indexOf(58) + 1).trim();
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String f(android.content.Context r4) {
        /*
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r4.getSystemService(r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 != 0) goto L_0x0011
            java.lang.String r1 = u.upd.a.a
            java.lang.String r2 = "No IMEI."
            u.upd.b.e(r1, r2)
        L_0x0011:
            java.lang.String r1 = ""
            java.lang.String r2 = "android.permission.READ_PHONE_STATE"
            boolean r2 = a(r4, r2)     // Catch: Exception -> 0x005c
            if (r2 == 0) goto L_0x0064
            java.lang.String r0 = r0.getDeviceId()     // Catch: Exception -> 0x005c
        L_0x001f:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x005b
            java.lang.String r0 = u.upd.a.a
            java.lang.String r1 = "No IMEI."
            u.upd.b.e(r0, r1)
            java.lang.String r0 = p(r4)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L_0x005b
            java.lang.String r0 = u.upd.a.a
            java.lang.String r1 = "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead."
            u.upd.b.e(r0, r1)
            android.content.ContentResolver r0 = r4.getContentResolver()
            java.lang.String r1 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r1)
            java.lang.String r1 = u.upd.a.a
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getDeviceId: Secure.ANDROID_ID: "
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r2 = r2.toString()
            u.upd.b.a(r1, r2)
        L_0x005b:
            return r0
        L_0x005c:
            r0 = move-exception
            java.lang.String r2 = u.upd.a.a
            java.lang.String r3 = "No IMEI."
            u.upd.b.e(r2, r3, r0)
        L_0x0064:
            r0 = r1
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: u.upd.a.f(android.content.Context):java.lang.String");
    }

    public static String g(Context context) {
        return n.b(f(context));
    }

    public static String h(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return "";
            }
            return telephonyManager.getNetworkOperatorName();
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String i(Context context) {
        try {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            return String.valueOf(String.valueOf(displayMetrics.heightPixels)) + "*" + String.valueOf(displayMetrics.widthPixels);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String[] j(Context context) {
        String[] strArr = new String[2];
        strArr[0] = "";
        strArr[1] = "";
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (context.getPackageManager().checkPermission(UpdateConfig.g, context.getPackageName()) != 0) {
            strArr[0] = "";
            return strArr;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            strArr[0] = "";
            return strArr;
        } else if (connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
            strArr[0] = "Wi-Fi";
            return strArr;
        } else {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                strArr[0] = "2G/3G";
                strArr[1] = networkInfo.getSubtypeName();
                return strArr;
            }
            return strArr;
        }
    }

    public static boolean k(Context context) {
        return "Wi-Fi".equals(j(context)[0]);
    }

    public static boolean l(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception e2) {
            return true;
        }
    }

    public static boolean b() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static int m(Context context) {
        try {
            Calendar instance = Calendar.getInstance(x(context));
            if (instance != null) {
                return instance.getTimeZone().getRawOffset() / 3600000;
            }
        } catch (Exception e2) {
            b.a(a, "error in getTimeZone", e2);
        }
        return 8;
    }

    public static String[] n(Context context) {
        String[] strArr = new String[2];
        try {
            Locale x = x(context);
            if (x != null) {
                strArr[0] = x.getCountry();
                strArr[1] = x.getLanguage();
            }
            if (TextUtils.isEmpty(strArr[0])) {
                strArr[0] = f.c;
            }
            if (TextUtils.isEmpty(strArr[1])) {
                strArr[1] = f.c;
            }
        } catch (Exception e2) {
            b.b(a, "error in getLocaleInfo", e2);
        }
        return strArr;
    }

    private static Locale x(Context context) {
        Locale locale = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setToDefaults();
            Settings.System.getConfiguration(context.getContentResolver(), configuration);
            if (configuration != null) {
                locale = configuration.locale;
            }
        } catch (Exception e2) {
            b.b(a, "fail to read user config locale");
        }
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }

    public static String o(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString(f.d);
                if (string != null) {
                    return string.trim();
                }
                b.b(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
            }
        } catch (Exception e2) {
            b.b(a, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", e2);
        }
        return null;
    }

    public static String p(Context context) {
        WifiManager wifiManager;
        try {
            wifiManager = (WifiManager) context.getSystemService("wifi");
        } catch (Exception e2) {
            b.e(a, "Could not get mac address." + e2.toString());
        }
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return wifiManager.getConnectionInfo().getMacAddress();
        }
        b.e(a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
        return "";
    }

    public static String q(Context context) {
        int[] r = r(context);
        if (r == null) {
            return f.c;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(r[0]);
        stringBuffer.append("*");
        stringBuffer.append(r[1]);
        return stringBuffer.toString();
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
        } catch (Exception e2) {
            b.b(a, "read resolution fail", e2);
            return null;
        }
    }

    private static int a(Object obj, String str) {
        try {
            Field declaredField = DisplayMetrics.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.getInt(obj);
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static String s(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e2) {
            b.a(a, "read carrier fail", e2);
            return f.c;
        }
    }

    public static String a(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(date);
    }

    public static String c() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(new Date());
    }

    public static Date a(String str) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).parse(str);
        } catch (Exception e2) {
            return null;
        }
    }

    public static int a(Date date, Date date2) {
        if (!date.after(date2)) {
            date2 = date;
            date = date2;
        }
        return (int) ((date.getTime() - date2.getTime()) / 1000);
    }

    public static String t(Context context) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get("UMENG_CHANNEL")) == null)) {
                String obj2 = obj.toString();
                if (obj2 != null) {
                    return obj2;
                }
                b.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
                return f.c;
            }
        } catch (Exception e2) {
            b.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
            e2.printStackTrace();
        }
        return f.c;
    }

    public static String u(Context context) {
        return context.getPackageName();
    }

    public static String v(Context context) {
        return context.getPackageManager().getApplicationLabel(context.getApplicationInfo()).toString();
    }

    public static boolean w(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0;
        } catch (Exception e2) {
            return false;
        }
    }
}

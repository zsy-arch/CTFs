package com.amap.api.col;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Xml;
import android.view.WindowManager;
import com.alipay.sdk.util.h;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.io.FileInputStream;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: DeviceInfo.java */
/* loaded from: classes.dex */
public class ge {
    private static String a = "";
    private static boolean b = false;
    private static String c = null;
    private static String d = "";
    private static String e = "";
    private static String f = "";
    private static String g = "";
    private static String h = "";
    private static String i = "";

    public static String a() {
        return c;
    }

    public static String a(Context context) {
        try {
            return v(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return y(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static int c(Context context) {
        try {
            return z(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static int d(Context context) {
        try {
            return w(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static String e(Context context) {
        try {
            return u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static void b() {
        try {
            if (Build.VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", Integer.TYPE).invoke(null, 40964);
            }
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "setTraficTag");
        }
    }

    private static String t(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        FileInputStream fileInputStream2 = null;
        try {
            if (gk.a(context, "android.permission.READ_EXTERNAL_STORAGE") && "mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                XmlPullParser newPullParser = Xml.newPullParser();
                fileInputStream = new FileInputStream(file);
                try {
                    newPullParser.setInput(fileInputStream, "utf-8");
                    boolean z = false;
                    for (int eventType = newPullParser.getEventType(); 1 != eventType; eventType = newPullParser.next()) {
                        switch (eventType) {
                            case 2:
                                if (newPullParser.getAttributeCount() > 0) {
                                    int attributeCount = newPullParser.getAttributeCount();
                                    for (int i2 = 0; i2 < attributeCount; i2++) {
                                        String attributeValue = newPullParser.getAttributeValue(i2);
                                        if ("UTDID2".equals(attributeValue) || "UTDID".equals(attributeValue)) {
                                            z = true;
                                        }
                                    }
                                    break;
                                } else {
                                    break;
                                }
                                break;
                            case 3:
                                z = false;
                                break;
                            case 4:
                                if (!z) {
                                    break;
                                } else {
                                    String text = newPullParser.getText();
                                    if (fileInputStream == null) {
                                        return text;
                                    }
                                    try {
                                        fileInputStream.close();
                                        return text;
                                    } catch (Throwable th2) {
                                        return text;
                                    }
                                }
                        }
                    }
                    fileInputStream2 = fileInputStream;
                } catch (Throwable th3) {
                    th = th3;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Throwable th4) {
                        }
                    }
                    throw th;
                }
            }
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Throwable th5) {
                }
            }
        } catch (Throwable th6) {
            fileInputStream = null;
            th = th6;
        }
        return "";
    }

    public static String f(Context context) {
        if (a != null && !"".equals(a)) {
            return a;
        }
        if (a(context, "android.permission.WRITE_SETTINGS")) {
            a = Settings.System.getString(context.getContentResolver(), "mqBRboGZkQPcAkyk");
        }
        if (a != null && !"".equals(a)) {
            return a;
        }
        try {
            a = t(context);
        } catch (Throwable th) {
        }
        return a == null ? "" : a;
    }

    public static String c() {
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        try {
            if (Build.VERSION.SDK_INT >= 9) {
                e = Build.SERIAL;
            }
        } catch (Throwable th) {
        }
        return e == null ? "" : e;
    }

    private static boolean a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String g(Context context) {
        String str;
        WifiManager wifiManager;
        if (context == null) {
            return "";
        }
        try {
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getWifiMacs");
        }
        if (!a(context, "android.permission.ACCESS_WIFI_STATE") || (wifiManager = (WifiManager) context.getSystemService("wifi")) == null) {
            return "";
        }
        if (wifiManager.isWifiEnabled()) {
            str = wifiManager.getConnectionInfo().getBSSID();
            return str;
        }
        str = "";
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String h(Context context) {
        StringBuilder sb = new StringBuilder();
        if (context != null) {
            try {
            } catch (Throwable th) {
                go.a(th, "DeviceInfo", "getWifiMacs");
            }
            if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                if (wifiManager == null) {
                    return "";
                }
                if (wifiManager.isWifiEnabled()) {
                    List<ScanResult> scanResults = wifiManager.getScanResults();
                    if (scanResults == null || scanResults.size() == 0) {
                        return sb.toString();
                    }
                    List<ScanResult> a2 = a(scanResults);
                    boolean z = true;
                    for (int i2 = 0; i2 < a2.size() && i2 < 7; i2++) {
                        ScanResult scanResult = a2.get(i2);
                        if (z) {
                            z = false;
                        } else {
                            sb.append(h.b);
                        }
                        sb.append(scanResult.BSSID);
                    }
                }
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public static String i(Context context) {
        try {
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getDeviceMac");
        }
        if (f != null && !"".equals(f)) {
            return f;
        }
        if (!a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return f;
        }
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        f = wifiManager.getConnectionInfo().getMacAddress();
        if ("02:00:00:00:00:00".equals(f) || "00:00:00:00:00:00".equals(f)) {
            f = d();
        }
        return f;
    }

    private static String d() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] bArr = null;
                    if (Build.VERSION.SDK_INT >= 9) {
                        bArr = networkInterface.getHardwareAddress();
                    }
                    if (bArr == null) {
                        return "";
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : bArr) {
                        String upperCase = Integer.toHexString(b2 & 255).toUpperCase();
                        if (upperCase.length() == 1) {
                            sb.append("0");
                        }
                        sb.append(upperCase).append(":");
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
        } catch (Exception e2) {
            go.a(e2, "DeviceInfo", "getMacAddr");
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] j(Context context) {
        try {
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "cellInfo");
        }
        if (!a(context, "android.permission.READ_PHONE_STATE") || !a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            return new String[]{"", ""};
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return new String[]{"", ""};
        }
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation instanceof GsmCellLocation) {
            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
            return new String[]{gsmCellLocation.getLac() + "||" + gsmCellLocation.getCid(), "gsm"};
        }
        if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            int systemId = cdmaCellLocation.getSystemId();
            int networkId = cdmaCellLocation.getNetworkId();
            int baseStationId = cdmaCellLocation.getBaseStationId();
            if (systemId < 0 || networkId < 0 || baseStationId < 0) {
            }
            return new String[]{systemId + "||" + networkId + "||" + baseStationId, "cdma"};
        }
        return new String[]{"", ""};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String k(Context context) {
        TelephonyManager A;
        try {
            if (a(context, "android.permission.READ_PHONE_STATE") && (A = A(context)) != null) {
                String networkOperator = A.getNetworkOperator();
                if (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 3) {
                    return "";
                }
                return networkOperator.substring(3);
            }
            return "";
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getMNC");
            return "";
        }
    }

    public static int l(Context context) {
        try {
            return z(context);
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getNetWorkType");
            return -1;
        }
    }

    public static int m(Context context) {
        try {
            return w(context);
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getActiveNetWorkType");
            return -1;
        }
    }

    public static NetworkInfo n(Context context) {
        ConnectivityManager x;
        if (a(context, UpdateConfig.g) && (x = x(context)) != null) {
            return x.getActiveNetworkInfo();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String o(Context context) {
        try {
            NetworkInfo n = n(context);
            if (n == null) {
                return null;
            }
            return n.getExtraInfo();
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getNetworkExtraInfo");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String p(Context context) {
        try {
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getReslution");
        }
        if (g != null && !"".equals(g)) {
            return g;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return "";
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        g = i3 > i2 ? i2 + "*" + i3 : i3 + "*" + i2;
        return g;
    }

    public static String q(Context context) {
        try {
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getDeviceID");
        }
        if (h != null && !"".equals(h)) {
            return h;
        }
        if (!a(context, "android.permission.READ_PHONE_STATE")) {
            return h;
        }
        TelephonyManager A = A(context);
        if (A == null) {
            return "";
        }
        h = A.getDeviceId();
        if (h == null) {
            h = "";
        }
        return h;
    }

    public static String r(Context context) {
        try {
            return u(context);
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getSubscriberId");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String s(Context context) {
        try {
            return v(context);
        } catch (Throwable th) {
            go.a(th, "DeviceInfo", "getNetworkOperatorName");
            return "";
        }
    }

    private static String u(Context context) {
        if (i != null && !"".equals(i)) {
            return i;
        }
        if (!a(context, "android.permission.READ_PHONE_STATE")) {
            return i;
        }
        TelephonyManager A = A(context);
        if (A == null) {
            return "";
        }
        i = A.getSubscriberId();
        if (i == null) {
            i = "";
        }
        return i;
    }

    private static String v(Context context) {
        if (!a(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        TelephonyManager A = A(context);
        if (A == null) {
            return "";
        }
        String simOperatorName = A.getSimOperatorName();
        if (TextUtils.isEmpty(simOperatorName)) {
            return A.getNetworkOperatorName();
        }
        return simOperatorName;
    }

    private static int w(Context context) {
        ConnectivityManager x;
        NetworkInfo activeNetworkInfo;
        if (context == null || !a(context, UpdateConfig.g) || (x = x(context)) == null || (activeNetworkInfo = x.getActiveNetworkInfo()) == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    private static ConnectivityManager x(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static String y(Context context) {
        String r = r(context);
        return (r == null || r.length() < 5) ? "" : r.substring(3, 5);
    }

    private static int z(Context context) {
        TelephonyManager A;
        if (a(context, "android.permission.READ_PHONE_STATE") && (A = A(context)) != null) {
            return A.getNetworkType();
        }
        return -1;
    }

    private static TelephonyManager A(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    private static List<ScanResult> a(List<ScanResult> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size - 1; i2++) {
            for (int i3 = 1; i3 < size - i2; i3++) {
                if (list.get(i3 - 1).level > list.get(i3).level) {
                    list.set(i3 - 1, list.get(i3));
                    list.set(i3, list.get(i3 - 1));
                }
            }
        }
        return list;
    }
}

package com.loc;

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
import android.view.WindowManager;
import com.alipay.sdk.util.h;
import com.umeng.update.UpdateConfig;
import java.io.File;
import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* compiled from: DeviceInfo.java */
/* loaded from: classes2.dex */
public final class n {
    private static String a = "";
    private static boolean b = false;
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static String f = "";

    /* compiled from: DeviceInfo.java */
    /* loaded from: classes2.dex */
    static class a extends DefaultHandler {
        a() {
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public final void characters(char[] cArr, int i, int i2) throws SAXException {
            if (n.b) {
                String unused = n.a = new String(cArr, i, i2);
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public final void endElement(String str, String str2, String str3) throws SAXException {
            boolean unused = n.b = false;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public final void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str2.equals("string") && "UTDID".equals(attributes.getValue("name"))) {
                boolean unused = n.b = true;
            }
        }
    }

    public static String a(Context context) {
        try {
            return u(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private static List<ScanResult> a(List<ScanResult> list) {
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            for (int i2 = 1; i2 < size - i; i2++) {
                if (list.get(i2 - 1).level > list.get(i2).level) {
                    list.set(i2 - 1, list.get(i2));
                    list.set(i2, list.get(i2 - 1));
                }
            }
        }
        return list;
    }

    public static void a() {
        try {
            if (Build.VERSION.SDK_INT > 14) {
                TrafficStats.class.getDeclaredMethod("setThreadStatsTag", Integer.TYPE).invoke(null, 40964);
            }
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "setTraficTag");
        }
    }

    private static boolean a(Context context, String str) {
        return context != null && context.checkCallingOrSelfPermission(str) == 0;
    }

    public static String b(Context context) {
        try {
            String r = r(context);
            return (r == null || r.length() < 5) ? "" : r.substring(3, 5);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static int c(Context context) {
        try {
            return x(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    private static String c() {
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
            w.a(e2, "DeviceInfo", "getMacAddr");
        }
        return "";
    }

    public static int d(Context context) {
        try {
            return v(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static String e(Context context) {
        try {
            return t(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String f(Context context) {
        try {
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getUTDID");
        }
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
            if ("mounted".equals(Environment.getExternalStorageState())) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.UTSystemConfig/Global/Alvin2.xml");
                if (file.exists()) {
                    SAXParserFactory.newInstance().newSAXParser().parse(file, new a());
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (Throwable th2) {
            w.a(th2, "DeviceInfo", "getUTDID");
        }
        return a == null ? "" : a;
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
            w.a(th, "DeviceInfo", "getWifiMacs");
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
                w.a(th, "DeviceInfo", "getWifiMacs");
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
                    for (int i = 0; i < a2.size() && i < 7; i++) {
                        ScanResult scanResult = a2.get(i);
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
            w.a(th, "DeviceInfo", "getDeviceMac");
        }
        if ((c == null || "".equals(c)) && a(context, "android.permission.ACCESS_WIFI_STATE")) {
            WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
            if (wifiManager == null) {
                return "";
            }
            c = wifiManager.getConnectionInfo().getMacAddress();
            if ("02:00:00:00:00:00".equals(c) || "00:00:00:00:00:00".equals(c)) {
                c = c();
            }
            return c;
        }
        return c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] j(Context context) {
        try {
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "cellInfo");
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
            return new String[]{systemId + "||" + cdmaCellLocation.getNetworkId() + "||" + cdmaCellLocation.getBaseStationId(), "cdma"};
        }
        return new String[]{"", ""};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String k(Context context) {
        TelephonyManager y;
        try {
            if (a(context, "android.permission.READ_PHONE_STATE") && (y = y(context)) != null) {
                String networkOperator = y.getNetworkOperator();
                return (TextUtils.isEmpty(networkOperator) || networkOperator.length() < 3) ? "" : networkOperator.substring(3);
            }
            return "";
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getMNC");
            return "";
        }
    }

    public static int l(Context context) {
        try {
            return x(context);
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getNetWorkType");
            return -1;
        }
    }

    public static int m(Context context) {
        try {
            return v(context);
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getActiveNetWorkType");
            return -1;
        }
    }

    public static NetworkInfo n(Context context) {
        ConnectivityManager w;
        if (a(context, UpdateConfig.g) && (w = w(context)) != null) {
            return w.getActiveNetworkInfo();
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
            w.a(th, "DeviceInfo", "getNetworkExtraInfo");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String p(Context context) {
        try {
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getReslution");
        }
        if (d != null && !"".equals(d)) {
            return d;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null) {
            return "";
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        d = i2 > i ? i + "*" + i2 : i2 + "*" + i;
        return d;
    }

    public static String q(Context context) {
        try {
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getDeviceID");
        }
        if ((e == null || "".equals(e)) && a(context, "android.permission.READ_PHONE_STATE")) {
            TelephonyManager y = y(context);
            if (y == null) {
                return "";
            }
            String deviceId = y.getDeviceId();
            e = deviceId;
            if (deviceId == null) {
                e = "";
            }
            return e;
        }
        return e;
    }

    public static String r(Context context) {
        try {
            return t(context);
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getSubscriberId");
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String s(Context context) {
        try {
            return u(context);
        } catch (Throwable th) {
            w.a(th, "DeviceInfo", "getNetworkOperatorName");
            return "";
        }
    }

    private static String t(Context context) {
        if ((f == null || "".equals(f)) && a(context, "android.permission.READ_PHONE_STATE")) {
            TelephonyManager y = y(context);
            if (y == null) {
                return "";
            }
            String subscriberId = y.getSubscriberId();
            f = subscriberId;
            if (subscriberId == null) {
                f = "";
            }
            return f;
        }
        return f;
    }

    private static String u(Context context) {
        if (!a(context, "android.permission.READ_PHONE_STATE")) {
            return null;
        }
        TelephonyManager y = y(context);
        if (y == null) {
            return "";
        }
        String simOperatorName = y.getSimOperatorName();
        return TextUtils.isEmpty(simOperatorName) ? y.getNetworkOperatorName() : simOperatorName;
    }

    private static int v(Context context) {
        ConnectivityManager w;
        NetworkInfo activeNetworkInfo;
        if (context == null || !a(context, UpdateConfig.g) || (w = w(context)) == null || (activeNetworkInfo = w.getActiveNetworkInfo()) == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    private static ConnectivityManager w(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    private static int x(Context context) {
        TelephonyManager y;
        if (a(context, "android.permission.READ_PHONE_STATE") && (y = y(context)) != null) {
            return y.getNetworkType();
        }
        return -1;
    }

    private static TelephonyManager y(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }
}

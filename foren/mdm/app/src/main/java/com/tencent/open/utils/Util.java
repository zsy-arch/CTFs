package com.tencent.open.utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.j;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import u.aly.dc;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class Util {
    private static String f;
    private static String a = "";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static int e = -1;
    private static String g = "0123456789ABCDEF";

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str == null) {
            return bundle;
        }
        try {
            for (String str2 : str.split(a.b)) {
                String[] split = str2.split("=");
                if (split.length == 2) {
                    bundle.putString(URLDecoder.decode(split[0]), URLDecoder.decode(split[1]));
                }
            }
            return bundle;
        } catch (Exception e2) {
            return null;
        }
    }

    public static JSONObject decodeUrlToJson(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (str != null) {
            for (String str2 : str.split(a.b)) {
                String[] split = str2.split("=");
                if (split.length == 2) {
                    try {
                        try {
                            split[0] = URLDecoder.decode(split[0]);
                            split[1] = URLDecoder.decode(split[1]);
                        } catch (JSONException e2) {
                            f.e("openSDK_LOG.Util", "decodeUrlToJson has exception: " + e2.getMessage());
                        }
                    } catch (Exception e3) {
                    }
                    jSONObject.put(split[0], split[1]);
                }
            }
        }
        return jSONObject;
    }

    public static Bundle parseUrl(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (MalformedURLException e2) {
            return new Bundle();
        }
    }

    public static JSONObject parseUrlToJson(String str) {
        try {
            URL url = new URL(str.replace("auth://", "http://"));
            JSONObject decodeUrlToJson = decodeUrlToJson(null, url.getQuery());
            decodeUrlToJson(decodeUrlToJson, url.getRef());
            return decodeUrlToJson;
        } catch (MalformedURLException e2) {
            return new JSONObject();
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public static class Statistic {
        public long reqSize;
        public String response;
        public long rspSize;

        public Statistic(String str, int i) {
            this.response = str;
            this.reqSize = i;
            if (this.response != null) {
                this.rspSize = this.response.length();
            }
        }
    }

    public static JSONObject parseJson(String str) throws JSONException {
        if (str.equals("false")) {
            str = "{value : false}";
        }
        if (str.equals("true")) {
            str = "{value : true}";
        }
        if (str.contains("allback(")) {
            str = str.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
        }
        if (str.contains("online[0]=")) {
            str = "{online:" + str.charAt(str.length() - 2) + h.d;
        }
        return new JSONObject(str);
    }

    public static void showAlert(Context context, String str, String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(str);
        builder.setMessage(str2);
        builder.create().show();
    }

    public static String getUserIp() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces != null && networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress()) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e2) {
            f.a("openSDK_LOG.Util", "getUserIp SocketException ", e2);
        }
        return "";
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private static boolean a(Context context) {
        Signature[] signatureArr;
        boolean z = false;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(TbsConfig.APP_QB, 64);
            String str = packageInfo.versionName;
            if (SystemUtils.compareVersion(str, "4.3") >= 0 && !str.startsWith("4.4") && (signatureArr = packageInfo.signatures) != null) {
                try {
                    MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(signatureArr[0].toByteArray());
                    String hexString = toHexString(instance.digest());
                    instance.reset();
                    if (hexString.equals("d8391a394d4a179e6fe7bdb8a301258b")) {
                        z = true;
                    }
                } catch (NoSuchAlgorithmException e2) {
                    f.e("openSDK_LOG.Util", "isQQBrowerAvailable has exception: " + e2.getMessage());
                }
            }
        } catch (PackageManager.NameNotFoundException e3) {
        }
        return z;
    }

    public static boolean openBrowser(Context context, String str) {
        boolean z;
        try {
            z = a(context);
        } catch (Exception e2) {
            z = false;
        }
        try {
            if (z) {
                a(context, TbsConfig.APP_QB, "com.tencent.mtt.MainActivity", str);
            } else {
                a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
            }
        } catch (Exception e3) {
            if (z) {
                try {
                    a(context, "com.android.browser", "com.android.browser.BrowserActivity", str);
                } catch (Exception e4) {
                    try {
                        a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
                    } catch (Exception e5) {
                        try {
                            a(context, "com.android.chrome", "com.google.android.apps.chrome.Main", str);
                        } catch (Exception e6) {
                            return false;
                        }
                    }
                }
            } else {
                try {
                    a(context, "com.google.android.browser", "com.android.browser.BrowserActivity", str);
                } catch (Exception e7) {
                    try {
                        a(context, "com.android.chrome", "com.google.android.apps.chrome.Main", str);
                    } catch (Exception e8) {
                        return false;
                    }
                }
            }
            return true;
        }
        return true;
    }

    private static void a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, str2));
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(1073741824);
        intent.addFlags(268435456);
        intent.setData(Uri.parse(str3));
        context.startActivity(intent);
    }

    public static String encrypt(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(getBytesUTF8(str));
            byte[] digest = instance.digest();
            if (digest == null) {
                return str;
            }
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                sb.append(a(b2 >>> 4));
                sb.append(a(b2));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e2) {
            f.e("openSDK_LOG.Util", "encrypt has exception: " + e2.getMessage());
            return str;
        }
    }

    private static char a(int i) {
        int i2 = i & 15;
        if (i2 < 10) {
            return (char) (i2 + 48);
        }
        return (char) ((i2 - 10) + 97);
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [com.tencent.open.utils.Util$1] */
    public static void reportBernoulli(final Context context, String str, long j, String str2) {
        final Bundle bundle = new Bundle();
        bundle.putString("appid_for_getting_config", str2);
        bundle.putString("strValue", str2);
        bundle.putString("nValue", str);
        bundle.putString("qver", Constants.SDK_VERSION);
        if (j != 0) {
            bundle.putLong("elt", j);
        }
        new Thread() { // from class: com.tencent.open.utils.Util.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    HttpUtils.openUrl2(context, "http://cgi.qplus.com/report/report", "GET", bundle);
                } catch (Exception e2) {
                    f.e("openSDK_LOG.Util", "reportBernoulli has exception: " + e2.getMessage());
                }
            }
        }.start();
    }

    public static boolean hasSDCard() {
        File file = null;
        if (Environment.getExternalStorageState().equals("mounted")) {
            file = Environment.getExternalStorageDirectory();
        }
        return file != null;
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            String num = Integer.toString(b2 & 255, 16);
            if (num.length() == 1) {
                num = "0" + num;
            }
            sb.append(num);
        }
        return sb.toString();
    }

    public static String toHexString(String str) {
        byte[] bytesUTF8 = getBytesUTF8(str);
        StringBuilder sb = new StringBuilder(bytesUTF8.length * 2);
        for (int i = 0; i < bytesUTF8.length; i++) {
            sb.append(g.charAt((bytesUTF8[i] & 240) >> 4));
            sb.append(g.charAt((bytesUTF8[i] & dc.m) >> 0));
        }
        return sb.toString();
    }

    public static String hexToString(String str) {
        if ("0x".equals(str.substring(0, 2))) {
            str = str.substring(2);
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < bArr.length; i++) {
            try {
                bArr[i] = (byte) (Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16) & 255);
            } catch (Exception e2) {
                f.e("openSDK_LOG.Util", "hexToString has exception: " + e2.getMessage());
            }
        }
        try {
            return new String(bArr, "utf-8");
        } catch (Exception e3) {
            f.e("openSDK_LOG.Util", "hexToString has exception: " + e3.getMessage());
            return str;
        }
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            f.e("openSDK_LOG.Util", "getAppVersion error" + e2.getMessage());
            return "";
        }
    }

    public static final String getApplicationLable(Context context) {
        CharSequence applicationLabel;
        if (context == null || (applicationLabel = context.getPackageManager().getApplicationLabel(context.getApplicationInfo())) == null) {
            return null;
        }
        return applicationLabel.toString();
    }

    public static final boolean isValidUrl(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("http://") || str.startsWith("https://");
    }

    public static boolean fileExists(String str) {
        File file;
        if (str == null || (file = new File(str)) == null || !file.exists()) {
            return false;
        }
        return true;
    }

    public static final String subString(String str, int i, String str2, String str3) {
        Exception e2;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "UTF-8";
        }
        try {
            if (str.getBytes(str2).length <= i) {
                return str;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < str.length(); i3++) {
                int length = str.substring(i3, i3 + 1).getBytes(str2).length;
                if (i2 + length > i) {
                    String substring = str.substring(0, i3);
                    try {
                        if (!TextUtils.isEmpty(str3)) {
                            substring = substring + str3;
                        }
                        return substring;
                    } catch (Exception e3) {
                        e2 = e3;
                        System.out.println("StructMsg sSubString error : " + e2.getMessage());
                        return str;
                    }
                } else {
                    i2 += length;
                }
            }
            return str;
        } catch (Exception e4) {
            e2 = e4;
        }
    }

    public static int parseIntValue(String str) {
        return parseIntValue(str, 0);
    }

    public static int parseIntValue(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e2) {
            return i;
        }
    }

    public static boolean checkNetWork(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return true;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (networkInfo.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    public static Bundle composeViaReportParams(String str, String str2, String str3, String str4, String str5, String str6) {
        return composeViaReportParams(str, str3, str4, str2, str5, str6, "", "", "", "", "", "");
    }

    public static Bundle composeViaReportParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        Bundle bundle = new Bundle();
        bundle.putString("openid", str);
        bundle.putString("report_type", str2);
        bundle.putString("act_type", str3);
        bundle.putString("via", str4);
        bundle.putString("app_id", str5);
        bundle.putString(j.c, str6);
        bundle.putString("type", str7);
        bundle.putString("login_status", str8);
        bundle.putString("need_user_auth", str9);
        bundle.putString("to_uin", str10);
        bundle.putString("call_source", str11);
        bundle.putString("to_type", str12);
        return bundle;
    }

    public static Bundle composeHaboCgiReportParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PARAM_PLATFORM, "1");
        bundle.putString(j.c, str);
        bundle.putString("code", str2);
        bundle.putString("tmcost", str3);
        bundle.putString("rate", str4);
        bundle.putString("cmd", str5);
        bundle.putString("uin", str6);
        bundle.putString("appid", str7);
        bundle.putString("share_type", str8);
        bundle.putString("detail", str9);
        bundle.putString("os_ver", Build.VERSION.RELEASE);
        bundle.putString("network", com.tencent.open.b.a.a(Global.getContext()));
        bundle.putString("apn", com.tencent.open.b.a.b(Global.getContext()));
        bundle.putString("model_name", Build.MODEL);
        bundle.putString("sdk_ver", Constants.SDK_VERSION);
        bundle.putString("packagename", Global.getPackageName());
        bundle.putString("app_ver", getAppVersionName(Global.getContext(), Global.getPackageName()));
        return bundle;
    }

    public static String getLocation(Context context) {
        Location lastKnownLocation;
        if (context == null) {
            return "";
        }
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            Criteria criteria = new Criteria();
            criteria.setCostAllowed(false);
            criteria.setAccuracy(2);
            String bestProvider = locationManager.getBestProvider(criteria, true);
            if (!(bestProvider == null || (lastKnownLocation = locationManager.getLastKnownLocation(bestProvider)) == null)) {
                f = lastKnownLocation.getLatitude() + "*" + lastKnownLocation.getLongitude();
                return f;
            }
            return "";
        } catch (Exception e2) {
            f.b("openSDK_LOG.Util", "getLocation>>>", e2);
        }
        return "";
    }

    public static void getPackageInfo(Context context, String str) {
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 0);
                b = packageInfo.versionName;
                a = b.substring(0, b.lastIndexOf(46));
                d = b.substring(b.lastIndexOf(46) + 1, b.length());
                e = packageInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e2) {
                f.e("openSDK_LOG.Util", "getPackageInfo has exception: " + e2.getMessage());
            } catch (Exception e3) {
                f.e("openSDK_LOG.Util", "getPackageInfo has exception: " + e3.getMessage());
            }
        }
    }

    public static String getVersionName(Context context, String str) {
        if (context == null) {
            return "";
        }
        getPackageInfo(context, str);
        return b;
    }

    public static String getAppVersionName(Context context, String str) {
        if (context == null) {
            return "";
        }
        getPackageInfo(context, str);
        return a;
    }

    public static String getQUA3(Context context, String str) {
        if (context == null) {
            return "";
        }
        c = getAppVersionName(context, str);
        return c;
    }

    public static byte[] getBytesUTF8(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            return null;
        }
    }

    public static boolean isNumeric(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static boolean isTablet(Context context) {
        double d2 = 0.0d;
        try {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            float f2 = displayMetrics.widthPixels / displayMetrics.xdpi;
            d2 = Math.sqrt(Math.pow(displayMetrics.heightPixels / displayMetrics.ydpi, 2.0d) + Math.pow(f2, 2.0d));
        } catch (Throwable th) {
        }
        return d2 > 6.5d;
    }

    public static boolean isQQVersionBelow(Context context, String str) {
        boolean z = !isTablet(context) || SystemUtils.getAppVersionName(context, Constants.PACKAGE_QQ_PAD) == null;
        if (z && SystemUtils.getAppVersionName(context, Constants.PACKAGE_TIM) != null) {
            z = false;
        }
        return z ? SystemUtils.compareQQVersion(context, str) < 0 : z;
    }

    public static boolean isSupportShareToQQ(Context context) {
        return (isTablet(context) && SystemUtils.getAppVersionName(context, Constants.PACKAGE_QQ_PAD) != null) || SystemUtils.compareQQVersion(context, "4.1") >= 0 || SystemUtils.getAppVersionName(context, Constants.PACKAGE_TIM) != null;
    }

    public static boolean isSupportPushToQZone(Context context) {
        return SystemUtils.compareQQVersion(context, SystemUtils.QQ_VERSION_NAME_5_9_5) >= 0 || SystemUtils.getAppVersionName(context, Constants.PACKAGE_TIM) != null;
    }
}

package com.alipay.sdk.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.h;
import com.alipay.sdk.app.i;
import com.alipay.sdk.app.statistic.c;
import com.hyphenate.util.HanziToPinyin;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale"})
/* loaded from: classes.dex */
public final class l {
    static final String a = "com.alipay.android.app";
    public static final int b = 99;
    public static final int c = 73;
    private static final String d = "com.eg.android.AlipayGphone";
    private static final String e = "com.eg.android.AlipayGphoneRC";

    public static String a() {
        return EnvUtils.isSandBox() ? "com.eg.android.AlipayGphoneRC" : d;
    }

    public static Map<String, String> a(String str) {
        HashMap hashMap = new HashMap();
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        for (String str2 : split) {
            int indexOf = str2.indexOf("=", 1);
            hashMap.put(str2.substring(0, indexOf), URLDecoder.decode(str2.substring(indexOf + 1)));
        }
        return hashMap;
    }

    public static String a(String str, String str2, String str3) {
        try {
            int length = str.length() + str3.indexOf(str);
            if (length <= str.length()) {
                return "";
            }
            int i = 0;
            if (!TextUtils.isEmpty(str2)) {
                i = str3.indexOf(str2, length);
            }
            if (i <= 0) {
                return str3.substring(length);
            }
            return str3.substring(length, i);
        } catch (Throwable th) {
            return "";
        }
    }

    public static String a(byte[] bArr) {
        try {
            String obj = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr))).getPublicKey().toString();
            if (obj.indexOf("modulus") != -1) {
                return obj.substring(obj.indexOf("modulus") + 8, obj.lastIndexOf(",")).trim();
            }
        } catch (Exception e2) {
            com.alipay.sdk.app.statistic.a.a("auth", c.n, e2);
        }
        return null;
    }

    public static a a(Context context) {
        return a(context, a());
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.alipay.sdk.util.l$a, android.content.pm.PackageInfo] */
    private static a a(Context context, String str) {
        PackageInfo c2;
        PackageInfo packageInfo = 0;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 192);
            if (!a(c2) || c2 == null) {
                return packageInfo;
            }
            a aVar = new a();
            aVar.a = c2.signatures;
            aVar.b = c2.versionCode;
            return aVar;
        } finally {
            if (!a((PackageInfo) packageInfo)) {
                try {
                    c(context, str);
                } catch (Throwable th) {
                    com.alipay.sdk.app.statistic.a.a("auth", c.m, th);
                }
            }
        }
    }

    private static boolean a(PackageInfo packageInfo) {
        String str = "";
        boolean z = false;
        if (packageInfo == null) {
            str = str + "info == null";
        } else if (packageInfo.signatures == null) {
            str = str + "info.signatures == null";
        } else if (packageInfo.signatures.length <= 0) {
            str = str + "info.signatures.length <= 0";
        } else {
            z = true;
        }
        if (!z) {
            com.alipay.sdk.app.statistic.a.a("auth", c.l, str);
        }
        return z;
    }

    private static PackageInfo b(Context context, String str) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getPackageInfo(str, 192);
    }

    private static PackageInfo c(Context context, String str) {
        for (PackageInfo packageInfo : context.getPackageManager().getInstalledPackages(192)) {
            if (packageInfo.packageName.equals(str)) {
                return packageInfo;
            }
        }
        return null;
    }

    private static a b(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        a aVar = new a();
        aVar.a = packageInfo.signatures;
        aVar.b = packageInfo.versionCode;
        return aVar;
    }

    /* loaded from: classes.dex */
    public static class a {
        public Signature[] a;
        public int b;

        public final boolean a() {
            if (this.a == null || this.a.length <= 0) {
                return false;
            }
            for (int i = 0; i < this.a.length; i++) {
                String a = l.a(this.a[i].toByteArray());
                if (a != null && !TextUtils.equals(a, com.alipay.sdk.cons.a.h)) {
                    com.alipay.sdk.app.statistic.a.a(c.b, c.t, a);
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(a, 128) != null;
        } catch (PackageManager.NameNotFoundException e2) {
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a(), 128);
            if (packageInfo == null) {
                return false;
            }
            if (packageInfo.versionCode > 73) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(c.b, c.B, th);
            return false;
        }
    }

    public static boolean d(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(a(), 128);
            if (packageInfo == null) {
                return false;
            }
            if (packageInfo.versionCode < 99) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public static String e(Context context) {
        String b2 = b();
        String c2 = c();
        String f = f(context);
        return " (" + b2 + h.b + c2 + h.b + f + ";;" + g(context) + ")(sdk android)";
    }

    public static String b() {
        return "Android " + Build.VERSION.RELEASE;
    }

    public static WebView a(Activity activity, String str, String str2) {
        Context applicationContext = activity.getApplicationContext();
        if (!TextUtils.isEmpty(str2)) {
            CookieSyncManager.createInstance(applicationContext).sync();
            CookieManager.getInstance().setCookie(str, str2);
            CookieSyncManager.getInstance().sync();
        }
        LinearLayout linearLayout = new LinearLayout(applicationContext);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        linearLayout.setOrientation(1);
        activity.setContentView(linearLayout, layoutParams);
        WebView webView = new WebView(applicationContext);
        layoutParams.weight = 1.0f;
        webView.setVisibility(0);
        linearLayout.addView(webView, layoutParams);
        WebSettings settings = webView.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + e(applicationContext));
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        webView.setVerticalScrollbarOverlay(true);
        webView.setDownloadListener(new m(applicationContext));
        if (Build.VERSION.SDK_INT >= 7) {
            try {
                Method method = webView.getSettings().getClass().getMethod("setDomStorageEnabled", Boolean.TYPE);
                if (method != null) {
                    method.invoke(webView.getSettings(), true);
                }
            } catch (Exception e2) {
            }
        }
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable th) {
            try {
                Method method2 = webView.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                if (method2 != null) {
                    method2.invoke(webView, "searchBoxJavaBridge_");
                    method2.invoke(webView, "accessibility");
                    method2.invoke(webView, "accessibilityTraversal");
                }
            } catch (Throwable th2) {
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            webView.getSettings().setCacheMode(1);
        }
        webView.loadUrl(str);
        return webView;
    }

    public static String c() {
        String e2 = e();
        int indexOf = e2.indexOf("-");
        if (indexOf != -1) {
            e2 = e2.substring(0, indexOf);
        }
        int indexOf2 = e2.indexOf("\n");
        if (indexOf2 != -1) {
            e2 = e2.substring(0, indexOf2);
        }
        return "Linux " + e2;
    }

    private static String e() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/version"), 256);
            String readLine = bufferedReader.readLine();
            bufferedReader.close();
            Matcher matcher = Pattern.compile("\\w+\\s+\\w+\\s+([^\\s]+)\\s+\\(([^\\s@]+(?:@[^\\s.]+)?)[^)]*\\)\\s+\\((?:[^(]*\\([^)]*\\))?[^)]*\\)\\s+([^\\s]+)\\s+(?:PREEMPT\\s+)?(.+)").matcher(readLine);
            if (matcher.matches() && matcher.groupCount() >= 4) {
                return matcher.group(1) + "\n" + matcher.group(2) + HanziToPinyin.Token.SEPARATOR + matcher.group(3) + "\n" + matcher.group(4);
            }
            return "Unavailable";
        } catch (IOException e2) {
            return "Unavailable";
        }
    }

    public static String f(Context context) {
        return context.getResources().getConfiguration().locale.toString();
    }

    public static String g(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
    }

    private static DisplayMetrics j(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    private static String k(Context context) {
        String a2 = k.a(context);
        return a2.substring(0, a2.indexOf("://"));
    }

    private static String f() {
        return "-1;-1";
    }

    public static String d() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 24; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 65.0d)));
                    break;
                case 1:
                    sb.append(String.valueOf((char) Math.round((Math.random() * 25.0d) + 97.0d)));
                    break;
                case 2:
                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }

    public static boolean b(String str) {
        return Pattern.compile("^http(s)?://([a-z0-9_\\-]+\\.)*(alipaydev|alipay|taobao)\\.(com|net)(:\\d+)?(/.*)?$").matcher(str).matches();
    }

    public static String h(Context context) {
        String str = "";
        try {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.processName.equals(a())) {
                    str = str + "#M";
                } else {
                    str = runningAppProcessInfo.processName.startsWith(new StringBuilder().append(a()).append(":").toString()) ? str + "#" + runningAppProcessInfo.processName.replace(a() + ":", "") : str;
                }
            }
        } catch (Throwable th) {
            str = "";
        }
        if (str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() == 0) {
            return "N";
        }
        return str;
    }

    public static String i(Context context) {
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < installedPackages.size(); i++) {
                PackageInfo packageInfo = installedPackages.get(i);
                int i2 = packageInfo.applicationInfo.flags;
                if ((i2 & 1) == 0 && (i2 & 128) == 0) {
                    if (packageInfo.packageName.equals(a())) {
                        sb.append(packageInfo.packageName).append(packageInfo.versionCode).append("-");
                    } else if (!packageInfo.packageName.contains("theme") && !packageInfo.packageName.startsWith("com.google.") && !packageInfo.packageName.startsWith("com.android.")) {
                        sb.append(packageInfo.packageName).append("-");
                    }
                }
            }
            return sb.toString();
        } catch (Throwable th) {
            com.alipay.sdk.app.statistic.a.a(c.b, "GetInstalledAppEx", th);
            return "";
        }
    }

    @SuppressLint({"InlinedApi"})
    private static boolean c(PackageInfo packageInfo) {
        int i = packageInfo.applicationInfo.flags;
        return (i & 1) == 0 && (i & 128) == 0;
    }

    public static boolean a(WebView webView, String str, Activity activity) {
        if (!TextUtils.isEmpty(str)) {
            if (str.toLowerCase().startsWith(com.alipay.sdk.cons.a.i.toLowerCase()) || str.toLowerCase().startsWith(com.alipay.sdk.cons.a.j.toLowerCase())) {
                try {
                    a a2 = a(activity);
                    if (a2 != null && !a2.a()) {
                        if (str.startsWith("intent://platformapi/startapp")) {
                            str = str.replaceFirst("intent://platformapi/startapp\\?", com.alipay.sdk.cons.a.i);
                        }
                        activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    }
                } catch (Throwable th) {
                }
            } else if (TextUtils.equals(str, com.alipay.sdk.cons.a.l) || TextUtils.equals(str, com.alipay.sdk.cons.a.m)) {
                h.a = h.a();
                activity.finish();
            } else if (str.startsWith(com.alipay.sdk.cons.a.k)) {
                try {
                    String substring = str.substring(str.indexOf(com.alipay.sdk.cons.a.k) + 24);
                    int parseInt = Integer.parseInt(substring.substring(substring.lastIndexOf(com.alipay.sdk.cons.a.n) + 10));
                    if (parseInt == i.SUCCEEDED.h || parseInt == i.PAY_WAITTING.h) {
                        String decode = URLDecoder.decode(str);
                        String substring2 = decode.substring(decode.indexOf(com.alipay.sdk.cons.a.k) + 24, decode.lastIndexOf(com.alipay.sdk.cons.a.n));
                        i a3 = i.a(parseInt);
                        h.a = h.a(a3.h, a3.i, substring2);
                    } else {
                        i a4 = i.a(i.FAILED.h);
                        h.a = h.a(a4.h, a4.i, "");
                    }
                } catch (Exception e2) {
                    i a5 = i.a(i.PARAMS_ERROR.h);
                    h.a = h.a(a5.h, a5.i, "");
                }
                activity.runOnUiThread(new n(activity));
            } else {
                webView.loadUrl(str);
            }
        }
        return true;
    }
}

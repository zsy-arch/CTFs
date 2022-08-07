package com.alipay.sdk.app.statistic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.sdk.cons.a;
import com.alipay.sdk.tid.b;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public final class c {
    public static final String A = "BindWaitTimeoutEx";
    public static final String B = "CheckClientExistEx";
    public static final String C = "CheckClientSignEx";
    public static final String D = "GetInstalledAppEx";
    public static final String E = "GetInstalledAppEx";
    public static final String F = "partner";
    public static final String G = "out_trade_no";
    public static final String H = "trade_no";
    public static final String a = "net";
    public static final String b = "biz";
    public static final String c = "cp";
    public static final String d = "auth";
    public static final String e = "third";
    public static final String f = "FormatResultEx";
    public static final String g = "GetApdidEx";
    public static final String h = "GetApdidNull";
    public static final String i = "GetApdidTimeout";
    public static final String j = "GetUtdidEx";
    public static final String k = "GetPackageInfoEx";
    public static final String l = "NotIncludeSignatures";
    public static final String m = "GetInstalledPackagesEx";
    public static final String n = "GetPublicKeyFromSignEx";
    public static final String o = "H5PayNetworkError";
    public static final String p = "H5AuthNetworkError";
    public static final String q = "SSLError";
    public static final String r = "H5PayDataAnalysisError";
    public static final String s = "H5AuthDataAnalysisError";
    public static final String t = "PublicKeyUnmatch";

    /* renamed from: u  reason: collision with root package name */
    public static final String f4u = "ClientBindFailed";
    public static final String v = "TriDesEncryptError";
    public static final String w = "TriDesDecryptError";
    public static final String x = "ClientBindException";
    public static final String y = "SaveTradeTokenError";
    public static final String z = "ClientBindServiceFailed";
    String J;
    String K;
    String N;
    String Q = "";
    String I = String.format("123456789,%s", new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    String L = String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", a(a.f), a(a.g));
    String M = String.format("%s,%s,-,-,-", a(b.a().a), a(com.alipay.sdk.sys.b.a().c()));
    String O = "-";
    String P = "-";
    String R = "-";

    public c(Context context) {
        this.K = a(context);
        this.N = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", a(com.alipay.sdk.util.a.d(context)), f.a, a(Build.VERSION.RELEASE), a(Build.MODEL), "-", a(com.alipay.sdk.util.a.a(context).a()), a(com.alipay.sdk.util.a.b(context).p), "gw", a(com.alipay.sdk.util.a.a(context).b()));
    }

    private boolean a() {
        return TextUtils.isEmpty(this.Q);
    }

    public final void a(String str, String str2, Throwable th) {
        a(str, str2, a(th));
    }

    private void a(String str, String str2, Throwable th, String str3) {
        a(str, str2, a(th), str3);
    }

    public final void a(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(this.Q)) {
            str5 = str5 + "^";
        }
        this.Q += (str5 + String.format("%s,%s,%s,%s", str, str2, a(str3), str4));
    }

    public final void a(String str, String str2, String str3) {
        a(str, str2, str3, "-");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace("[", "【").replace("]", "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("-", "=").replace("^", "~");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName()).append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (int i2 = 0; i2 < stackTrace.length; i2++) {
                    stringBuffer.append(stackTrace[i2].toString() + " 》 ");
                }
            }
        } catch (Throwable th2) {
        }
        return stringBuffer.toString();
    }

    private String b(String str) {
        String str2;
        String str3 = null;
        if (TextUtils.isEmpty(this.Q)) {
            return "";
        }
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        if (split != null) {
            str2 = null;
            for (String str4 : split) {
                String[] split2 = str4.split("=");
                if (split2 != null && split2.length == 2) {
                    if (split2[0].equalsIgnoreCase(F)) {
                        split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(G)) {
                        str2 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(H)) {
                        str3 = split2[1].replace("\"", "");
                    }
                }
            }
        } else {
            str2 = null;
        }
        String a2 = a(str3);
        String a3 = a(str2);
        this.J = String.format("%s,%s,-,%s,-,-,-", a2, a3, a(a3));
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", this.I, this.J, this.K, this.L, this.M, this.N, this.O, this.P, this.Q, this.R);
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String b() {
        return String.format("123456789,%s", new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date()));
    }

    private static String c(String str) {
        String str2;
        String str3 = null;
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        if (split != null) {
            str2 = null;
            for (String str4 : split) {
                String[] split2 = str4.split("=");
                if (split2 != null && split2.length == 2) {
                    if (split2[0].equalsIgnoreCase(F)) {
                        split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(G)) {
                        str2 = split2[1].replace("\"", "");
                    } else if (split2[0].equalsIgnoreCase(H)) {
                        str3 = split2[1].replace("\"", "");
                    }
                }
            }
        } else {
            str2 = null;
        }
        String a2 = a(str3);
        String a3 = a(str2);
        return String.format("%s,%s,-,%s,-,-,-", a2, a3, a(a3));
    }

    private static String a(Context context) {
        String str = "-";
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                str2 = applicationContext.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (Throwable th) {
            }
        }
        return String.format("%s,%s,-,-,-", str, str2);
    }

    private static String c() {
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", a(a.f), a(a.g));
    }

    private static String d() {
        return String.format("%s,%s,-,-,-", a(b.a().a), a(com.alipay.sdk.sys.b.a().c()));
    }

    private static String b(Context context) {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", a(com.alipay.sdk.util.a.d(context)), f.a, a(Build.VERSION.RELEASE), a(Build.MODEL), "-", a(com.alipay.sdk.util.a.a(context).a()), a(com.alipay.sdk.util.a.b(context).p), "gw", a(com.alipay.sdk.util.a.a(context).b()));
    }
}

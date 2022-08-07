package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.WebView;
import e.a.a.a.a;

/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public static String f1570a = null;

    /* renamed from: b  reason: collision with root package name */
    public static String f1571b = "GA";

    /* renamed from: c  reason: collision with root package name */
    public static String f1572c = "GE";

    /* renamed from: d  reason: collision with root package name */
    public static String f1573d = "9422";

    /* renamed from: e  reason: collision with root package name */
    public static String f1574e = "0";
    public static String f = "";
    public static boolean g;
    public static boolean h;
    public static boolean i;

    public static String a() {
        StringBuilder a2 = a.a(" ");
        a2.append(Build.MODEL.replaceAll("[ |\\/|\\_|\\&|\\|]", BuildConfig.FLAVOR));
        a2.append(" ");
        return a2.toString();
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f1570a)) {
            return f1570a;
        }
        WebView.getTbsSDKVersion(context);
        f1570a = a(context, String.valueOf(43939), "0", f1571b, f1572c, f1573d, f1574e, f, g);
        return f1570a;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0187  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String a(android.content.Context r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.j.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    public static String a(String str) {
        return TbsConfig.APP_WX.equals(str) ? "WX" : TbsConfig.APP_QQ.equals(str) ? "QQ" : TbsConfig.APP_QZONE.equals(str) ? "QZ" : TbsConfig.APP_QB.equals(str) ? "QB" : "TRD";
    }

    public static void a(StringBuilder sb, String str, String str2) {
        sb.append("&");
        sb.append(str);
        sb.append("=");
        sb.append(str2);
    }

    public static int b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getWidth();
        }
        return -1;
    }

    public static int c(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay != null) {
            return defaultDisplay.getHeight();
        }
        return -1;
    }

    public static boolean d(Context context) {
        if (h) {
            return i;
        }
        try {
            i = (Math.min(b(context), c(context)) * TbsListener.ErrorCode.STARTDOWNLOAD_1) / e(context) >= 700;
            h = true;
            return i;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static int e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (defaultDisplay == null) {
            return TbsListener.ErrorCode.STARTDOWNLOAD_1;
        }
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }
}

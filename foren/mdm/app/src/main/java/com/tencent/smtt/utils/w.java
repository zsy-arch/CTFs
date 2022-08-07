package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alipay.sdk.sys.a;
import com.hyphenate.util.HanziToPinyin;
import com.tencent.connect.common.Constants;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes2.dex */
public class w {
    private static String a = null;
    private static String b = "GA";
    private static String c = "GE";
    private static String d = "9422";
    private static String e = "0";
    private static String f = "";
    private static boolean g = false;
    private static boolean h = false;
    private static boolean i = false;

    private static String a() {
        return HanziToPinyin.Token.SEPARATOR + Build.MODEL.replaceAll("[ |\\/|\\_|\\&|\\|]", "") + HanziToPinyin.Token.SEPARATOR;
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = a(context, String.valueOf(WebView.getTbsSDKVersion(context)), "0", b, c, d, e, f, g);
        return a;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(24:2|(2:49|3)|(4:51|4|(1:32)|6)|7|(2:(1:10)|44)(3:36|(1:38)|44)|11|(1:13)|14|(1:16)|17|(1:19)|20|47|21|22|(1:24)|25|45|26|27|(1:29)|30|31|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0136, code lost:
        r1 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0139, code lost:
        r1 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(android.content.Context r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.w.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean):java.lang.String");
    }

    private static String a(String str) {
        return str.equals("com.tencent.mm") ? "WX" : str.equals("com.tencent.mobileqq") ? Constants.SOURCE_QQ : str.equals("com.qzone") ? "QZ" : str.equals(TbsConfig.APP_QB) ? "QB" : "TRD";
    }

    private static void a(StringBuilder sb, String str, String str2) {
        sb.append(a.b).append(str).append("=").append(str2);
    }

    private static int b(Context context) {
        try {
            return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
        } catch (Exception e2) {
            return -1;
        }
    }

    private static int c(Context context) {
        try {
            return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
        } catch (Exception e2) {
            return -1;
        }
    }

    private static boolean d(Context context) {
        if (h) {
            return i;
        }
        i = (Math.min(b(context), c(context)) * 160) / e(context) >= 700;
        h = true;
        return i;
    }

    private static int e(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }
}

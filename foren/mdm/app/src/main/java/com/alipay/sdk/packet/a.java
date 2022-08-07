package com.alipay.sdk.packet;

import android.text.TextUtils;
import com.alipay.sdk.app.statistic.c;
import com.alipay.sdk.util.h;

/* loaded from: classes.dex */
public final class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(com.alipay.sdk.sys.a.b);
        if (split.length == 0) {
            return "";
        }
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        for (String str6 : split) {
            if (TextUtils.isEmpty(str5)) {
                str5 = !str6.contains("biz_type") ? null : e(str6);
            }
            if (TextUtils.isEmpty(str4)) {
                str4 = !str6.contains("biz_no") ? null : e(str6);
            }
            if (TextUtils.isEmpty(str3)) {
                str3 = (!str6.contains(c.H) || str6.startsWith(c.G)) ? null : e(str6);
            }
            if (TextUtils.isEmpty(str2)) {
                str2 = !str6.contains("app_userid") ? null : e(str6);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str5)) {
            sb.append("biz_type=" + str5 + h.b);
        }
        if (!TextUtils.isEmpty(str4)) {
            sb.append("biz_no=" + str4 + h.b);
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append("trade_no=" + str3 + h.b);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append("app_userid=" + str2 + h.b);
        }
        String sb2 = sb.toString();
        if (sb2.endsWith(h.b)) {
            return sb2.substring(0, sb2.length() - 1);
        }
        return sb2;
    }

    private static String b(String str) {
        if (!str.contains("biz_type")) {
            return null;
        }
        return e(str);
    }

    private static String c(String str) {
        if (!str.contains("biz_no")) {
            return null;
        }
        return e(str);
    }

    private static String d(String str) {
        if (!str.contains(c.H) || str.startsWith(c.G)) {
            return null;
        }
        return e(str);
    }

    private static String e(String str) {
        String[] split = str.split("=");
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        if (str2.contains("\"")) {
            return str2.replaceAll("\"", "");
        }
        return str2;
    }

    private static String f(String str) {
        if (!str.contains("app_userid")) {
            return null;
        }
        return e(str);
    }
}

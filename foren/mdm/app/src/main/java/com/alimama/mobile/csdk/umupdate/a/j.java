package com.alimama.mobile.csdk.umupdate.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.alipay.sdk.sys.a;
import com.hyphenate.util.HanziToPinyin;
import com.yolanda.nohttp.Headers;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Utils.java */
/* loaded from: classes.dex */
public class j {
    private static Map<String, String> a;

    public static String a(String str) {
        if (str.contains("/")) {
            return str.replace("/", "^$^");
        }
        return str;
    }

    public static String b(String str) {
        if (str.contains("^$^")) {
            return str.replace("^$^", "/");
        }
        return str;
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bytes);
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < digest.length; i++) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(digest[i])));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    public static void a(Context context, String str) {
        context.startActivity(context.getPackageManager().getLaunchIntentForPackage(str));
    }

    public static boolean b(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String a(String str, Map<String, Object> map) {
        StringBuilder sb = new StringBuilder(str);
        for (String str2 : map.keySet()) {
            try {
                sb.append(URLEncoder.encode(str2, "utf-8")).append("=").append(URLEncoder.encode(map.get(str2) == null ? "" : map.get(str2).toString(), "utf-8")).append(a.b);
            } catch (UnsupportedEncodingException e) {
                Log.e("Alimama", "", e);
            }
        }
        if (sb.toString().endsWith(a.b)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString().replaceAll(HanziToPinyin.Token.SEPARATOR, "");
    }

    public static Map<String, String> a() {
        if (a == null || a.size() < 1) {
            a = new HashMap();
            a.put(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        }
        return a;
    }
}

package com.umeng.analytics.social;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.em.db.UserDao;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.social.UMPlatformData;
import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: UMUtils.java */
/* loaded from: classes2.dex */
public abstract class f {
    private static Map<String, String> a;

    /* JADX INFO: Access modifiers changed from: protected */
    public static String[] a(Context context, String str, UMPlatformData... uMPlatformDataArr) throws a {
        if (uMPlatformDataArr == null || uMPlatformDataArr.length == 0) {
            throw new a("platform data is null");
        }
        String appkey = AnalyticsConfig.getAppkey(context);
        if (TextUtils.isEmpty(appkey)) {
            throw new a("can`t get appkey.");
        }
        ArrayList arrayList = new ArrayList();
        String str2 = "http://log.umsns.com/share/api/" + appkey + "/";
        if (a == null || a.isEmpty()) {
            a = b(context);
        }
        if (a != null && !a.isEmpty()) {
            for (Map.Entry<String, String> entry : a.entrySet()) {
                arrayList.add(entry.getKey() + "=" + entry.getValue());
            }
        }
        arrayList.add("date=" + String.valueOf(System.currentTimeMillis()));
        arrayList.add("channel=" + e.e);
        if (!TextUtils.isEmpty(str)) {
            arrayList.add("topic=" + str);
        }
        arrayList.addAll(a(uMPlatformDataArr));
        String b = b(uMPlatformDataArr);
        if (b == null) {
            b = com.alimama.mobile.csdk.umupdate.a.f.b;
        }
        String str3 = str2 + "?" + a(arrayList);
        while (str3.contains("%2C+")) {
            str3 = str3.replace("%2C+", a.b);
        }
        while (str3.contains("%3D")) {
            str3 = str3.replace("%3D", "=");
        }
        while (str3.contains("%5B")) {
            str3 = str3.replace("%5B", "");
        }
        while (str3.contains("%5D")) {
            str3 = str3.replace("%5D", "");
        }
        b.c(com.umeng.analytics.a.d, "URL:" + str3);
        b.c(com.umeng.analytics.a.d, "BODY:" + b);
        return new String[]{str3, b};
    }

    private static String a(List<String> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(URLEncoder.encode(list.toString()).getBytes());
            return byteArrayOutputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<String> a(UMPlatformData... uMPlatformDataArr) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        for (UMPlatformData uMPlatformData : uMPlatformDataArr) {
            sb.append(uMPlatformData.getMeida().toString());
            sb.append(',');
            sb2.append(uMPlatformData.getUsid());
            sb2.append(',');
            sb3.append(uMPlatformData.getWeiboId());
            sb3.append(',');
        }
        if (uMPlatformDataArr.length > 0) {
            sb.deleteCharAt(sb.length() - 1);
            sb2.deleteCharAt(sb2.length() - 1);
            sb3.deleteCharAt(sb3.length() - 1);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("platform=" + sb.toString());
        arrayList.add("usid=" + sb2.toString());
        if (sb3.length() > 0) {
            arrayList.add("weiboid=" + sb3.toString());
        }
        return arrayList;
    }

    private static String b(UMPlatformData... uMPlatformDataArr) {
        JSONObject jSONObject = new JSONObject();
        for (UMPlatformData uMPlatformData : uMPlatformDataArr) {
            UMPlatformData.GENDER gender = uMPlatformData.getGender();
            String name = uMPlatformData.getName();
            if (gender == null) {
                try {
                    if (TextUtils.isEmpty(name)) {
                    }
                } catch (Exception e) {
                    throw new a("build body exception", e);
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(UserDao.CONTACT_COLUMN_NAME_GENDER, gender == null ? "" : String.valueOf(gender.value));
            jSONObject2.put("name", name == null ? "" : String.valueOf(name));
            jSONObject.put(uMPlatformData.getMeida().toString(), jSONObject2);
        }
        if (jSONObject.length() == 0) {
            return null;
        }
        return jSONObject.toString();
    }

    private static Map<String, String> b(Context context) throws a {
        HashMap hashMap = new HashMap();
        Map<String, String> a2 = a(context);
        if (a2 == null || a2.isEmpty()) {
            throw new a("can`t get device id.");
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry<String, String> entry : a2.entrySet()) {
            if (!TextUtils.isEmpty(entry.getValue())) {
                sb2.append(entry.getKey()).append(",");
                sb.append(entry.getValue()).append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            hashMap.put("deviceid", sb.toString());
        }
        if (sb2.length() > 0) {
            sb2.deleteCharAt(sb2.length() - 1);
            hashMap.put("idtype", sb2.toString());
        }
        return hashMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.String, java.lang.String> a(android.content.Context r5) {
        /*
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r5.getSystemService(r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            if (r0 != 0) goto L_0x0016
            java.lang.String r1 = "MobclickAgent"
            java.lang.String r3 = "No IMEI."
            com.umeng.analytics.social.b.e(r1, r3)
        L_0x0016:
            r1 = 0
            java.lang.String r3 = "android.permission.READ_PHONE_STATE"
            boolean r3 = a(r5, r3)     // Catch: Exception -> 0x0053
            if (r3 == 0) goto L_0x005b
            java.lang.String r0 = r0.getDeviceId()     // Catch: Exception -> 0x0053
        L_0x0023:
            java.lang.String r1 = c(r5)
            android.content.ContentResolver r3 = r5.getContentResolver()
            java.lang.String r4 = "android_id"
            java.lang.String r3 = android.provider.Settings.Secure.getString(r3, r4)
            boolean r4 = android.text.TextUtils.isEmpty(r1)
            if (r4 != 0) goto L_0x003c
            java.lang.String r4 = "mac"
            r2.put(r4, r1)
        L_0x003c:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0047
            java.lang.String r1 = "imei"
            r2.put(r1, r0)
        L_0x0047:
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 != 0) goto L_0x0052
            java.lang.String r0 = "android_id"
            r2.put(r0, r3)
        L_0x0052:
            return r2
        L_0x0053:
            r0 = move-exception
            java.lang.String r3 = "MobclickAgent"
            java.lang.String r4 = "No IMEI."
            com.umeng.analytics.social.b.e(r3, r4, r0)
        L_0x005b:
            r0 = r1
            goto L_0x0023
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.social.f.a(android.content.Context):java.util.Map");
    }

    private static boolean a(Context context, String str) {
        return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
    }

    private static String c(Context context) {
        WifiManager wifiManager;
        try {
            wifiManager = (WifiManager) context.getSystemService("wifi");
        } catch (Exception e) {
            b.e(com.umeng.analytics.a.d, "Could not get mac address." + e.toString());
        }
        if (a(context, "android.permission.ACCESS_WIFI_STATE")) {
            return wifiManager.getConnectionInfo().getMacAddress();
        }
        b.e(com.umeng.analytics.a.d, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
        return "";
    }
}

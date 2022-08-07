package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.amap.api.services.district.DistrictSearchQuery;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;

/* compiled from: GeoFenceNetManager.java */
/* loaded from: classes2.dex */
public final class b {
    bi a;

    public b() {
        this.a = null;
        this.a = bi.a();
    }

    private String a(Context context, String str, Map<String, String> map) {
        String str2;
        if (cx.a(cx.c(context)) == -1) {
            return null;
        }
        HashMap hashMap = new HashMap();
        cl clVar = new cl();
        hashMap.clear();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.3.0");
        String a = m.a();
        String a2 = m.a(context, a, t.b(map));
        map.put("ts", a);
        map.put("scode", a2);
        clVar.b(map);
        clVar.a(hashMap);
        clVar.a(str);
        clVar.a(q.a(context));
        clVar.a(f.c);
        clVar.b(f.c);
        try {
            bi biVar = this.a;
            str2 = new String(bi.a(clVar), "utf-8");
        } catch (Throwable th) {
            f.a(th, "GeoFenceNetManager", "post");
            str2 = null;
        }
        return str2;
    }

    private static Map<String, String> b(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        HashMap hashMap = new HashMap();
        hashMap.put("key", k.f(context));
        if (!TextUtils.isEmpty(str)) {
            hashMap.put(f.aA, str);
        }
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("types", str2);
        }
        if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(str6)) {
            hashMap.put("location", str6 + "," + str5);
        }
        if (!TextUtils.isEmpty(str3)) {
            hashMap.put(DistrictSearchQuery.KEYWORDS_CITY, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            hashMap.put("offset", str4);
        }
        if (!TextUtils.isEmpty(str7)) {
            hashMap.put("radius", str7);
        }
        return hashMap;
    }

    public final String a(Context context, String str, String str2) {
        Map<String, String> b = b(context, str2, null, null, null, null, null, null);
        b.put("extensions", Constant.ALL);
        return a(context, str, b);
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5) {
        Map<String, String> b = b(context, str2, str3, str4, str5, null, null, null);
        b.put("children", "1");
        b.put("page", "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }

    public final String a(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Map<String, String> b = b(context, str2, str3, null, str4, str5, str6, str7);
        b.put("children", "1");
        b.put("page", "1");
        b.put("extensions", "base");
        return a(context, str, b);
    }
}

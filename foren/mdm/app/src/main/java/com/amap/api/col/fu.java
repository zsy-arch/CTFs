package com.amap.api.col;

import android.content.Context;
import com.yolanda.nohttp.Headers;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: BasicLBSRestHandler.java */
/* loaded from: classes.dex */
public abstract class fu<T, V> extends ft<T, V> {
    protected abstract String f();

    public fu(Context context, T t) {
        super(context, t);
    }

    @Override // com.amap.api.col.ig
    public byte[] g() {
        try {
            return f().getBytes("utf-8");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.amap.api.col.ft, com.amap.api.col.ig
    public Map<String, String> b() {
        return null;
    }

    @Override // com.amap.api.col.ft, com.amap.api.col.ig
    public Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", " application/json");
        hashMap.put(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        hashMap.put("User-Agent", "AMAP SDK Android Trace 5.2.1");
        hashMap.put("X-INFO", gd.b(this.d));
        hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", "5.2.1", "trace"));
        hashMap.put("logversion", "2.1");
        return hashMap;
    }

    @Override // com.amap.api.col.ft
    protected V e() {
        return null;
    }

    @Override // com.amap.api.col.ig
    public String c() {
        String str = "key=" + ga.f(this.d);
        String a = gd.a();
        return "http://restapi.amap.com/v3/grasproad?" + str + ("&ts=" + a) + ("&scode=" + gd.a(this.d, a, str));
    }
}

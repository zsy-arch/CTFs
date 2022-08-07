package com.amap.api.col;

import android.content.Context;
import com.alipay.sdk.cons.b;
import com.yolanda.nohttp.Headers;
import java.util.HashMap;
import java.util.Locale;
import org.apache.http.HttpHost;
import org.apache.http.protocol.HTTP;

/* compiled from: LocNetManager.java */
/* loaded from: classes.dex */
public final class jk {
    private static jk d = null;
    ia a;
    private Context e;
    volatile int b = 0;
    public String c = "com.autonavi.httpdns.HttpDnsManager";
    private int f = 0;
    private int g = jn.c;
    private boolean h = false;

    private jk(Context context) {
        this.a = null;
        this.e = null;
        this.e = context;
        this.a = ia.a();
    }

    public static jk a(Context context) {
        if (d == null) {
            d = new jk(context);
        }
        return d;
    }

    public final ii a(jl jlVar) throws Throwable {
        if (jq.a(jq.c(this.e)) == -1) {
            return null;
        }
        long b = jq.b();
        ii a = this.a.a(jlVar, this.h);
        this.f = Long.valueOf(jq.b() - b).intValue();
        return a;
    }

    public final jl a(Context context, byte[] bArr, String str) {
        try {
            HashMap hashMap = new HashMap();
            jl jlVar = new jl(context, jn.a("loc"));
            try {
                hashMap.put("Content-Type", "application/octet-stream");
                hashMap.put(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
                hashMap.put("gzipped", "1");
                hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
                hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.4.0");
                hashMap.put("KEY", ga.f(context));
                hashMap.put("enginever", "4.7");
                String a = gd.a();
                String a2 = gd.a(context, a, "key=" + ga.f(context));
                hashMap.put("ts", a);
                hashMap.put("scode", a2);
                hashMap.put("encr", "1");
                jlVar.b(hashMap);
                jlVar.a(String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "3.4.0", "loc", 3));
                jlVar.f();
                jlVar.b(str);
                jlVar.b(jq.a(bArr));
                jlVar.a(gh.a(context));
                HashMap hashMap2 = new HashMap();
                hashMap2.put("output", "bin");
                hashMap2.put("policy", "3103");
                jlVar.a(hashMap2);
                jlVar.a(this.g);
                jlVar.b(this.g);
                if (!this.h) {
                    return jlVar;
                }
                jlVar.b(jlVar.c().replace(HttpHost.DEFAULT_SCHEME_NAME, b.a));
                return jlVar;
            } catch (Throwable th) {
                return jlVar;
            }
        } catch (Throwable th2) {
            return null;
        }
    }

    public final void a(long j, boolean z) {
        try {
            this.h = z;
            this.g = Long.valueOf(j).intValue();
        } catch (Throwable th) {
            jn.a(th, "netmanager", "setOption");
        }
    }
}

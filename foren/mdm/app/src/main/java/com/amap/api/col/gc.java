package com.amap.api.col;

import android.content.Context;
import android.util.Log;
import com.yolanda.nohttp.Headers;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthManager.java */
@Deprecated
/* loaded from: classes.dex */
public class gc {
    private static gj c;
    public static int a = -1;
    public static String b = "";
    private static String d = "http://apiinit.amap.com/v3/log/init";
    private static String e = null;

    private static boolean a(Context context, gj gjVar, boolean z) {
        c = gjVar;
        try {
            String a2 = a();
            HashMap hashMap = new HashMap();
            hashMap.put("Content-Type", "application/x-www-form-urlencoded");
            hashMap.put(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
            hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
            hashMap.put("User-Agent", c.d());
            hashMap.put("X-INFO", gd.a(context, c, null, z));
            hashMap.put("logversion", "2.1");
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", c.b(), c.a()));
            ia a3 = ia.a();
            gl glVar = new gl();
            glVar.a(gh.a(context));
            glVar.a(hashMap);
            glVar.b(a(context));
            glVar.a(a2);
            return a(a3.b(glVar));
        } catch (Throwable th) {
            go.a(th, "Auth", "getAuth");
            return true;
        }
    }

    @Deprecated
    public static synchronized boolean a(Context context, gj gjVar) {
        boolean a2;
        synchronized (gc.class) {
            a2 = a(context, gjVar, false);
        }
        return a2;
    }

    public static void a(String str) {
        ga.a(str);
    }

    private static String a() {
        return d;
    }

    private static boolean a(byte[] bArr) {
        if (bArr == null) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(gk.a(bArr));
            if (jSONObject.has("status")) {
                int i = jSONObject.getInt("status");
                if (i == 1) {
                    a = 1;
                } else if (i == 0) {
                    a = 0;
                }
            }
            if (jSONObject.has("info")) {
                b = jSONObject.getString("info");
            }
            if (a == 0) {
                Log.i("AuthFailure", b);
            }
            return a == 1;
        } catch (JSONException e2) {
            go.a(e2, "Auth", "lData");
            return false;
        } catch (Throwable th) {
            go.a(th, "Auth", "lData");
            return false;
        }
    }

    private static Map<String, String> a(Context context) {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("resType", "json");
            hashMap.put("encode", "UTF-8");
            String a2 = gd.a();
            hashMap.put("ts", a2);
            hashMap.put("key", ga.f(context));
            hashMap.put("scode", gd.a(context, a2, gk.d("resType=json&encode=UTF-8&key=" + ga.f(context))));
        } catch (Throwable th) {
            go.a(th, "Auth", "gParams");
        }
        return hashMap;
    }
}

package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Proxy;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonSerializer;
import com.alipay.sdk.cons.b;
import com.alipay.sdk.cons.c;
import com.em.db.UserDao;
import com.yolanda.nohttp.Headers;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.apache.http.HttpHost;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* compiled from: LocNetManager.java */
/* loaded from: classes2.dex */
public final class cm {
    private static cm f = null;
    bi a;
    boolean e;
    private Object g;
    private Context i;
    private String h = "apilocatesrc.amap.com";
    boolean b = false;
    volatile int c = 0;
    public String d = "com.autonavi.httpdns.HttpDnsManager";
    private int j = 0;
    private ExecutorService k = null;
    private int l = f.c;

    /* compiled from: LocNetManager.java */
    /* loaded from: classes2.dex */
    class a implements Runnable {
        cn a;

        a(cn cnVar) {
            this.a = null;
            this.a = cnVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            cm.this.c++;
            cm.this.a(this.a);
            cm cmVar = cm.this;
            cmVar.c--;
        }
    }

    private cm(Context context) {
        int i = 1;
        this.a = null;
        this.g = null;
        this.i = null;
        this.e = false;
        this.i = context;
        try {
            if (this.g == null && !this.e) {
                s a2 = f.a("HttpDNS", JsonSerializer.VERSION);
                this.e = cu.a(context, a2);
                if (this.e) {
                    try {
                        this.g = au.a(context, a2, this.d, null, new Class[]{Context.class}, new Object[]{context});
                    } catch (Throwable th) {
                    }
                    if (this.g == null) {
                        i = 0;
                    }
                    cu.a(context, "HttpDns", i);
                } else {
                    this.e = true;
                }
            }
        } catch (Throwable th2) {
            f.a(th2, "APS", "initHttpDns");
        }
        this.a = bi.a();
    }

    public static cm a(Context context) {
        if (f == null) {
            f = new cm(context);
        }
        return f;
    }

    private String a(Context context, String str) {
        if (c(context)) {
            try {
                return (String) cs.a(this.g, "getIpByHostAsync", str);
            } catch (Throwable th) {
                cu.a(context, "HttpDns");
            }
        }
        return null;
    }

    private static boolean b(Context context) {
        int i;
        String str = null;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                str = System.getProperty("http.proxyHost");
                String property = System.getProperty("http.proxyPort");
                if (property == null) {
                    property = "-1";
                }
                i = Integer.parseInt(property);
            } else {
                str = Proxy.getHost(context);
                i = Proxy.getPort(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            i = -1;
        }
        return (str == null || i == -1) ? false : true;
    }

    private boolean c(Context context) {
        return this.g != null && !b(context);
    }

    public final int a() {
        return this.j;
    }

    public final bo a(Context context, JSONObject jSONObject, byte[] bArr, String str, boolean z) throws Throwable {
        if (cx.a(jSONObject, "httptimeout")) {
            try {
                this.l = jSONObject.getInt("httptimeout");
            } catch (Throwable th) {
                f.a(th, "LocNetManager", "req");
            }
        }
        if (cx.a(cx.c(context)) == -1) {
            return null;
        }
        HashMap hashMap = new HashMap();
        cn cnVar = new cn(context, f.a("loc"));
        hashMap.clear();
        hashMap.put("Content-Type", "application/octet-stream");
        hashMap.put(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        hashMap.put("gzipped", "1");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.3.0");
        hashMap.put("KEY", k.f(context));
        hashMap.put("enginever", "4.7");
        String a2 = m.a();
        String a3 = m.a(context, a2, "key=" + k.f(context));
        hashMap.put("ts", a2);
        hashMap.put("scode", a3);
        String str2 = "loc";
        if (!z) {
            str2 = "locf";
        }
        hashMap.put("encr", "1");
        cnVar.j = z;
        cnVar.k = String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "3.3.0", str2, 3);
        cnVar.f = hashMap;
        cnVar.g = str;
        cnVar.h = cx.a(bArr);
        cnVar.a(q.a(context));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("output", "bin");
        hashMap2.put("policy", "3103");
        cnVar.l = hashMap2;
        cnVar.a(this.l);
        cnVar.b(this.l);
        this.b = false;
        boolean optBoolean = jSONObject.optBoolean("locationProtocol", false);
        if (optBoolean) {
            cnVar.g = cnVar.b().replace(HttpHost.DEFAULT_SCHEME_NAME, b.a);
        } else {
            if ((cw.a(context, UserDao.PREF_TABLE_NAME, "dns_faile_count_total") < 2) && c(context) && !"http://abroad.apilocate.amap.com/mobile/binary".equals(cnVar.b())) {
                String a4 = a(context, this.h);
                if (!z && TextUtils.isEmpty(a4)) {
                    a4 = cw.a(context, "ip", "last_ip", "");
                }
                if (!TextUtils.isEmpty(a4)) {
                    this.b = true;
                    try {
                        SharedPreferences.Editor edit = context.getSharedPreferences("ip", 0).edit();
                        edit.putString("last_ip", a4);
                        cw.a(edit);
                    } catch (Throwable th2) {
                        f.a(th2, "SPUtil", "setPrefsInt");
                    }
                    cnVar.g = f.b().replace("apilocatesrc.amap.com", a4);
                    cnVar.a().put(c.f, "apilocatesrc.amap.com");
                }
            }
        }
        long b = cx.b();
        try {
            bi biVar = this.a;
            bo a5 = bi.a(cnVar, optBoolean);
            this.j = Long.valueOf(cx.b() - b).intValue();
            if (!this.b) {
                return a5;
            }
            cw.a(context, UserDao.PREF_TABLE_NAME, "dns_faile_count_total", 0L);
            return a5;
        } catch (Throwable th3) {
            if (this.b) {
                try {
                    if (this.c <= 5) {
                        if (this.k == null) {
                            this.k = z.b();
                        }
                        if (!this.k.isShutdown()) {
                            this.k.submit(new a(cnVar));
                        }
                    }
                } catch (Throwable th4) {
                }
            }
            throw th3;
        }
    }

    public final String a(byte[] bArr, Context context, String str) {
        String str2;
        if (cx.a(cx.c(context)) == -1) {
            return null;
        }
        HashMap hashMap = new HashMap();
        cn cnVar = new cn(context, f.a("loc"));
        hashMap.clear();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        hashMap.put("User-Agent", "AMAP_Location_SDK_Android 3.3.0");
        HashMap hashMap2 = new HashMap();
        hashMap2.put("custom", "26260A1F00020002");
        hashMap2.put("key", k.f(context));
        String a2 = m.a();
        String a3 = m.a(context, a2, t.b(hashMap2));
        hashMap2.put("ts", a2);
        hashMap2.put("scode", a3);
        cnVar.b(bArr);
        cnVar.j = true;
        cnVar.k = String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s&loc_channel=%s", "3.3.0", "loc", 3);
        cnVar.l = hashMap2;
        cnVar.f = hashMap;
        cnVar.g = str;
        cnVar.a(q.a(context));
        cnVar.a(f.c);
        cnVar.b(f.c);
        try {
            bi biVar = this.a;
            str2 = new String(bi.a(cnVar), "utf-8");
        } catch (Throwable th) {
            f.a(th, "LocNetManager", "post");
            str2 = null;
        }
        return str2;
    }

    final synchronized void a(cn cnVar) {
        cnVar.g = f.b();
        long a2 = cw.a(this.i, UserDao.PREF_TABLE_NAME, "dns_faile_count_total");
        if (a2 < 2) {
            bi biVar = this.a;
            bi.a(cnVar, false);
            long j = a2 + 1;
            if (j >= 2) {
                cv.a(this.i, "HttpDNS", "dns faile too much");
            }
            cw.a(this.i, UserDao.PREF_TABLE_NAME, "dns_faile_count_total", j);
        }
    }

    public final String b(byte[] bArr, Context context, String str) {
        String str2;
        if (cx.a(cx.c(context)) == -1) {
            return null;
        }
        HashMap hashMap = new HashMap();
        cl clVar = new cl();
        hashMap.clear();
        hashMap.put("Content-Type", "application/x-www-form-urlencoded");
        hashMap.put("Connection", HTTP.CONN_KEEP_ALIVE);
        clVar.a = hashMap;
        clVar.f = str;
        clVar.g = bArr;
        clVar.a(q.a(context));
        clVar.a(f.c);
        clVar.b(f.c);
        try {
            bi biVar = this.a;
            str2 = new String(bi.a(clVar), "utf-8");
        } catch (Throwable th) {
            f.a(th, "LocNetManager", "post");
            str2 = null;
        }
        return str2;
    }
}

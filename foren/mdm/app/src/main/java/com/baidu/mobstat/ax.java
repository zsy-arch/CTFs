package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.http.config.URLConfig;
import com.tencent.stat.DeviceInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ax {
    private static ax a;
    private Context b;
    private long c = 24;
    private long d = 0;
    private long e = 0;
    private long f = 0;
    private long g = 5;
    private long h = 24;
    private long i = 15;
    private long j = 15;
    private long k = 30;
    private long l = 12;
    private long m = 1;
    private long n = 24;
    private String o = "";
    private String p = "";

    private ax(Context context) {
        this.b = context;
        j();
        k();
    }

    private long a(long j) {
        if (j - System.currentTimeMillis() > 0) {
            return 0L;
        }
        return j;
    }

    public static ax a(Context context) {
        if (a == null) {
            synchronized (ax.class) {
                if (a == null) {
                    a = new ax(context);
                }
            }
        }
        return a;
    }

    public long a(t tVar) {
        long j = tVar.j;
        String b = cl.b("backups/system/.timestamp");
        try {
            if (!TextUtils.isEmpty(b)) {
                j = new JSONObject(b).getLong(tVar.toString());
            }
        } catch (JSONException e) {
            bb.a(e);
        }
        return a(j);
    }

    public void a(t tVar, long j) {
        tVar.j = j;
        String b = cl.b("backups/system/.timestamp");
        try {
            JSONObject jSONObject = !TextUtils.isEmpty(b) ? new JSONObject(b) : new JSONObject();
            jSONObject.put(tVar.toString(), j);
            cl.a("backups/system/.timestamp", jSONObject.toString(), false);
        } catch (JSONException e) {
            bb.a(e);
        }
    }

    public void a(String str) {
        cl.a(this.b, ".config2", str, false);
        j();
    }

    public boolean a() {
        return this.d != 0;
    }

    public void b(String str) {
        cl.a(this.b, ".sign", str, false);
        k();
    }

    public boolean b() {
        return this.e != 0;
    }

    public long c() {
        return this.c * 60 * 60 * 1000;
    }

    public String c(String str) {
        return (TextUtils.isEmpty(this.o) || !this.o.equals(str) || TextUtils.isEmpty(this.p)) ? "" : this.p;
    }

    public long d() {
        return this.n * 60 * 60 * 1000;
    }

    public long e() {
        return this.g * 60 * 1000;
    }

    public long f() {
        return this.h * 60 * 60 * 1000;
    }

    public long g() {
        return this.i * 24 * 60 * 60 * 1000;
    }

    public long h() {
        return this.j * 24 * 60 * 60 * 1000;
    }

    public long i() {
        return this.l * 60 * 60 * 1000;
    }

    public void j() {
        try {
            String str = new String(cs.b(false, cn.a(), cm.a(cl.a(this.b, ".config2").getBytes())));
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    this.d = jSONObject.getLong("c");
                } catch (JSONException e) {
                    bb.b(e);
                }
                try {
                    this.g = jSONObject.getLong("d");
                } catch (JSONException e2) {
                    bb.b(e2);
                }
                try {
                    this.h = jSONObject.getLong("e");
                } catch (JSONException e3) {
                    bb.b(e3);
                }
                try {
                    this.i = jSONObject.getLong("i");
                } catch (JSONException e4) {
                    bb.b(e4);
                }
                try {
                    this.c = jSONObject.getLong("f");
                } catch (JSONException e5) {
                    bb.b(e5);
                }
                try {
                    this.n = jSONObject.getLong(URLConfig.baidu_url);
                } catch (JSONException e6) {
                    bb.b(e6);
                }
                try {
                    this.j = jSONObject.getLong("pk");
                } catch (JSONException e7) {
                    bb.b(e7);
                }
                try {
                    this.k = jSONObject.getLong("at");
                } catch (JSONException e8) {
                    bb.b(e8);
                }
                try {
                    this.l = jSONObject.getLong("as");
                } catch (JSONException e9) {
                    bb.b(e9);
                }
                try {
                    this.m = jSONObject.getLong("ac");
                } catch (JSONException e10) {
                    bb.b(e10);
                }
                try {
                    this.e = jSONObject.getLong("mc");
                } catch (JSONException e11) {
                    bb.b(e11);
                }
                try {
                    this.f = jSONObject.getLong("lsc");
                } catch (JSONException e12) {
                    bb.b(e12);
                }
            }
        } catch (Exception e13) {
            bb.b(e13);
        }
    }

    public void k() {
        try {
            String str = new String(cs.b(false, cn.a(), cm.a(cl.a(this.b, ".sign").getBytes())));
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    this.p = jSONObject.getString("sign");
                } catch (Exception e) {
                    bb.b(e);
                }
                try {
                    this.o = jSONObject.getString(DeviceInfo.TAG_VERSION);
                } catch (Exception e2) {
                    bb.b(e2);
                }
            }
        } catch (Exception e3) {
            bb.b(e3);
        }
    }

    public boolean l() {
        long currentTimeMillis = System.currentTimeMillis();
        long a2 = a(t.LAST_SEND);
        long d = d();
        bb.a("canSend now=" + currentTimeMillis + ";lastSendTime=" + a2 + ";;sendLogTimeInterval=" + d);
        return currentTimeMillis - a2 > d;
    }
}

package com.baidu.mobstat;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.alipay.sdk.sys.a;
import com.http.config.URLConfig;
import com.umeng.update.UpdateConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class bo {
    static String a = "Android";
    String c;
    String d;
    String i;
    String j;
    int k;
    int l;
    String n;
    String o;
    String p;
    String q;
    String r;
    String s;
    String t;

    /* renamed from: u  reason: collision with root package name */
    String f28u;
    String v;
    boolean b = false;
    String e = null;
    String f = "0";
    String g = null;
    int h = -1;
    String m = null;

    public synchronized void a(Context context) {
        if (!this.b) {
            cl.e(context, "android.permission.READ_PHONE_STATE");
            cl.e(context, UpdateConfig.h);
            cl.e(context, UpdateConfig.g);
            cl.e(context, "android.permission.WRITE_SETTINGS");
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            this.c = CooperService.a().getOSVersion();
            this.d = CooperService.a().getOSSysVersion();
            this.o = CooperService.a().getPhoneModel();
            this.p = CooperService.a().getManufacturer();
            this.j = CooperService.a().getDeviceId(telephonyManager, context);
            this.f = be.a().i(context) ? "1" : "0";
            if (cu.q(context)) {
                this.f = "2";
            }
            this.f += "-0";
            try {
                this.t = CooperService.a().getMacID(context);
            } catch (Exception e) {
                cr.a(e);
            }
            try {
                this.v = cu.d(1, context);
            } catch (Exception e2) {
                cr.a(e2);
            }
            this.g = CooperService.a().getCUID(context, true);
            try {
                this.n = CooperService.a().getOperator(telephonyManager);
            } catch (Exception e3) {
                cr.a(e3);
            }
            try {
                this.k = cu.b(context);
                this.l = cu.c(context);
                if (context.getResources().getConfiguration().orientation == 2) {
                    cr.a("Configuration.ORIENTATION_LANDSCAPE");
                    this.k ^= this.l;
                    this.l = this.k ^ this.l;
                    this.k ^= this.l;
                }
            } catch (Exception e4) {
                cr.a(e4);
            }
            this.m = CooperService.a().getAppChannel(context);
            this.e = CooperService.a().getAppKey(context);
            try {
                this.h = CooperService.a().getAppVersionCode(context);
                this.i = CooperService.a().getAppVersionName(context);
            } catch (Exception e5) {
                cr.a(e5);
            }
            try {
                if (CooperService.a().checkCellLocationSetting(context)) {
                    this.q = cu.g(context);
                } else {
                    this.q = "0_0_0";
                }
            } catch (Exception e6) {
                cr.a(e6);
            }
            try {
                if (CooperService.a().checkGPSLocationSetting(context)) {
                    this.r = cu.h(context);
                } else {
                    this.r = "";
                }
            } catch (Exception e7) {
                cr.a(e7);
            }
            try {
                this.s = CooperService.a().getLinkedWay(context);
            } catch (Exception e8) {
                cr.a(e8);
            }
            this.b = true;
        }
    }

    public synchronized void a(Context context, JSONObject jSONObject) {
        a(context);
        if (jSONObject.length() > 10) {
            cr.a("***** have been installHeader header=" + jSONObject);
        } else {
            b(context, jSONObject);
            cr.a("installHeader header=" + jSONObject);
        }
    }

    public synchronized void b(Context context, JSONObject jSONObject) {
        try {
            jSONObject.put("o", a == null ? "" : a);
            jSONObject.put("st", 0);
            jSONObject.put(URLConfig.baidu_url, this.c == null ? "" : this.c);
            jSONObject.put(a.h, this.d == null ? "" : this.d);
            jSONObject.put("k", this.e == null ? "" : this.e);
            jSONObject.put("pt", this.f == null ? "0" : this.f);
            jSONObject.put("i", "");
            jSONObject.put("v", Build.SDK_VERSION);
            jSONObject.put("sc", 0);
            jSONObject.put("a", this.h);
            jSONObject.put("n", this.i == null ? "" : this.i);
            jSONObject.put("d", "");
            jSONObject.put("mc", this.t == null ? "" : this.t);
            jSONObject.put("bm", this.v == null ? "" : this.v);
            jSONObject.put("dd", this.j == null ? "" : this.j);
            jSONObject.put("ii", this.g == null ? "" : this.g);
            jSONObject.put("tg", 1);
            jSONObject.put("w", this.k);
            jSONObject.put("h", this.l);
            jSONObject.put("c", this.m == null ? "" : this.m);
            jSONObject.put("op", this.n == null ? "" : this.n);
            jSONObject.put("m", this.o == null ? "" : this.o);
            jSONObject.put("ma", this.p == null ? "" : this.p);
            jSONObject.put("cl", this.q);
            jSONObject.put("gl", this.r == null ? "" : this.r);
            jSONObject.put("l", this.s == null ? "" : this.s);
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put("sq", 0);
            jSONObject.put("pn", cu.f(1, context));
            jSONObject.put("pl", cu.p(context));
            cr.a("*******" + jSONObject.toString() + "; len: " + jSONObject.length());
        } catch (JSONException e) {
            cr.a("header ini error");
        }
    }
}

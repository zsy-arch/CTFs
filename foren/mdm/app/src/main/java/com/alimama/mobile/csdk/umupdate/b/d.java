package com.alimama.mobile.csdk.umupdate.b;

import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alimama.mobile.csdk.umupdate.a.g;
import com.alimama.mobile.csdk.umupdate.a.j;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import com.hyphenate.util.HanziToPinyin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;
import u.upd.e;

/* compiled from: XpReportRequest.java */
/* loaded from: classes.dex */
public class d extends e {
    public static String[] b;
    private static final String c = d.class.getName();
    public Map<String, Object> a;

    public d(Map<String, Object> map) {
        this.a = map;
    }

    public void a() {
        new c().sendAsync(this, null);
    }

    @Override // u.upd.h
    public String getHttpMethod() {
        return GET;
    }

    @Override // u.upd.h
    public JSONObject toJson() {
        return new JSONObject(this.a);
    }

    public static d a(JSONObject jSONObject) throws JSONException {
        Iterator<String> keys = jSONObject.keys();
        HashMap hashMap = new HashMap();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.get(next));
        }
        return new d(hashMap);
    }

    @Override // u.upd.h
    public String toGetUrl() {
        return j.a(a.d[0], this.a).toString();
    }

    public String b() {
        HashMap hashMap = new HashMap();
        for (String str : this.a.keySet()) {
            if (!str.equals("date") && !str.equals(f.bx) && !str.equals(f.az) && !str.equals("ts")) {
                hashMap.put(str, this.a.get(str));
            }
        }
        return j.a(a.d[0], hashMap);
    }

    /* compiled from: XpReportRequest.java */
    /* loaded from: classes.dex */
    public static class a {
        private static final Random k = new Random();
        private static final int l = 32767;
        private MMEntity b;
        private String c;
        private String d;
        private String e;
        private int f;
        private int g;
        private int h;
        private String i;
        private String j;
        private int m = 0;
        private String n = "";
        private int o = 1;
        List<Promoter> a = new ArrayList();

        public a(MMEntity mMEntity) {
            this.b = mMEntity;
        }

        public a a(String str) {
            this.i = str;
            return this;
        }

        public a b(String str) {
            this.j = str;
            return this;
        }

        public a a(int i) {
            this.f = i;
            return this;
        }

        public a b(int i) {
            this.g = i;
            return this;
        }

        public a c(int i) {
            this.h = i;
            return this;
        }

        public a d(int i) {
            this.o = i;
            return this;
        }

        public a e(int i) {
            this.m = i;
            return this;
        }

        public a a(Promoter... promoterArr) {
            for (Promoter promoter : promoterArr) {
                this.a.add(promoter);
            }
            if (this.a != null && this.a.size() > 0) {
                int size = this.a.size();
                try {
                    Promoter promoter2 = this.a.get(0);
                    if (this.a.size() == 1) {
                        this.n = promoter2.prom_act_pams;
                        g.c("set promoter act_pams to report act_params. [" + this.n + "]", new Object[0]);
                    } else {
                        this.n = promoter2.slot_act_pams;
                        g.c("set slot act_pams to report act_params. [" + this.n + "]", new Object[0]);
                    }
                } catch (NullPointerException e) {
                }
                StringBuffer stringBuffer = new StringBuffer();
                StringBuffer stringBuffer2 = new StringBuffer();
                for (int i = 0; i < size; i++) {
                    Promoter promoter3 = this.a.get(i);
                    stringBuffer.append(promoter3.promoter + ",");
                    stringBuffer2.append(promoter3.category + ",");
                }
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                this.d = stringBuffer.toString();
                this.e = stringBuffer2.toString();
            }
            return this;
        }

        public d a() {
            return new d(b());
        }

        public Map<String, Object> b() {
            this.c = com.alimama.mobile.a.a().b().l() ? "0" : "1";
            return c();
        }

        private boolean a(Map<String, Object> map) {
            if (d.b == null) {
                d.b = new String[]{f.aP, f.o, "device_id", "idmd5", "mc", f.bx, f.by, f.bz, f.az, "date", "access", "access_subtype"};
            }
            if (map == null || map.size() == 0) {
                return false;
            }
            String[] strArr = d.b;
            boolean z = true;
            for (String str : strArr) {
                if (!map.containsKey(str)) {
                    g.e(com.alimama.mobile.csdk.umupdate.a.e.e, "Report params has no required param [" + str + "]");
                    z = false;
                }
            }
            return z;
        }

        private Map<String, Object> c() {
            com.alimama.mobile.csdk.umupdate.a.a b = com.alimama.mobile.a.a().b();
            HashMap hashMap = new HashMap();
            try {
                if (!TextUtils.isEmpty(this.b.slotId)) {
                    hashMap.put(f.C, this.b.slotId);
                } else if (!TextUtils.isEmpty(this.b.appkey)) {
                    hashMap.put("app_key", this.b.appkey);
                } else {
                    g.d(com.alimama.mobile.csdk.umupdate.a.e.e, "Both APPKEY and SLOTID are empty, please specify either one. Request aborted.");
                    return null;
                }
                String timeConsuming = this.b.getTimeConsuming();
                if (!TextUtils.isEmpty(timeConsuming)) {
                    hashMap.put(f.bL, timeConsuming);
                }
                if (!TextUtils.isEmpty(this.i)) {
                    hashMap.put(f.au, this.i);
                }
                if (!TextUtils.isEmpty(this.b.tabId)) {
                    hashMap.put(f.av, this.b.tabId);
                }
                if (!TextUtils.isEmpty(this.j)) {
                    hashMap.put(f.aw, this.j);
                }
                hashMap.put("sdk_version", com.alimama.mobile.csdk.umupdate.a.e.b);
                hashMap.put(f.z, com.alimama.mobile.csdk.umupdate.a.e.c);
                hashMap.put("ts", Long.valueOf(System.currentTimeMillis()));
                hashMap.put("device_model", Build.MODEL);
                if (!TextUtils.isEmpty(this.n)) {
                    try {
                        String[] split = this.n.split(com.alipay.sdk.sys.a.b);
                        HashMap hashMap2 = new HashMap();
                        for (String str : split) {
                            String[] split2 = str.split("=");
                            if (split2.length == 2) {
                                hashMap2.put(split2[0], split2[1]);
                            }
                        }
                        for (String str2 : hashMap2.keySet()) {
                            hashMap.put(str2, hashMap2.get(str2));
                        }
                    } catch (Exception e) {
                    }
                }
                String p = b.p();
                if (!TextUtils.isEmpty(p)) {
                    hashMap.put("mc", p);
                }
                hashMap.put("carrier", b.E());
                if (this.b.module != null) {
                    hashMap.put(f.aj, this.b.module);
                }
                hashMap.put("os_version", Build.VERSION.RELEASE);
                hashMap.put("os", f.a);
                hashMap.put(f.A, Integer.valueOf(k.nextInt(l)));
                String[] C = b.C();
                hashMap.put("access", C[0]);
                hashMap.put("access_subtype", C[1]);
                if (!TextUtils.isEmpty(this.b.sid)) {
                    hashMap.put(f.o, this.b.sid);
                }
                if (!TextUtils.isEmpty(this.b.psid)) {
                    hashMap.put(f.ai, this.b.psid);
                }
                hashMap.put("device_id", b.r());
                hashMap.put("idmd5", j.c(b.r()));
                try {
                    Location D = b.D();
                    if (D != null) {
                        hashMap.put("lat", String.valueOf(D.getLatitude()));
                        hashMap.put("lng", String.valueOf(D.getLongitude()));
                        hashMap.put(f.Q, D.getProvider());
                        hashMap.put(f.O, String.valueOf(D.getTime()));
                        hashMap.put(f.P, String.valueOf(D.getAccuracy()));
                    }
                } catch (Exception e2) {
                }
                String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String str3 = format.split(HanziToPinyin.Token.SEPARATOR)[0];
                String str4 = format.split(HanziToPinyin.Token.SEPARATOR)[1];
                hashMap.put("date", str3);
                hashMap.put(f.az, str4);
                hashMap.put("timezone", b.o());
                hashMap.put(f.bw, this.c);
                hashMap.put("promoter", this.d != null ? this.d : "");
                hashMap.put(f.aP, this.e);
                hashMap.put(f.bx, Integer.valueOf(this.f));
                hashMap.put(f.by, Integer.valueOf(this.g));
                hashMap.put(f.bz, Integer.valueOf(this.b.layoutType));
                hashMap.put(f.bA, Integer.valueOf(this.h));
                String f = TextUtils.isEmpty(com.alimama.mobile.csdk.umupdate.a.e.d) ? b.f("MUNION_CHANNEL") : com.alimama.mobile.csdk.umupdate.a.e.d;
                if (!TextUtils.isEmpty(f)) {
                    hashMap.put("channel", f);
                }
                if (this.m != 0) {
                    hashMap.put(f.bC, Integer.valueOf(this.m));
                }
                hashMap.put(f.bD, Integer.valueOf(this.o));
                if (TextUtils.isEmpty(this.b.slotId)) {
                    String str5 = this.b.appkey;
                } else {
                    String str6 = this.b.slotId;
                }
                return hashMap;
            } catch (Exception e3) {
                throw new RuntimeException(e3);
            }
        }
    }
}

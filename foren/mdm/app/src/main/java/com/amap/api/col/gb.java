package com.amap.api.col;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.hyphenate.chat.MessageEncoder;
import com.vsf2f.f2f.ui.utils.Constant;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthConfigManager.java */
/* loaded from: classes.dex */
public class gb {
    public static int a = -1;
    public static String b = "";

    /* compiled from: AuthConfigManager.java */
    /* loaded from: classes.dex */
    public static class a {
        public b A;
        public String a;
        public int b = -1;
        public JSONObject c;
        public JSONObject d;
        public JSONObject e;
        public JSONObject f;
        public JSONObject g;
        public JSONObject h;
        public JSONObject i;
        public JSONObject j;
        public JSONObject k;
        public JSONObject l;
        public JSONObject m;
        public JSONObject n;
        public JSONObject o;
        public JSONObject p;
        public JSONObject q;
        public JSONObject r;
        public JSONObject s;
        public JSONObject t;

        /* renamed from: u */
        public C0014a f21u;
        public d v;
        public c w;
        public b x;
        public b y;
        public b z;

        /* compiled from: AuthConfigManager.java */
        /* renamed from: com.amap.api.col.gb$a$a */
        /* loaded from: classes.dex */
        public static class C0014a {
            public boolean a;
            public boolean b;
            public JSONObject c;
        }

        /* compiled from: AuthConfigManager.java */
        /* loaded from: classes.dex */
        public static class b {
            public boolean a;
            public String b;
            public String c;
            public String d;
        }

        /* compiled from: AuthConfigManager.java */
        /* loaded from: classes.dex */
        public static class c {
            public String a;
            public String b;
        }

        /* compiled from: AuthConfigManager.java */
        /* loaded from: classes.dex */
        public static class d {
            public String a;
            public String b;
            public String c;
        }
    }

    @Deprecated
    public static void a(String str) {
        a((Context) null, str);
    }

    public static void a(Context context, String str) {
        ga.a(context, str);
    }

    public static boolean a(String str, boolean z) {
        try {
            String[] split = URLDecoder.decode(str).split("/");
            if (split[split.length - 1].charAt(4) % 2 == 1) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return z;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amap.api.col.gb.a a(android.content.Context r11, com.amap.api.col.gj r12, java.lang.String r13, java.util.Map<java.lang.String, java.lang.String> r14) {
        /*
            Method dump skipped, instructions count: 827
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.gb.a(android.content.Context, com.amap.api.col.gj, java.lang.String, java.util.Map):com.amap.api.col.gb$a");
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null && jSONObject.has(str) && !jSONObject.getString(str).equals("[]")) {
            return jSONObject.optString(str);
        }
        return "";
    }

    private static void a(JSONObject jSONObject, a.b bVar) {
        if (bVar != null) {
            try {
                String a2 = a(jSONObject, "m");
                String a3 = a(jSONObject, "u");
                String a4 = a(jSONObject, "v");
                String a5 = a(jSONObject, "able");
                bVar.c = a2;
                bVar.b = a3;
                bVar.d = a4;
                bVar.a = a(a5, false);
            } catch (Throwable th) {
                go.a(th, "ConfigManager", "parsePluginEntity");
            }
        }
    }

    private static void a(JSONObject jSONObject, a.c cVar) {
        if (jSONObject != null) {
            try {
                String a2 = a(jSONObject, "md5");
                String a3 = a(jSONObject, "url");
                cVar.b = a2;
                cVar.a = a3;
            } catch (Throwable th) {
                go.a(th, "ConfigManager", "parseSDKCoordinate");
            }
        }
    }

    private static void a(JSONObject jSONObject, a.d dVar) {
        if (jSONObject != null) {
            try {
                String a2 = a(jSONObject, "md5");
                String a3 = a(jSONObject, "url");
                String a4 = a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4)) {
                    dVar.a = a3;
                    dVar.b = a2;
                    dVar.c = a4;
                }
            } catch (Throwable th) {
                go.a(th, "ConfigManager", "parseSDKUpdate");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AuthConfigManager.java */
    /* loaded from: classes.dex */
    public static class b extends ib {
        private String a;
        private Map<String, String> d;
        private boolean e;

        b(Context context, gj gjVar, String str, Map<String, String> map) {
            super(context, gjVar);
            this.a = str;
            this.d = map;
            this.e = Build.VERSION.SDK_INT != 19;
        }

        public boolean f() {
            return this.e;
        }

        @Override // com.amap.api.col.ig
        public Map<String, String> a() {
            return null;
        }

        @Override // com.amap.api.col.ig
        public String c() {
            return this.e ? "https://restapi.amap.com/v3/iasdkauth" : "http://restapi.amap.com/v3/iasdkauth";
        }

        @Override // com.amap.api.col.ib
        public byte[] e() {
            return null;
        }

        @Override // com.amap.api.col.ib
        public byte[] d() {
            return gk.a(gk.b(o()));
        }

        @Override // com.amap.api.col.ib
        protected String h() {
            return "3.0";
        }

        private Map<String, String> o() {
            String q = ge.q(this.b);
            if (TextUtils.isEmpty(q)) {
                q = ge.c();
            }
            if (!TextUtils.isEmpty(q)) {
                q = gg.b(new StringBuilder(q).reverse().toString());
            }
            HashMap hashMap = new HashMap();
            hashMap.put("authkey", this.a);
            hashMap.put("plattype", f.a);
            hashMap.put(Constant.PRODUCT, this.c.a());
            hashMap.put("version", this.c.b());
            hashMap.put("output", "json");
            hashMap.put("androidversion", Build.VERSION.SDK_INT + "");
            hashMap.put("deviceId", q);
            if (this.d != null && !this.d.isEmpty()) {
                hashMap.putAll(this.d);
            }
            hashMap.put("abitype", gk.a(this.b));
            hashMap.put(MessageEncoder.ATTR_EXT, this.c.e());
            return hashMap;
        }
    }
}

package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import java.net.URLDecoder;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthConfigManager.java */
/* loaded from: classes.dex */
public class ax {
    public static int a = -1;
    public static String b = "";

    /* compiled from: AuthConfigManager.java */
    /* loaded from: classes.dex */
    public static class a {
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
        public C0019a p;
        public d q;
        public c r;
        public b s;
        public b t;

        /* renamed from: u  reason: collision with root package name */
        public b f26u;
        public b v;

        /* compiled from: AuthConfigManager.java */
        /* renamed from: com.amap.api.services.a.ax$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0019a {
            public boolean a;
            public boolean b;
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

    /* JADX WARN: Removed duplicated region for block: B:11:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amap.api.services.a.ax.a a(android.content.Context r11, com.amap.api.services.a.be r12, java.lang.String r13, java.util.Map<java.lang.String, java.lang.String> r14) {
        /*
            Method dump skipped, instructions count: 740
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.ax.a(android.content.Context, com.amap.api.services.a.be, java.lang.String, java.util.Map):com.amap.api.services.a.ax$a");
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
                bh.a(th, "ConfigManager", "parsePluginEntity");
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
                bh.a(th, "ConfigManager", "parseSDKCoordinate");
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
                bh.a(th, "ConfigManager", "parseSDKUpdate");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AuthConfigManager.java */
    /* loaded from: classes.dex */
    public static class b extends cu {
        private String c;
        private Map<String, String> d;

        b(Context context, be beVar, String str, Map<String, String> map) {
            super(context, beVar);
            this.c = str;
            this.d = map;
        }

        @Override // com.amap.api.services.a.cz
        public Map<String, String> c() {
            return null;
        }

        @Override // com.amap.api.services.a.cz
        public String g() {
            return "https://restapi.amap.com/v3/iasdkauth";
        }

        @Override // com.amap.api.services.a.cu
        public byte[] a() {
            return null;
        }

        @Override // com.amap.api.services.a.cu
        public byte[] d() {
            return bf.a(bf.a(m()));
        }

        @Override // com.amap.api.services.a.cu
        protected String e() {
            return "3.0";
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x00aa  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.util.Map<java.lang.String, java.lang.String> m() {
            /*
                r5 = this;
                android.content.Context r0 = r5.a
                java.lang.String r0 = com.amap.api.services.a.ba.q(r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L_0x001d
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                java.lang.StringBuilder r0 = r1.reverse()
                java.lang.String r0 = r0.toString()
                java.lang.String r0 = com.amap.api.services.a.bc.b(r0)
            L_0x001d:
                java.util.HashMap r2 = new java.util.HashMap
                r2.<init>()
                java.lang.String r1 = "authkey"
                java.lang.String r3 = r5.c
                r2.put(r1, r3)
                java.lang.String r1 = "plattype"
                java.lang.String r3 = "android"
                r2.put(r1, r3)
                java.lang.String r1 = "product"
                com.amap.api.services.a.be r3 = r5.b
                java.lang.String r3 = r3.a()
                r2.put(r1, r3)
                java.lang.String r1 = "version"
                com.amap.api.services.a.be r3 = r5.b
                java.lang.String r3 = r3.b()
                r2.put(r1, r3)
                java.lang.String r1 = "output"
                java.lang.String r3 = "json"
                r2.put(r1, r3)
                java.lang.String r1 = "androidversion"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                int r4 = android.os.Build.VERSION.SDK_INT
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r4 = ""
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                r2.put(r1, r3)
                java.lang.String r1 = "deviceId"
                r2.put(r1, r0)
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.d
                if (r0 == 0) goto L_0x007d
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.d
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x007d
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.d
                r2.putAll(r0)
            L_0x007d:
                r1 = 0
                int r0 = android.os.Build.VERSION.SDK_INT
                r3 = 21
                if (r0 < r3) goto L_0x00c5
                android.content.Context r0 = r5.a     // Catch: Throwable -> 0x00bd
                android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()     // Catch: Throwable -> 0x00bd
                java.lang.Class<android.content.pm.ApplicationInfo> r3 = android.content.pm.ApplicationInfo.class
                java.lang.String r3 = r3.getName()     // Catch: Throwable -> 0x00bd
                java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: Throwable -> 0x00bd
                java.lang.String r4 = "primaryCpuAbi"
                java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch: Throwable -> 0x00bd
                r4 = 1
                r3.setAccessible(r4)     // Catch: Throwable -> 0x00bd
                java.lang.Object r0 = r3.get(r0)     // Catch: Throwable -> 0x00bd
                java.lang.String r0 = (java.lang.String) r0     // Catch: Throwable -> 0x00bd
            L_0x00a4:
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 == 0) goto L_0x00ac
                java.lang.String r0 = android.os.Build.CPU_ABI
            L_0x00ac:
                java.lang.String r1 = "abitype"
                r2.put(r1, r0)
                java.lang.String r0 = "ext"
                com.amap.api.services.a.be r1 = r5.b
                java.lang.String r1 = r1.d()
                r2.put(r0, r1)
                return r2
            L_0x00bd:
                r0 = move-exception
                java.lang.String r3 = "ConfigManager"
                java.lang.String r4 = "getcpu"
                com.amap.api.services.a.bh.a(r0, r3, r4)
            L_0x00c5:
                r0 = r1
                goto L_0x00a4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.ax.b.m():java.util.Map");
        }
    }
}

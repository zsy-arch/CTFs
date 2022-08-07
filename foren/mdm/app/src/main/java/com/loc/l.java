package com.loc;

import android.content.Context;
import java.net.URLDecoder;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: AuthConfigManager.java */
/* loaded from: classes.dex */
public final class l {
    public static int a = -1;
    public static String b = "";

    /* compiled from: AuthConfigManager.java */
    /* loaded from: classes2.dex */
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
        public C0048a p;
        public d q;
        public c r;
        public b s;
        public b t;

        /* renamed from: u  reason: collision with root package name */
        public b f39u;
        public b v;

        /* compiled from: AuthConfigManager.java */
        /* renamed from: com.loc.l$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static class C0048a {
            public boolean a;
            public boolean b;
            public JSONObject c;
        }

        /* compiled from: AuthConfigManager.java */
        /* loaded from: classes2.dex */
        public static class b {
            public boolean a;
            public String b;
            public String c;
            public String d;
        }

        /* compiled from: AuthConfigManager.java */
        /* loaded from: classes2.dex */
        public static class c {
            public String a;
            public String b;
        }

        /* compiled from: AuthConfigManager.java */
        /* loaded from: classes2.dex */
        public static class d {
            public String a;
            public String b;
            public String c;
        }
    }

    /* compiled from: AuthConfigManager.java */
    /* loaded from: classes2.dex */
    static class b extends bj {
        private String f;
        private Map<String, String> g = null;

        b(Context context, s sVar, String str) {
            super(context, sVar);
            this.f = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x00a4  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.util.Map<java.lang.String, java.lang.String> h() {
            /*
                r5 = this;
                android.content.Context r0 = r5.a
                java.lang.String r0 = com.loc.n.q(r0)
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 != 0) goto L_0x001d
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>(r0)
                java.lang.StringBuilder r0 = r1.reverse()
                java.lang.String r0 = r0.toString()
                java.lang.String r0 = com.loc.p.b(r0)
            L_0x001d:
                java.util.HashMap r2 = new java.util.HashMap
                r2.<init>()
                java.lang.String r1 = "authkey"
                java.lang.String r3 = r5.f
                r2.put(r1, r3)
                java.lang.String r1 = "plattype"
                java.lang.String r3 = "android"
                r2.put(r1, r3)
                java.lang.String r1 = "product"
                com.loc.s r3 = r5.b
                java.lang.String r3 = r3.a()
                r2.put(r1, r3)
                java.lang.String r1 = "version"
                com.loc.s r3 = r5.b
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
                java.lang.String r3 = r3.toString()
                r2.put(r1, r3)
                java.lang.String r1 = "deviceId"
                r2.put(r1, r0)
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.g
                if (r0 == 0) goto L_0x0077
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.g
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L_0x0077
                java.util.Map<java.lang.String, java.lang.String> r0 = r5.g
                r2.putAll(r0)
            L_0x0077:
                r1 = 0
                int r0 = android.os.Build.VERSION.SDK_INT
                r3 = 21
                if (r0 < r3) goto L_0x00bf
                android.content.Context r0 = r5.a     // Catch: Throwable -> 0x00b7
                android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo()     // Catch: Throwable -> 0x00b7
                java.lang.Class<android.content.pm.ApplicationInfo> r3 = android.content.pm.ApplicationInfo.class
                java.lang.String r3 = r3.getName()     // Catch: Throwable -> 0x00b7
                java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch: Throwable -> 0x00b7
                java.lang.String r4 = "primaryCpuAbi"
                java.lang.reflect.Field r3 = r3.getDeclaredField(r4)     // Catch: Throwable -> 0x00b7
                r4 = 1
                r3.setAccessible(r4)     // Catch: Throwable -> 0x00b7
                java.lang.Object r0 = r3.get(r0)     // Catch: Throwable -> 0x00b7
                java.lang.String r0 = (java.lang.String) r0     // Catch: Throwable -> 0x00b7
            L_0x009e:
                boolean r1 = android.text.TextUtils.isEmpty(r0)
                if (r1 == 0) goto L_0x00a6
                java.lang.String r0 = android.os.Build.CPU_ABI
            L_0x00a6:
                java.lang.String r1 = "abitype"
                r2.put(r1, r0)
                java.lang.String r0 = "ext"
                com.loc.s r1 = r5.b
                java.lang.String r1 = r1.d()
                r2.put(r0, r1)
                return r2
            L_0x00b7:
                r0 = move-exception
                java.lang.String r3 = "ConfigManager"
                java.lang.String r4 = "getcpu"
                com.loc.w.a(r0, r3, r4)
            L_0x00bf:
                r0 = r1
                goto L_0x009e
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.l.b.h():java.util.Map");
        }

        @Override // com.loc.bn
        public final Map<String, String> a() {
            return null;
        }

        @Override // com.loc.bj
        public final byte[] a_() {
            return null;
        }

        @Override // com.loc.bn
        public final String b() {
            return "https://restapi.amap.com/v3/iasdkauth";
        }

        @Override // com.loc.bj
        public final byte[] b_() {
            return t.a(t.a(h()));
        }

        @Override // com.loc.bj
        protected final String e() {
            return "3.0";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.loc.l.a a(android.content.Context r11, com.loc.s r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 834
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.l.a(android.content.Context, com.loc.s, java.lang.String):com.loc.l$a");
    }

    private static String a(JSONObject jSONObject, String str) throws JSONException {
        return (jSONObject != null && jSONObject.has(str) && !jSONObject.getString(str).equals("[]")) ? jSONObject.optString(str) : "";
    }

    @Deprecated
    public static void a(String str) {
        k.b(str);
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
                w.a(th, "ConfigManager", "parsePluginEntity");
            }
        }
    }

    public static boolean a(String str, boolean z) {
        try {
            String[] split = URLDecoder.decode(str).split("/");
            return split[split.length + (-1)].charAt(4) % 2 == 1;
        } catch (Throwable th) {
            return z;
        }
    }
}

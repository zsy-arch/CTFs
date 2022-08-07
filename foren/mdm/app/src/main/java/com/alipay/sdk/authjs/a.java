package com.alipay.sdk.authjs;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class a {
    public static final String a = "CallInfo";
    public static final String b = "call";
    public static final String c = "callback";
    public static final String d = "bundleName";
    public static final String e = "clientId";
    public static final String f = "param";
    public static final String g = "func";
    public static final String h = "msgType";
    public String i;
    public String j;
    public String k;
    public String l;
    public JSONObject m;
    private boolean n = false;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* renamed from: com.alipay.sdk.authjs.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static final class EnumC0009a extends Enum<EnumC0009a> {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        private static final /* synthetic */ int[] f = {a, b, c, d, e};

        private EnumC0009a(String str, int i) {
        }

        public static int[] a() {
            return (int[]) f.clone();
        }
    }

    /* renamed from: com.alipay.sdk.authjs.a$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[EnumC0009a.a().length];

        static {
            try {
                a[EnumC0009a.b - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[EnumC0009a.c - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[EnumC0009a.d - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static String a(int i) {
        switch (AnonymousClass1.a[i - 1]) {
            case 1:
                return "function not found";
            case 2:
                return "invalid parameter";
            case 3:
                return "runtime error";
            default:
                return "none";
        }
    }

    private boolean a() {
        return this.n;
    }

    private void a(boolean z) {
        this.n = z;
    }

    public a(String str) {
        this.l = str;
    }

    private String b() {
        return this.i;
    }

    private void a(String str) {
        this.i = str;
    }

    private String c() {
        return this.j;
    }

    private void b(String str) {
        this.j = str;
    }

    private String d() {
        return this.k;
    }

    private void c(String str) {
        this.k = str;
    }

    private String e() {
        return this.l;
    }

    private void d(String str) {
        this.l = str;
    }

    private JSONObject f() {
        return this.m;
    }

    private void a(JSONObject jSONObject) {
        this.m = jSONObject;
    }

    private String g() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(e, this.i);
        jSONObject.put(g, this.k);
        jSONObject.put("param", this.m);
        jSONObject.put(h, this.l);
        return jSONObject.toString();
    }
}

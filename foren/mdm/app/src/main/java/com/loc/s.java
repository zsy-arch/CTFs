package com.loc;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SDKInfo.java */
@ag(a = "a")
/* loaded from: classes.dex */
public class s {
    @ah(a = "a1", b = 6)
    private String a;
    @ah(a = "a2", b = 6)
    private String b;
    @ah(a = "a6", b = 2)
    private int c;
    @ah(a = "a3", b = 6)
    private String d;
    @ah(a = "a4", b = 6)
    private String e;
    @ah(a = "a5", b = 6)
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String[] l;

    /* compiled from: SDKInfo.java */
    /* loaded from: classes2.dex */
    public static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private boolean e = true;
        private String f = "standard";
        private String[] g = null;

        public a(String str, String str2, String str3) {
            this.a = str2;
            this.b = str2;
            this.d = str3;
            this.c = str;
        }

        public final a a(String str) {
            this.b = str;
            return this;
        }

        public final a a(String[] strArr) {
            this.g = (String[]) strArr.clone();
            return this;
        }

        public final s a() throws j {
            if (this.g != null) {
                return new s(this, (byte) 0);
            }
            throw new j("sdk packages is null");
        }
    }

    private s() {
        this.c = 1;
        this.l = null;
    }

    private s(a aVar) {
        int i = 1;
        this.c = 1;
        this.l = null;
        this.g = aVar.a;
        this.h = aVar.b;
        this.j = aVar.c;
        this.i = aVar.d;
        this.c = !aVar.e ? 0 : i;
        this.k = aVar.f;
        this.l = aVar.g;
        this.b = t.b(this.h);
        this.a = t.b(this.j);
        this.d = t.b(this.i);
        this.e = t.b(a(this.l));
        this.f = t.b(this.k);
    }

    /* synthetic */ s(a aVar, byte b) {
        this(aVar);
    }

    public static String a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("a1", t.b(str));
        return af.a((Map<String, String>) hashMap);
    }

    private static String a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str).append(h.b);
            }
            return sb.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String[] b(String str) {
        try {
            return str.split(h.b);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String g() {
        return "a6=1";
    }

    public final String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.a)) {
            this.j = t.c(this.a);
        }
        return this.j;
    }

    public final void a(boolean z) {
        this.c = z ? 1 : 0;
    }

    public final String b() {
        return this.g;
    }

    public final String c() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.b)) {
            this.h = t.c(this.b);
        }
        return this.h;
    }

    public final String d() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f)) {
            this.k = t.c(this.f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "standard";
        }
        return this.k;
    }

    public final boolean e() {
        return this.c == 1;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return getClass() == obj.getClass() && hashCode() == ((s) obj).hashCode();
    }

    public final String[] f() {
        if ((this.l == null || this.l.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.l = b(t.c(this.e));
        }
        return (String[]) this.l.clone();
    }

    public int hashCode() {
        v vVar = new v();
        vVar.a(this.j).a(this.g).a(this.h).a((Object[]) this.l);
        return vVar.a();
    }
}

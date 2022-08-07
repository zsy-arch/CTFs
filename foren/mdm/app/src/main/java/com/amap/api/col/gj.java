package com.amap.api.col;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SDKInfo.java */
@gy(a = "a")
/* loaded from: classes.dex */
public class gj {
    @gz(a = "a1", b = 6)
    private String a;
    @gz(a = "a2", b = 6)
    private String b;
    @gz(a = "a6", b = 2)
    private int c;
    @gz(a = "a3", b = 6)
    private String d;
    @gz(a = "a4", b = 6)
    private String e;
    @gz(a = "a5", b = 6)
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String[] l;

    private gj() {
        this.c = 1;
        this.l = null;
    }

    private gj(a aVar) {
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
        this.b = gk.b(this.h);
        this.a = gk.b(this.j);
        this.d = gk.b(this.i);
        this.e = gk.b(a(this.l));
        this.f = gk.b(this.k);
    }

    /* compiled from: SDKInfo.java */
    /* loaded from: classes.dex */
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

        public a a(String[] strArr) {
            this.g = (String[]) strArr.clone();
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public gj a() throws fz {
            if (this.g != null) {
                return new gj(this);
            }
            throw new fz("sdk packages is null");
        }
    }

    public void a(boolean z) {
        this.c = z ? 1 : 0;
    }

    public String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.a)) {
            this.j = gk.c(this.a);
        }
        return this.j;
    }

    public String b() {
        return this.g;
    }

    public String c() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.b)) {
            this.h = gk.c(this.b);
        }
        return this.h;
    }

    public String d() {
        if (TextUtils.isEmpty(this.i) && !TextUtils.isEmpty(this.d)) {
            this.i = gk.c(this.d);
        }
        return this.i;
    }

    public String e() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f)) {
            this.k = gk.c(this.f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "standard";
        }
        return this.k;
    }

    public boolean f() {
        return this.c == 1;
    }

    public String[] g() {
        if ((this.l == null || this.l.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.l = b(gk.c(this.e));
        }
        return (String[]) this.l.clone();
    }

    private String[] b(String str) {
        try {
            return str.split(h.b);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private String a(String[] strArr) {
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

    public static String a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("a1", gk.b(str));
        return gx.a((Map<String, String>) hashMap);
    }

    public static String h() {
        return "a6=1";
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (hashCode() != ((gj) obj).hashCode()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        gn gnVar = new gn();
        gnVar.a(this.j).a(this.g).a(this.h).a((Object[]) this.l);
        return gnVar.a();
    }
}

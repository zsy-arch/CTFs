package com.amap.api.services.a;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SDKInfo.java */
@br(a = "a")
/* loaded from: classes.dex */
public class be {
    @bs(a = "a1", b = 6)
    private String a;
    @bs(a = "a2", b = 6)
    private String b;
    @bs(a = "a6", b = 2)
    private int c;
    @bs(a = "a3", b = 6)
    private String d;
    @bs(a = "a4", b = 6)
    private String e;
    @bs(a = "a5", b = 6)
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String[] l;

    private be() {
        this.c = 1;
        this.l = null;
    }

    private be(a aVar) {
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
        this.b = bf.b(this.h);
        this.a = bf.b(this.j);
        this.d = bf.b(this.i);
        this.e = bf.b(a(this.l));
        this.f = bf.b(this.k);
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

        public a a(boolean z) {
            this.e = z;
            return this;
        }

        public a a(String[] strArr) {
            this.g = (String[]) strArr.clone();
            return this;
        }

        public a a(String str) {
            this.b = str;
            return this;
        }

        public be a() throws av {
            if (this.g != null) {
                return new be(this);
            }
            throw new av("sdk packages is null");
        }
    }

    public String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.a)) {
            this.j = bf.c(this.a);
        }
        return this.j;
    }

    public String b() {
        return this.g;
    }

    public String c() {
        if (TextUtils.isEmpty(this.h) && !TextUtils.isEmpty(this.b)) {
            this.h = bf.c(this.b);
        }
        return this.h;
    }

    public String d() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f)) {
            this.k = bf.c(this.f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "standard";
        }
        return this.k;
    }

    public String[] e() {
        if ((this.l == null || this.l.length == 0) && !TextUtils.isEmpty(this.e)) {
            this.l = b(bf.c(this.e));
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
        hashMap.put("a1", bf.b(str));
        return bq.a((Map<String, String>) hashMap);
    }

    public static String f() {
        return "a6=1";
    }
}

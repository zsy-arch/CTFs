package com.amap.api.services.a;

import java.util.HashMap;
import java.util.Map;

/* compiled from: DynamicPlugin.java */
@br(a = "file")
/* loaded from: classes.dex */
public class cn {
    @bs(a = "fname", b = 6)
    private String a;
    @bs(a = "md", b = 6)
    private String b;
    @bs(a = "sname", b = 6)
    private String c;
    @bs(a = "version", b = 6)
    private String d;
    @bs(a = "dversion", b = 6)
    private String e;
    @bs(a = "status", b = 6)
    private String f;

    public cn(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    private cn() {
    }

    public static String a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("dversion", str2);
        return bq.a((Map<String, String>) hashMap);
    }

    public static String b(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("status", str2);
        return bq.a((Map<String, String>) hashMap);
    }

    public static String a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("sname", str);
        return bq.a((Map<String, String>) hashMap);
    }

    public static String b(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("fname", str);
        return bq.a((Map<String, String>) hashMap);
    }

    public static String a(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("fname", str);
        hashMap.put("sname", str2);
        hashMap.put("dversion", str4);
        hashMap.put("version", str3);
        return bq.a((Map<String, String>) hashMap);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    /* compiled from: DynamicPlugin.java */
    /* loaded from: classes.dex */
    public static class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f = "copy";

        public a(String str, String str2, String str3, String str4, String str5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
            this.e = str5;
        }

        public a a(String str) {
            this.f = str;
            return this;
        }

        public cn a() {
            return new cn(this);
        }
    }
}

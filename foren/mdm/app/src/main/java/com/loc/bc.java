package com.loc;

import java.util.HashMap;
import java.util.Map;

/* compiled from: DynamicPlugin.java */
@ag(a = "file")
/* loaded from: classes.dex */
public class bc {
    @ah(a = "fname", b = 6)
    private String a;
    @ah(a = "md", b = 6)
    private String b;
    @ah(a = "sname", b = 6)
    private String c;
    @ah(a = "version", b = 6)
    private String d;
    @ah(a = "dversion", b = 6)
    private String e;
    @ah(a = "status", b = 6)
    private String f;

    /* compiled from: DynamicPlugin.java */
    /* loaded from: classes2.dex */
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

        public final a a(String str) {
            this.f = str;
            return this;
        }

        public final bc a() {
            return new bc(this);
        }
    }

    private bc() {
    }

    public bc(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    public static String a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("sname", str);
        return af.a((Map<String, String>) hashMap);
    }

    public static String a(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("dversion", str2);
        return af.a((Map<String, String>) hashMap);
    }

    public static String a(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("fname", str);
        hashMap.put("sname", str2);
        hashMap.put("dversion", str4);
        hashMap.put("version", str3);
        return af.a((Map<String, String>) hashMap);
    }

    public static String b(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("fname", str);
        return af.a((Map<String, String>) hashMap);
    }

    public static String b(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("sname", str);
        hashMap.put("status", str2);
        return af.a((Map<String, String>) hashMap);
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }
}

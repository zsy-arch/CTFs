package com.amap.api.col;

import java.util.HashMap;
import java.util.Map;

/* compiled from: LogInfo.java */
/* loaded from: classes.dex */
public abstract class hg {
    @gz(a = "b1", b = 6)
    protected String b;
    @gz(a = "a1", b = 6)
    private String d;
    @gz(a = "b2", b = 2)
    protected int a = -1;
    @gz(a = "b3", b = 2)
    protected int c = 1;

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.d = gk.b(str);
    }

    public int c() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public static String c(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("b1", str);
        return gx.a((Map<String, String>) hashMap);
    }

    public static String c(int i) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("b2").append("=").append(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }
}

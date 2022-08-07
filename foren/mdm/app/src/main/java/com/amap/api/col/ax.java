package com.amap.api.col;

/* compiled from: DTInfo.java */
@gy(a = "update_item")
/* loaded from: classes.dex */
public class ax {
    @gz(a = "localPath", b = 6)
    protected String h;
    @gz(a = "mCompleteCode", b = 2)
    protected int j;
    @gz(a = "mState", b = 2)
    public int l;
    @gz(a = "title", b = 6)
    protected String a = null;
    @gz(a = "url", b = 6)
    protected String b = null;
    @gz(a = "mAdcode", b = 6)
    protected String c = null;
    @gz(a = "fileName", b = 6)
    protected String d = null;
    @gz(a = "version", b = 6)
    protected String e = "";
    @gz(a = "lLocalLength", b = 5)
    protected long f = 0;
    @gz(a = "lRemoteLength", b = 5)
    protected long g = 0;
    @gz(a = "isProvince", b = 2)
    protected int i = 0;
    @gz(a = "mCityCode", b = 6)
    protected String k = "";
    @gz(a = "mPinyin", b = 6)
    public String m = "";

    public String d() {
        return this.a;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public int g() {
        return this.j;
    }

    public void d(String str) {
        this.k = str;
    }

    public String h() {
        return this.m;
    }

    public static String e(String str) {
        return "mAdcode='" + str + "'";
    }

    public static String f(String str) {
        return "mPinyin='" + str + "'";
    }
}

package com.amap.api.col;

/* compiled from: DTFileInfo.java */
@gy(a = "update_item_file")
/* loaded from: classes.dex */
class aw {
    @gz(a = "mAdcode", b = 6)
    private String a;
    @gz(a = "file", b = 6)
    private String b;

    public aw() {
        this.a = "";
        this.b = "";
    }

    public aw(String str, String str2) {
        this.a = "";
        this.b = "";
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.b;
    }

    public static String a(String str) {
        return "mAdcode='" + str + "'";
    }
}

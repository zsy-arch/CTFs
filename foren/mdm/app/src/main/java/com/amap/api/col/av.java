package com.amap.api.col;

/* compiled from: DTDownloadInfo.java */
@gy(a = "update_item_download_info")
/* loaded from: classes.dex */
class av {
    @gz(a = "mAdcode", b = 6)
    private String a;
    @gz(a = "fileLength", b = 5)
    private long b;
    @gz(a = "splitter", b = 2)
    private int c;
    @gz(a = "startPos", b = 5)
    private long d;
    @gz(a = "endPos", b = 5)
    private long e;

    public av() {
        this.a = "";
        this.b = 0L;
        this.c = 0;
        this.d = 0L;
        this.e = 0L;
    }

    public av(String str, long j, int i, long j2, long j3) {
        this.a = "";
        this.b = 0L;
        this.c = 0;
        this.d = 0L;
        this.e = 0L;
        this.a = str;
        this.b = j;
        this.c = i;
        this.d = j2;
        this.e = j3;
    }

    public static String a(String str) {
        return "mAdcode='" + str + "'";
    }
}

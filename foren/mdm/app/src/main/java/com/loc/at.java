package com.loc;

/* compiled from: DexDownloadItem.java */
/* loaded from: classes2.dex */
public final class at {
    String a;
    String b;
    String c;
    String d;
    String e;
    int f;
    int g;
    private String h;
    private String i;

    public at(String str, String str2) {
        this.h = str;
        this.i = str2;
        try {
            String[] split = str.split("/");
            int length = split.length;
            if (length > 1) {
                this.a = split[length - 1];
                String[] split2 = this.a.split("_");
                this.b = split2[0];
                this.c = split2[2];
                this.d = split2[1];
                this.f = Integer.parseInt(split2[3]);
                this.g = Integer.parseInt(split2[4].split("\\.")[0]);
            }
        } catch (Throwable th) {
            w.a(th, "DexDownloadItem", "DexDownloadItem");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String a() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String b() {
        return this.i;
    }
}

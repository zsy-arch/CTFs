package com.amap.api.col;

import java.util.HashMap;
import java.util.Map;

/* compiled from: LogUpdateRequest.java */
/* loaded from: classes.dex */
public class gq extends ig {
    private byte[] a;
    private String b;

    public gq(byte[] bArr) {
        this.b = "1";
        this.a = (byte[]) bArr.clone();
    }

    public gq(byte[] bArr, String str) {
        this.b = "1";
        this.a = (byte[]) bArr.clone();
        this.b = str;
    }

    private String d() {
        byte[] a = gk.a(gm.a);
        byte[] bArr = new byte[a.length + 50];
        System.arraycopy(this.a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return gg.a(bArr);
    }

    @Override // com.amap.api.col.ig
    public Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/zip");
        hashMap.put("Content-Length", String.valueOf(this.a.length));
        return hashMap;
    }

    @Override // com.amap.api.col.ig
    public Map<String, String> b() {
        return null;
    }

    @Override // com.amap.api.col.ig
    public String c() {
        return String.format(gm.b, "1", this.b, "1", "open", d());
    }

    @Override // com.amap.api.col.ig
    public byte[] g() {
        return this.a;
    }
}

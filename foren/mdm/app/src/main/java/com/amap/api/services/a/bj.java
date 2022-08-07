package com.amap.api.services.a;

import java.util.HashMap;
import java.util.Map;

/* compiled from: LogUpdateRequest.java */
/* loaded from: classes.dex */
public class bj extends cz {
    private byte[] a;
    private String b;

    public bj(byte[] bArr) {
        this.b = "1";
        this.a = (byte[]) bArr.clone();
    }

    public bj(byte[] bArr, String str) {
        this.b = "1";
        this.a = (byte[]) bArr.clone();
        this.b = str;
    }

    private String a() {
        byte[] a = bf.a(bg.a);
        byte[] bArr = new byte[a.length + 50];
        System.arraycopy(this.a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return bc.a(bArr);
    }

    @Override // com.amap.api.services.a.cz
    public Map<String, String> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/zip");
        hashMap.put("Content-Length", String.valueOf(this.a.length));
        return hashMap;
    }

    @Override // com.amap.api.services.a.cz
    public Map<String, String> b() {
        return null;
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return String.format(bg.b, "1", this.b, "1", "open", a());
    }

    @Override // com.amap.api.services.a.cz
    public byte[] f() {
        return this.a;
    }
}

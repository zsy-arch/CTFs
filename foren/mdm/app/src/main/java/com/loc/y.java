package com.loc;

import java.util.HashMap;
import java.util.Map;

/* compiled from: LogUpdateRequest.java */
/* loaded from: classes2.dex */
public final class y extends bn {
    private byte[] a;
    private String b;

    public y(byte[] bArr) {
        this.b = "1";
        this.a = (byte[]) bArr.clone();
    }

    public y(byte[] bArr, String str) {
        this.b = "1";
        this.a = (byte[]) bArr.clone();
        this.b = str;
    }

    @Override // com.loc.bn
    public final Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put("Content-Type", "application/zip");
        hashMap.put("Content-Length", String.valueOf(this.a.length));
        return hashMap;
    }

    @Override // com.loc.bn
    public final String b() {
        String str = u.b;
        byte[] a = t.a(u.a);
        byte[] bArr = new byte[a.length + 50];
        System.arraycopy(this.a, 0, bArr, 0, 50);
        System.arraycopy(a, 0, bArr, 50, a.length);
        return String.format(str, "1", this.b, "1", "open", p.a(bArr));
    }

    @Override // com.loc.bn
    public final Map<String, String> c() {
        return null;
    }

    @Override // com.loc.bn
    public final byte[] d() {
        return this.a;
    }
}

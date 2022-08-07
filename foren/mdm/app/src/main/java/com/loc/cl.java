package com.loc;

import java.util.Map;

/* compiled from: HttpRequest.java */
/* loaded from: classes2.dex */
public final class cl extends bn {
    Map<String, String> a = null;
    Map<String, String> b = null;
    String f = "";
    byte[] g = null;

    @Override // com.loc.bn
    public final Map<String, String> a() {
        return this.a;
    }

    public final void a(String str) {
        this.f = str;
    }

    public final void a(Map<String, String> map) {
        this.a = map;
    }

    @Override // com.loc.bn
    public final String b() {
        return this.f;
    }

    public final void b(Map<String, String> map) {
        this.b = map;
    }

    @Override // com.loc.bn
    public final Map<String, String> c() {
        return this.b;
    }

    @Override // com.loc.bn
    public final byte[] d() {
        return this.g;
    }
}

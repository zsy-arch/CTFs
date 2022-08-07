package com.amap.api.col;

import android.content.Context;
import java.util.Map;

/* compiled from: LocationRequest.java */
/* loaded from: classes.dex */
public final class jl extends ib {
    Map<String, String> a = null;
    String d = "";
    byte[] e = null;
    byte[] i = null;
    boolean j = false;
    String k = null;
    Map<String, String> l = null;

    public jl(Context context, gj gjVar) {
        super(context, gjVar);
    }

    @Override // com.amap.api.col.ig
    public final Map<String, String> a() {
        return this.a;
    }

    public final void a(String str) {
        this.k = str;
    }

    public final void a(Map<String, String> map) {
        this.l = map;
    }

    @Override // com.amap.api.col.ib, com.amap.api.col.ig
    public final Map<String, String> b() {
        return this.l;
    }

    public final void b(String str) {
        this.d = str;
    }

    public final void b(Map<String, String> map) {
        this.a = map;
    }

    public final void b(byte[] bArr) {
        this.e = bArr;
    }

    @Override // com.amap.api.col.ig
    public final String c() {
        return this.d;
    }

    @Override // com.amap.api.col.ib
    public final byte[] d() {
        return this.i;
    }

    @Override // com.amap.api.col.ib
    public final byte[] e() {
        return this.e;
    }

    public final void f() {
        this.j = true;
    }

    @Override // com.amap.api.col.ib
    public final boolean i() {
        return this.j;
    }

    @Override // com.amap.api.col.ib
    public final String k() {
        return this.k;
    }
}

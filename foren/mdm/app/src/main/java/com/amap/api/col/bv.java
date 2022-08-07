package com.amap.api.col;

/* compiled from: WaitingState.java */
/* loaded from: classes.dex */
public class bv extends bm {
    public bv(int i, aj ajVar) {
        super(i, ajVar);
    }

    @Override // com.amap.api.col.bn
    public void c() {
        this.b.l();
        this.b.d();
    }

    @Override // com.amap.api.col.bn
    public void d() {
        b(this.b.c);
        this.b.a(this.b.c);
        this.b.c().c();
    }

    @Override // com.amap.api.col.bn
    public void f() {
        b(this.b.d);
        this.b.a(this.b.d);
        this.b.c().c();
    }

    @Override // com.amap.api.col.bn
    public void a(int i) {
        b(this.b.b(i));
        this.b.a(this.b.b(i));
        this.b.c().c();
    }
}

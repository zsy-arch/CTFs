package com.amap.api.col;

/* compiled from: CityStateImp.java */
/* loaded from: classes.dex */
public abstract class bn {
    protected int a;
    protected aj b;

    public abstract void c();

    public bn(int i, aj ajVar) {
        this.a = i;
        this.b = ajVar;
    }

    public int b() {
        return this.a;
    }

    public boolean a(bn bnVar) {
        return bnVar.b() == b();
    }

    public void b(bn bnVar) {
        bh.a(b() + " ==> " + bnVar.b() + "   " + getClass() + "==>" + bnVar.getClass());
    }

    public void d() {
        bh.a("Wrong call start()  State: " + b() + "  " + getClass());
    }

    public void e() {
        bh.a("Wrong call continueDownload()  State: " + b() + "  " + getClass());
    }

    public void f() {
        bh.a("Wrong call pause()  State: " + b() + "  " + getClass());
    }

    public void a() {
        bh.a("Wrong call delete()  State: " + b() + "  " + getClass());
    }

    public void a(int i) {
        bh.a("Wrong call fail()  State: " + b() + "  " + getClass());
    }

    public void g() {
        bh.a("Wrong call hasNew()  State: " + b() + "  " + getClass());
    }

    public void h() {
        bh.a("Wrong call complete()  State: " + b() + "  " + getClass());
    }
}

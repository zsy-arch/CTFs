package c.a.e.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import c.a.e.a.v;

/* renamed from: c.a.e.a.b */
/* loaded from: classes.dex */
public abstract class AbstractC0030b implements v {

    /* renamed from: a */
    public Context f415a;

    /* renamed from: b */
    public Context f416b;

    /* renamed from: c */
    public l f417c;

    /* renamed from: d */
    public LayoutInflater f418d;

    /* renamed from: e */
    public v.a f419e;
    public int f;
    public int g;
    public w h;

    public AbstractC0030b(Context context, int i, int i2) {
        this.f415a = context;
        this.f418d = LayoutInflater.from(context);
        this.f = i;
        this.g = i2;
    }

    public abstract View a(p pVar, View view, ViewGroup viewGroup);

    public void a(int i) {
    }

    @Override // c.a.e.a.v
    public void a(v.a aVar) {
        this.f419e = aVar;
    }

    @Override // c.a.e.a.v
    public abstract void a(boolean z);

    @Override // c.a.e.a.v
    public boolean a(l lVar, p pVar) {
        return false;
    }

    @Override // c.a.e.a.v
    public boolean b(l lVar, p pVar) {
        return false;
    }

    @Override // c.a.e.a.v
    public boolean a(C c2) {
        v.a aVar = this.f419e;
        if (aVar != null) {
            return aVar.a(c2);
        }
        return false;
    }
}

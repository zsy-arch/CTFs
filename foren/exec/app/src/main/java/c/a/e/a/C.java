package c.a.e.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import c.a.e.a.l;

/* loaded from: classes.dex */
public class C extends l implements SubMenu {
    public l B;
    public p C;

    public C(Context context, l lVar, p pVar) {
        super(context);
        this.B = lVar;
        this.C = pVar;
    }

    @Override // c.a.e.a.l
    public void a(l.a aVar) {
        this.B.a(aVar);
    }

    @Override // c.a.e.a.l
    public boolean b(p pVar) {
        return this.B.b(pVar);
    }

    @Override // c.a.e.a.l
    public l c() {
        return this.B.c();
    }

    @Override // c.a.e.a.l
    public boolean e() {
        return this.B.e();
    }

    @Override // c.a.e.a.l
    public boolean f() {
        return this.B.f();
    }

    @Override // c.a.e.a.l
    public boolean g() {
        return this.B.g();
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.C;
    }

    @Override // c.a.e.a.l, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.B.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        a(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        a(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        a(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        p pVar = this.C;
        pVar.m = 0;
        pVar.l = drawable;
        pVar.x = true;
        pVar.n.b(false);
        return this;
    }

    @Override // c.a.e.a.l, android.view.Menu
    public void setQwertyMode(boolean z) {
        this.B.setQwertyMode(z);
    }

    @Override // c.a.e.a.l
    public boolean a(p pVar) {
        return this.B.a(pVar);
    }

    @Override // c.a.e.a.l
    public String b() {
        p pVar = this.C;
        int i = pVar != null ? pVar.f460a : 0;
        if (i == 0) {
            return null;
        }
        return "android:menu:actionviewstates:" + i;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        a(0, null, i, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        a(i, null, 0, null, null);
        return this;
    }

    @Override // c.a.e.a.l
    public boolean a(l lVar, MenuItem menuItem) {
        l.a aVar = this.f;
        return (aVar != null && aVar.a(lVar, menuItem)) || this.B.a(lVar, menuItem);
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        p pVar = this.C;
        pVar.l = null;
        pVar.m = i;
        pVar.x = true;
        pVar.n.b(false);
        return this;
    }
}

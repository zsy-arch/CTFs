package c.a.e.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import c.e.d.a.c;

/* loaded from: classes.dex */
public class D extends x implements SubMenu {
    public D(Context context, c cVar) {
        super(context, cVar);
    }

    @Override // android.view.SubMenu
    public void clearHeader() {
        ((c) this.f423a).clearHeader();
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return a(((c) this.f423a).getItem());
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        ((c) this.f423a).setHeaderIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        ((c) this.f423a).setHeaderTitle(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        ((c) this.f423a).setHeaderView(view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        ((c) this.f423a).setIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        ((c) this.f423a).setHeaderIcon(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        ((c) this.f423a).setHeaderTitle(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        ((c) this.f423a).setIcon(drawable);
        return this;
    }
}

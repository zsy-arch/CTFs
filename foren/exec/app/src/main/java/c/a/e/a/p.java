package c.a.e.a;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import c.a.a.C;
import c.a.b.a.a;
import c.a.e.a.w;
import c.e.d.a.b;

/* loaded from: classes.dex */
public final class p implements b {
    public View A;
    public c.e.h.b B;
    public MenuItem.OnActionExpandListener C;
    public ContextMenu.ContextMenuInfo E;

    /* renamed from: a */
    public final int f460a;

    /* renamed from: b */
    public final int f461b;

    /* renamed from: c */
    public final int f462c;

    /* renamed from: d */
    public final int f463d;

    /* renamed from: e */
    public CharSequence f464e;
    public CharSequence f;
    public Intent g;
    public char h;
    public char j;
    public Drawable l;
    public l n;
    public C o;
    public Runnable p;
    public MenuItem.OnMenuItemClickListener q;
    public CharSequence r;
    public CharSequence s;
    public int z;
    public int i = 4096;
    public int k = 4096;
    public int m = 0;
    public ColorStateList t = null;
    public PorterDuff.Mode u = null;
    public boolean v = false;
    public boolean w = false;
    public boolean x = false;
    public int y = 16;
    public boolean D = false;

    public p(l lVar, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.z = 0;
        this.n = lVar;
        this.f460a = i2;
        this.f461b = i;
        this.f462c = i3;
        this.f463d = i4;
        this.f464e = charSequence;
        this.z = i5;
    }

    public static void a(StringBuilder sb, int i, int i2, String str) {
        if ((i & i2) == i2) {
            sb.append(str);
        }
    }

    public char b() {
        return this.n.f() ? this.j : this.h;
    }

    public void c(boolean z) {
        if (z) {
            this.y |= 32;
        } else {
            this.y &= -33;
        }
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public boolean collapseActionView() {
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.n.a(this);
        }
        return false;
    }

    public boolean d(boolean z) {
        int i = this.y;
        this.y = (z ? 0 : 8) | (i & (-9));
        return i != this.y;
    }

    public boolean e() {
        return (this.y & 4) != 0;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public boolean expandActionView() {
        if (!c()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.C;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.n.b(this);
        }
        return false;
    }

    public boolean f() {
        return this.n.g() && b() != 0;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public View getActionView() {
        View view = this.A;
        if (view != null) {
            return view;
        }
        c.e.h.b bVar = this.B;
        if (bVar == null) {
            return null;
        }
        this.A = bVar.a(this);
        return this.A;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.k;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.j;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.r;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f461b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        Drawable drawable = this.l;
        if (drawable != null) {
            return a(drawable);
        }
        int i = this.m;
        if (i == 0) {
            return null;
        }
        Drawable c2 = a.c(this.n.f451b, i);
        this.m = 0;
        this.l = c2;
        return a(c2);
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.t;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.u;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.g;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.f460a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.E;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.i;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.h;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f462c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.o;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public CharSequence getTitle() {
        return this.f464e;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f;
        if (charSequence == null) {
            charSequence = this.f464e;
        }
        int i = Build.VERSION.SDK_INT;
        return charSequence;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.s;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.o != null;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.D;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.y & 1) == 1;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.y & 2) == 2;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.y & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        c.e.h.b bVar = this.B;
        return (bVar == null || !bVar.b()) ? (this.y & 8) == 0 : (this.y & 8) == 0 && this.B.a();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        if (this.j == c2) {
            return this;
        }
        this.j = Character.toLowerCase(c2);
        this.n.b(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        int i = this.y;
        this.y = (z ? 1 : 0) | (i & (-2));
        if (i != this.y) {
            this.n.b(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        if ((this.y & 4) != 0) {
            this.n.a((MenuItem) this);
        } else {
            b(z);
        }
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    /* renamed from: setContentDescription */
    public MenuItem mo3setContentDescription(CharSequence charSequence) {
        this.r = charSequence;
        this.n.b(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        if (z) {
            this.y |= 16;
        } else {
            this.y &= -17;
        }
        this.n.b(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.m = 0;
        this.l = drawable;
        this.x = true;
        this.n.b(false);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.t = colorStateList;
        this.v = true;
        this.x = true;
        this.n.b(false);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.u = mode;
        this.w = true;
        this.x = true;
        this.n.b(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.g = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        if (this.h == c2) {
            return this;
        }
        this.h = c2;
        this.n.b(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.C = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.q = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.h = c2;
        this.j = Character.toLowerCase(c3);
        this.n.b(false);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public void setShowAsAction(int i) {
        int i2 = i & 3;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            this.z = i;
            l lVar = this.n;
            lVar.l = true;
            lVar.b(true);
            return;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i) {
        int i2 = i & 3;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            this.z = i;
            l lVar = this.n;
            lVar.l = true;
            lVar.b(true);
            return this;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f464e = charSequence;
        this.n.b(false);
        C c2 = this.o;
        if (c2 != null) {
            c2.a(0, charSequence, 0, null, null);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f = charSequence;
        if (charSequence == null) {
            CharSequence charSequence2 = this.f464e;
        }
        this.n.b(false);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    /* renamed from: setTooltipText */
    public MenuItem mo4setTooltipText(CharSequence charSequence) {
        this.s = charSequence;
        this.n.b(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        if (d(z)) {
            l lVar = this.n;
            lVar.i = true;
            lVar.b(true);
        }
        return this;
    }

    public String toString() {
        CharSequence charSequence = this.f464e;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public CharSequence a(w.a aVar) {
        if (aVar == null || !aVar.c()) {
            return this.f464e;
        }
        CharSequence charSequence = this.f;
        if (charSequence == null) {
            charSequence = this.f464e;
        }
        int i = Build.VERSION.SDK_INT;
        return charSequence;
    }

    public void b(boolean z) {
        int i = this.y;
        this.y = (z ? 2 : 0) | (i & (-3));
        if (i != this.y) {
            this.n.b(false);
        }
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public b setActionView(View view) {
        int i;
        this.A = view;
        this.B = null;
        if (view != null && view.getId() == -1 && (i = this.f460a) > 0) {
            view.setId(i);
        }
        l lVar = this.n;
        lVar.l = true;
        lVar.b(true);
        return this;
    }

    public boolean c() {
        c.e.h.b bVar;
        if ((this.z & 8) == 0) {
            return false;
        }
        if (this.A == null && (bVar = this.B) != null) {
            this.A = bVar.a(this);
        }
        return this.A != null;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    /* renamed from: setContentDescription */
    public b mo3setContentDescription(CharSequence charSequence) {
        this.r = charSequence;
        this.n.b(false);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    /* renamed from: setTooltipText */
    public b mo4setTooltipText(CharSequence charSequence) {
        this.s = charSequence;
        this.n.b(false);
        return this;
    }

    public boolean d() {
        return (this.y & 32) == 32;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i) {
        if (this.j == c2 && this.k == i) {
            return this;
        }
        this.j = Character.toLowerCase(c2);
        this.k = KeyEvent.normalizeMetaState(i);
        this.n.b(false);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i) {
        if (this.h == c2 && this.i == i) {
            return this;
        }
        this.h = c2;
        this.i = KeyEvent.normalizeMetaState(i);
        this.n.b(false);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i, int i2) {
        this.h = c2;
        this.i = KeyEvent.normalizeMetaState(i);
        this.j = Character.toLowerCase(c3);
        this.k = KeyEvent.normalizeMetaState(i2);
        this.n.b(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        this.l = null;
        this.m = i;
        this.x = true;
        this.n.b(false);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        String string = this.n.f451b.getString(i);
        this.f464e = string;
        this.n.b(false);
        C c2 = this.o;
        if (c2 != null) {
            c2.setHeaderTitle(string);
        }
        return this;
    }

    public final Drawable a(Drawable drawable) {
        if (drawable != null && this.x && (this.v || this.w)) {
            drawable = C.b(drawable).mutate();
            if (this.v) {
                ColorStateList colorStateList = this.t;
                int i = Build.VERSION.SDK_INT;
                drawable.setTintList(colorStateList);
            }
            if (this.w) {
                PorterDuff.Mode mode = this.u;
                int i2 = Build.VERSION.SDK_INT;
                drawable.setTintMode(mode);
            }
            this.x = false;
        }
        return drawable;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setActionView(int i) {
        Context context = this.n.f451b;
        setActionView(LayoutInflater.from(context).inflate(i, (ViewGroup) new LinearLayout(context), false));
        return this;
    }

    @Override // c.e.d.a.b
    public c.e.h.b a() {
        return this.B;
    }

    @Override // c.e.d.a.b
    public b a(c.e.h.b bVar) {
        c.e.h.b bVar2 = this.B;
        if (bVar2 != null) {
            bVar2.f850b = null;
            bVar2.f849a = null;
        }
        this.A = null;
        this.B = bVar;
        this.n.b(true);
        c.e.h.b bVar3 = this.B;
        if (bVar3 != null) {
            bVar3.a(new o(this));
        }
        return this;
    }

    public void a(boolean z) {
        this.D = z;
        this.n.b(false);
    }
}

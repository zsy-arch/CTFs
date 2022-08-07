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
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import c.a.a.C;
import c.e.b.a;
import c.e.d.a.b;

/* renamed from: c.a.e.a.a  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0029a implements b {

    /* renamed from: a  reason: collision with root package name */
    public final int f410a;

    /* renamed from: b  reason: collision with root package name */
    public final int f411b;

    /* renamed from: c  reason: collision with root package name */
    public final int f412c;

    /* renamed from: d  reason: collision with root package name */
    public CharSequence f413d;

    /* renamed from: e  reason: collision with root package name */
    public CharSequence f414e;
    public Intent f;
    public char g;
    public char i;
    public Drawable k;
    public Context l;
    public CharSequence m;
    public CharSequence n;
    public int h = 4096;
    public int j = 4096;
    public ColorStateList o = null;
    public PorterDuff.Mode p = null;
    public boolean q = false;
    public boolean r = false;
    public int s = 16;

    public C0029a(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.l = context;
        this.f410a = i2;
        this.f411b = i;
        this.f412c = i4;
        this.f413d = charSequence;
    }

    @Override // c.e.d.a.b
    public b a(c.e.h.b bVar) {
        throw new UnsupportedOperationException();
    }

    @Override // c.e.d.a.b
    public c.e.h.b a() {
        return null;
    }

    public final void b() {
        if (this.k == null) {
            return;
        }
        if (this.q || this.r) {
            this.k = C.b(this.k);
            this.k = this.k.mutate();
            if (this.q) {
                Drawable drawable = this.k;
                ColorStateList colorStateList = this.o;
                int i = Build.VERSION.SDK_INT;
                drawable.setTintList(colorStateList);
            }
            if (this.r) {
                Drawable drawable2 = this.k;
                PorterDuff.Mode mode = this.p;
                int i2 = Build.VERSION.SDK_INT;
                drawable2.setTintMode(mode);
            }
        }
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.j;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.i;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.m;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f411b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.k;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.o;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.p;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f410a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.h;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.g;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f412c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f413d;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f414e;
        return charSequence != null ? charSequence : this.f413d;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.n;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.s & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.s & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.s & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.s & 8) == 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        this.i = Character.toLowerCase(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.s = (z ? 1 : 0) | (this.s & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.s = (z ? 2 : 0) | (this.s & (-3));
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    /* renamed from: setContentDescription */
    public MenuItem mo3setContentDescription(CharSequence charSequence) {
        this.m = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.s = (z ? 16 : 0) | (this.s & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.k = drawable;
        b();
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.o = colorStateList;
        this.q = true;
        b();
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.p = mode;
        this.r = true;
        b();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        this.g = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.g = c2;
        this.i = Character.toLowerCase(c3);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public void setShowAsAction(int i) {
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i) {
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f413d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f414e = charSequence;
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    /* renamed from: setTooltipText */
    public MenuItem mo4setTooltipText(CharSequence charSequence) {
        this.n = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        int i = 8;
        int i2 = this.s & 8;
        if (z) {
            i = 0;
        }
        this.s = i2 | i;
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i) {
        this.i = Character.toLowerCase(c2);
        this.j = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    /* renamed from: setContentDescription  reason: collision with other method in class */
    public b mo3setContentDescription(CharSequence charSequence) {
        this.m = charSequence;
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i) {
        this.g = c2;
        this.h = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        this.f413d = this.l.getResources().getString(i);
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    /* renamed from: setTooltipText  reason: collision with other method in class */
    public b mo4setTooltipText(CharSequence charSequence) {
        this.n = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        this.k = a.b(this.l, i);
        b();
        return this;
    }

    @Override // c.e.d.a.b, android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i, int i2) {
        this.g = c2;
        this.h = KeyEvent.normalizeMetaState(i);
        this.i = Character.toLowerCase(c3);
        this.j = KeyEvent.normalizeMetaState(i2);
        return this;
    }
}

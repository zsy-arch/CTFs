package c.a.e.a;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class q extends AbstractC0031c<c.e.d.a.b> implements MenuItem {

    /* renamed from: e  reason: collision with root package name */
    public Method f465e;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a extends c.e.h.b {

        /* renamed from: c  reason: collision with root package name */
        public final ActionProvider f466c;

        public a(Context context, ActionProvider actionProvider) {
            super(context);
            this.f466c = actionProvider;
        }
    }

    /* loaded from: classes.dex */
    static class b extends FrameLayout implements c.a.e.b {

        /* renamed from: a  reason: collision with root package name */
        public final CollapsibleActionView f468a;

        public b(View view) {
            super(view.getContext());
            this.f468a = (CollapsibleActionView) view;
            addView(view);
        }

        @Override // c.a.e.b
        public void onActionViewCollapsed() {
            this.f468a.onActionViewCollapsed();
        }

        @Override // c.a.e.b
        public void onActionViewExpanded() {
            this.f468a.onActionViewExpanded();
        }
    }

    /* loaded from: classes.dex */
    private class c extends C0032d<MenuItem.OnActionExpandListener> implements MenuItem.OnActionExpandListener {
        public c(MenuItem.OnActionExpandListener onActionExpandListener) {
            super(onActionExpandListener);
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener) this.f423a).onMenuItemActionCollapse(q.this.a(menuItem));
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return ((MenuItem.OnActionExpandListener) this.f423a).onMenuItemActionExpand(q.this.a(menuItem));
        }
    }

    /* loaded from: classes.dex */
    private class d extends C0032d<MenuItem.OnMenuItemClickListener> implements MenuItem.OnMenuItemClickListener {
        public d(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            super(onMenuItemClickListener);
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            return ((MenuItem.OnMenuItemClickListener) this.f423a).onMenuItemClick(q.this.a(menuItem));
        }
    }

    public q(Context context, c.e.d.a.b bVar) {
        super(context, bVar);
    }

    public a a(ActionProvider actionProvider) {
        return new a(this.f420b, actionProvider);
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return ((c.e.d.a.b) this.f423a).collapseActionView();
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return ((c.e.d.a.b) this.f423a).expandActionView();
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        c.e.h.b a2 = ((c.e.d.a.b) this.f423a).a();
        if (a2 instanceof a) {
            return ((a) a2).f466c;
        }
        return null;
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        View actionView = ((c.e.d.a.b) this.f423a).getActionView();
        return actionView instanceof b ? (View) ((b) actionView).f468a : actionView;
    }

    @Override // android.view.MenuItem
    public int getAlphabeticModifiers() {
        return ((c.e.d.a.b) this.f423a).getAlphabeticModifiers();
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return ((c.e.d.a.b) this.f423a).getAlphabeticShortcut();
    }

    @Override // android.view.MenuItem
    public CharSequence getContentDescription() {
        return ((c.e.d.a.b) this.f423a).getContentDescription();
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return ((c.e.d.a.b) this.f423a).getGroupId();
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return ((c.e.d.a.b) this.f423a).getIcon();
    }

    @Override // android.view.MenuItem
    public ColorStateList getIconTintList() {
        return ((c.e.d.a.b) this.f423a).getIconTintList();
    }

    @Override // android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return ((c.e.d.a.b) this.f423a).getIconTintMode();
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return ((c.e.d.a.b) this.f423a).getIntent();
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return ((c.e.d.a.b) this.f423a).getItemId();
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return ((c.e.d.a.b) this.f423a).getMenuInfo();
    }

    @Override // android.view.MenuItem
    public int getNumericModifiers() {
        return ((c.e.d.a.b) this.f423a).getNumericModifiers();
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return ((c.e.d.a.b) this.f423a).getNumericShortcut();
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return ((c.e.d.a.b) this.f423a).getOrder();
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return a(((c.e.d.a.b) this.f423a).getSubMenu());
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return ((c.e.d.a.b) this.f423a).getTitle();
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return ((c.e.d.a.b) this.f423a).getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public CharSequence getTooltipText() {
        return ((c.e.d.a.b) this.f423a).getTooltipText();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return ((c.e.d.a.b) this.f423a).hasSubMenu();
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return ((c.e.d.a.b) this.f423a).isActionViewExpanded();
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return ((c.e.d.a.b) this.f423a).isCheckable();
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return ((c.e.d.a.b) this.f423a).isChecked();
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return ((c.e.d.a.b) this.f423a).isEnabled();
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return ((c.e.d.a.b) this.f423a).isVisible();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        ((c.e.d.a.b) this.f423a).a(actionProvider != null ? a(actionProvider) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new b(view);
        }
        ((c.e.d.a.b) this.f423a).setActionView(view);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        ((c.e.d.a.b) this.f423a).setAlphabeticShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        ((c.e.d.a.b) this.f423a).setCheckable(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        ((c.e.d.a.b) this.f423a).setChecked(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence charSequence) {
        ((c.e.d.a.b) this.f423a).mo3setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        ((c.e.d.a.b) this.f423a).setEnabled(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        ((c.e.d.a.b) this.f423a).setIcon(drawable);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        ((c.e.d.a.b) this.f423a).setIconTintList(colorStateList);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        ((c.e.d.a.b) this.f423a).setIconTintMode(mode);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        ((c.e.d.a.b) this.f423a).setIntent(intent);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        ((c.e.d.a.b) this.f423a).setNumericShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        ((c.e.d.a.b) this.f423a).setOnActionExpandListener(onActionExpandListener != null ? new c(onActionExpandListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        ((c.e.d.a.b) this.f423a).setOnMenuItemClickListener(onMenuItemClickListener != null ? new d(onMenuItemClickListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        ((c.e.d.a.b) this.f423a).setShortcut(c2, c3);
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int i) {
        ((c.e.d.a.b) this.f423a).setShowAsAction(i);
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i) {
        ((c.e.d.a.b) this.f423a).setShowAsActionFlags(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        ((c.e.d.a.b) this.f423a).setTitle(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        ((c.e.d.a.b) this.f423a).setTitleCondensed(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTooltipText(CharSequence charSequence) {
        ((c.e.d.a.b) this.f423a).mo4setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        return ((c.e.d.a.b) this.f423a).setVisible(z);
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i) {
        ((c.e.d.a.b) this.f423a).setAlphabeticShortcut(c2, i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        ((c.e.d.a.b) this.f423a).setIcon(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i) {
        ((c.e.d.a.b) this.f423a).setNumericShortcut(c2, i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i, int i2) {
        ((c.e.d.a.b) this.f423a).setShortcut(c2, c3, i, i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        ((c.e.d.a.b) this.f423a).setTitle(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i) {
        ((c.e.d.a.b) this.f423a).setActionView(i);
        View actionView = ((c.e.d.a.b) this.f423a).getActionView();
        if (actionView instanceof CollapsibleActionView) {
            ((c.e.d.a.b) this.f423a).setActionView(new b(actionView));
        }
        return this;
    }
}

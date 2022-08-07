package c.a.e;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import c.a.e.a;
import c.a.e.a.l;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class d extends a implements l.a {

    /* renamed from: c  reason: collision with root package name */
    public Context f485c;

    /* renamed from: d  reason: collision with root package name */
    public ActionBarContextView f486d;

    /* renamed from: e  reason: collision with root package name */
    public a.AbstractC0005a f487e;
    public WeakReference<View> f;
    public boolean g;
    public l h;

    public d(Context context, ActionBarContextView actionBarContextView, a.AbstractC0005a aVar, boolean z) {
        this.f485c = context;
        this.f486d = actionBarContextView;
        this.f487e = aVar;
        l lVar = new l(actionBarContextView.getContext());
        lVar.m = 1;
        this.h = lVar;
        this.h.a(this);
    }

    @Override // c.a.e.a
    public void a(CharSequence charSequence) {
        this.f486d.setSubtitle(charSequence);
    }

    @Override // c.a.e.a
    public void b(CharSequence charSequence) {
        this.f486d.setTitle(charSequence);
    }

    @Override // c.a.e.a
    public Menu c() {
        return this.h;
    }

    @Override // c.a.e.a
    public MenuInflater d() {
        return new f(this.f486d.getContext());
    }

    @Override // c.a.e.a
    public CharSequence e() {
        return this.f486d.getSubtitle();
    }

    @Override // c.a.e.a
    public CharSequence f() {
        return this.f486d.getTitle();
    }

    @Override // c.a.e.a
    public void g() {
        this.f487e.b(this, this.h);
    }

    @Override // c.a.e.a
    public boolean h() {
        return this.f486d.c();
    }

    @Override // c.a.e.a
    public void a(int i) {
        this.f486d.setSubtitle(this.f485c.getString(i));
    }

    @Override // c.a.e.a
    public void b(int i) {
        this.f486d.setTitle(this.f485c.getString(i));
    }

    @Override // c.a.e.a
    public void a(View view) {
        this.f486d.setCustomView(view);
        this.f = view != null ? new WeakReference<>(view) : null;
    }

    @Override // c.a.e.a
    public View b() {
        WeakReference<View> weakReference = this.f;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // c.a.e.a.l.a
    public void a(l lVar) {
        this.f487e.b(this, this.h);
        this.f486d.e();
    }

    @Override // c.a.e.a
    public void a() {
        if (!this.g) {
            this.g = true;
            this.f486d.sendAccessibilityEvent(32);
            this.f487e.a(this);
        }
    }

    @Override // c.a.e.a
    public void a(boolean z) {
        this.f404b = z;
        this.f486d.setTitleOptional(z);
    }

    @Override // c.a.e.a.l.a
    public boolean a(l lVar, MenuItem menuItem) {
        return this.f487e.a(this, menuItem);
    }
}

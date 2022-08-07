package c.a.a;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatDelegateImpl;
import c.a.e.a;
import c.e.h.c;

/* loaded from: classes.dex */
public class B extends Dialog implements n {

    /* renamed from: a */
    public o f313a;

    /* renamed from: b */
    public final c.a f314b;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public B(android.content.Context r4, int r5) {
        /*
            r3 = this;
            if (r5 != 0) goto L_0x0013
            android.util.TypedValue r5 = new android.util.TypedValue
            r5.<init>()
            android.content.res.Resources$Theme r0 = r4.getTheme()
            int r1 = c.a.a.dialogTheme
            r2 = 1
            r0.resolveAttribute(r1, r5, r2)
            int r5 = r5.resourceId
        L_0x0013:
            r3.<init>(r4, r5)
            c.a.a.A r4 = new c.a.a.A
            r4.<init>(r3)
            r3.f314b = r4
            c.a.a.o r4 = r3.a()
            r5 = 0
            r4.a(r5)
            c.a.a.o r4 = r3.a()
            r4.a()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.B.<init>(android.content.Context, int):void");
    }

    public o a() {
        if (this.f313a == null) {
            this.f313a = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        return this.f313a;
    }

    @Override // c.a.a.n
    public a a(a.AbstractC0005a aVar) {
        return null;
    }

    @Override // c.a.a.n
    public void a(a aVar) {
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) a();
        appCompatDelegateImpl.f();
        ((ViewGroup) appCompatDelegateImpl.w.findViewById(16908290)).addView(view, layoutParams);
        appCompatDelegateImpl.g.onContentChanged();
    }

    @Override // c.a.a.n
    public void b(a aVar) {
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return c.a(this.f314b, getWindow().getDecorView(), this, keyEvent);
    }

    @Override // android.app.Dialog
    public <T extends View> T findViewById(int i) {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) a();
        appCompatDelegateImpl.f();
        return (T) appCompatDelegateImpl.f.findViewById(i);
    }

    @Override // android.app.Dialog
    public void invalidateOptionsMenu() {
        a().c();
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        a().b();
        super.onCreate(bundle);
        a().a(bundle);
    }

    @Override // android.app.Dialog
    public void onStop() {
        super.onStop();
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) a();
        appCompatDelegateImpl.i();
        AbstractC0020a aVar = appCompatDelegateImpl.j;
        if (aVar != null) {
            aVar.c(false);
        }
        AppCompatDelegateImpl.d dVar = appCompatDelegateImpl.N;
        if (dVar != null) {
            dVar.a();
        }
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        a().b(i);
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        a().a(charSequence);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        a().a(view);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        a().a(view, layoutParams);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        super.setTitle(i);
        a().a(getContext().getString(i));
    }

    public boolean a(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }
}

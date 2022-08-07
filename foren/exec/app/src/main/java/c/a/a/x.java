package c.a.a;

import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatDelegateImpl;
import c.e.h.n;
import c.e.h.s;
import c.e.h.t;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class x extends t {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppCompatDelegateImpl.b f375a;

    public x(AppCompatDelegateImpl.b bVar) {
        this.f375a = bVar;
    }

    @Override // c.e.h.s
    public void b(View view) {
        AppCompatDelegateImpl.this.q.setVisibility(8);
        AppCompatDelegateImpl appCompatDelegateImpl = AppCompatDelegateImpl.this;
        PopupWindow popupWindow = appCompatDelegateImpl.r;
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else if (appCompatDelegateImpl.q.getParent() instanceof View) {
            n.r((View) AppCompatDelegateImpl.this.q.getParent());
        }
        AppCompatDelegateImpl.this.q.removeAllViews();
        AppCompatDelegateImpl.this.t.a((s) null);
        AppCompatDelegateImpl.this.t = null;
    }
}

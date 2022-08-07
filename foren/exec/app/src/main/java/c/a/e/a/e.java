package c.a.e.a;

import android.view.View;
import android.view.ViewTreeObserver;
import c.a.e.a.i;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class e implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ i f424a;

    public e(i iVar) {
        this.f424a = iVar;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        if (this.f424a.b() && this.f424a.j.size() > 0 && !this.f424a.j.get(0).f435a.k()) {
            View view = this.f424a.q;
            if (view == null || !view.isShown()) {
                this.f424a.dismiss();
                return;
            }
            for (i.a aVar : this.f424a.j) {
                aVar.f435a.c();
            }
        }
    }
}

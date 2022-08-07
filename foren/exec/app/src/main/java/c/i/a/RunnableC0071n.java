package c.i.a;

import android.view.View;
import androidx.fragment.app.Fragment;

/* renamed from: c.i.a.n  reason: case insensitive filesystem */
/* loaded from: classes.dex */
class RunnableC0071n implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ o f975a;

    public RunnableC0071n(o oVar) {
        this.f975a = oVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f975a.f977c.e() != null) {
            this.f975a.f977c.a((View) null);
            o oVar = this.f975a;
            s sVar = oVar.f978d;
            Fragment fragment = oVar.f977c;
            sVar.a(fragment, fragment.o(), 0, 0, false);
        }
    }
}

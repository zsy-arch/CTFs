package c.a.e.a;

import android.view.MenuItem;
import c.a.e.a.i;

/* loaded from: classes.dex */
class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ i.a f426a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ MenuItem f427b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ l f428c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ h f429d;

    public g(h hVar, i.a aVar, MenuItem menuItem, l lVar) {
        this.f429d = hVar;
        this.f426a = aVar;
        this.f427b = menuItem;
        this.f428c = lVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        i.a aVar = this.f426a;
        if (aVar != null) {
            this.f429d.f430a.B = true;
            aVar.f436b.a(false);
            this.f429d.f430a.B = false;
        }
        if (this.f427b.isEnabled() && this.f427b.hasSubMenu()) {
            this.f428c.a(this.f427b, 4);
        }
    }
}

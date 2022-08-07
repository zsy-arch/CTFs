package c.a.e.a;

import android.os.SystemClock;
import android.view.MenuItem;
import c.a.e.a.i;
import c.a.f.K;

/* loaded from: classes.dex */
public class h implements K {

    /* renamed from: a */
    public final /* synthetic */ i f430a;

    public h(i iVar) {
        this.f430a = iVar;
    }

    @Override // c.a.f.K
    public void a(l lVar, MenuItem menuItem) {
        i.a aVar = null;
        this.f430a.h.removeCallbacksAndMessages(null);
        int size = this.f430a.j.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            } else if (lVar == this.f430a.j.get(i).f436b) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1) {
            int i2 = i + 1;
            if (i2 < this.f430a.j.size()) {
                aVar = this.f430a.j.get(i2);
            }
            this.f430a.h.postAtTime(new g(this, aVar, menuItem, lVar), lVar, SystemClock.uptimeMillis() + 200);
        }
    }

    @Override // c.a.f.K
    public void b(l lVar, MenuItem menuItem) {
        this.f430a.h.removeCallbacksAndMessages(lVar);
    }
}

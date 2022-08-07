package c.e.h;

import android.content.Context;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionMenuPresenter;
import c.a.e.a.q;

/* loaded from: classes.dex */
public abstract class b {

    /* renamed from: a */
    public a f849a;

    /* renamed from: b */
    public AbstractC0012b f850b;

    /* loaded from: classes.dex */
    public interface a {
    }

    /* renamed from: c.e.h.b$b */
    /* loaded from: classes.dex */
    public interface AbstractC0012b {
    }

    public b(Context context) {
    }

    public View a(MenuItem menuItem) {
        return ((q.a) this).f466c.onCreateActionView();
    }

    public boolean a() {
        return true;
    }

    public boolean b() {
        return false;
    }

    public void a(boolean z) {
        a aVar = this.f849a;
        if (aVar != null) {
            ((ActionMenuPresenter) aVar).b(z);
        }
    }

    public void a(AbstractC0012b bVar) {
        if (!(this.f850b == null || bVar == null)) {
            StringBuilder a2 = e.a.a.a.a.a("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ");
            a2.append(getClass().getSimpleName());
            a2.append(" instance while it is still in use somewhere else?");
            a2.toString();
        }
        this.f850b = bVar;
    }
}

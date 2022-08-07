package c.a.e.a;

import android.content.Context;
import android.os.Build;
import android.view.MenuItem;
import android.view.SubMenu;
import c.e.d.a.b;
import c.e.d.a.c;
import java.util.Map;

/* renamed from: c.a.e.a.c */
/* loaded from: classes.dex */
public abstract class AbstractC0031c<T> extends C0032d<T> {

    /* renamed from: b */
    public final Context f420b;

    /* renamed from: c */
    public Map<b, MenuItem> f421c;

    /* renamed from: d */
    public Map<c, SubMenu> f422d;

    public AbstractC0031c(Context context, T t) {
        super(t);
        this.f420b = context;
    }

    public final MenuItem a(MenuItem menuItem) {
        if (!(menuItem instanceof b)) {
            return menuItem;
        }
        b bVar = (b) menuItem;
        if (this.f421c == null) {
            this.f421c = new c.c.b();
        }
        MenuItem menuItem2 = this.f421c.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        Context context = this.f420b;
        int i = Build.VERSION.SDK_INT;
        r rVar = new r(context, bVar);
        this.f421c.put(bVar, rVar);
        return rVar;
    }

    public final SubMenu a(SubMenu subMenu) {
        if (!(subMenu instanceof c)) {
            return subMenu;
        }
        c cVar = (c) subMenu;
        if (this.f422d == null) {
            this.f422d = new c.c.b();
        }
        SubMenu subMenu2 = this.f422d.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        D d2 = new D(this.f420b, cVar);
        this.f422d.put(cVar, d2);
        return d2;
    }
}

package c.a.e;

import android.content.Context;
import android.os.Build;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import c.a.e.a;
import c.a.e.a.r;
import c.a.e.a.x;
import c.c.i;
import c.e.d.a.b;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class e extends ActionMode {

    /* renamed from: a  reason: collision with root package name */
    public final Context f488a;

    /* renamed from: b  reason: collision with root package name */
    public final a f489b;

    public e(Context context, a aVar) {
        this.f488a = context;
        this.f489b = aVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f489b.a();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f489b.b();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new x(this.f488a, (c.e.d.a.a) this.f489b.c());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f489b.d();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f489b.e();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f489b.f403a;
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f489b.f();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f489b.f404b;
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f489b.g();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f489b.h();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f489b.a(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f489b.a(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f489b.f403a = obj;
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f489b.b(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f489b.a(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f489b.a(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f489b.b(i);
    }

    /* loaded from: classes.dex */
    public static class a implements a.AbstractC0005a {

        /* renamed from: a  reason: collision with root package name */
        public final ActionMode.Callback f490a;

        /* renamed from: b  reason: collision with root package name */
        public final Context f491b;

        /* renamed from: c  reason: collision with root package name */
        public final ArrayList<e> f492c = new ArrayList<>();

        /* renamed from: d  reason: collision with root package name */
        public final i<Menu, Menu> f493d = new i<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f491b = context;
            this.f490a = callback;
        }

        @Override // c.a.e.a.AbstractC0005a
        public boolean a(a aVar, Menu menu) {
            return this.f490a.onCreateActionMode(b(aVar), a(menu));
        }

        @Override // c.a.e.a.AbstractC0005a
        public boolean b(a aVar, Menu menu) {
            return this.f490a.onPrepareActionMode(b(aVar), a(menu));
        }

        @Override // c.a.e.a.AbstractC0005a
        public boolean a(a aVar, MenuItem menuItem) {
            ActionMode.Callback callback = this.f490a;
            ActionMode b2 = b(aVar);
            int i = Build.VERSION.SDK_INT;
            return callback.onActionItemClicked(b2, new r(this.f491b, (b) menuItem));
        }

        public ActionMode b(a aVar) {
            int size = this.f492c.size();
            for (int i = 0; i < size; i++) {
                e eVar = this.f492c.get(i);
                if (eVar != null && eVar.f489b == aVar) {
                    return eVar;
                }
            }
            e eVar2 = new e(this.f491b, aVar);
            this.f492c.add(eVar2);
            return eVar2;
        }

        @Override // c.a.e.a.AbstractC0005a
        public void a(a aVar) {
            this.f490a.onDestroyActionMode(b(aVar));
        }

        public final Menu a(Menu menu) {
            Menu menu2 = this.f493d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            x xVar = new x(this.f491b, (c.e.d.a.a) menu);
            this.f493d.put(menu, xVar);
            return xVar;
        }
    }
}

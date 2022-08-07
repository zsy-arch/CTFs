package c.a.e.a;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import c.a.e.a.q;
import c.e.h.b;

/* loaded from: classes.dex */
public class r extends q {

    /* loaded from: classes.dex */
    class a extends q.a implements ActionProvider.VisibilityListener {

        /* renamed from: e */
        public b.AbstractC0012b f471e;

        public a(r rVar, Context context, ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        @Override // c.e.h.b
        public View a(MenuItem menuItem) {
            return this.f466c.onCreateActionView(menuItem);
        }

        @Override // c.e.h.b
        public boolean b() {
            return this.f466c.overridesItemVisibility();
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean z) {
            b.AbstractC0012b bVar = this.f471e;
            if (bVar != null) {
                p pVar = ((o) bVar).f459a;
                pVar.n.c(pVar);
            }
        }

        @Override // c.e.h.b
        public boolean a() {
            return this.f466c.isVisible();
        }

        @Override // c.e.h.b
        public void a(b.AbstractC0012b bVar) {
            this.f471e = bVar;
            this.f466c.setVisibilityListener(bVar != null ? this : null);
        }
    }

    public r(Context context, c.e.d.a.b bVar) {
        super(context, bVar);
    }

    @Override // c.a.e.a.q
    public q.a a(ActionProvider actionProvider) {
        return new a(this, this.f420b, actionProvider);
    }
}

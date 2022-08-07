package c.a.a;

import android.view.View;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import c.a.e.a;
import c.e.h.n;
import c.e.h.t;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class F extends t {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ I f330a;

    public F(I i) {
        this.f330a = i;
    }

    @Override // c.e.h.s
    public void b(View view) {
        View view2;
        I i = this.f330a;
        if (i.s && (view2 = i.i) != null) {
            view2.setTranslationY(0.0f);
            this.f330a.f.setTranslationY(0.0f);
        }
        this.f330a.f.setVisibility(8);
        this.f330a.f.setTransitioning(false);
        I i2 = this.f330a;
        i2.x = null;
        a.AbstractC0005a aVar = i2.n;
        if (aVar != null) {
            aVar.a(i2.m);
            i2.m = null;
            i2.n = null;
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f330a.f337e;
        if (actionBarOverlayLayout != null) {
            n.r(actionBarOverlayLayout);
        }
    }
}

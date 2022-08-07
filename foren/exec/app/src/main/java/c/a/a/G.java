package c.a.a;

import android.view.View;
import c.e.h.t;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class G extends t {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ I f331a;

    public G(I i) {
        this.f331a = i;
    }

    @Override // c.e.h.s
    public void b(View view) {
        I i = this.f331a;
        i.x = null;
        i.f.requestLayout();
    }
}

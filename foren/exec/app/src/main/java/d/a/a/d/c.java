package d.a.a.d;

import android.view.View;
import d.a.a.c.a.e;
import d.a.a.d.d;

/* loaded from: classes.dex */
public class c implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ d.a f1626a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ d f1627b;

    public c(d dVar, d.a aVar) {
        this.f1627b = dVar;
        this.f1626a = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ((e) this.f1626a).b(view);
        if (this.f1627b.isShowing()) {
            this.f1627b.dismiss();
        }
    }
}

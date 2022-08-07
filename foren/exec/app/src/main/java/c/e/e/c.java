package c.e.e;

import android.os.Handler;
import c.e.b.a.j;
import c.e.e.f;
import c.e.e.k;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class c implements k.a<f.c> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ j f796a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Handler f797b;

    public c(j jVar, Handler handler) {
        this.f796a = jVar;
        this.f797b = handler;
    }

    @Override // c.e.e.k.a
    public void a(f.c cVar) {
        f.c cVar2 = cVar;
        if (cVar2 == null) {
            this.f796a.a(1, this.f797b);
            return;
        }
        int i = cVar2.f812b;
        if (i == 0) {
            this.f796a.a(cVar2.f811a, this.f797b);
        } else {
            this.f796a.a(i, this.f797b);
        }
    }
}

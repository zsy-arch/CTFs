package c.a.e;

import android.view.View;
import c.e.h.s;
import c.e.h.t;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class g extends t {

    /* renamed from: a  reason: collision with root package name */
    public boolean f507a = false;

    /* renamed from: b  reason: collision with root package name */
    public int f508b = 0;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ h f509c;

    public g(h hVar) {
        this.f509c = hVar;
    }

    @Override // c.e.h.s
    public void b(View view) {
        int i = this.f508b + 1;
        this.f508b = i;
        if (i == this.f509c.f510a.size()) {
            s sVar = this.f509c.f513d;
            if (sVar != null) {
                sVar.b(null);
            }
            this.f508b = 0;
            this.f507a = false;
            this.f509c.f514e = false;
        }
    }

    @Override // c.e.h.t, c.e.h.s
    public void c(View view) {
        if (!this.f507a) {
            this.f507a = true;
            s sVar = this.f509c.f513d;
            if (sVar != null) {
                sVar.c(null);
            }
        }
    }
}

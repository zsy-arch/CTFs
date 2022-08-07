package c.a.f;

import android.view.View;
import c.e.h.t;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class qa extends t {

    /* renamed from: a  reason: collision with root package name */
    public boolean f628a = false;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ int f629b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ra f630c;

    public qa(ra raVar, int i) {
        this.f630c = raVar;
        this.f629b = i;
    }

    @Override // c.e.h.t, c.e.h.s
    public void a(View view) {
        this.f628a = true;
    }

    @Override // c.e.h.s
    public void b(View view) {
        if (!this.f628a) {
            this.f630c.f635a.setVisibility(this.f629b);
        }
    }

    @Override // c.e.h.t, c.e.h.s
    public void c(View view) {
        this.f630c.f635a.setVisibility(0);
    }
}

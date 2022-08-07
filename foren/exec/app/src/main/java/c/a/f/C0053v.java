package c.a.f;

import android.view.View;
import androidx.appcompat.widget.AppCompatSpinner;
import c.a.e.a.y;

/* renamed from: c.a.f.v  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0053v extends H {
    public final /* synthetic */ AppCompatSpinner.b j;
    public final /* synthetic */ AppCompatSpinner k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0053v(AppCompatSpinner appCompatSpinner, View view, AppCompatSpinner.b bVar) {
        super(view);
        this.k = appCompatSpinner;
        this.j = bVar;
    }

    @Override // c.a.f.H
    public y b() {
        return this.j;
    }

    @Override // c.a.f.H
    public boolean c() {
        if (this.k.g.b()) {
            return true;
        }
        this.k.g.c();
        return true;
    }
}

package c.i.a;

import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.Fragment;
import c.c.b;

/* loaded from: classes.dex */
public final class A implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Fragment f909a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Fragment f910b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ boolean f911c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ b f912d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ View f913e;
    public final /* synthetic */ L f;
    public final /* synthetic */ Rect g;

    public A(Fragment fragment, Fragment fragment2, boolean z, b bVar, View view, L l, Rect rect) {
        this.f909a = fragment;
        this.f910b = fragment2;
        this.f911c = z;
        this.f912d = bVar;
        this.f913e = view;
        this.f = l;
        this.g = rect;
    }

    @Override // java.lang.Runnable
    public void run() {
        C.a(this.f909a, this.f910b, this.f911c, (b<String, View>) this.f912d, false);
        View view = this.f913e;
        if (view != null) {
            this.f.a(view, this.g);
        }
    }
}

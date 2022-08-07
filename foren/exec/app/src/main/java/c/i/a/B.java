package c.i.a;

import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.Fragment;
import c.c.b;
import c.i.a.C;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class B implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ L f914a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ b f915b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f916c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ C.a f917d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ ArrayList f918e;
    public final /* synthetic */ View f;
    public final /* synthetic */ Fragment g;
    public final /* synthetic */ Fragment h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ ArrayList j;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Rect l;

    public B(L l, b bVar, Object obj, C.a aVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.f914a = l;
        this.f915b = bVar;
        this.f916c = obj;
        this.f917d = aVar;
        this.f918e = arrayList;
        this.f = view;
        this.g = fragment;
        this.h = fragment2;
        this.i = z;
        this.j = arrayList2;
        this.k = obj2;
        this.l = rect;
    }

    @Override // java.lang.Runnable
    public void run() {
        b<String, View> a2 = C.a(this.f914a, this.f915b, this.f916c, this.f917d);
        if (a2 != null) {
            this.f918e.addAll(a2.values());
            this.f918e.add(this.f);
        }
        C.a(this.g, this.h, this.i, a2, false);
        Object obj = this.f916c;
        if (obj != null) {
            this.f914a.b(obj, this.j, this.f918e);
            View a3 = C.a(a2, this.f917d, this.k, this.i);
            if (a3 != null) {
                this.f914a.a(a3, this.l);
            }
        }
    }
}

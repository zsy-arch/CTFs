package c.i.a;

import android.view.View;
import c.e.h.n;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class I implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f936a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ ArrayList f937b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ ArrayList f938c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ ArrayList f939d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ ArrayList f940e;

    public I(L l, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.f936a = i;
        this.f937b = arrayList;
        this.f938c = arrayList2;
        this.f939d = arrayList3;
        this.f940e = arrayList4;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (int i = 0; i < this.f936a; i++) {
            n.a((View) this.f937b.get(i), (String) this.f938c.get(i));
            n.a((View) this.f939d.get(i), (String) this.f940e.get(i));
        }
    }
}

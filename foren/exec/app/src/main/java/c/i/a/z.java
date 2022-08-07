package c.i.a;

import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class z implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Object f1012a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ L f1013b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ View f1014c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ Fragment f1015d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ ArrayList f1016e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ ArrayList g;
    public final /* synthetic */ Object h;

    public z(Object obj, L l, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.f1012a = obj;
        this.f1013b = l;
        this.f1014c = view;
        this.f1015d = fragment;
        this.f1016e = arrayList;
        this.f = arrayList2;
        this.g = arrayList3;
        this.h = obj2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj = this.f1012a;
        if (obj != null) {
            this.f1013b.a(obj, this.f1014c);
            this.f.addAll(C.a(this.f1013b, this.f1012a, this.f1015d, this.f1016e, this.f1014c));
        }
        if (this.g != null) {
            if (this.h != null) {
                ArrayList<View> arrayList = new ArrayList<>();
                arrayList.add(this.f1014c);
                this.f1013b.a(this.h, this.g, arrayList);
            }
            this.g.clear();
            this.g.add(this.f1014c);
        }
    }
}

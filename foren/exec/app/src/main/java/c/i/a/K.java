package c.i.a;

import android.view.View;
import c.e.h.n;
import java.util.ArrayList;
import java.util.Map;

/* loaded from: classes.dex */
public class K implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ArrayList f943a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Map f944b;

    public K(L l, ArrayList arrayList, Map map) {
        this.f943a = arrayList;
        this.f944b = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        int size = this.f943a.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.f943a.get(i);
            n.a(view, (String) this.f944b.get(n.j(view)));
        }
    }
}

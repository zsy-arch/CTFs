package c.i.a;

import android.view.View;
import c.e.h.n;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class J implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ArrayList f941a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Map f942b;

    public J(L l, ArrayList arrayList, Map map) {
        this.f941a = arrayList;
        this.f942b = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        int size = this.f941a.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.f941a.get(i);
            String j = n.j(view);
            if (j != null) {
                Iterator it = this.f942b.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        str = null;
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (j.equals(entry.getValue())) {
                        str = (String) entry.getKey();
                        break;
                    }
                }
                n.a(view, str);
            }
        }
    }
}

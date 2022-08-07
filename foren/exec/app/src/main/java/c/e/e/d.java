package c.e.e;

import c.e.e.f;
import c.e.e.k;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d implements k.a<f.c> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ String f798a;

    public d(String str) {
        this.f798a = str;
    }

    public void a(f.c cVar) {
        synchronized (f.f801c) {
            ArrayList<k.a<f.c>> arrayList = f.f802d.get(this.f798a);
            if (arrayList != null) {
                f.f802d.remove(this.f798a);
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList.get(i).a(cVar);
                }
            }
        }
    }
}

package c.d.a;

import c.c.i;
import c.e.g.b;
import java.util.ArrayList;
import java.util.HashSet;

/* loaded from: classes.dex */
public final class c<T> {

    /* renamed from: a  reason: collision with root package name */
    public final b<ArrayList<T>> f734a = new b<>(10);

    /* renamed from: b  reason: collision with root package name */
    public final i<T, ArrayList<T>> f735b = new i<>();

    /* renamed from: c  reason: collision with root package name */
    public final ArrayList<T> f736c = new ArrayList<>();

    /* renamed from: d  reason: collision with root package name */
    public final HashSet<T> f737d = new HashSet<>();

    public void a(T t) {
        if (!(this.f735b.a(t) >= 0)) {
            this.f735b.put(t, null);
        }
    }

    public final void a(T t, ArrayList<T> arrayList, HashSet<T> hashSet) {
        if (!arrayList.contains(t)) {
            if (!hashSet.contains(t)) {
                hashSet.add(t);
                ArrayList<T> arrayList2 = this.f735b.get(t);
                if (arrayList2 != null) {
                    int size = arrayList2.size();
                    for (int i = 0; i < size; i++) {
                        a(arrayList2.get(i), arrayList, hashSet);
                    }
                }
                hashSet.remove(t);
                arrayList.add(t);
                return;
            }
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
    }
}

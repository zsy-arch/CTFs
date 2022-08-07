package c.j;

import c.j.f;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final Map<f.a, List<b>> f1017a;

    public static void a(List<b> list, h hVar, f.a aVar, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                list.get(size).a(hVar, aVar, obj);
            }
        }
    }
}

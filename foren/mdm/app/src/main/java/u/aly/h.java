package u.aly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: UMCCAggregatedListObject.java */
/* loaded from: classes2.dex */
public class h implements Serializable {
    private static final long a = 1;
    private Map<List<String>, i> b = new HashMap();
    private long c = 0;

    public Map<List<String>, i> a() {
        return this.b;
    }

    public void a(Map<List<String>, i> map) {
        if (this.b.size() <= 0) {
            this.b = map;
        } else {
            b(map);
        }
    }

    private void b(Map<List<String>, i> map) {
        new ArrayList();
        new ArrayList();
        Iterator<Map.Entry<List<String>, i>> it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<List<String>, i> next = it.next();
            List<String> key = next.getKey();
            Iterator<Map.Entry<List<String>, i>> it2 = this.b.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<List<String>, i> next2 = it2.next();
                List<String> key2 = next.getKey();
                if (!key.equals(key2)) {
                    this.b.put(key2, next2.getValue());
                } else {
                    i value = next2.getValue();
                    a(next.getValue(), value);
                    this.b.remove(key);
                    this.b.put(key, value);
                }
            }
        }
    }

    private void a(i iVar, i iVar2) {
        iVar2.c(iVar2.g() + iVar.g());
        iVar2.b(iVar2.f() + iVar.f());
        iVar2.a(iVar2.e() + iVar.e());
        for (int i = 0; i < iVar.d().size(); i++) {
            iVar2.a(iVar.d().get(i));
        }
    }

    public long b() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public void a(final f fVar, l lVar) {
        try {
            if (a(lVar.a())) {
                i iVar = this.b.get(lVar.a());
                if (iVar != null) {
                    iVar.a(new f() { // from class: u.aly.h.1
                        @Override // u.aly.f, u.aly.g
                        public void a(Object obj, boolean z) {
                            i iVar2 = (i) obj;
                            h.this.b.remove(iVar2.a());
                            h.this.b.put(iVar2.b(), iVar2);
                            fVar.a(this, false);
                        }
                    }, lVar);
                } else {
                    a(fVar, lVar.a(), lVar);
                }
            } else {
                a(fVar, lVar.a(), lVar);
            }
        } catch (Exception e) {
            bo.e("aggregated faild!");
        }
    }

    public void a(f fVar, List<String> list, l lVar) {
        i iVar = new i();
        iVar.a(lVar);
        this.b.put(list, iVar);
        fVar.a(this, false);
    }

    public boolean a(List<?> list) {
        return this.b != null && this.b.containsKey(list);
    }

    public void a(f fVar) {
        for (List<String> list : this.b.keySet()) {
            if (!fVar.a()) {
                fVar.a(this.b.get(list), false);
            } else {
                return;
            }
        }
    }

    public int c() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public void d() {
        this.b.clear();
    }

    public boolean a(List<String> list, List<String> list2) {
        if (list == null || list.size() == 0) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size() - 1; i++) {
            arrayList.add(d.a(list.get(i)));
        }
        if (list == null || list.size() == 0) {
            return false;
        }
        return arrayList.contains(list2);
    }

    public void a(f fVar, l lVar, List<String> list, List<String> list2) {
        while (list.size() >= 1) {
            try {
                if (list.size() == 1) {
                    if (!a(list2, list)) {
                        fVar.a(false, false);
                        return;
                    } else {
                        a(fVar, lVar, list);
                        return;
                    }
                } else if (a(list2, list)) {
                    a(fVar, lVar, list);
                    return;
                } else {
                    list.remove(list.size() - 1);
                }
            } catch (Exception e) {
                bo.e("overFlowAggregated faild");
                return;
            }
        }
    }

    private void a(f fVar, l lVar, List<String> list) {
        if (a(list)) {
            a(fVar, lVar);
        } else {
            a(fVar, list, lVar);
        }
    }
}

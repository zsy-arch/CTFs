package c.j;

import c.b.a.b.b;
import c.j.f;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class i extends f {

    /* renamed from: c */
    public final WeakReference<h> f1027c;

    /* renamed from: a */
    public c.b.a.b.a<g, a> f1025a = new c.b.a.b.a<>();

    /* renamed from: d */
    public int f1028d = 0;

    /* renamed from: e */
    public boolean f1029e = false;
    public boolean f = false;
    public ArrayList<f.b> g = new ArrayList<>();

    /* renamed from: b */
    public f.b f1026b = f.b.INITIALIZED;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a */
        public f.b f1030a;

        /* renamed from: b */
        public e f1031b;

        public void a(h hVar, f.a aVar) {
            f.b a2 = i.a(aVar);
            this.f1030a = i.a(this.f1030a, a2);
            this.f1031b.a(hVar, aVar);
            this.f1030a = a2;
        }
    }

    public i(h hVar) {
        this.f1027c = new WeakReference<>(hVar);
    }

    public final void a(f.b bVar) {
        if (this.f1026b != bVar) {
            this.f1026b = bVar;
            if (this.f1029e || this.f1028d != 0) {
                this.f = true;
                return;
            }
            this.f1029e = true;
            b();
            this.f1029e = false;
        }
    }

    public void b(f.a aVar) {
        a(a(aVar));
    }

    public static f.a b(f.b bVar) {
        int ordinal = bVar.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return f.a.ON_CREATE;
        }
        if (ordinal == 2) {
            return f.a.ON_START;
        }
        if (ordinal == 3) {
            return f.a.ON_RESUME;
        }
        if (ordinal != 4) {
            throw new IllegalArgumentException(e.a.a.a.a.a("Unexpected state value ", bVar));
        }
        throw new IllegalArgumentException();
    }

    public final void a() {
        ArrayList<f.b> arrayList = this.g;
        arrayList.remove(arrayList.size() - 1);
    }

    public static f.b a(f.a aVar) {
        int ordinal = aVar.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal == 2) {
                    return f.b.RESUMED;
                }
                if (ordinal != 3) {
                    if (ordinal != 4) {
                        if (ordinal == 5) {
                            return f.b.DESTROYED;
                        }
                        throw new IllegalArgumentException(e.a.a.a.a.a("Unexpected event value ", aVar));
                    }
                }
            }
            return f.b.STARTED;
        }
        return f.b.CREATED;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b() {
        f.a aVar;
        f.b bVar;
        h hVar = this.f1027c.get();
        if (hVar != null) {
            while (true) {
                c.b.a.b.a<g, a> aVar2 = this.f1025a;
                if (!(aVar2.f678d == 0 || (aVar2.f675a.getValue().f1030a == (bVar = this.f1025a.f676b.getValue().f1030a) && this.f1026b == bVar))) {
                    this.f = false;
                    if (this.f1026b.compareTo(this.f1025a.f675a.getValue().f1030a) < 0) {
                        Iterator<Map.Entry<g, a>> descendingIterator = this.f1025a.descendingIterator();
                        while (descendingIterator.hasNext() && !this.f) {
                            Map.Entry<g, a> next = descendingIterator.next();
                            a value = next.getValue();
                            while (value.f1030a.compareTo(this.f1026b) > 0 && !this.f && this.f1025a.contains(next.getKey())) {
                                f.b bVar2 = value.f1030a;
                                int ordinal = bVar2.ordinal();
                                if (ordinal == 0) {
                                    throw new IllegalArgumentException();
                                } else if (ordinal != 1) {
                                    if (ordinal == 2) {
                                        aVar = f.a.ON_DESTROY;
                                    } else if (ordinal == 3) {
                                        aVar = f.a.ON_STOP;
                                    } else if (ordinal == 4) {
                                        aVar = f.a.ON_PAUSE;
                                    } else {
                                        throw new IllegalArgumentException(e.a.a.a.a.a("Unexpected state value ", bVar2));
                                    }
                                    this.g.add(a(aVar));
                                    value.a(hVar, aVar);
                                    a();
                                } else {
                                    throw new IllegalArgumentException();
                                }
                            }
                        }
                    }
                    b.c<g, a> cVar = this.f1025a.f676b;
                    if (!this.f && cVar != null && this.f1026b.compareTo(cVar.getValue().f1030a) > 0) {
                        b<g, a>.d a2 = this.f1025a.a();
                        while (a2.hasNext() && !this.f) {
                            Map.Entry next2 = a2.next();
                            a aVar3 = (a) next2.getValue();
                            while (aVar3.f1030a.compareTo(this.f1026b) < 0 && !this.f && this.f1025a.contains(next2.getKey())) {
                                this.g.add(aVar3.f1030a);
                                aVar3.a(hVar, b(aVar3.f1030a));
                                a();
                            }
                        }
                    }
                } else {
                    this.f = false;
                    return;
                }
            }
        }
    }

    public static f.b a(f.b bVar, f.b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }
}

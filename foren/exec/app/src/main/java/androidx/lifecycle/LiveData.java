package androidx.lifecycle;

import c.b.a.a.c;
import c.b.a.b.b;
import c.j.e;
import c.j.f;
import c.j.g;
import c.j.h;
import c.j.i;
import c.j.k;
import c.j.n;

/* loaded from: classes.dex */
public abstract class LiveData<T> {

    /* renamed from: a */
    public static final Object f268a = new Object();

    /* renamed from: e */
    public volatile Object f272e;
    public volatile Object f;
    public boolean h;
    public boolean i;

    /* renamed from: b */
    public final Object f269b = new Object();

    /* renamed from: c */
    public b<n<? super T>, LiveData<T>.a> f270c = new b<>();

    /* renamed from: d */
    public int f271d = 0;
    public int g = -1;

    /* loaded from: classes.dex */
    public abstract class a {

        /* renamed from: a */
        public final n<? super T> f274a;

        /* renamed from: b */
        public boolean f275b;

        /* renamed from: c */
        public int f276c;

        /* renamed from: d */
        public final /* synthetic */ LiveData f277d;

        public void a() {
        }

        public void a(boolean z) {
            if (z != this.f275b) {
                this.f275b = z;
                int i = 1;
                boolean z2 = this.f277d.f271d == 0;
                LiveData liveData = this.f277d;
                int i2 = liveData.f271d;
                if (!this.f275b) {
                    i = -1;
                }
                liveData.f271d = i2 + i;
                if (z2 && this.f275b) {
                    this.f277d.a();
                }
                LiveData liveData2 = this.f277d;
                if (liveData2.f271d == 0 && !this.f275b) {
                    liveData2.b();
                }
                if (this.f275b) {
                    this.f277d.b(this);
                }
            }
        }

        public abstract boolean b();
    }

    public LiveData() {
        Object obj = f268a;
        this.f272e = obj;
        this.f = obj;
        new k(this);
    }

    public void a() {
    }

    public final void a(LiveData<T>.a aVar) {
        if (aVar.f275b) {
            if (!aVar.b()) {
                aVar.a(false);
                return;
            }
            int i = aVar.f276c;
            int i2 = this.g;
            if (i < i2) {
                aVar.f276c = i2;
                aVar.f274a.a((Object) this.f272e);
            }
        }
    }

    public abstract void a(T t);

    public void b() {
    }

    public void b(LiveData<T>.a aVar) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (aVar == null) {
                b<n<? super T>, LiveData<T>.a>.d a2 = this.f270c.a();
                while (a2.hasNext()) {
                    a((a) ((a) a2.next().getValue()));
                    if (this.i) {
                        break;
                    }
                }
            } else {
                a((a) aVar);
                aVar = null;
            }
        } while (this.i);
        this.h = false;
    }

    /* loaded from: classes.dex */
    class LifecycleBoundObserver extends LiveData<T>.a implements e {

        /* renamed from: e */
        public final h f273e;
        public final /* synthetic */ LiveData f;

        @Override // c.j.e
        public void a(h hVar, f.a aVar) {
            if (((i) this.f273e.a()).f1026b == f.b.DESTROYED) {
                this.f.a((n) null);
            } else {
                a(b());
            }
        }

        @Override // androidx.lifecycle.LiveData.a
        public boolean b() {
            return ((i) this.f273e.a()).f1026b.compareTo(f.b.STARTED) >= 0;
        }

        @Override // androidx.lifecycle.LiveData.a
        public void a() {
            c.b.a.b.a<g, i.a> aVar = ((i) this.f273e.a()).f1025a;
            b.c<g, i.a> a2 = aVar.a(this);
            if (a2 != null) {
                aVar.f678d--;
                if (!aVar.f677c.isEmpty()) {
                    for (b.f<g, i.a> fVar : aVar.f677c.keySet()) {
                        fVar.a(a2);
                    }
                }
                b.c<g, i.a> cVar = a2.f682d;
                if (cVar != null) {
                    cVar.f681c = a2.f681c;
                } else {
                    aVar.f675a = a2.f681c;
                }
                b.c<g, i.a> cVar2 = a2.f681c;
                if (cVar2 != null) {
                    cVar2.f682d = a2.f682d;
                } else {
                    aVar.f676b = a2.f682d;
                }
                a2.f681c = null;
                a2.f682d = null;
                i.a aVar2 = a2.f680b;
            }
            aVar.f674e.remove(this);
        }
    }

    public void a(n<? super T> nVar) {
        a("removeObserver");
        LiveData<T>.a remove = this.f270c.remove(nVar);
        if (remove != null) {
            remove.a();
            remove.a(false);
        }
    }

    public static void a(String str) {
        if (!c.b().f668b.a()) {
            throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
        }
    }
}

package u.aly;

import com.hyphenate.util.HanziToPinyin;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import u.aly.cd;
import u.aly.cg;

/* compiled from: TUnion.java */
/* loaded from: classes2.dex */
public abstract class cg<T extends cg<?, ?>, F extends cd> implements bw<T, F> {
    private static final Map<Class<? extends dd>, de> c = new HashMap();
    protected Object a;
    protected F b;

    protected abstract Object a(cv cvVar, cq cqVar) throws cc;

    protected abstract Object a(cv cvVar, short s) throws cc;

    protected abstract F a(short s);

    protected abstract void b(F f, Object obj) throws ClassCastException;

    protected abstract cq c(F f);

    protected abstract void c(cv cvVar) throws cc;

    protected abstract void d(cv cvVar) throws cc;

    protected abstract da e();

    protected cg() {
        this.b = null;
        this.a = null;
    }

    static {
        c.put(df.class, new b());
        c.put(dg.class, new d());
    }

    protected cg(F f, Object obj) {
        a((cg<T, F>) f, obj);
    }

    protected cg(cg<T, F> cgVar) {
        if (!cgVar.getClass().equals(getClass())) {
            throw new ClassCastException();
        }
        this.b = cgVar.b;
        this.a = a(cgVar.a);
    }

    private static Object a(Object obj) {
        if (obj instanceof bw) {
            return ((bw) obj).p();
        }
        if (obj instanceof ByteBuffer) {
            return bx.d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Set) {
            return a((Set) obj);
        }
        if (obj instanceof Map) {
            return a((Map<Object, Object>) obj);
        }
        return obj;
    }

    private static Map a(Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            hashMap.put(a(entry.getKey()), a(entry.getValue()));
        }
        return hashMap;
    }

    private static Set a(Set set) {
        HashSet hashSet = new HashSet();
        for (Object obj : set) {
            hashSet.add(a(obj));
        }
        return hashSet;
    }

    private static List a(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (Object obj : list) {
            arrayList.add(a(obj));
        }
        return arrayList;
    }

    public F a() {
        return this.b;
    }

    public Object c() {
        return this.a;
    }

    public Object a(F f) {
        if (f == this.b) {
            return c();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
    }

    public Object a(int i) {
        return a((cg<T, F>) a((short) i));
    }

    public boolean d() {
        return this.b != null;
    }

    public boolean b(F f) {
        return this.b == f;
    }

    public boolean c(int i) {
        return b((cg<T, F>) a((short) i));
    }

    @Override // u.aly.bw
    public void a(cv cvVar) throws cc {
        c.get(cvVar.D()).b().b(cvVar, this);
    }

    public void a(F f, Object obj) {
        b(f, obj);
        this.b = f;
        this.a = obj;
    }

    public void a(int i, Object obj) {
        a((cg<T, F>) a((short) i), obj);
    }

    @Override // u.aly.bw
    public void b(cv cvVar) throws cc {
        c.get(cvVar.D()).b().a(cvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(getClass().getSimpleName());
        sb.append(HanziToPinyin.Token.SEPARATOR);
        if (a() != null) {
            Object c2 = c();
            sb.append(c((cg<T, F>) a()).a);
            sb.append(":");
            if (c2 instanceof ByteBuffer) {
                bx.a((ByteBuffer) c2, sb);
            } else {
                sb.append(c2.toString());
            }
        }
        sb.append(">");
        return sb.toString();
    }

    @Override // u.aly.bw
    public final void b() {
        this.b = null;
        this.a = null;
    }

    /* compiled from: TUnion.java */
    /* loaded from: classes2.dex */
    private static class b implements de {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TUnion.java */
    /* loaded from: classes2.dex */
    public static class a extends df<cg> {
        private a() {
        }

        /* renamed from: a */
        public void b(cv cvVar, cg cgVar) throws cc {
            cgVar.b = null;
            cgVar.a = null;
            cvVar.j();
            cq l = cvVar.l();
            cgVar.a = cgVar.a(cvVar, l);
            if (cgVar.a != null) {
                cgVar.b = (F) cgVar.a(l.c);
            }
            cvVar.m();
            cvVar.l();
            cvVar.k();
        }

        /* renamed from: b */
        public void a(cv cvVar, cg cgVar) throws cc {
            if (cgVar.a() == null || cgVar.c() == null) {
                throw new cw("Cannot write a TUnion with no set value!");
            }
            cvVar.a(cgVar.e());
            cvVar.a(cgVar.c((cg) cgVar.b));
            cgVar.c(cvVar);
            cvVar.c();
            cvVar.d();
            cvVar.b();
        }
    }

    /* compiled from: TUnion.java */
    /* loaded from: classes2.dex */
    private static class d implements de {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TUnion.java */
    /* loaded from: classes2.dex */
    public static class c extends dg<cg> {
        private c() {
        }

        /* renamed from: a */
        public void b(cv cvVar, cg cgVar) throws cc {
            cgVar.b = null;
            cgVar.a = null;
            short v = cvVar.v();
            cgVar.a = cgVar.a(cvVar, v);
            if (cgVar.a != null) {
                cgVar.b = (F) cgVar.a(v);
            }
        }

        /* renamed from: b */
        public void a(cv cvVar, cg cgVar) throws cc {
            if (cgVar.a() == null || cgVar.c() == null) {
                throw new cw("Cannot write a TUnion with no set value!");
            }
            cvVar.a(cgVar.b.a());
            cgVar.d(cvVar);
        }
    }
}

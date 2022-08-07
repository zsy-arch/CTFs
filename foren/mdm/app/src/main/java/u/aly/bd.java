package u.aly;

import com.alimama.mobile.csdk.umupdate.a.f;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: IdTracking.java */
/* loaded from: classes2.dex */
public class bd implements Serializable, Cloneable, bw<bd, e> {
    public static final Map<e, ci> d;
    private static final long e = -5764118265293965743L;
    private static final da f = new da("IdTracking");
    private static final cq g = new cq("snapshots", dc.k, 1);
    private static final cq h = new cq("journals", dc.m, 2);
    private static final cq i = new cq("checksum", (byte) 11, 3);
    private static final Map<Class<? extends dd>, de> j = new HashMap();
    public Map<String, bc> a;
    public List<bb> b;
    public String c;
    private e[] k;

    static {
        j.put(df.class, new b());
        j.put(dg.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.SNAPSHOTS, (e) new ci("snapshots", (byte) 1, new cl(dc.k, new cj((byte) 11), new cn((byte) 12, bc.class))));
        enumMap.put((EnumMap) e.JOURNALS, (e) new ci("journals", (byte) 2, new ck(dc.m, new cn((byte) 12, bb.class))));
        enumMap.put((EnumMap) e.CHECKSUM, (e) new ci("checksum", (byte) 2, new cj((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        ci.a(bd.class, d);
    }

    /* compiled from: IdTracking.java */
    /* loaded from: classes2.dex */
    public enum e implements cd {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
        CHECKSUM(3, "checksum");
        
        private static final Map<String, e> d = new HashMap();
        private final short e;
        private final String f;

        static {
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return d.get(str);
        }

        e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        @Override // u.aly.cd
        public short a() {
            return this.e;
        }

        @Override // u.aly.cd
        public String b() {
            return this.f;
        }
    }

    public bd() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public bd(Map<String, bc> map) {
        this();
        this.a = map;
    }

    public bd(bd bdVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (bdVar.f()) {
            HashMap hashMap = new HashMap();
            for (Map.Entry<String, bc> entry : bdVar.a.entrySet()) {
                hashMap.put(entry.getKey(), new bc(entry.getValue()));
            }
            this.a = hashMap;
        }
        if (bdVar.k()) {
            ArrayList arrayList = new ArrayList();
            for (bb bbVar : bdVar.b) {
                arrayList.add(new bb(bbVar));
            }
            this.b = arrayList;
        }
        if (bdVar.n()) {
            this.c = bdVar.c;
        }
    }

    /* renamed from: a */
    public bd p() {
        return new bd(this);
    }

    @Override // u.aly.bw
    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int c() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public void a(String str, bc bcVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bcVar);
    }

    public Map<String, bc> d() {
        return this.a;
    }

    public bd a(Map<String, bc> map) {
        this.a = map;
        return this;
    }

    public void e() {
        this.a = null;
    }

    public boolean f() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int g() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }

    public Iterator<bb> h() {
        if (this.b == null) {
            return null;
        }
        return this.b.iterator();
    }

    public void a(bb bbVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(bbVar);
    }

    public List<bb> i() {
        return this.b;
    }

    public bd a(List<bb> list) {
        this.b = list;
        return this;
    }

    public void j() {
        this.b = null;
    }

    public boolean k() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String l() {
        return this.c;
    }

    public bd a(String str) {
        this.c = str;
        return this;
    }

    public void m() {
        this.c = null;
    }

    public boolean n() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    /* renamed from: a */
    public e b(int i2) {
        return e.a(i2);
    }

    @Override // u.aly.bw
    public void a(cv cvVar) throws cc {
        j.get(cvVar.D()).b().b(cvVar, this);
    }

    @Override // u.aly.bw
    public void b(cv cvVar) throws cc {
        j.get(cvVar.D()).b().a(cvVar, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        if (this.a == null) {
            sb.append(f.b);
        } else {
            sb.append(this.a);
        }
        if (k()) {
            sb.append(", ");
            sb.append("journals:");
            if (this.b == null) {
                sb.append(f.b);
            } else {
                sb.append(this.b);
            }
        }
        if (n()) {
            sb.append(", ");
            sb.append("checksum:");
            if (this.c == null) {
                sb.append(f.b);
            } else {
                sb.append(this.c);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void o() throws cc {
        if (this.a == null) {
            throw new cw("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            b(new cp(new dh(objectOutputStream)));
        } catch (cc e2) {
            throw new IOException(e2.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            a(new cp(new dh(objectInputStream)));
        } catch (cc e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: IdTracking.java */
    /* loaded from: classes2.dex */
    private static class b implements de {
        private b() {
        }

        /* renamed from: a */
        public a b() {
            return new a();
        }
    }

    /* compiled from: IdTracking.java */
    /* loaded from: classes2.dex */
    public static class a extends df<bd> {
        private a() {
        }

        /* renamed from: a */
        public void b(cv cvVar, bd bdVar) throws cc {
            cvVar.j();
            while (true) {
                cq l = cvVar.l();
                if (l.b == 0) {
                    cvVar.k();
                    bdVar.o();
                    return;
                }
                switch (l.c) {
                    case 1:
                        if (l.b == 13) {
                            cs n = cvVar.n();
                            bdVar.a = new HashMap(n.c * 2);
                            for (int i = 0; i < n.c; i++) {
                                String z = cvVar.z();
                                bc bcVar = new bc();
                                bcVar.a(cvVar);
                                bdVar.a.put(z, bcVar);
                            }
                            cvVar.o();
                            bdVar.a(true);
                            break;
                        } else {
                            cy.a(cvVar, l.b);
                            break;
                        }
                    case 2:
                        if (l.b == 15) {
                            cr p = cvVar.p();
                            bdVar.b = new ArrayList(p.b);
                            for (int i2 = 0; i2 < p.b; i2++) {
                                bb bbVar = new bb();
                                bbVar.a(cvVar);
                                bdVar.b.add(bbVar);
                            }
                            cvVar.q();
                            bdVar.b(true);
                            break;
                        } else {
                            cy.a(cvVar, l.b);
                            break;
                        }
                    case 3:
                        if (l.b == 11) {
                            bdVar.c = cvVar.z();
                            bdVar.c(true);
                            break;
                        } else {
                            cy.a(cvVar, l.b);
                            break;
                        }
                    default:
                        cy.a(cvVar, l.b);
                        break;
                }
                cvVar.m();
            }
        }

        /* renamed from: b */
        public void a(cv cvVar, bd bdVar) throws cc {
            bdVar.o();
            cvVar.a(bd.f);
            if (bdVar.a != null) {
                cvVar.a(bd.g);
                cvVar.a(new cs((byte) 11, (byte) 12, bdVar.a.size()));
                for (Map.Entry<String, bc> entry : bdVar.a.entrySet()) {
                    cvVar.a(entry.getKey());
                    entry.getValue().b(cvVar);
                }
                cvVar.e();
                cvVar.c();
            }
            if (bdVar.b != null && bdVar.k()) {
                cvVar.a(bd.h);
                cvVar.a(new cr((byte) 12, bdVar.b.size()));
                for (bb bbVar : bdVar.b) {
                    bbVar.b(cvVar);
                }
                cvVar.f();
                cvVar.c();
            }
            if (bdVar.c != null && bdVar.n()) {
                cvVar.a(bd.i);
                cvVar.a(bdVar.c);
                cvVar.c();
            }
            cvVar.d();
            cvVar.b();
        }
    }

    /* compiled from: IdTracking.java */
    /* loaded from: classes2.dex */
    private static class d implements de {
        private d() {
        }

        /* renamed from: a */
        public c b() {
            return new c();
        }
    }

    /* compiled from: IdTracking.java */
    /* loaded from: classes2.dex */
    public static class c extends dg<bd> {
        private c() {
        }

        public void a(cv cvVar, bd bdVar) throws cc {
            db dbVar = (db) cvVar;
            dbVar.a(bdVar.a.size());
            for (Map.Entry<String, bc> entry : bdVar.a.entrySet()) {
                dbVar.a(entry.getKey());
                entry.getValue().b(dbVar);
            }
            BitSet bitSet = new BitSet();
            if (bdVar.k()) {
                bitSet.set(0);
            }
            if (bdVar.n()) {
                bitSet.set(1);
            }
            dbVar.a(bitSet, 2);
            if (bdVar.k()) {
                dbVar.a(bdVar.b.size());
                for (bb bbVar : bdVar.b) {
                    bbVar.b(dbVar);
                }
            }
            if (bdVar.n()) {
                dbVar.a(bdVar.c);
            }
        }

        public void b(cv cvVar, bd bdVar) throws cc {
            db dbVar = (db) cvVar;
            cs csVar = new cs((byte) 11, (byte) 12, dbVar.w());
            bdVar.a = new HashMap(csVar.c * 2);
            for (int i = 0; i < csVar.c; i++) {
                String z = dbVar.z();
                bc bcVar = new bc();
                bcVar.a(dbVar);
                bdVar.a.put(z, bcVar);
            }
            bdVar.a(true);
            BitSet b = dbVar.b(2);
            if (b.get(0)) {
                cr crVar = new cr((byte) 12, dbVar.w());
                bdVar.b = new ArrayList(crVar.b);
                for (int i2 = 0; i2 < crVar.b; i2++) {
                    bb bbVar = new bb();
                    bbVar.a(dbVar);
                    bdVar.b.add(bbVar);
                }
                bdVar.b(true);
            }
            if (b.get(1)) {
                bdVar.c = dbVar.z();
                bdVar.c(true);
            }
        }
    }
}

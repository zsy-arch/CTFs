package u.aly;

import com.alimama.mobile.csdk.umupdate.a.f;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: ImprintValue.java */
/* loaded from: classes2.dex */
public class bf implements Serializable, Cloneable, bw<bf, e> {
    public static final Map<e, ci> d;
    private static final long e = 7501688097813630241L;
    private static final da f = new da("ImprintValue");
    private static final cq g = new cq("value", (byte) 11, 1);
    private static final cq h = new cq("ts", (byte) 10, 2);
    private static final cq i = new cq("guid", (byte) 11, 3);
    private static final Map<Class<? extends dd>, de> j = new HashMap();
    private static final int k = 0;
    public String a;
    public long b;
    public String c;
    private byte l;
    private e[] m;

    static {
        j.put(df.class, new b());
        j.put(dg.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.VALUE, (e) new ci("value", (byte) 2, new cj((byte) 11)));
        enumMap.put((EnumMap) e.TS, (e) new ci("ts", (byte) 1, new cj((byte) 10)));
        enumMap.put((EnumMap) e.GUID, (e) new ci("guid", (byte) 1, new cj((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        ci.a(bf.class, d);
    }

    /* compiled from: ImprintValue.java */
    /* loaded from: classes2.dex */
    public enum e implements cd {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, "guid");
        
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
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
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

    public bf() {
        this.l = (byte) 0;
        this.m = new e[]{e.VALUE};
    }

    public bf(long j2, String str) {
        this();
        this.b = j2;
        b(true);
        this.c = str;
    }

    public bf(bf bfVar) {
        this.l = (byte) 0;
        this.m = new e[]{e.VALUE};
        this.l = bfVar.l;
        if (bfVar.e()) {
            this.a = bfVar.a;
        }
        this.b = bfVar.b;
        if (bfVar.k()) {
            this.c = bfVar.c;
        }
    }

    /* renamed from: a */
    public bf p() {
        return new bf(this);
    }

    @Override // u.aly.bw
    public void b() {
        this.a = null;
        b(false);
        this.b = 0L;
        this.c = null;
    }

    public String c() {
        return this.a;
    }

    public bf a(String str) {
        this.a = str;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public long f() {
        return this.b;
    }

    public bf a(long j2) {
        this.b = j2;
        b(true);
        return this;
    }

    public void g() {
        this.l = bt.b(this.l, 0);
    }

    public boolean h() {
        return bt.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = bt.a(this.l, 0, z);
    }

    public String i() {
        return this.c;
    }

    public bf b(String str) {
        this.c = str;
        return this;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
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
        StringBuilder sb = new StringBuilder("ImprintValue(");
        boolean z = true;
        if (e()) {
            sb.append("value:");
            if (this.a == null) {
                sb.append(f.b);
            } else {
                sb.append(this.a);
            }
            z = false;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.b);
        sb.append(", ");
        sb.append("guid:");
        if (this.c == null) {
            sb.append(f.b);
        } else {
            sb.append(this.c);
        }
        sb.append(")");
        return sb.toString();
    }

    public void l() throws cc {
        if (this.c == null) {
            throw new cw("Required field 'guid' was not present! Struct: " + toString());
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
            this.l = (byte) 0;
            a(new cp(new dh(objectInputStream)));
        } catch (cc e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* compiled from: ImprintValue.java */
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
    /* compiled from: ImprintValue.java */
    /* loaded from: classes2.dex */
    public static class a extends df<bf> {
        private a() {
        }

        /* renamed from: a */
        public void b(cv cvVar, bf bfVar) throws cc {
            cvVar.j();
            while (true) {
                cq l = cvVar.l();
                if (l.b == 0) {
                    cvVar.k();
                    if (!bfVar.h()) {
                        throw new cw("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    bfVar.l();
                    return;
                }
                switch (l.c) {
                    case 1:
                        if (l.b != 11) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bfVar.a = cvVar.z();
                            bfVar.a(true);
                            break;
                        }
                    case 2:
                        if (l.b != 10) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bfVar.b = cvVar.x();
                            bfVar.b(true);
                            break;
                        }
                    case 3:
                        if (l.b != 11) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bfVar.c = cvVar.z();
                            bfVar.c(true);
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
        public void a(cv cvVar, bf bfVar) throws cc {
            bfVar.l();
            cvVar.a(bf.f);
            if (bfVar.a != null && bfVar.e()) {
                cvVar.a(bf.g);
                cvVar.a(bfVar.a);
                cvVar.c();
            }
            cvVar.a(bf.h);
            cvVar.a(bfVar.b);
            cvVar.c();
            if (bfVar.c != null) {
                cvVar.a(bf.i);
                cvVar.a(bfVar.c);
                cvVar.c();
            }
            cvVar.d();
            cvVar.b();
        }
    }

    /* compiled from: ImprintValue.java */
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
    /* compiled from: ImprintValue.java */
    /* loaded from: classes2.dex */
    public static class c extends dg<bf> {
        private c() {
        }

        public void a(cv cvVar, bf bfVar) throws cc {
            db dbVar = (db) cvVar;
            dbVar.a(bfVar.b);
            dbVar.a(bfVar.c);
            BitSet bitSet = new BitSet();
            if (bfVar.e()) {
                bitSet.set(0);
            }
            dbVar.a(bitSet, 1);
            if (bfVar.e()) {
                dbVar.a(bfVar.a);
            }
        }

        public void b(cv cvVar, bf bfVar) throws cc {
            db dbVar = (db) cvVar;
            bfVar.b = dbVar.x();
            bfVar.b(true);
            bfVar.c = dbVar.z();
            bfVar.c(true);
            if (dbVar.b(1).get(0)) {
                bfVar.a = dbVar.z();
                bfVar.a(true);
            }
        }
    }
}

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

/* compiled from: Response.java */
/* loaded from: classes2.dex */
public class bg implements Serializable, Cloneable, bw<bg, e> {
    public static final Map<e, ci> d;
    private static final long e = -4549277923241195391L;
    private static final da f = new da("Response");
    private static final cq g = new cq("resp_code", (byte) 8, 1);
    private static final cq h = new cq("msg", (byte) 11, 2);
    private static final cq i = new cq(av.N, (byte) 12, 3);
    private static final Map<Class<? extends dd>, de> j = new HashMap();
    private static final int k = 0;
    public int a;
    public String b;
    public be c;
    private byte l;
    private e[] m;

    static {
        j.put(df.class, new b());
        j.put(dg.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put((EnumMap) e.RESP_CODE, (e) new ci("resp_code", (byte) 1, new cj((byte) 8)));
        enumMap.put((EnumMap) e.MSG, (e) new ci("msg", (byte) 2, new cj((byte) 11)));
        enumMap.put((EnumMap) e.IMPRINT, (e) new ci(av.N, (byte) 2, new cn((byte) 12, be.class)));
        d = Collections.unmodifiableMap(enumMap);
        ci.a(bg.class, d);
    }

    /* compiled from: Response.java */
    /* loaded from: classes2.dex */
    public enum e implements cd {
        RESP_CODE(1, "resp_code"),
        MSG(2, "msg"),
        IMPRINT(3, av.N);
        
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
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
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

    public bg() {
        this.l = (byte) 0;
        this.m = new e[]{e.MSG, e.IMPRINT};
    }

    public bg(int i2) {
        this();
        this.a = i2;
        a(true);
    }

    public bg(bg bgVar) {
        this.l = (byte) 0;
        this.m = new e[]{e.MSG, e.IMPRINT};
        this.l = bgVar.l;
        this.a = bgVar.a;
        if (bgVar.h()) {
            this.b = bgVar.b;
        }
        if (bgVar.k()) {
            this.c = new be(bgVar.c);
        }
    }

    /* renamed from: a */
    public bg p() {
        return new bg(this);
    }

    @Override // u.aly.bw
    public void b() {
        a(false);
        this.a = 0;
        this.b = null;
        this.c = null;
    }

    public int c() {
        return this.a;
    }

    public bg a(int i2) {
        this.a = i2;
        a(true);
        return this;
    }

    public void d() {
        this.l = bt.b(this.l, 0);
    }

    public boolean e() {
        return bt.a(this.l, 0);
    }

    public void a(boolean z) {
        this.l = bt.a(this.l, 0, z);
    }

    public String f() {
        return this.b;
    }

    public bg a(String str) {
        this.b = str;
        return this;
    }

    public void g() {
        this.b = null;
    }

    public boolean h() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public be i() {
        return this.c;
    }

    public bg a(be beVar) {
        this.c = beVar;
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

    /* renamed from: c */
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
        StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.a);
        if (h()) {
            sb.append(", ");
            sb.append("msg:");
            if (this.b == null) {
                sb.append(f.b);
            } else {
                sb.append(this.b);
            }
        }
        if (k()) {
            sb.append(", ");
            sb.append("imprint:");
            if (this.c == null) {
                sb.append(f.b);
            } else {
                sb.append(this.c);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void l() throws cc {
        if (this.c != null) {
            this.c.m();
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

    /* compiled from: Response.java */
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
    /* compiled from: Response.java */
    /* loaded from: classes2.dex */
    public static class a extends df<bg> {
        private a() {
        }

        /* renamed from: a */
        public void b(cv cvVar, bg bgVar) throws cc {
            cvVar.j();
            while (true) {
                cq l = cvVar.l();
                if (l.b == 0) {
                    cvVar.k();
                    if (!bgVar.e()) {
                        throw new cw("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                    }
                    bgVar.l();
                    return;
                }
                switch (l.c) {
                    case 1:
                        if (l.b != 8) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bgVar.a = cvVar.w();
                            bgVar.a(true);
                            break;
                        }
                    case 2:
                        if (l.b != 11) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bgVar.b = cvVar.z();
                            bgVar.b(true);
                            break;
                        }
                    case 3:
                        if (l.b != 12) {
                            cy.a(cvVar, l.b);
                            break;
                        } else {
                            bgVar.c = new be();
                            bgVar.c.a(cvVar);
                            bgVar.c(true);
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
        public void a(cv cvVar, bg bgVar) throws cc {
            bgVar.l();
            cvVar.a(bg.f);
            cvVar.a(bg.g);
            cvVar.a(bgVar.a);
            cvVar.c();
            if (bgVar.b != null && bgVar.h()) {
                cvVar.a(bg.h);
                cvVar.a(bgVar.b);
                cvVar.c();
            }
            if (bgVar.c != null && bgVar.k()) {
                cvVar.a(bg.i);
                bgVar.c.b(cvVar);
                cvVar.c();
            }
            cvVar.d();
            cvVar.b();
        }
    }

    /* compiled from: Response.java */
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
    /* compiled from: Response.java */
    /* loaded from: classes2.dex */
    public static class c extends dg<bg> {
        private c() {
        }

        public void a(cv cvVar, bg bgVar) throws cc {
            db dbVar = (db) cvVar;
            dbVar.a(bgVar.a);
            BitSet bitSet = new BitSet();
            if (bgVar.h()) {
                bitSet.set(0);
            }
            if (bgVar.k()) {
                bitSet.set(1);
            }
            dbVar.a(bitSet, 2);
            if (bgVar.h()) {
                dbVar.a(bgVar.b);
            }
            if (bgVar.k()) {
                bgVar.c.b(dbVar);
            }
        }

        public void b(cv cvVar, bg bgVar) throws cc {
            db dbVar = (db) cvVar;
            bgVar.a = dbVar.w();
            bgVar.a(true);
            BitSet b = dbVar.b(2);
            if (b.get(0)) {
                bgVar.b = dbVar.z();
                bgVar.b(true);
            }
            if (b.get(1)) {
                bgVar.c = new be();
                bgVar.c.a(dbVar);
                bgVar.c(true);
            }
        }
    }
}

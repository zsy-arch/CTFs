package u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TBinaryProtocol.java */
/* loaded from: classes2.dex */
public class co extends cv {
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    private static final da h = new da();
    protected boolean c;
    protected boolean d;
    protected int e;
    protected boolean f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    /* compiled from: TBinaryProtocol.java */
    /* loaded from: classes2.dex */
    public static class a implements cx {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.a = false;
            this.b = true;
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        @Override // u.aly.cx
        public cv a(dj djVar) {
            co coVar = new co(djVar, this.a, this.b);
            if (this.c != 0) {
                coVar.c(this.c);
            }
            return coVar;
        }
    }

    public co(dj djVar) {
        this(djVar, false, true);
    }

    public co(dj djVar, boolean z, boolean z2) {
        super(djVar);
        this.c = false;
        this.d = true;
        this.f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = z;
        this.d = z2;
    }

    @Override // u.aly.cv
    public void a(ct ctVar) throws cc {
        if (this.d) {
            a(b | ctVar.b);
            a(ctVar.a);
            a(ctVar.c);
            return;
        }
        a(ctVar.a);
        a(ctVar.b);
        a(ctVar.c);
    }

    @Override // u.aly.cv
    public void a() {
    }

    @Override // u.aly.cv
    public void a(da daVar) {
    }

    @Override // u.aly.cv
    public void b() {
    }

    @Override // u.aly.cv
    public void a(cq cqVar) throws cc {
        a(cqVar.b);
        a(cqVar.c);
    }

    @Override // u.aly.cv
    public void c() {
    }

    @Override // u.aly.cv
    public void d() throws cc {
        a((byte) 0);
    }

    @Override // u.aly.cv
    public void a(cs csVar) throws cc {
        a(csVar.a);
        a(csVar.b);
        a(csVar.c);
    }

    @Override // u.aly.cv
    public void e() {
    }

    @Override // u.aly.cv
    public void a(cr crVar) throws cc {
        a(crVar.a);
        a(crVar.b);
    }

    @Override // u.aly.cv
    public void f() {
    }

    @Override // u.aly.cv
    public void a(cz czVar) throws cc {
        a(czVar.a);
        a(czVar.b);
    }

    @Override // u.aly.cv
    public void g() {
    }

    @Override // u.aly.cv
    public void a(boolean z) throws cc {
        a(z ? (byte) 1 : (byte) 0);
    }

    @Override // u.aly.cv
    public void a(byte b2) throws cc {
        this.i[0] = b2;
        this.g.b(this.i, 0, 1);
    }

    @Override // u.aly.cv
    public void a(short s) throws cc {
        this.j[0] = (byte) ((s >> 8) & 255);
        this.j[1] = (byte) (s & 255);
        this.g.b(this.j, 0, 2);
    }

    @Override // u.aly.cv
    public void a(int i) throws cc {
        this.k[0] = (byte) ((i >> 24) & 255);
        this.k[1] = (byte) ((i >> 16) & 255);
        this.k[2] = (byte) ((i >> 8) & 255);
        this.k[3] = (byte) (i & 255);
        this.g.b(this.k, 0, 4);
    }

    @Override // u.aly.cv
    public void a(long j) throws cc {
        this.l[0] = (byte) ((j >> 56) & 255);
        this.l[1] = (byte) ((j >> 48) & 255);
        this.l[2] = (byte) ((j >> 40) & 255);
        this.l[3] = (byte) ((j >> 32) & 255);
        this.l[4] = (byte) ((j >> 24) & 255);
        this.l[5] = (byte) ((j >> 16) & 255);
        this.l[6] = (byte) ((j >> 8) & 255);
        this.l[7] = (byte) (255 & j);
        this.g.b(this.l, 0, 8);
    }

    @Override // u.aly.cv
    public void a(double d) throws cc {
        a(Double.doubleToLongBits(d));
    }

    @Override // u.aly.cv
    public void a(String str) throws cc {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.g.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new cc("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // u.aly.cv
    public void a(ByteBuffer byteBuffer) throws cc {
        int limit = byteBuffer.limit() - byteBuffer.position();
        a(limit);
        this.g.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    @Override // u.aly.cv
    public ct h() throws cc {
        int w = w();
        if (w < 0) {
            if (((-65536) & w) == b) {
                return new ct(z(), (byte) (w & 255), w());
            }
            throw new cw(4, "Bad version in readMessageBegin");
        } else if (!this.c) {
            return new ct(b(w), u(), w());
        } else {
            throw new cw(4, "Missing version in readMessageBegin, old client?");
        }
    }

    @Override // u.aly.cv
    public void i() {
    }

    @Override // u.aly.cv
    public da j() {
        return h;
    }

    @Override // u.aly.cv
    public void k() {
    }

    @Override // u.aly.cv
    public cq l() throws cc {
        byte u2 = u();
        return new cq("", u2, u2 == 0 ? (short) 0 : v());
    }

    @Override // u.aly.cv
    public void m() {
    }

    @Override // u.aly.cv
    public cs n() throws cc {
        return new cs(u(), u(), w());
    }

    @Override // u.aly.cv
    public void o() {
    }

    @Override // u.aly.cv
    public cr p() throws cc {
        return new cr(u(), w());
    }

    @Override // u.aly.cv
    public void q() {
    }

    @Override // u.aly.cv
    public cz r() throws cc {
        return new cz(u(), w());
    }

    @Override // u.aly.cv
    public void s() {
    }

    @Override // u.aly.cv
    public boolean t() throws cc {
        return u() == 1;
    }

    @Override // u.aly.cv
    public byte u() throws cc {
        if (this.g.h() >= 1) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        a(this.m, 0, 1);
        return this.m[0];
    }

    @Override // u.aly.cv
    public short v() throws cc {
        int i = 0;
        byte[] bArr = this.n;
        if (this.g.h() >= 2) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(2);
        } else {
            a(this.n, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    @Override // u.aly.cv
    public int w() throws cc {
        int i = 0;
        byte[] bArr = this.o;
        if (this.g.h() >= 4) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(4);
        } else {
            a(this.o, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    @Override // u.aly.cv
    public long x() throws cc {
        int i = 0;
        byte[] bArr = this.p;
        if (this.g.h() >= 8) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(8);
        } else {
            a(this.p, 0, 8);
        }
        return (bArr[i + 7] & 255) | ((bArr[i] & 255) << 56) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8);
    }

    @Override // u.aly.cv
    public double y() throws cc {
        return Double.longBitsToDouble(x());
    }

    @Override // u.aly.cv
    public String z() throws cc {
        int w = w();
        if (this.g.h() < w) {
            return b(w);
        }
        try {
            String str = new String(this.g.f(), this.g.g(), w, "UTF-8");
            this.g.a(w);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new cc("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public String b(int i) throws cc {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.g.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new cc("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // u.aly.cv
    public ByteBuffer A() throws cc {
        int w = w();
        d(w);
        if (this.g.h() >= w) {
            ByteBuffer wrap = ByteBuffer.wrap(this.g.f(), this.g.g(), w);
            this.g.a(w);
            return wrap;
        }
        byte[] bArr = new byte[w];
        this.g.d(bArr, 0, w);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i, int i2) throws cc {
        d(i2);
        return this.g.d(bArr, i, i2);
    }

    public void c(int i) {
        this.e = i;
        this.f = true;
    }

    protected void d(int i) throws cc {
        if (i < 0) {
            throw new cw("Negative length: " + i);
        } else if (this.f) {
            this.e -= i;
            if (this.e < 0) {
                throw new cw("Message length exceeded: " + i);
            }
        }
    }
}

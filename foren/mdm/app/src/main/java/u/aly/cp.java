package u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TCompactProtocol.java */
/* loaded from: classes2.dex */
public class cp extends cv {
    private static final da d = new da("");
    private static final cq e = new cq("", (byte) 0, 0);
    private static final byte[] f = new byte[16];
    private static final byte h = -126;
    private static final byte i = 1;
    private static final byte j = 31;
    private static final byte k = -32;
    private static final int l = 5;
    byte[] a;
    byte[] b;
    byte[] c;
    private bu m;
    private short n;
    private cq o;
    private Boolean p;
    private final long q;
    private byte[] r;

    static {
        f[0] = 0;
        f[2] = 1;
        f[3] = 3;
        f[6] = 4;
        f[8] = 5;
        f[10] = 6;
        f[4] = 7;
        f[11] = 8;
        f[15] = 9;
        f[14] = 10;
        f[13] = 11;
        f[12] = 12;
    }

    /* compiled from: TCompactProtocol.java */
    /* loaded from: classes2.dex */
    public static class a implements cx {
        private final long a;

        public a() {
            this.a = -1L;
        }

        public a(int i) {
            this.a = i;
        }

        @Override // u.aly.cx
        public cv a(dj djVar) {
            return new cp(djVar, this.a);
        }
    }

    /* compiled from: TCompactProtocol.java */
    /* loaded from: classes2.dex */
    private static class b {
        public static final byte a = 1;
        public static final byte b = 2;
        public static final byte c = 3;
        public static final byte d = 4;
        public static final byte e = 5;
        public static final byte f = 6;
        public static final byte g = 7;
        public static final byte h = 8;
        public static final byte i = 9;
        public static final byte j = 10;
        public static final byte k = 11;
        public static final byte l = 12;

        private b() {
        }
    }

    public cp(dj djVar, long j2) {
        super(djVar);
        this.m = new bu(15);
        this.n = (short) 0;
        this.o = null;
        this.p = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.r = new byte[1];
        this.c = new byte[1];
        this.q = j2;
    }

    public cp(dj djVar) {
        this(djVar, -1L);
    }

    @Override // u.aly.cv
    public void B() {
        this.m.c();
        this.n = (short) 0;
    }

    @Override // u.aly.cv
    public void a(ct ctVar) throws cc {
        b(h);
        d(((ctVar.b << 5) & (-32)) | 1);
        b(ctVar.c);
        a(ctVar.a);
    }

    @Override // u.aly.cv
    public void a(da daVar) throws cc {
        this.m.a(this.n);
        this.n = (short) 0;
    }

    @Override // u.aly.cv
    public void b() throws cc {
        this.n = this.m.a();
    }

    @Override // u.aly.cv
    public void a(cq cqVar) throws cc {
        if (cqVar.b == 2) {
            this.o = cqVar;
        } else {
            a(cqVar, (byte) -1);
        }
    }

    private void a(cq cqVar, byte b2) throws cc {
        if (b2 == -1) {
            b2 = e(cqVar.b);
        }
        if (cqVar.c <= this.n || cqVar.c - this.n > 15) {
            b(b2);
            a(cqVar.c);
        } else {
            d(((cqVar.c - this.n) << 4) | b2);
        }
        this.n = cqVar.c;
    }

    @Override // u.aly.cv
    public void d() throws cc {
        b((byte) 0);
    }

    @Override // u.aly.cv
    public void a(cs csVar) throws cc {
        if (csVar.c == 0) {
            d(0);
            return;
        }
        b(csVar.c);
        d((e(csVar.a) << 4) | e(csVar.b));
    }

    @Override // u.aly.cv
    public void a(cr crVar) throws cc {
        a(crVar.a, crVar.b);
    }

    @Override // u.aly.cv
    public void a(cz czVar) throws cc {
        a(czVar.a, czVar.b);
    }

    @Override // u.aly.cv
    public void a(boolean z) throws cc {
        byte b2 = 1;
        if (this.o != null) {
            cq cqVar = this.o;
            if (!z) {
                b2 = 2;
            }
            a(cqVar, b2);
            this.o = null;
            return;
        }
        if (!z) {
            b2 = 2;
        }
        b(b2);
    }

    @Override // u.aly.cv
    public void a(byte b2) throws cc {
        b(b2);
    }

    @Override // u.aly.cv
    public void a(short s) throws cc {
        b(c((int) s));
    }

    @Override // u.aly.cv
    public void a(int i2) throws cc {
        b(c(i2));
    }

    @Override // u.aly.cv
    public void a(long j2) throws cc {
        b(c(j2));
    }

    @Override // u.aly.cv
    public void a(double d2) throws cc {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        a(Double.doubleToLongBits(d2), bArr, 0);
        this.g.b(bArr);
    }

    @Override // u.aly.cv
    public void a(String str) throws cc {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e2) {
            throw new cc("UTF-8 not supported!");
        }
    }

    @Override // u.aly.cv
    public void a(ByteBuffer byteBuffer) throws cc {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i2, int i3) throws cc {
        b(i3);
        this.g.b(bArr, i2, i3);
    }

    @Override // u.aly.cv
    public void a() throws cc {
    }

    @Override // u.aly.cv
    public void e() throws cc {
    }

    @Override // u.aly.cv
    public void f() throws cc {
    }

    @Override // u.aly.cv
    public void g() throws cc {
    }

    @Override // u.aly.cv
    public void c() throws cc {
    }

    protected void a(byte b2, int i2) throws cc {
        if (i2 <= 14) {
            d((i2 << 4) | e(b2));
            return;
        }
        d(e(b2) | 240);
        b(i2);
    }

    private void b(int i2) throws cc {
        int i3 = 0;
        while ((i2 & (-128)) != 0) {
            i3++;
            this.a[i3] = (byte) ((i2 & 127) | 128);
            i2 >>>= 7;
        }
        this.a[i3] = (byte) i2;
        this.g.b(this.a, 0, i3 + 1);
    }

    private void b(long j2) throws cc {
        int i2 = 0;
        while (((-128) & j2) != 0) {
            i2++;
            this.b[i2] = (byte) ((127 & j2) | 128);
            j2 >>>= 7;
        }
        this.b[i2] = (byte) j2;
        this.g.b(this.b, 0, i2 + 1);
    }

    private long c(long j2) {
        return (j2 << 1) ^ (j2 >> 63);
    }

    private int c(int i2) {
        return (i2 << 1) ^ (i2 >> 31);
    }

    private void a(long j2, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) (j2 & 255);
        bArr[i2 + 1] = (byte) ((j2 >> 8) & 255);
        bArr[i2 + 2] = (byte) ((j2 >> 16) & 255);
        bArr[i2 + 3] = (byte) ((j2 >> 24) & 255);
        bArr[i2 + 4] = (byte) ((j2 >> 32) & 255);
        bArr[i2 + 5] = (byte) ((j2 >> 40) & 255);
        bArr[i2 + 6] = (byte) ((j2 >> 48) & 255);
        bArr[i2 + 7] = (byte) ((j2 >> 56) & 255);
    }

    private void b(byte b2) throws cc {
        this.r[0] = b2;
        this.g.b(this.r);
    }

    private void d(int i2) throws cc {
        b((byte) i2);
    }

    @Override // u.aly.cv
    public ct h() throws cc {
        byte u2 = u();
        if (u2 != -126) {
            throw new cw("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(u2));
        }
        byte u3 = u();
        byte b2 = (byte) (u3 & j);
        if (b2 != 1) {
            throw new cw("Expected version 1 but got " + ((int) b2));
        }
        int E = E();
        return new ct(z(), (byte) ((u3 >> 5) & 3), E);
    }

    @Override // u.aly.cv
    public da j() throws cc {
        this.m.a(this.n);
        this.n = (short) 0;
        return d;
    }

    @Override // u.aly.cv
    public void k() throws cc {
        this.n = this.m.a();
    }

    @Override // u.aly.cv
    public cq l() throws cc {
        short s;
        byte u2 = u();
        if (u2 == 0) {
            return e;
        }
        short s2 = (short) ((u2 & 240) >> 4);
        if (s2 == 0) {
            s = v();
        } else {
            s = (short) (s2 + this.n);
        }
        cq cqVar = new cq("", d((byte) (u2 & dc.m)), s);
        if (c(u2)) {
            this.p = ((byte) (u2 & dc.m)) == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.n = cqVar.c;
        return cqVar;
    }

    @Override // u.aly.cv
    public cs n() throws cc {
        int E = E();
        byte u2 = E == 0 ? (byte) 0 : u();
        return new cs(d((byte) (u2 >> 4)), d((byte) (u2 & dc.m)), E);
    }

    @Override // u.aly.cv
    public cr p() throws cc {
        byte u2 = u();
        int i2 = (u2 >> 4) & 15;
        if (i2 == 15) {
            i2 = E();
        }
        return new cr(d(u2), i2);
    }

    @Override // u.aly.cv
    public cz r() throws cc {
        return new cz(p());
    }

    @Override // u.aly.cv
    public boolean t() throws cc {
        if (this.p == null) {
            return u() == 1;
        }
        boolean booleanValue = this.p.booleanValue();
        this.p = null;
        return booleanValue;
    }

    @Override // u.aly.cv
    public byte u() throws cc {
        if (this.g.h() > 0) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        this.g.d(this.c, 0, 1);
        return this.c[0];
    }

    @Override // u.aly.cv
    public short v() throws cc {
        return (short) g(E());
    }

    @Override // u.aly.cv
    public int w() throws cc {
        return g(E());
    }

    @Override // u.aly.cv
    public long x() throws cc {
        return d(F());
    }

    @Override // u.aly.cv
    public double y() throws cc {
        byte[] bArr = new byte[8];
        this.g.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    @Override // u.aly.cv
    public String z() throws cc {
        String str;
        int E = E();
        f(E);
        if (E == 0) {
            return "";
        }
        try {
            if (this.g.h() >= E) {
                str = new String(this.g.f(), this.g.g(), E, "UTF-8");
                this.g.a(E);
            } else {
                str = new String(e(E), "UTF-8");
            }
            return str;
        } catch (UnsupportedEncodingException e2) {
            throw new cc("UTF-8 not supported!");
        }
    }

    @Override // u.aly.cv
    public ByteBuffer A() throws cc {
        int E = E();
        f(E);
        if (E == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[E];
        this.g.d(bArr, 0, E);
        return ByteBuffer.wrap(bArr);
    }

    private byte[] e(int i2) throws cc {
        if (i2 == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i2];
        this.g.d(bArr, 0, i2);
        return bArr;
    }

    private void f(int i2) throws cw {
        if (i2 < 0) {
            throw new cw("Negative length: " + i2);
        } else if (this.q != -1 && i2 > this.q) {
            throw new cw("Length exceeded max allowed: " + i2);
        }
    }

    @Override // u.aly.cv
    public void i() throws cc {
    }

    @Override // u.aly.cv
    public void m() throws cc {
    }

    @Override // u.aly.cv
    public void o() throws cc {
    }

    @Override // u.aly.cv
    public void q() throws cc {
    }

    @Override // u.aly.cv
    public void s() throws cc {
    }

    private int E() throws cc {
        int i2 = 0;
        if (this.g.h() >= 5) {
            byte[] f2 = this.g.f();
            int g = this.g.g();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                byte b2 = f2[g + i2];
                i4 |= (b2 & Byte.MAX_VALUE) << i3;
                if ((b2 & 128) != 128) {
                    this.g.a(i2 + 1);
                    return i4;
                }
                i3 += 7;
                i2++;
            }
        } else {
            int i5 = 0;
            while (true) {
                byte u2 = u();
                i5 |= (u2 & Byte.MAX_VALUE) << i2;
                if ((u2 & 128) != 128) {
                    return i5;
                }
                i2 += 7;
            }
        }
    }

    private long F() throws cc {
        int i2 = 0;
        long j2 = 0;
        if (this.g.h() >= 10) {
            byte[] f2 = this.g.f();
            int g = this.g.g();
            int i3 = 0;
            while (true) {
                byte b2 = f2[g + i2];
                j2 |= (b2 & Byte.MAX_VALUE) << i3;
                if ((b2 & 128) != 128) {
                    break;
                }
                i3 += 7;
                i2++;
            }
            this.g.a(i2 + 1);
        } else {
            while (true) {
                byte u2 = u();
                j2 |= (u2 & Byte.MAX_VALUE) << i2;
                if ((u2 & 128) != 128) {
                    break;
                }
                i2 += 7;
            }
        }
        return j2;
    }

    private int g(int i2) {
        return (i2 >>> 1) ^ (-(i2 & 1));
    }

    private long d(long j2) {
        return (j2 >>> 1) ^ (-(1 & j2));
    }

    private long a(byte[] bArr) {
        return ((bArr[7] & 255) << 56) | ((bArr[6] & 255) << 48) | ((bArr[5] & 255) << 40) | ((bArr[4] & 255) << 32) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8) | (bArr[0] & 255);
    }

    private boolean c(byte b2) {
        int i2 = b2 & dc.m;
        return i2 == 1 || i2 == 2;
    }

    private byte d(byte b2) throws cw {
        switch ((byte) (b2 & dc.m)) {
            case 0:
                return (byte) 0;
            case 1:
            case 2:
                return (byte) 2;
            case 3:
                return (byte) 3;
            case 4:
                return (byte) 6;
            case 5:
                return (byte) 8;
            case 6:
                return (byte) 10;
            case 7:
                return (byte) 4;
            case 8:
                return (byte) 11;
            case 9:
                return dc.m;
            case 10:
                return dc.l;
            case 11:
                return dc.k;
            case 12:
                return (byte) 12;
            default:
                throw new cw("don't know what type: " + ((int) ((byte) (b2 & dc.m))));
        }
    }

    private byte e(byte b2) {
        return f[b2];
    }
}

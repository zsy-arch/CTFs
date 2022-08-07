package u.aly;

/* compiled from: TMemoryInputTransport.java */
/* loaded from: classes2.dex */
public final class di extends dj {
    private byte[] a;
    private int b;
    private int c;

    public di() {
    }

    public di(byte[] bArr) {
        a(bArr);
    }

    public di(byte[] bArr, int i, int i2) {
        c(bArr, i, i2);
    }

    public void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public void c(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i + i2;
    }

    public void e() {
        this.a = null;
    }

    @Override // u.aly.dj
    public void c() {
    }

    @Override // u.aly.dj
    public boolean a() {
        return true;
    }

    @Override // u.aly.dj
    public void b() throws dk {
    }

    @Override // u.aly.dj
    public int a(byte[] bArr, int i, int i2) throws dk {
        int h = h();
        if (i2 > h) {
            i2 = h;
        }
        if (i2 > 0) {
            System.arraycopy(this.a, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    @Override // u.aly.dj
    public void b(byte[] bArr, int i, int i2) throws dk {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // u.aly.dj
    public byte[] f() {
        return this.a;
    }

    @Override // u.aly.dj
    public int g() {
        return this.b;
    }

    @Override // u.aly.dj
    public int h() {
        return this.c - this.b;
    }

    @Override // u.aly.dj
    public void a(int i) {
        this.b += i;
    }
}

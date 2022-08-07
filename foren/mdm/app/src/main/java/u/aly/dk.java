package u.aly;

/* compiled from: TTransportException.java */
/* loaded from: classes2.dex */
public class dk extends cc {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    private static final long g = 1;
    protected int f;

    public dk() {
        this.f = 0;
    }

    public dk(int i) {
        this.f = 0;
        this.f = i;
    }

    public dk(int i, String str) {
        super(str);
        this.f = 0;
        this.f = i;
    }

    public dk(String str) {
        super(str);
        this.f = 0;
    }

    public dk(int i, Throwable th) {
        super(th);
        this.f = 0;
        this.f = i;
    }

    public dk(Throwable th) {
        super(th);
        this.f = 0;
    }

    public dk(String str, Throwable th) {
        super(str, th);
        this.f = 0;
    }

    public dk(int i, String str, Throwable th) {
        super(str, th);
        this.f = 0;
        this.f = i;
    }

    public int a() {
        return this.f;
    }
}

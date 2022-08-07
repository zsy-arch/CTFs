package u.aly;

/* compiled from: TApplicationException.java */
/* loaded from: classes2.dex */
public class bv extends cc {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final da j = new da("TApplicationException");
    private static final cq k = new cq("message", (byte) 11, 1);
    private static final cq l = new cq("type", (byte) 8, 2);
    private static final long m = 1;
    protected int i;

    public bv() {
        this.i = 0;
    }

    public bv(int i) {
        this.i = 0;
        this.i = i;
    }

    public bv(int i, String str) {
        super(str);
        this.i = 0;
        this.i = i;
    }

    public bv(String str) {
        super(str);
        this.i = 0;
    }

    public int a() {
        return this.i;
    }

    public static bv a(cv cvVar) throws cc {
        cvVar.j();
        String str = null;
        int i = 0;
        while (true) {
            cq l2 = cvVar.l();
            if (l2.b == 0) {
                cvVar.k();
                return new bv(i, str);
            }
            switch (l2.c) {
                case 1:
                    if (l2.b != 11) {
                        cy.a(cvVar, l2.b);
                        break;
                    } else {
                        str = cvVar.z();
                        break;
                    }
                case 2:
                    if (l2.b != 8) {
                        cy.a(cvVar, l2.b);
                        break;
                    } else {
                        i = cvVar.w();
                        break;
                    }
                default:
                    cy.a(cvVar, l2.b);
                    break;
            }
            cvVar.m();
        }
    }

    public void b(cv cvVar) throws cc {
        cvVar.a(j);
        if (getMessage() != null) {
            cvVar.a(k);
            cvVar.a(getMessage());
            cvVar.c();
        }
        cvVar.a(l);
        cvVar.a(this.i);
        cvVar.c();
        cvVar.d();
        cvVar.b();
    }
}

package u.aly;

import u.aly.cp;

/* compiled from: TProtocolUtil.java */
/* loaded from: classes2.dex */
public class cy {
    private static int a = Integer.MAX_VALUE;

    public static void a(int i) {
        a = i;
    }

    public static void a(cv cvVar, byte b) throws cc {
        a(cvVar, b, a);
    }

    public static void a(cv cvVar, byte b, int i) throws cc {
        int i2 = 0;
        if (i <= 0) {
            throw new cc("Maximum skip depth exceeded");
        }
        switch (b) {
            case 2:
                cvVar.t();
                return;
            case 3:
                cvVar.u();
                return;
            case 4:
                cvVar.y();
                return;
            case 5:
            case 7:
            case 9:
            default:
                return;
            case 6:
                cvVar.v();
                return;
            case 8:
                cvVar.w();
                return;
            case 10:
                cvVar.x();
                return;
            case 11:
                cvVar.A();
                return;
            case 12:
                cvVar.j();
                while (true) {
                    cq l = cvVar.l();
                    if (l.b == 0) {
                        cvVar.k();
                        return;
                    } else {
                        a(cvVar, l.b, i - 1);
                        cvVar.m();
                    }
                }
            case 13:
                cs n = cvVar.n();
                while (i2 < n.c) {
                    a(cvVar, n.a, i - 1);
                    a(cvVar, n.b, i - 1);
                    i2++;
                }
                cvVar.o();
                return;
            case 14:
                cz r = cvVar.r();
                while (i2 < r.b) {
                    a(cvVar, r.a, i - 1);
                    i2++;
                }
                cvVar.s();
                return;
            case 15:
                cr p = cvVar.p();
                while (i2 < p.b) {
                    a(cvVar, p.a, i - 1);
                    i2++;
                }
                cvVar.q();
                return;
        }
    }

    public static cx a(byte[] bArr, cx cxVar) {
        if (bArr[0] > 16) {
            return new cp.a();
        }
        if (bArr.length <= 1 || (bArr[1] & 128) == 0) {
            return cxVar;
        }
        return new cp.a();
    }
}

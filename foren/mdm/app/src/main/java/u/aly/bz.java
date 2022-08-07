package u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import u.aly.cp;

/* compiled from: TDeserializer.java */
/* loaded from: classes2.dex */
public class bz {
    private final cv a;
    private final di b;

    public bz() {
        this(new cp.a());
    }

    public bz(cx cxVar) {
        this.b = new di();
        this.a = cxVar.a(this.b);
    }

    public void a(bw bwVar, byte[] bArr) throws cc {
        try {
            this.b.a(bArr);
            bwVar.a(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(bw bwVar, String str, String str2) throws cc {
        try {
            try {
                a(bwVar, str.getBytes(str2));
            } catch (UnsupportedEncodingException e) {
                throw new cc("JVM DOES NOT SUPPORT ENCODING: " + str2);
            }
        } finally {
            this.a.B();
        }
    }

    public void a(bw bwVar, byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        try {
            try {
                if (j(bArr, cdVar, cdVarArr) != null) {
                    bwVar.a(this.a);
                }
            } catch (Exception e) {
                throw new cc(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public Boolean a(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        return (Boolean) a((byte) 2, bArr, cdVar, cdVarArr);
    }

    public Byte b(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        return (Byte) a((byte) 3, bArr, cdVar, cdVarArr);
    }

    public Double c(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        return (Double) a((byte) 4, bArr, cdVar, cdVarArr);
    }

    public Short d(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        return (Short) a((byte) 6, bArr, cdVar, cdVarArr);
    }

    public Integer e(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        return (Integer) a((byte) 8, bArr, cdVar, cdVarArr);
    }

    public Long f(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        return (Long) a((byte) 10, bArr, cdVar, cdVarArr);
    }

    public String g(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        return (String) a((byte) 11, bArr, cdVar, cdVarArr);
    }

    public ByteBuffer h(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        return (ByteBuffer) a((byte) 100, bArr, cdVar, cdVarArr);
    }

    public Short i(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        try {
            try {
                if (j(bArr, cdVar, cdVarArr) != null) {
                    this.a.j();
                    return Short.valueOf(this.a.l().c);
                }
                return null;
            } catch (Exception e) {
                throw new cc(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private Object a(byte b, byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        try {
            try {
                cq j = j(bArr, cdVar, cdVarArr);
                if (j != null) {
                    switch (b) {
                        case 2:
                            if (j.b == 2) {
                                return Boolean.valueOf(this.a.t());
                            }
                            break;
                        case 3:
                            if (j.b == 3) {
                                return Byte.valueOf(this.a.u());
                            }
                            break;
                        case 4:
                            if (j.b == 4) {
                                return Double.valueOf(this.a.y());
                            }
                            break;
                        case 6:
                            if (j.b == 6) {
                                return Short.valueOf(this.a.v());
                            }
                            break;
                        case 8:
                            if (j.b == 8) {
                                return Integer.valueOf(this.a.w());
                            }
                            break;
                        case 10:
                            if (j.b == 10) {
                                return Long.valueOf(this.a.x());
                            }
                            break;
                        case 11:
                            if (j.b == 11) {
                                return this.a.z();
                            }
                            break;
                        case 100:
                            if (j.b == 11) {
                                return this.a.A();
                            }
                            break;
                    }
                }
                return null;
            } catch (Exception e) {
                throw new cc(e);
            }
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    private cq j(byte[] bArr, cd cdVar, cd... cdVarArr) throws cc {
        int i = 0;
        this.b.a(bArr);
        cd[] cdVarArr2 = new cd[cdVarArr.length + 1];
        cdVarArr2[0] = cdVar;
        for (int i2 = 0; i2 < cdVarArr.length; i2++) {
            cdVarArr2[i2 + 1] = cdVarArr[i2];
        }
        this.a.j();
        cq cqVar = null;
        while (i < cdVarArr2.length) {
            cqVar = this.a.l();
            if (cqVar.b == 0 || cqVar.c > cdVarArr2[i].a()) {
                return null;
            }
            if (cqVar.c != cdVarArr2[i].a()) {
                cy.a(this.a, cqVar.b);
                this.a.m();
            } else {
                i++;
                if (i < cdVarArr2.length) {
                    this.a.j();
                }
            }
        }
        return cqVar;
    }

    public void a(bw bwVar, String str) throws cc {
        a(bwVar, str.getBytes());
    }
}

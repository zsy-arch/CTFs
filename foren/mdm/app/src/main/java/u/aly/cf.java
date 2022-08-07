package u.aly;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import u.aly.cp;

/* compiled from: TSerializer.java */
/* loaded from: classes2.dex */
public class cf {
    private final ByteArrayOutputStream a;
    private final dh b;
    private cv c;

    public cf() {
        this(new cp.a());
    }

    public cf(cx cxVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new dh(this.a);
        this.c = cxVar.a(this.b);
    }

    public byte[] a(bw bwVar) throws cc {
        this.a.reset();
        bwVar.b(this.c);
        return this.a.toByteArray();
    }

    public String a(bw bwVar, String str) throws cc {
        try {
            return new String(a(bwVar), str);
        } catch (UnsupportedEncodingException e) {
            throw new cc("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public String b(bw bwVar) throws cc {
        return new String(a(bwVar));
    }
}

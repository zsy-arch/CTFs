package u.aly;

import android.content.Context;
import u.aly.aw;
import u.aly.x;

/* compiled from: Defcon.java */
/* loaded from: classes2.dex */
public class ay implements ap {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final long e = 14400000;
    private static final long f = 28800000;
    private static final long g = 86400000;
    private static ay j = null;
    private int h = 0;
    private final long i = 60000;

    public static synchronized ay a(Context context) {
        ay ayVar;
        synchronized (ay.class) {
            if (j == null) {
                j = new ay();
                j.a(x.a(context).b().a(0));
            }
            ayVar = j;
        }
        return ayVar;
    }

    private ay() {
    }

    public void a(aw awVar, Context context) {
        if (this.h == 1) {
            awVar.b.i = null;
            awVar.b.a = null;
            awVar.b.b = null;
            awVar.b.h = null;
        } else if (this.h == 2) {
            awVar.b.c.clear();
            awVar.b.c.add(b(context));
            awVar.b.i = null;
            awVar.b.a = null;
            awVar.b.b = null;
            awVar.b.h = null;
        } else if (this.h == 3) {
            awVar.b.c = null;
            awVar.b.i = null;
            awVar.b.a = null;
            awVar.b.b = null;
            awVar.b.h = null;
        }
    }

    public aw.o b(Context context) {
        aw.o oVar = new aw.o();
        oVar.b = as.g(context);
        long currentTimeMillis = System.currentTimeMillis();
        oVar.c = currentTimeMillis;
        oVar.d = currentTimeMillis + 60000;
        oVar.e = 60000L;
        return oVar;
    }

    public long a() {
        switch (this.h) {
            case 1:
                return e;
            case 2:
                return f;
            case 3:
                return 86400000L;
            default:
                return 0L;
        }
    }

    public long b() {
        return this.h == 0 ? 0L : 300000L;
    }

    public void a(int i) {
        if (i >= 0 && i <= 3) {
            this.h = i;
        }
    }

    public boolean c() {
        return this.h != 0;
    }

    @Override // u.aly.ap
    public void a(x.a aVar) {
        a(aVar.a(0));
    }
}

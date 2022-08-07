package u.aly;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import u.aly.x;

/* compiled from: ImLatent.java */
/* loaded from: classes2.dex */
public class az implements ap {
    private static az l = null;
    private bs e;
    private at f;
    private Context k;
    private final long a = 1296000000;
    private final long b = 129600000;
    private final int c = 1800000;
    private final int d = 10000;
    private long g = 1296000000;
    private int h = 10000;
    private long i = 0;
    private long j = 0;

    public static synchronized az a(Context context, at atVar) {
        az azVar;
        synchronized (az.class) {
            if (l == null) {
                l = new az(context, atVar);
                l.a(x.a(context).b());
            }
            azVar = l;
        }
        return azVar;
    }

    private az(Context context, at atVar) {
        this.k = context;
        this.e = bs.a(context);
        this.f = atVar;
    }

    public boolean a() {
        if (this.e.i() || this.f.f()) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.f.m();
        if (currentTimeMillis > this.g) {
            this.i = bj.a(this.h, t.a(this.k));
            this.j = currentTimeMillis;
            return true;
        } else if (currentTimeMillis <= 129600000) {
            return false;
        } else {
            this.i = 0L;
            this.j = currentTimeMillis;
            return true;
        }
    }

    public long b() {
        return this.i;
    }

    public long c() {
        return this.j;
    }

    @Override // u.aly.ap
    public void a(x.a aVar) {
        this.g = aVar.a(1296000000L);
        int b = aVar.b(0);
        if (b != 0) {
            this.h = b;
        } else if (AnalyticsConfig.sLatentWindow <= 0 || AnalyticsConfig.sLatentWindow > 1800000) {
            this.h = 10000;
        } else {
            this.h = AnalyticsConfig.sLatentWindow;
        }
    }
}

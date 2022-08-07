package c.a.e;

import android.view.View;
import android.view.animation.Interpolator;
import c.e.h.r;
import c.e.h.s;
import c.e.h.t;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class h {

    /* renamed from: c */
    public Interpolator f512c;

    /* renamed from: d */
    public s f513d;

    /* renamed from: e */
    public boolean f514e;

    /* renamed from: b */
    public long f511b = -1;
    public final t f = new g(this);

    /* renamed from: a */
    public final ArrayList<r> f510a = new ArrayList<>();

    public void a() {
        if (this.f514e) {
            Iterator<r> it = this.f510a.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
            this.f514e = false;
        }
    }

    public void b() {
        View view;
        if (!this.f514e) {
            Iterator<r> it = this.f510a.iterator();
            while (it.hasNext()) {
                r next = it.next();
                long j = this.f511b;
                if (j >= 0) {
                    next.a(j);
                }
                Interpolator interpolator = this.f512c;
                if (!(interpolator == null || (view = next.f872a.get()) == null)) {
                    view.animate().setInterpolator(interpolator);
                }
                if (this.f513d != null) {
                    next.a(this.f);
                }
                View view2 = next.f872a.get();
                if (view2 != null) {
                    view2.animate().start();
                }
            }
            this.f514e = true;
        }
    }

    public h a(long j) {
        if (!this.f514e) {
            this.f511b = j;
        }
        return this;
    }

    public h a(Interpolator interpolator) {
        if (!this.f514e) {
            this.f512c = interpolator;
        }
        return this;
    }

    public h a(s sVar) {
        if (!this.f514e) {
            this.f513d = sVar;
        }
        return this;
    }
}

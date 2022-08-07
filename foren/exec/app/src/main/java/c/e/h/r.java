package c.e.h;

import android.os.Build;
import android.view.View;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public final class r {

    /* renamed from: a */
    public WeakReference<View> f872a;

    /* renamed from: b */
    public Runnable f873b = null;

    /* renamed from: c */
    public Runnable f874c = null;

    /* renamed from: d */
    public int f875d = -1;

    public r(View view) {
        this.f872a = new WeakReference<>(view);
    }

    public r a(long j) {
        View view = this.f872a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public r b(float f) {
        View view = this.f872a.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }

    public r a(float f) {
        View view = this.f872a.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public void a() {
        View view = this.f872a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public r a(s sVar) {
        View view = this.f872a.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            a(view, sVar);
        }
        return this;
    }

    public final void a(View view, s sVar) {
        if (sVar != null) {
            view.animate().setListener(new p(this, sVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public r a(u uVar) {
        View view = this.f872a.get();
        if (view != null) {
            int i = Build.VERSION.SDK_INT;
            q qVar = null;
            if (uVar != null) {
                qVar = new q(this, uVar, view);
            }
            view.animate().setUpdateListener(qVar);
        }
        return this;
    }
}

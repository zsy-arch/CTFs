package c.a.f;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import c.a.e.a.y;

/* loaded from: classes.dex */
public abstract class H implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final float f535a;

    /* renamed from: d  reason: collision with root package name */
    public final View f538d;

    /* renamed from: e  reason: collision with root package name */
    public Runnable f539e;
    public Runnable f;
    public boolean g;
    public int h;
    public final int[] i = new int[2];

    /* renamed from: b  reason: collision with root package name */
    public final int f536b = ViewConfiguration.getTapTimeout();

    /* renamed from: c  reason: collision with root package name */
    public final int f537c = (ViewConfiguration.getLongPressTimeout() + this.f536b) / 2;

    /* loaded from: classes.dex */
    private class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = H.this.f538d.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* loaded from: classes.dex */
    private class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            H h = H.this;
            h.a();
            View view = h.f538d;
            if (view.isEnabled() && !view.isLongClickable() && h.c()) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                view.onTouchEvent(obtain);
                obtain.recycle();
                h.g = true;
            }
        }
    }

    public H(View view) {
        this.f538d = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f535a = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
    }

    public final void a() {
        Runnable runnable = this.f;
        if (runnable != null) {
            this.f538d.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f539e;
        if (runnable2 != null) {
            this.f538d.removeCallbacks(runnable2);
        }
    }

    public abstract y b();

    public abstract boolean c();

    public boolean d() {
        y b2 = b();
        if (b2 == null || !b2.b()) {
            return true;
        }
        b2.dismiss();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0087, code lost:
        if (r4 != 3) goto L_0x0079;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0113  */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r12, android.view.MotionEvent r13) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.f.H.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.g = false;
        this.h = -1;
        Runnable runnable = this.f539e;
        if (runnable != null) {
            this.f538d.removeCallbacks(runnable);
        }
    }
}

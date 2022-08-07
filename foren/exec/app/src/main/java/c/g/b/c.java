package c.g.b;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import c.e.h.n;
import com.tencent.smtt.utils.TbsLog;
import java.util.Arrays;

/* loaded from: classes.dex */
public class c {

    /* renamed from: a */
    public static final Interpolator f903a = new a();

    /* renamed from: b */
    public int f904b;

    /* renamed from: c */
    public int f905c;

    /* renamed from: e */
    public float[] f907e;
    public float[] f;
    public float[] g;
    public float[] h;
    public int[] i;
    public int[] j;
    public int[] k;
    public int l;
    public VelocityTracker m;
    public float n;
    public float o;
    public int p;
    public int q;
    public OverScroller r;
    public final a s;
    public View t;
    public boolean u;
    public final ViewGroup v;

    /* renamed from: d */
    public int f906d = -1;
    public final Runnable w = new b(this);

    /* loaded from: classes.dex */
    public static abstract class a {
        public int a(int i) {
            return i;
        }

        public abstract int a(View view);

        public abstract int a(View view, int i, int i2);

        public abstract void a(int i, int i2);

        public abstract void a(View view, float f, float f2);

        public abstract void a(View view, int i);

        public abstract void a(View view, int i, int i2, int i3, int i4);

        public int b(View view) {
            return 0;
        }

        public abstract int b(View view, int i, int i2);

        public void b(int i, int i2) {
        }

        public boolean b(int i) {
            return false;
        }

        public abstract boolean b(View view, int i);

        public abstract void c(int i);
    }

    public c(Context context, ViewGroup viewGroup, a aVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (aVar != null) {
            this.v = viewGroup;
            this.s = aVar;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.p = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.f905c = viewConfiguration.getScaledTouchSlop();
            this.n = viewConfiguration.getScaledMaximumFlingVelocity();
            this.o = viewConfiguration.getScaledMinimumFlingVelocity();
            this.r = new OverScroller(context, f903a);
        } else {
            throw new IllegalArgumentException("Callback may not be null");
        }
    }

    public static c a(ViewGroup viewGroup, float f, a aVar) {
        c cVar = new c(viewGroup.getContext(), viewGroup, aVar);
        cVar.f905c = (int) ((1.0f / f) * cVar.f905c);
        return cVar;
    }

    public void b() {
        this.f906d = -1;
        float[] fArr = this.f907e;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
            Arrays.fill(this.f, 0.0f);
            Arrays.fill(this.g, 0.0f);
            Arrays.fill(this.h, 0.0f);
            Arrays.fill(this.i, 0);
            Arrays.fill(this.j, 0);
            Arrays.fill(this.k, 0);
            this.l = 0;
        }
        VelocityTracker velocityTracker = this.m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.m = null;
        }
    }

    public boolean c(MotionEvent motionEvent) {
        View a2;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            b();
        }
        if (this.m == null) {
            this.m = VelocityTracker.obtain();
        }
        this.m.addMovement(motionEvent);
        if (actionMasked != 0) {
            if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 3) {
                        if (actionMasked == 5) {
                            int pointerId = motionEvent.getPointerId(actionIndex);
                            float x = motionEvent.getX(actionIndex);
                            float y = motionEvent.getY(actionIndex);
                            b(x, y, pointerId);
                            int i = this.f904b;
                            if (i == 0) {
                                int i2 = this.i[pointerId];
                                int i3 = this.q;
                                if ((i2 & i3) != 0) {
                                    this.s.b(i2 & i3, pointerId);
                                }
                            } else if (i == 2 && (a2 = a((int) x, (int) y)) == this.t) {
                                b(a2, pointerId);
                            }
                        } else if (actionMasked == 6) {
                            a(motionEvent.getPointerId(actionIndex));
                        }
                    }
                } else if (!(this.f907e == null || this.f == null)) {
                    int pointerCount = motionEvent.getPointerCount();
                    for (int i4 = 0; i4 < pointerCount; i4++) {
                        int pointerId2 = motionEvent.getPointerId(i4);
                        if (c(pointerId2)) {
                            float x2 = motionEvent.getX(i4);
                            float y2 = motionEvent.getY(i4);
                            float f = x2 - this.f907e[pointerId2];
                            float f2 = y2 - this.f[pointerId2];
                            View a3 = a((int) x2, (int) y2);
                            boolean z = a3 != null && a(a3, f, f2);
                            if (z) {
                                int left = a3.getLeft();
                                int i5 = (int) f;
                                int a4 = this.s.a(a3, left + i5, i5);
                                int i6 = (int) f2;
                                this.s.b(a3, a3.getTop() + i6, i6);
                                int a5 = this.s.a(a3);
                                this.s.b(a3);
                                if (a5 != 0) {
                                    if (a5 > 0 && a4 == left) {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            a(f, f2, pointerId2);
                            if (this.f904b != 1) {
                                if (z && b(a3, pointerId2)) {
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    b(motionEvent);
                }
            }
            b();
        } else {
            float x3 = motionEvent.getX();
            float y3 = motionEvent.getY();
            int pointerId3 = motionEvent.getPointerId(0);
            b(x3, y3, pointerId3);
            View a6 = a((int) x3, (int) y3);
            if (a6 == this.t && this.f904b == 2) {
                b(a6, pointerId3);
            }
            int i7 = this.i[pointerId3];
            int i8 = this.q;
            if ((i7 & i8) != 0) {
                this.s.b(i7 & i8, pointerId3);
            }
        }
        return this.f904b == 1;
    }

    public void d(int i) {
        this.v.removeCallbacks(this.w);
        if (this.f904b != i) {
            this.f904b = i;
            this.s.c(i);
            if (this.f904b == 0) {
                this.t = null;
            }
        }
    }

    public void a(View view, int i) {
        if (view.getParent() == this.v) {
            this.t = view;
            this.f906d = i;
            this.s.a(view, i);
            d(1);
            return;
        }
        StringBuilder a2 = e.a.a.a.a.a("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (");
        a2.append(this.v);
        a2.append(")");
        throw new IllegalArgumentException(a2.toString());
    }

    public void a() {
        b();
        if (this.f904b == 2) {
            int currX = this.r.getCurrX();
            int currY = this.r.getCurrY();
            this.r.abortAnimation();
            int currX2 = this.r.getCurrX();
            int currY2 = this.r.getCurrY();
            this.s.a(this.t, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        d(0);
    }

    public boolean b(View view, int i, int i2) {
        this.t = view;
        this.f906d = -1;
        boolean a2 = a(i, i2, 0, 0);
        if (!a2 && this.f904b == 0 && this.t != null) {
            this.t = null;
        }
        return a2;
    }

    public final boolean a(int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int left = this.t.getLeft();
        int top = this.t.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.r.abortAnimation();
            d(0);
            return false;
        }
        View view = this.t;
        int a2 = a(i3, (int) this.o, (int) this.n);
        int a3 = a(i4, (int) this.o, (int) this.n);
        int abs = Math.abs(i5);
        int abs2 = Math.abs(i6);
        int abs3 = Math.abs(a2);
        int abs4 = Math.abs(a3);
        int i7 = abs3 + abs4;
        int i8 = abs + abs2;
        if (a2 != 0) {
            f2 = abs3;
            f = i7;
        } else {
            f2 = abs;
            f = i8;
        }
        float f5 = f2 / f;
        if (a3 != 0) {
            f4 = abs4;
            f3 = i7;
        } else {
            f4 = abs2;
            f3 = i8;
        }
        int b2 = b(i5, a2, this.s.a(view));
        this.s.b(view);
        this.r.startScroll(left, top, i5, i6, (int) ((b(i6, a3, 0) * (f4 / f3)) + (b2 * f5)));
        d(2);
        return true;
    }

    public boolean b(int i, int i2) {
        if (this.u) {
            return a(i, i2, (int) this.m.getXVelocity(this.f906d), (int) this.m.getYVelocity(this.f906d));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    public final int b(int i, int i2, int i3) {
        int i4;
        if (i == 0) {
            return 0;
        }
        int width = this.v.getWidth();
        float f = width / 2;
        float sin = (((float) Math.sin((Math.min(1.0f, Math.abs(i) / width) - 0.5f) * 0.47123894f)) * f) + f;
        int abs = Math.abs(i2);
        if (abs > 0) {
            i4 = Math.round(Math.abs(sin / abs) * 1000.0f) * 4;
        } else {
            i4 = (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f);
        }
        return Math.min(i4, 600);
    }

    public final int a(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        return abs > i3 ? i > 0 ? i3 : -i3 : i;
    }

    public final void b(float f, float f2, int i) {
        float[] fArr = this.f907e;
        int i2 = 0;
        if (fArr == null || fArr.length <= i) {
            int i3 = i + 1;
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            float[] fArr5 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            float[] fArr6 = this.f907e;
            if (fArr6 != null) {
                System.arraycopy(fArr6, 0, fArr2, 0, fArr6.length);
                float[] fArr7 = this.f;
                System.arraycopy(fArr7, 0, fArr3, 0, fArr7.length);
                float[] fArr8 = this.g;
                System.arraycopy(fArr8, 0, fArr4, 0, fArr8.length);
                float[] fArr9 = this.h;
                System.arraycopy(fArr9, 0, fArr5, 0, fArr9.length);
                int[] iArr4 = this.i;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.j;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.k;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f907e = fArr2;
            this.f = fArr3;
            this.g = fArr4;
            this.h = fArr5;
            this.i = iArr;
            this.j = iArr2;
            this.k = iArr3;
        }
        float[] fArr10 = this.f907e;
        this.g[i] = f;
        fArr10[i] = f;
        float[] fArr11 = this.f;
        this.h[i] = f2;
        fArr11[i] = f2;
        int[] iArr7 = this.i;
        int i4 = (int) f;
        int i5 = (int) f2;
        if (i4 < this.v.getLeft() + this.p) {
            i2 = 1;
        }
        if (i5 < this.v.getTop() + this.p) {
            i2 |= 4;
        }
        if (i4 > this.v.getRight() - this.p) {
            i2 |= 2;
        }
        if (i5 > this.v.getBottom() - this.p) {
            i2 |= 8;
        }
        iArr7[i] = i2;
        this.l |= 1 << i;
    }

    public final float a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        return abs > f3 ? f > 0.0f ? f3 : -f3 : f;
    }

    public boolean a(boolean z) {
        if (this.f904b == 2) {
            boolean computeScrollOffset = this.r.computeScrollOffset();
            int currX = this.r.getCurrX();
            int currY = this.r.getCurrY();
            int left = currX - this.t.getLeft();
            int top = currY - this.t.getTop();
            if (left != 0) {
                n.c(this.t, left);
            }
            if (top != 0) {
                n.d(this.t, top);
            }
            if (!(left == 0 && top == 0)) {
                this.s.a(this.t, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.r.getFinalX() && currY == this.r.getFinalY()) {
                this.r.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.v.post(this.w);
                } else {
                    d(0);
                }
            }
        }
        return this.f904b == 2;
    }

    public final void a(float f, float f2) {
        this.u = true;
        this.s.a(this.t, f, f2);
        this.u = false;
        if (this.f904b == 1) {
            d(0);
        }
    }

    public final void c() {
        this.m.computeCurrentVelocity(TbsLog.TBSLOG_CODE_SDK_BASE, this.n);
        a(a(this.m.getXVelocity(this.f906d), this.o, this.n), a(this.m.getYVelocity(this.f906d), this.o, this.n));
    }

    public final void a(int i) {
        if (this.f907e != null && b(i)) {
            this.f907e[i] = 0.0f;
            this.f[i] = 0.0f;
            this.g[i] = 0.0f;
            this.h[i] = 0.0f;
            this.i[i] = 0;
            this.j[i] = 0;
            this.k[i] = 0;
            this.l = (~(1 << i)) & this.l;
        }
    }

    public final boolean c(int i) {
        if (b(i)) {
            return true;
        }
        String str = "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.";
        return false;
    }

    public void a(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            b();
        }
        if (this.m == null) {
            this.m = VelocityTracker.obtain();
        }
        this.m.addMovement(motionEvent);
        int i2 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View a2 = a((int) x, (int) y);
            b(x, y, pointerId);
            b(a2, pointerId);
            int i3 = this.i[pointerId];
            int i4 = this.q;
            if ((i3 & i4) != 0) {
                this.s.b(i3 & i4, pointerId);
            }
        } else if (actionMasked == 1) {
            if (this.f904b == 1) {
                c();
            }
            b();
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                if (this.f904b == 1) {
                    a(0.0f, 0.0f);
                }
                b();
            } else if (actionMasked == 5) {
                int pointerId2 = motionEvent.getPointerId(actionIndex);
                float x2 = motionEvent.getX(actionIndex);
                float y2 = motionEvent.getY(actionIndex);
                b(x2, y2, pointerId2);
                if (this.f904b == 0) {
                    b(a((int) x2, (int) y2), pointerId2);
                    int i5 = this.i[pointerId2];
                    int i6 = this.q;
                    if ((i5 & i6) != 0) {
                        this.s.b(i5 & i6, pointerId2);
                        return;
                    }
                    return;
                }
                if (a(this.t, (int) x2, (int) y2)) {
                    b(this.t, pointerId2);
                }
            } else if (actionMasked == 6) {
                int pointerId3 = motionEvent.getPointerId(actionIndex);
                if (this.f904b == 1 && pointerId3 == this.f906d) {
                    int pointerCount = motionEvent.getPointerCount();
                    while (true) {
                        if (i2 >= pointerCount) {
                            i = -1;
                            break;
                        }
                        int pointerId4 = motionEvent.getPointerId(i2);
                        if (pointerId4 != this.f906d) {
                            View a3 = a((int) motionEvent.getX(i2), (int) motionEvent.getY(i2));
                            View view = this.t;
                            if (a3 == view && b(view, pointerId4)) {
                                i = this.f906d;
                                break;
                            }
                        }
                        i2++;
                    }
                    if (i == -1) {
                        c();
                    }
                }
                a(pointerId3);
            }
        } else if (this.f904b != 1) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (i2 < pointerCount2) {
                int pointerId5 = motionEvent.getPointerId(i2);
                if (c(pointerId5)) {
                    float x3 = motionEvent.getX(i2);
                    float y3 = motionEvent.getY(i2);
                    float f = x3 - this.f907e[pointerId5];
                    float f2 = y3 - this.f[pointerId5];
                    a(f, f2, pointerId5);
                    if (this.f904b != 1) {
                        View a4 = a((int) x3, (int) y3);
                        if (a(a4, f, f2) && b(a4, pointerId5)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i2++;
            }
            b(motionEvent);
        } else if (c(this.f906d)) {
            int findPointerIndex = motionEvent.findPointerIndex(this.f906d);
            float x4 = motionEvent.getX(findPointerIndex);
            float y4 = motionEvent.getY(findPointerIndex);
            float[] fArr = this.g;
            int i7 = this.f906d;
            int i8 = (int) (x4 - fArr[i7]);
            int i9 = (int) (y4 - this.h[i7]);
            int left = this.t.getLeft() + i8;
            int top = this.t.getTop() + i9;
            int left2 = this.t.getLeft();
            int top2 = this.t.getTop();
            if (i8 != 0) {
                left = this.s.a(this.t, left, i8);
                n.c(this.t, left - left2);
            }
            if (i9 != 0) {
                top = this.s.b(this.t, top, i9);
                n.d(this.t, top - top2);
            }
            if (!(i8 == 0 && i9 == 0)) {
                this.s.a(this.t, left, top, left - left2, top - top2);
            }
            b(motionEvent);
        }
    }

    public final void b(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (c(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.g[pointerId] = x;
                this.h[pointerId] = y;
            }
        }
    }

    public boolean b(int i) {
        return ((1 << i) & this.l) != 0;
    }

    public boolean b(View view, int i) {
        if (view == this.t && this.f906d == i) {
            return true;
        }
        if (view == null || !this.s.b(view, i)) {
            return false;
        }
        this.f906d = i;
        a(view, i);
        return true;
    }

    public final void a(float f, float f2, int i) {
        int i2 = 1;
        if (!a(f, f2, i, 1)) {
            i2 = 0;
        }
        if (a(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (a(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (a(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.j;
            iArr[i] = iArr[i] | i2;
            this.s.a(i2, i);
        }
    }

    public final boolean a(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.i[i] & i2) != i2 || (this.q & i2) == 0 || (this.k[i] & i2) == i2 || (this.j[i] & i2) == i2) {
            return false;
        }
        int i3 = this.f905c;
        if (abs <= i3 && abs2 <= i3) {
            return false;
        }
        if (abs >= abs2 * 0.5f || !this.s.b(i2)) {
            return (this.j[i] & i2) == 0 && abs > ((float) this.f905c);
        }
        int[] iArr = this.k;
        iArr[i] = iArr[i] | i2;
        return false;
    }

    public final boolean a(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.s.a(view) > 0;
        this.s.b(view);
        return z && Math.abs(f) > ((float) this.f905c);
    }

    public boolean a(View view, int i, int i2) {
        return view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom();
    }

    public View a(int i, int i2) {
        for (int childCount = this.v.getChildCount() - 1; childCount >= 0; childCount--) {
            ViewGroup viewGroup = this.v;
            this.s.a(childCount);
            View childAt = viewGroup.getChildAt(childCount);
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }
}

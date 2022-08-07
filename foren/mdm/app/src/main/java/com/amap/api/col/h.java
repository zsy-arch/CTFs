package com.amap.api.col;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.amap.api.maps.model.AMapGestureListener;
import com.autonavi.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.ae.gmap.gesture.HoverGestureDetector;
import com.autonavi.ae.gmap.gesture.MoveGestureDetector;
import com.autonavi.ae.gmap.gesture.ScaleRotateGestureDetector;
import com.autonavi.ae.gmap.gesture.ZoomOutGestureDetector;
import com.autonavi.amap.mapcore.message.HoverGestureMapMessage;
import com.autonavi.amap.mapcore.message.MoveGestureMapMessage;
import com.autonavi.amap.mapcore.message.RotateGestureMapMessage;
import com.autonavi.amap.mapcore.message.ScaleGestureMapMessage;

/* compiled from: GLMapGestureDetector.java */
/* loaded from: classes.dex */
public class h {
    k a;
    Context b;
    GestureDetector c;
    public AMapGestureListener d;
    private ScaleRotateGestureDetector e;
    private MoveGestureDetector f;
    private HoverGestureDetector g;
    private ZoomOutGestureDetector h;
    private boolean i = false;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private boolean o = false;
    private boolean p = false;
    private boolean q = true;
    private Handler r = new Handler(Looper.getMainLooper());

    static /* synthetic */ int g(h hVar) {
        int i = hVar.k;
        hVar.k = i + 1;
        return i;
    }

    static /* synthetic */ int h(h hVar) {
        int i = hVar.l;
        hVar.l = i + 1;
        return i;
    }

    static /* synthetic */ int l(h hVar) {
        int i = hVar.j;
        hVar.j = i + 1;
        return i;
    }

    static /* synthetic */ int m(h hVar) {
        int i = hVar.m;
        hVar.m = i + 1;
        return i;
    }

    public void a(AMapGestureListener aMapGestureListener) {
        this.d = aMapGestureListener;
    }

    public void a() {
        this.j = 0;
        this.l = 0;
        this.k = 0;
        this.m = 0;
        this.n = 0;
    }

    public h(k kVar) {
        this.b = kVar.t();
        this.a = kVar;
        a aVar = new a();
        this.c = new GestureDetector(this.b, aVar, this.r);
        this.c.setOnDoubleTapListener(aVar);
        this.e = new ScaleRotateGestureDetector(this.b, new d());
        this.f = new MoveGestureDetector(this.b, new c());
        this.g = new HoverGestureDetector(this.b, new b());
        this.h = new ZoomOutGestureDetector(this.b, new e());
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.n < motionEvent.getPointerCount()) {
            this.n = motionEvent.getPointerCount();
        }
        if ((motionEvent.getAction() & 255) == 0) {
            this.p = false;
            this.q = false;
        }
        if (motionEvent.getAction() == 6 && motionEvent.getPointerCount() > 0) {
            this.p = true;
        }
        if (this.o && this.n >= 2) {
            this.o = false;
        }
        try {
            if (this.d != null) {
                if (motionEvent.getAction() == 0) {
                    this.d.onDown(motionEvent.getX(), motionEvent.getY());
                } else if (motionEvent.getAction() == 1) {
                    this.d.onUp(motionEvent.getX(), motionEvent.getY());
                }
            }
            this.c.onTouchEvent(motionEvent);
            boolean onTouchEvent = this.g.onTouchEvent(motionEvent);
            if (this.i && this.m > 0) {
                return onTouchEvent;
            }
            this.h.onTouchEvent(motionEvent);
            if (this.o) {
                return onTouchEvent;
            }
            this.e.onTouchEvent(motionEvent);
            return this.f.onTouchEvent(motionEvent);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public void b() {
        if (this.r != null) {
            this.r.removeCallbacks(null);
            this.r = null;
        }
    }

    /* compiled from: GLMapGestureDetector.java */
    /* loaded from: classes.dex */
    private class a implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {
        float a;
        long b;
        private int d;
        private EAMapPlatformGestureInfo e;

        private a() {
            this.d = 0;
            this.a = 0.0f;
            this.e = new EAMapPlatformGestureInfo();
            this.b = 0L;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            h.this.o = false;
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (h.this.d != null) {
                h.this.d.onFling(f, f2);
            }
            try {
                if (h.this.a.h().isScrollGesturesEnabled() && h.this.m <= 0 && h.this.k <= 0 && h.this.l == 0 && !h.this.q) {
                    this.e.mGestureState = 3;
                    this.e.mGestureType = 3;
                    this.e.mLocation = new float[]{motionEvent2.getX(), motionEvent2.getY()};
                    int a = h.this.a.a(this.e);
                    h.this.a.onFling();
                    h.this.a.a().startMapSlidAnim(a, new Point((int) motionEvent2.getX(), (int) motionEvent2.getY()), f, f2);
                }
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onFling");
                th.printStackTrace();
            }
            return true;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            if (h.this.n == 1) {
                this.e.mGestureState = 3;
                this.e.mGestureType = 7;
                this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                h.this.a.a(h.this.a.a(this.e), motionEvent);
                if (h.this.d != null) {
                    h.this.d.onLongPress(motionEvent.getX(), motionEvent.getY());
                }
            }
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (h.this.d == null) {
                return false;
            }
            h.this.d.onScroll(f, f2);
            return false;
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public void onShowPress(MotionEvent motionEvent) {
            try {
                this.e.mGestureState = 3;
                this.e.mGestureType = 7;
                this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                h.this.a.a().clearAnimations(h.this.a.a(this.e), false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // android.view.GestureDetector.OnGestureListener
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            h.this.c.setIsLongpressEnabled(false);
            this.d = motionEvent.getPointerCount();
            if (h.this.d != null) {
                h.this.d.onDoubleTap(motionEvent.getX(), motionEvent.getY());
            }
            return false;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            boolean z = true;
            if (this.d < motionEvent.getPointerCount()) {
                this.d = motionEvent.getPointerCount();
            }
            int action = motionEvent.getAction() & 255;
            if (this.d == 1) {
                try {
                    if (!h.this.a.h().isZoomGesturesEnabled()) {
                        return false;
                    }
                } catch (Throwable th) {
                    gr.b(th, "GLMapGestrureDetector", "onDoubleTapEvent");
                    th.printStackTrace();
                }
                if (action == 0) {
                    this.e.mGestureState = 1;
                    this.e.mGestureType = 9;
                    this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                    int a = h.this.a.a(this.e);
                    this.a = motionEvent.getY();
                    h.this.a.a(a, ScaleGestureMapMessage.obtain(100, 1.0f, 0, 0));
                    this.b = SystemClock.uptimeMillis();
                } else if (action == 2) {
                    h.this.o = true;
                    float y = this.a - motionEvent.getY();
                    if (Math.abs(y) >= 20.0f) {
                        this.e.mGestureState = 2;
                        this.e.mGestureType = 9;
                        this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                        int a2 = h.this.a.a(this.e);
                        float mapHeight = (4.0f * y) / h.this.a.getMapHeight();
                        if (y > 0.0f) {
                            h.this.a.a(a2, ScaleGestureMapMessage.obtain(101, mapHeight, 0, 0));
                        } else {
                            h.this.a.a(a2, ScaleGestureMapMessage.obtain(101, mapHeight, 0, 0));
                        }
                        this.a = motionEvent.getY();
                    }
                } else {
                    this.e.mGestureState = 3;
                    this.e.mGestureType = 9;
                    this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
                    int a3 = h.this.a.a(this.e);
                    h.this.c.setIsLongpressEnabled(true);
                    h.this.a.a(a3, ScaleGestureMapMessage.obtain(102, 1.0f, 0, 0));
                    if (action == 1) {
                        h.this.a.a(a3, 3);
                        long uptimeMillis = SystemClock.uptimeMillis() - this.b;
                        if (!h.this.o || uptimeMillis < 200) {
                            return h.this.a.b(a3, motionEvent);
                        }
                        h.this.o = false;
                    } else {
                        h.this.o = false;
                    }
                }
            } else {
                z = false;
            }
            return z;
        }

        @Override // android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (h.this.n != 1) {
                return false;
            }
            this.e.mGestureState = 3;
            this.e.mGestureType = 8;
            this.e.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
            int a = h.this.a.a(this.e);
            if (h.this.d != null) {
                try {
                    h.this.d.onSingleTap(motionEvent.getX(), motionEvent.getY());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            return h.this.a.c(a, motionEvent);
        }
    }

    /* compiled from: GLMapGestureDetector.java */
    /* loaded from: classes.dex */
    private class d extends ScaleRotateGestureDetector.SimpleOnScaleRotateGestureListener {
        private boolean b;
        private boolean c;
        private boolean d;
        private Point e;
        private float[] f;
        private float g;
        private float[] h;
        private float i;
        private EAMapPlatformGestureInfo j;

        private d() {
            this.b = false;
            this.c = false;
            this.d = false;
            this.e = new Point();
            this.f = new float[10];
            this.g = 0.0f;
            this.h = new float[10];
            this.i = 0.0f;
            this.j = new EAMapPlatformGestureInfo();
        }

        @Override // com.autonavi.ae.gmap.gesture.ScaleRotateGestureDetector.SimpleOnScaleRotateGestureListener
        public boolean onScaleRotate(ScaleRotateGestureDetector scaleRotateGestureDetector) {
            this.j.mGestureState = 2;
            this.j.mGestureType = 4;
            this.j.mLocation = new float[]{scaleRotateGestureDetector.getEvent().getX(), scaleRotateGestureDetector.getEvent().getY()};
            int a = h.this.a.a(this.j);
            boolean z = false;
            float scaleFactor = scaleRotateGestureDetector.getScaleFactor();
            float timeDelta = (float) scaleRotateGestureDetector.getTimeDelta();
            int focusX = (int) scaleRotateGestureDetector.getFocusX();
            int focusY = (int) scaleRotateGestureDetector.getFocusY();
            float abs = Math.abs(focusX - this.e.x);
            float abs2 = Math.abs(focusY - this.e.y);
            this.e.x = focusX;
            this.e.y = focusY;
            float log = (float) Math.log(scaleFactor);
            if (h.this.k <= 0 && Math.abs(log) > 0.2d) {
                this.d = true;
            }
            try {
                if (h.this.a.h().isZoomGesturesEnabled()) {
                    if (!this.b && 0.06f < Math.abs(log)) {
                        this.b = true;
                    }
                    if (this.b && 0.01f < Math.abs(log)) {
                        z = true;
                        if (((abs <= 2.0f && abs2 <= 2.0f) || Math.abs(log) >= 0.02f) && timeDelta > 0.0f) {
                            this.g = log / timeDelta;
                            this.f[h.this.k % 10] = Math.abs(this.g);
                            h.g(h.this);
                            h.this.a.a(a, ScaleGestureMapMessage.obtain(101, log, focusX, focusY));
                            if (log > 0.0f) {
                                h.this.a.a(a, 1);
                            } else {
                                h.this.a.a(a, 2);
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onScaleRotate");
                th.printStackTrace();
                z = false;
            }
            try {
                if (!h.this.a.h().isRotateGesturesEnabled() || h.this.a.e(a) || this.d) {
                    return z;
                }
                float rotationDegreesDelta = scaleRotateGestureDetector.getRotationDegreesDelta();
                if (!this.c && Math.abs(rotationDegreesDelta) >= 4.0f) {
                    this.c = true;
                }
                if (!this.c || 1.0f >= Math.abs(rotationDegreesDelta)) {
                    return z;
                }
                if ((abs > 4.0f || abs2 > 4.0f) && Math.abs(rotationDegreesDelta) < 2.0f) {
                    return z;
                }
                this.i = rotationDegreesDelta / timeDelta;
                this.h[h.this.l % 10] = Math.abs(this.i);
                h.h(h.this);
                h.this.a.a(a, RotateGestureMapMessage.obtain(101, rotationDegreesDelta, focusX, focusY));
                z = true;
                h.this.a.a(a, 6);
                return true;
            } catch (Throwable th2) {
                gr.b(th2, "GLMapGestrureDetector", "onScaleRotate");
                th2.printStackTrace();
                return z;
            }
        }

        @Override // com.autonavi.ae.gmap.gesture.ScaleRotateGestureDetector.SimpleOnScaleRotateGestureListener
        public boolean onScaleRotateBegin(ScaleRotateGestureDetector scaleRotateGestureDetector) {
            this.j.mGestureState = 1;
            this.j.mGestureType = 4;
            this.j.mLocation = new float[]{scaleRotateGestureDetector.getEvent().getX(), scaleRotateGestureDetector.getEvent().getY()};
            int a = h.this.a.a(this.j);
            int focusX = (int) scaleRotateGestureDetector.getFocusX();
            int focusY = (int) scaleRotateGestureDetector.getFocusY();
            this.d = false;
            this.e.x = focusX;
            this.e.y = focusY;
            this.b = false;
            this.c = false;
            h.this.a.a(a, ScaleGestureMapMessage.obtain(100, 1.0f, focusX, focusY));
            try {
                if (h.this.a.h().isRotateGesturesEnabled() && !h.this.a.e(a)) {
                    h.this.a.a(a, RotateGestureMapMessage.obtain(100, h.this.a.m(a), focusX, focusY));
                }
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onScaleRotateBegin");
                th.printStackTrace();
            }
            return true;
        }

        @Override // com.autonavi.ae.gmap.gesture.ScaleRotateGestureDetector.SimpleOnScaleRotateGestureListener
        public void onScaleRotateEnd(ScaleRotateGestureDetector scaleRotateGestureDetector) {
            this.j.mGestureState = 3;
            this.j.mGestureType = 4;
            this.j.mLocation = new float[]{scaleRotateGestureDetector.getEvent().getX(), scaleRotateGestureDetector.getEvent().getY()};
            int a = h.this.a.a(this.j);
            this.d = false;
            h.this.a.a(a, ScaleGestureMapMessage.obtain(102, 1.0f, 0, 0));
            if (h.this.k > 0) {
                int i = h.this.k > 10 ? 10 : h.this.k;
                float f = 0.0f;
                for (int i2 = 0; i2 < 10; i2++) {
                    f += this.f[i2];
                    this.f[i2] = 0.0f;
                }
                float f2 = f / i;
                if (0.004f <= f2) {
                    float f3 = f2 * 300.0f;
                    if (f3 >= 1.5f) {
                        f3 = 1.5f;
                    }
                    if (this.g < 0.0f) {
                        f3 = -f3;
                    }
                    float a2 = f3 + h.this.a.a(a);
                }
                this.g = 0.0f;
            }
            if (!h.this.a.e(a)) {
                try {
                    if (h.this.a.h().isRotateGesturesEnabled()) {
                        h.this.a.a(a, RotateGestureMapMessage.obtain(102, h.this.a.m(a), 0, 0));
                    }
                } catch (Throwable th) {
                    gr.b(th, "GLMapGestrureDetector", "onScaleRotateEnd");
                    th.printStackTrace();
                }
                if (h.this.l > 0) {
                    h.this.a.a(a, 6);
                    int i3 = h.this.l > 10 ? 10 : h.this.l;
                    float f4 = 0.0f;
                    for (int i4 = 0; i4 < 10; i4++) {
                        f4 += this.h[i4];
                        this.h[i4] = 0.0f;
                    }
                    float f5 = f4 / i3;
                    if (0.1f <= f5) {
                        float f6 = f5 * 200.0f;
                        int m = ((int) h.this.a.m(a)) % com.umeng.analytics.a.q;
                        if (f6 >= 60.0f) {
                            f6 = 60.0f;
                        }
                        if (this.i < 0.0f) {
                            f6 = -f6;
                        }
                        float f7 = ((int) (f6 + m)) % com.umeng.analytics.a.q;
                    }
                }
                this.g = 0.0f;
            }
        }
    }

    /* compiled from: GLMapGestureDetector.java */
    /* loaded from: classes.dex */
    private class c implements MoveGestureDetector.OnMoveGestureListener {
        private final float b;
        private final float c;
        private EAMapPlatformGestureInfo d;

        private c() {
            this.b = 1.0f;
            this.c = 4.0f;
            this.d = new EAMapPlatformGestureInfo();
        }

        @Override // com.autonavi.ae.gmap.gesture.MoveGestureDetector.OnMoveGestureListener
        public boolean onMove(MoveGestureDetector moveGestureDetector) {
            if (h.this.i) {
                return true;
            }
            try {
                if (h.this.a.h().isScrollGesturesEnabled()) {
                    if (!h.this.p) {
                        this.d.mGestureState = 2;
                        this.d.mGestureType = 3;
                        this.d.mLocation = new float[]{moveGestureDetector.getEvent().getX(), moveGestureDetector.getEvent().getY()};
                        int a = h.this.a.a(this.d);
                        PointF focusDelta = moveGestureDetector.getFocusDelta();
                        float f = 1.0f;
                        if (h.this.j == 0) {
                            f = 4.0f;
                        }
                        if (Math.abs(focusDelta.x) <= f && Math.abs(focusDelta.y) <= f) {
                            return false;
                        }
                        if (h.this.j == 0) {
                            h.this.a.a().clearAnimations(a, false);
                        }
                        h.this.a.a(a, MoveGestureMapMessage.obtain(101, focusDelta.x, focusDelta.y));
                        h.l(h.this);
                        return true;
                    }
                }
                return true;
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onMove");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.autonavi.ae.gmap.gesture.MoveGestureDetector.OnMoveGestureListener
        public boolean onMoveBegin(MoveGestureDetector moveGestureDetector) {
            try {
                if (h.this.a.h().isScrollGesturesEnabled()) {
                    this.d.mGestureState = 1;
                    this.d.mGestureType = 3;
                    this.d.mLocation = new float[]{moveGestureDetector.getEvent().getX(), moveGestureDetector.getEvent().getY()};
                    h.this.a.a(h.this.a.a(this.d), MoveGestureMapMessage.obtain(100, 0.0f, 0.0f));
                }
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onMoveBegin");
                th.printStackTrace();
            }
            return true;
        }

        @Override // com.autonavi.ae.gmap.gesture.MoveGestureDetector.OnMoveGestureListener
        public void onMoveEnd(MoveGestureDetector moveGestureDetector) {
            try {
                if (h.this.a.h().isScrollGesturesEnabled()) {
                    this.d.mGestureState = 3;
                    this.d.mGestureType = 3;
                    this.d.mLocation = new float[]{moveGestureDetector.getEvent().getX(), moveGestureDetector.getEvent().getY()};
                    int a = h.this.a.a(this.d);
                    if (h.this.j > 0) {
                        h.this.a.a(a, 5);
                    }
                    h.this.a.a(a, MoveGestureMapMessage.obtain(102, 0.0f, 0.0f));
                }
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onMoveEnd");
                th.printStackTrace();
            }
        }
    }

    /* compiled from: GLMapGestureDetector.java */
    /* loaded from: classes.dex */
    private class b implements HoverGestureDetector.OnHoverGestureListener {
        private EAMapPlatformGestureInfo b;

        private b() {
            this.b = new EAMapPlatformGestureInfo();
        }

        @Override // com.autonavi.ae.gmap.gesture.HoverGestureDetector.OnHoverGestureListener
        public boolean onHove(HoverGestureDetector hoverGestureDetector) {
            boolean z = false;
            this.b.mGestureState = 2;
            this.b.mGestureType = 6;
            this.b.mLocation = new float[]{hoverGestureDetector.getEvent().getX(), hoverGestureDetector.getEvent().getY()};
            try {
                if (!h.this.a.h().isTiltGesturesEnabled()) {
                    return true;
                }
                int a = h.this.a.a(this.b);
                if (!h.this.a.d(a) && h.this.l <= 3) {
                    float f = hoverGestureDetector.getFocusDelta().x;
                    float f2 = hoverGestureDetector.getFocusDelta().y;
                    if (!h.this.i) {
                        PointF pointerDelta = hoverGestureDetector.getPointerDelta(0);
                        PointF pointerDelta2 = hoverGestureDetector.getPointerDelta(1);
                        if ((pointerDelta.y > 10.0f && pointerDelta2.y > 10.0f) || (pointerDelta.y < -10.0f && pointerDelta2.y < -10.0f)) {
                            z = true;
                        }
                        if (z && Math.abs(f2) > 10.0f && Math.abs(f) < 10.0f) {
                            h.this.i = true;
                        }
                    }
                    if (!h.this.i) {
                        return true;
                    }
                    h.this.i = true;
                    float f3 = f2 / 6.0f;
                    if (Math.abs(f3) <= 1.0f) {
                        return true;
                    }
                    h.this.a.a(a, HoverGestureMapMessage.obtain(101, f3));
                    h.m(h.this);
                    return true;
                }
                return false;
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onHove");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.autonavi.ae.gmap.gesture.HoverGestureDetector.OnHoverGestureListener
        public boolean onHoveBegin(HoverGestureDetector hoverGestureDetector) {
            this.b.mGestureState = 1;
            this.b.mGestureType = 6;
            this.b.mLocation = new float[]{hoverGestureDetector.getEvent().getX(), hoverGestureDetector.getEvent().getY()};
            try {
                if (!h.this.a.h().isTiltGesturesEnabled()) {
                    return true;
                }
                int a = h.this.a.a(this.b);
                if (h.this.a.d(a)) {
                    return false;
                }
                h.this.a.a(a, HoverGestureMapMessage.obtain(100, h.this.a.o(a)));
                return true;
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onHoveBegin");
                th.printStackTrace();
                return true;
            }
        }

        @Override // com.autonavi.ae.gmap.gesture.HoverGestureDetector.OnHoverGestureListener
        public void onHoveEnd(HoverGestureDetector hoverGestureDetector) {
            this.b.mGestureState = 3;
            this.b.mGestureType = 6;
            this.b.mLocation = new float[]{hoverGestureDetector.getEvent().getX(), hoverGestureDetector.getEvent().getY()};
            try {
                if (h.this.a.h().isTiltGesturesEnabled()) {
                    int a = h.this.a.a(this.b);
                    if (!h.this.a.d(a)) {
                        if (h.this.a.o(a) >= 0.0f && h.this.m > 0) {
                            h.this.a.a(a, 7);
                        }
                        h.this.i = false;
                        h.this.a.a(a, HoverGestureMapMessage.obtain(102, h.this.a.o(a)));
                    }
                }
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onHoveEnd");
                th.printStackTrace();
            }
        }
    }

    /* compiled from: GLMapGestureDetector.java */
    /* loaded from: classes.dex */
    private class e extends ZoomOutGestureDetector.SimpleOnZoomOutGestureListener {
        EAMapPlatformGestureInfo a;

        private e() {
            this.a = new EAMapPlatformGestureInfo();
        }

        @Override // com.autonavi.ae.gmap.gesture.ZoomOutGestureDetector.SimpleOnZoomOutGestureListener, com.autonavi.ae.gmap.gesture.ZoomOutGestureDetector.OnZoomOutGestureListener
        public void onZoomOut(ZoomOutGestureDetector zoomOutGestureDetector) {
            try {
                if (h.this.a.h().isZoomGesturesEnabled() && Math.abs(zoomOutGestureDetector.getFocusX()) <= 10.0f && Math.abs(zoomOutGestureDetector.getFocusY()) <= 10.0f && zoomOutGestureDetector.getTimeDelta() < 200) {
                    h.this.q = true;
                    this.a.mGestureState = 2;
                    this.a.mGestureType = 2;
                    this.a.mLocation = new float[]{zoomOutGestureDetector.getEvent().getX(), zoomOutGestureDetector.getEvent().getY()};
                    int a = h.this.a.a(this.a);
                    h.this.a.a(a, 4);
                    h.this.a.c(a);
                }
            } catch (Throwable th) {
                gr.b(th, "GLMapGestrureDetector", "onZoomOut");
                th.printStackTrace();
            }
        }
    }
}

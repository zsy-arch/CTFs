package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Marker;
import com.autonavi.ae.gmap.listener.MapWidgetListener;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;

/* compiled from: MapOverlayViewGroup.java */
/* loaded from: classes.dex */
public class eg extends ViewGroup implements cd {
    private k c;
    private Context d;
    private ei e;
    private ef f;
    private ed g;
    private eh h;
    private ec i;
    private ee j;
    private ej k;
    private View l;
    private View m;
    private TextView n;
    private TextView o;
    private ce p;
    private AMap.InfoWindowAdapter r;
    private Drawable q = null;
    private boolean s = true;
    private AMap.InfoWindowAdapter t = new AMap.InfoWindowAdapter() { // from class: com.amap.api.col.eg.1
        @Override // com.amap.api.maps.AMap.InfoWindowAdapter
        public View getInfoWindow(Marker marker) {
            try {
                if (eg.this.q == null) {
                    eg.this.q = dn.a(eg.this.d, "infowindow_bg.9.png");
                }
                if (eg.this.m == null) {
                    eg.this.m = new LinearLayout(eg.this.d);
                    eg.this.m.setBackground(eg.this.q);
                    eg.this.n = new TextView(eg.this.d);
                    eg.this.n.setText(marker.getTitle());
                    eg.this.n.setTextColor(-16777216);
                    eg.this.o = new TextView(eg.this.d);
                    eg.this.o.setTextColor(-16777216);
                    eg.this.o.setText(marker.getSnippet());
                    ((LinearLayout) eg.this.m).setOrientation(1);
                    ((LinearLayout) eg.this.m).addView(eg.this.n);
                    ((LinearLayout) eg.this.m).addView(eg.this.o);
                }
            } catch (Throwable th) {
                gr.b(th, "MapOverlayViewGroup", "showInfoWindow decodeDrawableFromAsset");
                th.printStackTrace();
            }
            return eg.this.m;
        }

        @Override // com.amap.api.maps.AMap.InfoWindowAdapter
        public View getInfoContents(Marker marker) {
            return null;
        }
    };
    int a = 0;
    int b = 0;

    public eg(Context context, k kVar) {
        super(context);
        try {
            this.c = kVar;
            this.d = context;
            setBackgroundColor(-1);
            a(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(Context context) {
        int i;
        this.e = new ei(context, this.c);
        this.h = new eh(context, this.c);
        this.i = new ec(context);
        this.j = new ee(context);
        this.k = new ej(context, this.c);
        this.f = new ef(context, this.c);
        this.g = new ed(context, this.c);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        if (this.c.l() != null) {
            addView(this.c.l(), 0, layoutParams);
            i = 1;
        } else {
            i = 0;
        }
        addView(this.i, i, layoutParams);
        addView(this.e, layoutParams);
        addView(this.h, layoutParams);
        addView(this.j, new ViewGroup.LayoutParams(-2, -2));
        addView(this.k, new a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 83));
        addView(this.f, new a(-2, -2, FPoint.obtain(0.0f, 0.0f), 0, 0, 83));
        addView(this.g, new a(-2, -2, FPoint.obtain(0.0f, 0.0f), 0, 0, 51));
        this.g.setVisibility(8);
        this.c.a(new MapWidgetListener() { // from class: com.amap.api.col.eg.2
            @Override // com.autonavi.ae.gmap.listener.MapWidgetListener
            public void invalidateScaleView() {
                if (eg.this.h != null) {
                    eg.this.h.post(new Runnable() { // from class: com.amap.api.col.eg.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            eg.this.h.b();
                        }
                    });
                }
            }

            @Override // com.autonavi.ae.gmap.listener.MapWidgetListener
            public void invalidateCompassView() {
                if (eg.this.g != null) {
                    eg.this.g.post(new Runnable() { // from class: com.amap.api.col.eg.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            eg.this.g.b();
                        }
                    });
                }
            }

            @Override // com.autonavi.ae.gmap.listener.MapWidgetListener
            public void invalidateZoomController(final float f) {
                if (eg.this.k != null) {
                    eg.this.k.post(new Runnable() { // from class: com.amap.api.col.eg.2.3
                        @Override // java.lang.Runnable
                        public void run() {
                            eg.this.k.a(f);
                        }
                    });
                }
            }

            @Override // com.autonavi.ae.gmap.listener.MapWidgetListener
            public void setFrontViewVisibility(boolean z) {
            }
        });
        this.r = this.t;
        try {
            if (!this.c.h().isMyLocationButtonEnabled()) {
                this.f.setVisibility(8);
            }
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImpGLSurfaceView", "locationView gone");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (this.j != null && z && this.c.m()) {
            this.j.a(true);
        }
    }

    public void b(boolean z) {
        if (this.k != null) {
            this.k.a(z);
        }
    }

    public void c(boolean z) {
        if (this.f != null) {
            if (z) {
                this.f.setVisibility(0);
            } else {
                this.f.setVisibility(8);
            }
        }
    }

    public void d(boolean z) {
        if (this.g != null) {
            this.g.a(z);
        }
    }

    public void e(boolean z) {
        if (this.h != null) {
            this.h.a(z);
        }
    }

    public void f(boolean z) {
        if (this.e != null) {
            this.e.setVisibility(z ? 0 : 8);
        }
    }

    public void a(float f) {
        if (this.k != null) {
            this.k.a(f);
        }
    }

    public void a(int i) {
        if (this.k != null) {
            this.k.a(i);
        }
    }

    public void b(int i) {
        if (this.e != null) {
            this.e.a(i);
            this.e.postInvalidate();
            l();
        }
    }

    private void l() {
        if (this.h != null && this.h.getVisibility() == 0) {
            this.h.postInvalidate();
        }
    }

    public void c(int i) {
        if (this.e != null) {
            this.e.b(i);
            l();
        }
    }

    public void d(int i) {
        if (this.e != null) {
            this.e.c(i);
            l();
        }
    }

    public float e(int i) {
        if (this.e == null) {
            return 0.0f;
        }
        l();
        return this.e.d(i);
    }

    public void a(int i, float f) {
        if (this.e != null) {
            this.e.a(i, f);
            l();
        }
    }

    public void a(AMap.InfoWindowAdapter infoWindowAdapter) throws RemoteException {
        if (infoWindowAdapter == null) {
            this.r = this.t;
        } else {
            this.r = infoWindowAdapter;
        }
    }

    public Point a() {
        if (this.e == null) {
            return null;
        }
        return this.e.c();
    }

    public ec c() {
        return this.i;
    }

    public ee d() {
        return this.j;
    }

    public ef f() {
        return this.f;
    }

    public ed g() {
        return this.g;
    }

    public ei h() {
        return this.e;
    }

    public void a(CameraPosition cameraPosition) {
        if (g.c != 1 && this.c.h().isLogoEnable()) {
            if (cameraPosition.zoom >= 10.0f && !dp.a(cameraPosition.target.latitude, cameraPosition.target.longitude)) {
                this.e.setVisibility(8);
            } else if (this.c.n() == -1) {
                this.e.setVisibility(0);
            }
        }
    }

    public void i() {
        if (this.k != null) {
            this.k.a();
        }
        if (this.h != null) {
            this.h.a();
        }
        if (this.e != null) {
            this.e.a();
        }
        if (this.f != null) {
            this.f.a();
        }
        if (this.g != null) {
            this.g.a();
        }
        if (this.j != null) {
            this.j.b();
        }
    }

    /* compiled from: MapOverlayViewGroup.java */
    /* loaded from: classes.dex */
    public static class a extends ViewGroup.LayoutParams {
        public FPoint a;
        public int b;
        public int c;
        public int d;

        public a(int i, int i2, FPoint fPoint, int i3, int i4, int i5) {
            super(i, i2);
            this.a = null;
            this.b = 0;
            this.c = 0;
            this.d = 51;
            this.a = fPoint;
            this.b = i3;
            this.c = i4;
            this.d = i5;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        try {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt != null) {
                    if (childAt.getLayoutParams() instanceof a) {
                        a(childAt, (a) childAt.getLayoutParams());
                    } else {
                        a(childAt, childAt.getLayoutParams());
                    }
                }
            }
            this.e.d();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(View view, ViewGroup.LayoutParams layoutParams) {
        int[] iArr = new int[2];
        a(view, layoutParams.width, layoutParams.height, iArr);
        if (view instanceof ee) {
            a(view, iArr[0], iArr[1], 20, (this.c.k().y - 80) - iArr[1], 51);
        } else {
            a(view, iArr[0], iArr[1], 0, 0, 51);
        }
    }

    private void a(View view, a aVar) {
        int[] iArr = new int[2];
        a(view, aVar.width, aVar.height, iArr);
        if (view instanceof ej) {
            a(view, iArr[0], iArr[1], getWidth() - iArr[0], getHeight(), aVar.d);
        } else if (view instanceof ef) {
            a(view, iArr[0], iArr[1], getWidth() - iArr[0], iArr[1], aVar.d);
        } else if (view instanceof ed) {
            a(view, iArr[0], iArr[1], 0, 0, aVar.d);
        } else if (aVar.a != null) {
            IPoint obtain = IPoint.obtain();
            this.c.b().map2Win(aVar.a.x, aVar.a.y, obtain);
            obtain.x += aVar.b;
            obtain.y += aVar.c;
            a(view, iArr[0], iArr[1], obtain.x, obtain.y, aVar.d);
            obtain.recycle();
        }
    }

    @Override // com.amap.api.col.cd
    public void a(ce ceVar) {
        if (ceVar != null) {
            try {
                if (!(this.r == this.t && ceVar.getTitle() == null && ceVar.getSnippet() == null) && ceVar.isInfoWindowEnable()) {
                    if (this.p != null && !this.p.getId().equals(ceVar.getId())) {
                        b();
                    }
                    if (this.r != null) {
                        this.p = ceVar;
                        ceVar.a(true);
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    private View b(ce ceVar) throws RemoteException {
        Throwable th;
        View infoWindow;
        View view = null;
        Marker marker = new Marker(ceVar);
        try {
            if (this.q == null) {
                this.q = dn.a(this.d, "infowindow_bg.9.png");
            }
        } catch (Throwable th2) {
            gr.b(th2, "MapOverlayViewGroup", "showInfoWindow decodeDrawableFromAsset");
            th2.printStackTrace();
        }
        try {
            infoWindow = this.r.getInfoWindow(marker);
            if (infoWindow == null) {
                try {
                    infoWindow = this.r.getInfoContents(marker);
                } catch (Throwable th3) {
                    view = infoWindow;
                    th = th3;
                    gr.b(th, "MapOverlayViewGroup", "getInfoWindow or getInfoContents");
                    th.printStackTrace();
                    return view;
                }
            }
        } catch (Throwable th4) {
            th = th4;
        }
        if (infoWindow == null) {
            if (this.r == this.t) {
                view = this.t.getInfoWindow(marker);
            }
            return view;
        }
        view = infoWindow;
        if (view.getBackground() == null) {
            view.setBackground(this.q);
        }
        return view;
    }

    @Override // com.amap.api.col.cd
    public void e() {
        View b;
        try {
            if (this.p == null || !this.p.j()) {
                if (this.l != null && this.l.getVisibility() == 0) {
                    this.l.setVisibility(8);
                }
            } else if (this.s) {
                int e = this.p.e() + this.p.c();
                int f = this.p.f() + this.p.d() + 2;
                if ((!this.p.g() || e != this.a || f != this.b) && (b = b(this.p)) != null) {
                    a(b, e, f);
                    this.p.h();
                    if (this.l != null) {
                        a aVar = (a) this.l.getLayoutParams();
                        if (aVar != null) {
                            aVar.a = this.p.a();
                            aVar.b = e;
                            aVar.c = f;
                        }
                        onLayout(false, 0, 0, 0, 0);
                        this.a = e;
                        this.b = f;
                        if (this.r == this.t) {
                            if (this.n != null) {
                                this.n.setText(this.p.getTitle());
                            }
                            if (this.o != null) {
                                this.o.setText(this.p.getSnippet());
                            }
                        }
                        if (this.l.getVisibility() == 8) {
                            this.l.setVisibility(0);
                        }
                    }
                }
            }
        } catch (Throwable th) {
            gr.b(th, "MapOverlayViewGroup", "redrawInfoWindow");
            th.printStackTrace();
        }
    }

    private void a(View view, int i, int i2) throws RemoteException {
        int i3;
        int i4 = -2;
        if (view != null) {
            if (this.l != null) {
                if (view != this.l) {
                    this.l.clearFocus();
                    removeView(this.l);
                } else {
                    return;
                }
            }
            this.l = view;
            ViewGroup.LayoutParams layoutParams = this.l.getLayoutParams();
            this.l.setDrawingCacheEnabled(true);
            this.l.setDrawingCacheQuality(0);
            this.p.h();
            if (layoutParams != null) {
                i3 = layoutParams.width;
                i4 = layoutParams.height;
            } else {
                i3 = -2;
            }
            addView(this.l, new a(i3, i4, this.p.a(), i, i2, 81));
        }
    }

    @Override // com.amap.api.col.cd
    public void b() {
        if (this.l != null) {
            this.l.clearFocus();
            removeView(this.l);
            dt.a(this.l.getBackground());
            dt.a(this.q);
            this.l = null;
        }
        if (this.p != null) {
            this.p.a(false);
        }
        this.p = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.a = 0;
        this.b = 0;
    }

    private void a(View view, int i, int i2, int[] iArr) {
        View view2;
        if ((view instanceof ListView) && (view2 = (View) view.getParent()) != null) {
            iArr[0] = view2.getWidth();
            iArr[1] = view2.getHeight();
        }
        if (i <= 0 || i2 <= 0) {
            view.measure(0, 0);
        }
        if (i == -2) {
            iArr[0] = view.getMeasuredWidth();
        } else if (i == -1) {
            iArr[0] = getMeasuredWidth();
        } else {
            iArr[0] = i;
        }
        if (i2 == -2) {
            iArr[1] = view.getMeasuredHeight();
        } else if (i2 == -1) {
            iArr[1] = getMeasuredHeight();
        } else {
            iArr[1] = i2;
        }
    }

    private void a(View view, int i, int i2, int i3, int i4, int i5) {
        int i6 = i5 & 7;
        int i7 = i5 & 112;
        if (i6 == 5) {
            i3 -= i;
        } else if (i6 == 1) {
            i3 -= i / 2;
        }
        if (i7 == 80) {
            i4 -= i2;
        } else if (i7 == 17) {
            i4 -= i2 / 2;
        } else if (i7 == 16) {
            i4 = (i4 / 2) - (i2 / 2);
        }
        view.layout(i3, i4, i3 + i, i4 + i2);
    }

    public void j() {
        b();
        dt.a(this.q);
        i();
        removeAllViews();
        this.n = null;
        this.o = null;
        this.m = null;
    }

    @Override // com.amap.api.col.cd
    public boolean a(MotionEvent motionEvent) {
        if (this.l == null || this.p == null || !dt.a(new Rect(this.l.getLeft(), this.l.getTop(), this.l.getRight(), this.l.getBottom()), (int) motionEvent.getX(), (int) motionEvent.getY())) {
            return false;
        }
        return true;
    }

    public void a(Canvas canvas) {
        Bitmap drawingCache;
        if (this.l != null && this.p != null && (drawingCache = this.l.getDrawingCache(true)) != null) {
            canvas.drawBitmap(drawingCache, this.l.getLeft(), this.l.getTop(), new Paint());
        }
    }

    public void k() {
        this.a = 0;
        this.b = 0;
    }
}

package com.amap.api.col;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.IPoint;

/* compiled from: MyLocationOverlay.java */
/* loaded from: classes.dex */
public class cn {
    ValueAnimator b;
    private k e;
    private Marker f;
    private Circle g;
    private LatLng i;
    private double j;
    private Context k;
    private t l;
    private MyLocationStyle h = new MyLocationStyle();
    private int m = 4;
    private boolean n = false;
    private final String o = "location_map_gps_locked.png";
    private final String p = "location_map_gps_3d.png";
    private BitmapDescriptor q = null;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;

    /* renamed from: u  reason: collision with root package name */
    private boolean f9u = false;
    private boolean v = false;
    a a = null;
    Animator.AnimatorListener c = new Animator.AnimatorListener() { // from class: com.amap.api.col.cn.1
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            cn.this.i();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }
    };
    ValueAnimator.AnimatorUpdateListener d = new ValueAnimator.AnimatorUpdateListener() { // from class: com.amap.api.col.cn.2
        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            try {
                if (cn.this.g != null) {
                    LatLng latLng = (LatLng) valueAnimator.getAnimatedValue();
                    cn.this.g.setCenter(latLng);
                    cn.this.f.setPosition(latLng);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };

    public cn(k kVar, Context context) {
        this.k = context.getApplicationContext();
        this.e = kVar;
        this.l = new t(this.k, kVar);
        a(4, true);
    }

    public void a(MyLocationStyle myLocationStyle) {
        try {
            this.h = myLocationStyle;
            a(this.h.isMyLocationShowing());
            if (!this.h.isMyLocationShowing()) {
                this.l.a(false);
                this.m = this.h.getMyLocationType();
            } else {
                j();
                if (!(this.f == null && this.g == null)) {
                    this.l.a(this.f);
                    a(this.h.getMyLocationType());
                }
            }
        } catch (Throwable th) {
            gr.b(th, "MyLocationOverlay", "setMyLocationStyle");
            th.printStackTrace();
        }
    }

    public MyLocationStyle a() {
        return this.h;
    }

    public void a(int i) {
        a(i, false);
    }

    private void a(int i, boolean z) {
        this.m = i;
        this.n = false;
        this.s = false;
        this.r = false;
        this.f9u = false;
        this.v = false;
        switch (this.m) {
            case 1:
                this.r = true;
                this.s = true;
                this.t = true;
                break;
            case 2:
                this.r = true;
                this.t = true;
                break;
            case 3:
                this.r = true;
                this.v = true;
                break;
            case 4:
                this.r = true;
                this.f9u = true;
                break;
            case 5:
                this.f9u = true;
                break;
            case 7:
                this.v = true;
                break;
        }
        if (this.f9u || this.v) {
            if (this.v) {
                this.l.a(true);
                if (!z) {
                    try {
                        this.e.a(z.a(17.0f));
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                b(45.0f);
            } else {
                this.l.a(false);
            }
            this.l.a();
            if (this.f != null) {
                this.f.setFlat(true);
                return;
            }
            return;
        }
        if (this.f != null) {
            this.f.setFlat(false);
        }
        h();
        g();
        f();
    }

    private void f() {
        this.l.b();
    }

    private void g() {
        b(0.0f);
    }

    private void h() {
        c(0.0f);
    }

    private void b(float f) {
        if (this.e != null) {
            try {
                this.e.a(z.c(f));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void c(float f) {
        if (this.e != null) {
            try {
                this.e.a(z.d(f));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a(Location location) {
        if (location != null) {
            a(this.h.isMyLocationShowing());
            if (this.h.isMyLocationShowing()) {
                this.i = new LatLng(location.getLatitude(), location.getLongitude());
                this.j = location.getAccuracy();
                if (this.f == null && this.g == null) {
                    j();
                }
                if (this.g != null) {
                    try {
                        if (this.j != -1.0d) {
                            this.g.setRadius(this.j);
                        }
                    } catch (Throwable th) {
                        gr.b(th, "MyLocationOverlay", "setCentAndRadius");
                        th.printStackTrace();
                    }
                }
                d(location.getBearing());
                if (!this.i.equals(this.f.getPosition())) {
                    a(this.i);
                } else {
                    i();
                }
            }
        }
    }

    private void d(float f) {
        if (this.t) {
            float f2 = f % 360.0f;
            if (f2 > 180.0f) {
                f2 -= 360.0f;
            } else if (f2 < -180.0f) {
                f2 += 360.0f;
            }
            if (this.f != null) {
                this.f.setRotateAngle(-f2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.i == null || !this.r) {
            return;
        }
        if (!this.s || !this.n) {
            this.n = true;
            try {
                IPoint obtain = IPoint.obtain();
                GLMapState.lonlat2Geo(this.i.longitude, this.i.latitude, obtain);
                this.e.b(z.a(obtain));
            } catch (Throwable th) {
                gr.b(th, "MyLocationOverlay", "moveMapToLocation");
                th.printStackTrace();
            }
        }
    }

    private void j() {
        if (this.h == null) {
            this.h = new MyLocationStyle();
            this.h.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
            l();
            return;
        }
        if (this.h.getMyLocationIcon() == null || this.h.getMyLocationIcon().getBitmap() == null) {
            this.h.myLocationIcon(BitmapDescriptorFactory.fromAsset("location_map_gps_locked.png"));
        }
        l();
    }

    public void b() throws RemoteException {
        k();
        if (this.l != null) {
            f();
            this.l = null;
        }
    }

    private void k() {
        if (this.g != null) {
            try {
                this.e.a(this.g.getId());
            } catch (Throwable th) {
                gr.b(th, "MyLocationOverlay", "locationIconRemove");
                th.printStackTrace();
            }
            this.g = null;
        }
        if (this.f != null) {
            this.f.remove();
            this.f.destroy();
            this.f = null;
            this.l.a((Marker) null);
        }
    }

    public void a(boolean z) {
        if (!(this.g == null || this.g.isVisible() == z)) {
            this.g.setVisible(z);
        }
        if (this.f != null && this.f.isVisible() != z) {
            this.f.setVisible(z);
        }
    }

    private void l() {
        try {
            if (this.g == null) {
                this.g = this.e.addCircle(new CircleOptions().zIndex(1.0f));
            }
            if (this.g != null) {
                if (this.g.getStrokeWidth() != this.h.getStrokeWidth()) {
                    this.g.setStrokeWidth(this.h.getStrokeWidth());
                }
                if (this.g.getFillColor() != this.h.getRadiusFillColor()) {
                    this.g.setFillColor(this.h.getRadiusFillColor());
                }
                if (this.g.getStrokeColor() != this.h.getStrokeColor()) {
                    this.g.setStrokeColor(this.h.getStrokeColor());
                }
                if (this.i != null) {
                    this.g.setCenter(this.i);
                }
                this.g.setRadius(this.j);
                this.g.setVisible(true);
            }
            if (this.f == null) {
                this.f = this.e.addMarker(new MarkerOptions().visible(false));
            }
            if (this.f != null) {
                if (!(this.f.getAnchorU() == this.h.getAnchorU() && this.f.getAnchorV() == this.h.getAnchorV())) {
                    this.f.setAnchor(this.h.getAnchorU(), this.h.getAnchorV());
                }
                if (this.f.getIcons() == null || this.f.getIcons().size() == 0) {
                    this.f.setIcon(this.h.getMyLocationIcon());
                } else if (this.h.getMyLocationIcon() != null && !this.f.getIcons().get(0).equals(this.h.getMyLocationIcon())) {
                    this.f.setIcon(this.h.getMyLocationIcon());
                }
                if (this.i != null) {
                    this.f.setPosition(this.i);
                    this.f.setVisible(true);
                }
            }
            i();
            this.l.a(this.f);
        } catch (Throwable th) {
            gr.b(th, "MyLocationOverlay", "myLocStyle");
            th.printStackTrace();
        }
    }

    public void a(float f) {
        if (this.f != null) {
            this.f.setRotateAngle(f);
        }
    }

    public String c() {
        if (this.f != null) {
            return this.f.getId();
        }
        return null;
    }

    public String d() throws RemoteException {
        if (this.g != null) {
            return this.g.getId();
        }
        return null;
    }

    public void e() {
        this.g = null;
        this.f = null;
    }

    private void a(LatLng latLng) {
        LatLng position = this.f.getPosition();
        if (position == null) {
            position = new LatLng(0.0d, 0.0d);
        }
        if (this.a == null) {
            this.a = new a();
        }
        if (this.b == null) {
            this.b = ValueAnimator.ofObject(new a(), position, latLng);
            this.b.addListener(this.c);
            this.b.addUpdateListener(this.d);
        } else {
            this.b.setObjectValues(position, latLng);
            this.b.setEvaluator(this.a);
        }
        if (position.latitude == 0.0d && position.longitude == 0.0d) {
            this.b.setDuration(1L);
        } else {
            this.b.setDuration(1000L);
        }
        this.b.start();
    }

    /* compiled from: MyLocationOverlay.java */
    /* loaded from: classes.dex */
    public class a implements TypeEvaluator {
        public a() {
        }

        @Override // android.animation.TypeEvaluator
        public Object evaluate(float f, Object obj, Object obj2) {
            LatLng latLng = (LatLng) obj;
            LatLng latLng2 = (LatLng) obj2;
            return new LatLng(latLng.latitude + (f * (latLng2.latitude - latLng.latitude)), latLng.longitude + (f * (latLng2.longitude - latLng.longitude)));
        }
    }
}

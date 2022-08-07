package com.amap.api.col;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: NaviCarOverlay.java */
/* loaded from: classes.dex */
public class fb {
    private int c;
    private int d;
    private float e;
    private int f;
    private Timer i;
    private es l;
    private BitmapDescriptor m;
    private BitmapDescriptor n;
    private Marker o;
    private Marker p;
    private Marker q;
    private MapView s;
    private Bitmap x;
    private Bitmap y;
    private float z;
    private boolean a = true;
    private IPoint b = null;
    private float g = 0.0f;
    private boolean h = false;
    private float j = 0.0f;
    private int k = -1;
    private AMap r = null;
    private boolean t = true;

    /* renamed from: u  reason: collision with root package name */
    private LatLng f19u = null;
    private Polyline v = null;
    private List<LatLng> w = new ArrayList();
    private int A = 0;

    public fb(MapView mapView, es esVar) {
        this.m = null;
        this.n = null;
        this.m = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313412));
        this.n = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(fo.a(), 1191313495));
        this.s = mapView;
        this.l = esVar;
    }

    public void a() {
        if (this.a && this.p != null) {
            this.r.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(this.p.getPosition(), this.l.getLockZoom(), 0.0f, 0.0f)));
            this.o.setRotateAngle(360.0f - this.j);
        }
    }

    public void b() {
        if (this.a && this.p != null) {
            this.r.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(this.p.getPosition(), this.l.getLockZoom(), this.l.getLockTilt(), this.j)));
            this.o.setRotateAngle(0.0f);
        }
    }

    public void a(boolean z) {
        this.a = z;
        if (this.o != null && this.r != null && this.q != null && this.p != null) {
            if (!this.a) {
                this.o.setFlat(true);
                this.q.setGeoPoint(this.p.getGeoPoint());
                this.o.setGeoPoint(this.p.getGeoPoint());
                this.o.setRotateAngle(this.p.getRotateAngle());
            } else if (this.l.getNaviMode() == 1) {
                this.r.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(this.p.getPosition()).bearing(0.0f).tilt(0.0f).zoom(this.l.getLockZoom()).build()));
                int height = (int) (this.s.getHeight() * this.l.getAnchorY());
                this.o.setPositionByPixels((int) (this.s.getWidth() * this.l.getAnchorX()), height);
                this.o.setRotateAngle(360.0f - this.j);
                this.o.setFlat(false);
                if (this.t) {
                    this.q.setVisible(true);
                } else {
                    this.q.setVisible(false);
                }
            } else {
                this.r.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(this.p.getPosition()).bearing(this.j).tilt(this.l.getLockTilt()).zoom(this.l.getLockZoom()).build()));
                int height2 = (int) (this.s.getHeight() * this.l.getAnchorY());
                this.o.setPositionByPixels((int) (this.s.getWidth() * this.l.getAnchorX()), height2);
                this.o.setRotateAngle(0.0f);
                this.o.setFlat(false);
                if (this.t) {
                    this.q.setVisible(true);
                } else {
                    this.q.setVisible(false);
                }
            }
        }
    }

    public void c() {
        if (this.o != null) {
            this.o.remove();
        }
        if (this.q != null) {
            this.q.remove();
        }
        if (this.p != null) {
            this.p.remove();
        }
        if (this.v != null) {
            this.v.remove();
        }
        this.v = null;
        this.o = null;
        this.q = null;
        this.p = null;
    }

    public void a(AMap aMap, LatLng latLng, float f) {
        if (aMap != null && latLng != null && this.m != null) {
            this.r = aMap;
            if (this.o == null) {
                this.o = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).setFlat(true).icon(this.m).position(latLng));
            }
            if (this.p == null) {
                this.p = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).setFlat(true).icon(this.m).position(latLng));
                this.p.setRotateAngle(f);
                this.p.setVisible(false);
            }
            if (this.q == null) {
                this.q = aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f).setFlat(true).icon(this.n).position(latLng));
                if (this.t) {
                    this.q.setVisible(true);
                } else {
                    this.q.setVisible(false);
                }
            }
            this.o.setVisible(true);
            new IPoint();
            a(fq.a(latLng.latitude, latLng.longitude, 20), f);
            g();
        }
    }

    public void a(LatLng latLng) {
        this.f19u = latLng;
    }

    public void d() {
        if (this.o != null) {
            this.o.remove();
        }
        if (this.p != null) {
            this.p.remove();
        }
        if (this.q != null) {
            this.q.remove();
        }
        this.m = null;
        if (this.i != null) {
            this.i.cancel();
        }
    }

    private void a(IPoint iPoint, float f) {
        boolean z;
        if (this.o != null) {
            IPoint geoPoint = this.p.getGeoPoint();
            if (geoPoint == null || geoPoint.x == 0 || geoPoint.y == 0) {
                geoPoint = iPoint;
            }
            this.f = 0;
            this.b = geoPoint;
            this.c = (iPoint.x - geoPoint.x) / 20;
            this.d = (iPoint.y - geoPoint.y) / 20;
            this.g = this.p.getRotateAngle();
            if (Float.compare(this.g, f) == 0) {
                z = true;
            } else {
                this.g = 360.0f - this.g;
                z = false;
            }
            float f2 = f - this.g;
            if (z) {
                f2 = 0.0f;
            }
            if (f2 > 180.0f) {
                f2 -= 360.0f;
            } else if (f2 < -180.0f) {
                f2 += 360.0f;
            }
            this.e = f2 / 20.0f;
            this.h = true;
        }
    }

    private void g() {
        if (this.i == null) {
            this.i = new Timer();
            this.i.schedule(new TimerTask() { // from class: com.amap.api.col.fb.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    fb.this.h();
                }
            }, 0L, 50L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.h && this.o != null && this.r != null) {
            try {
                IPoint geoPoint = this.o.getGeoPoint();
                int i = this.f;
                this.f = i + 1;
                if (i < 20) {
                    int i2 = this.b.x + (this.c * this.f);
                    int i3 = this.b.y + (this.d * this.f);
                    this.j = this.g + (this.e * this.f);
                    this.j %= 1800.0f;
                    if (!(i2 == 0 && i3 == 0)) {
                        geoPoint = new IPoint(i2, i3);
                    }
                    if (!this.a) {
                        this.o.setGeoPoint(geoPoint);
                        this.o.setFlat(true);
                        this.o.setRotateAngle(360.0f - this.j);
                        if (this.q != null) {
                            this.q.setGeoPoint(geoPoint);
                        }
                    } else if (this.l.getNaviMode() == 1) {
                        int width = (int) (this.s.getWidth() * this.l.getAnchorX());
                        int height = (int) (this.s.getHeight() * this.l.getAnchorY());
                        this.o.setPositionByPixels(width, height);
                        this.o.setFlat(false);
                        if (this.A == 1 || this.A == 2) {
                            this.r.moveCamera(CameraUpdateFactory.changeBearingGeoCenter(this.z, geoPoint));
                            this.o.setRotateAngle(((this.z - 360.0f) - this.j) % 360.0f);
                        } else {
                            this.r.moveCamera(CameraUpdateFactory.changeBearingGeoCenter(0.0f, geoPoint));
                            this.o.setRotateAngle(360.0f - this.j);
                        }
                        if (this.q != null) {
                            this.q.setPositionByPixels(width, height);
                            if (this.t) {
                                this.q.setVisible(true);
                            } else {
                                this.q.setVisible(false);
                            }
                        }
                    } else {
                        this.r.moveCamera(CameraUpdateFactory.changeBearingGeoCenter(this.j, geoPoint));
                        int width2 = (int) (this.s.getWidth() * this.l.getAnchorX());
                        int height2 = (int) (this.s.getHeight() * this.l.getAnchorY());
                        this.o.setPositionByPixels(width2, height2);
                        this.o.setRotateAngle(0.0f);
                        this.o.setFlat(false);
                        if (this.q != null) {
                            this.q.setPositionByPixels(width2, height2);
                            if (this.t) {
                                this.q.setVisible(true);
                            } else {
                                this.q.setVisible(false);
                            }
                        }
                    }
                    if (this.p != null) {
                        this.p.setGeoPoint(geoPoint);
                    }
                    if (this.p != null) {
                        this.p.setRotateAngle(360.0f - this.j);
                    }
                    a(geoPoint);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                gr.b(th, "NaviCarOverlay", "drawLeaderLine(IPoint nowPoint");
            }
        }
    }

    void a(IPoint iPoint) {
        try {
            if (this.k != -1) {
                if (this.f19u != null) {
                    new DPoint();
                    DPoint a = fq.a(iPoint.x, iPoint.y, 20);
                    LatLng latLng = new LatLng(a.y, a.x, false);
                    this.w.clear();
                    this.w.add(latLng);
                    this.w.add(this.f19u);
                    if (this.v == null) {
                        this.v = this.r.addPolyline(new PolylineOptions().add(latLng).add(this.f19u).color(this.k).width(5.0f));
                    } else {
                        this.v.setPoints(this.w);
                    }
                } else if (this.v != null) {
                    this.v.remove();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "NaviCarOverlay", "drawLeaderLine(IPoint nowPoint");
        }
    }

    public void e() {
        if (this.v != null) {
            this.v.remove();
        }
    }

    public void a(int i) {
        if (i == -1 && this.v != null) {
            this.v.remove();
        }
        this.k = i;
    }

    public void f() {
        if (this.o != null) {
            int width = (int) (this.s.getWidth() * this.l.getAnchorX());
            int height = (int) (this.s.getHeight() * this.l.getAnchorY());
            if (this.a) {
                LatLng position = this.p.getPosition();
                this.r.moveCamera(CameraUpdateFactory.changeBearing(this.j));
                this.r.moveCamera(CameraUpdateFactory.changeLatLng(position));
                this.o.setPositionByPixels(width, height);
            }
            if (this.q != null) {
                this.q.setPositionByPixels(width, height);
                if (!this.t || !this.a) {
                    this.q.setVisible(false);
                } else {
                    this.q.setVisible(true);
                }
            }
        }
    }

    public void a(Bitmap bitmap) {
        this.x = bitmap;
        this.m = BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public void b(Bitmap bitmap) {
        this.y = bitmap;
        this.n = BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public void a(float f) {
        this.z = f;
    }

    public void b(int i) {
        this.A = i;
    }
}

package com.amap.api.col;

import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.hyphenate.util.EMPrivateConstant;
import java.nio.FloatBuffer;

/* compiled from: CircleDelegateImp.java */
/* loaded from: classes.dex */
public class bx implements cb {
    private static float o = 4.0075016E7f;
    private static int p = 256;
    private static int q = 20;
    private String h;
    private k i;
    private FloatBuffer j;
    private LatLng a = null;
    private double b = 0.0d;
    private float c = 10.0f;
    private int d = -16777216;
    private int e = 0;
    private float f = 0.0f;
    private boolean g = true;
    private int k = 0;
    private boolean l = false;
    private IPoint m = IPoint.obtain();
    private FPoint n = FPoint.obtain();

    public bx(k kVar) {
        this.i = kVar;
        try {
            this.h = getId();
        } catch (RemoteException e) {
            gr.b(e, "CircleDelegateImp", "create");
            e.printStackTrace();
        }
    }

    @Override // com.amap.api.col.cg
    public boolean a() {
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.i.a(getId());
        this.i.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.h == null) {
            this.h = this.i.c("Circle");
        }
        return this.h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f) throws RemoteException {
        this.f = f;
        this.i.f();
        this.i.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.g = z;
        this.i.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.g;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return 0;
    }

    public boolean b() throws RemoteException {
        this.l = false;
        if (this.a != null) {
            float[] fArr = new float[1086];
            double b = b(this.a.latitude) * this.b;
            GLMapState b2 = this.i.b();
            b2.geo2Map(this.m.x, this.m.y, this.n);
            fArr[0] = this.n.x;
            fArr[1] = this.n.y;
            fArr[2] = 0.0f;
            for (int i = 0; i < 361; i++) {
                double d = (i * 3.141592653589793d) / 180.0d;
                int sin = (int) ((Math.sin(d) * b) + this.m.x);
                int cos = (int) ((Math.cos(d) * b) + this.m.y);
                b2.geo2Map(sin, cos, this.n);
                this.n.x = sin - this.i.getMapConfig().getS_x();
                this.n.y = cos - this.i.getMapConfig().getS_y();
                fArr[(i + 1) * 3] = this.n.x;
                fArr[((i + 1) * 3) + 1] = this.n.y;
                fArr[((i + 1) * 3) + 2] = 0.0f;
            }
            this.k = fArr.length / 3;
            this.j = dt.a(fArr);
        }
        return true;
    }

    @Override // com.amap.api.col.cg
    public void c() throws RemoteException {
        if (this.a != null && this.b > 0.0d && this.g) {
            b();
            if (this.j != null && this.k > 0) {
                dg.a(this.e, this.d, this.j, this.c, this.k, this.i.u(), 0, 0);
            }
            this.l = true;
        }
    }

    void e() {
        this.k = 0;
        if (this.j != null) {
            this.j.clear();
        }
        this.i.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setCenter(LatLng latLng) throws RemoteException {
        if (latLng != null) {
            this.a = latLng;
            GLMapState.lonlat2Geo(latLng.longitude, latLng.latitude, this.m);
            e();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public LatLng getCenter() throws RemoteException {
        return this.a;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setRadius(double d) throws RemoteException {
        this.b = d;
        e();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public double getRadius() throws RemoteException {
        return this.b;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setStrokeWidth(float f) throws RemoteException {
        this.c = f;
        this.i.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public float getStrokeWidth() throws RemoteException {
        return this.c;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setStrokeColor(int i) throws RemoteException {
        this.d = i;
        this.i.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public int getStrokeColor() throws RemoteException {
        return this.d;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public void setFillColor(int i) throws RemoteException {
        this.e = i;
        this.i.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public int getFillColor() throws RemoteException {
        return this.e;
    }

    private float a(double d) {
        return (float) ((Math.cos((3.141592653589793d * d) / 180.0d) * o) / (p << q));
    }

    private double b(double d) {
        return 1.0d / a(d);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            this.a = null;
            if (this.j != null) {
                this.j.clear();
                this.j = null;
            }
        } catch (Throwable th) {
            gr.b(th, "CircleDelegateImp", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
            Log.d("destroy erro", "CircleDelegateImp destroy");
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.ICircle
    public boolean contains(LatLng latLng) throws RemoteException {
        return this.b >= ((double) AMapUtils.calculateLineDistance(this.a, latLng));
    }

    @Override // com.amap.api.col.cg
    public boolean d() {
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }
}

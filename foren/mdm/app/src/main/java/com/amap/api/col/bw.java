package com.amap.api.col;

import android.graphics.Color;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.hyphenate.util.EMPrivateConstant;

/* compiled from: ArcDelegateImp.java */
/* loaded from: classes.dex */
public class bw implements ca {
    float a;
    float b;
    float c;
    float d;
    private LatLng e;
    private LatLng f;
    private LatLng g;
    private String l;
    private k m;
    private float[] n;
    private float h = 10.0f;
    private int i = -16777216;
    private float j = 0.0f;
    private boolean k = true;
    private int o = 0;
    private boolean p = false;
    private double q = 0.0d;
    private double r = 0.0d;
    private double s = 0.0d;

    public bw(k kVar) {
        this.m = kVar;
        try {
            this.l = getId();
        } catch (RemoteException e) {
            gr.b(e, "ArcDelegateImp", "create");
            e.printStackTrace();
        }
    }

    @Override // com.amap.api.col.cg
    public boolean a() {
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.m.a(getId());
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.l == null) {
            this.l = this.m.c("Arc");
        }
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f) throws RemoteException {
        this.j = f;
        this.m.f();
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.j;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.k = z;
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.k;
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
        if (this.e == null || this.f == null || this.g == null || !this.k) {
            return false;
        }
        try {
            this.p = false;
            GLMapState b = this.m.b();
            if (!e()) {
                this.n = new float[r8.length * 3];
                FPoint obtain = FPoint.obtain();
                this.m.a(this.e.latitude, this.e.longitude, obtain);
                FPoint obtain2 = FPoint.obtain();
                this.m.a(this.f.latitude, this.f.longitude, obtain2);
                FPoint obtain3 = FPoint.obtain();
                this.m.a(this.g.latitude, this.g.longitude, obtain3);
                FPoint[] fPointArr = {obtain, obtain2, obtain3};
                for (int i = 0; i < 3; i++) {
                    this.n[i * 3] = fPointArr[i].x;
                    this.n[(i * 3) + 1] = fPointArr[i].y;
                    this.n[(i * 3) + 2] = 0.0f;
                }
                this.o = fPointArr.length;
                return true;
            }
            DPoint f = f();
            int abs = (int) ((Math.abs(this.s - this.r) * 180.0d) / 3.141592653589793d);
            double d = (this.s - this.r) / abs;
            FPoint[] fPointArr2 = new FPoint[abs + 1];
            this.n = new float[fPointArr2.length * 3];
            for (int i2 = 0; i2 <= abs; i2++) {
                if (i2 == abs) {
                    FPoint obtain4 = FPoint.obtain();
                    this.m.a(this.g.latitude, this.g.longitude, obtain4);
                    fPointArr2[i2] = obtain4;
                } else {
                    fPointArr2[i2] = a(b, (i2 * d) + this.r, f.x, f.y);
                }
                fPointArr2[i2] = a(b, (i2 * d) + this.r, f.x, f.y);
                this.n[i2 * 3] = fPointArr2[i2].x;
                this.n[(i2 * 3) + 1] = fPointArr2[i2].y;
                this.n[(i2 * 3) + 2] = 0.0f;
            }
            f.recycle();
            this.o = fPointArr2.length;
            return true;
        } catch (Throwable th) {
            gr.b(th, "ArcDelegateImp", "calMapFPoint");
            th.printStackTrace();
            return false;
        }
    }

    private FPoint a(GLMapState gLMapState, double d, double d2, double d3) {
        double cos = Math.cos(d) * this.q;
        int i = (int) (((-Math.sin(d)) * this.q) + d3);
        FPoint obtain = FPoint.obtain();
        gLMapState.geo2Map((int) (cos + d2), i, obtain);
        return obtain;
    }

    private boolean e() {
        return Math.abs(((this.e.latitude - this.f.latitude) * (this.f.longitude - this.g.longitude)) - ((this.e.longitude - this.f.longitude) * (this.f.latitude - this.g.latitude))) >= 1.0E-6d;
    }

    private DPoint f() {
        IPoint obtain = IPoint.obtain();
        this.m.a(this.e.latitude, this.e.longitude, obtain);
        IPoint obtain2 = IPoint.obtain();
        this.m.a(this.f.latitude, this.f.longitude, obtain2);
        IPoint obtain3 = IPoint.obtain();
        this.m.a(this.g.latitude, this.g.longitude, obtain3);
        double d = obtain.x;
        double d2 = obtain.y;
        double d3 = obtain2.x;
        double d4 = obtain2.y;
        double d5 = obtain3.x;
        double d6 = obtain3.y;
        double d7 = (((d6 - d2) * ((((d4 * d4) - (d2 * d2)) + (d3 * d3)) - (d * d))) + ((d4 - d2) * ((((d2 * d2) - (d6 * d6)) + (d * d)) - (d5 * d5)))) / (((2.0d * (d3 - d)) * (d6 - d2)) - ((2.0d * (d5 - d)) * (d4 - d2)));
        double d8 = (((d5 - d) * ((((d3 * d3) - (d * d)) + (d4 * d4)) - (d2 * d2))) + ((d3 - d) * ((((d * d) - (d5 * d5)) + (d2 * d2)) - (d6 * d6)))) / (((2.0d * (d4 - d2)) * (d5 - d)) - ((2.0d * (d6 - d2)) * (d3 - d)));
        this.q = Math.sqrt(((d - d7) * (d - d7)) + ((d2 - d8) * (d2 - d8)));
        this.r = a(d7, d8, d, d2);
        double a = a(d7, d8, d3, d4);
        this.s = a(d7, d8, d5, d6);
        if (this.r < this.s) {
            if (a <= this.r || a >= this.s) {
                this.s -= 6.283185307179586d;
            }
        } else if (a <= this.s || a >= this.r) {
            this.s += 6.283185307179586d;
        }
        obtain.recycle();
        obtain2.recycle();
        obtain3.recycle();
        return DPoint.obtain(d7, d8);
    }

    private double a(double d, double d2, double d3, double d4) {
        double d5 = (d2 - d4) / this.q;
        if (Math.abs(d5) > 1.0d) {
            d5 = Math.signum(d5);
        }
        double asin = Math.asin(d5);
        if (asin >= 0.0d) {
            if (d3 < d) {
                return 3.141592653589793d - Math.abs(asin);
            }
            return asin;
        } else if (d3 < d) {
            return 3.141592653589793d - asin;
        } else {
            return asin + 6.283185307179586d;
        }
    }

    @Override // com.amap.api.col.cg
    public void c() throws RemoteException {
        if (this.e != null && this.f != null && this.g != null && this.k) {
            b();
            if (this.n != null && this.o > 0) {
                float mapLenWithWin = this.m.b().getMapLenWithWin((int) this.h);
                this.m.b().getMapLenWithWin(1);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.n, this.n.length, mapLenWithWin, this.m.c(), this.b, this.c, this.d, this.a, 0.0f, false, true, false, this.m.u());
            }
            this.p = true;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IArc
    public void setStrokeWidth(float f) throws RemoteException {
        this.h = f;
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IArc
    public float getStrokeWidth() throws RemoteException {
        return this.h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IArc
    public void setStrokeColor(int i) throws RemoteException {
        this.i = i;
        this.a = Color.alpha(i) / 255.0f;
        this.b = Color.red(i) / 255.0f;
        this.c = Color.green(i) / 255.0f;
        this.d = Color.blue(i) / 255.0f;
        this.m.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IArc
    public int getStrokeColor() throws RemoteException {
        return this.i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            this.e = null;
            this.f = null;
            this.g = null;
        } catch (Throwable th) {
            gr.b(th, "ArcDelegateImp", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
            Log.d("destroy erro", "ArcDelegateImp destroy");
        }
    }

    @Override // com.amap.api.col.cg
    public boolean d() {
        return this.p;
    }

    public void a(LatLng latLng) {
        this.e = latLng;
    }

    public void b(LatLng latLng) {
        this.f = latLng;
    }

    public void c(LatLng latLng) {
        this.g = latLng;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }
}

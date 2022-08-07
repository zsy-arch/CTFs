package com.amap.api.col;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.Rectangle;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.hyphenate.util.EMPrivateConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/* compiled from: NavigateArrowDelegateImp.java */
/* loaded from: classes.dex */
public class co implements cf {
    float a;
    float b;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float[] j;
    private k l;
    private String r;
    private float m = 10.0f;
    private int n = -16777216;
    private int o = -16777216;
    private float p = 0.0f;
    private boolean q = true;
    private List<IPoint> s = new Vector();
    private int t = 0;

    /* renamed from: u  reason: collision with root package name */
    private boolean f10u = false;
    private LatLngBounds v = null;
    Rect i = null;
    int k = 0;

    public co(k kVar) {
        this.l = kVar;
        try {
            this.r = getId();
        } catch (RemoteException e) {
            gr.b(e, "NavigateArrowDelegateImp", "create");
            e.printStackTrace();
        }
    }

    void a(List<LatLng> list) throws RemoteException {
        this.s.clear();
        if (this.i == null) {
            this.i = new Rect();
        }
        dt.a(this.i);
        if (list != null) {
            LatLng latLng = null;
            for (LatLng latLng2 : list) {
                if (latLng2 != null && !latLng2.equals(latLng)) {
                    IPoint obtain = IPoint.obtain();
                    this.l.a(latLng2.latitude, latLng2.longitude, obtain);
                    this.s.add(obtain);
                    dt.b(this.i, obtain.x, obtain.y);
                    latLng = latLng2;
                }
            }
        }
        this.t = 0;
        this.i.sort();
        this.l.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.l.a(getId());
        this.l.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.r == null) {
            this.r = this.l.c("NavigateArrow");
        }
        return this.r;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public void setPoints(List<LatLng> list) throws RemoteException {
        a(list);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public List<LatLng> getPoints() throws RemoteException {
        return b();
    }

    private List<LatLng> b() throws RemoteException {
        if (this.s == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (IPoint iPoint : this.s) {
            if (iPoint != null) {
                DPoint obtain = DPoint.obtain();
                this.l.a(iPoint.x, iPoint.y, obtain);
                arrayList.add(new LatLng(obtain.y, obtain.x));
                obtain.recycle();
            }
        }
        return arrayList;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public void setWidth(float f) throws RemoteException {
        this.m = f;
        this.l.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public float getWidth() throws RemoteException {
        return this.m;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public void setTopColor(int i) throws RemoteException {
        this.n = i;
        this.a = Color.alpha(i) / 255.0f;
        this.b = Color.red(i) / 255.0f;
        this.c = Color.green(i) / 255.0f;
        this.d = Color.blue(i) / 255.0f;
        this.l.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public int getTopColor() throws RemoteException {
        return this.n;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public void setSideColor(int i) throws RemoteException {
        this.o = i;
        this.e = Color.alpha(i) / 255.0f;
        this.f = Color.red(i) / 255.0f;
        this.g = Color.green(i) / 255.0f;
        this.h = Color.blue(i) / 255.0f;
        this.l.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.INavigateArrow
    public int getSideColor() throws RemoteException {
        return this.o;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f) throws RemoteException {
        this.p = f;
        this.l.f();
        this.l.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.p;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.q = z;
        this.l.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.q;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    @Override // com.amap.api.col.cg
    public boolean a() {
        Rectangle geoRectangle;
        if (this.i == null || (geoRectangle = this.l.getMapConfig().getGeoRectangle()) == null || !geoRectangle.isOverlap(this.i)) {
            return false;
        }
        return true;
    }

    public boolean a(MapConfig mapConfig) throws RemoteException {
        int i = 0;
        int s_x = mapConfig.getS_x();
        int s_y = mapConfig.getS_y();
        this.f10u = false;
        int size = this.s.size();
        if (this.j == null || this.j.length < size * 3) {
            this.j = new float[size * 3];
        }
        this.k = size * 3;
        for (IPoint iPoint : this.s) {
            this.j[i * 3] = iPoint.x - s_x;
            this.j[(i * 3) + 1] = iPoint.y - s_y;
            this.j[(i * 3) + 2] = 0.0f;
            i++;
        }
        this.t = this.s.size();
        return true;
    }

    @Override // com.amap.api.col.cg
    public void c() throws RemoteException {
        if (this.s != null && this.s.size() != 0 && this.m > 0.0f) {
            a(this.l.getMapConfig());
            if (this.j != null && this.t > 0) {
                AMapNativeRenderer.nativeDrawLineByTextureID(this.j, this.k, this.l.b().getMapLenWithWin((int) this.m), this.l.c(), this.b, this.c, this.d, this.a, 0.0f, false, true, true, this.l.u());
            }
            this.f10u = true;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            if (this.j != null) {
                this.j = null;
            }
        } catch (Throwable th) {
            gr.b(th, "NavigateArrowDelegateImp", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
            Log.d("destroy erro", "NavigateArrowDelegateImp destroy");
        }
    }

    @Override // com.amap.api.col.cg
    public boolean d() {
        return this.f10u;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }
}

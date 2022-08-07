package com.amap.api.col;

import android.graphics.Rect;
import android.os.RemoteException;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.FPointBounds;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.Rectangle;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.hyphenate.util.EMPrivateConstant;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/* compiled from: PolygonDelegateImp.java */
/* loaded from: classes.dex */
public class cp implements ci {
    private static float w = 1.0E10f;
    private k c;
    private String f;
    private float g;
    private int h;
    private int i;
    private List<LatLng> j;
    private FloatBuffer m;
    private FloatBuffer n;
    private float d = 0.0f;
    private boolean e = true;
    private List<IPoint> k = new Vector();
    private List<FPoint> l = new ArrayList();
    private int o = 0;
    private int p = 0;
    private LatLngBounds q = null;
    private boolean r = false;
    private float s = 0.0f;
    private List<IPoint> t = new ArrayList();

    /* renamed from: u  reason: collision with root package name */
    private Object f11u = new Object();
    FPointBounds a = null;
    Rect b = null;
    private float v = 0.0f;

    public cp(k kVar) {
        this.c = kVar;
        try {
            this.f = getId();
        } catch (RemoteException e) {
            gr.b(e, "PolygonDelegateImp", "create");
            e.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.c.a(getId());
        this.c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.f == null) {
            this.f = this.c.c("Polygon");
        }
        return this.f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public void setPoints(List<LatLng> list) throws RemoteException {
        synchronized (this.f11u) {
            this.j = list;
            a(list);
            this.c.setRunLowFrame(false);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public List<LatLng> getPoints() throws RemoteException {
        return this.j;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f) throws RemoteException {
        this.d = f;
        this.c.f();
        this.c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.d;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.e = z;
        this.c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.e;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean equalsRemote(IOverlay iOverlay) throws RemoteException {
        return equals(iOverlay) || iOverlay.getId().equals(getId());
    }

    void a(List<LatLng> list) throws RemoteException {
        LatLngBounds.Builder builder = LatLngBounds.builder();
        if (this.b == null) {
            this.b = new Rect();
        }
        dt.a(this.b);
        this.k.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint obtain = IPoint.obtain();
                    this.c.a(latLng.latitude, latLng.longitude, obtain);
                    this.k.add(obtain);
                    dt.b(this.b, obtain.x, obtain.y);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
            int size = this.k.size();
            if (size > 1) {
                IPoint iPoint = this.k.get(0);
                IPoint iPoint2 = this.k.get(size - 1);
                if (iPoint.x == iPoint2.x && iPoint.y == iPoint2.y) {
                    this.k.remove(size - 1);
                }
            }
        }
        this.b.sort();
        this.q = builder.build();
        if (this.m != null) {
            this.m.clear();
        }
        if (this.n != null) {
            this.n.clear();
        }
        if (dt.a(this.k, 0, this.k.size())) {
            Collections.reverse(this.k);
        }
        this.o = 0;
        this.p = 0;
        this.c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    @Override // com.amap.api.col.cg
    public boolean a() {
        return this.c.getMapConfig().getGeoRectangle().isOverlap(this.b);
    }

    @Override // com.amap.api.col.cg
    public void c() throws RemoteException {
        if (this.k != null && this.k.size() != 0) {
            MapConfig mapConfig = this.c.getMapConfig();
            Rectangle geoRectangle = mapConfig.getGeoRectangle();
            IPoint[] clipRect = geoRectangle.getClipRect();
            List<IPoint> list = this.k;
            if (a(geoRectangle)) {
                synchronized (this.f11u) {
                    list = dt.a(clipRect, this.k, true);
                }
            }
            if (list.size() > 2) {
                a(list, mapConfig.getS_x(), mapConfig.getS_y());
                if (this.m != null && this.n != null && this.o > 0 && this.p > 0) {
                    dg.a(this.h, this.i, this.m, this.g, this.n, this.o, this.p, this.c.u());
                }
            }
            this.r = true;
        }
    }

    private boolean a(Rectangle rectangle) {
        this.v = this.c.g();
        b();
        if (this.v <= 10.0f || rectangle == null) {
            return false;
        }
        try {
            if (!rectangle.contains(this.b)) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    private void a(List<IPoint> list, int i, int i2) throws RemoteException {
        b();
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size >= 2) {
            IPoint iPoint = list.get(0);
            arrayList.add(iPoint);
            for (int i3 = 1; i3 < size - 1; i3++) {
                iPoint = list.get(i3);
                if (a(iPoint, iPoint)) {
                    arrayList.add(iPoint);
                } else {
                    iPoint = iPoint;
                }
            }
            arrayList.add(list.get(size - 1));
            float[] fArr = new float[arrayList.size() * 3];
            IPoint[] iPointArr = new IPoint[arrayList.size()];
            Iterator it = arrayList.iterator();
            int i4 = 0;
            while (it.hasNext()) {
                IPoint iPoint2 = (IPoint) it.next();
                fArr[i4 * 3] = iPoint2.x - i;
                fArr[(i4 * 3) + 1] = iPoint2.y - i2;
                fArr[(i4 * 3) + 2] = 0.0f;
                iPointArr[i4] = iPoint2;
                i4++;
            }
            IPoint[] a = a(iPointArr);
            if (a.length == 0) {
                if (w == 1.0E10f) {
                    w = 1.0E8f;
                } else {
                    w = 1.0E10f;
                }
                a = a(iPointArr);
            }
            float[] fArr2 = new float[a.length * 3];
            int i5 = 0;
            for (IPoint iPoint3 : a) {
                fArr2[i5 * 3] = iPoint3.x - i;
                fArr2[(i5 * 3) + 1] = iPoint3.y - i2;
                fArr2[(i5 * 3) + 2] = 0.0f;
                i5++;
            }
            this.o = iPointArr.length;
            this.p = a.length;
            this.m = dt.a(fArr);
            this.n = dt.a(fArr2);
        }
    }

    private boolean a(IPoint iPoint, IPoint iPoint2) {
        return ((float) (iPoint2.x - iPoint.x)) >= this.s || ((float) (iPoint2.x - iPoint.x)) <= (-this.s) || ((float) (iPoint2.y - iPoint.y)) >= this.s || ((float) (iPoint2.y - iPoint.y)) <= (-this.s);
    }

    private void b() {
        float g = this.c.g();
        if (this.k.size() <= 5000 || g > 12.0f) {
            this.s = this.c.b().getMapLenWithWin(10);
            return;
        }
        float f = (g / 2.0f) + (this.g / 2.0f);
        if (f > 200.0f) {
            f = 200.0f;
        }
        this.s = this.c.b().getMapLenWithWin((int) f);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public void setStrokeWidth(float f) throws RemoteException {
        this.g = f;
        this.c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public float getStrokeWidth() throws RemoteException {
        return this.g;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public void setFillColor(int i) throws RemoteException {
        this.h = i;
        this.c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public int getFillColor() throws RemoteException {
        return this.h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public void setStrokeColor(int i) throws RemoteException {
        this.i = i;
        this.c.setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public int getStrokeColor() throws RemoteException {
        return this.i;
    }

    static IPoint[] a(IPoint[] iPointArr) {
        int length = iPointArr.length;
        float[] fArr = new float[length * 2];
        for (int i = 0; i < length; i++) {
            fArr[i * 2] = iPointArr[i].x * w;
            fArr[(i * 2) + 1] = iPointArr[i].y * w;
        }
        ds a = new df().a(fArr);
        int i2 = a.b;
        IPoint[] iPointArr2 = new IPoint[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iPointArr2[i3] = new IPoint();
            iPointArr2[i3].x = (int) (fArr[a.a(i3) * 2] / w);
            iPointArr2[i3].y = (int) (fArr[(a.a(i3) * 2) + 1] / w);
        }
        return iPointArr2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            if (this.m != null) {
                this.m.clear();
                this.m = null;
            }
            if (this.n != null) {
                this.n = null;
            }
        } catch (Throwable th) {
            gr.b(th, "PolygonDelegateImp", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolygon
    public boolean contains(LatLng latLng) throws RemoteException {
        try {
            return dt.a(latLng, getPoints());
        } catch (Throwable th) {
            gr.b(th, "PolygonDelegateImp", "contains");
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.amap.api.col.cg
    public boolean d() {
        return this.r;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
    }
}

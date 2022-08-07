package com.amap.api.col;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.PolylineOptions;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.FPoint3;
import com.autonavi.amap.mapcore.FPointBounds;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.Rectangle;
import com.autonavi.amap.mapcore.interfaces.IOverlay;
import com.hyphenate.util.EMPrivateConstant;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: PolylineDelegateImp.java */
/* loaded from: classes.dex */
public class cq implements cj {
    private float K;
    private float L;
    private float M;
    private float N;
    private float[] Q;
    private int[] R;
    private int[] S;
    private PolylineOptions X;
    private i f;
    private String g;
    private FloatBuffer q;
    private List<IPoint> h = new ArrayList();
    private List<FPoint> i = new ArrayList();
    private List<LatLng> j = new ArrayList();
    private List<BitmapDescriptor> k = new ArrayList();
    private List<r> l = new ArrayList();
    private List<Integer> m = new ArrayList();
    private List<Integer> n = new ArrayList();
    private List<Integer> o = new ArrayList();
    private List<Integer> p = new ArrayList();
    private BitmapDescriptor r = null;
    private LatLngBounds s = null;
    private Object t = new Object();

    /* renamed from: u  reason: collision with root package name */
    private boolean f12u = true;
    private boolean v = true;
    private boolean w = false;
    private boolean x = false;
    private boolean y = false;
    private boolean z = true;
    private boolean A = false;
    private boolean B = false;
    private boolean C = true;
    private int D = 0;
    private int E = 0;
    private int F = -16777216;
    private int G = 0;
    private float H = 10.0f;
    private float I = 0.0f;
    private float J = 0.0f;
    private float O = 1.0f;
    private float P = 0.0f;
    private double T = 5.0d;
    private boolean U = false;
    private final int V = 2;
    private FPointBounds W = null;
    Rect a = null;
    private int Y = 0;
    private int Z = 2;
    int b = 0;
    int c = 0;
    ArrayList<FPoint> d = new ArrayList<>();
    long e = 0;

    public void a(boolean z) {
        this.C = z;
        this.f.d().setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setGeodesic(boolean z) throws RemoteException {
        this.w = z;
        this.f.d().setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public boolean isGeodesic() {
        return this.w;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setDottedLine(boolean z) {
        if (this.D == 2 || this.D == 0) {
            this.x = z;
            if (z && this.v) {
                this.D = 2;
            }
            this.f.d().setRunLowFrame(false);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public boolean isDottedLine() {
        return this.x;
    }

    public cq(i iVar, PolylineOptions polylineOptions) {
        this.f = iVar;
        setOptions(polylineOptions);
        try {
            this.g = getId();
        } catch (RemoteException e) {
            gr.b(e, "PolylineDelegateImp", "create");
            e.printStackTrace();
        }
    }

    void a(List<LatLng> list) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        LatLngBounds.Builder builder = LatLngBounds.builder();
        if (list != null) {
            LatLng latLng = null;
            Iterator<LatLng> it = list.iterator();
            while (it.hasNext()) {
                latLng = it.next();
                if (!this.w) {
                    IPoint obtain = IPoint.obtain();
                    this.f.d().a(latLng.latitude, latLng.longitude, obtain);
                    arrayList.add(obtain);
                    builder.include(latLng);
                } else if (latLng != null) {
                    if (Math.abs(latLng.longitude - latLng.longitude) < 0.01d) {
                        IPoint obtain2 = IPoint.obtain();
                        this.f.d().a(latLng.latitude, latLng.longitude, obtain2);
                        arrayList.add(obtain2);
                        builder.include(latLng);
                        IPoint obtain3 = IPoint.obtain();
                        this.f.d().a(latLng.latitude, latLng.longitude, obtain3);
                        arrayList.add(obtain3);
                        builder.include(latLng);
                    } else {
                        a(latLng, latLng, arrayList, builder);
                    }
                }
            }
        }
        this.h = arrayList;
        this.G = 0;
        if (this.h.size() > 0) {
            this.s = builder.build();
        }
        if (this.a == null) {
            this.a = new Rect();
        }
        dt.a(this.a);
        for (IPoint iPoint : this.h) {
            dt.b(this.a, iPoint.x, iPoint.y);
        }
        this.a.sort();
        this.f.d().setRunLowFrame(false);
    }

    IPoint a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, double d, int i) {
        IPoint obtain = IPoint.obtain();
        double d2 = iPoint2.x - iPoint.x;
        double d3 = iPoint2.y - iPoint.y;
        obtain.y = (int) (((i * d) / Math.sqrt(((d3 * d3) / (d2 * d2)) + 1.0d)) + iPoint3.y);
        obtain.x = (int) (((d3 * (iPoint3.y - obtain.y)) / d2) + iPoint3.x);
        return obtain;
    }

    void a(List<IPoint> list, List<IPoint> list2, double d) {
        if (list.size() == 3) {
            for (int i = 0; i <= 10; i = (int) (i + 1.0f)) {
                float f = i / 10.0f;
                IPoint obtain = IPoint.obtain();
                double d2 = ((1.0d - f) * (1.0d - f) * list.get(0).x) + (2.0f * f * (1.0d - f) * list.get(1).x * d) + (list.get(2).x * f * f);
                double d3 = ((1.0d - f) * (1.0d - f) * list.get(0).y) + (2.0f * f * (1.0d - f) * list.get(1).y * d) + (list.get(2).y * f * f);
                obtain.x = (int) (d2 / ((((1.0d - f) * (1.0d - f)) + (((2.0f * f) * (1.0d - f)) * d)) + (f * f)));
                obtain.y = (int) (d3 / ((((1.0d - f) * (1.0d - f)) + (((2.0f * f) * (1.0d - f)) * d)) + (f * f)));
                list2.add(obtain);
            }
        }
    }

    void a(LatLng latLng, LatLng latLng2, List<IPoint> list, LatLngBounds.Builder builder) {
        double abs = (Math.abs(latLng.longitude - latLng2.longitude) * 3.141592653589793d) / 180.0d;
        LatLng latLng3 = new LatLng((latLng2.latitude + latLng.latitude) / 2.0d, (latLng2.longitude + latLng.longitude) / 2.0d, false);
        builder.include(latLng).include(latLng3).include(latLng2);
        int i = latLng3.latitude > 0.0d ? -1 : 1;
        IPoint obtain = IPoint.obtain();
        this.f.d().a(latLng.latitude, latLng.longitude, obtain);
        IPoint obtain2 = IPoint.obtain();
        this.f.d().a(latLng2.latitude, latLng2.longitude, obtain2);
        IPoint obtain3 = IPoint.obtain();
        this.f.d().a(latLng3.latitude, latLng3.longitude, obtain3);
        double cos = Math.cos(0.5d * abs);
        IPoint a = a(obtain, obtain2, obtain3, Math.hypot(obtain.x - obtain2.x, obtain.y - obtain2.y) * 0.5d * Math.tan(0.5d * abs), i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(obtain);
        arrayList.add(a);
        arrayList.add(obtain2);
        a(arrayList, list, cos);
        obtain.recycle();
        a.recycle();
        obtain2.recycle();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void remove() throws RemoteException {
        this.f.d(getId());
        setVisible(false);
        this.f.d().setRunLowFrame(false);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public String getId() throws RemoteException {
        if (this.g == null) {
            this.g = this.f.a("Polyline");
        }
        return this.g;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setPoints(List<LatLng> list) throws RemoteException {
        try {
            this.j = list;
            synchronized (this.t) {
                a(list);
            }
            this.z = true;
            this.f.d().setRunLowFrame(false);
            this.X.setPoints(list);
        } catch (Throwable th) {
            gr.b(th, "PolylineDelegateImp", "setPoints");
            this.h.clear();
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public List<LatLng> getPoints() throws RemoteException {
        return this.j;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setWidth(float f) throws RemoteException {
        this.H = f;
        this.f.d().setRunLowFrame(false);
        this.X.width(f);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public float getWidth() throws RemoteException {
        return this.H;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setColor(int i) {
        if (this.D == 0 || this.D == 2) {
            this.F = i;
            this.K = Color.alpha(i) / 255.0f;
            this.L = Color.red(i) / 255.0f;
            this.M = Color.green(i) / 255.0f;
            this.N = Color.blue(i) / 255.0f;
            if (this.v) {
                if (this.x) {
                    this.D = 2;
                } else {
                    this.D = 0;
                }
            }
            this.f.d().setRunLowFrame(false);
        }
        this.X.color(i);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public int getColor() throws RemoteException {
        return this.F;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setZIndex(float f) throws RemoteException {
        this.I = f;
        this.f.c();
        this.f.d().setRunLowFrame(false);
        this.X.zIndex(f);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public float getZIndex() throws RemoteException {
        return this.I;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setVisible(boolean z) throws RemoteException {
        this.f12u = z;
        this.f.d().setRunLowFrame(false);
        this.X.visible(z);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isVisible() throws RemoteException {
        return this.f12u;
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
        Rectangle geoRectangle = this.f.d().getMapConfig().getGeoRectangle();
        if (this.a == null || geoRectangle == null || geoRectangle.isOverlap(this.a)) {
            return true;
        }
        return false;
    }

    public boolean b() throws RemoteException {
        synchronized (this.t) {
            FPointBounds.Builder builder = new FPointBounds.Builder();
            this.i.clear();
            this.B = false;
            this.Q = new float[this.h.size() * 3];
            this.b = this.Q.length;
            int i = 0;
            for (IPoint iPoint : this.h) {
                FPoint3 fPoint3 = new FPoint3();
                this.f.d().a(iPoint.y, iPoint.x, (FPoint) fPoint3);
                this.Q[i * 3] = fPoint3.x;
                this.Q[(i * 3) + 1] = fPoint3.y;
                this.Q[(i * 3) + 2] = 0.0f;
                if (this.m != null && this.m.size() > i) {
                    fPoint3.setColorIndex(this.m.get(i).intValue());
                } else if (this.n != null && this.n.size() > i) {
                    fPoint3.setColorIndex(this.n.get(i).intValue());
                }
                this.i.add(fPoint3);
                builder.include(fPoint3);
                i++;
            }
            this.W = builder.build();
        }
        if (!this.C) {
            this.q = dt.a(this.Q);
        }
        this.G = this.h.size();
        f();
        return true;
    }

    private void f() {
        if (this.G <= 5000 || this.J > 12.0f) {
            this.P = this.f.d().b().getMapLenWithWin(10);
            return;
        }
        float f = (this.H / 2.0f) + (this.J / 2.0f);
        if (f > 200.0f) {
            f = 200.0f;
        }
        this.P = this.f.d().b().getMapLenWithWin((int) f);
    }

    private void e(List<FPoint> list) throws RemoteException {
        int i = 0;
        this.d.clear();
        int size = list.size();
        if (size >= 2) {
            FPoint fPoint = list.get(0);
            this.d.add(fPoint);
            for (int i2 = 1; i2 < size - 1; i2++) {
                fPoint = list.get(i2);
                if (i2 == 1 || a(fPoint, fPoint)) {
                    this.d.add(fPoint);
                } else {
                    this.d.remove(this.d.size() - 1);
                    this.d.add(fPoint);
                    fPoint = fPoint;
                }
            }
            this.d.add(list.get(size - 1));
            int size2 = this.d.size() * 3;
            this.b = size2;
            if (this.Q == null || this.Q.length < this.b) {
                this.Q = new float[size2];
            }
            if (this.D == 5 || this.D == 3) {
                int[] iArr = new int[this.d.size()];
                ArrayList arrayList = new ArrayList();
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < size2 / 3; i5++) {
                    FPoint3 fPoint3 = (FPoint3) this.d.get(i5);
                    this.Q[i5 * 3] = fPoint3.x;
                    this.Q[(i5 * 3) + 1] = fPoint3.y;
                    this.Q[(i5 * 3) + 2] = 0.0f;
                    i4 = fPoint3.colorIndex;
                    if (i5 == 0) {
                        arrayList.add(Integer.valueOf(i4));
                    } else if (i4 != i4) {
                        if (i4 != -1) {
                            i4 = i4;
                        }
                        arrayList.add(Integer.valueOf(i4));
                        i4 = i4;
                    }
                    iArr[i3] = i5;
                    i3++;
                }
                this.R = new int[arrayList.size()];
                System.arraycopy(iArr, 0, this.R, 0, this.R.length);
                this.o = arrayList;
                this.p = arrayList;
                return;
            }
            Iterator<FPoint> it = this.d.iterator();
            while (it.hasNext()) {
                FPoint next = it.next();
                this.Q[i * 3] = next.x;
                this.Q[(i * 3) + 1] = next.y;
                this.Q[(i * 3) + 2] = 0.0f;
                i++;
            }
        }
    }

    private boolean a(FPoint fPoint, FPoint fPoint2) {
        return Math.abs(fPoint2.x - fPoint.x) >= this.P || Math.abs(fPoint2.y - fPoint.y) >= this.P;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setCustomTexture(BitmapDescriptor bitmapDescriptor) {
        long nanoTime = System.nanoTime();
        if (nanoTime - this.e >= 16) {
            this.e = nanoTime;
            if (bitmapDescriptor != null) {
                synchronized (this) {
                    if (!(this.r == null || bitmapDescriptor == null || !this.r.equals(bitmapDescriptor))) {
                        this.r.recycle();
                    }
                    this.v = false;
                    this.y = false;
                    this.D = 1;
                    this.r = bitmapDescriptor;
                    this.f.d().setRunLowFrame(false);
                    this.X.setCustomTexture(bitmapDescriptor);
                }
            }
        }
    }

    @Override // com.amap.api.col.cg
    public void c() throws RemoteException {
        if (!(this.h == null || this.h.size() == 0 || this.H <= 0.0f || this.f.d() == null)) {
            synchronized (this.t) {
                int s_x = this.f.d().getMapConfig().getS_x();
                int s_y = this.f.d().getMapConfig().getS_y();
                if (this.i.size() == this.h.size()) {
                    int i = 0;
                    for (IPoint iPoint : this.h) {
                        this.i.get(i).x = iPoint.x - s_x;
                        this.i.get(i).y = iPoint.y - s_y;
                        i++;
                    }
                } else {
                    this.i.clear();
                    int i2 = 0;
                    for (IPoint iPoint2 : this.h) {
                        FPoint3 fPoint3 = new FPoint3();
                        if (this.m != null && this.m.size() > i2) {
                            fPoint3.setColorIndex(this.m.get(i2).intValue());
                        }
                        fPoint3.x = iPoint2.x - s_x;
                        fPoint3.y = iPoint2.y - s_y;
                        this.i.add(fPoint3);
                        i2++;
                    }
                }
            }
            if (this.z) {
                b();
                this.z = false;
            }
            if (this.Q != null && this.G > 0) {
                if (this.C) {
                    a(this.f.d().getMapConfig());
                } else {
                    if (this.q == null) {
                        this.q = dt.a(this.Q);
                    }
                    dg.a(this.F, this.q, this.H, this.G, this.f.e());
                }
            }
            this.B = true;
        }
    }

    private void a(MapConfig mapConfig) {
        float mapLenWithWin = this.f.d().b().getMapLenWithWin((int) this.H);
        switch (this.D) {
            case 0:
                f(mapLenWithWin, mapConfig);
                return;
            case 1:
                d(mapLenWithWin, mapConfig);
                return;
            case 2:
                e(mapLenWithWin, mapConfig);
                return;
            case 3:
                c(mapLenWithWin, mapConfig);
                return;
            case 4:
                b(mapLenWithWin, mapConfig);
                return;
            case 5:
                a(mapLenWithWin, mapConfig);
                return;
            default:
                return;
        }
    }

    private void a(float f, MapConfig mapConfig) {
        if (!this.y) {
            try {
                if (this.k != null) {
                    this.S = new int[this.k.size()];
                    boolean z = Build.VERSION.SDK_INT >= 12;
                    int i = 0;
                    for (BitmapDescriptor bitmapDescriptor : this.k) {
                        this.S[i] = a(z, bitmapDescriptor);
                        i++;
                    }
                    this.y = true;
                }
            } catch (Throwable th) {
                gr.b(th, "MarkerDelegateImp", "loadtexture");
                return;
            }
        }
        FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
        try {
            List<FPoint> list = this.i;
            if (a(clipMapRect)) {
                synchronized (this.t) {
                    list = dt.b(clipMapRect, this.i, false);
                }
            }
            if (list.size() >= 2) {
                e(list);
                int[] iArr = new int[this.o.size()];
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    iArr[i2] = this.S[this.o.get(i2).intValue()];
                }
                if ((this.R != null) && (iArr != null)) {
                    AMapNativeRenderer.nativeDrawLineByMultiTextureID(this.Q, this.b, f, iArr, iArr.length, this.R, this.R.length, 1.0f - this.O, this.f.e());
                }
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    private int a(boolean z, BitmapDescriptor bitmapDescriptor) {
        r rVar = null;
        if (z && (rVar = this.f.d().a(bitmapDescriptor)) != null) {
            return rVar.f();
        }
        if (rVar == null) {
            rVar = new r(bitmapDescriptor, 0);
        }
        Bitmap bitmap = bitmapDescriptor.getBitmap();
        if (bitmap == null || bitmap.isRecycled()) {
            return 0;
        }
        int g = g();
        if (z) {
            this.f.d().a(rVar);
        }
        this.l.add(rVar);
        dt.b(g, bitmap, true);
        return g;
    }

    private void b(float f, MapConfig mapConfig) {
        int[] iArr = new int[this.n.size()];
        for (int i = 0; i < this.n.size(); i++) {
            iArr[i] = this.n.get(i).intValue();
        }
        try {
            b();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        AMapNativeRenderer.nativeDrawGradientColorLine(this.Q, this.Q.length, f, iArr, this.n.size(), this.R, this.R.length, this.f.d().c(), this.f.e());
    }

    private void c(float f, MapConfig mapConfig) {
        boolean z = false;
        int[] iArr = new int[this.n.size()];
        for (int i = 0; i < this.n.size(); i++) {
            iArr[i] = this.n.get(i).intValue();
        }
        FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
        try {
            List<FPoint> list = this.i;
            if (a(clipMapRect)) {
                synchronized (this.t) {
                    list = dt.b(clipMapRect, this.i, false);
                }
            }
            if (list.size() >= 2) {
                e(list);
                int[] iArr2 = new int[this.p.size()];
                for (int i2 = 0; i2 < iArr2.length; i2++) {
                    iArr2[i2] = this.p.get(i2).intValue();
                }
                boolean z2 = iArr2 != null;
                if (this.R != null) {
                    z = true;
                }
                if (z2 && z) {
                    AMapNativeRenderer.nativeDrawLineByMultiColor(this.Q, this.b, f, this.f.d().c(), iArr2, iArr2.length, this.R, this.R.length, this.f.e());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void d(float f, MapConfig mapConfig) {
        boolean z = true;
        z = false;
        if (!this.y) {
            synchronized (this) {
                if (this.r != null) {
                    if (Build.VERSION.SDK_INT < 12) {
                    }
                    this.E = a(z, this.r);
                    this.y = true;
                }
            }
        }
        try {
            if (mapConfig.getChangeRatio() == 1.0d && this.Q != null) {
                this.Y++;
                if (this.Y > this.Z) {
                    AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.b, f, this.E, this.L, this.M, this.N, this.K, 1.0f - this.O, false, false, false, this.f.e());
                    return;
                }
            }
            this.Y = 0;
            FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
            List<FPoint> list = this.i;
            if (a(clipMapRect)) {
                synchronized (this.t) {
                    list = dt.a(clipMapRect, this.i, false);
                }
            }
            if (list.size() >= 2) {
                e(list);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.b, f, this.E, this.L, this.M, this.N, this.K, 1.0f - this.O, false, false, false, this.f.e());
            }
        } catch (Throwable th) {
        }
    }

    private void e(float f, MapConfig mapConfig) {
        try {
            b();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.Q.length, f, this.f.d().d(), this.L, this.M, this.N, this.K, 0.0f, true, true, false, this.f.e());
    }

    private void f(float f, MapConfig mapConfig) {
        try {
            List<FPoint> list = this.i;
            if (this.f.d() != null) {
                if (mapConfig.getChangeRatio() == 1.0d && this.Q != null) {
                    this.Y++;
                    if (this.Y > this.Z) {
                        AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.b, f, this.f.d().c(), this.L, this.M, this.N, this.K, 0.0f, false, true, false, this.f.e());
                        return;
                    }
                }
                this.Y = 0;
                FPoint[] clipMapRect = mapConfig.getGeoRectangle().getClipMapRect();
                if (a(clipMapRect)) {
                    synchronized (this.t) {
                        list = dt.a(clipMapRect, this.i, false);
                    }
                }
                if (list.size() >= 2) {
                    e(list);
                    AMapNativeRenderer.nativeDrawLineByTextureID(this.Q, this.b, f, this.f.d().c(), this.L, this.M, this.N, this.K, 0.0f, false, true, false, this.f.e());
                }
            }
        } catch (Throwable th) {
        }
    }

    private int g() {
        int[] iArr = {0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    private boolean a(FPoint[] fPointArr) {
        this.J = this.f.d().g();
        f();
        if (this.J <= 10.0f) {
            return false;
        }
        try {
            if (this.f.d() == null) {
                return false;
            }
            if (dt.a(this.W.northeast, fPointArr)) {
                if (dt.a(this.W.southwest, fPointArr)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void destroy() {
        try {
            remove();
            if (this.l != null && this.l.size() > 0) {
                for (int i = 0; i < this.l.size(); i++) {
                    r rVar = this.l.get(i);
                    if (rVar != null) {
                        this.f.a(Integer.valueOf(rVar.f()));
                        this.f.d().b(rVar.j());
                    }
                }
            }
            if (this.Q != null) {
                this.Q = null;
            }
            if (this.q != null) {
                this.q.clear();
                this.q = null;
            }
            if (this.k != null && this.k.size() > 0) {
                for (BitmapDescriptor bitmapDescriptor : this.k) {
                    bitmapDescriptor.recycle();
                }
            }
            if (this.r != null) {
                this.r.recycle();
            }
            if (this.n != null) {
                this.n.clear();
                this.n = null;
            }
            if (this.m != null) {
                this.m.clear();
                this.m = null;
            }
            if (this.j != null) {
                this.j.clear();
                this.j = null;
            }
            this.X = null;
        } catch (Throwable th) {
            gr.b(th, "PolylineDelegateImp", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
            Log.d("destroy erro", "PolylineDelegateImp destroy");
        }
    }

    @Override // com.amap.api.col.cg
    public boolean d() {
        return this.B;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public LatLng getNearestLatLng(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        if (this.j == null || this.j.size() == 0) {
            return null;
        }
        float f = 0.0f;
        int i = 0;
        for (int i2 = 0; i2 < this.j.size(); i2++) {
            try {
                if (i2 == 0) {
                    f = AMapUtils.calculateLineDistance(latLng, this.j.get(i2));
                    i = i;
                } else {
                    f = AMapUtils.calculateLineDistance(latLng, this.j.get(i2));
                    if (f > f) {
                        i = i2;
                    } else {
                        f = f;
                        i = i;
                    }
                }
            } catch (Throwable th) {
                gr.b(th, "PolylineDelegateImp", "getNearestLatLng");
                th.printStackTrace();
                return null;
            }
        }
        return this.j.get(i);
    }

    @Override // com.amap.api.col.cj
    public boolean a(LatLng latLng) {
        float[] fArr = new float[this.Q.length];
        System.arraycopy(this.Q, 0, fArr, 0, this.Q.length);
        if (fArr.length / 3 < 2) {
            return false;
        }
        try {
            ArrayList<FPoint> h = h();
            if (h != null) {
                if (h.size() >= 1) {
                    double mapLenWithWin = this.f.d().b().getMapLenWithWin(((int) this.H) / 4);
                    double mapLenWithWin2 = this.f.d().b().getMapLenWithWin((int) this.T);
                    FPoint b = b(latLng);
                    FPoint fPoint = null;
                    for (int i = 0; i < h.size() - 1; i++) {
                        if (i == 0) {
                            fPoint = h.get(i);
                        }
                        fPoint = h.get(i + 1);
                        if ((mapLenWithWin2 + mapLenWithWin) - a(b, fPoint, fPoint) >= 0.0d) {
                            h.clear();
                            return true;
                        }
                    }
                    h.clear();
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private ArrayList<FPoint> h() {
        ArrayList<FPoint> arrayList = new ArrayList<>();
        int i = 0;
        while (i < this.Q.length) {
            float f = this.Q[i];
            int i2 = i + 1;
            arrayList.add(FPoint.obtain(f, this.Q[i2]));
            i = i2 + 1 + 1;
        }
        return arrayList;
    }

    private double a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        return a(fPoint.x, fPoint.y, fPoint2.x, fPoint2.y, fPoint3.x, fPoint3.y);
    }

    private FPoint b(LatLng latLng) {
        IPoint obtain = IPoint.obtain();
        this.f.d().a(latLng.latitude, latLng.longitude, obtain);
        FPoint obtain2 = FPoint.obtain();
        this.f.d().a(obtain.y, obtain.x, obtain2);
        obtain.recycle();
        return obtain2;
    }

    private double a(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = ((d5 - d3) * (d - d3)) + ((d6 - d4) * (d2 - d4));
        if (d7 <= 0.0d) {
            return Math.sqrt(((d - d3) * (d - d3)) + ((d2 - d4) * (d2 - d4)));
        }
        double d8 = ((d5 - d3) * (d5 - d3)) + ((d6 - d4) * (d6 - d4));
        if (d7 >= d8) {
            return Math.sqrt(((d - d5) * (d - d5)) + ((d2 - d6) * (d2 - d6)));
        }
        double d9 = d7 / d8;
        double d10 = ((d5 - d3) * d9) + d3;
        double d11 = (d9 * (d6 - d4)) + d4;
        return Math.sqrt(((d11 - d2) * (d11 - d2)) + ((d - d10) * (d - d10)));
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setTransparency(float f) {
        this.O = f;
        this.f.d().setRunLowFrame(false);
    }

    public void b(List<BitmapDescriptor> list) {
        if (list != null && list.size() != 0) {
            if (list.size() > 1) {
                this.v = false;
                this.D = 5;
                this.k = list;
                this.f.d().setRunLowFrame(false);
                return;
            }
            setCustomTexture(list.get(0));
        }
    }

    public void c(List<Integer> list) {
        if (list != null && list.size() != 0) {
            this.m = list;
            this.o = f(list);
        }
    }

    public void d(List<Integer> list) {
        if (list != null && list.size() != 0) {
            this.n = list;
            if (list.size() > 1) {
                this.v = false;
                this.p = f(list);
                this.D = 3;
                this.f.d().setRunLowFrame(false);
                return;
            }
            setColor(list.get(0).intValue());
        }
    }

    public void b(boolean z) {
        if (z && this.n != null && this.n.size() > 1) {
            this.A = z;
            this.D = 4;
            this.f.d().setRunLowFrame(false);
        }
    }

    private List<Integer> f(List<Integer> list) {
        int[] iArr = new int[list.size()];
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int intValue = list.get(i3).intValue();
            if (i3 == 0) {
                arrayList.add(Integer.valueOf(intValue));
            } else if (intValue != i2) {
                arrayList.add(Integer.valueOf(intValue));
            }
            iArr[i] = i3;
            i++;
            i2 = intValue;
        }
        this.R = new int[arrayList.size()];
        System.arraycopy(iArr, 0, this.R, 0, this.R.length);
        return arrayList;
    }

    public void e() {
        this.y = false;
        this.E = 0;
        if (this.S != null) {
            Arrays.fill(this.S, 0);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public void setAboveMaskLayer(boolean z) {
        this.U = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlay
    public boolean isAboveMaskLayer() {
        return this.U;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public void setOptions(PolylineOptions polylineOptions) {
        if (polylineOptions != null) {
            this.X = polylineOptions;
            try {
                setColor(polylineOptions.getColor());
                setGeodesic(polylineOptions.isGeodesic());
                setDottedLine(polylineOptions.isDottedLine());
                setAboveMaskLayer(polylineOptions.isAboveMaskLayer());
                setVisible(polylineOptions.isVisible());
                setWidth(polylineOptions.getWidth());
                setZIndex(polylineOptions.getZIndex());
                a(polylineOptions.isUseTexture());
                setTransparency(polylineOptions.getTransparency());
                if (polylineOptions.getColorValues() != null) {
                    d(polylineOptions.getColorValues());
                    b(polylineOptions.isUseGradient());
                }
                if (polylineOptions.getCustomTexture() != null) {
                    setCustomTexture(polylineOptions.getCustomTexture());
                    e();
                }
                if (polylineOptions.getCustomTextureList() != null) {
                    b(polylineOptions.getCustomTextureList());
                    c(polylineOptions.getCustomTextureIndex());
                    e();
                }
                setPoints(polylineOptions.getPoints());
            } catch (RemoteException e) {
                gr.b(e, "PolylineDelegateImp", "setOptions");
                e.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IPolyline
    public PolylineOptions getOptions() {
        return this.X;
    }
}

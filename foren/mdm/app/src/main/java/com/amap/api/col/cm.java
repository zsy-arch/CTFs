package com.amap.api.col;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;
import android.view.animation.AnimationUtils;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.animation.Animation;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.animation.GLAnimation;
import com.autonavi.amap.mapcore.animation.GLAnimationSet;
import com.autonavi.amap.mapcore.animation.GLTransformation;
import com.autonavi.amap.mapcore.animation.GLTranslateAnimation;
import com.autonavi.amap.mapcore.interfaces.IAnimation;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IOverlayImage;
import com.hyphenate.util.EMPrivateConstant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: MarkerDelegateImp.java */
/* loaded from: classes.dex */
public class cm implements ce, IAnimation, IMarkerAction {
    private static int f = 0;
    private float K;
    private float L;
    private r O;
    private String R;
    private LatLng S;
    private LatLng T;
    private String U;
    private String V;
    float[] a;
    private p aa;
    private Object ab;
    private int ak;
    private int al;
    float[] b;
    GLAnimation e;
    private int q;
    private int r;
    private MarkerOptions y;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private float j = 0.0f;
    private float k = 0.0f;
    private boolean l = false;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private FPoint s = FPoint.obtain();
    private float[] t = {-1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    /* renamed from: u  reason: collision with root package name */
    private float f8u = 0.0f;
    private float v = 1.0f;
    private float w = 1.0f;
    private float x = 1.0f;
    private boolean z = false;
    private boolean A = true;
    private int B = 5;
    private boolean C = true;
    private boolean D = true;
    private boolean E = false;
    private boolean F = false;
    private boolean G = false;
    private boolean H = true;
    private FPoint I = FPoint.obtain();
    private Point J = new Point();
    private int M = 0;
    private int N = 0;
    private r[] P = null;
    Rect c = new Rect(0, 0, 0, 0);
    private boolean Q = false;
    private float W = 0.5f;
    private float X = 1.0f;
    private boolean Y = false;
    private boolean Z = true;
    private boolean ac = false;
    private List<BitmapDescriptor> ad = new CopyOnWriteArrayList();
    private boolean ae = false;
    private boolean af = false;
    int d = 9;
    private boolean ag = true;
    private int ah = 0;
    private int ai = 20;
    private boolean aj = false;
    private long am = 0;
    private float an = Float.MAX_VALUE;
    private float ao = Float.MIN_VALUE;
    private float ap = Float.MIN_VALUE;
    private float aq = Float.MAX_VALUE;

    private static String a(String str) {
        f++;
        return str + f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setRotateAngle(float f2) {
        this.y.rotateAngle(f2);
        this.k = f2;
        this.j = (((-f2) % 360.0f) + 360.0f) % 360.0f;
        this.i = true;
        r();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void destroy(boolean z) {
        try {
            this.Q = true;
            if (z) {
                remove();
            }
            if (this.aa != null) {
                for (int i = 0; this.P != null && i < this.P.length; i++) {
                    r rVar = this.P[i];
                    if (rVar != null) {
                        this.aa.a(rVar);
                        this.aa.d().b(rVar.j());
                    }
                }
            }
            for (int i2 = 0; this.ad != null && i2 < this.ad.size(); i2++) {
                this.ad.get(i2).recycle();
            }
            this.S = null;
            this.ab = null;
            this.P = null;
        } catch (Throwable th) {
            gr.b(th, "MarkerDelegateImp", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
            Log.d("destroy erro", "MarkerDelegateImp destroy");
        }
    }

    synchronized void m() {
        if (this.ad != null) {
            this.ad.clear();
        }
    }

    public synchronized void a(ArrayList<BitmapDescriptor> arrayList) {
        m();
        if (arrayList != null) {
            Iterator<BitmapDescriptor> it = arrayList.iterator();
            while (it.hasNext()) {
                BitmapDescriptor next = it.next();
                if (next != null) {
                    this.ad.add(next);
                }
            }
        }
        if (this.ad.size() > 0) {
            this.M = this.ad.get(0).getWidth();
            this.N = this.ad.get(0).getHeight();
        } else {
            this.ad.add(BitmapDescriptorFactory.defaultMarker());
            this.M = this.ad.get(0).getWidth();
            this.N = this.ad.get(0).getHeight();
        }
    }

    public cm(MarkerOptions markerOptions, p pVar) {
        this.aa = pVar;
        setMarkerOptions(markerOptions);
    }

    public int n() {
        try {
            return this.M;
        } catch (Throwable th) {
            return 0;
        }
    }

    public int o() {
        try {
            return this.N;
        } catch (Throwable th) {
            return 0;
        }
    }

    @Override // com.amap.api.col.ch
    public Rect h() {
        if (this.t == null) {
            this.c.set(0, 0, 0, 0);
            return this.c;
        }
        try {
            GLMapState b = this.aa.d().b();
            int n = n();
            int o = o();
            IPoint obtain = IPoint.obtain();
            b.map2Win(this.s.x, this.s.y, obtain);
            Matrix.setIdentityM(this.a, 0);
            Matrix.rotateM(this.a, 0, -this.j, 0.0f, 0.0f, 1.0f);
            if (this.l) {
                Matrix.rotateM(this.a, 0, this.aa.d().getMapConfig().getS_c(), 1.0f, 0.0f, 0.0f);
                Matrix.rotateM(this.a, 0, this.aa.d().getMapConfig().getS_r(), 0.0f, 0.0f, 1.0f);
            }
            float[] fArr = new float[4];
            this.b[0] = (-n) * this.W;
            this.b[1] = o * this.X;
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.set((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]), (int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.b[0] = n * (1.0f - this.W);
            this.b[1] = o * this.X;
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.union((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.b[0] = n * (1.0f - this.W);
            this.b[1] = (-o) * (1.0f - this.X);
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.union((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.b[0] = (-n) * this.W;
            this.b[1] = (-o) * (1.0f - this.X);
            this.b[2] = 0.0f;
            this.b[3] = 1.0f;
            Matrix.multiplyMV(fArr, 0, this.a, 0, this.b, 0);
            this.c.union((int) (obtain.x + fArr[0]), (int) (obtain.y - fArr[1]));
            this.o = this.c.centerX() - obtain.x;
            this.p = this.c.top - obtain.y;
            obtain.recycle();
            return this.c;
        } catch (Throwable th) {
            gr.b(th, "MarkerDelegateImp", "getRect");
            th.printStackTrace();
            return new Rect(0, 0, 0, 0);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public boolean remove() {
        r();
        this.Z = false;
        if (this.aa != null) {
            return this.aa.a((ch) this);
        }
        return false;
    }

    private void r() {
        if (this.aa.d() != null) {
            this.aa.d().setRunLowFrame(false);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public LatLng getPosition() {
        if (!this.aj || this.s == null) {
            return this.S;
        }
        DPoint obtain = DPoint.obtain();
        IPoint obtain2 = IPoint.obtain();
        p();
        this.aa.d().a(this.s.x, this.s.y, obtain2);
        GLMapState.geo2LonLat(obtain2.x, obtain2.y, obtain);
        LatLng latLng = new LatLng(obtain.y, obtain.x);
        obtain2.recycle();
        obtain.recycle();
        return latLng;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public String getId() {
        if (this.R == null) {
            this.R = a("Marker");
        }
        return this.R;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            gr.b(new AMapException("非法坐标值 latlng is null"), "setPosition", "Marker");
            return;
        }
        this.S = latLng;
        IPoint obtain = IPoint.obtain();
        if (this.ae) {
            try {
                double[] a = ir.a(latLng.longitude, latLng.latitude);
                this.T = new LatLng(a[1], a[0]);
                GLMapState.lonlat2Geo(a[0], a[1], obtain);
            } catch (Throwable th) {
                this.T = latLng;
            }
        } else {
            GLMapState.lonlat2Geo(latLng.longitude, latLng.latitude, obtain);
        }
        this.q = obtain.x;
        this.r = obtain.y;
        this.aj = false;
        p();
        r();
        this.i = true;
        obtain.recycle();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker, com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setTitle(String str) {
        this.U = str;
        r();
        this.y.title(str);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public String getTitle() {
        return this.U;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker, com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setSnippet(String str) {
        this.V = str;
        r();
        this.y.snippet(str);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public String getSnippet() {
        return this.V;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setDraggable(boolean z) {
        this.Y = z;
        this.y.draggable(z);
        r();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public synchronized void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList != null) {
            if (this.ad != null) {
                a(arrayList);
                this.af = false;
                this.g = false;
                this.E = false;
                r();
                this.i = true;
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public synchronized ArrayList<BitmapDescriptor> getIcons() {
        ArrayList<BitmapDescriptor> arrayList;
        if (this.ad == null || this.ad.size() <= 0) {
            arrayList = null;
        } else {
            ArrayList<BitmapDescriptor> arrayList2 = new ArrayList<>();
            for (BitmapDescriptor bitmapDescriptor : this.ad) {
                arrayList2.add(bitmapDescriptor);
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public synchronized void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            if (this.ad != null) {
                this.ad.clear();
                this.ad.add(bitmapDescriptor);
                this.E = false;
                this.af = false;
                this.g = false;
                r();
                this.i = true;
                this.M = bitmapDescriptor.getWidth();
                this.N = bitmapDescriptor.getHeight();
            }
        }
    }

    private void s() {
        try {
            this.t[(this.d * 0) + 4] = this.O.b();
            this.t[(0 * this.d) + 5] = this.O.d();
            this.t[(this.d * 1) + 4] = this.O.c();
            this.t[(this.d * 1) + 5] = this.O.d();
            this.t[(this.d * 2) + 4] = this.O.c();
            this.t[(this.d * 2) + 5] = this.O.a();
            this.t[(this.d * 3) + 4] = this.O.b();
            this.t[(this.d * 3) + 5] = this.O.a();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public boolean isDraggable() {
        return this.Y;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public boolean isRemoved() {
        try {
            return !this.aa.c(this);
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void showInfoWindow() {
        if (this.Z && isInfoWindowEnable()) {
            this.aa.b((ce) this);
            r();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void hideInfoWindow() {
        if (isInfoWindowShown()) {
            this.aa.b((ch) this);
            r();
            this.h = false;
        }
        this.i = false;
    }

    @Override // com.amap.api.col.ce
    public void a(boolean z) {
        this.h = z;
        this.i = true;
    }

    @Override // com.amap.api.col.ch, com.autonavi.amap.mapcore.interfaces.IMarker
    public boolean isInfoWindowShown() {
        return this.h;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setVisible(boolean z) {
        if (this.Z != z) {
            this.y.visible(z);
            this.Z = z;
            if (!z) {
                this.G = false;
                if (isInfoWindowShown()) {
                    this.aa.b((ch) this);
                }
            }
            r();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public boolean isVisible() {
        return this.Z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setAnchor(float f2, float f3) {
        if (this.W != f2 || this.X != f3) {
            this.y.anchor(f2, f3);
            this.W = f2;
            this.X = f3;
            this.i = true;
            r();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getAnchorU() {
        return this.W;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getAnchorV() {
        return this.X;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public boolean equalsRemote(IOverlayImage iOverlayImage) throws RemoteException {
        return equals(iOverlayImage) || iOverlayImage.getId().equals(getId());
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public int hashCodeRemote() {
        return super.hashCode();
    }

    public boolean p() {
        try {
            if (this.aa == null || this.aa.d() == null || this.aa.d().b() == null) {
                return false;
            }
            if (this.s == null) {
                this.s = FPoint.obtain();
            }
            if (this.aj) {
                this.aa.d().b(this.ak, this.al, this.s);
            } else {
                this.aa.d().a(this.r, this.q, this.s);
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    private void a(k kVar, float f2) throws RemoteException {
        float n = ((int) (this.v * n())) * f2;
        float o = ((int) (this.w * o())) * f2;
        float f3 = this.s.x;
        float f4 = this.s.y;
        float s_c = kVar.getMapConfig().getS_c();
        float f5 = this.j;
        if (this.l) {
            f5 -= kVar.getMapConfig().getS_r();
            s_c = 0.0f;
        }
        this.t[(this.d * 0) + 0] = f3 - (this.W * n);
        this.t[(this.d * 0) + 1] = ((1.0f - this.X) * o) + f4;
        this.t[(this.d * 0) + 2] = f3;
        this.t[(this.d * 0) + 3] = f4;
        this.t[(this.d * 0) + 6] = f5;
        this.t[(this.d * 0) + 7] = s_c;
        this.t[(this.d * 0) + 8] = this.x;
        this.t[(this.d * 1) + 0] = ((1.0f - this.W) * n) + f3;
        this.t[(this.d * 1) + 1] = ((1.0f - this.X) * o) + f4;
        this.t[(this.d * 1) + 2] = f3;
        this.t[(this.d * 1) + 3] = f4;
        this.t[(this.d * 1) + 6] = f5;
        this.t[(this.d * 1) + 7] = s_c;
        this.t[(this.d * 1) + 8] = this.x;
        this.t[(this.d * 2) + 0] = ((1.0f - this.W) * n) + f3;
        this.t[(this.d * 2) + 1] = f4 - (this.X * o);
        this.t[(this.d * 2) + 2] = f3;
        this.t[(this.d * 2) + 3] = f4;
        this.t[(this.d * 2) + 6] = f5;
        this.t[(this.d * 2) + 7] = s_c;
        this.t[(this.d * 2) + 8] = this.x;
        this.t[(this.d * 3) + 0] = f3 - (n * this.W);
        this.t[(this.d * 3) + 1] = f4 - (o * this.X);
        this.t[(this.d * 3) + 2] = f3;
        this.t[(this.d * 3) + 3] = f4;
        this.t[(this.d * 3) + 6] = f5;
        this.t[(this.d * 3) + 7] = s_c;
        this.t[(this.d * 3) + 8] = this.x;
        if (this.ad != null && this.ad.size() > 0) {
            this.ah++;
            if (this.ah >= this.ai * this.ad.size()) {
                this.ah = 0;
            }
            if (this.ai == 0) {
                this.ai = 1;
            }
            this.O = this.P[this.ah / this.ai];
            if (!this.ag) {
                r();
            }
        }
    }

    @Override // com.amap.api.col.ch
    public void a(k kVar, float[] fArr, int i, float f2) {
        if (this.Q) {
            return;
        }
        if ((this.S != null || this.aj) && this.ad != null) {
            this.s.x = this.q - kVar.getMapConfig().getS_x();
            this.s.y = this.r - kVar.getMapConfig().getS_y();
            try {
                if (!this.g) {
                    this.am = System.currentTimeMillis();
                    this.g = true;
                }
                if (this.aj && this.D) {
                    kVar.b(this.ak, this.al, this.s);
                }
                t();
                a(kVar, f2);
                if (!this.E || !this.ag) {
                    s();
                    this.E = true;
                }
                a(fArr, i);
                if (this.i && isInfoWindowShown()) {
                    this.aa.d().j();
                    if (System.currentTimeMillis() - this.am > 1000) {
                        this.i = false;
                    }
                }
            } catch (Throwable th) {
                gr.b(th, "MarkerDelegateImp", "drawMarker");
            }
        }
    }

    private void a(float[] fArr, int i) {
        if (this.P != null && this.P.length > 0) {
            System.arraycopy(this.t, 0, fArr, i, this.t.length);
        }
    }

    @Override // com.amap.api.col.ch
    public void a(k kVar) {
        Bitmap bitmap;
        if (!this.af) {
            synchronized (this) {
                if (this.ad != null) {
                    this.P = new r[this.ad.size()];
                    boolean z = Build.VERSION.SDK_INT >= 12;
                    int i = 0;
                    for (BitmapDescriptor bitmapDescriptor : this.ad) {
                        r rVar = null;
                        int f2 = (!z || (rVar = kVar.a(bitmapDescriptor)) == null) ? 0 : rVar.f();
                        if (rVar == null) {
                            rVar = new r(bitmapDescriptor, f2);
                        }
                        if (f2 == 0 && (bitmap = bitmapDescriptor.getBitmap()) != null && !bitmap.isRecycled()) {
                            this.M = bitmap.getWidth();
                            this.N = bitmap.getHeight();
                            int e = this.aa.d().e();
                            if (e == 0) {
                                int u2 = u();
                                rVar.a(u2);
                                if (z) {
                                    kVar.a(rVar);
                                }
                                dt.b(u2, bitmap, false);
                            } else {
                                if (this.aa.a(bitmap, rVar)) {
                                    dt.a(e, bitmap, (int) (rVar.b() * 512.0f), (int) (rVar.a() * 1024.0f));
                                    rVar.a(e);
                                } else {
                                    int u3 = u();
                                    dt.b(u3, bitmap, false);
                                    rVar.a(u3);
                                }
                                if (z) {
                                    kVar.a(rVar);
                                }
                            }
                        }
                        rVar.g();
                        this.P[i] = rVar;
                        i++;
                    }
                    if (this.ad.size() == 1) {
                        this.ag = true;
                    } else {
                        this.ag = false;
                    }
                    this.af = true;
                }
                p();
            }
        }
    }

    private void t() {
        if (this.H || this.e == null || this.e.hasEnded()) {
            this.v = 1.0f;
            this.w = 1.0f;
            this.H = true;
            return;
        }
        r();
        GLTransformation gLTransformation = new GLTransformation();
        this.e.getTransformation(AnimationUtils.currentAnimationTimeMillis(), gLTransformation);
        if (gLTransformation != null) {
            if (!Double.isNaN(gLTransformation.scale_x) && !Double.isNaN(gLTransformation.scale_y)) {
                this.v = (float) gLTransformation.scale_x;
                this.w = (float) gLTransformation.scale_y;
            }
            if (!Double.isNaN(gLTransformation.rotate)) {
                setRotateAngle((float) gLTransformation.rotate);
            }
            if (!Double.isNaN(gLTransformation.x) && !Double.isNaN(gLTransformation.y)) {
                double d = gLTransformation.x;
                double d2 = gLTransformation.y;
                if (this.aj) {
                    FPoint obtain = FPoint.obtain();
                    this.aa.d().b().win2Map((int) d, (int) d2, obtain);
                    IPoint obtain2 = IPoint.obtain();
                    this.aa.d().b().map2Geo(obtain.x, obtain.y, obtain2);
                    a(obtain2.x, obtain2.y);
                    obtain.recycle();
                    obtain2.recycle();
                    this.aj = true;
                } else {
                    a((int) d, (int) d2);
                }
            }
            if (!Double.isNaN(gLTransformation.alpha)) {
                this.x = (float) gLTransformation.alpha;
            }
        }
        this.i = true;
    }

    private int u() {
        int[] iArr = {0};
        GLES20.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    @Override // com.amap.api.col.ch
    public boolean i() {
        return this.ag;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setPeriod(int i) {
        if (i <= 1) {
            this.ai = 1;
        } else {
            this.ai = i;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setObject(Object obj) {
        this.ab = obj;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public Object getObject() {
        return this.ab;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setPerspective(boolean z) {
        this.ac = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public boolean isPerspective() {
        return this.ac;
    }

    @Override // com.amap.api.col.ch
    public int k() {
        try {
            if (this.ad == null || this.ad.size() <= 0) {
                return 0;
            }
            return this.O.f();
        } catch (Throwable th) {
            return 0;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public int getPeriod() {
        return this.ai;
    }

    @Override // com.amap.api.col.ce
    public LatLng b() {
        if (!this.aj) {
            return this.ae ? this.T : this.S;
        }
        this.aa.d().b().win2Map(this.ak, this.al, this.s);
        DPoint obtain = DPoint.obtain();
        this.aa.d().b(this.ak, this.al, obtain);
        LatLng latLng = new LatLng(obtain.y, obtain.y);
        obtain.recycle();
        return latLng;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void set2Top() {
        this.aa.a((ce) this);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setFlat(boolean z) throws RemoteException {
        this.l = z;
        r();
        this.y.setFlat(z);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public boolean isFlat() {
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getRotateAngle() {
        return this.k;
    }

    @Override // com.amap.api.col.ce
    public int c() {
        return this.m;
    }

    @Override // com.amap.api.col.ce
    public int d() {
        return this.n;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setPositionByPixels(int i, int i2) {
        this.ak = i;
        this.al = i2;
        this.aj = true;
        p();
        r();
        this.i = true;
    }

    @Override // com.amap.api.col.ce
    public int e() {
        return this.o;
    }

    @Override // com.amap.api.col.ce
    public int f() {
        return this.p;
    }

    @Override // com.amap.api.col.ce
    public FPoint a() {
        return this.s;
    }

    @Override // com.amap.api.col.ce
    public boolean g() {
        return this.aj;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public void setZIndex(float f2) {
        this.f8u = f2;
        this.y.zIndex(f2);
        this.aa.g();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IOverlayImage
    public float getZIndex() {
        return this.f8u;
    }

    @Override // com.amap.api.col.ch
    public boolean j() {
        if (this.aj) {
            return true;
        }
        if (this.s != null) {
            if (!this.H) {
                return true;
            }
            this.J.x = this.q;
            this.J.y = this.r;
            if (this.aa.d().getMapConfig().getGeoRectangle().contains(this.q, this.r)) {
                return true;
            }
            v();
            this.I.x = this.s.x;
            this.I.y = this.s.y;
            FPoint[] mapRect = this.aa.d().getMapConfig().getMapRect();
            a(mapRect);
            if (dt.a(this.I, mapRect)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setGeoPoint(IPoint iPoint) {
        this.aj = false;
        a(iPoint.x, iPoint.y);
    }

    private void a(int i, int i2) {
        this.q = i;
        this.r = i2;
        DPoint obtain = DPoint.obtain();
        GLMapState.geo2LonLat(this.q, this.r, obtain);
        this.S = new LatLng(obtain.y, obtain.x, false);
        this.aa.d().b().geo2Map(this.q, this.r, this.s);
        obtain.recycle();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public IPoint getGeoPoint() {
        IPoint obtain = IPoint.obtain();
        if (this.aj) {
            this.aa.d().a(this.ak, this.al, obtain);
        } else {
            obtain.set(this.q, this.r);
        }
        return obtain;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setAnimation(Animation animation) {
        IAnimation q = q();
        if (q != null) {
            q.setAnimation(animation == null ? null : animation.glAnimation);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAnimation
    public void setAnimation(GLAnimation gLAnimation) {
        if (gLAnimation != null) {
            this.e = gLAnimation;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker, com.autonavi.amap.mapcore.interfaces.IAnimation
    public boolean startAnimation() {
        if (this.e != null) {
            if (this.e instanceof GLAnimationSet) {
                GLAnimationSet gLAnimationSet = (GLAnimationSet) this.e;
                for (GLAnimation gLAnimation : gLAnimationSet.getAnimations()) {
                    a(gLAnimation);
                    gLAnimation.setDuration(gLAnimationSet.getDuration());
                }
            } else {
                a(this.e);
            }
            this.H = false;
            this.e.start();
            r();
        }
        return false;
    }

    private void a(GLAnimation gLAnimation) {
        if (gLAnimation instanceof GLTranslateAnimation) {
            if (this.aj) {
                this.S = getPosition();
                setPosition(this.S);
                this.aj = true;
            }
            if (this.aj) {
                ((GLTranslateAnimation) gLAnimation).mFromXDelta = this.ak;
                ((GLTranslateAnimation) gLAnimation).mFromYDelta = this.al;
                IPoint obtain = IPoint.obtain();
                this.aa.d().b(((GLTranslateAnimation) gLAnimation).mToYDelta, ((GLTranslateAnimation) gLAnimation).mToXDelta, obtain);
                ((GLTranslateAnimation) gLAnimation).mToXDelta = obtain.x;
                ((GLTranslateAnimation) gLAnimation).mToYDelta = obtain.y;
                obtain.recycle();
                return;
            }
            ((GLTranslateAnimation) gLAnimation).mFromXDelta = this.q;
            ((GLTranslateAnimation) gLAnimation).mFromYDelta = this.r;
            IPoint obtain2 = IPoint.obtain();
            GLMapState.lonlat2Geo(((GLTranslateAnimation) gLAnimation).mToXDelta, ((GLTranslateAnimation) gLAnimation).mToYDelta, obtain2);
            ((GLTranslateAnimation) gLAnimation).mToXDelta = obtain2.x;
            ((GLTranslateAnimation) gLAnimation).mToYDelta = obtain2.y;
            obtain2.recycle();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker, com.autonavi.amap.mapcore.interfaces.IAnimation
    public void setAnimationListener(Animation.AnimationListener animationListener) {
        if (this.e != null) {
            this.e.setAnimationListener(animationListener);
        }
    }

    public IAnimation q() {
        return this;
    }

    @Override // com.amap.api.col.ch, com.autonavi.amap.mapcore.interfaces.IMarker
    public IMarkerAction getIMarkerAction() {
        return this;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public float getAlpha() {
        return this.x;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setAlpha(float f2) {
        this.x = f2;
        this.y.alpha(f2);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public int getDisplayLevel() {
        return this.B;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public MarkerOptions getOptions() {
        return this.y;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setMarkerOptions(MarkerOptions markerOptions) {
        if (markerOptions != null) {
            this.y = markerOptions;
            this.S = this.y.getPosition();
            IPoint obtain = IPoint.obtain();
            this.ae = this.y.isGps();
            if (this.y.getPosition() != null) {
                if (this.ae) {
                    try {
                        double[] a = ir.a(this.y.getPosition().longitude, this.y.getPosition().latitude);
                        this.T = new LatLng(a[1], a[0]);
                        GLMapState.lonlat2Geo(a[0], a[1], obtain);
                    } catch (Throwable th) {
                        gr.b(th, "MarkerDelegateImp", "create");
                        this.T = this.y.getPosition();
                    }
                } else {
                    GLMapState.lonlat2Geo(this.S.longitude, this.S.latitude, obtain);
                }
            }
            this.q = obtain.x;
            this.r = obtain.y;
            this.W = this.y.getAnchorU();
            this.X = this.y.getAnchorV();
            this.m = this.y.getInfoWindowOffsetX();
            this.n = this.y.getInfoWindowOffsetY();
            this.ai = this.y.getPeriod();
            this.f8u = this.y.getZIndex();
            this.F = this.y.isBelowMaskLayer();
            p();
            a(this.y.getIcons());
            this.Z = this.y.isVisible();
            this.V = this.y.getSnippet();
            this.U = this.y.getTitle();
            this.Y = this.y.isDraggable();
            this.R = getId();
            this.ac = this.y.isPerspective();
            this.l = this.y.isFlat();
            this.F = this.y.isBelowMaskLayer();
            this.x = this.y.getAlpha();
            setRotateAngle(this.y.getRotateAngle());
            this.B = this.y.getDisplayLevel();
            this.z = this.y.isInfoWindowAutoOverturn();
            this.A = this.y.isInfoWindowEnable();
            this.a = new float[16];
            this.b = new float[4];
            obtain.recycle();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public boolean isClickable() {
        return this.C;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public boolean isInfoWindowAutoOverturn() {
        return this.z;
    }

    @Override // com.amap.api.col.ce, com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public boolean isInfoWindowEnable() {
        return this.A;
    }

    @Override // com.amap.api.col.ch
    public void b(boolean z) {
        this.G = z;
    }

    @Override // com.amap.api.col.ch
    public boolean l() {
        return this.G;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setInfoWindowEnable(boolean z) {
        this.A = z;
        if (!z) {
            hideInfoWindow();
        }
        this.y.infoWindowEnable(z);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setAutoOverturnInfoWindow(boolean z) {
        this.z = z;
        this.y.autoOverturnInfoWindow(z);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setClickable(boolean z) {
        this.C = z;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setDisplayLevel(int i) {
        this.B = i;
        this.y.displayLevel(i);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setFixingPointEnable(boolean z) {
        this.D = z;
        if (!z) {
            boolean z2 = false;
            if (this.aj) {
                z2 = true;
            }
            this.S = getPosition();
            setPosition(this.S);
            if (z2) {
                this.aj = true;
            }
        } else if (this.aj && this.S != null) {
            IPoint obtain = IPoint.obtain();
            this.aa.d().b().map2Win(this.s.x, this.s.y, obtain);
            this.ak = obtain.x;
            this.al = obtain.y;
            obtain.recycle();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setPositionNotUpdate(LatLng latLng) {
        setPosition(latLng);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarkerAction
    public void setRotateAngleNotUpdate(float f2) {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMarker
    public void setBelowMaskLayer(boolean z) {
        this.F = z;
    }

    private void a(FPoint[] fPointArr) {
        if (fPointArr != null) {
            v();
            if (this.K > 0.0f && this.L > 0.0f && fPointArr.length == 4) {
                this.an = Math.min(fPointArr[0].x, fPointArr[1].x);
                this.an = Math.min(this.an, fPointArr[2].x);
                this.an = Math.min(this.an, fPointArr[3].x);
                this.ao = Math.max(fPointArr[0].x, fPointArr[1].x);
                this.ao = Math.max(this.ao, fPointArr[2].x);
                this.ao = Math.max(this.ao, fPointArr[3].x);
                this.aq = Math.min(fPointArr[0].y, fPointArr[1].y);
                this.aq = Math.min(this.aq, fPointArr[2].y);
                this.aq = Math.min(this.aq, fPointArr[3].y);
                this.ap = Math.max(fPointArr[0].y, fPointArr[1].y);
                this.ap = Math.max(this.ap, fPointArr[2].y);
                this.ap = Math.max(this.ap, fPointArr[3].y);
                if (this.s.x < (this.an + this.ao) / 2.0f) {
                    this.I.x = this.s.x + (this.K / 2.0f);
                } else {
                    this.I.x = this.s.x - (this.K / 2.0f);
                }
                if (this.s.y < (this.aq + this.ap) / 2.0f) {
                    this.I.y = this.s.y;
                    return;
                }
                this.I.y = this.s.y - this.L;
            }
        }
    }

    private void v() {
        if (this.aa.d() != null && this.aa.d().getMapConfig() != null) {
            this.K = this.aa.d().getMapConfig().getMapPerPixelUnitLength() * n();
            this.L = this.aa.d().getMapConfig().getMapPerPixelUnitLength() * o();
        }
    }
}

package com.amap.api.col;

import android.graphics.Rect;
import android.opengl.Matrix;
import android.os.RemoteException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: MultiPointOverlayDelegate.java */
/* loaded from: classes.dex */
public class ag implements IMultiPointOverlay {
    private static int F = 0;
    public static a v;
    private String C;
    List<MultiPointItem> h;
    IPoint m;
    ah n;
    BitmapDescriptor a = BitmapDescriptorFactory.defaultMarker();
    BitmapDescriptor b = null;
    float c = 0.0f;
    float d = 0.0f;
    float e = 0.0f;
    float f = 0.5f;
    float g = 0.5f;
    ai i = null;
    af j = null;
    af k = new af(0, 1, 0, 1);
    List<MultiPointItem> l = new ArrayList();
    private float[] D = {-0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 0.0f, -0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f, -0.5f, 0.0f, 0.0f, 1.0f, 0.0f};
    private boolean E = true;
    List<ae> o = new ArrayList();
    private ExecutorService G = null;
    private List<String> H = new ArrayList();
    private int I = 3;
    private float[] J = new float[this.I * 200];
    float[] p = new float[16];
    float[] q = new float[4];
    float[] r = new float[4];
    Rect s = new Rect();
    af t = null;

    /* renamed from: u  reason: collision with root package name */
    af f6u = null;
    int w = 0;
    int x = 0;
    float[] y = new float[12];
    String z = "precision highp float;\nattribute vec3 aVertex;//顶点数组,三维坐标\nuniform mat4 aMVPMatrix;//mvp矩阵\nvoid main(){\n  gl_Position = aMVPMatrix * vec4(aVertex, 1.0);\n}";
    String A = "//有颜色 没有纹理\nprecision highp float;\nvoid main(){\n  gl_FragColor = vec4(0,0,1,1.0);\n}";
    int B = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MultiPointOverlayDelegate.java */
    /* loaded from: classes.dex */
    public static class a extends cu {
    }

    public ag(MultiPointOverlayOptions multiPointOverlayOptions, ah ahVar) {
        this.n = ahVar;
        a(multiPointOverlayOptions);
        ae aeVar = new ae(a());
        aeVar.a(this.b);
        this.o.add(aeVar);
    }

    private float[] a() {
        if (this.D == null) {
            return null;
        }
        float[] fArr = (float[]) this.D.clone();
        float f = this.f - 0.5f;
        float f2 = this.g - 0.5f;
        fArr[0] = fArr[0] + f;
        fArr[1] = fArr[1] - f2;
        fArr[6] = fArr[6] + f;
        fArr[7] = fArr[7] - f2;
        fArr[12] = fArr[12] + f;
        fArr[13] = fArr[13] - f2;
        fArr[18] = f + fArr[18];
        fArr[19] = fArr[19] - f2;
        return fArr;
    }

    private static String a(String str) {
        F++;
        return str + F;
    }

    private void a(MultiPointOverlayOptions multiPointOverlayOptions) {
        if (multiPointOverlayOptions != null) {
            if (multiPointOverlayOptions.getIcon() != null) {
                this.b = multiPointOverlayOptions.getIcon();
            } else {
                this.b = this.a;
            }
            this.f = multiPointOverlayOptions.getAnchorU();
            this.g = multiPointOverlayOptions.getAnchorV();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void addItems(List<MultiPointItem> list) {
        af b;
        try {
            synchronized (this) {
                if (this.h == null) {
                    this.h = new ArrayList();
                }
                this.h.clear();
                this.h.addAll(list);
                int size = this.h.size();
                for (int i = 0; i < size; i++) {
                    if (this.h != null) {
                        MultiPointItem multiPointItem = this.h.get(i);
                        if (!(multiPointItem == null || multiPointItem.getLatLng() == null || multiPointItem.getIPoint() != null)) {
                            IPoint iPoint = new IPoint();
                            MapProjection.lonlat2Geo(multiPointItem.getLatLng().longitude, multiPointItem.getLatLng().latitude, iPoint);
                            multiPointItem.setIPoint(iPoint);
                        }
                    } else {
                        return;
                    }
                }
                if (this.i == null && (b = b()) != null) {
                    this.i = new ai(b);
                }
                int size2 = this.h.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    if (this.h != null) {
                        MultiPointItem multiPointItem2 = this.h.get(i2);
                        if (!(multiPointItem2 == null || multiPointItem2.getIPoint() == null || this.i == null)) {
                            this.i.a(multiPointItem2);
                        }
                    } else {
                        return;
                    }
                }
                d();
            }
        } catch (Throwable th) {
            gr.b(th, "MultiPointOverlayDelegate", "addItems");
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void addItem(MultiPointItem multiPointItem) {
        synchronized (this) {
        }
        d();
    }

    private af b() {
        if (this.h == null || this.h.size() == 0) {
            return null;
        }
        Iterator<MultiPointItem> it = this.h.iterator();
        MultiPointItem next = it.next();
        int i = next.getIPoint().x;
        int i2 = next.getIPoint().x;
        int i3 = next.getIPoint().y;
        int i4 = next.getIPoint().y;
        while (it.hasNext()) {
            MultiPointItem next2 = it.next();
            int i5 = next2.getIPoint().x;
            i4 = next2.getIPoint().y;
            if (i5 < i) {
                i = i5;
            }
            if (i5 > i2) {
                i2 = i5;
            }
            if (i4 < i3) {
                i3 = i4;
            }
            if (i4 <= i4) {
                i4 = i4;
            }
        }
        return new af(i, i2, i3, i4);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void draw(MapConfig mapConfig, float[] fArr, float[] fArr2) {
        int i;
        if (this.E) {
            c();
            if (this.o.size() >= 1 && this.i != null) {
                float s_r = mapConfig.getS_r();
                float s_c = mapConfig.getS_c();
                if (mapConfig.getChangeRatio() != 1.0d || this.l.size() == 0) {
                    synchronized (this.l) {
                        a(mapConfig);
                        this.l.clear();
                        this.c = mapConfig.getMapPerPixelUnitLength();
                        this.d = this.c * this.b.getWidth();
                        this.e = this.c * this.b.getHeight();
                        double d = this.d * this.e * 16.0f;
                        a(this.d, this.e, s_r, s_c);
                        this.i.a(this.j, this.l, d);
                    }
                }
                if (this.m == null) {
                    this.m = new IPoint();
                }
                if (!(this.m == null || mapConfig == null)) {
                    this.m.x = mapConfig.getS_x();
                    this.m.y = mapConfig.getS_y();
                }
                ae aeVar = this.o.get(0);
                synchronized (this.l) {
                    i = 0;
                    for (MultiPointItem multiPointItem : this.l) {
                        IPoint iPoint = multiPointItem.getIPoint();
                        if (iPoint != null) {
                            int i2 = iPoint.x - this.m.x;
                            int i3 = iPoint.y - this.m.y;
                            if (aeVar != null && aeVar.b()) {
                                this.J[(this.I * i) + 0] = i2;
                                this.J[(this.I * i) + 1] = i3;
                                this.J[(this.I * i) + 2] = 0.0f;
                                i++;
                                if (i >= 200) {
                                    aeVar.a(fArr, fArr2, this.J, this.d, this.e, s_r, s_c, i);
                                    i = 0;
                                }
                            }
                        }
                    }
                }
                if (i > 0) {
                    aeVar.a(fArr, fArr2, this.J, this.d, this.e, s_r, s_c, i);
                }
            }
        }
    }

    private void c() {
        if (this.G == null) {
            this.G = Executors.newSingleThreadExecutor();
        }
        for (final ae aeVar : this.o) {
            if (aeVar != null && !aeVar.b()) {
                final String str = aeVar.hashCode() + "";
                if (!this.H.contains(str)) {
                    this.H.add(str);
                    this.G.execute(new Runnable() { // from class: com.amap.api.col.ag.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (!aeVar.b()) {
                                aeVar.a();
                                ag.this.H.remove(str);
                            }
                        }
                    });
                }
            }
        }
    }

    private void a(MapConfig mapConfig) {
        if (mapConfig != null) {
            Rect rect = mapConfig.getGeoRectangle().getRect();
            if (this.j == null) {
                this.j = new af(rect.left, rect.right, rect.top, rect.bottom);
            } else {
                this.j.a(rect.left, rect.right, rect.top, rect.bottom);
            }
        }
    }

    private void a(float f, float f2, float f3, float f4) {
        if (this.k == null) {
            this.k = new af(0, 1, 0, 1);
        }
        this.s.set(0, 0, 0, 0);
        IPoint iPoint = new IPoint();
        float f5 = this.f;
        float f6 = this.g;
        Matrix.setIdentityM(this.p, 0);
        Matrix.rotateM(this.p, 0, -f3, 0.0f, 0.0f, 1.0f);
        this.r[0] = 0.0f;
        this.r[1] = 0.0f;
        this.r[2] = 0.0f;
        this.r[3] = 0.0f;
        this.q[0] = (-f) * f5;
        this.q[1] = f2 * f6;
        this.q[2] = 0.0f;
        this.q[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, this.q, 0);
        this.s.set((int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]), (int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]));
        this.q[0] = (1.0f - f5) * f;
        this.q[1] = f2 * f6;
        this.q[2] = 0.0f;
        this.q[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, this.q, 0);
        this.s.union((int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]));
        this.q[0] = (1.0f - f5) * f;
        this.q[1] = (-f2) * (1.0f - f6);
        this.q[2] = 0.0f;
        this.q[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, this.q, 0);
        this.s.union((int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]));
        this.q[0] = (-f) * f5;
        this.q[1] = (-f2) * (1.0f - f6);
        this.q[2] = 0.0f;
        this.q[3] = 1.0f;
        Matrix.multiplyMV(this.r, 0, this.p, 0, this.q, 0);
        this.s.union((int) (iPoint.x + this.r[0]), (int) (iPoint.y - this.r[1]));
        this.k.a(this.s.left, this.s.right, this.s.top, this.s.bottom);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public MultiPointItem onClick(IPoint iPoint) {
        if (!this.E || this.i == null) {
            return null;
        }
        if (this.t == null) {
            this.t = new af(0, 1, 0, 1);
        }
        int i = (int) (this.c * 8.0f);
        this.t.a(iPoint.x - i, iPoint.x + i, iPoint.y - i, i + iPoint.y);
        if (!(this.i == null || this.i.b() == null || this.i.b().a(this.t))) {
            return null;
        }
        synchronized (this.l) {
            for (int size = this.l.size() - 1; size >= 0; size--) {
                MultiPointItem multiPointItem = this.l.get(size);
                IPoint iPoint2 = multiPointItem.getIPoint();
                if (iPoint2 != null) {
                    if (this.k == null) {
                        return null;
                    }
                    if (this.f6u == null) {
                        this.f6u = new af(0, 1, 0, 1);
                    }
                    this.f6u.a(iPoint2.x + this.k.a, iPoint2.x + this.k.c, iPoint2.y + this.k.b, iPoint2.y + this.k.d);
                    if (this.f6u.b(this.t)) {
                        return multiPointItem;
                    }
                }
            }
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void setAnchor(float f, float f2) {
        this.f = f;
        this.g = f2;
        d();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public String getId() throws RemoteException {
        if (this.C == null) {
            this.C = a("MultiPointOverlay");
        }
        return this.C;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void remove(boolean z) {
        this.E = false;
        e();
        this.w = 0;
        this.x = 0;
        if (this.a != null) {
            this.a.recycle();
        }
        if (this.h != null) {
            this.h.clear();
            this.h = null;
        }
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        if (this.l != null) {
            this.l.clear();
        }
        if (this.G != null) {
            this.G.shutdownNow();
            this.G = null;
        }
        if (this.H != null) {
            this.H.clear();
        }
        if (this.o != null) {
            for (ae aeVar : this.o) {
                if (aeVar != null) {
                    aeVar.c();
                }
            }
            this.o.clear();
        }
        if (z && this.n != null) {
            this.n.a(this);
        }
        this.n.c();
        this.n = null;
        this.D = null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void setVisible(boolean z) {
        if (this.E != z) {
            d();
        }
        this.E = z;
    }

    private void d() {
        if (this.n != null) {
            this.n.c();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay
    public void destroy(boolean z) {
        remove(z);
        if (this.b != null) {
            this.b.recycle();
        }
    }

    private static void e() {
        v = null;
    }
}

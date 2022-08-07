package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import com.amap.api.col.ee;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CustomRenderer;
import com.amap.api.maps.InfoWindowAnimationManager;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.AMapCameraInfo;
import com.amap.api.maps.model.AMapGestureListener;
import com.amap.api.maps.model.Arc;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.CrossOverlay;
import com.amap.api.maps.model.CrossOverlayOptions;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.IndoorBuildingInfo;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrow;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.RouteOverlay;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.services.core.AMapException;
import com.autonavi.ae.gmap.GLMapEngine;
import com.autonavi.ae.gmap.GLMapRender;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.ae.gmap.callback.GLMapCoreCallback;
import com.autonavi.ae.gmap.gesture.EAMapPlatformGestureInfo;
import com.autonavi.ae.gmap.glinterface.MapLabelItem;
import com.autonavi.ae.gmap.gloverlay.BaseMapOverlay;
import com.autonavi.ae.gmap.gloverlay.BaseRouteOverlay;
import com.autonavi.ae.gmap.gloverlay.CrossVectorOverlay;
import com.autonavi.ae.gmap.gloverlay.GLOverlayBundle;
import com.autonavi.ae.gmap.gloverlay.GLTextureProperty;
import com.autonavi.ae.gmap.listener.MapSurfaceListener;
import com.autonavi.ae.gmap.listener.MapWidgetListener;
import com.autonavi.ae.gmap.maploader.NetworkState;
import com.autonavi.ae.gmap.maploader.VMapDataCache;
import com.autonavi.ae.gmap.style.MapTilsCacheAndResManager;
import com.autonavi.ae.gmap.style.StyleItem;
import com.autonavi.ae.gmap.utils.GLConvertUtil;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;
import com.autonavi.amap.mapcore.AEUtil;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.CameraUpdateMessage;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapConfig;
import com.autonavi.amap.mapcore.Rectangle;
import com.autonavi.amap.mapcore.VirtualEarthProjection;
import com.autonavi.amap.mapcore.animation.GLAlphaAnimation;
import com.autonavi.amap.mapcore.interfaces.IAMapListener;
import com.autonavi.amap.mapcore.interfaces.IMarkerAction;
import com.autonavi.amap.mapcore.interfaces.IMultiPointOverlay;
import com.autonavi.amap.mapcore.message.GestureMapMessage;
import com.hyphenate.util.EMPrivateConstant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: AMapDelegateImp.java */
/* loaded from: classes.dex */
public class a implements k, GLMapCoreCallback, IAMapListener {
    private static boolean az = false;
    private AMap.OnMarkerClickListener D;
    private AMap.OnPolylineClickListener E;
    private AMap.OnMarkerDragListener F;
    private AMap.OnMapLoadedListener G;
    private AMap.OnCameraChangeListener H;
    private AMap.OnMapClickListener I;
    private AMap.OnMapTouchListener J;
    private AMap.OnPOIClickListener K;
    private AMap.OnMapLongClickListener L;
    private AMap.OnInfoWindowClickListener M;
    private AMap.OnIndoorBuildingActiveListener N;
    private AMap.OnMyLocationChangeListener O;
    private AMapGestureListener U;
    private cr V;
    private UiSettings W;
    cd a;
    private Context aF;
    private GLMapEngine aG;
    private boolean aL;
    private int aY;
    private int aZ;
    private final l aa;
    private MapSurfaceListener af;
    private MapWidgetListener ag;
    private cn al;
    private LocationSource am;
    private Thread ay;
    eg c;
    final u d;
    protected final p e;
    protected ac g;
    CustomRenderer h;
    private int y = -1;
    private int z = -1;
    private int A = 0;
    private Bitmap B = null;
    private Bitmap C = null;
    private AMap.onMapPrintScreenListener Q = null;
    private AMap.OnMapScreenShotListener R = null;
    private AMap.CancelableCallback S = null;
    private Object T = new Object();
    protected boolean b = false;
    private boolean Z = false;
    private boolean ab = true;
    private boolean ad = false;
    private int ae = 0;
    private boolean ah = false;
    private MapConfig ai = new MapConfig(true);
    private boolean aj = false;
    private boolean ak = false;
    private boolean an = false;
    private Marker ao = null;
    private ce ap = null;
    private boolean aq = false;
    private boolean ar = false;
    private boolean as = false;
    private boolean at = false;
    private boolean au = false;
    private Rect av = new Rect();
    private int aw = 1;
    private MyTrafficStyle ax = null;
    private boolean aA = false;
    private int aB = 0;
    private int aD = -1;
    int i = -1;
    private List<r> aE = new ArrayList();
    private boolean aI = false;
    private float aJ = 0.0f;
    private float aK = 1.0f;
    private boolean aM = true;
    private boolean aN = false;
    private boolean aO = false;
    private int aP = 0;
    private GL10 aQ = null;
    private volatile boolean aR = false;
    private volatile boolean aS = false;
    private boolean aT = true;
    private boolean aU = false;
    private boolean aV = false;
    private Lock aW = new ReentrantLock();
    private int aX = 0;
    final Handler n = new Handler(Looper.getMainLooper()) { // from class: com.amap.api.col.a.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            boolean z = true;
            if (message != null && !a.this.ad) {
                try {
                    a.this.p();
                    switch (message.what) {
                        case 2:
                            StringBuilder sb = new StringBuilder();
                            sb.append("Key验证失败：[");
                            if (message.obj != null) {
                                sb.append(message.obj);
                            } else {
                                sb.append(gb.b);
                            }
                            sb.append("]");
                            Log.w("amapsdk", sb.toString());
                            break;
                        case 10:
                            CameraPosition cameraPosition = (CameraPosition) message.obj;
                            if (!(cameraPosition == null || a.this.H == null)) {
                                a.this.H.onCameraChange(cameraPosition);
                                break;
                            }
                            break;
                        case 11:
                            CameraPosition cameraPosition2 = a.this.getCameraPosition();
                            if (!(cameraPosition2 == null || a.this.c == null)) {
                                a.this.c.a(cameraPosition2);
                            }
                            a.this.g(true);
                            if (a.this.at) {
                                a.this.j();
                                a.this.at = false;
                            }
                            a.this.a(true, cameraPosition2);
                            synchronized (a.this.T) {
                                if (a.this.S != null) {
                                    a.this.S.onFinish();
                                }
                            }
                            break;
                        case 12:
                            if (a.this.c != null) {
                                a.this.c.a(a.this.g());
                                break;
                            }
                            break;
                        case 13:
                            if (a.this.c != null) {
                                ed g = a.this.c.g();
                                if (g != null) {
                                    g.b();
                                    break;
                                } else {
                                    return;
                                }
                            }
                            break;
                        case 14:
                            if (a.this.J != null) {
                                a.this.J.onTouch((MotionEvent) message.obj);
                                break;
                            }
                            break;
                        case 15:
                            Bitmap bitmap = (Bitmap) message.obj;
                            int i = message.arg1;
                            if (bitmap == null || a.this.c == null) {
                                if (a.this.Q != null) {
                                    a.this.Q.onMapPrint(null);
                                }
                                if (a.this.R != null) {
                                    a.this.R.onMapScreenShot(null);
                                    a.this.R.onMapScreenShot(null, i);
                                }
                            } else {
                                Canvas canvas = new Canvas(bitmap);
                                ei h = a.this.c.h();
                                if (h != null) {
                                    h.onDraw(canvas);
                                }
                                a.this.c.a(canvas);
                                if (a.this.Q != null) {
                                    a.this.Q.onMapPrint(new BitmapDrawable(a.this.aF.getResources(), bitmap));
                                }
                                if (a.this.R != null) {
                                    a.this.R.onMapScreenShot(bitmap);
                                    a.this.R.onMapScreenShot(bitmap, i);
                                }
                            }
                            a.this.Q = null;
                            a.this.R = null;
                            break;
                        case 16:
                            if (a.this.G != null) {
                                a.this.G.onMapLoaded();
                                break;
                            }
                            break;
                        case 17:
                            if (a.this.aG.isInMapAnimation(1) && a.this.d != null) {
                                a.this.d.b(false);
                            }
                            if (a.this.d != null) {
                                u uVar = a.this.d;
                                if (message.arg1 == 0) {
                                    z = false;
                                }
                                uVar.a(z);
                                break;
                            }
                            break;
                        case 18:
                            if (a.this.a != null) {
                                a.this.a.e();
                                break;
                            }
                            break;
                        case 19:
                            if (a.this.I != null) {
                                DPoint obtain = DPoint.obtain();
                                a.this.b(message.arg1, message.arg2, obtain);
                                a.this.I.onMapClick(new LatLng(obtain.y, obtain.x));
                                obtain.recycle();
                                break;
                            }
                            break;
                        case 20:
                            a.this.K.onPOIClick((Poi) message.obj);
                            break;
                    }
                    a.this.p();
                } catch (Throwable th) {
                    gr.b(th, "AMapDelegateImp", "handleMessage");
                    th.printStackTrace();
                }
            }
        }
    };
    private AbstractRunnableC0011a bb = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.11
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            try {
                a.this.setTrafficEnabled(this.c);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private AbstractRunnableC0011a bc = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.17
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            a.this.a(this.g, this.c);
        }
    };
    private AbstractRunnableC0011a bd = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.25
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            try {
                a.this.setCenterToPixel(a.this.aY, a.this.aZ);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private AbstractRunnableC0011a be = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.33
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            a.this.b(this.g, this.d, this.e, this.f);
        }
    };
    private AbstractRunnableC0011a bf = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.38
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            a.this.setMapCustomEnable(this.c);
        }
    };
    private AbstractRunnableC0011a bg = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.39
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            a.this.c(this.g, this.c);
        }
    };
    private AbstractRunnableC0011a bh = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.40
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            a.this.d(this.g, this.c);
        }
    };
    private AbstractRunnableC0011a bi = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.41
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            a.this.e(this.g, this.c);
        }
    };
    private AbstractRunnableC0011a bj = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.2
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            a.this.f(this.g, this.c);
        }
    };
    private AbstractRunnableC0011a bk = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.3
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            try {
                a.this.setIndoorEnabled(this.c);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    };
    private Runnable bl = new Runnable() { // from class: com.amap.api.col.a.4
        @Override // java.lang.Runnable
        public void run() {
            ei h;
            if (a.this.c != null && (h = a.this.c.h()) != null) {
                h.d();
            }
        }
    };
    private AbstractRunnableC0011a bm = new AbstractRunnableC0011a() { // from class: com.amap.api.col.a.5
        @Override // com.amap.api.col.a.AbstractRunnableC0011a, java.lang.Runnable
        public void run() {
            super.run();
            a.this.g(this.g, this.c);
        }
    };
    private EAMapPlatformGestureInfo bn = new EAMapPlatformGestureInfo();
    Point o = new Point();
    Rect p = new Rect();
    protected String q = null;
    private ac bo = null;
    float[] r = new float[16];
    float[] s = new float[16];
    float[] t = new float[16];
    private IPoint[] bp = null;

    /* renamed from: u */
    float[] f5u = new float[12];
    String v = "precision highp float;\nattribute vec3 aVertex;//顶点数组,三维坐标\nuniform mat4 aMVPMatrix;//mvp矩阵\nvoid main(){\n  gl_Position = aMVPMatrix * vec4(aVertex, 1.0);\n}";
    String w = "//有颜色 没有纹理\nprecision highp float;\nvoid main(){\n  gl_FragColor = vec4(1.0,0,0,1.0);\n}";
    int x = -1;
    h j = new h(this);
    private GLMapRender aH = new GLMapRender(this);
    public int k;
    public int l;
    private int ac = a(0, new Rect(0, 0, this.k, this.l), this.k, this.l);
    private final v Y = new v(this);
    private b ba = new b();
    final i f = new i(this);
    private m X = new s(this);
    private e P = new e(this);
    private final q aC = new q();
    ah m = new ah(this);

    @Override // com.amap.api.col.k
    public void a(MapWidgetListener mapWidgetListener) {
        this.ag = mapWidgetListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setVisibilityEx(int i) {
        if (this.aa != null) {
            try {
                this.aa.setVisibility(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onActivityPause() {
        this.ah = true;
        if (this.aa instanceof d) {
            this.aa.setRenderMode(1);
        }
        p(this.ac);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onActivityResume() {
        this.ah = false;
        if (this.aa instanceof d) {
            this.aa.setRenderMode(this.ae);
        }
        int i = this.ac;
        if (i == 0) {
            i = this.aG.getEngineIDWithType(0);
        }
        q(i);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void queueEvent(Runnable runnable) {
        this.aa.queueEvent(runnable);
    }

    /* compiled from: AMapDelegateImp.java */
    /* renamed from: com.amap.api.col.a$a */
    /* loaded from: classes.dex */
    public static abstract class AbstractRunnableC0011a implements Runnable {
        boolean b;
        boolean c;
        int d;
        int e;
        int f;
        int g;

        private AbstractRunnableC0011a() {
            this.b = false;
            this.c = false;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.b = false;
        }
    }

    public a(l lVar, Context context, AttributeSet attributeSet) {
        this.a = null;
        this.V = null;
        this.aL = false;
        this.aF = context;
        AEUtil.init(this.aF);
        g.b = ga.c(context);
        da.a(this.aF);
        this.aG = new GLMapEngine(this.aF, this);
        this.aa = lVar;
        lVar.setRenderer(this.aH);
        this.c = new eg(this.aF, this);
        this.c.d().a(new c());
        this.d = new u(this.aF, this);
        this.e = new p(this.aF, this);
        this.aG.setMapCoreListener(this);
        this.aa.setRenderMode(0);
        this.aL = false;
        this.aH.setRenderFps(15.0f);
        this.aG.setMapListener(this);
        this.a = this.c;
        this.V = new cr(this, context);
        this.ay = new f(this.aF, this);
        this.am = new ad(this.aF);
    }

    @Override // com.amap.api.col.k
    public GLMapEngine a() {
        return this.aG;
    }

    @Override // com.amap.api.col.k
    public void a(int i, int i2) {
        if (this.aX == 0 || i2 != 5) {
            this.aX = i2;
        }
    }

    public void a(final int i, final boolean z) {
        if (b(i, 2) != z) {
            if (!this.aR || !this.aS || !this.b) {
                this.bc.c = z;
                this.bc.b = true;
                this.bc.g = i;
                return;
            }
            queueEvent(new Runnable() { // from class: com.amap.api.col.a.6
                @Override // java.lang.Runnable
                public void run() {
                    int i2 = 0;
                    try {
                        a.this.b(i, 2, z);
                        if (!z) {
                            i2 = 1;
                        }
                        a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SETTRAFFICTEXTURE, i2, 0, 0, 0);
                        a.this.resetRenderTimeLong();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    @Override // com.amap.api.col.k
    public float a(int i) {
        if (this.aR) {
            return this.aG.getMapZoomer(i);
        }
        return 0.0f;
    }

    public float b(int i) {
        if (this.aR) {
            return this.aG.getMapZoomer(i);
        }
        if (this.ai != null) {
            return getMapConfig().getS_z();
        }
        return 0.0f;
    }

    public boolean a(int i, int i2, int i3) {
        CameraUpdateMessage a;
        if (!this.aR || ((int) b(i)) >= this.aG.getMaxZoomLevel(i)) {
            return false;
        }
        try {
            if (this.aj) {
                a = z.a(1.0f, (Point) null);
            } else if (!this.Y.isZoomInByScreenCenter()) {
                this.o.x = i2;
                this.o.y = i3;
                a = z.a(1.0f, this.o);
            } else {
                a = z.a(1.0f, (Point) null);
            }
            b(a);
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImp", "onDoubleTap");
            th.printStackTrace();
        }
        p();
        return true;
    }

    @Override // com.amap.api.col.k
    public void c(int i) {
        if (this.aR && ((int) b(i)) > this.aG.getMinZoomLevel(i)) {
            try {
                b(z.b());
            } catch (Throwable th) {
                gr.b(th, "AMapDelegateImp", "onDoubleTap");
                th.printStackTrace();
            }
            p();
        }
    }

    @Override // com.amap.api.col.k
    public boolean d(int i) {
        return false;
    }

    @Override // com.amap.api.col.k
    public boolean e(int i) {
        return b(i, 6);
    }

    private void s(final int i) {
        if (this.aR) {
            this.j.a();
            this.aI = true;
            this.aN = true;
            try {
                stopAnimation();
            } catch (RemoteException e) {
            }
            queueEvent(new Runnable() { // from class: com.amap.api.col.a.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        a.this.aG.clearAllMessages(i);
                        a.this.aG.setParamater(i, 4001, 1, 0, 0, 0);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    private void t(final int i) {
        this.aI = true;
        this.aN = false;
        if (this.ar) {
            this.ar = false;
        }
        if (this.aq) {
            this.aq = false;
        }
        if (this.as) {
            this.as = false;
        }
        this.an = false;
        if (!(this.F == null || this.ao == null)) {
            try {
                this.F.onMarkerDragEnd(this.ao);
            } catch (Throwable th) {
                gr.b(th, "AMapDelegateImp", "OnMarkerDragListener.onMarkerDragEnd");
                th.printStackTrace();
            }
            this.ao = null;
        }
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.aG.setParamater(i, 4001, 0, 0, 0, 0);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        });
        this.aa.postDelayed(new Runnable() { // from class: com.amap.api.col.a.9
            @Override // java.lang.Runnable
            public void run() {
                a.this.aP = 1;
            }
        }, 300L);
    }

    private void a(MotionEvent motionEvent) throws RemoteException {
        if (this.an && this.ao != null && this.ap != null) {
            LatLng b2 = this.ap.b();
            LatLng position = this.ap.getPosition();
            DPoint obtain = DPoint.obtain();
            b((int) motionEvent.getX(), (int) (motionEvent.getY() - 60.0f), obtain);
            LatLng latLng = new LatLng((position.latitude + obtain.y) - b2.latitude, (position.longitude + obtain.x) - b2.longitude);
            obtain.recycle();
            this.ao.setPosition(latLng);
            if (this.F != null) {
                this.F.onMarkerDrag(this.ao);
            }
        }
    }

    @Override // com.amap.api.col.k
    public GLMapState b() {
        if (this.aG != null) {
            return this.aG.getMapState(1);
        }
        return null;
    }

    @Override // com.amap.api.col.k
    public int c() {
        return this.y;
    }

    @Override // com.amap.api.col.k
    public int d() {
        return this.z;
    }

    @Override // com.amap.api.col.k
    public int e() {
        return this.A;
    }

    @Override // com.amap.api.col.k
    public void a(Location location) throws RemoteException {
        if (location != null) {
            try {
                if (!this.ab || this.am == null) {
                    if (this.al != null) {
                        this.al.b();
                    }
                    this.al = null;
                    return;
                }
                if (this.al == null && this.al == null) {
                    this.al = new cn(this, this.aF);
                }
                if (!(location.getLongitude() == 0.0d || location.getLatitude() == 0.0d)) {
                    this.al.a(location);
                }
                if (this.O != null) {
                    this.O.onMyLocationChange(location);
                }
                p();
            } catch (Throwable th) {
                gr.b(th, "AMapDelegateImp", "showMyLocationOverlay");
                th.printStackTrace();
            }
        }
    }

    @Override // com.amap.api.col.k
    public boolean a(String str) throws RemoteException {
        p();
        return this.f.d(str);
    }

    @Override // com.amap.api.col.k
    public void f() {
        this.f.c();
    }

    @Override // com.amap.api.col.k
    public void a(double d2, double d3, IPoint iPoint) {
        GLMapState.lonlat2Geo(d3, d2, iPoint);
    }

    @Override // com.amap.api.col.k
    public void a(int i, int i2, FPoint fPoint) {
        this.aG.p20ToMapPoint(1, i2, i, fPoint);
    }

    @Override // com.amap.api.col.k
    public void a(int i, int i2, DPoint dPoint) {
        GLMapState.geo2LonLat(i, i2, dPoint);
    }

    @Override // com.amap.api.col.k
    public float g() {
        return b(this.ac);
    }

    @Override // com.amap.api.col.k
    public n h() {
        return this.Y;
    }

    @Override // com.amap.api.col.k
    public void a(ce ceVar) throws RemoteException {
        if (ceVar != null && this.a != null) {
            this.a.a(ceVar);
        }
    }

    @Override // com.amap.api.col.k
    public void i() {
        if (this.a != null) {
            this.a.b();
        }
    }

    @Override // com.amap.api.col.k
    public void a(double d2, double d3, FPoint fPoint) {
        if (this.aR && this.aG != null) {
            Point LatLongToPixels = VirtualEarthProjection.LatLongToPixels(d2, d3, 20);
            this.aG.p20ToMapPoint(1, LatLongToPixels.x, LatLongToPixels.y, fPoint);
        }
    }

    @Override // com.amap.api.col.k
    public void b(int i, int i2, DPoint dPoint) {
        if (this.aR && this.aG != null) {
            Point point = new Point();
            this.aG.screenToP20Point(1, i, i2, point);
            DPoint PixelsToLatLong = VirtualEarthProjection.PixelsToLatLong(point.x, point.y, 20);
            dPoint.x = PixelsToLatLong.x;
            dPoint.y = PixelsToLatLong.y;
            PixelsToLatLong.recycle();
        }
    }

    protected void a(GLMapState gLMapState, int i, int i2, DPoint dPoint) {
        if (this.aR && this.aG != null) {
            Point point = new Point();
            gLMapState.screenToP20Point(i, i2, point);
            DPoint PixelsToLatLong = VirtualEarthProjection.PixelsToLatLong(point.x, point.y, 20);
            dPoint.x = PixelsToLatLong.x;
            dPoint.y = PixelsToLatLong.y;
            PixelsToLatLong.recycle();
        }
    }

    @Override // com.amap.api.col.k
    public void b(double d2, double d3, IPoint iPoint) {
        if (this.aR && this.aG != null) {
            try {
                Point LatLongToPixels = VirtualEarthProjection.LatLongToPixels(d2, d3, 20);
                FPoint obtain = FPoint.obtain();
                this.aG.p20ToScreenPoint(1, LatLongToPixels.x, LatLongToPixels.y, obtain);
                if (obtain.x == -10000.0f && obtain.y == -10000.0f) {
                    GLMapState newMapState = this.aG.getNewMapState(1);
                    newMapState.setCameraDegree(0.0f);
                    newMapState.recalculate();
                    newMapState.p20ToScreenPoint(LatLongToPixels.x, LatLongToPixels.y, obtain);
                    newMapState.recycle();
                }
                iPoint.x = (int) obtain.x;
                iPoint.y = (int) obtain.y;
                obtain.recycle();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.amap.api.col.k
    public void a(int i, int i2, IPoint iPoint) {
        if (this.aR && this.aG != null) {
            this.aG.screenToP20Point(1, i, i2, iPoint);
        }
    }

    public void a(int i, int i2, PointF pointF) {
        if (this.aR && this.aG != null) {
            this.aG.p20ToScreenPoint(1, i, i2, pointF);
        }
    }

    @Override // com.amap.api.col.k
    public void j() {
        if (this.aR) {
            this.n.sendEmptyMessage(18);
        }
    }

    @Override // com.amap.api.col.k
    public void a(boolean z) {
        if (!this.ad && this.c != null) {
            this.c.b(z);
        }
    }

    @Override // com.amap.api.col.k
    public void b(boolean z) {
        if (!this.ad && this.c != null) {
            this.c.a(z);
        }
    }

    @Override // com.amap.api.col.k
    public void c(boolean z) {
        if (!this.ad && this.c != null) {
            this.c.c(z);
        }
    }

    @Override // com.amap.api.col.k
    public void d(boolean z) {
        if (!this.ad && this.c != null) {
            this.c.d(z);
        }
    }

    @Override // com.amap.api.col.k
    public void e(boolean z) {
        if (!this.ad && this.c != null) {
            this.c.e(z);
        }
    }

    @Override // com.amap.api.col.k
    public void f(int i) {
        if (!this.ad && this.c != null) {
            this.c.a(i);
        }
    }

    @Override // com.amap.api.col.k
    public LatLngBounds a(LatLng latLng, float f, float f2, float f3) {
        int mapWidth = getMapWidth();
        int mapHeight = getMapHeight();
        if (mapWidth <= 0 || mapHeight <= 0 || this.ad) {
            return null;
        }
        float a = dt.a(this.ai, f);
        GLMapState gLMapState = new GLMapState(1, this.aG.getMapEnginePtr());
        if (latLng != null) {
            IPoint obtain = IPoint.obtain();
            GLMapState.lonlat2Geo(latLng.longitude, latLng.latitude, obtain);
            gLMapState.setCameraDegree(0.0f);
            gLMapState.setMapAngle(0.0f);
            gLMapState.setMapGeoCenter(obtain.x, obtain.y);
            gLMapState.setMapZoomer(a);
            gLMapState.recalculate();
            obtain.recycle();
        }
        DPoint obtain2 = DPoint.obtain();
        a(gLMapState, 0, 0, obtain2);
        LatLng latLng2 = new LatLng(obtain2.y, obtain2.x, false);
        a(gLMapState, mapWidth, mapHeight, obtain2);
        LatLng latLng3 = new LatLng(obtain2.y, obtain2.x, false);
        obtain2.recycle();
        gLMapState.recycle();
        return LatLngBounds.builder().include(latLng3).include(latLng2).build();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.aR || !this.aM) {
            return false;
        }
        this.bn.mGestureState = 3;
        this.bn.mGestureType = 8;
        this.bn.mLocation = new float[]{motionEvent.getX(), motionEvent.getY()};
        int a = a(this.bn);
        q();
        switch (motionEvent.getAction() & 255) {
            case 0:
                r();
                s(a);
                break;
            case 1:
                t(a);
                break;
        }
        if (motionEvent.getAction() != 2 || !this.an) {
            if (this.aI) {
                try {
                    this.j.a(motionEvent);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (this.J == null) {
                return true;
            }
            this.n.removeMessages(14);
            Message obtainMessage = this.n.obtainMessage();
            obtainMessage.what = 14;
            obtainMessage.obj = MotionEvent.obtain(motionEvent);
            obtainMessage.sendToTarget();
            return true;
        }
        try {
            a(motionEvent);
            return true;
        } catch (Throwable th2) {
            gr.b(th2, "AMapDelegateImp", "onDragMarker");
            th2.printStackTrace();
            return true;
        }
    }

    @Override // com.amap.api.col.k
    public void b(int i, int i2, PointF pointF) {
        if (this.aR && !this.ah && this.aG != null) {
            this.aG.screenToMapGPoint(1, i, i2, pointF);
        }
    }

    @Override // com.amap.api.col.k
    public void a(float f, float f2, IPoint iPoint) {
        if (this.aR && !this.ah && this.aG != null) {
            this.aG.mapToP20Point(1, f, f2, iPoint);
        }
    }

    @Override // com.amap.api.col.k
    public float g(int i) {
        if (!this.aR || this.ah || this.aG == null) {
            return 0.0f;
        }
        return this.aG.getMapState(1).getGLUnitWithWin(i);
    }

    public CameraPosition f(boolean z) {
        LatLng v;
        try {
            if (!this.aR || this.ah || this.aG == null) {
                DPoint obtain = DPoint.obtain();
                a(this.ai.getS_x(), this.ai.getS_y(), obtain);
                LatLng latLng = new LatLng(obtain.y, obtain.x);
                obtain.recycle();
                return CameraPosition.builder().target(latLng).bearing(this.ai.getS_r()).tilt(this.ai.getS_c()).zoom(this.ai.getS_z()).build();
            }
            if (z) {
                DPoint obtain2 = DPoint.obtain();
                b(this.ai.getAnchorX(), this.ai.getAnchorY(), obtain2);
                v = new LatLng(obtain2.y, obtain2.x, false);
                obtain2.recycle();
            } else {
                v = v();
            }
            return CameraPosition.builder().target(v).bearing(this.aG.getMapAngle(1)).tilt(this.aG.getCameraDegree(1)).zoom(this.aG.getMapZoomer(1)).build();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private LatLng v() {
        GLMapState mapState;
        if (!this.aR || this.aG == null || (mapState = this.aG.getMapState(1)) == null) {
            DPoint PixelsToLatLong = VirtualEarthProjection.PixelsToLatLong(this.ai.getS_x(), this.ai.getS_y(), 20);
            LatLng latLng = new LatLng(PixelsToLatLong.y, PixelsToLatLong.x, false);
            PixelsToLatLong.recycle();
            return latLng;
        }
        Point mapGeoCenter = mapState.getMapGeoCenter();
        DPoint PixelsToLatLong2 = VirtualEarthProjection.PixelsToLatLong(mapGeoCenter.x, mapGeoCenter.y, 20);
        LatLng latLng2 = new LatLng(PixelsToLatLong2.y, PixelsToLatLong2.x, false);
        PixelsToLatLong2.recycle();
        return latLng2;
    }

    @Override // com.amap.api.col.k
    public Point k() {
        return this.c != null ? this.c.a() : new Point();
    }

    @Override // com.amap.api.col.k
    public View l() {
        if (this.aa instanceof View) {
            return (View) this.aa;
        }
        return null;
    }

    @Override // com.amap.api.col.k
    public boolean m() {
        if (!(this.aG == null || this.aG.getMapZoomer(1) < 17.0f || this.g == null || this.g.g == null)) {
            FPoint obtain = FPoint.obtain();
            a(this.g.g.x, this.g.g.y, (PointF) obtain);
            if (this.av.contains((int) obtain.x, (int) obtain.y)) {
                return true;
            }
        }
        return false;
    }

    private synchronized void w() {
        synchronized (this.aE) {
            int size = this.aE.size();
            for (int i = 0; i < size; i++) {
                this.aE.get(i).e().recycle();
            }
            this.aE.clear();
        }
    }

    @Override // com.amap.api.col.k
    public void a(r rVar) {
        if (rVar != null && rVar.f() != 0) {
            synchronized (this.aE) {
                this.aE.add(rVar);
            }
        }
    }

    @Override // com.amap.api.col.k
    public void b(String str) {
        int i;
        synchronized (this.aE) {
            int size = this.aE.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    i = -1;
                    break;
                } else if (this.aE.get(i2).j().equals(str)) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i >= 0) {
                this.aE.remove(i);
            }
        }
    }

    @Override // com.amap.api.col.k
    public r a(BitmapDescriptor bitmapDescriptor) {
        r rVar;
        if (bitmapDescriptor == null || bitmapDescriptor.getBitmap() == null || bitmapDescriptor.getBitmap().isRecycled()) {
            return null;
        }
        synchronized (this.aE) {
            int i = 0;
            while (true) {
                if (i >= this.aE.size()) {
                    rVar = null;
                    break;
                }
                rVar = this.aE.get(i);
                if (rVar.e().equals(bitmapDescriptor)) {
                    break;
                }
                i++;
            }
        }
        return rVar;
    }

    @Override // com.amap.api.col.k
    public int a(IMarkerAction iMarkerAction, Rect rect) {
        return 0;
    }

    @Override // com.amap.api.col.k
    public void h(int i) {
        if (this.c != null) {
            this.c.b(i);
        }
    }

    @Override // com.amap.api.col.k
    public void i(int i) {
        if (this.c != null) {
            this.c.c(i);
        }
    }

    @Override // com.amap.api.col.k
    public void j(int i) {
        if (this.c != null) {
            this.c.d(i);
        }
    }

    @Override // com.amap.api.col.k
    public float k(int i) {
        if (this.c != null) {
            return this.c.e(i);
        }
        return 0.0f;
    }

    @Override // com.amap.api.col.k
    public void a(int i, float f) {
        if (this.c != null) {
            this.c.a(i, f);
        }
    }

    @Override // com.amap.api.col.k
    public int n() {
        return this.aD;
    }

    public void a(Runnable runnable) {
        if (this.aa != null) {
            this.aa.post(runnable);
        }
    }

    @Override // com.amap.api.col.k
    public void a(int i, MotionEvent motionEvent) {
        try {
            this.aq = false;
            l(i);
            this.ap = this.e.a(motionEvent);
            if (this.ap != null && this.ap.isDraggable()) {
                this.ao = new Marker(this.ap);
                LatLng position = this.ao.getPosition();
                LatLng b2 = this.ap.b();
                IPoint obtain = IPoint.obtain();
                b(b2.latitude, b2.longitude, obtain);
                obtain.y -= 60;
                DPoint obtain2 = DPoint.obtain();
                b(obtain.x, obtain.y, obtain2);
                this.ao.setPosition(new LatLng((position.latitude + obtain2.y) - b2.latitude, (position.longitude + obtain2.x) - b2.longitude));
                this.e.a(this.ap);
                if (this.F != null) {
                    this.F.onMarkerDragStart(this.ao);
                }
                this.an = true;
                obtain.recycle();
                obtain2.recycle();
            } else if (this.L != null) {
                DPoint obtain3 = DPoint.obtain();
                b((int) motionEvent.getX(), (int) motionEvent.getY(), obtain3);
                this.L.onMapLongClick(new LatLng(obtain3.y, obtain3.x));
                this.ar = true;
                obtain3.recycle();
            }
            this.aH.resetTickCount(30);
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImp", "onLongPress");
            th.printStackTrace();
        }
    }

    @Override // com.amap.api.col.k
    public boolean b(int i, MotionEvent motionEvent) {
        if (this.aR) {
            a(i, (int) motionEvent.getX(), (int) motionEvent.getY());
        }
        return false;
    }

    @Override // com.amap.api.col.k
    public boolean c(int i, MotionEvent motionEvent) {
        if (!this.aR) {
            return false;
        }
        try {
            if (g(motionEvent) || e(motionEvent) || f(motionEvent) || d(motionEvent)) {
                return true;
            }
            b(motionEvent);
            return true;
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImp", "onSingleTapUp");
            th.printStackTrace();
            return true;
        }
    }

    private void b(final MotionEvent motionEvent) {
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.10
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Message obtain = Message.obtain();
                    Poi b2 = a.this.b((int) motionEvent.getX(), (int) motionEvent.getY(), 25);
                    if (a.this.K == null) {
                        a.this.c(motionEvent);
                    } else if (b2 != null) {
                        obtain.what = 20;
                        obtain.obj = b2;
                        a.this.n.sendMessage(obtain);
                    } else {
                        a.this.c(motionEvent);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public void c(final MotionEvent motionEvent) {
        this.n.post(new Runnable() { // from class: com.amap.api.col.a.12
            @Override // java.lang.Runnable
            public void run() {
                Message obtain = Message.obtain();
                obtain.what = 19;
                obtain.arg1 = (int) motionEvent.getX();
                obtain.arg2 = (int) motionEvent.getY();
                a.this.n.sendMessage(obtain);
            }
        });
    }

    private boolean d(MotionEvent motionEvent) {
        cg a;
        if (this.E != null) {
            DPoint obtain = DPoint.obtain();
            b((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
            LatLng latLng = new LatLng(obtain.y, obtain.x);
            obtain.recycle();
            if (!(latLng == null || (a = this.f.a(latLng)) == null)) {
                this.E.onPolylineClick(new Polyline((cj) a));
            }
        }
        return false;
    }

    private boolean e(MotionEvent motionEvent) throws RemoteException {
        LatLng b2;
        if (this.e.b(motionEvent)) {
            ce e = this.e.e();
            if (e == null) {
                return true;
            }
            try {
                Marker marker = new Marker(e);
                if (this.D == null || (!this.D.onMarkerClick(marker) && this.e.h() > 0)) {
                    a(e);
                    if (!e.g() && (b2 = e.b()) != null) {
                        IPoint obtain = IPoint.obtain();
                        a(b2.latitude, b2.longitude, obtain);
                        a(z.a(obtain));
                    }
                    this.e.a(e);
                    return true;
                }
                this.e.a(e);
                return true;
            } catch (Throwable th) {
                gr.b(th, "AMapDelegateImp", "onMarkerTap");
                th.printStackTrace();
            }
        }
        return false;
    }

    private boolean f(MotionEvent motionEvent) {
        if (this.m == null) {
            return false;
        }
        IPoint obtain = IPoint.obtain();
        if (this.aG != null) {
            a((int) motionEvent.getX(), (int) motionEvent.getY(), obtain);
        }
        boolean a = this.m.a(obtain);
        obtain.recycle();
        return a;
    }

    private boolean g(MotionEvent motionEvent) throws RemoteException {
        if (this.a == null || !this.a.a(motionEvent)) {
            return false;
        }
        if (this.M == null) {
            return true;
        }
        ce e = this.e.e();
        if (!e.isVisible()) {
            return true;
        }
        this.M.onInfoWindowClick(new Marker(e));
        return true;
    }

    private void h(int i, boolean z) {
        this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SETISSTIMAP, z ? 1 : 0, 0, 0, 0);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void drawFrame(GL10 gl10) {
        if (!this.ah && !this.ad) {
            a(1, gl10);
            this.aG.drawFrame();
            this.aG.pushRendererState();
            a(gl10);
            y();
            if (!this.aU) {
                this.aU = true;
                a(new Runnable() { // from class: com.amap.api.col.a.13
                    @Override // java.lang.Runnable
                    public void run() {
                        if (a.this.af != null) {
                            a.this.af.onDrawFrameFirst(1);
                        }
                    }
                });
            }
            if (this.h != null) {
                this.h.onDrawFrame(gl10);
            }
            this.aG.popRendererState();
        }
    }

    private void a(int i, GL10 gl10) {
        if (this.i != -1) {
            this.aH.setRenderFps(this.i);
            p();
        } else if (this.aG.isInMapAction(i) || this.aN) {
            this.aH.setRenderFps(40.0f);
        } else if (this.aG.isInMapAnimation(i)) {
            this.aH.setRenderFps(30.0f);
            this.aH.resetTickCount(15);
        } else if (this.aL) {
            this.aH.setRenderFps(10.0f);
        } else {
            this.aH.setRenderFps(15.0f);
        }
    }

    private void a(GL10 gl10) {
        int i = 1;
        if (this.au) {
            if (!this.aG.canStopMapRender(1)) {
                i = 0;
            }
            Message obtainMessage = this.n.obtainMessage(15, dt.a(0, 0, getMapWidth(), getMapHeight()));
            obtainMessage.arg1 = i;
            obtainMessage.sendToTarget();
            this.au = false;
        }
    }

    public void o() {
        if (this.ai.getMapRect() == null || this.aA) {
            x();
            this.aA = false;
        }
        GLMapState mapState = this.aG.getMapState(1);
        if (mapState != null) {
            Point mapGeoCenter = mapState.getMapGeoCenter();
            this.ai.setS_x(mapGeoCenter.x);
            this.ai.setS_y(mapGeoCenter.y);
            this.ai.setS_z(mapState.getMapZoomer());
            this.ai.setS_c(mapState.getCameraDegree());
            this.ai.setS_r(mapState.getMapAngle());
            if (this.i != -1) {
                this.aH.setRenderFps(this.i);
            } else if (this.aG.getAnimateionsCount() == 0) {
                this.aH.setRenderFps(15.0f);
            }
            if (this.ai.isMapStateChange()) {
                DPoint PixelsToLatLong = VirtualEarthProjection.PixelsToLatLong(mapGeoCenter.x, mapGeoCenter.y, 20);
                CameraPosition cameraPosition = new CameraPosition(new LatLng(PixelsToLatLong.y, PixelsToLatLong.x, false), this.ai.getS_z(), this.ai.getS_c(), this.ai.getS_r());
                PixelsToLatLong.recycle();
                Message obtainMessage = this.n.obtainMessage();
                obtainMessage.what = 10;
                obtainMessage.obj = cameraPosition;
                this.n.sendMessage(obtainMessage);
                this.aO = true;
                j();
                x();
                try {
                    if (this.Y.isZoomControlsEnabled() && this.ai.isNeedUpdateZoomControllerState()) {
                        this.ag.invalidateZoomController(this.ai.getS_z());
                    }
                    if (this.ai.getChangeGridRatio() != 1.0d) {
                        g(true);
                    }
                    if (this.Y.isCompassEnabled() && (this.ai.isTiltChanged() || this.ai.isBearingChanged())) {
                        this.ag.invalidateCompassView();
                    }
                    if (this.Y.isScaleControlsEnabled()) {
                        this.ag.invalidateScaleView();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else if (this.aO && !this.aN && this.aG.getAnimateionsCount() == 0 && this.aG.getStateMessageCount() == 0) {
                this.aO = false;
                onChangeFinish();
            }
        }
    }

    private void x() {
        try {
            this.ai.setMapRect(dt.a((k) this, true));
            GLMapState mapState = this.aG.getMapState(1);
            if (mapState != null) {
                mapState.getPixel20Bound(this.p);
                this.ai.getGeoRectangle().updateRect(this.p, this.ai.getS_x(), this.ai.getS_y());
                this.ai.setMapPerPixelUnitLength(mapState.getGLUnitWithWin(1));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void y() {
        final ec c2 = this.c.c();
        if (c2 != null && c2.getVisibility() != 8) {
            if (!this.ak) {
                this.n.sendEmptyMessage(16);
                this.ak = true;
                g(true);
            }
            this.n.post(new Runnable() { // from class: com.amap.api.col.a.14
                @Override // java.lang.Runnable
                public void run() {
                    if (!a.this.ah) {
                        try {
                            if (a.this.g != null) {
                                a.this.setIndoorBuildingInfo(a.this.g);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        c2.a(false);
                    }
                }
            });
        }
    }

    void g(boolean z) {
        this.n.obtainMessage(17, z ? 1 : 0, 0).sendToTarget();
    }

    public void l(final int i) {
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.15
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.aR) {
                    a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_CLEAN_SELECTED_SUBWAY, 0, 0, 0, 0);
                }
            }
        });
    }

    @Override // com.autonavi.ae.gmap.callback.GLMapCoreCallback
    public void clearException(int i) {
    }

    @Override // com.autonavi.ae.gmap.callback.GLMapCoreCallback
    public void onException(int i, int i2) {
    }

    public Poi b(int i, int i2, int i3) {
        MapLabelItem mapLabelItem;
        if (!this.aR) {
            return null;
        }
        try {
            ArrayList<MapLabelItem> a = a(1, i, i2, i3);
            if (a == null || a.size() <= 0) {
                mapLabelItem = null;
            } else {
                mapLabelItem = a.get(0);
            }
            if (mapLabelItem == null) {
                return null;
            }
            DPoint PixelsToLatLong = VirtualEarthProjection.PixelsToLatLong(mapLabelItem.pixel20X, mapLabelItem.pixel20Y, 20);
            Poi poi = new Poi(mapLabelItem.name, new LatLng(PixelsToLatLong.y, PixelsToLatLong.x, false), mapLabelItem.poiid);
            PixelsToLatLong.recycle();
            return poi;
        } catch (Throwable th) {
            return null;
        }
    }

    public ArrayList<MapLabelItem> a(int i, int i2, int i3, int i4) {
        if (!this.aR) {
            return null;
        }
        ArrayList<MapLabelItem> arrayList = new ArrayList<>();
        byte[] labelBuffer = this.aG.getLabelBuffer(i, i2, i3, i4);
        if (labelBuffer == null) {
            return null;
        }
        int i5 = 4;
        int i6 = GLConvertUtil.getInt(labelBuffer, 0) >= 1 ? 1 : 0;
        for (int i7 = 0; i7 < i6; i7++) {
            MapLabelItem mapLabelItem = new MapLabelItem();
            int i8 = GLConvertUtil.getInt(labelBuffer, i5);
            int i9 = i5 + 4;
            int i10 = GLConvertUtil.getInt(labelBuffer, i9);
            int i11 = i9 + 4;
            mapLabelItem.x = i8;
            mapLabelItem.y = this.aa.getHeight() - i10;
            mapLabelItem.pixel20X = GLConvertUtil.getInt(labelBuffer, i11);
            int i12 = i11 + 4;
            mapLabelItem.pixel20Y = GLConvertUtil.getInt(labelBuffer, i12);
            int i13 = i12 + 4;
            mapLabelItem.type = GLConvertUtil.getInt(labelBuffer, i13);
            int i14 = i13 + 4;
            mapLabelItem.mSublayerId = GLConvertUtil.getInt(labelBuffer, i14);
            int i15 = i14 + 4;
            mapLabelItem.mIsFouces = labelBuffer[i15] != 0;
            int i16 = i15 + 1;
            if (labelBuffer[i16] == 0) {
                mapLabelItem.poiid = null;
            } else {
                String str = "";
                for (int i17 = 0; i17 < 20 && labelBuffer[i17 + i16] != 0; i17++) {
                    str = str + ((char) labelBuffer[i17 + i16]);
                }
                mapLabelItem.poiid = str;
            }
            int i18 = i16 + 20;
            i5 = i18 + 1;
            byte b2 = labelBuffer[i18];
            StringBuffer stringBuffer = new StringBuffer();
            for (int i19 = 0; i19 < b2; i19++) {
                stringBuffer.append((char) GLConvertUtil.getShort(labelBuffer, i5));
                i5 += 2;
            }
            mapLabelItem.name = stringBuffer.toString();
            arrayList.add(mapLabelItem);
        }
        return arrayList;
    }

    @Override // com.amap.api.col.k
    public float m(int i) {
        if (this.aR) {
            return this.aG.getMapAngle(i);
        }
        return 0.0f;
    }

    @Override // com.amap.api.col.k
    public Point n(int i) {
        if (this.aR) {
            return this.aG.getMapCenter(i);
        }
        return null;
    }

    @Override // com.amap.api.col.k
    public float o(int i) {
        if (this.aR) {
            return this.aG.getCameraDegree(i);
        }
        return 0.0f;
    }

    @Override // com.amap.api.col.k
    public void a(int i, GestureMapMessage gestureMapMessage) {
        if (this.aR) {
            try {
                gestureMapMessage.isUseAnchor = this.aj;
                gestureMapMessage.anchorX = this.ai.getAnchorX();
                gestureMapMessage.anchorY = this.ai.getAnchorY();
                this.aG.addGestureMessage(i, gestureMapMessage, this.Y.isGestureScaleByMapCenter(), this.ai.getAnchorX(), this.ai.getAnchorY());
            } catch (RemoteException e) {
            }
        }
    }

    public void p(int i) {
        if (this.aH != null) {
            this.aH.renderPause();
        }
        r(i);
        NetworkState.getInstance().setNetWorkChangeListener(null);
    }

    public void q(int i) {
        r(i);
        if (this.aH != null) {
            this.aH.renderResume();
        }
        NetworkState.getInstance().setNetWorkChangeListener(this);
    }

    @Override // com.autonavi.ae.gmap.callback.GLMapCoreCallback
    public void resetRenderTimeLong() {
        if (this.aH != null) {
            this.aH.resetTickCount(6);
        }
    }

    @Override // com.autonavi.ae.gmap.callback.GLMapCoreCallback
    public void resetRenderTimeLongLong() {
        if (this.aH != null) {
            this.aH.resetTickCount(30);
        }
    }

    public void p() {
        if (this.aH != null) {
            this.aH.resetTickCount(2);
        }
    }

    public void q() {
        if (this.aH != null) {
            this.aH.resetTickCount(2);
        }
    }

    @Override // com.autonavi.ae.gmap.callback.GLMapCoreCallback
    public void resetRenderTime(boolean z) {
        if (this.aH == null) {
            return;
        }
        if (z) {
            this.aH.resetTickCount(10);
        } else {
            this.aH.resetTickCount(2);
        }
    }

    public void r() {
        if (this.aR && this.aH != null && !this.aH.isRenderPause()) {
            requestRender();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void requestRender() {
        if (this.aH != null && !this.aH.isRenderPause()) {
            this.aa.requestRender();
        }
    }

    public void b(int i, boolean z) {
        if (z) {
            this.aG.setParamater(i, 1024, 1, 0, 0, 0);
        } else {
            this.aG.setParamater(i, 1024, 0, 0, 0, 0);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getRenderMode() {
        return this.aa.getRenderMode();
    }

    private void z() {
        if (!az) {
            try {
                this.ay.setName("AuthThread");
                this.ay.start();
                az = true;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.amap.api.col.k
    public float s() {
        return this.aK;
    }

    public synchronized void b(int i, int i2, int i3, int i4) {
        a(i, i2, i3, i4, false, false, (StyleItem[]) null);
    }

    public synchronized void a(final int i, final int i2, final int i3, final int i4, final boolean z, final boolean z2, final StyleItem[] styleItemArr) {
        this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_RESTORED_MAPMODESTATE, i2, i3, i4, 0);
        if (!this.aR) {
            this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_MAPMODESTATE, i2, i3, i4, 0);
        } else if (!this.aS || !this.aR || !this.b) {
            this.be.g = i;
            this.be.d = i2;
            this.be.e = i3;
            this.be.f = i4;
            this.be.b = true;
        } else {
            queueEvent(new Runnable() { // from class: com.amap.api.col.a.16
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (!z) {
                            int[] mapModeState = a.this.aG.getMapModeState(i, false);
                            if (mapModeState == null) {
                                return;
                            }
                            if (mapModeState[0] == i2 && mapModeState[1] == i3 && mapModeState[2] == i4) {
                                return;
                            }
                        }
                        a.this.aG.SetMapModeAndStyle(i, i2, i3, i4, z, z2, styleItemArr);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    public void r(final int i) {
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.18
            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.aG.clearAllMessages(i);
                    a.this.aG.clearAnimations(i, true);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    @Override // com.autonavi.ae.gmap.callback.GLMapCoreCallback
    public void postQueueEvent(Runnable runnable) {
        queueEvent(runnable);
    }

    @Override // com.autonavi.ae.gmap.callback.GLMapCoreCallback
    public void postOnUIThread(Runnable runnable) {
        a(runnable);
    }

    public void c(final int i, final boolean z) {
        b(i, 18, z);
        if (!this.aR || !this.aS) {
            this.bg.c = z;
            this.bg.b = true;
            this.bg.g = i;
            return;
        }
        p();
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.19
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (z) {
                        a.this.aG.setParamater(i, 1021, 1, 0, 0, 0);
                        a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT, 1, 0, 0, 0);
                    } else {
                        a.this.aG.setParamater(i, 1021, 0, 0, 0, 0);
                        a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT, 0, 0, 0, 0);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public void d(final int i, final boolean z) {
        if (!this.aR || !this.aS) {
            this.bh.c = z;
            this.bh.b = true;
            this.bh.g = i;
            return;
        }
        p();
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.20
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SHOW_BUILDING_NORMAL, 1, 0, 0, 0);
                } else {
                    a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SHOW_BUILDING_NORMAL, 0, 0, 0, 0);
                }
            }
        });
    }

    public void e(final int i, final boolean z) {
        if (!this.aR || !this.aS) {
            this.bi.c = z;
            this.bi.b = true;
            this.bi.g = i;
            return;
        }
        p();
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.21
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (z) {
                        a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SHOW_BUILDING_MARK, 1, 0, 0, 0);
                    } else {
                        a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SHOW_BUILDING_MARK, 0, 0, 0, 0);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public void f(final int i, final boolean z) {
        if (!this.aR || !this.aS) {
            this.bj.c = z;
            this.bj.b = true;
            this.bj.g = i;
            return;
        }
        p();
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.22
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (z) {
                        a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SHOW_BUILDING_POI, 1, 0, 0, 0);
                    } else {
                        a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SHOW_BUILDING_POI, 0, 0, 0, 0);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public void g(final int i, final boolean z) {
        b(i, 12, z);
        if (!this.aR || !this.aS) {
            this.bm.c = z;
            this.bm.b = true;
            this.bm.g = i;
            return;
        }
        p();
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.23
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (z) {
                        a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SHOW_BUILDING_TEXTURE, 1, 0, 0, 0);
                    } else {
                        a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SHOW_BUILDING_TEXTURE, 0, 0, 0, 0);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public synchronized void a(final int i, GL10 gl10, EGLConfig eGLConfig) {
        this.aQ = gl10;
        this.aS = false;
        this.k = this.aa.getWidth();
        this.l = this.aa.getHeight();
        this.aV = false;
        this.aG.initConnectionManager();
        u(i);
        a(new Runnable() { // from class: com.amap.api.col.a.24
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.af != null) {
                    a.this.af.onSurfaceCreated(i);
                }
            }
        });
        this.aR = true;
        try {
            this.q = gl10.glGetString(7937);
        } catch (Exception e) {
        }
        GLMapState mapState = this.aG.getMapState(1);
        if (!(mapState == null || mapState.getNativeInstance() == 0)) {
            mapState.setMapGeoCenter(this.ai.getS_x(), this.ai.getS_y());
            mapState.setMapAngle(this.ai.getS_r());
            mapState.setMapZoomer(this.ai.getS_z());
            mapState.setCameraDegree(this.ai.getS_c());
            this.aG.setMapState(1, mapState, false);
        }
        D();
        z();
        if (this.h != null) {
            this.h.onSurfaceCreated(gl10, eGLConfig);
        }
        p.b();
        ae.d();
        ct.c();
        dg.a();
        bz.e();
        AMapNativeRenderer.nativeDrawLineInit();
    }

    public void a(int i, GL10 gl10, int i2, int i3) {
        int i4 = 2;
        this.aV = false;
        this.k = i2;
        this.l = i3;
        this.av = new Rect(0, 0, i2, i3);
        if (!this.aR) {
            a(i, gl10, (EGLConfig) null);
        }
        if (!this.aS) {
            int i5 = this.aF.getResources().getDisplayMetrics().densityDpi;
            float f = this.aF.getResources().getDisplayMetrics().density;
            int i6 = 100;
            if (i5 <= 120) {
                i4 = 1;
            } else if (i5 <= 160) {
                if (Math.max(i2, i3) <= 480) {
                    i6 = 120;
                } else {
                    i6 = 100;
                }
                i4 = 1;
            } else if (i5 <= 240) {
                if (Math.min(i2, i3) >= 1000) {
                    i6 = 60;
                } else {
                    i6 = 70;
                }
            } else if (i5 <= 320) {
                i6 = 50;
                i4 = 3;
            } else if (i5 <= 480) {
                i6 = 50;
                i4 = 3;
            } else {
                i6 = 40;
                i4 = 4;
            }
            this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_MAP_VALUE, i6, (int) (f * 100.0f), (int) (f * 100.0f), i4);
            this.aK = i6 / 100.0f;
            if (this.ai != null) {
                this.ai.setMapZoomScale(this.aK);
            }
            this.aG.setParamater(i, 1001, 1, 0, 0, 0);
            if (this.aL) {
                this.aG.setParamater(i, 1900, 1, 15, 0, 0);
            } else {
                this.aG.setParamater(i, 1900, 0, 0, 0, 0);
            }
            if (b(i, 18)) {
                this.aG.setParamater(i, 1021, 1, 0, 0, 0);
                this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT, 1, 0, 0, 0);
            } else {
                this.aG.setParamater(i, 1021, 0, 0, 0, 0);
                this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT, 0, 0, 0, 0);
            }
            this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_ROADARROW, 1, 0, 0, 0);
            this.aG.setParamater(1, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_INDOOR, 0, 0, 0, 0);
            if (b(i, 16)) {
                this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_TRAFFIC, 1, 0, 0, 0);
            } else {
                this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_TRAFFIC, 0, 0, 0, 0);
            }
            this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SCENIC_WIDGET_ICON_CONTER, 17, 0, 0, 0);
            h(i, false);
        }
        this.aG.changeSurface(i, 0, 0, i2, i3, i2, i3);
        synchronized (this) {
            this.aS = true;
        }
        if (this.aY == 0 && this.aZ == 0) {
            this.ai.setAnchorX(i2 >> 1);
            this.ai.setAnchorY(i3 >> 1);
        } else {
            this.ai.setAnchorX(Math.max(1, Math.min(this.aY, i2 - 1)));
            this.ai.setAnchorY(Math.max(1, Math.min(this.aZ, i3 - 1)));
        }
        this.aG.setParamater(i, AMapException.CODE_AMAP_ENGINE_RESPONSE_ERROR, this.ai.getAnchorX(), this.ai.getAnchorY(), 0, 0);
        this.b = true;
        if (this.bg.b) {
            this.bg.run();
        }
        if (this.be.b) {
            this.be.run();
        }
        if (this.bc.b) {
            this.bc.run();
        }
        if (this.bf.b) {
            this.bf.run();
        }
        if (this.bb.b) {
            this.bb.run();
        }
        if (this.bh.b) {
            this.bh.run();
        }
        if (this.bi.b) {
            this.bi.run();
        }
        if (this.bj.b) {
            this.bj.run();
        }
        if (this.bm.b) {
            this.bm.run();
        }
        if (this.bk.b) {
            this.bk.run();
        }
        if (this.bd.b) {
            this.bd.run();
        }
        if (this.h != null) {
            this.h.onSurfaceChanged(gl10, i2, i3);
        }
        if (this.n != null) {
            this.n.post(this.bl);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void destorySurface(int i) {
        this.aW.lock();
        try {
            if (this.aR) {
                this.aG.destorySurface();
                VMapDataCache.getInstance().reset();
                if (this.af != null) {
                    this.af.onSurfaceDestroy(i);
                }
            }
            this.aR = false;
            this.aQ = null;
            this.aS = false;
            this.aV = false;
        } catch (Throwable th) {
        } finally {
            this.aW.unlock();
        }
    }

    @Override // com.amap.api.col.k
    public Context t() {
        return this.aF;
    }

    private void u(int i) {
        int i2;
        int i3;
        int i4 = 0;
        int[] mapModeState = this.aG.getMapModeState(i, false);
        if (mapModeState != null) {
            i3 = mapModeState[0];
            i2 = mapModeState[1];
            i4 = mapModeState[2];
        } else {
            i2 = 0;
            i3 = 0;
        }
        this.aG.loadStartResource(i, i3, i2, i4, b(i, 2), this.k, this.l);
    }

    public int a(int i, Rect rect, int i2, int i3) {
        if (this.aG == null || i < 0 || rect == null) {
            return 0;
        }
        int engineIDWithType = this.aG.getEngineIDWithType(i);
        if (!this.aG.isEngineCreated(engineIDWithType)) {
            this.aG.createEngineWithFrame(engineIDWithType, rect, i2, i3);
            this.aG.setOvelayBundle(engineIDWithType, new GLOverlayBundle<>(engineIDWithType, this));
            if (engineIDWithType == 2) {
                a(engineIDWithType, -1, false);
                u(engineIDWithType);
            }
            this.aG.setMapAngle(engineIDWithType, this.ai.getS_r());
            this.aG.setMapZoom(engineIDWithType, this.ai.getS_z());
            this.aG.setMapCenter(engineIDWithType, this.ai.getS_x(), this.ai.getS_y());
            this.aG.setCameraDegree(engineIDWithType, this.ai.getS_c());
            return engineIDWithType;
        }
        a(engineIDWithType, rect.left, rect.top, rect.width(), rect.height(), i2, i3);
        return engineIDWithType;
    }

    public void a(final int i, final int i2, final boolean z) {
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.26
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.aG != null) {
                    a.this.aG.setParamater(i, GLMapStaticValue.AM_PARAMETERNAME_SHOW_CONTENT, i2, z ? 1 : 0, 0, 0);
                }
            }
        });
    }

    @Override // com.amap.api.col.k
    public int a(EAMapPlatformGestureInfo eAMapPlatformGestureInfo) {
        if (this.aG != null) {
            return this.aG.getEngineIDWithGestureInfo(eAMapPlatformGestureInfo);
        }
        return 1;
    }

    public void a(final int i, final int i2, final int i3, final int i4, final int i5, final int i6, final int i7) {
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.27
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.aG != null) {
                    a.this.aG.setServiceViewRect(i, i2, i3, i4, i5, i6, i7);
                }
            }
        });
    }

    public void b(int i, int i2, boolean z) {
        if (this.aG != null) {
            this.aG.setSrvViewStateBoolValue(i, i2, z);
        }
    }

    private boolean b(int i, int i2) {
        if (this.aG != null) {
            return this.aG.getSrvViewStateBoolValue(i, i2);
        }
        return false;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public CameraPosition getCameraPosition() throws RemoteException {
        return f(this.aj);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getMaxZoomLevel() {
        if (this.ai != null) {
            return this.ai.getMaxZoomLevel();
        }
        return 19.0f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getMinZoomLevel() {
        if (this.ai != null) {
            return this.ai.getMinZoomLevel();
        }
        return 3.0f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void moveCamera(CameraUpdate cameraUpdate) throws RemoteException {
        if (cameraUpdate != null) {
            a(cameraUpdate.getCameraUpdateFactoryDelegate());
        }
    }

    @Override // com.amap.api.col.k
    public void a(CameraUpdateMessage cameraUpdateMessage) throws RemoteException {
        if (this.aG != null && !this.ad) {
            if (this.ah && this.aG.getStateMessageCount() > 0) {
                CameraUpdateMessage c2 = z.c();
                c2.nowType = CameraUpdateMessage.Type.changeGeoCenterZoomTiltBearing;
                c2.geoPoint = new Point(this.ai.getS_x(), this.ai.getS_y());
                c2.zoom = this.ai.getS_z();
                c2.bearing = this.ai.getS_r();
                c2.tilt = this.ai.getS_c();
                this.aG.addMessage(cameraUpdateMessage);
                while (this.aG.getStateMessageCount() > 0) {
                    if (this.aG.getStateMessage() != null) {
                        cameraUpdateMessage.mergeCameraUpdateDelegate(c2);
                    }
                }
                cameraUpdateMessage = c2;
            }
            p();
            this.aG.clearAnimations(1, false);
            cameraUpdateMessage.isChangeFinished = true;
            c(cameraUpdateMessage);
            this.aG.addMessage(cameraUpdateMessage);
        }
    }

    private void c(CameraUpdateMessage cameraUpdateMessage) {
        cameraUpdateMessage.isUseAnchor = this.aj;
        if (this.aj) {
            cameraUpdateMessage.anchorX = this.ai.getAnchorX();
            cameraUpdateMessage.anchorY = this.ai.getAnchorY();
        }
        if (cameraUpdateMessage.width == 0) {
            cameraUpdateMessage.width = getMapWidth();
        }
        if (cameraUpdateMessage.height == 0) {
            cameraUpdateMessage.height = getMapHeight();
        }
        cameraUpdateMessage.mapConfig = this.ai;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void animateCamera(CameraUpdate cameraUpdate) throws RemoteException {
        b(cameraUpdate.getCameraUpdateFactoryDelegate());
    }

    @Override // com.amap.api.col.k
    public void b(CameraUpdateMessage cameraUpdateMessage) throws RemoteException {
        a(cameraUpdateMessage, 250L, (AMap.CancelableCallback) null);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void animateCameraWithCallback(CameraUpdate cameraUpdate, AMap.CancelableCallback cancelableCallback) throws RemoteException {
        animateCameraWithDurationAndCallback(cameraUpdate, 250L, cancelableCallback);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void animateCameraWithDurationAndCallback(CameraUpdate cameraUpdate, long j, AMap.CancelableCallback cancelableCallback) {
        a(cameraUpdate.getCameraUpdateFactoryDelegate(), j, cancelableCallback);
    }

    public void a(CameraUpdateMessage cameraUpdateMessage, long j, AMap.CancelableCallback cancelableCallback) {
        if (cameraUpdateMessage != null && !this.ad) {
            if (this.ah || getMapHeight() == 0 || getMapWidth() == 0) {
                try {
                    a(cameraUpdateMessage);
                    if (cancelableCallback != null) {
                        cancelableCallback.onFinish();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else {
                synchronized (this.T) {
                    if (this.aG.isInMapAnimation(1) && this.S != null) {
                        this.S.onCancel();
                    }
                    p();
                    this.S = cancelableCallback;
                    c(cameraUpdateMessage);
                    this.aG.clearAnimations(1, false);
                    cameraUpdateMessage.generateMapAnimation(this.aG, (int) j);
                }
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void stopAnimation() throws RemoteException {
        if (this.aG != null && this.aG.getAnimateionsCount() > 0) {
            a(true, (CameraPosition) null);
            this.aG.clearAnimations(1, false);
            synchronized (this.T) {
                if (this.S != null) {
                    this.S.onCancel();
                    this.S = null;
                }
            }
        }
        p();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Polyline addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        p();
        cj a = this.f.a(polylineOptions);
        if (a != null) {
            return new Polyline(a);
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public NavigateArrow addNavigateArrow(NavigateArrowOptions navigateArrowOptions) throws RemoteException {
        p();
        cf a = this.f.a(navigateArrowOptions);
        if (a != null) {
            return new NavigateArrow(a);
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Polygon addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        p();
        ci a = this.f.a(polygonOptions);
        if (a != null) {
            return new Polygon(a);
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Circle addCircle(CircleOptions circleOptions) throws RemoteException {
        p();
        cb a = this.f.a(circleOptions);
        if (a != null) {
            return new Circle(a);
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Arc addArc(ArcOptions arcOptions) throws RemoteException {
        p();
        ca a = this.f.a(arcOptions);
        if (a != null) {
            return new Arc(a);
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        p();
        cc a = this.f.a(groundOverlayOptions);
        if (a != null) {
            return new GroundOverlay(a);
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public MultiPointOverlay addMultiPointOverlay(MultiPointOverlayOptions multiPointOverlayOptions) throws RemoteException {
        p();
        IMultiPointOverlay a = this.m.a(multiPointOverlayOptions);
        if (a != null) {
            return new MultiPointOverlay(a);
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Marker addMarker(MarkerOptions markerOptions) throws RemoteException {
        p();
        return this.e.a(markerOptions);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Text addText(TextOptions textOptions) throws RemoteException {
        p();
        return this.e.a(textOptions);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public ArrayList<Marker> addMarkers(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException {
        p();
        return this.e.a(arrayList, z);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        return this.d.a(tileOverlayOptions);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void clear() throws RemoteException {
        try {
            clear(false);
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImp", "clear");
            th.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0032 A[Catch: Throwable -> 0x0042, TryCatch #0 {Throwable -> 0x0042, blocks: (B:3:0x0001, B:6:0x000a, B:7:0x0016, B:9:0x0029, B:10:0x002e, B:12:0x0032, B:13:0x0037, B:15:0x003b), top: B:19:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0029 A[Catch: Throwable -> 0x0042, TryCatch #0 {Throwable -> 0x0042, blocks: (B:3:0x0001, B:6:0x000a, B:7:0x0016, B:9:0x0029, B:10:0x002e, B:12:0x0032, B:13:0x0037, B:15:0x003b), top: B:19:0x0001 }] */
    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void clear(boolean r4) throws android.os.RemoteException {
        /*
            r3 = this;
            r0 = 0
            r3.i()     // Catch: Throwable -> 0x0042
            com.amap.api.col.cn r1 = r3.al     // Catch: Throwable -> 0x0042
            if (r1 == 0) goto L_0x0040
            if (r4 == 0) goto L_0x003b
            com.amap.api.col.cn r0 = r3.al     // Catch: Throwable -> 0x0042
            java.lang.String r1 = r0.c()     // Catch: Throwable -> 0x0042
            com.amap.api.col.cn r0 = r3.al     // Catch: Throwable -> 0x0042
            java.lang.String r0 = r0.d()     // Catch: Throwable -> 0x0042
        L_0x0016:
            com.amap.api.col.i r2 = r3.f     // Catch: Throwable -> 0x0042
            r2.b(r0)     // Catch: Throwable -> 0x0042
            com.amap.api.col.u r0 = r3.d     // Catch: Throwable -> 0x0042
            r0.c()     // Catch: Throwable -> 0x0042
            com.amap.api.col.p r0 = r3.e     // Catch: Throwable -> 0x0042
            r0.a(r1)     // Catch: Throwable -> 0x0042
            com.amap.api.col.eg r0 = r3.c     // Catch: Throwable -> 0x0042
            if (r0 == 0) goto L_0x002e
            com.amap.api.col.eg r0 = r3.c     // Catch: Throwable -> 0x0042
            r0.k()     // Catch: Throwable -> 0x0042
        L_0x002e:
            com.amap.api.col.ah r0 = r3.m     // Catch: Throwable -> 0x0042
            if (r0 == 0) goto L_0x0037
            com.amap.api.col.ah r0 = r3.m     // Catch: Throwable -> 0x0042
            r0.b()     // Catch: Throwable -> 0x0042
        L_0x0037:
            r3.p()     // Catch: Throwable -> 0x0042
        L_0x003a:
            return
        L_0x003b:
            com.amap.api.col.cn r1 = r3.al     // Catch: Throwable -> 0x0042
            r1.e()     // Catch: Throwable -> 0x0042
        L_0x0040:
            r1 = r0
            goto L_0x0016
        L_0x0042:
            r0 = move-exception
            java.lang.String r1 = "AMapDelegateImp"
            java.lang.String r2 = "clear"
            com.amap.api.col.gr.b(r0, r1, r2)
            r0.printStackTrace()
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.a.clear(boolean):void");
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getMapType() throws RemoteException {
        return this.aw;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapType(int i) throws RemoteException {
        int i2;
        int i3 = 1;
        int i4 = 0;
        this.aw = i;
        if (i == 1) {
            i3 = 0;
            i2 = 0;
        } else if (i == 2) {
            i2 = 1;
            i3 = 0;
        } else if (i == 3) {
            i2 = 0;
            i4 = 4;
        } else if (i == 4) {
            i3 = 0;
            i2 = 0;
            i4 = 4;
        } else {
            try {
                this.aw = 1;
                i3 = 0;
                i2 = 0;
            } catch (Throwable th) {
                gr.b(th, "AMapDelegateImp", "setMaptype");
                th.printStackTrace();
                return;
            }
        }
        this.ai.setMapStyleMode(i2);
        this.ai.setMapStyleTime(i3);
        this.ai.setMapStyleState(i4);
        if (this.ai.isCustomStyleEnable()) {
            a(1, i2, i3, i4, true, false, (StyleItem[]) null);
            this.ai.setCustomStyleEnable(false);
        } else {
            b(1, i2, i3, i4);
        }
        p();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean isTrafficEnabled() throws RemoteException {
        return this.ai.isTrafficEnabled();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setTrafficEnabled(final boolean z) throws RemoteException {
        if (!this.aR || this.ad) {
            this.bb.c = z;
            this.bb.b = true;
            this.bb.g = 1;
            return;
        }
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.28
            @Override // java.lang.Runnable
            public void run() {
                int i = 0;
                try {
                    if (a.this.ai.isTrafficEnabled() != z) {
                        a.this.ai.setTrafficEnabled(z);
                        a.this.aH.setTrafficMode(z);
                        if (z) {
                            i = 1;
                        }
                        a.this.aG.setParamater(1, AMapException.CODE_AMAP_CLIENT_UPLOAD_TOO_FREQUENT, 8, 10, 0, 0);
                        if (a.this.ax != null) {
                            a.this.setMyTrafficStyle(a.this.ax);
                        }
                        a.this.aG.setParamater(1, GLMapStaticValue.AM_PARAMETERNAME_TRAFFIC, i, 0, 0, 0);
                        a.this.p();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean isIndoorEnabled() throws RemoteException {
        return this.ai.isIndoorEnable();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setIndoorEnabled(final boolean z) throws RemoteException {
        if (!this.aR || this.ad) {
            this.bk.c = z;
            this.bk.b = true;
            this.bk.g = 1;
            return;
        }
        this.ai.setIndoorEnable(z);
        p();
        if (z) {
            this.aG.setParamater(1, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_INDOOR, 1, 0, 0, 0);
        } else {
            this.aG.setParamater(1, GLMapStaticValue.AM_PARAMETERNAME_PROCESS_INDOOR, 0, 0, 0, 0);
            this.ai.maxZoomLevel = this.ai.isSetLimitZoomLevel() ? this.ai.getMaxZoomLevel() : 19.0f;
            if (this.Y.isZoomControlsEnabled()) {
                this.ag.invalidateZoomController(this.ai.getS_z());
            }
        }
        if (this.Y.isIndoorSwitchEnabled()) {
            this.n.post(new Runnable() { // from class: com.amap.api.col.a.29
                @Override // java.lang.Runnable
                public void run() {
                    if (z) {
                        a.this.b(true);
                    } else if (a.this.c != null && a.this.c.d() != null) {
                        a.this.c.d().a(false);
                    }
                }
            });
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void set3DBuildingEnabled(boolean z) throws RemoteException {
        p(1);
        d(1, z);
        q(1);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean isMyLocationEnabled() throws RemoteException {
        return this.ab;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyLocationEnabled(boolean z) throws RemoteException {
        if (!this.ad) {
            try {
                if (this.c != null) {
                    ef f = this.c.f();
                    if (this.am == null) {
                        f.a(false);
                    } else if (z) {
                        this.am.activate(this.P);
                        f.a(true);
                        if (this.al == null) {
                            this.al = new cn(this, this.aF);
                        }
                    } else {
                        if (this.al != null) {
                            this.al.b();
                            this.al = null;
                        }
                        this.am.deactivate();
                    }
                }
                if (!z) {
                    this.Y.setMyLocationButtonEnabled(z);
                }
                this.ab = z;
                p();
            } catch (Throwable th) {
                gr.b(th, "AMapDelegateImp", "setMyLocationEnabled");
                th.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setLoadOfflineData(final boolean z) throws RemoteException {
        queueEvent(new Runnable() { // from class: com.amap.api.col.a.30
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.aG != null) {
                    a.this.aG.setParamater(1, GLMapStaticValue.AM_PARAMETERNAME_ON_OFF_DBLITE, z ? 1 : 0, 0, 0, 0);
                }
            }
        });
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyLocationStyle(MyLocationStyle myLocationStyle) throws RemoteException {
        if (!this.ad) {
            if (this.al == null) {
                this.al = new cn(this, this.aF);
            }
            if (this.al != null) {
                if (myLocationStyle.getInterval() < 1000) {
                    myLocationStyle.interval(1000L);
                }
                if (this.am != null && (this.am instanceof ad)) {
                    ((ad) this.am).a(myLocationStyle.getInterval());
                    ((ad) this.am).a(myLocationStyle.getMyLocationType());
                }
                this.al.a(myLocationStyle);
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyLocationType(int i) throws RemoteException {
        if (this.al != null && this.al.a() != null) {
            this.al.a().myLocationType(i);
            setMyLocationStyle(this.al.a());
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public List<Marker> getMapScreenMarkers() throws RemoteException {
        return !dt.b(getMapWidth(), getMapHeight()) ? new ArrayList() : this.e.f();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapTextEnable(boolean z) throws RemoteException {
        p(1);
        b(1, z);
        q(1);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyTrafficStyle(MyTrafficStyle myTrafficStyle) throws RemoteException {
        if (!this.ad) {
            this.ax = myTrafficStyle;
            if (this.aR && myTrafficStyle != null) {
                this.aG.setParamater(1, AMapException.CODE_AMAP_CLIENT_USERID_ILLEGAL, 1, 1, 1, 1);
                this.aG.setParamater(1, AMapException.CODE_AMAP_CLIENT_NEARBY_NULL_RESULT, myTrafficStyle.getSmoothColor(), myTrafficStyle.getSlowColor(), myTrafficStyle.getCongestedColor(), myTrafficStyle.getSeriousCongestedColor());
                this.aG.setParamater(1, AMapException.CODE_AMAP_CLIENT_UPLOAD_TOO_FREQUENT, (int) (myTrafficStyle.getRatio() * 10.0f), 10, 0, 0);
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Location getMyLocation() throws RemoteException {
        if (this.am != null) {
            return this.P.a;
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setLocationSource(LocationSource locationSource) throws RemoteException {
        try {
            if (!this.ad) {
                if (this.am != null && (this.am instanceof ad)) {
                    this.am.deactivate();
                }
                this.am = locationSource;
                if (locationSource != null) {
                    this.c.f().a(true);
                } else {
                    this.c.f().a(false);
                }
            }
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImp", "setLocationSource");
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMyLocationRotateAngle(float f) throws RemoteException {
        if (this.al != null) {
            this.al.a(f);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public UiSettings getAMapUiSettings() throws RemoteException {
        if (this.W == null) {
            this.W = new UiSettings(this.Y);
        }
        return this.W;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Projection getAMapProjection() throws RemoteException {
        return new Projection(this.X);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMapClickListener(AMap.OnMapClickListener onMapClickListener) throws RemoteException {
        this.I = onMapClickListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMapTouchListener(AMap.OnMapTouchListener onMapTouchListener) throws RemoteException {
        this.J = onMapTouchListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnPOIClickListener(AMap.OnPOIClickListener onPOIClickListener) throws RemoteException {
        this.K = onPOIClickListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMapLongClickListener(AMap.OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        this.L = onMapLongClickListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMarkerClickListener(AMap.OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        this.D = onMarkerClickListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnPolylineClickListener(AMap.OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        this.E = onPolylineClickListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMarkerDragListener(AMap.OnMarkerDragListener onMarkerDragListener) throws RemoteException {
        this.F = onMarkerDragListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMaploadedListener(AMap.OnMapLoadedListener onMapLoadedListener) throws RemoteException {
        this.G = onMapLoadedListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnCameraChangeListener(AMap.OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        this.H = onCameraChangeListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnInfoWindowClickListener(AMap.OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        this.M = onInfoWindowClickListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnIndoorBuildingActiveListener(AMap.OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        this.N = onIndoorBuildingActiveListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMyLocationChangeListener(AMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        this.O = onMyLocationChangeListener;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setInfoWindowAdapter(AMap.InfoWindowAdapter infoWindowAdapter) throws RemoteException {
        if (!this.ad) {
            if (infoWindowAdapter instanceof AMap.MultiPositionInfoWindowAdapter) {
                if (this.a != null) {
                    this.a.b();
                }
                this.a = this.V;
                this.V.a((AMap.MultiPositionInfoWindowAdapter) infoWindowAdapter);
                return;
            }
            this.V.destroy();
            this.a = this.c;
            this.c.a(infoWindowAdapter);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setOnMultiPointClickListener(AMap.OnMultiPointClickListener onMultiPointClickListener) {
        if (this.m != null) {
            this.m.a(onMultiPointClickListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void getMapPrintScreen(AMap.onMapPrintScreenListener onmapprintscreenlistener) {
        this.Q = onmapprintscreenlistener;
        this.au = true;
        p();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void getMapScreenShot(AMap.OnMapScreenShotListener onMapScreenShotListener) {
        this.R = onMapScreenShotListener;
        this.au = true;
        p();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getScalePerPixel() throws RemoteException {
        try {
            return ((float) ((((Math.cos((getCameraPosition().target.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (Math.pow(2.0d, g()) * 256.0d))) * s();
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImp", "getScalePerPixel");
            th.printStackTrace();
            return 0.0f;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setRunLowFrame(boolean z) {
        if (!z) {
            r();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removecache() throws RemoteException {
        removecache(null);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removecache(AMap.OnCacheRemoveListener onCacheRemoveListener) throws RemoteException {
        if (this.n != null && this.aG != null) {
            try {
                this.aG.setParamater(1, GLMapStaticValue.AM_PARAMETERNAME_ON_OFF_DBLITE, 0, 0, 0, 0);
                d dVar = new d(this.aF, onCacheRemoveListener);
                this.n.removeCallbacks(dVar);
                this.n.post(dVar);
            } catch (Throwable th) {
                gr.b(th, "AMapDelegateImp", "removecache");
                th.printStackTrace();
            }
        }
    }

    /* compiled from: AMapDelegateImp.java */
    /* loaded from: classes.dex */
    public class d implements Runnable {
        private Context b;
        private AMap.OnCacheRemoveListener c;

        public d(Context context, AMap.OnCacheRemoveListener onCacheRemoveListener) {
            a.this = r1;
            this.b = context;
            this.c = onCacheRemoveListener;
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x0032 A[Catch: Throwable -> 0x0050, TryCatch #4 {Throwable -> 0x0050, blocks: (B:10:0x002a, B:12:0x0032, B:13:0x0042, B:15:0x0046), top: B:54:0x002a }] */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0046 A[Catch: Throwable -> 0x0050, TRY_LEAVE, TryCatch #4 {Throwable -> 0x0050, blocks: (B:10:0x002a, B:12:0x0032, B:13:0x0042, B:15:0x0046), top: B:54:0x002a }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0093 A[Catch: Throwable -> 0x00ad, TryCatch #1 {Throwable -> 0x00ad, blocks: (B:35:0x008b, B:37:0x0093, B:38:0x00a3, B:40:0x00a7), top: B:50:0x008b }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00a7 A[Catch: Throwable -> 0x00ad, TRY_LEAVE, TryCatch #1 {Throwable -> 0x00ad, blocks: (B:35:0x008b, B:37:0x0093, B:38:0x00a3, B:40:0x00a7), top: B:50:0x008b }] */
        /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 189
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.a.d.run():void");
        }

        public boolean equals(Object obj) {
            return obj instanceof d;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCustomRenderer(CustomRenderer customRenderer) throws RemoteException {
        this.h = customRenderer;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCenterToPixel(int i, int i2) throws RemoteException {
        this.aj = true;
        this.aY = i;
        this.aZ = i2;
        if (this.aS && this.aR) {
            if (this.ai.getAnchorX() != this.aY || this.ai.getAnchorY() != this.aZ) {
                this.ai.setAnchorX(this.aY);
                this.ai.setAnchorY(this.aZ);
                queueEvent(new Runnable() { // from class: com.amap.api.col.a.31
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.ai.setAnchorX(Math.max(0, Math.min(a.this.aY, a.this.k)));
                        a.this.ai.setAnchorY(Math.max(0, Math.min(a.this.aZ, a.this.l)));
                        a.this.aG.setParamater(1, AMapException.CODE_AMAP_ENGINE_RESPONSE_ERROR, a.this.ai.getAnchorX(), a.this.ai.getAnchorY(), 0, 0);
                        a.this.aA = true;
                    }
                });
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapTextZIndex(int i) throws RemoteException {
        this.aB = i;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getMapTextZIndex() throws RemoteException {
        return this.aB;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void reloadMap() {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setRenderFps(int i) {
        try {
            this.i = Math.max(10, Math.min(i, 40));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setIndoorBuildingInfo(IndoorBuildingInfo indoorBuildingInfo) throws RemoteException {
        if (!this.ad && indoorBuildingInfo != null && indoorBuildingInfo.activeFloorName != null && indoorBuildingInfo.poiid != null) {
            this.g = (ac) indoorBuildingInfo;
            p();
            queueEvent(new Runnable() { // from class: com.amap.api.col.a.32
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.aG != null) {
                        a.this.aG.setIndoorBuildingToBeActive(1, a.this.g.activeFloorName, a.this.g.activeFloorIndex, a.this.g.poiid);
                    }
                }
            });
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setAMapGestureListener(AMapGestureListener aMapGestureListener) {
        if (this.j != null) {
            this.U = aMapGestureListener;
            this.j.a(aMapGestureListener);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getZoomToSpanLevel(LatLng latLng, LatLng latLng2) {
        MapConfig mapConfig = getMapConfig();
        if (latLng == null || latLng2 == null || !this.aR || this.ad) {
            return mapConfig.getS_z();
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(latLng);
        builder.include(latLng2);
        GLMapState gLMapState = new GLMapState(1, this.aG.getMapEnginePtr());
        Pair<Float, IPoint> a = dt.a(mapConfig, 0, 0, 0, 0, builder.build(), getMapWidth(), getMapHeight());
        gLMapState.recycle();
        if (a != null) {
            return ((Float) a.first).floatValue();
        }
        return gLMapState.getMapZoomer();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Pair<Float, LatLng> calculateZoomToSpanLevel(int i, int i2, int i3, int i4, LatLng latLng, LatLng latLng2) {
        if (latLng != null && latLng2 != null && i == i2 && i2 == i3 && i3 == i4 && latLng.latitude == latLng2.latitude && latLng.longitude == latLng2.longitude) {
            return new Pair<>(Float.valueOf(getMaxZoomLevel()), latLng);
        }
        MapConfig mapConfig = getMapConfig();
        if (latLng == null || latLng2 == null || !this.aR || this.ad) {
            DPoint obtain = DPoint.obtain();
            GLMapState.geo2LonLat(mapConfig.getS_x(), mapConfig.getS_y(), obtain);
            Pair<Float, LatLng> pair = new Pair<>(Float.valueOf(mapConfig.getS_z()), new LatLng(obtain.y, obtain.x));
            obtain.recycle();
            return pair;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(latLng);
        builder.include(latLng2);
        GLMapState gLMapState = new GLMapState(1, this.aG.getMapEnginePtr());
        Pair<Float, IPoint> a = dt.a(mapConfig, i, i2, i3, i4, builder.build(), getMapWidth(), getMapHeight());
        gLMapState.recycle();
        if (a == null) {
            return null;
        }
        DPoint obtain2 = DPoint.obtain();
        GLMapState.geo2LonLat(((IPoint) a.second).x, ((IPoint) a.second).y, obtain2);
        Pair<Float, LatLng> pair2 = new Pair<>(a.first, new LatLng(obtain2.y, obtain2.x));
        obtain2.recycle();
        return pair2;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public InfoWindowAnimationManager getInfoWindowAnimationManager() {
        return new InfoWindowAnimationManager(this.V);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMaskLayerParams(int i, int i2, int i3, int i4, final int i5, long j) {
        GLAlphaAnimation gLAlphaAnimation;
        try {
            if (this.aC != null) {
                float f = i4 / 255.0f;
                if (i5 == -1) {
                    gLAlphaAnimation = new GLAlphaAnimation(f, 0.0f);
                    gLAlphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.amap.api.col.a.34
                        @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
                        public void onAnimationStart() {
                        }

                        @Override // com.amap.api.maps.model.animation.Animation.AnimationListener
                        public void onAnimationEnd() {
                            a.this.n.post(new Runnable() { // from class: com.amap.api.col.a.34.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    a.this.aD = i5;
                                    if (a.this.c != null) {
                                        a.this.c.h().setVisibility(0);
                                    }
                                }
                            });
                        }
                    });
                } else {
                    this.aD = i5;
                    gLAlphaAnimation = new GLAlphaAnimation(0.0f, f);
                    if (f > 0.2d) {
                        if (this.c != null) {
                            this.c.h().setVisibility(4);
                        }
                    } else if (this.c != null) {
                        this.c.h().setVisibility(0);
                    }
                }
                gLAlphaAnimation.setInterpolator(new LinearInterpolator());
                gLAlphaAnimation.setDuration(j);
                this.aC.a(i, i2, i3, i4);
                this.aC.a(gLAlphaAnimation);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMaxZoomLevel(float f) {
        this.ai.setMaxZoomLevel(f);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMinZoomLevel(float f) {
        this.ai.setMinZoomLevel(f);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void resetMinMaxZoomPreference() {
        this.ai.resetMinMaxZoomPreference();
        try {
            if (this.Y.isZoomControlsEnabled() && this.ai.isNeedUpdateZoomControllerState()) {
                this.ag.invalidateZoomController(this.ai.getS_z());
            }
        } catch (RemoteException e) {
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapStatusLimits(LatLngBounds latLngBounds) {
        try {
            this.ai.setLimitLatLngBounds(latLngBounds);
            A();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void A() {
        try {
            LatLngBounds limitLatLngBounds = this.ai.getLimitLatLngBounds();
            if (!(limitLatLngBounds == null || this.aG == null)) {
                GLMapState gLMapState = new GLMapState(1, this.aG.getMapEnginePtr());
                IPoint obtain = IPoint.obtain();
                GLMapState.lonlat2Geo(limitLatLngBounds.northeast.longitude, limitLatLngBounds.northeast.latitude, obtain);
                IPoint obtain2 = IPoint.obtain();
                GLMapState.lonlat2Geo(limitLatLngBounds.southwest.longitude, limitLatLngBounds.southwest.latitude, obtain2);
                this.ai.setLimitIPoints(new IPoint[]{obtain, obtain2});
                gLMapState.recycle();
                B();
                return;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.ai.setLimitIPoints(null);
        this.ai.setLimitZoomLevel(0.0f);
    }

    private void B() {
        if (this.ai.getLimitIPoints() != null && this.ai.getLimitIPoints().length == 2 && this.aG != null) {
            IPoint iPoint = this.ai.getLimitIPoints()[0];
            IPoint iPoint2 = this.ai.getLimitIPoints()[1];
            GLMapState gLMapState = new GLMapState(1, this.aG.getMapEnginePtr());
            float floatValue = ((Float) dt.a(this.ai, iPoint.x, iPoint.y, iPoint2.x, iPoint2.y, getMapWidth(), getMapHeight()).first).floatValue();
            gLMapState.recycle();
            setMinZoomLevel(floatValue);
            this.ai.setLimitZoomLevel(floatValue);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public Handler getMainHandler() {
        return this.n;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onChangeFinish() {
        p();
        Message obtainMessage = this.n.obtainMessage();
        obtainMessage.what = 11;
        this.n.sendMessage(obtainMessage);
    }

    protected void a(boolean z, CameraPosition cameraPosition) {
        if (this.ai != null && this.ai.getChangedCounter() != 0) {
            try {
                this.ai.resetChangedCounter();
                if (this.U != null) {
                    this.U.onMapStable();
                }
                if (this.H != null && this.aa.isEnabled()) {
                    if (cameraPosition == null) {
                        try {
                            cameraPosition = getCameraPosition();
                        } catch (RemoteException e) {
                            gr.b(e, "AMapDelegateImp", "cameraChangeFinish");
                            e.printStackTrace();
                        }
                    }
                    this.H.onCameraChangeFinish(cameraPosition);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setZoomScaleParam(float f) {
        this.aK = f;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onFling() {
        if (this.d != null) {
            this.d.b(true);
        }
        this.at = true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getMapWidth() {
        return this.k;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public int getMapHeight() {
        return this.l;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float getCameraAngle() {
        return o(this.ac);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean isMaploaded() {
        return this.ak;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public MapConfig getMapConfig() {
        return this.ai;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public View getView() throws RemoteException {
        return this.c;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setZOrderOnTop(boolean z) throws RemoteException {
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void onIndoorBuildingActivity(int i, byte[] bArr) {
        int i2;
        ac acVar = null;
        if (bArr != null) {
            try {
                ac acVar2 = new ac();
                byte b2 = bArr[0];
                acVar2.a = new String(bArr, 1, b2, "utf-8");
                int i3 = b2 + 1;
                int i4 = i3 + 1;
                byte b3 = bArr[i3];
                acVar2.b = new String(bArr, i4, b3, "utf-8");
                int i5 = b3 + i4;
                int i6 = i5 + 1;
                byte b4 = bArr[i5];
                acVar2.activeFloorName = new String(bArr, i6, b4, "utf-8");
                int i7 = b4 + i6;
                acVar2.activeFloorIndex = GLConvertUtil.getInt(bArr, i7);
                int i8 = i7 + 4;
                int i9 = i8 + 1;
                byte b5 = bArr[i8];
                acVar2.poiid = new String(bArr, i9, b5, "utf-8");
                int i10 = b5 + i9;
                int i11 = i10 + 1;
                byte b6 = bArr[i10];
                acVar2.h = new String(bArr, i11, b6, "utf-8");
                int i12 = b6 + i11;
                acVar2.c = GLConvertUtil.getInt(bArr, i12);
                int i13 = i12 + 4;
                acVar2.floor_indexs = new int[acVar2.c];
                acVar2.floor_names = new String[acVar2.c];
                acVar2.d = new String[acVar2.c];
                for (int i14 = 0; i14 < acVar2.c; i14++) {
                    acVar2.floor_indexs[i14] = GLConvertUtil.getInt(bArr, i13);
                    int i15 = i13 + 4;
                    int i16 = i15 + 1;
                    byte b7 = bArr[i15];
                    if (b7 > 0) {
                        acVar2.floor_names[i14] = new String(bArr, i16, b7, "utf-8");
                        i2 = i16 + b7;
                    } else {
                        i2 = i16;
                    }
                    i13 = i2 + 1;
                    byte b8 = bArr[i2];
                    if (b8 > 0) {
                        acVar2.d[i14] = new String(bArr, i13, b8, "utf-8");
                        i13 += b8;
                    }
                }
                acVar2.e = GLConvertUtil.getInt(bArr, i13);
                int i17 = i13 + 4;
                if (acVar2.e > 0) {
                    acVar2.f = new int[acVar2.e];
                    int i18 = i17;
                    for (int i19 = 0; i19 < acVar2.e; i19++) {
                        acVar2.f[i19] = GLConvertUtil.getInt(bArr, i18);
                        i18 += 4;
                    }
                }
                acVar = acVar2;
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.bo = acVar;
        a(new Runnable() { // from class: com.amap.api.col.a.35
            @Override // java.lang.Runnable
            public void run() {
                if (a.this.ba != null) {
                    a.this.ba.a(a.this.bo);
                }
            }
        });
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public AMapCameraInfo getCamerInfo() {
        try {
            GLMapState b2 = b();
            float mapAngle = b2.getMapAngle();
            float f = this.k / this.l;
            float fov = b2.getFov();
            int[] iArr = new int[3];
            b2.getCameraPosition(iArr);
            return new AMapCameraInfo(fov, f, mapAngle, iArr[0], iArr[1], iArr[2]);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public MapTilsCacheAndResManager getMapCacheMgr() {
        if (!this.aR || this.aG == null) {
            return null;
        }
        return this.aG.getMapCacheMgr();
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void destroy() {
        this.ad = true;
        try {
            if (this.m != null) {
                this.m.a();
            }
            if (this.am != null) {
                this.am.deactivate();
            }
            this.am = null;
            this.ba = null;
            if (this.aH != null) {
                this.aH.renderPause();
            }
            if (this.C != null) {
                this.C.recycle();
                this.C = null;
            }
            if (this.B != null) {
                this.B.recycle();
                this.B = null;
            }
            if (this.j != null) {
                this.j.a((AMapGestureListener) null);
                this.j.b();
                this.j = null;
            }
            if (this.f != null) {
                this.f.b();
            }
            if (this.e != null) {
                this.e.j();
            }
            if (this.d != null) {
                this.d.f();
            }
            w();
            if (this.ay != null) {
                this.ay.interrupt();
                this.ay = null;
            }
            if (this.aG != null) {
                queueEvent(new Runnable() { // from class: com.amap.api.col.a.36
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            a.this.aG.destorySurface();
                            a.this.aG = null;
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
                Thread.sleep(200L);
            }
            if (this.c != null) {
                this.c.j();
                this.c = null;
            }
            if (this.al != null) {
                this.al.b();
                this.al = null;
            }
            this.am = null;
            C();
            this.ax = null;
            gr.b();
        } catch (Throwable th) {
            gr.b(th, "AMapDelegateImp", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            th.printStackTrace();
        }
    }

    private void C() {
        this.O = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.P = null;
        this.Q = null;
        this.R = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AMapDelegateImp.java */
    /* loaded from: classes.dex */
    public class c implements ee.a {
        private c() {
            a.this = r1;
        }

        @Override // com.amap.api.col.ee.a
        public void a(int i) {
            if (a.this.g != null) {
                a.this.g.activeFloorIndex = a.this.g.floor_indexs[i];
                a.this.g.activeFloorName = a.this.g.floor_names[i];
                try {
                    a.this.setIndoorBuildingInfo(a.this.g);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AMapDelegateImp.java */
    /* loaded from: classes.dex */
    public class b {
        b() {
            a.this = r1;
        }

        public void a(ac acVar) {
            float f;
            float f2;
            if (a.this.ai.isIndoorEnable()) {
                final ee d = a.this.c.d();
                if (acVar == null) {
                    try {
                        if (a.this.N != null) {
                            a.this.N.OnIndoorBuilding(acVar);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (a.this.g != null) {
                        a.this.g.g = null;
                    }
                    if (d.d()) {
                        a.this.n.post(new Runnable() { // from class: com.amap.api.col.a.b.1
                            @Override // java.lang.Runnable
                            public void run() {
                                d.a(false);
                            }
                        });
                    }
                    MapConfig mapConfig = a.this.ai;
                    if (a.this.ai.isSetLimitZoomLevel()) {
                        f = a.this.ai.getMaxZoomLevel();
                    } else {
                        f = 19.0f;
                    }
                    mapConfig.maxZoomLevel = f;
                    try {
                        if (a.this.Y.isZoomControlsEnabled()) {
                            a.this.ag.invalidateZoomController(a.this.ai.getS_z());
                            return;
                        }
                        return;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
                if (a.this.g == null || !a.this.g.poiid.equals(acVar.poiid) || !d.d()) {
                    if (a.this.g == null || !a.this.g.poiid.equals(acVar.poiid) || a.this.g.g == null) {
                        a.this.g = acVar;
                        if (a.this.aG != null) {
                            a.this.g.g = a.this.aG.getMapCenter(1);
                        }
                    }
                    try {
                        if (a.this.N != null) {
                            a.this.N.OnIndoorBuilding(acVar);
                        }
                        MapConfig mapConfig2 = a.this.ai;
                        if (a.this.ai.isSetLimitZoomLevel()) {
                            f2 = a.this.ai.getMaxZoomLevel();
                        } else {
                            f2 = 20.0f;
                        }
                        mapConfig2.maxZoomLevel = f2;
                        if (a.this.Y.isZoomControlsEnabled()) {
                            a.this.ag.invalidateZoomController(a.this.ai.getS_z());
                        }
                        if (a.this.Y.isIndoorSwitchEnabled()) {
                            if (!d.d()) {
                                a.this.Y.setIndoorSwitchEnabled(true);
                            }
                            a.this.n.post(new Runnable() { // from class: com.amap.api.col.a.b.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        d.a(a.this.g.floor_names);
                                        d.a(a.this.g.activeFloorName);
                                        if (!d.d()) {
                                            d.a(true);
                                        }
                                    } catch (Throwable th3) {
                                        th3.printStackTrace();
                                    }
                                }
                            });
                        } else if (!a.this.Y.isIndoorSwitchEnabled() && d.d()) {
                            a.this.Y.setIndoorSwitchEnabled(false);
                        }
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMapListener
    public void beforeDrawLabel(int i, GLMapState gLMapState) {
        o();
        if (this.aG != null) {
            this.aG.pushRendererState();
        }
        this.f.a(true, this.aB);
        if (this.aG != null) {
            this.aG.popRendererState();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMapListener
    public void afterDrawLabel(int i, GLMapState gLMapState) {
        o();
        if (this.aG != null) {
            this.aG.pushRendererState();
        }
        this.d.b();
        this.f.a(false, this.aB);
        if (this.m != null) {
            this.m.a(this.ai, getViewMatrix(), getProjectionMatrix());
        }
        if (this.e != null) {
            this.e.a();
        }
        if (this.aG != null) {
            this.aG.popRendererState();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMapListener
    public void afterDrawFrame(final int i, GLMapState gLMapState) {
        float mapZoomer = gLMapState.getMapZoomer();
        if (this.aG == null || (!this.aG.isInMapAction(i) && !this.aG.isInMapAnimation(i))) {
            if (this.aL) {
                this.aH.setRenderFps(10.0f);
            } else {
                this.aH.setRenderFps(15.0f);
            }
            if (this.aP == 1) {
                this.aP = 0;
            }
            if (this.aJ != mapZoomer) {
                this.aJ = mapZoomer;
            }
        }
        if (!this.aV) {
            this.aV = true;
            a(new Runnable() { // from class: com.amap.api.col.a.37
                @Override // java.lang.Runnable
                public void run() {
                    if (a.this.af != null) {
                        a.this.af.onSurfaceChanged(i, a.this.k, a.this.l);
                    }
                }
            });
        }
    }

    private void D() {
        if (this.B == null || this.B.isRecycled()) {
            this.B = dt.a(this.aF, "amap_sdk_lineTexture.png");
        }
        if (this.C == null || this.C.isRecycled()) {
            this.C = dt.a(this.aF, "amap_sdk_lineDashTexture.png");
        }
        this.y = dt.a(this.B);
        this.z = dt.a(this.C, true);
        this.A = dt.a(512, 1024);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public long createGLOverlay(int i) {
        if (this.aG != null) {
            return this.aG.createOverlay(1, i);
        }
        return 0L;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public long getGlOverlayMgrPtr() {
        if (this.aG != null) {
            return this.aG.getGlOverlayMgrPtr(1);
        }
        return 0L;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public CrossOverlay addCrossVector(CrossOverlayOptions crossOverlayOptions) {
        if (crossOverlayOptions == null || crossOverlayOptions.getRes() == null) {
            return null;
        }
        CrossVectorOverlay crossVectorOverlay = new CrossVectorOverlay(1, t(), this);
        if (crossOverlayOptions != null) {
            crossVectorOverlay.setAttribute(crossOverlayOptions.getAttribute());
        }
        if (this.aG != null) {
            this.aG.getOvelayBundle(1).addOverlay(crossVectorOverlay);
            crossVectorOverlay.resumeMarker(crossOverlayOptions.getRes());
        }
        return new CrossOverlay(crossOverlayOptions, crossVectorOverlay);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public RouteOverlay addNaviRouteOverlay() {
        if (!this.aR || this.aG == null) {
            return null;
        }
        return new RouteOverlay(new BaseRouteOverlay(1, t(), this));
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void addOverlayTexture(int i, GLTextureProperty gLTextureProperty) {
        if (this.aG != null) {
            this.aG.addOverlayTexture(i, gLTextureProperty);
            this.aG.getOvelayBundle(1).addOverlayTextureItem(gLTextureProperty.mId, gLTextureProperty.mAnchor, gLTextureProperty.mXRatio, gLTextureProperty.mYRatio, gLTextureProperty.mBitmap.getWidth(), gLTextureProperty.mBitmap.getHeight());
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCustomMapStylePath(String str) {
        if (!TextUtils.isEmpty(str) && !str.equals(this.ai.getCustomStylePath())) {
            this.ai.setCustomStylePath(str);
            this.Z = true;
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setCustomTextureResourcePath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.ai.setCustomTextureResourcePath(str);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public MyLocationStyle getMyLocationStyle() throws RemoteException {
        if (this.al != null) {
            return this.al.a();
        }
        return null;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setMapCustomEnable(boolean z) {
        if (!this.aR || this.ad) {
            this.bf.b = true;
            this.bf.c = z;
        } else if (!TextUtils.isEmpty(this.ai.getCustomStylePath())) {
            try {
                if (this.Z || (this.ai.isCustomStyleEnable() ^ z)) {
                    this.ai.setCustomStyleEnable(z);
                    if (z) {
                        g(1, true);
                        cy cyVar = new cy(this.aF);
                        StyleItem[] a = cyVar.a(this.ai.getCustomStylePath());
                        this.ai.setCustomBackgroundColor(cyVar.a());
                        if (a != null && a.length > 0) {
                            a(1, 0, 0, 0, true, true, a);
                        }
                    } else {
                        g(1, false);
                        a(1, this.ai.getMapStyleMode(), this.ai.getMapStyleTime(), this.ai.getMapStyleState(), true, false, (StyleItem[]) null);
                    }
                }
                this.Z = false;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void removeGLOverlay(BaseMapOverlay baseMapOverlay) {
        if (this.aG != null) {
            this.aG.getOvelayBundle(1).removeOverlay(baseMapOverlay);
        }
    }

    @Override // com.amap.api.col.k
    public float[] u() {
        if (this.aG != null) {
            GLMapState mapState = this.aG.getMapState(1);
            mapState.getViewMatrix(this.s);
            mapState.getProjectionMatrix(this.t);
            if (this.s == null || this.t == null) {
                return this.r;
            }
            Matrix.multiplyMM(this.r, 0, this.t, 0, this.s, 0);
        }
        return this.r;
    }

    @Override // com.amap.api.col.k
    public String c(String str) {
        if (this.f != null) {
            return this.f.a(str);
        }
        return null;
    }

    @Override // com.amap.api.col.k
    public void h(boolean z) {
        if (!this.ad) {
            this.c.f(z);
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float[] getViewMatrix() {
        GLMapState mapState;
        if (this.aR && this.aS && this.aG != null && (mapState = this.aG.getMapState(1)) != null) {
            mapState.getViewMatrix(this.s);
        }
        return this.s;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public float[] getProjectionMatrix() {
        GLMapState mapState;
        if (this.aR && this.aS && this.aG != null && (mapState = this.aG.getMapState(1)) != null) {
            mapState.getProjectionMatrix(this.t);
        }
        return this.t;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void changeSurface(GL10 gl10, int i, int i2) {
        try {
            a(1, gl10, i, i2);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void createSurface(GL10 gl10, EGLConfig eGLConfig) {
        try {
            a(1, gl10, eGLConfig);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void renderSurface(GL10 gl10) {
        drawFrame(gl10);
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public boolean canStopMapRender() {
        if (this.aG != null) {
            this.aG.canStopMapRender(1);
        }
        return true;
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void getLatLngRect(DPoint[] dPointArr) {
        try {
            Rectangle geoRectangle = this.ai.getGeoRectangle();
            if (geoRectangle != null) {
                IPoint[] clipRect = geoRectangle.getClipRect();
                for (int i = 0; i < 4; i++) {
                    GLMapState.geo2LonLat(clipRect[i].x, clipRect[i].y, dPointArr[i]);
                }
            }
        } catch (Throwable th) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x00bb A[Catch: Throwable -> 0x0124, LOOP:0: B:21:0x00b8->B:23:0x00bb, LOOP_END, TryCatch #0 {Throwable -> 0x0124, blocks: (B:9:0x0013, B:11:0x001b, B:12:0x0047, B:16:0x0089, B:18:0x0091, B:20:0x00a1, B:21:0x00b8, B:23:0x00bb, B:27:0x00d5, B:29:0x00db, B:31:0x00de, B:32:0x00ec, B:33:0x00f3, B:35:0x00f6, B:36:0x0103, B:38:0x010b, B:40:0x011f), top: B:48:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f6 A[Catch: Throwable -> 0x0124, LOOP:1: B:33:0x00f3->B:35:0x00f6, LOOP_END, TryCatch #0 {Throwable -> 0x0124, blocks: (B:9:0x0013, B:11:0x001b, B:12:0x0047, B:16:0x0089, B:18:0x0091, B:20:0x00a1, B:21:0x00b8, B:23:0x00bb, B:27:0x00d5, B:29:0x00db, B:31:0x00de, B:32:0x00ec, B:33:0x00f3, B:35:0x00f6, B:36:0x0103, B:38:0x010b, B:40:0x011f), top: B:48:0x0011 }] */
    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkMapState(com.autonavi.ae.gmap.GLMapState r13) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.a.checkMapState(com.autonavi.ae.gmap.GLMapState):void");
    }

    @Override // com.autonavi.amap.mapcore.interfaces.IAMap
    public void setRenderMode(int i) {
        if (this.aa != null) {
            this.ae = i;
            this.aa.setRenderMode(i);
        }
    }
}

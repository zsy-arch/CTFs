package com.amap.api.col;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CrossOverlay;
import com.amap.api.maps.model.CrossOverlayOptions;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.AMapNaviViewOptions;
import com.amap.api.navi.IAMapNaviView;
import com.amap.api.navi.INavi;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.RouteOverlayOptions;
import com.amap.api.navi.view.DirectionView;
import com.amap.api.navi.view.DriveWayView;
import com.amap.api.navi.view.NextTurnTipView;
import com.amap.api.navi.view.OverviewButtonView;
import com.amap.api.navi.view.TrafficBarView;
import com.amap.api.navi.view.TrafficButtonView;
import com.amap.api.navi.view.ZoomButtonView;
import com.amap.api.navi.view.ZoomInIntersectionView;
import com.autonavi.ae.gmap.gloverlay.GLCrossVector;
import com.tencent.smtt.sdk.TbsListener;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/* compiled from: AMapNaviViewCore.java */
/* loaded from: classes.dex */
public class es implements View.OnClickListener, AMap.OnCameraChangeListener, AMap.OnMapLoadedListener, AMap.OnMapTouchListener, IAMapNaviView {
    private static int M = 1000;
    private static int N = 500;
    OverviewButtonView A;
    DriveWayView B;
    RelativeLayout C;
    RelativeLayout D;
    private AMapNaviViewOptions T;
    private MapView U;
    private INavi V;
    private fd W;
    private AMap X;
    private a Y;
    ZoomInIntersectionView a;
    private AMapNaviViewListener ac;
    private DriveWayView ah;
    private ZoomInIntersectionView ai;
    private TrafficBarView aj;
    private DirectionView ak;
    private TrafficButtonView al;
    private NextTurnTipView am;
    private ZoomButtonView an;
    private AMapNaviView ao;
    private Context aq;
    private ZoomButtonView ar;
    private OverviewButtonView as;
    private CrossOverlay av;
    NextTurnTipView c;
    TextView d;
    TextView e;
    TextView f;
    FrameLayout g;
    FrameLayout h;
    LinearLayout i;
    LinearLayout j;
    LinearLayout k;
    FrameLayout l;
    LinearLayout m;
    TextView n;
    TextView o;
    TrafficBarView p;
    DirectionView q;
    TrafficButtonView r;
    Drawable s;
    Drawable t;

    /* renamed from: u */
    TextView f16u;
    TextView v;
    ImageView w;
    ImageView x;
    ImageView y;
    ImageView z;
    View b = null;
    int E = 480;
    int F = 800;
    boolean G = false;
    int H = 0;
    int I = 0;
    boolean J = false;
    boolean K = true;
    boolean L = false;
    private boolean O = false;
    private double P = 0.0d;
    private double Q = 0.0d;
    private int R = Integer.MAX_VALUE;
    private int S = Integer.MAX_VALUE;
    private long Z = 5000;
    private boolean aa = true;
    private int ab = 0;
    private boolean ad = true;
    private boolean ae = true;
    private boolean af = false;
    private int ag = 0;
    private View.OnClickListener ap = new View.OnClickListener() { // from class: com.amap.api.col.es.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (es.this.O) {
                es.this.recoverLockMode();
            } else {
                es.this.e(true);
                es.this.a(false);
                es.this.W.b();
            }
            if (es.this.ac != null) {
                es.this.ac.onScanViewButtonClick();
            }
        }
    };
    private View.OnClickListener at = new View.OnClickListener() { // from class: com.amap.api.col.es.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            es.this.s();
        }
    };
    private View.OnClickListener au = new View.OnClickListener() { // from class: com.amap.api.col.es.4
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (es.this.ao != null && es.this.as != null) {
                if (es.this.ao.isRouteOverviewNow()) {
                    es.this.ao.recoverLockMode();
                } else {
                    es.this.ao.displayOverview();
                }
                es.this.as.setChecked(es.this.ao.isRouteOverviewNow());
            }
        }
    };
    private boolean aw = false;

    public es(AMapNaviView aMapNaviView, AMapNaviViewOptions aMapNaviViewOptions) {
        this.T = null;
        this.T = aMapNaviViewOptions;
        this.ao = aMapNaviView;
        this.aq = aMapNaviView.getContext();
    }

    private void a(int i) {
        M = i;
    }

    private void b(int i) {
        N = i;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public double getAnchorX() {
        return this.P;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public double getAnchorY() {
        return this.Q;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public int getLockZoom() {
        return this.R;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLockZoom(int i) {
        if (i != this.R) {
            this.T.setZoom(i);
            setViewOptions(this.T);
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public int getLockTilt() {
        return this.S;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLockTilt(int i) {
        if (i != this.S) {
            this.T.setTilt(i);
            setViewOptions(this.T);
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public int getNaviMode() {
        return this.ag;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setNaviMode(int i) {
        if ((i == 1 || i == 0) && i != this.ag) {
            this.ag = i;
            a(true);
            if (i == 1) {
                m();
            } else if (i == 0) {
                p();
            }
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public boolean isAutoChangeZoom() {
        return this.af;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public AMapNaviViewOptions getViewOptions() {
        return this.T;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setViewOptions(AMapNaviViewOptions aMapNaviViewOptions) {
        this.T = aMapNaviViewOptions;
        a();
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public AMap getMap() {
        return this.X;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void init() {
        try {
            if (this.T == null) {
                this.T = new AMapNaviViewOptions();
            }
            this.V = AMapNavi.getInstance(this.aq);
            fo.a(this.aq.getApplicationContext());
            this.b = fo.a((Activity) this.aq, 1191378947, null);
            this.ao.addView(this.b);
            this.U = (MapView) this.b.findViewById(1191772176);
            this.G = g();
            if (this.W == null) {
                this.W = new fd(this.aq, this.U, this);
            }
            j();
            this.Y = new a(this);
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "init()");
        }
    }

    private boolean g() {
        return ((Activity) this.aq).getRequestedOrientation() == 0 || this.ao.getResources().getConfiguration().orientation == 2;
    }

    private void h() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.aq.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.E = displayMetrics.widthPixels;
        this.F = displayMetrics.heightPixels;
    }

    private void i() {
        try {
            this.X.setOnMapLoadedListener(this);
            this.X.setOnCameraChangeListener(this);
            this.X.setOnMapTouchListener(this);
            this.V.addAMapNaviListener(this.W);
            this.g.setOnClickListener(this);
            this.z.setOnClickListener(this);
            this.w.setOnClickListener(this);
            this.c.setOnClickListener(this);
            this.q.setOnClickListener(this);
            this.n.setOnClickListener(this);
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "initListener()");
        }
    }

    private void j() {
        try {
            this.p = new TrafficBarView(this.aq);
            this.a = (ZoomInIntersectionView) this.ao.findViewById(1191772186);
            this.q = (DirectionView) this.b.findViewById(1191772181);
            this.D = (RelativeLayout) this.b.findViewById(1191772175);
            this.B = (DriveWayView) this.b.findViewById(1191772190);
            this.B.setAMapNaviView(this.ao);
            this.c = (NextTurnTipView) this.b.findViewById(1191772180);
            this.d = (TextView) this.b.findViewById(1191772179);
            this.e = (TextView) this.b.findViewById(1191772183);
            this.f = (TextView) this.b.findViewById(1191772184);
            this.f16u = (TextView) this.b.findViewById(1191772196);
            this.v = (TextView) this.b.findViewById(1191772197);
            this.g = (FrameLayout) this.b.findViewById(1191772200);
            this.h = (FrameLayout) this.b.findViewById(1191772195);
            this.x = (ImageView) this.b.findViewById(1191772202);
            this.y = (ImageView) this.b.findViewById(1191772194);
            this.z = (ImageView) this.b.findViewById(1191772203);
            this.w = (ImageView) this.b.findViewById(1191772193);
            this.l = (FrameLayout) this.b.findViewById(1191772198);
            this.m = (LinearLayout) this.b.findViewById(1191772192);
            this.n = (TextView) this.b.findViewById(1191772199);
            this.o = (TextView) this.b.findViewById(1191772201);
            this.r = (TrafficButtonView) this.b.findViewById(1191772185);
            this.r.setOnClickListener(this.at);
            this.A = (OverviewButtonView) this.b.findViewById(1191772189);
            this.A.setOnClickListener(this.ap);
            this.s = fo.a().getDrawable(1191313492);
            this.t = fo.a().getDrawable(1191313491);
            this.C = (RelativeLayout) this.b.findViewById(1191772191);
            this.i = (LinearLayout) this.b.findViewById(1191772177);
            this.j = (LinearLayout) this.b.findViewById(1191772182);
            this.k = (LinearLayout) this.b.findViewById(1191772187);
            this.an = (ZoomButtonView) this.b.findViewById(1191772188);
            this.an.getZoomInBtn().setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.col.es.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    es.this.zoomIn();
                }
            });
            this.an.getZoomOutBtn().setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.col.es.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    es.this.zoomOut();
                }
            });
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "findView()");
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void onConfigurationChanged(Configuration configuration) {
        try {
            h();
            this.G = g();
            a(this.E / 2);
            c(this.G);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapNaviView", "onConfigurationChanged(Configuration newConfig)");
        }
    }

    private void c(boolean z) {
        if (this.aj != null) {
            this.aj.onConfigurationChanged(z);
        }
        if (this.p != null) {
            this.p.onConfigurationChanged(z);
        }
        if (z) {
            if (!this.J) {
                this.f.setVisibility(0);
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.C.getLayoutParams();
            layoutParams.width = c(320);
            this.C.setLayoutParams(layoutParams);
            d(0);
            b(0, 0, 10, 0);
            if (this.a.getVisibility() == 0) {
                n();
            }
        } else {
            this.f.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.C.getLayoutParams();
            layoutParams2.width = -2;
            this.C.setLayoutParams(layoutParams2);
            d(40);
            b(0, 0, 10, 30);
            if (this.a.getVisibility() == 0) {
                n();
            }
        }
        a(this.aa);
    }

    public void a() {
        int i = 0;
        i = 8;
        try {
            fr.a("AmapNaviCore", "checkViewOptions");
            this.P = this.T.getMapCenter_X();
            this.Q = this.T.getMapCenter_Y();
            d(this.T.isLayoutVisible());
            this.Z = this.T.getLockMapDelayed();
            this.af = this.T.isAutoChangeZoom();
            this.ad = this.T.isRealCrossDisplayShow();
            this.ae = this.T.isModelCrossDisplayShow();
            if (!this.T.isCompassEnabled()) {
                this.q.setVisibility(8);
            }
            if (!this.T.isTrafficBarEnabled()) {
                this.p.setVisibility(8);
            } else {
                TrafficBarView trafficBarView = this.p;
                if (!this.aa) {
                }
                trafficBarView.setVisibility(i);
            }
            if (this.T.isTrafficLayerEnabled()) {
                this.r.reDrawBackground(this.T.getDefaultTrafficBitmap(), this.T.getPressedTrafficBitmap());
                this.r.setVisibility(0);
            } else {
                this.r.setVisibility(8);
            }
            if (!this.T.isRouteListButtonShow()) {
                this.A.setVisibility(8);
            } else {
                this.A.reDrawBackground(this.T.getDefaultOverBitmap(), this.T.getPressedOverBitmap());
                if (!this.aa) {
                    this.A.setVisibility(0);
                }
            }
            if (TextUtils.isEmpty(this.T.getCustomMapStylePath())) {
                this.X.setMapCustomEnable(false);
                if (this.T.isNaviNight()) {
                    this.X.setMapType(3);
                } else {
                    this.X.setMapType(1);
                }
            } else {
                this.X.setMapCustomEnable(true);
                this.X.setCustomMapStylePath(this.T.getCustomMapStylePath());
            }
            this.W.d(this.T.isTrafficLine());
            this.W.e(this.T.isSensorEnable());
            this.W.a(this.T.getLeaderLineColor());
            this.W.a(this.T.isAutoDrawRoute());
            this.W.b(this.T.isLaneInfoShow());
            this.W.f(this.T.isCameraBubbleShow());
            Bitmap startMarker = this.T.getStartMarker();
            Bitmap endMarker = this.T.getEndMarker();
            Bitmap wayMarker = this.T.getWayMarker();
            Bitmap monitorMarker = this.T.getMonitorMarker();
            Bitmap carBitmap = this.T.getCarBitmap();
            Bitmap fourCornersBitmap = this.T.getFourCornersBitmap();
            RouteOverlayOptions routeOverlayOptions = this.T.getRouteOverlayOptions();
            this.W.a(startMarker);
            this.W.b(endMarker);
            this.W.c(wayMarker);
            this.W.d(monitorMarker);
            this.W.e(carBitmap);
            this.W.f(fourCornersBitmap);
            this.W.a(routeOverlayOptions);
            this.V.getNaviSetting().setScreenAlwaysBright(this.T.isScreenAlwaysBright());
            this.V.getNaviSetting().setTrafficInfoUpdateEnabled(this.T.isTrafficInfoUpdateEnabled());
            this.V.getNaviSetting().setCameraInfoUpdateEnabled(this.T.isCameraInfoUpdateEnabled());
            this.V.setReCalculateRouteForYaw(this.T.isReCalculateRouteForYaw());
            this.V.setReCalculateRouteForTrafficJam(this.T.isReCalculateRouteForTrafficJam());
            if (this.T.isSettingMenuEnabled()) {
                this.x.setVisibility(0);
                this.z.setVisibility(0);
            } else {
                this.x.setVisibility(8);
                this.z.setVisibility(8);
            }
            l();
            this.d.setTextColor(-1);
            this.e.setTextColor(-1);
            this.f.setTextColor(-1);
            this.m.setBackgroundDrawable(fo.a().getDrawable(1191313496));
            Drawable drawable = fo.a().getDrawable(1191313508);
            this.x.setBackgroundDrawable(drawable);
            this.y.setBackgroundDrawable(drawable);
            this.w.setImageDrawable(fo.a().getDrawable(1191313538));
            this.z.setImageDrawable(fo.a().getDrawable(1191313540));
            this.n.setTextColor(-1);
            this.o.setTextColor(-1);
            this.W.a("#ffffff", "#ffffff");
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "checkViewOptions()");
        }
    }

    private int c(int i) {
        Context context = this.aq;
        if (i == 0) {
            return 0;
        }
        if (context == null) {
            return i;
        }
        try {
            return (int) TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
            gr.b(e, "AMapNaviView", "dp2px(int dipValue)");
            return i;
        }
    }

    private int a(float f) {
        Context context = this.aq;
        if (f == 0.0f) {
            return 0;
        }
        if (context == null) {
            return (int) f;
        }
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public final void onCreate(Bundle bundle) {
        try {
            this.U.onCreate(bundle);
            this.X = this.U.getMap();
            MyTrafficStyle myTrafficStyle = new MyTrafficStyle();
            myTrafficStyle.setRatio(0.7f);
            myTrafficStyle.setSmoothColor(Color.parseColor("#CC80CD65"));
            myTrafficStyle.setCongestedColor(Color.parseColor("#F2CB7257"));
            myTrafficStyle.setSlowColor(Color.parseColor("#F2D5C247"));
            myTrafficStyle.setSeriousCongestedColor(Color.parseColor("#CCA52A2A"));
            this.X.setMyTrafficStyle(myTrafficStyle);
            this.X.getUiSettings().setZoomControlsEnabled(false);
            this.X.setTrafficEnabled(true);
            a();
            i();
            h();
            c(this.G);
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "onCreate(Bundle bundle)");
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public final void onResume() {
        try {
            this.U.onResume();
            h();
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapNaviView", "onResume()");
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public final void onPause() {
        try {
            this.U.onPause();
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapNaviView", "onPause()");
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public final void onDestroy() {
        try {
            this.V.removeAMapNaviListener(this.W);
            this.W.f();
            this.U.onDestroy();
            fo.b();
            k();
            this.ao.removeAllViews();
            this.Y.removeCallbacksAndMessages(null);
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "onDestroy()");
        }
    }

    private void k() {
        if (this.a != null) {
            this.a.setVisibility(8);
            this.a.recycleResource();
        }
        if (this.ai != null) {
            this.ai.setVisibility(8);
            this.ai.recycleResource();
        }
        if (this.B != null) {
            this.B.setVisibility(8);
            this.B.recycleResource();
        }
        if (this.ah != null) {
            this.ah.setVisibility(8);
            this.ah.recycleResource();
        }
        if (this.q != null) {
            this.q.setVisibility(8);
            this.q.recycleResource();
        }
        if (this.ak != null) {
            this.ak.setVisibility(8);
            this.ak.recycleResource();
        }
        if (this.p != null) {
            this.p.setVisibility(8);
            this.p.recycleResource();
        }
        if (this.aj != null) {
            this.aj.setVisibility(8);
            this.aj.recycleResource();
        }
        if (this.c != null) {
            this.c.setVisibility(8);
            this.c.recycleResource();
        }
        if (this.am != null) {
            this.am.setVisibility(8);
            this.am.recycleResource();
        }
        if (this.r != null) {
            this.r.setVisibility(8);
            this.r.recycleResource();
        }
        if (this.al != null) {
            this.al.setVisibility(8);
            this.al.recycleResource();
        }
        if (this.A != null) {
            this.A.setVisibility(8);
            this.A.recycleResource();
        }
        if (this.as != null) {
            this.as.setVisibility(8);
            this.as.recycleResource();
        }
        if (this.an != null) {
            this.an.setVisibility(8);
        }
        if (this.ar != null) {
            this.ar.setVisibility(8);
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public final void onSaveInstanceState(Bundle bundle) {
        try {
            this.U.onSaveInstanceState(bundle);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapNaviView", "onSaveInstanceState(android.os.Bundle paramBundle)");
        }
    }

    @Override // com.amap.api.maps.AMap.OnMapLoadedListener
    public void onMapLoaded() {
        try {
            fr.b("AmapNaviViewCore-->onMapLoaded()");
            this.H = this.U.getHeight();
            this.I = this.U.getWidth();
            h();
            b((this.F / 10) * 4);
            a(this.E / 2);
            if (!(this.H == 0 || this.I == 0)) {
                this.X.setPointToCenter((int) (this.I * this.P), (int) (this.H * this.Q));
            }
            this.W.a();
            this.W.a(this.V.getNaviPath());
        } finally {
            this.Y.sendEmptyMessage(5);
        }
    }

    private void l() {
        if (this.T.getZoom() != this.R) {
            this.R = this.T.getZoom();
            this.X.moveCamera(CameraUpdateFactory.zoomTo(this.R));
        }
        if (this.T.getTilt() != this.S) {
            this.S = this.T.getTilt();
            this.X.moveCamera(CameraUpdateFactory.changeTilt(this.S));
        }
    }

    @Override // com.amap.api.maps.AMap.OnCameraChangeListener
    public void onCameraChange(CameraPosition cameraPosition) {
        try {
            if (!this.aw) {
                if (getLazyDirectionView() != null) {
                    getLazyDirectionView().setRotate(360.0f - cameraPosition.bearing);
                }
                if (this.q.isShown()) {
                    this.q.setRotate(360.0f - cameraPosition.bearing);
                }
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "onCameraChange(CameraPosition arg0)");
        }
    }

    @Override // com.amap.api.maps.AMap.OnCameraChangeListener
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void displayOverview() {
        e(true);
        a(false, false);
        this.W.b();
    }

    private void m() {
        if (this.W != null) {
            this.W.d();
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void openNorthMode() {
        this.ag = 1;
        a(true);
        this.W.d();
    }

    public void a(AMapNaviCross aMapNaviCross) {
        if (this.K && this.ad) {
            n();
            this.ao.requestLayout();
            this.L = true;
            this.a.setVisibility(0);
            this.a.setIntersectionBitMap(aMapNaviCross);
            b(true);
        }
    }

    public void b() {
        if (this.ad && this.a.getVisibility() == 0) {
            this.L = false;
            o();
            this.a.setVisibility(8);
            this.a.recycleResource();
            this.ao.requestLayout();
            b(false);
        }
    }

    public void a(AMapModelCross aMapModelCross) {
        if (this.ae) {
            GLCrossVector.AVectorCrossAttr aVectorCrossAttr = new GLCrossVector.AVectorCrossAttr();
            aVectorCrossAttr.nCenterX = 0;
            aVectorCrossAttr.nCenterY = 0;
            aVectorCrossAttr.stRectMax = new Rect();
            aVectorCrossAttr.stRectMin = new Rect();
            aVectorCrossAttr.nAngle = 0;
            if (g()) {
                Rect landscapeCross = this.T.getLandscapeCross();
                if (landscapeCross != null) {
                    aVectorCrossAttr.stAreaRect = landscapeCross;
                } else {
                    aVectorCrossAttr.stAreaRect = new Rect(0, c(48), (int) (this.E * 0.4d), this.F);
                }
            } else {
                Rect verticalCross = this.T.getVerticalCross();
                if (verticalCross != null) {
                    aVectorCrossAttr.stAreaRect = verticalCross;
                } else {
                    aVectorCrossAttr.stAreaRect = new Rect(0, c(48), this.E, c(290));
                }
            }
            aVectorCrossAttr.stAreaColor = Color.argb((int) TbsListener.ErrorCode.INCR_UPDATE_FAIL, 95, 95, 95);
            aVectorCrossAttr.fImportBorderWidth = 22;
            aVectorCrossAttr.stImportBorderColor = Color.argb(255, 255, 255, 255);
            aVectorCrossAttr.fUnImportBorderWidth = aVectorCrossAttr.fImportBorderWidth;
            aVectorCrossAttr.stUnImportBorderColor = aVectorCrossAttr.stImportBorderColor;
            aVectorCrossAttr.fArrowBorderWidth = 22;
            aVectorCrossAttr.stArrowBorderColor = Color.argb(0, 0, 50, 20);
            aVectorCrossAttr.fImportLineWidth = 18;
            aVectorCrossAttr.stImportLineColor = Color.argb(255, 150, 170, 200);
            aVectorCrossAttr.fUnImportLineWidth = aVectorCrossAttr.fImportLineWidth;
            aVectorCrossAttr.stUnImportLineColor = aVectorCrossAttr.stImportLineColor;
            aVectorCrossAttr.fDashLineWidth = 2;
            aVectorCrossAttr.stDashLineColor = aVectorCrossAttr.stUnImportBorderColor;
            aVectorCrossAttr.fArrowLineWidth = 18;
            aVectorCrossAttr.stArrowLineColor = Color.argb(255, 255, 253, 65);
            if (this.T.isNaviNight()) {
                aVectorCrossAttr.dayMode = false;
            } else {
                aVectorCrossAttr.dayMode = true;
            }
            try {
                InputStream open = this.aq.getResources().getAssets().open("vector3d_arrow_in.png");
                this.av = getMap().addCrossOverlay(new CrossOverlayOptions().setAttribute(aVectorCrossAttr).setRes(BitmapFactory.decodeStream(open)));
                this.av.setData(aMapModelCross.getPicBuf1());
                this.av.setVisible(true);
                open.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            b(true);
        }
    }

    public void c() {
        if (this.ae && this.av != null) {
            this.av.setVisible(false);
            b(false);
        }
    }

    private void n() {
        if (this.G) {
            this.a.setLayoutParams(new RelativeLayout.LayoutParams(M, -1));
            a(a(M), 0, 0, 0);
            return;
        }
        this.a.setLayoutParams(new RelativeLayout.LayoutParams(-1, N));
    }

    private void o() {
        this.a.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
        if (this.G) {
            a(0, 0, 0, 0);
        }
    }

    private void p() {
        this.W.e();
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void recoverLockMode() {
        a(true);
    }

    public void q() {
        try {
            new AlertDialog.Builder(this.aq).setTitle("提示").setMessage("确定退出导航?").setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.amap.api.col.es.8
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    es.this.V.stopNavi();
                    es.this.Y.sendEmptyMessage(3);
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: com.amap.api.col.es.7
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).show();
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "showDialog()");
        }
    }

    @Override // com.amap.api.maps.AMap.OnMapTouchListener
    public void onTouch(MotionEvent motionEvent) {
        try {
            if (this.a.getVisibility() == 0) {
                this.a.setVisibility(8);
            }
            if (this.aw && this.av != null) {
                this.av.setVisible(false);
            }
            this.Y.sendEmptyMessage(4);
            this.Y.removeMessages(0);
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "onTouch(MotionEvent arg0)");
        }
    }

    public void a(boolean z) {
        a(z, false);
    }

    void a(boolean z, boolean z2) {
        int i = 0;
        try {
            if (!(this.ac == null || this.aa == z || this.J)) {
                this.ac.onLockMap(z);
            }
            if (!this.G) {
                this.C.setVisibility(0);
            } else if (z) {
                this.C.setVisibility(8);
            } else {
                this.C.setVisibility(0);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "setCarLock(boolean isLock, boolean autoRestore)");
        }
        if (!this.J) {
            this.aa = z;
            if (this.ac != null) {
                if (z) {
                    this.ac.onNaviMapMode(0);
                } else {
                    this.ac.onNaviMapMode(1);
                }
            }
            this.Y.removeMessages(0);
            if (z) {
                e(false);
            } else {
                o();
                if (z2) {
                    this.Y.sendEmptyMessageDelayed(0, this.Z);
                }
            }
            this.W.c(z);
            this.g.setVisibility(z ? 8 : 0);
            this.h.setVisibility(z ? 0 : 8);
            this.an.setVisibility(!z ? 0 : 8);
            if (this.T.isRouteListButtonShow()) {
                this.A.setVisibility(!z ? 0 : 8);
            }
            if (this.T.isTrafficBarEnabled() && this.V.getEngineType() == 0) {
                TrafficBarView trafficBarView = this.p;
                if (!z) {
                    i = 8;
                }
                trafficBarView.setVisibility(i);
            }
            this.ao.requestLayout();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        try {
            if (1191772200 == view.getId()) {
                recoverLockMode();
                b(false);
            }
            if (1191772203 == view.getId()) {
                this.Y.sendEmptyMessage(1);
            }
            if (1191772193 == view.getId() && this.ac != null && !this.ac.onNaviBackClick()) {
                this.Y.sendEmptyMessage(2);
            }
            if (1191772180 == view.getId() && this.ac != null) {
                this.ac.onNaviTurnClick();
            }
            if (this.q.equals(view)) {
                r();
            }
            if (this.n.equals(view) && this.ac != null) {
                this.ac.onNextRoadClick();
            }
            if (this.c.equals(view)) {
                this.ab++;
                if (this.ab > 2) {
                    this.ab = 0;
                    t();
                }
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "AMapNaviView", "onClick(View v)");
        }
    }

    public void r() {
        this.X.animateCamera(CameraUpdateFactory.changeBearing(0.0f));
        this.Y.sendEmptyMessage(4);
        this.Y.removeMessages(0);
        this.Y.sendEmptyMessageDelayed(0, this.Z);
    }

    public void s() {
        boolean z = true;
        boolean isTrafficEnabled = this.X.isTrafficEnabled();
        this.r.setIsTrafficOpen(!isTrafficEnabled);
        if (getLazyTrafficButtonView() != null) {
            getLazyTrafficButtonView().setIsTrafficOpen(!isTrafficEnabled);
        }
        if (isTrafficEnabled) {
            z = false;
        }
        setTrafficLine(z);
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public boolean isTrafficLine() {
        return this.X.isTrafficEnabled();
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setTrafficLine(boolean z) {
        this.X.setTrafficEnabled(z);
        if (this.W != null) {
            this.W.d(z);
        }
    }

    private void t() {
        if (this.W != null) {
            this.W.c();
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setAMapNaviViewListener(AMapNaviViewListener aMapNaviViewListener) {
        this.ac = aMapNaviViewListener;
    }

    public void d() {
        this.J = true;
        this.l.setVisibility(0);
        this.g.setVisibility(8);
        this.x.setVisibility(8);
        this.z.setVisibility(8);
        this.h.setVisibility(8);
        this.p.setVisibility(8);
        this.r.setVisibility(8);
        this.e.setVisibility(8);
        this.A.setVisibility(8);
        this.f.setVisibility(8);
    }

    public void e() {
        this.l.setVisibility(8);
        this.x.setVisibility(0);
        this.z.setVisibility(0);
        this.g.setVisibility(8);
        this.e.setVisibility(0);
        this.h.setVisibility(0);
        this.r.setVisibility(0);
        this.p.setVisibility(0);
        this.an.setVisibility(8);
        this.A.setVisibility(8);
        c(this.G);
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public boolean isShowRoadEnlarge() {
        return this.L;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public boolean isOrientationLandscape() {
        return this.G;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void layout(boolean z, int i, int i2, int i3, int i4) {
        try {
            if (u()) {
                v();
                if (this.B != null) {
                    this.B.setDefaultTopMargin(this.e.getHeight());
                    this.B.invalidate();
                }
                if (this.p != null) {
                    this.p.setTmcBarPosition(this.ao.getWidth(), this.ao.getHeight(), this.F, fn.b(this.aq, 55), isOrientationLandscape());
                    w();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "AMapNaviView", "onLayout(boolean changed, int left, int top, int right,\n                            int bottom)");
        }
    }

    private boolean u() {
        return (this.H == this.U.getHeight() && this.I == this.U.getWidth()) ? false : true;
    }

    private void v() {
        this.H = this.U.getHeight();
        this.I = this.U.getWidth();
        if (!(this.H == 0 || this.I == 0)) {
            this.X.setPointToCenter((int) (this.I * this.P), (int) (this.H * this.Q));
        }
        this.W.g();
    }

    private void w() {
        int tmcBarBgHeight;
        if (this.p == null) {
            this.p = new TrafficBarView(this.aq);
        }
        this.D.removeView(this.p);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        int height = this.p.getDisplayingBitmap().getHeight();
        if (isOrientationLandscape()) {
            tmcBarBgHeight = (((this.p.getTmcBarBgHeight() * 2) / 3) - height) >> 1;
        } else {
            tmcBarBgHeight = (this.p.getTmcBarBgHeight() - height) >> 1;
        }
        layoutParams.setMargins(this.p.getTmcBarBgPosX(), tmcBarBgHeight + this.p.getTmcBarBgPosY(), 0, 0);
        this.D.addView(this.p, layoutParams);
    }

    private void d(int i) {
        if (this.K || i == 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.U.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, c(i));
            this.U.setLayoutParams(layoutParams);
        }
    }

    private void a(int i, int i2, int i3, int i4) {
        if (this.K || i4 == 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.U.getLayoutParams();
            layoutParams.setMargins(c(i), c(i2), c(i3), c(i4));
            this.U.setLayoutParams(layoutParams);
        }
    }

    private void b(int i, int i2, int i3, int i4) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.k.getLayoutParams();
        layoutParams.setMargins(c(i), c(i2), c(i3), c(i4));
        this.k.setLayoutParams(layoutParams);
    }

    private void d(boolean z) {
        this.K = z;
        if (!this.K) {
            this.m.setVisibility(8);
            this.k.setVisibility(8);
            this.i.setVisibility(8);
            this.j.setVisibility(8);
            d(0);
            return;
        }
        this.m.setVisibility(0);
        this.k.setVisibility(0);
        this.i.setVisibility(0);
        this.j.setVisibility(0);
        if (!this.G) {
            d(40);
        } else {
            d(0);
        }
    }

    public void e(boolean z) {
        this.O = z;
        this.A.setChecked(z);
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public DriveWayView getLazyDriveWayView() {
        return this.ah;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLazyDriveWayView(DriveWayView driveWayView) {
        this.ah = driveWayView;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public ZoomInIntersectionView getLazyZoomInIntersectionView() {
        return this.ai;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLazyZoomInIntersectionView(ZoomInIntersectionView zoomInIntersectionView) {
        this.ai = zoomInIntersectionView;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public TrafficBarView getLazyTrafficBarView() {
        return this.aj;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLazyTrafficBarView(TrafficBarView trafficBarView) {
        this.aj = trafficBarView;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public DirectionView getLazyDirectionView() {
        return this.ak;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLazyDirectionView(DirectionView directionView) {
        this.ak = directionView;
        this.ak.setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.col.es.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                es.this.r();
            }
        });
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public TrafficButtonView getLazyTrafficButtonView() {
        return this.al;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLazyTrafficButtonView(TrafficButtonView trafficButtonView) {
        this.al = trafficButtonView;
        this.al.setOnClickListener(this.at);
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public NextTurnTipView getLazyNextTurnTipView() {
        return this.am;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLazyNextTurnTipView(NextTurnTipView nextTurnTipView) {
        this.am = nextTurnTipView;
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void zoomIn() {
        a(false);
        this.X.animateCamera(CameraUpdateFactory.zoomIn());
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void zoomOut() {
        a(false);
        this.X.animateCamera(CameraUpdateFactory.zoomOut());
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLazyZoomButtonView(ZoomButtonView zoomButtonView) {
        this.ar = zoomButtonView;
        if (this.ar != null) {
            this.ar.getZoomInBtn().setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.col.es.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (es.this.ao != null) {
                        es.this.ao.zoomIn();
                    }
                }
            });
            this.ar.getZoomOutBtn().setOnClickListener(new View.OnClickListener() { // from class: com.amap.api.col.es.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (es.this.ao != null) {
                        es.this.ao.zoomOut();
                    }
                }
            });
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public void setLazyOverviewButtonView(OverviewButtonView overviewButtonView) {
        this.as = overviewButtonView;
        if (this.as != null) {
            this.as.setChecked(isRouteOverviewNow());
            this.as.setOnClickListener(this.au);
        }
    }

    @Override // com.amap.api.navi.IAMapNaviView
    public boolean isRouteOverviewNow() {
        return this.O;
    }

    /* compiled from: AMapNaviViewCore.java */
    /* loaded from: classes.dex */
    public static class a extends Handler {
        private WeakReference<es> a;

        a(es esVar) {
            try {
                this.a = new WeakReference<>(esVar);
            } catch (Throwable th) {
                th.printStackTrace();
                gr.b(th, "AMapNaviView", "MapViewListenerTriggerHandler()");
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            es esVar = this.a.get();
            if (esVar != null) {
                try {
                    switch (message.what) {
                        case 0:
                            esVar.a(true);
                            break;
                        case 1:
                            if (esVar.ac != null) {
                                esVar.ac.onNaviSetting();
                                break;
                            }
                            break;
                        case 2:
                            esVar.q();
                            break;
                        case 3:
                            if (esVar.ac != null) {
                                esVar.ac.onNaviCancel();
                                break;
                            }
                            break;
                        case 4:
                            esVar.a(false);
                            break;
                        case 5:
                            if (esVar.ac != null) {
                                esVar.ac.onNaviViewLoaded();
                                break;
                            }
                            break;
                    }
                } catch (Throwable th) {
                    fn.a(th);
                    gr.b(th, "AMapNaviView", "MapViewListenerTriggerHandler.handleMessage(android.os.Message msg) ");
                }
            }
        }
    }

    protected void b(boolean z) {
        fr.a("AmapNaviCore", "threadName=" + Thread.currentThread().getName() + ",checkCrossView=" + z);
        if (z) {
            this.aw = true;
            this.r.setVisibility(8);
            this.q.setVisibility(8);
            return;
        }
        this.aw = false;
        this.q.setVisibility(0);
        this.r.setVisibility(0);
    }

    public boolean f() {
        return this.aw;
    }
}

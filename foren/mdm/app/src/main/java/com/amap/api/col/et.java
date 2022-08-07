package com.amap.api.col;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.MyNaviListener;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AmapCarLocation;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.rtbt.CarLocation;
import com.autonavi.rtbt.DGNaviInfo;
import com.autonavi.rtbt.IFrameForRTBT;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FrameForRTBT.java */
/* loaded from: classes.dex */
public class et implements IFrameForRTBT {
    NaviInfo a;
    AMapNaviLocation f;
    AmapCarLocation g;
    private int o;
    private fe q;
    private Context r;

    /* renamed from: u  reason: collision with root package name */
    private boolean f17u;
    int b = 0;
    String c = null;
    int d = 0;
    int e = 0;
    int h = 0;
    byte[] i = null;
    String j = null;
    int k = 0;
    int l = 0;
    private int p = -1;
    int m = 0;
    int n = 0;
    private a t = new a();
    private List<AMapNaviListener> s = new ArrayList();

    public et(Context context, fe feVar) {
        this.q = feVar;
        this.r = context;
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void requestHttp(int i, int i2, int i3, String str, String str2, byte[] bArr, int i4) {
        try {
            String str3 = "http://s.amap.com/" + str;
            if (this.q != null) {
                ip.a(2).a(new fk(this.q, this.r, str3, i3, str2, i, i2, bArr));
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForWTBT", "requestHttp(int moduleID, int connectID, int type, String url,\n                            String head, byte[] data, int dataLength)");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void updateNaviInfo(DGNaviInfo dGNaviInfo) {
        try {
            this.a = new NaviInfo(dGNaviInfo);
            this.a.setCurrentSpeed(this.o);
            this.t.sendEmptyMessage(0);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "updateNaviInfo(DGNaviInfo dgNaviInfo)");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void playNaviSound(int i, String str) {
        try {
            this.b = i;
            if (i != 8 && !str.contains("行进方向有误")) {
                this.c = str;
                this.t.sendEmptyMessage(1);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "playNaviSound(int soundType, String soundStr)");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void endEmulatorNavi() {
        try {
            this.t.sendEmptyMessage(2);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "endEmulatorNavi()");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void arriveWay(int i) {
        try {
            this.d = i;
            this.t.sendEmptyMessage(3);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "arriveWay(int wayId)");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void offRoute() {
        try {
            if (this.t != null) {
                this.t.sendEmptyMessage(5);
            }
            if (this.q != null) {
                this.q.d();
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForWTBT", "offRoute()");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void routeDestroy() {
        try {
            this.t.sendEmptyMessage(6);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "routeDestroy()");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void carLocationChange(CarLocation carLocation) {
        if (carLocation != null) {
            try {
                this.o = carLocation.m_Speed;
                this.f = new AMapNaviLocation();
                this.f.setBearing(carLocation.m_CarDir);
                this.f.setSpeed(carLocation.m_Speed);
                this.f.setMatchStatus(carLocation.m_MatchStatus);
                this.f.setCoord(new NaviLatLng(carLocation.m_Latitude, carLocation.m_Longitude));
                this.f.setTime(System.currentTimeMillis());
                if (this.t != null) {
                    this.t.sendEmptyMessage(7);
                    fr.a("FrameForWTBT carLocationChange(rtbt位置回调),Latitude=" + carLocation.m_Latitude + ",Longitude=" + carLocation.m_Longitude);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                gr.b(th, "FrameForWTBT", "carLocationChange(CarLocation carLocation)");
            }
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void carProjectionChange(CarLocation carLocation) {
        try {
            this.g = new AmapCarLocation(carLocation);
            this.t.sendEmptyMessage(8);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "carProjectionChange(CarLocation carLocation)");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void setRouteRequestState(int i) {
        int i2;
        try {
            this.h = i;
            if (i == 13) {
                this.h = 20;
            }
            switch (i) {
                case 1:
                    if (this.q != null) {
                        i2 = this.q.c(0);
                    } else {
                        i2 = -1;
                    }
                    if (this.s != null) {
                        if (i2 == -1) {
                            this.t.sendEmptyMessage(12);
                            break;
                        } else {
                            this.t.sendEmptyMessage(11);
                            break;
                        }
                    }
                    break;
            }
            if (i != 1) {
                this.t.sendEmptyMessage(12);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            fn.a(th);
            gr.b(th, "FrameForWTBT", "setRouteRequestState(int requestRouteState)");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public int getPlayState() {
        return 0;
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void lockScreenNaviTips(String str, int i, int i2) {
        try {
            this.j = str;
            this.k = i;
            this.l = i2;
            this.t.sendEmptyMessage(13);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "lockScreenNaviTips(String soundStr, int iTurnIcon,\n                                   int iSegRemainLen)");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void vibratePhoneTips(int i, int i2) {
        try {
            this.m = i;
            this.n = i2;
            this.t.sendEmptyMessage(14);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "vibratePhoneTips(int iStrength, int iTime)");
        }
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public int GetDialect() {
        return 0;
    }

    @Override // com.autonavi.rtbt.IFrameForRTBT
    public void PlayVoiceType(int i) {
    }

    @Override // com.amap.api.col.ey
    public void a(AMapNaviListener aMapNaviListener) {
        if (aMapNaviListener != null) {
            try {
                if (this.s != null && !this.s.contains(aMapNaviListener)) {
                    this.s.add(aMapNaviListener);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                fn.a(th);
                gr.b(th, "FrameForWTBT", "addAMapNaviListener(AMapNaviListener naviListener)");
            }
        }
    }

    @Override // com.amap.api.col.ey
    public void b(AMapNaviListener aMapNaviListener) {
        try {
            if (this.s != null) {
                this.s.remove(aMapNaviListener);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            fn.a(th);
            gr.b(th, "FrameForWTBT", "removeNaviListener(AMapNaviListener naviListener)");
        }
    }

    @Override // com.amap.api.col.ey
    public void a() {
        try {
            if (this.s != null) {
                this.s.clear();
                this.s = null;
            }
            this.a = null;
            this.c = null;
            this.f = null;
            this.g = null;
            this.j = null;
            this.q = null;
            this.r = null;
            if (this.t != null) {
                this.t.removeCallbacksAndMessages(null);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            fn.a(th);
            gr.b(th, "FrameForWTBT", "destroy()");
        }
    }

    /* compiled from: FrameForRTBT.java */
    /* loaded from: classes.dex */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                super.handleMessage(message);
                if (et.this.s != null) {
                    switch (message.what) {
                        case 0:
                            for (int i = 0; i < et.this.s.size(); i++) {
                                ((AMapNaviListener) et.this.s.get(i)).onNaviInfoUpdate(et.this.a);
                            }
                            return;
                        case 1:
                            for (int i2 = 0; i2 < et.this.s.size(); i2++) {
                                ((AMapNaviListener) et.this.s.get(i2)).onGetNavigationText(et.this.b, et.this.c);
                            }
                            return;
                        case 2:
                            for (int i3 = 0; i3 < et.this.s.size(); i3++) {
                                ((AMapNaviListener) et.this.s.get(i3)).onEndEmulatorNavi();
                            }
                            return;
                        case 3:
                            if (et.this.d < 0) {
                                return;
                            }
                            if (et.this.d == 0) {
                                for (int i4 = 0; i4 < et.this.s.size(); i4++) {
                                    ((AMapNaviListener) et.this.s.get(i4)).onArriveDestination();
                                }
                                return;
                            }
                            for (int i5 = 0; i5 < et.this.s.size(); i5++) {
                                ((AMapNaviListener) et.this.s.get(i5)).onArrivedWayPoint(et.this.d);
                            }
                            return;
                        case 4:
                        case 5:
                            for (int i6 = 0; i6 < et.this.s.size(); i6++) {
                                ((AMapNaviListener) et.this.s.get(i6)).onReCalculateRouteForYaw();
                            }
                            return;
                        case 6:
                        case 9:
                        case 10:
                        case 13:
                        case 14:
                            return;
                        case 7:
                            if (et.this.f != null) {
                                for (int i7 = 0; i7 < et.this.s.size(); i7++) {
                                    ((AMapNaviListener) et.this.s.get(i7)).onLocationChange(et.this.f);
                                }
                                return;
                            }
                            return;
                        case 8:
                            for (int i8 = 0; i8 < et.this.s.size(); i8++) {
                                if (et.this.s.get(i8) instanceof MyNaviListener) {
                                    ((MyNaviListener) et.this.s.get(i8)).carProjectionChange(et.this.g);
                                }
                            }
                            return;
                        case 11:
                            for (int i9 = 0; i9 < et.this.s.size(); i9++) {
                                ((AMapNaviListener) et.this.s.get(i9)).onCalculateRouteSuccess();
                            }
                            return;
                        case 12:
                            for (int i10 = 0; i10 < et.this.s.size(); i10++) {
                                ((AMapNaviListener) et.this.s.get(i10)).onCalculateRouteFailure(et.this.h);
                            }
                            fi.a("http://restapi.amap.com/v4/direction/bicycling".replace("http://restapi.amap.com/", ""), et.this.h);
                            return;
                        case 15:
                            for (int i11 = 0; i11 < et.this.s.size(); i11++) {
                                ((AMapNaviListener) et.this.s.get(i11)).onInitNaviSuccess();
                            }
                            return;
                        case 16:
                            for (int i12 = 0; i12 < et.this.s.size(); i12++) {
                                ((AMapNaviListener) et.this.s.get(i12)).onInitNaviFailure();
                            }
                            return;
                        case 17:
                            for (int i13 = 0; i13 < et.this.s.size(); i13++) {
                                ((AMapNaviListener) et.this.s.get(i13)).onStartNavi(et.this.p);
                            }
                            return;
                        case 18:
                            break;
                        default:
                            return;
                    }
                    for (int i14 = 0; i14 < et.this.s.size(); i14++) {
                        ((AMapNaviListener) et.this.s.get(i14)).onGpsOpenStatus(et.this.f17u);
                    }
                }
            } catch (Throwable th) {
                fn.a(th);
                gr.b(th, "FrameForWTBT", "NaviListenerTriggerHandler.handleMessage(Message msg)");
            }
        }
    }

    @Override // com.amap.api.col.ey
    public void b() {
        try {
            if (this.t != null) {
                this.t.sendEmptyMessageDelayed(15, 150L);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForTBT", "initSuccess()");
        }
    }

    @Override // com.amap.api.col.ey
    public void c() {
        try {
            if (this.t != null) {
                this.t.sendEmptyMessage(16);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForTBT", "initFailure()");
        }
    }

    @Override // com.amap.api.col.ey
    public void a(int i) {
        try {
            this.p = i;
            if (this.t != null) {
                this.t.sendEmptyMessage(17);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForTBT", "onStartNavi(int flag)");
        }
    }

    @Override // com.amap.api.col.ey
    public NaviInfo d() {
        return this.a;
    }

    @Override // com.amap.api.col.ey
    public void a(boolean z) {
        try {
            this.f17u = z;
            if (this.t != null) {
                this.t.sendEmptyMessage(13);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForTBT", "onGpsOpenStatus(boolean enabled)");
        }
    }
}

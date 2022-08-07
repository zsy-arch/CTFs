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
import com.autonavi.wtbt.CarLocation;
import com.autonavi.wtbt.DGNaviInfo;
import com.autonavi.wtbt.IFrameForWTBT;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FrameForWTBT.java */
/* loaded from: classes.dex */
public class eu implements IFrameForWTBT {
    NaviInfo a;
    AMapNaviLocation f;
    AmapCarLocation g;
    private int o;
    private fg q;
    private Context r;

    /* renamed from: u  reason: collision with root package name */
    private boolean f18u;
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

    public eu(Context context, fg fgVar) {
        this.q = fgVar;
        this.r = context;
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
    public void requestHttp(int i, int i2, int i3, String str, String str2, byte[] bArr, int i4) {
        try {
            String str3 = "http://s.amap.com/" + str;
            if (this.q != null) {
                ip.a(2).a(new fl(this.q, this.r, str3, i3, str2, i, i2, bArr));
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForWTBT", "requestHttp(int moduleID, int connectID, int type, String url,\nString head, byte[] data, int dataLength)");
        }
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
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

    @Override // com.autonavi.wtbt.IFrameForWTBT
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

    @Override // com.autonavi.wtbt.IFrameForWTBT
    public void endEmulatorNavi() {
        try {
            this.t.sendEmptyMessage(2);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "endEmulatorNavi()");
        }
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
    public void arriveWay(int i) {
        try {
            this.d = i;
            this.t.sendEmptyMessage(3);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "arriveWay(int wayId)");
        }
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
    public void offRoute() {
        try {
            if (this.t != null) {
                this.t.sendEmptyMessage(5);
            }
            if (this.q != null) {
                this.q.f();
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForWTBT", "offRoute()");
        }
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
    public void routeDestroy() {
        try {
            this.t.sendEmptyMessage(6);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "routeDestroy()");
        }
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
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
                    fr.a("FrameForWTBT carLocationChange(wtbt位置回调),Latitude=" + carLocation.m_Latitude + ",Longitude=" + carLocation.m_Longitude);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                gr.b(th, "FrameForWTBT", "carLocationChange(CarLocation carLocation)");
            }
        }
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
    public void carProjectionChange(CarLocation carLocation) {
        try {
            this.g = new AmapCarLocation(carLocation);
            this.t.sendEmptyMessage(8);
        } catch (Throwable th) {
            th.printStackTrace();
            gr.b(th, "FrameForWTBT", "carProjectionChange(CarLocation carLocation)");
        }
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
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

    @Override // com.autonavi.wtbt.IFrameForWTBT
    public int getPlayState() {
        return 0;
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
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

    @Override // com.autonavi.wtbt.IFrameForWTBT
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

    @Override // com.autonavi.wtbt.IFrameForWTBT
    public int GetDialect() {
        return 0;
    }

    @Override // com.autonavi.wtbt.IFrameForWTBT
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
            if (this.t != null) {
                this.t.removeCallbacksAndMessages(null);
            }
            this.j = null;
            this.f = null;
            this.g = null;
            this.a = null;
        } catch (Throwable th) {
            th.printStackTrace();
            fn.a(th);
            gr.b(th, "FrameForWTBT", "destroy()");
        }
    }

    /* compiled from: FrameForWTBT.java */
    /* loaded from: classes.dex */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                super.handleMessage(message);
                if (eu.this.s != null) {
                    switch (message.what) {
                        case 0:
                            for (int i = 0; i < eu.this.s.size(); i++) {
                                ((AMapNaviListener) eu.this.s.get(i)).onNaviInfoUpdate(eu.this.a);
                            }
                            return;
                        case 1:
                            for (int i2 = 0; i2 < eu.this.s.size(); i2++) {
                                ((AMapNaviListener) eu.this.s.get(i2)).onGetNavigationText(eu.this.b, eu.this.c);
                            }
                            return;
                        case 2:
                            for (int i3 = 0; i3 < eu.this.s.size(); i3++) {
                                ((AMapNaviListener) eu.this.s.get(i3)).onEndEmulatorNavi();
                            }
                            return;
                        case 3:
                            if (eu.this.d < 0) {
                                return;
                            }
                            if (eu.this.d == 0) {
                                for (int i4 = 0; i4 < eu.this.s.size(); i4++) {
                                    ((AMapNaviListener) eu.this.s.get(i4)).onArriveDestination();
                                }
                                return;
                            }
                            for (int i5 = 0; i5 < eu.this.s.size(); i5++) {
                                ((AMapNaviListener) eu.this.s.get(i5)).onArrivedWayPoint(eu.this.d);
                            }
                            return;
                        case 4:
                        case 5:
                            for (int i6 = 0; i6 < eu.this.s.size(); i6++) {
                                ((AMapNaviListener) eu.this.s.get(i6)).onReCalculateRouteForYaw();
                            }
                            return;
                        case 6:
                        case 9:
                        case 10:
                        case 14:
                            return;
                        case 7:
                            if (eu.this.f != null) {
                                for (int i7 = 0; i7 < eu.this.s.size(); i7++) {
                                    ((AMapNaviListener) eu.this.s.get(i7)).onLocationChange(eu.this.f);
                                }
                                return;
                            }
                            return;
                        case 8:
                            for (int i8 = 0; i8 < eu.this.s.size(); i8++) {
                                if (eu.this.s.get(i8) instanceof MyNaviListener) {
                                    ((MyNaviListener) eu.this.s.get(i8)).carProjectionChange(eu.this.g);
                                }
                            }
                            return;
                        case 11:
                            for (int i9 = 0; i9 < eu.this.s.size(); i9++) {
                                ((AMapNaviListener) eu.this.s.get(i9)).onCalculateRouteSuccess();
                            }
                            return;
                        case 12:
                            for (int i10 = 0; i10 < eu.this.s.size(); i10++) {
                                ((AMapNaviListener) eu.this.s.get(i10)).onCalculateRouteFailure(eu.this.h);
                            }
                            fi.a("http://restapi.amap.com/v3/direction/walking".replace("http://restapi.amap.com/", ""), eu.this.h);
                            return;
                        case 13:
                            for (int i11 = 0; i11 < eu.this.s.size(); i11++) {
                                ((AMapNaviListener) eu.this.s.get(i11)).onGpsOpenStatus(eu.this.f18u);
                            }
                            return;
                        case 15:
                            for (int i12 = 0; i12 < eu.this.s.size(); i12++) {
                                ((AMapNaviListener) eu.this.s.get(i12)).onInitNaviSuccess();
                            }
                            return;
                        case 16:
                            for (int i13 = 0; i13 < eu.this.s.size(); i13++) {
                                ((AMapNaviListener) eu.this.s.get(i13)).onInitNaviFailure();
                            }
                            return;
                        case 17:
                            for (int i14 = 0; i14 < eu.this.s.size(); i14++) {
                                ((AMapNaviListener) eu.this.s.get(i14)).onStartNavi(eu.this.p);
                            }
                            return;
                        case 18:
                            break;
                        default:
                            return;
                    }
                    for (int i15 = 0; i15 < eu.this.s.size(); i15++) {
                        ((AMapNaviListener) eu.this.s.get(i15)).onGpsOpenStatus(eu.this.f18u);
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
            this.f18u = z;
            if (this.t != null) {
                this.t.sendEmptyMessage(13);
            }
        } catch (Throwable th) {
            fn.a(th);
            gr.b(th, "FrameForTBT", "onGpsOpenStatus(boolean enabled)");
        }
    }
}

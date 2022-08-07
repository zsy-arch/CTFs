package com.amap.api.col;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.autonavi.amap.mapcore.Inner_3dMap_location;
import com.autonavi.amap.mapcore.Inner_3dMap_locationOption;

/* compiled from: MapNetLocation.java */
/* loaded from: classes.dex */
public final class jb {
    Context a;
    private ji h;
    private jh i;
    private jk k;
    private ConnectivityManager l;
    private jm m;
    private Inner_3dMap_locationOption o;
    private a j = null;
    boolean b = false;
    private StringBuilder n = new StringBuilder();
    String c = null;
    private it p = null;
    long d = 0;
    WifiInfo e = null;
    boolean f = true;
    private String q = "00:00:00:00:00:00";
    int g = 12;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MapNetLocation.java */
    /* loaded from: classes.dex */
    public class a extends BroadcastReceiver {
        private a() {
        }

        /* synthetic */ a(jb jbVar, byte b) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (context != null && intent != null) {
                try {
                    String action = intent.getAction();
                    if (!TextUtils.isEmpty(action)) {
                        if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                            if (jb.this.h != null) {
                                jb.this.h.c();
                            }
                        } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED") && jb.this.h != null) {
                            jb.this.h.d();
                        }
                    }
                } catch (Throwable th) {
                    jn.a(th, "NetLocation", "onReceive");
                }
            }
        }
    }

    public jb(Context context) {
        this.a = null;
        this.h = null;
        this.i = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.o = null;
        try {
            this.a = context.getApplicationContext();
            jq.b(this.a);
            a(this.a);
            this.o = new Inner_3dMap_locationOption();
            if (this.h == null) {
                this.h = new ji(this.a, (WifiManager) jq.a(this.a, "wifi"));
                this.h.a(this.b);
            }
            if (this.i == null) {
                this.i = new jh(this.a);
            }
            if (this.k == null) {
                this.k = jk.a(this.a);
            }
            if (this.l == null) {
                this.l = (ConnectivityManager) jq.a(this.a, "connectivity");
            }
            this.m = new jm();
            c();
        } catch (Throwable th) {
            jn.a(th, "NetLocation", "<init>");
        }
    }

    private static it a(it itVar, String... strArr) {
        if (strArr == null || strArr.length == 0) {
            return iw.a().a(itVar);
        }
        if (strArr[0].equals("shake")) {
            return iw.a().a(itVar);
        }
        if (!strArr[0].equals("fusion")) {
            return itVar;
        }
        iw.a();
        return iw.b(itVar);
    }

    private void a(Context context) {
        try {
            if (context.checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS") == 0) {
                this.b = true;
            }
        } catch (Throwable th) {
        }
    }

    private boolean a(long j) {
        if (jq.b() - j >= 800) {
            return false;
        }
        long j2 = 0;
        if (jd.a(this.p)) {
            j2 = jq.a() - this.p.getTime();
        }
        return j2 <= 10000;
    }

    private void c() {
        try {
            if (this.j == null) {
                this.j = new a(this, (byte) 0);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            this.a.registerReceiver(this.j, intentFilter);
            this.h.b(false);
            this.i.f();
        } catch (Throwable th) {
            jn.a(th, "NetLocation", "initBroadcastListener");
        }
    }

    private it d() throws Exception {
        it itVar = new it("");
        if (this.h.g()) {
            itVar.setErrorCode(15);
            return itVar;
        }
        try {
            if (this.m == null) {
                this.m = new jm();
            }
            this.m.a(this.a, this.o.isNeedAddress(), this.o.isOffset(), this.i, this.h, this.l, this.q, this.c);
            jc jcVar = new jc();
            byte[] bArr = null;
            String str = "";
            try {
                try {
                    ii a2 = this.k.a(this.k.a(this.a, this.m.a(), jn.a()));
                    if (a2 != null) {
                        bArr = a2.a;
                        str = a2.c;
                    }
                    if (bArr == null || bArr.length == 0) {
                        itVar.setErrorCode(4);
                        this.n.append("please check the network");
                        if (!TextUtils.isEmpty(str)) {
                            this.n.append(" #csid:" + str);
                        }
                        itVar.setLocationDetail(this.n.toString());
                        return itVar;
                    }
                    String str2 = new String(bArr, "UTF-8");
                    if (str2.contains("\"status\":\"0\"")) {
                        return jcVar.a(str2, this.a, a2);
                    }
                    if (str2.contains("</body></html>")) {
                        itVar.setErrorCode(5);
                        if (this.h == null || !this.h.a(this.l)) {
                            this.n.append("request may be intercepted");
                        } else {
                            this.n.append("make sure you are logged in to the network");
                        }
                        if (!TextUtils.isEmpty(str)) {
                            this.n.append(" #csid:" + str);
                        }
                        itVar.setLocationDetail(this.n.toString());
                        return itVar;
                    }
                    byte[] a3 = jj.a(bArr);
                    if (a3 == null) {
                        itVar.setErrorCode(5);
                        this.n.append("decrypt response data error");
                        if (!TextUtils.isEmpty(str)) {
                            this.n.append(" #csid:" + str);
                        }
                        itVar.setLocationDetail(this.n.toString());
                        return itVar;
                    }
                    it a4 = jcVar.a(a3);
                    if (a4 == null) {
                        it itVar2 = new it("");
                        itVar2.setErrorCode(5);
                        this.n.append("location is null");
                        if (!TextUtils.isEmpty(str)) {
                            this.n.append(" #csid:" + str);
                        }
                        itVar2.setLocationDetail(this.n.toString());
                        return itVar2;
                    }
                    this.c = a4.a();
                    if (a4.getErrorCode() != 0) {
                        if (!TextUtils.isEmpty(str)) {
                            a4.setLocationDetail(a4.getLocationDetail() + " #csid:" + str);
                        }
                        return a4;
                    } else if (jd.a(a4)) {
                        if (a4.e() != null) {
                        }
                        if (a4.getErrorCode() == 0 && a4.getLocationType() == 0) {
                            if ("-5".equals(a4.d()) || "1".equals(a4.d()) || "2".equals(a4.d()) || "14".equals(a4.d()) || "24".equals(a4.d()) || "-1".equals(a4.d())) {
                                a4.setLocationType(5);
                            } else {
                                a4.setLocationType(6);
                            }
                            this.n.append(a4.d());
                            if (!TextUtils.isEmpty(str)) {
                                this.n.append(" #csid:" + str);
                            }
                            a4.setLocationDetail(this.n.toString());
                        }
                        return a4;
                    } else {
                        String b = a4.b();
                        a4.setErrorCode(6);
                        StringBuilder sb = this.n;
                        StringBuilder append = new StringBuilder("location faile retype:").append(a4.d()).append(" rdesc:");
                        if (b == null) {
                            b = f.b;
                        }
                        sb.append(append.append(b).toString());
                        if (!TextUtils.isEmpty(str)) {
                            this.n.append(" #csid:" + str);
                        }
                        a4.setLocationDetail(this.n.toString());
                        return a4;
                    }
                } catch (Throwable th) {
                    jn.a(th, "NetLocation", "getApsLoc req");
                    itVar.setErrorCode(4);
                    this.n.append("please check the network");
                    itVar.setLocationDetail(this.n.toString());
                    return itVar;
                }
            } catch (Throwable th2) {
                jn.a(th2, "NetLocation", "getApsLoc buildV4Dot2");
                itVar.setErrorCode(3);
                this.n.append("buildV4Dot2 error " + th2.getMessage());
                itVar.setLocationDetail(this.n.toString());
                return itVar;
            }
        } catch (Throwable th3) {
            jn.a(th3, "NetLocation", "getApsLoc");
            this.n.append("get parames error:" + th3.getMessage());
            itVar.setErrorCode(3);
            itVar.setLocationDetail(this.n.toString());
            return itVar;
        }
    }

    public final Inner_3dMap_location a() {
        if (this.n.length() > 0) {
            this.n.delete(0, this.n.length());
        }
        if (a(this.d) && jd.a(this.p)) {
            return this.p;
        }
        this.d = jq.b();
        if (this.a == null) {
            this.n.append("context is null");
            Inner_3dMap_location inner_3dMap_location = new Inner_3dMap_location("");
            inner_3dMap_location.setErrorCode(1);
            inner_3dMap_location.setLocationDetail(this.n.toString());
            return inner_3dMap_location;
        }
        try {
            this.i.f();
        } catch (Throwable th) {
            jn.a(th, "NetLocation", "getLocation getCgiListParam");
        }
        try {
            this.h.b(true);
        } catch (Throwable th2) {
            jn.a(th2, "NetLocation", "getLocation getScanResultsParam");
        }
        try {
            this.p = d();
            this.p = a(this.p, new String[0]);
        } catch (Throwable th3) {
            jn.a(th3, "NetLocation", "getLocation getScanResultsParam");
        }
        return this.p;
    }

    public final void a(Inner_3dMap_locationOption inner_3dMap_locationOption) {
        this.o = inner_3dMap_locationOption;
        if (this.o == null) {
            this.o = new Inner_3dMap_locationOption();
        }
        try {
            ji jiVar = this.h;
            this.o.isWifiActiveScan();
            jiVar.c(this.o.isWifiScan());
        } catch (Throwable th) {
        }
        try {
            this.k.a(this.o.getHttpTimeOut(), this.o.getLocationProtocol().equals(Inner_3dMap_locationOption.Inner_3dMap_Enum_LocationProtocol.HTTPS));
        } catch (Throwable th2) {
        }
    }

    public final void b() {
        this.b = false;
        this.c = null;
        try {
            if (!(this.a == null || this.j == null)) {
                this.a.unregisterReceiver(this.j);
            }
            if (this.i != null) {
                this.i.g();
            }
            if (this.h != null) {
                this.h.h();
            }
            this.j = null;
        } catch (Throwable th) {
            this.j = null;
            throw th;
        }
    }
}

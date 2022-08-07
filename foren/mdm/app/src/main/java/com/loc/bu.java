package com.loc;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonSerializer;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.em.db.UserDao;
import com.hyphenate.util.EMPrivateConstant;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.json.JSONObject;
import u.aly.dc;

/* compiled from: APS.java */
@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public final class bu {
    private static long S = 0;
    private static volatile long T = 0;
    static int v = -1;
    static long w = 0;
    static long x = 0;
    static int y = -1;
    bv b;
    private volatile Context A = null;
    private ConnectivityManager B = null;
    private volatile cd C = null;
    private volatile cc D = null;
    private ArrayList<ScanResult> E = new ArrayList<>();
    private volatile ArrayList<ScanResult> F = new ArrayList<>();
    private HashMap<String, ArrayList<ScanResult>> G = new HashMap<>();
    private ArrayList<a> H = new ArrayList<>();
    private ArrayList<a> I = new ArrayList<>();
    Object a = new Object();
    private c J = null;
    private volatile WifiInfo K = null;
    private JSONObject L = null;
    private volatile AMapLocationServer M = null;
    private volatile long N = 0;
    private long O = 0;
    private long P = 0;
    private volatile boolean Q = false;
    private boolean R = false;
    private int U = 0;
    private volatile String V = "00:00:00:00:00:00";
    private cp W = null;
    private volatile Timer X = null;
    private volatile TimerTask Y = null;
    private int Z = 0;
    private Object aa = null;
    private volatile Object ab = null;
    private boolean ac = false;
    private boolean ad = false;
    private String ae = null;
    private String af = null;
    private long ag = 0;
    private volatile long ah = 0;
    private volatile String ai = null;
    private cb aj = null;
    private cm ak = null;
    private StringBuilder al = new StringBuilder();
    boolean c = false;
    private boolean am = true;
    private boolean an = true;
    private volatile boolean ao = true;
    private boolean ap = false;
    private boolean aq = false;
    Object d = new Object();
    public boolean e = false;
    public String f = "com.data.carrier_v4.CollectorManager";
    public String g = "com.autonavi.aps.amapapi.offline.Off";
    BluetoothAdapter h = null;
    boolean i = false;
    b j = null;
    boolean k = false;
    private boolean ar = false;
    private boolean as = false;
    volatile boolean l = false;
    int m = 12;
    boolean n = false;
    StringBuilder o = null;
    StringBuilder p = null;
    String q = null;
    TreeMap<Integer, ScanResult> r = null;
    boolean s = true;
    boolean t = true;

    /* renamed from: u */
    boolean f34u = true;
    private String at = null;
    private String au = null;
    StringBuilder z = null;
    private boolean av = false;
    private Map<String, String> aw = new HashMap();

    /* compiled from: APS.java */
    /* loaded from: classes2.dex */
    public static class a implements Comparable<a> {
        public String a;
        public String b;
        public byte[] c;
        public String d;
        public String e;
        public int f;
        public int g;
        public String h;
        public long i;
        public int j = 0;

        public a(String str, String str2, byte[] bArr, String str3, int i, int i2, int i3, int i4, long j) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = 0;
            this.g = 0;
            this.h = null;
            this.i = 0L;
            this.a = str;
            this.b = str2;
            this.c = bArr;
            this.d = Integer.toHexString(i).trim().toUpperCase(Locale.CHINA);
            if (this.d.length() < 4) {
                this.d += "00000";
                this.d = this.d.substring(0, 4);
            }
            this.e = Integer.toHexString(i2).trim().toUpperCase(Locale.CHINA);
            if (this.e.length() < 4) {
                this.e += "00000";
                this.e = this.e.substring(0, 4);
            }
            this.f = i3;
            this.g = i4;
            this.i = j;
            this.h = str3;
        }

        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(a aVar) {
            a aVar2 = aVar;
            if (this.g < aVar2.g) {
                return 1;
            }
            return (this.g == aVar2.g || this.g <= aVar2.g) ? 0 : -1;
        }

        public final String toString() {
            return "name = " + this.b + ",uuid = " + this.a + ",major = " + this.d + ",minor = " + this.e + ",TxPower = " + this.f + ",rssi = " + this.g + ",time = " + this.i;
        }
    }

    /* compiled from: APS.java */
    @SuppressLint({"NewApi"})
    /* loaded from: classes2.dex */
    public class b implements BluetoothAdapter.LeScanCallback {
        b() {
            bu.this = r1;
        }

        @Override // android.bluetooth.BluetoothAdapter.LeScanCallback
        public final void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            int i2;
            try {
                a a = bu.this.a(bluetoothDevice, i, bArr);
                synchronized (bu.this.a) {
                    int i3 = 0;
                    while (i3 < bu.this.H.size()) {
                        a aVar = (a) bu.this.H.get(i3);
                        if (aVar.h.equals(a.h)) {
                            bu.this.H.remove(aVar);
                            bu.this.H.add(a);
                            return;
                        }
                        if (cx.b() - aVar.i > 3000) {
                            bu.this.H.remove(aVar);
                            i2 = i3 - 1;
                        } else {
                            i2 = i3;
                        }
                        i3 = i2 + 1;
                    }
                    bu.this.H.add(a);
                }
            } catch (Throwable th) {
                f.a(th, "APS", "onLeScan");
            }
        }
    }

    /* compiled from: APS.java */
    /* loaded from: classes2.dex */
    public class c extends BroadcastReceiver {
        private c() {
            bu.this = r1;
        }

        /* synthetic */ c(bu buVar, byte b) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (context != null && intent != null) {
                try {
                    String action = intent.getAction();
                    if (!TextUtils.isEmpty(action)) {
                        cd cdVar = bu.this.C;
                        if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                            if (cdVar != null && cx.b() - bu.T > 5000) {
                                List<ScanResult> a = cdVar.a();
                                if (a != null) {
                                    synchronized (bu.this.d) {
                                        bu.this.F.clear();
                                        bu.this.F.addAll(a);
                                        long unused = bu.T = cx.b();
                                    }
                                    return;
                                }
                                synchronized (bu.this.d) {
                                    bu.this.F.clear();
                                }
                            }
                        } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                            if (bu.this.C != null) {
                                int c = cdVar.c();
                                if (bu.this.F == null) {
                                    bu.this.F = new ArrayList();
                                }
                                switch (c) {
                                    case 0:
                                        bu.this.r();
                                        return;
                                    case 1:
                                        bu.this.r();
                                        return;
                                    case 2:
                                    case 3:
                                    default:
                                        return;
                                    case 4:
                                        bu.this.r();
                                        return;
                                }
                            }
                        } else if (action.equals("android.intent.action.SCREEN_ON")) {
                            bu.this.t = true;
                        } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                            bu.this.t = false;
                            if (bu.this.aa != null) {
                                bu.this.A();
                                bu.this.y();
                            }
                        } else if (!action.equals("android.intent.action.AIRPLANE_MODE") && action.equals("android.net.conn.CONNECTIVITY_CHANGE") && bu.this.B() && bu.this.t) {
                            bu.i(bu.this);
                        }
                    }
                } catch (Throwable th) {
                    f.a(th, "APS", "onReceive");
                }
            }
        }
    }

    public synchronized void A() {
        if (this.Y != null) {
            this.Y.cancel();
            this.Y = null;
        }
        if (this.X != null) {
            this.X.cancel();
            this.X.purge();
            this.X = null;
        }
    }

    public boolean B() {
        if (this.C == null || this.B == null) {
            return false;
        }
        return this.C.a(this.B);
    }

    private String C() {
        try {
            if (this.aa != null) {
                return (String) cs.a(this.aa, "getInnerString", "version");
            }
        } catch (Throwable th) {
            f.a(th, "APS", "getCollVer");
        }
        return null;
    }

    private boolean D() {
        return cq.f() && this.ab != null && cq.w();
    }

    private AMapLocationServer a(String str, String str2, JSONObject jSONObject, String str3) {
        Object obj;
        try {
            if (D()) {
                try {
                    obj = cs.a(this.ab, "getPureOfflineLocation", str, str2, jSONObject, str3);
                } catch (Throwable th) {
                    obj = null;
                }
                AMapLocationServer aMapLocationServer = new AMapLocationServer("");
                aMapLocationServer.b(new JSONObject((String) obj));
                return aMapLocationServer;
            }
        } catch (Throwable th2) {
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private AMapLocationServer a(boolean z, boolean z2) throws Exception {
        String str;
        AMapLocationServer aMapLocationServer;
        AMapLocationServer aMapLocationServer2 = new AMapLocationServer("");
        if (this.n) {
            aMapLocationServer2.setErrorCode(15);
            this.n = false;
            return aMapLocationServer2;
        }
        if (!z) {
            this.N = cx.b();
            if (this.A == null) {
                this.al.append("context is null");
                aMapLocationServer2.setErrorCode(1);
                aMapLocationServer2.setLocationDetail(this.al.toString());
                return aMapLocationServer2;
            }
        }
        try {
            String str2 = "0";
            String f = k.f(this.A);
            int a2 = cx.a(-32768, 32767);
            String str3 = "";
            String str4 = "";
            String str5 = "api_serverSDK_130905";
            String str6 = "S128DF1572465B890OE3F7A13167KLEI";
            if (this.W == null) {
                this.W = new cp();
            }
            if (!this.an) {
                str5 = "UC_nlp_20131029";
                str6 = "BKZCHMBBSSUK7U8GLUKHBB56CCFF78U";
            }
            if (this.o == null) {
                this.o = new StringBuilder();
            } else {
                this.o.delete(0, this.o.length());
            }
            if (this.p == null) {
                this.p = new StringBuilder();
            } else {
                this.p.delete(0, this.p.length());
            }
            cc ccVar = this.D;
            int d = ccVar.d();
            int e = ccVar.e();
            TelephonyManager g = ccVar.g();
            ArrayList<cb> b2 = ccVar.b();
            ArrayList<cb> m = ccVar.m();
            if (e == 2) {
                str2 = "1";
            }
            if (g != null) {
                if (TextUtils.isEmpty(f.a)) {
                    f.a = n.q(this.A);
                }
                if (TextUtils.isEmpty(f.a)) {
                    f.a = "888888888888888";
                }
                if (TextUtils.isEmpty(f.b)) {
                    f.b = g.getSubscriberId();
                }
                if (TextUtils.isEmpty(f.b)) {
                    f.b = "888888888888888";
                }
            }
            if (cx.a(this.B.getActiveNetworkInfo()) != -1) {
                String b3 = cx.b(g);
                if (this.s) {
                    cd cdVar = this.C;
                    if (cd.a(this.K)) {
                        str4 = "2";
                        str3 = b3;
                    }
                }
                str4 = "1";
                str3 = b3;
            } else {
                this.K = null;
            }
            if (!b2.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                switch (e) {
                    case 1:
                        a("resetCdmaData");
                        cb cbVar = b2.get(0);
                        sb.delete(0, sb.length());
                        sb.append("<mcc>").append(cbVar.a).append("</mcc>");
                        sb.append("<mnc>").append(cbVar.b).append("</mnc>");
                        sb.append("<lac>").append(cbVar.c).append("</lac>");
                        sb.append("<cellid>").append(cbVar.d);
                        sb.append("</cellid>");
                        sb.append("<signal>").append(cbVar.j);
                        sb.append("</signal>");
                        str = sb.toString();
                        for (int i = 1; i < b2.size(); i++) {
                            cb cbVar2 = b2.get(i);
                            this.o.append(cbVar2.c).append(",");
                            this.o.append(cbVar2.d).append(",");
                            this.o.append(cbVar2.j);
                            if (i < b2.size() - 1) {
                                this.o.append("*");
                            }
                        }
                        break;
                    case 2:
                        cb cbVar3 = b2.get(0);
                        sb.delete(0, sb.length());
                        sb.append("<mcc>").append(cbVar3.a).append("</mcc>");
                        sb.append("<sid>").append(cbVar3.g).append("</sid>");
                        sb.append("<nid>").append(cbVar3.h).append("</nid>");
                        sb.append("<bid>").append(cbVar3.i).append("</bid>");
                        if (cbVar3.f <= 0 || cbVar3.e <= 0) {
                            a("resetCdmaData");
                        } else {
                            if (D()) {
                                cs.a(this.ab, "setCdmaLatLon", Integer.valueOf(cbVar3.e), Integer.valueOf(cbVar3.f));
                            }
                            sb.append("<lon>").append(cbVar3.f).append("</lon>");
                            sb.append("<lat>").append(cbVar3.e).append("</lat>");
                        }
                        sb.append("<signal>").append(cbVar3.j).append("</signal>");
                        str = sb.toString();
                        break;
                    default:
                        a("resetCdmaData");
                        str = "";
                        break;
                }
                sb.delete(0, sb.length());
            } else {
                str = "";
            }
            if ((d & 4) != 4 || m.isEmpty()) {
                this.W.C.clear();
            } else {
                this.W.C.clear();
                this.W.C.addAll(m);
            }
            if (t()) {
                cd cdVar2 = this.C;
                if (cd.a(this.K)) {
                    this.p.append(this.K.getBSSID()).append(",");
                    int rssi = this.K.getRssi();
                    if (rssi < -128) {
                        rssi = 0;
                    } else if (rssi > 127) {
                        rssi = 0;
                    }
                    this.p.append(rssi).append(",");
                    String ssid = this.K.getSSID();
                    int i2 = 32;
                    try {
                        i2 = this.K.getSSID().getBytes("UTF-8").length;
                    } catch (Exception e2) {
                    }
                    if (i2 >= 32) {
                        ssid = "unkwn";
                    }
                    this.p.append(ssid.replace("*", "."));
                }
                if (this.E != null) {
                    synchronized (this.d) {
                        if (this.W.F != null) {
                            this.W.F.clear();
                            this.W.F.addAll(this.E);
                        }
                    }
                }
            } else {
                r();
                if (this.W.F != null) {
                    this.W.F.clear();
                }
            }
            boolean z3 = true;
            if (cx.a(this.L, "reversegeo")) {
                z3 = this.L.getBoolean("reversegeo");
            }
            if (!z3) {
                this.W.b = (short) 2;
            } else {
                this.W.b = (short) 0;
            }
            if (cx.a(this.L, "multi") && this.L.getString("multi").equals("1")) {
                cp cpVar = this.W;
                cpVar.b = (short) (cpVar.b + 1);
            }
            if (i()) {
                p();
            } else {
                this.I.clear();
                this.W.G.clear();
            }
            this.W.c = str5;
            this.W.d = str6;
            this.W.f = cx.d();
            this.W.g = f.a + cx.e();
            this.W.h = cx.b(this.A);
            this.W.i = str2;
            this.W.j = "0";
            this.W.k = this.R ? "1" : "0";
            this.W.l = "0";
            this.W.m = "0";
            this.W.n = "0";
            this.W.o = f;
            this.W.p = f.a;
            this.W.q = f.b;
            this.W.s = String.valueOf(a2);
            this.W.t = this.V;
            this.W.v = "3.3.0";
            this.W.w = C();
            this.W.f35u = "";
            this.W.x = str3;
            this.W.y = str4;
            this.W.z = d;
            this.W.A = str;
            this.W.B = this.o.toString();
            this.W.D = ccVar.l();
            this.W.H = String.valueOf(cx.b() - T);
            this.W.E = this.p.toString();
            this.W.G = this.I;
            try {
                if (TextUtils.isEmpty(this.q)) {
                    this.q = n.f(this.A);
                }
                this.W.K = this.q;
            } catch (Throwable th) {
            }
            this.o.delete(0, this.o.length());
            this.p.delete(0, this.p.length());
            co coVar = new co();
            byte[] bArr = null;
            String str7 = "";
            try {
                try {
                    bo a3 = this.ak.a(this.A, this.L, this.W.a(), f.a(), z2);
                    int a4 = this.ak.a();
                    if (a3 != null) {
                        bArr = a3.a;
                        str7 = a3.c;
                    }
                    aMapLocationServer2.a(a4);
                    if (z) {
                        aMapLocationServer = aMapLocationServer2;
                    } else if (bArr == null || bArr.length == 0) {
                        aMapLocationServer2.setErrorCode(4);
                        this.al.append("please check the network");
                        if (!TextUtils.isEmpty(str7)) {
                            this.al.append(" #csid:" + str7);
                        }
                        aMapLocationServer2.f(this.z.toString());
                        aMapLocationServer2.setLocationDetail(this.al.toString());
                        return aMapLocationServer2;
                    } else {
                        String str8 = new String(bArr, "UTF-8");
                        if (str8.contains("\"status\":\"0\"")) {
                            AMapLocationServer a5 = coVar.a(str8, this.A, a3);
                            a5.f(this.z.toString());
                            a5.a(a4);
                            return a5;
                        } else if (str8.contains("</body></html>")) {
                            aMapLocationServer2.setErrorCode(5);
                            if (B()) {
                                this.al.append("make sure you are logged in to the network");
                            } else {
                                this.al.append("request may be intercepted");
                            }
                            if (!TextUtils.isEmpty(str7)) {
                                this.al.append(" #csid:" + str7);
                            }
                            aMapLocationServer2.setLocationDetail(this.al.toString());
                            return aMapLocationServer2;
                        } else {
                            byte[] a6 = ce.a(bArr);
                            if (a6 == null) {
                                aMapLocationServer2.setErrorCode(5);
                                this.al.append("decrypt response data error");
                                if (!TextUtils.isEmpty(str7)) {
                                    this.al.append(" #csid:" + str7);
                                }
                                aMapLocationServer2.setLocationDetail(this.al.toString());
                                return aMapLocationServer2;
                            }
                            AMapLocationServer a7 = coVar.a(a6);
                            if (a7 == null) {
                                AMapLocationServer aMapLocationServer3 = new AMapLocationServer("");
                                aMapLocationServer3.setErrorCode(5);
                                this.al.append("location is null");
                                if (!TextUtils.isEmpty(str7)) {
                                    this.al.append(" #csid:" + str7);
                                }
                                aMapLocationServer3.setLocationDetail(this.al.toString());
                                return aMapLocationServer3;
                            }
                            a7.f(this.z.toString());
                            try {
                                if (!"-1".equals(a7.c()) || a7.getAccuracy() > 5.0f) {
                                    j();
                                } else if (!this.i && i()) {
                                    if (this.j == null) {
                                        this.j = new b();
                                    }
                                    this.h.startLeScan(this.j);
                                    this.i = true;
                                }
                            } catch (Throwable th2) {
                            }
                            a7.a(a4);
                            if (a7.getErrorCode() != 0) {
                                if (!TextUtils.isEmpty(str7)) {
                                    a7.setLocationDetail(a7.getLocationDetail() + " #csid:" + str7);
                                }
                                return a7;
                            } else if (cx.a(a7)) {
                                if (a7.e() != null) {
                                }
                                if (a7.getErrorCode() == 0 && a7.getLocationType() == 0) {
                                    if ("-5".equals(a7.c()) || "1".equals(a7.c()) || "2".equals(a7.c()) || "14".equals(a7.c()) || "24".equals(a7.c()) || "-1".equals(a7.c())) {
                                        a7.setLocationType(5);
                                    } else {
                                        a7.setLocationType(6);
                                    }
                                    this.al.append(a7.c());
                                    if (!TextUtils.isEmpty(str7)) {
                                        this.al.append(" #csid:" + str7);
                                    }
                                    a7.setLocationDetail(this.al.toString());
                                }
                                a7.setOffset(this.an);
                                a7.a(this.am);
                                aMapLocationServer = a7;
                            } else {
                                this.ae = a7.a();
                                a7.setErrorCode(6);
                                this.al.append("location faile retype:" + a7.c() + " rdesc:" + (this.ae != null ? this.ae : f.b));
                                if (!TextUtils.isEmpty(str7)) {
                                    this.al.append(" #csid:" + str7);
                                }
                                a7.f(this.z.toString());
                                a7.setLocationDetail(this.al.toString());
                                return a7;
                            }
                        }
                    }
                    return aMapLocationServer;
                } catch (Throwable th3) {
                    f.a(th3, "APS", "getApsLoc req");
                    aMapLocationServer2.setErrorCode(4);
                    aMapLocationServer2.f(this.z.toString());
                    this.al.append("please check the network");
                    aMapLocationServer2.setLocationDetail(this.al.toString());
                    return aMapLocationServer2;
                }
            } catch (Throwable th4) {
                f.a(th4, "APS", "getApsLoc buildV4Dot2");
                aMapLocationServer2.setErrorCode(3);
                this.al.append("buildV4Dot2 error " + th4.getMessage());
                aMapLocationServer2.setLocationDetail(this.al.toString());
                return aMapLocationServer2;
            }
        } catch (Throwable th5) {
            this.al.append("get parames error:" + th5.getMessage());
            aMapLocationServer2.setErrorCode(3);
            aMapLocationServer2.setLocationDetail(this.al.toString());
            aMapLocationServer2.f(this.z.toString());
            return aMapLocationServer2;
        }
    }

    private String a(byte[] bArr) {
        if (bArr == null || bArr.length <= 24) {
            return "";
        }
        if (bArr[0] == 2 && bArr[1] == 1 && ((bArr[2] == 5 || bArr[2] == 6) && bArr[3] == 23)) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 9, bArr2, 0, 16);
            StringBuffer stringBuffer = new StringBuffer();
            int length = bArr2.length;
            for (int i = 0; i < length; i++) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(bArr2[i])));
            }
            String stringBuffer2 = stringBuffer.toString();
            String str = this.aw.get(stringBuffer2);
            if (str != null) {
                return str;
            }
            byte[] a2 = ce.a(cx.c(bArr2), new BigInteger("8021267762677846189778330391499"), new BigInteger("49549924105414102803086139689747"));
            if (a2 == null || a2.length < 8) {
                return "";
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            for (int i2 = 6; i2 > 0; i2--) {
                stringBuffer3.append(String.format("%02X", Byte.valueOf(a2[i2])));
            }
            String stringBuffer4 = stringBuffer3.toString();
            this.aw.put(stringBuffer2, stringBuffer4);
            return stringBuffer4;
        }
        if (bArr[0] == 2 && bArr[1] == 1 && bArr[2] == 6 && bArr[3] == 22 && bArr[5] == -88 && bArr[6] == 1 && bArr[7] == 32) {
            try {
                byte[] b2 = ce.b(cx.d(bArr), new byte[]{-1, -15, 55, 33, 4, 21, dc.n, 20, -85, 9, 0, 2, -91, -43, -59, -75});
                if (b2 != null) {
                    StringBuffer stringBuffer5 = new StringBuffer();
                    for (int i3 = 0; i3 < 8; i3++) {
                        stringBuffer5.append(String.format("%02X", Byte.valueOf(b2[i3])));
                    }
                    return stringBuffer5.toString();
                }
            } catch (Throwable th) {
            }
        }
        return "";
    }

    private synchronized StringBuilder a(StringBuilder sb, boolean z) {
        boolean z2 = false;
        synchronized (this) {
            cc ccVar = this.D;
            if (this.Q) {
                ccVar.h();
            }
            if (sb == null) {
                sb = new StringBuilder(700);
            } else {
                sb.delete(0, sb.length());
            }
            int e = ccVar.e();
            ArrayList<cb> b2 = ccVar.b();
            switch (e) {
                case 1:
                    for (int i = 1; i < b2.size(); i++) {
                        sb.append("#").append(b2.get(i).b);
                        sb.append("|").append(b2.get(i).c);
                        sb.append("|").append(b2.get(i).d);
                    }
                    break;
            }
            if (((!z && TextUtils.isEmpty(this.V)) || this.V.equals("00:00:00:00:00:00")) && this.K != null) {
                this.V = n.i(this.A);
                if (this.A != null && !TextUtils.isEmpty(this.V)) {
                    SharedPreferences sharedPreferences = this.A.getSharedPreferences(UserDao.PREF_TABLE_NAME, 0);
                    String a2 = o.a(this.V.getBytes("UTF-8"));
                    if (!TextUtils.isEmpty(a2)) {
                        sharedPreferences.edit().putString("smac", a2).commit();
                    }
                }
                if (TextUtils.isEmpty(this.V)) {
                    this.V = "00:00:00:00:00:00";
                }
            }
            String str = "";
            cd cdVar = this.C;
            if (cd.a(this.K)) {
                str = this.K.getBSSID();
            }
            this.n = false;
            int size = this.E.size();
            boolean z3 = false;
            boolean z4 = false;
            for (int i2 = 0; i2 < size; i2++) {
                String str2 = this.E.get(i2).BSSID;
                z3 = (this.k || "<unknown ssid>".equals(this.E.get(i2).SSID)) ? z3 : true;
                String str3 = "nb";
                if (str.equals(str2)) {
                    str3 = "access";
                    z4 = true;
                } else {
                    z4 = z4;
                }
                sb.append(String.format(Locale.US, "#%s,%s", str2, str3));
            }
            boolean z5 = this.E.size() == 0 ? true : z3;
            try {
                if (!this.k && !z5) {
                    List<WifiConfiguration> g = this.C.g();
                    int i3 = 0;
                    while (g != null) {
                        if (i3 < g.size()) {
                            z2 = sb.toString().contains(g.get(i3).BSSID) ? true : z2;
                            i3++;
                        }
                    }
                }
            } catch (Throwable th) {
            }
            if (!this.k && !z5 && !z2) {
                this.n = true;
            }
            if (!z4 && !TextUtils.isEmpty(str)) {
                sb.append("#").append(str);
                sb.append(",access");
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(0);
            }
        }
        return sb;
    }

    static /* synthetic */ void a(bu buVar, int i) {
        Object obj;
        int i2 = 674234367;
        if (buVar.u() && cq.v()) {
            switch (i) {
                case 0:
                    i2 = 70254591;
                    break;
                case 1:
                    break;
                case 2:
                    try {
                        if (buVar.B()) {
                            i2 = 2083520511;
                            break;
                        }
                    } catch (Throwable th) {
                        f.a(th, "APS", "up");
                        return;
                    }
                    break;
                default:
                    i2 = 70254591;
                    break;
            }
            Object obj2 = buVar.aa;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("e", 1);
            jSONObject.put("d", i2);
            jSONObject.put("u", 1);
            cs.a(obj2, "callBackWrapData", jSONObject.toString());
            try {
                obj = cs.a(buVar.aa, "getReportDate", new Object[0]);
            } catch (Throwable th2) {
                obj = null;
            }
            if (obj != null) {
                String b2 = buVar.ak.b((byte[]) obj, buVar.A, "http://cgicol.amap.com/collection/writedata?ver=v1.0_ali&");
                if (buVar.u()) {
                    buVar.Z = ((Integer) cs.a(buVar.aa, "setUploadResult", b2, Integer.valueOf(i2))).intValue();
                }
            }
            buVar.x();
            if (buVar.u() && buVar.z() == 0) {
                buVar.A();
            } else if (buVar.Z >= 3) {
                buVar.A();
            }
        }
    }

    private void a(String str) {
        try {
            if (D()) {
                try {
                    cs.a(this.ab, str, new Object[0]);
                } catch (Throwable th) {
                }
                if (this.A == null) {
                }
            } else if (this.ab != null) {
                cs.a(this.ab, "stopOff", new Object[0]);
                this.l = false;
            }
        } catch (Throwable th2) {
        }
    }

    private static boolean a(int i) {
        int i2 = 20;
        try {
            i2 = WifiManager.calculateSignalLevel(i, 20);
        } catch (ArithmeticException e) {
            f.a(e, "APS", "wifiSigFine");
        }
        return i2 > 0;
    }

    private void b(JSONObject jSONObject) {
        try {
            if (D()) {
                cs.a(this.ab, "setLastLoc", jSONObject);
            }
        } catch (Throwable th) {
        }
    }

    private void c(Context context) {
        try {
            if (this.A == null) {
                this.A = context.getApplicationContext();
                cq.e(this.A);
                cx.b(this.A);
                if (this.C == null) {
                    Context context2 = this.A;
                    JSONObject jSONObject = this.L;
                    this.C = new cd(context2, (WifiManager) cx.a(this.A, "wifi"));
                }
                if (this.D == null) {
                    Context context3 = this.A;
                    JSONObject jSONObject2 = this.L;
                    this.D = new cc(context3);
                }
            }
        } catch (Throwable th) {
            f.a(th, "APS", "initBase");
        }
    }

    private void d(Context context) {
        try {
            if (cq.w() && this.ab == null && !this.as) {
                s a2 = f.a("OfflineLocation", JsonSerializer.VERSION);
                this.as = cu.a(context, a2);
                if (this.as) {
                    try {
                        this.ab = au.a(context, a2, this.g, null, new Class[]{Context.class}, new Object[]{context});
                    } catch (Throwable th) {
                    }
                } else {
                    this.as = true;
                }
            }
        } catch (Throwable th2) {
            f.a(th2, "APS", "initOffLocation");
        }
    }

    private void h() {
        this.ak = cm.a(this.A);
        if (this.B == null) {
            this.B = (ConnectivityManager) cx.a(this.A, "connectivity");
        }
        this.W = new cp();
    }

    static /* synthetic */ void i(bu buVar) {
        if (!buVar.u()) {
            return;
        }
        if (!cq.v()) {
            buVar.A();
            return;
        }
        if (buVar.Y == null) {
            buVar.Y = new TimerTask() { // from class: com.loc.bu.1
                final /* synthetic */ int a = 2;

                @Override // java.util.TimerTask, java.lang.Runnable
                public final void run() {
                    try {
                        Thread.currentThread().setPriority(1);
                        long b2 = cx.b() - bu.this.P;
                        if (bu.this.u() && bu.this.z() == 0) {
                            bu.this.A();
                        }
                        if (b2 >= 10000) {
                            if (bu.this.B()) {
                                bu.a(bu.this, this.a);
                            } else {
                                bu.this.A();
                            }
                        }
                    } catch (Throwable th) {
                        f.a(th, "APS", "timerTaskU run");
                    }
                }
            };
        }
        if (buVar.X == null) {
            buVar.X = new Timer("T-U", false);
            buVar.X.schedule(buVar.Y, 2000L, 2000L);
        }
    }

    private boolean i() {
        try {
            if (this.h == null || !this.h.isEnabled() || !cq.C()) {
                return false;
            }
            return cx.c() >= 18;
        } catch (Throwable th) {
            return false;
        }
    }

    private void j() {
        try {
            if (cx.c() >= 18 && this.h != null) {
                if (this.j == null) {
                    this.j = new b();
                }
                if (this.i) {
                    this.h.stopLeScan(this.j);
                }
                this.i = false;
                this.I.clear();
                this.H.clear();
            }
        } catch (Throwable th) {
        }
    }

    private void k() {
        try {
            if (D()) {
                cs.a(this.ab, "resetPureOfflineCache", new Object[0]);
            }
        } catch (Throwable th) {
        }
    }

    private void l() {
        try {
            if (this.J == null) {
                this.J = new c(this, (byte) 0);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
            intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.A.registerReceiver(this.J, intentFilter);
            s();
        } catch (Throwable th) {
            f.a(th, "APS", "initBroadcastListener");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x01d2 A[Catch: all -> 0x005b, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x000c, B:9:0x0026, B:11:0x002c, B:13:0x0030, B:15:0x0038, B:18:0x0048, B:20:0x0050, B:23:0x0055, B:27:0x0060, B:29:0x0068, B:30:0x006d, B:31:0x0079, B:32:0x007c, B:33:0x0087, B:35:0x008d, B:37:0x0095, B:38:0x00a4, B:39:0x00ba, B:41:0x00c0, B:43:0x0109, B:46:0x0115, B:48:0x0121, B:50:0x0127, B:52:0x017b, B:55:0x0187, B:57:0x0193, B:59:0x019b, B:62:0x01a6, B:64:0x01b0, B:66:0x01b8, B:67:0x01c2, B:69:0x01d2, B:70:0x01e7, B:72:0x01ef, B:74:0x01fc, B:75:0x0204, B:77:0x021b, B:78:0x0223, B:80:0x022b), top: B:83:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01ef A[Catch: all -> 0x005b, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x000c, B:9:0x0026, B:11:0x002c, B:13:0x0030, B:15:0x0038, B:18:0x0048, B:20:0x0050, B:23:0x0055, B:27:0x0060, B:29:0x0068, B:30:0x006d, B:31:0x0079, B:32:0x007c, B:33:0x0087, B:35:0x008d, B:37:0x0095, B:38:0x00a4, B:39:0x00ba, B:41:0x00c0, B:43:0x0109, B:46:0x0115, B:48:0x0121, B:50:0x0127, B:52:0x017b, B:55:0x0187, B:57:0x0193, B:59:0x019b, B:62:0x01a6, B:64:0x01b0, B:66:0x01b8, B:67:0x01c2, B:69:0x01d2, B:70:0x01e7, B:72:0x01ef, B:74:0x01fc, B:75:0x0204, B:77:0x021b, B:78:0x0223, B:80:0x022b), top: B:83:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0223 A[Catch: all -> 0x005b, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x000c, B:9:0x0026, B:11:0x002c, B:13:0x0030, B:15:0x0038, B:18:0x0048, B:20:0x0050, B:23:0x0055, B:27:0x0060, B:29:0x0068, B:30:0x006d, B:31:0x0079, B:32:0x007c, B:33:0x0087, B:35:0x008d, B:37:0x0095, B:38:0x00a4, B:39:0x00ba, B:41:0x00c0, B:43:0x0109, B:46:0x0115, B:48:0x0121, B:50:0x0127, B:52:0x017b, B:55:0x0187, B:57:0x0193, B:59:0x019b, B:62:0x01a6, B:64:0x01b0, B:66:0x01b8, B:67:0x01c2, B:69:0x01d2, B:70:0x01e7, B:72:0x01ef, B:74:0x01fc, B:75:0x0204, B:77:0x021b, B:78:0x0223, B:80:0x022b), top: B:83:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized java.lang.String m() {
        /*
            Method dump skipped, instructions count: 584
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bu.m():java.lang.String");
    }

    private void n() {
        boolean z = false;
        long b2 = cx.b();
        if (!t()) {
            this.s = false;
        } else if (S == 0) {
            z = true;
        } else if (cx.b() - S >= 5000 && cx.b() - T >= 1500) {
            z = true;
        }
        if (z || cx.b() - T > 5000) {
            if (b2 - this.O >= 10000) {
                synchronized (this.d) {
                    this.F.clear();
                }
            }
            s();
            if (b2 - this.O >= 10000) {
                for (int i = 20; i > 0 && this.F.isEmpty(); i--) {
                    try {
                        Thread.sleep(150L);
                    } catch (Throwable th) {
                    }
                }
            }
            synchronized (this.d) {
            }
        }
        if (this.F.isEmpty() && this.C != null) {
            T = cx.b();
            List<ScanResult> a2 = this.C.a();
            if (a2 != null) {
                synchronized (this.d) {
                    this.F.addAll(a2);
                }
            }
        }
    }

    private synchronized void o() {
        ArrayList<ScanResult> arrayList = this.E;
        ArrayList<ScanResult> arrayList2 = this.F;
        arrayList.clear();
        synchronized (this.d) {
            if (arrayList2 != null && arrayList2.size() > 0) {
                arrayList.addAll(arrayList2);
            }
        }
    }

    private synchronized void p() {
        ArrayList<a> arrayList = this.I;
        ArrayList<a> arrayList2 = this.H;
        arrayList.clear();
        synchronized (this.a) {
            if (arrayList2 != null && arrayList2.size() > 0) {
                if (arrayList2.size() > 20) {
                    Collections.sort(arrayList2);
                }
                for (int i = 0; i < arrayList2.size(); i++) {
                    arrayList.add(arrayList2.get(i));
                    if (i >= 30) {
                        break;
                    }
                }
            }
        }
    }

    private synchronized void q() {
        if (this.E != null && !this.E.isEmpty()) {
            if (cx.b() - T > com.umeng.analytics.a.k) {
                r();
                this.E.clear();
            }
            boolean f = cx.f();
            if (!cx.a(this.L, "nbssid")) {
                f = f;
            } else if (this.L.getString("nbssid").equals("1")) {
                f = true;
            } else if (this.L.getString("nbssid").equals("0")) {
                f = false;
            }
            if (this.r == null) {
                this.r = new TreeMap<>(Collections.reverseOrder());
            }
            this.r.clear();
            int size = this.E.size();
            for (int i = 0; i < size; i++) {
                ScanResult scanResult = this.E.get(i);
                if (cx.b(scanResult != null ? scanResult.BSSID : "") && (size <= 20 || a(scanResult.level))) {
                    if (TextUtils.isEmpty(scanResult.SSID)) {
                        scanResult.SSID = "unkwn";
                    } else if (f) {
                        scanResult.SSID = scanResult.SSID.replace("*", ".");
                        if (scanResult.SSID.getBytes("UTF-8").length >= 32 && !"<unknown ssid>".equals(scanResult.SSID)) {
                            scanResult.SSID = String.valueOf(i);
                        }
                    } else if (!"<unknown ssid>".equals(scanResult.SSID)) {
                        scanResult.SSID = String.valueOf(i);
                    }
                    this.r.put(Integer.valueOf((scanResult.level * 30) + i), scanResult);
                }
            }
            this.E.clear();
            for (ScanResult scanResult2 : this.r.values()) {
                this.E.add(scanResult2);
            }
            this.r.clear();
        }
    }

    public void r() {
        this.K = null;
        synchronized (this.d) {
            this.F.clear();
            this.G.clear();
        }
    }

    private void s() {
        if (this.f34u) {
            try {
                if (this.C.d()) {
                    S = cx.b();
                }
            } catch (Throwable th) {
                f.a(th, "APS", "updateWifi");
            }
        }
    }

    private boolean t() {
        if (this.C != null) {
            return this.C.e();
        }
        return false;
    }

    public boolean u() {
        return this.aa != null;
    }

    private boolean v() {
        try {
            if (!u()) {
                return false;
            }
            return w();
        } catch (Throwable th) {
            f.a(th, "APS", "collStarted");
            return false;
        }
    }

    private boolean w() {
        try {
            return ((Boolean) cs.a(this.aa, "isStarted", new Object[0])).booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    private void x() {
        if (u() && u() && z() > 0) {
        }
    }

    public void y() {
        if (u() && v()) {
            f.f = 20;
            try {
                cs.a(this.aa, EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY, new Object[0]);
            } catch (Throwable th) {
                f.a(th, "APS", "stop3rdCM");
            }
        }
    }

    public int z() {
        try {
            return ((Integer) cs.a(this.aa, "getLeftUploadNum", new Object[0])).intValue();
        } catch (Throwable th) {
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x016a A[Catch: all -> 0x0116, TryCatch #4 {, blocks: (B:4:0x0006, B:6:0x000e, B:7:0x001a, B:9:0x001e, B:12:0x003b, B:14:0x0045, B:16:0x004c, B:18:0x0050, B:20:0x0054, B:22:0x0058, B:24:0x005e, B:25:0x0068, B:27:0x006e, B:28:0x0078, B:30:0x0080, B:32:0x008a, B:35:0x0099, B:38:0x00a2, B:39:0x00b1, B:41:0x00b7, B:43:0x00bf, B:44:0x00c1, B:46:0x00cf, B:48:0x00d7, B:53:0x00eb, B:55:0x00f3, B:57:0x00f7, B:59:0x0103, B:60:0x0109, B:61:0x010d, B:64:0x0119, B:66:0x011d, B:68:0x0145, B:69:0x014d, B:70:0x015c, B:72:0x016a, B:74:0x016e, B:77:0x0177, B:79:0x017d, B:80:0x018e, B:82:0x0194, B:84:0x01a0, B:85:0x01a4, B:86:0x01bb, B:88:0x01c1, B:90:0x01c5, B:91:0x01dc, B:93:0x01e9, B:95:0x01f2, B:97:0x01f6, B:99:0x01fa, B:102:0x0203, B:105:0x020a, B:107:0x020e, B:112:0x0225, B:114:0x0229, B:118:0x0231, B:120:0x0235, B:122:0x0241, B:124:0x0252, B:126:0x0258, B:128:0x0265, B:130:0x026d, B:132:0x028b, B:138:0x02a2, B:139:0x02a8, B:141:0x02ac, B:143:0x02b6, B:145:0x02c3, B:146:0x02c7, B:150:0x02f8, B:152:0x0308, B:155:0x0310, B:157:0x0320, B:158:0x0334, B:160:0x0359, B:162:0x0373, B:163:0x039e, B:164:0x03ae, B:165:0x03b4, B:166:0x03c0, B:168:0x03c4, B:169:0x03d0, B:171:0x03da, B:173:0x03ea, B:174:0x03f9), top: B:185:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01bb A[Catch: Throwable -> 0x027e, all -> 0x0116, TRY_ENTER, TryCatch #4 {, blocks: (B:4:0x0006, B:6:0x000e, B:7:0x001a, B:9:0x001e, B:12:0x003b, B:14:0x0045, B:16:0x004c, B:18:0x0050, B:20:0x0054, B:22:0x0058, B:24:0x005e, B:25:0x0068, B:27:0x006e, B:28:0x0078, B:30:0x0080, B:32:0x008a, B:35:0x0099, B:38:0x00a2, B:39:0x00b1, B:41:0x00b7, B:43:0x00bf, B:44:0x00c1, B:46:0x00cf, B:48:0x00d7, B:53:0x00eb, B:55:0x00f3, B:57:0x00f7, B:59:0x0103, B:60:0x0109, B:61:0x010d, B:64:0x0119, B:66:0x011d, B:68:0x0145, B:69:0x014d, B:70:0x015c, B:72:0x016a, B:74:0x016e, B:77:0x0177, B:79:0x017d, B:80:0x018e, B:82:0x0194, B:84:0x01a0, B:85:0x01a4, B:86:0x01bb, B:88:0x01c1, B:90:0x01c5, B:91:0x01dc, B:93:0x01e9, B:95:0x01f2, B:97:0x01f6, B:99:0x01fa, B:102:0x0203, B:105:0x020a, B:107:0x020e, B:112:0x0225, B:114:0x0229, B:118:0x0231, B:120:0x0235, B:122:0x0241, B:124:0x0252, B:126:0x0258, B:128:0x0265, B:130:0x026d, B:132:0x028b, B:138:0x02a2, B:139:0x02a8, B:141:0x02ac, B:143:0x02b6, B:145:0x02c3, B:146:0x02c7, B:150:0x02f8, B:152:0x0308, B:155:0x0310, B:157:0x0320, B:158:0x0334, B:160:0x0359, B:162:0x0373, B:163:0x039e, B:164:0x03ae, B:165:0x03b4, B:166:0x03c0, B:168:0x03c4, B:169:0x03d0, B:171:0x03da, B:173:0x03ea, B:174:0x03f9), top: B:185:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized com.autonavi.aps.amapapi.model.AMapLocationServer a() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 1061
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bu.a():com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    public final AMapLocationServer a(double d, double d2) {
        try {
            String a2 = this.ak.a(("output=json&radius=1000&extensions=all&location=" + d2 + "," + d).getBytes("UTF-8"), this.A, "http://restapi.amap.com/v3/geocode/regeo");
            new co();
            if (a2.contains("\"status\":\"1\"")) {
                AMapLocationServer a3 = co.a(a2);
                a3.setLatitude(d);
                a3.setLongitude(d2);
                return a3;
            }
        } catch (Throwable th) {
        }
        return null;
    }

    public final AMapLocationServer a(AMapLocationServer aMapLocationServer, String... strArr) {
        bx.a().a(this.ao);
        if (strArr == null || strArr.length == 0) {
            return bx.a().a(aMapLocationServer);
        }
        if (strArr[0].equals("shake")) {
            return bx.a().a(aMapLocationServer);
        }
        if (!strArr[0].equals("fusion")) {
            return aMapLocationServer;
        }
        bx.a();
        return bx.b(aMapLocationServer);
    }

    public final synchronized AMapLocationServer a(boolean z) {
        AMapLocationServer aMapLocationServer;
        h();
        cb c2 = !this.Q ? this.D.c() : null;
        if (TextUtils.isEmpty(this.at)) {
            aMapLocationServer = new AMapLocationServer("");
            aMapLocationServer.setErrorCode(this.m);
            aMapLocationServer.setLocationDetail(this.al.toString());
        } else {
            try {
                aMapLocationServer = a(false, z);
            } catch (Throwable th) {
                aMapLocationServer = null;
            }
            if (cx.a(aMapLocationServer)) {
                aMapLocationServer.d(f.bf);
                this.ai = this.z.toString();
                this.aj = c2;
                this.M = aMapLocationServer;
                b(aMapLocationServer.a(1));
                k();
            } else {
                String sb = this.z.toString();
                d(this.A);
                String str = this.at;
                JSONObject jSONObject = this.L;
                Context context = this.A;
                AMapLocationServer a2 = a(str, sb, jSONObject, m());
                if (cx.a(a2)) {
                    this.ai = sb;
                    a2.d("file");
                    a2.setLocationType(8);
                    a2.setLocationDetail("离线定位结果，在线定位失败原因:" + aMapLocationServer.getErrorInfo());
                    this.M = a2;
                    b(a2.a(1));
                    aMapLocationServer = a2;
                }
            }
        }
        return aMapLocationServer;
    }

    public final a a(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        String upperCase;
        int i2;
        int i3;
        try {
            long b2 = cx.b();
            String a2 = a(bArr);
            if (a2.length() == 16) {
                upperCase = a2 + "0000000000000000";
                i3 = 0;
                i2 = 0;
            } else if (a2.length() == 12) {
                upperCase = a2 + "00000000000000000000";
                i3 = 0;
                i2 = 0;
            } else {
                byte[] bArr2 = new byte[16];
                System.arraycopy(bArr, 9, bArr2, 0, 16);
                String b3 = cx.b(bArr2);
                upperCase = (b3.substring(0, 32)).toUpperCase(Locale.getDefault());
                i2 = ((bArr[25] & 255) * 256) + (bArr[26] & 255);
                i3 = ((bArr[27] & 255) * 256) + (bArr[28] & 255);
                if (i3 == 11669 || i2 == 2080 || i2 == 1796 || bluetoothDevice == null) {
                    return null;
                }
            }
            byte b4 = bArr[29];
            String address = bluetoothDevice.getAddress();
            if (!BluetoothAdapter.checkBluetoothAddress(address.toUpperCase(Locale.CHINA))) {
                return null;
            }
            byte[] bArr3 = new byte[6];
            int i4 = 0;
            for (String str : address.split(":")) {
                bArr3[i4] = (byte) Integer.parseInt(str, 16);
                i4++;
            }
            return new a(upperCase, bluetoothDevice.getName(), bArr3, address, i2, i3, b4, i, b2);
        } catch (Throwable th) {
            f.a(th, "APS", "createFromScanData");
            return null;
        }
    }

    public final synchronized void a(Context context) {
        c(context);
        h();
        f.g = true;
        d(this.A);
        this.P = cx.b();
        if (!this.ap) {
            l();
            this.ap = true;
        }
        this.D.a();
        this.D.f();
        cf.a().a(this.A, (String) null);
        ci.a().a(this.A);
        Context context2 = this.A;
        try {
            if (context2.checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS") == 0) {
                this.ac = true;
            }
            if (context2.checkCallingOrSelfPermission("android.permission.BLUETOOTH_ADMIN") == 0 && context2.checkCallingOrSelfPermission("android.permission.BLUETOOTH") == 0) {
                this.ad = true;
            }
        } catch (Throwable th) {
        }
        try {
            if (cx.c() >= 18 && this.ad) {
                this.h = BluetoothAdapter.getDefaultAdapter();
            }
        } catch (Throwable th2) {
        }
        this.e = true;
    }

    public final synchronized void a(Context context, JSONObject jSONObject) {
        if (!this.av) {
            a(jSONObject);
            c(context);
            if (this.at != null) {
                this.at = null;
                this.au = null;
                if (this.z != null) {
                    this.z.delete(0, this.z.length());
                }
            }
            if (!this.Q) {
                this.D.a(this.Q, true);
            }
            this.aq = this.L.optBoolean("isOnceLocationLatest", false);
            if (this.aq) {
                if (!this.ap) {
                    l();
                    this.ap = true;
                }
                n();
            }
            o();
            if (this.E != null && this.E.isEmpty()) {
                T = cx.b();
                List<ScanResult> a2 = this.C.a();
                if (a2 != null) {
                    this.E.addAll(a2);
                    synchronized (this.d) {
                        if (this.F != null && this.F.isEmpty()) {
                            this.F.addAll(a2);
                        }
                    }
                }
            }
            q();
            this.at = m();
            if (!TextUtils.isEmpty(this.at)) {
                this.au = this.at + com.alipay.sdk.sys.a.b + this.an + com.alipay.sdk.sys.a.b + this.am;
                this.z = a(this.z, true);
            }
            this.av = true;
        }
    }

    public final synchronized void a(AMapLocationServer aMapLocationServer) {
        if (cx.a(aMapLocationServer)) {
            cf.a().a(this.au, this.z, aMapLocationServer, this.A, true);
        }
    }

    public final void a(JSONObject jSONObject) {
        boolean z;
        boolean z2;
        boolean z3;
        this.L = jSONObject;
        if (cx.a(this.L, "collwifiscan")) {
            try {
                String string = this.L.getString("collwifiscan");
                if (TextUtils.isEmpty(string)) {
                    f.f = 20;
                } else {
                    f.f = Integer.parseInt(string) / 1000;
                }
            } catch (Throwable th) {
                f.a(th, "APS", "setExtra");
            }
        }
        try {
            z2 = cx.a(this.L, "reversegeo") ? this.L.getBoolean("reversegeo") : true;
            try {
                z3 = cx.a(this.L, "isOffset") ? this.L.getBoolean("isOffset") : true;
                try {
                    z = cx.a(this.L, "isLocationCacheEnable") ? this.L.getBoolean("isLocationCacheEnable") : true;
                    try {
                        if (cx.a(this.L, "isWifiPassiveScan")) {
                            this.f34u = this.L.getBoolean("isWifiPassiveScan");
                        }
                        if (cx.a(this.L, "isMock")) {
                            this.k = this.L.getBoolean("isMock");
                        }
                        if (!(z3 == this.an && z2 == this.am && z == this.ao)) {
                            this.ai = null;
                            this.M = null;
                            b((JSONObject) null);
                            this.ah = 0L;
                            this.N = 0L;
                            bx.a().b();
                        }
                    } catch (Throwable th2) {
                    }
                } catch (Throwable th3) {
                    z = true;
                }
            } catch (Throwable th4) {
                z = true;
                z3 = true;
            }
        } catch (Throwable th5) {
            z = true;
            z2 = true;
            z3 = true;
        }
        this.an = z3;
        this.am = z2;
        this.ao = z;
        this.Q = cx.a(this.A);
    }

    public final synchronized void a(boolean z, Context context) {
        if (!this.c) {
            if (this.b == null) {
                this.b = new bv(context.getApplicationContext());
                this.b.b();
            }
            if (this.b != null) {
                if (z) {
                    this.b.d();
                } else {
                    this.b.c();
                }
            }
            this.c = true;
        }
    }

    public final void b() {
        try {
            a(this.A, this.L);
            a(a(true, true));
        } catch (Throwable th) {
            f.a(th, "APS", "doFusionLocation");
        }
    }

    public final synchronized void b(Context context) {
        if (v == -1 || d()) {
            v = 1;
            cq.a(context);
            y = cq.u();
            cx.a(context, y);
            x = cx.b();
            w = cx.b();
        }
    }

    @SuppressLint({"NewApi"})
    public final synchronized void c() {
        AMapLocationServer.d = null;
        this.av = false;
        this.e = false;
        f.g = false;
        y();
        this.aa = null;
        this.ai = null;
        k();
        if (this.b != null) {
            this.b.a();
            this.b = null;
            this.c = false;
        }
        A();
        ch.a().a(this.A);
        bx.a().b();
        cx.g();
        if (!(this.A == null || this.J == null)) {
            this.A.unregisterReceiver(this.J);
        }
        this.J = null;
        this.ap = false;
        if (this.D != null) {
            this.D.i();
        }
        ci.a().b();
        a("stopOff");
        a(EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
        this.l = false;
        this.ag = 0L;
        r();
        if (this.E != null) {
            this.E.clear();
        }
        this.M = null;
        this.A = null;
        if (this.r != null) {
            this.r.clear();
            this.r = null;
        }
        this.aa = null;
        this.ab = null;
        this.z = null;
        this.o = null;
        this.p = null;
        j();
        this.h = null;
    }

    public final boolean d() {
        try {
            if (this.A == null) {
                return false;
            }
            if (cx.b() - w >= cq.A()) {
                w = cx.b();
                if (cx.e(this.A) > y) {
                    return true;
                }
            }
            return cx.b() - x >= ((long) cq.B());
        } catch (Throwable th) {
            f.a(th, "APS", "isConfigNeedUpdate");
            return false;
        }
    }

    public final synchronized AMapLocationServer e() {
        AMapLocationServer a2;
        if (this.al.length() > 0) {
            this.al.delete(0, this.al.length());
        }
        if (TextUtils.isEmpty(this.at)) {
            a2 = new AMapLocationServer("");
            a2.setErrorCode(this.m);
            a2.setLocationDetail(this.al.toString());
        } else {
            if (!cf.a().b()) {
                cf.a().a(this.A, cf.a().a(this.au, this.z, this.A));
            }
            a2 = cf.a().a(this.au, this.z, this.ao);
            if (cx.a(a2)) {
                this.ah = 0L;
                a2.setLocationType(4);
                this.M = a2;
                b(a2.a(1));
                k();
            }
        }
        return a2;
    }

    public final void f() {
        boolean z = true;
        z = false;
        if (cq.v()) {
            Context context = this.A;
            try {
                if (this.aa == null && !this.ar) {
                    s a2 = f.a("Collection", JsonSerializer.VERSION);
                    this.ar = cu.a(context, a2);
                    if (this.ar) {
                        try {
                            this.aa = au.a(context, a2, this.f, null, new Class[]{Context.class}, new Object[]{context});
                        } catch (Throwable th) {
                        }
                    } else {
                        this.ar = true;
                    }
                }
            } catch (Throwable th2) {
                f.a(th2, "APS", "initCollection");
            }
        }
        if (this.t && cq.v() && !v() && u()) {
            if (!cq.v()) {
                y();
                return;
            }
            if (cx.a(this.L, "coll")) {
                try {
                    if (!this.L.getString("coll").equals("0")) {
                        z = true;
                    }
                } catch (Throwable th3) {
                    f.a(th3, "APS", "start3rdCM");
                }
            }
            if (!z) {
                y();
            } else if (!v()) {
                try {
                    x();
                    try {
                        cs.a(this.aa, "start", new Object[0]);
                    } catch (Throwable th4) {
                    }
                    if (this.A == null) {
                    }
                } catch (Throwable th5) {
                    f.a(th5, "APS", "start3rdCM");
                }
            }
        }
    }
}

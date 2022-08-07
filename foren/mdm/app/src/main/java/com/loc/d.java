package com.loc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.view.PointerIconCompat;
import android.text.TextUtils;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceManagerBase;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.APSService;
import com.amap.api.location.DPoint;
import com.amap.api.location.LocationManagerBase;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AMapLocationManager.java */
/* loaded from: classes2.dex */
public class d implements LocationManagerBase {
    cu D;
    a I;
    private Context O;
    AMapLocationClientOption a;
    public HandlerC0046d b;
    g c;
    GeoFenceManagerBase e;
    h i;
    Messenger k;
    Intent l;
    b v;
    volatile bz w;
    private int M = 0;
    private boolean N = false;
    private boolean P = false;
    private boolean Q = false;
    ArrayList<AMapLocationListener> d = new ArrayList<>();
    boolean f = false;
    public boolean g = true;
    public boolean h = true;
    Messenger j = null;
    int m = 0;
    boolean n = false;
    long o = 0;
    AMapLocation p = null;
    long q = 0;
    long r = 0;
    public boolean s = false;
    private JSONArray R = null;
    private int S = 0;
    private boolean T = true;
    int t = 240;

    /* renamed from: u */
    int f37u = 80;
    volatile boolean x = false;
    volatile float y = 0.0f;
    volatile double z = 0.0d;
    boolean A = false;
    AMapLocationClientOption.AMapLocationMode B = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
    Object C = new Object();
    String E = null;
    private ServiceConnection U = new ServiceConnection() { // from class: com.loc.d.1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                d.this.j = new Messenger(iBinder);
                d.this.P = true;
            } catch (Throwable th) {
                f.a(th, "AMapLocationManager", "onServiceConnected");
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            d.this.j = null;
            d.this.P = false;
        }
    };
    boolean F = false;
    private LinkedList<c> V = new LinkedList<>();
    private LinkedList<c> W = new LinkedList<>();
    private int X = 0;
    private AMapLocation Y = null;
    String G = null;
    boolean H = false;
    AMapLocation J = null;
    String K = null;
    Hashtable<PendingIntent, ArrayList<GeoFence>> L = new Hashtable<>();

    /* compiled from: AMapLocationManager.java */
    /* loaded from: classes2.dex */
    public class a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Looper looper) {
            super(looper);
            d.this = r1;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1002:
                    try {
                        d.a(d.this, (AMapLocationListener) message.obj);
                        return;
                    } catch (Throwable th) {
                        f.a(th, "AMapLocationManage$MHandlerr", "handleMessage SET_LISTENER");
                        return;
                    }
                case 1003:
                    try {
                        d.this.j();
                        return;
                    } catch (Throwable th2) {
                        f.a(th2, "AMapLocationManager$MHandler", "handleMessage START_LOCATION");
                        return;
                    }
                case 1004:
                    try {
                        d.this.k();
                        return;
                    } catch (Throwable th3) {
                        f.a(th3, "AMapLocationManager$MHandler", "handleMessage STOP_LOCATION");
                        return;
                    }
                case 1005:
                    try {
                        d.b(d.this, (AMapLocationListener) message.obj);
                        return;
                    } catch (Throwable th4) {
                        f.a(th4, "AMapLocationManager$MHandler", "handleMessage REMOVE_LISTENER");
                        return;
                    }
                case 1006:
                    try {
                        d.a(d.this, (GeoFence) message.obj);
                        return;
                    } catch (Throwable th5) {
                        f.a(th5, "AMapLocationManager$MHandler", "handleMessage ADD_GEOFENCE");
                        return;
                    }
                case 1007:
                    try {
                        d.a(d.this, (PendingIntent) message.obj);
                        return;
                    } catch (Throwable th6) {
                        f.a(th6, "AMapLocationManager$MHandler", "handleMessage REMOVE_GEOFENCE");
                        return;
                    }
                case 1008:
                    try {
                        d.j(d.this);
                        return;
                    } catch (Throwable th7) {
                        f.a(th7, "AMapLocationManager$ActionHandler", "handleMessage START_SOCKET");
                        return;
                    }
                case 1009:
                    try {
                        d.k(d.this);
                        return;
                    } catch (Throwable th8) {
                        f.a(th8, "AMapLocationManager$ActionHandler", "handleMessage STOP_SOCKET");
                        return;
                    }
                case 1010:
                    try {
                        d.b(d.this, (GeoFence) message.obj);
                        return;
                    } catch (Throwable th9) {
                        f.a(th9, "AMapLocationManager$MHandler", "handleMessage REMOVE_GEOFENCE_ONE");
                        return;
                    }
                case 1011:
                    try {
                        d.f(d.this);
                        return;
                    } catch (Throwable th10) {
                        f.a(th10, "AMapLocationManager$MHandler", "handleMessage DESTROY");
                        return;
                    }
                case 1012:
                case 1013:
                case PointerIconCompat.TYPE_HORIZONTAL_DOUBLE_ARROW /* 1014 */:
                default:
                    return;
                case PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW /* 1015 */:
                    try {
                        g gVar = d.this.c;
                        gVar.d = d.this.a;
                        gVar.b();
                        return;
                    } catch (Throwable th11) {
                        f.a(th11, "AMapLocationManager$ActionHandler", "handleMessage START_GPS_LOCATION");
                        return;
                    }
                case PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW /* 1016 */:
                    try {
                        if (d.this.g() || !d.this.n) {
                            d.h(d.this);
                        } else {
                            d.this.a((int) PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, 1000L);
                        }
                        return;
                    } catch (Throwable th12) {
                        f.a(th12, "AMapLocationManager$ActionHandler", "handleMessage START_LBS_LOCATION");
                        return;
                    }
                case PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW /* 1017 */:
                    try {
                        d.this.c.a();
                        return;
                    } catch (Throwable th13) {
                        f.a(th13, "AMapLocationManager$ActionHandler", "handleMessage STOP_GPS_LOCATION");
                        return;
                    }
                case PointerIconCompat.TYPE_ZOOM_IN /* 1018 */:
                    try {
                        d.this.a = (AMapLocationClientOption) message.obj;
                        if (d.this.a != null) {
                            d.i(d.this);
                            return;
                        }
                        return;
                    } catch (Throwable th14) {
                        f.a(th14, "AMapLocationManager$ActionHandler", "handleMessage SET_OPTION");
                        return;
                    }
                case PointerIconCompat.TYPE_ZOOM_OUT /* 1019 */:
                    try {
                        d.this.a();
                        d.this.a((int) PointerIconCompat.TYPE_GRAB, (Object) null, 10000L);
                        d.this.a(1021, (Object) null, 10000L);
                        d.this.a((int) GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT, (Object) null, 30000L);
                        return;
                    } catch (Throwable th15) {
                        f.a(th15, "AMapLocationManager$ActionHandler", "handleMessage ACTION_START_SENSOR");
                        return;
                    }
                case PointerIconCompat.TYPE_GRAB /* 1020 */:
                    try {
                        d.this.b();
                        return;
                    } catch (Throwable th16) {
                        f.a(th16, "AMapLocationManager$ActionHandler", "handleMessage ACTION_GET_PRESSURE");
                        return;
                    }
                case 1021:
                    try {
                        d.this.c();
                        return;
                    } catch (Throwable th17) {
                        f.a(th17, "AMapLocationManager$ActionHandler", "handleMessage ACTION_STOP_SENSOR");
                        return;
                    }
                case GLMapStaticValue.AM_PARAMETERNAME_PROCESS_3DOBJECT /* 1022 */:
                    try {
                        d.this.d();
                        return;
                    } catch (Throwable th18) {
                        f.a(th18, "AMapLocationManager$ActionHandler", "handleMessage ACTION_SAVE_GPSINFO");
                        return;
                    }
            }
        }
    }

    /* compiled from: AMapLocationManager.java */
    /* loaded from: classes2.dex */
    public static class b extends HandlerThread {
        d a;

        public b(String str, d dVar) {
            super(str);
            this.a = null;
            this.a = dVar;
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                d.a(this.a, this.a.l);
                super.onLooperPrepared();
            } catch (Throwable th) {
            }
        }
    }

    /* compiled from: AMapLocationManager.java */
    /* loaded from: classes2.dex */
    public static class c {
        double a;
        double b;
        long c;
        float d;
        float e;
        int f;
        String g;

        c(AMapLocation aMapLocation, int i) {
            this.a = aMapLocation.getLatitude();
            this.b = aMapLocation.getLongitude();
            this.c = aMapLocation.getTime();
            this.d = aMapLocation.getAccuracy();
            this.e = aMapLocation.getSpeed();
            this.f = i;
            this.g = aMapLocation.getProvider();
        }

        public final boolean equals(Object obj) {
            try {
                c cVar = (c) obj;
                if (cVar == null || this.a != cVar.a) {
                    return false;
                }
                return this.b == cVar.b;
            } catch (Throwable th) {
                return false;
            }
        }

        public final int hashCode() {
            return super.hashCode();
        }

        public final String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.a);
            stringBuffer.append(",");
            stringBuffer.append(this.b);
            stringBuffer.append(",");
            stringBuffer.append(this.d);
            stringBuffer.append(",");
            stringBuffer.append(this.c);
            stringBuffer.append(",");
            stringBuffer.append(this.e);
            stringBuffer.append(",");
            stringBuffer.append(this.f);
            stringBuffer.append(",");
            stringBuffer.append(this.g);
            return stringBuffer.toString();
        }
    }

    /* compiled from: AMapLocationManager.java */
    /* renamed from: com.loc.d$d */
    /* loaded from: classes2.dex */
    public class HandlerC0046d extends Handler {
        public HandlerC0046d() {
            d.this = r1;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HandlerC0046d(Looper looper) {
            super(looper);
            d.this = r1;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (!d.this.A || f.n) {
                switch (message.what) {
                    case 1:
                        try {
                            d.a(d.this, message.getData());
                            return;
                        } catch (Throwable th) {
                            f.a(th, "AMapLocationManager$ActionHandler", "handleMessage RESULT_LBS_LOCATIONSUCCESS");
                            return;
                        }
                    case 2:
                    case 8:
                        try {
                            d.a(d.this, message);
                            return;
                        } catch (Throwable th2) {
                            f.a(th2, "AMapLocationManager$ActionHandler", "handleMessage RESULT_GPS_LOCATIONSUCCESS");
                            return;
                        }
                    case 3:
                        d.this.n = false;
                        return;
                    case 5:
                        try {
                            d.this.a(false);
                            if (message.obj != null) {
                                d.this.a((AMapLocation) message.obj, d.this.J);
                                return;
                            }
                            return;
                        } catch (Throwable th3) {
                            f.a(th3, "AMapLocationManager$ActionHandler", "handleMessage RESULT_GPS_LOCATIONCHANGE");
                            return;
                        }
                    case 6:
                        try {
                            Bundle data = message.getData();
                            if (data != null) {
                                d.this.t = data.getInt("lMaxGeoDis");
                                d.this.f37u = data.getInt("lMinGeoDis");
                                String string = data.getString("locationJson");
                                AMapLocation aMapLocation = new AMapLocation("");
                                f.a(aMapLocation, new JSONObject(string));
                                if (!TextUtils.isEmpty(aMapLocation.getAdCode())) {
                                    d.this.J = aMapLocation;
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (Throwable th4) {
                            f.a(th4, "AMapLocationManager$ActionHandler", "handleMessage RESULT_GPS_GEO_SUCCESS");
                            return;
                        }
                    case 7:
                        try {
                            Bundle data2 = message.getData();
                            d.this.T = data2.getBoolean("ngpsAble");
                            return;
                        } catch (Throwable th5) {
                            f.a(th5, "AMapLocationManager$ActionHandler", "handleMessage RESULT_NGPS_ABLE");
                            return;
                        }
                    case 100:
                        try {
                            d.b(d.this);
                            return;
                        } catch (Throwable th6) {
                            f.a(th6, "AMapLocationManager$ActionHandler", "handleMessage RESULT_FASTSKY");
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    public d(Context context, Intent intent) {
        this.c = null;
        this.k = null;
        this.l = null;
        this.v = null;
        this.w = null;
        this.D = null;
        this.I = null;
        this.O = context;
        this.l = intent;
        if (f.c()) {
            try {
                cv.a(this.O, f.a("loc"));
            } catch (Throwable th) {
            }
        }
        try {
            if (Looper.myLooper() == null) {
                this.b = new HandlerC0046d(this.O.getMainLooper());
            } else {
                this.b = new HandlerC0046d();
            }
        } catch (Throwable th2) {
            f.a(th2, "AMapLocationManager", "init 1");
        }
        try {
            this.v = new b("amapLocManagerThread", this);
            this.v.setPriority(5);
            this.v.start();
            this.I = a(this.v.getLooper());
            this.k = new Messenger(this.b);
        } catch (Throwable th3) {
            f.a(th3, "AMapLocationManager", "init 5");
        }
        try {
            this.i = new h(this.O);
        } catch (Throwable th4) {
            f.a(th4, "AMapLocationManager", "init 2");
        }
        try {
            this.c = new g(this.O, this.b);
        } catch (Throwable th5) {
            f.a(th5, "AMapLocationManager", "init 3");
        }
        this.w = new bz(this.O);
        if (this.D == null) {
            this.D = new cu();
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(24:(8:108|2|99|3|89|4|(1:6)|7)|(22:112|9|(2:91|11)|13|95|14|15|16|(1:18)(1:86)|19|103|20|(1:34)|110|35|(1:37)(1:64)|38|93|39|(7:41|105|42|101|43|(3:87|45|46)|80)(1:85)|(2:48|107)|49)|60|(0)|13|95|14|15|16|(0)(0)|19|103|20|(7:22|24|26|28|30|32|34)|110|35|(0)(0)|38|93|39|(0)(0)|(0)|49|(3:(1:102)|(0)|(1:100))) */
    /* JADX WARN: Can't wrap try/catch for region: R(31:108|2|99|3|89|4|(1:6)|7|(22:112|9|(2:91|11)|13|95|14|15|16|(1:18)(1:86)|19|103|20|(1:34)|110|35|(1:37)(1:64)|38|93|39|(7:41|105|42|101|43|(3:87|45|46)|80)(1:85)|(2:48|107)|49)|60|(0)|13|95|14|15|16|(0)(0)|19|103|20|(7:22|24|26|28|30|32|34)|110|35|(0)(0)|38|93|39|(0)(0)|(0)|49|(3:(1:102)|(0)|(1:100))) */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0044, code lost:
        if (com.loc.cx.a(r2) == false) goto L_0x0046;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0104, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0105, code lost:
        com.loc.f.a(r0, "AMapLocationManager", "apsLocation:doFirstNetLocate");
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x010c, code lost:
        r3 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0116, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0117, code lost:
        com.loc.f.a(r0, "AMapLocationManager", "apsLocation:callback");
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0123, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0124, code lost:
        com.loc.f.a(r0, "AMapLocationManager", "apsLocation:reportLBSLocUseTime");
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0058 A[Catch: Throwable -> 0x011f, all -> 0x012c, TRY_LEAVE, TryCatch #2 {Throwable -> 0x011f, blocks: (B:11:0x0040, B:16:0x004c, B:18:0x0058, B:62:0x0105, B:66:0x0117, B:70:0x0124, B:76:0x0134), top: B:91:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x009c A[Catch: Throwable -> 0x0116, all -> 0x012c, TryCatch #6 {all -> 0x012c, blocks: (B:3:0x0003, B:4:0x000f, B:6:0x0019, B:7:0x001c, B:9:0x003a, B:11:0x0040, B:14:0x0047, B:16:0x004c, B:18:0x0058, B:20:0x005e, B:22:0x0062, B:24:0x0068, B:26:0x0070, B:28:0x0074, B:30:0x007a, B:32:0x0081, B:34:0x008d, B:35:0x0095, B:37:0x009c, B:38:0x00aa, B:39:0x00c0, B:42:0x00c8, B:43:0x00cc, B:45:0x00d2, B:51:0x00dd, B:54:0x00ea, B:59:0x00fa, B:62:0x0105, B:64:0x010f, B:66:0x0117, B:70:0x0124, B:76:0x0134, B:79:0x013e), top: B:99:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d8 A[Catch: Throwable -> 0x00f7, TRY_ENTER, TRY_LEAVE, TryCatch #11 {Throwable -> 0x00f7, blocks: (B:48:0x00d8, B:56:0x00f3), top: B:108:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00f3 A[Catch: Throwable -> 0x00f7, TRY_ENTER, TRY_LEAVE, TryCatch #11 {Throwable -> 0x00f7, blocks: (B:48:0x00d8, B:56:0x00f3), top: B:108:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x010f A[Catch: Throwable -> 0x0116, all -> 0x012c, TRY_ENTER, TRY_LEAVE, TryCatch #6 {all -> 0x012c, blocks: (B:3:0x0003, B:4:0x000f, B:6:0x0019, B:7:0x001c, B:9:0x003a, B:11:0x0040, B:14:0x0047, B:16:0x004c, B:18:0x0058, B:20:0x005e, B:22:0x0062, B:24:0x0068, B:26:0x0070, B:28:0x0074, B:30:0x007a, B:32:0x0081, B:34:0x008d, B:35:0x0095, B:37:0x009c, B:38:0x00aa, B:39:0x00c0, B:42:0x00c8, B:43:0x00cc, B:45:0x00d2, B:51:0x00dd, B:54:0x00ea, B:59:0x00fa, B:62:0x0105, B:64:0x010f, B:66:0x0117, B:70:0x0124, B:76:0x0134, B:79:0x013e), top: B:99:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0040 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.autonavi.aps.amapapi.model.AMapLocationServer a(com.loc.bu r10) {
        /*
            Method dump skipped, instructions count: 342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.d.a(com.loc.bu):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    private a a(Looper looper) {
        a aVar;
        synchronized (this.C) {
            this.I = new a(looper);
            aVar = this.I;
        }
        return aVar;
    }

    private static c a(AMapLocation aMapLocation, int i) {
        return new c(aMapLocation, i);
    }

    private void a(int i, Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (Throwable th) {
                f.a(th, "AMapLocationManager", "sendLocMessage");
                return;
            }
        }
        if (TextUtils.isEmpty(this.E)) {
            this.E = f.c(this.O);
        }
        bundle.putString("c", this.E);
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.setData(bundle);
        obtain.replyTo = this.k;
        if (this.j != null) {
            this.j.send(obtain);
        }
    }

    public void a(int i, Object obj, long j) {
        synchronized (this.C) {
            if (this.I != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.obj = obj;
                this.I.sendMessageDelayed(obtain, j);
            }
        }
    }

    private void a(AMapLocation aMapLocation) {
        try {
            if (GeocodeSearch.GPS.equals(aMapLocation.getProvider()) || g()) {
                aMapLocation.setAltitude(cx.b(aMapLocation.getAltitude()));
                aMapLocation.setBearing(cx.a(aMapLocation.getBearing()));
                aMapLocation.setSpeed(cx.a(aMapLocation.getSpeed()));
                Iterator<AMapLocationListener> it = this.d.iterator();
                while (it.hasNext()) {
                    try {
                        it.next().onLocationChanged(aMapLocation);
                    } catch (Throwable th) {
                    }
                }
            }
        } catch (Throwable th2) {
        }
    }

    public void a(AMapLocation aMapLocation, AMapLocation aMapLocation2) {
        if (cx.a(aMapLocation)) {
            Bundle bundle = null;
            if (aMapLocation != null) {
                bundle = new Bundle();
                bundle.putDouble("lat", aMapLocation.getLatitude());
                bundle.putDouble("lon", aMapLocation.getLongitude());
            }
            if (this.a.isNeedAddress() && aMapLocation2 == null) {
                a(10, bundle);
            } else if (aMapLocation2 != null && this.a.isNeedAddress()) {
                float a2 = cx.a(new double[]{aMapLocation2.getLatitude(), aMapLocation2.getLongitude(), aMapLocation.getLatitude(), aMapLocation.getLongitude()});
                if (a2 < this.t) {
                    f.a(aMapLocation, aMapLocation2);
                }
                if (a2 > this.f37u) {
                    a(10, bundle);
                }
            }
        }
    }

    private void a(AMapLocation aMapLocation, Throwable th) {
        AMapLocation aMapLocation2;
        boolean z;
        AMapLocation aMapLocation3;
        try {
            if (!f.c() || aMapLocation != null) {
                if (aMapLocation == null) {
                    aMapLocation2 = new AMapLocation("");
                    aMapLocation2.setErrorCode(8);
                } else {
                    aMapLocation2 = aMapLocation;
                }
                if (!GeocodeSearch.GPS.equals(aMapLocation2.getProvider())) {
                    aMapLocation2.setProvider("lbs");
                }
                if (this.Q) {
                    long time = aMapLocation2.getTime();
                    if (aMapLocation2.getErrorCode() == 0) {
                        AMapLocation aMapLocation4 = this.p;
                        this.Y = aMapLocation2;
                        long b2 = cx.b();
                        boolean z2 = false;
                        this.r = 0L;
                        this.X = 0;
                        if (aMapLocation4 == null || aMapLocation2 == null || aMapLocation4.getLocationType() != 1 || this.M <= 3) {
                            aMapLocation3 = aMapLocation2;
                        } else if (aMapLocation2.getAccuracy() < 0.0f || aMapLocation2.getSpeed() < 0.0f) {
                            if (aMapLocation2.getAccuracy() < 0.0f) {
                                aMapLocation2.setAccuracy(0.0f);
                            }
                            if (aMapLocation2.getSpeed() < 0.0f) {
                                aMapLocation2.setSpeed(0.0f);
                            }
                            aMapLocation3 = aMapLocation2;
                        } else {
                            long time2 = aMapLocation2.getTime() - aMapLocation4.getTime();
                            if (time2 >= 0) {
                                if (aMapLocation2.getLocationType() == 1) {
                                    if (b2 - this.q < 5000) {
                                        if (cx.a(new double[]{aMapLocation4.getLatitude(), aMapLocation4.getLongitude(), aMapLocation2.getLatitude(), aMapLocation2.getLongitude()}) > (((aMapLocation4.getSpeed() + aMapLocation2.getSpeed()) * ((float) time2)) / 2000.0f) + (2.0f * (aMapLocation4.getAccuracy() + aMapLocation2.getAccuracy())) + 3000.0f) {
                                            z2 = true;
                                        }
                                    } else {
                                        this.M = 0;
                                    }
                                }
                                if (z2) {
                                    if (this.r == 0) {
                                        this.r = cx.b();
                                    }
                                    if (b2 - this.r < 30000) {
                                        this.N = true;
                                        this.X = 1;
                                        aMapLocation3 = aMapLocation4;
                                    }
                                }
                            }
                            aMapLocation3 = aMapLocation2;
                        }
                        c a2 = a(aMapLocation3, this.X);
                        c a3 = a(this.Y, this.X);
                        if (!this.N) {
                            if (!this.V.contains(a2)) {
                                if (this.V.size() >= 5) {
                                    this.V.removeFirst();
                                }
                                this.V.add(a2);
                            }
                        } else if (this.X == 0) {
                            if (!this.V.contains(a2) && !this.W.contains(a2)) {
                                this.W.add(a2);
                            }
                        } else if (!this.W.contains(a3)) {
                            this.W.add(a3);
                        }
                        if (this.V.size() + this.W.size() >= 10) {
                            this.V.addAll(this.W);
                            StringBuffer stringBuffer = new StringBuffer();
                            Iterator<c> it = this.V.iterator();
                            while (it.hasNext()) {
                                stringBuffer.append(it.next().toString());
                                stringBuffer.append("#");
                            }
                            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                            cu.a("gpsstatistics", stringBuffer.toString());
                            this.V.clear();
                            this.W.clear();
                            this.N = false;
                        }
                    }
                    this.q = cx.b();
                    this.p = aMapLocation2;
                    aMapLocation2.setTime(time);
                    a(aMapLocation2);
                    if (!this.A || f.c()) {
                        boolean z3 = false;
                        if (aMapLocation2.getLocationType() == 1) {
                            this.K = null;
                            z3 = cx.a(aMapLocation2, this.c.j);
                        }
                        if (!z3 && !this.F) {
                            cj cjVar = new cj();
                            cjVar.a(aMapLocation2);
                            cjVar.a(this.K);
                            this.i.a(cjVar);
                        }
                        if (cq.o()) {
                            int i = 0;
                            switch (this.S) {
                                case 1:
                                    z = true;
                                    i = 0;
                                    break;
                                case 2:
                                case 4:
                                case 8:
                                    z = true;
                                    i = 1;
                                    break;
                                case 3:
                                case 5:
                                case 6:
                                case 7:
                                default:
                                    z = false;
                                    break;
                            }
                            if (z) {
                                if (this.R == null) {
                                    this.R = new JSONArray();
                                }
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("lon", aMapLocation2.getLongitude());
                                jSONObject.put("lat", aMapLocation2.getLatitude());
                                jSONObject.put("type", i);
                                jSONObject.put("timestamp", cx.a());
                                this.R = this.R.put(jSONObject);
                                if (this.R.length() >= cq.p()) {
                                    h();
                                }
                            }
                        }
                        cu.a(this.O, this.S, aMapLocation2);
                    } else {
                        return;
                    }
                }
                if (!this.A || f.c()) {
                    cv.b(this.O);
                    if (this.a.isOnceLocation()) {
                        k();
                    }
                }
            } else if (th != null) {
                cv.a(this.O, "loc", th.getMessage());
            } else {
                cv.a(this.O, "loc", "amaplocation is null");
            }
        } catch (Throwable th2) {
            f.a(th2, "AMapLocationManager", "handlerLocation part3");
        }
    }

    static /* synthetic */ void a(d dVar, PendingIntent pendingIntent) {
        if (pendingIntent != null) {
            try {
                if (dVar.L.containsKey(pendingIntent)) {
                    if (dVar.e != null) {
                        Iterator<GeoFence> it = dVar.L.get(pendingIntent).iterator();
                        while (it.hasNext()) {
                            dVar.e.removeGeoFence(it.next());
                        }
                    }
                    dVar.L.remove(pendingIntent);
                }
            } catch (Throwable th) {
                f.a(th, "AMapLocationManager", "doRemoveGeoFenceAlert2");
            }
        }
    }

    static /* synthetic */ void a(d dVar, Intent intent) {
        if (intent == null) {
            try {
                intent = new Intent(dVar.O, APSService.class);
            } catch (Throwable th) {
                f.a(th, "AMapLocationManager", "startServiceImpl");
                return;
            }
        }
        intent.putExtra("a", !TextUtils.isEmpty(AMapLocationClientOption.getAPIKEY()) ? AMapLocationClientOption.getAPIKEY() : k.f(dVar.O));
        intent.putExtra("b", dVar.O.getPackageName());
        intent.putExtra("c", f.c(dVar.O));
        dVar.O.bindService(intent, dVar.U, 1);
    }

    static /* synthetic */ void a(d dVar, Bundle bundle) {
        AMapLocation aMapLocation;
        Throwable th = null;
        try {
            dVar.M = 0;
            if (bundle != null) {
                String string = bundle.getString("locationJson");
                aMapLocation = new AMapLocation("");
                JSONObject jSONObject = new JSONObject(string);
                f.a(aMapLocation, jSONObject);
                try {
                    if (cx.a(jSONObject, "nb")) {
                        dVar.K = jSONObject.getString("nb");
                    } else {
                        dVar.K = null;
                    }
                    dVar.S = bundle.getInt("originalLocType", 0);
                    if (aMapLocation.getErrorCode() == 0 && !TextUtils.isEmpty(aMapLocation.getAdCode())) {
                        dVar.J = aMapLocation;
                    }
                    dVar.F = bundle.getBoolean("fixlastlocation", false);
                } catch (Throwable th2) {
                }
            } else {
                aMapLocation = null;
            }
        } catch (Throwable th3) {
            f.a(th3, "AMapLocationManager$MHandler", "handleMessage LBS_LOCATIONSUCCESS");
            aMapLocation = null;
            th = th3;
        }
        dVar.a(aMapLocation, th);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005d A[Catch: Throwable -> 0x0096, TryCatch #1 {Throwable -> 0x0096, blocks: (B:22:0x0057, B:24:0x005d, B:26:0x0067), top: B:43:0x0057 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ void a(com.loc.d r8, android.os.Message r9) {
        /*
            r6 = 0
            r1 = 0
            java.lang.Object r0 = r9.obj     // Catch: Throwable -> 0x008c
            com.amap.api.location.AMapLocation r0 = (com.amap.api.location.AMapLocation) r0     // Catch: Throwable -> 0x008c
            int r2 = r0.getLocationType()     // Catch: Throwable -> 0x0098
            r8.S = r2     // Catch: Throwable -> 0x0098
            int r2 = r0.getErrorCode()     // Catch: Throwable -> 0x0098
            if (r2 != 0) goto L_0x0016
            r2 = 1
            r8.a(r2)     // Catch: Throwable -> 0x0098
        L_0x0016:
            int r2 = r0.getErrorCode()     // Catch: Throwable -> 0x0098
            r3 = 15
            if (r2 != r3) goto L_0x0029
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r2 = com.amap.api.location.AMapLocationClientOption.AMapLocationMode.Device_Sensors     // Catch: Throwable -> 0x0098
            com.amap.api.location.AMapLocationClientOption$AMapLocationMode r3 = r8.B     // Catch: Throwable -> 0x0098
            boolean r2 = r2.equals(r3)     // Catch: Throwable -> 0x0098
            if (r2 != 0) goto L_0x0029
        L_0x0028:
            return
        L_0x0029:
            com.loc.g r2 = r8.c     // Catch: Throwable -> 0x0098
            int r2 = r2.j     // Catch: Throwable -> 0x0098
            r3 = 4
            if (r2 < r3) goto L_0x0078
            r2 = 1
            r0.setGpsAccuracyStatus(r2)     // Catch: Throwable -> 0x0098
            r2 = r0
            r0 = r1
        L_0x0036:
            boolean r3 = r8.h
            if (r3 == 0) goto L_0x004f
            android.os.Messenger r3 = r8.j
            if (r3 == 0) goto L_0x004f
            android.os.Bundle r3 = new android.os.Bundle
            r3.<init>()
            java.lang.String r4 = "extraJson"
            java.lang.String r5 = r8.G
            r3.putString(r4, r5)
            r8.a(r6, r3)
            r8.h = r6
        L_0x004f:
            com.amap.api.location.AMapLocation r3 = r8.J
            r8.a(r2, r3)
            r8.a(r2, r0)
            int r0 = r2.getErrorCode()     // Catch: Throwable -> 0x0096
            if (r0 != 0) goto L_0x006f
            double r2 = r2.getAltitude()     // Catch: Throwable -> 0x0096
            r8.z = r2     // Catch: Throwable -> 0x0096
            boolean r0 = r8.x     // Catch: Throwable -> 0x0096
            if (r0 != 0) goto L_0x006f
            r0 = 1019(0x3fb, float:1.428E-42)
            r2 = 0
            r4 = 0
            r8.a(r0, r2, r4)     // Catch: Throwable -> 0x0096
        L_0x006f:
            boolean r0 = r8.T
            if (r0 == 0) goto L_0x0028
            r0 = 7
            r8.a(r0, r1)
            goto L_0x0028
        L_0x0078:
            com.loc.g r2 = r8.c     // Catch: Throwable -> 0x0098
            int r2 = r2.j     // Catch: Throwable -> 0x0098
            if (r2 != 0) goto L_0x0085
            r2 = -1
            r0.setGpsAccuracyStatus(r2)     // Catch: Throwable -> 0x0098
            r2 = r0
            r0 = r1
            goto L_0x0036
        L_0x0085:
            r2 = 0
            r0.setGpsAccuracyStatus(r2)     // Catch: Throwable -> 0x0098
            r2 = r0
            r0 = r1
            goto L_0x0036
        L_0x008c:
            r0 = move-exception
            r2 = r1
        L_0x008e:
            java.lang.String r3 = "AMapLocationManager$ActionHandler"
            java.lang.String r4 = "handleMessage GPS_LOCATIONSUCCESS"
            com.loc.f.a(r0, r3, r4)
            goto L_0x0036
        L_0x0096:
            r0 = move-exception
            goto L_0x006f
        L_0x0098:
            r2 = move-exception
            r7 = r2
            r2 = r0
            r0 = r7
            goto L_0x008e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.d.a(com.loc.d, android.os.Message):void");
    }

    static /* synthetic */ void a(d dVar, GeoFence geoFence) {
        GeoFence geoFence2 = null;
        if (geoFence != null) {
            try {
                if (dVar.e == null) {
                    dVar.e = cx.g(dVar.O);
                    dVar.e.setActivateAction(7);
                }
                PendingIntent pendingIntent = geoFence.getPendingIntent();
                ArrayList<GeoFence> arrayList = new ArrayList<>();
                if (!dVar.L.isEmpty()) {
                    arrayList = dVar.L.get(pendingIntent);
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    } else {
                        Iterator<GeoFence> it = arrayList.iterator();
                        while (it.hasNext()) {
                            geoFence2 = it.next();
                            if (!geoFence2.getFenceId().equals(geoFence.getFenceId())) {
                                geoFence2 = geoFence2;
                            }
                        }
                        if (geoFence2 != null) {
                            arrayList.remove(geoFence2);
                        }
                    }
                    arrayList.add(geoFence);
                } else {
                    arrayList.add(geoFence);
                }
                dVar.L.put(pendingIntent, arrayList);
                dVar.e.addRoundGeoFence(geoFence.getCenter(), geoFence.getRadius(), null, geoFence.getFenceId(), geoFence.getExpiration(), geoFence.getPendingIntent());
            } catch (Throwable th) {
                f.a(th, "AMapLocationManager", "doAddGeoFenceAlert");
            }
        }
    }

    static /* synthetic */ void a(d dVar, AMapLocationListener aMapLocationListener) {
        if (aMapLocationListener == null) {
            throw new IllegalArgumentException("listener参数不能为null");
        }
        if (dVar.d == null) {
            dVar.d = new ArrayList<>();
        }
        if (!dVar.d.contains(aMapLocationListener)) {
            dVar.d.add(aMapLocationListener);
        }
    }

    public void a(boolean z) {
        this.o = cx.b();
        this.n = true;
        if (z) {
            this.M++;
        }
    }

    static /* synthetic */ void b(d dVar) {
        boolean z = true;
        boolean z2 = false;
        try {
            if (dVar.O.checkCallingOrSelfPermission("android.permission.SYSTEM_ALERT_WINDOW") == 0) {
                z2 = true;
            } else if (dVar.O instanceof Activity) {
                z2 = true;
                z = false;
            } else {
                z = false;
            }
            if (z2) {
                AlertDialog.Builder builder = new AlertDialog.Builder(dVar.O);
                builder.setMessage(cq.g());
                if (!"".equals(cq.h()) && cq.h() != null) {
                    builder.setPositiveButton(cq.h(), new DialogInterface.OnClickListener() { // from class: com.loc.d.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            d.this.i();
                            dialogInterface.cancel();
                        }
                    });
                }
                builder.setNegativeButton(cq.i(), new DialogInterface.OnClickListener() { // from class: com.loc.d.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog create = builder.create();
                if (z) {
                    create.getWindow().setType(AMapException.CODE_AMAP_ENGINE_TABLEID_NOT_EXIST);
                }
                create.setCanceledOnTouchOutside(false);
                create.show();
                return;
            }
            dVar.i();
        } catch (Throwable th) {
            dVar.i();
            f.a(th, "AMapLocationManager", "showDialog");
        }
    }

    static /* synthetic */ void b(d dVar, GeoFence geoFence) {
        if (geoFence != null) {
            try {
                PendingIntent pendingIntent = geoFence.getPendingIntent();
                String fenceId = geoFence.getFenceId();
                if (pendingIntent != null && dVar.L.containsKey(pendingIntent) && !TextUtils.isEmpty(fenceId) && !dVar.L.isEmpty() && dVar.e != null) {
                    Iterator<GeoFence> it = dVar.L.get(pendingIntent).iterator();
                    while (it != null) {
                        if (it.hasNext()) {
                            GeoFence next = it.next();
                            if (!fenceId.equals(next.getFenceId())) {
                                if (next.getExpiration() != -1 && next.getExpiration() <= cx.b()) {
                                }
                            }
                            dVar.e.removeGeoFence(next);
                            it.remove();
                        } else {
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                f.a(th, "AMapLocationManager", "doRemoveGeoFenceAlert");
            }
        }
    }

    static /* synthetic */ void b(d dVar, AMapLocationListener aMapLocationListener) {
        if (!dVar.d.isEmpty() && dVar.d.contains(aMapLocationListener)) {
            dVar.d.remove(aMapLocationListener);
        }
        if (dVar.d.isEmpty()) {
            dVar.k();
        }
    }

    private void e() {
        synchronized (this.C) {
            if (this.I != null) {
                this.I.removeMessages(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW);
            }
        }
    }

    static /* synthetic */ void f(d dVar) {
        dVar.g = true;
        dVar.h = true;
        dVar.P = false;
        dVar.k();
        if (dVar.D != null) {
            dVar.D.b(dVar.O);
        }
        cu.a(dVar.O);
        dVar.h();
        if (dVar.e != null) {
            dVar.e.removeGeoFence();
        }
        if (dVar.U != null) {
            dVar.O.unbindService(dVar.U);
        }
        if (dVar.d != null) {
            dVar.d.clear();
            dVar.d = null;
        }
        dVar.U = null;
        synchronized (dVar.C) {
            if (dVar.I != null) {
                dVar.I.removeCallbacksAndMessages(null);
            }
            dVar.I = null;
        }
        if (dVar.v != null) {
            if (Build.VERSION.SDK_INT >= 18) {
                try {
                    cs.a(dVar.v, HandlerThread.class, "quitSafely", new Object[0]);
                } catch (Throwable th) {
                    dVar.v.quit();
                }
            } else {
                dVar.v.quit();
            }
        }
        dVar.v = null;
        if (dVar.b != null) {
            dVar.b.removeCallbacksAndMessages(null);
        }
        dVar.i.b();
        dVar.i = null;
        dVar.d();
        if (dVar.w != null) {
            dVar.w.f();
            dVar.w = null;
        }
    }

    private boolean f() {
        int i = 0;
        do {
            try {
                if (this.j != null) {
                    break;
                }
                Thread.sleep(100L);
                i++;
            } catch (Throwable th) {
                f.a(th, "AMapLocationManager", "checkAPSManager");
                return false;
            }
        } while (i < 50);
        if (this.j != null) {
            return true;
        }
        Message obtain = Message.obtain();
        Bundle bundle = new Bundle();
        AMapLocation aMapLocation = new AMapLocation("");
        aMapLocation.setErrorCode(10);
        aMapLocation.setLocationDetail("请检查配置文件是否配置服务");
        bundle.putString("locationJson", aMapLocation.toStr(1));
        obtain.setData(bundle);
        obtain.what = 1;
        this.b.sendMessage(obtain);
        return false;
    }

    public boolean g() {
        return cx.b() - this.o > 10000;
    }

    private synchronized void h() {
        if (cq.o() && this.R != null && this.R.length() > 0) {
            bq.a(new bp(this.O, f.a("loc"), this.R.toString()), this.O);
            this.R = null;
        }
    }

    static /* synthetic */ void h(d dVar) {
        long j = 1000;
        try {
            if (dVar.g) {
                dVar.g = false;
                AMapLocationServer a2 = dVar.a(new bu());
                if (dVar.f()) {
                    Bundle bundle = new Bundle();
                    String str = "0";
                    if (a2 != null && (a2.getLocationType() == 2 || a2.getLocationType() == 4)) {
                        str = "1";
                    }
                    bundle.putString("isCacheLoc", str);
                    bundle.putString("extraJson", dVar.G);
                    dVar.a(0, bundle);
                }
            } else if (dVar.f()) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("extraJson", dVar.G);
                if (dVar.a.isSensorEnable()) {
                    bundle2.putDouble("e", dVar.z);
                    bundle2.putFloat("f", dVar.y);
                }
                if (dVar.g() || !dVar.n) {
                    dVar.a(1, bundle2);
                }
            }
            try {
                if (!dVar.a.isOnceLocation() && dVar.a.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
                    dVar.a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, dVar.a.getInterval() < 1000 ? 1000L : dVar.a.getInterval());
                }
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
            try {
                if (!dVar.a.isOnceLocation() && dVar.a.getLocationMode() != AMapLocationClientOption.AMapLocationMode.Device_Sensors) {
                    if (dVar.a.getInterval() >= 1000) {
                        j = dVar.a.getInterval();
                    }
                    dVar.a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, j);
                }
            } catch (Throwable th3) {
            }
            throw th2;
        }
    }

    public void i() {
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.autonavi.minimap", cq.l()));
            intent.setFlags(268435456);
            intent.setData(Uri.parse(cq.j()));
            this.O.startActivity(intent);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "callAMap part1");
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(cq.k()));
                intent2.setFlags(268435456);
                this.O.startActivity(intent2);
            } catch (Throwable th2) {
                f.a(th2, "AMapLocationManager", "callAMap part2");
            }
        }
    }

    static /* synthetic */ void i(d dVar) {
        AMapLocationClientOption aMapLocationClientOption = dVar.a;
        Context context = dVar.O;
        dVar.G = f.a(aMapLocationClientOption);
        g gVar = dVar.c;
        gVar.d = dVar.a;
        if (!(gVar.d.getLocationMode() == AMapLocationClientOption.AMapLocationMode.Device_Sensors || gVar.a == null)) {
            gVar.a.removeMessages(8);
        }
        if (dVar.Q && !dVar.a.getLocationMode().equals(dVar.B)) {
            dVar.k();
            dVar.j();
        }
        dVar.B = dVar.a.getLocationMode();
        if (dVar.D != null) {
            if (dVar.a.isOnceLocation()) {
                dVar.D.a(dVar.O, 0);
            } else {
                dVar.D.a(dVar.O, 1);
            }
            dVar.D.a(dVar.O, dVar.a);
        }
    }

    public void j() {
        long j = 0;
        if (this.a == null) {
            this.a = new AMapLocationClientOption();
        }
        if (!this.Q) {
            this.Q = true;
            switch (this.a.getLocationMode()) {
                case Battery_Saving:
                    a(PointerIconCompat.TYPE_TOP_LEFT_DIAGONAL_DOUBLE_ARROW, (Object) null, 0L);
                    a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, 0L);
                    break;
                case Device_Sensors:
                    e();
                    a(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, (Object) null, 0L);
                    break;
                case Hight_Accuracy:
                    a(PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW, (Object) null, 0L);
                    if (this.a.isGpsFirst() && this.a.isOnceLocation()) {
                        j = 30000;
                    }
                    a(PointerIconCompat.TYPE_TOP_RIGHT_DIAGONAL_DOUBLE_ARROW, (Object) null, j);
                    break;
            }
            if (this.a.isSensorEnable() && this.z == 0.0d) {
                try {
                    JSONObject j2 = cx.j();
                    if (j2 != null) {
                        this.z = j2.getDouble("altitude");
                        this.y = Float.valueOf(j2.getString("pressure")).floatValue();
                    }
                } catch (Throwable th) {
                    f.a(th, "AMapLocationManager", "readAltitudePressureFromDB");
                }
            }
        }
    }

    static /* synthetic */ void j(d dVar) {
        try {
            if (dVar.j != null) {
                dVar.m = 0;
                Bundle bundle = new Bundle();
                bundle.putString("extraJson", dVar.G);
                dVar.a(2, bundle);
            } else {
                dVar.m++;
                if (dVar.m < 10) {
                    dVar.a(1008, (Object) null, 50L);
                }
            }
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "startAssistantLocationImpl");
        }
    }

    public void k() {
        try {
            this.c.a();
            e();
            this.V.clear();
            this.W.clear();
            this.n = false;
            this.Q = false;
            this.o = 0L;
            this.m = 0;
            this.p = null;
            this.q = 0L;
            this.N = false;
            this.X = 0;
            this.M = 0;
            this.Y = null;
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "stopLocation");
        }
    }

    static /* synthetic */ void k(d dVar) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("extraJson", dVar.G);
            dVar.a(3, bundle);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "stopAssistantLocationImpl");
        }
    }

    final void a() {
        try {
            if (!this.x) {
                this.x = true;
                this.w.a();
            }
        } catch (Throwable th) {
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void addGeoFenceAlert(String str, double d, double d2, float f, long j, PendingIntent pendingIntent) {
        try {
            GeoFence geoFence = new GeoFence();
            geoFence.setFenceId(str);
            DPoint dPoint = new DPoint(d, d2);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(dPoint);
            arrayList.add(arrayList2);
            geoFence.setCenter(dPoint);
            geoFence.setPointList(arrayList);
            geoFence.setRadius(f);
            geoFence.setPendingIntent(pendingIntent);
            geoFence.setActivatesAction(7);
            geoFence.setType(0);
            geoFence.setExpiration(j);
            a(1006, geoFence, 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "addGeoFenceAlert");
        }
    }

    final void b() {
        try {
            if (this.w != null) {
                this.y = this.w.b();
            }
        } catch (Throwable th) {
        }
    }

    final void c() {
        try {
            if (this.w != null && this.x) {
                this.x = false;
                this.w.c();
            }
        } catch (Throwable th) {
        }
    }

    final void d() {
        try {
            if (this.z != 0.0d) {
                b();
                cx.a(this.z, this.y);
            }
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "doSaveGPSInfo");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public AMapLocation getLastKnownLocation() {
        try {
            if (this.i != null) {
                return this.i.a();
            }
            return null;
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "getLastKnownLocation");
            return null;
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public boolean isStarted() {
        return this.P;
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void onDestroy() {
        try {
            a(1011, (Object) null, 0L);
            this.A = true;
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "onDestroy");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void removeGeoFenceAlert(PendingIntent pendingIntent) {
        try {
            a(1007, pendingIntent, 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "removeGeoFenceAlert 2");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void removeGeoFenceAlert(PendingIntent pendingIntent, String str) {
        try {
            GeoFence geoFence = new GeoFence();
            geoFence.setFenceId(str);
            geoFence.setPendingIntent(pendingIntent);
            a(1010, geoFence, 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "removeGeoFenceAlert 1");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void setLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            a(1002, aMapLocationListener, 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "setLocationListener");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void setLocationOption(AMapLocationClientOption aMapLocationClientOption) {
        try {
            a(PointerIconCompat.TYPE_ZOOM_IN, aMapLocationClientOption.clone(), 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "setLocationOption");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void startAssistantLocation() {
        try {
            a(1008, (Object) null, 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "startAssistantLocation");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void startLocation() {
        try {
            a(1003, (Object) null, 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "startLocation");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void stopAssistantLocation() {
        try {
            a(1009, (Object) null, 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "stopAssistantLocation");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void stopLocation() {
        try {
            a(1004, (Object) null, 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "stopLocation");
        }
    }

    @Override // com.amap.api.location.LocationManagerBase
    public void unRegisterLocationListener(AMapLocationListener aMapLocationListener) {
        try {
            a(1005, aMapLocationListener, 0L);
        } catch (Throwable th) {
            f.a(th, "AMapLocationManager", "unRegisterLocationListener");
        }
    }
}

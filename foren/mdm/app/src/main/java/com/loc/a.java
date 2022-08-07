package com.loc;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.GeoFenceListener;
import com.amap.api.fence.GeoFenceManagerBase;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.DPoint;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.district.DistrictSearchQuery;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: GeoFenceManager.java */
@SuppressLint({"NewApi"})
/* loaded from: classes2.dex */
public class a implements GeoFenceManagerBase {
    Context b;
    cu a = null;
    PendingIntent c = null;
    String d = null;
    GeoFenceListener e = null;
    volatile int f = 1;
    ArrayList<GeoFence> g = new ArrayList<>();
    c h = null;
    Object i = new Object();
    HandlerC0044a j = null;
    b k = null;
    volatile boolean l = false;
    volatile boolean m = false;
    b n = null;
    c o = null;
    AMapLocationClient p = null;
    volatile AMapLocation q = null;
    long r = 0;
    AMapLocationClientOption s = null;
    int t = 0;

    /* renamed from: u */
    AMapLocationListener f33u = new AMapLocationListener() { // from class: com.loc.a.1
        /* JADX WARN: Removed duplicated region for block: B:13:0x0060 A[Catch: Throwable -> 0x009d, TryCatch #0 {Throwable -> 0x009d, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0014, B:9:0x0027, B:11:0x0036, B:13:0x0060, B:15:0x006b, B:16:0x0081, B:18:0x0090), top: B:20:0x0004 }] */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0027 A[Catch: Throwable -> 0x009d, TryCatch #0 {Throwable -> 0x009d, blocks: (B:3:0x0004, B:5:0x000a, B:7:0x0014, B:9:0x0027, B:11:0x0036, B:13:0x0060, B:15:0x006b, B:16:0x0081, B:18:0x0090), top: B:20:0x0004 }] */
        @Override // com.amap.api.location.AMapLocationListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onLocationChanged(com.amap.api.location.AMapLocation r10) {
            /*
                r9 = this;
                r0 = 8
                r1 = 1
                r2 = 0
                com.loc.a r3 = com.loc.a.this     // Catch: Throwable -> 0x009d
                r3.q = r10     // Catch: Throwable -> 0x009d
                if (r10 == 0) goto L_0x005e
                int r0 = r10.getErrorCode()     // Catch: Throwable -> 0x009d
                int r3 = r10.getErrorCode()     // Catch: Throwable -> 0x009d
                if (r3 != 0) goto L_0x0036
                com.loc.a r2 = com.loc.a.this     // Catch: Throwable -> 0x009d
                long r4 = com.loc.cx.b()     // Catch: Throwable -> 0x009d
                r2.r = r4     // Catch: Throwable -> 0x009d
                com.loc.a r2 = com.loc.a.this     // Catch: Throwable -> 0x009d
                r3 = 5
                r4 = 0
                r6 = 0
                r2.a(r3, r4, r6)     // Catch: Throwable -> 0x009d
            L_0x0025:
                if (r1 == 0) goto L_0x0060
                com.loc.a r0 = com.loc.a.this     // Catch: Throwable -> 0x009d
                r1 = 0
                r0.t = r1     // Catch: Throwable -> 0x009d
                com.loc.a r0 = com.loc.a.this     // Catch: Throwable -> 0x009d
                r1 = 6
                r2 = 0
                r4 = 0
                r0.a(r1, r2, r4)     // Catch: Throwable -> 0x009d
            L_0x0035:
                return
            L_0x0036:
                com.loc.a r1 = com.loc.a.this     // Catch: Throwable -> 0x009d
                java.lang.String r1 = "定位失败"
                int r3 = r10.getErrorCode()     // Catch: Throwable -> 0x009d
                java.lang.String r4 = r10.getErrorInfo()     // Catch: Throwable -> 0x009d
                r5 = 1
                java.lang.String[] r5 = new java.lang.String[r5]     // Catch: Throwable -> 0x009d
                r6 = 0
                java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: Throwable -> 0x009d
                java.lang.String r8 = "locationDetail:"
                r7.<init>(r8)     // Catch: Throwable -> 0x009d
                java.lang.String r8 = r10.getLocationDetail()     // Catch: Throwable -> 0x009d
                java.lang.StringBuilder r7 = r7.append(r8)     // Catch: Throwable -> 0x009d
                java.lang.String r7 = r7.toString()     // Catch: Throwable -> 0x009d
                r5[r6] = r7     // Catch: Throwable -> 0x009d
                com.loc.a.a(r1, r3, r4, r5)     // Catch: Throwable -> 0x009d
            L_0x005e:
                r1 = r2
                goto L_0x0025
            L_0x0060:
                android.os.Bundle r1 = new android.os.Bundle     // Catch: Throwable -> 0x009d
                r1.<init>()     // Catch: Throwable -> 0x009d
                com.loc.a r2 = com.loc.a.this     // Catch: Throwable -> 0x009d
                boolean r2 = r2.l     // Catch: Throwable -> 0x009d
                if (r2 != 0) goto L_0x0081
                com.loc.a r2 = com.loc.a.this     // Catch: Throwable -> 0x009d
                r3 = 7
                r2.a(r3)     // Catch: Throwable -> 0x009d
                java.lang.String r2 = "interval"
                r4 = 2000(0x7d0, double:9.88E-321)
                r1.putLong(r2, r4)     // Catch: Throwable -> 0x009d
                com.loc.a r2 = com.loc.a.this     // Catch: Throwable -> 0x009d
                r3 = 8
                r4 = 2000(0x7d0, double:9.88E-321)
                r2.a(r3, r1, r4)     // Catch: Throwable -> 0x009d
            L_0x0081:
                com.loc.a r2 = com.loc.a.this     // Catch: Throwable -> 0x009d
                int r3 = r2.t     // Catch: Throwable -> 0x009d
                int r3 = r3 + 1
                r2.t = r3     // Catch: Throwable -> 0x009d
                com.loc.a r2 = com.loc.a.this     // Catch: Throwable -> 0x009d
                int r2 = r2.t     // Catch: Throwable -> 0x009d
                r3 = 3
                if (r2 < r3) goto L_0x0035
                java.lang.String r2 = "location_errorcode"
                r1.putInt(r2, r0)     // Catch: Throwable -> 0x009d
                com.loc.a r0 = com.loc.a.this     // Catch: Throwable -> 0x009d
                r2 = 1002(0x3ea, float:1.404E-42)
                r0.a(r2, r1)     // Catch: Throwable -> 0x009d
                goto L_0x0035
            L_0x009d:
                r0 = move-exception
                goto L_0x0035
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.a.AnonymousClass1.onLocationChanged(com.amap.api.location.AMapLocation):void");
        }
    };

    /* compiled from: GeoFenceManager.java */
    /* renamed from: com.loc.a$a */
    /* loaded from: classes2.dex */
    public class HandlerC0044a extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HandlerC0044a(Looper looper) {
            super(looper);
            a.this = r1;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        a.this.b(message.getData());
                        break;
                    case 1:
                        a.this.c(message.getData());
                        break;
                    case 2:
                        a.this.e(message.getData());
                        break;
                    case 3:
                        a.this.d(message.getData());
                        break;
                    case 4:
                        a.this.f(message.getData());
                        break;
                    case 5:
                        a.this.b();
                        break;
                    case 6:
                        a.this.a(a.this.q);
                        break;
                    case 7:
                        a.this.a();
                        break;
                    case 8:
                        a.this.h(message.getData());
                        break;
                    case 9:
                        a.this.a(message.getData());
                        break;
                }
            } catch (Throwable th) {
            }
        }
    }

    /* compiled from: GeoFenceManager.java */
    /* loaded from: classes2.dex */
    public static class b extends HandlerThread {
        public b(String str) {
            super(str);
        }
    }

    /* compiled from: GeoFenceManager.java */
    /* loaded from: classes2.dex */
    public class c extends Handler {
        public c() {
            a.this = r1;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Looper looper) {
            super(looper);
            a.this = r1;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Bundle data = message.getData();
            switch (message.what) {
                case 1000:
                    a.this.g(data);
                    return;
                case 1001:
                    try {
                        a.this.a((GeoFence) data.getParcelable("geoFence"));
                        return;
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return;
                    }
                case 1002:
                    try {
                        a.this.b(data.getInt(GeoFence.BUNDLE_KEY_LOCERRORCODE));
                        return;
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public a(Context context) {
        this.b = null;
        try {
            this.b = context.getApplicationContext();
            c();
        } catch (Throwable th) {
            f.a(th, "GeoFenceManger", "<init>");
        }
    }

    public static float a(DPoint dPoint, List<DPoint> list) {
        float f = Float.MAX_VALUE;
        if (dPoint == null || list == null || list.isEmpty()) {
            return Float.MAX_VALUE;
        }
        for (DPoint dPoint2 : list) {
            f = Math.min(f, cx.a(dPoint, dPoint2));
        }
        return f;
    }

    private int a(List<GeoFence> list) {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            for (GeoFence geoFence : list) {
                b(geoFence);
            }
            return 0;
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "addGeoFenceList");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    private static Bundle a(GeoFence geoFence, String str, String str2, int i, int i2) {
        Bundle bundle = new Bundle();
        if (str == null) {
            str = "";
        }
        bundle.putString(GeoFence.BUNDLE_KEY_FENCEID, str);
        bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
        bundle.putInt("event", i);
        bundle.putInt(GeoFence.BUNDLE_KEY_LOCERRORCODE, i2);
        bundle.putParcelable(GeoFence.BUNDLE_KEY_FENCE, geoFence);
        return bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ca  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amap.api.fence.GeoFence a(android.os.Bundle r11, boolean r12) {
        /*
            r10 = this;
            r2 = -1
            r4 = 0
            com.amap.api.fence.GeoFence r5 = new com.amap.api.fence.GeoFence
            r5.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.amap.api.location.DPoint r0 = new com.amap.api.location.DPoint
            r0.<init>()
            if (r12 == 0) goto L_0x009c
            r1 = 1
            r5.setType(r1)
            java.lang.String r1 = "points"
            java.util.ArrayList r1 = r11.getParcelableArrayList(r1)
            if (r1 == 0) goto L_0x0024
            com.amap.api.location.DPoint r0 = b(r1)
        L_0x0024:
            float r6 = b(r0, r1)
            r5.setMaxDis2Center(r6)
            float r6 = a(r0, r1)
            r5.setMinDis2Center(r6)
        L_0x0032:
            int r6 = r10.f
            r5.setActivatesAction(r6)
            java.lang.String r6 = "customId"
            java.lang.String r6 = r11.getString(r6)
            r5.setCustomId(r6)
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r6.add(r1)
            r5.setPointList(r6)
            r5.setCenter(r0)
            java.lang.String r0 = "fenceid"
            java.lang.String r1 = r11.getString(r0)     // Catch: Throwable -> 0x00c0
            java.lang.String r0 = "expiration"
            r6 = -1
            long r2 = r11.getLong(r0, r6)     // Catch: Throwable -> 0x00d0
            java.lang.String r0 = "pIntent"
            android.os.Parcelable r0 = r11.getParcelable(r0)     // Catch: Throwable -> 0x00d0
            android.app.PendingIntent r0 = (android.app.PendingIntent) r0     // Catch: Throwable -> 0x00d0
            r8 = r2
            r2 = r0
            r3 = r1
            r0 = r8
        L_0x0068:
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 == 0) goto L_0x00c6
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            long r6 = com.loc.c.a()
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r3 = r3.toString()
            r5.setFenceId(r3)
        L_0x0082:
            java.lang.String r3 = r10.d
            r5.setPendingIntentAction(r3)
            r5.setExpiration(r0)
            if (r2 == 0) goto L_0x00ca
            r5.setPendingIntent(r2)
        L_0x008f:
            com.loc.cu r0 = r10.a
            if (r0 == 0) goto L_0x009b
            com.loc.cu r0 = r10.a
            android.content.Context r1 = r10.b
            r2 = 2
            r0.a(r1, r2)
        L_0x009b:
            return r5
        L_0x009c:
            r0 = 0
            r5.setType(r0)
            java.lang.String r0 = "point"
            android.os.Parcelable r0 = r11.getParcelable(r0)
            com.amap.api.location.DPoint r0 = (com.amap.api.location.DPoint) r0
            if (r0 == 0) goto L_0x00ad
            r1.add(r0)
        L_0x00ad:
            java.lang.String r6 = "radius"
            r7 = 1148846080(0x447a0000, float:1000.0)
            float r6 = r11.getFloat(r6, r7)
            r5.setRadius(r6)
            r5.setMinDis2Center(r6)
            r5.setMaxDis2Center(r6)
            goto L_0x0032
        L_0x00c0:
            r0 = move-exception
            r0 = r2
            r2 = r4
        L_0x00c3:
            r3 = r2
            r2 = r4
            goto L_0x0068
        L_0x00c6:
            r5.setFenceId(r3)
            goto L_0x0082
        L_0x00ca:
            android.app.PendingIntent r0 = r10.c
            r5.setPendingIntent(r0)
            goto L_0x008f
        L_0x00d0:
            r0 = move-exception
            r8 = r2
            r2 = r1
            r0 = r8
            goto L_0x00c3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.a(android.os.Bundle, boolean):com.amap.api.fence.GeoFence");
    }

    static void a(String str, int i, String str2, String... strArr) {
        Log.i("fenceErrLog", "===========================================");
        Log.i("fenceErrLog", "              " + str + "                ");
        Log.i("fenceErrLog", "===========================================");
        Log.i("fenceErrLog", "errorCode:" + i);
        Log.i("fenceErrLog", "错误信息:" + str2);
        if (strArr != null && strArr.length > 0) {
            for (String str3 : strArr) {
                Log.i("fenceErrLog", str3);
            }
        }
        Log.i("fenceErrLog", "===========================================");
    }

    private static boolean a(double d, double d2, double d3, double d4, double d5, double d6) {
        return Math.abs(((d3 - d) * (d6 - d2)) - ((d5 - d) * (d4 - d2))) < 1.0E-9d && (d - d3) * (d - d5) <= 0.0d && (d2 - d4) * (d2 - d6) <= 0.0d;
    }

    private static boolean a(GeoFence geoFence, int i) {
        boolean z = false;
        if ((i & 1) == 1) {
            try {
                if (geoFence.getStatus() == 1) {
                    z = true;
                }
            } catch (Throwable th) {
                f.a(th, "Utils", "remindStatus");
                return z;
            }
        }
        if ((i & 2) == 2 && geoFence.getStatus() == 2) {
            z = true;
        }
        if ((i & 4) == 4) {
            if (geoFence.getStatus() == 3) {
                return true;
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x019d A[Catch: Throwable -> 0x01a7, TryCatch #0 {Throwable -> 0x01a7, blocks: (B:3:0x0002, B:6:0x000a, B:8:0x0010, B:10:0x001a, B:11:0x001e, B:13:0x0022, B:18:0x0058, B:19:0x0060, B:21:0x0066, B:27:0x008d, B:29:0x00a8, B:31:0x00b7, B:33:0x00c1, B:36:0x0101, B:38:0x0110, B:42:0x0126, B:43:0x0128, B:44:0x012f, B:48:0x0143, B:49:0x0146, B:51:0x0160, B:61:0x019d, B:62:0x01a0), top: B:71:0x0002 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean a(com.amap.api.location.AMapLocation r32, com.amap.api.fence.GeoFence r33) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.a.a(com.amap.api.location.AMapLocation, com.amap.api.fence.GeoFence):boolean");
    }

    public static float b(DPoint dPoint, List<DPoint> list) {
        float f = Float.MIN_VALUE;
        if (dPoint == null || list == null || list.isEmpty()) {
            return Float.MIN_VALUE;
        }
        for (DPoint dPoint2 : list) {
            f = Math.max(f, cx.a(dPoint, dPoint2));
        }
        return f;
    }

    private int b(GeoFence geoFence) {
        try {
            if (this.g == null) {
                this.g = new ArrayList<>();
            }
            if (this.g.contains(geoFence)) {
                return 17;
            }
            this.g.add(geoFence);
            return 0;
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "addGeoFence2List");
            a("添加围栏失败", 8, th.getMessage(), new String[0]);
            return 8;
        }
    }

    private static DPoint b(List<DPoint> list) {
        DPoint dPoint;
        double d = 0.0d;
        DPoint dPoint2 = new DPoint();
        if (list != null) {
            try {
                double d2 = 0.0d;
                for (DPoint dPoint3 : list) {
                    d2 += dPoint3.getLatitude();
                    d += dPoint3.getLongitude();
                }
                dPoint = new DPoint(cx.c(d2 / list.size()), cx.c(d / list.size()));
            } catch (Throwable th) {
                f.a(th, "GeoFenceUtil", "getPolygonCenter");
                return dPoint2;
            }
        } else {
            dPoint = dPoint2;
        }
        return dPoint;
    }

    private static boolean b(AMapLocation aMapLocation, GeoFence geoFence) {
        Throwable th;
        boolean z = true;
        boolean z2 = false;
        try {
            if (a(aMapLocation, geoFence)) {
                if (geoFence.getEnterTime() == -1) {
                    if (geoFence.getStatus() != 1) {
                        geoFence.setEnterTime(cx.b());
                        geoFence.setStatus(1);
                        return true;
                    }
                } else if (geoFence.getStatus() != 3 && cx.b() - geoFence.getEnterTime() > 600000) {
                    geoFence.setStatus(3);
                    return true;
                }
            } else if (geoFence.getStatus() != 2) {
                try {
                    geoFence.setStatus(2);
                    geoFence.setEnterTime(-1L);
                    z2 = true;
                } catch (Throwable th2) {
                    th = th2;
                    f.a(th, "Utils", "isFenceStatusChanged");
                    return z;
                }
            }
            return z2;
        } catch (Throwable th3) {
            th = th3;
            z = false;
        }
    }

    private static int c(int i) {
        int i2 = 8;
        switch (i) {
            case 10000:
                i2 = 0;
                break;
            case 10001:
            case GLMapStaticValue.AM_CALLBACK_NEED_NEXTFRAME /* 10002 */:
            case 10007:
            case 10008:
            case 10009:
            case 10012:
            case 10013:
                i2 = 7;
                break;
            case 10003:
            case 10004:
            case 10005:
            case 10006:
            case 10010:
            case 10011:
            case 10014:
            case 10015:
            case 10016:
            case 10017:
                i2 = 4;
                break;
            case 20000:
            case 20001:
            case 20002:
                i2 = 1;
                break;
        }
        if (i2 != 0) {
            a("添加围栏失败", i2, "searchErrCode is " + i2, new String[0]);
        }
        return i2;
    }

    private void c() {
        if (!this.m) {
            try {
                if (Looper.myLooper() == null) {
                    this.h = new c(this.b.getMainLooper());
                } else {
                    this.h = new c();
                }
            } catch (Throwable th) {
                f.a(th, "GeoFenceManger", "init 1");
            }
            try {
                this.k = new b("fenceActionThread");
                this.k.setPriority(5);
                this.k.start();
                this.j = new HandlerC0044a(this.k.getLooper());
            } catch (Throwable th2) {
                f.a(th2, "GeoFenceManger", "init 2");
            }
            try {
                Context context = this.b;
                this.n = new b();
                this.o = new c();
                this.s = new AMapLocationClientOption();
                this.p = new AMapLocationClient(this.b);
                this.s.setLocationCacheEnable(false);
                this.p.setLocationListener(this.f33u);
                if (this.a == null) {
                    this.a = new cu();
                }
            } catch (Throwable th3) {
                f.a(th3, "GeoFenceManger", "init 3");
            }
            this.m = true;
            try {
                if (this.d != null && this.c == null) {
                    createPendingIntent(this.d);
                }
            } catch (Throwable th4) {
                f.a(th4, "GeoFenceManger", "init 4");
            }
        }
    }

    private void d() {
        if (this.m) {
            synchronized (this.i) {
                if (this.j != null) {
                    this.j.removeCallbacksAndMessages(null);
                }
                this.j = null;
            }
            if (this.p != null) {
                this.p.stopLocation();
                this.p.onDestroy();
            }
            this.p = null;
            if (this.k != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.k.quitSafely();
                } else {
                    this.k.quit();
                }
            }
            this.k = null;
            if (this.g != null) {
                this.g.clear();
                this.g = null;
            }
            this.n = null;
            if (this.c != null) {
                this.c.cancel();
            }
            this.c = null;
            if (this.a != null) {
                this.a.b(this.b);
            }
            this.m = false;
        }
    }

    private boolean e() {
        return this.q != null && cx.a(this.q) && cx.b() - this.r < 10000;
    }

    final void a() {
        try {
            if (this.p != null) {
                try {
                    if (this.l) {
                        a(8);
                    }
                    if (this.p != null) {
                        this.p.stopLocation();
                    }
                    this.l = false;
                } catch (Throwable th) {
                }
                this.s.setOnceLocation(true);
                this.p.setLocationOption(this.s);
                this.p.startLocation();
            }
        } catch (Throwable th2) {
            f.a(th2, "GeoFenceManager", "doStartOnceLocation");
        }
    }

    final void a(int i) {
        try {
            synchronized (this.i) {
                if (this.j != null) {
                    this.j.removeMessages(i);
                }
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "removeActionHandlerMessage");
        }
    }

    final void a(int i, Bundle bundle) {
        try {
            if (this.h != null) {
                Message obtainMessage = this.h.obtainMessage();
                obtainMessage.what = i;
                obtainMessage.setData(bundle);
                this.h.sendMessage(obtainMessage);
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "sendResultHandlerMessage");
        }
    }

    final void a(int i, Bundle bundle, long j) {
        try {
            synchronized (this.i) {
                if (this.j != null) {
                    Message obtainMessage = this.j.obtainMessage();
                    obtainMessage.what = i;
                    obtainMessage.setData(bundle);
                    this.j.sendMessageDelayed(obtainMessage, j);
                }
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "sendActionHandlerMessage");
        }
    }

    final void a(Bundle bundle) {
        int i = 1;
        if (bundle != null) {
            try {
                i = bundle.getInt("activatesAction", 1);
            } catch (Throwable th) {
                f.a(th, "GeoFenceManager", "doSetActivatesAction");
                return;
            }
        }
        if (this.f != i) {
            if (this.g != null && !this.g.isEmpty()) {
                Iterator<GeoFence> it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence next = it.next();
                    next.setStatus(0);
                    next.setEnterTime(-1L);
                }
            }
            if (this.j != null) {
                if (e()) {
                    a(6, null, 0L);
                } else {
                    a(7);
                    a(7, null, 1000L);
                }
            }
        }
        this.f = i;
    }

    final void a(GeoFence geoFence) {
        try {
            if (this.b == null) {
                return;
            }
            if (this.c != null || geoFence.getPendingIntent() != null) {
                Intent intent = new Intent();
                intent.putExtras(a(geoFence, geoFence.getFenceId(), geoFence.getCustomId(), geoFence.getStatus(), 0));
                if (this.d != null) {
                    intent.setAction(this.d);
                }
                intent.setPackage(k.c(this.b));
                if (geoFence.getPendingIntent() != null) {
                    geoFence.getPendingIntent().send(this.b, 0, intent);
                } else {
                    this.c.send(this.b, 0, intent);
                }
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "resultTriggerGeoFence");
        }
    }

    final void a(AMapLocation aMapLocation) {
        try {
            if (this.g != null && !this.g.isEmpty() && aMapLocation != null && aMapLocation.getErrorCode() == 0) {
                Iterator<GeoFence> it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence next = it.next();
                    if (b(aMapLocation, next) && a(next, this.f)) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("geoFence", next);
                        a(1001, bundle);
                    }
                }
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "doCheckFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addDistrictGeoFence(String str, String str2) {
        try {
            c();
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str2);
            a(4, bundle, 0L);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "addDistricetGeoFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addKeywordGeoFence(String str, String str2, String str3, int i, String str4) {
        int i2 = 25;
        try {
            c();
            int i3 = i <= 0 ? 10 : i;
            if (i3 <= 25) {
                i2 = i3;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString("poiType", str2);
            bundle.putString(DistrictSearchQuery.KEYWORDS_CITY, str3);
            bundle.putInt("size", i2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str4);
            a(2, bundle, 0L);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "addKeywordGeoFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addNearbyGeoFence(String str, String str2, DPoint dPoint, float f, int i, String str3) {
        int i2 = 25;
        try {
            c();
            if (f <= 0.0f || f > 50000.0f) {
                f = 3000.0f;
            }
            int i3 = i <= 0 ? 10 : i;
            if (i3 <= 25) {
                i2 = i3;
            }
            Bundle bundle = new Bundle();
            bundle.putString("keyword", str);
            bundle.putString("poiType", str2);
            bundle.putParcelable("centerPoint", dPoint);
            bundle.putFloat("aroundRadius", f);
            bundle.putInt("size", i2);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str3);
            a(3, bundle, 0L);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "addNearbyGeoFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addPolygonGeoFence(List<DPoint> list, String str) {
        try {
            c();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("points", new ArrayList<>(list));
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(1, bundle, 0L);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "addPolygonGeoFence");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void addRoundGeoFence(DPoint dPoint, float f, String str, String str2, long j, PendingIntent pendingIntent) {
        try {
            c();
            if (f <= 0.0f) {
                f = 1000.0f;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable("point", dPoint);
            bundle.putFloat("radius", f);
            bundle.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle.putString(GeoFence.BUNDLE_KEY_FENCEID, str2);
            bundle.putLong("expiration", j);
            bundle.putParcelable("pIntent", pendingIntent);
            a(0, bundle, 0L);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "addRoundGeoFence");
        }
    }

    final void b() {
        try {
            if (cx.a(this.q)) {
                AMapLocation aMapLocation = this.q;
                ArrayList<GeoFence> arrayList = this.g;
                float f = Float.MAX_VALUE;
                if (aMapLocation != null && aMapLocation.getErrorCode() == 0 && arrayList != null && !arrayList.isEmpty()) {
                    DPoint dPoint = new DPoint(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    Iterator<GeoFence> it = arrayList.iterator();
                    float f2 = Float.MAX_VALUE;
                    while (true) {
                        if (!it.hasNext()) {
                            f = f2;
                            break;
                        }
                        GeoFence next = it.next();
                        float a = cx.a(dPoint, next.getCenter());
                        if (a > next.getMinDis2Center() && a < next.getMaxDis2Center()) {
                            f = 0.0f;
                            break;
                        }
                        if (a > next.getMaxDis2Center()) {
                            f2 = Math.min(f2, a - next.getMaxDis2Center());
                        }
                        f2 = a < next.getMinDis2Center() ? Math.min(f2, next.getMinDis2Center() - a) : f2;
                    }
                }
                if (f < 1000.0f) {
                    a(7);
                    Bundle bundle = new Bundle();
                    bundle.putLong("interval", 2000L);
                    a(8, bundle, 1000L);
                } else if (f < 5000.0f) {
                    a(7);
                    a(7, null, 10000L);
                } else {
                    a(7);
                    a(7, null, ((f - 4000.0f) / 100.0f) * 1000.0f);
                }
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "doCheckLocationPolicy");
        }
    }

    final void b(int i) {
        try {
            if (this.b != null && this.c != null) {
                Intent intent = new Intent();
                intent.putExtras(a(null, null, null, 4, i));
                this.c.send(this.b, 0, intent);
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "resultRemindLocationError");
        }
    }

    final void b(Bundle bundle) {
        int i;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            String str = "";
            if (bundle != null && !bundle.isEmpty()) {
                DPoint dPoint = (DPoint) bundle.getParcelable("point");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (dPoint != null) {
                    if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d || dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                        a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                        i = 1;
                    } else {
                        GeoFence a = a(bundle, false);
                        i = b(a);
                        if (i == 0) {
                            arrayList.add(a);
                        }
                    }
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt(MyLocationStyle.ERROR_CODE, i);
                    bundle2.putParcelableArrayList("resultList", arrayList);
                    bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
                    a(1000, bundle2);
                }
                str = str;
            }
            i = 1;
            Bundle bundle22 = new Bundle();
            bundle22.putInt(MyLocationStyle.ERROR_CODE, i);
            bundle22.putParcelableArrayList("resultList", arrayList);
            bundle22.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            a(1000, bundle22);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "doAddGeoFence_round");
        }
    }

    final void c(Bundle bundle) {
        GeoFence a;
        int i = 1;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            String str = "";
            if (bundle != null && !bundle.isEmpty()) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("points");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (parcelableArrayList != null && parcelableArrayList.size() > 2 && (i = b((a = a(bundle, true)))) == 0) {
                    arrayList.add(a);
                }
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle2.putInt(MyLocationStyle.ERROR_CODE, i);
            bundle2.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle2);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "doAddGeoFence_polygon");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public PendingIntent createPendingIntent(String str) {
        try {
            c();
            Intent intent = new Intent();
            intent.setPackage(k.c(this.b));
            intent.setAction(str);
            this.c = PendingIntent.getBroadcast(this.b, 0, intent, 0);
            this.d = str;
            if (this.g != null && !this.g.isEmpty()) {
                Iterator<GeoFence> it = this.g.iterator();
                while (it.hasNext()) {
                    GeoFence next = it.next();
                    next.setPendingIntent(this.c);
                    next.setPendingIntentAction(this.d);
                }
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "createPendingIntent");
        }
        return this.c;
    }

    final void d(Bundle bundle) {
        int i = 1;
        try {
            String str = "";
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            if (bundle != null && !bundle.isEmpty()) {
                String string = bundle.getString("keyword");
                String string2 = bundle.getString("poiType");
                DPoint dPoint = (DPoint) bundle.getParcelable("centerPoint");
                float f = bundle.getFloat("aroundRadius", 3000.0f);
                int i2 = bundle.getInt("size", 10);
                String string3 = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                if (dPoint == null || TextUtils.isEmpty(string)) {
                    str = string3;
                } else if (dPoint.getLatitude() > 90.0d || dPoint.getLatitude() < -90.0d || dPoint.getLongitude() > 180.0d || dPoint.getLongitude() < -180.0d) {
                    a("添加围栏失败", 1, "经纬度错误，传入的纬度：" + dPoint.getLatitude() + "传入的经度:" + dPoint.getLongitude(), new String[0]);
                    str = string3;
                } else {
                    String a = this.n.a(this.b, "http://restapi.amap.com/v3/place/around?", string, string2, String.valueOf(i2), String.valueOf(cx.c(dPoint.getLatitude())), String.valueOf(cx.c(dPoint.getLongitude())), String.valueOf(Float.valueOf(f).intValue()));
                    if (a != null) {
                        List<GeoFence> arrayList2 = new ArrayList<>();
                        Bundle bundle2 = new Bundle();
                        bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string3);
                        bundle2.putString("pendingIntentAction", this.d);
                        bundle2.putLong("expiration", -1L);
                        bundle2.putInt("activatesAction", this.f);
                        bundle2.putFloat("geoRadius", 200.0f);
                        c cVar = this.o;
                        int b2 = c.b(a, arrayList2, bundle2);
                        if (b2 != 10000) {
                            i = c(b2);
                        } else if (arrayList2.isEmpty()) {
                            i = 16;
                            str = string3;
                        } else {
                            i = a(arrayList2);
                            if (i == 0) {
                                arrayList.addAll(arrayList2);
                                i = i;
                                str = string3;
                            }
                        }
                        str = string3;
                    } else {
                        i = 4;
                        str = string3;
                    }
                }
            }
            Bundle bundle3 = new Bundle();
            bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle3.putInt(MyLocationStyle.ERROR_CODE, i);
            bundle3.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle3);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "doAddGeoFence_nearby");
        }
    }

    final void e(Bundle bundle) {
        int i;
        try {
            String str = "";
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            if (bundle == null || bundle.isEmpty()) {
                i = 1;
            } else {
                String string = bundle.getString("keyword");
                String string2 = bundle.getString("poiType");
                String string3 = bundle.getString(DistrictSearchQuery.KEYWORDS_CITY);
                int i2 = bundle.getInt("size", 10);
                String string4 = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                Bundle bundle2 = new Bundle();
                bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, string4);
                bundle2.putString("pendingIntentAction", this.d);
                bundle2.putLong("expiration", -1L);
                bundle2.putInt("activatesAction", this.f);
                bundle2.putFloat("geoRadius", 1000.0f);
                String a = this.n.a(this.b, "http://restapi.amap.com/v3/place/text?", string, string2, string3, String.valueOf(i2));
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                    str = string4;
                    i = 1;
                } else if (a != null) {
                    List<GeoFence> arrayList2 = new ArrayList<>();
                    c cVar = this.o;
                    int a2 = c.a(a, arrayList2, bundle2);
                    if (a2 != 10000) {
                        i = c(a2);
                    } else if (arrayList2.isEmpty()) {
                        i = 16;
                        str = string4;
                    } else {
                        i = a(arrayList2);
                        if (i == 0) {
                            arrayList.addAll(arrayList2);
                            i = i;
                            str = string4;
                        }
                    }
                    str = string4;
                } else {
                    i = 4;
                    str = string4;
                }
            }
            Bundle bundle3 = new Bundle();
            bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle3.putInt(MyLocationStyle.ERROR_CODE, i);
            bundle3.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle3);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "doAddGeoFence_Keyword");
        }
    }

    final void f(Bundle bundle) {
        int i;
        try {
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            String str = "";
            if (bundle != null && !bundle.isEmpty()) {
                String string = bundle.getString("keyword");
                str = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                String a = this.n.a(this.b, "http://restapi.amap.com/v3/config/district?", string);
                if (!TextUtils.isEmpty(string)) {
                    if (a != null) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
                        bundle2.putString("pendingIntentAction", this.d);
                        bundle2.putLong("expiration", -1L);
                        bundle2.putInt("activatesAction", this.f);
                        ArrayList arrayList2 = new ArrayList();
                        int c2 = this.o.c(a, arrayList2, bundle2);
                        if (c2 != 10000) {
                            i = c(c2);
                        } else if (arrayList2.isEmpty()) {
                            i = 16;
                        } else {
                            i = a(arrayList2);
                            if (i == 0) {
                                arrayList.addAll(arrayList2);
                            }
                        }
                    } else {
                        i = 4;
                    }
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt(MyLocationStyle.ERROR_CODE, i);
                    bundle3.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
                    bundle3.putParcelableArrayList("resultList", arrayList);
                    a(1000, bundle3);
                }
                str = str;
            }
            i = 1;
            Bundle bundle32 = new Bundle();
            bundle32.putInt(MyLocationStyle.ERROR_CODE, i);
            bundle32.putString(GeoFence.BUNDLE_KEY_CUSTOMID, str);
            bundle32.putParcelableArrayList("resultList", arrayList);
            a(1000, bundle32);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "doAddGeoFence_district");
        }
    }

    final void g(Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    int i = bundle.getInt(MyLocationStyle.ERROR_CODE);
                    ArrayList parcelableArrayList = bundle.getParcelableArrayList("resultList");
                    String string = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                    if (string == null) {
                        string = "";
                    }
                    if (this.e != null) {
                        this.e.onGeoFenceCreateFinished(parcelableArrayList, i, string);
                    }
                    if (i == 0 && this.j != null) {
                        if (e()) {
                            new Bundle().putParcelable("loc", this.q);
                            a(6, null, 0L);
                            a(5, bundle, 0L);
                            return;
                        }
                        a(7);
                        a(7, null, 1000L);
                    }
                }
            } catch (Throwable th) {
                f.a(th, "GeoFenceManager", "resultAddGeoFenceFinished");
            }
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public List<GeoFence> getAllGeoFence() {
        if (this.g == null) {
            this.g = new ArrayList<>();
        }
        return (ArrayList) this.g.clone();
    }

    final void h(Bundle bundle) {
        try {
            if (this.p != null) {
                long j = 2000;
                if (bundle != null && !bundle.isEmpty()) {
                    j = bundle.getLong("interval");
                }
                this.s.setOnceLocation(false);
                this.s.setInterval(j);
                this.p.setLocationOption(this.s);
                if (!this.l) {
                    this.p.stopLocation();
                    this.p.startLocation();
                    this.l = true;
                }
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "doStartContinueLocation");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void removeGeoFence() {
        d();
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public boolean removeGeoFence(GeoFence geoFence) {
        boolean z = false;
        try {
            if (this.g != null) {
                c();
                z = this.g.remove(geoFence);
                if (z && this.g.size() == 0) {
                    d();
                }
            }
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "removeGeoFence(GeoFence)");
        }
        return z;
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void setActivateAction(int i) {
        try {
            c();
            if (i > 7 || i <= 0) {
                i = 1;
            }
            Bundle bundle = new Bundle();
            bundle.putInt("activatesAction", i);
            a(9, bundle, 0L);
        } catch (Throwable th) {
            f.a(th, "GeoFenceManager", "setActivateAction");
        }
    }

    @Override // com.amap.api.fence.GeoFenceManagerBase
    public void setGeoFenceListener(GeoFenceListener geoFenceListener) {
        this.e = geoFenceListener;
    }
}

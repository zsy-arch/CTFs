package com.loc;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.APSServiceBase;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.hyphenate.chat.MessageEncoder;
import com.hyphenate.util.HanziToPinyin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

/* compiled from: APSServiceCore.java */
/* loaded from: classes2.dex */
public class e implements APSServiceBase {
    Context a;
    Messenger e;
    boolean b = false;
    boolean c = false;
    private boolean v = false;
    private boolean w = false;
    String d = null;
    b f = null;
    private long x = 0;
    private String y = "";
    private long z = 0;
    private AMapLocationServer A = null;
    AMapLocation g = null;
    private long B = 0;
    private int C = 0;
    private volatile bz D = null;
    private h E = null;
    bu h = null;
    boolean i = false;
    long j = 0;
    long k = 0;
    cu l = null;
    private boolean F = true;
    private String G = "";
    String m = null;
    ServerSocket n = null;
    boolean o = false;
    Socket p = null;
    boolean q = false;
    a r = null;
    String s = null;
    boolean t = true;

    /* renamed from: u  reason: collision with root package name */
    boolean f38u = false;

    /* compiled from: APSServiceCore.java */
    /* loaded from: classes2.dex */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0073 A[Catch: Throwable -> 0x006a, TRY_ENTER, TryCatch #0 {Throwable -> 0x006a, blocks: (B:20:0x005a, B:21:0x0061, B:22:0x0063, B:23:0x0066, B:26:0x0073, B:27:0x007e, B:29:0x008b, B:31:0x0091, B:33:0x0099, B:35:0x009f, B:36:0x00a5, B:37:0x00b0, B:38:0x00bb, B:39:0x00c6, B:40:0x00d1, B:41:0x00dc), top: B:43:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x007e A[Catch: Throwable -> 0x006a, TryCatch #0 {Throwable -> 0x006a, blocks: (B:20:0x005a, B:21:0x0061, B:22:0x0063, B:23:0x0066, B:26:0x0073, B:27:0x007e, B:29:0x008b, B:31:0x0091, B:33:0x0099, B:35:0x009f, B:36:0x00a5, B:37:0x00b0, B:38:0x00bb, B:39:0x00c6, B:40:0x00d1, B:41:0x00dc), top: B:43:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0097  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00a5 A[Catch: Throwable -> 0x006a, TryCatch #0 {Throwable -> 0x006a, blocks: (B:20:0x005a, B:21:0x0061, B:22:0x0063, B:23:0x0066, B:26:0x0073, B:27:0x007e, B:29:0x008b, B:31:0x0091, B:33:0x0099, B:35:0x009f, B:36:0x00a5, B:37:0x00b0, B:38:0x00bb, B:39:0x00c6, B:40:0x00d1, B:41:0x00dc), top: B:43:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00b0 A[Catch: Throwable -> 0x006a, TryCatch #0 {Throwable -> 0x006a, blocks: (B:20:0x005a, B:21:0x0061, B:22:0x0063, B:23:0x0066, B:26:0x0073, B:27:0x007e, B:29:0x008b, B:31:0x0091, B:33:0x0099, B:35:0x009f, B:36:0x00a5, B:37:0x00b0, B:38:0x00bb, B:39:0x00c6, B:40:0x00d1, B:41:0x00dc), top: B:43:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00bb A[Catch: Throwable -> 0x006a, TryCatch #0 {Throwable -> 0x006a, blocks: (B:20:0x005a, B:21:0x0061, B:22:0x0063, B:23:0x0066, B:26:0x0073, B:27:0x007e, B:29:0x008b, B:31:0x0091, B:33:0x0099, B:35:0x009f, B:36:0x00a5, B:37:0x00b0, B:38:0x00bb, B:39:0x00c6, B:40:0x00d1, B:41:0x00dc), top: B:43:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00c6 A[Catch: Throwable -> 0x006a, TryCatch #0 {Throwable -> 0x006a, blocks: (B:20:0x005a, B:21:0x0061, B:22:0x0063, B:23:0x0066, B:26:0x0073, B:27:0x007e, B:29:0x008b, B:31:0x0091, B:33:0x0099, B:35:0x009f, B:36:0x00a5, B:37:0x00b0, B:38:0x00bb, B:39:0x00c6, B:40:0x00d1, B:41:0x00dc), top: B:43:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00d1 A[Catch: Throwable -> 0x006a, TryCatch #0 {Throwable -> 0x006a, blocks: (B:20:0x005a, B:21:0x0061, B:22:0x0063, B:23:0x0066, B:26:0x0073, B:27:0x007e, B:29:0x008b, B:31:0x0091, B:33:0x0099, B:35:0x009f, B:36:0x00a5, B:37:0x00b0, B:38:0x00bb, B:39:0x00c6, B:40:0x00d1, B:41:0x00dc), top: B:43:0x005a }] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x00dc A[Catch: Throwable -> 0x006a, TRY_LEAVE, TryCatch #0 {Throwable -> 0x006a, blocks: (B:20:0x005a, B:21:0x0061, B:22:0x0063, B:23:0x0066, B:26:0x0073, B:27:0x007e, B:29:0x008b, B:31:0x0091, B:33:0x0099, B:35:0x009f, B:36:0x00a5, B:37:0x00b0, B:38:0x00bb, B:39:0x00c6, B:40:0x00d1, B:41:0x00dc), top: B:43:0x005a }] */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void handleMessage(android.os.Message r7) {
            /*
                Method dump skipped, instructions count: 258
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.e.a.handleMessage(android.os.Message):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: APSServiceCore.java */
    /* loaded from: classes2.dex */
    public class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            try {
                if (e.this.D == null) {
                    e.this.D = new bz(e.this.a);
                }
                e.this.E = new h(e.this.a);
                e.this.h = new bu();
                super.onLooperPrepared();
            } catch (Throwable th) {
                f.a(th, "actionHandler", "onLooperPrepared");
            }
        }
    }

    public e(Context context) {
        this.a = context.getApplicationContext();
    }

    private static AMapLocationServer a(int i, String str) {
        try {
            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
            aMapLocationServer.setErrorCode(i);
            aMapLocationServer.setLocationDetail(str);
            return aMapLocationServer;
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        try {
            if (!this.v) {
                f.a(this.a);
                if (bundle != null) {
                    this.m = bundle.getString("extraJson");
                }
                if (TextUtils.isEmpty(this.m)) {
                    Context context = this.a;
                    this.m = f.a((AMapLocationClientOption) null);
                }
                this.v = true;
                a(this.m);
                this.h.a(this.a);
            }
        } catch (Throwable th) {
            this.F = false;
            this.G = th.getMessage();
            f.a(th, "APSServiceCore", "init");
        }
    }

    private void a(Messenger messenger) {
        try {
            if (cq.d(this.a) && messenger != null) {
                Message obtain = Message.obtain();
                obtain.what = 100;
                messenger.send(obtain);
            }
            if (cq.a()) {
                this.r.sendEmptyMessage(4);
            }
            if (cq.c() && cq.d() > 2) {
                this.r.sendEmptyMessage(5);
            }
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "checkConfig");
        }
    }

    private static void a(Messenger messenger, int i, Bundle bundle) {
        try {
            Message obtain = Message.obtain();
            obtain.setData(bundle);
            obtain.what = i;
            messenger.send(obtain);
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "sendMessage");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(Messenger messenger, String str, int i, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("locationJson", str);
        bundle.putInt("originalLocType", i);
        bundle.putBoolean("fixlastlocation", z);
        a(messenger, 1, bundle);
    }

    static /* synthetic */ void a(e eVar, Messenger messenger) {
        try {
            eVar.b(messenger);
            cq.f(eVar.a);
            eVar.a(true, eVar.a);
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "doCallOtherSer");
        }
    }

    static /* synthetic */ void a(e eVar, Messenger messenger, Bundle bundle) {
        if (bundle != null) {
            try {
                if (!bundle.isEmpty() && !eVar.w) {
                    eVar.w = true;
                    eVar.b(messenger);
                    cq.f(eVar.a);
                    eVar.a(false, eVar.a);
                    eVar.a(messenger);
                    if (cq.a(eVar.B) && "1".equals(bundle.getString("isCacheLoc"))) {
                        eVar.B = cx.b();
                        eVar.h.b();
                    }
                    eVar.c();
                }
            } catch (Throwable th) {
                f.a(th, "APSServiceCore", "doInitAuth");
            }
        }
    }

    private void a(String str) {
        boolean z = true;
        z = false;
        if (str != null) {
            try {
                if (!this.y.equals(str)) {
                    this.z = 0L;
                    JSONObject jSONObject = new JSONObject(str);
                    if (this.h != null) {
                        this.h.a(jSONObject);
                        if (jSONObject.optInt("isKillProcess", 0) != 1) {
                        }
                        this.b = z;
                        this.c = jSONObject.optBoolean("sensorEnable", false);
                    }
                    this.y = str;
                    if (cx.a(jSONObject, "isLocationCacheEnable")) {
                        this.t = jSONObject.getBoolean("isLocationCacheEnable");
                    }
                }
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v13 */
    private void a(Socket socket) {
        Throwable th;
        String str;
        BufferedReader bufferedReader;
        String[] split;
        String[] split2;
        String[] split3;
        String str2 = 0;
        str2 = 0;
        String str3 = null;
        if (socket != null) {
            int i = 30000;
            try {
                try {
                    str = "jsonp1";
                } catch (Throwable th2) {
                    th = th2;
                }
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null && readLine.length() > 0 && (split = readLine.split(HanziToPinyin.Token.SEPARATOR)) != null && split.length > 1 && (split2 = split[1].split("\\?")) != null && split2.length > 1 && (split3 = split2[1].split(com.alipay.sdk.sys.a.b)) != null && split3.length > 0) {
                            int i2 = 30000;
                            String str4 = str;
                            for (String str5 : split3) {
                                try {
                                    String[] split4 = str5.split("=");
                                    if (split4 != null && split4.length > 1) {
                                        if (MessageEncoder.ATTR_TO.equals(split4[0])) {
                                            i2 = Integer.parseInt(split4[1]);
                                        }
                                        if ("callback".equals(split4[0])) {
                                            str4 = split4[1];
                                        }
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    str = str4;
                                    String str6 = str + "&&" + str + "({'package':'" + this.d + "','error_code':1,'error':'params error'})";
                                    f.a(th, "APSServiceCore", "invoke part2");
                                    PrintStream printStream = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                                    printStream.println("HTTP/1.0 200 OK");
                                    printStream.println("Content-Length:" + str6.getBytes("UTF-8").length);
                                    printStream.println();
                                    printStream.println(str6);
                                    printStream.close();
                                    bufferedReader.close();
                                    socket.close();
                                    return;
                                }
                            }
                            i = i2;
                            str = str4;
                        }
                        int i3 = f.c;
                        f.c = i;
                        long currentTimeMillis = System.currentTimeMillis();
                        if ((this.A == null || currentTimeMillis - this.A.getTime() > 5000) && !cx.d(this.a)) {
                            try {
                                this.A = this.h.a();
                                if (this.A.getErrorCode() != 0) {
                                    str3 = str + "&&" + str + "({'package':'" + this.d + "','error_code':" + this.A.getErrorCode() + ",'error':'" + this.A.getErrorInfo() + "'})";
                                }
                            } finally {
                                f.c = i3;
                            }
                        }
                        if (str2 == 0) {
                            if (this.A == null) {
                                str2 = str + "&&" + str + "({'package':'" + this.d + "','error_code':8,'error':'unknown error'})";
                            } else {
                                AMapLocationServer aMapLocationServer = this.A;
                                str2 = str + "&&" + str + "({'package':'" + this.d + "','error_code':0,'error':'','location':{'y':" + aMapLocationServer.getLatitude() + ",'precision':" + aMapLocationServer.getAccuracy() + ",'x':" + aMapLocationServer.getLongitude() + "},'version_code':'3.3.0','version':'3.3.0'})";
                            }
                            if (cx.d(this.a)) {
                                str2 = str + "&&" + str + "({'package':'" + this.d + "','error_code':36,'error':'app is background'})";
                            }
                        }
                        PrintStream printStream2 = new PrintStream(socket.getOutputStream(), true, "UTF-8");
                        printStream2.println("HTTP/1.0 200 OK");
                        printStream2.println("Content-Length:" + str2.getBytes("UTF-8").length);
                        printStream2.println();
                        printStream2.println(str2);
                        printStream2.close();
                        bufferedReader.close();
                        socket.close();
                    } catch (Throwable th4) {
                        th = th4;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    bufferedReader = null;
                }
            } catch (Throwable th6) {
                f.a(th6, "APSServiceCore", "invoke part5");
            }
        }
    }

    private void a(boolean z, Context context) {
        try {
            this.h.a(z, context);
        } catch (Throwable th) {
        }
    }

    private void b(Messenger messenger) {
        try {
            this.h.b(this.a);
            if (cq.q()) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("ngpsAble", cq.s());
                a(messenger, 7, bundle);
                cq.r();
            }
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "initAuth");
        }
    }

    static /* synthetic */ void b(e eVar) {
        try {
            if (eVar.C < cq.b()) {
                eVar.C++;
                eVar.h.b();
                eVar.r.sendEmptyMessageDelayed(4, 2000L);
            }
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "doGpsFusion");
        }
    }

    static /* synthetic */ void b(e eVar, Messenger messenger, Bundle bundle) {
        int i;
        AMapLocation a2;
        int i2 = 0;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    if (!eVar.F) {
                        eVar.A = a(9, eVar.G);
                        a(messenger, eVar.A.toStr(1), 0, false);
                        return;
                    }
                    String string = bundle.getString("extraJson");
                    double d = bundle.getDouble("e", 0.0d);
                    float f = bundle.getFloat("f", 0.0f);
                    eVar.a(string);
                    long b2 = cx.b();
                    if (eVar.D != null) {
                        if (eVar.c) {
                            eVar.D.a();
                        } else {
                            eVar.D.c();
                        }
                    }
                    if (eVar.A == null || eVar.A.getErrorCode() != 0 || b2 - eVar.z >= 600) {
                        ct ctVar = new ct();
                        ctVar.a(cx.b());
                        eVar.A = eVar.h.a();
                        if (eVar.A != null) {
                            i2 = eVar.A.getLocationType();
                        }
                        ctVar.a(eVar.A);
                        long j = 0;
                        if (eVar.A != null) {
                            j = eVar.A.getTime();
                        }
                        eVar.A = eVar.h.a(eVar.A, new String[0]);
                        eVar.A.setTime(j);
                        if (!eVar.c || eVar.D == null) {
                            eVar.A.setAltitude(0.0d);
                            eVar.A.setBearing(0.0f);
                            eVar.A.setSpeed(0.0f);
                            i = i2;
                        } else {
                            eVar.D.a(f);
                            eVar.A.setAltitude(eVar.D.a(d));
                            eVar.A.setBearing(eVar.D.d());
                            eVar.A.setSpeed((float) eVar.D.e());
                            i = i2;
                        }
                        ctVar.b(cx.b());
                        if (eVar.A != null && eVar.A.getErrorCode() == 0) {
                            eVar.z = cx.b();
                        }
                        if (eVar.A == null) {
                            eVar.A = a(8, "loc is null");
                        }
                        String str = null;
                        if (eVar.A != null) {
                            str = eVar.A.toStr(1);
                        }
                        eVar.f38u = false;
                        if (!(eVar.A == null || eVar.A.getErrorCode() == 0 || !eVar.t || eVar.E == null || eVar.A.getErrorCode() == 7 || (a2 = eVar.E.a(eVar.A.j())) == null)) {
                            eVar.f38u = true;
                            str = a2.toStr(1);
                        }
                        a(messenger, str, i, eVar.f38u);
                        cu.a(eVar.a, ctVar);
                        i2 = i;
                    } else {
                        a(messenger, eVar.A.toStr(1), 4, false);
                    }
                    eVar.b(messenger);
                    if (eVar.h.d()) {
                        eVar.a(messenger);
                    }
                    if (cq.a(eVar.B) && eVar.A != null && (i2 == 2 || i2 == 4)) {
                        eVar.B = cx.b();
                        eVar.h.b();
                    }
                    eVar.c();
                }
            } catch (Throwable th) {
                f.a(th, "APSServiceCore", "doLocation");
            }
        }
    }

    private void c() {
        try {
            if (this.h != null) {
                this.h.f();
            }
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "startColl");
        }
    }

    static /* synthetic */ void c(e eVar) {
        try {
            if (cq.e()) {
                eVar.h.b();
            } else if (!cx.d(eVar.a)) {
                eVar.h.b();
            }
            eVar.r.sendEmptyMessageDelayed(5, cq.d() * 1000);
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "doOffFusion");
        }
    }

    static /* synthetic */ void d(e eVar) {
        try {
            if (cq.a(eVar.a, eVar.x)) {
                eVar.x = cx.a();
                eVar.h.b();
            }
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "doNGps");
        }
    }

    static /* synthetic */ void e(e eVar) {
        try {
            if (eVar.h != null) {
                eVar.h.b(eVar.a);
            }
            if (eVar.r != null) {
                eVar.r.removeCallbacksAndMessages(null);
            }
            if (eVar.f != null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    cs.a(eVar.f, HandlerThread.class, "quitSafely", new Object[0]);
                } else {
                    eVar.f.quit();
                }
            }
            eVar.f = null;
            eVar.r = null;
            if (eVar.D != null) {
                eVar.D.f();
            }
            if (eVar.E != null) {
                eVar.E.b();
            }
            eVar.b();
            eVar.v = false;
            eVar.w = false;
            eVar.h.c();
            if (eVar.l != null) {
                long b2 = cx.b() - eVar.j;
                cu.a(eVar.a, eVar.l.c(eVar.a), eVar.l.d(eVar.a), eVar.k, b2);
                eVar.l.e(eVar.a);
            }
            cu.a(eVar.a);
            z.a();
            if (eVar.b) {
                Process.killProcess(Process.myPid());
            }
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "threadDestroy");
        }
    }

    public final void a() {
        try {
            a((Bundle) null);
            if (!this.o) {
                this.o = true;
                this.n = new ServerSocket(43689);
            }
            while (this.o) {
                this.p = this.n.accept();
                a(this.p);
            }
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "startSocket");
        }
    }

    final void a(Messenger messenger, Bundle bundle) {
        float f;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty() && cq.y()) {
                    double d = bundle.getDouble("lat");
                    double d2 = bundle.getDouble("lon");
                    if (this.g != null) {
                        f = cx.a(new double[]{d, d2, this.g.getLatitude(), this.g.getLongitude()});
                        if (f < cq.z() * 3) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putInt("lMaxGeoDis", cq.z() * 3);
                            bundle2.putInt("lMinGeoDis", cq.z());
                            bundle2.putString("locationJson", this.g.toStr(1));
                            a(messenger, 6, bundle2);
                        }
                    } else {
                        f = -1.0f;
                    }
                    if (f == -1.0f || f > cq.z()) {
                        a((Bundle) null);
                        this.g = this.h.a(d, d2);
                    }
                }
            } catch (Throwable th) {
                f.a(th, "APSServiceCore", "doLocationGeo");
            }
        }
    }

    public final void b() {
        try {
            if (this.n != null) {
                this.n.close();
            }
            if (this.p != null) {
                this.p.close();
            }
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "stopScocket");
        }
        this.n = null;
        this.q = false;
        this.o = false;
    }

    @Override // com.amap.api.location.APSServiceBase
    public IBinder onBind(Intent intent) {
        String stringExtra = intent.getStringExtra("a");
        if (!TextUtils.isEmpty(stringExtra)) {
            l.a(stringExtra);
        }
        String stringExtra2 = intent.getStringExtra("b");
        this.i = "true".equals(intent.getStringExtra("as"));
        if (this.i && this.r != null) {
            this.r.sendEmptyMessageDelayed(9, 100L);
        }
        k.a(stringExtra2);
        String stringExtra3 = intent.getStringExtra("c");
        String c = f.c(this.a);
        if (TextUtils.isEmpty(stringExtra3) || !stringExtra3.equals(c)) {
            return null;
        }
        this.e = new Messenger(this.r);
        return this.e.getBinder();
    }

    @Override // com.amap.api.location.APSServiceBase
    public void onCreate() {
        try {
            this.d = this.a.getApplicationContext().getPackageName();
            this.f = new b("amapLocCoreThread");
            this.f.setPriority(5);
            this.f.start();
            this.r = new a(this.f.getLooper());
            this.j = cx.b();
            this.k = cx.a();
            this.l = new cu();
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "onCreate");
        }
    }

    @Override // com.amap.api.location.APSServiceBase
    public void onDestroy() {
        try {
            if (this.r != null) {
                this.r.sendEmptyMessage(11);
            }
        } catch (Throwable th) {
            f.a(th, "APSServiceCore", "onDestroy");
        }
    }

    @Override // com.amap.api.location.APSServiceBase
    public int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}

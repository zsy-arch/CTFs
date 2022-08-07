package com.tencent.map.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import com.alipay.sdk.util.h;
import com.hyphenate.chat.MessageEncoder;
import com.tencent.map.b.b;
import com.tencent.map.b.d;
import com.tencent.map.b.e;
import com.tencent.map.b.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class f implements b.a, d.c, e.c, g.c {
    private static boolean t = false;

    /* renamed from: u  reason: collision with root package name */
    private static f f42u = null;
    private e c;
    private d d;
    private g e;
    private int k;
    private int l;
    private int m;
    private long a = 5000;
    private Context b = null;
    private int f = 1024;
    private int g = 4;
    private c h = null;
    private b i = null;
    private com.tencent.map.a.a.b j = null;
    private byte[] n = new byte[0];
    private byte[] o = new byte[0];
    private boolean p = false;
    private c q = null;
    private b r = null;
    private a s = null;
    private long v = -1;
    private e.b w = null;
    private d.b x = null;
    private g.b y = null;
    private com.tencent.map.a.a.d z = null;
    private com.tencent.map.a.a.d A = null;
    private int B = 0;
    private int C = 0;
    private int D = 1;
    private String E = "";
    private String F = "";
    private String G = "";
    private String H = "";
    private String I = "";
    private String J = "";
    private boolean K = false;
    private boolean L = false;
    private long M = 0;
    private Handler N = null;
    private Runnable O = new Runnable() { // from class: com.tencent.map.b.f.1
        @Override // java.lang.Runnable
        public final void run() {
            if (System.currentTimeMillis() - f.this.M >= 8000) {
                if (!f.this.e.b() || !f.this.e.c()) {
                    f.this.d();
                } else {
                    f.this.e.a(0L);
                }
            }
        }
    };
    private final BroadcastReceiver P = new BroadcastReceiver() { // from class: com.tencent.map.b.f.2
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (!intent.getBooleanExtra("noConnectivity", false) && f.this.q != null) {
                f.this.q.sendEmptyMessage(256);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class a extends Thread {
        private e.b a;
        private d.b b;
        private g.b c;

        a(e.b bVar, d.b bVar2, g.b bVar3) {
            this.a = null;
            this.b = null;
            this.c = null;
            if (bVar != null) {
                this.a = (e.b) bVar.clone();
            }
            if (bVar2 != null) {
                this.b = (d.b) bVar2.clone();
            }
            if (bVar3 != null) {
                this.c = (g.b) bVar3.clone();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            if (!f.t) {
                try {
                    TelephonyManager telephonyManager = (TelephonyManager) f.this.b.getSystemService("phone");
                    f.this.E = telephonyManager.getDeviceId();
                    f.this.F = telephonyManager.getSubscriberId();
                    f.this.G = telephonyManager.getLine1Number();
                    Pattern compile = Pattern.compile("[0-9a-zA-Z+-]*");
                    f.this.E = f.this.E == null ? "" : f.this.E;
                    if (compile.matcher(f.this.E).matches()) {
                        f.this.E = f.this.E == null ? "" : f.this.E;
                    } else {
                        f.this.E = "";
                    }
                    f.this.F = f.this.F == null ? "" : f.this.F;
                    if (compile.matcher(f.this.F).matches()) {
                        f.this.F = f.this.F == null ? "" : f.this.F;
                    } else {
                        f.this.F = "";
                    }
                    f.this.G = f.this.G == null ? "" : f.this.G;
                    if (compile.matcher(f.this.G).matches()) {
                        f.this.G = f.this.G == null ? "" : f.this.G;
                    } else {
                        f.this.G = "";
                    }
                } catch (Exception e) {
                }
                boolean unused = f.t = true;
                f.this.E = f.this.E == null ? "" : f.this.E;
                f.this.F = f.this.F == null ? "" : f.this.F;
                f.this.G = f.this.G == null ? "" : f.this.G;
                f.this.I = j.a(f.this.E == null ? "0123456789ABCDEF" : f.this.E);
            }
            f.this.q.sendMessage(f.this.q.obtainMessage(16, (("{\"version\":\"1.1.8\",\"address\":" + f.this.l) + ",\"source\":203,\"access_token\":\"" + f.this.I + "\",\"app_name\":\"" + f.this.J + "\",\"bearing\":1") + ",\"attribute\":" + i.a(f.this.E, f.this.F, f.this.G, f.this.H, f.this.K) + ",\"location\":" + ((this.a == null || !this.a.a()) ? "{}" : i.a(this.a)) + ",\"cells\":" + i.a(this.b, f.this.d.b()) + ",\"wifis\":" + (f.this.g == 4 ? i.a(this.c) : "[]") + h.d));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class b extends Thread {
        private String a;
        private String b;
        private String c = null;

        public b(String str) {
            this.a = null;
            this.b = null;
            this.a = str;
            this.b = (f.this.D == 0 ? "http://lstest.map.soso.com/loc?c=1" : "http://lbs.map.qq.com/loc?c=1") + "&mars=" + f.this.m;
        }

        private String a(byte[] bArr, String str) {
            f.this.M = System.currentTimeMillis();
            StringBuffer stringBuffer = new StringBuffer();
            try {
                stringBuffer.append(new String(bArr, str));
                return stringBuffer.toString();
            } catch (Exception e) {
                return null;
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Message message = new Message();
            message.what = 8;
            try {
                byte[] a = j.a(this.a.getBytes());
                f.this.p = true;
                n a2 = b.a(this.b, "SOSO MAP LBS SDK", a);
                f.this.p = false;
                this.c = a(j.b(a2.a), a2.b);
                if (this.c != null) {
                    message.arg1 = 0;
                    message.obj = this.c;
                } else {
                    message.arg1 = 1;
                }
            } catch (Exception e) {
                int i = 0;
                while (true) {
                    i++;
                    if (i > 3) {
                        break;
                    }
                    try {
                        sleep(1000L);
                        byte[] a3 = j.a(this.a.getBytes());
                        f.this.p = true;
                        n a4 = b.a(this.b, "SOSO MAP LBS SDK", a3);
                        f.this.p = false;
                        this.c = a(j.b(a4.a), a4.b);
                        if (this.c != null) {
                            message.arg1 = 0;
                            message.obj = this.c;
                        } else {
                            message.arg1 = 1;
                        }
                    } catch (Exception e2) {
                    }
                }
                f.this.p = false;
                message.arg1 = 1;
            }
            f.j(f.this);
            f.this.q.sendMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class c extends Handler {
        public c() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    f.a(f.this, (e.b) message.obj);
                    return;
                case 2:
                    f.a(f.this, (d.b) message.obj);
                    return;
                case 3:
                    f.a(f.this, (g.b) message.obj);
                    return;
                case 4:
                    f.a(f.this, message.arg1);
                    return;
                case 5:
                    f.b(f.this, message.arg1);
                    return;
                case 6:
                    f.a(f.this, (Location) message.obj);
                    return;
                case 8:
                    if (message.arg1 == 0) {
                        f.this.a((String) message.obj);
                        return;
                    } else if (f.this.w == null || !f.this.w.a()) {
                        f.this.e();
                        return;
                    } else {
                        return;
                    }
                case 16:
                    if (message.obj != null) {
                        f.a(f.this, (String) message.obj);
                        f.this.s = null;
                        return;
                    }
                    return;
                case 256:
                    if (f.this.B == 1) {
                        f.this.d();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private f() {
        this.c = null;
        this.d = null;
        this.e = null;
        this.c = new e();
        this.d = new d();
        this.e = new g();
    }

    public static synchronized f a() {
        f fVar;
        synchronized (f.class) {
            if (f42u == null) {
                f42u = new f();
            }
            fVar = f42u;
        }
        return fVar;
    }

    private static ArrayList<com.tencent.map.a.a.c> a(JSONArray jSONArray) throws Exception {
        int length = jSONArray.length();
        ArrayList<com.tencent.map.a.a.c> arrayList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            arrayList.add(new com.tencent.map.a.a.c(jSONObject.getString("name"), jSONObject.getString(MessageEncoder.ATTR_ADDRESS), jSONObject.getString("catalog"), jSONObject.getDouble("dist"), Double.parseDouble(jSONObject.getString("latitude")), Double.parseDouble(jSONObject.getString("longitude"))));
        }
        return arrayList;
    }

    static /* synthetic */ void a(f fVar, int i) {
        if (i == 0) {
            fVar.w = null;
        }
        fVar.f = i == 0 ? 1 : 2;
        if (fVar.j != null) {
            fVar.j.a(fVar.f);
        }
    }

    static /* synthetic */ void a(f fVar, Location location) {
        if (location == null || location.getLatitude() > 359.0d || location.getLongitude() > 359.0d) {
            if (fVar.w == null || !fVar.w.a()) {
                fVar.e();
            } else {
                fVar.b(true);
            }
        }
        fVar.z = new com.tencent.map.a.a.d();
        fVar.z.z = 0;
        fVar.z.y = 0;
        fVar.z.b = i.a(location.getLatitude(), 6);
        fVar.z.c = i.a(location.getLongitude(), 6);
        if (fVar.w != null && fVar.w.a()) {
            fVar.z.e = i.a(fVar.w.b().getAccuracy(), 1);
            fVar.z.d = i.a(fVar.w.b().getAltitude(), 1);
            fVar.z.f = i.a(fVar.w.b().getSpeed(), 1);
            fVar.z.g = i.a(fVar.w.b().getBearing(), 1);
            fVar.z.a = 0;
        }
        fVar.z.x = true;
        if (!(fVar.l == 0 || fVar.A == null || fVar.B != 0)) {
            if ((fVar.l == 3 || fVar.l == 4) && fVar.l == fVar.A.z) {
                fVar.z.i = fVar.A.i;
                fVar.z.j = fVar.A.j;
                fVar.z.k = fVar.A.k;
                fVar.z.l = fVar.A.l;
                fVar.z.m = fVar.A.m;
                fVar.z.n = fVar.A.n;
                fVar.z.o = fVar.A.o;
                fVar.z.p = fVar.A.p;
                fVar.z.z = 3;
            }
            if (fVar.l == 4 && fVar.l == fVar.A.z && fVar.A.w != null) {
                fVar.z.w = new ArrayList<>();
                Iterator<com.tencent.map.a.a.c> it = fVar.A.w.iterator();
                while (it.hasNext()) {
                    fVar.z.w.add(new com.tencent.map.a.a.c(it.next()));
                }
                fVar.z.z = 4;
            }
            if (fVar.l == 7 && fVar.l == fVar.A.z) {
                fVar.z.z = 7;
                fVar.z.h = fVar.A.h;
                fVar.z.i = fVar.A.i;
                if (fVar.A.h == 0) {
                    fVar.z.j = fVar.A.j;
                    fVar.z.k = fVar.A.k;
                    fVar.z.l = fVar.A.l;
                    fVar.z.m = fVar.A.m;
                    fVar.z.n = fVar.A.n;
                    fVar.z.o = fVar.A.o;
                    fVar.z.p = fVar.A.p;
                } else {
                    fVar.z.q = fVar.A.q;
                    fVar.z.r = fVar.A.r;
                    fVar.z.s = fVar.A.s;
                    fVar.z.t = fVar.A.t;
                    fVar.z.f41u = fVar.A.f41u;
                    fVar.z.v = fVar.A.v;
                }
            }
        }
        if (fVar.B != 0 || fVar.A != null) {
            if (fVar.B != 0) {
                fVar.z.y = fVar.B;
            }
            if (System.currentTimeMillis() - fVar.v >= fVar.a && fVar.j != null && fVar.k == 1) {
                fVar.j.a(fVar.z);
                fVar.v = System.currentTimeMillis();
            }
        }
    }

    static /* synthetic */ void a(f fVar, d.b bVar) {
        fVar.x = bVar;
        if (fVar.e == null || !fVar.e.b() || !fVar.e.c()) {
            if (fVar.C > 0 && !i.a(bVar.a, bVar.b, bVar.c, bVar.d, bVar.e)) {
                fVar.C--;
            }
            fVar.d();
            return;
        }
        fVar.e.a(0L);
    }

    static /* synthetic */ void a(f fVar, e.b bVar) {
        if (bVar != null) {
            fVar.w = bVar;
            if (fVar.k == 1 && fVar.w != null && fVar.w.a()) {
                if (fVar.m == 0) {
                    fVar.b(false);
                } else if (fVar.m == 1 && fVar.i != null) {
                    b bVar2 = fVar.i;
                    double latitude = fVar.w.b().getLatitude();
                    double longitude = fVar.w.b().getLongitude();
                    Context context = fVar.b;
                    bVar2.a(latitude, longitude, fVar);
                }
            }
        }
    }

    static /* synthetic */ void a(f fVar, g.b bVar) {
        if (bVar != null) {
            fVar.y = bVar;
            fVar.d();
        }
    }

    static /* synthetic */ void a(f fVar, String str) {
        byte[] bArr;
        if (!i.c(str)) {
            if (fVar.C > 0) {
                fVar.C--;
            } else if (fVar.k == 0 && fVar.j != null) {
                fVar.j.a(null, -1);
            } else if (fVar.k == 1 && fVar.j != null) {
                fVar.z = new com.tencent.map.a.a.d();
                fVar.B = 3;
                fVar.z.y = 3;
                fVar.z.z = -1;
                fVar.j.a(fVar.z);
            }
        } else if (fVar.k != 0 || fVar.j == null) {
            String b2 = fVar.h == null ? null : (fVar.x == null || fVar.y == null) ? null : fVar.h.b(fVar.x.b, fVar.x.c, fVar.x.d, fVar.x.e, fVar.y.a());
            if (b2 != null) {
                fVar.a(b2);
                return;
            }
            if (!(fVar.h == null || fVar.x == null || fVar.y == null)) {
                fVar.h.a(fVar.x.b, fVar.x.c, fVar.x.d, fVar.x.e, fVar.y.a());
            }
            if (!fVar.p) {
                if (fVar.r != null) {
                    fVar.r.interrupt();
                }
                fVar.r = null;
                fVar.r = new b(str);
                fVar.r.start();
            }
        } else {
            try {
                bArr = str.getBytes();
            } catch (Exception e) {
                bArr = null;
            }
            fVar.j.a(bArr, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        double d;
        int i = 0;
        try {
            this.z = new com.tencent.map.a.a.d();
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject("location");
            this.z.a = 1;
            this.z.b = i.a(jSONObject2.getDouble("latitude"), 6);
            this.z.c = i.a(jSONObject2.getDouble("longitude"), 6);
            this.z.d = i.a(jSONObject2.getDouble("altitude"), 1);
            this.z.e = i.a(jSONObject2.getDouble("accuracy"), 1);
            this.z.x = this.m == 1;
            String string = jSONObject.getString("bearing");
            int i2 = -100;
            if (string != null && string.split(",").length > 1) {
                i = Integer.parseInt(string.split(",")[1]);
            }
            if (this.x != null) {
                i2 = this.x.f;
            }
            com.tencent.map.a.a.d dVar = this.z;
            double d2 = this.z.e;
            if (i >= 6) {
                d = 40.0d;
            } else if (i == 5) {
                d = 60.0d;
            } else if (i == 4) {
                d = 70.0d;
            } else if (i == 3) {
                d = 90.0d;
            } else if (i == 2) {
                d = 110.0d;
            } else {
                d = (i2 < -72 || i != 0) ? d2 <= 100.0d ? ((int) (((d2 - 1.0d) / 10.0d) + 1.0d)) * 10 : (d2 <= 100.0d || d2 > 800.0d) ? ((int) ((0.8d * d2) / 10.0d)) * 10 : ((int) ((0.85d * d2) / 10.0d)) * 10 : ((int) ((0.45d * d2) / 10.0d)) * 10;
            }
            dVar.e = d;
            this.z.z = 0;
            if ((this.l == 3 || this.l == 4) && this.m == 1) {
                JSONObject jSONObject3 = jSONObject.getJSONObject("details").getJSONObject("subnation");
                this.z.a(jSONObject3.getString("name"));
                this.z.m = jSONObject3.getString("town");
                this.z.n = jSONObject3.getString("village");
                this.z.o = jSONObject3.getString("street");
                this.z.p = jSONObject3.getString("street_no");
                this.z.z = 3;
                this.z.h = 0;
            }
            if (this.l == 4 && this.m == 1) {
                this.z.w = a(jSONObject.getJSONObject("details").getJSONArray("poilist"));
                this.z.z = 4;
            }
            if (this.l == 7 && this.m == 1) {
                JSONObject jSONObject4 = jSONObject.getJSONObject("details");
                int i3 = jSONObject4.getInt("stat");
                JSONObject jSONObject5 = jSONObject4.getJSONObject("subnation");
                if (i3 == 0) {
                    this.z.a(jSONObject5.getString("name"));
                    this.z.m = jSONObject5.getString("town");
                    this.z.n = jSONObject5.getString("village");
                    this.z.o = jSONObject5.getString("street");
                    this.z.p = jSONObject5.getString("street_no");
                } else if (i3 == 1) {
                    this.z.i = jSONObject5.getString("nation");
                    this.z.q = jSONObject5.getString("admin_level_1");
                    this.z.r = jSONObject5.getString("admin_level_2");
                    this.z.s = jSONObject5.getString("admin_level_3");
                    this.z.t = jSONObject5.getString("locality");
                    this.z.f41u = jSONObject5.getString("sublocality");
                    this.z.v = jSONObject5.getString("route");
                }
                this.z.h = i3;
                this.z.z = 7;
            }
            this.z.y = 0;
            this.A = new com.tencent.map.a.a.d(this.z);
            this.B = 0;
            if (this.h != null) {
                this.h.a(str);
            }
        } catch (Exception e) {
            this.z = new com.tencent.map.a.a.d();
            this.z.z = -1;
            this.z.y = 2;
            this.B = 2;
        }
        if (this.j != null && this.k == 1) {
            if (this.w == null || !this.w.a()) {
                this.j.a(this.z);
                this.v = System.currentTimeMillis();
            }
        }
    }

    static /* synthetic */ void b(f fVar, int i) {
        int i2 = 3;
        if (i == 3) {
            i2 = 4;
        }
        fVar.g = i2;
        if (fVar.j != null) {
            fVar.j.a(fVar.g);
        }
    }

    private void b(boolean z) {
        if (this.w != null && this.w.a()) {
            Location b2 = this.w.b();
            this.z = new com.tencent.map.a.a.d();
            this.z.b = i.a(b2.getLatitude(), 6);
            this.z.c = i.a(b2.getLongitude(), 6);
            this.z.d = i.a(b2.getAltitude(), 1);
            this.z.e = i.a(b2.getAccuracy(), 1);
            this.z.f = i.a(b2.getSpeed(), 1);
            this.z.g = i.a(b2.getBearing(), 1);
            this.z.a = 0;
            this.z.x = false;
            if (!z) {
                this.z.y = 0;
            } else {
                this.z.y = 1;
            }
            this.z.z = 0;
            this.A = new com.tencent.map.a.a.d(this.z);
            this.B = 0;
            if (System.currentTimeMillis() - this.v >= this.a && this.j != null && this.k == 1) {
                this.j.a(this.z);
                this.v = System.currentTimeMillis();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.s == null) {
            this.s = new a(this.w, this.x, this.y);
            this.s.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.z = new com.tencent.map.a.a.d();
        this.B = 1;
        this.z.y = 1;
        this.z.z = -1;
        this.z.a = 1;
        if (this.j != null && this.k == 1) {
            this.j.a(this.z);
        }
    }

    static /* synthetic */ void j(f fVar) {
    }

    @Override // com.tencent.map.b.b.a
    public final void a(double d, double d2) {
        synchronized (this.o) {
            Message obtainMessage = this.q.obtainMessage(6);
            Location location = new Location("Deflect");
            location.setLatitude(d);
            location.setLongitude(d2);
            obtainMessage.obj = location;
            this.q.sendMessage(obtainMessage);
        }
    }

    @Override // com.tencent.map.b.e.c
    public final void a(int i) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(4, i, 0));
        }
    }

    @Override // com.tencent.map.b.d.c
    public final void a(d.b bVar) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(2, bVar));
        }
    }

    @Override // com.tencent.map.b.e.c
    public final void a(e.b bVar) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(1, bVar));
        }
    }

    @Override // com.tencent.map.b.g.c
    public final void a(g.b bVar) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(3, bVar));
        }
    }

    public final boolean a(Context context, com.tencent.map.a.a.b bVar) {
        synchronized (this.n) {
            if (context == null || bVar == null) {
                return false;
            }
            if (!i.a(this.J)) {
                return false;
            }
            this.q = new c();
            this.N = new Handler(Looper.getMainLooper());
            this.b = context;
            this.j = bVar;
            l.a().a(this.b.getApplicationContext());
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (!(connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null)) {
                    this.K = connectivityManager.getActiveNetworkInfo().isRoaming();
                }
                this.b.registerReceiver(this.P, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            } catch (Exception e) {
            }
            this.k = this.j.a();
            this.l = this.j.b();
            this.m = this.j.c();
            this.v = -1L;
            if (this.l == 7) {
                this.l = 0;
            }
            this.L = false;
            this.D = 1;
            boolean a2 = this.c.a(this, this.b);
            boolean a3 = this.d.a(this.b, this);
            boolean a4 = this.e.a(this.b, this, 1);
            this.h = c.a();
            this.i = b.a();
            this.w = null;
            this.x = null;
            this.y = null;
            this.z = null;
            this.A = null;
            this.B = 0;
            if (this.h != null) {
                this.h.b();
            }
            this.C = 1;
            if (!a2 || this.m != 0) {
                return a3 || a4;
            }
            return true;
        }
    }

    public final boolean a(String str, String str2) {
        boolean z;
        synchronized (this.n) {
            if (a.a().a(str, str2)) {
                this.J = str;
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final void b() {
        synchronized (this.n) {
            try {
                if (this.j != null) {
                    this.j = null;
                    this.N.removeCallbacks(this.O);
                    this.b.unregisterReceiver(this.P);
                    this.c.a();
                    this.d.a();
                    this.e.a();
                }
            } catch (Exception e) {
            }
        }
    }

    @Override // com.tencent.map.b.g.c
    public final void b(int i) {
        synchronized (this.o) {
            this.q.sendMessage(this.q.obtainMessage(5, i, 0));
        }
    }
}

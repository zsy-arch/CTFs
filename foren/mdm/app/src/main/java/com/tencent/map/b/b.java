package com.tencent.map.b;

import android.location.Location;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class b {
    private static b b;
    private a i;
    private double c = 0.0d;
    private double d = 0.0d;
    private double e = 0.0d;
    private double f = 0.0d;
    private double g = 0.0d;
    private double h = 0.0d;
    private C0080b j = null;
    private boolean k = false;
    public String a = "";

    /* loaded from: classes2.dex */
    public interface a {
        void a(double d, double d2);
    }

    /* renamed from: com.tencent.map.b.b$b */
    /* loaded from: classes2.dex */
    public class C0080b extends Thread {
        public C0080b() {
            b.this = r1;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                byte[] a = j.a(b.this.a.getBytes());
                b.this.k = true;
                n a2 = b.a("http://ls.map.soso.com/deflect?c=1", "SOSO MAP LBS SDK", a);
                b.this.k = false;
                b.a(b.this, j.b(a2.a), a2.b);
            } catch (Exception e) {
                int i = 0;
                while (true) {
                    i++;
                    if (i <= 3) {
                        try {
                            sleep(2000L);
                            n a3 = b.a("http://ls.map.soso.com/deflect?c=1", "SOSO MAP LBS SDK", j.a(b.this.a.getBytes()));
                            b.this.k = false;
                            b.a(b.this, j.b(a3.a), a3.b);
                            return;
                        } catch (Exception e2) {
                        }
                    } else {
                        b.this.k = false;
                        if (b.this.i != null) {
                            b.this.i.a(360.0d, 360.0d);
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }

    public static b a() {
        if (b == null) {
            b = new b();
        }
        return b;
    }

    static /* synthetic */ void a(b bVar, byte[] bArr, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(new String(bArr, str));
        } catch (Exception e) {
            if (bVar.i != null) {
                bVar.i.a(360.0d, 360.0d);
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(stringBuffer.toString()).getJSONObject("location");
            double d = jSONObject.getDouble("latitude");
            double d2 = jSONObject.getDouble("longitude");
            bVar.g = d - bVar.e;
            bVar.h = d2 - bVar.f;
            bVar.c = bVar.e;
            bVar.d = bVar.f;
            if (bVar.i != null) {
                bVar.i.a(d, d2);
            }
        } catch (JSONException e2) {
            if (bVar.i != null) {
                bVar.i.a(360.0d, 360.0d);
            }
        }
    }

    public final void a(double d, double d2, a aVar) {
        this.i = aVar;
        if (!(this.g == 0.0d || this.h == 0.0d)) {
            float[] fArr = new float[10];
            Location.distanceBetween(d, d2, this.c, this.d, fArr);
            if (fArr[0] < 1500.0f) {
                this.i.a(this.g + d, this.h + d2);
                return;
            }
        }
        if (!this.k) {
            this.a = "{\"source\":101,\"access_token\":\"160e7bd42dec9428721034e0146fc6dd\",\"location\":{\"latitude\":" + d + ",\"longitude\":" + d2 + "}\t}";
            this.e = d;
            this.f = d2;
            this.j = new C0080b();
            this.j.start();
        }
    }

    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static n a(String str, String str2, byte[] bArr) throws o, r, Exception {
        boolean z = true;
        z = false;
        if (l.b() == null) {
        }
        if (!z) {
            throw new o();
        }
        try {
            return q.a(false, str, str2, null, bArr, false, true);
        } catch (Exception e) {
            throw e;
        }
    }
}

package com.loc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.alipay.sdk.util.e;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.hyphenate.util.EMPrivateConstant;
import com.loc.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: ConnectionServiceManager.java */
/* loaded from: classes2.dex */
public class bv {
    private String b;
    private Context c;
    private boolean d = true;
    public boolean a = false;
    private i e = null;
    private ServiceConnection f = null;
    private ServiceConnection g = null;
    private ServiceConnection h = null;
    private Intent i = new Intent();
    private String j = "com.autonavi.minimap";
    private String k = "com.amap.api.service.AMapService";
    private String l = "com.autonavi.minimap.LBSConnectionService";
    private boolean m = false;
    private boolean n = false;
    private boolean o = false;
    private String p = "invaid type";
    private String q = "empty appkey";
    private String r = EMPrivateConstant.CONNECTION_REFUSED;
    private String s = e.b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bv(Context context) {
        this.b = null;
        this.c = null;
        this.c = context;
        try {
            this.b = o.a(ce.a(k.f(context).getBytes("UTF-8"), "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"));
        } catch (Throwable th) {
            f.a(th, "ConnectionServiceManager", "ConnectionServiceManager");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.autonavi.aps.amapapi.model.AMapLocationServer a(android.os.Bundle r9) {
        /*
            r8 = this;
            r1 = 0
            if (r9 != 0) goto L_0x0004
        L_0x0003:
            return r1
        L_0x0004:
            java.lang.String r0 = "key"
            boolean r0 = r9.containsKey(r0)
            if (r0 == 0) goto L_0x0088
            java.lang.String r0 = "key"
            java.lang.String r0 = r9.getString(r0)
            byte[] r0 = com.loc.o.b(r0)
            java.lang.String r2 = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDCEYwdO3V2ANrhApjqyk7X8FH5AEaWly58kP9IDAhMqwtIbmcJrUK9oO9Afh3KZnOlDtjiowy733YqpLRO7WBvdbW/c4Dz/d3dy/m+6HMqxaak+GQQRHw/VPdKciaZ3eIZp4MWOyIQwiFSQvPTAo/Na8hV4SgBZHB3lGFw0yu+BmG+h32eIE6p4Y8EDCn+G+yzekX+taMrWTQIysledrygZSGPv1ukbdFDnH/xZEI0dCr9pZT+AZQl3o9a2aMyuRrHM0oupXKKiYl69Y8fKh1Tyd752rF6LrR5uOb9aOfXt18hb+3YL5P9rQ+ZRYbyHYFaxzBPA2jLq0KUQ+Dmg7YhAgMBAAECggEAL9pj0lF3BUHwtssNKdf42QZJMD0BKuDcdZrLV9ifs0f54EJY5enzKw8j76MpdV8N5QVkNX4/BZR0bs9uJogh31oHFs5EXeWbb7V8P7bRrxpNnSAijGBWwscQsyqymf48YlcL28949ujnjoEz3jQjgWOyYnrCgpVhphrQbCGmB5TcZnTFvHfozt/0tzuMj5na5lRnkD0kYXgr0x/SRZcPoCybSpc3t/B/9MAAboGaV/QQkTotr7VOuJfaPRjvg8rzyPzavo3evxsjXj7vDXbN4w0cbk/Uqn2JtvPQ8HoysmF2HdYvILZibvJmWH1hA58b4sn5s6AqFRjMOL7rHdD+gQKBgQD+IzoofmZK5tTxgO9sWsG71IUeshQP9fe159jKCehk1RfuIqqbRP0UcxJiw4eNjHs4zU0HeRL3iF5XfUs0FQanO/pp6YL1xgVdfQlDdTdk6KFHJ0sUJapnJn1S2k7IKfRKE1+rkofSXMYUTsgHF1fDp+gxy4yUMY+h9O+JlKVKOwKBgQDDfaDIblaSm+B0lyG//wFPynAeGd0Q8wcMZbQQ/LWMJZhMZ7fyUZ+A6eL/jB53a2tgnaw2rXBpMe1qu8uSpym2plU0fkgLAnVugS5+KRhOkUHyorcbpVZbs5azf7GlTydR5dI1PHF3Bncemoa6IsEvumHWgQbVyTTz/O9mlFafUwKBgQCvDebms8KUf5JY1F6XfaCLWGVl8nZdVCmQFKbA7Lg2lI5KS3jHQWsupeEZRORffU/3nXsc1apZ9YY+r6CYvI77rRXd1KqPzxos/o7d96TzjkZhc9CEjTlmmh2jb5rqx/Ns/xFcZq/GGH+cx3ODZvHeZQ9NFY+9GLJ+dfB2DX0ZtwKBgQC+9/lZ8telbpqMqpqwqRaJ8LMn5JIdHZu0E6IcuhFLr+ogMW3zTKMpVtGGXEXi2M/TWRPDchiO2tQX4Q5T2/KW19QCbJ5KCwPWiGF3owN4tNOciDGh0xkSidRc0xAh8bnyejSoBry8zlcNUVztdkgMLOGonvCjZWPSOTNQnPYluwKBgCV+WVftpTk3l+OfAJTaXEPNYdh7+WQjzxZKjUaDzx80Ts7hRo2U+EQT7FBjQQNqmmDnWtujo5p1YmJC0FT3n1CVa7g901pb3b0RcOziYWAoJi0/+kLyeo6XBhuLeZ7h90S70GGh1o0V/j/9N1jb5DCL4xKkvdYePPTSTku0BM+n"
            byte[] r0 = com.loc.ce.b(r0, r2)     // Catch: Throwable -> 0x0080
        L_0x001c:
            java.lang.String r2 = "result"
            boolean r2 = r9.containsKey(r2)
            if (r2 == 0) goto L_0x0003
            java.lang.String r2 = "result"
            java.lang.String r2 = r9.getString(r2)
            byte[] r2 = com.loc.o.b(r2)
            byte[] r0 = com.loc.ce.a(r0, r2)     // Catch: Throwable -> 0x0073
            java.lang.String r2 = new java.lang.String     // Catch: Throwable -> 0x0073
            java.lang.String r3 = "utf-8"
            r2.<init>(r0, r3)     // Catch: Throwable -> 0x0073
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: Throwable -> 0x0073
            r3.<init>(r2)     // Catch: Throwable -> 0x0073
            java.lang.String r0 = "error"
            boolean r0 = r3.has(r0)     // Catch: Throwable -> 0x0073
            if (r0 == 0) goto L_0x008a
            java.lang.String r0 = "error"
            java.lang.String r0 = r3.getString(r0)     // Catch: Throwable -> 0x0073
            java.lang.String r2 = r8.p     // Catch: Throwable -> 0x0073
            boolean r2 = r2.equals(r0)     // Catch: Throwable -> 0x0073
            if (r2 == 0) goto L_0x0057
            r2 = 0
            r8.d = r2     // Catch: Throwable -> 0x0073
        L_0x0057:
            java.lang.String r2 = r8.q     // Catch: Throwable -> 0x0073
            boolean r2 = r2.equals(r0)     // Catch: Throwable -> 0x0073
            if (r2 == 0) goto L_0x0062
            r2 = 0
            r8.d = r2     // Catch: Throwable -> 0x0073
        L_0x0062:
            java.lang.String r2 = r8.r     // Catch: Throwable -> 0x0073
            boolean r2 = r2.equals(r0)     // Catch: Throwable -> 0x0073
            if (r2 == 0) goto L_0x006d
            r2 = 0
            r8.d = r2     // Catch: Throwable -> 0x0073
        L_0x006d:
            java.lang.String r2 = r8.s     // Catch: Throwable -> 0x0073
            r2.equals(r0)     // Catch: Throwable -> 0x0073
            goto L_0x0003
        L_0x0073:
            r0 = move-exception
            java.lang.Class<com.loc.bv> r2 = com.loc.bv.class
            java.lang.String r2 = r2.getName()
            java.lang.String r3 = "parseData"
            com.loc.f.a(r0, r2, r3)
            goto L_0x0003
        L_0x0080:
            r0 = move-exception
            java.lang.String r2 = "ConnectionServiceManager"
            java.lang.String r3 = "parseData part"
            com.loc.f.a(r0, r2, r3)
        L_0x0088:
            r0 = r1
            goto L_0x001c
        L_0x008a:
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = new com.autonavi.aps.amapapi.model.AMapLocationServer     // Catch: Throwable -> 0x0073
            java.lang.String r2 = ""
            r0.<init>(r2)     // Catch: Throwable -> 0x0073
            r0.b(r3)     // Catch: Throwable -> 0x0073
            java.lang.String r2 = "lbs"
            r0.setProvider(r2)     // Catch: Throwable -> 0x0073
            r2 = 7
            r0.setLocationType(r2)     // Catch: Throwable -> 0x0073
            java.lang.String r2 = "WGS84"
            java.lang.String r3 = r0.d()     // Catch: Throwable -> 0x0073
            boolean r2 = r2.equals(r3)     // Catch: Throwable -> 0x0073
            if (r2 == 0) goto L_0x00d3
            double r2 = r0.getLatitude()     // Catch: Throwable -> 0x0073
            double r4 = r0.getLongitude()     // Catch: Throwable -> 0x0073
            boolean r2 = com.loc.f.a(r2, r4)     // Catch: Throwable -> 0x0073
            if (r2 == 0) goto L_0x00d3
            android.content.Context r2 = r8.c     // Catch: Throwable -> 0x0073
            double r4 = r0.getLongitude()     // Catch: Throwable -> 0x0073
            double r6 = r0.getLatitude()     // Catch: Throwable -> 0x0073
            com.amap.api.location.DPoint r2 = com.amap.api.location.a.a(r2, r4, r6)     // Catch: Throwable -> 0x0073
            double r4 = r2.getLatitude()     // Catch: Throwable -> 0x0073
            r0.setLatitude(r4)     // Catch: Throwable -> 0x0073
            double r2 = r2.getLongitude()     // Catch: Throwable -> 0x0073
            r0.setLongitude(r2)     // Catch: Throwable -> 0x0073
        L_0x00d3:
            r1 = r0
            goto L_0x0003
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bv.a(android.os.Bundle):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    public final void a() {
        try {
            if (this.m) {
                this.c.unbindService(this.f);
            }
            if (this.n) {
                this.c.unbindService(this.g);
            }
            if (this.o) {
                this.c.unbindService(this.h);
            }
        } catch (Throwable th) {
            f.a(th, "ConnectionServiceManager", "unbindService");
        }
        this.e = null;
        this.c = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.d = true;
        this.a = false;
        this.m = false;
        this.n = false;
        this.o = false;
    }

    public final void b() {
        try {
            if (this.f == null) {
                this.f = new ServiceConnection() { // from class: com.loc.bv.1
                    @Override // android.content.ServiceConnection
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        bv.this.a = true;
                        bv.this.e = i.a.a(iBinder);
                    }

                    @Override // android.content.ServiceConnection
                    public final void onServiceDisconnected(ComponentName componentName) {
                        bv.this.a = false;
                        bv.this.e = null;
                    }
                };
            }
            if (this.g == null) {
                this.g = new ServiceConnection() { // from class: com.loc.bv.2
                    @Override // android.content.ServiceConnection
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    }

                    @Override // android.content.ServiceConnection
                    public final void onServiceDisconnected(ComponentName componentName) {
                    }
                };
            }
            if (this.h == null) {
                this.h = new ServiceConnection() { // from class: com.loc.bv.3
                    @Override // android.content.ServiceConnection
                    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    }

                    @Override // android.content.ServiceConnection
                    public final void onServiceDisconnected(ComponentName componentName) {
                    }
                };
            }
        } catch (Throwable th) {
            f.a(th, "ConnectionServiceManager", "init");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean c() {
        ArrayList<String> n;
        ArrayList<String> m;
        try {
            if (cq.b(this.c)) {
                this.i.putExtra("appkey", this.b);
                this.i.setComponent(new ComponentName(this.j, this.k));
                try {
                    this.m = this.c.bindService(this.i, this.f, 1);
                } catch (Throwable th) {
                }
                if (!this.m && (m = cq.m()) != null) {
                    Iterator<String> it = m.iterator();
                    while (it.hasNext()) {
                        String next = it.next();
                        if (!next.equals(this.k)) {
                            this.i.setComponent(new ComponentName(this.j, next));
                            try {
                                this.m = this.c.bindService(this.i, this.f, 1);
                            } catch (Throwable th2) {
                            }
                            if (this.m) {
                                break;
                            }
                        }
                    }
                }
            }
            if (cq.c(this.c)) {
                Intent intent = new Intent();
                intent.putExtra("appkey", this.b);
                intent.setComponent(new ComponentName(this.j, this.l));
                try {
                    this.n = this.c.bindService(intent, this.g, 1);
                } catch (Throwable th3) {
                }
                if (!this.n && (n = cq.n()) != null) {
                    Iterator<String> it2 = n.iterator();
                    while (it2.hasNext()) {
                        String next2 = it2.next();
                        if (!next2.equals(this.l)) {
                            intent.setComponent(new ComponentName(this.j, next2));
                            try {
                                this.n = this.c.bindService(intent, this.g, 1);
                            } catch (Throwable th4) {
                            }
                            if (this.m) {
                                break;
                            }
                        }
                    }
                }
            }
            d();
            if (this.m) {
                if (this.n) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th5) {
            return false;
        }
    }

    public final void d() {
        List<cr> x;
        try {
            if (cq.g(this.c) && (x = cq.x()) != null && x.size() > 0) {
                for (cr crVar : x) {
                    if (crVar != null && crVar.a()) {
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName(crVar.b(), crVar.c()));
                        if (!TextUtils.isEmpty(crVar.e())) {
                            intent.setAction(crVar.e());
                        }
                        List<Map<String, String>> d = crVar.d();
                        if (d != null && d.size() > 0) {
                            for (int i = 0; i < d.size(); i++) {
                                Iterator<Map.Entry<String, String>> it = d.get(i).entrySet().iterator();
                                if (it.hasNext()) {
                                    Map.Entry<String, String> next = it.next();
                                    intent.putExtra(next.getKey().toString(), next.getValue().toString());
                                }
                            }
                        }
                        if (crVar.f()) {
                            this.c.startService(intent);
                        }
                        intent.putExtra("c", f.c(this.c));
                        boolean bindService = this.c.bindService(intent, this.h, 1);
                        if (bindService) {
                            this.o = bindService;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            f.a(th, "ConnectionServiceManager", "bindOtherService");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final AMapLocationServer e() {
        try {
            if (!this.d || !this.m) {
                return null;
            }
            Bundle bundle = new Bundle();
            bundle.putString("type", "corse");
            bundle.putString("appkey", this.b);
            if (this.e == null) {
                return null;
            }
            this.e.a(bundle);
            if (bundle.size() > 0) {
                return a(bundle);
            }
            return null;
        } catch (Throwable th) {
            f.a(th, "ConnectionServiceManager", "sendCommand");
            return null;
        }
    }
}

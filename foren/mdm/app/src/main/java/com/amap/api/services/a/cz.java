package com.amap.api.services.a;

import android.text.TextUtils;
import java.net.Proxy;
import java.util.Map;

/* compiled from: Request.java */
/* loaded from: classes.dex */
public abstract class cz {
    int e = 20000;
    int f = 20000;
    Proxy g = null;

    public abstract Map<String, String> b();

    public abstract Map<String, String> c();

    public abstract String g();

    public String k() {
        byte[] f = f();
        if (f == null || f.length == 0) {
            return g();
        }
        Map<String, String> b = b();
        if (b == null) {
            return g();
        }
        String a = cw.a(b);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(g()).append("?").append(a);
        return stringBuffer.toString();
    }

    public byte[] l() {
        byte[] f = f();
        if (f != null && f.length != 0) {
            return f;
        }
        String a = cw.a(b());
        if (!TextUtils.isEmpty(a)) {
            return bf.a(a);
        }
        return f;
    }

    public final void a(int i) {
        this.e = i;
    }

    public final void b(int i) {
        this.f = i;
    }

    public byte[] f() {
        return null;
    }

    public final void a(Proxy proxy) {
        this.g = proxy;
    }
}

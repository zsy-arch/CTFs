package com.amap.api.col;

import android.text.TextUtils;
import java.net.Proxy;
import java.util.Map;

/* compiled from: Request.java */
/* loaded from: classes.dex */
public abstract class ig {
    int f = 20000;
    int g = 20000;
    Proxy h = null;

    public abstract Map<String, String> a();

    public abstract Map<String, String> b();

    public abstract String c();

    public String m() {
        byte[] g = g();
        if (g == null || g.length == 0) {
            return c();
        }
        Map<String, String> b = b();
        if (b == null) {
            return c();
        }
        String a = id.a(b);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(c()).append("?").append(a);
        return stringBuffer.toString();
    }

    public byte[] n() {
        byte[] g = g();
        if (g != null && g.length != 0) {
            return g;
        }
        String a = id.a(b());
        if (!TextUtils.isEmpty(a)) {
            return gk.a(a);
        }
        return g;
    }

    public final void a(int i) {
        this.f = i;
    }

    public final void b(int i) {
        this.g = i;
    }

    public byte[] g() {
        return null;
    }

    public final void a(Proxy proxy) {
        this.h = proxy;
    }
}

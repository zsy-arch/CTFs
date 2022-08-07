package com.loc;

import android.content.Context;
import android.net.Proxy;
import android.os.Build;
import com.alimama.mobile.csdk.umupdate.a.f;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

/* compiled from: ProxyUtil.java */
/* loaded from: classes2.dex */
public final class q {
    private static String a() {
        String str;
        try {
            str = Proxy.getDefaultHost();
        } catch (Throwable th) {
            w.a(th, "ProxyUtil", "getDefHost");
            str = null;
        }
        return str == null ? f.b : str;
    }

    public static java.net.Proxy a(Context context) {
        java.net.Proxy proxy = null;
        try {
            proxy = Build.VERSION.SDK_INT >= 11 ? a(context, new URI("http://restapi.amap.com")) : b(context);
        } catch (Throwable th) {
            w.a(th, "ProxyUtil", "getProxy");
        }
        return proxy;
    }

    private static java.net.Proxy a(Context context, URI uri) {
        if (c(context)) {
            try {
                List<java.net.Proxy> select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty()) {
                    return null;
                }
                java.net.Proxy proxy = select.get(0);
                if (proxy != null) {
                    if (proxy.type() != Proxy.Type.DIRECT) {
                        return proxy;
                    }
                }
                return null;
            } catch (Throwable th) {
                w.a(th, "ProxyUtil", "getProxySelectorCfg");
            }
        }
        return null;
    }

    private static int b() {
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            w.a(th, "ProxyUtil", "getDefPort");
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x015d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0100 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0147 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0077 A[Catch: Throwable -> 0x0170, TRY_LEAVE, TryCatch #12 {Throwable -> 0x0170, blocks: (B:29:0x006c, B:34:0x0077), top: B:135:0x006c }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00cd A[Catch: all -> 0x0180, TryCatch #0 {all -> 0x0180, blocks: (B:53:0x00c0, B:55:0x00cd, B:57:0x00e3, B:59:0x00e9, B:63:0x00f5, B:69:0x0106, B:71:0x010e, B:73:0x0114, B:77:0x0120), top: B:128:0x00c0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.net.Proxy b(android.content.Context r12) {
        /*
            Method dump skipped, instructions count: 466
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.q.b(android.content.Context):java.net.Proxy");
    }

    private static boolean c(Context context) {
        return n.m(context) == 0;
    }
}

package com.amap.api.col;

import android.content.Context;
import android.os.Build;
import com.alimama.mobile.csdk.umupdate.a.f;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

/* compiled from: ProxyUtil.java */
/* loaded from: classes.dex */
public class gh {
    public static Proxy a(Context context) {
        Proxy proxy = null;
        try {
            if (Build.VERSION.SDK_INT >= 11) {
                proxy = a(context, new URI("http://restapi.amap.com"));
            } else {
                proxy = b(context);
            }
        } catch (Throwable th) {
            go.a(th, "ProxyUtil", "getProxy");
        }
        return proxy;
    }

    /* JADX WARN: Removed duplicated region for block: B:118:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00f9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x013c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0067 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0152 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0070 A[Catch: Throwable -> 0x0162, TRY_LEAVE, TryCatch #6 {Throwable -> 0x0162, blocks: (B:28:0x006a, B:30:0x0070), top: B:126:0x006a }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00c6 A[Catch: all -> 0x0172, TryCatch #12 {all -> 0x0172, blocks: (B:49:0x00b9, B:51:0x00c6, B:53:0x00dc, B:55:0x00e2, B:59:0x00ee, B:66:0x010b, B:68:0x0113, B:70:0x0119, B:74:0x0124), top: B:131:0x00b9 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.net.Proxy b(android.content.Context r12) {
        /*
            Method dump skipped, instructions count: 447
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.gh.b(android.content.Context):java.net.Proxy");
    }

    public static String a(String str) {
        return gk.c(str);
    }

    private static boolean a(String str, int i) {
        return (str == null || str.length() <= 0 || i == -1) ? false : true;
    }

    private static String a() {
        String str;
        try {
            str = android.net.Proxy.getDefaultHost();
        } catch (Throwable th) {
            go.a(th, "ProxyUtil", "getDefHost");
            str = null;
        }
        if (str == null) {
            return f.b;
        }
        return str;
    }

    private static Proxy a(Context context, URI uri) {
        if (c(context)) {
            try {
                List<Proxy> select = ProxySelector.getDefault().select(uri);
                if (select == null || select.isEmpty()) {
                    return null;
                }
                Proxy proxy = select.get(0);
                if (proxy != null) {
                    if (proxy.type() != Proxy.Type.DIRECT) {
                        return proxy;
                    }
                }
                return null;
            } catch (Throwable th) {
                go.a(th, "ProxyUtil", "getProxySelectorCfg");
            }
        }
        return null;
    }

    private static boolean c(Context context) {
        return ge.m(context) == 0;
    }

    private static int b() {
        try {
            return android.net.Proxy.getDefaultPort();
        } catch (Throwable th) {
            go.a(th, "ProxyUtil", "getDefPort");
            return -1;
        }
    }
}

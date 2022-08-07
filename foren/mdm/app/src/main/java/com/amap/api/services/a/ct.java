package com.amap.api.services.a;

import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: BaseNetManager.java */
/* loaded from: classes.dex */
public class ct {
    private static ct a;

    /* compiled from: BaseNetManager.java */
    /* loaded from: classes.dex */
    public interface a {
        URLConnection a(Proxy proxy, URL url);
    }

    public static ct a() {
        if (a == null) {
            a = new ct();
        }
        return a;
    }

    public byte[] a(cz czVar) throws av {
        try {
            db a2 = a(czVar, true);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (av e) {
            throw e;
        } catch (Throwable th) {
            throw new av("未知的错误");
        }
    }

    public byte[] b(cz czVar) throws av {
        try {
            db a2 = a(czVar, false);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (av e) {
            throw e;
        } catch (Throwable th) {
            bh.a(th, "BaseNetManager", "makeSyncPostRequest");
            throw new av("未知的错误");
        }
    }

    protected void c(cz czVar) throws av {
        if (czVar == null) {
            throw new av("requeust is null");
        } else if (czVar.g() == null || "".equals(czVar.g())) {
            throw new av("request url is empty");
        }
    }

    public db a(cz czVar, boolean z) throws av {
        Proxy proxy;
        try {
            c(czVar);
            if (czVar.g == null) {
                proxy = null;
            } else {
                proxy = czVar.g;
            }
            return new cw(czVar.e, czVar.f, proxy, z).a(czVar.k(), czVar.c(), czVar.l());
        } catch (av e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new av("未知的错误");
        }
    }
}

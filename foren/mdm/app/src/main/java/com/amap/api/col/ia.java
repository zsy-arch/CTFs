package com.amap.api.col;

import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: BaseNetManager.java */
/* loaded from: classes.dex */
public class ia {
    private static ia a;

    /* compiled from: BaseNetManager.java */
    /* loaded from: classes.dex */
    public interface a {
        URLConnection a(Proxy proxy, URL url);
    }

    public static ia a() {
        if (a == null) {
            a = new ia();
        }
        return a;
    }

    public byte[] a(ig igVar) throws fz {
        try {
            ii a2 = a(igVar, true);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (fz e) {
            throw e;
        } catch (Throwable th) {
            throw new fz("未知的错误");
        }
    }

    public byte[] b(ig igVar) throws fz {
        try {
            ii a2 = a(igVar, false);
            if (a2 != null) {
                return a2.a;
            }
            return null;
        } catch (fz e) {
            throw e;
        } catch (Throwable th) {
            go.a(th, "BaseNetManager", "makeSyncPostRequest");
            throw new fz("未知的错误");
        }
    }

    public ii c(ig igVar) throws fz {
        try {
            ii a2 = a(igVar, false);
            if (a2 != null) {
                return a2;
            }
            return null;
        } catch (fz e) {
            throw e;
        } catch (Throwable th) {
            go.a(th, "BaseNetManager", "makeSyncPostRequest");
            throw new fz("未知的错误");
        }
    }

    protected void d(ig igVar) throws fz {
        if (igVar == null) {
            throw new fz("requeust is null");
        } else if (igVar.c() == null || "".equals(igVar.c())) {
            throw new fz("request url is empty");
        }
    }

    public ii a(ig igVar, boolean z) throws fz {
        Proxy proxy;
        try {
            d(igVar);
            if (igVar.h == null) {
                proxy = null;
            } else {
                proxy = igVar.h;
            }
            return new id(igVar.f, igVar.g, proxy, z).a(igVar.m(), igVar.a(), igVar.n());
        } catch (fz e) {
            throw e;
        } catch (Throwable th) {
            th.printStackTrace();
            throw new fz("未知的错误");
        }
    }
}

package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.ServiceSettings;
import java.util.Map;

/* compiled from: BasicHandler.java */
/* loaded from: classes.dex */
public abstract class a<T, V> extends cz {
    protected T a;
    protected Context d;
    protected int b = 1;
    protected String c = "";
    private int h = 1;

    protected abstract V a(String str) throws AMapException;

    public a(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.d = context;
        this.a = t;
        this.b = 1;
        b(ServiceSettings.getInstance().getSoTimeOut());
        a(ServiceSettings.getInstance().getConnectionTimeOut());
    }

    protected V a(byte[] bArr) throws AMapException {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e) {
            i.a(e, "ProtocalHandler", "loadData");
            str = null;
        }
        if (str == null || str.equals("")) {
            return null;
        }
        i.b(str);
        return a(str);
    }

    public V a() throws AMapException {
        if (this.a != null) {
            return e();
        }
        return null;
    }

    private V e() throws AMapException {
        int i = 0;
        V v = null;
        while (i < this.b) {
            try {
                int protocol = ServiceSettings.getInstance().getProtocol();
                cy a = cy.a(false);
                a(bd.a(this.d));
                v = b(a(protocol, a, this));
                i = this.b;
            } catch (av e) {
                i++;
                if (i < this.b) {
                    try {
                        Thread.sleep(this.h * 1000);
                    } catch (InterruptedException e2) {
                        if (com.amap.api.maps.AMapException.ERROR_CONNECTION.equals(e.getMessage()) || com.amap.api.maps.AMapException.ERROR_SOCKET.equals(e.getMessage()) || com.amap.api.maps.AMapException.ERROR_UNKNOW_SERVICE.equals(e.getMessage())) {
                            throw new AMapException(AMapException.AMAP_CLIENT_NETWORK_EXCEPTION);
                        }
                        throw new AMapException(e.a());
                    }
                } else {
                    d();
                    if (com.amap.api.maps.AMapException.ERROR_CONNECTION.equals(e.getMessage()) || com.amap.api.maps.AMapException.ERROR_SOCKET.equals(e.getMessage()) || "未知的错误".equals(e.a()) || com.amap.api.maps.AMapException.ERROR_UNKNOW_SERVICE.equals(e.getMessage())) {
                        throw new AMapException(AMapException.AMAP_CLIENT_NETWORK_EXCEPTION);
                    }
                    throw new AMapException(e.a());
                }
            } catch (AMapException e3) {
                i++;
                if (i >= this.b) {
                    throw new AMapException(e3.getErrorMessage());
                }
            } catch (Throwable th) {
                throw new AMapException(AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
            }
        }
        return v;
    }

    protected byte[] a(int i, cy cyVar, cz czVar) throws av {
        if (i == 1) {
            return cyVar.b(czVar);
        }
        if (i == 2) {
            return cyVar.a(czVar);
        }
        return null;
    }

    @Override // com.amap.api.services.a.cz
    public Map<String, String> b() {
        return null;
    }

    @Override // com.amap.api.services.a.cz
    public Map<String, String> c() {
        return null;
    }

    private V b(byte[] bArr) throws AMapException {
        return a(bArr);
    }

    protected V d() {
        return null;
    }
}

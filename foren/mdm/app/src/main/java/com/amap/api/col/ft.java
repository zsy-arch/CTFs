package com.amap.api.col;

import android.content.Context;
import com.amap.api.maps.AMapException;
import java.util.Map;

/* compiled from: BasicHandler.java */
/* loaded from: classes.dex */
public abstract class ft<T, V> extends ig {
    protected T a;
    protected Context d;
    protected String e;
    protected int b = 1;
    protected String c = "";
    private int i = 1;

    protected abstract V a(String str) throws fs;

    public ft(Context context, T t) {
        a(context, t);
    }

    private void a(Context context, T t) {
        this.d = context;
        this.a = t;
        this.b = 1;
        b(30000);
        a(30000);
    }

    protected V a(byte[] bArr) throws fs {
        String str;
        try {
            str = new String(bArr, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        if (str == null || str.equals("")) {
            return null;
        }
        fv.a(str, this.e);
        return a(str);
    }

    public V d() throws fs {
        if (this.a != null) {
            return f();
        }
        return null;
    }

    private V f() throws fs {
        int i = 0;
        V v = null;
        while (i < this.b) {
            try {
                Cif a = Cif.a(false);
                a(gh.a(this.d));
                v = b(a(1, a, this));
                i = this.b;
            } catch (fs e) {
                i++;
                if (i >= this.b) {
                    throw new fs(e.a());
                }
            } catch (fz e2) {
                i++;
                if (i < this.b) {
                    try {
                        Thread.sleep(this.i * 1000);
                    } catch (InterruptedException e3) {
                        if (AMapException.ERROR_CONNECTION.equals(e2.getMessage()) || AMapException.ERROR_SOCKET.equals(e2.getMessage()) || AMapException.ERROR_UNKNOW_SERVICE.equals(e2.getMessage())) {
                            throw new fs(com.amap.api.services.core.AMapException.AMAP_CLIENT_NETWORK_EXCEPTION);
                        }
                        throw new fs(e2.a());
                    }
                } else {
                    e();
                    if (AMapException.ERROR_CONNECTION.equals(e2.getMessage()) || AMapException.ERROR_SOCKET.equals(e2.getMessage()) || "未知的错误".equals(e2.a()) || AMapException.ERROR_UNKNOW_SERVICE.equals(e2.getMessage())) {
                        throw new fs(com.amap.api.services.core.AMapException.AMAP_CLIENT_NETWORK_EXCEPTION);
                    }
                    throw new fs(e2.a());
                }
            } catch (Throwable th) {
                throw new fs(com.amap.api.services.core.AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
            }
        }
        return v;
    }

    protected byte[] a(int i, Cif ifVar, ig igVar) throws fz {
        if (i == 1) {
            return ifVar.b(igVar);
        }
        if (i == 2) {
            return ifVar.a(igVar);
        }
        return null;
    }

    @Override // com.amap.api.col.ig
    public Map<String, String> b() {
        return null;
    }

    @Override // com.amap.api.col.ig
    public Map<String, String> a() {
        return null;
    }

    private V b(byte[] bArr) throws fs {
        return a(bArr);
    }

    protected V e() {
        return null;
    }
}

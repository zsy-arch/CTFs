package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.a.q;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IRoutePOISearch;
import com.amap.api.services.routepoisearch.RoutePOISearch;
import com.amap.api.services.routepoisearch.RoutePOISearchQuery;
import com.amap.api.services.routepoisearch.RoutePOISearchResult;

/* compiled from: RoutePOISearchCore.java */
/* loaded from: classes.dex */
public class ar implements IRoutePOISearch {
    private RoutePOISearchQuery a;
    private Context b;
    private RoutePOISearch.OnRoutePOISearchListener c;
    private Handler d;

    public ar(Context context, RoutePOISearchQuery routePOISearchQuery) {
        this.d = null;
        this.b = context;
        this.a = routePOISearchQuery;
        this.d = q.a();
    }

    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public void setRoutePOISearchListener(RoutePOISearch.OnRoutePOISearchListener onRoutePOISearchListener) {
        this.c = onRoutePOISearchListener;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.amap.api.services.a.ar$1] */
    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public void searchRoutePOIAsyn() {
        new Thread() { // from class: com.amap.api.services.a.ar.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                RoutePOISearchResult routePOISearchResult;
                Message obtainMessage;
                Bundle bundle;
                try {
                    obtainMessage = ar.this.d.obtainMessage();
                    obtainMessage.arg1 = 14;
                    bundle = new Bundle();
                    routePOISearchResult = null;
                    try {
                        routePOISearchResult = ar.this.searchRoutePOI();
                        bundle.putInt(MyLocationStyle.ERROR_CODE, 1000);
                        q.j jVar = new q.j();
                        jVar.b = ar.this.c;
                        jVar.a = routePOISearchResult;
                        obtainMessage.obj = jVar;
                        obtainMessage.setData(bundle);
                        ar.this.d.sendMessage(obtainMessage);
                    } catch (AMapException e) {
                        bundle.putInt(MyLocationStyle.ERROR_CODE, e.getErrorCode());
                        q.j jVar2 = new q.j();
                        jVar2.b = ar.this.c;
                        jVar2.a = routePOISearchResult;
                        obtainMessage.obj = jVar2;
                        obtainMessage.setData(bundle);
                        ar.this.d.sendMessage(obtainMessage);
                    }
                } catch (Throwable th) {
                    q.j jVar3 = new q.j();
                    jVar3.b = ar.this.c;
                    jVar3.a = routePOISearchResult;
                    obtainMessage.obj = jVar3;
                    obtainMessage.setData(bundle);
                    ar.this.d.sendMessage(obtainMessage);
                    throw th;
                }
            }
        }.start();
    }

    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public void setQuery(RoutePOISearchQuery routePOISearchQuery) {
        this.a = routePOISearchQuery;
    }

    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public RoutePOISearchQuery getQuery() {
        return this.a;
    }

    @Override // com.amap.api.services.interfaces.IRoutePOISearch
    public RoutePOISearchResult searchRoutePOI() throws AMapException {
        try {
            o.a(this.b);
            if (!a()) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            }
            return new ac(this.b, this.a.clone()).a();
        } catch (AMapException e) {
            i.a(e, "RoutePOISearchCore", "searchRoutePOI");
            throw e;
        }
    }

    private boolean a() {
        if (this.a == null || this.a.getSearchType() == null || this.a.getFrom() == null || this.a.getTo() == null) {
            return false;
        }
        return true;
    }
}

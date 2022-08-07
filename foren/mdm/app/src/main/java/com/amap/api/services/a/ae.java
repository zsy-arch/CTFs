package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;

/* compiled from: WalkRouteSearchHandler.java */
/* loaded from: classes.dex */
public class ae extends b<RouteSearch.WalkRouteQuery, WalkRouteResult> {
    public ae(Context context, RouteSearch.WalkRouteQuery walkRouteQuery) {
        super(context, walkRouteQuery);
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(aw.f(this.d));
        stringBuffer.append("&origin=").append(i.a(((RouteSearch.WalkRouteQuery) this.a).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(i.a(((RouteSearch.WalkRouteQuery) this.a).getFromAndTo().getTo()));
        stringBuffer.append("&multipath=0");
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public WalkRouteResult a(String str) throws AMapException {
        return n.c(str);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/direction/walking?";
    }
}

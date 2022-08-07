package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;

/* compiled from: RideRouteSearchHandler.java */
/* loaded from: classes.dex */
public class ab extends b<RouteSearch.RideRouteQuery, RideRouteResult> {
    public ab(Context context, RouteSearch.RideRouteQuery rideRouteQuery) {
        super(context, rideRouteQuery);
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(aw.f(this.d));
        stringBuffer.append("&origin=").append(i.a(((RouteSearch.RideRouteQuery) this.a).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(i.a(((RouteSearch.RideRouteQuery) this.a).getFromAndTo().getTo()));
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public RideRouteResult a(String str) throws AMapException {
        return n.o(str);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/direction/riding?";
    }
}

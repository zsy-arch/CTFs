package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.RouteSearch;

/* compiled from: BusRouteSearchHandler.java */
/* loaded from: classes.dex */
public class c extends b<RouteSearch.BusRouteQuery, BusRouteResult> {
    public c(Context context, RouteSearch.BusRouteQuery busRouteQuery) {
        super(context, busRouteQuery);
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(aw.f(this.d));
        stringBuffer.append("&origin=").append(i.a(((RouteSearch.BusRouteQuery) this.a).getFromAndTo().getFrom()));
        stringBuffer.append("&destination=").append(i.a(((RouteSearch.BusRouteQuery) this.a).getFromAndTo().getTo()));
        String city = ((RouteSearch.BusRouteQuery) this.a).getCity();
        if (!n.i(city)) {
            city = b(city);
            stringBuffer.append("&city=").append(city);
        }
        if (!n.i(((RouteSearch.BusRouteQuery) this.a).getCity())) {
            stringBuffer.append("&cityd=").append(b(city));
        }
        stringBuffer.append("&strategy=").append("" + ((RouteSearch.BusRouteQuery) this.a).getMode());
        stringBuffer.append("&nightflag=").append(((RouteSearch.BusRouteQuery) this.a).getNightFlag());
        stringBuffer.append("&extensions=all");
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public BusRouteResult a(String str) throws AMapException {
        return n.a(str);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/direction/transit/integrated?";
    }
}

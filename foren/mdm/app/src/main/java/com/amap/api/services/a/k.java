package com.amap.api.services.a;

import android.content.Context;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;

/* compiled from: DriveRouteSearchHandler.java */
/* loaded from: classes.dex */
public class k extends b<RouteSearch.DriveRouteQuery, DriveRouteResult> {
    public k(Context context, RouteSearch.DriveRouteQuery driveRouteQuery) {
        super(context, driveRouteQuery);
    }

    @Override // com.amap.api.services.a.b
    protected String e() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("key=").append(aw.f(this.d));
        if (((RouteSearch.DriveRouteQuery) this.a).getFromAndTo() != null) {
            stringBuffer.append("&origin=").append(i.a(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getFrom()));
            if (!n.i(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getStartPoiID())) {
                stringBuffer.append("&originid=").append(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getStartPoiID());
            }
            stringBuffer.append("&destination=").append(i.a(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getTo()));
            if (!n.i(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getDestinationPoiID())) {
                stringBuffer.append("&destinationid=").append(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getDestinationPoiID());
            }
            if (!n.i(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getOriginType())) {
                stringBuffer.append("&origintype=").append(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getOriginType());
            }
            if (!n.i(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getDestinationType())) {
                stringBuffer.append("&destinationtype=").append(((RouteSearch.DriveRouteQuery) this.a).getFromAndTo().getDestinationType());
            }
        }
        stringBuffer.append("&strategy=").append("" + ((RouteSearch.DriveRouteQuery) this.a).getMode());
        stringBuffer.append("&extensions=all");
        if (((RouteSearch.DriveRouteQuery) this.a).hasPassPoint()) {
            stringBuffer.append("&waypoints=").append(((RouteSearch.DriveRouteQuery) this.a).getPassedPointStr());
        }
        if (((RouteSearch.DriveRouteQuery) this.a).hasAvoidpolygons()) {
            stringBuffer.append("&avoidpolygons=").append(((RouteSearch.DriveRouteQuery) this.a).getAvoidpolygonsStr());
        }
        if (((RouteSearch.DriveRouteQuery) this.a).hasAvoidRoad()) {
            stringBuffer.append("&avoidroad=").append(b(((RouteSearch.DriveRouteQuery) this.a).getAvoidRoad()));
        }
        stringBuffer.append("&output=json");
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public DriveRouteResult a(String str) throws AMapException {
        return n.b(str);
    }

    @Override // com.amap.api.services.a.cz
    public String g() {
        return h.a() + "/direction/driving?";
    }
}

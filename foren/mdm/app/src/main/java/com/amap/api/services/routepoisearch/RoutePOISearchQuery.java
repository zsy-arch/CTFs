package com.amap.api.services.routepoisearch;

import com.amap.api.services.a.i;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.routepoisearch.RoutePOISearch;

/* loaded from: classes.dex */
public class RoutePOISearchQuery implements Cloneable {
    private LatLonPoint a;
    private LatLonPoint b;
    private int c;
    private RoutePOISearch.RoutePOISearchType d;
    private int e;

    public RoutePOISearchQuery(LatLonPoint latLonPoint, LatLonPoint latLonPoint2, int i, RoutePOISearch.RoutePOISearchType routePOISearchType, int i2) {
        this.a = latLonPoint;
        this.b = latLonPoint2;
        this.c = i;
        this.d = routePOISearchType;
        if (i2 <= 0) {
            this.e = 250;
        } else if (i2 > 500) {
            this.e = 500;
        } else {
            this.e = i2;
        }
    }

    public LatLonPoint getFrom() {
        return this.a;
    }

    public LatLonPoint getTo() {
        return this.b;
    }

    public int getMode() {
        return this.c;
    }

    public RoutePOISearch.RoutePOISearchType getSearchType() {
        return this.d;
    }

    public int getRange() {
        return this.e;
    }

    public RoutePOISearchQuery clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            i.a(e, "RoutePOISearchQuery", "RoutePOISearchQueryclone");
        }
        return new RoutePOISearchQuery(this.a, this.b, this.c, this.d, this.e);
    }
}

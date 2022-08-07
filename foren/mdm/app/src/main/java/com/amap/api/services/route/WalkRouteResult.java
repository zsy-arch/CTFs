package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class WalkRouteResult extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<WalkRouteResult> CREATOR = new Parcelable.Creator<WalkRouteResult>() { // from class: com.amap.api.services.route.WalkRouteResult.1
        /* renamed from: a */
        public WalkRouteResult createFromParcel(Parcel parcel) {
            return new WalkRouteResult(parcel);
        }

        /* renamed from: a */
        public WalkRouteResult[] newArray(int i) {
            return new WalkRouteResult[i];
        }
    };
    private List<WalkPath> a;
    private RouteSearch.WalkRouteQuery b;

    public List<WalkPath> getPaths() {
        return this.a;
    }

    public void setPaths(List<WalkPath> list) {
        this.a = list;
    }

    public RouteSearch.WalkRouteQuery getWalkQuery() {
        return this.b;
    }

    public void setWalkQuery(RouteSearch.WalkRouteQuery walkRouteQuery) {
        this.b = walkRouteQuery;
    }

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.a);
        parcel.writeParcelable(this.b, i);
    }

    public WalkRouteResult(Parcel parcel) {
        super(parcel);
        this.a = new ArrayList();
        this.a = parcel.createTypedArrayList(WalkPath.CREATOR);
        this.b = (RouteSearch.WalkRouteQuery) parcel.readParcelable(RouteSearch.WalkRouteQuery.class.getClassLoader());
    }

    public WalkRouteResult() {
        this.a = new ArrayList();
    }
}

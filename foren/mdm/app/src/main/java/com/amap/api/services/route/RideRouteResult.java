package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RideRouteResult extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<RideRouteResult> CREATOR = new Parcelable.Creator<RideRouteResult>() { // from class: com.amap.api.services.route.RideRouteResult.1
        /* renamed from: a */
        public RideRouteResult createFromParcel(Parcel parcel) {
            return new RideRouteResult(parcel);
        }

        /* renamed from: a */
        public RideRouteResult[] newArray(int i) {
            return new RideRouteResult[i];
        }
    };
    private List<RidePath> a;
    private RouteSearch.RideRouteQuery b;

    public List<RidePath> getPaths() {
        return this.a;
    }

    public void setPaths(List<RidePath> list) {
        this.a = list;
    }

    public RouteSearch.RideRouteQuery getRideQuery() {
        return this.b;
    }

    public void setRideQuery(RouteSearch.RideRouteQuery rideRouteQuery) {
        this.b = rideRouteQuery;
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

    public RideRouteResult(Parcel parcel) {
        super(parcel);
        this.a = new ArrayList();
        this.a = parcel.createTypedArrayList(RidePath.CREATOR);
        this.b = (RouteSearch.RideRouteQuery) parcel.readParcelable(RouteSearch.RideRouteQuery.class.getClassLoader());
    }

    public RideRouteResult() {
        this.a = new ArrayList();
    }
}

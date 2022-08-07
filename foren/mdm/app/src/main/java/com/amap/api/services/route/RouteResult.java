package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes.dex */
public class RouteResult implements Parcelable {
    public static final Parcelable.Creator<RouteResult> CREATOR = new Parcelable.Creator<RouteResult>() { // from class: com.amap.api.services.route.RouteResult.1
        /* renamed from: a */
        public RouteResult createFromParcel(Parcel parcel) {
            return new RouteResult(parcel);
        }

        /* renamed from: a */
        public RouteResult[] newArray(int i) {
            return new RouteResult[i];
        }
    };
    private LatLonPoint a;
    private LatLonPoint b;

    public LatLonPoint getStartPos() {
        return this.a;
    }

    public void setStartPos(LatLonPoint latLonPoint) {
        this.a = latLonPoint;
    }

    public LatLonPoint getTargetPos() {
        return this.b;
    }

    public void setTargetPos(LatLonPoint latLonPoint) {
        this.b = latLonPoint;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
    }

    public RouteResult(Parcel parcel) {
        this.a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public RouteResult() {
    }
}

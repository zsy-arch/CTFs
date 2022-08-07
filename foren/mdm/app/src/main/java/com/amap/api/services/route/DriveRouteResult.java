package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.route.RouteSearch;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DriveRouteResult extends RouteResult implements Parcelable {
    public static final Parcelable.Creator<DriveRouteResult> CREATOR = new Parcelable.Creator<DriveRouteResult>() { // from class: com.amap.api.services.route.DriveRouteResult.1
        /* renamed from: a */
        public DriveRouteResult createFromParcel(Parcel parcel) {
            return new DriveRouteResult(parcel);
        }

        /* renamed from: a */
        public DriveRouteResult[] newArray(int i) {
            return new DriveRouteResult[i];
        }
    };
    private float a;
    private List<DrivePath> b;
    private RouteSearch.DriveRouteQuery c;

    public float getTaxiCost() {
        return this.a;
    }

    public void setTaxiCost(float f) {
        this.a = f;
    }

    public List<DrivePath> getPaths() {
        return this.b;
    }

    public void setPaths(List<DrivePath> list) {
        this.b = list;
    }

    public RouteSearch.DriveRouteQuery getDriveQuery() {
        return this.c;
    }

    public void setDriveQuery(RouteSearch.DriveRouteQuery driveRouteQuery) {
        this.c = driveRouteQuery;
    }

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.route.RouteResult, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeFloat(this.a);
        parcel.writeTypedList(this.b);
        parcel.writeParcelable(this.c, i);
    }

    public DriveRouteResult(Parcel parcel) {
        super(parcel);
        this.b = new ArrayList();
        this.a = parcel.readFloat();
        this.b = parcel.createTypedArrayList(DrivePath.CREATOR);
        this.c = (RouteSearch.DriveRouteQuery) parcel.readParcelable(RouteSearch.DriveRouteQuery.class.getClassLoader());
    }

    public DriveRouteResult() {
        this.b = new ArrayList();
    }
}

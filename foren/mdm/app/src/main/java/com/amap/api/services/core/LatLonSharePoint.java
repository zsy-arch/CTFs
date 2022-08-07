package com.amap.api.services.core;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class LatLonSharePoint extends LatLonPoint implements Parcelable {
    public static final Parcelable.Creator<LatLonSharePoint> CREATOR = new Parcelable.Creator<LatLonSharePoint>() { // from class: com.amap.api.services.core.LatLonSharePoint.1
        /* renamed from: a */
        public LatLonSharePoint createFromParcel(Parcel parcel) {
            return new LatLonSharePoint(parcel);
        }

        /* renamed from: a */
        public LatLonSharePoint[] newArray(int i) {
            return new LatLonSharePoint[i];
        }
    };
    private String a;

    public LatLonSharePoint(double d, double d2, String str) {
        super(d, d2);
        this.a = str;
    }

    public String getSharePointName() {
        return this.a;
    }

    public void setSharePointName(String str) {
        this.a = str;
    }

    protected LatLonSharePoint(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
    }

    @Override // com.amap.api.services.core.LatLonPoint, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.core.LatLonPoint, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
    }

    @Override // com.amap.api.services.core.LatLonPoint
    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + (super.hashCode() * 31);
    }

    @Override // com.amap.api.services.core.LatLonPoint
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj) && getClass() == obj.getClass()) {
            LatLonSharePoint latLonSharePoint = (LatLonSharePoint) obj;
            return this.a == null ? latLonSharePoint.a == null : this.a.equals(latLonSharePoint.a);
        }
        return false;
    }

    @Override // com.amap.api.services.core.LatLonPoint
    public String toString() {
        return super.toString() + "," + this.a;
    }
}

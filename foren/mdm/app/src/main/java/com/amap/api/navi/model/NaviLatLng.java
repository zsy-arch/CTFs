package com.amap.api.navi.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class NaviLatLng implements Parcelable {
    public static final Parcelable.Creator<NaviLatLng> CREATOR = new Parcelable.Creator<NaviLatLng>() { // from class: com.amap.api.navi.model.NaviLatLng.1
        /* renamed from: a */
        public NaviLatLng createFromParcel(Parcel parcel) {
            return new NaviLatLng(parcel.readDouble(), parcel.readDouble());
        }

        /* renamed from: a */
        public NaviLatLng[] newArray(int i) {
            return new NaviLatLng[i];
        }
    };
    private double latitude;
    private double longitude;

    public NaviLatLng(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
    }

    public NaviLatLng() {
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (hashCode() == obj.hashCode()) {
            return true;
        }
        if (!(obj instanceof NaviLatLng)) {
            return false;
        }
        NaviLatLng naviLatLng = (NaviLatLng) obj;
        if (!(Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(naviLatLng.latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(naviLatLng.longitude))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "lat/lng: (" + this.latitude + "," + this.longitude + ")";
    }
}

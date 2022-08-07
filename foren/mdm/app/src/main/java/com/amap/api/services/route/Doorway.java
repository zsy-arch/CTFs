package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes.dex */
public class Doorway implements Parcelable {
    public static final Parcelable.Creator<Doorway> CREATOR = new Parcelable.Creator<Doorway>() { // from class: com.amap.api.services.route.Doorway.1
        /* renamed from: a */
        public Doorway createFromParcel(Parcel parcel) {
            return new Doorway(parcel);
        }

        /* renamed from: a */
        public Doorway[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private LatLonPoint b;

    public String getName() {
        return this.a;
    }

    public void setName(String str) {
        this.a = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.b;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.b = latLonPoint;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeParcelable(this.b, i);
    }

    public Doorway(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
    }

    public Doorway() {
    }
}

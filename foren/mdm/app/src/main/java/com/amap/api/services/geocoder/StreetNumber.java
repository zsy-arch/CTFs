package com.amap.api.services.geocoder;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes.dex */
public final class StreetNumber implements Parcelable {
    public static final Parcelable.Creator<StreetNumber> CREATOR = new Parcelable.Creator<StreetNumber>() { // from class: com.amap.api.services.geocoder.StreetNumber.1
        /* renamed from: a */
        public StreetNumber createFromParcel(Parcel parcel) {
            return new StreetNumber(parcel);
        }

        /* renamed from: a */
        public StreetNumber[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String b;
    private LatLonPoint c;
    private String d;
    private float e;

    public StreetNumber() {
    }

    public String getStreet() {
        return this.a;
    }

    public void setStreet(String str) {
        this.a = str;
    }

    public String getNumber() {
        return this.b;
    }

    public void setNumber(String str) {
        this.b = str;
    }

    public LatLonPoint getLatLonPoint() {
        return this.c;
    }

    public void setLatLonPoint(LatLonPoint latLonPoint) {
        this.c = latLonPoint;
    }

    public String getDirection() {
        return this.d;
    }

    public void setDirection(String str) {
        this.d = str;
    }

    public float getDistance() {
        return this.e;
    }

    public void setDistance(float f) {
        this.e = f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeValue(this.c);
        parcel.writeString(this.d);
        parcel.writeFloat(this.e);
    }

    private StreetNumber(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.d = parcel.readString();
        this.e = parcel.readFloat();
    }
}

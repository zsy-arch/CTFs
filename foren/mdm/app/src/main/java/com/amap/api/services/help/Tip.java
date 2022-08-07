package com.amap.api.services.help;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;

/* loaded from: classes.dex */
public class Tip implements Parcelable {
    public static final Parcelable.Creator<Tip> CREATOR = new Parcelable.Creator<Tip>() { // from class: com.amap.api.services.help.Tip.1
        /* renamed from: a */
        public Tip createFromParcel(Parcel parcel) {
            return new Tip(parcel);
        }

        /* renamed from: a */
        public Tip[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private LatLonPoint b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;

    public Tip() {
    }

    public String getPoiID() {
        return this.a;
    }

    public void setID(String str) {
        this.a = str;
    }

    public LatLonPoint getPoint() {
        return this.b;
    }

    public void setPostion(LatLonPoint latLonPoint) {
        this.b = latLonPoint;
    }

    public String getName() {
        return this.c;
    }

    public void setName(String str) {
        this.c = str;
    }

    public String getDistrict() {
        return this.d;
    }

    public void setDistrict(String str) {
        this.d = str;
    }

    public String getAdcode() {
        return this.e;
    }

    public void setAdcode(String str) {
        this.e = str;
    }

    public String getAddress() {
        return this.f;
    }

    public void setAddress(String str) {
        this.f = str;
    }

    public void setTypeCode(String str) {
        this.g = str;
    }

    public String getTypeCode() {
        return this.g;
    }

    public String toString() {
        return "name:" + this.c + " district:" + this.d + " adcode:" + this.e;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.e);
        parcel.writeString(this.d);
        parcel.writeString(this.a);
        parcel.writeValue(this.b);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }

    private Tip(Parcel parcel) {
        this.c = parcel.readString();
        this.e = parcel.readString();
        this.d = parcel.readString();
        this.a = parcel.readString();
        this.b = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.f = parcel.readString();
        this.g = parcel.readString();
    }
}

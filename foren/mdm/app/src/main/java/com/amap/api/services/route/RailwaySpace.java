package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class RailwaySpace implements Parcelable {
    public static final Parcelable.Creator<RailwaySpace> CREATOR = new Parcelable.Creator<RailwaySpace>() { // from class: com.amap.api.services.route.RailwaySpace.1
        /* renamed from: a */
        public RailwaySpace createFromParcel(Parcel parcel) {
            return new RailwaySpace(parcel);
        }

        /* renamed from: a */
        public RailwaySpace[] newArray(int i) {
            return new RailwaySpace[i];
        }
    };
    private String a;
    private float b;

    public RailwaySpace(String str, float f) {
        this.a = str;
        this.b = f;
    }

    public String getCode() {
        return this.a;
    }

    public float getCost() {
        return this.b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeFloat(this.b);
    }

    protected RailwaySpace(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readFloat();
    }
}

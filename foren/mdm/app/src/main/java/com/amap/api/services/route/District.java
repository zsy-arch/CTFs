package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class District implements Parcelable {
    public static final Parcelable.Creator<District> CREATOR = new Parcelable.Creator<District>() { // from class: com.amap.api.services.route.District.1
        /* renamed from: a */
        public District createFromParcel(Parcel parcel) {
            return new District(parcel);
        }

        /* renamed from: a */
        public District[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private String b;

    public String getDistrictName() {
        return this.a;
    }

    public void setDistrictName(String str) {
        this.a = str;
    }

    public String getDistrictAdcode() {
        return this.b;
    }

    public void setDistrictAdcode(String str) {
        this.b = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }

    public District(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    public District() {
    }
}

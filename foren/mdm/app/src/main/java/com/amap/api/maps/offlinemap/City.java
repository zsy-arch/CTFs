package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class City implements Parcelable {
    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() { // from class: com.amap.api.maps.offlinemap.City.1
        /* renamed from: a */
        public City createFromParcel(Parcel parcel) {
            return new City(parcel);
        }

        /* renamed from: a */
        public City[] newArray(int i) {
            return new City[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;

    public City() {
        this.a = "";
        this.b = "";
        this.e = "";
    }

    public void setCity(String str) {
        this.a = str;
    }

    public String getCity() {
        return this.a;
    }

    public void setCode(String str) {
        if (str != null && !str.equals("[]")) {
            this.b = str;
        }
    }

    public String getCode() {
        return this.b;
    }

    public String getJianpin() {
        return this.c;
    }

    public void setJianpin(String str) {
        this.c = str;
    }

    public String getPinyin() {
        return this.d;
    }

    public void setPinyin(String str) {
        this.d = str;
    }

    public String getAdcode() {
        return this.e;
    }

    public void setAdcode(String str) {
        this.e = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }

    public City(Parcel parcel) {
        this.a = "";
        this.b = "";
        this.e = "";
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
    }
}

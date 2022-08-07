package com.amap.api.maps.offlinemap;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class Province implements Parcelable {
    public static final Parcelable.Creator<Province> CREATOR = new Parcelable.Creator<Province>() { // from class: com.amap.api.maps.offlinemap.Province.1
        /* renamed from: a */
        public Province createFromParcel(Parcel parcel) {
            return new Province(parcel);
        }

        /* renamed from: a */
        public Province[] newArray(int i) {
            return new Province[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private String d;

    public Province() {
        this.a = "";
        this.d = "";
    }

    public String getProvinceName() {
        return this.a;
    }

    public String getJianpin() {
        return this.b;
    }

    public String getPinyin() {
        return this.c;
    }

    public void setProvinceName(String str) {
        this.a = str;
    }

    public void setJianpin(String str) {
        this.b = str;
    }

    public void setPinyin(String str) {
        this.c = str;
    }

    public void setProvinceCode(String str) {
        this.d = str;
    }

    public String getProvinceCode() {
        return this.d;
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
    }

    public Province(Parcel parcel) {
        this.a = "";
        this.d = "";
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }
}

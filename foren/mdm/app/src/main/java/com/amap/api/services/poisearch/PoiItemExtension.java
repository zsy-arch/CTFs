package com.amap.api.services.poisearch;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class PoiItemExtension implements Parcelable {
    public static final Parcelable.Creator<PoiItemExtension> CREATOR = new Parcelable.Creator<PoiItemExtension>() { // from class: com.amap.api.services.poisearch.PoiItemExtension.1
        /* renamed from: a */
        public PoiItemExtension createFromParcel(Parcel parcel) {
            return new PoiItemExtension(parcel);
        }

        /* renamed from: a */
        public PoiItemExtension[] newArray(int i) {
            return new PoiItemExtension[i];
        }
    };
    private String a;
    private String b;

    public PoiItemExtension(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String getOpentime() {
        return this.a;
    }

    public String getmRating() {
        return this.b;
    }

    protected PoiItemExtension(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}

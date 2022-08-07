package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class Railway implements Parcelable {
    public static final Parcelable.Creator<Railway> CREATOR = new Parcelable.Creator<Railway>() { // from class: com.amap.api.services.route.Railway.1
        /* renamed from: a */
        public Railway createFromParcel(Parcel parcel) {
            return new Railway(parcel);
        }

        /* renamed from: a */
        public Railway[] newArray(int i) {
            return new Railway[i];
        }
    };
    private String a;
    private String b;

    public Railway() {
    }

    public String getID() {
        return this.a;
    }

    public String getName() {
        return this.b;
    }

    public void setID(String str) {
        this.a = str;
    }

    public void setName(String str) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    public Railway(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }
}

package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class Path implements Parcelable {
    public static final Parcelable.Creator<Path> CREATOR = new Parcelable.Creator<Path>() { // from class: com.amap.api.services.route.Path.1
        /* renamed from: a */
        public Path createFromParcel(Parcel parcel) {
            return new Path(parcel);
        }

        /* renamed from: a */
        public Path[] newArray(int i) {
            return null;
        }
    };
    private float a;
    private long b;

    public float getDistance() {
        return this.a;
    }

    public void setDistance(float f) {
        this.a = f;
    }

    public long getDuration() {
        return this.b;
    }

    public void setDuration(long j) {
        this.b = j;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
        parcel.writeLong(this.b);
    }

    public Path(Parcel parcel) {
        this.a = parcel.readFloat();
        this.b = parcel.readLong();
    }

    public Path() {
    }
}

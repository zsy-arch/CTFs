package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class DrivePath extends Path implements Parcelable {
    public static final Parcelable.Creator<DrivePath> CREATOR = new Parcelable.Creator<DrivePath>() { // from class: com.amap.api.services.route.DrivePath.1
        /* renamed from: a */
        public DrivePath createFromParcel(Parcel parcel) {
            return new DrivePath(parcel);
        }

        /* renamed from: a */
        public DrivePath[] newArray(int i) {
            return null;
        }
    };
    private String a;
    private float b;
    private float c;
    private int d;
    private List<DriveStep> e;

    public String getStrategy() {
        return this.a;
    }

    public void setStrategy(String str) {
        this.a = str;
    }

    public float getTolls() {
        return this.b;
    }

    public void setTolls(float f) {
        this.b = f;
    }

    public float getTollDistance() {
        return this.c;
    }

    public void setTollDistance(float f) {
        this.c = f;
    }

    public int getTotalTrafficlights() {
        return this.d;
    }

    public void setTotalTrafficlights(int i) {
        this.d = i;
    }

    public List<DriveStep> getSteps() {
        return this.e;
    }

    public void setSteps(List<DriveStep> list) {
        this.e = list;
    }

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.c);
        parcel.writeTypedList(this.e);
        parcel.writeInt(this.d);
    }

    public DrivePath(Parcel parcel) {
        super(parcel);
        this.e = new ArrayList();
        this.a = parcel.readString();
        this.b = parcel.readFloat();
        this.c = parcel.readFloat();
        this.e = parcel.createTypedArrayList(DriveStep.CREATOR);
        this.d = parcel.readInt();
    }

    public DrivePath() {
        this.e = new ArrayList();
    }
}

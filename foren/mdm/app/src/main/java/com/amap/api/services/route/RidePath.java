package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RidePath extends Path implements Parcelable {
    public static final Parcelable.Creator<RidePath> CREATOR = new Parcelable.Creator<RidePath>() { // from class: com.amap.api.services.route.RidePath.1
        /* renamed from: a */
        public RidePath createFromParcel(Parcel parcel) {
            return new RidePath(parcel);
        }

        /* renamed from: a */
        public RidePath[] newArray(int i) {
            return null;
        }
    };
    private List<RideStep> a;

    public List<RideStep> getSteps() {
        return this.a;
    }

    public void setSteps(List<RideStep> list) {
        this.a = list;
    }

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.route.Path, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.a);
    }

    public RidePath(Parcel parcel) {
        super(parcel);
        this.a = new ArrayList();
        this.a = parcel.createTypedArrayList(RideStep.CREATOR);
    }

    public RidePath() {
        this.a = new ArrayList();
    }
}

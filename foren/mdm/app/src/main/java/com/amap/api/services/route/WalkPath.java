package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class WalkPath extends Path implements Parcelable {
    public static final Parcelable.Creator<WalkPath> CREATOR = new Parcelable.Creator<WalkPath>() { // from class: com.amap.api.services.route.WalkPath.1
        /* renamed from: a */
        public WalkPath createFromParcel(Parcel parcel) {
            return new WalkPath(parcel);
        }

        /* renamed from: a */
        public WalkPath[] newArray(int i) {
            return null;
        }
    };
    private List<WalkStep> a;

    public List<WalkStep> getSteps() {
        return this.a;
    }

    public void setSteps(List<WalkStep> list) {
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

    public WalkPath(Parcel parcel) {
        super(parcel);
        this.a = new ArrayList();
        this.a = parcel.createTypedArrayList(WalkStep.CREATOR);
    }

    public WalkPath() {
        this.a = new ArrayList();
    }
}

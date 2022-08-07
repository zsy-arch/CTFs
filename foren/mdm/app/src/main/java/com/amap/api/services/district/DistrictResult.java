package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.AMapException;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class DistrictResult implements Parcelable {
    public Parcelable.Creator<DistrictResult> CREATOR;
    private DistrictSearchQuery a;
    private ArrayList<DistrictItem> b;
    private int c;
    private AMapException d;

    public DistrictResult(DistrictSearchQuery districtSearchQuery, ArrayList<DistrictItem> arrayList) {
        this.b = new ArrayList<>();
        this.CREATOR = new Parcelable.Creator<DistrictResult>() { // from class: com.amap.api.services.district.DistrictResult.1
            /* renamed from: a */
            public DistrictResult createFromParcel(Parcel parcel) {
                return new DistrictResult(parcel);
            }

            /* renamed from: a */
            public DistrictResult[] newArray(int i) {
                return new DistrictResult[i];
            }
        };
        this.a = districtSearchQuery;
        this.b = arrayList;
    }

    public DistrictResult() {
        this.b = new ArrayList<>();
        this.CREATOR = new Parcelable.Creator<DistrictResult>() { // from class: com.amap.api.services.district.DistrictResult.1
            /* renamed from: a */
            public DistrictResult createFromParcel(Parcel parcel) {
                return new DistrictResult(parcel);
            }

            /* renamed from: a */
            public DistrictResult[] newArray(int i) {
                return new DistrictResult[i];
            }
        };
    }

    public ArrayList<DistrictItem> getDistrict() {
        return this.b;
    }

    public void setDistrict(ArrayList<DistrictItem> arrayList) {
        this.b = arrayList;
    }

    public DistrictSearchQuery getQuery() {
        return this.a;
    }

    public void setQuery(DistrictSearchQuery districtSearchQuery) {
        this.a = districtSearchQuery;
    }

    public int getPageCount() {
        return this.c;
    }

    public void setPageCount(int i) {
        this.c = i;
    }

    public AMapException getAMapException() {
        return this.d;
    }

    public void setAMapException(AMapException aMapException) {
        this.d = aMapException;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeTypedList(this.b);
    }

    protected DistrictResult(Parcel parcel) {
        this.b = new ArrayList<>();
        this.CREATOR = new Parcelable.Creator<DistrictResult>() { // from class: com.amap.api.services.district.DistrictResult.1
            /* renamed from: a */
            public DistrictResult createFromParcel(Parcel parcel2) {
                return new DistrictResult(parcel2);
            }

            /* renamed from: a */
            public DistrictResult[] newArray(int i) {
                return new DistrictResult[i];
            }
        };
        this.a = (DistrictSearchQuery) parcel.readParcelable(DistrictSearchQuery.class.getClassLoader());
        this.b = parcel.createTypedArrayList(DistrictItem.CREATOR);
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.a == null) {
            hashCode = 0;
        } else {
            hashCode = this.a.hashCode();
        }
        int i2 = (hashCode + 31) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return i2 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            DistrictResult districtResult = (DistrictResult) obj;
            if (this.a == null) {
                if (districtResult.a != null) {
                    return false;
                }
            } else if (!this.a.equals(districtResult.a)) {
                return false;
            }
            return this.b == null ? districtResult.b == null : this.b.equals(districtResult.b);
        }
        return false;
    }

    public String toString() {
        return "DistrictResult [mDisQuery=" + this.a + ", mDistricts=" + this.b + "]";
    }
}

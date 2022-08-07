package com.amap.api.services.district;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class DistrictItem implements Parcelable {
    public static final Parcelable.Creator<DistrictItem> CREATOR = new Parcelable.Creator<DistrictItem>() { // from class: com.amap.api.services.district.DistrictItem.1
        /* renamed from: a */
        public DistrictItem createFromParcel(Parcel parcel) {
            return new DistrictItem(parcel);
        }

        /* renamed from: a */
        public DistrictItem[] newArray(int i) {
            return new DistrictItem[i];
        }
    };
    private String a;
    private String b;
    private String c;
    private LatLonPoint d;
    private String e;
    private List<DistrictItem> f;
    private String[] g;

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeParcelable(this.d, i);
        parcel.writeString(this.e);
        parcel.writeTypedList(this.f);
        parcel.writeInt(this.g.length);
        parcel.writeStringArray(this.g);
    }

    public void setDistrictBoundary(String[] strArr) {
        this.g = strArr;
    }

    public String[] districtBoundary() {
        return this.g;
    }

    public DistrictItem() {
        this.f = new ArrayList();
        this.g = new String[0];
    }

    public DistrictItem(String str, String str2, String str3, LatLonPoint latLonPoint, String str4) {
        this.f = new ArrayList();
        this.g = new String[0];
        this.c = str;
        this.a = str2;
        this.b = str3;
        this.d = latLonPoint;
        this.e = str4;
    }

    public String getCitycode() {
        return this.a;
    }

    public void setCitycode(String str) {
        this.a = str;
    }

    public String getAdcode() {
        return this.b;
    }

    public void setAdcode(String str) {
        this.b = str;
    }

    public String getName() {
        return this.c;
    }

    public void setName(String str) {
        this.c = str;
    }

    public LatLonPoint getCenter() {
        return this.d;
    }

    public void setCenter(LatLonPoint latLonPoint) {
        this.d = latLonPoint;
    }

    public String getLevel() {
        return this.e;
    }

    public void setLevel(String str) {
        this.e = str;
    }

    public List<DistrictItem> getSubDistrict() {
        return this.f;
    }

    public void setSubDistrict(ArrayList<DistrictItem> arrayList) {
        this.f = arrayList;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DistrictItem(Parcel parcel) {
        this.f = new ArrayList();
        this.g = new String[0];
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
        this.e = parcel.readString();
        this.f = parcel.createTypedArrayList(CREATOR);
        this.g = new String[parcel.readInt()];
        parcel.readStringArray(this.g);
    }

    public int hashCode() {
        int hashCode;
        int hashCode2;
        int hashCode3;
        int hashCode4;
        int hashCode5;
        int i = 0;
        if (this.b == null) {
            hashCode = 0;
        } else {
            hashCode = this.b.hashCode();
        }
        int i2 = (hashCode + 31) * 31;
        if (this.d == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = this.d.hashCode();
        }
        int i3 = (hashCode2 + i2) * 31;
        if (this.a == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = this.a.hashCode();
        }
        int hashCode6 = (((hashCode3 + i3) * 31) + Arrays.hashCode(this.g)) * 31;
        if (this.f == null) {
            hashCode4 = 0;
        } else {
            hashCode4 = this.f.hashCode();
        }
        int i4 = (hashCode4 + hashCode6) * 31;
        if (this.e == null) {
            hashCode5 = 0;
        } else {
            hashCode5 = this.e.hashCode();
        }
        int i5 = (hashCode5 + i4) * 31;
        if (this.c != null) {
            i = this.c.hashCode();
        }
        return i5 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            DistrictItem districtItem = (DistrictItem) obj;
            if (this.b == null) {
                if (districtItem.b != null) {
                    return false;
                }
            } else if (!this.b.equals(districtItem.b)) {
                return false;
            }
            if (this.d == null) {
                if (districtItem.d != null) {
                    return false;
                }
            } else if (!this.d.equals(districtItem.d)) {
                return false;
            }
            if (this.a == null) {
                if (districtItem.a != null) {
                    return false;
                }
            } else if (!this.a.equals(districtItem.a)) {
                return false;
            }
            if (!Arrays.equals(this.g, districtItem.g)) {
                return false;
            }
            if (this.f == null) {
                if (districtItem.f != null) {
                    return false;
                }
            } else if (!this.f.equals(districtItem.f)) {
                return false;
            }
            if (this.e == null) {
                if (districtItem.e != null) {
                    return false;
                }
            } else if (!this.e.equals(districtItem.e)) {
                return false;
            }
            return this.c == null ? districtItem.c == null : this.c.equals(districtItem.c);
        }
        return false;
    }

    public String toString() {
        return "DistrictItem [mCitycode=" + this.a + ", mAdcode=" + this.b + ", mName=" + this.c + ", mCenter=" + this.d + ", mLevel=" + this.e + ", mDistricts=" + this.f + "]";
    }
}

package com.amap.api.services.cloud;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class CloudItem implements Parcelable {
    public static final Parcelable.Creator<CloudItem> CREATOR = new Parcelable.Creator<CloudItem>() { // from class: com.amap.api.services.cloud.CloudItem.1
        /* renamed from: a */
        public CloudItem createFromParcel(Parcel parcel) {
            return new CloudItem(parcel);
        }

        /* renamed from: a */
        public CloudItem[] newArray(int i) {
            return new CloudItem[i];
        }
    };
    private String a;
    private int b;
    private String c;
    private String d;
    private HashMap<String, String> e;
    private List<CloudImage> f;
    protected final LatLonPoint mPoint;
    protected final String mSnippet;
    protected final String mTitle;

    public CloudItem(String str, LatLonPoint latLonPoint, String str2, String str3) {
        this.b = -1;
        this.a = str;
        this.mPoint = latLonPoint;
        this.mTitle = str2;
        this.mSnippet = str3;
    }

    public String getID() {
        return this.a;
    }

    public int getDistance() {
        return this.b;
    }

    public void setDistance(int i) {
        this.b = i;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSnippet() {
        return this.mSnippet;
    }

    public LatLonPoint getLatLonPoint() {
        return this.mPoint;
    }

    public String getCreatetime() {
        return this.c;
    }

    public void setCreatetime(String str) {
        this.c = str;
    }

    public String getUpdatetime() {
        return this.d;
    }

    public void setUpdatetime(String str) {
        this.d = str;
    }

    public HashMap<String, String> getCustomfield() {
        return this.e;
    }

    public void setCustomfield(HashMap<String, String> hashMap) {
        this.e = hashMap;
    }

    public List<CloudImage> getCloudImage() {
        return this.f;
    }

    public void setmCloudImage(List<CloudImage> list) {
        this.f = list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CloudItem(Parcel parcel) {
        this.b = -1;
        this.a = parcel.readString();
        this.b = parcel.readInt();
        this.mPoint = (LatLonPoint) parcel.readValue(LatLonPoint.class.getClassLoader());
        this.mTitle = parcel.readString();
        this.mSnippet = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = new HashMap<>();
        parcel.readMap(this.e, HashMap.class.getClassLoader());
        this.f = new ArrayList();
        parcel.readList(this.f, getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeInt(this.b);
        parcel.writeValue(this.mPoint);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSnippet);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeMap(this.e);
        parcel.writeList(this.f);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass() && CloudItem.class != obj.getClass() && CloudItemDetail.class != obj.getClass()) {
            return false;
        }
        CloudItem cloudItem = (CloudItem) obj;
        return this.a == null ? cloudItem.a == null : this.a.equals(cloudItem.a);
    }

    public int hashCode() {
        return (this.a == null ? 0 : this.a.hashCode()) + 31;
    }

    public String toString() {
        return this.mTitle;
    }
}

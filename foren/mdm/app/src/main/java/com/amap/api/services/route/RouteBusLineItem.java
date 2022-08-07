package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.busline.BusLineItem;
import com.amap.api.services.busline.BusStationItem;
import com.amap.api.services.core.LatLonPoint;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RouteBusLineItem extends BusLineItem implements Parcelable {
    public static final Parcelable.Creator<RouteBusLineItem> CREATOR = new Parcelable.Creator<RouteBusLineItem>() { // from class: com.amap.api.services.route.RouteBusLineItem.1
        /* renamed from: a */
        public RouteBusLineItem createFromParcel(Parcel parcel) {
            return new RouteBusLineItem(parcel);
        }

        /* renamed from: a */
        public RouteBusLineItem[] newArray(int i) {
            return null;
        }
    };
    private BusStationItem a;
    private BusStationItem b;
    private List<LatLonPoint> c;
    private int d;
    private List<BusStationItem> e;
    private float f;

    public BusStationItem getDepartureBusStation() {
        return this.a;
    }

    public void setDepartureBusStation(BusStationItem busStationItem) {
        this.a = busStationItem;
    }

    public BusStationItem getArrivalBusStation() {
        return this.b;
    }

    public void setArrivalBusStation(BusStationItem busStationItem) {
        this.b = busStationItem;
    }

    public List<LatLonPoint> getPolyline() {
        return this.c;
    }

    public void setPolyline(List<LatLonPoint> list) {
        this.c = list;
    }

    public int getPassStationNum() {
        return this.d;
    }

    public void setPassStationNum(int i) {
        this.d = i;
    }

    public List<BusStationItem> getPassStations() {
        return this.e;
    }

    public void setPassStations(List<BusStationItem> list) {
        this.e = list;
    }

    public float getDuration() {
        return this.f;
    }

    public void setDuration(float f) {
        this.f = f;
    }

    @Override // com.amap.api.services.busline.BusLineItem, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amap.api.services.busline.BusLineItem, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, i);
        parcel.writeParcelable(this.b, i);
        parcel.writeTypedList(this.c);
        parcel.writeInt(this.d);
        parcel.writeTypedList(this.e);
        parcel.writeFloat(this.f);
    }

    public RouteBusLineItem(Parcel parcel) {
        super(parcel);
        this.c = new ArrayList();
        this.e = new ArrayList();
        this.a = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.b = (BusStationItem) parcel.readParcelable(BusStationItem.class.getClassLoader());
        this.c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
        this.d = parcel.readInt();
        this.e = parcel.createTypedArrayList(BusStationItem.CREATOR);
        this.f = parcel.readFloat();
    }

    public RouteBusLineItem() {
        this.c = new ArrayList();
        this.e = new ArrayList();
    }

    @Override // com.amap.api.services.busline.BusLineItem
    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = super.hashCode() * 31;
        if (this.b == null) {
            hashCode = 0;
        } else {
            hashCode = this.b.hashCode();
        }
        int i2 = (hashCode + hashCode2) * 31;
        if (this.a != null) {
            i = this.a.hashCode();
        }
        return i2 + i;
    }

    @Override // com.amap.api.services.busline.BusLineItem
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj) && getClass() == obj.getClass()) {
            RouteBusLineItem routeBusLineItem = (RouteBusLineItem) obj;
            if (this.b == null) {
                if (routeBusLineItem.b != null) {
                    return false;
                }
            } else if (!this.b.equals(routeBusLineItem.b)) {
                return false;
            }
            return this.a == null ? routeBusLineItem.a == null : this.a.equals(routeBusLineItem.a);
        }
        return false;
    }
}

package com.amap.api.services.busline;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.a.i;
import com.amap.api.services.core.LatLonPoint;
import com.hyphenate.util.HanziToPinyin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
public class BusLineItem implements Parcelable {
    public static final Parcelable.Creator<BusLineItem> CREATOR = new Parcelable.Creator<BusLineItem>() { // from class: com.amap.api.services.busline.BusLineItem.1
        /* renamed from: a */
        public BusLineItem createFromParcel(Parcel parcel) {
            return new BusLineItem(parcel);
        }

        /* renamed from: a */
        public BusLineItem[] newArray(int i) {
            return null;
        }
    };
    private float a;
    private String b;
    private String c;
    private String d;
    private List<LatLonPoint> e;
    private List<LatLonPoint> f;
    private String g;
    private String h;
    private String i;
    private Date j;
    private Date k;
    private String l;
    private float m;
    private float n;
    private List<BusStationItem> o;

    public BusLineItem() {
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.o = new ArrayList();
    }

    public float getDistance() {
        return this.a;
    }

    public void setDistance(float f) {
        this.a = f;
    }

    public String getBusLineName() {
        return this.b;
    }

    public void setBusLineName(String str) {
        this.b = str;
    }

    public String getBusLineType() {
        return this.c;
    }

    public void setBusLineType(String str) {
        this.c = str;
    }

    public String getCityCode() {
        return this.d;
    }

    public void setCityCode(String str) {
        this.d = str;
    }

    public List<LatLonPoint> getDirectionsCoordinates() {
        return this.e;
    }

    public void setDirectionsCoordinates(List<LatLonPoint> list) {
        this.e = list;
    }

    public List<LatLonPoint> getBounds() {
        return this.f;
    }

    public void setBounds(List<LatLonPoint> list) {
        this.f = list;
    }

    public String getBusLineId() {
        return this.g;
    }

    public void setBusLineId(String str) {
        this.g = str;
    }

    public String getOriginatingStation() {
        return this.h;
    }

    public void setOriginatingStation(String str) {
        this.h = str;
    }

    public String getTerminalStation() {
        return this.i;
    }

    public void setTerminalStation(String str) {
        this.i = str;
    }

    public Date getFirstBusTime() {
        if (this.j == null) {
            return null;
        }
        return (Date) this.j.clone();
    }

    public void setFirstBusTime(Date date) {
        if (date == null) {
            this.j = null;
        } else {
            this.j = (Date) date.clone();
        }
    }

    public Date getLastBusTime() {
        if (this.k == null) {
            return null;
        }
        return (Date) this.k.clone();
    }

    public void setLastBusTime(Date date) {
        if (date == null) {
            this.k = null;
        } else {
            this.k = (Date) date.clone();
        }
    }

    public String getBusCompany() {
        return this.l;
    }

    public void setBusCompany(String str) {
        this.l = str;
    }

    public float getBasicPrice() {
        return this.m;
    }

    public void setBasicPrice(float f) {
        this.m = f;
    }

    public float getTotalPrice() {
        return this.n;
    }

    public void setTotalPrice(float f) {
        this.n = f;
    }

    public List<BusStationItem> getBusStations() {
        return this.o;
    }

    public void setBusStations(List<BusStationItem> list) {
        this.o = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BusLineItem busLineItem = (BusLineItem) obj;
            return this.g == null ? busLineItem.g == null : this.g.equals(busLineItem.g);
        }
        return false;
    }

    public int hashCode() {
        return (this.g == null ? 0 : this.g.hashCode()) + 31;
    }

    public String toString() {
        return this.b + HanziToPinyin.Token.SEPARATOR + i.a(this.j) + "-" + i.a(this.k);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeList(this.e);
        parcel.writeList(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(i.a(this.j));
        parcel.writeString(i.a(this.k));
        parcel.writeString(this.l);
        parcel.writeFloat(this.m);
        parcel.writeFloat(this.n);
        parcel.writeList(this.o);
    }

    public BusLineItem(Parcel parcel) {
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.o = new ArrayList();
        this.a = parcel.readFloat();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.f = parcel.readArrayList(LatLonPoint.class.getClassLoader());
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = i.d(parcel.readString());
        this.k = i.d(parcel.readString());
        this.l = parcel.readString();
        this.m = parcel.readFloat();
        this.n = parcel.readFloat();
        this.o = parcel.readArrayList(BusStationItem.class.getClassLoader());
    }
}

package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RouteRailwayItem extends Railway implements Parcelable {
    public static final Parcelable.Creator<RouteRailwayItem> CREATOR = new Parcelable.Creator<RouteRailwayItem>() { // from class: com.amap.api.services.route.RouteRailwayItem.1
        /* renamed from: a */
        public RouteRailwayItem createFromParcel(Parcel parcel) {
            return new RouteRailwayItem(parcel);
        }

        /* renamed from: a */
        public RouteRailwayItem[] newArray(int i) {
            return new RouteRailwayItem[i];
        }
    };
    private String a;
    private String b;
    private float c;
    private String d;
    private RailwayStationItem e;
    private RailwayStationItem f;
    private List<RailwayStationItem> g;
    private List<Railway> h;
    private List<RailwaySpace> i;

    public RouteRailwayItem() {
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.i = new ArrayList();
    }

    public String getTime() {
        return this.a;
    }

    public String getTrip() {
        return this.b;
    }

    public float getDistance() {
        return this.c;
    }

    public String getType() {
        return this.d;
    }

    public RailwayStationItem getDeparturestop() {
        return this.e;
    }

    public RailwayStationItem getArrivalstop() {
        return this.f;
    }

    public List<RailwayStationItem> getViastops() {
        return this.g;
    }

    public List<Railway> getAlters() {
        return this.h;
    }

    public List<RailwaySpace> getSpaces() {
        return this.i;
    }

    public void setTime(String str) {
        this.a = str;
    }

    public void setTrip(String str) {
        this.b = str;
    }

    public void setDistance(float f) {
        this.c = f;
    }

    public void setType(String str) {
        this.d = str;
    }

    public void setDeparturestop(RailwayStationItem railwayStationItem) {
        this.e = railwayStationItem;
    }

    public void setArrivalstop(RailwayStationItem railwayStationItem) {
        this.f = railwayStationItem;
    }

    public void setViastops(List<RailwayStationItem> list) {
        this.g = list;
    }

    public void setAlters(List<Railway> list) {
        this.h = list;
    }

    public void setSpaces(List<RailwaySpace> list) {
        this.i = list;
    }

    protected RouteRailwayItem(Parcel parcel) {
        super(parcel);
        this.g = new ArrayList();
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readFloat();
        this.d = parcel.readString();
        this.e = (RailwayStationItem) parcel.readParcelable(RailwayStationItem.class.getClassLoader());
        this.f = (RailwayStationItem) parcel.readParcelable(RailwayStationItem.class.getClassLoader());
        this.g = parcel.createTypedArrayList(RailwayStationItem.CREATOR);
        this.h = parcel.createTypedArrayList(Railway.CREATOR);
        this.i = parcel.createTypedArrayList(RailwaySpace.CREATOR);
    }

    @Override // com.amap.api.services.route.Railway, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeFloat(this.c);
        parcel.writeString(this.d);
        parcel.writeParcelable(this.e, i);
        parcel.writeParcelable(this.f, i);
        parcel.writeTypedList(this.g);
        parcel.writeTypedList(this.h);
        parcel.writeTypedList(this.i);
    }

    @Override // com.amap.api.services.route.Railway, android.os.Parcelable
    public int describeContents() {
        return 0;
    }
}

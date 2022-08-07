package com.amap.api.services.route;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.services.a.as;
import com.amap.api.services.a.av;
import com.amap.api.services.a.cf;
import com.amap.api.services.a.h;
import com.amap.api.services.a.i;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.interfaces.IRouteSearch;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class RouteSearch {
    public static final int BUS_COMFORTABLE = 4;
    public static final int BUS_DEFAULT = 0;
    public static final int BUS_LEASE_CHANGE = 2;
    public static final int BUS_LEASE_WALK = 3;
    public static final int BUS_NO_SUBWAY = 5;
    public static final int BUS_SAVE_MONEY = 1;
    public static final int BusComfortable = 4;
    public static final int BusDefault = 0;
    public static final int BusLeaseChange = 2;
    public static final int BusLeaseWalk = 3;
    public static final int BusNoSubway = 5;
    public static final int BusSaveMoney = 1;
    public static final int DRIVING_MULTI_CHOICE_AVOID_CONGESTION = 12;
    public static final int DRIVING_MULTI_CHOICE_AVOID_CONGESTION_NO_HIGHWAY = 15;
    public static final int DRIVING_MULTI_CHOICE_AVOID_CONGESTION_NO_HIGHWAY_SAVE_MONEY = 18;
    public static final int DRIVING_MULTI_CHOICE_AVOID_CONGESTION_SAVE_MONEY = 17;
    public static final int DRIVING_MULTI_CHOICE_HIGHWAY = 19;
    public static final int DRIVING_MULTI_CHOICE_HIGHWAY_AVOID_CONGESTION = 20;
    public static final int DRIVING_MULTI_CHOICE_NO_HIGHWAY = 13;
    public static final int DRIVING_MULTI_CHOICE_SAVE_MONEY = 14;
    public static final int DRIVING_MULTI_CHOICE_SAVE_MONEY_NO_HIGHWAY = 16;
    public static final int DRIVING_MULTI_STRATEGY_FASTEST_SAVE_MONEY_SHORTEST = 5;
    public static final int DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST = 11;
    public static final int DRIVING_MULTI_STRATEGY_FASTEST_SHORTEST_AVOID_CONGESTION = 10;
    public static final int DRIVING_SINGLE_AVOID_CONGESTION = 4;
    public static final int DRIVING_SINGLE_DEFAULT = 0;
    public static final int DRIVING_SINGLE_NO_EXPRESSWAYS = 3;
    public static final int DRIVING_SINGLE_NO_HIGHWAY = 6;
    public static final int DRIVING_SINGLE_NO_HIGHWAY_SAVE_MONEY = 7;
    public static final int DRIVING_SINGLE_NO_HIGHWAY_SAVE_MONEY_AVOID_CONGESTION = 9;
    public static final int DRIVING_SINGLE_SAVE_MONEY = 1;
    public static final int DRIVING_SINGLE_SAVE_MONEY_AVOID_CONGESTION = 8;
    public static final int DRIVING_SINGLE_SHORTEST = 2;
    public static final int DrivingAvoidCongestion = 4;
    public static final int DrivingDefault = 0;
    public static final int DrivingMultiStrategy = 5;
    public static final int DrivingNoExpressways = 3;
    public static final int DrivingNoHighAvoidCongestionSaveMoney = 9;
    public static final int DrivingNoHighWay = 6;
    public static final int DrivingNoHighWaySaveMoney = 7;
    public static final int DrivingSaveMoney = 1;
    public static final int DrivingSaveMoneyAvoidCongestion = 8;
    public static final int DrivingShortDistance = 2;
    public static final int RIDING_DEFAULT = 0;
    public static final int RIDING_FAST = 2;
    public static final int RIDING_RECOMMEND = 1;
    public static final int RidingDefault = 0;
    public static final int RidingFast = 2;
    public static final int RidingRecommend = 1;
    public static final int WALK_DEFAULT = 0;
    public static final int WALK_MULTI_PATH = 1;
    public static final int WalkDefault = 0;
    public static final int WalkMultipath = 1;
    private IRouteSearch a;

    /* loaded from: classes.dex */
    public interface OnRouteSearchListener {
        void onBusRouteSearched(BusRouteResult busRouteResult, int i);

        void onDriveRouteSearched(DriveRouteResult driveRouteResult, int i);

        void onRideRouteSearched(RideRouteResult rideRouteResult, int i);

        void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i);
    }

    public RouteSearch(Context context) {
        try {
            this.a = (IRouteSearch) cf.a(context, h.a(true), "com.amap.api.services.dynamic.RouteSearchWrapper", as.class, new Class[]{Context.class}, new Object[]{context});
        } catch (av e) {
            e.printStackTrace();
        }
        if (this.a == null) {
            try {
                this.a = new as(context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void setRouteSearchListener(OnRouteSearchListener onRouteSearchListener) {
        if (this.a != null) {
            this.a.setRouteSearchListener(onRouteSearchListener);
        }
    }

    public WalkRouteResult calculateWalkRoute(WalkRouteQuery walkRouteQuery) throws AMapException {
        if (this.a != null) {
            return this.a.calculateWalkRoute(walkRouteQuery);
        }
        return null;
    }

    public void calculateWalkRouteAsyn(WalkRouteQuery walkRouteQuery) {
        if (this.a != null) {
            this.a.calculateWalkRouteAsyn(walkRouteQuery);
        }
    }

    public BusRouteResult calculateBusRoute(BusRouteQuery busRouteQuery) throws AMapException {
        if (this.a != null) {
            return this.a.calculateBusRoute(busRouteQuery);
        }
        return null;
    }

    public void calculateBusRouteAsyn(BusRouteQuery busRouteQuery) {
        if (this.a != null) {
            this.a.calculateBusRouteAsyn(busRouteQuery);
        }
    }

    public DriveRouteResult calculateDriveRoute(DriveRouteQuery driveRouteQuery) throws AMapException {
        if (this.a != null) {
            return this.a.calculateDriveRoute(driveRouteQuery);
        }
        return null;
    }

    public void calculateDriveRouteAsyn(DriveRouteQuery driveRouteQuery) {
        if (this.a != null) {
            this.a.calculateDriveRouteAsyn(driveRouteQuery);
        }
    }

    public void calculateRideRouteAsyn(RideRouteQuery rideRouteQuery) {
        if (this.a != null) {
            this.a.calculateRideRouteAsyn(rideRouteQuery);
        }
    }

    public RideRouteResult calculateRideRoute(RideRouteQuery rideRouteQuery) throws AMapException {
        if (this.a != null) {
            return this.a.calculateRideRoute(rideRouteQuery);
        }
        return null;
    }

    /* loaded from: classes.dex */
    public static class FromAndTo implements Parcelable, Cloneable {
        public static final Parcelable.Creator<FromAndTo> CREATOR = new Parcelable.Creator<FromAndTo>() { // from class: com.amap.api.services.route.RouteSearch.FromAndTo.1
            /* renamed from: a */
            public FromAndTo createFromParcel(Parcel parcel) {
                return new FromAndTo(parcel);
            }

            /* renamed from: a */
            public FromAndTo[] newArray(int i) {
                return new FromAndTo[i];
            }
        };
        private LatLonPoint a;
        private LatLonPoint b;
        private String c;
        private String d;
        private String e;
        private String f;

        public FromAndTo(LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
            this.a = latLonPoint;
            this.b = latLonPoint2;
        }

        public LatLonPoint getFrom() {
            return this.a;
        }

        public LatLonPoint getTo() {
            return this.b;
        }

        public String getStartPoiID() {
            return this.c;
        }

        public void setStartPoiID(String str) {
            this.c = str;
        }

        public String getDestinationPoiID() {
            return this.d;
        }

        public void setDestinationPoiID(String str) {
            this.d = str;
        }

        public String getOriginType() {
            return this.e;
        }

        public void setOriginType(String str) {
            this.e = str;
        }

        public String getDestinationType() {
            return this.f;
        }

        public void setDestinationType(String str) {
            this.f = str;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeParcelable(this.b, i);
            parcel.writeString(this.c);
            parcel.writeString(this.d);
            parcel.writeString(this.e);
            parcel.writeString(this.f);
        }

        public FromAndTo(Parcel parcel) {
            this.a = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.b = (LatLonPoint) parcel.readParcelable(LatLonPoint.class.getClassLoader());
            this.c = parcel.readString();
            this.d = parcel.readString();
            this.e = parcel.readString();
            this.f = parcel.readString();
        }

        public FromAndTo() {
        }

        public int hashCode() {
            int hashCode;
            int hashCode2;
            int hashCode3;
            int hashCode4;
            int hashCode5;
            int i = 0;
            if (this.d == null) {
                hashCode = 0;
            } else {
                hashCode = this.d.hashCode();
            }
            int i2 = (hashCode + 31) * 31;
            if (this.a == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = this.a.hashCode();
            }
            int i3 = (hashCode2 + i2) * 31;
            if (this.c == null) {
                hashCode3 = 0;
            } else {
                hashCode3 = this.c.hashCode();
            }
            int i4 = (hashCode3 + i3) * 31;
            if (this.b == null) {
                hashCode4 = 0;
            } else {
                hashCode4 = this.b.hashCode();
            }
            int i5 = (hashCode4 + i4) * 31;
            if (this.e == null) {
                hashCode5 = 0;
            } else {
                hashCode5 = this.e.hashCode();
            }
            int i6 = (hashCode5 + i5) * 31;
            if (this.f != null) {
                i = this.f.hashCode();
            }
            return i6 + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                FromAndTo fromAndTo = (FromAndTo) obj;
                if (this.d == null) {
                    if (fromAndTo.d != null) {
                        return false;
                    }
                } else if (!this.d.equals(fromAndTo.d)) {
                    return false;
                }
                if (this.a == null) {
                    if (fromAndTo.a != null) {
                        return false;
                    }
                } else if (!this.a.equals(fromAndTo.a)) {
                    return false;
                }
                if (this.c == null) {
                    if (fromAndTo.c != null) {
                        return false;
                    }
                } else if (!this.c.equals(fromAndTo.c)) {
                    return false;
                }
                if (this.b == null) {
                    if (fromAndTo.b != null) {
                        return false;
                    }
                } else if (!this.b.equals(fromAndTo.b)) {
                    return false;
                }
                if (this.e == null) {
                    if (fromAndTo.e != null) {
                        return false;
                    }
                } else if (!this.e.equals(fromAndTo.e)) {
                    return false;
                }
                return this.f == null ? fromAndTo.f == null : this.f.equals(fromAndTo.f);
            }
            return false;
        }

        public FromAndTo clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                i.a(e, "RouteSearch", "FromAndToclone");
            }
            FromAndTo fromAndTo = new FromAndTo(this.a, this.b);
            fromAndTo.setStartPoiID(this.c);
            fromAndTo.setDestinationPoiID(this.d);
            fromAndTo.setOriginType(this.e);
            fromAndTo.setDestinationType(this.f);
            return fromAndTo;
        }
    }

    /* loaded from: classes.dex */
    public static class BusRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<BusRouteQuery> CREATOR = new Parcelable.Creator<BusRouteQuery>() { // from class: com.amap.api.services.route.RouteSearch.BusRouteQuery.1
            /* renamed from: a */
            public BusRouteQuery createFromParcel(Parcel parcel) {
                return new BusRouteQuery(parcel);
            }

            /* renamed from: a */
            public BusRouteQuery[] newArray(int i) {
                return new BusRouteQuery[i];
            }
        };
        private FromAndTo a;
        private int b;
        private String c;
        private String d;
        private int e;

        public BusRouteQuery(FromAndTo fromAndTo, int i, String str, int i2) {
            this.a = fromAndTo;
            this.b = i;
            this.c = str;
            this.e = i2;
        }

        public FromAndTo getFromAndTo() {
            return this.a;
        }

        public int getMode() {
            return this.b;
        }

        public String getCity() {
            return this.c;
        }

        public int getNightFlag() {
            return this.e;
        }

        public String getCityd() {
            return this.d;
        }

        public void setCityd(String str) {
            this.d = str;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeInt(this.b);
            parcel.writeString(this.c);
            parcel.writeInt(this.e);
            parcel.writeString(this.d);
        }

        public BusRouteQuery(Parcel parcel) {
            this.a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.readString();
            this.e = parcel.readInt();
            this.d = parcel.readString();
        }

        public BusRouteQuery() {
        }

        public int hashCode() {
            int hashCode;
            int hashCode2;
            int i = 0;
            if (this.c == null) {
                hashCode = 0;
            } else {
                hashCode = this.c.hashCode();
            }
            int i2 = (hashCode + 31) * 31;
            if (this.a == null) {
                hashCode2 = 0;
            } else {
                hashCode2 = this.a.hashCode();
            }
            int i3 = (((((hashCode2 + i2) * 31) + this.b) * 31) + this.e) * 31;
            if (this.d != null) {
                i = this.d.hashCode();
            }
            return i3 + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                BusRouteQuery busRouteQuery = (BusRouteQuery) obj;
                if (this.c == null) {
                    if (busRouteQuery.c != null) {
                        return false;
                    }
                } else if (!this.c.equals(busRouteQuery.c)) {
                    return false;
                }
                if (this.d == null) {
                    if (busRouteQuery.d != null) {
                        return false;
                    }
                } else if (!this.d.equals(busRouteQuery.d)) {
                    return false;
                }
                if (this.a == null) {
                    if (busRouteQuery.a != null) {
                        return false;
                    }
                } else if (!this.a.equals(busRouteQuery.a)) {
                    return false;
                }
                return this.b == busRouteQuery.b && this.e == busRouteQuery.e;
            }
            return false;
        }

        public BusRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                i.a(e, "RouteSearch", "BusRouteQueryclone");
            }
            BusRouteQuery busRouteQuery = new BusRouteQuery(this.a, this.b, this.c, this.e);
            busRouteQuery.setCityd(this.d);
            return busRouteQuery;
        }
    }

    /* loaded from: classes.dex */
    public static class WalkRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<WalkRouteQuery> CREATOR = new Parcelable.Creator<WalkRouteQuery>() { // from class: com.amap.api.services.route.RouteSearch.WalkRouteQuery.1
            /* renamed from: a */
            public WalkRouteQuery createFromParcel(Parcel parcel) {
                return new WalkRouteQuery(parcel);
            }

            /* renamed from: a */
            public WalkRouteQuery[] newArray(int i) {
                return new WalkRouteQuery[i];
            }
        };
        private FromAndTo a;
        private int b;

        public WalkRouteQuery(FromAndTo fromAndTo, int i) {
            this.a = fromAndTo;
            this.b = i;
        }

        public WalkRouteQuery(FromAndTo fromAndTo) {
            this.a = fromAndTo;
        }

        public FromAndTo getFromAndTo() {
            return this.a;
        }

        public int getMode() {
            return this.b;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeInt(this.b);
        }

        public WalkRouteQuery(Parcel parcel) {
            this.a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
        }

        public WalkRouteQuery() {
        }

        public int hashCode() {
            return (((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
                if (this.a == null) {
                    if (walkRouteQuery.a != null) {
                        return false;
                    }
                } else if (!this.a.equals(walkRouteQuery.a)) {
                    return false;
                }
                return this.b == walkRouteQuery.b;
            }
            return false;
        }

        public WalkRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                i.a(e, "RouteSearch", "WalkRouteQueryclone");
            }
            return new WalkRouteQuery(this.a);
        }
    }

    /* loaded from: classes.dex */
    public static class DriveRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<DriveRouteQuery> CREATOR = new Parcelable.Creator<DriveRouteQuery>() { // from class: com.amap.api.services.route.RouteSearch.DriveRouteQuery.1
            /* renamed from: a */
            public DriveRouteQuery createFromParcel(Parcel parcel) {
                return new DriveRouteQuery(parcel);
            }

            /* renamed from: a */
            public DriveRouteQuery[] newArray(int i) {
                return new DriveRouteQuery[i];
            }
        };
        private FromAndTo a;
        private int b;
        private List<LatLonPoint> c;
        private List<List<LatLonPoint>> d;
        private String e;

        public DriveRouteQuery(FromAndTo fromAndTo, int i, List<LatLonPoint> list, List<List<LatLonPoint>> list2, String str) {
            this.a = fromAndTo;
            this.b = i;
            this.c = list;
            this.d = list2;
            this.e = str;
        }

        public FromAndTo getFromAndTo() {
            return this.a;
        }

        public int getMode() {
            return this.b;
        }

        public List<LatLonPoint> getPassedByPoints() {
            return this.c;
        }

        public List<List<LatLonPoint>> getAvoidpolygons() {
            return this.d;
        }

        public String getAvoidRoad() {
            return this.e;
        }

        public String getPassedPointStr() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.c == null || this.c.size() == 0) {
                return null;
            }
            for (int i = 0; i < this.c.size(); i++) {
                LatLonPoint latLonPoint = this.c.get(i);
                stringBuffer.append(latLonPoint.getLongitude());
                stringBuffer.append(",");
                stringBuffer.append(latLonPoint.getLatitude());
                if (i < this.c.size() - 1) {
                    stringBuffer.append(com.alipay.sdk.util.h.b);
                }
            }
            return stringBuffer.toString();
        }

        public boolean hasPassPoint() {
            return !i.a(getPassedPointStr());
        }

        public String getAvoidpolygonsStr() {
            StringBuffer stringBuffer = new StringBuffer();
            if (this.d == null || this.d.size() == 0) {
                return null;
            }
            for (int i = 0; i < this.d.size(); i++) {
                List<LatLonPoint> list = this.d.get(i);
                for (int i2 = 0; i2 < list.size(); i2++) {
                    LatLonPoint latLonPoint = list.get(i2);
                    stringBuffer.append(latLonPoint.getLongitude());
                    stringBuffer.append(",");
                    stringBuffer.append(latLonPoint.getLatitude());
                    if (i2 < list.size() - 1) {
                        stringBuffer.append(com.alipay.sdk.util.h.b);
                    }
                }
                if (i < this.d.size() - 1) {
                    stringBuffer.append("|");
                }
            }
            return stringBuffer.toString();
        }

        public boolean hasAvoidpolygons() {
            return !i.a(getAvoidpolygonsStr());
        }

        public boolean hasAvoidRoad() {
            return !i.a(getAvoidRoad());
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeInt(this.b);
            parcel.writeTypedList(this.c);
            if (this.d == null) {
                parcel.writeInt(0);
            } else {
                parcel.writeInt(this.d.size());
                for (List<LatLonPoint> list : this.d) {
                    parcel.writeTypedList(list);
                }
            }
            parcel.writeString(this.e);
        }

        public DriveRouteQuery(Parcel parcel) {
            this.a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
            this.c = parcel.createTypedArrayList(LatLonPoint.CREATOR);
            int readInt = parcel.readInt();
            if (readInt == 0) {
                this.d = null;
            } else {
                this.d = new ArrayList();
            }
            for (int i = 0; i < readInt; i++) {
                this.d.add(parcel.createTypedArrayList(LatLonPoint.CREATOR));
            }
            this.e = parcel.readString();
        }

        public DriveRouteQuery() {
        }

        public int hashCode() {
            int hashCode;
            int hashCode2;
            int hashCode3;
            int i = 0;
            if (this.e == null) {
                hashCode = 0;
            } else {
                hashCode = this.e.hashCode();
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
            int i4 = (((hashCode3 + i3) * 31) + this.b) * 31;
            if (this.c != null) {
                i = this.c.hashCode();
            }
            return i4 + i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                DriveRouteQuery driveRouteQuery = (DriveRouteQuery) obj;
                if (this.e == null) {
                    if (driveRouteQuery.e != null) {
                        return false;
                    }
                } else if (!this.e.equals(driveRouteQuery.e)) {
                    return false;
                }
                if (this.d == null) {
                    if (driveRouteQuery.d != null) {
                        return false;
                    }
                } else if (!this.d.equals(driveRouteQuery.d)) {
                    return false;
                }
                if (this.a == null) {
                    if (driveRouteQuery.a != null) {
                        return false;
                    }
                } else if (!this.a.equals(driveRouteQuery.a)) {
                    return false;
                }
                if (this.b != driveRouteQuery.b) {
                    return false;
                }
                return this.c == null ? driveRouteQuery.c == null : this.c.equals(driveRouteQuery.c);
            }
            return false;
        }

        public DriveRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                i.a(e, "RouteSearch", "DriveRouteQueryclone");
            }
            return new DriveRouteQuery(this.a, this.b, this.c, this.d, this.e);
        }
    }

    /* loaded from: classes.dex */
    public static class RideRouteQuery implements Parcelable, Cloneable {
        public static final Parcelable.Creator<RideRouteQuery> CREATOR = new Parcelable.Creator<RideRouteQuery>() { // from class: com.amap.api.services.route.RouteSearch.RideRouteQuery.1
            /* renamed from: a */
            public RideRouteQuery createFromParcel(Parcel parcel) {
                return new RideRouteQuery(parcel);
            }

            /* renamed from: a */
            public RideRouteQuery[] newArray(int i) {
                return new RideRouteQuery[i];
            }
        };
        private FromAndTo a;
        private int b;

        public RideRouteQuery(FromAndTo fromAndTo, int i) {
            this.a = fromAndTo;
            this.b = i;
        }

        public RideRouteQuery(FromAndTo fromAndTo) {
            this.a = fromAndTo;
        }

        public FromAndTo getFromAndTo() {
            return this.a;
        }

        public int getMode() {
            return this.b;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.a, i);
            parcel.writeInt(this.b);
        }

        public RideRouteQuery(Parcel parcel) {
            this.a = (FromAndTo) parcel.readParcelable(FromAndTo.class.getClassLoader());
            this.b = parcel.readInt();
        }

        public RideRouteQuery() {
        }

        public int hashCode() {
            return (((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                WalkRouteQuery walkRouteQuery = (WalkRouteQuery) obj;
                if (this.a == null) {
                    if (walkRouteQuery.a != null) {
                        return false;
                    }
                } else if (!this.a.equals(walkRouteQuery.a)) {
                    return false;
                }
                return this.b == walkRouteQuery.b;
            }
            return false;
        }

        public RideRouteQuery clone() {
            try {
                super.clone();
            } catch (CloneNotSupportedException e) {
                i.a(e, "RouteSearch", "RideRouteQueryclone");
            }
            return new RideRouteQuery(this.a);
        }
    }
}

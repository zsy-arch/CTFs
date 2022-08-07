package com.amap.api.services.busline;

import com.amap.api.services.a.i;

/* loaded from: classes.dex */
public class BusStationQuery implements Cloneable {
    private String a;
    private String b;
    private int c = 10;
    private int d = 0;

    public BusStationQuery(String str, String str2) {
        this.a = str;
        this.b = str2;
        if (!a()) {
            new IllegalArgumentException("Empty query").printStackTrace();
        }
    }

    private boolean a() {
        return !i.a(this.a);
    }

    public String getQueryString() {
        return this.a;
    }

    public String getCity() {
        return this.b;
    }

    public int getPageSize() {
        return this.c;
    }

    public int getPageNumber() {
        return this.d;
    }

    public void setQueryString(String str) {
        this.a = str;
    }

    public void setCity(String str) {
        this.b = str;
    }

    public void setPageSize(int i) {
        int i2 = 20;
        if (i <= 20) {
            i2 = i;
        }
        if (i2 <= 0) {
            i2 = 10;
        }
        this.c = i2;
    }

    public void setPageNumber(int i) {
        this.d = i;
    }

    public BusStationQuery clone() {
        BusStationQuery busStationQuery = new BusStationQuery(this.a, this.b);
        busStationQuery.setPageNumber(this.d);
        busStationQuery.setPageSize(this.c);
        return busStationQuery;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.b == null) {
            hashCode = 0;
        } else {
            hashCode = this.b.hashCode();
        }
        int i2 = (((((hashCode + 31) * 31) + this.d) * 31) + this.c) * 31;
        if (this.a != null) {
            i = this.a.hashCode();
        }
        return i2 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            BusStationQuery busStationQuery = (BusStationQuery) obj;
            if (this.b == null) {
                if (busStationQuery.b != null) {
                    return false;
                }
            } else if (!this.b.equals(busStationQuery.b)) {
                return false;
            }
            if (this.d == busStationQuery.d && this.c == busStationQuery.c) {
                return this.a == null ? busStationQuery.a == null : this.a.equals(busStationQuery.a);
            }
            return false;
        }
        return false;
    }

    public boolean weakEquals(BusStationQuery busStationQuery) {
        if (this == busStationQuery) {
            return true;
        }
        if (busStationQuery == null) {
            return false;
        }
        if (this.b == null) {
            if (busStationQuery.b != null) {
                return false;
            }
        } else if (!this.b.equals(busStationQuery.b)) {
            return false;
        }
        if (this.c != busStationQuery.c) {
            return false;
        }
        return this.a == null ? busStationQuery.a == null : this.a.equals(busStationQuery.a);
    }
}

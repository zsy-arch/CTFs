package com.amap.api.services.geocoder;

/* loaded from: classes.dex */
public class GeocodeQuery {
    private String a;
    private String b;

    public GeocodeQuery(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String getLocationName() {
        return this.a;
    }

    public void setLocationName(String str) {
        this.a = str;
    }

    public String getCity() {
        return this.b;
    }

    public void setCity(String str) {
        this.b = str;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.b == null) {
            hashCode = 0;
        } else {
            hashCode = this.b.hashCode();
        }
        int i2 = (hashCode + 31) * 31;
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
            GeocodeQuery geocodeQuery = (GeocodeQuery) obj;
            if (this.b == null) {
                if (geocodeQuery.b != null) {
                    return false;
                }
            } else if (!this.b.equals(geocodeQuery.b)) {
                return false;
            }
            return this.a == null ? geocodeQuery.a == null : this.a.equals(geocodeQuery.a);
        }
        return false;
    }
}

package com.vsf2f.f2f.bean;

import java.io.Serializable;

/* loaded from: classes2.dex */
public class MyLatLng implements Serializable {
    public String latitude;
    public String longitude;

    MyLatLng(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

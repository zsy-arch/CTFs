package com.amap.api.navi;

import android.location.Location;

/* loaded from: classes.dex */
public interface IGpsCallback {
    public static final int GPS_OFFSET_AFTER = 2;
    public static final int GPS_OFFSET_BEFORE = 1;

    void onGpsStarted();

    void onLocationChanged(int i, Location location);
}

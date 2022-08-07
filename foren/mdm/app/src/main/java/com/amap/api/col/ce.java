package com.amap.api.col;

import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.interfaces.IMarker;

/* compiled from: IMarkerDelegate.java */
/* loaded from: classes.dex */
public interface ce extends ch, IMarker {
    FPoint a();

    void a(boolean z);

    LatLng b();

    int c();

    int d();

    int e();

    int f();

    boolean g();

    boolean isInfoWindowEnable();
}

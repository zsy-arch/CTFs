package com.autonavi.ae.gmap.glinterface;

import android.graphics.Point;

/* loaded from: classes.dex */
public interface IProjection {
    GLGeoPoint fromPixels(int i, int i2, int i3);

    Point toPixels(int i, GLGeoPoint gLGeoPoint, Point point);
}

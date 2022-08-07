package com.autonavi.ae.gmap.listener;

/* loaded from: classes.dex */
public interface MapWidgetListener {
    void invalidateCompassView();

    void invalidateScaleView();

    void invalidateZoomController(float f);

    void setFrontViewVisibility(boolean z);
}

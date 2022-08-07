package com.amap.api.col;

import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.CameraUpdateMessage;

/* compiled from: CameraZoomMessage.java */
/* loaded from: classes.dex */
public class aa extends CameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.CameraUpdateMessage
    public void runCameraUpdate(GLMapState gLMapState) {
        this.zoom = gLMapState.getMapZoomer() + this.amount;
        this.zoom = dt.a(this.mapConfig, this.zoom);
        normalChange(gLMapState);
    }

    @Override // com.autonavi.amap.mapcore.CameraUpdateMessage
    public void mergeCameraUpdateDelegate(CameraUpdateMessage cameraUpdateMessage) {
    }
}

package com.amap.api.col;

import android.util.Pair;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.CameraUpdateMessage;
import com.autonavi.amap.mapcore.IPoint;

/* compiled from: CameraBoundsMessage.java */
/* loaded from: classes.dex */
public class w extends CameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.CameraUpdateMessage
    public void runCameraUpdate(GLMapState gLMapState) {
        Pair<Float, IPoint> a = dt.a(this, gLMapState, this.mapConfig);
        gLMapState.setMapZoomer(((Float) a.first).floatValue());
        gLMapState.setMapGeoCenter(((IPoint) a.second).x, ((IPoint) a.second).y);
        gLMapState.setCameraDegree(0.0f);
        gLMapState.setMapAngle(0.0f);
    }

    @Override // com.autonavi.amap.mapcore.CameraUpdateMessage
    public void mergeCameraUpdateDelegate(CameraUpdateMessage cameraUpdateMessage) {
    }
}

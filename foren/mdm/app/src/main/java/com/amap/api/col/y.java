package com.amap.api.col;

import android.graphics.Point;
import com.autonavi.ae.gmap.GLMapState;
import com.autonavi.amap.mapcore.CameraUpdateMessage;

/* compiled from: CameraScrollMessage.java */
/* loaded from: classes.dex */
public class y extends CameraUpdateMessage {
    @Override // com.autonavi.amap.mapcore.CameraUpdateMessage
    public void runCameraUpdate(GLMapState gLMapState) {
        float f = this.xPixel;
        float f2 = this.yPixel;
        float f3 = f + (this.width / 2.0f);
        float f4 = f2 + (this.height / 2.0f);
        Point point = new Point();
        a(gLMapState, (int) f3, (int) f4, point);
        gLMapState.setMapGeoCenter(point.x, point.y);
    }

    @Override // com.autonavi.amap.mapcore.CameraUpdateMessage
    public void mergeCameraUpdateDelegate(CameraUpdateMessage cameraUpdateMessage) {
    }

    public void a(GLMapState gLMapState, int i, int i2, Point point) {
        gLMapState.screenToP20Point(i, i2, point);
    }
}

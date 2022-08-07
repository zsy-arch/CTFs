package com.amap.api.maps;

import com.autonavi.amap.mapcore.CameraUpdateMessage;

/* loaded from: classes.dex */
public final class CameraUpdate {
    CameraUpdateMessage a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CameraUpdate(CameraUpdateMessage cameraUpdateMessage) {
        this.a = cameraUpdateMessage;
    }

    public CameraUpdateMessage getCameraUpdateFactoryDelegate() {
        return this.a;
    }
}

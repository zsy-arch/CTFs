package com.amap.api.maps.model;

import com.autonavi.ae.gmap.gloverlay.CrossVectorOverlay;
import com.autonavi.ae.gmap.gloverlay.GLCrossVector;

/* loaded from: classes.dex */
public class CrossOverlay {
    CrossVectorOverlay a;
    CrossOverlayOptions b;

    public CrossOverlay(CrossOverlayOptions crossOverlayOptions, CrossVectorOverlay crossVectorOverlay) {
        this.a = null;
        this.b = null;
        this.b = crossOverlayOptions;
        this.a = crossVectorOverlay;
    }

    public void setData(byte[] bArr) {
        if (bArr != null && this.a != null) {
            this.a.setData(bArr);
        }
    }

    public void setAttribute(GLCrossVector.AVectorCrossAttr aVectorCrossAttr) {
        this.a.setAttribute(aVectorCrossAttr);
    }

    public void setVisible(boolean z) {
        if (this.a != null) {
            this.a.setVisible(z);
        }
    }

    public void remove() {
        if (this.a != null) {
            this.a.remove();
        }
    }
}

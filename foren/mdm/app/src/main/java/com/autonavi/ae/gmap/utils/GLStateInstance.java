package com.autonavi.ae.gmap.utils;

import com.autonavi.ae.gmap.gloverlay.BaseMapOverlay;
import com.autonavi.ae.gmap.gloverlay.GLOverlayBundle;

/* loaded from: classes.dex */
public class GLStateInstance {
    private int mEngineID;
    public GLOverlayBundle<BaseMapOverlay<?, ?>> mOverlayBundle = null;
    private long mStateInstance;

    public GLStateInstance(int i, long j) {
        this.mStateInstance = 0L;
        this.mEngineID = 0;
        this.mEngineID = i;
        this.mStateInstance = j;
    }

    public long getStateInstance() {
        return this.mStateInstance;
    }

    public int getEngineID() {
        return this.mEngineID;
    }
}

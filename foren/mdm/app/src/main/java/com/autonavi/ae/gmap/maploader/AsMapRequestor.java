package com.autonavi.ae.gmap.maploader;

/* compiled from: ConnectionManager.java */
/* loaded from: classes.dex */
class AsMapRequestor implements Runnable {
    public BaseMapLoader mMapLoader;

    public AsMapRequestor(BaseMapLoader baseMapLoader) {
        this.mMapLoader = null;
        this.mMapLoader = baseMapLoader;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.mMapLoader.doRequest();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

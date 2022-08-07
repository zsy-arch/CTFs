package com.autonavi.ae.gmap.maploader;

import java.util.Hashtable;
import java.util.Map;

/* loaded from: classes.dex */
public class TilesProcessingCtrl {
    private static final int EXPIRED_MAX_TIME = 60;
    private Hashtable<String, ProcessingTile> mProcessingTiles = new Hashtable<>();
    private int mRequireSize = 0;

    public synchronized void removeTile(String str) {
        this.mProcessingTiles.remove(str).recycle();
    }

    public synchronized boolean isProcessing(String str) {
        boolean z;
        if (this.mProcessingTiles.get(str) != null) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public synchronized void addProcessingTile(String str) {
        this.mProcessingTiles.put(str, ProcessingTile.obtain(str));
    }

    public void clearAll() {
        for (Map.Entry<String, ProcessingTile> entry : this.mProcessingTiles.entrySet()) {
            entry.getValue().recycle();
        }
        this.mProcessingTiles.clear();
    }

    public synchronized int getSize() {
        return this.mProcessingTiles.size();
    }
}

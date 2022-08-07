package com.autonavi.ae.gmap.maploader;

import com.autonavi.ae.gmap.maploader.Pools;

/* loaded from: classes.dex */
public class MapSourceGridData {
    private static final Pools.SynchronizedPool<MapSourceGridData> mPool = new Pools.SynchronizedPool<>(80);
    public String mGridName;
    public int mIndoorIndex;
    public int mIndoorVersion;
    private StringBuilder mKeyGridName;
    public Object mObj;
    public int mSourceType;

    public MapSourceGridData() {
        this.mObj = null;
        this.mKeyGridName = new StringBuilder("");
        this.mGridName = "";
        this.mSourceType = 0;
    }

    public static MapSourceGridData obtain() {
        MapSourceGridData acquire = mPool.acquire();
        return acquire != null ? acquire : new MapSourceGridData();
    }

    public void recycle() {
        mPool.release(this);
    }

    public MapSourceGridData(String str, int i) {
        this.mObj = null;
        this.mKeyGridName = new StringBuilder("");
        setGridData(str, i);
    }

    public void setGridData(String str, int i) {
        this.mGridName = str;
        this.mSourceType = i;
        this.mKeyGridName.delete(0, this.mKeyGridName.length());
        this.mKeyGridName.append(this.mGridName);
        this.mKeyGridName.append("-");
        this.mKeyGridName.append(i);
    }

    public MapSourceGridData(String str, String str2, int i) {
        this.mObj = null;
        this.mKeyGridName = new StringBuilder("");
        setGridData(str, str2, i);
    }

    public void setGridData(String str, String str2, int i) {
        this.mGridName = str;
        this.mSourceType = i;
        this.mKeyGridName.delete(0, this.mKeyGridName.length());
        this.mKeyGridName.append(this.mGridName);
        this.mKeyGridName.append("-");
        this.mKeyGridName.append(i);
        this.mKeyGridName.append("-");
        this.mKeyGridName.append(str2);
    }

    public MapSourceGridData(String str, int i, int i2, int i3) {
        this.mObj = null;
        this.mKeyGridName = new StringBuilder("");
        setGridData(str, i, i2, i3);
    }

    public void setGridData(String str, int i, int i2, int i3) {
        this.mGridName = str;
        this.mIndoorIndex = i2;
        this.mIndoorVersion = i3;
        this.mSourceType = i;
        this.mKeyGridName.delete(0, this.mKeyGridName.length());
        this.mKeyGridName.append(this.mGridName);
        this.mKeyGridName.append("-");
        this.mKeyGridName.append(i);
        this.mKeyGridName.append("-");
        this.mKeyGridName.append(i2);
    }

    public String getKeyGridName() {
        return this.mKeyGridName.toString();
    }

    public int getSourceType() {
        return this.mSourceType;
    }

    public String getGridName() {
        return this.mGridName;
    }
}

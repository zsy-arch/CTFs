package com.autonavi.ae.gmap.maploader;

import com.autonavi.ae.gmap.maploader.Pools;

/* loaded from: classes.dex */
public class ProcessingTile {
    private static final Pools.SynchronizedPool<ProcessingTile> mPool = new Pools.SynchronizedPool<>(30);
    public long mCreateTime = 0;
    public String mKeyName;

    public static ProcessingTile obtain(String str) {
        ProcessingTile acquire = mPool.acquire();
        if (acquire == null) {
            return new ProcessingTile(str);
        }
        acquire.setParams(str);
        return acquire;
    }

    public void recycle() {
        mPool.release(this);
    }

    public ProcessingTile(String str) {
        setParams(str);
    }

    private void setParams(String str) {
        this.mKeyName = str;
        this.mCreateTime = System.currentTimeMillis() / 1000;
    }
}

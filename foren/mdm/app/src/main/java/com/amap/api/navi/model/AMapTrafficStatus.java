package com.amap.api.navi.model;

import com.autonavi.ae.route.model.TmcBarItem;

/* loaded from: classes.dex */
public class AMapTrafficStatus {
    private int mLength;
    private int mStatus;

    public AMapTrafficStatus(TmcBarItem tmcBarItem) {
        this.mStatus = tmcBarItem.status;
        this.mLength = tmcBarItem.length;
    }

    public int getStatus() {
        return this.mStatus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatus(int i) {
        this.mStatus = i;
    }

    public int getLength() {
        return this.mLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLength(int i) {
        this.mLength = i;
    }
}

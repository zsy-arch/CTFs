package com.amap.api.navi.model;

import com.autonavi.ae.route.model.TmcBarItem;

/* loaded from: classes.dex */
public class TrafficStatus extends TmcBarItem {
    public AMapTrafficStatus trafficStatus;

    public TrafficStatus(TmcBarItem tmcBarItem) {
        this.status = tmcBarItem.status;
        this.length = tmcBarItem.length;
        this.trafficStatus = new AMapTrafficStatus(tmcBarItem);
    }

    public int getStatus() {
        return this.status;
    }

    void setStatus(int i) {
        this.status = i;
        this.trafficStatus.setStatus(i);
    }

    public int getLength() {
        return this.length;
    }

    void setLength(int i) {
        this.length = i;
        this.trafficStatus.setLength(i);
    }
}

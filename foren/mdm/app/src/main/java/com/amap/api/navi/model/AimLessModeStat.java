package com.amap.api.navi.model;

import com.autonavi.ae.guide.model.NoNaviInfor;

/* loaded from: classes.dex */
public class AimLessModeStat {
    private int aimlessModeDistance;
    private int aimlessModeTime;

    public AimLessModeStat(NoNaviInfor noNaviInfor) {
        this.aimlessModeDistance = noNaviInfor.noNaviDriveDist;
        this.aimlessModeTime = noNaviInfor.noNaviDriveTime;
    }

    public int getAimlessModeDistance() {
        return this.aimlessModeDistance;
    }

    public void setAimlessModeDistance(int i) {
        this.aimlessModeDistance = i;
    }

    public int getAimlessModeTime() {
        return this.aimlessModeTime;
    }

    public void setAimlessModeTime(int i) {
        this.aimlessModeTime = i;
    }
}

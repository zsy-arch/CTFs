package com.amap.api.navi.model;

/* loaded from: classes.dex */
public class AMapNaviRoadStatus {
    private int delaytime;
    private int passtime;
    private int speed;
    private int status;

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int i) {
        this.speed = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public int getPasstime() {
        return this.passtime;
    }

    public void setPasstime(int i) {
        this.passtime = i;
    }

    public int getDelaytime() {
        return this.delaytime;
    }

    public void setDelaytime(int i) {
        this.delaytime = i;
    }
}

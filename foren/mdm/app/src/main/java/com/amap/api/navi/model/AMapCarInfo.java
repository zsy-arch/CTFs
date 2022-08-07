package com.amap.api.navi.model;

/* loaded from: classes.dex */
public class AMapCarInfo {
    private boolean VehicleLoadSwitch;
    private boolean isRestriction;
    private String mCarNumber;
    private String mCarType;
    private String mVehicleHeight;
    private String mVehicleLoad;

    public String getCarNumber() {
        return this.mCarNumber;
    }

    public void setCarNumber(String str) {
        this.mCarNumber = str;
    }

    public boolean isRestriction() {
        return this.isRestriction;
    }

    public void setRestriction(boolean z) {
        this.isRestriction = z;
    }

    public String getCarType() {
        return this.mCarType;
    }

    public void setCarType(String str) {
        this.mCarType = str;
    }

    public String getVehicleHeight() {
        return this.mVehicleHeight;
    }

    public void setVehicleHeight(String str) {
        this.mVehicleHeight = str;
    }

    public String getVehicleLoad() {
        return this.mVehicleLoad;
    }

    public void setVehicleLoad(String str) {
        this.mVehicleLoad = str;
    }

    public boolean isVehicleLoadSwitch() {
        return this.VehicleLoadSwitch;
    }

    public void setVehicleLoadSwitch(boolean z) {
        this.VehicleLoadSwitch = z;
    }
}

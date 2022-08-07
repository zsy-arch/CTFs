package com.autonavi.ae.pos;

/* loaded from: classes.dex */
public class LocManager {
    public static native void addLocListener(LocListener locListener, int i);

    public static native void addParallelRoadObserver(LocParallelRoadObserver locParallelRoadObserver);

    public static native String getVersion();

    public static native long init();

    public static native void saveLocStorage();

    public static native void setCarPosByCoord(int i, int i2, double d);

    public static native void setCompass(double d, long j);

    public static native void setDoorIn(LocDoorIn locDoorIn);

    public static native void setGSVData(LocGSVData locGSVData);

    public static native void setGpsInfo(GpsInfo gpsInfo);

    public static native void setGyro(int i, int i2, float f, float f2, float f3, int i3, int i4, long j);

    public static native void setLogSwitch(int i);

    public static native void setMatchMode(int i);

    public static native void setMode(int i);

    public static native void setPressure(double d, long j);

    public static native void setPulse(int i, int i2, int i3, long j);

    public static native void switchParallelRoad(LocObjectId locObjectId);

    public static native void uninit();
}

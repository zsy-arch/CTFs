package com.amap.api.services.a;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alipay.sdk.util.j;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.interfaces.IRouteSearch;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RideRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;

/* compiled from: RouteSearchCore.java */
/* loaded from: classes.dex */
public class as implements IRouteSearch {
    private RouteSearch.OnRouteSearchListener a;
    private Context b;
    private Handler c = q.a();

    public as(Context context) {
        this.b = context.getApplicationContext();
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public void setRouteSearchListener(RouteSearch.OnRouteSearchListener onRouteSearchListener) {
        this.a = onRouteSearchListener;
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public WalkRouteResult calculateWalkRoute(RouteSearch.WalkRouteQuery walkRouteQuery) throws AMapException {
        try {
            o.a(this.b);
            if (walkRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else if (!a(walkRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else {
                RouteSearch.WalkRouteQuery clone = walkRouteQuery.clone();
                WalkRouteResult a = new ae(this.b, clone).a();
                if (a != null) {
                    a.setWalkQuery(clone);
                }
                return a;
            }
        } catch (AMapException e) {
            i.a(e, "RouteSearch", "calculateWalkRoute");
            throw e;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.as$1] */
    @Override // com.amap.api.services.interfaces.IRouteSearch
    public void calculateWalkRouteAsyn(final RouteSearch.WalkRouteQuery walkRouteQuery) {
        try {
            new Thread() { // from class: com.amap.api.services.a.as.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    WalkRouteResult walkRouteResult;
                    Message obtainMessage;
                    Bundle bundle;
                    try {
                        obtainMessage = q.a().obtainMessage();
                        obtainMessage.what = 102;
                        obtainMessage.arg1 = 1;
                        bundle = new Bundle();
                        walkRouteResult = null;
                        try {
                            walkRouteResult = as.this.calculateWalkRoute(walkRouteQuery);
                            bundle.putInt(MyLocationStyle.ERROR_CODE, 1000);
                            obtainMessage.obj = as.this.a;
                            bundle.putParcelable(j.c, walkRouteResult);
                            obtainMessage.setData(bundle);
                            as.this.c.sendMessage(obtainMessage);
                        } catch (AMapException e) {
                            bundle.putInt(MyLocationStyle.ERROR_CODE, e.getErrorCode());
                            obtainMessage.obj = as.this.a;
                            bundle.putParcelable(j.c, walkRouteResult);
                            obtainMessage.setData(bundle);
                            as.this.c.sendMessage(obtainMessage);
                        }
                    } catch (Throwable th) {
                        obtainMessage.obj = as.this.a;
                        bundle.putParcelable(j.c, walkRouteResult);
                        obtainMessage.setData(bundle);
                        as.this.c.sendMessage(obtainMessage);
                        throw th;
                    }
                }
            }.start();
        } catch (Throwable th) {
            i.a(th, "RouteSearch", "calculateWalkRouteAsyn");
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public BusRouteResult calculateBusRoute(RouteSearch.BusRouteQuery busRouteQuery) throws AMapException {
        try {
            o.a(this.b);
            if (busRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else if (!a(busRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else {
                RouteSearch.BusRouteQuery clone = busRouteQuery.clone();
                BusRouteResult a = new c(this.b, clone).a();
                if (a != null) {
                    a.setBusQuery(clone);
                }
                return a;
            }
        } catch (AMapException e) {
            i.a(e, "RouteSearch", "calculateBusRoute");
            throw e;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.as$2] */
    @Override // com.amap.api.services.interfaces.IRouteSearch
    public void calculateBusRouteAsyn(final RouteSearch.BusRouteQuery busRouteQuery) {
        try {
            new Thread() { // from class: com.amap.api.services.a.as.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    BusRouteResult busRouteResult;
                    Message obtainMessage;
                    Bundle bundle;
                    try {
                        obtainMessage = q.a().obtainMessage();
                        obtainMessage.what = 100;
                        obtainMessage.arg1 = 1;
                        bundle = new Bundle();
                        busRouteResult = null;
                        try {
                            busRouteResult = as.this.calculateBusRoute(busRouteQuery);
                            bundle.putInt(MyLocationStyle.ERROR_CODE, 1000);
                            obtainMessage.obj = as.this.a;
                            bundle.putParcelable(j.c, busRouteResult);
                            obtainMessage.setData(bundle);
                            as.this.c.sendMessage(obtainMessage);
                        } catch (AMapException e) {
                            bundle.putInt(MyLocationStyle.ERROR_CODE, e.getErrorCode());
                            obtainMessage.obj = as.this.a;
                            bundle.putParcelable(j.c, busRouteResult);
                            obtainMessage.setData(bundle);
                            as.this.c.sendMessage(obtainMessage);
                        }
                    } catch (Throwable th) {
                        obtainMessage.obj = as.this.a;
                        bundle.putParcelable(j.c, busRouteResult);
                        obtainMessage.setData(bundle);
                        as.this.c.sendMessage(obtainMessage);
                        throw th;
                    }
                }
            }.start();
        } catch (Throwable th) {
            i.a(th, "RouteSearch", "calculateBusRouteAsyn");
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public DriveRouteResult calculateDriveRoute(RouteSearch.DriveRouteQuery driveRouteQuery) throws AMapException {
        try {
            o.a(this.b);
            if (driveRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else if (!a(driveRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else {
                RouteSearch.DriveRouteQuery clone = driveRouteQuery.clone();
                DriveRouteResult a = new k(this.b, clone).a();
                if (a != null) {
                    a.setDriveQuery(clone);
                }
                return a;
            }
        } catch (AMapException e) {
            i.a(e, "RouteSearch", "calculateDriveRoute");
            throw e;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.as$3] */
    @Override // com.amap.api.services.interfaces.IRouteSearch
    public void calculateDriveRouteAsyn(final RouteSearch.DriveRouteQuery driveRouteQuery) {
        try {
            new Thread() { // from class: com.amap.api.services.a.as.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    DriveRouteResult driveRouteResult;
                    Message obtainMessage;
                    Bundle bundle;
                    try {
                        obtainMessage = q.a().obtainMessage();
                        obtainMessage.what = 101;
                        obtainMessage.arg1 = 1;
                        bundle = new Bundle();
                        driveRouteResult = null;
                        try {
                            driveRouteResult = as.this.calculateDriveRoute(driveRouteQuery);
                            bundle.putInt(MyLocationStyle.ERROR_CODE, 1000);
                            obtainMessage.obj = as.this.a;
                            bundle.putParcelable(j.c, driveRouteResult);
                            obtainMessage.setData(bundle);
                            as.this.c.sendMessage(obtainMessage);
                        } catch (AMapException e) {
                            bundle.putInt(MyLocationStyle.ERROR_CODE, e.getErrorCode());
                            obtainMessage.obj = as.this.a;
                            bundle.putParcelable(j.c, driveRouteResult);
                            obtainMessage.setData(bundle);
                            as.this.c.sendMessage(obtainMessage);
                        }
                    } catch (Throwable th) {
                        obtainMessage.obj = as.this.a;
                        bundle.putParcelable(j.c, driveRouteResult);
                        obtainMessage.setData(bundle);
                        as.this.c.sendMessage(obtainMessage);
                        throw th;
                    }
                }
            }.start();
        } catch (Throwable th) {
            i.a(th, "RouteSearch", "calculateDriveRouteAsyn");
        }
    }

    private boolean a(RouteSearch.FromAndTo fromAndTo) {
        return (fromAndTo == null || fromAndTo.getFrom() == null || fromAndTo.getTo() == null) ? false : true;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.amap.api.services.a.as$4] */
    @Override // com.amap.api.services.interfaces.IRouteSearch
    public void calculateRideRouteAsyn(final RouteSearch.RideRouteQuery rideRouteQuery) {
        try {
            new Thread() { // from class: com.amap.api.services.a.as.4
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    RideRouteResult rideRouteResult;
                    Message obtainMessage;
                    Bundle bundle;
                    try {
                        obtainMessage = q.a().obtainMessage();
                        obtainMessage.what = 103;
                        obtainMessage.arg1 = 1;
                        bundle = new Bundle();
                        rideRouteResult = null;
                        try {
                            rideRouteResult = as.this.calculateRideRoute(rideRouteQuery);
                            bundle.putInt(MyLocationStyle.ERROR_CODE, 1000);
                            obtainMessage.obj = as.this.a;
                            bundle.putParcelable(j.c, rideRouteResult);
                            obtainMessage.setData(bundle);
                            as.this.c.sendMessage(obtainMessage);
                        } catch (AMapException e) {
                            bundle.putInt(MyLocationStyle.ERROR_CODE, e.getErrorCode());
                            obtainMessage.obj = as.this.a;
                            bundle.putParcelable(j.c, rideRouteResult);
                            obtainMessage.setData(bundle);
                            as.this.c.sendMessage(obtainMessage);
                        }
                    } catch (Throwable th) {
                        obtainMessage.obj = as.this.a;
                        bundle.putParcelable(j.c, rideRouteResult);
                        obtainMessage.setData(bundle);
                        as.this.c.sendMessage(obtainMessage);
                        throw th;
                    }
                }
            }.start();
        } catch (Throwable th) {
            i.a(th, "RouteSearch", "calculateRideRouteAsyn");
        }
    }

    @Override // com.amap.api.services.interfaces.IRouteSearch
    public RideRouteResult calculateRideRoute(RouteSearch.RideRouteQuery rideRouteQuery) throws AMapException {
        try {
            o.a(this.b);
            if (rideRouteQuery == null) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else if (!a(rideRouteQuery.getFromAndTo())) {
                throw new AMapException("无效的参数 - IllegalArgumentException");
            } else {
                RouteSearch.RideRouteQuery clone = rideRouteQuery.clone();
                RideRouteResult a = new ab(this.b, clone).a();
                if (a != null) {
                    a.setRideQuery(clone);
                }
                return a;
            }
        } catch (AMapException e) {
            i.a(e, "RouteSearch", "calculaterideRoute");
            throw e;
        }
    }
}

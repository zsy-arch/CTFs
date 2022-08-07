package com.autonavi.ae.route.route;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class CalcRouteResult {
    private long mPtr;
    public Map<Object, Object> mResultInfo = new HashMap();

    public native int getErrorCode();

    public native double[] getRouteBound();

    public native int getRouteCount();

    public native void nativeDestroy();

    public native Route nativeGetRoute(int i);

    public void destroy() {
        nativeDestroy();
    }

    public Route getRoute(int i) {
        return nativeGetRoute(i);
    }
}

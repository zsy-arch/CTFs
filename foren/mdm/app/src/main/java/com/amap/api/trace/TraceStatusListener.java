package com.amap.api.trace;

import com.amap.api.maps.model.LatLng;
import java.util.List;

/* loaded from: classes.dex */
public interface TraceStatusListener {
    void onTraceStatus(List<TraceLocation> list, List<LatLng> list2, String str);
}

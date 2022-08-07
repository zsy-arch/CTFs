package com.amap.api.col;

import com.amap.api.col.gj;
import com.amap.api.maps.MapsInitializer;
import com.autonavi.amap.mapcore.Inner_3dMap_location;

/* compiled from: Util.java */
/* loaded from: classes.dex */
public final class jd {
    private static final String[] a = {"com.amap.api.maps", "com.amap.api.mapcore", "com.autonavi.amap.mapcore", "com.amap.api.3dmap.admic", "com.amap.api.trace", "com.amap.api.trace.core"};

    public static gj a() throws fz {
        String version = MapsInitializer.getVersion();
        return new gj.a("3dmap", version, "AMAP_SDK_Android_Map_" + version).a(a).a();
    }

    public static boolean a(it itVar) {
        if (itVar != null && !itVar.d().equals("8") && !itVar.d().equals("5") && !itVar.d().equals("6")) {
            return a((Inner_3dMap_location) itVar);
        }
        return false;
    }

    private static boolean a(Inner_3dMap_location inner_3dMap_location) {
        double longitude = inner_3dMap_location.getLongitude();
        double latitude = inner_3dMap_location.getLatitude();
        return !(longitude == 0.0d && latitude == 0.0d) && longitude <= 180.0d && latitude <= 90.0d && longitude >= -180.0d && latitude >= -90.0d;
    }
}

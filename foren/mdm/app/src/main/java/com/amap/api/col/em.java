package com.amap.api.col;

import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.ae.route.model.RoutePoi;

/* compiled from: AE8Temp.java */
/* loaded from: classes.dex */
public class em {
    private static int d = 0;
    private static NaviLatLng e = new NaviLatLng();
    private static RoutePoi[] a = new RoutePoi[0];
    private static RoutePoi[] b = new RoutePoi[0];
    private static RoutePoi[] c = new RoutePoi[0];

    public static boolean a() {
        return a != null && c != null && c.length > 0 && a.length > 0;
    }

    public static RoutePoi[] b() {
        return a;
    }

    public static void a(RoutePoi[] routePoiArr) {
        a = routePoiArr;
    }

    public static RoutePoi[] c() {
        return b;
    }

    public static void b(RoutePoi[] routePoiArr) {
        b = routePoiArr;
    }

    public static RoutePoi[] d() {
        return c;
    }

    public static void c(RoutePoi[] routePoiArr) {
        c = routePoiArr;
    }

    public static int e() {
        return d;
    }

    public static void a(int i) {
        d = i;
    }

    public static NaviLatLng f() {
        return e;
    }

    public static void a(NaviLatLng naviLatLng) {
        e = naviLatLng;
    }
}

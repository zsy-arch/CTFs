package com.amap.api.col;

import android.content.Context;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.ae.route.model.RoutePoi;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: Ae8Utils.java */
/* loaded from: classes.dex */
public class en {
    public static String a = "";
    private static String b = "http://restapi.amap.com";

    public static RoutePoi[] a(List<NaviLatLng> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        RoutePoi[] routePoiArr = new RoutePoi[list.size()];
        for (int i = 0; i < list.size(); i++) {
            NaviLatLng naviLatLng = list.get(i);
            RoutePoi routePoi = new RoutePoi();
            routePoi.naviLat = naviLatLng.getLatitude();
            routePoi.naviLon = naviLatLng.getLongitude();
            routePoi.latitude = naviLatLng.getLatitude();
            routePoi.longitude = naviLatLng.getLongitude();
            routePoiArr[i] = routePoi;
        }
        return routePoiArr;
    }

    public static List<NaviLatLng> a(RoutePoi[] routePoiArr) {
        if (routePoiArr == null || routePoiArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (RoutePoi routePoi : routePoiArr) {
            arrayList.add(new NaviLatLng(routePoi.latitude, routePoi.longitude));
        }
        return arrayList;
    }

    public static byte[] a(int i, String str, byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            try {
                dataOutputStream.write((byte) i);
                dataOutputStream.writeShort(str.length());
                dataOutputStream.write(str.getBytes("UTF-8"));
                dataOutputStream.writeShort(bArr.length);
                dataOutputStream.write(bArr);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    return byteArray;
                } catch (IOException e) {
                    return byteArray;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                try {
                    dataOutputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return null;
            }
        } finally {
            try {
                dataOutputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    public static ii a(Context context, int i, final byte[] bArr) {
        try {
            switch (i) {
                case 1:
                    a = b + "/v3/ae8/driving";
                    break;
                case 2:
                    a = b + "/v3/ae8/traffic/show";
                    break;
                case 3:
                    a = b + "/v3/ae8/traffic/broadcast/login";
                    break;
                case 7:
                    a = b + "/v3/ae8/intersection/enlarged";
                    break;
            }
            fr.c("request host==" + a);
            return Cif.b().c(new ib(context, fn.a()) { // from class: com.amap.api.col.en.1
                @Override // com.amap.api.col.ig
                public String c() {
                    return en.a;
                }

                @Override // com.amap.api.col.ib
                public byte[] d() {
                    return bArr;
                }

                @Override // com.amap.api.col.ig
                public Map<String, String> a() {
                    return null;
                }

                @Override // com.amap.api.col.ib
                public byte[] e() {
                    return null;
                }
            });
        } catch (fz e) {
            e.printStackTrace();
            gr.a(fn.a(), a.replace(b, ""), e);
            return null;
        }
    }
}

package com.loc;

import android.os.Bundle;
import com.alipay.sdk.util.h;
import com.amap.api.fence.DistrictItem;
import com.amap.api.fence.GeoFence;
import com.amap.api.fence.PoiItem;
import com.amap.api.location.DPoint;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: GeoFenceSearchResultParser.java */
/* loaded from: classes2.dex */
public final class c {
    private static long a = 0;

    public static int a(String str, List<GeoFence> list, Bundle bundle) {
        JSONArray optJSONArray;
        int i = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status", 0);
            i = jSONObject.optInt("infocode", 0);
            if (optInt == 1 && (optJSONArray = jSONObject.optJSONArray("pois")) != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    GeoFence geoFence = new GeoFence();
                    PoiItem poiItem = new PoiItem();
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                    poiItem.setPoiId(jSONObject2.optString("id"));
                    poiItem.setPoiName(jSONObject2.optString("name"));
                    poiItem.setPoiType(jSONObject2.optString("type"));
                    poiItem.setTypeCode(jSONObject2.optString("typecode"));
                    poiItem.setAddress(jSONObject2.optString("address"));
                    String optString = jSONObject2.optString("location");
                    if (optString != null) {
                        String[] split = optString.split(",");
                        poiItem.setLongitude(Double.parseDouble(split[0]));
                        poiItem.setLatitude(Double.parseDouble(split[1]));
                        List<List<DPoint>> arrayList = new ArrayList<>();
                        ArrayList arrayList2 = new ArrayList();
                        DPoint dPoint = new DPoint(poiItem.getLatitude(), poiItem.getLongitude());
                        arrayList2.add(dPoint);
                        arrayList.add(arrayList2);
                        geoFence.setPointList(arrayList);
                        geoFence.setCenter(dPoint);
                    }
                    poiItem.setTel(jSONObject2.optString("tel"));
                    poiItem.setProvince(jSONObject2.optString("pname"));
                    poiItem.setCity(jSONObject2.optString("cityname"));
                    poiItem.setAdname(jSONObject2.optString("adname"));
                    geoFence.setPoiItem(poiItem);
                    geoFence.setFenceId(new StringBuilder().append(a()).toString());
                    if (bundle != null) {
                        geoFence.setCustomId(bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID));
                        geoFence.setPendingIntentAction(bundle.getString("pendingIntentAction"));
                        geoFence.setType(2);
                        geoFence.setRadius(bundle.getFloat("geoRadius"));
                        geoFence.setExpiration(bundle.getLong("expiration"));
                        geoFence.setActivatesAction(bundle.getInt("activatesAction", 1));
                    }
                    if (list != null) {
                        list.add(geoFence);
                    }
                }
            }
        } catch (Throwable th) {
        }
        return i;
    }

    public static synchronized long a() {
        long j;
        synchronized (c.class) {
            boolean z = false;
            while (!z) {
                long b = cx.b();
                if (b == a) {
                    try {
                        Thread.currentThread();
                        Thread.sleep(1L);
                    } catch (InterruptedException e) {
                    }
                } else {
                    a = b;
                    z = true;
                }
            }
            j = a;
        }
        return j;
    }

    private List<DPoint> a(List<DPoint> list, float f) {
        double d;
        double d2;
        if (list == null) {
            return null;
        }
        if (list.size() <= 2) {
            return list;
        }
        double d3 = 0.0d;
        int i = 0;
        ArrayList arrayList = new ArrayList();
        DPoint dPoint = list.get(0);
        DPoint dPoint2 = list.get(list.size() - 1);
        for (int i2 = 1; i2 < list.size() - 1; i2++) {
            DPoint dPoint3 = list.get(i2);
            double longitude = dPoint3.getLongitude() - dPoint.getLongitude();
            double latitude = dPoint3.getLatitude() - dPoint.getLatitude();
            double longitude2 = dPoint2.getLongitude() - dPoint.getLongitude();
            double latitude2 = dPoint2.getLatitude() - dPoint.getLatitude();
            double d4 = ((longitude * longitude2) + (latitude * latitude2)) / ((longitude2 * longitude2) + (latitude2 * latitude2));
            if (d4 < 0.0d || (dPoint.getLongitude() == dPoint2.getLongitude() && dPoint.getLatitude() == dPoint2.getLatitude())) {
                d = dPoint.getLongitude();
                d2 = dPoint.getLatitude();
            } else if (d4 > 1.0d) {
                d = dPoint2.getLongitude();
                d2 = dPoint2.getLatitude();
            } else {
                d = dPoint.getLongitude() + (longitude2 * d4);
                d2 = (d4 * latitude2) + dPoint.getLatitude();
            }
            double a2 = cx.a(new DPoint(dPoint3.getLatitude(), dPoint3.getLongitude()), new DPoint(d2, d));
            if (a2 > d3) {
                i = i2;
                d3 = a2;
            }
        }
        if (d3 < f) {
            arrayList.add(dPoint);
            arrayList.add(dPoint2);
            return arrayList;
        }
        List<DPoint> a3 = a(list.subList(0, i + 1), f);
        List<DPoint> a4 = a(list.subList(i, list.size()), f);
        arrayList.addAll(a3);
        arrayList.remove(arrayList.size() - 1);
        arrayList.addAll(a4);
        return arrayList;
    }

    public static int b(String str, List<GeoFence> list, Bundle bundle) {
        return a(str, list, bundle);
    }

    public final int c(String str, List<GeoFence> list, Bundle bundle) {
        JSONArray optJSONArray;
        int i = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("status", 0);
            i = jSONObject.optInt("infocode", 0);
            String str2 = null;
            String str3 = null;
            float f = 0.0f;
            long j = 0;
            int i2 = 0;
            if (bundle != null) {
                str2 = bundle.getString(GeoFence.BUNDLE_KEY_CUSTOMID);
                str3 = bundle.getString("pendingIntentAction");
                f = bundle.getFloat("geoRadius");
                j = bundle.getLong("expiration");
                i2 = bundle.getInt("activatesAction");
            }
            if (optInt == 1 && (optJSONArray = jSONObject.optJSONArray("districts")) != null) {
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    GeoFence geoFence = new GeoFence();
                    JSONObject jSONObject2 = optJSONArray.getJSONObject(i3);
                    String optString = jSONObject2.optString("citycode");
                    String optString2 = jSONObject2.optString("adcode");
                    String optString3 = jSONObject2.optString("name");
                    String string = jSONObject2.getString("center");
                    DPoint dPoint = new DPoint();
                    if (string != null) {
                        String[] split = string.split(",");
                        dPoint.setLatitude(Double.parseDouble(split[1]));
                        dPoint.setLongitude(Double.parseDouble(split[0]));
                        geoFence.setCenter(dPoint);
                    }
                    geoFence.setCustomId(str2);
                    geoFence.setPendingIntentAction(str3);
                    geoFence.setType(3);
                    geoFence.setRadius(f);
                    geoFence.setExpiration(j);
                    geoFence.setActivatesAction(i2);
                    geoFence.setFenceId(new StringBuilder().append(a()).toString());
                    String optString4 = jSONObject2.optString("polyline");
                    float f2 = Float.MIN_VALUE;
                    float f3 = Float.MAX_VALUE;
                    if (optString4 != null) {
                        String[] split2 = optString4.split("\\|");
                        for (String str4 : split2) {
                            DistrictItem districtItem = new DistrictItem();
                            List<DPoint> arrayList3 = new ArrayList<>();
                            districtItem.setCitycode(optString);
                            districtItem.setAdcode(optString2);
                            districtItem.setDistrictName(optString3);
                            String[] split3 = str4.split(h.b);
                            for (String str5 : split3) {
                                String[] split4 = str5.split(",");
                                if (split4.length > 1) {
                                    arrayList3.add(new DPoint(Double.parseDouble(split4[1]), Double.parseDouble(split4[0])));
                                }
                            }
                            if (arrayList3.size() > 100.0f) {
                                arrayList3 = a(arrayList3, 100.0f);
                            }
                            arrayList2.add(arrayList3);
                            districtItem.setPolyline(arrayList3);
                            arrayList.add(districtItem);
                            f2 = Math.max(f2, a.b(dPoint, arrayList3));
                            f3 = Math.min(f3, a.a(dPoint, arrayList3));
                        }
                        geoFence.setMaxDis2Center(f2);
                        geoFence.setMinDis2Center(f3);
                        geoFence.setDistrictItemList(arrayList);
                        geoFence.setPointList(arrayList2);
                        if (list != null) {
                            list.add(geoFence);
                        }
                    }
                }
            }
        } catch (Throwable th) {
        }
        return i;
    }
}

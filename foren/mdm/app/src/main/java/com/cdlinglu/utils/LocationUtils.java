package com.cdlinglu.utils;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.cdlinglu.common.MyApplication;
import com.google.gson.Gson;
import com.vsf2f.f2f.bean.MyLocation;

/* loaded from: classes.dex */
public class LocationUtils {
    public static MyLocation locationToAmap(String location) {
        String value;
        MyLocation aMapLocation = new MyLocation();
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        String[] sub = location.split("#");
        if (sub != null) {
            for (int i = 0; i < sub.length; i++) {
                String[] kv = sub[i].split("=");
                if (!TextUtils.isEmpty(kv[0])) {
                    String key = "\"" + kv[0] + "\"";
                    if (kv.length > 1) {
                        value = "\"" + kv[1] + "\"";
                    } else {
                        value = "\"\"";
                    }
                    sb.append(key + ":" + value);
                }
                if (i < sub.length - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append(h.d);
        return (MyLocation) new Gson().fromJson(sb.toString(), (Class<Object>) aMapLocation.getClass());
    }

    public static String getDistance(double lat, double lng) {
        int distance = (int) AMapUtils.calculateLineDistance(MyApplication.getCurrentLatlnt(), new LatLng(lat, lng));
        if (distance / 1000 > 1) {
            if ((distance / 1000) / 100 > 1) {
                return ">100km";
            }
            return (distance / 1000) + "km";
        } else if (distance > 500) {
            return "<1km";
        } else {
            if (distance < 100) {
                return "<100m";
            }
            if (distance < 500) {
                return "<500m";
            }
            return distance + "m";
        }
    }

    public static String getDistanceStr(double lat, double lng) {
        int distance = (int) AMapUtils.calculateLineDistance(MyApplication.getCurrentLatlnt(), new LatLng(lat, lng));
        if (distance / 1000 > 1) {
            if ((distance / 1000) / 100 > 1) {
                return "100公里以外";
            }
            return (distance / 1000) + "公里以内";
        } else if (distance > 500) {
            return "1公里以内";
        } else {
            if (distance < 100) {
                return "100米以内";
            }
            if (distance < 500) {
                return "500米以内";
            }
            return distance + "米以内";
        }
    }
}

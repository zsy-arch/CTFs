package com.amap.api.col;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.AMapException;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CoreUtil.java */
/* loaded from: classes.dex */
public class fv {
    private static String[] a = {"com.amap.api.trace", "com.amap.api.trace.core"};

    public static void a(String str, String str2) throws fs {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status") && jSONObject.has("infocode")) {
                String string = jSONObject.getString("status");
                int i = jSONObject.getInt("infocode");
                if (!string.equals("1") && string.equals("0")) {
                    switch (i) {
                        case 10001:
                            throw new fs(AMapException.AMAP_INVALID_USER_KEY);
                        case GLMapStaticValue.AM_CALLBACK_NEED_NEXTFRAME /* 10002 */:
                            throw new fs(AMapException.AMAP_SERVICE_NOT_AVAILBALE);
                        case 10003:
                            throw new fs(AMapException.AMAP_DAILY_QUERY_OVER_LIMIT);
                        case 10004:
                            throw new fs(AMapException.AMAP_ACCESS_TOO_FREQUENT);
                        case 10005:
                            throw new fs(AMapException.AMAP_INVALID_USER_IP);
                        case 10006:
                            throw new fs(AMapException.AMAP_INVALID_USER_DOMAIN);
                        case 10007:
                            throw new fs(AMapException.AMAP_SIGNATURE_ERROR);
                        case 10008:
                            throw new fs(AMapException.AMAP_INVALID_USER_SCODE);
                        case 10009:
                            throw new fs(AMapException.AMAP_USERKEY_PLAT_NOMATCH);
                        case 10010:
                            throw new fs(AMapException.AMAP_IP_QUERY_OVER_LIMIT);
                        case 10011:
                            throw new fs(AMapException.AMAP_NOT_SUPPORT_HTTPS);
                        case 10012:
                            throw new fs(AMapException.AMAP_INSUFFICIENT_PRIVILEGES);
                        case 10013:
                            throw new fs(AMapException.AMAP_USER_KEY_RECYCLED);
                        case 20000:
                            throw new fs(AMapException.AMAP_SERVICE_INVALID_PARAMS);
                        case 20001:
                            throw new fs(AMapException.AMAP_SERVICE_MISSING_REQUIRED_PARAMS);
                        case 20002:
                            throw new fs(AMapException.AMAP_SERVICE_ILLEGAL_REQUEST);
                        case 20003:
                            throw new fs(AMapException.AMAP_SERVICE_UNKNOWN_ERROR);
                        case 30000:
                            throw new fs(AMapException.AMAP_ENGINE_RESPONSE_ERROR);
                        case 30001:
                            throw new fs(AMapException.AMAP_ENGINE_RESPONSE_DATA_ERROR);
                        case 30002:
                            throw new fs(AMapException.AMAP_ENGINE_CONNECT_TIMEOUT);
                        case 30003:
                            throw new fs(AMapException.AMAP_ENGINE_RETURN_TIMEOUT);
                        default:
                            throw new fs(jSONObject.getString("info"));
                    }
                }
            }
        } catch (JSONException e) {
            throw new fs("协议解析错误 - ProtocolException");
        }
    }

    public static int a(List<LatLng> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < list.size() - 1; i2++) {
            LatLng latLng = list.get(i2);
            LatLng latLng2 = list.get(i2 + 1);
            if (latLng == null || latLng2 == null) {
                return i;
            }
            i = (int) (AMapUtils.calculateLineDistance(latLng, latLng2) + i);
        }
        return i;
    }
}

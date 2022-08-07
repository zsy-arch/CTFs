package com.amap.api.services.a;

import com.alipay.sdk.util.h;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.autonavi.ae.gmap.utils.GLMapStaticValue;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CoreUtil.java */
/* loaded from: classes.dex */
public class i {
    public static boolean a(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static void b(String str) throws AMapException {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status")) {
                String string = jSONObject.getString("status");
                if (string.equals("1")) {
                    return;
                }
                if (!string.equals("0") || jSONObject.has("infocode")) {
                    int i = jSONObject.getInt("infocode");
                    if (string.equals("0")) {
                        switch (i) {
                            case 10001:
                                throw new AMapException(AMapException.AMAP_INVALID_USER_KEY);
                            case GLMapStaticValue.AM_CALLBACK_NEED_NEXTFRAME /* 10002 */:
                                throw new AMapException(AMapException.AMAP_SERVICE_NOT_AVAILBALE);
                            case 10003:
                                throw new AMapException(AMapException.AMAP_DAILY_QUERY_OVER_LIMIT);
                            case 10004:
                                throw new AMapException(AMapException.AMAP_ACCESS_TOO_FREQUENT);
                            case 10005:
                                throw new AMapException(AMapException.AMAP_INVALID_USER_IP);
                            case 10006:
                                throw new AMapException(AMapException.AMAP_INVALID_USER_DOMAIN);
                            case 10007:
                                throw new AMapException(AMapException.AMAP_SIGNATURE_ERROR);
                            case 10008:
                                throw new AMapException(AMapException.AMAP_INVALID_USER_SCODE);
                            case 10009:
                                throw new AMapException(AMapException.AMAP_USERKEY_PLAT_NOMATCH);
                            case 10010:
                                throw new AMapException(AMapException.AMAP_IP_QUERY_OVER_LIMIT);
                            case 10011:
                                throw new AMapException(AMapException.AMAP_NOT_SUPPORT_HTTPS);
                            case 10012:
                                throw new AMapException(AMapException.AMAP_INSUFFICIENT_PRIVILEGES);
                            case 10013:
                                throw new AMapException(AMapException.AMAP_USER_KEY_RECYCLED);
                            case 20000:
                                throw new AMapException(AMapException.AMAP_SERVICE_INVALID_PARAMS);
                            case 20001:
                                throw new AMapException(AMapException.AMAP_SERVICE_MISSING_REQUIRED_PARAMS);
                            case 20002:
                                throw new AMapException(AMapException.AMAP_SERVICE_ILLEGAL_REQUEST);
                            case 20003:
                                throw new AMapException(AMapException.AMAP_SERVICE_UNKNOWN_ERROR);
                            case 20800:
                                throw new AMapException(AMapException.AMAP_ROUTE_OUT_OF_SERVICE);
                            case 20801:
                                throw new AMapException(AMapException.AMAP_ROUTE_NO_ROADS_NEARBY);
                            case 20802:
                                throw new AMapException(AMapException.AMAP_ROUTE_FAIL);
                            case 20803:
                                throw new AMapException(AMapException.AMAP_OVER_DIRECTION_RANGE);
                            case 22000:
                                throw new AMapException(AMapException.AMAP_SERVICE_TABLEID_NOT_EXIST);
                            case 30000:
                                throw new AMapException(AMapException.AMAP_ENGINE_RESPONSE_ERROR);
                            case 30001:
                                throw new AMapException(AMapException.AMAP_ENGINE_RESPONSE_DATA_ERROR);
                            case 30002:
                                throw new AMapException(AMapException.AMAP_ENGINE_CONNECT_TIMEOUT);
                            case 30003:
                                throw new AMapException(AMapException.AMAP_ENGINE_RETURN_TIMEOUT);
                            case 32000:
                                throw new AMapException(AMapException.AMAP_ENGINE_TABLEID_NOT_EXIST);
                            case 32001:
                                throw new AMapException(AMapException.AMAP_ID_NOT_EXIST);
                            case 32002:
                                throw new AMapException(AMapException.AMAP_SERVICE_MAINTENANCE);
                            case 32200:
                                throw new AMapException(AMapException.AMAP_NEARBY_INVALID_USERID);
                            case 32201:
                                throw new AMapException(AMapException.AMAP_NEARBY_KEY_NOT_BIND);
                            default:
                                throw new AMapException(jSONObject.getString("info"));
                        }
                    }
                } else {
                    throw new AMapException(AMapException.AMAP_CLIENT_UNKNOWN_ERROR);
                }
            }
        } catch (JSONException e) {
            a(e, "CoreUtil", "paseAuthFailurJson");
            throw new AMapException("协议解析错误 - ProtocolException");
        }
    }

    public static double a(double d) {
        return Double.parseDouble(new DecimalFormat("0.000000", new DecimalFormatSymbols(Locale.US)).format(d));
    }

    public static String a(LatLonPoint latLonPoint) {
        if (latLonPoint == null) {
            return "";
        }
        double a = a(latLonPoint.getLongitude());
        return a + "," + a(latLonPoint.getLatitude());
    }

    public static Date c(String str) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        try {
            return new SimpleDateFormat("HHmm").parse(str);
        } catch (ParseException e) {
            a(e, "CoreUtil", "parseString2Time");
            return null;
        }
    }

    public static String a(Date date) {
        return date != null ? new SimpleDateFormat("HH:mm").format(date) : "";
    }

    public static Date d(String str) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        try {
            return new SimpleDateFormat("HH:mm").parse(str);
        } catch (ParseException e) {
            a(e, "CoreUtil", "parseTime");
            return null;
        }
    }

    public static String a(List<LatLonPoint> list) {
        if (list == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            LatLonPoint latLonPoint = list.get(i);
            if (latLonPoint != null) {
                double a = a(latLonPoint.getLongitude());
                stringBuffer.append(a).append(",").append(a(latLonPoint.getLatitude()));
                stringBuffer.append(h.b);
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    public static void a(Throwable th, String str, String str2) {
        try {
            bk a = bk.a();
            if (a != null) {
                a.c(th, str, str2);
            }
            th.printStackTrace();
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}

package com.amap.api.col;

import android.text.TextUtils;
import android.util.Log;
import com.amap.api.services.core.AMapException;
import org.json.JSONObject;

/* compiled from: RestFront.java */
/* loaded from: classes.dex */
public class fi {
    public static void a(String str, int i) {
        String str2 = "";
        String str3 = "";
        switch (i) {
            case 3:
                str2 = "5011";
                str3 = "起点错误";
                break;
            case 4:
                str2 = "5031";
                str3 = AMapException.AMAP_SERVICE_ILLEGAL_REQUEST;
                break;
            case 6:
                str2 = "5012";
                str3 = "终点错误";
                break;
            case 7:
                str2 = "5041";
                str3 = "算路服务端编码失败";
                break;
            case 8:
                str2 = "5052";
                str3 = "路径数据缺乏预览数据";
                break;
            case 9:
                str2 = "5053";
                str3 = "Buf数据格式错误";
                break;
            case 10:
                str2 = "5021";
                str3 = "起点找不到道路";
                break;
            case 11:
                str2 = "5022";
                str3 = "终点找不到道路";
                break;
            case 12:
                str2 = "5023";
                str3 = "途经点找不到道路";
                break;
            case 19:
                str2 = "5042";
                str3 = "算路失败未知错误";
                break;
            case 20:
                str2 = "5051";
                str3 = "路径距离太长";
                break;
            case 21:
                str2 = "5013";
                str3 = "途经点错误";
                break;
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            gr.a(fn.a(), str, str3, "", str2);
        }
    }

    public static int a(String str, ii iiVar) {
        if (iiVar == null) {
            return 2;
        }
        try {
            String str2 = iiVar.d;
            String str3 = new String(iiVar.a, "UTF-8");
            if (str3.contains("\"status\":\"0\"")) {
                Log.i("ATest", "错误码:" + str3);
                return a(str, str2, a(str3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static fj a(String str) throws Exception {
        fj fjVar = new fj();
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString("status");
        String string2 = jSONObject.getString("info");
        String string3 = jSONObject.getString("infocode");
        fjVar.a(Integer.parseInt(string));
        fjVar.a(string2);
        fjVar.b(Integer.parseInt(string3));
        return fjVar;
    }

    private static int a(String str, String str2, fj fjVar) throws Exception {
        if (fjVar.a() == 0) {
            gr.a(fn.a(), str, fjVar.c(), str2, String.valueOf(fjVar.b()));
            switch (fjVar.b()) {
                case 10001:
                    return 13;
                case 10003:
                    return 17;
                case 10004:
                    return 23;
                case 10008:
                    return 22;
                case 10009:
                    return 24;
                case 10016:
                    return 17;
                case 20000:
                case 20001:
                    return 18;
                case 20800:
                    return 25;
                case 20803:
                    return 26;
            }
        }
        return 19;
    }
}

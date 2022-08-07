package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.alipay.sdk.sys.a;
import com.http.config.URLConfig;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class u {
    public static JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(URLConfig.baidu_url, Build.VERSION.SDK_INT);
            jSONObject.put(a.h, Build.VERSION.RELEASE);
            jSONObject.put("ii", cu.a(2, context));
            jSONObject.put("w", cu.b(context));
            jSONObject.put("h", cu.c(context));
            jSONObject.put("ly", ba.d);
            jSONObject.put("pv", "8");
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                jSONObject.put("pn", cu.f(2, context));
                jSONObject.put("a", packageInfo.versionCode);
                jSONObject.put("n", packageInfo.versionName);
            } catch (Exception e) {
                bb.a(e);
            }
            jSONObject.put("mc", cu.b(2, context));
            jSONObject.put("bm", cu.d(2, context));
            jSONObject.put("m", Build.MODEL);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}

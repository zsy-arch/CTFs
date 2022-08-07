package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
class p {
    static p a = new p();

    p() {
    }

    public void a(Context context, String str, String str2) {
        PackageManager packageManager = context.getPackageManager();
        String str3 = "unkown";
        if (!"android.intent.action.PACKAGE_REMOVED".equals(str)) {
            try {
                str3 = packageManager.getPackageInfo(str2, 8192).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                bb.a(e);
            }
        }
        String str4 = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("n", str2);
            jSONObject.put("a", str);
            jSONObject.put("v", str3);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("app_change", jSONArray);
            jSONObject2.put("meta-data", sb.toString());
            str4 = cj.a(jSONObject2.toString().getBytes());
        } catch (Exception e2) {
            bb.b(e2.getMessage());
        }
        if (!TextUtils.isEmpty(str4)) {
            x.d.a(System.currentTimeMillis(), str4);
        }
    }
}

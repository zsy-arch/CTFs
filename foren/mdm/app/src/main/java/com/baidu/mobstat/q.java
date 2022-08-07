package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class q {
    static final q a = new q();

    q() {
    }

    private void a(boolean z, PackageInfo packageInfo, JSONArray jSONArray) {
        if (!z || !packageInfo.packageName.startsWith("com.android.")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", packageInfo.packageName);
                jSONObject.put("v", String.valueOf(packageInfo.versionName));
                jSONObject.put("f", packageInfo.firstInstallTime);
                jSONObject.put("l", packageInfo.lastUpdateTime);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void b(Context context, boolean z) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            List<PackageInfo> arrayList = new ArrayList<>(1);
            try {
                arrayList = packageManager.getInstalledPackages(0);
            } catch (Exception e) {
                bb.b(e);
            }
            JSONArray jSONArray = new JSONArray();
            for (PackageInfo packageInfo : arrayList) {
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if (applicationInfo != null) {
                    if (z == ((applicationInfo.flags & 1) != 0)) {
                        a(z, packageInfo, jSONArray);
                    }
                }
            }
            if (jSONArray.length() != 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(System.currentTimeMillis() + "|");
                sb.append(z ? 1 : 0);
                String str = "";
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("app_list", jSONArray);
                    jSONObject.put("meta-data", sb.toString());
                    str = cj.a(jSONObject.toString().getBytes());
                } catch (Exception e2) {
                }
                if (!TextUtils.isEmpty(str)) {
                    x.b.a(System.currentTimeMillis(), str);
                }
            }
        }
    }

    public synchronized void a(Context context, boolean z) {
        b(context, z);
    }
}

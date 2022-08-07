package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class n {
    static n a = new n();

    private void a(Context context, ArrayList<o> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        String str = "";
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<o> it = arrayList.iterator();
            while (it.hasNext()) {
                JSONObject a2 = it.next().a();
                if (a2 != null) {
                    jSONArray.put(a2);
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("app_apk", jSONArray);
            jSONObject.put("meta-data", sb.toString());
            str = cj.a(jSONObject.toString().getBytes());
        } catch (Exception e) {
            bb.b(e);
        }
        if (!TextUtils.isEmpty(str)) {
            x.e.a(System.currentTimeMillis(), str);
        }
    }

    private void b(Context context) {
        a(context, c(context));
    }

    private ArrayList<o> c(Context context) {
        ArrayList<o> arrayList = new ArrayList<>();
        Iterator<PackageInfo> it = d(context).iterator();
        while (it.hasNext()) {
            PackageInfo next = it.next();
            ApplicationInfo applicationInfo = next.applicationInfo;
            if (applicationInfo != null) {
                String str = next.packageName;
                String str2 = next.versionName;
                String str3 = "";
                Signature[] signatureArr = next.signatures;
                if (signatureArr != null && signatureArr.length != 0) {
                    str3 = signatureArr[0].toChars().toString();
                }
                String a2 = cp.a(str3.getBytes());
                String str4 = "";
                String str5 = applicationInfo.sourceDir;
                if (!TextUtils.isEmpty(str5)) {
                    str4 = cp.a(new File(str5));
                }
                arrayList.add(new o(str, str2, a2, str4));
            }
        }
        return arrayList;
    }

    private ArrayList<PackageInfo> d(Context context) {
        ArrayList<PackageInfo> arrayList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return arrayList;
        }
        List<PackageInfo> arrayList2 = new ArrayList<>(1);
        try {
            arrayList2 = packageManager.getInstalledPackages(64);
        } catch (Exception e) {
            bb.b(e);
        }
        for (PackageInfo packageInfo : arrayList2) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null && (applicationInfo.flags & 1) == 0) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    public synchronized void a(Context context) {
        b(context);
    }
}

package com.baidu.mobstat;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class r {
    static r a = new r();
    private String b = "";

    r() {
    }

    private String a(Context context, String str) {
        String str2 = "";
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return str2;
        }
        try {
            str2 = packageManager.getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            bb.b(e);
        }
        return str2 == null ? "" : str2;
    }

    private ArrayList<s> a(Context context, int i) {
        return Build.VERSION.SDK_INT >= 21 ? c(context, i) : b(context, i);
    }

    private void a(Context context, ArrayList<s> arrayList, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() + "|");
        sb.append(z ? 1 : 0);
        String str = "";
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<s> it = arrayList.iterator();
            while (it.hasNext()) {
                JSONObject a2 = it.next().a();
                if (a2 != null) {
                    jSONArray.put(a2);
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("app_trace", jSONArray);
            jSONObject.put("meta-data", sb.toString());
            str = cj.a(jSONObject.toString().getBytes());
        } catch (Exception e) {
            bb.b(e);
        }
        if (!TextUtils.isEmpty(str)) {
            x.c.a(System.currentTimeMillis(), str);
        }
    }

    private void a(Context context, boolean z, int i) {
        ArrayList<s> a2 = a(context, i);
        if (a2 != null && a2.size() != 0) {
            if (z) {
                String b = a2.get(0).b();
                if (a(b, this.b)) {
                    this.b = b;
                }
            }
            a(context, a2, z);
        }
    }

    private boolean a(int i) {
        return i == 100 || i == 200 || i == 130;
    }

    private boolean a(String str, String str2) {
        return !TextUtils.isEmpty(str) && !str.equals(this.b);
    }

    private ArrayList<s> b(Context context, int i) {
        List<ActivityManager.RunningTaskInfo> list = null;
        try {
            list = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(50);
        } catch (Exception e) {
            bb.b(e);
        }
        if (list == null) {
            return new ArrayList<>();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ActivityManager.RunningTaskInfo runningTaskInfo : list) {
            if (linkedHashMap.size() > i) {
                break;
            }
            ComponentName componentName = runningTaskInfo.topActivity;
            if (componentName != null) {
                String packageName = componentName.getPackageName();
                if (!TextUtils.isEmpty(packageName) && !b(context, packageName) && !linkedHashMap.containsKey(packageName)) {
                    linkedHashMap.put(packageName, new s(packageName, a(context, packageName), ""));
                }
            }
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    private boolean b(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(str, 0).applicationInfo;
            if (applicationInfo != null) {
                return (applicationInfo.flags & 1) != 0;
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            bb.b(e);
            return false;
        }
    }

    private ArrayList<s> c(Context context, int i) {
        String[] strArr;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return new ArrayList<>();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < runningAppProcesses.size() && linkedHashMap.size() <= i; i2++) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i2);
            if (!(!a(runningAppProcessInfo.importance) || (strArr = runningAppProcessInfo.pkgList) == null || strArr.length == 0)) {
                String str = runningAppProcessInfo.pkgList[0];
                if (!TextUtils.isEmpty(str) && !b(context, str) && !linkedHashMap.containsKey(str)) {
                    linkedHashMap.put(str, new s(str, a(context, str), String.valueOf(runningAppProcessInfo.importance)));
                }
            }
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    public synchronized void a(Context context, boolean z) {
        int i = 1;
        if (!z) {
            i = 20;
        }
        a(context, z, i);
    }
}

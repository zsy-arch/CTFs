package cn.jpush.android.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class v {
    public static ArrayList<ad> a(Context context, boolean z) {
        ArrayList<ad> arrayList = new ArrayList<>();
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            for (int i = 0; i < installedPackages.size(); i++) {
                PackageInfo packageInfo = installedPackages.get(i);
                ad adVar = new ad();
                adVar.a = packageInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                adVar.b = packageInfo.packageName;
                adVar.c = packageInfo.versionName;
                adVar.d = packageInfo.versionCode;
                arrayList.add(adVar);
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            ac.b();
        } catch (Exception e2) {
            e2.printStackTrace();
            ac.b();
        }
        return arrayList;
    }

    public static String[] a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            ac.i();
            return null;
        }
    }
}

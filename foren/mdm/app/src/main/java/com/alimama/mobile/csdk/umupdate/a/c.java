package com.alimama.mobile.csdk.umupdate.a;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.hyphenate.util.EMPrivateConstant;
import com.hyphenate.util.HanziToPinyin;
import com.umeng.update.UpdateConfig;
import java.util.List;
import java.util.Locale;

/* compiled from: DefaultAppUtils.java */
/* loaded from: classes.dex */
public class c implements a {
    private a a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private float o;
    private int p;
    private int q;
    private String r;
    private String s;
    private Context t;

    /* renamed from: u  reason: collision with root package name */
    private boolean f2u = false;
    private boolean v = false;
    private boolean w = false;
    private boolean x = false;
    private String y = null;

    /* compiled from: DefaultAppUtils.java */
    /* loaded from: classes.dex */
    public static class a {
        public String a = EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME;
        public String b = EnvironmentCompat.MEDIA_UNKNOWN;
        public String c = "cell";
        public String d = "wifi";
    }

    public void a(Context context) {
        a(context, new a());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0153 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.content.Context r9, com.alimama.mobile.csdk.umupdate.a.c.a r10) {
        /*
            Method dump skipped, instructions count: 501
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alimama.mobile.csdk.umupdate.a.c.a(android.content.Context, com.alimama.mobile.csdk.umupdate.a.c$a):void");
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String d(String str) {
        if (str == null || !str.equalsIgnoreCase("amazon")) {
            return null;
        }
        return "amz";
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean a(String str) {
        try {
            return a(Class.forName(str));
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean a(Class<?> cls) {
        return a(new Intent(this.t, cls));
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean b(String str) {
        return a(new Intent(str));
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean a(Intent intent) {
        return this.t.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean b(Class<?> cls) {
        return this.t.getPackageManager().queryIntentServices(new Intent(this.t, cls), 65536).size() > 0;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public ActivityInfo c(Class<?> cls) {
        try {
            return this.t.getPackageManager().getActivityInfo(new ComponentName(this.t, cls), 0);
        } catch (PackageManager.NameNotFoundException e) {
            g.a(e, "Failed to locate info for activity [" + cls.getName() + "]", new Object[0]);
            return null;
        }
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean d(Class<?> cls) {
        return this.t.getPackageManager().queryBroadcastReceivers(new Intent(this.t, cls), 65536).size() > 0;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean a() {
        return false;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean b() {
        return false;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public void k() {
    }

    protected void a(Context context, Class<?>... clsArr) {
        for (Class<?> cls : clsArr) {
            if (c(cls) == null) {
                g.e("No activity element declared for [" + cls.getName() + "].  Please ensure you have included this in your AndroidManifest.xml", new Object[0]);
            }
        }
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean e(String str) {
        return this.t.getPackageManager().checkPermission(str, this.t.getPackageName()) == 0;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean l() {
        return this.t != null && this.t.getResources().getConfiguration().orientation == 1;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean c() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.t.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean d() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean c(String str) {
        if (this.t == null) {
            return false;
        }
        try {
            this.t.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public boolean e() {
        if (this.t != null) {
            return this.t.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
        }
        return false;
    }

    public static boolean a(Activity activity) {
        Intent b = b(activity);
        if (b == null) {
            return false;
        }
        activity.startActivity(b);
        return true;
    }

    public static Intent b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
            return null;
        }
        ResolveInfo resolveInfo = queryIntentActivities.get(0);
        intent.setFlags(67108864);
        intent.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
        return intent;
    }

    public int c(Context context) {
        return context.getApplicationInfo().icon;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String j() {
        return this.d;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String f() {
        return this.c;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String g() {
        return this.b;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String h() {
        return this.e;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String i() {
        return this.f;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String m() {
        return this.g;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String n() {
        return this.i;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String o() {
        return this.j;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String p() {
        return this.m;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String q() {
        return this.k;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String r() {
        if (!TextUtils.isEmpty(this.k) && !this.a.b.equals(this.k)) {
            return this.k;
        }
        if (TextUtils.isEmpty(this.m) || this.a.b.equals(this.m)) {
            return Settings.Secure.getString(this.t.getContentResolver(), "android_id");
        }
        return this.m;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String s() {
        return this.l;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String t() {
        return this.n;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public float u() {
        return this.o;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public int v() {
        if (this.t == null) {
            return 0;
        }
        try {
            return Settings.System.getInt(this.t.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException e) {
            g.e("Get screen bright exception,info:" + e.toString(), new Object[0]);
            return 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x0150 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0155 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.alimama.mobile.csdk.umupdate.a.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int w() {
        /*
            Method dump skipped, instructions count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alimama.mobile.csdk.umupdate.a.c.w():int");
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public int x() {
        if (this.t == null) {
            return -1;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) this.t.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return new Long(memoryInfo.availMem / 1048576).intValue();
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public int y() {
        return 0;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public int z() {
        return 0;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public int A() {
        Intent registerReceiver = this.t.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        return (registerReceiver.getIntExtra("level", 0) * 100) / registerReceiver.getIntExtra("scale", 100);
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String[] C() {
        ConnectivityManager connectivityManager;
        String[] strArr = new String[2];
        strArr[0] = this.a.b;
        strArr[1] = this.a.b;
        if (this.t != null) {
            if (e(UpdateConfig.g) && (connectivityManager = (ConnectivityManager) this.t.getSystemService("connectivity")) != null) {
                if (connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = this.a.d;
                    return strArr;
                }
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    strArr[0] = this.a.c;
                    strArr[1] = networkInfo.getSubtypeName();
                    return strArr;
                }
            }
            return strArr;
        }
        return strArr;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String B() {
        return this.h;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public Location D() {
        LocationManager locationManager;
        Location lastKnownLocation;
        Location lastKnownLocation2;
        try {
            locationManager = (LocationManager) this.t.getSystemService("location");
        } catch (Exception e) {
            g.a(e, "", new Object[0]);
        }
        if (e("android.permission.ACCESS_FINE_LOCATION") && (lastKnownLocation2 = locationManager.getLastKnownLocation(GeocodeSearch.GPS)) != null) {
            g.b("get location from gps:" + lastKnownLocation2.getLatitude() + "," + lastKnownLocation2.getLongitude(), new Object[0]);
            return lastKnownLocation2;
        } else if (!e("android.permission.ACCESS_COARSE_LOCATION") || (lastKnownLocation = locationManager.getLastKnownLocation("network")) == null) {
            g.c("Could not get loction from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?", new Object[0]);
            return null;
        } else {
            g.b("get location from network:" + lastKnownLocation.getLatitude() + "," + lastKnownLocation.getLongitude(), new Object[0]);
            return lastKnownLocation;
        }
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String E() {
        return this.r;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String f(String str) {
        Object obj;
        if (this.t != null) {
            try {
                ApplicationInfo applicationInfo = this.t.getPackageManager().getApplicationInfo(this.t.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get(str)) == null)) {
                    return obj.toString();
                }
            } catch (Exception e) {
                g.d("Could not read meta-data from AndroidManifest.xml.", new Object[0]);
            }
        }
        return null;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public String F() {
        return this.s;
    }

    @Override // com.alimama.mobile.csdk.umupdate.a.a
    public float a(float f) {
        return TypedValue.applyDimension(1, f, this.t.getResources().getDisplayMetrics());
    }

    private String g(String str) {
        return TextUtils.isEmpty(str) ? this.a.b : str.replaceAll(HanziToPinyin.Token.SEPARATOR, "");
    }

    void a(boolean z) {
        this.x = z;
    }
}

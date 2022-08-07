package com.tencent.smtt.sdk.ui.dialog;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.TbsListener;
import e.a.a.a.a;
import java.io.BufferedInputStream;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public Context f1429a;

    /* renamed from: b  reason: collision with root package name */
    public ResolveInfo f1430b;

    /* renamed from: c  reason: collision with root package name */
    public Drawable f1431c;

    /* renamed from: d  reason: collision with root package name */
    public String f1432d;

    /* renamed from: e  reason: collision with root package name */
    public String f1433e;
    public String f;
    public boolean g;
    public boolean h;
    public String i;

    /* JADX WARN: Removed duplicated region for block: B:7:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public b(android.content.Context r3, int r4, java.lang.String r5, java.lang.String r6) {
        /*
            r2 = this;
            r2.<init>()
            java.lang.String r0 = ""
            r2.f1432d = r0
            r2.f1433e = r0
            r1 = 0
            r2.g = r1
            r2.h = r1
            r2.i = r0
            r0 = 0
            r1 = -1
            if (r4 == r1) goto L_0x001d
            android.content.res.Resources r1 = r3.getResources()     // Catch: Exception -> 0x001d
            android.graphics.drawable.Drawable r4 = r1.getDrawable(r4)     // Catch: Exception -> 0x001d
            goto L_0x001e
        L_0x001d:
            r4 = r0
        L_0x001e:
            if (r4 != 0) goto L_0x0026
            java.lang.String r4 = "application_icon"
            android.graphics.drawable.Drawable r4 = com.tencent.smtt.sdk.ui.dialog.e.a(r4)
        L_0x0026:
            android.content.Context r3 = r3.getApplicationContext()
            r2.f1429a = r3
            r2.f1430b = r0
            r2.f = r0
            r2.f1431c = r4
            r2.f1432d = r6
            r3 = 1
            r2.g = r3
            r2.i = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.ui.dialog.b.<init>(android.content.Context, int, java.lang.String, java.lang.String):void");
    }

    public b(Context context, ResolveInfo resolveInfo) {
        this.f1432d = BuildConfig.FLAVOR;
        this.f1433e = BuildConfig.FLAVOR;
        this.g = false;
        this.h = false;
        this.i = BuildConfig.FLAVOR;
        this.f1429a = context.getApplicationContext();
        this.f1430b = resolveInfo;
        this.f1431c = null;
        this.f1432d = null;
        this.f = null;
    }

    public b(Context context, Drawable drawable, String str, String str2, String str3) {
        this.f1432d = BuildConfig.FLAVOR;
        this.f1433e = BuildConfig.FLAVOR;
        this.g = false;
        this.h = false;
        this.i = BuildConfig.FLAVOR;
        this.f1429a = context.getApplicationContext();
        this.f1430b = null;
        this.f1431c = drawable;
        this.f1432d = str;
        this.f = str2;
        this.h = true;
        this.f1433e = str3;
    }

    public static Drawable a(Context context, String str) {
        if (TbsConfig.APP_QB.equals(str)) {
            try {
                return e.a("application_icon");
            } catch (Throwable th) {
                StringBuilder a2 = a.a("getApkIcon Error:");
                a2.append(Log.getStackTraceString(th));
                a2.toString();
                return null;
            }
        } else {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, TbsListener.ErrorCode.DOWNLOAD_INTERRUPT);
                if (applicationInfo == null) {
                    return null;
                }
                Resources resourcesForApplication = packageManager.getResourcesForApplication(applicationInfo);
                TypedValue typedValue = new TypedValue();
                resourcesForApplication.getValue(applicationInfo.icon, typedValue, true);
                try {
                    return Drawable.createFromResourceStream(resourcesForApplication, typedValue, new BufferedInputStream(resourcesForApplication.getAssets().openNonAssetFd(typedValue.assetCookie, typedValue.string.toString()).createInputStream()), null);
                } catch (Exception unused) {
                    return null;
                }
            } catch (Exception e2) {
                a.b("e = ", e2);
                return null;
            }
        }
    }

    public Drawable a() {
        Drawable drawable = this.f1431c;
        if (drawable != null) {
            return drawable;
        }
        Drawable a2 = a(this.f1429a, d());
        if (a2 != null) {
            return a2;
        }
        ResolveInfo resolveInfo = this.f1430b;
        return resolveInfo != null ? resolveInfo.loadIcon(this.f1429a.getPackageManager()) : this.f1431c;
    }

    public void a(ResolveInfo resolveInfo) {
        this.f1430b = resolveInfo;
    }

    public void a(Drawable drawable) {
        this.f1431c = drawable;
    }

    public void a(String str) {
        this.f1433e = str;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public String b() {
        ResolveInfo resolveInfo = this.f1430b;
        return resolveInfo != null ? resolveInfo.loadLabel(this.f1429a.getPackageManager()).toString() : this.f1432d;
    }

    public ResolveInfo c() {
        return this.f1430b;
    }

    public String d() {
        ResolveInfo resolveInfo = this.f1430b;
        if (resolveInfo != null) {
            return resolveInfo.activityInfo.packageName;
        }
        String str = this.f;
        return str == null ? BuildConfig.FLAVOR : str;
    }

    public boolean e() {
        return this.g;
    }

    public boolean f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public String h() {
        return this.f1433e;
    }
}

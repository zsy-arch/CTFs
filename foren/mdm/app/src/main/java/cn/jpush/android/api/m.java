package cn.jpush.android.api;

import android.app.Activity;
import android.app.Application;
import android.app.TabActivity;
import android.os.Build;
import android.os.Bundle;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ao;
import cn.jpush.android.util.b;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m implements Application.ActivityLifecycleCallbacks {
    private static final String[] z;

    /* JADX WARN: Removed duplicated region for block: B:12:0x002a A[LOOP:1: B:7:0x0019->B:12:0x002a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    static {
        /*
            r3 = 2
            r2 = 1
            r1 = 0
            r0 = 4
            java.lang.String[] r5 = new java.lang.String[r0]
            java.lang.String r4 = "/TY\fu'^\u0013\u0017t:_S\n4/YI\u0017u \u0014p?S\u0000"
            r0 = -1
            r6 = r5
            r7 = r5
            r5 = r1
        L_0x000c:
            char[] r4 = r4.toCharArray()
            int r8 = r4.length
            if (r8 > r2) goto L_0x006b
            r9 = r1
        L_0x0014:
            r10 = r4
            r11 = r9
            r14 = r8
            r8 = r4
            r4 = r14
        L_0x0019:
            char r13 = r8[r9]
            int r12 = r11 % 5
            switch(r12) {
                case 0: goto L_0x005f;
                case 1: goto L_0x0062;
                case 2: goto L_0x0065;
                case 3: goto L_0x0068;
                default: goto L_0x0020;
            }
        L_0x0020:
            r12 = 26
        L_0x0022:
            r12 = r12 ^ r13
            char r12 = (char) r12
            r8[r9] = r12
            int r9 = r11 + 1
            if (r4 != 0) goto L_0x002e
            r8 = r10
            r11 = r9
            r9 = r4
            goto L_0x0019
        L_0x002e:
            r8 = r4
            r4 = r10
        L_0x0030:
            if (r8 > r9) goto L_0x0014
            java.lang.String r8 = new java.lang.String
            r8.<init>(r4)
            java.lang.String r4 = r8.intern()
            switch(r0) {
                case 0: goto L_0x0047;
                case 1: goto L_0x0050;
                case 2: goto L_0x005a;
                default: goto L_0x003e;
            }
        L_0x003e:
            r6[r5] = r4
            java.lang.String r0 = "\u0002S[\u001bY7YQ\u001bY/VQ\u001c{-QN"
            r4 = r0
            r5 = r2
            r6 = r7
            r0 = r1
            goto L_0x000c
        L_0x0047:
            r6[r5] = r4
            java.lang.String r0 = "/TY\fu'^\u0013\u0017t:_S\n4-[I\u001b}!HDPV\u000fos=R\u000bh"
            r4 = r0
            r5 = r3
            r6 = r7
            r0 = r2
            goto L_0x000c
        L_0x0050:
            r6[r5] = r4
            r4 = 3
            java.lang.String r0 = "&[N\u0010u:\u001at\u0010n+TIP[\rnt1T\u0011w|7Tn[S\u001a:\u0007TI\u001bt:\u0014~?N\u000b}r,C\u0011v|+T\rrx,"
            r5 = r4
            r6 = r7
            r4 = r0
            r0 = r3
            goto L_0x000c
        L_0x005a:
            r6[r5] = r4
            cn.jpush.android.api.m.z = r7
            return
        L_0x005f:
            r12 = 78
            goto L_0x0022
        L_0x0062:
            r12 = 58
            goto L_0x0022
        L_0x0065:
            r12 = 61
            goto L_0x0022
        L_0x0068:
            r12 = 126(0x7e, float:1.77E-43)
            goto L_0x0022
        L_0x006b:
            r9 = r1
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.m.<clinit>():void");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        String str;
        HashMap hashMap;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String unused = l.f = activity.getClass().getName();
        str = l.e;
        if (ao.a(str)) {
            String unused2 = l.e = activity.getClass().getName();
        }
        if (Build.VERSION.SDK_INT >= 14 && l.a) {
            if (!f.a && f.b().a()) {
                hashMap = l.d;
                str2 = l.e;
                hashMap.put(str2, 0);
                str3 = l.g;
                if (!ao.a(str3)) {
                    str4 = l.g;
                    str5 = l.e;
                    if (str4.equals(str5)) {
                        str6 = l.c;
                        str7 = l.e;
                        b.b(activity, str6, str7, 0);
                    }
                }
            }
            if (activity instanceof TabActivity) {
                ac.d();
            } else {
                f.a = false;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0065, code lost:
        r0 = cn.jpush.android.api.l.f;
     */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onActivityResumed(android.app.Activity r7) {
        /*
            r6 = this;
            r5 = 2
            r4 = 1
            r3 = 0
            java.lang.Class r0 = r7.getClass()
            java.lang.String r0 = r0.getName()
            cn.jpush.android.api.l.a(r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 14
            if (r0 < r1) goto L_0x004b
            boolean r0 = cn.jpush.android.api.l.a
            if (r0 == 0) goto L_0x004b
            boolean r0 = cn.jpush.android.api.l.a()
            if (r0 == 0) goto L_0x0057
            android.content.Intent r0 = new android.content.Intent
            java.lang.String[] r1 = cn.jpush.android.api.m.z
            r1 = r1[r3]
            r0.<init>(r1)
            java.lang.String r1 = r7.getPackageName()
            r0.setPackage(r1)
            java.lang.String[] r1 = cn.jpush.android.api.m.z
            r1 = r1[r5]
            r0.addCategory(r1)
            android.content.pm.PackageManager r1 = r7.getPackageManager()
            android.content.pm.ResolveInfo r0 = r1.resolveActivity(r0, r3)
            if (r0 != 0) goto L_0x004c
            java.lang.String[] r0 = cn.jpush.android.api.m.z
            r0 = r0[r4]
            java.lang.String[] r1 = cn.jpush.android.api.m.z
            r2 = 3
            r1 = r1[r2]
            cn.jpush.android.util.ac.d(r0, r1)
        L_0x004b:
            return
        L_0x004c:
            android.content.pm.ActivityInfo r0 = r0.activityInfo
            java.lang.String r0 = r0.name
            cn.jpush.android.api.l.b(r0)
            cn.jpush.android.api.l.a(r3)
            goto L_0x004b
        L_0x0057:
            boolean r0 = cn.jpush.android.api.f.b
            if (r0 != 0) goto L_0x00ab
            cn.jpush.android.api.f r0 = cn.jpush.android.api.f.b()
            boolean r0 = r0.a()
            if (r0 == 0) goto L_0x00ab
            java.lang.String r0 = cn.jpush.android.api.l.b()
            if (r0 == 0) goto L_0x00ab
            java.util.HashMap r0 = cn.jpush.android.api.l.c()
            java.lang.String r1 = cn.jpush.android.api.l.b()
            boolean r0 = r0.containsKey(r1)
            if (r0 == 0) goto L_0x00b3
            java.util.HashMap r0 = cn.jpush.android.api.l.c()
            java.lang.String r1 = cn.jpush.android.api.l.b()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            r0.put(r1, r2)
            java.lang.String r0 = cn.jpush.android.api.l.d()
            boolean r0 = cn.jpush.android.util.ao.a(r0)
            if (r0 != 0) goto L_0x00ab
            java.lang.String r0 = cn.jpush.android.api.l.d()
            java.lang.String r1 = cn.jpush.android.api.l.b()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00ab
            java.lang.String r0 = cn.jpush.android.api.l.e()
            java.lang.String r1 = cn.jpush.android.api.l.b()
            cn.jpush.android.util.b.b(r7, r0, r1, r5)
        L_0x00ab:
            boolean r0 = r7 instanceof android.app.TabActivity
            if (r0 == 0) goto L_0x00e6
            cn.jpush.android.util.ac.d()
            goto L_0x004b
        L_0x00b3:
            java.util.HashMap r0 = cn.jpush.android.api.l.c()
            java.lang.String r1 = cn.jpush.android.api.l.b()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)
            r0.put(r1, r2)
            java.lang.String r0 = cn.jpush.android.api.l.d()
            boolean r0 = cn.jpush.android.util.ao.a(r0)
            if (r0 != 0) goto L_0x00ab
            java.lang.String r0 = cn.jpush.android.api.l.d()
            java.lang.String r1 = cn.jpush.android.api.l.b()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x00ab
            java.lang.String r0 = cn.jpush.android.api.l.e()
            java.lang.String r1 = cn.jpush.android.api.l.b()
            cn.jpush.android.util.b.b(r7, r0, r1, r4)
            goto L_0x00ab
        L_0x00e6:
            cn.jpush.android.api.f.b = r3
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.m.onActivityResumed(android.app.Activity):void");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}

package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.em.db.UserDao;
import com.hyphenate.util.EMPrivateConstant;
import java.util.Hashtable;
import org.json.JSONObject;

/* compiled from: HeatMap.java */
/* loaded from: classes2.dex */
public final class ci {
    private static ci a = null;
    private Hashtable<String, JSONObject> b = new Hashtable<>();
    private boolean c = false;

    private ci() {
    }

    public static synchronized ci a() {
        ci ciVar;
        synchronized (ci.class) {
            if (a == null) {
                a = new ci();
            }
            ciVar = a;
        }
        return ciVar;
    }

    public final void a(Context context) {
        if (bw.a && !this.c) {
            cx.b();
            try {
                ch.a().b(context);
            } catch (Throwable th) {
                f.a(th, "HeatMap", "loadDB");
            }
            this.c = true;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
        if (r9.b.containsKey(r3) != false) goto L_0x002e;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void a(android.content.Context r10, java.lang.String r11, com.autonavi.aps.amapapi.model.AMapLocationServer r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            r3 = 0
            boolean r0 = com.loc.cx.a(r12)     // Catch: all -> 0x0071
            if (r0 == 0) goto L_0x000e
            if (r10 == 0) goto L_0x000e
            boolean r0 = com.loc.bw.a     // Catch: all -> 0x0071
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            monitor-exit(r9)
            return
        L_0x0010:
            java.util.Hashtable<java.lang.String, org.json.JSONObject> r0 = r9.b     // Catch: all -> 0x0071
            int r0 = r0.size()     // Catch: all -> 0x0071
            r1 = 500(0x1f4, float:7.0E-43)
            if (r0 <= r1) goto L_0x002e
            double r0 = r12.getLatitude()     // Catch: all -> 0x0071
            double r2 = r12.getLongitude()     // Catch: all -> 0x0071
            java.lang.String r3 = com.loc.by.a(r0, r2)     // Catch: all -> 0x0071
            java.util.Hashtable<java.lang.String, org.json.JSONObject> r0 = r9.b     // Catch: all -> 0x0071
            boolean r0 = r0.containsKey(r3)     // Catch: all -> 0x0071
            if (r0 == 0) goto L_0x000e
        L_0x002e:
            if (r3 != 0) goto L_0x003c
            double r0 = r12.getLatitude()     // Catch: all -> 0x0071
            double r2 = r12.getLongitude()     // Catch: all -> 0x0071
            java.lang.String r3 = com.loc.by.a(r0, r2)     // Catch: all -> 0x0071
        L_0x003c:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: all -> 0x0071
            r0.<init>()     // Catch: all -> 0x0071
            java.lang.String r1 = "key"
            r0.put(r1, r11)     // Catch: Throwable -> 0x0068, all -> 0x0071
            java.lang.String r1 = "lat"
            double r4 = r12.getLatitude()     // Catch: Throwable -> 0x0068, all -> 0x0071
            r0.put(r1, r4)     // Catch: Throwable -> 0x0068, all -> 0x0071
            java.lang.String r1 = "lon"
            double r4 = r12.getLongitude()     // Catch: Throwable -> 0x0068, all -> 0x0071
            r0.put(r1, r4)     // Catch: Throwable -> 0x0068, all -> 0x0071
            java.lang.String r4 = r0.toString()     // Catch: Throwable -> 0x0068, all -> 0x0071
            r5 = 1
            long r6 = com.loc.cx.a()     // Catch: Throwable -> 0x0068, all -> 0x0071
            r8 = 1
            r1 = r9
            r2 = r10
            r1.a(r2, r3, r4, r5, r6, r8)     // Catch: Throwable -> 0x0068, all -> 0x0071
            goto L_0x000e
        L_0x0068:
            r0 = move-exception
            java.lang.String r1 = "HeatMap"
            java.lang.String r2 = "update"
            com.loc.f.a(r0, r1, r2)     // Catch: all -> 0x0071
            goto L_0x000e
        L_0x0071:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ci.a(android.content.Context, java.lang.String, com.autonavi.aps.amapapi.model.AMapLocationServer):void");
    }

    public final synchronized void a(Context context, String str, String str2, int i, long j, boolean z) {
        if (context != null) {
            if (!TextUtils.isEmpty(str) && bw.a) {
                JSONObject jSONObject = this.b.get(str);
                JSONObject jSONObject2 = jSONObject == null ? new JSONObject() : jSONObject;
                jSONObject2.put(EMPrivateConstant.EMMultiUserConstant.MUC_ELEMENT_NAME, str2);
                jSONObject2.put(f.az, j);
                if (this.b.containsKey(str)) {
                    jSONObject2.put("num", jSONObject2.getInt("num") + i);
                } else {
                    jSONObject2.put("num", i);
                }
                this.b.put(str, jSONObject2);
                if (!f.k && !cw.b(context, UserDao.PREF_TABLE_NAME, "ddex", false) && i >= 120) {
                    f.k = true;
                    cw.a(context, UserDao.PREF_TABLE_NAME, "ddex", true);
                }
                if (z) {
                    ch.a().a(context, str, str2, j);
                }
            }
        }
    }

    public final void b() {
        ci a2 = a();
        if (!a2.b.isEmpty()) {
            a2.b.clear();
        }
        this.c = false;
    }
}

package com.loc;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.alipay.sdk.app.statistic.c;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.em.db.UserDao;
import com.hyphenate.util.EMPrivateConstant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: ReportUtil.java */
/* loaded from: classes2.dex */
public final class cu {
    private static List<br> g = new ArrayList();
    public SparseArray<Long> a = new SparseArray<>();
    public int b = -1;
    public long c = 0;
    String[] d = {"ol", "cl", "gl", "ha", "bs", "ds"};
    public int e = -1;
    public long f = -1;

    public static void a(Context context) {
        if (context != null) {
            try {
                if (cq.o() && g != null && g.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(g);
                    bs.a(arrayList, context);
                    g.clear();
                }
            } catch (Throwable th) {
                f.a(th, "ReportUtil", EMPrivateConstant.EMMultiUserConstant.ITEM_DESTROY);
            }
        }
    }

    public static void a(Context context, int i, int i2, long j, long j2) {
        if (i != -1 && i2 != -1 && context != null) {
            try {
                if (cq.o()) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("param_int_first", i);
                    jSONObject.put("param_int_second", i2);
                    jSONObject.put("param_long_first", j);
                    jSONObject.put("param_long_second", j2);
                    a(context, "O012", jSONObject);
                }
            } catch (Throwable th) {
                try {
                    f.a(th, "ReportUtil", "applyStatisticsEx");
                } catch (Throwable th2) {
                    f.a(th2, "ReportUtil", "reportServiceAliveTime");
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0029 A[Catch: Throwable -> 0x002f, TRY_LEAVE, TryCatch #0 {Throwable -> 0x002f, blocks: (B:5:0x0006, B:8:0x000d, B:11:0x0016, B:12:0x0019, B:14:0x001f, B:15:0x0023, B:18:0x0029), top: B:25:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(android.content.Context r5, int r6, com.amap.api.location.AMapLocation r7) {
        /*
            r2 = 0
            r1 = 1
            if (r5 == 0) goto L_0x000c
            if (r7 == 0) goto L_0x000c
            boolean r0 = com.loc.cq.o()     // Catch: Throwable -> 0x002f
            if (r0 != 0) goto L_0x000d
        L_0x000c:
            return
        L_0x000d:
            java.lang.String r0 = "net"
            int r3 = r7.getErrorCode()     // Catch: Throwable -> 0x002f
            if (r3 != 0) goto L_0x0043
            r3 = r1
        L_0x0016:
            switch(r6) {
                case 2: goto L_0x0038;
                case 3: goto L_0x0019;
                case 4: goto L_0x0038;
                case 5: goto L_0x003c;
                case 6: goto L_0x003c;
                case 7: goto L_0x0038;
                case 8: goto L_0x0038;
                default: goto L_0x0019;
            }     // Catch: Throwable -> 0x002f
        L_0x0019:
            int r4 = r7.getErrorCode()     // Catch: Throwable -> 0x002f
            if (r4 == 0) goto L_0x0026
            int r4 = r7.getErrorCode()     // Catch: Throwable -> 0x002f
            switch(r4) {
                case 4: goto L_0x0040;
                case 5: goto L_0x0040;
                case 6: goto L_0x0040;
                case 7: goto L_0x0026;
                case 8: goto L_0x0026;
                case 9: goto L_0x0026;
                case 10: goto L_0x0026;
                case 11: goto L_0x0040;
                default: goto L_0x0026;
            }     // Catch: Throwable -> 0x002f
        L_0x0026:
            r1 = r2
        L_0x0027:
            if (r1 == 0) goto L_0x000c
            java.lang.String r1 = "O005"
            a(r5, r1, r3, r0)     // Catch: Throwable -> 0x002f
            goto L_0x000c
        L_0x002f:
            r0 = move-exception
            java.lang.String r1 = "ReportUtil"
            java.lang.String r2 = "reportBatting"
            com.loc.f.a(r0, r1, r2)
            goto L_0x000c
        L_0x0038:
            java.lang.String r0 = "cache"
            r2 = r1
            goto L_0x0019
        L_0x003c:
            java.lang.String r0 = "net"
            r2 = r1
            goto L_0x0019
        L_0x0040:
            java.lang.String r0 = "net"
            goto L_0x0027
        L_0x0043:
            r3 = r2
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cu.a(android.content.Context, int, com.amap.api.location.AMapLocation):void");
    }

    public static void a(Context context, ct ctVar) {
        int i;
        boolean z = true;
        boolean z2 = false;
        if (context != null) {
            try {
                if (cq.o()) {
                    AMapLocationServer c = ctVar.c();
                    int intValue = Long.valueOf(ctVar.b() - ctVar.a()).intValue();
                    String str = c.a;
                    if (c != null) {
                        i = Long.valueOf(c.i()).intValue();
                        switch (c.getLocationType()) {
                            case 1:
                                z = false;
                                break;
                            case 2:
                            case 4:
                                str = "cache";
                                z2 = true;
                                break;
                            case 5:
                            case 6:
                                str = c.a;
                                break;
                        }
                    } else {
                        i = 0;
                    }
                    if (z) {
                        if (!z2) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("param_int_first", i);
                            jSONObject.put("param_int_second", intValue);
                            a(context, "O003", jSONObject);
                        }
                        a(context, "O002", intValue, str);
                    }
                }
            } catch (Throwable th) {
                f.a(th, "ReportUtil", "reportLBSLocUseTime");
            }
        }
    }

    public static void a(Context context, String str) {
        try {
            a(context, "O010", 0, str);
        } catch (Throwable th) {
            f.a(th, "ReportUtil", "reportDex_dexFunction");
        }
    }

    public static void a(Context context, String str, int i) {
        try {
            a(context, "O009", i, str);
        } catch (Throwable th) {
            f.a(th, "ReportUtil", "reportDex_dexLoadClass");
        }
    }

    private static void a(Context context, String str, int i, String str2) {
        if (context != null) {
            try {
                if (cq.o()) {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("param_string_first", str2);
                    }
                    if (i != Integer.MAX_VALUE) {
                        jSONObject.put("param_int_first", i);
                    }
                    a(context, str, jSONObject);
                }
            } catch (Throwable th) {
                f.a(th, "ReportUtil", "applyStatisticsEx");
            }
        }
    }

    private static void a(Context context, String str, JSONObject jSONObject) {
        if (context != null) {
            try {
                if (cq.o()) {
                    br brVar = new br(context, "loc", "3.3.0", str);
                    brVar.a(jSONObject.toString());
                    g.add(brVar);
                    if (g.size() >= 30) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.addAll(g);
                        bs.a(arrayList, context);
                        g.clear();
                    }
                }
            } catch (Throwable th) {
                f.a(th, "ReportUtil", "applyStatistics");
            }
        }
    }

    public static void a(String str, String str2) {
        try {
            z.b(f.a("loc"), str2, str);
        } catch (Throwable th) {
            f.a(th, "ReportUtil", "reportLog");
        }
    }

    public static boolean a(Context context, s sVar) {
        try {
            return au.a(context, sVar);
        } catch (Throwable th) {
            return false;
        }
    }

    public static void b(Context context, ct ctVar) {
        if (context != null) {
            try {
                if (cq.o()) {
                    a(context, "O004", Long.valueOf(ctVar.b() - ctVar.a()).intValue(), null);
                }
            } catch (Throwable th) {
                f.a(th, "ReportUtil", "reportGPSLocUseTime");
            }
        }
    }

    public final void a(Context context, int i) {
        try {
            if (this.b != i) {
                if (!(this.b == -1 || this.b == i)) {
                    long b = cx.b() - this.c;
                    this.a.append(this.b, Long.valueOf(this.a.get(this.b, 0L).longValue() + b));
                }
                this.c = cx.b() - cw.a(context, UserDao.PREF_TABLE_NAME, this.d[i]);
                this.b = i;
            }
        } catch (Throwable th) {
            f.a(th, "ReportUtil", "setLocationType");
        }
    }

    public final void a(Context context, AMapLocationClientOption aMapLocationClientOption) {
        int i;
        try {
            switch (aMapLocationClientOption.getLocationMode()) {
                case Battery_Saving:
                    i = 4;
                    break;
                case Device_Sensors:
                    i = 5;
                    break;
                case Hight_Accuracy:
                    i = 3;
                    break;
                default:
                    i = -1;
                    break;
            }
            if (this.e != i) {
                if (!(this.e == -1 || this.e == i)) {
                    this.a.append(this.e, Long.valueOf((cx.b() - this.f) + this.a.get(this.e, 0L).longValue()));
                }
                this.f = cx.b() - cw.a(context, UserDao.PREF_TABLE_NAME, this.d[i]);
                this.e = i;
            }
        } catch (Throwable th) {
            f.a(th, "ReportUtil", "setLocationMode");
        }
    }

    public final void b(Context context) {
        try {
            long b = cx.b() - this.c;
            if (this.b != -1) {
                this.a.append(this.b, Long.valueOf(this.a.get(this.b, 0L).longValue() + b));
            }
            long b2 = cx.b() - this.f;
            if (this.e != -1) {
                this.a.append(this.e, Long.valueOf(this.a.get(this.e, 0L).longValue() + b2));
            }
            for (int i = 0; i < 6; i++) {
                long longValue = this.a.get(i, 0L).longValue();
                if (longValue > 0 && longValue > cw.a(context, UserDao.PREF_TABLE_NAME, this.d[i])) {
                    cw.a(context, UserDao.PREF_TABLE_NAME, this.d[i], longValue);
                }
            }
        } catch (Throwable th) {
            f.a(th, "ReportUtil", "saveLocationTypeAndMode");
        }
    }

    public final int c(Context context) {
        try {
            long a = cw.a(context, UserDao.PREF_TABLE_NAME, this.d[2]);
            long a2 = cw.a(context, UserDao.PREF_TABLE_NAME, this.d[0]);
            long a3 = cw.a(context, UserDao.PREF_TABLE_NAME, this.d[1]);
            if (a == 0 && a2 == 0 && a3 == 0) {
                return -1;
            }
            long j = a2 - a;
            long j2 = a3 - a;
            return a > j ? a > j2 ? 2 : 1 : j > j2 ? 0 : 1;
        } catch (Throwable th) {
            return -1;
        }
    }

    public final int d(Context context) {
        try {
            long a = cw.a(context, UserDao.PREF_TABLE_NAME, this.d[3]);
            long a2 = cw.a(context, UserDao.PREF_TABLE_NAME, this.d[4]);
            long a3 = cw.a(context, UserDao.PREF_TABLE_NAME, this.d[5]);
            if (a == 0 && a2 == 0 && a3 == 0) {
                return -1;
            }
            return a > a2 ? a > a3 ? 3 : 5 : a2 > a3 ? 4 : 5;
        } catch (Throwable th) {
            return -1;
        }
    }

    public final void e(Context context) {
        for (int i = 0; i < this.d.length; i++) {
            try {
                cw.a(context, UserDao.PREF_TABLE_NAME, this.d[i], 0L);
            } catch (Throwable th) {
                return;
            }
        }
    }
}

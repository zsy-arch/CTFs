package com.baidu.mobstat;

import android.content.Context;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ay {
    public static final ay a = new ay();

    private void a(JSONObject jSONObject) {
        bc bcVar = new bc(jSONObject);
        ba.c = bcVar.a;
        ba.d = bcVar.b;
        ba.e = bcVar.c;
    }

    private boolean a() {
        return !x.a.b() || !x.b.b() || !x.c.b() || !x.d.b() || !x.e.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0063 A[LOOP:0: B:12:0x005d->B:14:0x0063, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00ab A[LOOP:1: B:25:0x00a5->B:27:0x00ab, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ed A[LOOP:2: B:35:0x00e7->B:37:0x00ed, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0130 A[LOOP:3: B:45:0x012a->B:47:0x0130, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0174 A[LOOP:4: B:55:0x016e->B:57:0x0174, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0148 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0081 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00c3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x018c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(android.content.Context r6, org.json.JSONObject r7) {
        /*
            Method dump skipped, instructions count: 472
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.ay.b(android.content.Context, org.json.JSONObject):void");
    }

    private void c(Context context) {
        bb.a("collectAPWithStretegy 1");
        ax a2 = ax.a(context);
        long a3 = a2.a(t.AP_LIST);
        long currentTimeMillis = System.currentTimeMillis();
        long e = a2.e();
        bb.a("now time: " + currentTimeMillis + ": last time: " + a3 + "; time interval: " + e);
        if (a3 == 0 || currentTimeMillis - a3 > e) {
            bb.a("collectAPWithStretegy 2");
            m.a(context);
        }
    }

    private void d(Context context) {
        bb.a("collectAPPListWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        ax a2 = ax.a(context);
        long a3 = a2.a(t.APP_USER_LIST);
        long f = a2.f();
        bb.a("now time: " + currentTimeMillis + ": last time: " + a3 + "; userInterval : " + f);
        if (a3 == 0 || currentTimeMillis - a3 > f) {
            bb.a("collectUserAPPListWithStretegy 2");
            m.a(context, false);
        }
        long a4 = a2.a(t.APP_SYS_LIST);
        long g = a2.g();
        bb.a("now time: " + currentTimeMillis + ": last time: " + a4 + "; sysInterval : " + g);
        if (a4 == 0 || currentTimeMillis - a4 > g) {
            bb.a("collectSysAPPListWithStretegy 2");
            m.a(context, true);
        }
    }

    private void e(Context context) {
        bb.a("collectAPPTraceWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        ax a2 = ax.a(context);
        long a3 = a2.a(t.APP_TRACE_HIS);
        long i = a2.i();
        bb.a("now time: " + currentTimeMillis + ": last time: " + a3 + "; time interval: " + i);
        if (a3 == 0 || currentTimeMillis - a3 > i) {
            bb.a("collectAPPTraceWithStretegy 2");
            m.b(context, false);
        }
    }

    private void f(Context context) {
        bb.a("collectAPKWithStretegy 1");
        long currentTimeMillis = System.currentTimeMillis();
        ax a2 = ax.a(context);
        long a3 = a2.a(t.APP_APK);
        long h = a2.h();
        bb.a("now time: " + currentTimeMillis + ": last time: " + a3 + "; interval : " + h);
        if (a3 == 0 || currentTimeMillis - a3 > h) {
            bb.a("collectAPKWithStretegy 2");
            m.b(context);
        }
    }

    private void g(Context context) {
        ax.a(context).a(t.LAST_SEND, System.currentTimeMillis());
        JSONObject a2 = u.a(context);
        bb.a("header: " + a2);
        while (a()) {
            b(context, a2);
        }
    }

    public void a(Context context, long j) {
        ax.a(context).a(t.LAST_UPDATE, j);
    }

    public void a(Context context, String str) {
        ax.a(context).a(str);
    }

    public void a(Context context, JSONObject jSONObject) {
        bb.a("startDataAnynalyzed start");
        a(jSONObject);
        ax a2 = ax.a(context);
        boolean a3 = a2.a();
        bb.a("is data collect closed:" + a3);
        if (!a3) {
            if (!x.a.b(10000)) {
                c(context);
            }
            if (!x.b.b(10000)) {
                d(context);
            }
            if (!x.c.b(10000)) {
                e(context);
            }
            if (ba.f && !x.e.b(10000)) {
                f(context);
            }
            boolean m = cu.m(context);
            if (m && a2.l()) {
                bb.a("sendLog");
                g(context);
            } else if (!m) {
                bb.a("isWifiAvailable = false, will not sendLog");
            } else {
                bb.a("can not sendLog due to time stratergy");
            }
        }
        bb.a("startDataAnynalyzed finished");
    }

    public boolean a(Context context) {
        ax a2 = ax.a(context);
        long a3 = a2.a(t.LAST_UPDATE);
        long c = a2.c();
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a3 > c) {
            bb.a("need to update, checkWithLastUpdateTime lastUpdateTime =" + a3 + "nowTime=" + currentTimeMillis + ";timeInteveral=" + c);
            return true;
        }
        bb.a("no need to update, checkWithLastUpdateTime lastUpdateTime =" + a3 + "nowTime=" + currentTimeMillis + ";timeInteveral=" + c);
        return false;
    }

    public void b(Context context, String str) {
        ax.a(context).b(str);
    }

    public boolean b(Context context) {
        return !ax.a(context).a() || a(context);
    }
}

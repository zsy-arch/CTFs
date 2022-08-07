package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import java.lang.reflect.Method;
import u.aly.aw;

/* compiled from: SessionTracker.java */
/* loaded from: classes2.dex */
public class as {
    private static final String a = "session_start_time";
    private static final String b = "session_end_time";
    private static final String c = "session_id";
    private static final String g = "activities";
    private static final String h = "uptr";
    private static final String i = "dntr";
    private static String j = null;
    private static Context k = null;
    private final String d = "a_start_time";
    private final String e = "a_end_time";
    private final String f = "autoact";

    public aw.o a(Context context) {
        Method method;
        Method method2;
        int i2;
        SharedPreferences a2 = aq.a(context);
        String string = a2.getString(c, null);
        if (string == null) {
            return null;
        }
        long j2 = a2.getLong(a, 0L);
        long j3 = a2.getLong(b, 0L);
        long j4 = 0;
        if (j3 != 0) {
            j4 = j3 - j2;
            if (Math.abs(j4) > a.j) {
                j4 = 0;
            }
        }
        aw.o oVar = new aw.o();
        oVar.b = string;
        oVar.c = j2;
        oVar.d = j3;
        oVar.e = j4;
        double[] location = AnalyticsConfig.getLocation();
        if (location != null) {
            oVar.j.a = location[0];
            oVar.j.b = location[1];
            oVar.j.c = System.currentTimeMillis();
        }
        try {
            Class<?> cls = Class.forName("android.net.TrafficStats");
            method = cls.getMethod("getUidRxBytes", Integer.TYPE);
            method2 = cls.getMethod("getUidTxBytes", Integer.TYPE);
            i2 = context.getApplicationInfo().uid;
        } catch (Throwable th) {
        }
        if (i2 == -1) {
            return null;
        }
        long longValue = ((Long) method.invoke(null, Integer.valueOf(i2))).longValue();
        long longValue2 = ((Long) method2.invoke(null, Integer.valueOf(i2))).longValue();
        if (longValue > 0 && longValue2 > 0) {
            long j5 = a2.getLong(h, -1L);
            long j6 = a2.getLong(i, -1L);
            a2.edit().putLong(h, longValue2).putLong(i, longValue).commit();
            if (j5 > 0 && j6 > 0) {
                long j7 = longValue - j6;
                long j8 = longValue2 - j5;
                if (j7 > 0 && j8 > 0) {
                    oVar.i.a = j7;
                    oVar.i.b = j8;
                }
            }
        }
        au.a(a2, oVar);
        ad.a(a2, oVar);
        a(a2);
        return oVar;
    }

    private void a(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(a);
        edit.remove(b);
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.remove(g);
        edit.remove("autoact");
        edit.commit();
    }

    public String b(Context context) {
        String c2 = bl.c(context);
        String appkey = AnalyticsConfig.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(currentTimeMillis).append(appkey).append(c2);
        j = bm.a(sb.toString());
        return j;
    }

    public void c(Context context) {
        k = context;
        SharedPreferences a2 = aq.a(context);
        if (a2 != null) {
            SharedPreferences.Editor edit = a2.edit();
            int i2 = a2.getInt(a.y, 0);
            int parseInt = Integer.parseInt(bl.a(k));
            if (i2 != 0 && parseInt != i2) {
                if (g(context) == null) {
                    a(context, a2);
                }
                e(k);
                af.b(k).c();
                f(k);
            } else if (b(a2)) {
                bo.c("Start new session: " + a(context, a2));
            } else {
                String string = a2.getString(c, null);
                edit.putLong("a_start_time", System.currentTimeMillis());
                edit.putLong("a_end_time", 0L);
                edit.commit();
                bo.c("Extend current session: " + string);
            }
        }
    }

    public void d(Context context) {
        SharedPreferences a2 = aq.a(context);
        if (a2 != null) {
            if (a2.getLong("a_start_time", 0L) != 0 || !AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                long currentTimeMillis = System.currentTimeMillis();
                SharedPreferences.Editor edit = a2.edit();
                edit.putLong("a_start_time", 0L);
                edit.putLong("a_end_time", currentTimeMillis);
                edit.putLong(b, currentTimeMillis);
                edit.commit();
                return;
            }
            bo.e("onPause called before onResume");
        }
    }

    private boolean b(SharedPreferences sharedPreferences) {
        long j2 = sharedPreferences.getLong("a_start_time", 0L);
        long j3 = sharedPreferences.getLong("a_end_time", 0L);
        long currentTimeMillis = System.currentTimeMillis();
        if (j2 != 0 && currentTimeMillis - j2 < AnalyticsConfig.kContinueSessionMillis) {
            bo.e("onResume called before onPause");
            return false;
        } else if (currentTimeMillis - j3 > AnalyticsConfig.kContinueSessionMillis) {
            return true;
        } else {
            return false;
        }
    }

    private String a(Context context, SharedPreferences sharedPreferences) {
        af b2 = af.b(context);
        String b3 = b(context);
        aw.o a2 = a(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(c, b3);
        edit.putLong(a, System.currentTimeMillis());
        edit.putLong(b, 0L);
        edit.putLong("a_start_time", System.currentTimeMillis());
        edit.putLong("a_end_time", 0L);
        edit.putInt(a.y, Integer.parseInt(bl.a(context)));
        edit.putString(a.z, bl.b(context));
        edit.commit();
        if (a2 != null) {
            b2.a(a2);
        } else {
            b2.a((aj) null);
        }
        return b3;
    }

    public boolean e(Context context) {
        boolean z = false;
        SharedPreferences a2 = aq.a(context);
        if (!(a2 == null || a2.getString(c, null) == null)) {
            long j2 = a2.getLong("a_start_time", 0L);
            long j3 = a2.getLong("a_end_time", 0L);
            if (j2 > 0 && j3 == 0) {
                z = true;
                d(context);
            }
            af b2 = af.b(context);
            aw.o a3 = a(context);
            if (a3 != null) {
                b2.b(a3);
            }
        }
        return z;
    }

    public void f(Context context) {
        SharedPreferences a2 = aq.a(context);
        if (a2 != null) {
            String b2 = b(context);
            SharedPreferences.Editor edit = a2.edit();
            edit.putString(c, b2);
            edit.putLong(a, System.currentTimeMillis());
            edit.putLong(b, 0L);
            edit.putLong("a_start_time", System.currentTimeMillis());
            edit.putLong("a_end_time", 0L);
            edit.putInt(a.y, Integer.parseInt(bl.a(context)));
            edit.putString(a.z, bl.b(context));
            edit.commit();
            bo.c("Restart session: " + b2);
        }
    }

    public static String g(Context context) {
        if (j == null) {
            j = aq.a(context).getString(c, null);
        }
        return j;
    }

    public static String a() {
        try {
            if (j == null) {
                j = aq.a(k).getString(c, null);
            }
        } catch (Exception e) {
        }
        return j;
    }
}

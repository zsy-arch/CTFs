package com.baidu.mobstat;

import android.app.ActivityManager;
import android.content.Context;
import java.util.List;

/* loaded from: classes.dex */
public abstract class an extends Enum<an> {
    public static final an a = new ao("SERVICE", 0, 1);
    public static final an b = new ap("NO_SERVICE", 1, 2);
    public static final an c = new aq("ERISED", 2, 3);
    private static final /* synthetic */ an[] e = {a, b, c};
    private int d;

    private an(String str, int i, int i2) {
        super(str, i);
        this.d = i2;
    }

    public /* synthetic */ an(String str, int i, int i2, ao aoVar) {
        this(str, i, i2);
    }

    public static an a(int i) {
        an[] values = values();
        for (an anVar : values) {
            if (anVar.d == i) {
                return anVar;
            }
        }
        return b;
    }

    public static boolean b(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            try {
                List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(Integer.MAX_VALUE);
                int i = 0;
                while (runningServices != null) {
                    if (i >= runningServices.size()) {
                        break;
                    } else if ("com.baidu.bottom.service.BottomService".equals(runningServices.get(i).service.getClassName())) {
                        return true;
                    } else {
                        i++;
                    }
                }
            } catch (Exception e2) {
                cr.a(e2);
            }
        }
        return false;
    }

    public static an valueOf(String str) {
        return (an) Enum.valueOf(an.class, str);
    }

    public static an[] values() {
        return (an[]) e.clone();
    }

    public abstract void a(Context context);

    @Override // java.lang.Enum
    public String toString() {
        return String.valueOf(this.d);
    }
}

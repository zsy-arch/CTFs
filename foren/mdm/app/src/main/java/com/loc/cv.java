package com.loc;

import android.content.Context;

/* compiled from: RollBackDynamic.java */
/* loaded from: classes2.dex */
public final class cv {
    static boolean a = false;
    static boolean b = false;
    static boolean c = false;
    static boolean d = false;
    static int e = 0;
    static int f = 0;
    static boolean g = true;
    static boolean h = false;

    public static void a(Context context) {
        try {
            if (c && !a) {
                cw.a(context, "loc", "startMark", cw.b(context, "loc", "startMark", 0) + 1);
                a = true;
            }
        } catch (Throwable th) {
            f.a(th, "RollBackDynamic", "AddStartMark");
        }
    }

    private static void a(Context context, int i) {
        try {
            if (c) {
                cw.a(context, "loc", "endMark", i);
                cw.a(context, "loc", "startMark", i);
            }
        } catch (Throwable th) {
            f.a(th, "RollBackDynamic", "resetMark");
        }
    }

    public static void a(Context context, s sVar) {
        if (!d) {
            c = au.a(context, sVar);
            d = true;
            if (!c && f.c()) {
                au.a(context, "loc");
                cu.a("dexrollbackstatistics", "RollBack because of version error");
            }
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            au.a(context, str);
            cu.a("dexrollbackstatistics", "RollBack because of " + str2);
        } catch (Throwable th) {
            f.a(th, "RollBackDynamic", "rollBackDynamicFile");
        }
    }

    public static void b(Context context) {
        try {
            if (!d) {
                a(context, f.a("loc"));
                d = true;
            }
            if (c && !b) {
                cw.a(context, "loc", "endMark", cw.b(context, "loc", "endMark", 0) + 1);
                b = true;
            }
        } catch (Throwable th) {
            f.a(th, "RollBackDynamic", "AddEndMark");
        }
    }

    public static boolean c(Context context) {
        try {
        } catch (Throwable th) {
            f.a(th, "RollBackDynamic", "checkMark");
        }
        if (!c) {
            return false;
        }
        if (h) {
            return g;
        }
        if (e == 0) {
            e = cw.b(context, "loc", "startMark", 0);
        }
        if (f == 0) {
            f = cw.b(context, "loc", "endMark", 0);
        }
        if (!a && !b) {
            if (e < f) {
                a(context, 0);
                g = true;
            }
            if (e - f > 0 && e > 99) {
                a(context, 0);
                g = true;
            }
            if (e - f > 0 && e < 99) {
                a(context, -2);
                g = false;
            }
            if (e - f > 0 && f < 0) {
                a(context, "loc", "checkMark");
                g = false;
            }
        }
        cw.a(context, "loc", "isload", g);
        h = true;
        return g;
    }

    public static boolean d(Context context) {
        try {
            if (!c) {
                return false;
            }
            return cw.b(context, "loc", "isload", true);
        } catch (Throwable th) {
            f.a(th, "RollBackDynamic", "isLoad");
            return true;
        }
    }
}

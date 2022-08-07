package com.amap.api.services.a;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Log.java */
/* loaded from: classes.dex */
public class bi {
    public static final String a = "/a/";
    static final String b = "b";
    static final String c = "c";
    static final String d = "d";
    public static final String e = "e";
    public static final String f = "f";

    public static String a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + a + str;
    }

    public static Class<? extends bz> a(int i) {
        switch (i) {
            case 0:
                return bu.class;
            case 1:
                return bw.class;
            case 2:
                return bt.class;
            default:
                return null;
        }
    }

    public static bz b(int i) {
        switch (i) {
            case 0:
                return new bu();
            case 1:
                return new bw();
            case 2:
                return new bt();
            default:
                return null;
        }
    }

    public static String c(int i) {
        switch (i) {
            case 0:
                return c;
            case 1:
                return b;
            case 2:
                return d;
            default:
                return "";
        }
    }

    static bo a(Context context, int i) {
        switch (i) {
            case 0:
                return new bm(i);
            case 1:
                return new bn(i);
            case 2:
                return new bl(i);
            default:
                return null;
        }
    }

    public static void a(final Context context, final Throwable th, final int i, final String str, final String str2) {
        try {
            ExecutorService b2 = bk.b();
            if (b2 != null && !b2.isShutdown()) {
                b2.submit(new Runnable() { // from class: com.amap.api.services.a.bi.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            bo a2 = bi.a(context, i);
                            if (a2 != null) {
                                a2.a(context, th, str, str2);
                            }
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e2) {
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    public static void a(Context context) {
        try {
            bo a2 = a(context, 2);
            if (a2 != null) {
                a2.b(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(final Context context) {
        try {
            ExecutorService b2 = bk.b();
            if (b2 != null && !b2.isShutdown()) {
                b2.submit(new Runnable() { // from class: com.amap.api.services.a.bi.2
                    /* JADX WARN: Removed duplicated region for block: B:26:0x0064  */
                    /* JADX WARN: Removed duplicated region for block: B:28:0x0069  */
                    /* JADX WARN: Removed duplicated region for block: B:30:0x006e  */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void run() {
                        /*
                            Method dump skipped, instructions count: 179
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bi.AnonymousClass2.run():void");
                    }
                });
            }
        } catch (Throwable th) {
            bh.a(th, "Log", "processLog");
        }
    }
}

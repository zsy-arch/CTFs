package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Log.java */
/* loaded from: classes.dex */
public class gp {
    public static final String a = "/a/";
    static final String b = "b";
    static final String c = "c";
    static final String d = "d";
    public static final String g = "e";
    public static final String h = "f";
    public static final String e = "g";
    public static final String f = "h";

    public static String a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + a + str;
    }

    public static Class<? extends hg> a(int i) {
        switch (i) {
            case 0:
                return hb.class;
            case 1:
                return hd.class;
            case 2:
                return ha.class;
            default:
                return null;
        }
    }

    public static hg b(int i) {
        switch (i) {
            case 0:
                return new hb();
            case 1:
                return new hd();
            case 2:
                return new ha();
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

    static gv a(Context context, int i) {
        switch (i) {
            case 0:
                return new gt(i);
            case 1:
                return new gu(i);
            case 2:
                return new gs(i);
            default:
                return null;
        }
    }

    public static void a(final Context context, final gj gjVar, final String str, final String str2) {
        ExecutorService c2;
        try {
            if (gjVar.f() && (c2 = gr.c()) != null && !c2.isShutdown()) {
                c2.submit(new Runnable() { // from class: com.amap.api.col.gp.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            gv a2 = gp.a(context, 1);
                            if (TextUtils.isEmpty(str2)) {
                                a2.a(gjVar, context, new Throwable("gpsstatistics"), str, (String) null, (String) null);
                            } else {
                                a2.a(gjVar, context, str2, str, (String) null, (String) null);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }
        } catch (RejectedExecutionException e2) {
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(final Context context, final Throwable th, final int i, final String str, final String str2) {
        try {
            ExecutorService c2 = gr.c();
            if (c2 != null && !c2.isShutdown()) {
                c2.submit(new Runnable() { // from class: com.amap.api.col.gp.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            gv a2 = gp.a(context, i);
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
            gv a2 = a(context, 2);
            if (a2 != null) {
                a2.b(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(final Context context) {
        try {
            ExecutorService c2 = gr.c();
            if (c2 != null && !c2.isShutdown()) {
                c2.submit(new Runnable() { // from class: com.amap.api.col.gp.3
                    /* JADX WARN: Removed duplicated region for block: B:27:0x0076  */
                    /* JADX WARN: Removed duplicated region for block: B:29:0x007b  */
                    /* JADX WARN: Removed duplicated region for block: B:31:0x0080  */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public void run() {
                        /*
                            Method dump skipped, instructions count: 203
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.gp.AnonymousClass3.run():void");
                    }
                });
            }
        } catch (Throwable th) {
            go.a(th, "Log", "processLog");
        }
    }
}

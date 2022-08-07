package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: Log.java */
/* loaded from: classes2.dex */
public final class x {
    public static final String a = "/a/";
    static final String b = "b";
    static final String c = "c";
    static final String d = "d";
    public static final String g = "e";
    public static final String h = "f";
    public static final String e = "g";
    public static final String f = "h";

    public static Class<? extends ao> a(int i) {
        switch (i) {
            case 0:
                return aj.class;
            case 1:
                return al.class;
            case 2:
                return ai.class;
            default:
                return null;
        }
    }

    public static String a(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + a + str;
    }

    public static void a(Context context) {
        try {
            ad d2 = d(2);
            if (d2 != null) {
                d2.b(context);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(final Context context, final s sVar, final String str, final String str2) {
        ExecutorService b2;
        try {
            if (sVar.e() && (b2 = z.b()) != null && !b2.isShutdown()) {
                b2.submit(new Runnable() { // from class: com.loc.x.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            Context context2 = context;
                            ad d2 = x.d(1);
                            if (TextUtils.isEmpty(str2)) {
                                d2.a(sVar, context, new Throwable("gpsstatistics"), str, (String) null, (String) null);
                            } else {
                                d2.a(sVar, context, str2, str, (String) null, (String) null);
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
            ExecutorService b2 = z.b();
            if (b2 != null && !b2.isShutdown()) {
                b2.submit(new Runnable() { // from class: com.loc.x.2
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            Context context2 = context;
                            ad d2 = x.d(i);
                            if (d2 != null) {
                                d2.a(context, th, str, str2);
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

    public static ao b(int i) {
        switch (i) {
            case 0:
                return new aj();
            case 1:
                return new al();
            case 2:
                return new ai();
            default:
                return null;
        }
    }

    public static void b(final Context context) {
        try {
            ExecutorService b2 = z.b();
            if (b2 != null && !b2.isShutdown()) {
                b2.submit(new Runnable() { // from class: com.loc.x.3
                    /* JADX WARN: Removed duplicated region for block: B:26:0x006e  */
                    /* JADX WARN: Removed duplicated region for block: B:28:0x0073  */
                    /* JADX WARN: Removed duplicated region for block: B:30:0x0078  */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void run() {
                        /*
                            Method dump skipped, instructions count: 207
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.loc.x.AnonymousClass3.run():void");
                    }
                });
            }
        } catch (Throwable th) {
            w.a(th, "Log", "processLog");
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

    static ad d(int i) {
        switch (i) {
            case 0:
                return new ab(i);
            case 1:
                return new ac(i);
            case 2:
                return new aa(i);
            default:
                return null;
        }
    }
}

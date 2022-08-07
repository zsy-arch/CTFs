package com.baidu.mobstat;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* loaded from: classes.dex */
public final class cr {
    public static int a = 7;

    private static String a() {
        return "sdkstat";
    }

    private static void a(int i, String str) {
        if (a(i)) {
            Log.println(i, a(), str);
        }
    }

    public static void a(String str) {
        a(3, str);
    }

    public static void a(String str, Throwable th) {
        a(3, str + '\n' + d(th));
    }

    public static void a(Throwable th) {
        a(3, d(th));
    }

    private static boolean a(int i) {
        return i >= a;
    }

    public static void b(String str) {
        a(5, str);
    }

    public static void b(Throwable th) {
        a(5, d(th));
    }

    public static void c(String str) {
        a(6, str);
    }

    public static void c(Throwable th) {
        a(6, d(th));
    }

    private static String d(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}

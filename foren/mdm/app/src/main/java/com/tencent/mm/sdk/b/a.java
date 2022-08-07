package com.tencent.mm.sdk.b;

import android.os.Build;
import android.os.Looper;
import android.os.Process;

/* loaded from: classes2.dex */
public final class a {
    private static int level = 6;
    private static AbstractC0084a n;
    private static AbstractC0084a o;
    private static final String p;

    /* renamed from: com.tencent.mm.sdk.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface AbstractC0084a {
        int b();

        void d(String str, String str2);

        void e(String str, String str2);

        void f(String str, String str2);
    }

    static {
        b bVar = new b();
        n = bVar;
        o = bVar;
        StringBuilder sb = new StringBuilder();
        sb.append("VERSION.RELEASE:[" + Build.VERSION.RELEASE);
        sb.append("] VERSION.CODENAME:[" + Build.VERSION.CODENAME);
        sb.append("] VERSION.INCREMENTAL:[" + Build.VERSION.INCREMENTAL);
        sb.append("] BOARD:[" + Build.BOARD);
        sb.append("] DEVICE:[" + Build.DEVICE);
        sb.append("] DISPLAY:[" + Build.DISPLAY);
        sb.append("] FINGERPRINT:[" + Build.FINGERPRINT);
        sb.append("] HOST:[" + Build.HOST);
        sb.append("] MANUFACTURER:[" + Build.MANUFACTURER);
        sb.append("] MODEL:[" + Build.MODEL);
        sb.append("] PRODUCT:[" + Build.PRODUCT);
        sb.append("] TAGS:[" + Build.TAGS);
        sb.append("] TYPE:[" + Build.TYPE);
        sb.append("] USER:[" + Build.USER + "]");
        p = sb.toString();
    }

    public static void a(String str, String str2) {
        a(str, str2, null);
    }

    public static void a(String str, String str2, Object... objArr) {
        if (o != null && o.b() <= 4) {
            String format = objArr == null ? str2 : String.format(str2, objArr);
            if (format == null) {
                format = "";
            }
            AbstractC0084a aVar = o;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.f(str, format);
        }
    }

    public static void b(String str, String str2) {
        if (o != null && o.b() <= 2) {
            if (str2 == null) {
                str2 = "";
            }
            AbstractC0084a aVar = o;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.d(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (o != null && o.b() <= 1) {
            if (str2 == null) {
                str2 = "";
            }
            AbstractC0084a aVar = o;
            Process.myPid();
            Thread.currentThread().getId();
            Looper.getMainLooper().getThread().getId();
            aVar.e(str, str2);
        }
    }
}

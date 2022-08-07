package com.alimama.mobile.csdk.umupdate.a;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import u.upd.b;

/* compiled from: Log.java */
/* loaded from: classes.dex */
public class g {
    public static String a = "Munion";
    public static boolean b = b.a;

    public static void a(String str) {
        b("Changing log tag to %s", str);
        a = str;
    }

    public static void a(String str, Object... objArr) {
        if (b) {
            Log.v(a, g(str, objArr));
        }
    }

    public static void b(String str, Object... objArr) {
        if (b) {
            Log.d(a, g(str, objArr));
        }
    }

    public static void c(String str, Object... objArr) {
        if (b) {
            Log.i(a, g(str, objArr));
        }
    }

    public static void d(String str, Object... objArr) {
        Log.e(a, g(str, objArr));
    }

    public static void a(Throwable th, String str, Object... objArr) {
        Log.e(a, g(str, objArr), th);
    }

    public static void e(String str, Object... objArr) {
        Log.w(a, g(str, objArr));
    }

    public static void b(Throwable th, String str, Object... objArr) {
        Log.w(a, g(str, objArr), th);
    }

    public static void f(String str, Object... objArr) {
        Log.wtf(a, g(str, objArr));
    }

    public static void c(Throwable th, String str, Object... objArr) {
        Log.wtf(a, g(str, objArr), th);
    }

    private static String g(String str, Object... objArr) {
        String str2;
        if (!(objArr == null || objArr.length == 0)) {
            str = String.format(str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str2 = "<unknown>";
                break;
            } else if (!stackTrace[i].getClass().equals(g.class)) {
                String className = stackTrace[i].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                str2 = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            } else {
                i++;
            }
        }
        return String.format("[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str2, str);
    }

    /* compiled from: Log.java */
    /* loaded from: classes.dex */
    public static class a {
        public static final boolean a = g.b;
        private static final long b = 0;
        private final List<C0006a> c = new ArrayList();
        private boolean d = false;

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: Log.java */
        /* renamed from: com.alimama.mobile.csdk.umupdate.a.g$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static class C0006a {
            public final String a;
            public final long b;
            public final long c;

            public C0006a(String str, long j, long j2) {
                this.a = str;
                this.b = j;
                this.c = j2;
            }
        }

        public synchronized void a(String str, long j) {
            if (!this.d) {
                this.c.add(new C0006a(str, j, SystemClock.elapsedRealtime()));
            }
        }

        public synchronized void a(String str) {
            this.d = true;
            long a2 = a();
            if (a2 > 0) {
                long j = this.c.get(0).c;
                g.b("(%-4d ms) %s", Long.valueOf(a2), str);
                for (C0006a aVar : this.c) {
                    long j2 = aVar.c;
                    g.b("(+%-4d) [%2d] %s", Long.valueOf(j2 - j), Long.valueOf(aVar.b), aVar.a);
                    j = j2;
                }
            }
        }

        protected void finalize() throws Throwable {
            if (!this.d) {
                a("Request on the loose");
                g.d("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        private long a() {
            if (this.c.size() == 0) {
                return 0L;
            }
            return this.c.get(this.c.size() - 1).c - this.c.get(0).c;
        }
    }
}

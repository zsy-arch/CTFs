package u.aly;

import android.util.Log;
import com.umeng.analytics.a;
import java.util.Formatter;
import java.util.Locale;

/* compiled from: MLog.java */
/* loaded from: classes2.dex */
public class bo {
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 5;
    public static boolean a = false;
    private static String b = a.d;
    private static int h = 2000;

    private bo() {
    }

    public static void a(Locale locale, String str, Object... objArr) {
        try {
            c(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void b(Locale locale, String str, Object... objArr) {
        try {
            b(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void c(Locale locale, String str, Object... objArr) {
        try {
            e(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(Locale locale, String str, Object... objArr) {
        try {
            a(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(Locale locale, String str, Object... objArr) {
        try {
            d(b, new Formatter(locale).format(str, objArr).toString(), (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void a(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                c(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            c(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void b(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                b(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            b(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void c(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                e(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            e(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                a(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            a(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                d(b, new Formatter().format(str, objArr).toString(), (Throwable) null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            d(str, str2, (Throwable) null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void a(Throwable th) {
        c(b, (String) null, th);
    }

    public static void b(Throwable th) {
        a(b, (String) null, th);
    }

    public static void c(Throwable th) {
        d(b, (String) null, th);
    }

    public static void d(Throwable th) {
        b(b, (String) null, th);
    }

    public static void e(Throwable th) {
        e(b, (String) null, th);
    }

    public static void a(String str, Throwable th) {
        c(b, str, th);
    }

    public static void b(String str, Throwable th) {
        a(b, str, th);
    }

    public static void c(String str, Throwable th) {
        d(b, str, th);
    }

    public static void d(String str, Throwable th) {
        b(b, str, th);
    }

    public static void e(String str, Throwable th) {
        e(b, str, th);
    }

    public static void a(String str) {
        a(b, str, (Throwable) null);
    }

    public static void b(String str) {
        b(b, str, (Throwable) null);
    }

    public static void c(String str) {
        c(b, str, (Throwable) null);
    }

    public static void d(String str) {
        d(b, str, (Throwable) null);
    }

    public static void e(String str) {
        e(b, str, (Throwable) null);
    }

    public static void a(String str, String str2, Throwable th) {
        if (a) {
            a(1, str, str2, th);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (a) {
            a(2, str, str2, th);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (a) {
            a(3, str, str2, th);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (a) {
            a(4, str, str2, th);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (a) {
            a(5, str, str2, th);
        }
    }

    private static void a(int i, String str, String str2, Throwable th) {
        int length = str2.length();
        int i2 = 0;
        int i3 = h;
        int i4 = 0;
        while (i2 < 100) {
            if (length > i3) {
                switch (i) {
                    case 1:
                        Log.v(str, str2.substring(i4, i3));
                        break;
                    case 2:
                        Log.d(str, str2.substring(i4, i3));
                        break;
                    case 3:
                        Log.i(str, str2.substring(i4, i3));
                        break;
                    case 4:
                        Log.w(str, str2.substring(i4, i3));
                        break;
                    case 5:
                        Log.e(str, str2.substring(i4, i3));
                        break;
                }
                i3 += h;
                if (th != null) {
                    StackTraceElement[] stackTrace = th.getStackTrace();
                    for (StackTraceElement stackTraceElement : stackTrace) {
                        switch (i) {
                            case 1:
                                Log.v(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                            case 2:
                                Log.d(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                            case 3:
                                Log.i(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                            case 4:
                                Log.w(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                            case 5:
                                Log.e(str, "\t\tat\t" + stackTraceElement.toString());
                                break;
                        }
                    }
                }
                i2++;
                i4 = i3;
            } else {
                switch (i) {
                    case 1:
                        Log.v(str, str2.substring(i4, length));
                        return;
                    case 2:
                        Log.d(str, str2.substring(i4, length));
                        return;
                    case 3:
                        Log.i(str, str2.substring(i4, length));
                        return;
                    case 4:
                        Log.w(str, str2.substring(i4, length));
                        return;
                    case 5:
                        Log.e(str, str2.substring(i4, length));
                        return;
                    default:
                        return;
                }
            }
        }
    }
}

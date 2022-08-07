package com.alipay.apmobilesecuritysdk.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.apmobilesecuritysdk.d.e;
import com.alipay.apmobilesecuritysdk.f.g;
import com.alipay.apmobilesecuritysdk.f.h;
import com.alipay.apmobilesecuritysdk.f.i;
import com.alipay.b.a.a.b.b;
import com.alipay.b.a.a.c.a.c;
import com.alipay.b.a.a.c.a.d;
import com.hyphenate.util.HanziToPinyin;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/* loaded from: classes.dex */
public final class a {
    private Context a;
    private com.alipay.apmobilesecuritysdk.b.a b = com.alipay.apmobilesecuritysdk.b.a.a();
    private int c = 4;

    public a(Context context) {
        this.a = context;
    }

    public static String a(Context context) {
        String b = b(context);
        return com.alipay.b.a.a.a.a.a(b) ? h.f(context) : b;
    }

    public static String a(Context context, String str) {
        String a;
        try {
            a = i.a(str);
        } catch (Throwable th) {
        }
        if (!com.alipay.b.a.a.a.a.a(a)) {
            return a;
        }
        String a2 = g.a(context, str);
        i.a(str, a2);
        return com.alipay.b.a.a.a.a.a(a2) ? "" : a2;
    }

    private static boolean a() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String[] strArr = {"2016-11-10 2016-11-11", "2016-12-11 2016-12-12"};
        int random = ((int) (Math.random() * 24.0d * 60.0d * 60.0d)) * 1;
        for (int i = 0; i < 2; i++) {
            try {
                String[] split = strArr[i].split(HanziToPinyin.Token.SEPARATOR);
                if (split != null && split.length == 2) {
                    Date date = new Date();
                    Date parse = simpleDateFormat.parse(split[0] + " 00:00:00");
                    Date parse2 = simpleDateFormat.parse(split[1] + " 23:59:59");
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(parse2);
                    instance.add(13, random);
                    Date time = instance.getTime();
                    if (date.after(parse) && date.before(time)) {
                        return true;
                    }
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    private boolean a(Map<String, String> map, String str) {
        long j;
        boolean z;
        long j2 = 0;
        if (a()) {
            return com.alipay.b.a.a.a.a.a(a(this.a, str)) || com.alipay.b.a.a.a.a.a(b(this.a));
        }
        e.a();
        if (!com.alipay.b.a.a.a.a.a(e.b(this.a, map), i.c())) {
            return true;
        }
        try {
            j = Long.parseLong(h.b(this.a));
        } catch (Throwable th) {
            j = 0;
        }
        try {
            b.a();
            j2 = Long.parseLong(b.o());
            z = false;
        } catch (Throwable th2) {
            z = true;
            return Math.abs(j2 - j) > 3000 ? true : true;
        }
        if (Math.abs(j2 - j) > 3000 && !z) {
            String a = com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.c, "");
            String a2 = com.alipay.b.a.a.a.a.a(map, "utdid", "");
            if (!com.alipay.b.a.a.a.a.b(a) || com.alipay.b.a.a.a.a.a(a, i.d())) {
                return (com.alipay.b.a.a.a.a.b(a2) && !com.alipay.b.a.a.a.a.a(a2, i.e())) || !i.a(this.a, str) || com.alipay.b.a.a.a.a.a(a(this.a, str)) || com.alipay.b.a.a.a.a.a(b(this.a));
            }
            return true;
        }
    }

    private c b(Map<String, String> map) {
        com.alipay.apmobilesecuritysdk.f.b b;
        com.alipay.apmobilesecuritysdk.f.b c;
        try {
            Context context = this.a;
            d dVar = new d();
            String a = a(context, com.alipay.b.a.a.a.a.a(map, "appName", ""));
            String a2 = com.alipay.apmobilesecuritysdk.e.a.a();
            String e = h.e(context);
            dVar.c(a);
            dVar.d(a2);
            dVar.e(e);
            dVar.a(f.a);
            String str = "";
            String str2 = "";
            String str3 = "";
            String str4 = "";
            com.alipay.apmobilesecuritysdk.f.c c2 = com.alipay.apmobilesecuritysdk.f.d.c(context);
            if (c2 != null) {
                str2 = c2.a();
                str3 = c2.c();
            }
            if (com.alipay.b.a.a.a.a.a(str2) && (c = com.alipay.apmobilesecuritysdk.f.a.c(context)) != null) {
                str2 = c.a();
                str3 = c.c();
            }
            com.alipay.apmobilesecuritysdk.f.c b2 = com.alipay.apmobilesecuritysdk.f.d.b();
            if (b2 != null) {
                str = b2.a();
                str4 = b2.c();
            }
            if (com.alipay.b.a.a.a.a.a(str) && (b = com.alipay.apmobilesecuritysdk.f.a.b()) != null) {
                str = b.a();
                str4 = b.c();
            }
            dVar.g(str2);
            dVar.f(str);
            if (com.alipay.b.a.a.a.a.a(str2)) {
                dVar.b(str);
                dVar.h(str4);
            } else {
                dVar.b(str2);
                dVar.h(str3);
            }
            dVar.a(e.a(context, map));
            return com.alipay.b.a.a.c.d.a(this.a, this.b.c()).a(dVar);
        } catch (Throwable th) {
            com.alipay.apmobilesecuritysdk.c.a.a(th);
            return null;
        }
    }

    private static String b(Context context) {
        String b;
        try {
            b = i.b();
        } catch (Throwable th) {
        }
        if (!com.alipay.b.a.a.a.a.a(b)) {
            return b;
        }
        com.alipay.apmobilesecuritysdk.f.c b2 = com.alipay.apmobilesecuritysdk.f.d.b(context);
        if (b2 != null) {
            i.a(b2);
            String a = b2.a();
            if (com.alipay.b.a.a.a.a.b(a)) {
                return a;
            }
        }
        com.alipay.apmobilesecuritysdk.f.b b3 = com.alipay.apmobilesecuritysdk.f.a.b(context);
        if (b3 != null) {
            i.a(b3);
            String a2 = b3.a();
            if (com.alipay.b.a.a.a.a.b(a2)) {
                return a2;
            }
        }
        return "";
    }

    public final int a(Map<String, String> map) {
        int i;
        char c = 2;
        try {
            com.alipay.apmobilesecuritysdk.c.a.a(this.a, com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.c, ""), com.alipay.b.a.a.a.a.a(map, "utdid", ""), a(this.a));
            String a = com.alipay.b.a.a.a.a.a(map, "appName", "");
            b(this.a);
            a(this.a, a);
            i.a();
            boolean a2 = a(map, a);
            Context context = this.a;
            b.a();
            h.b(context, String.valueOf(b.o()));
            if (!a2) {
                i = 0;
            } else {
                Context context2 = this.a;
                new com.alipay.apmobilesecuritysdk.c.b();
                Context context3 = this.a;
                com.alipay.apmobilesecuritysdk.b.a.a().b();
                com.alipay.apmobilesecuritysdk.e.a.b();
                c b = b(map);
                if (b != null) {
                    if (b.a) {
                        if (!com.alipay.b.a.a.a.a.a(b.c)) {
                            c = 1;
                        }
                    } else if ("APPKEY_ERROR".equals(b.b)) {
                        c = 3;
                    }
                }
                switch (c) {
                    case 1:
                        h.a(this.a, "1".equals(b.e));
                        h.d(this.a, b.f == null ? "0" : b.f);
                        h.e(this.a, b.g);
                        h.a(this.a, b.h);
                        h.f(this.a, b.i);
                        i.c(e.b(this.a, map));
                        i.a(a, b.d);
                        i.b(b.c);
                        i.d(b.j);
                        String a3 = com.alipay.b.a.a.a.a.a(map, com.alipay.sdk.cons.b.c, "");
                        if (!com.alipay.b.a.a.a.a.b(a3) || com.alipay.b.a.a.a.a.a(a3, i.d())) {
                            a3 = i.d();
                        } else {
                            i.e(a3);
                        }
                        i.e(a3);
                        String a4 = com.alipay.b.a.a.a.a.a(map, "utdid", "");
                        if (!com.alipay.b.a.a.a.a.b(a4) || com.alipay.b.a.a.a.a.a(a4, i.e())) {
                            a4 = i.e();
                        } else {
                            i.f(a4);
                        }
                        i.f(a4);
                        i.a();
                        com.alipay.apmobilesecuritysdk.f.d.a(this.a, i.g());
                        Context context4 = this.a;
                        com.alipay.apmobilesecuritysdk.f.d.a();
                        com.alipay.apmobilesecuritysdk.f.a.a(this.a, new com.alipay.apmobilesecuritysdk.f.b(i.b(), i.c(), i.f()));
                        Context context5 = this.a;
                        com.alipay.apmobilesecuritysdk.f.a.a();
                        g.a(this.a, a, i.a(a));
                        Context context6 = this.a;
                        g.a();
                        h.a(this.a, a, System.currentTimeMillis());
                        i = 0;
                        break;
                    case 2:
                    default:
                        if (b != null) {
                            com.alipay.apmobilesecuritysdk.c.a.a("Server error, result:" + b.b);
                        } else {
                            com.alipay.apmobilesecuritysdk.c.a.a("Server error, returned null");
                        }
                        if (com.alipay.b.a.a.a.a.a(a(this.a, a))) {
                            i = 4;
                            break;
                        }
                        i = 0;
                        break;
                    case 3:
                        i = 1;
                        break;
                }
                Context context7 = this.a;
                a(this.a, a);
                h.f(this.a);
            }
            this.c = i;
            com.alipay.b.a.a.c.b.a a5 = com.alipay.b.a.a.c.d.a(this.a, this.b.c());
            Context context8 = this.a;
            NetworkInfo networkInfo = null;
            ConnectivityManager connectivityManager = (ConnectivityManager) context8.getSystemService("connectivity");
            if (connectivityManager != null) {
                networkInfo = connectivityManager.getActiveNetworkInfo();
            }
            if ((networkInfo != null && networkInfo.isConnected() && networkInfo.getType() == 1) && h.d(context8)) {
                new com.alipay.b.a.a.e.b(context8.getFilesDir().getAbsolutePath() + "/log/ap", a5).a();
            }
        } catch (Exception e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
        }
        return this.c;
    }
}

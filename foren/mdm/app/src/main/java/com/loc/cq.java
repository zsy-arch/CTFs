package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.android.phone.mrpc.core.gwprotocol.JsonSerializer;
import com.em.db.UserDao;
import com.loc.l;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AuthUtil.java */
/* loaded from: classes2.dex */
public final class cq {
    private static String J;
    private static String K;
    private static String e = "提示信息";
    private static String f = "确认";
    private static String g = "取消";
    private static String h = "";
    private static String i = "";
    private static String j = "";
    private static boolean k = false;
    private static long l = 0;
    private static long m = 0;
    private static long n = 5000;
    private static boolean o = false;
    private static int p = 0;
    private static boolean q = false;
    private static int r = 0;
    private static boolean s = false;
    static boolean a = true;
    private static int t = 3600000;

    /* renamed from: u */
    private static int f36u = 0;
    private static int v = 0;
    private static boolean w = true;
    private static int x = 1000;
    private static int y = 200;
    private static boolean z = false;
    private static boolean A = true;
    private static boolean B = true;
    private static int C = -1;
    private static long D = 0;
    private static ArrayList<String> E = new ArrayList<>();
    private static boolean F = true;
    private static int G = -1;
    private static long H = 0;
    private static ArrayList<String> I = new ArrayList<>();
    private static boolean L = false;
    private static boolean M = false;
    private static int N = 3000;
    private static int O = 3000;
    private static boolean P = true;
    private static long Q = 300000;
    private static int R = -1;
    private static boolean S = false;
    private static boolean T = false;
    private static boolean U = false;
    private static boolean V = false;
    static boolean b = false;
    private static List<cr> W = new ArrayList();
    private static boolean X = false;
    private static long Y = 0;
    private static int Z = 0;
    private static int aa = 0;
    private static List<String> ab = new ArrayList();
    private static boolean ac = true;
    private static int ad = 80;
    static int c = 1800000;
    static int d = 3600000;
    private static boolean ae = false;

    /* compiled from: AuthUtil.java */
    /* loaded from: classes2.dex */
    public static class a {
        boolean a = false;
        String b = "0";
        boolean c = false;
        int d = 5;

        a() {
        }
    }

    public static int A() {
        return c;
    }

    public static int B() {
        return d;
    }

    public static boolean C() {
        return ae;
    }

    private static a a(JSONObject jSONObject, String str) {
        Throwable th;
        a aVar;
        if (jSONObject != null) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (jSONObject2 != null) {
                    aVar = new a();
                    try {
                        aVar.a = l.a(jSONObject2.optString("b"), false);
                        aVar.b = jSONObject2.optString("t");
                        aVar.c = l.a(jSONObject2.optString("st"), false);
                        aVar.d = jSONObject2.optInt("i", 0);
                        return aVar;
                    } catch (Throwable th2) {
                        th = th2;
                        f.a(th, "AuthUtil", "getLocateObj");
                        return aVar;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                aVar = null;
            }
        }
        return null;
    }

    public static boolean a() {
        return o;
    }

    public static boolean a(long j2) {
        long b2 = cx.b();
        return k && b2 - m <= l && b2 - j2 >= n;
    }

    public static boolean a(Context context) {
        A = true;
        l.a a2 = l.a(context, f.a("loc"), f.b(context));
        if (a2 == null) {
            return false;
        }
        R = a2.b;
        return a(context, a2);
    }

    public static boolean a(Context context, long j2) {
        if (!M) {
            return false;
        }
        long a2 = cx.a();
        if (a2 - j2 < N) {
            return false;
        }
        if (O == -1) {
            return true;
        }
        if (!cx.c(a2, cw.a(context, UserDao.PREF_TABLE_NAME, "ngpsTime"))) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences(UserDao.PREF_TABLE_NAME, 0).edit();
                edit.putLong("ngpsTime", a2);
                edit.putInt("ngpsCount", 0);
                cw.a(edit);
            } catch (Throwable th) {
                f.a(th, "AuthUtil", "resetPrefsNGPS");
            }
            cw.a(context, UserDao.PREF_TABLE_NAME, "ngpsCount", 1);
            return true;
        }
        int b2 = cw.b(context, UserDao.PREF_TABLE_NAME, "ngpsCount", 0);
        if (b2 >= O) {
            return false;
        }
        cw.a(context, UserDao.PREF_TABLE_NAME, "ngpsCount", b2 + 1);
        return true;
    }

    private static boolean a(Context context, l.a.b bVar, String str, String str2) {
        boolean z2 = false;
        if (bVar != null) {
            try {
                z2 = bVar.a;
                String str3 = bVar.b;
                String str4 = bVar.c;
                String str5 = bVar.d;
                if (z2 && !TextUtils.isEmpty(str4) && !TextUtils.isEmpty(str3) && !TextUtils.isEmpty(str5)) {
                    au.a(context, new at(str3, str4), f.a(str, str2));
                }
            } catch (Throwable th) {
                f.a(th, "AuthUtil", "downLoadPluginDex");
            }
        }
        return z2;
    }

    private static boolean a(Context context, l.a aVar) {
        try {
            JSONObject jSONObject = aVar.d;
            if (jSONObject != null) {
                boolean a2 = l.a(jSONObject.optString("callamapflag"), true);
                B = a2;
                if (a2) {
                    C = jSONObject.optInt("count", C);
                    D = jSONObject.optLong("sysTime", D);
                    JSONArray optJSONArray = jSONObject.optJSONArray("sn");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            E.add(optJSONArray.getString(i2));
                        }
                    }
                    if (!(C == -1 || D == 0)) {
                        if (!cx.b(D, cw.a(context, UserDao.PREF_TABLE_NAME, "nowtime"))) {
                            h(context);
                        }
                    }
                }
            }
            JSONObject jSONObject2 = aVar.g;
            if (jSONObject2 != null) {
                boolean a3 = l.a(jSONObject2.optString("amappushflag"), false);
                F = a3;
                if (a3) {
                    G = jSONObject2.optInt("count", G);
                    H = jSONObject2.optLong("sysTime", H);
                    JSONArray optJSONArray2 = jSONObject2.optJSONArray("sn");
                    if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                        for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                            I.add(optJSONArray2.getString(i3));
                        }
                    }
                    if (!(G == -1 || H == 0)) {
                        if (!cx.b(H, cw.a(context, UserDao.PREF_TABLE_NAME, "pushSerTime"))) {
                            i(context);
                        }
                    }
                }
            }
            JSONObject jSONObject3 = aVar.h;
            if (jSONObject3 != null) {
                L = l.a(jSONObject3.optString("f"), false);
                f36u = jSONObject3.optInt("mco", 0);
                v = jSONObject3.optInt("co", 0);
                int optInt = jSONObject3.optInt("it", 3600) * 1000;
                t = optInt;
                if (optInt < 3600000) {
                    t = 3600000;
                }
                e = jSONObject3.optString("a", "提示信息");
                f = jSONObject3.optString("o", "确认");
                g = jSONObject3.optString("c", "取消");
                h = jSONObject3.optString("i", "");
                i = jSONObject3.optString("u", "");
                j = jSONObject3.optString("t", "");
                if (((TextUtils.isEmpty(h) || f.b.equals(h)) && (TextUtils.isEmpty(i) || f.b.equals(i))) || v > f36u) {
                    L = false;
                }
            }
            s a4 = f.a("loc");
            l.a.d dVar = aVar.q;
            if (dVar != null) {
                String str = dVar.b;
                String str2 = dVar.a;
                String str3 = dVar.c;
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
                    au.a(context, (at) null, a4);
                } else {
                    au.a(context, new at(str2, str), a4);
                }
            } else {
                au.a(context, (at) null, a4);
            }
            l.a.c cVar = aVar.r;
            if (cVar != null) {
                J = cVar.a;
                K = cVar.b;
                if (!TextUtils.isEmpty(J) && !TextUtils.isEmpty(K)) {
                    new r(context, "loc", J, K).a();
                }
            }
            l.a.C0048a aVar2 = aVar.p;
            if (aVar2 != null) {
                w = aVar2.a;
                cw.a(context, UserDao.PREF_TABLE_NAME, "exception", w);
                JSONObject jSONObject4 = aVar2.c;
                x = jSONObject4.optInt("fn", x);
                int optInt2 = jSONObject4.optInt("mpn", y);
                y = optInt2;
                if (optInt2 > 500) {
                    y = 500;
                }
                if (y < 30) {
                    y = 30;
                }
                z = l.a(jSONObject4.optString("igu"), false);
                bq.a(x, z);
                cw.a(context, UserDao.PREF_TABLE_NAME, "fn", x);
                cw.a(context, UserDao.PREF_TABLE_NAME, "mpn", y);
                cw.a(context, UserDao.PREF_TABLE_NAME, "igu", z);
            }
            JSONObject jSONObject5 = aVar.i;
            if (jSONObject5 != null && l.a(jSONObject5.optString("able"), false)) {
                a a5 = a(jSONObject5, "fs");
                if (a5 != null) {
                    o = a5.c;
                    p = Integer.parseInt(a5.b);
                }
                a a6 = a(jSONObject5, "us");
                if (a6 != null) {
                    q = a6.c;
                    s = a6.a;
                    r = Integer.parseInt(a6.b);
                    if (r < 2) {
                        q = false;
                    }
                }
                a a7 = a(jSONObject5, "rs");
                if (a7 != null) {
                    boolean z2 = a7.c;
                    k = z2;
                    if (z2) {
                        m = cx.b();
                        n = a7.d * 1000;
                    }
                    l = Integer.parseInt(a7.b) * 1000;
                }
            }
            JSONObject jSONObject6 = aVar.k;
            if (jSONObject6 != null) {
                boolean a8 = l.a(jSONObject6.optString("able"), false);
                M = a8;
                if (a8) {
                    int optInt3 = jSONObject6.optInt("c", 0);
                    if (optInt3 == 0) {
                        N = 3000;
                    } else {
                        N = optInt3 * 1000;
                    }
                    O = jSONObject6.getInt("t") / 2;
                }
            }
            JSONObject jSONObject7 = aVar.l;
            if (jSONObject7 != null) {
                boolean a9 = l.a(jSONObject7.optString("able"), true);
                P = a9;
                if (a9) {
                    Q = jSONObject7.optInt("c", 300) * 1000;
                }
            }
            l.a.b bVar = aVar.s;
            if (bVar != null) {
                S = a(context, bVar, "Collection", JsonSerializer.VERSION);
            }
            l.a.b bVar2 = aVar.f39u;
            if (bVar2 != null) {
                boolean a10 = a(context, bVar2, "HttpDNS", JsonSerializer.VERSION);
                U = a10;
                if (!a10 && cu.a(context, f.a("HttpDNS", JsonSerializer.VERSION))) {
                    cv.a(context, "HttpDNS", "config|get dnsDex able is false");
                }
            }
            JSONObject jSONObject8 = aVar.f;
            if (jSONObject8 != null) {
                X = l.a(jSONObject8.optString("able"), false);
                Y = jSONObject8.optLong("sysTime", cx.a());
                Z = jSONObject8.optInt("n", 1);
                aa = jSONObject8.optInt("nh", 1);
                if (X && (Z == -1 || Z >= aa)) {
                    JSONArray optJSONArray3 = jSONObject8.optJSONArray("l");
                    for (int i4 = 0; i4 < optJSONArray3.length(); i4++) {
                        try {
                            JSONObject optJSONObject = optJSONArray3.optJSONObject(i4);
                            cr crVar = new cr();
                            boolean a11 = l.a(optJSONObject.optString("able", "false"), false);
                            crVar.a = a11;
                            if (a11) {
                                crVar.b = optJSONObject.optString("pn", "");
                                crVar.c = optJSONObject.optString("cn", "");
                                crVar.e = optJSONObject.optString("a", "");
                                JSONArray optJSONArray4 = optJSONObject.optJSONArray("b");
                                if (optJSONArray4 != null) {
                                    ArrayList arrayList = new ArrayList();
                                    for (int i5 = 0; i5 < optJSONArray4.length(); i5++) {
                                        JSONObject optJSONObject2 = optJSONArray4.optJSONObject(i5);
                                        HashMap hashMap = new HashMap();
                                        try {
                                            hashMap.put(optJSONObject2.optString("k"), optJSONObject2.optString("v"));
                                            arrayList.add(hashMap);
                                        } catch (Throwable th) {
                                        }
                                    }
                                    crVar.d = arrayList;
                                }
                                crVar.f = l.a(optJSONObject.optString("is", "false"), false);
                                W.add(crVar);
                            }
                        } catch (Throwable th2) {
                        }
                    }
                    JSONArray optJSONArray5 = jSONObject8.optJSONArray("sl");
                    if (optJSONArray5 != null) {
                        for (int i6 = 0; i6 < optJSONArray5.length(); i6++) {
                            String optString = optJSONArray5.optJSONObject(i6).optString("pan");
                            if (!TextUtils.isEmpty(optString)) {
                                ab.add(optString);
                            }
                        }
                    }
                }
            }
            JSONObject jSONObject9 = aVar.e;
            if (jSONObject9 != null) {
                boolean a12 = l.a(jSONObject9.optString("able"), true);
                ac = a12;
                if (a12) {
                    ad = jSONObject9.optInt("c", ad);
                }
            }
            JSONObject jSONObject10 = aVar.c;
            if (jSONObject10 != null) {
                c = jSONObject10.optInt("ht", 30) * 60 * 1000;
                d = jSONObject10.optInt("at", 60) * 60 * 1000;
                boolean a13 = l.a(jSONObject10.optString("ofl"), true);
                a = a13;
                bw.a = a13;
                if (a) {
                    try {
                        l.a.b bVar3 = aVar.t;
                        if (bVar3 != null) {
                            T = a(context, bVar3, "OfflineLocation", JsonSerializer.VERSION);
                            cw.a(context, UserDao.PREF_TABLE_NAME, "oAble", T);
                        }
                    } catch (Throwable th3) {
                        f.a(th3, "AuthUtil", "loadConfigData_OfflineDex");
                    }
                }
                ae = l.a(jSONObject10.optString("ila"), ae);
                boolean a14 = l.a(jSONObject10.optString("icbd"), true);
                b = a14;
                if (a14) {
                    au.a(context, "loc");
                }
            }
            return true;
        }
    }

    public static int b() {
        return p;
    }

    public static boolean b(long j2) {
        if (!P) {
            return false;
        }
        return Q < 0 || cx.a() - j2 < Q;
    }

    public static boolean b(Context context) {
        if (!B) {
            return false;
        }
        if (C == -1 || D == 0) {
            return true;
        }
        if (!cx.b(D, cw.a(context, UserDao.PREF_TABLE_NAME, "nowtime"))) {
            h(context);
            cw.a(context, UserDao.PREF_TABLE_NAME, "count", 1);
            return true;
        }
        int b2 = cw.b(context, UserDao.PREF_TABLE_NAME, "count", 0);
        if (b2 >= C) {
            return false;
        }
        cw.a(context, UserDao.PREF_TABLE_NAME, "count", b2 + 1);
        return true;
    }

    public static boolean c() {
        return q;
    }

    public static boolean c(Context context) {
        if (!F) {
            return false;
        }
        if (G == -1 || H == 0) {
            return true;
        }
        if (!cx.b(H, cw.a(context, UserDao.PREF_TABLE_NAME, "pushSerTime"))) {
            i(context);
            cw.a(context, UserDao.PREF_TABLE_NAME, "pushCount", 1);
            return true;
        }
        int b2 = cw.b(context, UserDao.PREF_TABLE_NAME, "pushCount", 0);
        if (b2 >= G) {
            return false;
        }
        cw.a(context, UserDao.PREF_TABLE_NAME, "pushCount", b2 + 1);
        return true;
    }

    public static int d() {
        return r;
    }

    public static boolean d(Context context) {
        int b2;
        if (!L || v <= 0 || f36u <= 0 || v > f36u) {
            return false;
        }
        long a2 = cw.a(context, "abcd", "lct");
        long a3 = cw.a(context, "abcd", "lst");
        long b3 = cx.b();
        if (b3 < a2) {
            cw.a(context, "abcd", "lct", b3);
            return false;
        }
        if (b3 - a2 > com.umeng.analytics.a.j) {
            cw.a(context, "abcd", "lct", b3);
            cw.a(context, "abcd", "t", 0);
        }
        if (b3 - a3 < t || (b2 = cw.b(context, "abcd", "t", 0) + 1) > f36u) {
            return false;
        }
        cw.a(context, "abcd", "lst", b3);
        cw.a(context, "abcd", "t", b2);
        return true;
    }

    public static void e(Context context) {
        try {
            w = cw.b(context, UserDao.PREF_TABLE_NAME, "exception", true);
            f(context);
        } catch (Throwable th) {
            f.a(th, "AuthUtil", "loadLastAbleState p1");
        }
        try {
            T = cw.b(context, UserDao.PREF_TABLE_NAME, "oAble", false);
        } catch (Throwable th2) {
            f.a(th2, "AuthUtil", "loadLastAbleState p2");
        }
        try {
            x = cw.b(context, UserDao.PREF_TABLE_NAME, "fn", x);
            y = cw.b(context, UserDao.PREF_TABLE_NAME, "mpn", y);
            z = cw.b(context, UserDao.PREF_TABLE_NAME, "igu", z);
            bq.a(x, z);
        } catch (Throwable th3) {
        }
    }

    public static boolean e() {
        return s;
    }

    public static void f(Context context) {
        try {
            s a2 = f.a("loc");
            a2.a(w);
            z.a(context, a2);
        } catch (Throwable th) {
        }
    }

    public static boolean f() {
        bw.a = a;
        return a;
    }

    public static String g() {
        return e;
    }

    public static boolean g(Context context) {
        if (!X || Z == 0 || aa == 0 || Y == 0 || (Z != -1 && Z < aa)) {
            return false;
        }
        if (ab != null && ab.size() > 0) {
            for (String str : ab) {
                if (cx.b(context, str)) {
                    return false;
                }
            }
        }
        if (Z == -1 && aa == -1) {
            return true;
        }
        long a2 = cw.a(context, UserDao.PREF_TABLE_NAME, "ots");
        long a3 = cw.a(context, UserDao.PREF_TABLE_NAME, "otsh");
        int b2 = cw.b(context, UserDao.PREF_TABLE_NAME, "otn", 0);
        int b3 = cw.b(context, UserDao.PREF_TABLE_NAME, "otnh", 0);
        if (Z != -1) {
            if (!cx.b(Y, a2)) {
                try {
                    SharedPreferences.Editor edit = context.getSharedPreferences(UserDao.PREF_TABLE_NAME, 0).edit();
                    edit.putLong("ots", Y);
                    edit.putLong("otsh", Y);
                    edit.putInt("otn", 0);
                    edit.putInt("otnh", 0);
                    cw.a(edit);
                } catch (Throwable th) {
                    f.a(th, "AuthUtil", "resetPrefsBind");
                }
                cw.a(context, UserDao.PREF_TABLE_NAME, "otn", 1);
                cw.a(context, UserDao.PREF_TABLE_NAME, "otnh", 1);
                return true;
            } else if (b2 < Z) {
                if (aa == -1) {
                    cw.a(context, UserDao.PREF_TABLE_NAME, "otn", b2 + 1);
                    cw.a(context, UserDao.PREF_TABLE_NAME, "otnh", 0);
                    return true;
                } else if (!cx.a(Y, a3)) {
                    cw.a(context, UserDao.PREF_TABLE_NAME, "otsh", Y);
                    cw.a(context, UserDao.PREF_TABLE_NAME, "otn", b2 + 1);
                    cw.a(context, UserDao.PREF_TABLE_NAME, "otnh", 1);
                    return true;
                } else if (b3 < aa) {
                    cw.a(context, UserDao.PREF_TABLE_NAME, "otn", b2 + 1);
                    cw.a(context, UserDao.PREF_TABLE_NAME, "otnh", b3 + 1);
                    return true;
                }
            }
        }
        if (Z == -1) {
            cw.a(context, UserDao.PREF_TABLE_NAME, "otn", 0);
            if (aa == -1) {
                cw.a(context, UserDao.PREF_TABLE_NAME, "otnh", 0);
                return true;
            } else if (!cx.a(Y, a3)) {
                cw.a(context, UserDao.PREF_TABLE_NAME, "otsh", Y);
                cw.a(context, UserDao.PREF_TABLE_NAME, "otnh", 1);
                return true;
            } else if (b3 < aa) {
                cw.a(context, UserDao.PREF_TABLE_NAME, "otnh", b3 + 1);
                return true;
            }
        }
        return false;
    }

    public static String h() {
        return f;
    }

    private static void h(Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(UserDao.PREF_TABLE_NAME, 0).edit();
            edit.putLong("nowtime", D);
            edit.putInt("count", 0);
            cw.a(edit);
        } catch (Throwable th) {
            f.a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    public static String i() {
        return g;
    }

    private static void i(Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(UserDao.PREF_TABLE_NAME, 0).edit();
            edit.putLong("pushSerTime", H);
            edit.putInt("pushCount", 0);
            cw.a(edit);
        } catch (Throwable th) {
            f.a(th, "AuthUtil", "resetPrefsBind");
        }
    }

    public static String j() {
        return h;
    }

    public static String k() {
        return i;
    }

    public static String l() {
        return j;
    }

    public static ArrayList<String> m() {
        return E;
    }

    public static ArrayList<String> n() {
        return I;
    }

    public static boolean o() {
        return w;
    }

    public static int p() {
        return y;
    }

    public static boolean q() {
        return A;
    }

    public static void r() {
        A = false;
    }

    public static boolean s() {
        return M;
    }

    public static boolean t() {
        return P;
    }

    public static int u() {
        return R;
    }

    public static boolean v() {
        return S;
    }

    public static boolean w() {
        return T;
    }

    public static List<cr> x() {
        return W;
    }

    public static boolean y() {
        return ac;
    }

    public static int z() {
        return ad;
    }
}

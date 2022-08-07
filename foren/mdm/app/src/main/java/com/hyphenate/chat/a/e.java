package com.hyphenate.chat.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.hyphenate.chat.EMClient;

/* loaded from: classes2.dex */
public class e {
    private static final String a = "hyphenate.sdk.pref";
    private static SharedPreferences b;
    private static SharedPreferences.Editor c;
    private static e d;
    private static String e = "shared_key_ddversion";
    private static String f = "shared_key_ddxml";
    private static String g = "shared_key_ddtime";
    private static String h = "valid_before";
    private static String i = "scheduled_logout_time";
    private static String j = "shared_key_gcm_id";
    private long k = 0;

    /* loaded from: classes2.dex */
    public static class a {
        String a;
        long b;

        public a() {
        }

        public a(String str, long j) {
            this.a = str;
            this.b = j;
        }

        public a a(long j) {
            this.b = j;
            return this;
        }

        public a a(String str) {
            this.a = str;
            return this;
        }

        public String a() {
            if (this.b <= 0) {
                this.a = null;
            }
            return this.a;
        }

        public long b() {
            return this.b;
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    private e(Context context) {
        b = context.getSharedPreferences(a, 0);
        c = b.edit();
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (d == null) {
                d = new e(EMClient.getInstance().getContext());
            }
            eVar = d;
        }
        return eVar;
    }

    public void a(long j2) {
        c.putLong(g, j2);
        c.commit();
    }

    public void a(String str) {
        c.putString(e, str);
        c.commit();
    }

    public void a(String str, String str2) {
        if (str == null && str2 == null) {
            c.remove("debugIM");
            c.remove("debugRest");
        } else {
            c.putString("debugIM", str);
            c.putString("debugRest", str2);
        }
        c.commit();
    }

    public void a(boolean z) {
        c.putString("debugMode", String.valueOf(z));
        c.commit();
    }

    public long b() {
        return b.getLong(h, -1L);
    }

    public void b(long j2) {
        c.putLong(h, j2);
        c.commit();
    }

    public void b(String str) {
        c.putString(f, str);
        c.commit();
    }

    public String c() {
        return b.getString(e, "");
    }

    public void c(long j2) {
        this.k = j2;
        c.putLong(i, j2);
        c.commit();
    }

    public void c(String str) {
        c.putString("debugAppkey", str);
        c.commit();
    }

    public String d() {
        return b.getString(f, "");
    }

    public void d(String str) {
        c.putString(j, str);
        c.commit();
    }

    public long e() {
        return b.getLong(g, -1L);
    }

    public boolean f() {
        if (this.k != 0) {
            return true;
        }
        return b.contains(i);
    }

    public long g() {
        if (this.k != 0) {
            return this.k;
        }
        this.k = b.getLong(i, -1L);
        return this.k;
    }

    public void h() {
        if (f()) {
            this.k = 0L;
            c.remove(i);
            c.commit();
        }
    }

    public String i() {
        return b.getString("debugIM", null);
    }

    public String j() {
        return b.getString("debugRest", null);
    }

    public String k() {
        return b.getString("debugAppkey", null);
    }

    public String l() {
        return b.getString("debugMode", null);
    }

    public String m() {
        return b.getString(j, null);
    }
}

package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;

/* compiled from: SPUtil.java */
/* loaded from: classes2.dex */
public final class cw {
    public static long a(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getLong(str2, 0L);
        } catch (Throwable th) {
            f.a(th, "SPUtil", "getPrefsLong");
            return 0L;
        }
    }

    public static String a(Context context, String str, String str2, String str3) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            f.a(th, "SPUtil", "getPrefsInt");
            return str3;
        }
    }

    public static void a(Context context, String str, String str2, int i) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putInt(str2, i);
            a(edit);
        } catch (Throwable th) {
            f.a(th, "SPUtil", "setPrefsInt");
        }
    }

    public static void a(Context context, String str, String str2, long j) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putLong(str2, j);
            a(edit);
        } catch (Throwable th) {
            f.a(th, "SPUtil", "setPrefsLong");
        }
    }

    public static void a(Context context, String str, String str2, boolean z) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putBoolean(str2, z);
            a(edit);
        } catch (Throwable th) {
            f.a(th, "SPUtil", "updatePrefsBoolean");
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.loc.cw$1] */
    @SuppressLint({"NewApi"})
    public static void a(final SharedPreferences.Editor editor) {
        if (editor != null) {
            if (Build.VERSION.SDK_INT >= 9) {
                editor.apply();
                return;
            }
            try {
                new AsyncTask<Void, Void, Void>() { // from class: com.loc.cw.1
                    private Void a() {
                        try {
                            if (editor == null) {
                                return null;
                            }
                            editor.commit();
                            return null;
                        } catch (Throwable th) {
                            f.a(th, "SPUtil", "commit");
                            return null;
                        }
                    }

                    @Override // android.os.AsyncTask
                    protected final /* synthetic */ Void doInBackground(Void[] voidArr) {
                        return a();
                    }
                }.execute(null, null, null);
            } catch (Throwable th) {
                f.a(th, "SPUtil", "commit1");
            }
        }
    }

    public static int b(Context context, String str, String str2, int i) {
        try {
            return context.getSharedPreferences(str, 0).getInt(str2, i);
        } catch (Throwable th) {
            f.a(th, "SPUtil", "getPrefsInt");
            return i;
        }
    }

    public static boolean b(Context context, String str, String str2, boolean z) {
        try {
            return context.getSharedPreferences(str, 0).getBoolean(str2, z);
        } catch (Throwable th) {
            f.a(th, "SPUtil", "getPrefsBoolean");
            return z;
        }
    }
}

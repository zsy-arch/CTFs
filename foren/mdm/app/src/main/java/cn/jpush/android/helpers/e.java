package cn.jpush.android.helpers;

import android.content.Context;
import android.os.RemoteException;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.al;

/* loaded from: classes.dex */
public final class e {
    public static void a(Context context, String str, int i) {
        try {
            cn.jpush.android.e.o.b(str, i);
        } catch (RemoteException e) {
            ac.d();
        } catch (NullPointerException e2) {
            ac.a();
            al.a(context, str, i);
        }
    }

    public static void a(Context context, String str, long j) {
        try {
            cn.jpush.android.e.o.b(str, j);
        } catch (RemoteException e) {
            ac.d();
        } catch (NullPointerException e2) {
            ac.a();
            al.a(context, str, j);
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            cn.jpush.android.e.o.b(str, str2);
        } catch (RemoteException e) {
            ac.d();
        } catch (NullPointerException e2) {
            ac.a();
            al.a(context, str, str2);
        }
    }

    public static void a(Context context, String str, boolean z) {
        try {
            cn.jpush.android.e.o.b(str, z);
        } catch (RemoteException e) {
            ac.d();
        } catch (NullPointerException e2) {
            ac.a();
            al.a(context, str, z);
        }
    }

    public static int b(Context context, String str, int i) {
        try {
            return cn.jpush.android.e.o.a(str, i);
        } catch (RemoteException e) {
            ac.d();
            return i;
        } catch (NullPointerException e2) {
            ac.a();
            return al.b(context, str, i);
        }
    }

    public static long b(Context context, String str, long j) {
        try {
            return cn.jpush.android.e.o.a(str, j);
        } catch (RemoteException e) {
            ac.d();
            return j;
        } catch (NullPointerException e2) {
            ac.a();
            return al.b(context, str, j);
        }
    }

    public static String b(Context context, String str, String str2) {
        try {
            return cn.jpush.android.e.o.a(str, str2);
        } catch (RemoteException e) {
            ac.d();
            return str2;
        } catch (NullPointerException e2) {
            ac.a();
            return al.b(context, str, str2);
        }
    }

    public static boolean b(Context context, String str, boolean z) {
        try {
            return cn.jpush.android.e.o.a(str, z);
        } catch (RemoteException e) {
            ac.d();
            return z;
        } catch (NullPointerException e2) {
            ac.a();
            return al.b(context, str, z);
        }
    }
}

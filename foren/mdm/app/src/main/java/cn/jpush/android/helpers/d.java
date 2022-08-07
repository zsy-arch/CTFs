package cn.jpush.android.helpers;

import android.os.RemoteException;
import cn.jpush.android.e;
import cn.jpush.android.util.ac;

/* loaded from: classes.dex */
public final class d {
    public static int a() {
        try {
            return e.o.a();
        } catch (RemoteException e) {
            ac.d();
            e.printStackTrace();
            return 0;
        } catch (NullPointerException e2) {
            ac.a();
            return g.a();
        }
    }

    public static boolean a(int i) {
        try {
            return e.o.a(i);
        } catch (RemoteException e) {
            ac.d();
            e.printStackTrace();
            return false;
        } catch (NullPointerException e2) {
            ac.a();
            return g.a(i);
        }
    }

    public static int b() {
        try {
            return e.o.b();
        } catch (RemoteException e) {
            ac.d();
            e.printStackTrace();
            return 0;
        } catch (NullPointerException e2) {
            ac.a();
            return g.b();
        }
    }

    public static boolean b(int i) {
        try {
            return e.o.b(i);
        } catch (RemoteException e) {
            ac.d();
            e.printStackTrace();
            return false;
        } catch (NullPointerException e2) {
            ac.a();
            return g.b(i);
        }
    }
}

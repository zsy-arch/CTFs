package c.a.f;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import c.e.h.n;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class xa {

    /* renamed from: a  reason: collision with root package name */
    public static Method f662a;

    static {
        int i = Build.VERSION.SDK_INT;
        try {
            f662a = View.class.getDeclaredMethod("computeFitSystemWindows", Rect.class, Rect.class);
            if (!f662a.isAccessible()) {
                f662a.setAccessible(true);
            }
        } catch (NoSuchMethodException unused) {
        }
    }

    public static boolean a(View view) {
        return n.g(view) == 1;
    }

    public static void b(View view) {
        int i = Build.VERSION.SDK_INT;
        try {
            Method method = view.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            method.invoke(view, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
    }
}

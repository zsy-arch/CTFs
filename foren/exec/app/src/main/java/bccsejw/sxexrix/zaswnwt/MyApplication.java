package bccsejw.sxexrix.zaswnwt;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import d.a.a.a.a;
import d.a.a.c.d;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class MyApplication extends Application {

    /* renamed from: a  reason: collision with root package name */
    public static Application f301a;

    public void a(Application application) {
        f301a = application;
        d.a(this);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        if (a.f1600a == null) {
            a.f1600a = new a();
        }
        a.f1600a.a(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                Class.forName("android.content.pm.PackageParser$Package").getDeclaredConstructor(String.class).setAccessible(true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", new Class[0]);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("mHiddenApiWarningShown");
                declaredField.setAccessible(true);
                declaredField.setBoolean(invoke, true);
            } catch (Exception unused) {
            }
        }
        a(this);
    }
}

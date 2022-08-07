package c.e.a;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

/* loaded from: classes.dex */
public class b extends c.e.b.a {

    /* loaded from: classes.dex */
    public interface a {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    /* renamed from: c.e.a.b$b */
    /* loaded from: classes.dex */
    public interface AbstractC0008b {
        void a(int i);
    }

    public static void a() {
    }

    public static void a(Activity activity) {
        int i = Build.VERSION.SDK_INT;
        activity.finishAffinity();
    }

    public static void a(Activity activity, String[] strArr, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity instanceof AbstractC0008b) {
                ((AbstractC0008b) activity).a(i);
            }
            activity.requestPermissions(strArr, i);
        } else if (activity instanceof a) {
            new Handler(Looper.getMainLooper()).post(new a(strArr, activity, i));
        }
    }
}

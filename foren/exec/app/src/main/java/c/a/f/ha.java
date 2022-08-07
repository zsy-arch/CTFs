package c.a.f;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

/* loaded from: classes.dex */
public class ha extends ContextWrapper {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f597a = new Object();

    public static Context a(Context context) {
        if (!(context instanceof ha) && !(context.getResources() instanceof ja) && !(context.getResources() instanceof wa)) {
            int i = Build.VERSION.SDK_INT;
            wa.a();
        }
        return context;
    }
}

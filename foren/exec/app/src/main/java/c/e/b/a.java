package c.e.b;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import java.io.File;

/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f746a = new Object();

    public static boolean a(Context context, Intent[] intentArr, Bundle bundle) {
        int i = Build.VERSION.SDK_INT;
        context.startActivities(intentArr, bundle);
        return true;
    }

    public static File[] b(Context context, String str) {
        int i = Build.VERSION.SDK_INT;
        return context.getExternalFilesDirs(str);
    }

    public static File[] a(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getExternalCacheDirs();
    }

    public static Drawable b(Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return context.getDrawable(i);
    }

    public static ColorStateList a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        return context.getResources().getColorStateList(i);
    }

    public static int a(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }
}

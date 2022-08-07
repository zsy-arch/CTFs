package c.e.c;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import c.c.g;
import c.e.b.a.b;
import c.e.b.a.e;
import c.e.b.a.j;
import c.e.e.f;

/* loaded from: classes.dex */
public class c {

    /* renamed from: a */
    public static final i f776a;

    /* renamed from: b */
    public static final g<String, Typeface> f777b;

    static {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            f776a = new g();
        } else if (i >= 26) {
            f776a = new f();
        } else {
            if (i >= 24) {
                if (e.f780c != null) {
                    f776a = new e();
                }
            }
            int i2 = Build.VERSION.SDK_INT;
            f776a = new d();
        }
        f777b = new g<>(16);
    }

    public static String a(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface a(Context context, b bVar, Resources resources, int i, int i2, j jVar, Handler handler, boolean z) {
        Typeface typeface;
        if (bVar instanceof e) {
            e eVar = (e) bVar;
            boolean z2 = false;
            if (!z ? jVar == null : eVar.f758c == 0) {
                z2 = true;
            }
            typeface = f.a(context, eVar.f756a, jVar, handler, z2, z ? eVar.f757b : -1, i2);
        } else {
            typeface = f776a.a(context, (c.e.b.a.c) bVar, resources, i2);
            if (jVar != null) {
                if (typeface != null) {
                    jVar.a(typeface, handler);
                } else {
                    jVar.a(-3, handler);
                }
            }
        }
        if (typeface != null) {
            f777b.a(a(resources, i, i2), typeface);
        }
        return typeface;
    }

    public static Typeface a(Context context, Resources resources, int i, String str, int i2) {
        Typeface a2 = f776a.a(context, resources, i, str, i2);
        if (a2 != null) {
            f777b.a(a(resources, i, i2), a2);
        }
        return a2;
    }
}

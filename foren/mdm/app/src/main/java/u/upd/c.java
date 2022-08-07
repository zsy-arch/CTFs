package u.upd;

import android.content.Context;
import android.content.res.Resources;
import com.alimama.mobile.csdk.umupdate.a.f;
import u.aly.av;

/* compiled from: Res.java */
/* loaded from: classes2.dex */
public class c {
    private static final String a = c.class.getName();
    private static c b = null;
    private Resources c;
    private final String d;
    private final String e = f.bv;
    private final String f = "id";
    private final String g = f.bt;
    private final String h = "anim";
    private final String i = av.P;
    private final String j = "string";
    private final String k = "array";

    private c(Context context) {
        this.c = context.getResources();
        this.d = context.getPackageName();
    }

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (b == null) {
                b = new c(context.getApplicationContext());
            }
            cVar = b;
        }
        return cVar;
    }

    public int a(String str) {
        return a(str, "anim");
    }

    public int b(String str) {
        return a(str, "id");
    }

    public int c(String str) {
        return a(str, f.bv);
    }

    public int d(String str) {
        return a(str, f.bt);
    }

    public int e(String str) {
        return a(str, av.P);
    }

    public int f(String str) {
        return a(str, "string");
    }

    public int g(String str) {
        return a(str, "array");
    }

    private int a(String str, String str2) {
        int identifier = this.c.getIdentifier(str, str2, this.d);
        if (identifier != 0) {
            return identifier;
        }
        b.b(a, "getRes(" + str2 + "/ " + str + ")");
        b.b(a, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
        return 0;
    }
}

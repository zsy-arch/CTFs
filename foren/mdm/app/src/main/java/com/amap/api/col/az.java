package com.amap.api.col;

import android.content.Context;
import com.alipay.sdk.util.h;
import java.util.ArrayList;
import java.util.List;

/* compiled from: OfflineDBOperation.java */
/* loaded from: classes.dex */
public class az {
    private static volatile az a;
    private static gx b;
    private Context c;

    public static az a(Context context) {
        if (a == null) {
            synchronized (az.class) {
                if (a == null) {
                    a = new az(context);
                }
            }
        }
        return a;
    }

    private az(Context context) {
        this.c = context;
        b = b(this.c);
    }

    private gx b(Context context) {
        try {
            return new gx(context, ay.a());
        } catch (Throwable th) {
            gr.b(th, "OfflineDB", "getDB");
            th.printStackTrace();
            return null;
        }
    }

    private boolean b() {
        if (b == null) {
            b = b(this.c);
        }
        return b != null;
    }

    public ArrayList<au> a() {
        ArrayList<au> arrayList = new ArrayList<>();
        if (!b()) {
            return arrayList;
        }
        for (au auVar : b.b("", au.class)) {
            arrayList.add(auVar);
        }
        return arrayList;
    }

    public synchronized au a(String str) {
        au auVar = null;
        synchronized (this) {
            if (b()) {
                List b2 = b.b(au.e(str), au.class);
                if (b2.size() > 0) {
                    auVar = (au) b2.get(0);
                }
            }
        }
        return auVar;
    }

    public synchronized void a(au auVar) {
        if (b()) {
            b.a(auVar, au.f(auVar.h()));
            a(auVar.f(), auVar.b());
        }
    }

    private void a(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            String a2 = aw.a(str);
            if (b.b(a2, aw.class).size() > 0) {
                b.a(a2, aw.class);
            }
            String[] split = str2.split(h.b);
            ArrayList arrayList = new ArrayList();
            for (String str3 : split) {
                arrayList.add(new aw(str, str3));
            }
            b.a((List) arrayList);
        }
    }

    public synchronized List<String> b(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        if (b()) {
            arrayList.addAll(a(b.b(aw.a(str), aw.class)));
        }
        return arrayList;
    }

    private List<String> a(List<aw> list) {
        ArrayList arrayList = new ArrayList();
        if (list.size() > 0) {
            for (aw awVar : list) {
                arrayList.add(awVar.a());
            }
        }
        return arrayList;
    }

    public synchronized void c(String str) {
        if (b()) {
            b.a(ax.e(str), ax.class);
            b.a(aw.a(str), aw.class);
            b.a(av.a(str), av.class);
        }
    }

    public synchronized void b(au auVar) {
        if (b()) {
            b.a(ax.f(auVar.h()), ax.class);
            b.a(aw.a(auVar.f()), aw.class);
            b.a(av.a(auVar.f()), av.class);
        }
    }

    public void a(String str, int i, long j, long j2, long j3) {
        if (b()) {
            a(str, i, j, new long[]{j2, 0, 0, 0, 0}, new long[]{j3, 0, 0, 0, 0});
        }
    }

    public synchronized void a(String str, int i, long j, long[] jArr, long[] jArr2) {
        if (b()) {
            b.a(new av(str, j, i, jArr[0], jArr2[0]), av.a(str));
        }
    }

    public synchronized String d(String str) {
        String str2;
        str2 = null;
        if (b()) {
            List b2 = b.b(ax.f(str), ax.class);
            if (b2.size() > 0) {
                str2 = ((ax) b2.get(0)).e();
            }
        }
        return str2;
    }
}

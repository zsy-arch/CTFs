package com.amap.api.col;

import android.content.Context;
import com.amap.api.maps.AMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: TaskManager.java */
/* loaded from: classes.dex */
public class at {
    private static at a;
    private ip b;
    private LinkedHashMap<String, iq> c = new LinkedHashMap<>();
    private boolean d = true;

    public static at a(int i) {
        return a(true, i);
    }

    private static synchronized at a(boolean z, int i) {
        at atVar;
        synchronized (at.class) {
            if (a == null) {
                a = new at(z, i);
            } else if (z && a.b == null) {
                a.b = ip.a(i);
            }
            atVar = a;
        }
        return atVar;
    }

    private at(boolean z, int i) {
        if (z) {
            try {
                this.b = ip.a(i);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void a() {
        synchronized (this.c) {
            if (this.c.size() >= 1) {
                for (Map.Entry<String, iq> entry : this.c.entrySet()) {
                    entry.getKey();
                    ((ap) entry.getValue()).b();
                }
                this.c.clear();
            }
        }
    }

    public void a(as asVar) {
        synchronized (this.c) {
            ap apVar = (ap) this.c.get(asVar.b());
            if (apVar != null) {
                apVar.b();
            }
        }
    }

    public void a(as asVar, Context context, AMap aMap) throws fz {
        if (this.b == null) {
        }
        if (!this.c.containsKey(asVar.b())) {
            ap apVar = new ap((bi) asVar, context.getApplicationContext(), aMap);
            synchronized (this.c) {
                this.c.put(asVar.b(), apVar);
            }
        }
        this.b.a(this.c.get(asVar.b()));
    }

    public void b() {
        a();
        ip ipVar = this.b;
        ip.a();
        this.b = null;
        c();
    }

    public static void c() {
        a = null;
    }

    public void b(as asVar) {
        ap apVar = (ap) this.c.get(asVar.b());
        if (apVar != null) {
            synchronized (this.c) {
                apVar.c();
                this.c.remove(asVar.b());
            }
        }
    }
}

package com.amap.api.col;

import android.content.Context;
import java.util.List;

/* compiled from: LogDBOperation.java */
/* loaded from: classes.dex */
public class hf {
    private gx a;

    public hf(Context context) {
        try {
            this.a = new gx(context, gx.a((Class<? extends gw>) he.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, Class<? extends hg> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            go.a(th, "LogDB", "delLog");
        }
    }

    public void b(String str, Class<? extends hg> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(String str, Class<? extends hg> cls) {
        this.a.a(hg.c(str), (Class) cls);
    }

    public List<? extends hg> a(int i, Class<? extends hg> cls) {
        try {
            return this.a.b(hg.c(i), cls);
        } catch (Throwable th) {
            go.a(th, "LogDB", "ByState");
            return null;
        }
    }

    public void a(hg hgVar) {
        if (hgVar != null) {
            String c = hg.c(hgVar.b());
            List a = this.a.a(c, (Class) hgVar.getClass(), true);
            if (a == null || a.size() == 0) {
                this.a.a((gx) hgVar, true);
                return;
            }
            hg hgVar2 = (hg) a.get(0);
            if (hgVar.a() == 0) {
                hgVar2.b(hgVar2.c() + 1);
            } else {
                hgVar2.b(0);
            }
            this.a.a(c, (Object) hgVar2, true);
        }
    }

    public void b(hg hgVar) {
        try {
            this.a.a(hg.c(hgVar.b()), hgVar);
        } catch (Throwable th) {
            go.a(th, "LogDB", "updateLogInfo");
        }
    }
}

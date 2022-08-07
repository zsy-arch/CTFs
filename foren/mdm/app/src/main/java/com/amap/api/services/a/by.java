package com.amap.api.services.a;

import android.content.Context;
import java.util.List;

/* compiled from: LogDBOperation.java */
/* loaded from: classes.dex */
public class by {
    private bq a;

    public by(Context context) {
        try {
            this.a = new bq(context, bq.a((Class<? extends bp>) bx.class));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void a(String str, Class<? extends bz> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            bh.a(th, "LogDB", "delLog");
        }
    }

    public void b(String str, Class<? extends bz> cls) {
        try {
            c(str, cls);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(String str, Class<? extends bz> cls) {
        this.a.a(bz.c(str), (Class) cls);
    }

    public List<? extends bz> a(int i, Class<? extends bz> cls) {
        try {
            return this.a.b(bz.c(i), cls);
        } catch (Throwable th) {
            bh.a(th, "LogDB", "ByState");
            return null;
        }
    }

    public void a(bz bzVar) {
        if (bzVar != null) {
            String c = bz.c(bzVar.b());
            List a = this.a.a(c, (Class) bzVar.getClass(), true);
            if (a == null || a.size() == 0) {
                this.a.a((bq) bzVar, true);
                return;
            }
            bz bzVar2 = (bz) a.get(0);
            if (bzVar.a() == 0) {
                bzVar2.b(bzVar2.c() + 1);
            } else {
                bzVar2.b(0);
            }
            this.a.a(c, (Object) bzVar2, true);
        }
    }

    public void b(bz bzVar) {
        try {
            this.a.a(bz.c(bzVar.b()), bzVar);
        } catch (Throwable th) {
            bh.a(th, "LogDB", "updateLogInfo");
        }
    }
}

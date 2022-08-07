package com.amap.api.col;

import android.content.Context;
import java.util.List;

/* compiled from: SDKDBOperation.java */
/* loaded from: classes.dex */
public class hh {
    private gx a;
    private Context b;

    public hh(Context context, boolean z) {
        this.b = context;
        this.a = a(this.b, z);
    }

    private gx a(Context context, boolean z) {
        try {
            return new gx(context, gx.a((Class<? extends gw>) he.class));
        } catch (Throwable th) {
            if (!z) {
                go.a(th, "SDKDB", "getDB");
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public void a(gj gjVar) {
        if (gjVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b, false);
                }
                String a = gj.a(gjVar.a());
                List<gj> b = this.a.b(a, gj.class);
                if (b == null || b.size() == 0) {
                    this.a.a((gx) gjVar);
                } else if (a(b, gjVar)) {
                    this.a.a(a, gjVar);
                }
            } catch (Throwable th) {
                go.a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }

    private boolean a(List<gj> list, gj gjVar) {
        for (gj gjVar2 : list) {
            if (gjVar2.equals(gjVar)) {
                return false;
            }
        }
        return true;
    }

    public List<gj> a() {
        try {
            return this.a.a(gj.h(), gj.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}

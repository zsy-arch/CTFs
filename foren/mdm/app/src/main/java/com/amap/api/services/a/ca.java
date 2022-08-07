package com.amap.api.services.a;

import android.content.Context;
import java.util.List;

/* compiled from: SDKDBOperation.java */
/* loaded from: classes.dex */
public class ca {
    private bq a;
    private Context b;

    public ca(Context context, boolean z) {
        this.b = context;
        this.a = a(this.b, z);
    }

    private bq a(Context context, boolean z) {
        try {
            return new bq(context, bq.a((Class<? extends bp>) bx.class));
        } catch (Throwable th) {
            if (!z) {
                bh.a(th, "SDKDB", "getDB");
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public void a(be beVar) {
        if (beVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b, false);
                }
                String a = be.a(beVar.a());
                List b = this.a.b(a, be.class);
                if (b == null || b.size() == 0) {
                    this.a.a((bq) beVar);
                } else {
                    this.a.a(a, beVar);
                }
            } catch (Throwable th) {
                bh.a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }

    public List<be> a() {
        try {
            return this.a.a(be.f(), be.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}

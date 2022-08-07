package com.amap.api.services.a;

import android.content.Context;
import java.util.List;

/* compiled from: UpdateLogDBOperation.java */
/* loaded from: classes.dex */
public class cb {
    private bq a;
    private Context b;

    public cb(Context context) {
        this.b = context;
        this.a = a(this.b);
    }

    private bq a(Context context) {
        try {
            return new bq(context, bq.a((Class<? extends bp>) bx.class));
        } catch (Throwable th) {
            bh.a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    public cc a() {
        try {
            if (this.a == null) {
                this.a = a(this.b);
            }
            List b = this.a.b("1=1", cc.class);
            if (b.size() > 0) {
                return (cc) b.get(0);
            }
        } catch (Throwable th) {
            bh.a(th, "UpdateLogDB", "getUpdateLog");
        }
        return null;
    }

    public void a(cc ccVar) {
        if (ccVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b);
                }
                List b = this.a.b("1=1", cc.class);
                if (b == null || b.size() == 0) {
                    this.a.a((bq) ccVar);
                } else {
                    this.a.a("1=1", ccVar);
                }
            } catch (Throwable th) {
                bh.a(th, "UpdateLogDB", "updateLog");
            }
        }
    }
}

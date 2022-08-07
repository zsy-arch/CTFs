package com.amap.api.col;

import android.content.Context;
import java.util.List;

/* compiled from: UpdateLogDBOperation.java */
/* loaded from: classes.dex */
public class hi {
    private gx a;
    private Context b;

    public hi(Context context) {
        this.b = context;
        this.a = a(this.b);
    }

    private gx a(Context context) {
        try {
            return new gx(context, gx.a((Class<? extends gw>) he.class));
        } catch (Throwable th) {
            go.a(th, "UpdateLogDB", "getDB");
            return null;
        }
    }

    public hj a() {
        try {
            if (this.a == null) {
                this.a = a(this.b);
            }
            List b = this.a.b("1=1", hj.class);
            if (b.size() > 0) {
                return (hj) b.get(0);
            }
        } catch (Throwable th) {
            go.a(th, "UpdateLogDB", "getUpdateLog");
        }
        return null;
    }

    public void a(hj hjVar) {
        if (hjVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b);
                }
                List b = this.a.b("1=1", hj.class);
                if (b == null || b.size() == 0) {
                    this.a.a((gx) hjVar);
                } else {
                    this.a.a("1=1", hjVar);
                }
            } catch (Throwable th) {
                go.a(th, "UpdateLogDB", "updateLog");
            }
        }
    }
}

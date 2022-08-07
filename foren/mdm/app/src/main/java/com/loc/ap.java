package com.loc;

import android.content.Context;
import java.util.Iterator;
import java.util.List;

/* compiled from: SDKDBOperation.java */
/* loaded from: classes2.dex */
public final class ap {
    private af a;
    private Context b;

    public ap(Context context, boolean z) {
        this.b = context;
        this.a = a(this.b, z);
    }

    private static af a(Context context, boolean z) {
        try {
            return new af(context, af.a((Class<? extends ae>) am.class));
        } catch (Throwable th) {
            if (!z) {
                w.a(th, "SDKDB", "getDB");
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    public final List<s> a() {
        try {
            return this.a.a(s.g(), s.class, true);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void a(s sVar) {
        boolean z = false;
        if (sVar != null) {
            try {
                if (this.a == null) {
                    this.a = a(this.b, false);
                }
                String a = s.a(sVar.a());
                List b = this.a.b(a, s.class);
                if (b == null || b.size() == 0) {
                    this.a.a((af) sVar);
                    return;
                }
                Iterator it = b.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((s) it.next()).equals(sVar)) {
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    this.a.a(a, sVar);
                }
            } catch (Throwable th) {
                w.a(th, "SDKDB", "insert");
                th.printStackTrace();
            }
        }
    }
}

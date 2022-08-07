package com.amap.api.col;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: LoaderFactory.java */
/* loaded from: classes.dex */
public class hu {
    private static final hu a = new hu();
    private final Map<String, hn> b = new HashMap();

    private hu() {
    }

    public static hu a() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized hn a(Context context, gj gjVar) throws Exception {
        hn hnVar;
        if (!a(gjVar) || context == null) {
            hnVar = null;
        } else {
            String a2 = gjVar.a();
            hnVar = this.b.get(a2);
            if (hnVar == null) {
                try {
                    hs hsVar = new hs(context.getApplicationContext(), gjVar, true);
                    try {
                        this.b.put(a2, hsVar);
                        hq.a(context, gjVar);
                        hnVar = hsVar;
                    } catch (Throwable th) {
                        hnVar = hsVar;
                    }
                } catch (Throwable th2) {
                }
            }
        }
        return hnVar;
    }

    private boolean a(gj gjVar) {
        return gjVar != null && !TextUtils.isEmpty(gjVar.b()) && !TextUtils.isEmpty(gjVar.a());
    }
}

package com.amap.api.services.a;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ClassLoaderFactory.java */
/* loaded from: classes.dex */
public class ch {
    private static final ch a = new ch();
    private final Map<String, cg> b = new HashMap();

    private ch() {
    }

    public static ch a() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized cg a(Context context, be beVar) throws Exception {
        cg cgVar;
        if (!a(beVar) || context == null) {
            throw new Exception("sdkInfo or context referance is null");
        }
        String a2 = beVar.a();
        cgVar = this.b.get(a2);
        if (cgVar == null) {
            try {
                ck ckVar = new ck(context.getApplicationContext(), beVar, true);
                try {
                    this.b.put(a2, ckVar);
                    cl.a(context, beVar);
                    cgVar = ckVar;
                } catch (Throwable th) {
                    cgVar = ckVar;
                }
            } catch (Throwable th2) {
            }
        }
        return cgVar;
    }

    private boolean a(be beVar) {
        return beVar != null && !TextUtils.isEmpty(beVar.b()) && !TextUtils.isEmpty(beVar.a());
    }
}

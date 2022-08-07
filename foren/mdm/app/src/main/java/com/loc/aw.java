package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ClassLoaderFactory.java */
/* loaded from: classes2.dex */
public final class aw {
    private static final aw a = new aw();
    private final Map<String, av> b = new HashMap();

    private aw() {
    }

    public static aw a() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized av a(Context context, s sVar) throws Exception {
        boolean z;
        av avVar;
        if (sVar != null) {
            if (!TextUtils.isEmpty(sVar.b()) && !TextUtils.isEmpty(sVar.a())) {
                z = true;
                if (z || context == null) {
                    throw new Exception("sdkInfo or context referance is null");
                }
                String a2 = sVar.a();
                avVar = this.b.get(a2);
                if (avVar == null) {
                    try {
                        az azVar = new az(context.getApplicationContext(), sVar);
                        try {
                            this.b.put(a2, azVar);
                            ba.a(context, sVar);
                            avVar = azVar;
                        } catch (Throwable th) {
                            avVar = azVar;
                        }
                    } catch (Throwable th2) {
                    }
                }
            }
        }
        z = false;
        if (z) {
        }
        throw new Exception("sdkInfo or context referance is null");
        return avVar;
    }
}

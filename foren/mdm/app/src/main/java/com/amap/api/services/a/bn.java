package com.amap.api.services.a;

import android.content.Context;
import android.os.Looper;
import java.util.List;

/* compiled from: ExceptionLogProcessor.java */
/* loaded from: classes.dex */
public class bn extends bo {
    private static boolean a = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public bn(int i) {
        super(i);
    }

    @Override // com.amap.api.services.a.bo
    protected boolean a(Context context) {
        boolean z = true;
        if (ba.m(context) != 1 || !a) {
            return false;
        }
        a = false;
        synchronized (Looper.getMainLooper()) {
            cb cbVar = new cb(context);
            cc a2 = cbVar.a();
            if (a2 != null) {
                if (a2.b()) {
                    a2.b(false);
                    cbVar.a(a2);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    @Override // com.amap.api.services.a.bo
    protected String a(List<be> list) {
        return null;
    }
}

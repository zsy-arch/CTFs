package com.amap.api.services.a;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

/* compiled from: CrashLogProcessor.java */
/* loaded from: classes.dex */
public class bm extends bo {
    private static boolean a = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public bm(int i) {
        super(i);
    }

    @Override // com.amap.api.services.a.bo
    protected boolean a(Context context) {
        boolean z = true;
        if (!a) {
            return false;
        }
        a = false;
        synchronized (Looper.getMainLooper()) {
            cb cbVar = new cb(context);
            cc a2 = cbVar.a();
            if (a2 != null) {
                if (a2.a()) {
                    a2.a(false);
                    cbVar.a(a2);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    @Override // com.amap.api.services.a.bo
    protected String a(String str) {
        return bc.c(str + bf.a(new Date().getTime()));
    }

    @Override // com.amap.api.services.a.bo
    protected String a(List<be> list) {
        return null;
    }
}

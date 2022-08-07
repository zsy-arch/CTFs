package com.amap.api.col;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

/* compiled from: CrashLogProcessor.java */
/* loaded from: classes.dex */
public class gt extends gv {
    private static boolean b = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public gt(int i) {
        super(i);
    }

    @Override // com.amap.api.col.gv
    protected boolean a(Context context) {
        boolean z = true;
        if (!b) {
            return false;
        }
        b = false;
        synchronized (Looper.getMainLooper()) {
            hi hiVar = new hi(context);
            hj a = hiVar.a();
            if (a != null) {
                if (a.a()) {
                    a.a(false);
                    hiVar.a(a);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    @Override // com.amap.api.col.gv
    protected String a(String str) {
        return gg.c(str + gk.a(new Date().getTime()));
    }

    @Override // com.amap.api.col.gv
    protected String a(List<gj> list) {
        return null;
    }
}

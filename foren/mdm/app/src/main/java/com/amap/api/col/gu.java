package com.amap.api.col;

import android.content.Context;
import android.os.Looper;
import java.util.List;

/* compiled from: ExceptionLogProcessor.java */
/* loaded from: classes.dex */
public class gu extends gv {
    private static boolean b = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public gu(int i) {
        super(i);
    }

    @Override // com.amap.api.col.gv
    protected boolean a(Context context) {
        boolean z = true;
        if (ge.m(context) != 1 || !b) {
            return false;
        }
        b = false;
        synchronized (Looper.getMainLooper()) {
            hi hiVar = new hi(context);
            hj a = hiVar.a();
            if (a != null) {
                if (a.b()) {
                    a.b(false);
                    hiVar.a(a);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    @Override // com.amap.api.col.gv
    protected String a(List<gj> list) {
        return null;
    }
}

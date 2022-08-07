package com.loc;

import android.content.Context;
import android.os.Looper;
import java.util.Date;
import java.util.List;

/* compiled from: CrashLogProcessor.java */
/* loaded from: classes2.dex */
public final class ab extends ad {
    private static boolean b = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public ab(int i) {
        super(i);
    }

    @Override // com.loc.ad
    protected final String a(String str) {
        return p.c(str + t.a(new Date().getTime()));
    }

    @Override // com.loc.ad
    protected final String a(List<s> list) {
        return null;
    }

    @Override // com.loc.ad
    protected final boolean a(Context context) {
        boolean z = true;
        if (!b) {
            return false;
        }
        b = false;
        synchronized (Looper.getMainLooper()) {
            aq aqVar = new aq(context);
            ar a = aqVar.a();
            if (a != null) {
                if (a.a()) {
                    a.a(false);
                    aqVar.a(a);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }
}

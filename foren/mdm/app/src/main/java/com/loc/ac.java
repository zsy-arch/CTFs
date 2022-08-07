package com.loc;

import android.content.Context;
import android.os.Looper;
import java.util.List;

/* compiled from: ExceptionLogProcessor.java */
/* loaded from: classes2.dex */
public final class ac extends ad {
    private static boolean b = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public ac(int i) {
        super(i);
    }

    @Override // com.loc.ad
    protected final String a(List<s> list) {
        return null;
    }

    @Override // com.loc.ad
    protected final boolean a(Context context) {
        boolean z = true;
        if (n.m(context) != 1 || !b) {
            return false;
        }
        b = false;
        synchronized (Looper.getMainLooper()) {
            aq aqVar = new aq(context);
            ar a = aqVar.a();
            if (a != null) {
                if (a.b()) {
                    a.b(false);
                    aqVar.a(a);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }
}

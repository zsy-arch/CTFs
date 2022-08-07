package com.loc;

import android.content.Context;
import android.os.Looper;

/* compiled from: AnrLogProcessor.java */
/* loaded from: classes2.dex */
public final class aa extends ad {
    private static boolean b = true;
    private String[] c = new String[10];
    private int d = 0;
    private boolean e = false;
    private int f = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    public aa(int i) {
        super(i);
    }

    private String d() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = this.d; i < 10 && i <= 9; i++) {
                sb.append(this.c[i]);
            }
            for (int i2 = 0; i2 < this.d; i2++) {
                sb.append(this.c[i2]);
            }
        } catch (Throwable th) {
            w.a(th, "ANRWriter", "getLogInfo");
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0117  */
    @Override // com.loc.ad
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final java.lang.String a(java.util.List<com.loc.s> r9) {
        /*
            Method dump skipped, instructions count: 528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.aa.a(java.util.List):java.lang.String");
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
                if (a.c()) {
                    a.c(false);
                    aqVar.a(a);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }
}

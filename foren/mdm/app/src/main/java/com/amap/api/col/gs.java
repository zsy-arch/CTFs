package com.amap.api.col;

import android.content.Context;
import android.os.Looper;

/* compiled from: AnrLogProcessor.java */
/* loaded from: classes.dex */
public class gs extends gv {
    private static boolean b = true;
    private String[] c = new String[10];
    private int d = 0;
    private boolean e = false;
    private int f = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    public gs(int i) {
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
                if (a.c()) {
                    a.c(false);
                    hiVar.a(a);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f5  */
    @Override // com.amap.api.col.gv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.String a(java.util.List<com.amap.api.col.gj> r9) {
        /*
            Method dump skipped, instructions count: 494
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.gs.a(java.util.List):java.lang.String");
    }

    private String e() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = this.d; i < 10 && i <= 9; i++) {
                sb.append(this.c[i]);
            }
            for (int i2 = 0; i2 < this.d; i2++) {
                sb.append(this.c[i2]);
            }
        } catch (Throwable th) {
            go.a(th, "ANRWriter", "getLogInfo");
        }
        return sb.toString();
    }

    private void c(String str) {
        try {
            if (this.d > 9) {
                this.d = 0;
            }
            this.c[this.d] = str;
            this.d++;
        } catch (Throwable th) {
            go.a(th, "ANRWriter", "addData");
        }
    }
}

package com.amap.api.services.a;

import android.content.Context;
import android.os.Looper;

/* compiled from: AnrLogProcessor.java */
/* loaded from: classes.dex */
public class bl extends bo {
    private static boolean a = true;
    private String[] b = new String[10];
    private int c = 0;
    private boolean d = false;
    private int e = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    public bl(int i) {
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
                if (a2.c()) {
                    a2.c(false);
                    cbVar.a(a2);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00db  */
    @Override // com.amap.api.services.a.bo
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.String a(java.util.List<com.amap.api.services.a.be> r10) {
        /*
            Method dump skipped, instructions count: 415
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.services.a.bl.a(java.util.List):java.lang.String");
    }

    private String d() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = this.c; i < 10 && i <= 9; i++) {
                sb.append(this.b[i]);
            }
            for (int i2 = 0; i2 < this.c; i2++) {
                sb.append(this.b[i2]);
            }
        } catch (Throwable th) {
            bh.a(th, "ANRWriter", "getLogInfo");
        }
        return sb.toString();
    }

    private void b(String str) {
        try {
            if (this.c > 9) {
                this.c = 0;
            }
            this.b[this.c] = str;
            this.c++;
        } catch (Throwable th) {
            bh.a(th, "ANRWriter", "addData");
        }
    }
}

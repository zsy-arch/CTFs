package com.loc;

import android.support.v4.os.EnvironmentCompat;
import java.util.Locale;

/* compiled from: Cgi.java */
/* loaded from: classes2.dex */
public final class cb {
    private static cb p = null;
    public int k;
    public boolean n;
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = -113;
    public short l = 0;
    public long m = 0;
    public boolean o = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public cb(int i, boolean z) {
        this.k = 0;
        this.n = false;
        this.k = i;
        this.n = z;
    }

    public final int a() {
        return this.c;
    }

    public final boolean a(cb cbVar) {
        if (cbVar == null) {
            return false;
        }
        switch (cbVar.k) {
            case 1:
                return this.k == 1 && cbVar.c == this.c && cbVar.d == this.d && cbVar.b == this.b;
            case 2:
                return this.k == 2 && cbVar.i == this.i && cbVar.h == this.h && cbVar.g == this.g;
            case 3:
                return this.k == 3 && cbVar.c == this.c && cbVar.d == this.d && cbVar.b == this.b;
            case 4:
                return this.k == 4 && cbVar.c == this.c && cbVar.d == this.d && cbVar.b == this.b;
            default:
                return false;
        }
    }

    public final int b() {
        return this.d;
    }

    public final int c() {
        return this.h;
    }

    public final int d() {
        return this.i;
    }

    public final int e() {
        return this.j;
    }

    public final String toString() {
        switch (this.k) {
            case 1:
                return String.format(Locale.CHINA, "GSM lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n));
            case 2:
                return String.format(Locale.CHINA, "CDMA bid=%d, nid=%d, sid=%d, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.i), Integer.valueOf(this.h), Integer.valueOf(this.g), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n));
            case 3:
                return String.format(Locale.CHINA, "LTE lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n));
            case 4:
                return String.format(Locale.CHINA, "WCDMA lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.b), Boolean.valueOf(this.o), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n));
            default:
                return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }
}

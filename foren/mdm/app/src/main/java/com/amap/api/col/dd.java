package com.amap.api.col;

import com.autonavi.amap.mapcore.DPoint;

/* compiled from: Bounds.java */
/* loaded from: classes.dex */
public class dd {
    public final double a;
    public final double b;
    public final double c;
    public final double d;
    public final double e;
    public final double f;

    public dd(double d, double d2, double d3, double d4) {
        this.a = d;
        this.b = d3;
        this.c = d2;
        this.d = d4;
        this.e = (d + d2) / 2.0d;
        this.f = (d3 + d4) / 2.0d;
    }

    public boolean a(double d, double d2) {
        return this.a <= d && d <= this.c && this.b <= d2 && d2 <= this.d;
    }

    public boolean a(DPoint dPoint) {
        return a(dPoint.x, dPoint.y);
    }

    public boolean a(double d, double d2, double d3, double d4) {
        return d < this.c && this.a < d2 && d3 < this.d && this.b < d4;
    }

    public boolean a(dd ddVar) {
        return a(ddVar.a, ddVar.c, ddVar.b, ddVar.d);
    }

    public boolean b(dd ddVar) {
        return ddVar.a >= this.a && ddVar.c <= this.c && ddVar.b >= this.b && ddVar.d <= this.d;
    }
}

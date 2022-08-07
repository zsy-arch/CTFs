package com.amap.api.maps.model;

import com.amap.api.col.dd;
import com.autonavi.amap.mapcore.DPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: PointQuadTree.java */
/* loaded from: classes.dex */
class a {
    private final dd a;
    private final int b;
    private List<WeightedLatLng> c;
    private List<a> d;

    /* JADX INFO: Access modifiers changed from: protected */
    public a(dd ddVar) {
        this(ddVar, 0);
    }

    private a(double d, double d2, double d3, double d4, int i) {
        this(new dd(d, d2, d3, d4), i);
    }

    private a(dd ddVar, int i) {
        this.d = null;
        this.a = ddVar;
        this.b = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(WeightedLatLng weightedLatLng) {
        DPoint point = weightedLatLng.getPoint();
        if (this.a.a(point.x, point.y)) {
            a(point.x, point.y, weightedLatLng);
        }
    }

    private void a(double d, double d2, WeightedLatLng weightedLatLng) {
        if (this.d == null) {
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(weightedLatLng);
            if (this.c.size() > 50 && this.b < 40) {
                a();
            }
        } else if (d2 < this.a.f) {
            if (d < this.a.e) {
                this.d.get(0).a(d, d2, weightedLatLng);
            } else {
                this.d.get(1).a(d, d2, weightedLatLng);
            }
        } else if (d < this.a.e) {
            this.d.get(2).a(d, d2, weightedLatLng);
        } else {
            this.d.get(3).a(d, d2, weightedLatLng);
        }
    }

    private void a() {
        this.d = new ArrayList(4);
        this.d.add(new a(this.a.a, this.a.e, this.a.b, this.a.f, this.b + 1));
        this.d.add(new a(this.a.e, this.a.c, this.a.b, this.a.f, this.b + 1));
        this.d.add(new a(this.a.a, this.a.e, this.a.f, this.a.d, this.b + 1));
        this.d.add(new a(this.a.e, this.a.c, this.a.f, this.a.d, this.b + 1));
        List<WeightedLatLng> list = this.c;
        this.c = null;
        for (WeightedLatLng weightedLatLng : list) {
            a(weightedLatLng.getPoint().x, weightedLatLng.getPoint().y, weightedLatLng);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<WeightedLatLng> a(dd ddVar) {
        ArrayList arrayList = new ArrayList();
        a(ddVar, arrayList);
        return arrayList;
    }

    private void a(dd ddVar, Collection<WeightedLatLng> collection) {
        if (this.a.a(ddVar)) {
            if (this.d != null) {
                for (a aVar : this.d) {
                    aVar.a(ddVar, collection);
                }
            } else if (this.c != null) {
                if (ddVar.b(this.a)) {
                    collection.addAll(this.c);
                    return;
                }
                for (WeightedLatLng weightedLatLng : this.c) {
                    if (ddVar.a(weightedLatLng.getPoint())) {
                        collection.add(weightedLatLng);
                    }
                }
            }
        }
    }
}

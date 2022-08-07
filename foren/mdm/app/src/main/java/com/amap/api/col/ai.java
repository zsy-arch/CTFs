package com.amap.api.col;

import com.amap.api.maps.model.MultiPointItem;
import com.autonavi.amap.mapcore.IPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: QuadTree2.java */
/* loaded from: classes.dex */
public class ai {
    private final af a;
    private final int b;
    private int c;
    private List<MultiPointItem> d;
    private List<ai> e;

    /* JADX INFO: Access modifiers changed from: protected */
    public ai(af afVar) {
        this(afVar, 0);
    }

    private ai(int i, int i2, int i3, int i4, int i5) {
        this(new af(i, i2, i3, i4), i5);
    }

    private ai(af afVar, int i) {
        this.c = 30;
        this.e = null;
        this.a = afVar;
        this.b = i;
        this.c = a(this.b);
    }

    private int a(int i) {
        switch (i) {
            case 0:
                return 50;
            case 1:
                return 30;
            case 2:
            case 3:
                return 20;
            case 4:
                return 10;
            case 5:
                return 10;
            case 6:
                return 5;
            default:
                return 5;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(MultiPointItem multiPointItem) {
        IPoint iPoint = multiPointItem.getIPoint();
        if (this.a.a(iPoint.x, iPoint.y)) {
            a(iPoint.x, iPoint.y, multiPointItem);
        }
    }

    private void a(int i, int i2, MultiPointItem multiPointItem) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        if (this.d.size() <= this.c || this.b >= 40) {
            this.d.add(multiPointItem);
            return;
        }
        if (this.e == null) {
            c();
        }
        if (this.e == null) {
            return;
        }
        if (i2 < this.a.f) {
            if (i < this.a.e) {
                this.e.get(0).a(i, i2, multiPointItem);
            } else {
                this.e.get(1).a(i, i2, multiPointItem);
            }
        } else if (i < this.a.e) {
            this.e.get(2).a(i, i2, multiPointItem);
        } else {
            this.e.get(3).a(i, i2, multiPointItem);
        }
    }

    private void c() {
        this.e = new ArrayList(4);
        this.e.add(new ai(this.a.a, this.a.e, this.a.b, this.a.f, this.b + 1));
        this.e.add(new ai(this.a.e, this.a.c, this.a.b, this.a.f, this.b + 1));
        this.e.add(new ai(this.a.a, this.a.e, this.a.f, this.a.d, this.b + 1));
        this.e.add(new ai(this.a.e, this.a.c, this.a.f, this.a.d, this.b + 1));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        this.e = null;
        if (this.d != null) {
            this.d.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(af afVar, Collection<MultiPointItem> collection, double d) {
        a(afVar, collection, 1.0f, d);
    }

    private void a(af afVar, Collection<MultiPointItem> collection, float f, double d) {
        float f2;
        if (this.a.a(afVar)) {
            if (this.d != null) {
                int size = (int) (this.d.size() * f);
                for (int i = 0; i < size; i++) {
                    MultiPointItem multiPointItem = this.d.get(i);
                    if (afVar.a(multiPointItem.getIPoint())) {
                        collection.add(multiPointItem);
                    }
                }
            }
            if (d > 0.0d) {
                double d2 = ((this.a.d - this.a.b) * (this.a.c - this.a.a)) / d;
                if (d2 < 0.7d) {
                    return;
                }
                if (d2 > 1.0d) {
                    f2 = 1.0f;
                } else {
                    f2 = (float) ((((4.8188d * d2) * d2) - (d2 * 4.9339d)) + 1.1093d);
                }
            } else {
                f2 = f;
            }
            if (this.e != null) {
                for (ai aiVar : this.e) {
                    aiVar.a(afVar, collection, f2, d);
                }
            }
        }
    }

    public af b() {
        return this.a;
    }
}

package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.interfaces.IX5DateSorter;

/* loaded from: classes.dex */
public class DateSorter {
    public static int DAY_COUNT = 5;

    /* renamed from: a  reason: collision with root package name */
    public android.webkit.DateSorter f1105a;

    /* renamed from: b  reason: collision with root package name */
    public IX5DateSorter f1106b;

    static {
        a();
    }

    public DateSorter(Context context) {
        u a2 = u.a();
        if (a2 == null || !a2.b()) {
            this.f1105a = new android.webkit.DateSorter(context);
        } else {
            this.f1106b = a2.c().h(context);
        }
    }

    public static boolean a() {
        u a2 = u.a();
        return a2 != null && a2.b();
    }

    public long getBoundary(int i) {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? this.f1105a.getBoundary(i) : this.f1106b.getBoundary(i);
    }

    public int getIndex(long j) {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? this.f1105a.getIndex(j) : this.f1106b.getIndex(j);
    }

    public String getLabel(int i) {
        u a2 = u.a();
        return (a2 == null || !a2.b()) ? this.f1105a.getLabel(i) : this.f1106b.getLabel(i);
    }
}

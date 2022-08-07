package c.e.h;

import android.view.View;
import android.view.ViewParent;

/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public ViewParent f855a;

    /* renamed from: b  reason: collision with root package name */
    public ViewParent f856b;

    /* renamed from: c  reason: collision with root package name */
    public final View f857c;

    /* renamed from: d  reason: collision with root package name */
    public boolean f858d;

    /* renamed from: e  reason: collision with root package name */
    public int[] f859e;

    public f(View view) {
        this.f857c = view;
    }

    public final ViewParent a(int i) {
        if (i == 0) {
            return this.f855a;
        }
        if (i != 1) {
            return null;
        }
        return this.f856b;
    }

    public boolean b(int i) {
        return a(i) != null;
    }

    public final void a(int i, ViewParent viewParent) {
        if (i == 0) {
            this.f855a = viewParent;
        } else if (i == 1) {
            this.f856b = viewParent;
        }
    }
}

package androidx.appcompat.view.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import c.a.e.a.l;
import c.a.e.a.p;
import c.a.e.a.v;
import c.a.e.a.w;
import c.a.f.ka;

/* loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements l.b, w, AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f57a = {16842964, 16843049};

    /* renamed from: b  reason: collision with root package name */
    public l f58b;

    /* renamed from: c  reason: collision with root package name */
    public int f59c;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    @Override // c.a.e.a.w
    public void a(l lVar) {
        this.f58b = lVar;
    }

    public int getWindowAnimations() {
        return this.f59c;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((p) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        ka a2 = ka.a(context, attributeSet, f57a, i, 0);
        if (a2.f(0)) {
            setBackgroundDrawable(a2.b(0));
        }
        if (a2.f(1)) {
            setDivider(a2.b(1));
        }
        a2.f605b.recycle();
    }

    @Override // c.a.e.a.l.b
    public boolean a(p pVar) {
        return this.f58b.a(pVar, (v) null, 0);
    }
}

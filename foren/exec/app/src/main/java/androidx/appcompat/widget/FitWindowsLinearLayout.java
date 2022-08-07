package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import c.a.a.s;
import c.a.f.G;

/* loaded from: classes.dex */
public class FitWindowsLinearLayout extends LinearLayout implements G {

    /* renamed from: a  reason: collision with root package name */
    public G.a f138a;

    public FitWindowsLinearLayout(Context context) {
        super(context);
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        G.a aVar = this.f138a;
        if (aVar != null) {
            rect.top = ((s) aVar).f370a.g(rect.top);
        }
        return super.fitSystemWindows(rect);
    }

    public void setOnFitSystemWindowsListener(G.a aVar) {
        this.f138a = aVar;
    }

    public FitWindowsLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}

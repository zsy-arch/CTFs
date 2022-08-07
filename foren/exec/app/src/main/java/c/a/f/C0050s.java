package c.a.f;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import c.a.a.C;
import c.a.j;

/* renamed from: c.a.f.s  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0050s extends PopupWindow {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f640a = false;

    /* renamed from: b  reason: collision with root package name */
    public boolean f641b;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public C0050s(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        ka a2 = ka.a(context, attributeSet, j.PopupWindow, i, i2);
        if (a2.f(j.PopupWindow_overlapAnchor)) {
            boolean a3 = a2.a(j.PopupWindow_overlapAnchor, false);
            if (f640a) {
                this.f641b = a3;
            } else {
                C.a(this, a3);
            }
        }
        setBackgroundDrawable(a2.b(j.PopupWindow_android_popupBackground));
        a2.f605b.recycle();
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (f640a && this.f641b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (f640a && this.f641b) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (f640a && this.f641b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }
}

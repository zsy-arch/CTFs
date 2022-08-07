package com.tencent.open.c;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class a extends RelativeLayout {
    private static final String a = a.class.getName();
    private Rect b;
    private boolean c = false;
    private AbstractC0088a d = null;

    /* compiled from: ProGuard */
    /* renamed from: com.tencent.open.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public interface AbstractC0088a {
        void onKeyboardHidden();

        void onKeyboardShown(int i);
    }

    public a(Context context) {
        super(context);
        this.b = null;
        if (this.b == null) {
            this.b = new Rect();
        }
    }

    public void a(AbstractC0088a aVar) {
        this.d = aVar;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i2);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.b);
        int height = (activity.getWindowManager().getDefaultDisplay().getHeight() - this.b.top) - size;
        if (!(this.d == null || size == 0)) {
            if (height > 100) {
                this.d.onKeyboardShown((Math.abs(this.b.height()) - getPaddingBottom()) - getPaddingTop());
            } else {
                this.d.onKeyboardHidden();
            }
        }
        super.onMeasure(i, i2);
    }
}

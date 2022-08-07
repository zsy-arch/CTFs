package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/* loaded from: classes2.dex */
public class MyListview extends ListView {
    public MyListview(Context context) {
        super(context);
    }

    public MyListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}

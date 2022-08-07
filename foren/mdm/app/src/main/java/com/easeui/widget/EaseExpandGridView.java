package com.easeui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/* loaded from: classes.dex */
public class EaseExpandGridView extends GridView {
    public EaseExpandGridView(Context context) {
        super(context);
    }

    public EaseExpandGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}

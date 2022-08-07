package com.em.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Chronometer;

/* loaded from: classes.dex */
public class MyChronometer extends Chronometer {
    public MyChronometer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyChronometer(Context context) {
        super(context);
    }

    @Override // android.widget.Chronometer, android.view.View
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(0);
    }
}

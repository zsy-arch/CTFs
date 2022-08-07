package com.hy.frame.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

/* loaded from: classes2.dex */
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawableLeft;
        Drawable[] drawables = getCompoundDrawables();
        if (!(drawables == null || (drawableLeft = drawables[0]) == null)) {
            canvas.translate((getWidth() - ((drawableLeft.getIntrinsicWidth() + getPaint().measureText(getText().toString())) + getCompoundDrawablePadding())) / 2.0f, 0.0f);
        }
        super.onDraw(canvas);
    }
}

package com.amap.api.col;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.LinearInterpolator;

/* compiled from: BlankView.java */
/* loaded from: classes.dex */
public class ec extends View {
    public static final int a = Color.argb(255, 235, 235, 235);
    public static final int b = Color.argb(255, 21, 21, 21);
    private Paint c = new Paint();

    public ec(Context context) {
        super(context);
        this.c.setAntiAlias(true);
        this.c.setColor(a);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.c);
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
            return;
        }
        setVisibility(8);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        alphaAnimation.setDuration(200L);
        clearAnimation();
        startAnimation(alphaAnimation);
    }
}
